package cc.cat.service.Impl;

import cc.cat.entity.Category;
import cc.cat.mapper.CategoryMapper;
import cc.cat.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
