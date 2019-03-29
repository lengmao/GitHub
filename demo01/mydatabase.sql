/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mydatabase

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-29 16:58:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '登录用户名',
  `parentCode` varchar(32) NOT NULL COMMENT '父菜单id',
  `url` varchar(64) DEFAULT NULL COMMENT '访问地址',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `code` varchar(32) NOT NULL,
  `description` text,
  PRIMARY KEY (`code`,`parentCode`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('cb8a2912bd964e0ca6c08cc17f65bd73', '系统管理', '-1', '', 'lnr lnr-store', 'sys01', '');
INSERT INTO `sys_menu` VALUES ('7e2babe5b2014e2ea0d1c324f733e807', '菜单管理', 'sys01', '/menu-list', 'lnr lnr-store', 'sys0101', '');
INSERT INTO `sys_menu` VALUES ('ec29ef29cc7a4bbb99ab6426b9c85df8', '用户管理', 'sys01', '/user-list', 'lnr lnr-user', 'sys0102', '');
INSERT INTO `sys_menu` VALUES ('00c3a01aea0045c2be32a2c56ef496b2', '角色管理', 'sys01', '/role-list', 'lnr lnr-store', 'sys0103', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(80) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('170a7f2452b146fc89f0040c3a6423ef', '用户', '用户拥有自身所需的菜单权限');
INSERT INTO `sys_role` VALUES ('a97f07516e1f42d1aadd8af25245278e', '管理员', '管理员拥有所有菜单权限');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `roleId` varchar(80) CHARACTER SET utf8 NOT NULL,
  `menuId` varchar(80) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('007e9a37af64405d83de2b75d581e310', '170a7f2452b146fc89f0040c3a6423ef', 'sys0103');
INSERT INTO `sys_role_menu` VALUES ('020d26c370af4dbc9b6c041fa729c42b', 'a97f07516e1f42d1aadd8af25245278e', 'sys01');
INSERT INTO `sys_role_menu` VALUES ('6662819e2fff4afd9b303af3278813a8', '170a7f2452b146fc89f0040c3a6423ef', 'sys01');
INSERT INTO `sys_role_menu` VALUES ('8a7f2885ec8d4ea393e5e8e1305e78a2', 'a97f07516e1f42d1aadd8af25245278e', 'sys0101');
INSERT INTO `sys_role_menu` VALUES ('9a9ff8bbd1c14c70a16a529c05eec3cc', 'a97f07516e1f42d1aadd8af25245278e', 'sys0103');
INSERT INTO `sys_role_menu` VALUES ('a409d611e91540bfa067db352e96c956', 'a97f07516e1f42d1aadd8af25245278e', 'sys0102');
INSERT INTO `sys_role_menu` VALUES ('bcce7405ce00404889ab446a98ad73e6', '170a7f2452b146fc89f0040c3a6423ef', 'sys0101');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('6d5f35daa9304bdd85bb19e5f801eab4', 'demo', 'fe01ce2a7fbac8fafaed7c982a04e229', 'demo@163.com', '2019-03-26 16:36:34', '1', '2019-03-26 16:37:03');
INSERT INTO `sys_user` VALUES ('a4dff746b64d4328836ed8eee7f4b82e', 'admin', '96e79218965eb72c92a549dd5a330112', 'admin@163.com', '2019-03-18 14:29:02', '1', '2019-03-18 14:29:24');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `userId` varchar(80) CHARACTER SET utf8 NOT NULL,
  `roleId` varchar(80) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('547d25fc98d641e0bdc74c1d2fe16717', '6d5f35daa9304bdd85bb19e5f801eab4', '170a7f2452b146fc89f0040c3a6423ef');
INSERT INTO `sys_user_role` VALUES ('b7108aa99d384cd38e8044753e6b3090', 'a4dff746b64d4328836ed8eee7f4b82e', 'a97f07516e1f42d1aadd8af25245278e');
INSERT INTO `sys_user_role` VALUES ('c480fb5990534b45ae6d5831739a097c', '6d5f35daa9304bdd85bb19e5f801eab4', 'a97f07516e1f42d1aadd8af25245278e');
INSERT INTO `sys_user_role` VALUES ('dcec81d02c094159ac0e26ca49e98df6', 'a4dff746b64d4328836ed8eee7f4b82e', '170a7f2452b146fc89f0040c3a6423ef');

-- ----------------------------
-- Table structure for test_data
-- ----------------------------
DROP TABLE IF EXISTS `test_data`;
CREATE TABLE `test_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of test_data
-- ----------------------------
INSERT INTO `test_data` VALUES ('1', 'www', 'www', 'www');
INSERT INTO `test_data` VALUES ('2', 'www', 'www', 'www');
INSERT INTO `test_data` VALUES ('3', 'eee', 'eee', 'eee');
