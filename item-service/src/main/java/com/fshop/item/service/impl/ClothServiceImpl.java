package com.fshop.item.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fshop.item.entity.Cloth;
import com.fshop.item.mapper.ClothMapper;
import com.fshop.item.service.ClothService;
import org.springframework.stereotype.Service;

@Service
public class ClothServiceImpl extends ServiceImpl<ClothMapper, Cloth> implements ClothService {
}
