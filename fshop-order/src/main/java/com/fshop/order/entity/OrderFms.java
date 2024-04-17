package com.fshop.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFms {

    //快递信息id
    private Integer fmsId;

    //用户id
    private String userId;

    //订单id
    private Integer orderId;

    //快递订单详情
    private String fmsDetails;

    //创建时间
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}

