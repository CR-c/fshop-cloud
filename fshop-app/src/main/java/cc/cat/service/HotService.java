package cc.cat.service;

import cc.cat.entity.Hot;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = RuntimeException.class)

public interface HotService extends IService<Hot> {
}
