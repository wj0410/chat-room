package io.github.wj0410.chatroom.system.controller;

import io.github.wj0410.chatroom.system.dto.UserDTO;
import io.github.wj0410.chatroom.system.query.UserQuery;
import io.github.wj0410.chatroom.system.service.UserService;
import io.github.wj0410.cloudbox.easycrud.mp.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjie
 * @date 2023/11/9
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, UserDTO, UserQuery> {

}
