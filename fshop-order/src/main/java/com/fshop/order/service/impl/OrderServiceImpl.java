package com.fshop.order.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fshop.order.entity.Order;
import com.fshop.order.mapper.OrderMapper;
import com.fshop.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2024-03-20 22:31:07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


}
