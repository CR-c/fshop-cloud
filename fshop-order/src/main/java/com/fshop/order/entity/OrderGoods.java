package com.fshop.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
//import com.fshop.cart.entity.Carts;
import com.fshop.api.entity.Carts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
public class OrderGoods {

    //订单商品信息id
    private Long orderGoodsId;

    //订单id
    private Long orderId;

    //用户id
    private String userId;

    //商品的数量
    private Integer quantity;

    //商品的id
    private String clothId;

    //商品的价格
    private Double price;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //尺寸
    private String size;

    //颜色
    private String color;

    //图片
    private String img;

    //商品名称
    private String clothName;

    public OrderGoods(Long orderId, Carts carts) {
        this.orderId = orderId;
        this.userId = carts.getUserId();
        this.quantity = carts.getQuantity();
        this.clothId = carts.getClothId();
        this.price = carts.getPrice();
        this.size = carts.getSize();
        this.color = carts.getColor();
        this.img = carts.getImg();
        this.clothName = carts.getClothName();
    }

    public OrderGoods(Long orderGoodsId, Long orderId, String userId, Integer quantity, String clothId, Double price, LocalDateTime createTime, LocalDateTime updateTime, String size, String color, String img, String clothName) {
        this.orderGoodsId = orderGoodsId;
        this.orderId = orderId;
        this.userId = userId;
        this.quantity = quantity;
        this.clothId = clothId;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.size = size;
        this.color = color;
        this.img = img;
        this.clothName = clothName;
    }
}

