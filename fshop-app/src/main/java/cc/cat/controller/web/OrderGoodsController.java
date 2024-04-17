package cc.cat.controller.web;

import cc.cat.common.R;
import cc.cat.entity.Carts;
import cc.cat.entity.Order;
import cc.cat.entity.OrderGoods;
import cc.cat.service.CartsService;
import cc.cat.service.OrderGoodsService;
import cc.cat.service.OrderService;
import cc.cat.utils.HeadUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class OrderGoodsController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartsService cartsService;
    private String userId;


    @PostMapping("/app/addGoods/{orderId}")
    public R<String> app_addGoods(@RequestBody List<Carts> carts, @PathVariable Long orderId) {
        //查询订单
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        //查询对应用户的刚创建的订单
        queryWrapper.eq(Order::getOrderId, orderId);
        queryWrapper.eq(Order::getOrderStatus, Order.Create_status);
        Order order = orderService.getOne(queryWrapper);
        //进行统计
        boolean save;
        for (Carts cart : carts) {
            //如果被选中了才,能进行添加
            if (cart.isCheckFlag()) {
                OrderGoods orderGoods = new OrderGoods(orderId, cart);
                save = orderGoodsService.save(orderGoods);
                //将添加到订单中的商品在购物车中删除
                //测试,将其状态修改-->未选中
                cart.setCheckFlag(false);
                cartsService.updateByCartsId(cart);
//                cartsService.update(cart);
                //进行统计价格
                order.setOrderPrice(order.getOrderPrice() + (orderGoods.getPrice() * orderGoods.getQuantity()));
                if (!save) {
                    return R.error("添加失败");
                }
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
        userId = HeadUtils.getHeadUserId(request);
        //根据订单id和用户id查询订单商品信息
        LambdaQueryWrapper<OrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderGoods::getOrderId, orderId);
        queryWrapper.eq(OrderGoods::getUserId, userId);

        List<OrderGoods> orderGoodsList = orderGoodsService.list(queryWrapper);


        if (orderGoodsList.size() != 0) {
            return R.success(orderGoodsList);
        }
        return R.error("没有商品");
    }

    @GetMapping("/app/getOrderGoodsPage")
    public R<List<OrderGoods>> app_getGoodsPage(HttpServletRequest request) {
        //查询用户id对应的所有订单商品信息
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<OrderGoods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderGoods::getUserId, userId);
        List<OrderGoods> list = orderGoodsService.list(queryWrapper);
        if(list.size()>0){
            return R.success(list);
        }
        return R.error("空列表");
    }

}

