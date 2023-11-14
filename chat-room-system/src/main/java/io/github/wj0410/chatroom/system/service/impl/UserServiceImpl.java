package io.github.wj0410.chatroom.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.wj0410.chatroom.system.mapper.UserMapper;
import io.github.wj0410.chatroom.system.entity.User;
import io.github.wj0410.chatroom.system.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}


