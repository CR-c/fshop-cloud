package cc.cat.controller.common;


import cc.cat.common.R;
import cc.cat.config.FtpConfig;
import cc.cat.entity.User;
import cc.cat.service.UserService;
import cc.cat.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("common")
public class CommonController {

    //使用application.yml中 image.url属性
//    @Value("${image.url}")
//    private String url;

//    //使用application.yml中 image.path属性
//    @Value("${spring.ftp.path}")
//    private String path;
    private String userId;
    @Autowired
    private FtpUtils ftpUtil;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private UserService userService;
    //    private SshUtils sshUtils
    @Autowired
    private SshUtils sshUtils;
    @PostMapping("/upLoad")
    public R<String> upLoad(@RequestBody MultipartFile file, HttpServletRequest httpServletRequest) {
        userId = HeadUtils.getHeadUserId(httpServletRequest);
        if(file==null || file.isEmpty()){
            return R.error("文件为空");
        }

        try {
            //获取文件名称
//            String fileName = file.getOriginalFilename();
            String fileName = fileUtils.fileRename(file);
            String path = '/'+userId+'/';
            boolean upload = ftpUtil.upload(path, fileName, file.getInputStream());
            if(upload){

                return R.success("文件上传成功");
            }else {
                return R.error("文件上传失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
