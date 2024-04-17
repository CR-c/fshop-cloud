package com.fshop.order.controller;

import com.fshop.common.R;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fshop.common.utils.HeadUtils;
import com.fshop.order.entity.Order;
import com.fshop.order.entity.OrderGoods;
import com.fshop.order.entity.dto.OrderDto;
import com.fshop.order.service.OrderGoodsService;
import com.fshop.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2024-03-20 22:31:07
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {


    /**
     * 服务对象
     */
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderGoodsService orderGoodsService;
    private String userId;
    ;

    /**
     * 创建一个订单,返回订单号
     *
     * @param request
     * @return
     */
    @GetMapping("/app/getCreateOrderId")
    public R<Long> getCreateOrderId(HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        //如果已经存在创建且为空的订单,则直接获取该订单号避免浪费
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getUserId, userId);
        orderLambdaQueryWrapper.eq(Order::getOrderStatus, Order.Create_status);
        Order oneOrder = orderService.getOne(orderLambdaQueryWrapper);
        if(oneOrder!=null){
            return R.success(oneOrder.getOrderId());
        }
        //创建订单
        Order order = new Order();
        //设置用户id
        order.setUserId(userId);
        //设置订单状态为刚创建
        order.setOrderStatus(Order.Create_status);
        boolean save = orderService.save(order);
        if (save) {
            //查询该订单的订单号
            LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Order::getUserId, userId);
            queryWrapper.eq(Order::getOrderStatus,Order.Create_status);
            Order newOrder = orderService.getOne(queryWrapper);
            return R.success(newOrder.getOrderId());
        } else {
            return R.error("创建订单失败");
        }
    }


    /**
     * 提交订单后,设置配送信息
     *
     * @param order
     * @param request
     * @return
     */
    @PostMapping("/app/updateOrder")
    public R<String> app_updateOrder(@RequestBody Order order, HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId);
        queryWrapper.eq(Order::getOrderId,order.getOrderId());
        //修改状态
        boolean update = orderService.update(order, queryWrapper);
        if(update){
            return R.success("更新成功");
        }
        return R.error("更新失败");
    }

    @GetMapping("/app/getOrder/{orderId}")
    public R<Order> app_getOrder(@PathVariable("orderId") String orderId,@RequestParam(name = "status") int status, HttpServletRequest request) {

        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId);
        queryWrapper.eq(Order::getOrderId,orderId);
        //获取对应用户不同状态的订单
        queryWrapper.eq(Order::getOrderStatus,status);
        Order order = orderService.getOne(queryWrapper);
        return R.success(order);
    }

    @DeleteMapping("/app/delOrder/{orderId}")
    public R<String> app_delOrder(@PathVariable("orderId") String orderId, HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId);
        queryWrapper.eq(Order::getOrderId,orderId);
        boolean remove = orderService.remove(queryWrapper);
        if(remove){
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }

    @PutMapping("app/cancelOrder/{orderId}")
    public R<String> app_cancelOrder(@PathVariable("orderId") String orderId, @RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String message = requestBody.get("message");
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId);
        queryWrapper.eq(Order::getOrderId,orderId);
        Order order = orderService.getOne(queryWrapper);
        //设置为取消状态
        order.setOrderStatus(Order.Cancel_status);
        //备注设置为取消原因
        order.setOrderMessage(message);
        boolean update = orderService.update(order,queryWrapper);
        if(update){
            return R.success("取消成功");
        }
        return R.error("取消失败");
    }

    @PostMapping("/app/payOrder/{orderId}")
    public R<String> app_payOrder(@PathVariable("orderId") String orderId, HttpServletRequest request) {

        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId);
        queryWrapper.eq(Order::getOrderId,orderId);
        Order order = orderService.getOne(queryWrapper);
        //支付成功设置为邮递状态
        order.setOrderStatus(Order.Before_Fms_status);
        order.setOrderPayTime(LocalDateTime.now());
        boolean update = orderService.update(order,queryWrapper);

        if(update){
            return R.success("支付成功");
        }
        return R.error("支付失败");
    }


    /**
     //分页获取
     *
     * @param page
     * @param pageSize
     * @param status
     * @param request
     * @return
     */
//    @GetMapping("/getOrderPage")
//    public R<Page> getOrderList(@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "status") int status, HttpServletRequest request) {
//
//        userId = HeadUtils.getHeadUserId(request);
//        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Order::getUserId, userId);
//        if(status!= 0){
//            queryWrapper.eq(Order::getOrderStatus, status);
//        }
//        Page<Order> pageInfo = new Page<>(page, pageSize);
//        Page<Order> paged = orderService.page(pageInfo, queryWrapper);
//        return R.success(paged);
//    }

    @GetMapping("/getOrderPage")
    public R<Page<OrderDto>> getOrderList(@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "status") int status, HttpServletRequest request) {
//        int statusNum = Integer.parseInt(status);
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId);
        if(status!= 0){
            queryWrapper.eq(Order::getOrderStatus, status);
        }
        Page<Order> pageInfo = new Page<>(page, pageSize);
        Page<Order> paged = orderService.page(pageInfo, queryWrapper);


        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order : paged.getRecords()) {

            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);

            // 查询订单明细
            LambdaQueryWrapper<OrderGoods> orderGoodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderGoodsLambdaQueryWrapper.eq(OrderGoods::getOrderId, order.getOrderId());
            List<OrderGoods> orderGoodsList = orderGoodsService.list(orderGoodsLambdaQueryWrapper);
            //添加
            orderDto.setGoodsList(orderGoodsList);
            orderDtoList.add(orderDto);
        }
        Page<OrderDto> orderDtoPage = new Page<>(page, pageSize);
        orderDtoPage.setRecords(orderDtoList);
        return R.success(orderDtoPage);
    }

}

