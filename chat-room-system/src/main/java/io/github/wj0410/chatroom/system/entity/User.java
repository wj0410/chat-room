package io.github.wj0410.chatroom.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wj0410.cloudbox.easycrud.mp.entity.BaseEntity;
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
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

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
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户状态 1.正常 2.冻结
     */
    @TableField(value = "state")
    private Integer state;

}