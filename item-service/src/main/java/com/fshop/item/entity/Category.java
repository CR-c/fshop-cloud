package com.fshop.item.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    //分类名字
    private String categoryName;
    //分类id
    private Integer categoryId;
    //

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    //创建用户id
    private String createUser;
    //更新用户id
    private String updateUser;
    //是否为子分类
    private int categoryChildren;
    //主分类id
    private int categoryFather;
}
