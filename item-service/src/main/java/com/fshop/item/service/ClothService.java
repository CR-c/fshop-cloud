package com.fshop.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.item.entity.Cloth;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = RuntimeException.class)

public interface ClothService extends IService<Cloth> {
}
