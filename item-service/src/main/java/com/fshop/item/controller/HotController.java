package com.fshop.item.controller;


import com.fshop.common.R;
import com.fshop.item.entity.Hot;
import com.fshop.item.service.HotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hot")
public class HotController {

    @Autowired
    private HotService hotService;

    @GetMapping("/app/getHotPanel")
    public R<List<Hot>> getHotPanel(){
        List<Hot> list = hotService.list();
        return R.success(list);
    }


}
