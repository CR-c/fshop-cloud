package cc.cat.service.Impl;

import cc.cat.entity.Picture;
import cc.cat.mapper.PictureMapper;
import cc.cat.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {
}
