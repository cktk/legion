/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云RDS
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : cc.c0c0.cc:3306
 Source Schema         : legion

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 04/11/2021 16:47:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_member
-- ----------------------------
DROP TABLE IF EXISTS `app_member`;
CREATE TABLE `app_member` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `platform` tinyint(1) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `vip_end_time` datetime(6) DEFAULT NULL,
  `vip_start_time` datetime(6) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `vip_status` tinyint(1) DEFAULT NULL,
  `birth` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `invite_code` varchar(255) DEFAULT NULL,
  `grade` int(11) unsigned DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `invite_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `invite_code` (`invite_code`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_member
-- ----------------------------
BEGIN;
INSERT INTO `app_member` VALUES (255227305549369344, '', '2020-03-25 21:14:32.000000', 0, 'admin', '2020-04-27 17:20:45.307000', 'https://ooo.0o0.ooo/2020/04/18/NmF3IP4TOoVbLf5.png', '', '18782059031', '187****9031', NULL, -1, '', 0, 1, '1251014922381430800', '2030-04-30 00:00:00.000000', '2020-04-01 00:00:00.000000', 'MEMBER', 1, NULL, '中国 北京市 北京市 朝阳区 酒仙桥路 3号 电子城．国际电子总部', '12N3VE6CK440G', 0, '30.5681733200,104.0666198700', '', NULL);
INSERT INTO `app_member` VALUES (1251783645962833920, 'admin', '2020-04-19 16:04:12.000000', 0, 'admin', '2021-10-13 17:41:03.442000', 'https://ooo.0o0.ooo/2020/04/18/NmF3IP4TOoVbLf5.png', '', '18782059092', '187****9092', NULL, 1, '', 0, 1, '1251783646004776960', '2020-04-07 00:00:00.000000', '2020-04-01 00:00:00.000000', 'MEMBER', 2, NULL, '中国 北京市 北京市 朝阳区 酒仙桥路 3号 电子城．国际电子总部', '12NPQJ01S4400', 0, '30.5681733200,104.0666198700', '1251014922381430800', NULL);
INSERT INTO `app_member` VALUES (1251783729291071489, 'admin', '2020-04-19 16:04:32.000000', 0, 'admin', '2020-04-27 22:03:48.509000', 'https://ooo.0o0.ooo/2020/04/18/NmF3IP4TOoVbLf5.png', '', '18782059033', '187****9093', NULL, 2, '', 0, 0, '1251783729303654400', NULL, NULL, 'MEMBER', 0, NULL, '中国 北京市 北京市 朝阳区 酒仙桥路 3号 电子城．国际电子总部', '12NPQLDKC4400', 0, '30.5681733200,104.0666198700', '1251014922381430800', NULL);
COMMIT;

-- ----------------------------
-- Table structure for b_refuge_info
-- ----------------------------
DROP TABLE IF EXISTS `b_refuge_info`;
CREATE TABLE `b_refuge_info` (
  `id` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '避难所ID',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '避难所名称',
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '避难所地址',
  `admin_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理员名字',
  `admin_id` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理员id',
  `phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理员电话',
  `condition` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '有无物资状况',
  `p_num` int(11) DEFAULT NULL COMMENT '避难所总人数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of b_refuge_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_act_business
-- ----------------------------
DROP TABLE IF EXISTS `t_act_business`;
CREATE TABLE `t_act_business` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `proc_def_id` varchar(255) DEFAULT NULL,
  `proc_inst_id` varchar(255) DEFAULT NULL,
  `result` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `table_id` bigint(20) unsigned DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_business
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_act_category
-- ----------------------------
DROP TABLE IF EXISTS `t_act_category`;
CREATE TABLE `t_act_category` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_parent` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) unsigned NOT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_act_model
-- ----------------------------
DROP TABLE IF EXISTS `t_act_model`;
CREATE TABLE `t_act_model` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `model_key` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_model
-- ----------------------------
BEGIN;
INSERT INTO `t_act_model` VALUES (1, 'admin', '2021-10-13 16:44:19', 0, 'admin', '2021-10-13 16:44:19', '123', '123', '123', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_act_node
-- ----------------------------
DROP TABLE IF EXISTS `t_act_node`;
CREATE TABLE `t_act_node` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `node_id` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `relate_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_node
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_act_process
-- ----------------------------
DROP TABLE IF EXISTS `t_act_process`;
CREATE TABLE `t_act_process` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `category_id` bigint(20) unsigned DEFAULT NULL,
  `deployment_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `diagram_name` varchar(255) DEFAULT NULL,
  `latest` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `process_key` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `xml_name` varchar(255) DEFAULT NULL,
  `business_table` varchar(255) DEFAULT NULL,
  `route_name` varchar(255) DEFAULT NULL,
  `all_user` bit(1) DEFAULT NULL,
  `category_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_process
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_act_starter
-- ----------------------------
DROP TABLE IF EXISTS `t_act_starter`;
CREATE TABLE `t_act_starter` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `process_def_id` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_act_starter
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_auto_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_auto_chat`;
CREATE TABLE `t_auto_chat` (
  `id` bigint(20) unsigned NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `keywords` text,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `bad` int(11) DEFAULT NULL,
  `good` int(11) DEFAULT NULL,
  `content` text,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `evaluable` bit(1) DEFAULT NULL,
  `hot` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `search` (`title`,`keywords`) /*!50100 WITH PARSER `ngram` */
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auto_chat
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `home_uri` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_client
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` bigint(20) unsigned NOT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_parent` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
BEGIN;
INSERT INTO `t_department` VALUES (40322777781112832, '', '2018-08-10 20:40:40', 0, '', '2018-08-11 00:03:06', 0, 1.00, 0, '总部', b'1');
INSERT INTO `t_department` VALUES (40322811096469504, '', '2018-08-10 20:40:48', 0, '', '2018-08-11 00:27:05', 40322777781112832, 1.00, 0, '技术部', b'1');
INSERT INTO `t_department` VALUES (40322852833988608, '', '2018-08-10 20:40:58', 0, '', '2018-08-11 01:29:42', 40322811096469504, 1.00, 0, '研发中心', NULL);
INSERT INTO `t_department` VALUES (40327204755738624, '', '2018-08-10 20:58:15', 0, '', '2018-08-10 22:02:15', 40322811096469504, 2.00, 0, '大数据', NULL);
INSERT INTO `t_department` VALUES (40327253309001728, '', '2018-08-10 20:58:27', 0, '', '2018-08-11 17:26:38', 40322811096469504, 3.00, -1, '人工智障', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_department_header
-- ----------------------------
DROP TABLE IF EXISTS `t_department_header`;
CREATE TABLE `t_department_header` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `department_id` bigint(20) unsigned DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department_header
-- ----------------------------
BEGIN;
INSERT INTO `t_department_header` VALUES (1254427833757995008, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 0, 682265633886208);
INSERT INTO `t_department_header` VALUES (1254427833757995009, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 0, 16739222421508096);
INSERT INTO `t_department_header` VALUES (1254427833757995010, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 1, 16739222421508096);
COMMIT;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
BEGIN;
INSERT INTO `t_dict` VALUES (75135930788220928, 'admin', '2018-11-14 22:15:43', 0, 'admin', '2018-11-27 01:39:06', '', '性别', 'sex', 0.00);
INSERT INTO `t_dict` VALUES (75383353938808832, 'admin', '2018-11-15 14:38:53', 0, 'admin', '2021-10-13 16:37:41', '1', '消息类型', 'message_type', 1.00);
INSERT INTO `t_dict` VALUES (75388696739713024, 'admin', '2018-11-15 15:00:07', 0, 'admin', '2018-11-27 01:39:22', '', '按钮权限类型', 'permission_type', 2.00);
INSERT INTO `t_dict` VALUES (75392985935646720, 'admin', '2018-11-15 15:17:10', 0, 'admin', '2018-11-27 01:39:29', '', '腾讯云对象存储区域', 'tencent_bucket_region', 3.00);
INSERT INTO `t_dict` VALUES (79717732567748608, 'admin', '2018-11-27 13:42:10', 0, 'admin', '2018-11-27 13:42:10', '', '流程节点类型', 'process_node_type', 4.00);
INSERT INTO `t_dict` VALUES (81843858882695168, 'admin', '2018-12-03 10:30:38', 0, 'admin', '2018-12-03 10:30:49', '', '优先级', 'priority', 5.00);
INSERT INTO `t_dict` VALUES (82236987314016256, 'admin', '2018-12-04 12:32:47', 0, 'admin', '2018-12-04 12:32:47', '', '业务表', 'business_table', 6.00);
INSERT INTO `t_dict` VALUES (82236987314016257, 'admin', '2018-12-04 12:32:47', 0, 'admin', '2018-12-04 12:32:47', '', '业务表单路由', 'business_form', 7.00);
INSERT INTO `t_dict` VALUES (99020862912466944, 'admin', '2019-01-19 20:05:54', 0, 'admin', '2019-01-19 20:06:10', '', '请假类型', 'leave_type', 8.00);
COMMIT;

-- ----------------------------
-- Table structure for t_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_data`;
CREATE TABLE `t_dict_data` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dict_id` bigint(20) unsigned DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sort_order` (`sort_order`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `t_dict_data` VALUES (75158227712479232, 'admin', '2018-11-14 23:44:19', 0, 'admin', '2021-10-13 16:37:24', '1', 75135930788220928, 0.00, 0, '男', '男');
INSERT INTO `t_dict_data` VALUES (75159254272577536, 'admin', '2018-11-14 23:48:24', 0, 'admin', '2019-04-01 23:53:17', '', 75135930788220928, 1.00, 0, '女', '女');
INSERT INTO `t_dict_data` VALUES (75159898425397248, 'admin', '2018-11-14 23:50:57', 0, 'admin', '2019-04-01 23:53:22', '', 75135930788220928, 2.00, -1, '保密', '保密');
INSERT INTO `t_dict_data` VALUES (75385648017575936, 'admin', '2018-11-15 14:48:00', 0, 'admin', '2019-04-02 00:10:36', '', 75383353938808832, 0.00, 0, '系统公告', '系统公告');
INSERT INTO `t_dict_data` VALUES (75385706913992704, 'admin', '2018-11-15 14:48:14', 0, 'admin', '2019-04-02 00:10:32', '', 75383353938808832, 1.00, 0, '提醒', '提醒');
INSERT INTO `t_dict_data` VALUES (75385774274514944, 'admin', '2018-11-15 14:48:30', 0, 'admin', '2019-04-02 00:10:28', '', 75383353938808832, 2.00, 0, '私信', '私信');
INSERT INTO `t_dict_data` VALUES (75390787835138048, 'admin', '2018-11-15 15:08:26', 0, 'admin', '2018-11-15 15:08:26', '', 75388696739713024, 0.00, 0, '查看操作(view)', 'view');
INSERT INTO `t_dict_data` VALUES (75390886501945344, 'admin', '2018-11-15 15:08:49', 0, 'admin', '2018-11-15 15:08:57', '', 75388696739713024, 1.00, 0, '添加操作(add)', 'add');
INSERT INTO `t_dict_data` VALUES (75390993939042304, 'admin', '2018-11-15 15:09:15', 0, 'admin', '2018-11-15 15:09:15', '', 75388696739713024, 2.00, 0, '编辑操作(edit)', 'edit');
INSERT INTO `t_dict_data` VALUES (75391067532300288, 'admin', '2018-11-15 15:09:32', 0, 'admin', '2018-11-15 15:09:32', '', 75388696739713024, 3.00, 0, '删除操作(delete)', 'delete');
INSERT INTO `t_dict_data` VALUES (75391126902673408, 'admin', '2018-11-15 15:09:46', 0, 'admin', '2018-11-15 15:09:46', '', 75388696739713024, 4.00, 0, '清空操作(clear)', 'clear');
INSERT INTO `t_dict_data` VALUES (75391192883269632, 'admin', '2018-11-15 15:10:02', 0, 'admin', '2018-11-15 15:10:02', '', 75388696739713024, 5.00, 0, '启用操作(enable)', 'enable');
INSERT INTO `t_dict_data` VALUES (75391251024711680, 'admin', '2018-11-15 15:10:16', 0, 'admin', '2018-11-15 15:10:16', '', 75388696739713024, 6.00, 0, '禁用操作(disable)', 'disable');
INSERT INTO `t_dict_data` VALUES (75391297124306944, 'admin', '2018-11-15 15:10:27', 0, 'admin', '2018-11-15 15:10:27', '', 75388696739713024, 7.00, 0, '搜索操作(search)', 'search');
INSERT INTO `t_dict_data` VALUES (75391343379091456, 'admin', '2018-11-15 15:10:38', 0, 'admin', '2018-11-15 15:10:38', '', 75388696739713024, 8.00, 0, '上传文件(upload)', 'upload');
INSERT INTO `t_dict_data` VALUES (75391407526776832, 'admin', '2018-11-15 15:10:53', 0, 'admin', '2018-11-15 15:10:53', '', 75388696739713024, 9.00, 0, '导出操作(output)', 'output');
INSERT INTO `t_dict_data` VALUES (75391475042488320, 'admin', '2018-11-15 15:11:09', 0, 'admin', '2018-11-15 15:11:09', '', 75388696739713024, 10.00, 0, '导入操作(input)', 'input');
INSERT INTO `t_dict_data` VALUES (75391522182270976, 'admin', '2018-11-15 15:11:21', 0, 'admin', '2018-11-15 15:11:21', '', 75388696739713024, 11.00, 0, '分配权限(editPerm)', 'editPerm');
INSERT INTO `t_dict_data` VALUES (75391576364290048, 'admin', '2018-11-15 15:11:34', 0, 'admin', '2018-11-15 15:11:34', '', 75388696739713024, 12.00, 0, '设为默认(setDefault)', 'setDefault');
INSERT INTO `t_dict_data` VALUES (75391798033256448, 'admin', '2018-11-15 15:12:26', 0, 'admin', '2018-11-15 15:12:26', '', 75388696739713024, 13.00, 0, '其他操作(other)', 'other');
INSERT INTO `t_dict_data` VALUES (75393605291741184, 'admin', '2018-11-15 15:19:37', 0, 'admin', '2018-11-15 15:19:37', '', 75392985935646720, 0.00, 0, '北京一区（华北）', 'ap-beijing-1');
INSERT INTO `t_dict_data` VALUES (75393681254780928, 'admin', '2018-11-15 15:19:55', 0, 'admin', '2018-11-15 15:19:55', '', 75392985935646720, 1.00, 0, '北京', 'ap-beijing');
INSERT INTO `t_dict_data` VALUES (75393767619694592, 'admin', '2018-11-15 15:20:16', 0, 'admin', '2018-11-15 15:20:16', '', 75392985935646720, 2.00, 0, '上海（华东）', 'ap-shanghai');
INSERT INTO `t_dict_data` VALUES (75393851484803072, 'admin', '2018-11-15 15:20:36', 0, 'admin', '2018-11-15 15:20:36', '', 75392985935646720, 3.00, 0, '广州（华南）', 'ap-guangzhou');
INSERT INTO `t_dict_data` VALUES (75393961887272960, 'admin', '2018-11-15 15:21:02', 0, 'admin', '2018-11-15 15:21:02', '', 75392985935646720, 4.00, 0, '成都（西南）', 'ap-chengdu');
INSERT INTO `t_dict_data` VALUES (75394053969022976, 'admin', '2018-11-15 15:21:24', 0, 'admin', '2018-11-15 15:21:24', '', 75392985935646720, 5.00, 0, '重庆', 'ap-chongqing');
INSERT INTO `t_dict_data` VALUES (75394122474590208, 'admin', '2018-11-15 15:21:41', 0, 'admin', '2018-11-15 15:21:41', '', 75392985935646720, 6.00, 0, '新加坡', 'ap-singapore');
INSERT INTO `t_dict_data` VALUES (75394186202845184, 'admin', '2018-11-15 15:21:56', 0, 'admin', '2018-11-15 15:21:56', '', 75392985935646720, 7.00, 0, '香港', 'ap-hongkong');
INSERT INTO `t_dict_data` VALUES (75394254255427584, 'admin', '2018-11-15 15:22:12', 0, 'admin', '2018-11-15 15:22:12', '', 75392985935646720, 8.00, 0, '多伦多', 'na-toronto');
INSERT INTO `t_dict_data` VALUES (75394309125312512, 'admin', '2018-11-15 15:22:25', 0, 'admin', '2018-11-15 15:22:25', '', 75392985935646720, 9.00, 0, '法兰克福', 'eu-frankfurt');
INSERT INTO `t_dict_data` VALUES (75394367044456448, 'admin', '2018-11-15 15:22:39', 0, 'admin', '2018-11-15 15:22:39', '', 75392985935646720, 10.00, 0, '孟买', 'ap-mumbai');
INSERT INTO `t_dict_data` VALUES (75394448523005952, 'admin', '2018-11-15 15:22:58', 0, 'admin', '2018-11-15 15:22:58', '', 75392985935646720, 11.00, 0, '首尔', 'ap-seoul');
INSERT INTO `t_dict_data` VALUES (75394604765024256, 'admin', '2018-11-15 15:23:36', 0, 'admin', '2018-11-15 15:23:36', '', 75392985935646720, 12.00, 0, '硅谷', 'na-siliconvalley');
INSERT INTO `t_dict_data` VALUES (75394659299364864, 'admin', '2018-11-15 15:23:49', 0, 'admin', '2018-11-15 15:23:49', '', 75392985935646720, 13.00, 0, '弗吉尼亚', 'na-ashburn');
INSERT INTO `t_dict_data` VALUES (75394705700950016, 'admin', '2018-11-15 15:24:00', 0, 'admin', '2018-11-15 15:24:00', '', 75392985935646720, 14.00, 0, '曼谷', 'ap-bangkok');
INSERT INTO `t_dict_data` VALUES (75394759287377920, 'admin', '2018-11-15 15:24:12', 0, 'admin', '2018-11-15 15:24:12', '', 75392985935646720, 15.00, 0, '莫斯科', 'eu-moscow');
INSERT INTO `t_dict_data` VALUES (79717808262352896, 'admin', '2018-11-27 13:42:28', 0, 'admin', '2018-11-27 13:42:28', '', 79717732567748608, 0.00, 0, '开始节点', '0');
INSERT INTO `t_dict_data` VALUES (79717858308788224, 'admin', '2018-11-27 13:42:40', 0, 'admin', '2018-11-27 13:42:40', '', 79717732567748608, 1.00, 0, '审批节点', '1');
INSERT INTO `t_dict_data` VALUES (79717920061526016, 'admin', '2018-11-27 13:42:54', 0, 'admin', '2018-12-08 20:35:39', '', 79717732567748608, 2.00, 0, '结束节点', '2');
INSERT INTO `t_dict_data` VALUES (81843987719131136, 'admin', '2018-12-03 10:31:08', 0, 'admin', '2018-12-03 10:31:08', '', 81843858882695168, 0.00, 0, '普通', '0');
INSERT INTO `t_dict_data` VALUES (81844044015079424, 'admin', '2018-12-03 10:31:22', 0, 'admin', '2018-12-03 10:31:22', '', 81843858882695168, 1.00, 0, '重要', '1');
INSERT INTO `t_dict_data` VALUES (81844100705292288, 'admin', '2018-12-03 10:31:35', 0, 'admin', '2018-12-03 10:31:35', '', 81843858882695168, 2.00, 0, '紧急', '2');
INSERT INTO `t_dict_data` VALUES (82237106587439104, 'admin', '2018-12-04 12:33:15', 0, 'admin', '2018-12-04 12:33:15', '', 82236987314016256, 0.00, 0, '请假申请表', 't_leave');
INSERT INTO `t_dict_data` VALUES (82237106587439105, 'admin', '2018-12-04 12:33:15', 0, 'admin', '2018-12-04 12:33:15', '', 82236987314016257, 0.00, 0, '请假申请表', 'leave');
INSERT INTO `t_dict_data` VALUES (99020985985929216, 'admin', '2019-01-19 20:06:23', 0, 'admin', '2019-04-01 23:55:48', '', 99020862912466944, 0.00, 0, '年假', '年假');
INSERT INTO `t_dict_data` VALUES (99021020739932160, 'admin', '2019-01-19 20:06:32', 0, 'admin', '2019-04-01 23:55:52', '', 99020862912466944, 1.00, 0, '事假', '事假');
INSERT INTO `t_dict_data` VALUES (99021195566911488, 'admin', '2019-01-19 20:07:13', 0, 'admin', '2019-04-01 23:55:56', '', 99020862912466944, 2.00, 0, '病假', '病假');
INSERT INTO `t_dict_data` VALUES (99021242476007424, 'admin', '2019-01-19 20:07:24', 0, 'admin', '2019-04-01 23:56:01', '', 99020862912466944, 3.00, 0, '调休', '调休');
INSERT INTO `t_dict_data` VALUES (99021300730695680, 'admin', '2019-01-19 20:07:38', 0, 'admin', '2019-04-01 23:56:05', '', 99020862912466944, 4.00, 0, '产假', '产假');
INSERT INTO `t_dict_data` VALUES (99021341889400832, 'admin', '2019-01-19 20:07:48', 0, 'admin', '2019-04-01 23:56:10', '', 99020862912466944, 5.00, 0, '陪产假', '陪产假');
INSERT INTO `t_dict_data` VALUES (99021382393794560, 'admin', '2019-01-19 20:07:58', 0, 'admin', '2019-04-01 23:56:14', '', 99020862912466944, 6.00, 0, '婚假', '婚假');
INSERT INTO `t_dict_data` VALUES (99021437171404800, 'admin', '2019-01-19 20:08:11', 0, 'admin', '2019-04-01 23:56:18', '', 99020862912466944, 7.00, 0, '例假', '例假');
INSERT INTO `t_dict_data` VALUES (99021497724571648, 'admin', '2019-01-19 20:08:25', 0, 'admin', '2019-04-01 23:56:23', '', 99020862912466944, 8.00, 0, '丧假', '丧假');
INSERT INTO `t_dict_data` VALUES (99021556704874496, 'admin', '2019-01-19 20:08:39', 0, 'admin', '2019-04-01 23:56:27', '', 99020862912466944, 9.00, 0, '哺乳假', '哺乳假');
INSERT INTO `t_dict_data` VALUES (125170157323554816, 'admin', '2019-04-01 23:53:52', 0, 'admin', '2019-04-01 23:53:52', '', 75383353938808832, 3.00, 0, '工作流', '工作流');
COMMIT;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` bigint(20) unsigned DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `f_key` varchar(255) DEFAULT NULL,
  `location` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file
-- ----------------------------
BEGIN;
INSERT INTO `t_file` VALUES (1453363256788914176, 'admin', '2021-10-27 22:09:53', 0, 'admin', '2021-10-27 22:09:53', 'lol.jpg', 2220991, 'image/jpeg', 'http://c0c0.oss-cn-qingdao.aliyuncs.com/6e41bb9901614d96a9f78485451eed56.jpg', '6e41bb9901614d96a9f78485451eed56.jpg', 2);
COMMIT;

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `duration` int(11) unsigned DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cost_time` int(11) unsigned DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `request_param` longtext,
  `request_type` varchar(255) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `log_type` tinyint(1) DEFAULT NULL,
  `device` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
BEGIN;
INSERT INTO `t_log` VALUES (1448202545787965440, NULL, '2021-10-13 16:22:48', 0, NULL, '2021-10-13 16:22:48', 423, '192.168.0.157', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"nmzj\",\"saveLogin\":\"true\",\"captchaId\":\"ee22983c18b043518e52ca91e4ebb810\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448222471252742144, NULL, '2021-10-13 17:41:59', 0, NULL, '2021-10-13 17:41:59', 325, '192.168.0.157', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"sfze\",\"saveLogin\":\"true\",\"captchaId\":\"5f9dcaaba4f6442cbf5cd96c7df60ba0\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448288331942203392, NULL, '2021-10-13 22:03:41', 0, NULL, '2021-10-13 22:03:41', 342, '127.0.0.1', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"65vq\",\"saveLogin\":\"true\",\"captchaId\":\"0150cf15b4be4176932bb0e9119c255b\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 94.0.4606.71 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1448300873699692544, NULL, '2021-10-13 22:53:32', 0, NULL, '2021-10-13 22:53:32', 1673, '192.168.2.100', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 75.0.3770.142 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448301586626514944, NULL, '2021-10-13 22:56:21', 0, NULL, '2021-10-13 22:56:21', 15556, '192.168.2.100', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 75.0.3770.142 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448302917424648192, NULL, '2021-10-13 23:01:39', 0, NULL, '2021-10-13 23:01:39', 1124, '192.168.2.100', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 75.0.3770.142 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448472580548857856, NULL, '2021-10-14 10:15:50', 0, NULL, '2021-10-14 10:15:50', 457, '192.168.0.157', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"02k1\",\"saveLogin\":\"true\",\"captchaId\":\"0556303a38714bd2a3bb6e022a115ddb\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448511857315942400, NULL, '2021-10-14 12:51:54', 0, NULL, '2021-10-14 12:51:54', 1296, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448526954734161920, NULL, '2021-10-14 13:51:54', 0, NULL, '2021-10-14 13:51:54', 434, '192.168.0.157', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"captchaId\":\"6f393c5b42f44d039ec4d59516f9322e\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448526965668712448, NULL, '2021-10-14 13:51:56', 0, NULL, '2021-10-14 13:51:56', 1759, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448527653526179840, NULL, '2021-10-14 13:54:40', 0, NULL, '2021-10-14 13:54:40', 1277, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448527661377916928, NULL, '2021-10-14 13:54:42', 0, NULL, '2021-10-14 13:54:42', 1071, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448528614541889536, NULL, '2021-10-14 13:58:29', 0, NULL, '2021-10-14 13:58:29', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448528732829650944, NULL, '2021-10-14 13:58:57', 0, NULL, '2021-10-14 13:58:57', 1249, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448528869140336640, NULL, '2021-10-14 13:59:30', 0, NULL, '2021-10-14 13:59:30', 1054, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448528877315035136, NULL, '2021-10-14 13:59:31', 0, NULL, '2021-10-14 13:59:31', 1050, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448529031153717248, NULL, '2021-10-14 14:00:08', 0, NULL, '2021-10-14 14:00:08', 1064, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448529064250970112, NULL, '2021-10-14 14:00:16', 0, NULL, '2021-10-14 14:00:16', 1041, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531205095034880, NULL, '2021-10-14 14:08:46', 0, NULL, '2021-10-14 14:08:46', 1195, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531209788461056, NULL, '2021-10-14 14:08:48', 0, NULL, '2021-10-14 14:08:48', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531214603522048, NULL, '2021-10-14 14:08:49', 0, NULL, '2021-10-14 14:08:49', 1060, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531219406000128, NULL, '2021-10-14 14:08:50', 0, NULL, '2021-10-14 14:08:50', 1050, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531224267198464, NULL, '2021-10-14 14:08:51', 0, NULL, '2021-10-14 14:08:51', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531261978185728, NULL, '2021-10-14 14:09:00', 0, NULL, '2021-10-14 14:09:00', 1046, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531537418129408, NULL, '2021-10-14 14:10:06', 0, NULL, '2021-10-14 14:10:06', 1078, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448531624118587392, NULL, '2021-10-14 14:10:26', 0, NULL, '2021-10-14 14:10:26', 1051, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533097057488896, NULL, '2021-10-14 14:16:18', 0, NULL, '2021-10-14 14:16:18', 1478, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533160651526144, NULL, '2021-10-14 14:16:33', 0, NULL, '2021-10-14 14:16:33', 1053, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533166242533376, NULL, '2021-10-14 14:16:34', 0, NULL, '2021-10-14 14:16:34', 1077, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533179693666304, NULL, '2021-10-14 14:16:37', 0, NULL, '2021-10-14 14:16:37', 1049, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533193459372032, NULL, '2021-10-14 14:16:41', 0, NULL, '2021-10-14 14:16:41', 1052, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533203307597824, NULL, '2021-10-14 14:16:43', 0, NULL, '2021-10-14 14:16:43', 1045, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533252599058432, NULL, '2021-10-14 14:16:55', 0, NULL, '2021-10-14 14:16:55', 1049, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533259582574592, NULL, '2021-10-14 14:16:56', 0, NULL, '2021-10-14 14:16:56', 1038, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533266306043904, NULL, '2021-10-14 14:16:58', 0, NULL, '2021-10-14 14:16:58', 1036, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533282819018752, NULL, '2021-10-14 14:17:02', 0, NULL, '2021-10-14 14:17:02', 1073, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533292482695168, NULL, '2021-10-14 14:17:04', 0, NULL, '2021-10-14 14:17:04', 1057, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533318017617920, NULL, '2021-10-14 14:17:11', 0, NULL, '2021-10-14 14:17:11', 1151, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533351123259392, NULL, '2021-10-14 14:17:18', 0, NULL, '2021-10-14 14:17:18', 1045, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533355716022272, NULL, '2021-10-14 14:17:19', 0, NULL, '2021-10-14 14:17:19', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533430437548032, NULL, '2021-10-14 14:17:37', 0, NULL, '2021-10-14 14:17:37', 1090, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448533780208947200, NULL, '2021-10-14 14:19:00', 0, NULL, '2021-10-14 14:19:00', 1046, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448534461703655424, NULL, '2021-10-14 14:21:43', 0, NULL, '2021-10-14 14:21:43', 1053, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448534500454830080, NULL, '2021-10-14 14:21:52', 0, NULL, '2021-10-14 14:21:52', 1062, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448534729413496832, NULL, '2021-10-14 14:22:47', 0, NULL, '2021-10-14 14:22:47', 1194, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448534811630243840, NULL, '2021-10-14 14:23:06', 0, NULL, '2021-10-14 14:23:06', 1049, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448534865451552768, NULL, '2021-10-14 14:23:19', 0, NULL, '2021-10-14 14:23:19', 1033, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448535200542887936, NULL, '2021-10-14 14:24:39', 0, NULL, '2021-10-14 14:24:39', 1062, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448535499127001088, NULL, '2021-10-14 14:25:50', 0, NULL, '2021-10-14 14:25:50', 1184, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448535510816526336, NULL, '2021-10-14 14:25:53', 0, NULL, '2021-10-14 14:25:53', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448535540583501824, NULL, '2021-10-14 14:26:00', 0, NULL, '2021-10-14 14:26:00', 1046, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448535742539239424, NULL, '2021-10-14 14:26:48', 0, NULL, '2021-10-14 14:26:48', 1058, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536132139749376, NULL, '2021-10-14 14:28:21', 0, NULL, '2021-10-14 14:28:21', 1050, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536342991605760, NULL, '2021-10-14 14:29:11', 0, NULL, '2021-10-14 14:29:11', 1057, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536393453277184, NULL, '2021-10-14 14:29:23', 0, NULL, '2021-10-14 14:29:23', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536461711380480, NULL, '2021-10-14 14:29:40', 0, NULL, '2021-10-14 14:29:40', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536472276832256, NULL, '2021-10-14 14:29:42', 0, NULL, '2021-10-14 14:29:42', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536480455725056, NULL, '2021-10-14 14:29:44', 0, NULL, '2021-10-14 14:29:44', 1055, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536489226014720, NULL, '2021-10-14 14:29:47', 0, NULL, '2021-10-14 14:29:47', 1065, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536510558244864, NULL, '2021-10-14 14:29:51', 0, NULL, '2021-10-14 14:29:51', 1050, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536519466946560, NULL, '2021-10-14 14:29:53', 0, NULL, '2021-10-14 14:29:53', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536922782830592, NULL, '2021-10-14 14:31:30', 0, NULL, '2021-10-14 14:31:30', 1320, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448536976918712320, NULL, '2021-10-14 14:31:43', 0, NULL, '2021-10-14 14:31:43', 1243, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448537084334837760, NULL, '2021-10-14 14:32:08', 0, NULL, '2021-10-14 14:32:08', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448537099761487872, NULL, '2021-10-14 14:32:12', 0, NULL, '2021-10-14 14:32:12', 1110, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448537150307045376, NULL, '2021-10-14 14:32:24', 0, NULL, '2021-10-14 14:32:24', 1030, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448537169445654528, NULL, '2021-10-14 14:32:28', 0, NULL, '2021-10-14 14:32:28', 1032, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448537187640545280, NULL, '2021-10-14 14:32:33', 0, NULL, '2021-10-14 14:32:33', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538005496270848, NULL, '2021-10-14 14:35:48', 0, NULL, '2021-10-14 14:35:48', 1234, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538086739939328, NULL, '2021-10-14 14:36:07', 0, NULL, '2021-10-14 14:36:07', 1042, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538240779948032, NULL, '2021-10-14 14:36:44', 0, NULL, '2021-10-14 14:36:44', 1087, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538477955256320, NULL, '2021-10-14 14:37:40', 0, NULL, '2021-10-14 14:37:40', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538590576513024, NULL, '2021-10-14 14:38:07', 0, NULL, '2021-10-14 14:38:07', 1058, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538674206740480, NULL, '2021-10-14 14:38:27', 0, NULL, '2021-10-14 14:38:27', 1052, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538680619831296, NULL, '2021-10-14 14:38:29', 0, NULL, '2021-10-14 14:38:29', 1064, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538696549797888, NULL, '2021-10-14 14:38:33', 0, NULL, '2021-10-14 14:38:33', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538705429139456, NULL, '2021-10-14 14:38:35', 0, NULL, '2021-10-14 14:38:35', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538712240689152, NULL, '2021-10-14 14:38:36', 0, NULL, '2021-10-14 14:38:36', 1042, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448538868486901760, NULL, '2021-10-14 14:39:14', 0, NULL, '2021-10-14 14:39:14', 1038, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448539005921660928, NULL, '2021-10-14 14:39:47', 0, NULL, '2021-10-14 14:39:47', 1167, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448539024053637120, NULL, '2021-10-14 14:39:51', 0, NULL, '2021-10-14 14:39:51', 1036, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448539147349397504, NULL, '2021-10-14 14:40:20', 0, NULL, '2021-10-14 14:40:20', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448539213556486144, NULL, '2021-10-14 14:40:36', 0, NULL, '2021-10-14 14:40:36', 1052, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448540109061361664, NULL, '2021-10-14 14:44:09', 0, NULL, '2021-10-14 14:44:09', 1065, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448540455552815104, NULL, '2021-10-14 14:45:32', 0, NULL, '2021-10-14 14:45:32', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448540899293401088, NULL, '2021-10-14 14:47:18', 0, NULL, '2021-10-14 14:47:18', 1059, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448540988162314240, NULL, '2021-10-14 14:47:39', 0, NULL, '2021-10-14 14:47:39', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448540997695967232, NULL, '2021-10-14 14:47:41', 0, NULL, '2021-10-14 14:47:41', 1038, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448541003609935872, NULL, '2021-10-14 14:47:43', 0, NULL, '2021-10-14 14:47:43', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448543053445992448, NULL, '2021-10-14 14:55:51', 0, NULL, '2021-10-14 14:55:51', 1210, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448543238314135552, NULL, '2021-10-14 14:56:36', 0, NULL, '2021-10-14 14:56:36', 1136, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448543257272389632, NULL, '2021-10-14 14:56:40', 0, NULL, '2021-10-14 14:56:40', 1064, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448544303688323072, NULL, '2021-10-14 15:00:49', 0, NULL, '2021-10-14 15:00:49', 1049, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448544517031596032, NULL, '2021-10-14 15:01:40', 0, NULL, '2021-10-14 15:01:40', 1037, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448545547899244544, NULL, '2021-10-14 15:05:46', 0, NULL, '2021-10-14 15:05:46', 1063, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448546532835069952, NULL, '2021-10-14 15:09:41', 0, NULL, '2021-10-14 15:09:41', 1146, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448546560421007360, NULL, '2021-10-14 15:09:48', 0, NULL, '2021-10-14 15:09:48', 1119, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448546638871269376, NULL, '2021-10-14 15:10:06', 0, NULL, '2021-10-14 15:10:06', 1066, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448546811810811904, NULL, '2021-10-14 15:10:48', 0, NULL, '2021-10-14 15:10:48', 1039, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448547016870334464, NULL, '2021-10-14 15:11:36', 0, NULL, '2021-10-14 15:11:36', 1039, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448547218360504320, NULL, '2021-10-14 15:12:24', 0, NULL, '2021-10-14 15:12:24', 1069, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448547708452343808, NULL, '2021-10-14 15:14:21', 0, NULL, '2021-10-14 15:14:21', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448547866716016640, NULL, '2021-10-14 15:14:59', 0, NULL, '2021-10-14 15:14:59', 1031, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448548008449937408, NULL, '2021-10-14 15:15:33', 0, NULL, '2021-10-14 15:15:33', 1041, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448548608826806272, NULL, '2021-10-14 15:17:56', 0, NULL, '2021-10-14 15:17:56', 1048, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448550648122576896, NULL, '2021-10-14 15:26:02', 0, NULL, '2021-10-14 15:26:02', 1233, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448550679948955648, NULL, '2021-10-14 15:26:10', 0, NULL, '2021-10-14 15:26:10', 1033, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551274671902720, NULL, '2021-10-14 15:28:31', 0, NULL, '2021-10-14 15:28:31', 1278, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551282322313216, NULL, '2021-10-14 15:28:33', 0, NULL, '2021-10-14 15:28:33', 1056, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551379097489408, NULL, '2021-10-14 15:28:56', 0, NULL, '2021-10-14 15:28:56', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551386676596736, NULL, '2021-10-14 15:28:58', 0, NULL, '2021-10-14 15:28:58', 1036, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551643661602816, NULL, '2021-10-14 15:30:00', 0, NULL, '2021-10-14 15:30:00', 1061, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551692655267840, NULL, '2021-10-14 15:30:11', 0, NULL, '2021-10-14 15:30:11', 1060, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551871315841024, NULL, '2021-10-14 15:30:54', 0, NULL, '2021-10-14 15:30:54', 1052, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551935660658688, NULL, '2021-10-14 15:31:09', 0, NULL, '2021-10-14 15:31:09', 1045, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551949149540352, NULL, '2021-10-14 15:31:12', 0, NULL, '2021-10-14 15:31:12', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551959270395904, NULL, '2021-10-14 15:31:15', 0, NULL, '2021-10-14 15:31:15', 1048, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448551972444704768, NULL, '2021-10-14 15:31:18', 0, NULL, '2021-10-14 15:31:18', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552133271097344, NULL, '2021-10-14 15:31:56', 0, NULL, '2021-10-14 15:31:56', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552264280182784, NULL, '2021-10-14 15:32:27', 0, NULL, '2021-10-14 15:32:27', 1063, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552489916960768, NULL, '2021-10-14 15:33:21', 0, NULL, '2021-10-14 15:33:21', 1238, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552753893871616, NULL, '2021-10-14 15:34:24', 0, NULL, '2021-10-14 15:34:24', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552764111196160, NULL, '2021-10-14 15:34:27', 0, NULL, '2021-10-14 15:34:27', 1045, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552782033457152, NULL, '2021-10-14 15:34:31', 0, NULL, '2021-10-14 15:34:31', 1052, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552796696743936, NULL, '2021-10-14 15:34:34', 0, NULL, '2021-10-14 15:34:34', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552819308236800, NULL, '2021-10-14 15:34:40', 0, NULL, '2021-10-14 15:34:40', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552828594425856, NULL, '2021-10-14 15:34:42', 0, NULL, '2021-10-14 15:34:42', 1043, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552883103600640, NULL, '2021-10-14 15:34:55', 0, NULL, '2021-10-14 15:34:55', 1042, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448552993246023680, NULL, '2021-10-14 15:35:21', 0, NULL, '2021-10-14 15:35:21', 1684, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448553013177356288, NULL, '2021-10-14 15:35:26', 0, NULL, '2021-10-14 15:35:26', 1068, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448553018877415424, NULL, '2021-10-14 15:35:27', 0, NULL, '2021-10-14 15:35:27', 1086, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448553314542292992, NULL, '2021-10-14 15:36:38', 0, NULL, '2021-10-14 15:36:38', 1035, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448553345445924864, NULL, '2021-10-14 15:36:45', 0, NULL, '2021-10-14 15:36:45', 1034, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448553357714264064, NULL, '2021-10-14 15:36:48', 0, NULL, '2021-10-14 15:36:48', 1029, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448554686230695936, NULL, '2021-10-14 15:42:05', 0, NULL, '2021-10-14 15:42:05', 1319, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448554841604493312, NULL, '2021-10-14 15:42:42', 0, NULL, '2021-10-14 15:42:42', 1061, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448554881714622464, NULL, '2021-10-14 15:42:51', 0, NULL, '2021-10-14 15:42:51', 1044, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448554887871860736, NULL, '2021-10-14 15:42:53', 0, NULL, '2021-10-14 15:42:53', 1048, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448554954271887360, NULL, '2021-10-14 15:43:09', 0, NULL, '2021-10-14 15:43:09', 1055, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555043077885952, NULL, '2021-10-14 15:43:30', 0, NULL, '2021-10-14 15:43:30', 1064, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555091262050304, NULL, '2021-10-14 15:43:41', 0, NULL, '2021-10-14 15:43:41', 1068, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555111960940544, NULL, '2021-10-14 15:43:46', 0, NULL, '2021-10-14 15:43:46', 1111, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555255590686720, NULL, '2021-10-14 15:44:21', 0, NULL, '2021-10-14 15:44:21', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555340814749696, NULL, '2021-10-14 15:44:41', 0, NULL, '2021-10-14 15:44:41', 1042, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555367050121216, NULL, '2021-10-14 15:44:47', 0, NULL, '2021-10-14 15:44:47', 1050, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555432741310464, NULL, '2021-10-14 15:45:03', 0, NULL, '2021-10-14 15:45:03', 1049, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555475384799232, NULL, '2021-10-14 15:45:13', 0, NULL, '2021-10-14 15:45:13', 1045, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555494263361536, NULL, '2021-10-14 15:45:17', 0, NULL, '2021-10-14 15:45:17', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448555783229935616, NULL, '2021-10-14 15:46:26', 0, NULL, '2021-10-14 15:46:26', 1228, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448556011760783360, NULL, '2021-10-14 15:47:21', 0, NULL, '2021-10-14 15:47:21', 1046, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448556277943898112, NULL, '2021-10-14 15:48:24', 0, NULL, '2021-10-14 15:48:24', 1040, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448556321187172352, NULL, '2021-10-14 15:48:35', 0, NULL, '2021-10-14 15:48:35', 1038, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448556841394114560, NULL, '2021-10-14 15:50:39', 0, NULL, '2021-10-14 15:50:39', 1057, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448556881944645632, NULL, '2021-10-14 15:50:48', 0, NULL, '2021-10-14 15:50:48', 1056, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557082000363520, NULL, '2021-10-14 15:51:36', 0, NULL, '2021-10-14 15:51:36', 1036, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557231401472000, NULL, '2021-10-14 15:52:12', 0, NULL, '2021-10-14 15:52:12', 1031, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557281414352896, NULL, '2021-10-14 15:52:24', 0, NULL, '2021-10-14 15:52:24', 1047, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557373160558592, NULL, '2021-10-14 15:52:45', 0, NULL, '2021-10-14 15:52:45', 1051, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557401547608064, NULL, '2021-10-14 15:52:52', 0, NULL, '2021-10-14 15:52:52', 1049, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557442370768896, NULL, '2021-10-14 15:53:02', 0, NULL, '2021-10-14 15:53:02', 1055, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557658239012864, NULL, '2021-10-14 15:53:53', 0, NULL, '2021-10-14 15:53:53', 1053, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557916662665216, NULL, '2021-10-14 15:54:55', 0, NULL, '2021-10-14 15:54:55', 1236, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557951513137152, NULL, '2021-10-14 15:55:03', 0, NULL, '2021-10-14 15:55:03', 1048, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448557956269477888, NULL, '2021-10-14 15:55:04', 0, NULL, '2021-10-14 15:55:04', 1057, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448558539038658560, NULL, '2021-10-14 15:57:23', 0, NULL, '2021-10-14 15:57:23', 1236, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448559165885779968, NULL, '2021-10-14 15:59:53', 0, NULL, '2021-10-14 15:59:53', 1246, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448559346714808320, NULL, '2021-10-14 16:00:36', 0, NULL, '2021-10-14 16:00:36', 1053, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448559466235695104, NULL, '2021-10-14 16:01:04', 0, NULL, '2021-10-14 16:01:04', 1059, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448559706237964288, NULL, '2021-10-14 16:02:02', 0, NULL, '2021-10-14 16:02:02', 1059, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448560152654516224, NULL, '2021-10-14 16:03:48', 0, NULL, '2021-10-14 16:03:48', 1233, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1448560421152886784, NULL, '2021-10-14 16:04:52', 0, NULL, '2021-10-14 16:04:52', 1050, '192.168.0.157', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453354102296809472, NULL, '2021-10-27 21:33:15', 0, NULL, '2021-10-27 21:33:15', 403, '127.0.0.1', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"xz23\",\"saveLogin\":\"true\",\"captchaId\":\"263d65be13eb42ffad28e1ac8280f813\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453354496389419008, NULL, '2021-10-27 21:34:49', 0, NULL, '2021-10-27 21:34:49', 2439, '127.0.0.1', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453354725490692096, NULL, '2021-10-27 21:35:46', 0, NULL, '2021-10-27 21:35:46', 3322, '127.0.0.1', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453355027539300352, NULL, '2021-10-27 21:36:55', 0, NULL, '2021-10-27 21:36:55', 1356, '127.0.0.1', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453355121072279552, NULL, '2021-10-27 21:37:19', 0, NULL, '2021-10-27 21:37:19', 1041, '127.0.0.1', '未知', '获取系统信息', '[]', 'GET', '/api/legion/system/info', 'admin', 0, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453357822954835968, NULL, '2021-10-27 21:48:02', 0, NULL, '2021-10-27 21:48:02', 133, '127.0.0.1', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"sjmj\",\"saveLogin\":\"true\",\"captchaId\":\"699cc60de2ca4870b0de0261917690f0\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453358124932141056, NULL, '2021-10-27 21:49:14', 0, NULL, '2021-10-27 21:49:14', 172, '127.0.0.1', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"code\":\"cjm1\",\"saveLogin\":\"true\",\"captchaId\":\"798fc292e7e7487ba05d35a7d98b9421\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 95.0.4638.54 | Mac OSX | PC端');
INSERT INTO `t_log` VALUES (1453536416964415488, NULL, '2021-10-28 09:37:43', 0, NULL, '2021-10-28 09:37:43', 629, '192.168.0.157', '未知', '登录系统', '{\"code\":\"Teqw\",\"saveLogin\":\"true\",\"captchaId\":\"112b55b67ce54efb87ec62eec3d60f72\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453541738101739520, NULL, '2021-10-28 09:58:51', 0, NULL, '2021-10-28 09:58:51', 651, '192.168.0.157', '未知', '登录系统', '{\"saveLogin\":\"true\",\"captchaId\":\"\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453554315066216448, NULL, '2021-10-28 10:48:50', 0, NULL, '2021-10-28 10:48:50', 663, '192.168.0.157', '未知', '登录系统', '{\"code\":\"q2a9\",\"saveLogin\":\"true\",\"captchaId\":\"9ef4d220ef274ee1aba09a5e1f973e02\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453908445777498112, NULL, '2021-10-29 10:16:01', 0, NULL, '2021-10-29 10:16:01', 173, '192.168.0.157', '未知', '登录系统', '{\"code\":\"sprr\",\"saveLogin\":\"true\",\"captchaId\":\"be1dd54bf0b445ad896964488d063f9e\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453912532313051136, NULL, '2021-10-29 10:32:15', 0, NULL, '2021-10-29 10:32:15', 224, '192.168.0.157', '未知', '登录系统', '{\"code\":\"jwsk\",\"saveLogin\":\"true\",\"captchaId\":\"d9393c63d9a843cb93e493d172a82495\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453912600290136064, NULL, '2021-10-29 10:32:31', 0, NULL, '2021-10-29 10:32:31', 149, '192.168.0.157', '未知', '登录系统', '{\"code\":\"cjcn\",\"saveLogin\":\"true\",\"captchaId\":\"75b3592b226f4ca1a686c717117b7f96\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453916470064058368, NULL, '2021-10-29 10:47:54', 0, NULL, '2021-10-29 10:47:54', 270, '192.168.0.157', '未知', '登录系统', '{\"code\":\"pekx\",\"saveLogin\":\"true\",\"captchaId\":\"79a3f792f0574eb696e48cd6ae641f81\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453924517620617216, NULL, '2021-10-29 11:19:54', 0, NULL, '2021-10-29 11:19:54', 979, '172.21.69.72', '未知', '登录系统', '{\"code\":\"yoj2\",\"saveLogin\":\"true\",\"captchaId\":\"e9252e71f76648cea1c58812500ce90b\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453927801022451712, NULL, '2021-10-29 11:32:56', 0, NULL, '2021-10-29 11:32:56', 752, '192.168.0.157', '未知', '登录系统', '{\"code\":\"tuv8\",\"saveLogin\":\"true\",\"captchaId\":\"6c331a08385042908671885d76456513\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453963046501355520, NULL, '2021-10-29 13:52:58', 0, NULL, '2021-10-29 13:52:58', 182, '172.21.69.72', '未知', '登录系统', '{\"code\":\"e2nh\",\"saveLogin\":\"true\",\"captchaId\":\"ada3968586a241cb9836fd62104ad6dc\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453963964538032128, NULL, '2021-10-29 13:56:37', 0, NULL, '2021-10-29 13:56:37', 146, '172.21.69.72', '未知', '登录系统', '{\"code\":\"kky3\",\"saveLogin\":\"true\",\"captchaId\":\"146fc1befd08495693d8f1c1c5505796\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453964423441027072, NULL, '2021-10-29 13:58:27', 0, NULL, '2021-10-29 13:58:27', 146, '172.21.69.72', '未知', '登录系统', '{\"code\":\"ydsg\",\"saveLogin\":\"true\",\"captchaId\":\"7e1d0525da754b44b09612a1b7ebf6fd\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Firefox 93.0 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453964892863336448, NULL, '2021-10-29 14:00:19', 0, NULL, '2021-10-29 14:00:19', 151, '172.21.69.72', '未知', '登录系统', '{\"code\":\"gkhr\",\"saveLogin\":\"true\",\"captchaId\":\"916c1ff31c6144629aaad9c60b5035de\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Chrome 92.0.4515.159 | Windows Windows 10 or Windows Server 2016 | PC端');
INSERT INTO `t_log` VALUES (1453970598962270208, NULL, '2021-10-29 14:22:59', 0, NULL, '2021-10-29 14:22:59', 140, '172.21.69.72', '未知', '登录系统', '{\"code\":\"juxv\",\"saveLogin\":\"true\",\"captchaId\":\"1f776c0e4e114e79b5078c71b928cbeb\",\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/api/legion/login', 'admin', 1, 'Firefox 93.0 | Windows Windows 10 or Windows Server 2016 | PC端');
COMMIT;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_send` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `is_template` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
BEGIN;
INSERT INTO `t_message` VALUES (43615268366192640, 'admin', '2018-08-19 22:43:51', 0, 'admin', '2021-10-13 16:20:41', b'1', '欢迎您注册账号使用Legion 点我查看使用须知', '系统公告', '<p style=\"text-align: center;\">系统公告</p>', b'0');
INSERT INTO `t_message` VALUES (1352138048674074624, 'admin', '2021-01-21 14:16:47', 0, 'admin', '2021-01-22 01:35:38', b'0', '您收到了一个新的委托转办的“${processName}”任务，申请人：${applyer}', '工作流', '<p>${nickname}您好，<span style=\"font-size: 1em;\">您收到了一个新的</span>委托转办的“${processName}”任务，申请人：${nickname}，赶快去系统“工作流程-我的待办”中查看处理吧！</p>', b'1');
INSERT INTO `t_message` VALUES (1352243408286126080, 'admin', '2021-01-21 21:15:27', 0, 'admin', '2021-01-22 01:35:21', b'0', '您收到了一条新的“${processName}”待办任务，申请人：${applyer}', '工作流', '<p>${nickname}您好，您收到了一条新的“${processName}”待办任务，申请人：${applyer}，赶快去“工作流程-我的待办”处理查看吧<br></p>', b'1');
INSERT INTO `t_message` VALUES (1352244242726129664, 'admin', '2021-01-21 21:18:46', 0, 'admin', '2021-01-22 01:34:57', b'0', '您申请的“${processName}”任务已审批通过', '工作流', '<p>${nickname}您好，您申请的“${processName}”任务已审批通过，赶快去“工作流程-我的申请”处理查看吧<br></p>', b'1');
INSERT INTO `t_message` VALUES (1352304101387538432, 'admin', '2021-01-22 01:16:37', 0, 'admin', '2021-01-22 01:33:48', b'0', '${nickname}您好，您申请的“${processName}”任务已被驳回', '工作流', '<p>${nickname}您好，您申请的“${processName}”任务已被驳回，赶快去“工作流程-我的申请”处理查看吧<br></p>', b'1');
COMMIT;

-- ----------------------------
-- Table structure for t_message_send
-- ----------------------------
DROP TABLE IF EXISTS `t_message_send`;
CREATE TABLE `t_message_send` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `message_id` bigint(20) unsigned DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message_send
-- ----------------------------
BEGIN;
INSERT INTO `t_message_send` VALUES (1448197638313873408, '', '2021-10-13 16:03:18', 0, 'test3', '2021-10-13 16:22:09', 43615268366192640, 1, 1448197635516272640, '');
COMMIT;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) unsigned NOT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level` int(11) unsigned DEFAULT NULL,
  `button_type` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `show_always` bit(1) DEFAULT NULL,
  `is_menu` bit(1) DEFAULT NULL,
  `is_parent` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES (5129710648430592, '', '2018-06-04 19:02:29', 0, 'admin', '2021-01-11 01:00:01', '', 'sys', 125909152017944576, 0, 1.00, 'Main', '/sys', '系统管理', 'ios-settings', 1, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (5129710648430593, '', '2018-06-04 19:02:32', 0, '', '2018-08-13 15:15:33', '', 'user-manage', 5129710648430592, 0, 1.10, 'sys/user-manage/userManage', 'user-manage', '用户管理', 'md-person', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (5129710648430594, '', '2018-06-04 19:02:35', 0, '', '2018-10-13 13:51:36', '', 'role-manage', 5129710648430592, 0, 1.60, 'sys/role-manage/roleManage', 'role-manage', '角色权限管理', 'md-contacts', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (5129710648430595, '', '2018-06-04 19:02:37', 0, '', '2018-09-23 23:32:02', '', 'menu-manage', 5129710648430592, 0, 1.70, 'sys/menu-manage/menuManage', 'menu-manage', '菜单权限管理', 'md-menu', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (15701400130424832, '', '2018-06-03 22:04:06', 0, '', '2018-09-19 22:16:44', '', '', 5129710648430593, 1, 1.11, '', '/legion/user/admin/add*', '添加用户', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (15701915807518720, '', '2018-06-03 22:06:09', 0, '', '2018-06-06 14:46:51', '', '', 5129710648430593, 1, 1.13, '', '/legion/user/admin/disable/**', '禁用用户', '', 3, 'disable', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (15708892205944832, '', '2018-06-03 22:33:52', 0, '', '2018-06-28 16:44:48', '', '', 5129710648430593, 1, 1.14, '', '/legion/user/admin/enable/**', '启用用户', '', 3, 'enable', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16678126574637056, '', '2018-06-06 14:45:16', 0, '', '2018-09-19 22:16:48', '', '', 5129710648430593, 1, 1.12, '', '/legion/user/admin/edit*', '编辑用户', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16678447719911424, '', '2018-06-06 14:46:32', 0, '', '2018-08-10 21:41:16', '', '', 5129710648430593, 1, 1.15, '', '/legion/user/delByIds/**', '删除用户', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16687383932047360, '', '2018-06-06 15:22:03', 0, '', '2018-09-19 22:07:34', '', '', 5129710648430594, 1, 1.21, '', '/legion/role/save*', '添加角色', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16689632049631232, '', '2018-06-06 15:30:59', 0, '', '2018-09-19 22:07:37', '', '', 5129710648430594, 1, 1.22, '', '/legion/role/edit*', '编辑角色', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16689745006432256, '', '2018-06-06 15:31:26', 0, '', '2018-08-10 21:41:23', '', '', 5129710648430594, 1, 1.23, '', '/legion/role/delAllByIds/**', '删除角色', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16689883993083904, NULL, '2018-06-06 15:31:59', 0, NULL, '2018-06-06 15:31:59', NULL, NULL, 5129710648430594, 1, 1.24, NULL, '/legion/role/editRolePerm**', '分配权限', NULL, 3, 'editPerm', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16690313745666048, '', '2018-06-06 15:33:41', 0, '', '2018-09-19 22:07:46', '', '', 5129710648430594, 1, 1.25, '', '/legion/role/setDefault*', '设为默认角色', '', 3, 'setDefault', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16694861252005888, '', '2018-06-06 15:51:46', 0, '', '2018-09-19 22:07:52', '', '', 5129710648430595, 1, 1.31, '', '/legion/permission/add*', '添加菜单', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16695107491205120, '', '2018-06-06 15:52:44', 0, '', '2018-09-19 22:07:57', '', '', 5129710648430595, 1, 1.32, '', '/legion/permission/edit*', '编辑菜单', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (16695243126607872, '', '2018-06-06 15:53:17', 0, '', '2018-08-10 21:41:33', '', '', 5129710648430595, 1, 1.33, '', '/legion/permission/delByIds/**', '删除菜单', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (25014528525733888, '', '2018-06-29 14:51:09', 0, '', '2018-10-08 11:13:27', '', '', 5129710648430593, 1, 1.16, '', '无', '上传图片', '', 3, 'upload', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (39915540965232640, NULL, '2018-08-09 17:42:28', 0, NULL, '2018-08-09 17:42:28', NULL, 'monitor', 125909152017944576, 0, 2.00, 'Main', '/monitor', '系统监控', 'ios-analytics', 1, NULL, 0, NULL, b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (39916171171991552, '', '2018-08-09 17:44:57', 0, 'admin', '2019-01-20 00:37:29', '', 'druid', 39915540965232640, 0, 2.40, 'sys/monitor/monitor', 'druid', 'SQL监控', 'md-analytics', 2, '', 0, 'http://127.0.0.1:8888/druid/login.html', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (39918482854252544, '', '2018-08-09 17:54:08', 0, 'admin', '2020-11-19 16:23:56', '', 'swagger', 39915540965232640, 0, 2.50, 'sys/monitor/monitor', 'swagger', '接口文档', 'md-document', 2, '', 0, 'http://127.0.0.1:8888/doc.html', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (40238597734928384, '', '2018-08-10 15:06:10', 0, 'admin', '2020-12-10 02:21:33', '', 'department-manage', 5129710648430592, 0, 1.20, 'sys/department-manage/departmentManage', 'department-manage', '部门管理', 'md-git-branch', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (41363147411427328, '', '2018-08-13 17:34:43', 0, 'admin', '2020-03-25 20:31:16', '', 'log-manage', 39915540965232640, 0, 2.20, 'sys/log-manage/logManage', 'log-manage', '日志管理', 'md-list-box', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (41363537456533504, '', '2018-08-13 17:36:16', 0, '', '2018-08-13 17:56:11', '', '', 41363147411427328, 1, 2.11, '', '/legion/log/delByIds/**', '删除日志', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (41364927394353152, '', '2018-08-13 17:41:48', 0, 'admin', '2020-12-11 16:32:40', '', '', 41363147411427328, 1, 2.12, '', '/legion/log/delAll*', '清空日志', '', 3, 'clear', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (41370251991977984, NULL, '2018-08-13 18:02:57', 0, NULL, '2018-08-13 18:02:57', NULL, 'quartz-job', 39915540965232640, 0, 2.10, 'sys/quartz-manage/quartzManage', 'quartz-job', '定时任务', 'md-time', 2, '', 0, NULL, b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (41371711400054784, '', '2018-08-13 18:08:45', 0, '', '2019-07-07 21:24:26', '', 'actuator', 39915540965232640, 0, 2.30, 'sys/actuator/actuator', 'actuator', 'Actuator监控', 'logo-angular', 2, '', 0, '', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (41469219249852416, NULL, '2018-08-14 00:36:13', 0, NULL, '2018-08-14 00:36:13', NULL, '', 41371711400054784, 1, 2.31, '', '无', '查看数据', '', 3, 'view', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (43117268627886080, '', '2018-08-18 13:44:58', 0, '', '2018-08-18 20:55:04', '', 'message-manage', 5129710648430592, 0, 1.30, 'sys/message-manage/messageManage', 'message-manage', '消息管理', 'md-mail', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (44986029924421632, '', '2018-08-23 17:30:46', 0, '', '2018-09-23 23:26:53', '', 'social-manage', 5129710648430592, 0, 1.50, 'sys/social-manage/socialManage', 'social-manage', '社交账号绑定', 'md-share', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (45069342940860416, '', '2018-08-23 23:01:49', 0, '', '2018-08-24 10:01:09', '', '', 44986029924421632, 1, 1.42, '', '无', '查看社交账号数据', '', 3, 'view', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45235228800716800, '', '2018-08-24 10:01:00', 0, '', '2018-09-19 22:07:23', '', '', 44986029924421632, 1, 1.41, '', '/legion/relate/delByIds*', '删除解绑', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45235621697949696, '', '2018-08-24 10:02:33', 0, '', '2018-09-19 22:06:57', '', '', 40238597734928384, 1, 1.21, '', '/legion/department/add*', '添加部门', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45235787867885568, '', '2018-08-24 10:03:13', 0, '', '2018-09-19 22:07:02', '', '', 40238597734928384, 1, 1.22, '', '/legion/department/edit*', '编辑部门', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45235939278065664, NULL, '2018-08-24 10:03:49', 0, NULL, '2018-08-24 10:03:49', NULL, '', 40238597734928384, 1, 1.23, '', '/legion/department/delByIds/*', '删除部门', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45236734832676864, '', '2018-08-24 10:06:59', 0, '', '2018-09-19 22:07:07', '', '', 43117268627886080, 1, 1.31, '', '/legion/message/add*', '添加消息', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45237010692050944, '', '2018-08-24 10:08:04', 0, '', '2018-09-19 22:07:12', '', '', 43117268627886080, 1, 1.32, '', '/legion/message/edit*', '编辑消息', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45237170029465600, NULL, '2018-08-24 10:08:42', 0, NULL, '2018-08-24 10:08:42', NULL, '', 43117268627886080, 1, 1.33, '', '/legion/message/delByIds/*', '删除消息', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45264987354042368, '', '2018-08-24 11:59:14', 0, '', '2018-09-19 22:08:11', '', '', 41370251991977984, 1, 2.11, '', '/legion/quartzJob/add*', '添加定时任务', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45265487029866496, '', '2018-08-24 12:01:14', 0, '', '2018-09-19 22:08:17', '', '', 41370251991977984, 1, 2.12, '', '/legion/quartzJob/edit*', '编辑定时任务', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45265762415284224, '', '2018-08-24 12:02:19', 0, '', '2018-09-19 22:08:24', '', '', 41370251991977984, 1, 2.13, '', '/legion/quartzJob/pause*', '暂停定时任务', '', 3, 'disable', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45265886315024384, '', '2018-08-24 12:02:49', 0, '', '2018-09-19 22:09:13', '', '', 41370251991977984, 1, 2.14, '', '/legion/quartzJob/resume*', '恢复定时任务', '', 3, 'enable', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (45266070000373760, NULL, '2018-08-24 12:03:33', 0, NULL, '2018-08-24 12:03:33', NULL, '', 41370251991977984, 1, 2.15, '', '/legion/quartzJob/delByIds/*', '删除定时任务', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (56309618086776832, '', '2018-09-23 23:26:40', 0, 'admin', '2018-11-15 15:17:43', '', 'oss-manage', 5129710648430592, 0, 1.40, 'sys/oss-manage/ossManage', 'oss-manage', '文件对象存储', 'ios-folder', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (56898976661639168, '', '2018-09-25 14:28:34', 0, '', '2018-09-25 15:12:46', '', '', 5129710648430593, 1, 1.17, '', '/legion/user/importData*', '导入用户', '', 3, 'input', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (57009544286441472, NULL, '2018-09-25 21:47:55', 0, NULL, '2018-09-25 21:47:55', NULL, '', 43117268627886080, 1, 1.40, '', '/legion/messageSend/save*', '添加已发送消息', '', 3, 'add', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (57009744761589760, NULL, '2018-09-25 21:48:43', 0, NULL, '2018-09-25 21:48:43', NULL, '', 43117268627886080, 1, 1.50, '', '/legion/messageSend/update*', '编辑已发送消息', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (57009981228060672, NULL, '2018-09-25 21:49:39', 0, NULL, '2018-09-25 21:49:39', NULL, '', 43117268627886080, 1, 1.60, '', '/legion/messageSend/delByIds/*', '删除已发送消息', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (57212882168844288, '', '2018-09-26 11:15:55', 0, '', '2018-10-08 11:10:05', '', '', 56309618086776832, 1, 1.41, '', '无', '上传文件', '', 3, 'upload', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (58480609315524608, '', '2018-09-29 23:13:24', 0, 'admin', '2018-11-14 13:24:26', '', 'setting', 5129710648430592, 0, 1.90, 'sys/setting-manage/settingManage', 'setting', '系统配置', 'ios-settings-outline', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (61394706252173312, NULL, '2018-10-08 00:12:59', 0, NULL, '2018-10-08 00:12:59', NULL, '', 58480609315524608, 1, 1.81, '', '/legion/setting/seeSecret/**', '查看私密配置', '', 3, 'view', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (61417744146370560, '', '2018-10-08 01:44:32', 0, '', '2018-10-08 01:50:03', '', '', 58480609315524608, 1, 1.82, '', '/legion/setting/*/set*', '编辑配置', '', 3, 'edit', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (61560041605435392, NULL, '2018-10-08 11:09:58', 0, NULL, '2018-10-08 11:09:58', NULL, '', 56309618086776832, 1, 1.42, '', '/legion/file/rename*', '重命名文件', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (61560275261722624, NULL, '2018-10-08 11:10:54', 0, NULL, '2018-10-08 11:10:54', NULL, '', 56309618086776832, 1, 1.43, '', '/legion/file/copy*', '复制文件', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (61560480518377472, NULL, '2018-10-08 11:11:43', 0, NULL, '2018-10-08 11:11:43', NULL, '', 56309618086776832, 1, 1.44, '', '/legion/file/delete/*', '删除文件', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (75002207560273920, 'admin', '2018-11-14 13:24:21', 0, 'admin', '2018-11-20 20:06:22', '', 'dict', 5129710648430592, 0, 1.80, 'sys/dict-manage/dictManage', 'dict', '数据字典管理', 'md-bookmarks', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (76215889006956544, 'admin', '2018-11-17 21:47:05', 0, 'admin', '2018-11-17 21:47:53', '', '', 75002207560273920, 1, 1.81, '', '/legion/dict/add*', '添加字典', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (76216071333351424, 'admin', '2018-11-17 21:47:48', 0, 'admin', '2018-11-17 21:47:48', NULL, '', 75002207560273920, 1, 1.82, '', '/legion/dict/edit*', '编辑字典', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (76216264070008832, 'admin', '2018-11-17 21:48:34', 0, 'admin', '2018-11-17 21:48:34', NULL, '', 75002207560273920, 1, 1.83, '', '/legion/dict/delByIds/**', '删除字典', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (76216459709124608, 'admin', '2018-11-17 21:49:21', 0, 'admin', '2018-11-17 21:49:21', NULL, '', 75002207560273920, 1, 1.84, '', '/legion/dictData/add*', '添加字典数据', '', 3, 'add', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (76216594207870976, 'admin', '2018-11-17 21:49:53', 0, 'admin', '2018-11-17 21:49:53', NULL, '', 75002207560273920, 1, 1.85, '', '/legion/dictData/edit*', '编辑字典数据', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (76216702639017984, 'admin', '2018-11-17 21:50:18', 0, 'admin', '2018-11-17 21:50:18', NULL, '', 75002207560273920, 1, 1.86, '', '/legion/dictData/delByIds/**', '删除字典数据', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (76606430504816640, 'admin', '2018-11-18 23:38:57', 0, 'admin', '2018-11-18 23:39:12', '', 'activiti', 125909152017944576, 0, 1.10, 'Main', '/activiti', '工作流程', 'md-git-compare', 1, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (76607201262702592, 'admin', '2018-11-18 23:42:01', 0, 'admin', '2018-11-20 19:41:58', '', 'model-manage', 76606430504816640, 0, 1.00, 'activiti/model-manage/modelManage', 'model-manage', '模型管理', 'md-cube', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (76914082455752704, 'admin', '2018-11-19 20:01:28', 0, 'admin', '2018-11-20 19:41:50', '', 'process-manage', 76606430504816640, 0, 0.90, 'activiti/process-manage/processManage', 'process-manage', '流程管理', 'md-calendar', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (80539147005071360, 'admin', '2018-11-29 20:06:10', 0, 'admin', '2018-12-04 14:14:41', '', 'category-manage', 76606430504816640, 0, 2.00, 'activiti/category-manage/categoryManage', 'category-manage', '流程分类管理', 'md-bookmark', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (81319435670917120, 'admin', '2018-12-01 23:46:45', 0, 'admin', '2018-12-02 15:37:03', '', 'process-ins-manage', 76606430504816640, 0, 0.70, 'activiti/process-ins-manage/processInsManage', 'process-ins-manage', '运行中的流程', 'md-fastforward', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (81558529864896512, 'admin', '2018-12-02 15:36:50', 0, 'admin', '2018-12-08 14:50:38', '', 'apply-manage', 76606430504816640, 0, 0.00, 'activiti/apply-manage/applyManage', 'apply-manage', '我的申请', 'md-clipboard', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (81716172680073216, 'admin', '2018-12-03 02:03:15', 0, 'admin', '2018-12-03 02:03:15', NULL, 'todo-manage', 76606430504816640, 0, 0.10, 'activiti/todo-manage/todoManage', 'todo-manage', '我的待办', 'ios-pricetag', 2, '', 0, NULL, b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (82269650301227008, 'admin', '2018-12-04 14:42:34', 0, 'admin', '2018-12-04 14:42:34', NULL, 'done-manage', 76606430504816640, 0, 0.20, 'activiti/done-manage/doneManage', 'done-manage', '我的已办', 'ios-pricetag-outline', 2, '', 0, NULL, b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (84082369492946944, 'admin', '2018-12-09 14:45:40', 0, 'admin', '2018-12-09 14:46:59', '', '', 81558529864896512, 1, 0.00, '', '/legion/leave/add*', '添加请假申请', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84082920431554560, 'admin', '2018-12-09 14:47:51', 0, 'admin', '2020-12-11 01:18:01', '', '', 81558529864896512, 1, 4.00, '', '/legion/actBusiness/delByIds/**', '删除草稿申请', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84083562503999488, 'admin', '2018-12-09 14:50:25', 0, 'admin', '2018-12-09 14:50:25', NULL, '', 81716172680073216, 1, 0.00, '', '/legion/actTask/pass**', '审批通过', '', 3, 'enable', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84083641302388736, 'admin', '2018-12-09 14:50:43', 0, 'admin', '2018-12-09 14:50:43', NULL, '', 81716172680073216, 1, 1.00, '', '/legion/actTask/back**', '审批驳回', '', 3, 'disable', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84084404707659776, 'admin', '2018-12-09 14:53:45', 0, 'admin', '2018-12-09 14:53:45', NULL, '', 81716172680073216, 1, 3.00, '', '/legion/actTask/delegate**', '委托代办', '', 3, 'other', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84084724590448640, 'admin', '2018-12-09 14:55:02', 0, 'admin', '2018-12-09 14:55:02', NULL, '', 82269650301227008, 1, 0.00, '', '/legion/actTask/deleteHistoric/**', '删除已办任务', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84084965817454592, 'admin', '2018-12-09 14:55:59', 0, 'admin', '2018-12-09 14:55:59', NULL, '', 81319435670917120, 1, 0.00, '', '/legion/actProcess/delInsByIds/**', '删除运行流程', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84085542324539392, 'admin', '2018-12-09 14:58:17', 0, 'admin', '2018-12-09 14:58:17', NULL, '', 76914082455752704, 1, 0.00, '', '/legion/actProcess/updateInfo/**', '编辑流程信息', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84085810797744128, 'admin', '2018-12-09 14:59:21', 0, 'admin', '2018-12-09 14:59:21', NULL, '', 76914082455752704, 1, 1.00, '', '/legion/actProcess/editNodeUser**', '流程节点设置', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84085980943880192, 'admin', '2018-12-09 15:00:01', 0, 'admin', '2018-12-09 15:00:01', NULL, '', 76914082455752704, 1, 2.00, '', '/legion/actProcess/convertToModel/**', '流程转模型', '', 3, 'other', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84086163001839616, 'admin', '2018-12-09 15:00:45', 0, 'admin', '2018-12-09 15:01:37', '', '', 76914082455752704, 1, 3.00, '', '/legion/actProcess/delByIds/**', '删除流程定义', '', 3, 'delete', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84086362248056832, 'admin', '2018-12-09 15:01:32', 0, 'admin', '2018-12-09 15:01:32', NULL, '', 76914082455752704, 1, 4.00, '', '/legion/actModel/deployByFile**', '文件部署流程', '', 3, 'other', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84086882907983872, 'admin', '2018-12-09 15:03:36', 0, 'admin', '2018-12-09 15:03:36', NULL, '', 76607201262702592, 1, 0.00, '', '/legion/actModel/add**', '添加模型', '', 3, 'add', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84087009940869120, 'admin', '2018-12-09 15:04:06', 0, 'admin', '2018-12-09 15:04:06', NULL, '', 76607201262702592, 1, 1.00, '', '/legion/actModel/delByIds/**', '删除模型', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84087154040377344, 'admin', '2018-12-09 15:04:41', 0, 'admin', '2018-12-09 15:04:41', NULL, '', 76607201262702592, 1, 2.00, '', '/legion/actModel/deploy/**', '部署模型', '', 3, 'other', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84087344352727040, 'admin', '2018-12-09 15:05:26', 0, 'admin', '2018-12-09 15:05:33', '', '', 80539147005071360, 1, 0.00, '', '/legion/actCategory/add**', '添加流程分类', '', 3, 'add', 0, '', b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84087480852156416, 'admin', '2018-12-09 15:05:59', 0, 'admin', '2018-12-09 15:05:59', NULL, '', 80539147005071360, 1, 1.00, '', '/legion/actCategory/edit**', '编辑流程分类', '', 3, 'edit', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (84087593041399808, 'admin', '2018-12-09 15:06:25', 0, 'admin', '2018-12-09 15:06:25', NULL, '', 80539147005071360, 1, 2.00, '', '/legion/actCategory/delByIds/**', '删除流程分类', '', 3, 'delete', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (102235632889237504, 'admin', '2019-01-28 17:00:15', 0, 'admin', '2019-11-22 11:47:14', '', 'vue-generator', 125909152017944576, 0, 3.00, 'Main', '/vue-generator', 'Vue代码生成', 'md-send', 1, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (102237605176807424, 'admin', '2019-01-28 17:08:06', 0, 'admin', '2019-02-01 20:41:31', '', 'table-generator', 102235632889237504, 0, 0.00, 'legion-vue-generator/tableGenerator', 'table', '增删改表格生成', 'md-grid', 2, '', 0, '', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (102240052523831296, 'admin', '2019-01-28 17:17:49', 0, 'admin', '2019-02-01 20:41:38', '', 'tree-generator', 102235632889237504, 0, 1.00, 'legion-vue-generator/treeGenerator', 'tree', '树形结构生成', 'md-git-branch', 2, '', 0, '', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (103658022701633536, 'admin', '2019-02-01 15:12:20', 0, 'admin', '2019-02-01 18:38:29', '', 'test', 102235632889237504, 0, 3.00, 'legion-vue-generator/test', 'test', '代码生成测试页', 'ios-bug', 2, '', 0, '', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (113601631450304512, 'admin', '2019-03-01 01:44:41', 0, 'admin', '2019-03-01 01:44:41', NULL, '', 81716172680073216, 1, 2.00, '', '/legion/actTask/backAll/**', '审批驳回至发起人', '', 3, 'disable', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (117806106536841216, 'admin', '2019-03-12 16:11:46', 0, 'admin', '2019-03-12 16:11:46', NULL, 'process-finish-manage', 76606430504816640, 0, 0.80, 'activiti/process-finish-manage/processFinishManage', 'process-finish-manage', '结束的流程', 'md-power', 2, '', 0, NULL, b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (121426317022334976, 'admin', '2019-03-22 15:57:11', 0, 'admin', '2019-03-22 15:57:11', NULL, 'redis', 39915540965232640, 0, 2.21, 'sys/redis/redis', 'redis', 'Redis缓存管理', 'md-barcode', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (125909152017944576, 'admin', '2019-04-04 00:50:22', 0, 'admin', '2021-07-01 17:29:33', 'undefined', 'legion', 0, -1, 0.00, 'normal', '', 'Legion管理系统', 'md-home', 0, '', 0, '', b'1', b'1', b'1');
INSERT INTO `t_permission` VALUES (127995258721013760, 'admin', '2019-04-09 18:59:49', 0, 'admin', '2021-07-01 17:29:39', 'window', 'doc', 0, -1, 2.00, 'normal', '', '开发文档', 'md-document', 0, '', 0, 'https://www.kancloud.cn/cktk/legion/content', b'1', b'0', b'0');
INSERT INTO `t_permission` VALUES (127996320085446656, 'admin', '2019-04-09 19:04:02', 0, 'admin', '2021-07-01 16:31:35', '', 'app', 0, -1, 1.00, 'normal', '', 'App应用管理', 'md-phone-portrait', 0, '', 0, '', b'1', b'1', b'1');
INSERT INTO `t_permission` VALUES (149452775095275520, 'admin', '2019-06-08 00:04:19', 0, 'admin', '2020-12-13 17:01:58', '', 'admin', 39915540965232640, 0, 2.29, 'sys/monitor/monitor', '/admin', 'Admin监控', 'md-speedometer', 2, '', 0, 'http://127.0.0.1:8888/legion/admin/wallboard', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (156365156580855808, 'admin', '2019-06-27 01:51:39', 0, 'admin', '2019-06-27 01:51:39', NULL, '', 5129710648430593, 1, 1.18, '', '/legion/user/resetPass', '重置密码', '', 3, 'other', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (160509731956330496, 'admin', '2019-07-08 12:20:43', 0, 'admin', '2019-07-08 12:22:13', '', 'weapp', 127996320085446656, 0, 0.00, 'Main', '/weapp', '微信小程序', 'ios-appstore', 1, '', 0, '', b'0', NULL, b'1');
INSERT INTO `t_permission` VALUES (160509918166650881, 'admin', '2019-07-08 12:21:28', 0, 'admin', '2020-04-27 11:10:54', '', 'weapp', 160509731956330496, 0, 0.00, 'weapp/weapp', 'weapp', '微信小程序', 'md-phone-portrait', 2, '', 0, '', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (211251162815401984, 'admin', '2019-11-25 12:49:03', 0, 'admin', '2019-11-25 12:49:12', '', 'open', 125909152017944576, 0, 1.20, 'Main', '/open', '开放平台', 'ios-apps', 1, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (211251678651879424, 'admin', '2019-11-25 12:51:06', 0, 'admin', '2020-12-10 12:16:22', '', 'client', 211251162815401984, 0, 0.00, 'open/client/client', 'client', '接入网站管理', 'md-browsers', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (211652331387752448, 'admin', '2019-11-26 15:23:09', 0, 'admin', '2020-12-10 12:16:35', '', 'doc', 211251162815401984, 0, 1.00, 'sys/monitor/monitor', 'doc', '开放平台文档', 'md-document', 2, '', 0, 'https://www.kancloud.cn/cktk/legion/1399478', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (1251152298521006080, 'admin', '2020-04-17 22:15:28', 0, 'admin', '2020-04-27 18:30:59', '', 'member', 127996320085446656, 0, 2.00, 'Main', '/member', '会员管理', 'md-contact', 1, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (1251153074307862528, 'admin', '2020-04-17 22:18:33', 0, 'admin', '2020-04-27 18:31:17', '', 'member', 1251152298521006080, 0, 0.00, 'app/member/member', 'member', '注册会员管理', 'md-contacts', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (1251760630449442816, 'admin', '2020-04-19 14:32:46', 0, 'admin', '2020-04-27 18:31:03', '', 'member-log', 127996320085446656, 0, 3.00, 'Main', '/member-log', '日志记录', 'md-list-box', 1, '', 0, '', b'1', NULL, b'0');
INSERT INTO `t_permission` VALUES (1251761480286736384, 'admin', '2020-04-19 14:36:08', 0, 'admin', '2021-10-13 16:33:40', '', 'member-log', 1251760630449442816, 0, 0.00, 'sys/log-manage/logManage', 'member-log', '日志记录', 'md-list-box', 2, '', 0, '', b'1', NULL, b'1');
INSERT INTO `t_permission` VALUES (1254739560500432897, 'admin', '2020-04-27 19:49:58', 0, 'admin', '2020-04-27 19:49:58', NULL, '', 81716172680073216, 1, 0.00, '', '/legion/actTask/passAll/**', '批量通过', '', 3, 'other', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (1254739666461134848, 'admin', '2020-04-27 19:50:23', 0, 'admin', '2020-04-27 19:50:23', NULL, '', 81716172680073216, 1, 0.00, '', '/legion/actTask/back**', '批量驳回', '', 3, 'disable', 0, NULL, b'1', NULL, NULL);
INSERT INTO `t_permission` VALUES (1337084109473845248, 'admin', '2020-12-11 01:17:48', 0, 'admin', '2020-12-11 01:17:48', NULL, '', 81558529864896512, 1, 3.00, '', '/legion/actBusiness/apply*', '提交申请', '', 3, 'enable', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337084263631294465, 'admin', '2020-12-11 01:18:25', 0, 'admin', '2020-12-11 01:18:32', '', '', 81558529864896512, 1, 3.00, '', '/legion/actBusiness/cancel*', '撤回申请', '', 3, 'disable', 0, '', b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337084380564295681, 'admin', '2020-12-11 01:18:53', 0, 'admin', '2020-12-11 01:18:53', NULL, '', 117806106536841216, 1, 1.00, '', '/legion/actProcess/delHistoricInsByIds**', '删除结束流程', '', 3, 'delete', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087041741131777, 'admin', '2020-12-11 01:29:27', 0, 'admin', '2020-12-11 01:29:27', NULL, '', 76914082455752704, 1, 0.00, '', '/legion/actProcess/editStartUser**', '编辑流程发起人', '', 3, 'edit', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087257902977024, 'admin', '2020-12-11 01:30:19', 0, 'admin', '2020-12-11 01:30:19', NULL, '', 211251678651879424, 1, 1.00, '', '/legion/client/save**', '添加网站', '', 3, 'add', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087364513796097, 'admin', '2020-12-11 01:30:44', 0, 'admin', '2020-12-11 01:31:28', '', '', 211251678651879424, 1, 3.00, '', '/legion/client/delByIds**', '删除网站', '', 3, 'delete', 0, '', b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087482155634689, 'admin', '2020-12-11 01:31:12', 0, 'admin', '2020-12-11 01:31:32', '', '', 211251678651879424, 1, 2.00, '', '/legion/client/update**', '编辑网站', '', 3, 'edit', 0, '', b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087799127576577, 'admin', '2020-12-11 01:32:28', 0, 'admin', '2020-12-11 01:32:28', NULL, '', 121426317022334976, 1, 1.00, '', '/legion/redis/getAllByPage**', '获取Redis', '', 3, 'view', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087867276627969, 'admin', '2020-12-11 01:32:44', 0, 'admin', '2020-12-11 01:32:44', NULL, '', 121426317022334976, 1, 2.00, '', '/legion/redis/save', '添加Redis', '', 3, 'add', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337087940756639745, 'admin', '2020-12-11 01:33:02', 0, 'admin', '2020-12-11 01:33:02', NULL, '', 121426317022334976, 1, 2.00, '', '/legion/redis/delByKeys**', '删除Redis', '', 3, 'delete', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337088037640867841, 'admin', '2020-12-11 01:33:25', 0, 'admin', '2020-12-11 01:33:25', NULL, '', 121426317022334976, 1, 2.00, '', '/legion/redis/delAll', '清空Redis', '', 3, 'clear', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337088390826430465, 'admin', '2020-12-11 01:34:49', 0, 'admin', '2020-12-11 01:34:49', NULL, '', 1251153074307862528, 1, 1.00, '', '/legion/app/member/admin/add**', '添加会员', '', 3, 'add', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337088472535666689, 'admin', '2020-12-11 01:35:08', 0, 'admin', '2020-12-11 01:35:08', NULL, '', 1251153074307862528, 1, 2.00, '', '/legion/app/member/admin/edit**', '编辑会员', '', 3, 'edit', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1337088563610783745, 'admin', '2020-12-11 01:35:30', 0, 'admin', '2020-12-11 01:35:30', NULL, '', 1251153074307862528, 1, 3.00, '', '/legion/app/member/delByIds**', '删除会员', '', 3, 'delete', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1347151276110123008, 'admin', '2021-01-07 20:01:08', 0, 'admin', '2021-01-07 20:16:04', '', 'auto-chat', 125909152017944576, 0, 1.30, 'Main', '/auto-chat', '智能助手客服', 'logo-twitch', 1, '', 0, '', b'1', b'1', b'1');
INSERT INTO `t_permission` VALUES (1347154151167102977, 'admin', '2021-01-07 20:12:33', 0, 'admin', '2021-01-19 18:51:15', '', 'chat-setting', 1347151276110123008, 0, 1.00, 'auto-chat/setting/setting', 'chat-setting', '页面基本配置', 'md-desktop', 2, '', 0, '', b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1347154848814075905, 'admin', '2021-01-07 20:15:20', 0, 'admin', '2021-01-29 02:07:55', '', 'chat-reply', 1347151276110123008, 0, 2.00, 'auto-chat/reply/reply', 'chat-reply', '自动回复配置', 'ios-chatboxes', 2, '', 0, '', b'1', b'1', b'1');
INSERT INTO `t_permission` VALUES (1354853724400521217, 'admin', '2021-01-29 02:07:55', 0, 'admin', '2021-01-29 02:08:58', '', '', 1347154848814075905, 1, 1.00, '', '/legion/autoChat/save**', '添加回复', '', 3, 'add', 0, '', b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1354853977182834688, 'admin', '2021-01-29 02:08:55', 0, 'admin', '2021-01-29 02:08:55', NULL, '', 1347154848814075905, 1, 2.00, '', '/legion/autoChat/update**', '编辑回复', '', 3, 'edit', 0, NULL, b'1', b'1', b'0');
INSERT INTO `t_permission` VALUES (1354854134687338496, 'admin', '2021-01-29 02:09:32', 0, 'admin', '2021-01-29 02:09:32', NULL, '', 1347154848814075905, 1, 3.00, '', '/legion/autoChat/delByIds**', '删除回复', '', 3, 'delete', 0, NULL, b'1', b'1', b'0');
COMMIT;

-- ----------------------------
-- Table structure for t_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `job_class_name` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_quartz_job
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `default_role` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `data_type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (496138616573952, '', '2018-04-22 23:03:49', 'admin', '2018-11-15 23:02:59', 'ROLE_ADMIN', 0, NULL, '超级管理员 拥有所有权限', 0);
INSERT INTO `t_role` VALUES (496138616573953, '', '2018-05-02 21:40:03', 'admin', '2018-11-01 22:59:48', 'ROLE_USER', 0, b'1', '普通注册用户 路过看看', 0);
INSERT INTO `t_role` VALUES (16457350655250432, '', '2018-06-06 00:08:00', 'admin', '2018-11-02 20:42:24', 'ROLE_TEST', 0, NULL, '测试权限按钮显示', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_role_department
-- ----------------------------
DROP TABLE IF EXISTS `t_role_department`;
CREATE TABLE `t_role_department` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `department_id` bigint(20) unsigned DEFAULT NULL,
  `role_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_department
-- ----------------------------
BEGIN;
INSERT INTO `t_role_department` VALUES (70763874256687105, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322777781112832, 16457350655250432);
INSERT INTO `t_role_department` VALUES (70763874265075712, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322811096469504, 16457350655250432);
INSERT INTO `t_role_department` VALUES (70763874277658624, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322852833988608, 16457350655250432);
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `permission_id` bigint(20) unsigned DEFAULT NULL,
  `role_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES (1453631719298174976, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 125909152017944576, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174977, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 5129710648430592, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174978, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 5129710648430593, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174979, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 15701400130424832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174980, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 16678126574637056, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174981, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 15701915807518720, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174982, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 15708892205944832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174983, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 16678447719911424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174984, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 25014528525733888, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174985, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 56898976661639168, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174986, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 156365156580855808, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174987, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 40238597734928384, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174988, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 45235621697949696, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174989, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 45235787867885568, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174990, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 45235939278065664, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174991, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 43117268627886080, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174992, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 45236734832676864, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174993, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 45237010692050944, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174994, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 45237170029465600, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174995, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 57009544286441472, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174996, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 57009744761589760, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174997, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 57009981228060672, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174998, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 56309618086776832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298174999, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 57212882168844288, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175000, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 61560041605435392, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175001, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 61560275261722624, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175002, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 61560480518377472, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175003, 'admin', '2021-10-28 15:56:24', 0, 'admin', '2021-10-28 15:56:24', 44986029924421632, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175004, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 45235228800716800, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175005, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 45069342940860416, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175006, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 5129710648430594, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175007, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16687383932047360, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175008, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16689632049631232, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175009, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16689745006432256, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175010, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16689883993083904, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175011, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16690313745666048, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175012, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 5129710648430595, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175013, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16694861252005888, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175014, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16695107491205120, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175015, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 16695243126607872, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175016, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 75002207560273920, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175017, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76215889006956544, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175018, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76216071333351424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175019, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76216264070008832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175020, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76216459709124608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175021, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76216594207870976, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175022, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76216702639017984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175023, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 58480609315524608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175024, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 61394706252173312, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175025, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 61417744146370560, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175026, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76606430504816640, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175027, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 81558529864896512, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175028, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84082369492946944, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175029, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 1337084109473845248, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175030, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 1337084263631294465, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175031, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84082920431554560, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175032, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 81716172680073216, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175033, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84083562503999488, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175034, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 1254739560500432897, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175035, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 1254739666461134848, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175036, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84083641302388736, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175037, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 113601631450304512, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175038, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84084404707659776, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175039, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 82269650301227008, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175040, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84084724590448640, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175041, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 81319435670917120, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175042, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84084965817454592, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175043, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 117806106536841216, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175044, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 1337084380564295681, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175045, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 76914082455752704, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175046, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 84085542324539392, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175047, 'admin', '2021-10-28 15:56:25', 0, 'admin', '2021-10-28 15:56:25', 1337087041741131777, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175048, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84085810797744128, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175049, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84085980943880192, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175050, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84086163001839616, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175051, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84086362248056832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175052, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 76607201262702592, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175053, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84086882907983872, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175054, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84087009940869120, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175055, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84087154040377344, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175056, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 80539147005071360, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175057, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84087344352727040, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175058, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84087480852156416, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175059, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 84087593041399808, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175060, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 211251162815401984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175061, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 211251678651879424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175062, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337087257902977024, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175063, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337087482155634689, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175064, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337087364513796097, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175065, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 211652331387752448, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175066, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1347151276110123008, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175067, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1347154151167102977, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175068, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1347154848814075905, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175069, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1354853724400521217, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175070, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1354853977182834688, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175071, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1354854134687338496, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175072, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 39915540965232640, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175073, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 41370251991977984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175074, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 45264987354042368, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175075, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 45265487029866496, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175076, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 45265762415284224, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175077, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 45265886315024384, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175078, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 45266070000373760, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175079, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 41363147411427328, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175080, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 41363537456533504, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175081, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 41364927394353152, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175082, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 121426317022334976, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175083, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337087799127576577, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175084, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337087867276627969, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175085, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337087940756639745, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175086, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 1337088037640867841, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175087, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 149452775095275520, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175088, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 41371711400054784, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175089, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 41469219249852416, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175090, 'admin', '2021-10-28 15:56:26', 0, 'admin', '2021-10-28 15:56:26', 39916171171991552, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175091, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 39918482854252544, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175092, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 102235632889237504, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175093, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 102237605176807424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175094, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 102240052523831296, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175095, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 103658022701633536, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175096, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 127996320085446656, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175097, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 160509731956330496, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175098, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 160509918166650881, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175099, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1251152298521006080, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175100, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1251153074307862528, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175101, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1337088390826430465, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175102, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1337088472535666689, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175103, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1337088563610783745, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175104, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1251760630449442816, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1453631719298175105, 'admin', '2021-10-28 15:56:27', 0, 'admin', '2021-10-28 15:56:27', 1251761480286736384, 496138616573952);
COMMIT;

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `value` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_setting` VALUES ('ALI_OSS', 'admin', '2021-10-13 15:44:46', 0, 'admin', '2021-10-27 22:09:27', '{\"serviceName\":\"ALI_OSS\",\"accessKey\":\"LTAI5tLD2EQxFHUudMFYRBjM\",\"secretKey\":\"yviJavS7GMJWqbxqosVAMWNoI3TpQs\",\"endpoint\":\"oss-cn-qingdao.aliyuncs.com\",\"bucket\":\"c0c0\",\"http\":\"http://\",\"bucketRegion\":\"\",\"filePath\":\"\",\"changed\":true}');
INSERT INTO `t_setting` VALUES ('OSS_USED', 'admin', '2021-10-13 15:44:46', 0, 'admin', '2021-10-13 15:44:46', 'ALI_OSS');
INSERT INTO `t_setting` VALUES ('OTHER_SETTING', 'admin', '2021-10-13 17:09:22', 0, 'admin', '2021-10-13 17:17:58', '{\"domain\":\" http://192.168.0.128:8888/api\",\"ssoDomain\":\"\",\"blacklist\":\"\"}');
COMMIT;

-- ----------------------------
-- Table structure for t_social
-- ----------------------------
DROP TABLE IF EXISTS `t_social`;
CREATE TABLE `t_social` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `platform` tinyint(1) unsigned DEFAULT NULL,
  `relate_username` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `relate_open_id` (`open_id`,`platform`) USING BTREE,
  UNIQUE KEY `relate_username` (`relate_username`,`platform`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_social
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_stop_word
-- ----------------------------
DROP TABLE IF EXISTS `t_stop_word`;
CREATE TABLE `t_stop_word` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_stop_word
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `department_id` bigint(20) unsigned DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `pass_strength` varchar(2) DEFAULT NULL,
  `department_title` varchar(255) DEFAULT NULL,
  `birth` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (682265633886208, '', '2018-05-01 16:13:51', 'admin', '2020-04-12 22:03:47', '北京市,市辖区,东城区', 'https://ooo.0o0.ooo/2019/04/28/5cc5a71a6e3b6.png', '我是大帅逼', 'admin@cktk.cn', '18782059031', '管理员', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '男', 0, 1, 'admin', 0, 40322777781112832, '天府1街', '弱', '总部', '2020-04-15 00:00:00.000000');
INSERT INTO `t_user` VALUES (4363087427670016, '', '2018-05-03 15:09:42', 'admin', '2021-10-27 22:16:56', '北京市,市辖区,东城区', 'https://ooo.0o0.ooo/2019/04/28/5cc5a71a6e3b6.png', '', 'test@cktk.cn', '18782059033', '游客1', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '男', 0, 0, 'test', 0, 40322777781112832, '', '弱', '总部', '2020-04-28 00:00:00.000000');
INSERT INTO `t_user` VALUES (16739222421508096, '', '2018-06-06 18:48:02', 'admin', '2020-04-27 22:01:24', '北京市,市辖区,东城区', 'https://ooo.0o0.ooo/2019/04/28/5cc5a71a6e3b6.png', '', 'test2@cktk.cn', '18782059034', '游客2', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '男', 0, 0, 'test2', 0, 40322777781112832, '', '弱', '总部', '2020-04-23 00:00:00.000000');
INSERT INTO `t_user` VALUES (1448197635516272640, 'admin', '2021-10-13 16:03:18', 'admin', '2021-10-13 16:03:46', '北京市,市辖区,朝阳区', 'https://ooo.0o0.ooo/2019/04/28/5cc5a71a6e3b6.png', '', 'test3@qq.com', '18782059035', '游客3', '$2a$10$Zdj7g7twwa.LyrTsZNiD/eQRFseHsVxfrYDf3lEMV.xV4EFnIjLfO', '女', 0, 1, 'test3', 0, 40322777781112832, '', '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1448200832830017536, 'admin', '2021-10-13 16:16:00', 'admin', '2021-10-27 22:16:23', '北京市,市辖区,东城区', '', '', 'legion1@qq.com', '18782059036', 'legion1', '$2a$10$54voAwqvFkdOXDqykULQwudSQGjqaADT6Sbp.Si.ehbVAw.7LMAJS', '男', 0, 0, 'admin1', 0, 40322777781112832, '', '', '总部', '1997-12-02 00:00:00.000000');
INSERT INTO `t_user` VALUES (1448200832830017537, 'admin', '2021-10-13 16:16:00', 'admin', '2021-10-27 22:16:12', '北京市,市辖区,东城区', '', '', 'legion2@qq.com', '18782059037', 'legion2', '$2a$10$BGCBRGhi4imDa9QeL29.kOydTDEfzBiu6KkJQMdqP.e4ZFo5alSTO', '女', 0, 1, 'admin2', 0, 40322777781112832, '', '', '总部', '1997-12-02 00:00:00.000000');
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) unsigned NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES (1453364912683356160, 'admin', '2021-10-27 22:16:12', 0, 'admin', '2021-10-27 22:16:12', 496138616573953, 1448200832830017537);
INSERT INTO `t_user_role` VALUES (1453364958975889408, 'admin', '2021-10-27 22:16:23', 0, 'admin', '2021-10-27 22:16:23', 496138616573953, 1448200832830017536);
INSERT INTO `t_user_role` VALUES (1453364977967697920, 'admin', '2021-10-27 22:16:28', 0, 'admin', '2021-10-27 22:16:28', 496138616573952, 1448197635516272640);
INSERT INTO `t_user_role` VALUES (1453364999979405312, 'admin', '2021-10-27 22:16:33', 0, 'admin', '2021-10-27 22:16:33', 16457350655250432, 16739222421508096);
INSERT INTO `t_user_role` VALUES (1453365020351139840, 'admin', '2021-10-27 22:16:38', 0, 'admin', '2021-10-27 22:16:38', 496138616573952, 682265633886208);
INSERT INTO `t_user_role` VALUES (1453365095815057408, 'admin', '2021-10-27 22:16:56', 0, 'admin', '2021-10-27 22:16:56', 496138616573953, 4363087427670016);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
