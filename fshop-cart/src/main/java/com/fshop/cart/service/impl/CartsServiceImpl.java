package com.fshop.cart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fshop.cart.entity.Carts;
import com.fshop.cart.mapper.CartsMapper;
import com.fshop.cart.service.CartsService;
import org.springframework.stereotype.Service;

/**
 * (Carts)表服务实现类
 *
 * @author makejava
 * @since 2024-03-18 00:04:41
 */
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts> implements CartsService {


    @Override
    public void updateByCartsId(Carts cart) {
        LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Carts::getCartsId,cart.getCartsId());
        super.update(cart,queryWrapper);
    }


}
