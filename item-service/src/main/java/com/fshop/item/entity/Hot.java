package com.fshop.item.entity;

import lombok.Data;

@Data
public class Hot {

    //热门名字
    private String hotName;

    //热门图片
    private String hotPicture;

    //热门注释
    private String hotText;

    //热门类型
    private int hotType;

    //热门路径
    private String hotAlt;

    //热门id
    private int hotId;
}
