package com.fshop.order.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fshop.api.client.CartClient;
import com.fshop.api.entity.Carts;
import com.fshop.common.R;
import com.fshop.common.utils.HeadUtils;
import com.fshop.common.utils.UserContext;
import com.fshop.order.entity.Order;
import com.fshop.order.entity.OrderGoods;
import com.fshop.order.service.OrderGoodsService;
import com.fshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (OrderGoods)表控制层
 *
 * @author makejava
 * @since 2024-03-21 14:34:49
 */
@Slf4j
@RestController
@RequestMapping("orderGoods")
@RequiredArgsConstructor
@RefreshScope

public class OrderGoodsController {
    /**
     * 服务对象
     */
    private final OrderGoodsService orderGoodsService;

    private final OrderService orderService;

    private final CartClient cartClient;

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    private String userId;
    //读取spring.cloud.nacos.discovery.ip的值
    @Value("${spring.cloud.nacos.discovery.ip}")
    private String nacosIp;
    @GetMapping("/test")
    public void test(){
        System.out.println(nacosIp);
    }

    /**
     * 增加商品信息到对应的购物车中
     * @param carts
     * @param orderId
     * @return
     */
    @PostMapping("/app/addGoods/{orderId}")
    public R<String> app_addGoods(@RequestBody List<Carts> carts, @PathVariable Long orderId) {
        log.info("订单添加商品");

        //查询订单
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        //查询对应用户的刚创建的订单
        queryWrapper.eq(Order::getOrderId, orderId);
        queryWrapper.eq(Order::getOrderStatus, Order.Create_status);
        Order order = orderService.getOne(queryWrapper);
//        List<ServiceInstance> instances = discoveryClient.getInstances("fshop-cart");
//        if(instances.isEmpty()){
//            return R.error("购物车服务未启动");
//        }

        //进行统计
        boolean save;
        for (Carts cart : carts) {
            //如果被选中了才,能进行添加
            if (cart.isCheckFlag()) {
                OrderGoods orderGoods = new OrderGoods(orderId, cart);
                save = orderGoodsService.save(orderGoods);
                if (!save) {
                    return R.error("添加失败");
                }
                //将添加到订单中的商品在购物车中删除
                //测试,将其状态修改-->未选中
                cart.setCheckFlag(false);
                //调用cart服务中的app_updateByCartsId
                R<String> r = cartClient.app_updateByCartsId(cart);
                if(r.getCode()!=200){
                    return R.error("添加失败");
                }
                //负载均衡简单版
//                ServiceInstance serviceInstance = instances.get(RandomUtil.randomInt(instances.size()));
//                ServiceInstance serviceInstance = instances.get(new Random().nextInt(instances.size()));
//                log.info("负载均衡选择的uri:"+serviceInstance.getUri().toString());
//                restTemplate.exchange(
//                        serviceInstance.getUri()+"/app/updateByCartsId",
//                        HttpMethod.POST,
//                        request,
//                        new ParameterizedTypeReference<R<String>>() {}
//                );

//                cartsService.updateByCartsId(cart);
                //进行统计价格
                order.setOrderPrice(order.getOrderPrice() + (orderGoods.getPrice() * orderGoods.getQuantity()));

            }
        }
        //成功添加订单商品信息,将订单的状态修改为待提交(0)
        order.setOrderStatus(Order.Submit_status);
        save = orderService.updateById(order);


        if (save) {
            return R.success("添加成功");
        }
        return R.error("添加失败");
    }

    @GetMapping("/app/getGoods/{orderId}")
    public R<List<OrderGoods>> app_getGoods(@PathVariable Long orderId, HttpServletRequest request) {
        userId = UserContext.getUser();
        //根据订单id和用户id查询订单商品信息
        LambdaQueryWrapper<OrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderGoods::getOrderId, orderId);
        queryWrapper.eq(OrderGoods::getUserId, userId);

        List<OrderGoods> orderGoodsList = orderGoodsService.list(queryWrapper);


        //清楚线程数据
        UserContext.removeUser();
        if (orderGoodsList.size() != 0) {
            return R.success(orderGoodsList);
        }
        return R.error("没有商品");
    }

    @GetMapping("/app/getOrderGoodsPage")
    public R<List<OrderGoods>> app_getGoodsPage(HttpServletRequest request) {
        //查询用户id对应的所有订单商品信息
        userId = UserContext.getUser();
        LambdaQueryWrapper<OrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderGoods::getUserId, userId);
        List<OrderGoods> list = orderGoodsService.list(queryWrapper);
        //清楚线程数据
        UserContext.removeUser();
        if(list.size()>0){
            return R.success(list);
        }
        return R.error("空列表");
    }

}

