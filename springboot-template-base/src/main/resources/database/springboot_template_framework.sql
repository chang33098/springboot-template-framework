/*
 Navicat MySQL Data Transfer

 Source Server         : 本地数据库1
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : springboot_template_framework

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 24/11/2019 22:59:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mybatis_test1
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_test1`;
CREATE TABLE `mybatis_test1`  (
  `stringColumn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'stringColumn',
  `intColumn` int(11) NULL DEFAULT NULL COMMENT 'intColumn',
  `doubleColumn` decimal(12, 2) NULL DEFAULT 0.00 COMMENT 'decimalColumn',
  `timestampColumn` datetime(0) NULL DEFAULT NULL COMMENT 'timestampColumn',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mybatis_test1
-- ----------------------------
INSERT INTO `mybatis_test1` VALUES ('ozlsb0iwxb5s3e7phh', 5988, 4142.89, '2019-11-10 16:32:56', 1, NULL, NULL, NULL, NULL, '0');
INSERT INTO `mybatis_test1` VALUES ('create-7lsw5fj6d406x2lvm8', 1509, 6584.59, '2019-11-12 05:49:14', 2, NULL, NULL, NULL, NULL, '0');
INSERT INTO `mybatis_test1` VALUES ('create-o8i3a0ko80eu2khrbj', 1617, 1277.56, '2019-11-12 05:52:26', 3, NULL, NULL, NULL, NULL, '0');
INSERT INTO `mybatis_test1` VALUES ('create-3rjq02qaxdbhzhx0ht', 6025, 6440.26, '2019-11-12 05:53:26', 4, NULL, '2019-11-12 05:53:26', NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记(0:未删除, 1:已删除)',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型名称',
  `dict_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型(由大写英文和下划线组成)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_code_index`(`dict_code`) USING BTREE,
  UNIQUE INDEX `name_index`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dict
-- ----------------------------
INSERT INTO `system_dict` VALUES (2, '2019-09-19 15:38:32', 0, '【页面级别】数据字典', '页面级别', 'MENU_LEVEL', '2019-10-27 03:43:22', 0, 0);
INSERT INTO `system_dict` VALUES (3, '2019-09-19 15:47:04', 0, '【用户状态】数据字典', '用户状态', 'USER_STATUS', '2019-10-25 17:14:25', 0, 0);
INSERT INTO `system_dict` VALUES (9, '2019-10-25 17:17:02', 0, '【测试字典】测试专用数据字典', '测试字典', 'TEST_DICT', '2019-10-25 17:17:19', 0, 0);
INSERT INTO `system_dict` VALUES (10, NULL, 0, 'description-vpt7vz9fyrpypqrstgna6wkwau6hd84bjqlqasgjvl1b6epl1i', 'modify-dict-name-b2ztdxqx', 'create-dict-code-v1lzb6az', NULL, NULL, NULL);
INSERT INTO `system_dict` VALUES (11, NULL, 0, 'create-description-v9w9hk816rc2glkm6l5jvqlfu6vzwbm65ba6sy4gm03aw959rs', 'create-dict-name-hjlbu465', 'create-dict-code-13srbhb0', NULL, NULL, NULL);
INSERT INTO `system_dict` VALUES (12, '2019-11-17 02:48:44', 0, 'create-description-4eqahsf04q02f1v9fvilrodgcmwfg4k3x07n2n4lhrwrv5usrg', 'create-dict-name-hgukchtl', 'create-dict-code-902lzik1', NULL, NULL, NULL);
INSERT INTO `system_dict` VALUES (16, '2019-11-17 02:58:46', 0, 'create-description-wqdeghrcl0vuby61n3hqfw9mgtrgbak3makw5jfpc1xznbj4uw', 'create-dict-name-e4zrxop5', 'create-dict-code-md7h1afn', NULL, NULL, NULL);
INSERT INTO `system_dict` VALUES (20, '2019-11-17 04:06:34', 0, 'description-jw3ms5116xjlv0ugod0nc43ctk7ceg2c928hfrac52epzezt9s', 'create-dict-name-9geoq4r3', 'dict-code-i9pg7eln', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for system_dict_option
-- ----------------------------
DROP TABLE IF EXISTS `system_dict_option`;
CREATE TABLE `system_dict_option`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` tinyint(4) NULL DEFAULT NULL COMMENT '选项代码',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选线值',
  `dict_id` bigint(20) NULL DEFAULT NULL COMMENT '字典ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKf5hik1l1ptj6s348k3twxkydn`(`dict_id`) USING BTREE,
  CONSTRAINT `FKf5hik1l1ptj6s348k3twxkydn` FOREIGN KEY (`dict_id`) REFERENCES `system_dict` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dict_option
-- ----------------------------
INSERT INTO `system_dict_option` VALUES (62, 1, '启用', 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (63, 2, '禁用', 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (67, 1, '你妈死了', 9, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (68, 2, '我是你哥哥', 9, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (69, 3, '我是你野爹', 9, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (74, 1, '父页面', 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (75, 2, '子页面', 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_dict_option` VALUES (79, NULL, NULL, 11, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (80, NULL, NULL, 11, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (81, NULL, NULL, 11, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (82, NULL, NULL, 12, NULL, '2019-11-17 02:48:45', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (83, NULL, NULL, 12, NULL, '2019-11-17 02:48:45', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (84, NULL, NULL, 12, NULL, '2019-11-17 02:48:45', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (92, 1, 'create-X8UGNZ9H', 16, NULL, '2019-11-17 02:58:46', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (93, 0, 'create-JWS5VB68', 16, NULL, '2019-11-17 02:58:46', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (94, 0, 'create-SQZQ284D', 16, NULL, '2019-11-17 02:58:46', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (109, 0, 'create-RHTZFTVZ', 20, NULL, '2019-11-17 04:06:34', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (110, 0, 'create-HE3H3KL7', 20, NULL, '2019-11-17 04:06:34', NULL, NULL, '0');
INSERT INTO `system_dict_option` VALUES (111, 0, 'create-O24V99ZW', 20, NULL, '2019-11-17 04:06:34', NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_page
-- ----------------------------
DROP TABLE IF EXISTS `system_page`;
CREATE TABLE `system_page`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块代码(由英文和下划线组成)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面作用描述',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面名称',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面访问链接',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_page
-- ----------------------------
INSERT INTO `system_page` VALUES (3, 'SYS_USER', '2019-08-14 17:08:24', '系统用户模块', '系统用户', '2019-09-20 15:03:57', '/system/users', 0, 0, '0');
INSERT INTO `system_page` VALUES (4, 'SYS_ROLE', '2019-08-14 17:09:11', '系统角色模块', '系统角色', '2019-08-24 16:12:42', '/system/role', 0, 0, '0');
INSERT INTO `system_page` VALUES (5, 'SYS_PAGE', '2019-08-14 17:09:39', '系统角色模块', '系统页面', '2019-08-24 16:12:48', '/system/page', 0, 0, '0');
INSERT INTO `system_page` VALUES (6, 'SYS_PERMISSION', '2019-08-14 17:10:05', '系统权限模块', '系统权限', '2019-08-24 16:13:01', '/system/permission', 0, 0, '0');
INSERT INTO `system_page` VALUES (7, 'TEST1', '2019-08-16 16:54:20', '测试页面1', ' 测试页面1', '2019-08-24 16:13:10', '/test1', 0, 0, '0');
INSERT INTO `system_page` VALUES (8, 'TEST2', '2019-08-16 17:00:00', '测试页面2', '测试页面2', '2019-08-24 16:13:18', '/test2', 0, 0, '0');
INSERT INTO `system_page` VALUES (12, 'MODIFY_D5YY4POK', '2019-11-16 18:29:02', 'A969ISNZAKEYQ17OR7SJV22B9I7OG22E', '8e9h6ae2td4j7u0g', NULL, '/system/test/bvn8t', NULL, NULL, '0');
INSERT INTO `system_page` VALUES (13, 'MODIFY_E8F2TS60', '2019-11-16 18:32:14', '6ORK93BGQYQRBP182YCOQLVRJX9WAHM8', '1c4mp9survw446s4', NULL, '/system/test/c0fsu', NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_page_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `system_page_permission_ref`;
CREATE TABLE `system_page_permission_ref`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `intercept_urls` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限所拦截的URL',
  `page_id` bigint(20) NULL DEFAULT NULL COMMENT '系统页面ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '系统权限ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKr9x1ofgues5iplgpjxn8uoy2s`(`page_id`) USING BTREE,
  INDEX `FKk0fg4ymthjr4fl0i9r30bchye`(`permission_id`) USING BTREE,
  CONSTRAINT `FKk0fg4ymthjr4fl0i9r30bchye` FOREIGN KEY (`permission_id`) REFERENCES `system_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKr9x1ofgues5iplgpjxn8uoy2s` FOREIGN KEY (`page_id`) REFERENCES `system_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_page_permission_ref
-- ----------------------------
INSERT INTO `system_page_permission_ref` VALUES (62, '/system/role/table', 4, 1, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (63, '/system/role/create', 4, 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (64, '/system/role/modify', 4, 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (65, '/system/role/delete', 4, 4, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (66, '/system/role/batch_del', 4, 5, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (67, '/system/page/table', 5, 1, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (68, '/system/page/create', 5, 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (69, '/system/page/modify', 5, 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (70, '/system/page/delete', 5, 4, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (71, '/system/permission/table', 6, 1, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (72, '/system/permission/create', 6, 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (73, '/system/permission/modify', 6, 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (74, '/system/permission/delete', 6, 4, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (75, '/test1/create', 7, 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (76, '/test1/modify', 7, 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (77, '/test1/delete', 7, 4, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (78, '/test1/table', 7, 1, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (79, '/test2/table', 8, 1, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (80, '/test2/create', 8, 2, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (81, '/test2/modify', 8, 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (82, '/test2/delete', 8, 4, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (98, '/system/user/table', 3, 1, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (99, '/system/user/modify', 3, 3, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (100, '/system/user/delete', 3, 4, 0, NULL, 0, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (129, '/test/create-interceptUrl1/7htxs', 13, 1, NULL, '2019-11-16 18:32:14', NULL, NULL, '1');
INSERT INTO `system_page_permission_ref` VALUES (130, '/test/create-interceptUrl2/wdp2q', 13, 2, NULL, '2019-11-16 18:32:14', NULL, NULL, '1');
INSERT INTO `system_page_permission_ref` VALUES (131, '/test/create-interceptUrl3/2mcco', 13, 3, NULL, '2019-11-16 18:32:14', NULL, NULL, '1');
INSERT INTO `system_page_permission_ref` VALUES (132, '/test/create-interceptUrl4/blwv4', 13, 4, NULL, '2019-11-16 18:32:14', NULL, NULL, '1');
INSERT INTO `system_page_permission_ref` VALUES (133, '/test/modify-interceptUrl1/dn758', 13, 1, NULL, '2019-11-16 18:33:59', NULL, NULL, '0');
INSERT INTO `system_page_permission_ref` VALUES (134, '/test/modify-interceptUrl1/shmqv', 13, 1, NULL, '2019-11-16 18:33:59', NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限代码(由英文和下划线组成)',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作用描述',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_index`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
INSERT INTO `system_permission` VALUES (1, 'LIST', '用于访问模块的数据列表页面。', '列表', 0, '2019-08-15 15:07:18', 0, '2019-10-27 03:47:51', '0');
INSERT INTO `system_permission` VALUES (2, 'CREATE', '用于创建数据的权限。', '创建', 0, '2019-08-15 15:07:49', 0, '2019-08-15 15:32:28', '0');
INSERT INTO `system_permission` VALUES (3, 'MODIFY', '用于更新 | 编辑数据的操作权限。', '编辑', 0, '2019-08-15 15:08:45', 0, '2019-08-16 14:04:23', '0');
INSERT INTO `system_permission` VALUES (4, 'DELETE', '用于删除单个数据的操作权限。', '删除', 0, '2019-08-15 15:09:22', 0, NULL, '0');
INSERT INTO `system_permission` VALUES (5, 'BATCH_DEL', '用于操作批量删除的权限。', '批量删除', 0, '2019-08-15 15:10:26', 0, NULL, '0');
INSERT INTO `system_permission` VALUES (6, 'IMPORT', '用于Excel批量导入数据的操作权限。', '导入数据', 0, '2019-08-15 15:12:04', 0, NULL, '0');
INSERT INTO `system_permission` VALUES (7, 'EXPORT', '将数据转为Excel并导出的操作权限。', '导出数据', 0, '2019-08-15 15:13:09', 0, NULL, '0');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, '2019-08-18 21:18:59', '超级管理员角色', '超级管理员', '2019-08-18 13:28:29', NULL, NULL, '0');
INSERT INTO `system_role` VALUES (3, '2019-08-30 15:43:12', '测试角色，管理系统页面模块。', '测试角色1', '2019-09-23 16:55:56', NULL, NULL, '0');
INSERT INTO `system_role` VALUES (4, '2019-08-30 15:43:29', '测试角色2，管理系统权限模块。', '测试角色2', NULL, NULL, NULL, '0');
INSERT INTO `system_role` VALUES (5, '2019-08-30 15:43:50', '测试角色3，管理系统用户模块。', '测试角色3', NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_role_menu_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu_permission_ref`;
CREATE TABLE `system_role_menu_permission_ref`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_menu_id` bigint(20) NULL DEFAULT NULL COMMENT '角色页面ID',
  `page_permission_id` bigint(20) NULL DEFAULT NULL COMMENT '页面权限ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKnhd6njfvoamyv2igcuoyaquji`(`role_menu_id`) USING BTREE,
  INDEX `FK65ut5vubrd29pf7x1dh8l36kl`(`page_permission_id`) USING BTREE,
  CONSTRAINT `FK65ut5vubrd29pf7x1dh8l36kl` FOREIGN KEY (`page_permission_id`) REFERENCES `system_page_permission_ref` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnhd6njfvoamyv2igcuoyaquji` FOREIGN KEY (`role_menu_id`) REFERENCES `system_role_menu_ref` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu_permission_ref
-- ----------------------------
INSERT INTO `system_role_menu_permission_ref` VALUES (40, 2, 98, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_role_menu_permission_ref` VALUES (41, 2, 99, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_role_menu_permission_ref` VALUES (42, 2, 100, NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_role_menu_ref
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu_ref`;
CREATE TABLE `system_role_menu_ref`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标(使用layui的图标class)',
  `menu_level` tinyint(4) NULL DEFAULT NULL COMMENT '菜单级别(1:父菜单, 2:子菜单)',
  `menu_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单代码(由菜单名称的拼音组成)',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `sort_no` tinyint(4) NULL DEFAULT NULL COMMENT '排序编号(默认为0)',
  `page_id` bigint(20) NULL DEFAULT NULL COMMENT '系统页面ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '系统角色ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID',
  `opened` tinyint(4) NULL DEFAULT 0 COMMENT '是否展开菜单 0:不展开, 1:展开',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menuCode_index`(`menu_code`) USING BTREE,
  INDEX `FK66oc01y1uxo4a53vwc6lrd894`(`page_id`) USING BTREE,
  INDEX `FKkwgmakgw5pibsjkqrd34oa451`(`role_id`) USING BTREE,
  INDEX `FKnse7k8og5by3o6d4j9so50xth`(`parent_id`) USING BTREE,
  CONSTRAINT `FK66oc01y1uxo4a53vwc6lrd894` FOREIGN KEY (`page_id`) REFERENCES `system_page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkwgmakgw5pibsjkqrd34oa451` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnse7k8og5by3o6d4j9so50xth` FOREIGN KEY (`parent_id`) REFERENCES `system_role_menu_ref` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu_ref
-- ----------------------------
INSERT INTO `system_role_menu_ref` VALUES (1, 'layui-icon-template-1', 1, 'xitongmokuai', '系统模块', 1, NULL, 1, NULL, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_role_menu_ref` VALUES (2, 'layui-icon-username', 2, 'xitongyonghu', '系统用户', 1, 3, 1, 1, 0, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_role_menu_ref` VALUES (4, 'layui-icon-template-1', 1, 'ceshimokuai1', '测试模块1', 10, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_role_menu_ref` VALUES (6, 'layui-icon-template-1', 1, 'ceshimokuai2', '测试模块2', 11, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, '0');
INSERT INTO `system_role_menu_ref` VALUES (7, 'layui-icon-template-1', 1, 'ceshimokuai3', '测试模块3', 9, NULL, 1, NULL, 0, NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户描述',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆密码',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '用户状态 1:正常, 2:已禁用',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆账号',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '系统角色ID',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `deleted` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_index`(`username`) USING BTREE,
  INDEX `FK9nobbk3ao6y0609svdo6kejyq`(`role_id`) USING BTREE,
  CONSTRAINT `FK9nobbk3ao6y0609svdo6kejyq` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, '1567443671371.jpg', '2019-09-02 17:01:45', 'chang_avalon@163.com    超级管理员', NULL, 'chang', '$2a$10$tzrRKgQ41Rrpt5QGnrhsj.IJ9Jgc1vcM/9JMLlT9Ww4O0Ihj9AD2e', '13922358340', 1, '2019-09-20 16:10:36', 'chang_avalon@163.com', 1, NULL, NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
