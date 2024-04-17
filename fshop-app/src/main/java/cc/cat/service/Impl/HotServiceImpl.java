package cc.cat.service.Impl;

import cc.cat.entity.Hot;
import cc.cat.mapper.HotMapper;
import cc.cat.service.HotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HotServiceImpl extends ServiceImpl<HotMapper, Hot> implements HotService {
}
