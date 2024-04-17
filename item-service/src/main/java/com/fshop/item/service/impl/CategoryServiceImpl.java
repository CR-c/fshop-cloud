package com.fshop.item.service.impl;




import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fshop.item.entity.Category;
import com.fshop.item.mapper.CategoryMapper;
import com.fshop.item.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
