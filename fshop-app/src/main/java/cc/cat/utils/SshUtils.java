package cc.cat.utils;

import cc.cat.common.R;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class SshUtils {

//    @Value("${SSH.url}")
//    private String url;
//
//    @Value("${SSH.username}")
//    private String username;
//
//    @Value("${SSH.password}")
//    private String password;
//
//    @Value("${SSH.port}")
//    private int port;
//
//    public R<Session> connect(){
//        JSch jSch = new JSch();
//        Session jSchSession = null;
//        try {
//            jSchSession = jSch.getSession(username, url, port);
//            jSchSession.setPassword(password);
//            Properties config = new Properties();
//            // 去掉首次确认链接确定
//            jSchSession.setConfig("StrictHostKeyChecking", "no");
//            jSchSession.setConfig(config);
//
//            // 超时连接时间为3秒
//            jSchSession.setTimeout(3000);
//
//            jSchSession.connect();
//            Boolean flag = jSchSession.isConnected();
//            if(flag){
//                return R.success(jSchSession);
//            }
//            //获取链接结果
//
//        } catch (JSchException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        return R.error("链接失败");
//    }

}
