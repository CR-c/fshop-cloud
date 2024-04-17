package com.fshop.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fshop.admin.entity.User;
import com.fshop.admin.mapper.UserMapper;
import com.fshop.admin.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
