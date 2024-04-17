package cc.cat.dto;

import cc.cat.entity.Carts;
import cc.cat.entity.Order;
import cc.cat.entity.OrderGoods;
import lombok.Data;

import java.util.List;

/**
 * 订单类与对应的商品类
 */
@Data
public class OrderDto extends Order {

    private List<OrderGoods> goodsList;

}
