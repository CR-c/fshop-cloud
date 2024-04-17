package com.fshop.admin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    //微信用户id
    private String openId;
    //微信昵称
    private String nickname;
    //年龄
    private Integer age;
    //性别
    private Integer sex;
    //手机号码
    private String phone;
    //状态
    private Integer status;
    //微信头像
    private String pic;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    private String updateUser;

}
