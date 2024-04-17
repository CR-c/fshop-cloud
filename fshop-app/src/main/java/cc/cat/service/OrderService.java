package cc.cat.service;

import cc.cat.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2024-03-20 22:31:07
 */
@Transactional(rollbackFor = RuntimeException.class)

public interface OrderService extends IService<Order> {

}
