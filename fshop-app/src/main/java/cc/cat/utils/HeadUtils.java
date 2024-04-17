package cc.cat.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class HeadUtils {

    public static String getHeadUserId(HttpServletRequest request){
        //从请求头获取用户id
        String token = request.getHeader("Authorization"); //格式Bearer oc5F5666hl6vvOWS_Ej2eHTD6JX4
        if(token!=null){
            log.info("token:{}",token);
            //进行空格分割
            String[] strings = token.split(" ");
            //获取用户id
            return strings[1];
        }
        return null;
    }
}
