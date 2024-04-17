package com.fshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.order.entity.OrderGoods;
import org.springframework.transaction.annotation.Transactional;

/**
 * (OrderGoods)表服务接口
 *
 * @author makejava
 * @since 2024-03-21 14:34:49
 */
@Transactional(rollbackFor = RuntimeException.class)

public interface OrderGoodsService extends IService<OrderGoods> {

}
