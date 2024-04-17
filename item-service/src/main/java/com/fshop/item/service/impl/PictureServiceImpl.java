package com.fshop.item.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fshop.item.entity.Picture;
import com.fshop.item.mapper.PictureMapper;
import com.fshop.item.service.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {
}
