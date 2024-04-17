package com.fshop.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fshop.admin.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = RuntimeException.class)

public interface UserService extends IService<User> {
}
