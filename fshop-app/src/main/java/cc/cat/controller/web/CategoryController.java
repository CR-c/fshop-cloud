package cc.cat.controller.web;

import cc.cat.common.R;


import cc.cat.entity.Category;
import cc.cat.service.CategoryService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/web/add")
    public R<String> webAdd(@RequestBody Category category){
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Category::getCategoryName,category.getCategoryName());
        Category selectOne = categoryService.getOne(lambdaQueryWrapper);
        if(selectOne==null){
            categoryService.save(category);
            return R.success("添加成功");
        }
        return R.error("错误!!!");
    }

    /**
     * 查询10个分类
     * @return
     */
    @GetMapping("/app/getPanel")
    public R<List<Category>> appGetPanel(@RequestParam(name = "status") int status ,@RequestParam(name = "fId",required = false) int fId){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(status==1){
            categoryLambdaQueryWrapper.eq(Category::getCategoryChildren,1);
            categoryLambdaQueryWrapper.eq(Category::getCategoryFather,fId);
        }
        else {
            categoryLambdaQueryWrapper.eq(Category::getCategoryChildren,0);
        }
        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);
        System.out.println("-------------------------------------------");
        System.out.println(JSON.toJSONString(list));
        System.out.println("-------------------------------------------");
        return R.success(list);
    }
}
