package com.fshop.order.entity.dto;


import com.fshop.order.entity.Order;
import com.fshop.order.entity.OrderGoods;
import lombok.Data;

import java.util.List;

/**
 * 订单类与对应的商品类
 */
@Data
public class OrderDto extends Order {

    private List<OrderGoods> goodsList;

}
