package com.fshop.cart.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 购物车订单类
 */
@Slf4j
@Data
public class Carts{
    //购物车订单id
    private int cartsId;
    //用户id
    private String userId;
    //数量
    private int quantity;
    //衣服编号
    @JsonSerialize(using= ToStringSerializer.class)
    private String clothId;
    //单价
    private Double price;
    //尺码
    private String size;
    //颜色
    private String color;
    //图片url
    private String img;
    //衣服名称
    private String clothName;
    //选中标记
    private boolean checkFlag;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    // 省略getter和setter方法

}

