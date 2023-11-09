package io.github.wj0410.chatroom.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wj0410.chatroom.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjie
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}