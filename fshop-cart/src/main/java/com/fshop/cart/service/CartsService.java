package com.fshop.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.cart.entity.Carts;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Carts)表服务接口
 *
 * @author makejava
 * @since 2024-03-18 00:04:41
 */
@Transactional(rollbackFor = RuntimeException.class)

public interface CartsService extends IService<Carts> {

    //根据cartsId进行更新
    void updateByCartsId(Carts cart);

}
