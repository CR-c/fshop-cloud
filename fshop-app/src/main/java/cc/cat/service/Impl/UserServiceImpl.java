package cc.cat.service.Impl;

import cc.cat.entity.User;
import cc.cat.mapper.UserMapper;
import cc.cat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
