package com.fshop.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.item.entity.Hot;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = RuntimeException.class)

public interface HotService extends IService<Hot> {
}
