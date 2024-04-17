package com.fshop.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fshop.admin.entity.User;
import com.fshop.admin.service.UserService;
import com.fshop.common.R;
import com.fshop.common.utils.FileUtils;
import com.fshop.common.utils.FtpUtils;
import com.fshop.common.utils.HeadUtils;
import com.fshop.common.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private FileUtils fileUtils;

    private final static String WX_OPEN_APP_ID="wx304ecd5300aeb9db";

    private final static String WX_OPEN_APP_SECRET="b3f580ea60b0dcf0e108980d54a55a6f";

    private static String baseUrl="";



    @PostMapping("/app/updateUserInfo")
    public R<String> updateUserInfo(@RequestBody User user){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("open_id",user.getOpenId());
        boolean updateFlag = userService.update(user,userUpdateWrapper);

//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.eq(User::getOpenId,user.getOpenId());
//        boolean updateFlag = userService.update(user,userLambdaQueryWrapper);
//        boolean updateFlag = userService.saveOrUpdate(user, userLambdaQueryWrapper);
        if(updateFlag){
            return R.success("更新成功");
        }
        return R.error("更新失败");
    }

    @PostMapping("/app/updUser_pic")
    public R<String> updUser_pic(@RequestBody MultipartFile file,HttpServletRequest request){
        String userId = HeadUtils.getHeadUserId(request);
        if(file==null || file.isEmpty()){
            return R.error("文件为空");
        }
        try {
            //获取文件名称
//            String fileName = file.getOriginalFilename();
            String fileName = fileUtils.fileRename(file);
            String path = '/'+ userId +'/';
            boolean upload = ftpUtils.upload(path, fileName, file.getInputStream());
            if(upload){
                LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(User::getOpenId,userId);
                User user = userService.getOne(queryWrapper);
                user.setPic(path+fileName);
                userService.update(user,queryWrapper);
                return R.success("文件上传成功");
            }else {
                return R.error("文件上传失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/app/userInfo")
    public R<User> test(HttpServletRequest request){
        String openId = HeadUtils.getHeadUserId(request);

        if(openId!=null){
            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getOpenId, openId));
            return R.success(user);
        }
        return R.error("错误");
    }

    @GetMapping("/app/login")
    public R<String> appLogin(String code){


        StringBuffer baseAccessTokenUrl = new StringBuffer()
//                .append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("https://api.weixin.qq.com/sns/jscode2session")
                .append("?appid=%s")
                .append("&secret=%s")
                .append("&js_code=%s")
                .append("&grant_type=authorization_code");
        String accessTokenUrl = String.format(baseAccessTokenUrl.toString(),
                this.WX_OPEN_APP_ID,
                this.WX_OPEN_APP_SECRET,
                code);
        try{
            String accesstokenInfo = HttpClientUtils.get(accessTokenUrl);

            baseUrl=accesstokenInfo;
            System.out.println("accesstokenInfo:"+accesstokenInfo);
            //从返回字符串获取两个值 openid  和  access_token
            JSONObject jsonObject = JSONObject.parseObject(accesstokenInfo);
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");

            //查询用户,判断是否存在该id
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getOpenId, openid);
            User user = userService.getOne(userLambdaQueryWrapper);

            if(user==null){ //当没有这个用户时,进行注册保存
                user = new User();
//                根据openid,access_token进行获取信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //进行拼接
                String userInfoUrl = String.format(baseUserInfoUrl,openid,access_token);
                //发请求获取数据
                String resultInfo = HttpClientUtils.get(userInfoUrl);
                log.info("resultInfo:"+resultInfo);
                //json解析
                JSONObject resultUserInfoJson = JSONObject.parseObject(resultInfo);
                //获取错误代码,
                String errorCode = resultUserInfoJson.getString("errcode");
                //当没有报错,获取到实际信息的时候进行保存数据
                if (errorCode==null){
                    //用户昵称
                    String nickname = resultUserInfoJson.getString("nickname");
                    user.setNickname(nickname);
                    //用户头像
                    String headimgurl = resultUserInfoJson.getString("headimgurl");
                    user.setPic(headimgurl);
                    //用户性别
                    int sex = resultUserInfoJson.getIntValue("sex");
                    user.setSex(sex);
                }
                //用户id
                user.setOpenId(openid);
                //用户状态
                user.setStatus(1);
                //保存至数据库中
                userService.save(user);
            }
            //返回openId
            return R.success(openid);

        }catch (Exception e){
            log.info(e.toString());
            return R.error(e.toString());
        }

    }

}
