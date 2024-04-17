package cc.cat.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
//表名与mysql关键字冲突
@TableName("`order`")
public class Order {
    //已取消
    public static final int Cancel_status = -2;
    //新创建订单(没有添加商品信息的状态)
    public static final int Create_status = -1;
    //添加商品到对应订单中(待提交)
    public static final int Submit_status = 0;
    //待支付
    public static final int Pay_status = 1;
    //待发货
    public static final int Before_Fms_status = 2;
    //待收货
    public static final int Fms_status = 3;
    //待评价
    public static final int Message_status = 4;
    //已完成
    public static final int Finish_status = 5;


    @TableId(type = IdType.AUTO)
    //订单编号
    private Long orderId;

    //用户编号
    private String userId;

    //订单总价
    private Double orderPrice;

    //订单备注
    private String orderMessage;

    //订单配送时间下标
    private Integer orderDeliveryId;

    //订单商品总价
    private Double orderGoodsPrice;

    //订单运费总价
    private Double orderFmsPrice;

    //订单配送地址id
    private Integer orderAddressId;

    //订单配送地址
    private String orderAddress;

    //订单收件人
    private String orderReceiver;

    //订单收件人手机
    private String orderReceiverPhone;

    //订单状态 -2已取消,-1空订单,1交易中,2待发货,3待收货,4待评价,5已完成,
    private int orderStatus;

    //订单创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //订单更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    //订单支付时间
    private LocalDateTime orderPayTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public Integer getOrderDeliveryId() {
        return orderDeliveryId;
    }

    public void setOrderDeliveryId(Integer orderDeliveryId) {
        this.orderDeliveryId = orderDeliveryId;
    }

    public Double getOrderGoodsPrice() {
        return orderGoodsPrice;
    }

    public void setOrderGoodsPrice(Double orderGoodsPrice) {
        this.orderGoodsPrice = orderGoodsPrice;
    }

    public Double getOrderFmsPrice() {
        return orderFmsPrice;
    }

    public void setOrderFmsPrice(Double orderFmsPrice) {
        this.orderFmsPrice = orderFmsPrice;
    }

    public Integer getOrderAddressId() {
        return orderAddressId;
    }

    public void setOrderAddressId(Integer orderAddressId) {
        this.orderAddressId = orderAddressId;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(String orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    public String getOrderReceiverPhone() {
        return orderReceiverPhone;
    }

    public void setOrderReceiverPhone(String orderReceiverPhone) {
        this.orderReceiverPhone = orderReceiverPhone;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(LocalDateTime orderPayTime) {
        this.orderPayTime = orderPayTime;
    }


    //    public Order(String userId) {
//        this.userId = userId;
//    }
}

