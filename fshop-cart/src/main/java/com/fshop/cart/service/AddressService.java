package com.fshop.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.cart.entity.Address;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Address)表服务接口
 *
 * @author makejava
 * @since 2023-12-13 17:21:46
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface AddressService extends IService<Address> {

}
