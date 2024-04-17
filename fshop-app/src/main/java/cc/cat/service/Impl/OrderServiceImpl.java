package cc.cat.service.Impl;

import cc.cat.entity.Order;
import cc.cat.mapper.OrderMapper;
import cc.cat.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
