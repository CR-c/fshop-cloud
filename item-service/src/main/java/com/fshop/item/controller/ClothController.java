package com.fshop.item.controller;
import com.fshop.common.R;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fshop.item.entity.Cloth;
import com.fshop.item.service.ClothService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Slf4j

@RestController
@RequestMapping("/cloth")
public class ClothController {
    @Autowired
    private ClothService clothService;


    @PostMapping("/web/add")
    public R<String> webAdd(@RequestBody Cloth cloth){
        cloth.setSId(String.valueOf(cloth.getId()));
        clothService.save(cloth);
        return R.success("成功添加");
    }

    @GetMapping("/app/faList")
    public R<List<Cloth>> appList(@RequestParam(name = "categoryFather") int categoryFather){
        LambdaQueryWrapper<Cloth> clothLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clothLambdaQueryWrapper.eq(Cloth::getCategoryFather,categoryFather);
        List<Cloth> clothList = clothService.list(clothLambdaQueryWrapper);

        return R.success(clothList);
    }


    @GetMapping("/app/getClothInfo")
    public R<Cloth> appGetClothInfo(@RequestParam(name = "sId") String sId){
        LambdaQueryWrapper<Cloth> clothLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clothLambdaQueryWrapper.eq(Cloth::getSId,sId);
        Cloth cloth = clothService.getOne(clothLambdaQueryWrapper);
        if (cloth!=null){
            return R.success(cloth);
        }
        return R.error("没有这个商品");
    }
}
