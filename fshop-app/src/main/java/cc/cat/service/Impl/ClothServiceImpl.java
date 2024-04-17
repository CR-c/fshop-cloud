package cc.cat.service.Impl;

import cc.cat.entity.Cloth;
import cc.cat.mapper.ClothMapper;
import cc.cat.service.ClothService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClothServiceImpl extends ServiceImpl<ClothMapper, Cloth> implements ClothService {
}
