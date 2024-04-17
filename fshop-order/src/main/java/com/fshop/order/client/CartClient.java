package com.fshop.order.client;

import com.fshop.cart.entity.Carts;
import com.fshop.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("fshop-cart")

public interface CartClient {
    @PostMapping("/carts/app/updateByCartsId")
    R<String> app_updateByCartsId(@RequestBody Carts carts);

}
