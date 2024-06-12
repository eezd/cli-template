/*
 Navicat Premium Data Transfer

 Source Server         : main-mysql
 Source Server Type    : MySQL
 Source Server Version : 50743 (5.7.43)
 Source Host           : onlydb.134333.xyz:33306
 Source Schema         : eezd_sys

 Target Server Type    : MySQL
 Target Server Version : 50743 (5.7.43)
 File Encoding         : 65001

 Date: 12/06/2024 17:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-03-09 18:24:01', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-03-09 18:24:01', 'anonymousUser', '2024-05-27 14:29:46', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-03-09 18:24:02', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'false', 'Y', 'admin', '2024-03-09 18:24:02', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'true', 'Y', 'admin', '2024-03-09 18:24:02', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-03-09 18:24:02', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (145, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-13 12:14:51');
INSERT INTO `sys_logininfor` VALUES (146, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-13 14:12:10');
INSERT INTO `sys_logininfor` VALUES (147, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '1', 'AuthenticationManager is not supported', '2024-05-13 15:08:21');
INSERT INTO `sys_logininfor` VALUES (148, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-13 15:26:40');
INSERT INTO `sys_logininfor` VALUES (149, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-27 13:35:08');
INSERT INTO `sys_logininfor` VALUES (150, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '1', '密码输入错误1次', '2024-05-27 15:57:38');
INSERT INTO `sys_logininfor` VALUES (151, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '1', '用户不存在/密码错误', '2024-05-27 15:57:38');
INSERT INTO `sys_logininfor` VALUES (152, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '1', '密码输入错误2次', '2024-05-27 15:57:58');
INSERT INTO `sys_logininfor` VALUES (153, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '1', '用户不存在/密码错误', '2024-05-27 15:57:58');
INSERT INTO `sys_logininfor` VALUES (154, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '1', '密码输入错误3次', '2024-05-27 15:58:03');
INSERT INTO `sys_logininfor` VALUES (155, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '1', '用户不存在/密码错误', '2024-05-27 15:58:03');
INSERT INTO `sys_logininfor` VALUES (156, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '登录成功', '2024-05-27 15:58:24');
INSERT INTO `sys_logininfor` VALUES (157, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '登录成功', '2024-05-27 15:58:51');
INSERT INTO `sys_logininfor` VALUES (158, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-27 15:58:55');
INSERT INTO `sys_logininfor` VALUES (159, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-27 16:00:16');
INSERT INTO `sys_logininfor` VALUES (160, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-27 16:01:04');
INSERT INTO `sys_logininfor` VALUES (161, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-27 16:09:23');
INSERT INTO `sys_logininfor` VALUES (162, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-27 16:10:26');
INSERT INTO `sys_logininfor` VALUES (163, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '登录成功', '2024-05-27 18:24:08');
INSERT INTO `sys_logininfor` VALUES (164, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '登录成功', '2024-05-28 11:32:02');
INSERT INTO `sys_logininfor` VALUES (165, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:45:15');
INSERT INTO `sys_logininfor` VALUES (166, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:46:10');
INSERT INTO `sys_logininfor` VALUES (167, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:46:57');
INSERT INTO `sys_logininfor` VALUES (168, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:48:28');
INSERT INTO `sys_logininfor` VALUES (169, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:52:39');
INSERT INTO `sys_logininfor` VALUES (170, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:54:08');
INSERT INTO `sys_logininfor` VALUES (171, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:55:20');
INSERT INTO `sys_logininfor` VALUES (172, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 11:56:20');
INSERT INTO `sys_logininfor` VALUES (173, 'Admin', '127.0.0.1', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2024-05-28 12:00:42');
INSERT INTO `sys_logininfor` VALUES (174, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '登录成功', '2024-05-29 15:20:49');
INSERT INTO `sys_logininfor` VALUES (175, 'Admin', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '登录成功', '2024-06-12 16:31:20');
INSERT INTO `sys_logininfor` VALUES (176, 'test', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '注册成功', '2024-06-12 16:55:12');
INSERT INTO `sys_logininfor` VALUES (177, 'test', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '注册成功', '2024-06-12 16:55:44');
INSERT INTO `sys_logininfor` VALUES (178, 'test', '127.0.0.1', '内网IP', 'Firefox 12', 'Windows 10', '0', '注册成功', '2024-06-12 16:56:53');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (5, 'log-test', 0, 'com.eezd.main.web.HelloController.testLog()', 'POST', 1, 'admin', '/log-test', '127.0.0.1', '内网IP', '{\"psw\":\"123457\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":1}', 0, '', '2024-05-13 13:04:23', 388);
INSERT INTO `sys_oper_log` VALUES (6, 'log-test', 0, 'com.eezd.main.web.HelloController.testLog()', 'POST', 1, 'admin', '/log-test', '127.0.0.1', '内网IP', '{\"psw\":\"123457\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":1}', 0, '', '2024-05-13 13:19:59', 2819);
INSERT INTO `sys_oper_log` VALUES (7, 'log-test', 0, 'com.eezd.main.web.HelloController.testLog()', 'POST', 1, 'admin', '/log-test', '127.0.0.1', '内网IP', '{\"psw\":\"123457\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":1}', 0, '', '2024-05-13 15:27:39', 367);
INSERT INTO `sys_oper_log` VALUES (8, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configId\":7,\"configKey\":\"system:config:test\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"createBy\":\"\",\"createTime\":\"2024-05-27 18:35:29.983\",\"remark\":\"\",\"updateBy\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 18:35:31', 776);
INSERT INTO `sys_oper_log` VALUES (9, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configId\":8,\"configKey\":\"system:config:test\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"createBy\":\"\",\"createTime\":\"2024-05-27 18:37:19.613\",\"remark\":\"\",\"updateBy\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 18:37:20', 839);
INSERT INTO `sys_oper_log` VALUES (10, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":8,\"configName\":\"测试系统配置2\",\"updateBy\":\"admin\",\"updateTime\":\"2024-05-27 18:39:15.645\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 18:39:16', 1428);
INSERT INTO `sys_oper_log` VALUES (11, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configId\":9,\"configKey\":\"system:config:test\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"createBy\":\"\",\"createTime\":\"2024-05-27 18:44:22.031\",\"remark\":\"\",\"updateBy\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 18:44:23', 792);
INSERT INTO `sys_oper_log` VALUES (12, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configId\":10,\"configKey\":\"system:config:test\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"createBy\":\"admin\",\"createTime\":\"2024-05-27 18:45:57.108\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 18:45:58', 766);
INSERT INTO `sys_oper_log` VALUES (13, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configKey\":\"system:config:test\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 19:08:34', 853);
INSERT INTO `sys_oper_log` VALUES (14, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configKey\":\"system.config.testApi2\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"remark\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 19:14:14', 946);
INSERT INTO `sys_oper_log` VALUES (15, '系统配置', 3, 'com.eezd.main.web.system.controller.SysConfigController.remove()', 'POST', 1, 'admin', '/system/config/remove/11,12', '127.0.0.1', '内网IP', '[11,12]', '', 1, '内置参数【system:config:test】不能删除 ', '2024-05-27 19:19:07', 247);
INSERT INTO `sys_oper_log` VALUES (16, '系统配置', 3, 'com.eezd.main.web.system.controller.SysConfigController.remove()', 'POST', 1, 'admin', '/system/config/remove/11,12', '127.0.0.1', '内网IP', '[11,12]', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-27 19:19:35', 1583);
INSERT INTO `sys_oper_log` VALUES (17, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"ApiTest@qq.com\",\"nickName\":\"测试系统用户\",\"phonenumber\":\"15888888888\",\"remark\":\"测试数据\",\"roleId\":2,\"sex\":\"1\",\"status\":\"0\",\"userName\":\"ApiTest\"}', '{\"msg\":\"新增用户\'ApiTest\'失败，手机号码已存在\",\"code\":500}', 0, '', '2024-05-28 11:32:25', 422);
INSERT INTO `sys_oper_log` VALUES (18, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"ApiTest@qq.com\",\"nickName\":\"测试系统用户\",\"phonenumber\":\"15988888888\",\"remark\":\"测试数据\",\"roleId\":2,\"sex\":\"1\",\"status\":\"0\",\"userName\":\"ApiTest\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-28 11:33:04', 1624);
INSERT INTO `sys_oper_log` VALUES (19, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"ApiTest@qq.com\",\"nickName\":\"测试系统用户\",\"phonenumber\":\"15988888888\",\"remark\":\"测试数据\",\"roleId\":2,\"sex\":\"1\",\"status\":\"0\",\"userName\":\"ApiTest\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-28 11:36:38', 1588);
INSERT INTO `sys_oper_log` VALUES (20, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"ApiTest@qq.com\",\"nickName\":\"测试系统用户\",\"phonenumber\":\"15988888888\",\"remark\":\"测试数据\",\"roleId\":2,\"sex\":\"1\",\"status\":\"0\",\"userName\":\"ApiTest\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-28 11:57:06', 1521);
INSERT INTO `sys_oper_log` VALUES (21, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, 'rawPassword cannot be null', '2024-05-28 12:03:21', 408);
INSERT INTO `sys_oper_log` VALUES (22, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, 'rawPassword cannot be null', '2024-05-28 12:03:34', 195);
INSERT INTO `sys_oper_log` VALUES (23, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, 'rawPassword cannot be null', '2024-05-28 12:21:58', 421);
INSERT INTO `sys_oper_log` VALUES (24, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\r\n### The error may exist in com/eezd/main/web/system/mapper/SysUserMapper.java (best guess)\r\n### The error may involve com.eezd.main.web.system.mapper.SysUserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_user  ( password,     create_by, create_time )  VALUES  ( ?,     ?, ? )\r\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2024-05-28 12:22:26', 1207);
INSERT INTO `sys_oper_log` VALUES (25, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, 'rawPassword cannot be null', '2024-05-28 12:23:45', 235);
INSERT INTO `sys_oper_log` VALUES (26, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, 'rawPassword cannot be null', '2024-05-28 12:25:10', 241);
INSERT INTO `sys_oper_log` VALUES (27, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '', 1, 'rawPassword cannot be null', '2024-05-28 12:26:10', 240);
INSERT INTO `sys_oper_log` VALUES (28, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{}}', 0, '', '2024-05-28 12:28:47', 245);
INSERT INTO `sys_oper_log` VALUES (29, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{}}', 0, '', '2024-05-28 12:29:18', 262);
INSERT INTO `sys_oper_log` VALUES (30, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{}}', 0, '', '2024-05-28 12:29:22', 176);
INSERT INTO `sys_oper_log` VALUES (31, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{}}', 0, '', '2024-05-28 12:35:33', 243);
INSERT INTO `sys_oper_log` VALUES (32, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"roleId\":2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"roleId\":2}}', 0, '', '2024-05-28 12:35:48', 180);
INSERT INTO `sys_oper_log` VALUES (33, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"roleId\":2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"roleId\":2}}', 0, '', '2024-05-28 12:37:14', 274);
INSERT INTO `sys_oper_log` VALUES (34, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"roleId\":2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"roleId\":2}}', 0, '', '2024-05-28 12:37:38', 259);
INSERT INTO `sys_oper_log` VALUES (35, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"roleId\":2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"roleId\":2}}', 0, '', '2024-05-28 12:37:39', 201);
INSERT INTO `sys_oper_log` VALUES (36, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"roleId\":2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"roleId\":2}}', 0, '', '2024-05-28 12:49:17', 242);
INSERT INTO `sys_oper_log` VALUES (37, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"roleId\":2}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"roleId\":2}}', 0, '', '2024-05-28 15:34:38', 235);
INSERT INTO `sys_oper_log` VALUES (38, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"userName\":\"2\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"userName\":\"2\"}}', 0, '', '2024-05-28 15:35:39', 236);
INSERT INTO `sys_oper_log` VALUES (39, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"userName\":\"2\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"userName\":\"2\"}}', 0, '', '2024-05-28 15:35:42', 179);
INSERT INTO `sys_oper_log` VALUES (40, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"createTime\":\"2024-05-29 15:21:13.852\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-29 15:21:14', 736);
INSERT INTO `sys_oper_log` VALUES (41, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configKey\":\"system.config.testApi\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"createBy\":\"admin\",\"createTime\":\"2024-05-29 15:31:54.981\",\"remark\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-29 15:31:56', 808);
INSERT INTO `sys_oper_log` VALUES (42, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{}', '', 1, '', '2024-05-29 15:33:07', 409);
INSERT INTO `sys_oper_log` VALUES (43, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{}', '', 1, '', '2024-05-29 15:33:33', 363);
INSERT INTO `sys_oper_log` VALUES (44, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{}', '', 1, '', '2024-05-29 15:35:12', 465);
INSERT INTO `sys_oper_log` VALUES (45, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{}', '', 1, '', '2024-05-29 15:35:14', 413);
INSERT INTO `sys_oper_log` VALUES (46, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":11111}', '', 1, '', '2024-05-29 15:37:37', 413);
INSERT INTO `sys_oper_log` VALUES (47, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":14,\"updateBy\":\"admin\",\"updateTime\":\"2024-05-29 15:38:43.216\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-05-29 15:38:44', 1341);
INSERT INTO `sys_oper_log` VALUES (48, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":14,\"configType\":\"Y123\",\"updateBy\":\"admin\",\"updateTime\":\"2024-05-29 15:39:08.281\"}', '', 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1\r\n### The error may exist in com/eezd/main/web/system/mapper/SysConfigMapper.java (best guess)\r\n### The error may involve com.eezd.main.web.system.mapper.SysConfigMapper.updateById-Inline\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE sys_config  SET config_type=?,   update_by=?, update_time=?  WHERE config_id=?\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1\n; Data truncation: Data too long for column \'config_type\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1', '2024-05-29 15:39:09', 970);
INSERT INTO `sys_oper_log` VALUES (49, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":14,\"configType\":\"Y123\",\"updateBy\":\"admin\",\"updateTime\":\"2024-05-29 15:40:57.501\"}', '', 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1\r\n### The error may exist in com/eezd/main/web/system/mapper/SysConfigMapper.java (best guess)\r\n### The error may involve com.eezd.main.web.system.mapper.SysConfigMapper.updateById-Inline\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE sys_config  SET config_type=?,   update_by=?, update_time=?  WHERE config_id=?\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1\n; Data truncation: Data too long for column \'config_type\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1', '2024-05-29 15:40:58', 1055);
INSERT INTO `sys_oper_log` VALUES (50, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":14,\"configType\":\"Y123\",\"updateBy\":\"admin\",\"updateTime\":\"2024-05-29 15:49:43.325\"}', '', 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1\r\n### The error may exist in com/eezd/main/web/system/mapper/SysConfigMapper.java (best guess)\r\n### The error may involve com.eezd.main.web.system.mapper.SysConfigMapper.updateById-Inline\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE sys_config  SET config_type=?,   update_by=?, update_time=?  WHERE config_id=?\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1\n; Data truncation: Data too long for column \'config_type\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'config_type\' at row 1', '2024-05-29 15:49:44', 1352);
INSERT INTO `sys_oper_log` VALUES (51, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"remark\":\"\",\"roleId\":2,\"sex\":\"2\",\"status\":\"0\",\"userId\":0,\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"password\":\"123456\",\"phonenumber\":\"15888888889\",\"remark\":\"\",\"roleId\":2,\"sex\":\"2\",\"status\":\"0\",\"userId\":0,\"userName\":\"test\"}}', 0, '', '2024-06-12 16:32:11', 484);
INSERT INTO `sys_oper_log` VALUES (52, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"createBy\":\"admin\",\"createTime\":\"2024-06-12 16:32:58.707\",\"delFlag\":\"0\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"remark\":\"\",\"roleId\":2,\"sex\":\"2\",\"status\":\"0\",\"userId\":6,\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:32:59', 1499);
INSERT INTO `sys_oper_log` VALUES (53, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"createBy\":\"admin\",\"createTime\":\"2024-06-12 16:33:29.616\",\"delFlag\":\"0\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"remark\":\"\",\"roleId\":2,\"sex\":\"2\",\"status\":\"0\",\"userId\":999,\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:33:30', 1403);
INSERT INTO `sys_oper_log` VALUES (54, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"remark\":\"\",\"sex\":\"2\",\"status\":\"0\",\"userName\":\"test\"}', '', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\r\n### The error may exist in com/eezd/main/web/system/mapper/SysUserMapper.java (best guess)\r\n### The error may involve com.eezd.main.web.system.mapper.SysUserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_user  ( user_name, nick_name, email, phonenumber, sex, avatar, password, status, del_flag,   create_by, create_time,   remark )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?,   ?, ?,   ? )\r\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2024-06-12 16:35:47', 1505);
INSERT INTO `sys_oper_log` VALUES (55, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"delFlag\":\"0\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"remark\":\"\",\"sex\":\"2\",\"status\":\"0\",\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:38:05', 1479);
INSERT INTO `sys_oper_log` VALUES (56, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"sex\":\"2\",\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:38:28', 1365);
INSERT INTO `sys_oper_log` VALUES (57, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"sex\":\"2\",\"userName\":\"test\"}', '', 1, 'rawPassword cannot be null', '2024-06-12 16:38:42', 489);
INSERT INTO `sys_oper_log` VALUES (58, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"sex\":\"2\",\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:40:10', 5188);
INSERT INTO `sys_oper_log` VALUES (59, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:40:30', 4316);
INSERT INTO `sys_oper_log` VALUES (60, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"sex\":\"2\",\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:41:37', 1683);
INSERT INTO `sys_oper_log` VALUES (61, '用户管理', 2, 'com.eezd.main.web.system.controller.SysUserController.edit()', 'POST', 1, 'admin', '/system/user/update', '127.0.0.1', '内网IP', '{\"userId\":1004}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:42:10', 4848);
INSERT INTO `sys_oper_log` VALUES (62, '用户管理', 2, 'com.eezd.main.web.system.controller.SysUserController.edit()', 'POST', 1, 'admin', '/system/user/update', '127.0.0.1', '内网IP', '{\"userId\":1004}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:43:26', 1306);
INSERT INTO `sys_oper_log` VALUES (63, '用户管理', 2, 'com.eezd.main.web.system.controller.SysUserController.edit()', 'POST', 1, 'admin', '/system/user/update', '127.0.0.1', '内网IP', '{\"sex\":\"1\",\"userId\":1004}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:43:42', 1078);
INSERT INTO `sys_oper_log` VALUES (64, '用户管理', 3, 'com.eezd.main.web.system.controller.SysUserController.remove()', 'POST', 1, 'admin', '/system/user/remove/1004', '127.0.0.1', '内网IP', '[1004]', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 16:43:58', 907);
INSERT INTO `sys_oper_log` VALUES (65, '用户管理', 3, 'com.eezd.main.web.system.controller.SysUserController.remove()', 'POST', 1, 'admin', '/system/user/remove/1004', '127.0.0.1', '内网IP', '[1004]', '{\"msg\":\"操作失败\",\"code\":500}', 0, '', '2024-06-12 16:44:03', 891);
INSERT INTO `sys_oper_log` VALUES (66, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configKey\":\"system.config.testApi\",\"configName\":\"测试系统配置\",\"configType\":\"Y\",\"configValue\":\"True\",\"remark\":\"\"}', '{\"msg\":\"新增参数\'测试系统配置\'失败，参数键名已存在\",\"code\":500}', 0, '', '2024-06-12 17:00:53', 342);
INSERT INTO `sys_oper_log` VALUES (67, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configKey\":\"system.config.testApi2\",\"configName\":\"测试系统配置\",\"configValue\":\"True2\",\"createBy\":\"admin\",\"createTime\":\"2024-06-12 17:01:03.451\",\"remark\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 17:01:04', 641);
INSERT INTO `sys_oper_log` VALUES (68, '用户管理', 1, 'com.eezd.main.web.system.controller.SysUserController.add()', 'POST', 1, 'admin', '/system/user', '127.0.0.1', '内网IP', '{\"avatar\":\"https://qmplusimg.henrongyi.top/gva_header.jpg\",\"email\":\"test@qq.com\",\"nickName\":\"测试员\",\"phonenumber\":\"15888888889\",\"sex\":\"2\",\"userName\":\"test\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 17:01:30', 1368);
INSERT INTO `sys_oper_log` VALUES (69, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":15,\"configType\":\"Y\",\"updateBy\":\"admin\",\"updateTime\":\"2024-06-12 17:02:59.93\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 17:03:00', 1202);
INSERT INTO `sys_oper_log` VALUES (70, '系统配置', 2, 'com.eezd.main.web.system.controller.SysConfigController.update()', 'POST', 1, 'admin', '/system/config/update', '127.0.0.1', '内网IP', '{\"configId\":15,\"configType\":\"N\",\"remark\":\"123\",\"updateBy\":\"admin\",\"updateTime\":\"2024-06-12 17:03:11.969\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 17:03:12', 959);
INSERT INTO `sys_oper_log` VALUES (71, '系统配置', 3, 'com.eezd.main.web.system.controller.SysConfigController.remove()', 'POST', 1, 'admin', '/system/config/remove/14,15', '127.0.0.1', '内网IP', '[14,15]', '', 1, '内置参数【system.config.testApi】不能删除 ', '2024-06-12 17:03:45', 165);
INSERT INTO `sys_oper_log` VALUES (72, '系统配置', 3, 'com.eezd.main.web.system.controller.SysConfigController.remove()', 'POST', 1, 'admin', '/system/config/remove/15', '127.0.0.1', '内网IP', '[15]', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 17:03:56', 640);
INSERT INTO `sys_oper_log` VALUES (73, '系统配置', 1, 'com.eezd.main.web.system.controller.SysConfigController.add()', 'POST', 1, 'admin', '/system/config/add', '127.0.0.1', '内网IP', '{\"configKey\":\"system.config.testApi\",\"configName\":\"测试系统配置\",\"configValue\":\"True\",\"createBy\":\"admin\",\"createTime\":\"2024-06-12 17:04:34.141\",\"remark\":\"\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, '', '2024-06-12 17:04:35', 641);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `premission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `premission_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`premission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1015 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1000, '用户查询', 'system:user:query', '');
INSERT INTO `sys_permission` VALUES (1001, '用户新增', 'system:user:add', '');
INSERT INTO `sys_permission` VALUES (1002, '用户修改', 'system:user:edit', '');
INSERT INTO `sys_permission` VALUES (1003, '用户删除', 'system:user:remove', '');
INSERT INTO `sys_permission` VALUES (1004, '用户导出', 'system:user:export', '');
INSERT INTO `sys_permission` VALUES (1005, '用户导入', 'system:user:import', '');
INSERT INTO `sys_permission` VALUES (1006, '重置密码', 'system:user:resetPwd', '');
INSERT INTO `sys_permission` VALUES (1007, '配置', 'system:role:query', '');
INSERT INTO `sys_permission` VALUES (1008, '角色新增', 'system:role:add', '');
INSERT INTO `sys_permission` VALUES (1009, '角色修改', 'system:role:update', '');
INSERT INTO `sys_permission` VALUES (1010, '角色删除', 'system:role:remove', '');
INSERT INTO `sys_permission` VALUES (1011, '角色导出', 'system:role:export', '');
INSERT INTO `sys_permission` VALUES (1012, '配置查询', 'system:config:query', '');
INSERT INTO `sys_permission` VALUES (1013, '配置修改', 'system:config:update', '');
INSERT INTO `sys_permission` VALUES (1014, '配置删除', 'system:config:remove', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '0', '0', 'admin', '2024-03-08 14:48:36', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '0', '0', 'admin', '2024-03-08 14:48:36', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `premission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `premission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (2, 1000);
INSERT INTO `sys_role_permission` VALUES (2, 1007);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '2' COMMENT '用户性别(0男 1女 2未知)',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, 'admin', '若依', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-06-12 16:31:20', 'admin', '2023-12-17 17:16:16', NULL, NULL, '管理员');
INSERT INTO `sys_user` VALUES (2, 2, 'ry', '若依', 'ry@qq.com', '15666666666', '1', '', '$2a$10$aOCYB1saD7wXW4ih4GfEOel4u9JXex5GvZLKQlQcpBKQSFuLv4lz.', '0', '0', '127.0.0.1', '2024-03-16 17:22:22', 'admin', '2023-12-17 17:16:16', 'admin', '2024-03-02 14:56:26', '测试员');

SET FOREIGN_KEY_CHECKS = 1;
