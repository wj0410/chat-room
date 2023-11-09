package io.github.wj0410.chatroom.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wj0410.easycrudmp.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 * @author wangjie
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
public class User extends BaseEntity {
    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 性别 1.男 2.女 3.未知
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户状态 1.正常 2.冻结
     */
    @TableField(value = "state")
    private Integer state;
}