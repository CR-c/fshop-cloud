package cc.cat.controller.web;


import cc.cat.common.R;
import cc.cat.entity.Picture;
import cc.cat.service.PictureService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    private final static int SWIPER_Status=3;
    private final static int SWIPER_Cloth_Status=1;


    @Autowired
    private PictureService pictureService;


    @GetMapping("/getSwiper")
    public R<List<Picture>> getSwiper(){
        LambdaQueryWrapper<Picture> wrapper = new LambdaQueryWrapper<>();
        //查询轮播图数据
        wrapper.eq(Picture::getStatus,SWIPER_Status);
        List<Picture> list = pictureService.list();
        return R.success(list);
    }

    @GetMapping("/app/getClothSwiper")
    public R<List<Picture>> getClothSwiper(@RequestParam(name = "clothId") String clothId){
        LambdaQueryWrapper<Picture> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Picture::getClothId,clothId);
        wrapper.eq(Picture::getStatus,SWIPER_Cloth_Status);
        List<Picture> list = pictureService.list(wrapper);
        if (list.size()>0){
            return R.success(list);
        }
        return R.error("没有图片");
    }

}
