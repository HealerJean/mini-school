/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : d01_shiro_login

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 23/06/2019 17:41:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for scf_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `scf_sys_menu`;
CREATE TABLE `scf_sys_menu` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ref_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系统CODE',
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单调用地址',
  `method` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '调用方法（GET，POST，PUT，DELETE）',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态',
  `pid` bigint(16) unsigned DEFAULT NULL COMMENT '父级id',
  `pchain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '父链id，“,”分隔',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` int(8) DEFAULT NULL COMMENT '显示排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `front_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '前端菜单标识（前端菜单唯一标识）',
  `is_permission` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否需要权限拦截，10：需要，99：不需要',
  `menu_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单类型：0: 后端路径, 1:前端菜单，2:非展示前端菜单',
  `create_user` bigint(16) unsigned DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(16) unsigned DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统模块-菜单表';

-- ----------------------------
-- Records of scf_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `scf_sys_menu` VALUES (1, 'hlj-manager', '系统管理', '1', 'get', '10', 0, '0', NULL, NULL, NULL, NULL, '10', '1', NULL, NULL, '2019-06-04 19:28:14', NULL, NULL, '2019-06-23 15:40:09');
INSERT INTO `scf_sys_menu` VALUES (2, 'hlj-manager', '列表查询', '/api/users', 'get', '10', 5, '5,0', NULL, NULL, NULL, NULL, '10', '0', NULL, NULL, '2019-06-12 17:16:04', NULL, NULL, '2019-06-13 15:52:41');
INSERT INTO `scf_sys_menu` VALUES (3, 'hlj-manager', '修改', '/api/user/*', 'put', '10', 5, '5,0', NULL, NULL, NULL, NULL, '10', '0', NULL, NULL, '2019-06-12 17:19:08', NULL, NULL, '2019-06-13 15:52:16');
INSERT INTO `scf_sys_menu` VALUES (4, 'hlj-manager', '删除', '/api/user/*', 'delete', '10', 5, '5,0', NULL, NULL, NULL, NULL, '10', '0', NULL, NULL, '2019-06-12 17:19:21', NULL, NULL, '2019-06-13 15:52:23');
INSERT INTO `scf_sys_menu` VALUES (5, 'hlj-manager', '查询', '/api/user/*', 'get', '10', 5, '5,0', NULL, NULL, NULL, NULL, '10', '0', NULL, NULL, '2019-06-12 17:19:35', NULL, NULL, '2019-06-13 15:52:27');
INSERT INTO `scf_sys_menu` VALUES (6, 'hlj-manager', '新增', '/api/user/add', 'post', '10', 5, '5,0', NULL, NULL, NULL, NULL, '10', '0', NULL, NULL, '2019-06-12 17:21:05', NULL, NULL, '2019-06-13 15:52:34');
INSERT INTO `scf_sys_menu` VALUES (7, 'hlj-client', '用户查询', '/api/users', 'get', '10', 0, '0', NULL, NULL, NULL, NULL, '10', '0', NULL, NULL, '2019-06-23 15:45:46', NULL, NULL, '2019-06-23 15:47:11');
COMMIT;

-- ----------------------------
-- Table structure for scf_sys_ref_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `scf_sys_ref_role_menu`;
CREATE TABLE `scf_sys_ref_role_menu` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ref_role_id` bigint(16) unsigned NOT NULL COMMENT '角色ID',
  `ref_menu_id` bigint(16) unsigned NOT NULL COMMENT '菜单ID',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态',
  `create_user` bigint(16) unsigned DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(16) unsigned DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`ref_role_id`) USING BTREE COMMENT '角色id索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统模块-角色与菜单关系表';

-- ----------------------------
-- Records of scf_sys_ref_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `scf_sys_ref_role_menu` VALUES (1, 1, 1, '10', NULL, NULL, '2019-06-13 15:53:50', NULL, NULL, '2019-06-23 15:28:52');
INSERT INTO `scf_sys_ref_role_menu` VALUES (2, 1, 2, '10', NULL, NULL, '2019-06-13 15:53:58', NULL, NULL, NULL);
INSERT INTO `scf_sys_ref_role_menu` VALUES (3, 1, 3, '10', NULL, NULL, '2019-06-13 15:54:10', NULL, NULL, NULL);
INSERT INTO `scf_sys_ref_role_menu` VALUES (4, 1, 4, '10', NULL, NULL, '2019-06-13 15:54:21', NULL, NULL, NULL);
INSERT INTO `scf_sys_ref_role_menu` VALUES (5, 1, 5, '10', NULL, NULL, '2019-06-13 15:54:29', NULL, NULL, NULL);
INSERT INTO `scf_sys_ref_role_menu` VALUES (6, 1, 6, '10', NULL, NULL, '2019-06-13 15:54:35', NULL, NULL, NULL);
INSERT INTO `scf_sys_ref_role_menu` VALUES (7, 1, 7, '10', NULL, NULL, '2019-06-23 15:46:04', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for scf_sys_ref_user_role
-- ----------------------------
DROP TABLE IF EXISTS `scf_sys_ref_user_role`;
CREATE TABLE `scf_sys_ref_user_role` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ref_user_id` bigint(16) unsigned NOT NULL COMMENT '用户ID',
  `ref_role_id` bigint(16) unsigned NOT NULL COMMENT '角色ID',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态',
  `create_user` bigint(16) unsigned DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(16) unsigned DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`ref_user_id`) USING BTREE COMMENT '用户id索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统模块-用户与角色关系表';

-- ----------------------------
-- Records of scf_sys_ref_user_role
-- ----------------------------
BEGIN;
INSERT INTO `scf_sys_ref_user_role` VALUES (1, 1, 1, '10', 1, 'HealerJean', '2019-06-13 15:43:02', 1, 'HealerJean', NULL);
COMMIT;

-- ----------------------------
-- Table structure for scf_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `scf_sys_role`;
CREATE TABLE `scf_sys_role` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `ref_system_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系统CODE',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `create_user` bigint(16) unsigned DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(16) unsigned DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统模块-角色表';

-- ----------------------------
-- Records of scf_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `scf_sys_role` VALUES (1, '系统管理员', 'hlj-manager', '10', '系统管理员', 1, 'HealerJean', '2019-06-13 15:43:42', 1, 'HealerJean', '2019-06-23 15:40:19');
COMMIT;

-- ----------------------------
-- Table structure for scf_user_department
-- ----------------------------
DROP TABLE IF EXISTS `scf_user_department`;
CREATE TABLE `scf_user_department` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门名称',
  `department_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门描述',
  `pid` bigint(16) unsigned NOT NULL DEFAULT '0' COMMENT '主键',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态',
  `create_user` bigint(16) unsigned DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(16) unsigned DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统模块-部门表';

-- ----------------------------
-- Records of scf_user_department
-- ----------------------------
BEGIN;
INSERT INTO `scf_user_department` VALUES (1, '平台部', '最高指挥部', 0, '10', NULL, NULL, '2019-06-23 15:30:33', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for scf_user_info
-- ----------------------------
DROP TABLE IF EXISTS `scf_user_info`;
CREATE TABLE `scf_user_info` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `real_name` varchar(64) NOT NULL COMMENT '用户名',
  `username` varchar(64) NOT NULL COMMENT '真实姓名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `telephone` varchar(20) NOT NULL COMMENT '手机号',
  `gender` varchar(8) NOT NULL COMMENT '性别',
  `user_type` varchar(32) NOT NULL COMMENT '用户类型',
  `status` varchar(8) NOT NULL COMMENT '用户状态',
  `create_user` bigint(16) unsigned DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(16) unsigned DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新人名称',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `new_column` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of scf_user_info
-- ----------------------------
BEGIN;
INSERT INTO `scf_user_info` VALUES (1, 'healerjean', 'healerjean', 'admin', 'healerjean@gmail.com', '18888888888', 'gender', 'admin', '10', NULL, NULL, '2019-06-23 14:30:12', NULL, NULL, '2019-06-23 14:43:43', NULL);
COMMIT;

-- ----------------------------
-- Table structure for scf_user_ref_user_department
-- ----------------------------
DROP TABLE IF EXISTS `scf_user_ref_user_department`;
CREATE TABLE `scf_user_ref_user_department` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ref_user_id` bigint(16) unsigned NOT NULL DEFAULT '0' COMMENT '用户主键',
  `ref_department_id` bigint(16) unsigned NOT NULL DEFAULT '0' COMMENT '部门主键',
  `status` varchar(8) DEFAULT NULL COMMENT '是否有效10有效，99废弃',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户模块-用户-部门表';

-- ----------------------------
-- Records of scf_user_ref_user_department
-- ----------------------------
BEGIN;
INSERT INTO `scf_user_ref_user_department` VALUES (1, 1, 1, '10');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
