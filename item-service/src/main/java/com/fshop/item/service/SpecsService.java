package com.fshop.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.item.entity.Specs;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Specs)表服务接口
 *
 * @author makejava
 * @since 2023-12-14 12:56:12
 */
@Transactional(rollbackFor = RuntimeException.class)

public interface SpecsService extends IService<Specs> {

}
