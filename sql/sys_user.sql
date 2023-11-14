/*
 Navicat Premium Data Transfer

 Source Server         : ubuntu_10.211.55.4
 Source Server Type    : MySQL
 Source Server Version : 50725 (5.7.25-0ubuntu0.18.04.2)
 Source Host           : 10.211.55.4:3306
 Source Schema         : chat_room

 Target Server Type    : MySQL
 Target Server Version : 50725 (5.7.25-0ubuntu0.18.04.2)
 File Encoding         : 65001

 Date: 14/11/2023 11:45:55
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`             bigint(20) NOT NULL,
    `account`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
    `real_name`      varchar(30)                                           DEFAULT NULL COMMENT '真实姓名',
    `nick_name`      varchar(30)                                           DEFAULT NULL COMMENT '用户昵称',
    `password`       varchar(100)                                          DEFAULT NULL COMMENT '密码',
    `sex`            tinyint(1) DEFAULT NULL COMMENT '性别 1.男 2.女 3.未知',
    `email`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
    `phone`          varchar(11)                                           DEFAULT NULL COMMENT '手机号',
    `avatar`         varchar(255)                                          DEFAULT NULL COMMENT '头像地址',
    `state`          tinyint(1) DEFAULT NULL COMMENT '用户状态 1.正常 2.冻结',
    `create_by_id`   bigint(20) DEFAULT NULL COMMENT '创建人ID',
    `create_by_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
    `update_by_id`   bigint(20) DEFAULT NULL COMMENT '更新人ID',
    `update_by_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
    `create_time`    datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime                                              DEFAULT NULL COMMENT '更新时间',
    `is_delete`      tinyint(1) DEFAULT '0' COMMENT '删除标识',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
