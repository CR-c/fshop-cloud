package com.fshop.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fshop.common.R;
import com.fshop.order.entity.Order;
import com.fshop.order.entity.OrderFms;
import com.fshop.order.service.OrderFmsService;
import com.fshop.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (OrderFms)表控制层
 *
 * @author makejava
 * @since 2024-03-26 09:03:32
 */
@Slf4j
@RestController
@RequestMapping("orderFms")
public class OrderFmsController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderFmsService orderFmsService;

    @Autowired
    private OrderService orderService;

    //模拟发货
    @PostMapping("/app/deliver")
    public R<String> app_deliver(@RequestBody OrderFms orderFms){
        log.info("模拟发货");
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getOrderId,orderFms.getOrderId());
        Order order = orderService.getOne(orderLambdaQueryWrapper);
        if(order!=null){
            orderFms.setUserId(order.getUserId());
            //正式版本不需要设置
            orderFms.setFmsDetails("北京东二环驿站已接单");
            orderFmsService.save(orderFms);
            //设置订单为待收货
            order.setOrderStatus(Order.Fms_status);
            orderService.update(order,orderLambdaQueryWrapper);
            return R.success("发货成功");
        }
        return R.error("发货失败");
    }

    @GetMapping("/app/getOrderFms/{orderId}")
    public R<List<OrderFms>> app_getOrderFms(@PathVariable("orderId") String orderId){
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getOrderId,orderId);
        Order order = orderService.getOne(orderLambdaQueryWrapper);
        //待收货,待评价,已完成可差物流信息
        if(order.getOrderStatus()>=Order.Fms_status){
            LambdaQueryWrapper<OrderFms> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByDesc(OrderFms::getCreateTime);
            queryWrapper.eq(OrderFms::getOrderId,orderId);
            List<OrderFms> list = orderFmsService.list(queryWrapper);
            if(list.size()>0){
                return R.success(list);
            }
        }else if(order.getOrderStatus()==Order.Before_Fms_status){
            return R.error("订单尚未发货");
        }
        return R.error("查询失败");

    }



}

