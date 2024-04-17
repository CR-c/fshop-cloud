package cc.cat.service;

import org.springframework.transaction.annotation.Transactional;
import cc.cat.entity.OrderFms;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (OrderFms)表服务接口
 *
 * @author makejava
 * @since 2024-03-26 09:03:32
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface OrderFmsService extends IService<OrderFms> {

}
