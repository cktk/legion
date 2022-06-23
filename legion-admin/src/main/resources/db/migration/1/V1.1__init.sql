
DROP TABLE IF EXISTS `app_member`;
CREATE TABLE `app_member` (
	                          `id` bigint unsigned NOT NULL,
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
	                          `grade` int unsigned DEFAULT NULL,
	                          `position` varchar(255) DEFAULT NULL,
	                          `invite_by` varchar(255) DEFAULT NULL,
	                          `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
	                          `create_id` varchar(64) DEFAULT NULL,
	                          `update_id` varchar(64) DEFAULT NULL,
	                          PRIMARY KEY (`id`),
	                          UNIQUE KEY `username` (`username`),
	                          UNIQUE KEY `mobile` (`mobile`) USING BTREE,
	                          UNIQUE KEY `invite_code` (`invite_code`) USING BTREE,
	                          KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



INSERT INTO `app_member` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `avatar`, `email`, `mobile`, `nickname`, `password`, `platform`, `sex`, `status`, `type`, `username`, `vip_end_time`, `vip_start_time`, `permissions`, `vip_status`, `birth`, `address`, `invite_code`, `grade`, `position`, `invite_by`, `description`, `create_id`, `update_id`) VALUES (255227305549369344, '', '2020-03-25 21:14:32.000000', 0, 'admin', '2020-04-27 17:20:45.307000', 'https://ooo.0o0.ooo/2020/04/18/NmF3IP4TOoVbLf5.png', '', '18782059031', '187****9031', NULL, -1, '', 0, 1, '1251014922381430800', '2030-04-30 00:00:00.000000', '2020-04-01 00:00:00.000000', 'MEMBER', 1, NULL, '中国 北京市 北京市 朝阳区 酒仙桥路 3号 电子城．国际电子总部', '12N3VE6CK440G', 0, '30.5681733200,104.0666198700', '', NULL, NULL, NULL);
INSERT INTO `app_member` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `avatar`, `email`, `mobile`, `nickname`, `password`, `platform`, `sex`, `status`, `type`, `username`, `vip_end_time`, `vip_start_time`, `permissions`, `vip_status`, `birth`, `address`, `invite_code`, `grade`, `position`, `invite_by`, `description`, `create_id`, `update_id`) VALUES (1251783645962833920, 'admin', '2020-04-19 16:04:12.000000', 0, 'admin', '2020-04-27 17:24:23.091000', 'https://ooo.0o0.ooo/2020/04/18/NmF3IP4TOoVbLf5.png', '', '18782059092', '187****9092', NULL, 1, '', 0, 1, '1251783646004776960', '2020-04-07 00:00:00.000000', '2020-04-01 00:00:00.000000', 'MEMBER', 2, NULL, '中国 北京市 北京市 朝阳区 酒仙桥路 3号 电子城．国际电子总部', '12NPQJ01S4400', 0, '30.5681733200,104.0666198700', '1251014922381430800', NULL, NULL, NULL);
INSERT INTO `app_member` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `avatar`, `email`, `mobile`, `nickname`, `password`, `platform`, `sex`, `status`, `type`, `username`, `vip_end_time`, `vip_start_time`, `permissions`, `vip_status`, `birth`, `address`, `invite_code`, `grade`, `position`, `invite_by`, `description`, `create_id`, `update_id`) VALUES (1251783729291071489, 'admin', '2020-04-19 16:04:32.000000', 0, 'admin', '2020-04-27 22:03:48.509000', 'https://ooo.0o0.ooo/2020/04/18/NmF3IP4TOoVbLf5.png', '', '18782059033', '187****9093', NULL, 2, '', 0, 0, '1251783729303654400', NULL, NULL, 'MEMBER', 0, NULL, '中国 北京市 北京市 朝阳区 酒仙桥路 3号 电子城．国际电子总部', '12NPQLDKC4400', 0, '30.5681733200,104.0666198700', '1251014922381430800', NULL, NULL, NULL);



DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
	                                  `SCHED_NAME` varchar(120) NOT NULL,
	                                  `TRIGGER_NAME` varchar(200) NOT NULL,
	                                  `TRIGGER_GROUP` varchar(200) NOT NULL,
	                                  `BLOB_DATA` blob,
	                                  `create_id` varchar(64) DEFAULT NULL,
	                                  `update_id` varchar(64) DEFAULT NULL,
	                                  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
	                                  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
	                              `SCHED_NAME` varchar(120) NOT NULL,
	                              `CALENDAR_NAME` varchar(200) NOT NULL,
	                              `CALENDAR` blob NOT NULL,
	                              `create_id` varchar(64) DEFAULT NULL,
	                              `update_id` varchar(64) DEFAULT NULL,
	                              PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
	                                  `SCHED_NAME` varchar(120) NOT NULL,
	                                  `TRIGGER_NAME` varchar(200) NOT NULL,
	                                  `TRIGGER_GROUP` varchar(200) NOT NULL,
	                                  `CRON_EXPRESSION` varchar(200) NOT NULL,
	                                  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
	                                  `create_id` varchar(64) DEFAULT NULL,
	                                  `update_id` varchar(64) DEFAULT NULL,
	                                  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
	                                  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
	                                   `SCHED_NAME` varchar(120) NOT NULL,
	                                   `ENTRY_ID` varchar(95) NOT NULL,
	                                   `TRIGGER_NAME` varchar(200) NOT NULL,
	                                   `TRIGGER_GROUP` varchar(200) NOT NULL,
	                                   `INSTANCE_NAME` varchar(200) NOT NULL,
	                                   `FIRED_TIME` bigint NOT NULL,
	                                   `SCHED_TIME` bigint NOT NULL,
	                                   `PRIORITY` int NOT NULL,
	                                   `STATE` varchar(16) NOT NULL,
	                                   `JOB_NAME` varchar(200) DEFAULT NULL,
	                                   `JOB_GROUP` varchar(200) DEFAULT NULL,
	                                   `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
	                                   `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
	                                   `create_id` varchar(64) DEFAULT NULL,
	                                   `update_id` varchar(64) DEFAULT NULL,
	                                   PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
	                                `create_id` varchar(64) DEFAULT NULL,
	                                `update_id` varchar(64) DEFAULT NULL,
	                                PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
	                          `SCHED_NAME` varchar(120) NOT NULL,
	                          `LOCK_NAME` varchar(40) NOT NULL,
	                          `create_id` varchar(64) DEFAULT NULL,
	                          `update_id` varchar(64) DEFAULT NULL,
	                          PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`, `create_id`, `update_id`) VALUES ('quartzScheduler', 'TRIGGER_ACCESS', NULL, NULL);



DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
	                                        `SCHED_NAME` varchar(120) NOT NULL,
	                                        `TRIGGER_GROUP` varchar(200) NOT NULL,
	                                        `create_id` varchar(64) DEFAULT NULL,
	                                        `update_id` varchar(64) DEFAULT NULL,
	                                        PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
	                                    `SCHED_NAME` varchar(120) NOT NULL,
	                                    `INSTANCE_NAME` varchar(200) NOT NULL,
	                                    `LAST_CHECKIN_TIME` bigint NOT NULL,
	                                    `CHECKIN_INTERVAL` bigint NOT NULL,
	                                    `create_id` varchar(64) DEFAULT NULL,
	                                    `update_id` varchar(64) DEFAULT NULL,
	                                    PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
	                                    `SCHED_NAME` varchar(120) NOT NULL,
	                                    `TRIGGER_NAME` varchar(200) NOT NULL,
	                                    `TRIGGER_GROUP` varchar(200) NOT NULL,
	                                    `REPEAT_COUNT` bigint NOT NULL,
	                                    `REPEAT_INTERVAL` bigint NOT NULL,
	                                    `TIMES_TRIGGERED` bigint NOT NULL,
	                                    `create_id` varchar(64) DEFAULT NULL,
	                                    `update_id` varchar(64) DEFAULT NULL,
	                                    PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
	                                    CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
	                                     `SCHED_NAME` varchar(120) NOT NULL,
	                                     `TRIGGER_NAME` varchar(200) NOT NULL,
	                                     `TRIGGER_GROUP` varchar(200) NOT NULL,
	                                     `STR_PROP_1` varchar(512) DEFAULT NULL,
	                                     `STR_PROP_2` varchar(512) DEFAULT NULL,
	                                     `STR_PROP_3` varchar(512) DEFAULT NULL,
	                                     `INT_PROP_1` int DEFAULT NULL,
	                                     `INT_PROP_2` int DEFAULT NULL,
	                                     `LONG_PROP_1` bigint DEFAULT NULL,
	                                     `LONG_PROP_2` bigint DEFAULT NULL,
	                                     `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
	                                     `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
	                                     `BOOL_PROP_1` varchar(1) DEFAULT NULL,
	                                     `BOOL_PROP_2` varchar(1) DEFAULT NULL,
	                                     `create_id` varchar(64) DEFAULT NULL,
	                                     `update_id` varchar(64) DEFAULT NULL,
	                                     PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
	                                     CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
	                             `SCHED_NAME` varchar(120) NOT NULL,
	                             `TRIGGER_NAME` varchar(200) NOT NULL,
	                             `TRIGGER_GROUP` varchar(200) NOT NULL,
	                             `JOB_NAME` varchar(200) NOT NULL,
	                             `JOB_GROUP` varchar(200) NOT NULL,
	                             `DESCRIPTION` varchar(250) DEFAULT NULL,
	                             `NEXT_FIRE_TIME` bigint DEFAULT NULL,
	                             `PREV_FIRE_TIME` bigint DEFAULT NULL,
	                             `PRIORITY` int DEFAULT NULL,
	                             `TRIGGER_STATE` varchar(16) NOT NULL,
	                             `TRIGGER_TYPE` varchar(8) NOT NULL,
	                             `START_TIME` bigint NOT NULL,
	                             `END_TIME` bigint DEFAULT NULL,
	                             `CALENDAR_NAME` varchar(200) DEFAULT NULL,
	                             `MISFIRE_INSTR` smallint DEFAULT NULL,
	                             `JOB_DATA` blob,
	                             `create_id` varchar(64) DEFAULT NULL,
	                             `update_id` varchar(64) DEFAULT NULL,
	                             PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
	                             KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
	                             CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;DROP TABLE IF EXISTS `t_auto_chat`;
CREATE TABLE `t_auto_chat` (
	                           `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                           `title` varchar(255) DEFAULT NULL,
	                           `keywords` text,
	                           `create_by` varchar(255) DEFAULT NULL,
	                           `create_time` datetime(6) DEFAULT NULL,
	                           `del_flag` int DEFAULT NULL,
	                           `update_by` varchar(255) DEFAULT NULL,
	                           `update_time` datetime(6) DEFAULT NULL,
	                           `bad` int DEFAULT NULL,
	                           `good` int DEFAULT NULL,
	                           `content` text,
	                           `sort_order` decimal(10,2) DEFAULT NULL,
	                           `evaluable` bit(1) DEFAULT NULL,
	                           `hot` bit(1) DEFAULT NULL,
	                           `create_id` varchar(64) DEFAULT NULL,
	                           `update_id` varchar(64) DEFAULT NULL,
	                           PRIMARY KEY (`id`),
	                           FULLTEXT KEY `search` (`title`,`keywords`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client` (
	                        `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                        `create_by` varchar(255) DEFAULT NULL,
	                        `create_time` datetime(6) DEFAULT NULL,
	                        `del_flag` tinyint(1) DEFAULT NULL,
	                        `update_by` varchar(255) DEFAULT NULL,
	                        `update_time` datetime(6) DEFAULT NULL,
	                        `client_secret` varchar(255) DEFAULT NULL,
	                        `home_uri` varchar(255) DEFAULT NULL,
	                        `name` varchar(255) DEFAULT NULL,
	                        `redirect_uri` varchar(255) DEFAULT NULL,
	                        `create_id` varchar(64) DEFAULT NULL,
	                        `update_id` varchar(64) DEFAULT NULL,
	                        `auto_approve` varchar(255) DEFAULT NULL,
	                        `logo` varchar(255) DEFAULT NULL,
	                        PRIMARY KEY (`id`),
	                        KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_datasource_conf`;
CREATE TABLE `t_datasource_conf` (
	                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	                                 `name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '别名',
	                                 `url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'jdbcurl',
	                                 `username` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
	                                 `password` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
	                                 `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	                                 `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新',
	                                 `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标记',
	                                 `tenant_id` int DEFAULT NULL COMMENT '租户ID',
	                                 `ds_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库类型',
	                                 `conf_type` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置类型',
	                                 `ds_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库名称',
	                                 `instance` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实例',
	                                 `port` int DEFAULT NULL COMMENT '端口',
	                                 `host` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主机',
	                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据源表';

DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
	                            `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                            `create_by` varchar(255) DEFAULT NULL,
	                            `create_time` datetime DEFAULT NULL,
	                            `del_flag` int DEFAULT NULL,
	                            `update_by` varchar(255) DEFAULT NULL,
	                            `update_time` datetime DEFAULT NULL,
	                            `parent_id` bigint unsigned NOT NULL,
	                            `sort_order` decimal(10,2) DEFAULT NULL,
	                            `status` tinyint(1) DEFAULT NULL,
	                            `title` varchar(255) DEFAULT NULL,
	                            `is_parent` bit(1) DEFAULT NULL,
	                            `create_id` varchar(64) DEFAULT NULL,
	                            `update_id` varchar(64) DEFAULT NULL,
	                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40681289119961089 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40322777781112832, '', '2018-08-10 20:40:40', 0, '', '2018-08-11 00:03:06', 0, 1.00, 0, '总部', b'1', NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40322811096469504, '', '2018-08-10 20:40:48', 0, '', '2018-08-11 00:27:05', 40322777781112832, 1.00, 0, '技术部', b'1', NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40322852833988608, '', '2018-08-10 20:40:58', 0, '', '2018-08-11 01:29:42', 40322811096469504, 1.00, 0, '研发中心', NULL, NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40327204755738624, '', '2018-08-10 20:58:15', 0, '', '2018-08-10 22:02:15', 40322811096469504, 2.00, 0, '大数据', NULL, NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40327253309001728, '', '2018-08-10 20:58:27', 0, '', '2018-08-11 17:26:38', 40322811096469504, 3.00, -1, '人工智障', NULL, NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40343262766043136, '', '2018-08-10 22:02:04', 0, '', '2018-08-11 00:02:53', 0, 2.00, 0, '成都分部', b'1', NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40344005342400512, '', '2018-08-10 22:05:01', 0, '', '2018-08-11 17:48:44', 40343262766043136, 2.00, 0, 'Vue', NULL, NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40389030113710080, '', '2018-08-11 01:03:56', 0, '', '2018-08-11 17:50:04', 40343262766043136, 1.00, 0, 'JAVA', b'0', NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40652270295060480, '', '2018-08-11 18:29:57', 0, '', '2018-08-12 18:45:01', 0, 3.00, 0, '人事部', b'1', NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40652338142121984, NULL, '2018-08-11 18:30:13', 0, NULL, '2018-08-11 18:30:13', 40652270295060480, 1.00, 0, '游客', b'0', NULL, NULL);
INSERT INTO `t_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `parent_id`, `sort_order`, `status`, `title`, `is_parent`, `create_id`, `update_id`) VALUES (40681289119961088, '', '2018-08-11 20:25:16', 0, '', '2018-08-11 22:47:48', 40652270295060480, 2.00, 0, 'VIP', b'0', NULL, NULL);



DROP TABLE IF EXISTS `t_department_header`;
CREATE TABLE `t_department_header` (
	                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                                   `create_by` varchar(255) DEFAULT NULL,
	                                   `create_time` datetime DEFAULT NULL,
	                                   `del_flag` tinyint(1) DEFAULT NULL,
	                                   `update_by` varchar(255) DEFAULT NULL,
	                                   `update_time` datetime DEFAULT NULL,
	                                   `department_id` bigint unsigned DEFAULT NULL,
	                                   `type` tinyint(1) DEFAULT NULL,
	                                   `user_id` bigint unsigned DEFAULT NULL,
	                                   `create_id` varchar(64) DEFAULT NULL,
	                                   `update_id` varchar(64) DEFAULT NULL,
	                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1254427833757995011 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_department_header` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `department_id`, `type`, `user_id`, `create_id`, `update_id`) VALUES (1254427833757995008, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 0, 682265633886208, NULL, NULL);
INSERT INTO `t_department_header` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `department_id`, `type`, `user_id`, `create_id`, `update_id`) VALUES (1254427833757995009, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 0, 16739222421508096, NULL, NULL);
INSERT INTO `t_department_header` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `department_id`, `type`, `user_id`, `create_id`, `update_id`) VALUES (1254427833757995010, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 1, 16739222421508096, NULL, NULL);



DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
	                      `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                      `create_by` varchar(255) DEFAULT NULL,
	                      `create_time` datetime DEFAULT NULL,
	                      `del_flag` tinyint(1) DEFAULT NULL,
	                      `update_by` varchar(255) DEFAULT NULL,
	                      `update_time` datetime DEFAULT NULL,
	                      `description` varchar(255) DEFAULT NULL,
	                      `title` varchar(255) DEFAULT NULL,
	                      `type` varchar(255) DEFAULT NULL,
	                      `sort_order` decimal(10,2) DEFAULT NULL,
	                      `create_id` varchar(64) DEFAULT NULL,
	                      `update_id` varchar(64) DEFAULT NULL,
	                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99020862912466945 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_dict` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `title`, `type`, `sort_order`, `create_id`, `update_id`) VALUES (75135930788220928, 'admin', '2018-11-14 22:15:43', 0, 'admin', '2018-11-27 01:39:06', '', '性别', 'sex', 0.00, NULL, NULL);
INSERT INTO `t_dict` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `title`, `type`, `sort_order`, `create_id`, `update_id`) VALUES (75383353938808832, 'admin', '2018-11-15 14:38:53', 0, 'admin', '2018-11-27 01:39:15', '', '消息类型', 'message_type', 1.00, NULL, NULL);
INSERT INTO `t_dict` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `title`, `type`, `sort_order`, `create_id`, `update_id`) VALUES (75388696739713024, 'admin', '2018-11-15 15:00:07', 0, 'admin', '2018-11-27 01:39:22', '', '按钮权限类型', 'permission_type', 2.00, NULL, NULL);
INSERT INTO `t_dict` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `title`, `type`, `sort_order`, `create_id`, `update_id`) VALUES (75392985935646720, 'admin', '2018-11-15 15:17:10', 0, 'admin', '2018-11-27 01:39:29', '', '腾讯云对象存储区域', 'tencent_bucket_region', 3.00, NULL, NULL);



DROP TABLE IF EXISTS `t_dict_data`;
CREATE TABLE `t_dict_data` (
	                           `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                           `create_by` varchar(255) DEFAULT NULL,
	                           `create_time` datetime DEFAULT NULL,
	                           `del_flag` tinyint(1) DEFAULT NULL,
	                           `update_by` varchar(255) DEFAULT NULL,
	                           `update_time` datetime DEFAULT NULL,
	                           `description` varchar(255) DEFAULT NULL,
	                           `dict_id` bigint unsigned DEFAULT NULL,
	                           `sort_order` decimal(10,2) DEFAULT NULL,
	                           `status` tinyint(1) DEFAULT NULL,
	                           `title` varchar(255) DEFAULT NULL,
	                           `value` varchar(255) DEFAULT NULL,
	                           `create_id` varchar(64) DEFAULT NULL,
	                           `update_id` varchar(64) DEFAULT NULL,
	                           `type` varchar(255) DEFAULT NULL,
	                           `type_code` varchar(255) DEFAULT NULL,
	                           PRIMARY KEY (`id`),
	                           KEY `sort_order` (`sort_order`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125170157323554817 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75158227712479232, 'admin', '2018-11-14 23:44:19', 0, 'admin', '2019-04-01 23:53:13', '', 75135930788220928, 0.00, 0, '男', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75159254272577536, 'admin', '2018-11-14 23:48:24', 0, 'admin', '2019-04-01 23:53:17', '', 75135930788220928, 1.00, 0, '女', '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75159898425397248, 'admin', '2018-11-14 23:50:57', 0, 'admin', '2019-04-01 23:53:22', '', 75135930788220928, 2.00, -1, '保密', '9', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75385648017575936, 'admin', '2018-11-15 14:48:00', 0, 'admin', '2019-04-02 00:10:36', '', 75383353938808832, 0.00, 0, '系统公告', '系统公告', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75385706913992704, 'admin', '2018-11-15 14:48:14', 0, 'admin', '2019-04-02 00:10:32', '', 75383353938808832, 1.00, 0, '提醒', '提醒', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75385774274514944, 'admin', '2018-11-15 14:48:30', 0, 'admin', '2019-04-02 00:10:28', '', 75383353938808832, 2.00, 0, '私信', '私信', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75390787835138048, 'admin', '2018-11-15 15:08:26', 0, 'admin', '2018-11-15 15:08:26', '', 75388696739713024, 0.00, 0, '查看操作(view)', 'view', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75390886501945344, 'admin', '2018-11-15 15:08:49', 0, 'admin', '2018-11-15 15:08:57', '', 75388696739713024, 1.00, 0, '添加操作(add)', 'add', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75390993939042304, 'admin', '2018-11-15 15:09:15', 0, 'admin', '2018-11-15 15:09:15', '', 75388696739713024, 2.00, 0, '编辑操作(edit)', 'edit', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391067532300288, 'admin', '2018-11-15 15:09:32', 0, 'admin', '2018-11-15 15:09:32', '', 75388696739713024, 3.00, 0, '删除操作(delete)', 'delete', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391126902673408, 'admin', '2018-11-15 15:09:46', 0, 'admin', '2018-11-15 15:09:46', '', 75388696739713024, 4.00, 0, '清空操作(clear)', 'clear', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391192883269632, 'admin', '2018-11-15 15:10:02', 0, 'admin', '2018-11-15 15:10:02', '', 75388696739713024, 5.00, 0, '启用操作(enable)', 'enable', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391251024711680, 'admin', '2018-11-15 15:10:16', 0, 'admin', '2018-11-15 15:10:16', '', 75388696739713024, 6.00, 0, '禁用操作(disable)', 'disable', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391297124306944, 'admin', '2018-11-15 15:10:27', 0, 'admin', '2018-11-15 15:10:27', '', 75388696739713024, 7.00, 0, '搜索操作(search)', 'search', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391343379091456, 'admin', '2018-11-15 15:10:38', 0, 'admin', '2018-11-15 15:10:38', '', 75388696739713024, 8.00, 0, '上传文件(upload)', 'upload', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391407526776832, 'admin', '2018-11-15 15:10:53', 0, 'admin', '2018-11-15 15:10:53', '', 75388696739713024, 9.00, 0, '导出操作(output)', 'output', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391475042488320, 'admin', '2018-11-15 15:11:09', 0, 'admin', '2018-11-15 15:11:09', '', 75388696739713024, 10.00, 0, '导入操作(input)', 'input', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391522182270976, 'admin', '2018-11-15 15:11:21', 0, 'admin', '2018-11-15 15:11:21', '', 75388696739713024, 11.00, 0, '分配权限(editPerm)', 'editPerm', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391576364290048, 'admin', '2018-11-15 15:11:34', 0, 'admin', '2018-11-15 15:11:34', '', 75388696739713024, 12.00, 0, '设为默认(setDefault)', 'setDefault', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75391798033256448, 'admin', '2018-11-15 15:12:26', 0, 'admin', '2018-11-15 15:12:26', '', 75388696739713024, 13.00, 0, '其他操作(other)', 'other', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75393605291741184, 'admin', '2018-11-15 15:19:37', 0, 'admin', '2018-11-15 15:19:37', '', 75392985935646720, 0.00, 0, '北京一区（华北）', 'ap-beijing-1', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75393681254780928, 'admin', '2018-11-15 15:19:55', 0, 'admin', '2018-11-15 15:19:55', '', 75392985935646720, 1.00, 0, '北京', 'ap-beijing', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75393767619694592, 'admin', '2018-11-15 15:20:16', 0, 'admin', '2018-11-15 15:20:16', '', 75392985935646720, 2.00, 0, '上海（华东）', 'ap-shanghai', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75393851484803072, 'admin', '2018-11-15 15:20:36', 0, 'admin', '2018-11-15 15:20:36', '', 75392985935646720, 3.00, 0, '广州（华南）', 'ap-guangzhou', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75393961887272960, 'admin', '2018-11-15 15:21:02', 0, 'admin', '2018-11-15 15:21:02', '', 75392985935646720, 4.00, 0, '成都（西南）', 'ap-chengdu', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394053969022976, 'admin', '2018-11-15 15:21:24', 0, 'admin', '2018-11-15 15:21:24', '', 75392985935646720, 5.00, 0, '重庆', 'ap-chongqing', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394122474590208, 'admin', '2018-11-15 15:21:41', 0, 'admin', '2018-11-15 15:21:41', '', 75392985935646720, 6.00, 0, '新加坡', 'ap-singapore', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394186202845184, 'admin', '2018-11-15 15:21:56', 0, 'admin', '2018-11-15 15:21:56', '', 75392985935646720, 7.00, 0, '香港', 'ap-hongkong', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394254255427584, 'admin', '2018-11-15 15:22:12', 0, 'admin', '2018-11-15 15:22:12', '', 75392985935646720, 8.00, 0, '多伦多', 'na-toronto', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394309125312512, 'admin', '2018-11-15 15:22:25', 0, 'admin', '2018-11-15 15:22:25', '', 75392985935646720, 9.00, 0, '法兰克福', 'eu-frankfurt', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394367044456448, 'admin', '2018-11-15 15:22:39', 0, 'admin', '2018-11-15 15:22:39', '', 75392985935646720, 10.00, 0, '孟买', 'ap-mumbai', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394448523005952, 'admin', '2018-11-15 15:22:58', 0, 'admin', '2018-11-15 15:22:58', '', 75392985935646720, 11.00, 0, '首尔', 'ap-seoul', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394604765024256, 'admin', '2018-11-15 15:23:36', 0, 'admin', '2018-11-15 15:23:36', '', 75392985935646720, 12.00, 0, '硅谷', 'na-siliconvalley', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394659299364864, 'admin', '2018-11-15 15:23:49', 0, 'admin', '2018-11-15 15:23:49', '', 75392985935646720, 13.00, 0, '弗吉尼亚', 'na-ashburn', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394705700950016, 'admin', '2018-11-15 15:24:00', 0, 'admin', '2018-11-15 15:24:00', '', 75392985935646720, 14.00, 0, '曼谷', 'ap-bangkok', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_data` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `dict_id`, `sort_order`, `status`, `title`, `value`, `create_id`, `update_id`, `type`, `type_code`) VALUES (75394759287377920, 'admin', '2018-11-15 15:24:12', 0, 'admin', '2018-11-15 15:24:12', '', 75392985935646720, 15.00, 0, '莫斯科', 'eu-moscow', NULL, NULL, NULL, NULL);



DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
	                      `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                      `create_by` varchar(255) DEFAULT NULL,
	                      `create_time` datetime DEFAULT NULL,
	                      `del_flag` tinyint(1) DEFAULT NULL,
	                      `update_by` varchar(255) DEFAULT NULL,
	                      `update_time` datetime DEFAULT NULL,
	                      `name` varchar(255) DEFAULT NULL,
	                      `size` bigint unsigned DEFAULT NULL,
	                      `type` varchar(255) DEFAULT NULL,
	                      `url` varchar(255) DEFAULT NULL,
	                      `f_key` varchar(255) DEFAULT NULL,
	                      `location` tinyint(1) DEFAULT NULL,
	                      `create_id` varchar(64) DEFAULT NULL,
	                      `update_id` varchar(64) DEFAULT NULL,
	                      `category_id` varchar(255) DEFAULT NULL,
	                      PRIMARY KEY (`id`),
	                      KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_file_category`;
CREATE TABLE `t_file_category` (
	                               `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                               `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `create_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `create_time` datetime DEFAULT NULL,
	                               `del_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `update_by` datetime DEFAULT NULL,
	                               `update_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `update_time` datetime DEFAULT NULL,
	                               `is_parent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `parent_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `sort_order` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
	                       `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                       `create_by` varchar(255) DEFAULT NULL,
	                       `create_time` datetime DEFAULT NULL,
	                       `del_flag` tinyint(1) DEFAULT NULL,
	                       `update_by` varchar(255) DEFAULT NULL,
	                       `update_time` datetime DEFAULT NULL,
	                       `description` varchar(255) DEFAULT NULL,
	                       `end_date` datetime DEFAULT NULL,
	                       `start_date` datetime DEFAULT NULL,
	                       `title` varchar(255) DEFAULT NULL,
	                       `duration` int unsigned DEFAULT NULL,
	                       `type` varchar(255) DEFAULT NULL,
	                       `create_id` varchar(64) DEFAULT NULL,
	                       `update_id` varchar(64) DEFAULT NULL,
	                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
	                     `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                     `create_by` varchar(255) DEFAULT NULL,
	                     `create_time` datetime DEFAULT NULL,
	                     `del_flag` tinyint(1) DEFAULT NULL,
	                     `update_by` varchar(255) DEFAULT NULL,
	                     `update_time` datetime DEFAULT NULL,
	                     `cost_time` int unsigned DEFAULT NULL,
	                     `ip` varchar(255) DEFAULT NULL,
	                     `ip_info` varchar(255) DEFAULT NULL,
	                     `name` varchar(255) DEFAULT NULL,
	                     `request_param` longtext,
	                     `request_type` varchar(255) DEFAULT NULL,
	                     `request_url` varchar(255) DEFAULT NULL,
	                     `username` varchar(255) DEFAULT NULL,
	                     `log_type` tinyint(1) DEFAULT NULL,
	                     `device` varchar(255) DEFAULT NULL,
	                     `create_id` varchar(64) DEFAULT NULL,
	                     `update_id` varchar(64) DEFAULT NULL,
	                     PRIMARY KEY (`id`),
	                     KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
	                         `id` bigint unsigned NOT NULL AUTO_INCREMENT,
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
	                         `create_id` varchar(64) DEFAULT NULL,
	                         `update_id` varchar(64) DEFAULT NULL,
	                         PRIMARY KEY (`id`),
	                         KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1352304101387538433 DEFAULT CHARSET=utf8mb3;
DROP TABLE IF EXISTS `t_message_send`;
CREATE TABLE `t_message_send` (
	                              `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                              `create_by` varchar(255) DEFAULT NULL,
	                              `create_time` datetime DEFAULT NULL,
	                              `del_flag` tinyint(1) DEFAULT NULL,
	                              `update_by` varchar(255) DEFAULT NULL,
	                              `update_time` datetime DEFAULT NULL,
	                              `message_id` bigint unsigned DEFAULT NULL,
	                              `status` tinyint(1) DEFAULT NULL,
	                              `user_id` bigint unsigned DEFAULT NULL,
	                              `params` varchar(255) DEFAULT NULL,
	                              `create_id` varchar(64) DEFAULT NULL,
	                              `update_id` varchar(64) DEFAULT NULL,
	                              PRIMARY KEY (`id`),
	                              KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
	                            `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                            `create_by` varchar(255) DEFAULT NULL,
	                            `create_time` datetime DEFAULT NULL,
	                            `del_flag` tinyint(1) DEFAULT NULL,
	                            `update_by` varchar(255) DEFAULT NULL,
	                            `update_time` datetime DEFAULT NULL,
	                            `description` varchar(255) DEFAULT NULL,
	                            `name` varchar(255) DEFAULT NULL,
	                            `parent_id` bigint unsigned NOT NULL,
	                            `type` tinyint(1) DEFAULT NULL,
	                            `sort_order` decimal(10,2) DEFAULT NULL,
	                            `component` varchar(255) DEFAULT NULL,
	                            `path` varchar(255) DEFAULT NULL,
	                            `title` varchar(255) DEFAULT NULL,
	                            `icon` varchar(255) DEFAULT NULL,
	                            `level` int unsigned DEFAULT NULL,
	                            `button_type` varchar(255) DEFAULT NULL,
	                            `status` tinyint(1) DEFAULT NULL,
	                            `url` varchar(255) DEFAULT NULL,
	                            `show_always` bit(1) DEFAULT NULL,
	                            `is_menu` bit(1) DEFAULT NULL,
	                            `is_parent` bit(1) DEFAULT NULL,
	                            `i18n` varchar(255) DEFAULT NULL,
	                            `localize` bit(1) DEFAULT NULL,
	                            `update_id` varchar(64) DEFAULT NULL,
	                            `create_id` varchar(64) DEFAULT NULL,
	                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1459862052989440001 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (5129710648430592, '', '2018-06-04 19:02:29', 0, 'admin', '2021-01-11 01:00:01', '', 'sys', 125909152017944576, 0, 1.00, 'Main', '/sys', '系统管理', 'ios-settings', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (5129710648430593, '', '2018-06-04 19:02:32', 0, 'admin', '2022-02-21 01:19:08', '', 'user-manage', 5129710648430592, 0, 1.10, 'sys/user-manage/userManage', 'user-manage', '用户管理', 'md-person', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (5129710648430594, '', '2018-06-04 19:02:35', 0, '', '2018-10-13 13:51:36', '', 'role-manage', 5129710648430592, 0, 1.60, 'sys/role-manage/roleManage', 'role-manage', '角色权限管理', 'md-contacts', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (5129710648430595, '', '2018-06-04 19:02:37', 0, '', '2018-09-23 23:32:02', '', 'menu-manage', 5129710648430592, 0, 1.70, 'sys/menu-manage/menuManage', 'menu-manage', '菜单权限管理', 'md-menu', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (15701400130424832, NULL, NULL, 0, NULL, '2022-02-28 09:44:36', '', '', 5129710648430593, 1, 1.11, '', '/legion/user/admin/add*', '添加用户', '', 3, 'add', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (15701915807518720, NULL, NULL, 0, NULL, '2022-02-28 09:44:53', '', '', 5129710648430593, 1, 1.13, '', '/legion/user/admin/disable/**', '禁用用户', '', 3, 'disable', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (15708892205944832, NULL, NULL, 0, NULL, '2022-02-28 09:44:56', '', '', 5129710648430593, 1, 1.14, '', '/legion/user/admin/enable/**', '启用用户', '', 3, 'enable', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16392452747300864, '', '2018-06-05 19:50:06', 0, 'admin', '2021-12-29 00:29:12', '', 'access', 125909152017944576, 0, 7.00, 'Main', '/access', '权限测试页', 'md-lock', 1, '', 0, '', b'0', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16392767785668608, '', '2018-06-05 19:51:21', 0, 'admin', '2021-12-28 00:46:21', '', 'access_index', 16392452747300864, 0, 5.10, 'access/access', 'index', '权限按钮测试页', 'md-lock', 2, '', 0, '', b'1', NULL, b'1', 'permTestPage', b'1', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16438800255291392, '', '2018-06-05 22:54:18', 0, 'admin', '2022-02-21 01:17:23', '', '', 16392767785668608, 1, 5.11, '', '', '添加按钮测试', '', 3, 'add', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16438962738434048, '', '2018-06-05 22:54:55', 0, 'admin', '2022-02-21 01:18:51', '', '', 16392767785668608, 1, 5.12, '', '', '编辑按钮测试', '', 3, 'edit', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16439068543946752, '', '2018-06-05 22:55:20', 0, 'admin', '2022-02-21 01:18:56', '', '', 16392767785668608, 1, 5.13, '', '', '删除按钮测试', '', 3, 'delete', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16678126574637056, NULL, NULL, 0, NULL, '2022-02-28 09:44:50', '', '', 5129710648430593, 1, 1.12, '', '/legion/user/admin/edit*', '编辑用户', '', 3, 'edit', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16678447719911424, NULL, NULL, 0, NULL, '2022-02-28 09:45:01', '', '', 5129710648430593, 1, 1.15, '', '/legion/user/delByIds/**', '删除用户', '', 3, 'delete', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16687383932047360, '', '2018-06-06 15:22:03', 0, '', '2018-09-19 22:07:34', '', '', 5129710648430594, 1, 1.21, '', '/legion/role/save*', '添加角色', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16689632049631232, '', '2018-06-06 15:30:59', 0, '', '2018-09-19 22:07:37', '', '', 5129710648430594, 1, 1.22, '', '/legion/role/edit*', '编辑角色', '', 3, 'edit', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16689745006432256, '', '2018-06-06 15:31:26', 0, '', '2018-08-10 21:41:23', '', '', 5129710648430594, 1, 1.23, '', '/legion/role/delAllByIds/**', '删除角色', '', 3, 'delete', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16689883993083904, NULL, '2018-06-06 15:31:59', 0, NULL, '2018-06-06 15:31:59', NULL, NULL, 5129710648430594, 1, 1.24, NULL, '/legion/role/editRolePerm**', '分配权限', NULL, 3, 'editPerm', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16690313745666048, '', '2018-06-06 15:33:41', 0, '', '2018-09-19 22:07:46', '', '', 5129710648430594, 1, 1.25, '', '/legion/role/setDefault*', '设为默认角色', '', 3, 'setDefault', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16694861252005888, '', '2018-06-06 15:51:46', 0, '', '2018-09-19 22:07:52', '', '', 5129710648430595, 1, 1.31, '', '/legion/permission/add*', '添加菜单', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16695107491205120, '', '2018-06-06 15:52:44', 0, '', '2018-09-19 22:07:57', '', '', 5129710648430595, 1, 1.32, '', '/legion/permission/edit*', '编辑菜单', '', 3, 'edit', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (16695243126607872, '', '2018-06-06 15:53:17', 0, '', '2018-08-10 21:41:33', '', '', 5129710648430595, 1, 1.33, '', '/legion/permission/delByIds/**', '删除菜单', '', 3, 'delete', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (25014528525733888, '', '2018-06-29 14:51:09', 0, '', '2018-10-08 11:13:27', '', '', 5129710648430593, 1, 1.16, '', '无', '上传图片', '', 3, 'upload', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (39915540965232640, NULL, '2018-08-09 17:42:28', 0, NULL, '2018-08-09 17:42:28', NULL, 'monitor', 125909152017944576, 0, 2.00, 'Main', '/monitor', '系统监控', 'ios-analytics', 1, NULL, 0, NULL, b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (39916171171991552, '', '2018-08-09 17:44:57', 0, 'admin', '2019-01-20 00:37:29', '', 'druid', 39915540965232640, 0, 2.40, 'sys/monitor/monitor', 'druid', 'SQL监控', 'md-analytics', 2, '', 0, 'http://127.0.0.1:8888/druid/login.html', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (39918482854252544, '', '2018-08-09 17:54:08', 0, 'admin', '2020-11-19 16:23:56', '', 'swagger', 39915540965232640, 0, 2.50, 'sys/monitor/monitor', 'swagger', '接口文档', 'md-document', 2, '', 0, 'http://127.0.0.1:8888/doc.html', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (40238597734928384, '', '2018-08-10 15:06:10', 0, 'admin', '2020-12-10 02:21:33', '', 'department-manage', 5129710648430592, 0, 1.20, 'sys/department-manage/departmentManage', 'department-manage', '部门管理', 'md-git-branch', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41363147411427328, '', '2018-08-13 17:34:43', 0, 'admin', '2020-03-25 20:31:16', '', 'log-manage', 39915540965232640, 0, 2.20, 'sys/log-manage/logManage', 'log-manage', '日志管理', 'md-list-box', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41363537456533504, '', '2018-08-13 17:36:16', 0, '', '2018-08-13 17:56:11', '', '', 41363147411427328, 1, 2.11, '', '/legion/log/delByIds/**', '删除日志', '', 3, 'delete', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41364927394353152, '', '2018-08-13 17:41:48', 0, 'admin', '2020-12-11 16:32:40', '', '', 41363147411427328, 1, 2.12, '', '/legion/log/delAll*', '清空日志', '', 3, 'clear', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41370251991977984, NULL, '2018-08-13 18:02:57', 0, NULL, '2018-08-13 18:02:57', NULL, 'quartz-job', 39915540965232640, 0, 2.10, 'sys/quartz-manage/quartzManage', 'quartz-job', '定时任务', 'md-time', 2, '', 0, NULL, b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41371711400054784, '', '2018-08-13 18:08:45', 0, '', '2019-07-07 21:24:26', '', 'actuator', 39915540965232640, 0, 2.30, 'sys/actuator/actuator', 'actuator', 'Actuator监控', 'logo-angular', 2, '', 0, '', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41373430515240960, '', '2018-08-13 18:15:35', 0, 'admin', '2019-11-22 11:47:07', '', 'vue-template', 125909152017944576, 0, 3.10, 'Main', '/vue-template', '后台Vue模版', 'ios-albums', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41375330996326400, NULL, NULL, 0, NULL, '2022-02-27 01:42:18', '', 'simple-table', 41373430515240960, 0, 3.10, 'legion-vue-template/simple-table/simpleTable', 'simple-table', '简单表格+动态列', 'ios-grid-outline', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41376192166629376, NULL, NULL, 0, NULL, '2022-02-27 01:42:43', '', 'search-table', 41373430515240960, 0, 3.40, 'legion-vue-template/search-table/searchTable', 'search-table', '搜索表格', 'md-grid', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41377034236071936, NULL, NULL, 0, NULL, '2022-02-27 01:42:49', '', 'complex-table', 41373430515240960, 0, 3.50, 'legion-vue-template/complex-table/complexTable', 'complex-table', '复杂表格', 'ios-grid', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41378916912336896, NULL, NULL, 0, NULL, '2022-02-27 01:42:25', '', 'tree', 41373430515240960, 0, 3.22, 'legion-vue-template/tree/tree', 'tree', '树形结构', 'ios-git-network', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (41469219249852416, NULL, '2018-08-14 00:36:13', 0, NULL, '2018-08-14 00:36:13', NULL, '', 41371711400054784, 1, 2.31, '', '无', '查看数据', '', 3, 'view', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (42082442672082944, NULL, NULL, 0, NULL, '2022-02-27 01:42:21', '', 'new-window', 41373430515240960, 0, 3.21, 'legion-vue-template/new-window/newWindow', 'new-window', '新窗口操作', 'ios-browsers', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (43117268627886080, NULL, NULL, 0, NULL, '2022-02-27 01:40:20', '', 'message-manage', 5129710648430592, 0, 1.30, 'sys/message-manage/messageManage', 'message-manage', '消息管理', 'md-mail', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (44986029924421632, NULL, NULL, 0, NULL, '2022-02-27 01:40:31', '', 'social-manage', 5129710648430592, 0, 1.50, 'sys/social-manage/socialManage', 'social-manage', '社交账号绑定', 'md-share', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45069342940860416, '', '2018-08-23 23:01:49', 0, '', '2018-08-24 10:01:09', '', '', 44986029924421632, 1, 1.42, '', '无', '查看社交账号数据', '', 3, 'view', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45235228800716800, '', '2018-08-24 10:01:00', 0, '', '2018-09-19 22:07:23', '', '', 44986029924421632, 1, 1.41, '', '/legion/relate/delByIds*', '删除解绑', '', 3, 'delete', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45235621697949696, NULL, NULL, 0, NULL, '2022-02-28 09:45:13', '', '', 40238597734928384, 1, 1.21, '', '/legion/department/add*', '添加部门', '', 3, 'add', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45235787867885568, '', '2018-08-24 10:03:13', 0, '', '2018-09-19 22:07:02', '', '', 40238597734928384, 1, 1.22, '', '/legion/department/edit*', '编辑部门', '', 3, 'edit', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45235939278065664, NULL, '2018-08-24 10:03:49', 0, NULL, '2018-08-24 10:03:49', NULL, '', 40238597734928384, 1, 1.23, '', '/legion/department/delByIds/*', '删除部门', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45236734832676864, '', '2018-08-24 10:06:59', 0, '', '2018-09-19 22:07:07', '', '', 43117268627886080, 1, 1.31, '', '/legion/message/add*', '添加消息', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45237010692050944, '', '2018-08-24 10:08:04', 0, '', '2018-09-19 22:07:12', '', '', 43117268627886080, 1, 1.32, '', '/legion/message/edit*', '编辑消息', '', 3, 'edit', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45237170029465600, NULL, '2018-08-24 10:08:42', 0, NULL, '2018-08-24 10:08:42', NULL, '', 43117268627886080, 1, 1.33, '', '/legion/message/delByIds/*', '删除消息', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45264987354042368, '', '2018-08-24 11:59:14', 0, '', '2018-09-19 22:08:11', '', '', 41370251991977984, 1, 2.11, '', '/legion/quartzJob/add*', '添加定时任务', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45265487029866496, '', '2018-08-24 12:01:14', 0, '', '2018-09-19 22:08:17', '', '', 41370251991977984, 1, 2.12, '', '/legion/quartzJob/edit*', '编辑定时任务', '', 3, 'edit', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45265762415284224, '', '2018-08-24 12:02:19', 0, '', '2018-09-19 22:08:24', '', '', 41370251991977984, 1, 2.13, '', '/legion/quartzJob/pause*', '暂停定时任务', '', 3, 'disable', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45265886315024384, '', '2018-08-24 12:02:49', 0, '', '2018-09-19 22:09:13', '', '', 41370251991977984, 1, 2.14, '', '/legion/quartzJob/resume*', '恢复定时任务', '', 3, 'enable', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (45266070000373760, NULL, '2018-08-24 12:03:33', 0, NULL, '2018-08-24 12:03:33', NULL, '', 41370251991977984, 1, 2.15, '', '/legion/quartzJob/delByIds/*', '删除定时任务', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (56309618086776832, NULL, NULL, 0, NULL, '2022-02-27 01:40:25', '', 'oss-manage', 5129710648430592, 0, 1.40, 'sys/oss-manage/ossManage', 'oss-manage', '文件对象存储', 'ios-folder', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (56898976661639168, NULL, NULL, 0, NULL, '2022-02-28 09:45:06', '', '', 5129710648430593, 1, 1.17, '', '/legion/user/importData*', '导入用户', '', 3, 'input', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (56911328312299520, NULL, NULL, 0, NULL, '2022-02-27 01:42:55', '', 'excel', 41373430515240960, 0, 3.60, 'legion-vue-template/excel/excel', 'excel', 'Excel导入导出', 'md-exit', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (57009544286441472, NULL, '2018-09-25 21:47:55', 0, NULL, '2018-09-25 21:47:55', NULL, '', 43117268627886080, 1, 1.40, '', '/legion/messageSend/save*', '添加已发送消息', '', 3, 'add', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (57009744761589760, NULL, '2018-09-25 21:48:43', 0, NULL, '2018-09-25 21:48:43', NULL, '', 43117268627886080, 1, 1.50, '', '/legion/messageSend/update*', '编辑已发送消息', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (57009981228060672, NULL, '2018-09-25 21:49:39', 0, NULL, '2018-09-25 21:49:39', NULL, '', 43117268627886080, 1, 1.60, '', '/legion/messageSend/delByIds/*', '删除已发送消息', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (57212882168844288, '', '2018-09-26 11:15:55', 0, '', '2018-10-08 11:10:05', '', '', 56309618086776832, 1, 1.41, '', '无', '上传文件', '', 3, 'upload', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (58480609315524608, NULL, NULL, 0, NULL, '2022-02-27 01:40:41', '', 'setting', 5129710648430592, 0, 1.90, 'sys/setting-manage/settingManage', 'setting', '系统配置', 'ios-settings-outline', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (61394706252173312, NULL, '2018-10-08 00:12:59', 0, NULL, '2018-10-08 00:12:59', NULL, '', 58480609315524608, 1, 1.81, '', '/legion/setting/seeSecret/**', '查看私密配置', '', 3, 'view', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (61417744146370560, '', '2018-10-08 01:44:32', 0, '', '2018-10-08 01:50:03', '', '', 58480609315524608, 1, 1.82, '', '/legion/setting/*/set*', '编辑配置', '', 3, 'edit', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (61560041605435392, NULL, '2018-10-08 11:09:58', 0, NULL, '2018-10-08 11:09:58', NULL, '', 56309618086776832, 1, 1.42, '', '/legion/file/rename*', '重命名文件', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (61560275261722624, NULL, '2018-10-08 11:10:54', 0, NULL, '2018-10-08 11:10:54', NULL, '', 56309618086776832, 1, 1.43, '', '/legion/file/copy*', '复制文件', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (61560480518377472, NULL, '2018-10-08 11:11:43', 0, NULL, '2018-10-08 11:11:43', NULL, '', 56309618086776832, 1, 1.44, '', '/legion/file/delete/*', '删除文件', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (63482475359244288, NULL, NULL, 0, NULL, '2022-02-27 01:43:02', '', 'custom-tree', 41373430515240960, 0, 3.80, 'legion-vue-template/custom-tree/customTree', 'custom-tree', '自定义树', 'md-git-network', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (63741744973352960, NULL, NULL, 0, NULL, '2022-02-27 01:42:33', '', 'render', 41373430515240960, 0, 3.30, 'legion-vue-template/render/render', 'render', 'Render函数示例', 'md-aperture', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (64290663792906240, NULL, NULL, 0, NULL, '2022-02-27 01:43:13', '', 'tree&table', 41373430515240960, 0, 3.90, 'legion-vue-template/tree&table/tree&table', 'tree&table', '树+表格', 'md-list', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (66790433014943744, NULL, NULL, 0, NULL, '2022-02-27 01:43:19', '', 'card-list', 41373430515240960, 0, 3.91, 'legion-vue-template/card-list/cardList', 'card-list', '卡片列表', 'md-card', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (75002207560273920, 'admin', '2018-11-14 13:24:21', 0, 'admin', '2018-11-20 20:06:22', '', 'dict', 5129710648430592, 0, 1.80, 'sys/dict-manage/dictManage', 'dict', '数据字典管理', 'md-bookmarks', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76215889006956544, 'admin', '2018-11-17 21:47:05', 0, 'admin', '2018-11-17 21:47:53', '', '', 75002207560273920, 1, 1.81, '', '/legion/dict/add*', '添加字典', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76216071333351424, 'admin', '2018-11-17 21:47:48', 0, 'admin', '2018-11-17 21:47:48', NULL, '', 75002207560273920, 1, 1.82, '', '/legion/dict/edit*', '编辑字典', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76216264070008832, 'admin', '2018-11-17 21:48:34', 0, 'admin', '2018-11-17 21:48:34', NULL, '', 75002207560273920, 1, 1.83, '', '/legion/dict/delByIds/**', '删除字典', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76216459709124608, 'admin', '2018-11-17 21:49:21', 0, 'admin', '2018-11-17 21:49:21', NULL, '', 75002207560273920, 1, 1.84, '', '/legion/dictData/add*', '添加字典数据', '', 3, 'add', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76216594207870976, 'admin', '2018-11-17 21:49:53', 0, 'admin', '2018-11-17 21:49:53', NULL, '', 75002207560273920, 1, 1.85, '', '/legion/dictData/edit*', '编辑字典数据', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76216702639017984, 'admin', '2018-11-17 21:50:18', 0, 'admin', '2018-11-17 21:50:18', NULL, '', 75002207560273920, 1, 1.86, '', '/legion/dictData/delByIds/**', '删除字典数据', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76606430504816640, 'admin', '2018-11-18 23:38:57', 0, 'admin', '2018-11-18 23:39:12', '', 'activiti', 125909152017944576, 0, 1.10, 'Main', '/activiti', '工作流程', 'md-git-compare', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76607201262702592, NULL, NULL, 0, NULL, '2022-02-27 01:41:13', '', 'model-manage', 76606430504816640, 0, 1.00, 'activiti/model-manage/modelManage', 'model-manage', '模型管理', 'md-cube', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (76914082455752704, NULL, NULL, 0, NULL, '2022-02-27 01:41:08', '', 'process-manage', 76606430504816640, 0, 0.90, 'activiti/process-manage/processManage', 'process-manage', '流程管理', 'md-calendar', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (80539147005071360, NULL, NULL, 0, NULL, '2022-02-27 01:41:21', '', 'category-manage', 76606430504816640, 0, 2.00, 'activiti/category-manage/categoryManage', 'category-manage', '流程分类管理', 'md-bookmark', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (81319435670917120, NULL, NULL, 0, NULL, '2022-02-27 01:40:57', '', 'process-ins-manage', 76606430504816640, 0, 0.70, 'activiti/process-ins-manage/processInsManage', 'process-ins-manage', '运行中的流程', 'md-fastforward', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (81558529864896512, NULL, NULL, 0, NULL, '2022-02-27 01:40:37', '', 'apply-manage', 76606430504816640, 0, 0.00, 'activiti/apply-manage/applyManage', 'apply-manage', '我的申请', 'md-clipboard', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (81716172680073216, NULL, NULL, 0, NULL, '2022-02-27 01:40:47', '', 'todo-manage', 76606430504816640, 0, 0.10, 'activiti/todo-manage/todoManage', 'todo-manage', '我的待办', 'ios-pricetag', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (82269650301227008, NULL, NULL, 0, NULL, '2022-02-27 01:40:52', '', 'done-manage', 76606430504816640, 0, 0.20, 'activiti/done-manage/doneManage', 'done-manage', '我的已办', 'ios-pricetag-outline', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84082369492946944, 'admin', '2018-12-09 14:45:40', 0, 'admin', '2018-12-09 14:46:59', '', '', 81558529864896512, 1, 0.00, '', '/legion/leave/add*', '添加请假申请', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84082920431554560, 'admin', '2018-12-09 14:47:51', 0, 'admin', '2020-12-11 01:18:01', '', '', 81558529864896512, 1, 4.00, '', '/legion/actBusiness/delByIds/**', '删除草稿申请', '', 3, 'delete', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84083562503999488, 'admin', '2018-12-09 14:50:25', 0, 'admin', '2018-12-09 14:50:25', NULL, '', 81716172680073216, 1, 0.00, '', '/legion/actTask/pass**', '审批通过', '', 3, 'enable', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84083641302388736, 'admin', '2018-12-09 14:50:43', 0, 'admin', '2018-12-09 14:50:43', NULL, '', 81716172680073216, 1, 1.00, '', '/legion/actTask/back**', '审批驳回', '', 3, 'disable', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84084404707659776, 'admin', '2018-12-09 14:53:45', 0, 'admin', '2018-12-09 14:53:45', NULL, '', 81716172680073216, 1, 3.00, '', '/legion/actTask/delegate**', '委托代办', '', 3, 'other', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84084724590448640, 'admin', '2018-12-09 14:55:02', 0, 'admin', '2018-12-09 14:55:02', NULL, '', 82269650301227008, 1, 0.00, '', '/legion/actTask/deleteHistoric/**', '删除已办任务', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84084965817454592, 'admin', '2018-12-09 14:55:59', 0, 'admin', '2018-12-09 14:55:59', NULL, '', 81319435670917120, 1, 0.00, '', '/legion/actProcess/delInsByIds/**', '删除运行流程', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84085542324539392, 'admin', '2018-12-09 14:58:17', 0, 'admin', '2018-12-09 14:58:17', NULL, '', 76914082455752704, 1, 0.00, '', '/legion/actProcess/updateInfo/**', '编辑流程信息', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84085810797744128, 'admin', '2018-12-09 14:59:21', 0, 'admin', '2018-12-09 14:59:21', NULL, '', 76914082455752704, 1, 1.00, '', '/legion/actProcess/editNodeUser**', '流程节点设置', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84085980943880192, 'admin', '2018-12-09 15:00:01', 0, 'admin', '2018-12-09 15:00:01', NULL, '', 76914082455752704, 1, 2.00, '', '/legion/actProcess/convertToModel/**', '流程转模型', '', 3, 'other', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84086163001839616, 'admin', '2018-12-09 15:00:45', 0, 'admin', '2018-12-09 15:01:37', '', '', 76914082455752704, 1, 3.00, '', '/legion/actProcess/delByIds/**', '删除流程定义', '', 3, 'delete', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84086362248056832, 'admin', '2018-12-09 15:01:32', 0, 'admin', '2018-12-09 15:01:32', NULL, '', 76914082455752704, 1, 4.00, '', '/legion/actModel/deployByFile**', '文件部署流程', '', 3, 'other', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84086882907983872, 'admin', '2018-12-09 15:03:36', 0, 'admin', '2018-12-09 15:03:36', NULL, '', 76607201262702592, 1, 0.00, '', '/legion/actModel/add**', '添加模型', '', 3, 'add', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84087009940869120, 'admin', '2018-12-09 15:04:06', 0, 'admin', '2018-12-09 15:04:06', NULL, '', 76607201262702592, 1, 1.00, '', '/legion/actModel/delByIds/**', '删除模型', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84087154040377344, 'admin', '2018-12-09 15:04:41', 0, 'admin', '2018-12-09 15:04:41', NULL, '', 76607201262702592, 1, 2.00, '', '/legion/actModel/deploy/**', '部署模型', '', 3, 'other', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84087344352727040, 'admin', '2018-12-09 15:05:26', 0, 'admin', '2018-12-09 15:05:33', '', '', 80539147005071360, 1, 0.00, '', '/legion/actCategory/add**', '添加流程分类', '', 3, 'add', 0, '', b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84087480852156416, 'admin', '2018-12-09 15:05:59', 0, 'admin', '2018-12-09 15:05:59', NULL, '', 80539147005071360, 1, 1.00, '', '/legion/actCategory/edit**', '编辑流程分类', '', 3, 'edit', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (84087593041399808, 'admin', '2018-12-09 15:06:25', 0, 'admin', '2018-12-09 15:06:25', NULL, '', 80539147005071360, 1, 2.00, '', '/legion/actCategory/delByIds/**', '删除流程分类', '', 3, 'delete', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (102235632889237504, 'admin', '2019-01-28 17:00:15', 0, 'admin', '2019-11-22 11:47:14', '', 'vue-generator', 125909152017944576, 0, 3.00, 'Main', '/vue-generator', 'Vue代码生成', 'md-send', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (102237605176807424, NULL, NULL, 0, NULL, '2022-02-27 01:41:46', '', 'table-generator', 102235632889237504, 0, 0.00, 'legion-vue-generator/tableGenerator', 'table', '增删改表格生成', 'md-grid', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (102240052523831296, NULL, NULL, 0, NULL, '2022-02-27 01:41:58', '', 'tree-generator', 102235632889237504, 0, 1.00, 'legion-vue-generator/treeGenerator', 'tree', '树形结构生成', 'md-git-branch', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (103658022701633536, NULL, NULL, 0, NULL, '2022-02-27 01:42:08', '', 'test', 102235632889237504, 0, 3.00, 'legion-vue-generator/test', 'test', '代码生成测试页', 'ios-bug', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (113601631450304512, 'admin', '2019-03-01 01:44:41', 0, 'admin', '2019-03-01 01:44:41', NULL, '', 81716172680073216, 1, 2.00, '', '/legion/actTask/backAll/**', '审批驳回至发起人', '', 3, 'disable', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (113602149589454848, NULL, NULL, 0, NULL, '2022-02-27 01:44:23', '', 'components', 125909152017944576, 0, 5.00, 'Main', '/components', '业务组件', 'md-cube', 1, '', 0, '', b'0', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (113602342657462272, NULL, NULL, 0, NULL, '2022-02-27 01:44:18', '', 'components', 113602149589454848, 0, 0.00, 'legion-components/legion-components', 'components', '业务组件', 'md-cube', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (113603512293658624, 'admin', '2019-03-01 01:52:09', 0, 'admin', '2019-06-26 20:38:47', '', 'library', 125909152017944576, 0, 6.00, 'Main', '/library', '第三方依赖工具/组件', 'ios-link', 1, '', 0, '', b'0', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (113603617897844736, NULL, NULL, 0, NULL, '2022-02-27 01:44:30', '', 'library', 113603512293658624, 0, 0.00, 'legion-library/legion-library', 'library', '第三方依赖工具/组件', 'ios-link', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (117806106536841216, NULL, NULL, 0, NULL, '2022-02-27 01:41:02', '', 'process-finish-manage', 76606430504816640, 0, 0.80, 'activiti/process-finish-manage/processFinishManage', 'process-finish-manage', '结束的流程', 'md-power', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (121426317022334976, 'admin', '2019-03-22 15:57:11', 0, 'admin', '2019-03-22 15:57:11', NULL, 'redis', 39915540965232640, 0, 2.21, 'sys/redis/redis', 'redis', 'Redis缓存管理', 'md-barcode', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (125909152017944576, NULL, NULL, 0, NULL, '2022-02-27 01:46:01', 'undefined', 'legion', 0, -1, 0.00, 'normal', '', '管理系统', 'md-home', 0, '', 0, '', b'1', b'1', b'1', 'legionAdmin', b'1', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (127996320085446656, NULL, NULL, 0, NULL, '2022-02-27 01:39:36', '', 'app', 0, -1, 1.00, 'normal', '', 'App管理TOC', 'md-phone-portrait', 0, '', 0, '', b'1', b'1', b'1', 'appAdmin', b'1', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (128366789653434368, 'admin', '2019-04-10 19:36:09', 0, 'admin', '2022-02-22 01:06:53', '', 'app', 127996320085446656, 0, 1.00, 'Main', '/app', 'App预览', 'md-phone-portrait', 1, '', 0, '', b'0', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (128368112335589376, 'admin', '2019-04-10 19:41:24', 0, 'admin', '2022-02-22 01:07:24', '', 'uniapp', 128366789653434368, 0, 0.00, 'uniapp/uniapp', 'uniapp', 'App图片预览', 'md-phone-portrait', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (129033675403694080, NULL, NULL, 0, NULL, '2022-02-27 01:42:14', '', 'single-window', 41373430515240960, 0, 1.00, 'legion-vue-template/single-window/singleWindow', 'single-window', '动态组件单页操作', 'md-easel', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (149452775095275520, 'admin', '2019-06-08 00:04:19', 0, 'admin', '2020-12-13 17:01:58', '', 'admin', 39915540965232640, 0, 2.29, 'sys/monitor/monitor', '/admin', 'Admin监控', 'md-speedometer', 2, '', 0, 'http://127.0.0.1:8888/legion/admin/wallboard', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (156365156580855808, NULL, NULL, 0, NULL, '2022-02-28 09:45:10', '', '', 5129710648430593, 1, 1.18, '', '/legion/user/resetPass', '重置密码', '', 3, 'other', 0, '', b'1', NULL, NULL, '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (160509731956330496, 'admin', '2019-07-08 12:20:43', 0, 'admin', '2019-07-08 12:22:13', '', 'weapp', 127996320085446656, 0, 0.00, 'Main', '/weapp', '微信小程序', 'ios-appstore', 1, '', 0, '', b'0', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (160509918166650881, 'admin', '2019-07-08 12:21:28', 0, 'admin', '2020-04-27 11:10:54', '', 'weapp', 160509731956330496, 0, 0.00, 'weapp/weapp', 'weapp', '微信小程序', 'md-phone-portrait', 2, '', 0, '', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (175808753877127169, 'admin', '2019-08-19 17:33:34', 0, 'admin', '2020-08-14 14:34:16', '', 'sso', 125909152017944576, 0, 8.00, 'Main', '/sso', '单点登录测试站', 'md-log-in', 1, '', 0, '', b'0', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (175808922710446081, 'admin', '2019-08-19 17:34:15', 0, 'admin', '2021-12-28 00:47:10', '', 'sso', 175808753877127169, 0, 0.00, 'sso/sso', 'sso', '单点登录测试站', 'md-log-in', 2, '', 0, '', b'1', NULL, b'0', 'ssoTestPage', b'1', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (210154306362413056, 'admin', '2019-11-22 12:10:32', 0, 'admin', '2021-01-15 21:53:12', '', 'charts', 125909152017944576, 0, 3.10, 'Main', '/charts', '图表数据大屏', 'md-analytics', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (210155258859491329, NULL, NULL, 0, NULL, '2022-02-27 01:43:25', '', 'dashboard1', 210154306362413056, 0, 0.00, 'legion-charts/dashboard1/dashboard1', 'dashboard1', 'Dashboard1', 'md-speedometer', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (210156371755143168, NULL, NULL, 0, NULL, '2022-02-27 01:43:31', '', 'dashboard2', 210154306362413056, 0, 1.00, 'legion-charts/dashboard2/dashboard2', 'dashboard2', 'Dashboard2', 'ios-speedometer', 2, '', 0, '', b'1', NULL, b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (211251162815401984, 'admin', '2019-11-25 12:49:03', 0, 'admin', '2019-11-25 12:49:12', '', 'open', 125909152017944576, 0, 1.20, 'Main', '/open', '开放平台', 'ios-apps', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (211251678651879424, 'admin', '2019-11-25 12:51:06', 0, 'admin', '2020-12-10 12:16:22', '', 'client', 211251162815401984, 0, 0.00, 'open/client/client', 'client', '接入网站管理', 'md-browsers', 2, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (211652331387752448, 'admin', '2019-11-26 15:23:09', 0, 'admin', '2020-12-10 12:16:35', '', 'doc', 211251162815401984, 0, 1.00, 'sys/monitor/monitor', 'doc', '开放平台文档', 'md-document', 2, '', 0, 'https://www.kancloud.cn/exrick/legion/1399478', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1251152298521006080, 'admin', '2020-04-17 22:15:28', 0, 'admin', '2020-04-27 18:30:59', '', 'member', 127996320085446656, 0, 2.00, 'Main', '/member', '会员管理', 'md-contact', 1, '', 0, '', b'1', NULL, b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1251153074307862528, NULL, NULL, 0, NULL, '2022-02-27 01:44:47', '', 'member', 1251152298521006080, 0, 0.00, 'app-toC/member/member', 'member', '注册会员管理', 'md-contacts', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1251760630449442816, 'admin', '2020-04-19 14:32:46', 0, 'admin', '2020-04-27 18:31:03', '', 'member-log', 127996320085446656, 0, 3.00, 'Main', '/member-log', '日志记录', 'md-list-box', 1, '', 0, '', b'1', NULL, b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1251761480286736384, NULL, NULL, 0, NULL, '2022-02-27 01:44:51', '', 'member-log', 1251760630449442816, 0, 0.00, 'sys/log-manage/logManage', 'member-log', '日志记录', 'md-list-box', 2, '', 0, '', b'1', NULL, b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1254739560500432897, 'admin', '2020-04-27 19:49:58', 0, 'admin', '2020-04-27 19:49:58', NULL, '', 81716172680073216, 1, 0.00, '', '/legion/actTask/passAll/**', '批量通过', '', 3, 'other', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1254739666461134848, 'admin', '2020-04-27 19:50:23', 0, 'admin', '2020-04-27 19:50:23', NULL, '', 81716172680073216, 1, 0.00, '', '/legion/actTask/back**', '批量驳回', '', 3, 'disable', 0, NULL, b'1', NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337084109473845248, 'admin', '2020-12-11 01:17:48', 0, 'admin', '2020-12-11 01:17:48', NULL, '', 81558529864896512, 1, 3.00, '', '/legion/actBusiness/apply*', '提交申请', '', 3, 'enable', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337084263631294465, 'admin', '2020-12-11 01:18:25', 0, 'admin', '2020-12-11 01:18:32', '', '', 81558529864896512, 1, 3.00, '', '/legion/actBusiness/cancel*', '撤回申请', '', 3, 'disable', 0, '', b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337084380564295681, 'admin', '2020-12-11 01:18:53', 0, 'admin', '2020-12-11 01:18:53', NULL, '', 117806106536841216, 1, 1.00, '', '/legion/actProcess/delHistoricInsByIds**', '删除结束流程', '', 3, 'delete', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087041741131777, 'admin', '2020-12-11 01:29:27', 0, 'admin', '2020-12-11 01:29:27', NULL, '', 76914082455752704, 1, 0.00, '', '/legion/actProcess/editStartUser**', '编辑流程发起人', '', 3, 'edit', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087257902977024, 'admin', '2020-12-11 01:30:19', 0, 'admin', '2020-12-11 01:30:19', NULL, '', 211251678651879424, 1, 1.00, '', '/legion/client/save**', '添加网站', '', 3, 'add', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087364513796097, 'admin', '2020-12-11 01:30:44', 0, 'admin', '2020-12-11 01:31:28', '', '', 211251678651879424, 1, 3.00, '', '/legion/client/delByIds**', '删除网站', '', 3, 'delete', 0, '', b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087482155634689, 'admin', '2020-12-11 01:31:12', 0, 'admin', '2020-12-11 01:31:32', '', '', 211251678651879424, 1, 2.00, '', '/legion/client/update**', '编辑网站', '', 3, 'edit', 0, '', b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087799127576577, 'admin', '2020-12-11 01:32:28', 0, 'admin', '2020-12-11 01:32:28', NULL, '', 121426317022334976, 1, 1.00, '', '/legion/redis/getAllByPage**', '获取Redis', '', 3, 'view', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087867276627969, 'admin', '2020-12-11 01:32:44', 0, 'admin', '2020-12-11 01:32:44', NULL, '', 121426317022334976, 1, 2.00, '', '/legion/redis/save', '添加Redis', '', 3, 'add', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337087940756639745, 'admin', '2020-12-11 01:33:02', 0, 'admin', '2020-12-11 01:33:02', NULL, '', 121426317022334976, 1, 2.00, '', '/legion/redis/delByKeys**', '删除Redis', '', 3, 'delete', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337088037640867841, 'admin', '2020-12-11 01:33:25', 0, 'admin', '2020-12-11 01:33:25', NULL, '', 121426317022334976, 1, 2.00, '', '/legion/redis/delAll', '清空Redis', '', 3, 'clear', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337088390826430465, 'admin', '2020-12-11 01:34:49', 0, 'admin', '2020-12-11 01:34:49', NULL, '', 1251153074307862528, 1, 1.00, '', '/legion/app/member/admin/add**', '添加会员', '', 3, 'add', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337088472535666689, 'admin', '2020-12-11 01:35:08', 0, 'admin', '2020-12-11 01:35:08', NULL, '', 1251153074307862528, 1, 2.00, '', '/legion/app/member/admin/edit**', '编辑会员', '', 3, 'edit', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1337088563610783745, 'admin', '2020-12-11 01:35:30', 0, 'admin', '2020-12-11 01:35:30', NULL, '', 1251153074307862528, 1, 3.00, '', '/legion/app/member/delByIds**', '删除会员', '', 3, 'delete', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1347151276110123008, 'admin', '2021-01-07 20:01:08', 0, 'admin', '2021-01-07 20:16:04', '', 'auto-chat', 125909152017944576, 0, 1.30, 'Main', '/auto-chat', '智能助手客服', 'logo-twitch', 1, '', 0, '', b'1', b'1', b'1', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1347154151167102977, NULL, NULL, 0, NULL, '2022-02-27 01:41:27', '', 'chat-setting', 1347151276110123008, 0, 1.00, 'auto-chat/setting/setting', 'chat-setting', '页面基本配置', 'md-desktop', 2, '', 0, '', b'1', b'1', b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1347154848814075905, NULL, NULL, 0, NULL, '2022-02-27 01:41:31', '', 'chat-reply', 1347151276110123008, 0, 2.00, 'auto-chat/reply/reply', 'chat-reply', '自动回复配置', 'ios-chatboxes', 2, '', 0, '', b'1', b'1', b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1348946449513189376, NULL, NULL, 0, NULL, '2022-02-27 01:43:38', '', 'dashboard3', 210154306362413056, 0, 3.00, 'legion-charts/dashboard3/dashboard3', 'dashboard3', 'Dashboard3', 'ios-stats', 2, '', 0, '', b'1', b'1', b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1348954596869017600, NULL, NULL, 0, NULL, '2022-02-27 01:43:45', '', 'dashboard4', 210154306362413056, 0, 4.00, 'legion-charts/dashboard4/dashboard4', 'dashboard4', 'Dashboard4', 'md-timer', 2, '', 0, '', b'1', b'1', b'0', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1354853724400521217, 'admin', '2021-01-29 02:07:55', 0, 'admin', '2021-01-29 02:08:58', '', '', 1347154848814075905, 1, 1.00, '', '/legion/autoChat/save**', '添加回复', '', 3, 'add', 0, '', b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1354853977182834688, 'admin', '2021-01-29 02:08:55', 0, 'admin', '2021-01-29 02:08:55', NULL, '', 1347154848814075905, 1, 2.00, '', '/legion/autoChat/update**', '编辑回复', '', 3, 'edit', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1354854134687338496, 'admin', '2021-01-29 02:09:32', 0, 'admin', '2021-01-29 02:09:32', NULL, '', 1347154848814075905, 1, 3.00, '', '/legion/autoChat/delByIds**', '删除回复', '', 3, 'delete', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1459830387986534400, NULL, NULL, 0, NULL, '2022-02-27 01:40:00', '', 'app-toB', 125909152017944576, 0, 0.00, 'Main', '/app-toB', 'App管理TOB', 'md-phone-portrait', 1, '', 0, '', b'1', b'1', b'1', '', b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1459860315381567488, 'admin', '2021-11-14 20:26:37', 0, 'admin', '2021-11-14 20:26:37', NULL, 'preview-app-toB', 1459830387986534400, 0, 1.00, 'app-toB/preview/preview', 'preview', 'App在线预览', 'md-phone-portrait', 2, '', 0, NULL, b'1', b'1', b'0', NULL, b'0', NULL, NULL);
INSERT INTO `t_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `description`, `name`, `parent_id`, `type`, `sort_order`, `component`, `path`, `title`, `icon`, `level`, `button_type`, `status`, `url`, `show_always`, `is_menu`, `is_parent`, `i18n`, `localize`, `update_id`, `create_id`) VALUES (1459862052989440000, NULL, NULL, 0, NULL, '2022-02-27 01:40:10', '', 'file', 1459830387986534400, 0, 2.00, 'app-toB/file/file', 'file', '云文件', 'md-folder', 2, '', 0, '', b'1', b'1', b'0', '', b'0', NULL, NULL);



DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job` (
	                            `id` bigint unsigned NOT NULL AUTO_INCREMENT,
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
	                            `update_id` varchar(64) DEFAULT NULL,
	                            `create_id` varchar(64) DEFAULT NULL,
	                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
	                      `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                      `create_by` varchar(255) DEFAULT NULL,
	                      `create_time` datetime DEFAULT NULL,
	                      `update_by` varchar(255) DEFAULT NULL,
	                      `update_time` datetime DEFAULT NULL,
	                      `name` varchar(255) DEFAULT NULL,
	                      `del_flag` tinyint(1) DEFAULT NULL,
	                      `default_role` bit(1) DEFAULT NULL,
	                      `description` varchar(255) DEFAULT NULL,
	                      `data_type` tinyint(1) DEFAULT NULL,
	                      `update_id` varchar(64) DEFAULT NULL,
	                      `create_id` varchar(64) DEFAULT NULL,
	                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16457350655250433 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_role` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `name`, `del_flag`, `default_role`, `description`, `data_type`, `update_id`, `create_id`) VALUES (496138616573952, '', '2018-04-22 23:03:49', 'admin', '2018-11-15 23:02:59', 'ROLE_ADMIN', 0, NULL, '超级管理员 拥有所有权限', 0, NULL, NULL);
INSERT INTO `t_role` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `name`, `del_flag`, `default_role`, `description`, `data_type`, `update_id`, `create_id`) VALUES (496138616573953, '', '2018-05-02 21:40:03', 'admin', '2018-11-01 22:59:48', 'ROLE_USER', 0, b'1', '普通注册用户 路过看看', 0, NULL, NULL);
INSERT INTO `t_role` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `name`, `del_flag`, `default_role`, `description`, `data_type`, `update_id`, `create_id`) VALUES (16457350655250432, '', '2018-06-06 00:08:00', 'admin', '2018-11-02 20:42:24', 'ROLE_TEST', 0, NULL, '测试权限按钮显示', 1, NULL, NULL);



DROP TABLE IF EXISTS `t_role_department`;
CREATE TABLE `t_role_department` (
	                                 `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                                 `create_by` varchar(255) DEFAULT NULL,
	                                 `create_time` datetime DEFAULT NULL,
	                                 `del_flag` tinyint(1) DEFAULT NULL,
	                                 `update_by` varchar(255) DEFAULT NULL,
	                                 `update_time` datetime DEFAULT NULL,
	                                 `department_id` bigint unsigned DEFAULT NULL,
	                                 `role_id` bigint unsigned DEFAULT NULL,
	                                 `update_id` varchar(64) DEFAULT NULL,
	                                 `create_id` varchar(64) DEFAULT NULL,
	                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70763874277658625 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_role_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `department_id`, `role_id`, `update_id`, `create_id`) VALUES (70763874256687105, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322777781112832, 16457350655250432, NULL, NULL);
INSERT INTO `t_role_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `department_id`, `role_id`, `update_id`, `create_id`) VALUES (70763874265075712, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322811096469504, 16457350655250432, NULL, NULL);
INSERT INTO `t_role_department` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `department_id`, `role_id`, `update_id`, `create_id`) VALUES (70763874277658624, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322852833988608, 16457350655250432, NULL, NULL);



DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
	                                 `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                                 `create_by` varchar(255) DEFAULT NULL,
	                                 `create_time` datetime DEFAULT NULL,
	                                 `del_flag` tinyint(1) DEFAULT NULL,
	                                 `update_by` varchar(255) DEFAULT NULL,
	                                 `update_time` datetime DEFAULT NULL,
	                                 `permission_id` bigint unsigned DEFAULT NULL,
	                                 `role_id` bigint unsigned DEFAULT NULL,
	                                 `update_id` varchar(64) DEFAULT NULL,
	                                 `create_id` varchar(64) DEFAULT NULL,
	                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1459863419535626571 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626408, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 125909152017944576, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626409, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 1459830387986534400, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626410, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 1459860315381567488, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626411, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 1459862052989440000, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626412, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 5129710648430592, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626413, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 5129710648430593, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626414, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 15701400130424832, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626415, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 16678126574637056, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626416, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 15701915807518720, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626417, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 15708892205944832, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626418, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 16678447719911424, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626419, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 25014528525733888, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626420, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 56898976661639168, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626421, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 156365156580855808, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626422, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 40238597734928384, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626423, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 45235621697949696, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626424, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 45235787867885568, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626425, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 45235939278065664, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626426, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 43117268627886080, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626427, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 45236734832676864, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626428, 'admin', '2022-02-27 01:39:00', 0, NULL, '2022-02-27 01:39:00', 45237010692050944, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626429, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45237170029465600, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626430, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 57009544286441472, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626431, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 57009744761589760, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626432, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 57009981228060672, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626433, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 56309618086776832, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626434, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 57212882168844288, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626435, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 61560041605435392, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626436, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 61560275261722624, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626437, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 61560480518377472, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626438, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 44986029924421632, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626439, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45235228800716800, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626440, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45069342940860416, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626441, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 5129710648430594, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626442, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16687383932047360, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626443, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16689632049631232, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626444, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16689745006432256, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626445, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16689883993083904, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626446, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16690313745666048, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626447, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 5129710648430595, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626448, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16694861252005888, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626449, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16695107491205120, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626450, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16695243126607872, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626451, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 75002207560273920, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626452, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76215889006956544, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626453, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76216071333351424, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626454, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76216264070008832, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626455, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76216459709124608, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626456, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76216594207870976, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626457, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76216702639017984, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626458, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 58480609315524608, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626459, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 61394706252173312, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626460, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 61417744146370560, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626461, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76606430504816640, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626462, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 81558529864896512, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626463, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84082369492946944, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626464, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337084109473845248, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626465, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337084263631294465, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626466, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84082920431554560, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626467, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 81716172680073216, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626468, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84083562503999488, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626469, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1254739560500432897, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626470, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1254739666461134848, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626471, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84083641302388736, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626472, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 113601631450304512, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626473, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84084404707659776, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626474, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 82269650301227008, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626475, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84084724590448640, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626476, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 81319435670917120, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626477, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84084965817454592, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626478, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 117806106536841216, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626479, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337084380564295681, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626480, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76914082455752704, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626481, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84085542324539392, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626482, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087041741131777, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626483, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84085810797744128, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626484, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84085980943880192, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626485, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84086163001839616, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626486, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84086362248056832, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626487, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 76607201262702592, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626488, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84086882907983872, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626489, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84087009940869120, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626490, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84087154040377344, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626491, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 80539147005071360, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626492, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84087344352727040, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626493, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84087480852156416, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626494, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 84087593041399808, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626495, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 211251162815401984, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626496, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 211251678651879424, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626497, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087257902977024, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626498, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087482155634689, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626499, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087364513796097, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626500, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 211652331387752448, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626501, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1347151276110123008, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626502, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1347154151167102977, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626503, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1347154848814075905, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626504, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1354853724400521217, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626505, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1354853977182834688, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626506, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1354854134687338496, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626507, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 39915540965232640, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626508, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41370251991977984, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626509, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45264987354042368, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626510, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45265487029866496, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626511, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45265762415284224, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626512, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45265886315024384, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626513, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 45266070000373760, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626514, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41363147411427328, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626515, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41363537456533504, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626516, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41364927394353152, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626517, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 121426317022334976, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626518, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087799127576577, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626519, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087867276627969, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626520, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337087940756639745, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626521, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337088037640867841, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626522, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 149452775095275520, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626523, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41371711400054784, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626524, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41469219249852416, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626525, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 39916171171991552, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626526, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 39918482854252544, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626527, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 102235632889237504, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626528, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 102237605176807424, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626529, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 102240052523831296, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626530, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 103658022701633536, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626531, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41373430515240960, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626532, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 129033675403694080, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626533, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41375330996326400, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626534, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 42082442672082944, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626535, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41378916912336896, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626536, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 63741744973352960, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626537, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41376192166629376, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626538, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 41377034236071936, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626539, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 56911328312299520, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626540, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 63482475359244288, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626541, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 64290663792906240, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626542, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 66790433014943744, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626543, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 210154306362413056, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626544, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 210155258859491329, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626545, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 210156371755143168, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626546, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1348946449513189376, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626547, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1348954596869017600, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626548, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 113602149589454848, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626549, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 113602342657462272, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626550, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 113603512293658624, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626551, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 113603617897844736, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626552, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16392452747300864, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626553, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16392767785668608, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626554, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16438800255291392, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626555, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16438962738434048, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626556, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 16439068543946752, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626557, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 175808753877127169, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626558, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 175808922710446081, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626559, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 127996320085446656, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626560, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 160509731956330496, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626561, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 160509918166650881, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626562, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 128366789653434368, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626563, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 128368112335589376, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626564, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1251152298521006080, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626565, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1251153074307862528, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626566, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337088390826430465, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626567, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337088472535666689, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626568, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1337088563610783745, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626569, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1251760630449442816, 496138616573952, NULL, NULL);
INSERT INTO `t_role_permission` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `permission_id`, `role_id`, `update_id`, `create_id`) VALUES (1459863419535626570, 'admin', '2022-02-27 01:39:01', 0, NULL, '2022-02-27 01:39:01', 1251761480286736384, 496138616573952, NULL, NULL);



DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
	                         `id` varchar(255) NOT NULL,
	                         `create_by` varchar(255) DEFAULT NULL,
	                         `create_time` datetime DEFAULT NULL,
	                         `del_flag` tinyint(1) DEFAULT NULL,
	                         `update_by` varchar(255) DEFAULT NULL,
	                         `update_time` datetime DEFAULT NULL,
	                         `value` longtext,
	                         `update_id` varchar(64) DEFAULT NULL,
	                         `create_id` varchar(64) DEFAULT NULL,
	                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_setting` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `value`, `update_id`, `create_id`) VALUES ('LOCAL_OSS', 'admin', '2022-02-27 01:25:14', 0, NULL, NULL, '{\"serviceName\":\"LOCAL_OSS\",\"endpoint\":\"127.0.0.1:8080/api/legion/file/view\",\"http\":\"http://\",\"filePath\":\"/Users/leiyanfu/Downloads\"}', NULL, '682265633886208');
INSERT INTO `t_setting` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `value`, `update_id`, `create_id`) VALUES ('OSS_USED', 'admin', '2022-02-27 01:25:14', 0, NULL, NULL, 'LOCAL_OSS', NULL, '682265633886208');



DROP TABLE IF EXISTS `t_social`;
CREATE TABLE `t_social` (
	                        `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                        `create_by` varchar(255) DEFAULT NULL,
	                        `create_time` datetime(6) DEFAULT NULL,
	                        `del_flag` tinyint(1) DEFAULT NULL,
	                        `update_by` varchar(255) DEFAULT NULL,
	                        `update_time` datetime(6) DEFAULT NULL,
	                        `avatar` varchar(255) DEFAULT NULL,
	                        `open_id` varchar(255) DEFAULT NULL,
	                        `platform` tinyint unsigned DEFAULT NULL,
	                        `relate_username` varchar(255) DEFAULT NULL,
	                        `username` varchar(255) DEFAULT NULL,
	                        `update_id` varchar(64) DEFAULT NULL,
	                        `create_id` varchar(64) DEFAULT NULL,
	                        PRIMARY KEY (`id`),
	                        UNIQUE KEY `relate_open_id` (`open_id`,`platform`) USING BTREE,
	                        UNIQUE KEY `relate_username` (`relate_username`,`platform`) USING BTREE,
	                        KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `t_stop_word`;
CREATE TABLE `t_stop_word` (
	                           `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                           `create_by` varchar(255) DEFAULT NULL,
	                           `create_time` datetime DEFAULT NULL,
	                           `del_flag` tinyint(1) DEFAULT NULL,
	                           `update_by` varchar(255) DEFAULT NULL,
	                           `update_time` datetime DEFAULT NULL,
	                           `title` varchar(255) DEFAULT NULL,
	                           `create_id` bigint DEFAULT NULL,
	                           `update_id` bigint DEFAULT NULL,
	                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
	                      `id` bigint unsigned NOT NULL AUTO_INCREMENT,
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
	                      `status` int DEFAULT NULL,
	                      `type` int DEFAULT NULL,
	                      `username` varchar(255) DEFAULT NULL,
	                      `del_flag` tinyint(1) DEFAULT NULL,
	                      `department_id` bigint unsigned DEFAULT NULL,
	                      `street` varchar(255) DEFAULT NULL,
	                      `pass_strength` varchar(2) DEFAULT NULL,
	                      `department_title` varchar(255) DEFAULT NULL,
	                      `birth` datetime(6) DEFAULT NULL,
	                      `update_id` varchar(64) DEFAULT NULL,
	                      `create_id` varchar(64) DEFAULT NULL,
	                      PRIMARY KEY (`id`),
	                      UNIQUE KEY `username` (`username`) USING BTREE,
	                      UNIQUE KEY `email` (`email`) USING BTREE,
	                      UNIQUE KEY `mobile` (`mobile`) USING BTREE,
	                      KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16739222421508097 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_user` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `address`, `avatar`, `description`, `email`, `mobile`, `nickname`, `password`, `sex`, `status`, `type`, `username`, `del_flag`, `department_id`, `street`, `pass_strength`, `department_title`, `birth`, `update_id`, `create_id`) VALUES (682265633886208, '', '2018-05-01 16:13:51', 'admin', '2020-04-12 22:03:47', '北京市,市辖区,东城区', 'https://ooo.0o0.ooo/2019/04/28/5cc5a71a6e3b6.png', '我是大帅逼', 'admin@exrick.cn', '18782059031', '管理员', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '男', 0, 1, 'admin', 0, 40322777781112832, '天府1街', '弱', '总部', '2020-04-15 00:00:00.000000', NULL, NULL);


DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
	                           `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	                           `create_by` varchar(255) DEFAULT NULL,
	                           `create_time` datetime DEFAULT NULL,
	                           `del_flag` tinyint(1) DEFAULT NULL,
	                           `update_by` varchar(255) DEFAULT NULL,
	                           `update_time` datetime DEFAULT NULL,
	                           `role_id` bigint unsigned DEFAULT NULL,
	                           `user_id` bigint unsigned DEFAULT NULL,
	                           `update_id` varchar(64) DEFAULT NULL,
	                           `create_id` varchar(64) DEFAULT NULL,
	                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1254772636383318017 DEFAULT CHARSET=utf8mb3;



INSERT INTO `t_user_role` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `role_id`, `user_id`, `update_id`, `create_id`) VALUES (1251516834239352832, 'admin', '2020-04-18 22:24:00', 0, 'admin', '2020-04-18 22:24:00', 496138616573952, 682265633886209, NULL, NULL);
INSERT INTO `t_user_role` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `role_id`, `user_id`, `update_id`, `create_id`) VALUES (1251516834239352833, 'admin', '2020-04-18 22:24:00', 0, 'admin', '2020-04-18 22:24:00', 496138616573953, 682265633886209, NULL, NULL);
INSERT INTO `t_user_role` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `role_id`, `user_id`, `update_id`, `create_id`) VALUES (1251516869526032384, 'admin', '2020-04-18 22:24:08', 0, 'admin', '2020-04-18 22:24:08', 496138616573953, 4363087427670016, NULL, NULL);
INSERT INTO `t_user_role` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `role_id`, `user_id`, `update_id`, `create_id`) VALUES (1254427593457930240, 'admin', '2020-04-26 23:10:19', 0, 'admin', '2020-04-26 23:10:19', 496138616573952, 682265633886208, NULL, NULL);
INSERT INTO `t_user_role` (`id`, `create_by`, `create_time`, `del_flag`, `update_by`, `update_time`, `role_id`, `user_id`, `update_id`, `create_id`) VALUES (1254772636383318016, 'admin', '2020-04-27 22:01:24', 0, 'admin', '2020-04-27 22:01:24', 16457350655250432, 16739222421508096, NULL, NULL);


SET FOREIGN_KEY_CHECKS = 1;
