package com.fshop.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fshop.item.entity.Hot;
import com.fshop.item.mapper.HotMapper;
import com.fshop.item.service.HotService;
import org.springframework.stereotype.Service;

@Service
public class HotServiceImpl extends ServiceImpl<HotMapper, Hot> implements HotService {
}
