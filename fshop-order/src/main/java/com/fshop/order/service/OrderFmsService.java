package com.fshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.order.entity.OrderFms;
import org.springframework.transaction.annotation.Transactional;

/**
 * (OrderFms)表服务接口
 *
 * @author makejava
 * @since 2024-03-26 09:03:32
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface OrderFmsService extends IService<OrderFms> {

}
