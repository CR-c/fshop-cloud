package cc.cat.controller.web;

import cc.cat.entity.Cloth;
import cc.cat.entity.Specs;
import cc.cat.service.ClothService;
import cc.cat.service.SpecsService;
import cc.cat.common.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * (Specs)表控制层
 *
 * @author makejava
 * @since 2023-12-14 12:56:12
 */
@Slf4j
@RestController
@RequestMapping("specs")
public class SpecsController {
    /**
     * 服务对象
     */
    @Autowired
    private SpecsService specsService;

    @Autowired
    private ClothService clothService;

    @PostMapping("/web/addSpecs")
    public R<String> web_addSpecs(@RequestBody Specs specs){
        //查询是否有这件衣服
        LambdaQueryWrapper<Cloth> clothLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clothLambdaQueryWrapper.eq(Cloth::getId, specs.getClothId());
        Cloth cloth = clothService.getOne(clothLambdaQueryWrapper);
        if(cloth!=null){
            specsService.save(specs);
            return R.success("添加成功");
        }
        return R.error("添加失败");
    }
    @GetMapping("/app/specsById")
    public R<List<Specs>> app_specsById(@RequestParam("id")Long id){
        LambdaQueryWrapper<Specs> specsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        specsLambdaQueryWrapper.eq(Specs::getClothId, id);
        List<Specs> list = specsService.list(specsLambdaQueryWrapper);
        return R.success(list);
    }

    /**
     * 根据颜色和id查询数量
     * @param id
     * @param size
     * @param color
     * @return
     */
    @GetMapping("/app/specsNumBySC")
    public R<Integer> app_specsNumBySC(@RequestParam(value = "id") Long id,@RequestParam(value = "size",required = false)String size,@RequestParam(value = "color",required = false)String color){
        LambdaQueryWrapper<Specs> specsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(size!=null&&size.length()>0){
            specsLambdaQueryWrapper.eq(Specs::getSize, size);
        }
        if(color!=null&&color.length()>0){
            specsLambdaQueryWrapper.eq(Specs::getColor, color);
        }
        specsLambdaQueryWrapper.eq(Specs::getClothId, id);
        List<Specs> specsList = specsService.list(specsLambdaQueryWrapper);
        Integer sum = 0;
        for(Specs specs:specsList){
            sum+=specs.getNum();
        }
        return R.success(sum);

    }
}

