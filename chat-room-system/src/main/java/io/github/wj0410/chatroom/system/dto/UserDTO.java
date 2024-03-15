package io.github.wj0410.chatroom.system.dto;

import io.github.wj0410.chatroom.system.entity.User;
import io.github.wj0410.cloudbox.easycrud.mp.OperationEnum;
import io.github.wj0410.cloudbox.easycrud.mp.annotation.NotBlank;
import io.github.wj0410.cloudbox.easycrud.mp.annotation.Unique;
import io.github.wj0410.cloudbox.easycrud.mp.dto.BaseDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author wangjie
 */
@Data
public class UserDTO extends BaseDTO {
    /**
     * 用户名
     */
    @Unique(msg = "用户名已存在")
    @NotBlank(operations = {OperationEnum.SAVE})
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    @NotBlank(operations = {OperationEnum.SAVE})
    private String realName;

    /**
     * 密码
     */
    @NotBlank(operations = {OperationEnum.SAVE})
    private String password;

    /**
     * 性别 1.男 2.女 3.未知
     */
    @NotBlank(operations = {OperationEnum.SAVE})
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户状态 1.正常 2.冻结
     */
    private Integer state;

    @Override
    public Serializable buildEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
