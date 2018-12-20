/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : ewip

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-12-17 11:17:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alarm_threshold
-- ----------------------------
DROP TABLE IF EXISTS `alarm_threshold`;
CREATE TABLE `alarm_threshold` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '灾害名称',
  `maxtmp` int(11) DEFAULT NULL COMMENT '最高温',
  `mintmp` int(11) DEFAULT NULL COMMENT '最低温',
  `hour_rainnum` int(11) DEFAULT NULL COMMENT '小时降雨',
  `day_rainnum` int(11) DEFAULT NULL COMMENT '24小时降雨',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_threshold
-- ----------------------------
INSERT INTO `alarm_threshold` VALUES ('7a4fefd3de8f11e8bfa38cec4b81c244', '中小河流洪水', '29', '0', '52', '280', '中小河流洪水', '2018-11-07 21:51:49');
INSERT INTO `alarm_threshold` VALUES ('83694d51de8f11e8bfa38cec4b81c244', '山洪', '38', '2', '53', '260', '山洪', '2018-11-07 21:51:43');
INSERT INTO `alarm_threshold` VALUES ('88c6b11ede8f11e8bfa38cec4b81c244', '内涝隐患点', '24', '2', '35', '160', '内涝隐患点', '2018-11-07 21:51:39');
INSERT INTO `alarm_threshold` VALUES ('8e71446ede8f11e8bfa38cec4b81c244', '易涝区', '28', '2', '26', '180', '易涝区', '2018-11-07 21:51:34');
INSERT INTO `alarm_threshold` VALUES ('9269b686de8f11e8bfa38cec4b81c244', '森林火灾重点防范区', '35', '26', '0', '0', '森林火灾重点防范区', '2018-11-07 21:51:22');
INSERT INTO `alarm_threshold` VALUES ('991bc9f3de8f11e8bfa38cec4b81c244', '陡坡', '18', '2', '35', '200', '陡坡', '2018-11-07 21:51:25');
INSERT INTO `alarm_threshold` VALUES ('9ee71774de8f11e8bfa38cec4b81c244', '洼地', '20', '3', '54', '230', '洼地', '2018-11-07 21:51:31');

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` varchar(64) NOT NULL COMMENT '地区id',
  `area_name` varchar(50) NOT NULL COMMENT '地区名称',
  `code` varchar(14) NOT NULL COMMENT '地区编码',
  `p_id` varchar(64) DEFAULT NULL COMMENT '地区父类id',
  `level` smallint(1) DEFAULT NULL COMMENT '地区级别(0：国家；1：省；2：市；3：县；4：乡镇)',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `altitude` double DEFAULT NULL COMMENT '海拔高度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('4c55dcdc970511e8a5ed8cec4b81c244', '庆阳市', '62100000000000', '9aaef2f0970011e8a5ed8cec4b81c244', '2', '106.00385', '35.41907', '234.34', '2018-10-12 10:53:07');
INSERT INTO `area` VALUES ('52db5b81970911e8a5ed8cec4b81c244', '华池县', '62102300000000', '4c55dcdc970511e8a5ed8cec4b81c244', '3', '108.00385', '107.98', '36.47', '2018-11-06 15:44:40');
INSERT INTO `area` VALUES ('9aaef2f0970011e8a5ed8cec4b81c244', '甘肃省', '62000000000000', '', '1', '2314.234', '2314.234', '2314.234', '2018-08-16 15:16:35');

-- ----------------------------
-- Table structure for base_disaster_history
-- ----------------------------
DROP TABLE IF EXISTS `base_disaster_history`;
CREATE TABLE `base_disaster_history` (
  `id` varchar(64) NOT NULL COMMENT '气象灾害id',
  `type` varchar(20) NOT NULL COMMENT '灾害类型',
  `severity` varchar(20) NOT NULL COMMENT '灾害等级',
  `color` varchar(10) NOT NULL COMMENT '等级颜色',
  `level` varchar(10) DEFAULT NULL COMMENT '规模等级',
  `damage` varchar(10) DEFAULT NULL COMMENT '危害程度',
  `code` varchar(50) DEFAULT NULL COMMENT '灾害编号',
  `name` varchar(20) DEFAULT NULL COMMENT '灾害名称',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `scale` varchar(255) DEFAULT NULL COMMENT '影响范围',
  `lon` varchar(500) DEFAULT NULL,
  `lat` varchar(500) DEFAULT NULL,
  `monitor_people` varchar(20) DEFAULT NULL COMMENT '监测人',
  `monitor_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '监测时间',
  `monitor_organ` varchar(50) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `imgname` varchar(50) DEFAULT NULL COMMENT '图片名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_disaster_history
-- ----------------------------
INSERT INTO `base_disaster_history` VALUES ('dc36598bde6911e8bfa38cec4b81c244', '霜冻', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '霜冻橙色', '2018-11-06 20:27:10', '2018-11-06 20:27:10', '望奎县', '126.4693883438', '46.8375807643', '', '2018-11-06 20:27:10', '望奎县气象局', '', 'shuangdongchengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659a7de6911e8bfa38cec4b81c244', '暴雨', 'BLUE', '蓝色', 'IV', '一般', '', '暴雨蓝色', '2018-11-06 20:27:05', '2018-11-06 20:27:05', '望奎县望奎镇', '126.500625', '46.841615', '', '2018-11-06 20:27:05', '望奎县气象局', '', 'baoyulanse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659adde6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:27:04', '2018-11-06 20:27:04', '望奎县后三乡', '126.350556', '46.925833', '', '2018-11-06 20:27:04', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659b8de6911e8bfa38cec4b81c244', '暴雨', 'YELLOW', '黄色', 'Ⅲ', '较重', '', '暴雨黄色', '2018-11-06 20:27:03', '2018-11-06 20:27:03', '望奎县先锋镇', '126.380333', '46.803333', '', '2018-11-06 20:27:03', '望奎县气象局', '', 'baoyuhuangse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659bede6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:35:45', '2018-11-06 20:35:45', '望奎县通江镇\n,望奎县先锋镇', '126.446111,\n126.380333', '46.659167,\n46.803333', '', '2018-11-06 20:35:45', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659c4de6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:27:00', '2018-11-06 20:27:00', '望奎县海丰镇八方村', '126.89078', '46.868616', '', '2018-11-06 20:27:00', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659ccde6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:26:59', '2018-11-06 20:26:59', '望奎县后三乡', '126.350556', '46.925833', '', '2018-11-06 20:26:59', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659d2de6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:35:41', '2018-11-06 20:35:41', '望奎县灯塔镇敏三村,望奎县火箭镇', '126.6929087264,126.795556', '46.8271069530,\n46.905278', '', '2018-11-06 20:35:41', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659d8de6911e8bfa38cec4b81c244', '大风', 'BLUE', '蓝色', 'IV', '一般', '', '大风蓝色', '2018-11-06 20:26:57', '2018-11-06 20:26:57', '望奎县', '126.4693883438', '46.8375807643', '', '2018-11-06 20:26:57', '望奎县气象局', '', 'dafenglanse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659ddde6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:35:28', '2018-11-06 20:35:28', '望奎县灵山乡,\n望奎县先锋镇富饶村\n,望奎县恭六乡,\n望奎县惠七镇,望奎县后三乡', '126.455278,\n126.240679,\n126.862778,\n126.668333,126.350556', '46.5604,\n46.886447,\n47.1000278,\n47.0575,\n46.925833', '', '2018-11-06 20:35:28', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659e6de6911e8bfa38cec4b81c244', '冰雹,大风', 'ORANGE,BLUE', '橙色,蓝色', 'Ⅱ,IV', '严重,一般', '', '冰雹橙色,大风蓝色', '2018-11-06 20:35:15', '2018-11-06 20:35:15', '望奎县灵山乡,望奎县先锋镇富饶村,望奎县恭六乡\n,望奎县惠七镇,望奎县后三乡', '126.455278,\n126.240679,126.862778,\n126.668333,126.350556', '46.5604,\n46.886447,\n47.1000278,\n47.0575,\n46.925833', '', '2018-11-06 20:35:15', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659e9de6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:26:55', '2018-11-06 20:26:55', '望奎县东郊镇', '126.562717', '46.846389', '', '2018-11-06 20:26:55', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659f1de6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', '2018-11-06 20:26:54', '2018-11-06 20:26:54', '望奎县海丰镇', '126.827778', '46.831944', '', '2018-11-06 20:26:54', '望奎县气象局', '', 'bingbaochengse.gif');
INSERT INTO `base_disaster_history` VALUES ('dc3659f7de6911e8bfa38cec4b81c244', '暴雨', 'BLUE', '蓝色', 'IV', '一般', '', '暴雨蓝色', '2018-11-06 20:26:53', '2018-11-06 20:26:53', '望奎县', '126.4693883438', '46.8375807643', '', '2018-11-06 20:26:53', '望奎县气象局', '', 'baoyulanse.gif');

-- ----------------------------
-- Table structure for base_disaster_route
-- ----------------------------
DROP TABLE IF EXISTS `base_disaster_route`;
CREATE TABLE `base_disaster_route` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `type` varchar(20) NOT NULL COMMENT '灾害类型',
  `color` varchar(10) NOT NULL COMMENT '等级颜色',
  `level` varchar(10) DEFAULT NULL COMMENT '危害程度',
  `name` varchar(20) NOT NULL COMMENT '灾害名称',
  `points` varchar(100) DEFAULT NULL COMMENT '灾害点名称',
  `path` varchar(100) DEFAULT NULL COMMENT '影响路线名称',
  `length` varchar(20) DEFAULT NULL COMMENT '路线长度',
  `road_time` double DEFAULT NULL COMMENT '乘车耗时',
  `ranges` varchar(500) DEFAULT NULL COMMENT '经纬度范围',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_disaster_route
-- ----------------------------
INSERT INTO `base_disaster_route` VALUES ('dc77c041de6911e8bfa38cec4b81c244', '暴雨', '蓝色', '一般', '暴雨蓝色', '先锋镇、通江镇、火箭镇', '通肯河流域', '50公里', null, '126.3711807458-127.2052346465，46.6132795477-47.3773634473', null, null, '2018-12-05 09:56:38', '2018-12-05 09:56:39');
INSERT INTO `base_disaster_route` VALUES ('dc77c05ade6911e8bfa38cec4b81c244', '暴雨', '蓝色', '一般', '暴雨蓝色', '恭六乡、东升乡、海丰镇', '克音河、诺敏河流域', '45公里', null, '126.8753633682-126.8595701070，47.1084975189-46.7955097453', null, null, '2018-12-05 09:56:38', '2018-12-05 09:56:39');
INSERT INTO `base_disaster_route` VALUES ('dc77c063de6911e8bfa38cec4b81c244', '暴雨', '蓝色', '一般', '暴雨蓝色', '海丰镇、卫星镇、通江镇', '呼兰河流域', '45公里', null, '126.8595701070-126.3711807458，46.7955097453-46.6132795477', null, null, '2018-12-05 09:56:38', '2018-12-05 09:56:39');
INSERT INTO `base_disaster_route` VALUES ('dc77c06bde6911e8bfa38cec4b81c244', '暴雨', '蓝色', '一般', '暴雨蓝色', '灯塔镇、望奎镇、先锋镇', '头道乌龙沟', '8公里', null, '126.6565648576-126.4321890687，46.8287256332-46.8196796167', null, null, '2018-12-05 09:56:38', '2018-12-05 09:56:39');

-- ----------------------------
-- Table structure for base_disaster_type
-- ----------------------------
DROP TABLE IF EXISTS `base_disaster_type`;
CREATE TABLE `base_disaster_type` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '气象灾害id',
  `type` varchar(20) NOT NULL COMMENT '灾害类型',
  `severity` varchar(20) NOT NULL COMMENT '灾害等级',
  `color` varchar(10) NOT NULL COMMENT '等级颜色',
  `level` varchar(10) NOT NULL COMMENT '规模等级',
  `damage` varchar(10) DEFAULT NULL COMMENT '危害程度',
  `code` varchar(50) DEFAULT NULL COMMENT '灾害编号',
  `name` varchar(20) DEFAULT NULL COMMENT '灾害名称',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_disaster_type
-- ----------------------------
INSERT INTO `base_disaster_type` VALUES ('dcb44043de6911e8bfa38cec4b81c244', '冰雹', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '冰雹橙色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb4405cde6911e8bfa38cec4b81c244', '大风', 'BLUE', '蓝色', 'Ⅳ', '一般', '', '大风蓝色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb44065de6911e8bfa38cec4b81c244', '寒潮', 'BLUE', '蓝色', 'Ⅳ', '一般', '', '寒潮蓝色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb4406bde6911e8bfa38cec4b81c244', '暴雨', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '暴雨橙色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb44073de6911e8bfa38cec4b81c244', '暴雪', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '暴雪橙色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb4407cde6911e8bfa38cec4b81c244', '霜冻', 'YELLOW', '黄色', 'Ⅲ', '较重', '', '霜冻黄色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb4407fde6911e8bfa38cec4b81c244', '大雾', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '大雾橙色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb44084de6911e8bfa38cec4b81c244', '高温', 'YELLOW', '黄色', 'Ⅲ', '较重', '', '高温黄色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');
INSERT INTO `base_disaster_type` VALUES ('dcb4408dde6911e8bfa38cec4b81c244', '干旱', 'ORANGE', '橙色', 'Ⅱ', '严重', '', '干旱橙色', null, null, '2018-12-05 09:56:40', '2018-12-05 09:56:40');

-- ----------------------------
-- Table structure for base_facility_office
-- ----------------------------
DROP TABLE IF EXISTS `base_facility_office`;
CREATE TABLE `base_facility_office` (
  `id` varchar(64) NOT NULL,
  `type` varchar(60) DEFAULT NULL COMMENT '场所类型',
  `name` varchar(60) NOT NULL COMMENT '办公场所名称',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属部门',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `worker` int(10) DEFAULT NULL COMMENT '工作人员人数',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `description` varchar(500) DEFAULT NULL COMMENT '场所描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_facility_office
-- ----------------------------
INSERT INTO `base_facility_office` VALUES ('dcf8b7c5de6911e8bfa38cec4b81c244', '办公室', '望奎县应急办', '应急办', '0', '0', '126.492500947', '46.8391594817', '陈宝良', '18904852288', '黑龙江省望奎县人民政府', '办公场地是政府办公室合用，人员实政府人员兼职。', null, null, '2018-12-05 09:56:42', '2018-12-05 09:56:43');

-- ----------------------------
-- Table structure for base_facility_publish
-- ----------------------------
DROP TABLE IF EXISTS `base_facility_publish`;
CREATE TABLE `base_facility_publish` (
  `id` varchar(64) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '设备名称',
  `code` varchar(20) DEFAULT NULL COMMENT '设备编号',
  `factory` varchar(60) DEFAULT NULL COMMENT '设备厂家',
  `type` varchar(20) DEFAULT NULL COMMENT '设备类型',
  `address` varchar(100) DEFAULT NULL COMMENT '安装地址',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '管理单位',
  `equipmentUse` varchar(100) DEFAULT NULL COMMENT '设备用途',
  `principal` varchar(20) NOT NULL COMMENT '联系人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `status` smallint(1) DEFAULT '0' COMMENT '是否部署：0：未部署；1：已部署',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警信息发布设施表';

-- ----------------------------
-- Records of base_facility_publish
-- ----------------------------
INSERT INTO `base_facility_publish` VALUES ('dd329cb9de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县厢白满族乡', '126.609667', '46.91944', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '0', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329cd9de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县东升乡', '126.881667', '47.00444', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329ce4de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县灯塔乡', '126.795556', '46.905278', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '0', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329cefde6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县后三乡', '126.350556', '46.925833', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '0', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329cf8de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县灵山满族乡', '126.455278', '46.94', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d00de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县火箭镇', '126.501388', '46.7525', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d09de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县先锋镇', '126.380333', '46.803333', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d14de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县惠七满族镇', '126.668333', '47.0527', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d23de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县莲花镇', '126.811111', '46.99333', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '0', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d2ede6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县海丰镇', '126.827778', '46.831944', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '0', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d36de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县通江镇', '126.446111', '46.659167', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d3fde6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县卫星镇', '126.661111', '46.710278', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '0', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d4dde6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县东郊镇', '126.54', '46.846389', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');
INSERT INTO `base_facility_publish` VALUES ('dd329d53de6911e8bfa38cec4b81c244', '显示屏', '', '沈阳恒远通电子有限公司', '户外显示屏', '黑龙江绥化市望奎县恭六乡', '126.862778', '47.100278', '望奎县气象局', '预报预警发布设备', '冯晓伟', '13836478828', '1', '2018-11-05 09:53:25', null, null, '2018-12-05 09:56:44');

-- ----------------------------
-- Table structure for base_facility_shelter
-- ----------------------------
DROP TABLE IF EXISTS `base_facility_shelter`;
CREATE TABLE `base_facility_shelter` (
  `id` varchar(64) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '名称',
  `district` varchar(30) DEFAULT NULL COMMENT '区县',
  `address` varchar(100) DEFAULT NULL COMMENT '位置（地址）',
  `code` varchar(20) DEFAULT NULL COMMENT '地区编号',
  `area` double DEFAULT NULL COMMENT '面积（㎡）',
  `capacity` int(11) DEFAULT NULL COMMENT '容纳人口（人）',
  `unit` varchar(60) DEFAULT NULL COMMENT '主管单位',
  `principal` varchar(20) NOT NULL COMMENT '联系人',
  `tel` varchar(100) DEFAULT NULL COMMENT '电话',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_facility_shelter
-- ----------------------------
INSERT INTO `base_facility_shelter` VALUES ('dd60cdaade6911e8bfa38cec4b81c244', '文化广场', '望奎县', '黑龙江省绥化市望奎县西郊路北', '231221100', '25000', '4000', '城市管理行政执法局', '唐晓飞', '', '18045576002', '126.4700783', '46.83855076', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60cdc6de6911e8bfa38cec4b81c244', '林枫广场', '望奎县', '黑龙江省绥化市望奎县西郊东南路北', '231221100', '20000', '3500', '城市管理行政执法局', '唐晓飞', '', '18045576002', '126.4973009', '46.83760948', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60cdd2de6911e8bfa38cec4b81c244', '体育广场', '望奎县', '黑龙江省绥化市望奎县西郊东北路西', '231221100', '30000', '5000', '城市管理行政执法局', '唐晓飞', '', '18045576002', '126.4971083', '46.84288892', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60cde0de6911e8bfa38cec4b81c244', '东郊中心小学', '望奎县', '黑龙江省绥化市望奎县东郊镇政府路北', '231221201', '12000', '2500', '东郊镇政府', '陈志鸿', '', '13836459445', '126.54', '46.846389', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60cdebde6911e8bfa38cec4b81c244', '恭六乡乾三后村广场', '望奎县', '黑龙江省绥化市望奎县恭六乡乾三后村', '231221210205', '8000', '1500', '恭六乡乾三后村村委会', '高亚斌', '', '15590994056', '126.8753633682', '47.1084975189', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60cdf7de6911e8bfa38cec4b81c244', '莲花镇信五村广场', '望奎县', '黑龙江省绥化市望奎县莲花镇信五村', '231221104203', '10000', '1600', '莲花镇信五村村委会', '齐成武', '', '15246527050', '126.7760992422', '46.9902351205', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60ce05de6911e8bfa38cec4b81c244', '灯塔镇政府', '望奎县', '黑龙江省绥化市望奎县灯塔镇政府', '231221109', '15000', '2000', '灯塔镇政府', '阚克', '', '18804555567', '126.795556', '46.905278', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');
INSERT INTO `base_facility_shelter` VALUES ('dd60ce0bde6911e8bfa38cec4b81c244', '海丰镇政府', '望奎县', '黑龙江省绥化市望奎县海丰镇政府', '231221103', '15000', '2000', '海丰镇政府', '王炳楠', '', '13846776773', '126.827778', '46.831944', null, null, '2018-12-05 09:56:46', '2018-12-05 09:56:46');

-- ----------------------------
-- Table structure for base_facility_supply
-- ----------------------------
DROP TABLE IF EXISTS `base_facility_supply`;
CREATE TABLE `base_facility_supply` (
  `id` varchar(64) NOT NULL,
  `district` varchar(30) NOT NULL COMMENT '区县',
  `code` varchar(20) DEFAULT NULL COMMENT '地区编号',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `tel` varchar(100) DEFAULT NULL COMMENT '单位联系电话',
  `type` varchar(100) DEFAULT NULL COMMENT '救援物资类型',
  `name` varchar(20) DEFAULT NULL COMMENT '物资名称',
  `existing` varchar(255) DEFAULT NULL COMMENT '已有物资',
  `model` varchar(100) DEFAULT NULL COMMENT '已有物资规格型号',
  `amount` varchar(30) DEFAULT NULL COMMENT '数量',
  `materialUse` varchar(255) DEFAULT NULL COMMENT '用途',
  `address` varchar(100) DEFAULT NULL COMMENT '应急储备地址',
  `unit` varchar(60) DEFAULT NULL COMMENT '主管单位',
  `principal` varchar(20) NOT NULL COMMENT '联系人',
  `phone` varchar(20) NOT NULL COMMENT '手机',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_facility_supply
-- ----------------------------
INSERT INTO `base_facility_supply` VALUES ('ddb77b6cde6911e8bfa38cec4b81c244', '望奎县', '231221106205', '126.2810695881', '46.7567654839', '6765105', '防汛抗旱物资', '铁线', '铁线', '', '5吨', '防汛抗旱救灾', '先锋镇散南村哈达屯', '望奎县水务局', '赵宝军', '6765105', null, null, '2018-12-05 09:56:48', '2018-12-05 09:56:48');
INSERT INTO `base_facility_supply` VALUES ('ddb77b88de6911e8bfa38cec4b81c244', '望奎县', '231221106205', '126.2810695881', '46.7567654839', '6765105', '防汛抗旱物资', '编织袋', '编织袋', '', '10万', '防汛抗旱救灾', '先锋镇散南村哈达屯', '望奎县水务局', '赵宝军', '6765105', null, null, '2018-12-05 09:56:48', '2018-12-05 09:56:48');
INSERT INTO `base_facility_supply` VALUES ('ddb77b91de6911e8bfa38cec4b81c244', '望奎县', '231221106205', '126.2810695881', '46.7567654839', '6765105', '防汛抗旱物资', '水泵', '水泵', '', '100台', '防汛抗旱救灾', '先锋镇散南村哈达屯', '望奎县水务局', '赵宝军', '6765105', null, null, '2018-12-05 09:56:48', '2018-12-05 09:56:48');

-- ----------------------------
-- Table structure for base_risk_depression
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_depression`;
CREATE TABLE `base_risk_depression` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '名称',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `province` varchar(60) DEFAULT NULL COMMENT '省名',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(255) DEFAULT NULL COMMENT '区县代码',
  `type` varchar(30) DEFAULT NULL COMMENT '灾害点类型',
  `scale` varchar(30) DEFAULT NULL COMMENT '灾害点规模',
  `stability` varchar(30) DEFAULT NULL COMMENT '稳定性',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '管理单位',
  `monitor_people` varchar(20) NOT NULL COMMENT '联系人',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  `weather_causes` varchar(20) DEFAULT NULL COMMENT '气象致灾因子',
  `24h_prec` double DEFAULT NULL COMMENT '过去24小时雨量',
  `1h_prec` double DEFAULT NULL COMMENT '过去1小时雨量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_depression
-- ----------------------------
INSERT INTO `base_risk_depression` VALUES ('ddf93285de6911e8bfa38cec4b81c244', '发展村', '126.15', '16.45', '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '洪涝', '严重', '不稳定', '火箭镇人民政府', '韩景福', '', '暴雨', '80', '40');

-- ----------------------------
-- Table structure for base_risk_flood
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_flood`;
CREATE TABLE `base_risk_flood` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '中小河流名称',
  `code` varchar(60) DEFAULT NULL COMMENT '代码',
  `lon` double DEFAULT NULL COMMENT '经度',
  `lat` double DEFAULT NULL COMMENT '纬度',
  `province` varchar(60) DEFAULT NULL COMMENT '省名',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(255) DEFAULT NULL COMMENT '区县代码',
  `range_lon` varchar(300) DEFAULT NULL COMMENT '受影响经度范围',
  `range_lat` varchar(300) DEFAULT NULL COMMENT '受影响纬度范围',
  `measures` varchar(500) DEFAULT NULL COMMENT '防御措施',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '管理单位',
  `monitor_people` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  `weather_causes` varchar(20) DEFAULT NULL COMMENT '气象致灾因子',
  `24h_prec` double DEFAULT NULL COMMENT '过去24小时雨量',
  `1h_prec` double DEFAULT NULL COMMENT '过去1小时雨量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_flood
-- ----------------------------
INSERT INTO `base_risk_flood` VALUES ('de63a061de6911e8bfa38cec4b81c244', '呼兰河', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_flood` VALUES ('de63a080de6911e8bfa38cec4b81c244', '通肯河', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_flood` VALUES ('de63a08cde6911e8bfa38cec4b81c244', '克音河', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_flood` VALUES ('de63a09ade6911e8bfa38cec4b81c244', '努敏河', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_flood` VALUES ('de63a0a8de6911e8bfa38cec4b81c244', '头道乌龙沟', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_flood` VALUES ('de63a0b1de6911e8bfa38cec4b81c244', '二道乌龙沟', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_flood` VALUES ('de63a0b9de6911e8bfa38cec4b81c244', '三道乌龙沟', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');

-- ----------------------------
-- Table structure for base_risk_forest
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_forest`;
CREATE TABLE `base_risk_forest` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '火险点名称',
  `code` varchar(60) DEFAULT NULL COMMENT '代码',
  `lon` double NOT NULL COMMENT '经度范围',
  `lat` double NOT NULL COMMENT '纬度范围',
  `province` varchar(60) DEFAULT NULL COMMENT '省名',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(60) DEFAULT NULL COMMENT '区县代码',
  `area` double DEFAULT NULL COMMENT '影响面积',
  `measures` varchar(500) DEFAULT NULL COMMENT '防御措施',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '监测单位',
  `monitor_people` varchar(20) NOT NULL COMMENT '联系人',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  `weather_causes` varchar(20) DEFAULT NULL COMMENT '致灾气象因子',
  `tmax` double DEFAULT NULL COMMENT '最高温',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_forest
-- ----------------------------

-- ----------------------------
-- Table structure for base_risk_geologic
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_geologic`;
CREATE TABLE `base_risk_geologic` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `field_number` varchar(30) DEFAULT NULL COMMENT '野外编号',
  `indoor_number` varchar(30) DEFAULT NULL COMMENT '室内编号',
  `type` varchar(30) DEFAULT NULL COMMENT '灾害点类型',
  `street` varchar(30) DEFAULT NULL COMMENT '镇街道办',
  `rock` varchar(30) DEFAULT NULL COMMENT '岩土成因',
  `scale` varchar(20) DEFAULT NULL COMMENT '灾害点规模',
  `stability` varchar(20) DEFAULT NULL COMMENT '稳定性',
  `economic_loss` varchar(20) DEFAULT NULL COMMENT '直接经济损失',
  `thread_people` int(11) DEFAULT NULL COMMENT '威胁人口',
  `thread_property` int(11) DEFAULT NULL COMMENT '威胁资产',
  `level` varchar(20) DEFAULT NULL COMMENT '险情等级',
  `weather_causes` varchar(20) DEFAULT NULL COMMENT '气象致灾因子',
  `24h_prec` double DEFAULT NULL COMMENT '过去24小时雨量',
  `1h_prec` double DEFAULT NULL COMMENT '过去1小时雨量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_geologic
-- ----------------------------
INSERT INTO `base_risk_geologic` VALUES ('ded7c932de6911e8bfa38cec4b81c244', '海丰镇恭头一村闫家大窝棚屯', '绥化市望奎县', '126.8897242556', '46.8162732895', '', '', '崩塌', '海丰镇', '', '小型', '不稳定', '', '370', '20000000', '小型', '暴雨', '0', '0');
INSERT INTO `base_risk_geologic` VALUES ('ded7c952de6911e8bfa38cec4b81c244', '海丰镇恭头一村向阳屯', '绥化市望奎县', '126.8769897244', '46.7845627497', '', '', '崩塌', '海丰镇', '', '小型', '不稳定', '', '370', '20000000', '小型', '暴雨', '0', '0');
INSERT INTO `base_risk_geologic` VALUES ('ded7c95dde6911e8bfa38cec4b81c244', '海丰镇恭头一村四马沟屯', '绥化市望奎县', '126.8897242556', '46.8162732895', '', '', '崩塌', '海丰镇', '', '小型', '不稳定', '', '560', '32000000', '小型', '暴雨', '0', '0');
INSERT INTO `base_risk_geologic` VALUES ('ded7c968de6911e8bfa38cec4b81c244', '海丰镇恭头二村崔家屯', '绥化市望奎县', '126.8510685084', '46.7679707764', '', '', '崩塌', '海丰镇', '', '小型', '不稳定', '', '620', '35000000', '小型', '暴雨', '0', '0');
INSERT INTO `base_risk_geologic` VALUES ('ded7c979de6911e8bfa38cec4b81c244', '恭六乡乾二村张德屯', '绥化市望奎县', '126.8753633682', '47.1100775189', '', '', '洪水', '恭六乡', '', '小型', '不稳定', '', '319', '15000000', '小型', '暴雨', '0', '0');
INSERT INTO `base_risk_geologic` VALUES ('ded7c985de6911e8bfa38cec4b81c244', '恭六乡乾二村后刘家屯', '绥化市望奎县', '126.932734048', '47.0815171674', '', '', '崩塌', '恭六乡', '', '小型', '不稳定', '', '288', '14000000', '小型', '暴雨', '0', '0');
INSERT INTO `base_risk_geologic` VALUES ('ded7c990de6911e8bfa38cec4b81c244', '东升乡乾一村毛山屯', '绥化市望奎县', '126.9607999584', '47.029604184', '', '', '崩塌', '东升乡', '', '小型', '不稳定', '', '323', '16000000', '小型', '暴雨', '0', '0');

-- ----------------------------
-- Table structure for base_risk_mountain
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_mountain`;
CREATE TABLE `base_risk_mountain` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '山洪沟名称',
  `code` varchar(60) DEFAULT NULL COMMENT '代码',
  `lon` double DEFAULT NULL COMMENT '经度',
  `lat` double DEFAULT NULL COMMENT '纬度',
  `province` varchar(60) DEFAULT NULL COMMENT '省名',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(255) DEFAULT NULL COMMENT '区县代码',
  `range_lon` varchar(300) DEFAULT NULL COMMENT '受影响经度范围',
  `range_lat` varchar(300) DEFAULT NULL COMMENT '受影响纬度范围',
  `level` varchar(30) DEFAULT NULL COMMENT '危害等级',
  `measures` varchar(500) DEFAULT NULL COMMENT '防御措施',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '管理单位',
  `monitor_people` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `weather_causes` varchar(20) DEFAULT NULL COMMENT '气象致灾因子',
  `24h_prec` double DEFAULT NULL COMMENT '过去24小时雨量',
  `1h_prec` double DEFAULT NULL COMMENT '过去1小时雨量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_mountain
-- ----------------------------
INSERT INTO `base_risk_mountain` VALUES ('df1b0d27de6911e8bfa38cec4b81c244', '李坪小流域', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '小型', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_mountain` VALUES ('df1b0d40de6911e8bfa38cec4b81c244', '三合小流域', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '小型', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_mountain` VALUES ('df1b0d46de6911e8bfa38cec4b81c244', '苏家小流域', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '小型', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_mountain` VALUES ('df1b0d51de6911e8bfa38cec4b81c244', '二前小流域', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '小型', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');
INSERT INTO `base_risk_mountain` VALUES ('df1b0d5ade6911e8bfa38cec4b81c244', '文家小流域', '', null, null, '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '小型', '加固堤防', '望奎县水务局', '王兆权', '', '暴雨', '0', '0');

-- ----------------------------
-- Table structure for base_risk_slope
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_slope`;
CREATE TABLE `base_risk_slope` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '名称',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `province` varchar(60) DEFAULT NULL COMMENT '省名',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(255) DEFAULT NULL COMMENT '区县代码',
  `type` varchar(30) DEFAULT NULL COMMENT '灾害点类型',
  `scale` varchar(30) DEFAULT NULL COMMENT '灾害点规模',
  `stability` varchar(30) DEFAULT NULL COMMENT '稳定性',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '管理单位',
  `monitor_people` varchar(20) NOT NULL COMMENT '联系人',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  `weather_causes` varchar(20) DEFAULT NULL COMMENT '气象致灾因子',
  `24h_prec` double DEFAULT NULL COMMENT '过去24小时雨量',
  `1h_prec` double DEFAULT NULL COMMENT '过去1小时雨量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_slope
-- ----------------------------
INSERT INTO `base_risk_slope` VALUES ('df73e668de6911e8bfa38cec4b81c244', '阎家大窝棚坡', '126.8457926575', '46.8291336644', '黑龙江', '230000', '绥化市', '绥化市', '望奎县', '231221', '山体滑坡', '小', '稳定', '海丰镇政府', '张文宇', '', '暴雨', '0', '0');
INSERT INTO `base_risk_slope` VALUES ('df73e67fde6911e8bfa38cec4b81c244', '崔家屯坡', '126.8457926575', '46.8291336644', '黑龙江', '230000', '绥化市', '绥化市', '望奎县', '231221', '山体滑坡', '小', '稳定', '海丰镇政府', '范桂详', '', '暴雨', '0', '0');
INSERT INTO `base_risk_slope` VALUES ('df73e688de6911e8bfa38cec4b81c244', '八方坡', '126.8907800485', '46.8686164404', '黑龙江', '230000', '绥化市', '绥化市', '望奎县', '231221', '山体滑坡', '小', '稳定', '海丰镇政府', '权利军', '', '暴雨', '0', '0');
INSERT INTO `base_risk_slope` VALUES ('df73e690de6911e8bfa38cec4b81c244', '黄崖子坡', '126.4593801527', '46.6671352075', '黑龙江', '230000', '绥化市', '绥化市', '望奎县', '231221', '山体滑坡', '小', '稳定', '通江镇政府', '贾振刚', '', '暴雨', '0', '0');

-- ----------------------------
-- Table structure for base_risk_waterlogging
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_waterlogging`;
CREATE TABLE `base_risk_waterlogging` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '内涝名称',
  `code` varchar(60) DEFAULT NULL COMMENT '代码',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `alt` double DEFAULT NULL COMMENT '高程',
  `province` varchar(60) DEFAULT NULL COMMENT '省',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(60) DEFAULT NULL COMMENT '区县代码',
  `stree` varchar(60) DEFAULT NULL COMMENT '社区（街道）名',
  `street_code` varchar(60) DEFAULT NULL COMMENT '社区（街道）代码',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '内涝灾害开始时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '内涝灾害结束时间',
  `depth` double DEFAULT NULL COMMENT '最大积水深度',
  `area` double DEFAULT NULL COMMENT '最大淹没面积',
  `1h_prec` double DEFAULT NULL COMMENT '内涝发生时最大1小时雨量',
  `2h_prec` double DEFAULT NULL COMMENT '内涝发生时最大2小时雨量',
  `3h_prec` double DEFAULT NULL COMMENT '内涝发生时最大3小时雨量',
  `6h_prec` double DEFAULT NULL COMMENT '内涝发生时最大6小时雨量',
  `12h_prec` double DEFAULT NULL COMMENT '内涝发生时最大12小时雨量',
  `24h_prec` double DEFAULT NULL COMMENT '内涝发生时最大24小时雨量',
  `range_lon` varchar(300) DEFAULT NULL COMMENT '受影响经度范围',
  `range_lat` varchar(300) DEFAULT NULL COMMENT '受影响纬度范围',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '管理单位',
  `monitor_people` varchar(20) NOT NULL COMMENT '联系人',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_waterlogging
-- ----------------------------
INSERT INTO `base_risk_waterlogging` VALUES ('dfca86fdde6911e8bfa38cec4b81c244', '望奎县经济技术开发区', '', '126.4522614073', '46.8319925149', null, '黑龙江省', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '2018-08-24 00:00:00', '2018-08-27 00:00:00', null, null, null, null, null, null, null, null, '', '', '望奎县水务局', '王兆权', '');
INSERT INTO `base_risk_waterlogging` VALUES ('dfca8716de6911e8bfa38cec4b81c244', '坤南强排站', '', '126.3711807458', '46.6132795477', null, '黑龙江省', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '2018-08-24 00:00:00', '2018-09-03 00:00:00', null, null, null, null, null, null, null, null, '', '', '望奎县水务局', '王兆权', '');

-- ----------------------------
-- Table structure for base_risk_waterlogging_area
-- ----------------------------
DROP TABLE IF EXISTS `base_risk_waterlogging_area`;
CREATE TABLE `base_risk_waterlogging_area` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '名称',
  `code` varchar(60) DEFAULT NULL COMMENT '代码',
  `lon` double NOT NULL COMMENT '经度范围',
  `lat` double NOT NULL COMMENT '纬度范围',
  `province` varchar(60) DEFAULT NULL COMMENT '省名',
  `province_code` varchar(60) DEFAULT NULL COMMENT '省代码',
  `city` varchar(60) DEFAULT NULL COMMENT '市(地区/州)名',
  `city_code` varchar(60) DEFAULT NULL COMMENT '市(地区/州)代码',
  `district` varchar(60) DEFAULT NULL COMMENT '区县',
  `district_code` varchar(60) DEFAULT NULL COMMENT '区县代码',
  `street` varchar(60) DEFAULT NULL COMMENT '社区（街道）名',
  `street_code` varchar(60) DEFAULT NULL COMMENT '社区（街道）代码',
  `measures` varchar(500) DEFAULT NULL COMMENT '防御措施',
  `depth` double DEFAULT NULL COMMENT '最大积水深度',
  `area` double DEFAULT NULL COMMENT '最大淹没面积',
  `1h_prec` double DEFAULT NULL COMMENT '内涝发生时最大1小时雨量',
  `monitor_organ` varchar(50) DEFAULT NULL COMMENT '监测单位',
  `monitor_people` varchar(20) NOT NULL COMMENT '联系人',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_risk_waterlogging_area
-- ----------------------------
INSERT INTO `base_risk_waterlogging_area` VALUES ('e0458337de6911e8bfa38cec4b81c244', '乌龙沟涝区', '', '126.8111718643', '47.0323948663', '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '及时排水', null, null, null, '望奎县水务局', '', '');
INSERT INTO `base_risk_waterlogging_area` VALUES ('e0458351de6911e8bfa38cec4b81c244', '克音河涝区', '', '126.6684601665', '46.5570478336', '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '及时排水', null, null, null, '望奎县水务局', '', '');
INSERT INTO `base_risk_waterlogging_area` VALUES ('e045835cde6911e8bfa38cec4b81c244', '灵山涝区', '', '115.4341124778', '26.3545549604', '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '及时排水', null, null, null, '望奎县水务局', '', '');
INSERT INTO `base_risk_waterlogging_area` VALUES ('e0458368de6911e8bfa38cec4b81c244', '西八荒涝区', '', '126.1257896713', '46.3108172159', '黑龙江', '230000', '绥化市', '231200', '望奎县', '231221', '', '', '及时排水', null, null, null, '望奎县水务局', '', '');

-- ----------------------------
-- Table structure for base_unit_agricultur_park
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_agricultur_park`;
CREATE TABLE `base_unit_agricultur_park` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '园区名称',
  `district` varchar(20) DEFAULT NULL COMMENT '区县',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `type` varchar(20) DEFAULT NULL COMMENT '园区类型',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `worker` int(10) DEFAULT NULL COMMENT '工作人员人数',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '园区描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_agricultur_park
-- ----------------------------
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ade2de6911e8bfa38cec4b81c244', '宽三村绿色棚室园区', '望奎县', '望奎县农业局', '专业生产型', '93750', '8', '小马家沟西侧', '126.0812821639', '42.6674243063', '佟文军', '15845544333', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084adf8de6911e8bfa38cec4b81c244', '东郊镇蔬菜园区', '望奎县', '望奎县农业局', '专业生产型', '125000', '9', '东郊镇\n厢兰五村', '104.7025000478', '35.0446045972', '赵智超', '13845505066', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae01de6911e8bfa38cec4b81c244', '兴达蔬菜园区', '望奎县', '望奎县农业局', '专业生产型', '187500', '12', '宽头公路两侧', '126.48414661', '46.8315954179', '隋百辉', '15845599888', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae0cde6911e8bfa38cec4b81c244', '兴达粘玉米园区', '望奎县', '望奎县农业局', '专业生产型', '187500', '7', '宽头公路两侧', '126.4961283158', '46.8415589238', '隋百辉', '15845599888', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae12de6911e8bfa38cec4b81c244', '盛源蔬菜园区', '望奎县', '望奎县农业局', '专业生产型', '62500', '10', '八方二里二屯', '126.47126953', '46.82307434', '隋百辉', '15845599888', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae1dde6911e8bfa38cec4b81c244', '轮作示范园区', '望奎县', '望奎县农业局', '专业生产型', '1250000', '15', '绥北高速两侧', '126.8457926575', '46.8291336644', '隋百辉', '15845599888', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae26de6911e8bfa38cec4b81c244', '惠七镇惠六村经济作物园区', '望奎县', '望奎县农业局', '专业生产型', '312500', '16', '惠七镇惠六村', '126.6820386965', '47.0602586078', '于业伟', '15246529555', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae2cde6911e8bfa38cec4b81c244', '果蔬园区', '望奎县', '望奎县农业局', '专业生产型', '187500', '24', '后三东屯', '126.4912562539', '46.8307755502', '王福成', '15146539797', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae34de6911e8bfa38cec4b81c244', '双高大豆种植', '望奎县', '望奎县农业局', '专业生产型', '187500', '19', '莲花镇屯', '126.6942357641', '46.4230436508', '刘恕', '18724378255', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae3dde6911e8bfa38cec4b81c244', '后头马铃薯种植示范园区', '望奎县', '望奎县农业局', '专业生产型', '625000', '22', '正白后头村', '126.577430748', '46.5407482482', '张宝贵', '18245554111', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae45de6911e8bfa38cec4b81c244', '正兰后头村高产玉米种植示范园区', '望奎县', '望奎县农业局', '专业生产型', '625000', '15', '正兰后头村后头屯西', '126.4995783158', '46.8420089238', '王德宝', '13555335544', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae51de6911e8bfa38cec4b81c244', '140栋标准化黑木耳吊袋大棚示范园区', '望奎县', '望奎县农业局', '专业生产型', '125000', '12', '坤头村大岗子屯西', '125.2931790894', '46.2355910435', '王志龙', '15945555111', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');
INSERT INTO `base_unit_agricultur_park` VALUES ('e084ae5cde6911e8bfa38cec4b81c244', '绿色水稻种植园区', '望奎县', '望奎县农业局', '专业生产型', '5000000', '10', '镇厢白二村西', '126.47665032', '46.83632087', '王志龙', '15945555111', '主要种植农业蔬菜。', null, null, '2018-12-05 09:56:50', '2018-12-05 09:56:50');

-- ----------------------------
-- Table structure for base_unit_attractions
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_attractions`;
CREATE TABLE `base_unit_attractions` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '景区名称',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `capacity` int(11) DEFAULT NULL COMMENT '可容纳人数',
  `worker` int(11) DEFAULT NULL COMMENT '工作人员人数',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '景区描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_attractions
-- ----------------------------
INSERT INTO `base_unit_attractions` VALUES ('e0c3bd1dde6911e8bfa38cec4b81c244', '望奎县植物园', '望奎县', '望奎县人民政府', '15', '7000', '30', '黑龙江省绥化市望奎县县城北郊海望路西', '126.48', '46.85', '蔡玉军', '15845080999', '国家2A级旅游景区，配设大型音乐喷泉、舞台、景观花坛、罗马柱、张拉膜等特色建筑，并配备排水、照明系统和绿化工程。', null, null, '2018-12-05 09:56:51', '2018-12-05 09:56:52');

-- ----------------------------
-- Table structure for base_unit_bridge
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_bridge`;
CREATE TABLE `base_unit_bridge` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '桥梁名称',
  `type` varchar(20) DEFAULT NULL COMMENT '桥型',
  `address` varchar(255) DEFAULT NULL COMMENT '桥址',
  `length` varchar(20) DEFAULT NULL COMMENT '主跨长度',
  `build_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '成桥时间',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '桥梁描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_bridge
-- ----------------------------
INSERT INTO `base_unit_bridge` VALUES ('e115ced0de6911e8bfa38cec4b81c244', '绥化望奎大桥', '梁式桥', '望奎县南部呼兰河', '566.4米', '2000-09-01 00:00:00', '望奎县', '126.692352', '46.691681', '望奎县公路管理站', '刘彦辉', '13836430888', '2000年9月1日改造完工跨径总长560米', null, null, '2018-12-05 09:56:53', '2018-12-05 09:56:54');
INSERT INTO `base_unit_bridge` VALUES ('e115cee7de6911e8bfa38cec4b81c244', '北八里桥', '梁式桥', '望奎县北部沟壑', '105.24米', '2013-11-01 00:00:00', '望奎县', '126.481651', '46.882853', '望奎县公路管理站', '刘彦辉', '13836430888', '2013年11月1日改造完工跨径总长100米', null, null, '2018-12-05 09:56:53', '2018-12-05 09:56:54');
INSERT INTO `base_unit_bridge` VALUES ('e115ceedde6911e8bfa38cec4b81c244', '克音河桥', '梁式桥', '望奎县东部克音河', '187.72米', '2017-11-01 00:00:00', '望奎县', '126.926657', '46.926804', '望奎县公路管理站', '刘彦辉', '13836430888', '2017年11月1日改造完工跨径总长180米', null, null, '2018-12-05 09:56:53', '2018-12-05 09:56:54');

-- ----------------------------
-- Table structure for base_unit_danger
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_danger`;
CREATE TABLE `base_unit_danger` (
  `id` varchar(64) NOT NULL,
  `district` varchar(20) DEFAULT NULL COMMENT '区县',
  `name` varchar(60) NOT NULL COMMENT '名称',
  `project` varchar(60) DEFAULT NULL COMMENT '项目名称',
  `building` varchar(60) DEFAULT NULL COMMENT '建筑物名称',
  `number` int(10) DEFAULT NULL COMMENT '单体数',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `product` varchar(255) DEFAULT NULL COMMENT '品名',
  `tanks` varchar(40) DEFAULT NULL COMMENT '储罐个数及容量',
  `machine` int(10) DEFAULT NULL COMMENT '加油机台数',
  `lightning_people` varchar(20) DEFAULT NULL COMMENT '企业防雷安全责任人',
  `lightning_phone` varchar(20) DEFAULT NULL COMMENT '责任人联系电话',
  `area` varchar(255) DEFAULT NULL COMMENT '所属片区',
  `leader` varchar(20) DEFAULT NULL COMMENT '局领导',
  `lightning_leader` varchar(20) DEFAULT NULL COMMENT '防雷所分管领导',
  `cadres` varchar(20) DEFAULT NULL COMMENT '业务中层干部',
  `test_leader` varchar(20) DEFAULT NULL COMMENT '检测片区组长',
  `test_member` varchar(40) DEFAULT NULL COMMENT '检测片区组员',
  `test_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最新检测日期',
  `report` varchar(40) DEFAULT NULL COMMENT '最新报告编号',
  `status` varchar(60) DEFAULT NULL COMMENT '防雷安全隐患情况',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_danger
-- ----------------------------
INSERT INTO `base_unit_danger` VALUES ('e15c1f67de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部望奎加油站', '望奎加油站', '望奎加油站', '1', '黑龙江省绥化市望奎县望奎镇西门外糖厂路南', '126.456973', '46.833376', '汽油、柴油', '4/30', '4', '李洪鹏', '15846667333', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018044', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1f80de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部北林加油站', '北林加油站', '北林加油站', '1', '黑龙江省绥化市望奎县望奎镇北门外海望路西', '126.487355', '46.848884', '汽油、柴油', '3/15', '3', '刘东', '15845088055', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018052', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1f89de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部东城加油站', '东城加油站', '东城加油站', '1', '黑龙江省绥化市望奎县望奎镇中央大街1号', '126.515702', '46.842293', '汽油、柴油', '4/30', '4', '任龙', '15845531515', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018047', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1f91de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部环城加油站', '环城加油站', '环城加油站', '1', '黑龙江省绥化市望奎县望奎镇交通街158号', '126.475799', '46.83167', '汽油、柴油', '5/30', '6', '王永辉', '15946155556', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018045', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1f9ade6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部龙华加油站', '龙华加油站', '龙华加油站', '1', '黑龙江省绥化市望奎县绥安公路与海丰公路东北角', '126.517919', '46.836089', '汽油、柴油', '4/30', '4', '周光', '13845558266', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018046', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fa2de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部第八加油站', '第八加油站', '第八加油站', '1', '黑龙江省绥化市望奎县望奎镇奋斗路与交通街东南角', '126.491353', '46.833582', '汽油、柴油', '4/30', '5', '乔栋', '13846753333', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018063', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fa8de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部第九加油站', '第九加油站', '第九加油站', '1', '黑龙江省绥化市望奎县先锋镇散前村', '126.285108', '46.764089', '汽油、柴油', '4/30', '4', '王立志', '13846746862', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018043', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fb4de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部通江加油站', '通江加油站', '通江加油站', '1', '黑龙江省绥化市望奎县通江镇所在地', '126.460474', '46.666393', '汽油、柴油', '2/50', '2', '刘武', '13555324444', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018042', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fbcde6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部卫星加油站', '卫星加油站', '卫星加油站', '1', '黑龙江省绥化市望奎县卫星镇南路西', '126.671085', '46.720716', '汽油、柴油', '3/20', '2', '张伟峰', '15945557333', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018062', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fc2de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部绥北高速公路四方台服务区东加油站', '高速东加油站', '高速东加油站', '1', '黑龙江省绥化市望奎县绥北高速公路K45公路+500米处路东侧', '126.857033', '47.047366', '汽油、柴油', '5/30', '4', '王瑞国', '13836487555', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018050', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fcade6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化望奎经营部绥北高速公路四方台服务区西加油站', '高速西加油站', '高速西加油站', '1', '黑龙江省绥化市望奎县绥北高速公路K45公路+500米处路西侧', '126.854671', '47.047352', '汽油、柴油', '5/30', '4', '王瑞国', '13836487555', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018051', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fd3de6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化销售分公司四方台加油站', '四方台加油站', '四方台加油站', '1', '黑龙江省绥化市北林区四方台镇内', '126.979715', '46.929011', '汽油、柴油', '2/30、2/40', '3', '王利军', '15184565757', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018048', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fdede6911e8bfa38cec4b81c244', '望奎县', '中国石油天然股份有限公司黑龙江绥化销售分公司张维加油站', '张维加油站', '张维加油站', '1', '黑龙江省绥化市北林区张维镇内', '127.000827', '47.064612', '汽油、柴油', '3/30', '2', '乔传宝', '13846710777', '', '', '', '', '', '', '0000-00-00 00:00:00', 'LJ甲02HLJ(SH)2018049', '无', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fe7de6911e8bfa38cec4b81c244', '望奎县', '望奎县东升农机服务站', '望奎县东升农机服务站', '望奎县东升农机服务站', null, '', '126.83', '46.9', '汽油、柴油', '2/30', '2', '姜玉良', '13836488885', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1fecde6911e8bfa38cec4b81c244', '望奎县', '望奎县富饶农机服务站', '望奎县富饶农机服务站', '望奎县富饶农机服务站', null, '', '126.3', '46.75', '汽油、柴油', '2/30', '2', '李占军', '15845534998', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1ff5de6911e8bfa38cec4b81c244', '望奎县', '望奎县惠七农机服务站', '望奎县惠七农机服务站', '望奎县惠七农机服务站', null, '', '126.67', '47.1', '汽油、柴油', '2/30', '2', '徐建军', '13054259993', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c1ffede6911e8bfa38cec4b81c244', '望奎县', '望奎县厢白农机加油站', '望奎县厢白农机加油站', '望奎县厢白农机加油站', null, '', '126.53', '46.9', '汽油、柴油', '2/30', '2', '刘云辉', '13274569488', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2017de6911e8bfa38cec4b81c244', '望奎县', '望奎县后三农机服务站', '望奎县后三农机服务站', '望奎县后三农机服务站', null, '', '126.32', '46.92', '汽油、柴油', '2/30', '2', '吕贵文', '13836426847', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2020de6911e8bfa38cec4b81c244', '望奎县', '望奎县灵山农机服务站', '望奎县灵山农机服务站', '望奎县灵山农机服务站', null, '', '126.38', '46.91', '汽油、柴油', '2/30', '2', '王彦军', '13845526410', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c202bde6911e8bfa38cec4b81c244', '望奎县', '望奎县通江农机服务站', '望奎县通江农机服务站', '望奎县通江农机服务站', null, '', '126.4', '46.61', '汽油、柴油', '2/30', '2', '金喜涛', '13846723339', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c203fde6911e8bfa38cec4b81c244', '望奎县', '望奎县海丰农机服务站', '望奎县海丰农机服务站', '望奎县海丰农机服务站', null, '', '126.79', '46.81', '汽油、柴油', '2/30', '2', '高庆波', '18245532000', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2047de6911e8bfa38cec4b81c244', '望奎县', '望奎县恭六农机服务站', '望奎县恭六农机服务站', '望奎县恭六农机服务站', null, '', '126.85', '47.08', '汽油、柴油', '2/30', '2', '赵德强', '13555317799', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2050de6911e8bfa38cec4b81c244', '望奎县', '望奎县火箭农机服务站', '望奎县火箭农机服务站', '望奎县火箭农机服务站', null, '', '126.5', '46.75', '汽油、柴油', '2/30', '2', '冷树信', '13763792677', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2059de6911e8bfa38cec4b81c244', '望奎县', '望奎县富源农机服务站', '望奎县富源农机服务站', '望奎县富源农机服务站', null, '', '126.33', '46.68', '汽油、柴油', '2/30', '2', '赵  双', '15045549888', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2061de6911e8bfa38cec4b81c244', '望奎县', '望奎县东郊农机服务站', '望奎县东郊农机服务站', '望奎县东郊农机服务站', null, '', '126.46', '46.83', '汽油、柴油', '2/30', '2', '刘洪涛', '13796584674', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2067de6911e8bfa38cec4b81c244', '望奎县', '望奎县灯塔农机服务站', '望奎县灯塔农机服务站', '望奎县灯塔农机服务站', null, '', '126.77', '46.9', '汽油、柴油', '2/30', '2', '徐明权', '13796562111', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2072de6911e8bfa38cec4b81c244', '望奎县', '望奎县莲花农机服务站       ', '望奎县莲花农机服务站       ', '望奎县莲花农机服务站       ', null, '', '126.8', '46.98', '汽油、柴油', '2/30', '2', '张  波', '15046647111', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c207bde6911e8bfa38cec4b81c244', '望奎县', '望奎县灯塔乡敏三农机服务站 ', '望奎县灯塔乡敏三农机服务站 ', '望奎县灯塔乡敏三农机服务站 ', null, '', '126.66', '46.8', '汽油、柴油', '2/30', '2', '周士臣', '13845544135', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2086de6911e8bfa38cec4b81c244', '望奎县', '望奎县卫星镇宏晟加油站     ', '望奎县卫星镇宏晟加油站     ', '望奎县卫星镇宏晟加油站     ', null, '', '126.66', '46.68', '汽油、柴油', '2/30', '2', '赵春林', '13945506085', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c208fde6911e8bfa38cec4b81c244', '望奎县', '中国石化销售有限公司黑龙江绥化石油分公司先锋加油站 ', '中国石化销售有限公司黑龙江绥化\n石油分公司先锋加油站 ', '中国石化销售有限公司黑龙江绥化\n石油分公司先锋加油站 ', null, '', '126.4', '46.8', '汽油、柴油', '2/50、3/30', '6', '张路', '18645133521', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');
INSERT INTO `base_unit_danger` VALUES ('e15c2094de6911e8bfa38cec4b81c244', '望奎县', '中国石化销售有限公司黑龙江绥化石油分公司通用加油站 ', '中国石化销售有限公司黑龙江绥化\n石油分公司通用加油站 ', '', null, '', '126.5', '46.8', '汽油、柴油', '4/30', '4', '师东旭', '18346458555', '', '', '', '', '', '', null, '', '', null, null, '2018-12-05 09:56:55', '2018-12-05 09:56:56');

-- ----------------------------
-- Table structure for base_unit_dike
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_dike`;
CREATE TABLE `base_unit_dike` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '提防名称',
  `river` varchar(60) DEFAULT NULL COMMENT '河流',
  `flood_prevention` varchar(30) DEFAULT NULL COMMENT '防洪标准',
  `length` varchar(20) DEFAULT NULL COMMENT '长度',
  `altitude` varchar(40) DEFAULT NULL COMMENT '高程',
  `distance` varchar(20) DEFAULT NULL COMMENT '平均堤距',
  `height` varchar(255) DEFAULT NULL COMMENT '高度',
  `width` varchar(255) DEFAULT NULL COMMENT '堤顶宽度',
  `soil` varchar(255) DEFAULT NULL COMMENT '堤身土质',
  `slope_length` varchar(255) DEFAULT NULL COMMENT '堤岸堤坡长度',
  `province` varchar(20) DEFAULT NULL COMMENT '所属省份',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '提防描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_dike
-- ----------------------------
INSERT INTO `base_unit_dike` VALUES ('e1a10e0ede6911e8bfa38cec4b81c244', '呼兰河堤防', '呼兰河', '20年一遇', '19.2公里', '136.83-136.81米', '1500米', '3.8米', '5米', '均质黏土', '7.6米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '2015年加高培厚，可抵御20年一遇洪水', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');
INSERT INTO `base_unit_dike` VALUES ('e1a10e30de6911e8bfa38cec4b81c244', '克音河堤防', '克音河', '20年一遇', '60.8公里', '150.56-136.80米', '200米', '3.5米', '5米', '均质黏土', '8.75米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '2016年加高培厚，可抵御20年一遇洪水', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');
INSERT INTO `base_unit_dike` VALUES ('e1a10e3cde6911e8bfa38cec4b81c244', '通肯河堤防', '通肯河', '20年一遇', '35公里', '168.75-149.10米', '1200米', '3.5米', '5米', '均质黏土', '8.75米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '2015年加高培厚，可抵御20年一遇洪水', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');
INSERT INTO `base_unit_dike` VALUES ('e1a10e44de6911e8bfa38cec4b81c244', '头道乌龙沟左堤防', '头道乌龙沟左', '10年一遇', '26.8公里', '193.00-145.00米', '40米', '0.7米', '1-2米', '均质黏土', '1.4米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '原设计标准10年一遇防洪标准，现在破坏严重，只能达到5年一遇防洪防洪标准。', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');
INSERT INTO `base_unit_dike` VALUES ('e1a10e4dde6911e8bfa38cec4b81c244', '头道乌龙沟右堤防', '头道乌龙沟右', '10年一遇', '27.7公里', '193.00-145.00米', '40米', '0.7米', '1-2米', '均质黏土', '1.4米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '原设计标准10年一遇防洪标准，现在破坏严重，只能达到5年一遇防洪防洪标准。', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');
INSERT INTO `base_unit_dike` VALUES ('e1a10e58de6911e8bfa38cec4b81c244', '二道乌龙沟左堤防', '二道乌龙沟左', '10年一遇', '30公里', '187.50-148.00米', '60米', '0.5米', '1-2米', '均质黏土', '1米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '原设计标准10年一遇防洪标准，现在破坏严重，只能达到5年一遇防洪防洪标准。', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');
INSERT INTO `base_unit_dike` VALUES ('e1a10e64de6911e8bfa38cec4b81c244', '二道乌龙沟右堤防', '二道乌龙沟右', '10年一遇', '34.25公里', '187.50-148.00米', '60米', '0.5米', '1-2米', '均质黏土', '1米', '黑龙江省', '黑龙江省望奎县水务局', '赵文涛', '13846712457', '原设计标准10年一遇防洪标准，现在破坏严重，只能达到5年一遇防洪防洪标准。', null, null, '2018-12-05 09:56:58', '2018-12-05 09:56:58');

-- ----------------------------
-- Table structure for base_unit_highway
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_highway`;
CREATE TABLE `base_unit_highway` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '高速公路名称',
  `code` varchar(10) NOT NULL DEFAULT '' COMMENT '高速编号',
  `start` varchar(20) DEFAULT NULL COMMENT '起点',
  `end` varchar(20) DEFAULT NULL COMMENT '终点',
  `province` varchar(20) DEFAULT NULL COMMENT '所属省份',
  `length` varchar(30) DEFAULT NULL COMMENT '全线长',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '高速描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_highway
-- ----------------------------
INSERT INTO `base_unit_highway` VALUES ('e2076223de6911e8bfa38cec4b81c244', '绥北高速', 'S15', '绥化', '北安', '黑龙江省', '223公里', '黑龙江省高等级管理所', '段钢文', '15045680777', ' 绥北高速公路起点位于绥化城区西部，绥安公路南侧，终点位于北安市建华村西南，与北安至黑河、北安至五大连池两条高速公路交汇点相衔接，全长223公里。', null, null, '2018-12-05 09:57:00', '2018-12-05 09:57:00');

-- ----------------------------
-- Table structure for base_unit_hospital
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_hospital`;
CREATE TABLE `base_unit_hospital` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '医院名称',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `doctor` int(10) DEFAULT NULL COMMENT '医生人数',
  `nurse` int(11) DEFAULT NULL COMMENT '护士人数',
  `ambulance` int(11) DEFAULT NULL COMMENT '救护车数量',
  `bed` int(11) DEFAULT NULL COMMENT '床位数量',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '医院描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_hospital
-- ----------------------------
INSERT INTO `base_unit_hospital` VALUES ('e249eb3ade6911e8bfa38cec4b81c244', '望奎县人民医院', '绥化市', '40000', '153', '267', '8', '500', '126.4770483246', '46.8395016141', '望奎县卫计局', '于占海', '13945526161', '三甲医院', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb56de6911e8bfa38cec4b81c244', '望奎县中医院', '绥化市', '10528', '188', '221', '4', '550', '126.48868661', '46.8404054179', '望奎县卫计局', '刘士义', '13845505155', '三甲医院', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb5fde6911e8bfa38cec4b81c244', '望奎县妇幼保健院', '绥化市', '6462', '58', '39', '1', '30', '126.491710947', '46.8351394817', '望奎县卫计局', '庄德仲', '13766768558', '专科医院', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb64de6911e8bfa38cec4b81c244', '望奎镇通江镇中心卫生院', '绥化市', '5000', '15', '5', '1', '40', '116.1543514552', '38.0339832743', '望奎县卫计局', '李良孝', '13804866933', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb70de6911e8bfa38cec4b81c244', '望奎县卫星镇中心卫生院', '绥化市', '1500', '27', '7', '1', '30', '126.6659849814', '46.7273166404', '望奎县卫计局', '王辉', '15046643666', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb78de6911e8bfa38cec4b81c244', '望奎县海丰镇卫生院', '绥化市', '3500', '20', '4', '0', '30', '126.498130947', '46.8403894817', '望奎县卫计局', '孙振双', '15145728777', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb81de6911e8bfa38cec4b81c244', '望奎县敏三乡卫生院', '绥化市', '2000', '6', '3', '0', '8', '126.6896226959', '46.8292190194', '望奎县卫计局', '林春来', '13199258844', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb86de6911e8bfa38cec4b81c244', '望奎县灯塔乡中心卫生院', '绥化市', '3500', '8', '3', '1', '25', '126.7983125871', '46.9107242539', '望奎县卫计局', '徐贵辉', '13206909922', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb92de6911e8bfa38cec4b81c244', '望奎县东升乡卫生院', '绥化市', '2500', '9', '3', '1', '15', '126.8929210162', '47.0105908908', '望奎县卫计局', '孙越超', '18045508345', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eb9dde6911e8bfa38cec4b81c244', '望奎县莲花镇中心卫生院', '绥化市', '5000', '18', '4', '0', '30', '126.8268114946', '47.0024230503', '望奎县卫计局', '袁长虹', '13351459555', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eba9de6911e8bfa38cec4b81c244', '望奎县厢白乡卫生院', '绥化市', '3000', '22', '4', '1', '25', '126.6361923777', '46.9450568881', '望奎县卫计局', '唐志刚', '18724378111', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebaede6911e8bfa38cec4b81c244', '望奎县惠七镇卫生院', '绥化市', '5000', '15', '5', '0', '30', '126.6840049338', '47.0650110655', '望奎县卫计局', '唐景明', '15326555766', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebbade6911e8bfa38cec4b81c244', '望奎县恭六乡卫生院', '绥化市', '3000', '8', '2', '1', '30', '126.8753923682', '47.1084905189', '望奎县卫计局', '张洪伟', '13846793737', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebbfde6911e8bfa38cec4b81c244', '望奎县望奎镇卫生院', '绥化市', '1000', '19', '8', '0', '17', '126.4982583158', '46.8432389238', '望奎县卫计局', '邢立平', '15845084777', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebc5de6911e8bfa38cec4b81c244', '望奎县灵山乡卫生院', '绥化市', '3500', '8', '2', '0', '30', '126.4670346411', '46.9421400666', '望奎县卫计局', '程广飞', '15946154222', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebcbde6911e8bfa38cec4b81c244', '望奎县后三乡卫生院', '绥化市', '3500', '12', '5', '0', '30', '126.3733249523', '46.9351959674', '望奎县卫计局', '刘向淑', '18645541002', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebd0de6911e8bfa38cec4b81c244', '望奎县先锋镇卫生院', '绥化市', '1500', '19', '8', '1', '30', '126.3854870369', '46.8076307069', '望奎县卫计局', '王晓娟', '13845549120', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebd9de6911e8bfa38cec4b81c244', '望奎县富源乡卫生院', '绥化市', '2000', '8', '3', '0', '10', '126.3263811807', '46.7426462271', '望奎县卫计局', '由树军', '13796549040', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebe1de6911e8bfa38cec4b81c244', '望奎县火箭镇卫生院', '绥化市', '4000', '17', '5', '0', '35', '126.5146068103', '46.7604723157', '望奎县卫计局', '芦波', '15045548106', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ebeade6911e8bfa38cec4b81c244', '望奎县东郊镇卫生院', '绥化市', '2000', '11', '4', '0', '15', '126.5646733929', '46.8544741051', '望奎县卫计局', '许祥文', '15665085151', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec04de6911e8bfa38cec4b81c244', '望奎县东郊乡后水五村卫生室', '绥化市', '60', '3', '0', '0', null, '126.5993233036', '46.8786001354', '望奎县卫计局', '孔宪峰', '13846798943', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec09de6911e8bfa38cec4b81c244', '望奎县东郊乡厢兰四村卫生室', '绥化市', '60', '1', '0', '0', null, '126.5624231784', '46.8344472202', '望奎县卫计局', '王录辉', '13846765589', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec12de6911e8bfa38cec4b81c244', '望奎县东郊乡前水五村卫生室', '绥化市', '60', '3', '0', '0', null, '126.6192629188', '46.8541907128', '望奎县卫计局', '陈兆茹', '15845537260', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec17de6911e8bfa38cec4b81c244', '望奎县东郊乡水四村卫生室', '绥化市', '60', '4', '0', '0', null, '126.6121072069', '46.832551052', '望奎县卫计局', '王波', '13796551260', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec20de6911e8bfa38cec4b81c244', '望奎县东郊乡前三村卫生室', '绥化市', '60', '1', '0', '0', null, '126.5178689086', '46.896758781', '望奎县卫计局', '周洪飞', '13836437832', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec2bde6911e8bfa38cec4b81c244', '望奎县东郊乡前二村卫生室', '绥化市', '60', '2', '0', '0', null, '126.5582700427', '46.903716694', '望奎县卫计局', '冷杰', '13836424108', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec34de6911e8bfa38cec4b81c244', '望奎县东郊乡厢兰五村卫生室', '绥化市', '60', '1', '0', '0', null, '126.5661433929', '46.8547141051', '望奎县卫计局', '王福生', '13945526910', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec3ade6911e8bfa38cec4b81c244', '望奎县通江镇通江村卫生室', '绥化市', '60', '3', '0', '0', null, '126.446111', '46.659167', '望奎县卫计局', '赵士娟', '13846741195', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec42de6911e8bfa38cec4b81c244', '望奎县通江镇坤头村卫生室', '绥化市', '60', '7', '0', '0', null, '126.4593801527', '46.6671352075', '望奎县卫计局', '牛福顺', '13846747177', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec4bde6911e8bfa38cec4b81c244', '望奎县通江镇坤南村卫生室', '绥化市', '60', '6', '0', '0', null, '126.3711807458', '46.6132795477', '望奎县卫计局', '吴广文', '13836405058', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec50de6911e8bfa38cec4b81c244', '望奎县通江镇正兰头村卫生室', '绥化市', '60', '5', '0', '0', null, '126.4593801527', '46.6671352075', '望奎县卫计局', '贾云飞', '13251656111', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec56de6911e8bfa38cec4b81c244', '望奎县通江镇红头村卫生室', '绥化市', '60', '3', '0', '0', null, '126.4879829663', '46.6887216926', '望奎县卫计局', '李秋菊', '15046588766', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec5cde6911e8bfa38cec4b81c244', '望奎县通江镇厢白二村卫生室', '绥化市', '60', '3', '0', '0', null, '126.4518013661', '46.687987315', '望奎县卫计局', '孙岩', '13945506161', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec64de6911e8bfa38cec4b81c244', '望奎县通江镇厢白头村卫生室', '绥化市', '60', '3', '0', '0', null, '126.446841025', '46.6634617794', '望奎县卫计局', '王辉', '13945526378', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec70de6911e8bfa38cec4b81c244', '望奎县通江镇坤二村卫生室', '绥化市', '60', '5', '0', '0', null, '126.408578711', '46.6704612241', '望奎县卫计局', '王喜军', '0455-6747555', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec75de6911e8bfa38cec4b81c244', '望奎县厢白乡后二村卫生室', '绥化市', '60', '1', '0', '0', null, '126.7034848605', '46.968544571', '望奎县卫计局', '穆永伟', '15146534455', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec7bde6911e8bfa38cec4b81c244', '望奎县厢白乡后三村卫生室', '绥化市', '60', '0', '0', '0', null, '126.6340823777', '46.9445268881', '望奎县卫计局', '林淑平', '15245513736', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec84de6911e8bfa38cec4b81c244', '望奎县厢白乡前二村卫生室', '绥化市', '60', '3', '0', '0', null, '126.4315134683', '46.6926851147', '望奎县卫计局', '毕学森', '13804865275', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec8cde6911e8bfa38cec4b81c244', '望奎县厢白乡前三村卫生室', '绥化市', '60', '5', '0', '0', null, '126.6506893711', '46.9113104206', '望奎县卫计局', '张立庭', '15845536379', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec92de6911e8bfa38cec4b81c244', '望奎县厢白乡后头村卫生室', '绥化市', '60', '1', '0', '0', null, '126.7542342977', '46.9664417074', '望奎县卫计局', '佟伟', '13763725543', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ec9ade6911e8bfa38cec4b81c244', '望奎县厢白乡惠五村卫生室', '绥化市', '60', '3', '0', '0', null, '126.6633754259', '46.9759017918', '望奎县卫计局', '白万国', '15945559403', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eca3de6911e8bfa38cec4b81c244', '望奎县厢白乡兰六村卫生室', '绥化市', '60', '2', '0', '0', null, '126.5554324773', '46.9949879304', '望奎县卫计局', '孙晶', '18745577370', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecabde6911e8bfa38cec4b81c244', '望奎县莲花镇宽四村卫生室', '绥化市', '60', '1', '0', '0', null, '126.8228271562', '46.9987746812', '望奎县卫计局', '郑国锋', '13089949386', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecb1de6911e8bfa38cec4b81c244', '望奎县莲花镇宽五西村卫生室', '绥化市', '60', '0', '0', '0', null, '126.8504575413', '47.0508783098', '望奎县卫计局', '李洪志', '15845083086', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecb7de6911e8bfa38cec4b81c244', '望奎县莲花镇宽五东村卫生室', '绥化市', '60', '2', '0', '0', null, '126.8504575413', '47.0508783098', '望奎县卫计局', '孙奎', '13846723288', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecbfde6911e8bfa38cec4b81c244', '望奎县莲花镇前二村卫生室', '绥化市', '60', '2', '0', '0', null, '126.8582526631', '46.9309848193', '望奎县卫计局', '倪志礼', '13845594851', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eccbde6911e8bfa38cec4b81c244', '望奎县莲花镇信五村卫生室', '绥化市', '60', '2', '0', '0', null, '126.7760992422', '46.9902351205', '望奎县卫计局', '陶德文', '13845537630', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecd0de6911e8bfa38cec4b81c244', '望奎县莲花镇后三村卫生室', '绥化市', '60', '2', '0', '0', null, '126.7843681928', '46.964446546', '望奎县卫计局', '初立明', '15946151769', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecd9de6911e8bfa38cec4b81c244', '望奎县莲花镇厢黄后二村卫生室', '绥化市', '60', '1', '0', '0', null, '126.7236062754', '46.9931248718', '望奎县卫计局', '周国发', '13796551512', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ece1de6911e8bfa38cec4b81c244', '望奎县莲花镇信六村卫生室', '绥化市', '60', '2', '0', '0', null, '126.7837649661', '47.0381578533', '望奎县卫计局', '胡卫东', '15046588300', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecedde6911e8bfa38cec4b81c244', '望奎县莲花镇白后头村卫生室', '绥化市', '60', '1', '0', '0', null, '126.7542342977', '46.9664417074', '望奎县卫计局', '张金柱', '15045548843', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecf2de6911e8bfa38cec4b81c244', '望奎县火箭镇坤后二南北村卫生室', '绥化市', '60', '3', '0', '0', null, '126.31957', '46.70707', '望奎县卫计局', '张文光', '0455-6851320', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ecfbde6911e8bfa38cec4b81c244', '望奎县火箭镇坤后二南村卫生室', '绥化市', '60', '3', '0', '0', null, '126.317558', '46.67786', '望奎县卫计局', '徐敏彪', '13763747488', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed04de6911e8bfa38cec4b81c244', '望奎县火箭镇厢白三村卫生室', '绥化市', '60', '3', '0', '0', null, '126.4216724575', '46.7439522025', '望奎县卫计局', '刘大庆', '13836459291', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed0cde6911e8bfa38cec4b81c244', '望奎县火箭镇坤后二北村卫生室', '绥化市', '60', '3', '0', '0', null, '126.3332167274', '46.7424145708', '望奎县卫计局', '李永', '13846753859', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed17de6911e8bfa38cec4b81c244', '望奎县东升后头东村卫生室', '绥化市', '60', '3', '0', '0', null, '126.9290702568', '46.970646841', '望奎县卫计局', '王金波', '15004557120', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed1dde6911e8bfa38cec4b81c244', '望奎县东升乾一村卫生室', '绥化市', '60', '2', '0', '0', null, '126.9414043439', '46.9947912269', '望奎县卫计局', '邵士彬', '18746521692', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed23de6911e8bfa38cec4b81c244', '望奎县东升前头村卫生室', '绥化市', '60', '3', '0', '0', null, '126.8949167972', '46.9598541439', '望奎县卫计局', '孙智慧', '15845535588', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed2bde6911e8bfa38cec4b81c244', '望奎县东升恭四村卫生室', '绥化市', '60', '2', '0', '0', null, '126.8929210162', '47.0105908908', '望奎县卫计局', '姜辉', '13766768802', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed34de6911e8bfa38cec4b81c244', '望奎县东升恭五村卫生室', '绥化市', '60', '1', '0', '0', null, '126.8929210162', '47.0105908908', '望奎县卫计局', '孟凡明', '13845599917', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed3cde6911e8bfa38cec4b81c244', '望奎县东升后头西村卫生室', '绥化市', '60', '1', '0', '0', null, '126.8873681941', '46.9870554261', '望奎县卫计局', '纪伟', '13845534288', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed42de6911e8bfa38cec4b81c244', '望奎县卫星惠二村卫生室', '绥化市', '60', '8', '0', '0', null, '126.6747146537', '46.7500828653', '望奎县卫计局', '赵雪东', '13763713809', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed53de6911e8bfa38cec4b81c244', '望奎县卫星水头村卫生室', '绥化市', '60', '7', '0', '0', null, '126.6397889926', '46.7076757792', '望奎县卫计局', '孙化生', '18724367111', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed59de6911e8bfa38cec4b81c244', '望奎县卫星水二村卫生室', '绥化市', '60', '6', '0', '0', null, '126.612617251', '46.7413470293', '望奎县卫计局', '曹春信', '13763789444', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed64de6911e8bfa38cec4b81c244', '望奎县卫星敏头村卫生室', '绥化市', '60', '4', '0', '0', null, '126.7110371043', '46.7430158691', '望奎县卫计局', '孙立颖', '15146537755', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed6ade6911e8bfa38cec4b81c244', '望奎县卫星厢兰头村卫生室', '绥化市', '60', '6', '0', '0', null, '126.5782481896', '46.7033634947', '望奎县卫计局', '李双力', '13624552533', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed70de6911e8bfa38cec4b81c244', '望奎县卫星信头村卫生室', '绥化市', '60', '7', '0', '0', null, '126.7679837828', '46.7453627041', '望奎县卫计局', '刘洪伟', '13945557290', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed78de6911e8bfa38cec4b81c244', '望奎县卫星惠头村卫生室', '绥化市', '60', '5', '0', '0', null, '126.6704949814', '46.7248366404', '望奎县卫计局', '侯淑艳', '15945552016', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed7ede6911e8bfa38cec4b81c244', '望奎县灯塔乡信二村卫生室', '绥化市', '60', '3', '0', '0', null, '126.7606560176', '46.7866529602', '望奎县卫计局', '李满', '13836459515', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed84de6911e8bfa38cec4b81c244', '望奎县灯塔乡敏三村卫生室', '绥化市', '60', '2', '0', '0', null, '126.6929087264', '46.827106953', '望奎县卫计局', '臧东启', '13846755557', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed8cde6911e8bfa38cec4b81c244', '望奎县灯塔乡惠四村卫生室', '绥化市', '60', '3', '0', '0', null, '126.6565648576', '46.8287256332', '望奎县卫计局', '杜宏岩', '13836424560', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed92de6911e8bfa38cec4b81c244', '望奎县灯塔乡信三村卫生室', '绥化市', '60', '3', '0', '0', null, '126.7477698662', '46.8426801732', '望奎县卫计局', '曲东亮', '13836437117', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ed97de6911e8bfa38cec4b81c244', '望奎县恭六乡乾三后村卫生室', '绥化市', '60', '3', '0', '0', null, '126.8753633682', '47.1084975189', '望奎县卫计局', '孙禄君', '13555396275', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eda0de6911e8bfa38cec4b81c244', '望奎县恭六乡乾三前村卫生室', '绥化市', '60', '2', '0', '0', null, '126.932734048', '47.0815171674', '望奎县卫计局', '李方正', '15545523388', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eda9de6911e8bfa38cec4b81c244', '望奎县恭六乡恭六村卫生室', '绥化市', '60', '3', '0', '0', null, '126.8790683279', '47.1135658993', '望奎县卫计局', '刁秀峰', '13796562030', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edaede6911e8bfa38cec4b81c244', '望奎县恭六乡信七村卫生室', '绥化市', '60', '2', '0', '0', null, '126.7590216251', '47.0915223', '望奎县卫计局', '赵景新', '13945548421', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edb4de6911e8bfa38cec4b81c244', '望奎县恭六乡恭五村卫生室', '绥化市', '60', '4', '0', '0', null, '126.894630773', '47.0572319417', '望奎县卫计局', '张耀武', '13555382146', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edbcde6911e8bfa38cec4b81c244', '望奎县恭六乡乾二村卫生室', '绥化市', '60', '4', '0', '0', null, '126.932734048', '47.0815171674', '望奎县卫计局', '刘东辉', '13945544726', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edc5de6911e8bfa38cec4b81c244', '望奎县恭六乡宽六村卫生室', '绥化市', '60', '3', '0', '0', null, '126.8239158152', '47.1029288338', '望奎县卫计局', '杨英奎', '13846707146', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edcdde6911e8bfa38cec4b81c244', '望奎县火箭镇正兰四村卫生室', '绥化市', '60', '7', '0', '0', null, '126.559164434', '46.7978590001', '望奎县卫计局', '姜路', '13836405680', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edd6de6911e8bfa38cec4b81c244', '望奎县火箭镇正兰三村卫生室', '绥化市', '60', '4', '0', '0', null, '126.5277276469', '46.719037762', '望奎县卫计局', '赵成', '15145720609', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eddcde6911e8bfa38cec4b81c244', '望奎县火箭镇厢红三村卫生室', '绥化市', '60', '4', '0', '0', null, '126.4702734329', '46.7878372215', '望奎县卫计局', '刘跃华', '15145531144', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249edf5de6911e8bfa38cec4b81c244', '望奎县火箭镇正兰二村卫生室', '绥化市', '60', '7', '0', '0', null, '126.5277236469', '46.719037762', '望奎县卫计局', '孙忠坤', '13836463999', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee04de6911e8bfa38cec4b81c244', '望奎县火箭镇厢兰二村卫生室', '绥化市', '60', '3', '0', '0', null, '126.5694222213', '46.740103954', '望奎县卫计局', '方怀亮', '13804865139', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee09de6911e8bfa38cec4b81c244', '望奎县火箭镇厢红二村卫生室', '绥化市', '60', '9', '0', '0', null, '126.4731168121', '46.7174000772', '望奎县卫计局', '李玉梅', '13163571686', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee12de6911e8bfa38cec4b81c244', '望奎县火箭镇厢兰三村卫生室', '绥化市', '60', '5', '0', '0', null, '126.5694374281', '46.7747755052', '望奎县卫计局', '任大为', '15245512518', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee17de6911e8bfa38cec4b81c244', '望奎县先锋镇先锋村卫生室', '绥化市', '60', '1', '0', '0', null, '126.3939265617', '46.8110054853', '望奎县卫计局', '王印', '13845505799', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee1dde6911e8bfa38cec4b81c244', '望奎县先锋镇沿江村卫生室', '绥化市', '60', '1', '0', '0', null, '127.2052346465', '47.3773634473', '望奎县卫计局', '王忠', '13796562277', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee29de6911e8bfa38cec4b81c244', '望奎县先锋镇厢白四村卫生室', '绥化市', '60', '5', '0', '0', null, '126.4321890687', '46.8196796167', '望奎县卫计局', '马生辉', '13945548807', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee31de6911e8bfa38cec4b81c244', '望奎县先锋镇三段村卫生室', '绥化市', '60', '5', '0', '0', null, '126.2697062184', '46.8219348992', '望奎县卫计局', '梁德宝', '13846746440', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee3cde6911e8bfa38cec4b81c244', '望奎县先锋镇坤四村卫生室', '绥化市', '60', '3', '0', '0', null, '126.3695443897', '46.8698665608', '望奎县卫计局', '谷明瑞', '13845544387', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee42de6911e8bfa38cec4b81c244', '望奎县先锋镇散北村卫生室', '绥化市', '60', '5', '0', '0', null, '126.3939265617', '46.8110054853', '望奎县卫计局', '王东方', '13904555432', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee4bde6911e8bfa38cec4b81c244', '望奎县先锋镇散南村卫生室', '绥化市', '60', '5', '0', '0', null, '126.2529504092', '46.7990562706', '望奎县卫计局', '王立德', '13945557886', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee50de6911e8bfa38cec4b81c244', '望奎县先锋镇四段村卫生室', '绥化市', '60', '6', '0', '0', null, '126.239991256', '46.8864134034', '望奎县卫计局', '王树林', '13845549421', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee5cde6911e8bfa38cec4b81c244', '望奎县先锋镇厢白五村卫生室', '绥化市', '60', '5', '0', '0', null, '126.4229946373', '46.8366309579', '望奎县卫计局', '王雨波', '15845080130', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee61de6911e8bfa38cec4b81c244', '望奎县先锋镇坤三村卫生室', '绥化市', '60', '5', '0', '0', null, '126.3622432525', '46.8277733401', '望奎县卫计局', '张永', '13763713677', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee6ade6911e8bfa38cec4b81c244', '望奎县灵山乡正兰后头村卫生室', '绥化市', '60', '7', '0', '0', null, '126.4579229177', '46.9408639838', '望奎县卫计局', '王景成', '13796584202', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee70de6911e8bfa38cec4b81c244', '望奎县灵山乡正白后三村卫生室', '绥化市', '60', '5', '0', '0', null, '126.4983507336', '46.9523994097', '望奎县卫计局', '赵斌', '13945513283', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee75de6911e8bfa38cec4b81c244', '望奎县灵山乡正白后二村卫生室', '绥化市', '60', '3', '0', '0', null, '126.5366480552', '46.9563150643', '望奎县卫计局', '王振新', '15945553739', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee7bde6911e8bfa38cec4b81c244', '望奎县灵山乡厢红七村卫生室', '绥化市', '60', '3', '0', '0', null, '126.4411578466', '46.9747938504', '望奎县卫计局', '杨庆娟', '15045548479', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee84de6911e8bfa38cec4b81c244', '望奎县灵山乡正兰六村卫生室', '绥化市', '60', '4', '0', '0', null, '126.8457926575', '46.8291336644', '望奎县卫计局', '侯刚', '13766768133', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee8cde6911e8bfa38cec4b81c244', '望奎县海丰镇恭二村卫生室', '绥化市', '60', '2', '0', '0', null, '126.8457926575', '46.8291336644', '望奎县卫计局', '林忠喜', '15945554811', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ee95de6911e8bfa38cec4b81c244', '望奎县海丰镇八方村卫生室', '绥化市', '60', '3', '0', '0', null, '126.8907800485', '46.8686164404', '望奎县卫计局', '冯光辉', '13351459799', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eea0de6911e8bfa38cec4b81c244', '望奎县海丰镇恭三村卫生室', '绥化市', '60', '3', '0', '0', null, '126.8457916575', '46.8291336644', '望奎县卫计局', '李长飞', '13945562335', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eea6de6911e8bfa38cec4b81c244', '望奎县海丰镇宽二村卫生室', '绥化市', '60', '2', '0', '0', null, '126.8041515183', '46.8176022976', '望奎县卫计局', '丁百山', '13304555593', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eeaede6911e8bfa38cec4b81c244', '望奎县海丰镇宽头村卫生室', '绥化市', '60', '4', '0', '0', null, '126.8006184181', '46.7711182654', '望奎县卫计局', '于达', '13836460459', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eebade6911e8bfa38cec4b81c244', '望奎县海丰镇恭头二村卫生室', '绥化市', '60', '4', '0', '0', null, '126.859570107', '46.7955097453', '望奎县卫计局', '李春祥', '13804865821', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eec2de6911e8bfa38cec4b81c244', '望奎县海丰镇恭头一村卫生室', '绥化市', '60', '5', '0', '0', null, '126.8457926575', '46.8291336644', '望奎县卫计局', '张春雨', '15945552322', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eec8de6911e8bfa38cec4b81c244', '望奎县后三乡正兰后三村卫生室', '绥化市', '60', '3', '0', '0', null, '126.3981400368', '46.9622899296', '望奎县卫计局', '袁中山', '13836440006', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eed0de6911e8bfa38cec4b81c244', '望奎县后三乡厢白七村卫生室', '绥化市', '60', '3', '0', '0', null, '126.3916397072', '46.9562149301', '望奎县卫计局', '王岩', '13846776839', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eed6de6911e8bfa38cec4b81c244', '望奎县后三乡厢白十三村卫生室', '绥化市', '60', '3', '0', '0', null, '126.3564636266', '46.9499402827', '望奎县卫计局', '马壮', '13945526644', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eedfde6911e8bfa38cec4b81c244', '望奎县后三乡正兰前二村卫生室', '绥化市', '60', '6', '0', '0', null, '126.4199517604', '46.9039415441', '望奎县卫计局', '徐明', '13836426670', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eee7de6911e8bfa38cec4b81c244', '望奎县后三乡正兰前三村卫生室', '绥化市', '60', '7', '0', '0', null, '126.3803958232', '46.9054440273', '望奎县卫计局', '赵洪亮', '13555328293', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eeedde6911e8bfa38cec4b81c244', '望奎县惠七镇北水八村卫生室', '绥化市', '60', '1', '0', '0', null, '126.6820386965', '47.0602586078', '望奎县卫计局', '周长成', '13796553419', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eef5de6911e8bfa38cec4b81c244', '望奎县惠七镇水七村卫生室', '绥化市', '60', '4', '0', '0', null, '126.685301991', '47.0603133116', '望奎县卫计局', '杨金国', '13846753840', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249eefede6911e8bfa38cec4b81c244', '望奎县惠七镇惠七村卫生室', '绥化市', '60', '3', '0', '0', null, '126.66075301', '47.093069357', '望奎县卫计局', '李贵祥', '13836460499', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef06de6911e8bfa38cec4b81c244', '望奎县惠七镇敏七村卫生室', '绥化市', '60', '2', '0', '0', null, '126.7244208777', '47.0908831045', '望奎县卫计局', '刘忠敏', '13846753285', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef0fde6911e8bfa38cec4b81c244', '望奎县惠七镇惠六村卫生室', '绥化市', '60', '6', '0', '0', null, '126.6559646778', '47.0288541932', '望奎县卫计局', '唐贵军', '13945557360', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef17de6911e8bfa38cec4b81c244', '望奎县惠七镇敏六村卫生室', '绥化市', '60', '2', '0', '0', null, '126.7168211092', '47.0490036323', '望奎县卫计局', '曲凤源', '13845558221', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef1dde6911e8bfa38cec4b81c244', '望奎县惠七镇惠五村卫生室', '绥化市', '60', '4', '0', '0', null, '126.6561988659', '47.0091312641', '望奎县卫计局', '王振伟', '13763725825', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef23de6911e8bfa38cec4b81c244', '望奎县惠七镇前水八村卫生室', '绥化市', '60', '2', '0', '0', null, '126.660872495', '47.0693834502', '望奎县卫计局', '马志福', '13555376781', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef29de6911e8bfa38cec4b81c244', '望奎县望奎镇厢红六村卫生室', '绥化市', '60', '6', '0', '0', null, '126.500302947', '46.8341774817', '望奎县卫计局', '李艳丽', '13945532939', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef31de6911e8bfa38cec4b81c244', '望奎县望奎镇正兰五村卫生室', '绥化市', '60', '6', '0', '0', null, '126.5065493545', '46.8420588843', '望奎县卫计局', '张志军', '15145516688', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef3ade6911e8bfa38cec4b81c244', '望奎县望奎镇厢红五村卫生室', '绥化市', '60', '2', '0', '0', null, '126.4766779908', '46.8261817334', '望奎县卫计局', '李金凤', '13796551407', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef50de6911e8bfa38cec4b81c244', '望奎县望奎镇正兰后四村卫生室', '绥化市', '60', '2', '0', '0', null, '126.5006105481', '46.8224509573', '望奎县卫计局', '杨春辉', '15046580904', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef59de6911e8bfa38cec4b81c244', '望奎县灯塔乡厢白前头村卫生室', '绥化市', '60', '3', '0', '0', null, '126.7609583499', '46.9244498769', '望奎县卫计局', '付文义', '13845573390', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef5fde6911e8bfa38cec4b81c244', '望奎县灯塔乡宽三村卫生室', '绥化市', '60', '1', '0', '0', null, '126.8143497524', '46.8787574853', '望奎县卫计局', '张奉君', '15948323319', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef67de6911e8bfa38cec4b81c244', '望奎县灯塔乡信四村卫生室', '绥化市', '60', '4', '0', '0', null, '126.7399251895', '46.9010282292', '望奎县卫计局', '陈秀辉', '13194559756', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');
INSERT INTO `base_unit_hospital` VALUES ('e249ef6dde6911e8bfa38cec4b81c244', '望奎县灯塔乡敏四村卫生室', '绥化市', '60', '5', '0', '0', null, '126.700246646', '46.8691875462', '望奎县卫计局', '刘俊丰', '13836471260', '公共卫生服务', null, null, '2018-12-05 09:57:02', '2018-12-05 09:57:02');

-- ----------------------------
-- Table structure for base_unit_market
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_market`;
CREATE TABLE `base_unit_market` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '商场名称',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `floor` int(5) DEFAULT NULL COMMENT '楼层',
  `merchant` int(11) DEFAULT NULL COMMENT '商户数量',
  `capacity` int(11) DEFAULT NULL COMMENT '可容纳人数',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '商场描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_market
-- ----------------------------
INSERT INTO `base_unit_market` VALUES ('e292e131de6911e8bfa38cec4b81c244', '黑龙江省鑫缘商都经贸有限公司', '望奎县', '15000', '5', '209', '2300', '126.48', '46.83', '县非国有经济办', '冯永财', '13284046666', '鑫缘商都是现代化商场，融购物餐饮为一体的大型商场，经营项目有：六桂福金店，家用电器，品牌箱包，品牌化妆品，品牌家纺，品牌男女装，休闲系列，高档皮草，玩具卖场。', null, null, '2018-12-05 09:57:04', '2018-12-05 09:57:05');
INSERT INTO `base_unit_market` VALUES ('e292e16fde6911e8bfa38cec4b81c244', '望奎县华晨经贸有限责任公司', '望奎县', '13000', '5', '280', '2000', '126.48', '46.83', '县非国有经济办', '宋环宇', '13304555609', '华晨经贸有限责任公司是现代化有限公司，融购物餐饮为一体的大型商场，经营项目有影院、西餐厅、大型电玩城、儿童欢乐场、家用电器，品牌箱包，品牌化妆品，品牌家纺，品牌男女装，休闲系列，高档皮草。\n', null, null, '2018-12-05 09:57:04', '2018-12-05 09:57:05');
INSERT INTO `base_unit_market` VALUES ('e292e186de6911e8bfa38cec4b81c244', '望奎县中央商贸有限公司', '望奎县', '2243', '7', '328', '1600', '126.48', '46.83', '县非国有经济办', '范亚君', '15246527171', '中央商贸有限公司主营项目有品牌箱包，品牌化妆品，品牌家纺，品牌男女装，休闲系列服饰。', null, null, '2018-12-05 09:57:04', '2018-12-05 09:57:05');
INSERT INTO `base_unit_market` VALUES ('e292e1a0de6911e8bfa38cec4b81c244', '望奎县华鑫商贸有限公司', '望奎县', '1500', '3', '180', '1300', '126.48', '46.83', '县非国有经济办', '翟有辉', '18944625333', '华鑫商贸有限公司主营项目有休闲系列服饰，品牌家纺等。', null, null, '2018-12-05 09:57:04', '2018-12-05 09:57:05');

-- ----------------------------
-- Table structure for base_unit_plant_area
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_plant_area`;
CREATE TABLE `base_unit_plant_area` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '种植区名称',
  `district` varchar(20) DEFAULT NULL COMMENT '区县',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `crops` varchar(60) DEFAULT NULL COMMENT '主要作物',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `worker` int(10) DEFAULT NULL COMMENT '工作人员人数',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '种植区描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_plant_area
-- ----------------------------
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2ec9de6911e8bfa38cec4b81c244', '火箭镇坤后二北村水稻智能催芽示范区', '望奎县', '望奎县农业局', '蔬菜', '31250', '22', '火箭镇坤后二北村', '126.3332167274', '46.7424145708', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2f24de6911e8bfa38cec4b81c244', '火箭镇现代旱作农业马铃薯示范区', '望奎县', '望奎县农业局', '蔬菜', '1375000', '14', '火箭镇厢红二村', '126.5282234829', '46.8286055024', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2f46de6911e8bfa38cec4b81c244', '火箭镇互联网+马铃薯高标准示范基地', '望奎县', '望奎县农业局', '蔬菜', '312500', '13', '火箭镇正兰二村', '126.48946972', '46.75682712', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2f68de6911e8bfa38cec4b81c244', '龙薯联社社马铃薯脱毒种薯繁育基地', '望奎县', '望奎县农业局', '蔬菜', '625000', '10', '火箭镇厢红二村', '126.5282234829', '46.8286055024', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2f87de6911e8bfa38cec4b81c244', '火箭镇正兰三村设施蔬菜示范区', '望奎县', '望奎县农业局', '蔬菜', '125000', '18', '火箭镇正兰三村', '126.5277276469', '46.719037762', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2fa9de6911e8bfa38cec4b81c244', '火箭镇厢红三村现代化烤烟集中育苗示范区', '望奎县', '望奎县农业局', '蔬菜', '30000', '19', '火箭镇厢红三村', '126.51233368', '46.7174698', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2fcede6911e8bfa38cec4b81c244', '火箭镇厢红二村玉米大垄模式栽培示范区', '望奎县', '望奎县农业局', '蔬菜', '5312500', '14', '火箭镇厢红二村', '126.4731168121', '46.7174000772', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb2febde6911e8bfa38cec4b81c244', '火箭镇万亩现代旱作农业玉米示范区', '望奎县', '望奎县农业局', '蔬菜', '4043750', '26', '火箭镇正兰三村', '126.5277276469', '46.719037762', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb3013de6911e8bfa38cec4b81c244', '火箭镇厢兰三村玉米大垄栽培模式示范区', '望奎县', '望奎县农业局', '蔬菜', '531250', '24', '火箭镇厢兰三村', '126.5694374281', '46.7747755052', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb3035de6911e8bfa38cec4b81c244', '火箭镇正兰四村中草药种植示范区', '望奎县', '望奎县农业局', '蔬菜', '281250', '18', '火箭镇正兰四村', '126.5591037011', '46.7988677348', '邱洪福', '15045546555', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb3051de6911e8bfa38cec4b81c244', '正兰头村玉米大垄栽培模式示范区', '望奎县', '望奎县农业局', '蔬菜', '2125000', '17', '正兰头村', '126.4359737683', '46.6906684782', '王志龙', '15945558000', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb306bde6911e8bfa38cec4b81c244', '红头村鲜食玉米示范区', '望奎县', '望奎县农业局', '蔬菜', '250000', '12', '红头村', '126.47256805', '46.68689925', '王志龙', '15945558000', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb3087de6911e8bfa38cec4b81c244', '红头村玉米大垄模式栽培示范区', '望奎县', '望奎县农业局', '蔬菜', '2812500', '24', '红头村', '126.4816443602', '46.6823670252', '王志龙', '15945558000', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb30a9de6911e8bfa38cec4b81c244', '设施蔬菜示范区', '望奎县', '望奎县农业局', '蔬菜', '125000', '8', '正兰后四村', '126.5025598426', '46.8222184176', '陈丽瑶', '15004555999', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb30ccde6911e8bfa38cec4b81c244', '望奎县第一良种场现代农业科技示范区', '望奎县', '望奎县农业局', '蔬菜', '93750', '13', '第一良种场', '126.5097413545', '46.8422428843', '陈丽瑶', '15004555999', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb30ebde6911e8bfa38cec4b81c244', '黑土情缘杂粮种植示范区', '望奎县', '望奎县农业局', '蔬菜', '262500', '10', '水头村', '126.49399577', '46.83940993', '刘伟龙', '13845537366', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb310dde6911e8bfa38cec4b81c244', '两个爸爸家庭农场示范区', '望奎县', '望奎县农业局', '蔬菜', '125000', '9', '水头村', '126.6397889926', '46.7076757792', '刘伟龙', '13845537366', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb312fde6911e8bfa38cec4b81c244', '吴老六采摘园', '望奎县', '望奎县农业局', '蔬菜', '40625', '6', '水头村', '126.62505291', '46.70566332', '刘伟龙', '13845537366', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb314ede6911e8bfa38cec4b81c244', '景丰瓜菜种子繁育基地', '望奎县', '望奎县农业局', '蔬菜', '125000', '17', '先锋村坤三乌龙江屯', '126.3622432525', '46.8277733401', '刘伟龙', '13845537366', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb3168de6911e8bfa38cec4b81c244', '龙蛙互联网+水稻绿色有机水稻高标准示范基地', '望奎县', '望奎县农业局', '蔬菜', '18750000', '16', '四段村', '126.239991256', '46.8864134034', '张宝贵', '18245554111', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb31b2de6911e8bfa38cec4b81c244', '四段村水稻智能化催芽示范区', '望奎县', '望奎县农业局', '蔬菜', '125000', '8', '四段村小山屯', '126.22710905', '46.87804353', '张宝贵', '18245554111', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb31ccde6911e8bfa38cec4b81c244', '白四村设施蔬菜示范区', '望奎县', '望奎县农业局', '蔬菜', '93750', '6', '青望路南', '126.4282651375', '46.7919178446', '张宝贵', '18245554111', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');
INSERT INTO `base_unit_plant_area` VALUES ('e2fb31ebde6911e8bfa38cec4b81c244', '启民北药种植农民专业合作社', '望奎县', '望奎县农业局', '蔬菜', '312500', '8', '开发区北', '126.4522534073', '46.8320265149', '张宝贵', '18245554111', '主要种植农业蔬菜为主。', null, null, '2018-12-05 09:57:06', '2018-12-05 09:57:06');

-- ----------------------------
-- Table structure for base_unit_reservoir
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_reservoir`;
CREATE TABLE `base_unit_reservoir` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '名称',
  `district` varchar(20) DEFAULT NULL COMMENT '区县',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `district_code` varchar(30) DEFAULT NULL COMMENT '地区编码',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `level` varchar(20) DEFAULT NULL,
  `storage` double DEFAULT NULL COMMENT '总库容(万m3)',
  `limit_storage` double DEFAULT NULL COMMENT '防限库容(万m3)',
  `water_limit` double DEFAULT NULL COMMENT '防限水位(m)',
  `water_normal` double DEFAULT NULL COMMENT '正常蓄水位(m)',
  `principal` varchar(20) NOT NULL COMMENT '联系人',
  `phone` varchar(20) NOT NULL COMMENT '手机',
  `water_line` varchar(2) DEFAULT NULL COMMENT '有无水位',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_reservoir
-- ----------------------------
INSERT INTO `base_unit_reservoir` VALUES ('e346f0dade6911e8bfa38cec4b81c244', '卫星水库', '黑龙江省绥化市望奎县', '望奎县先锋镇坤四村', '231221106204', '126.3695443897', '46.8698665608', '中型', '2034', '1618', '148.2', '148.4', '冯雨春', '13945557995', '有', null, null, '2018-12-05 09:57:08', '2018-12-05 09:57:08');
INSERT INTO `base_unit_reservoir` VALUES ('e346f0f6de6911e8bfa38cec4b81c244', '山头芦水库', '黑龙江省绥化市望奎县', '望奎县先锋镇白四村', '201221103201', '126.4282651375', '46.7919178446', '中型', '2564', '400', '144.35', '146.46', '宋荣生', '13836426693', '有', null, null, '2018-12-05 09:57:08', '2018-12-05 09:57:08');
INSERT INTO `base_unit_reservoir` VALUES ('e346f0ffde6911e8bfa38cec4b81c244', '敏二水库', '黑龙江省绥化市望奎县', '卫星镇惠二村', '231221102203', '126.707353851', '46.7813012153', '小型', '309', '52.9', '164.35', '164.35', '刘伟龙', '13904555158', '无', null, null, '2018-12-05 09:57:08', '2018-12-05 09:57:08');
INSERT INTO `base_unit_reservoir` VALUES ('e346f10dde6911e8bfa38cec4b81c244', '信三水库', '黑龙江省绥化市望奎县', '灯塔乡信三村', '231221207204', '126.7477698662', '46.8426801732', '小型', '124', '70.7', '175.25', '175.25', '阚克', '13555376600', '无', null, null, '2018-12-05 09:57:08', '2018-12-05 09:57:08');
INSERT INTO `base_unit_reservoir` VALUES ('e346f116de6911e8bfa38cec4b81c244', '后三西水库', '黑龙江省绥化市望奎县', '莲花镇厢黄后三西村', '231221104207', '126.7843981928', '46.964366546', '小型', '70.18', '43.2', '180', '180', '赵恒久', '13945532629', '无', null, null, '2018-12-05 09:57:08', '2018-12-05 09:57:08');

-- ----------------------------
-- Table structure for base_unit_school
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_school`;
CREATE TABLE `base_unit_school` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '学校名称',
  `type` varchar(10) DEFAULT NULL COMMENT '学校类型',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `people` int(11) DEFAULT NULL COMMENT '学校人数',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '学校描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_school
-- ----------------------------
INSERT INTO `base_unit_school` VALUES ('e3933e07de6911e8bfa38cec4b81c244', '富饶中心小学', '小学', '绥化市望奎县', '11000', '198', '126.13', '46.52', '县教育局', '李绍辉', '13836426959', '我校是乡镇中心小学，原有教学用房670平方，2016年新建教学综合楼，建筑面积1045平方米，现代化教育教学设施齐备。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e24de6911e8bfa38cec4b81c244', '望奎镇小学', '小学', '绥化市望奎县', '1.22', '207', '126.562737', '45.744651', '县教育局', '李雪峰', '13163636299', '学校面积1.22万平方米，建筑面积783平方米，一至五年级，五个教学班。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e2cde6911e8bfa38cec4b81c244', '第六小学', '小学', '绥化市望奎县', '22814', '749', '126.173056', '46.535278', '县教育局', '郑晓兴', '13836490844', '一栋教学楼，', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e35de6911e8bfa38cec4b81c244', '厢白小学', '小学', '绥化市望奎县', '21985', '158', '126.609167', '46.936388', '县教育局', '谭跃雷', '18245899111', '一栋教学楼，操场面积7500平', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e3dde6911e8bfa38cec4b81c244', '敏三中心小学', '小学', '绥化市望奎县', '10000', '160', '126.6911', '46.8792', '县教育局', '李景利', '15045547832', '学校为混凝土铁皮盖', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e43de6911e8bfa38cec4b81c244', '先锋镇中学', '初级中学', '绥化市望奎县', '22500', '437', '126.644167', '47.336111', '县教育局', '陈晓东', '15245518886', '一栋教学楼、宿舍楼和食堂楼', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e49de6911e8bfa38cec4b81c244', '莲花镇中学', '初级中学', '绥化市望奎县', '42000', '262', '126.483863', '46.592587', '县教育局', '张国良', '13763763637', '学校面积42000万平方米，建筑面积5460平方米，六至九年级，八个教学班。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e51de6911e8bfa38cec4b81c244', '海丰镇中心小学', '小学', '绥化市望奎县', '12000', '552', '126.8', '46.8', '县教育局', '陈兆新', '15146537888', '我校是乡镇中心小学，2015年新建教学综合楼，\n建筑面积3494平方米，现代化教育教学设施齐备。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e5dde6911e8bfa38cec4b81c244', '富源中学', '初级中学', '绥化市望奎县', '28000', '362', '126.33', '46.74', '县教育局', '冷泉', '15046649977', '学校建筑面积5831平方米', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e65de6911e8bfa38cec4b81c244', '东升乡中心小学', '小学', '绥化市望奎县', '20000', '210', '126.897623999999', '47.007628', '县教育局', '毛凤阁', '13836471830', '学校校舍占地面积6581平方米，位于东升乡东北部，有一栋教学楼、两趟平房和一个食堂。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e6bde6911e8bfa38cec4b81c244', '惠七镇中学', '初级中学', '绥化市望奎县', '27000', '224', '126.385', '45.5506', '县教育局', '于彦飞', '15045081166', '学校面积较大，主体教学楼二层一栋，平房食堂一栋，平房宿舍一栋，功能室平方一栋。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e76de6911e8bfa38cec4b81c244', '第一中学', '高级中学', '绥化市望奎县', '4.5', '2700', '126.5', '46.83', '县教育局', '邹尚操', '13836405512', '学校位于望奎县卫生路前进街，占地面积大约4.5万平方米，建筑面积约11559平方米，在校师生近3000人。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e79de6911e8bfa38cec4b81c244', '第三小学', '小学', '绥化市望奎县', '18720', '1608', '126.173056', '46.535277', '县教育局', '孙雪峰', '15145537000', '两栋教学楼，操场面积7215平方米', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e84de6911e8bfa38cec4b81c244', '富饶中学', '初级中学', '绥化市望奎县', '1.55', '191', '126.1426', '46.5311', '县教育局', '李密林', '13836437939', '建筑面积3456平方米，教学楼一栋，食堂宿舍楼一栋。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e8ade6911e8bfa38cec4b81c244', '厢白满族乡中学', '初级中学', '绥化市望奎县', '21985', '112', '126.562737', '45.744651', '县教育局', '符彦成', '15184560345', '学校面积大…', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e90de6911e8bfa38cec4b81c244', '第二中学', '高级中学', '绥化市望奎县', '3.2', '3764', '126.485758', '46.844767', '县教育局', '张立波', '18724365888', '', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e96de6911e8bfa38cec4b81c244', '东升乡中学', '初级中学', '绥化市望奎县', '2', '280', '126.897905', '47.012849', '县教育局', '赵连军', '18697054588', '学校面积较大，主体教学楼四层一栋，平房食堂一栋，平房宿舍一栋，教师公寓二层一栋。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933e9ede6911e8bfa38cec4b81c244', '卫星镇中学', '初级中学', '绥化市望奎县', '30000', '578', '126.385', '47.3842', '县教育局', '张井林', '13804865727', '学校面积大…', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ea7de6911e8bfa38cec4b81c244', '灯塔中学', '初级中学', '绥化市望奎县', '20000', '300', '126.799862', '46.911056', '县教育局', '郑革', '15004556966', '学校面积20000平方米，学生265人，教师35人，教学班8个', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933eacde6911e8bfa38cec4b81c244', '海丰镇中学', '初级中学', '绥化市望奎县', '27232', '694', '126.048', '46.3785', '县教育局', '朱彦龙', '13796573136', '学校建筑面积6682平方米', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ebdde6911e8bfa38cec4b81c244', '灵山中学', '初级中学', '绥化市望奎县', '2', '217', '124.1426', '44.5311', '县教育局', '孙刚', '18204557744', '建筑面积3230平方米，教学楼一栋，食堂宿舍楼一栋。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ec6de6911e8bfa38cec4b81c244', '惠七满族镇中心小学', '小学', '绥化市望奎县', '18768', '377', '126.68', '47.06', '县教育局', '范洪胜', '15045524400', '建筑面积4773平方米，教学楼一栋，幼儿园一所，食堂一栋。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933eccde6911e8bfa38cec4b81c244', '敏三中学', '初级中学', '绥化市望奎县', '2', '268', '126.680833', '46.821111', '县教育局', '梁晓峰', '13763747222', '学校教学楼一栋4层，宿舍平房一栋，食堂平房一栋', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ed4de6911e8bfa38cec4b81c244', '第六中学', '初级中学', '绥化市望奎县', '27760', '2086', '126.48', '46.83', '县教育局', '宁殿全', '13674555858', '与一小合用校园', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933edade6911e8bfa38cec4b81c244', '灯塔乡中心小学', '小学', '绥化市望奎县', '1', '300', '126.799862', '46.911056', '县教育局', '郑革', '15004556966', '学校面积1万平方米，学生200人，教师60人，教学班10个。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ee2de6911e8bfa38cec4b81c244', '富源中心小学', '小学', '绥化市望奎县', '8057.7', '170', '126.316667', '46.733333', '县教育局', '邵海涛', '15046587555', '我校是乡中心小学，2013年新建教学综合楼，\n建筑面积1760平方米，现代化教育教学设施齐备。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ee8de6911e8bfa38cec4b81c244', '第四中学', '初级中学', '绥化市望奎县', '2', '1841', '126.418', '46', '县教育局', '丁爱民', '15145539000', '学校位于望奎县红旗街解放路，占地面积大约2万平方米，建筑面积约6224平方米，在校师生1841人。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ef1de6911e8bfa38cec4b81c244', '东郊中心小学', '小学', '绥化市望奎县', '11000', '154', '126.5581204', '46.8507609', '县教育局', '李森林', '13836460266', '学校为混凝土铁皮盖，两栋平房，一栋540平，一栋460平', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933ef9de6911e8bfa38cec4b81c244', '先锋小学', '小学', '绥化市望奎县', '10760', '412', '126.377778', '46.801944', '县教育局', '徐工兵', '13845566077', '两栋教学楼，操场面积2694平方米', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933effde6911e8bfa38cec4b81c244', '通江镇中学', '初级中学', '绥化市望奎县', '45325', '664', '126.343', '46.557', '县教育局', '王春荣', '15145534551', '学校建筑面积3750平方米', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f1ede6911e8bfa38cec4b81c244', '莲花镇中心小学', '小学', '绥化市望奎县', '19000', '250', '126.368', '46.556', '县教育局', '董伟', '13763738393', '我校是乡镇中心小学，共有两个教学综合楼，\n建筑面积3457平方米，现代化教育教学设施齐备。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f24de6911e8bfa38cec4b81c244', '卫星镇中心小学', '小学', '绥化市望奎县', '20000', '647', '126.189722', '46.535277', '县教育局', '赵力兴', '13555350299', '独立校园', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f27de6911e8bfa38cec4b81c244', '通江小学', '小学', '绥化市望奎县', '1.56', '670', '126.048', '46.3785', '县教育局', '金美汐', '13199039304', '学校面积约：1.56万平方米、教职工：120人、学生：670人。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f2fde6911e8bfa38cec4b81c244', '恭六小学', '小学', '绥化市望奎县', '1.2', '366', '126.9', '47.11', '县教育局', '古浩', '13836405556', '建筑面积6110平方米，教学楼一栋，幼儿园一所，食堂一栋，教室宿舍一栋。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f35de6911e8bfa38cec4b81c244', '后三乡中学', '初级中学', '绥化市望奎县', '34600', '410', '126.3659', '46.9236', '县教育局', '周希峰', '13945526895', '学校面积大…', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f3dde6911e8bfa38cec4b81c244', '和平小学', '小学', '绥化市望奎县', '18835', '2772', '126.089722', '46.934167', '县教育局', '于桂君', '13945562877', '三栋教学楼和一栋平房，总建筑面积10997平方米。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f43de6911e8bfa38cec4b81c244', '第五中学', '初级中学', '绥化市望奎县', '33856', '2274', '126.463051', '46.835616', '县教育局', '王旭东', '13763792399', '学校面积33856平方米，校舍面积13508.4平方米，学生人数2274人，教师人数144人，48个教学班', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f4cde6911e8bfa38cec4b81c244', '幼儿园', '幼儿园', '绥化市望奎县', '3377', '284', '126.599', '46.0564', '县教育局', '张木秋', '13555317771', '一栋教学楼，', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f54de6911e8bfa38cec4b81c244', '后三乡中心小学', '小学', '绥化市望奎县', '12100', '217', '126.614167', '47.5575', '县教育局', '马元力', '15774552129', '我校是一所乡镇中心小学，2017年9月新落成教学楼', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f5ade6911e8bfa38cec4b81c244', '火箭镇中学', '初级中学', '绥化市望奎县', '14400', '643', '126.51', '46.76', '县教育局', '邹莉莉', '13664554788', '学校校舍占地面积14400平方米，位于火箭乡中部，有一栋教学楼、一栋宿舍楼和一个食堂。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f5fde6911e8bfa38cec4b81c244', '火箭镇小学', '小学', '绥化市望奎县', '14400', '527', '126.51', '46.76', '县教育局', '邹莉莉', '13664554788', '学校校舍占地面积14400平方米，位于火箭乡中部，有一栋教学楼、一个食堂。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f65de6911e8bfa38cec4b81c244', '第一小学', '小学', '绥化市望奎县', '33946', '2001', '126.462737', '46.674656', '县教育局', '徐春涛', '13845558335', '与六中合用校园', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f6bde6911e8bfa38cec4b81c244', '恭六中学', '初级中学', '绥化市望奎县', '3.3', '460', '126.376', '46.5533', '县教育局', '王新地', '15845535366', '学校面积大…', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f73de6911e8bfa38cec4b81c244', '第三中学', '初级中学', '绥化市望奎县', '22814', '1100', '126.49', '46.828', '县教育局', '董术海', '1514653155', '学校面积大…', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');
INSERT INTO `base_unit_school` VALUES ('e3933f7cde6911e8bfa38cec4b81c244', '灵山小学', '小学', '绥化市望奎县', '1.3', '195', '126.450172', '46.93528', '县教育局', '孙振邦', '15145553335', '建筑面积6110平方米，教学楼一栋，幼儿园两所，食堂一所，多功能教室一所。', null, null, '2018-12-05 09:57:10', '2018-12-05 09:57:10');

-- ----------------------------
-- Table structure for base_unit_square
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_square`;
CREATE TABLE `base_unit_square` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '广场名称',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `capacity` int(11) DEFAULT NULL COMMENT '可容纳人数',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '广场描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_square
-- ----------------------------
INSERT INTO `base_unit_square` VALUES ('e3d824f9de6911e8bfa38cec4b81c244', '文化广场', '望奎县', '25000', '4000', '126.4700783438', '46.8385507643', '城市管理行政执法局', '唐晓飞', '18045576002', '位于望奎县西郊，内有健身设施，诗词 长廊，环境优美。', null, null, '2018-12-05 09:57:11', '2018-12-05 09:57:12');
INSERT INTO `base_unit_square` VALUES ('e3d82515de6911e8bfa38cec4b81c244', '林枫广场', '望奎县', '20000', '3500', '126.497300947', '46.8376094817', '城市管理行政执法局', '唐晓飞', '18045576002', '内有健身器材、休息凉亭、花坛树木。', null, null, '2018-12-05 09:57:11', '2018-12-05 09:57:12');
INSERT INTO `base_unit_square` VALUES ('e3d8251ede6911e8bfa38cec4b81c244', '体育广场', '望奎县', '30000', '5000', '126.4971083158', '46.8428889238', '城市管理行政执法局', '唐晓飞', '18045576002', '位于望奎县东北部，内有橡胶跑道、篮球场，设施\n齐全', null, null, '2018-12-05 09:57:11', '2018-12-05 09:57:12');

-- ----------------------------
-- Table structure for base_unit_station
-- ----------------------------
DROP TABLE IF EXISTS `base_unit_station`;
CREATE TABLE `base_unit_station` (
  `id` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '车站名称',
  `district` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `area` double DEFAULT NULL COMMENT '占地面积（㎡）',
  `vehicle` int(11) DEFAULT NULL COMMENT '车辆数',
  `capacity` int(11) DEFAULT NULL COMMENT '可容纳人数',
  `lon` double NOT NULL COMMENT '经度',
  `lat` double NOT NULL COMMENT '纬度',
  `unit` varchar(60) DEFAULT NULL COMMENT '所属管辖单位',
  `principal` varchar(20) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '车站描述',
  `createUser` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(64) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_unit_station
-- ----------------------------
INSERT INTO `base_unit_station` VALUES ('a38b8448e3f111e88da08cec4b81c244', '望奎县客运站', '望奎县', '11502', '170', '700', '126.47', '46.83', '望奎县交通局', '徐连刚', '15045548008', '望奎县客运站位于望奎县南五路，每日发送到哈尔滨、大庆、绥化等地客车。', null, null, '2018-12-05 09:57:13', '2018-12-05 09:57:14');

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` varchar(64) NOT NULL COMMENT '渠道id',
  `name` varchar(50) NOT NULL COMMENT '渠道名称',
  `code` varchar(50) NOT NULL COMMENT '渠道编码',
  `icon` varchar(50) DEFAULT NULL COMMENT '渠道图标',
  `p_id` varchar(64) DEFAULT NULL COMMENT '渠道父id',
  `type` smallint(1) NOT NULL DEFAULT '0' COMMENT '类型：0：渠道；1：手段',
  `status` smallint(1) DEFAULT NULL COMMENT '是否启用：0：未启用；1：启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道手段表';

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('059d557bdd8511e8bfa38cec4b81c244', '邮件', 'EMAIL', '/channel/email.png', '', '0', '1', '2018-12-05 10:02:01');
INSERT INTO `channel` VALUES ('1e988e3a8b2811e8b73168f7285847c8', '腾讯微博', 'TENCENT_WEIBO', 'channel/07-微博.png', 'd8730fa68b1511e8b73168f7285847c8', '1', '0', '2018-10-30 14:43:43');
INSERT INTO `channel` VALUES ('28c0317c8b2811e8b73168f7285847c8', '新浪微博', 'SINA_WEIBO', 'channel/07-微博.png', 'd8730fa68b1511e8b73168f7285847c8', '1', '0', '2018-10-30 14:43:45');
INSERT INTO `channel` VALUES ('8d2448858b1511e8b73168f7285847c8', '短信', 'SMS', 'channel/01-短信.png', '', '0', '1', '2018-10-30 14:43:48');
INSERT INTO `channel` VALUES ('93f5c4fc8b1511e8b73168f7285847c8', '声讯', 'VOICE', 'channel/03-声讯.png', '', '0', '0', '2018-10-30 14:43:51');
INSERT INTO `channel` VALUES ('9b7a04038b1511e8b73168f7285847c8', 'APP', 'APP', 'channel/02-手机APP.png', '', '0', '0', '2018-10-30 14:43:56');
INSERT INTO `channel` VALUES ('a142bd608b1511e8b73168f7285847c8', '大喇叭', 'SPEAKER', 'channel/04-大喇叭.png', '', '0', '0', '2018-10-30 14:43:57');
INSERT INTO `channel` VALUES ('aa2ebc348b1511e8b73168f7285847c8', '显示屏', 'LED', 'channel/05-显示屏.png', '', '0', '0', '2018-10-30 14:43:58');
INSERT INTO `channel` VALUES ('b94d47008b1511e8b73168f7285847c8', '网站', 'WEB', 'channel/06-网站.png', '', '0', '0', '2018-10-30 14:44:00');
INSERT INTO `channel` VALUES ('d8730fa68b1511e8b73168f7285847c8', '微博', 'WEIBO', 'channel/07-微博.png', '', '0', '0', '2018-11-02 18:37:43');
INSERT INTO `channel` VALUES ('ddc553428b1511e8b73168f7285847c8', '微信', 'WECHAT', 'channel/08-微信.png', '', '0', '1', '2018-10-30 14:44:03');
INSERT INTO `channel` VALUES ('e53e88dd8b1511e8b73168f7285847c8', '北斗', 'BEIDOU', 'channel/09-北斗卫星.png', '', '0', '0', '2018-10-30 14:44:05');
INSERT INTO `channel` VALUES ('f1d29c338b1511e8b73168f7285847c8', '电视', 'TV', 'channel/10-电视.png', '', '0', '0', '2018-10-30 16:03:16');
INSERT INTO `channel` VALUES ('f7ce05fb8b1511e8b73168f7285847c8', '广播', 'BROADCAST', 'channel/11-广播.png', '', '0', '0', '2018-10-30 16:02:21');
INSERT INTO `channel` VALUES ('fddde3b78b1511e8b73168f7285847c8', '传真', 'FAX', 'channel/12-传真机.png', '', '0', '0', '2018-11-02 18:38:26');

-- ----------------------------
-- Table structure for disaster
-- ----------------------------
DROP TABLE IF EXISTS `disaster`;
CREATE TABLE `disaster` (
  `id` varchar(64) NOT NULL COMMENT '灾种id',
  `name` varchar(100) NOT NULL COMMENT '灾种名称',
  `code` varchar(20) NOT NULL COMMENT '灾种编码',
  `p_id` varchar(64) NOT NULL COMMENT '灾种父类id',
  `disaster_color` smallint(1) DEFAULT '0' COMMENT '灾种颜色：0：红色；1：橙色；2：黄色；3：蓝色',
  `disaster_level` smallint(1) DEFAULT '0' COMMENT '灾种级别：0：Ⅰ级/特别重大；1：Ⅱ级/重大；2：Ⅲ级/较大；3：Ⅳ级/一般',
  `icon` varchar(50) DEFAULT NULL COMMENT '灾种图标',
  `type` smallint(1) DEFAULT '0' COMMENT '类型：0：事件；1：类型；2：灾种',
  `is_config` smallint(1) DEFAULT '0' COMMENT '是否配置：0：未配置灾种级别颜色，1：已配置灾种级别颜色',
  `is_strategy` smallint(1) DEFAULT '0' COMMENT '是否配置策略：0：未配置；1：已配置',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='灾种表';

-- ----------------------------
-- Records of disaster
-- ----------------------------
INSERT INTO `disaster` VALUES ('01ae456190b711e885e268f7285847c8', '台风', '11B01', 'cea0952e90b611e885e268f7285847c8', '2', '2', '/disaster/11B01-2.gif', '2', '1', '0', '2018-10-30 18:07:53');
INSERT INTO `disaster` VALUES ('20a53c218bde11e8b73168f7285847c8', '灾种管理', 'zzgl', '-1', null, null, '', '-1', '0', '0', '2018-07-23 16:16:15');
INSERT INTO `disaster` VALUES ('2a1c9a2a90b711e885e268f7285847c8', '干旱', '11B22', 'dd0f5ea08e6811e885e268f7285847c8', '0', '0', '/disaster/11A52-0.gif', '2', '1', '0', '2018-07-30 16:49:05');
INSERT INTO `disaster` VALUES ('2bced1308e2911e885e268f7285847c8', '气象灾害', 'zrzht', '367f58908dbf11e8a2f668f7285847c8', '0', '0', null, '1', '0', '0', '2018-07-23 11:33:51');
INSERT INTO `disaster` VALUES ('2ef1323190b711e885e268f7285847c8', '干旱', '11B22', 'dd0f5ea08e6811e885e268f7285847c8', '1', '1', '/disaster/11A52-1.gif', '2', '1', '0', '2018-07-27 14:56:50');
INSERT INTO `disaster` VALUES ('3615dcc490b711e885e268f7285847c8', '干旱', '11B22', 'dd0f5ea08e6811e885e268f7285847c8', '2', '2', '/disaster/11A52-2.gif', '2', '1', '0', '2018-07-27 14:56:46');
INSERT INTO `disaster` VALUES ('367f58908dbf11e8a2f668f7285847c8', '自然灾害', 'zrzha', '20a53c218bde11e8b73168f7285847c8', null, null, null, '0', '0', '0', '2018-07-22 22:55:02');
INSERT INTO `disaster` VALUES ('3c69d8c290b711e885e268f7285847c8', '干旱', '11B22', 'dd0f5ea08e6811e885e268f7285847c8', '3', '3', '/disaster/11A52-3.gif', '2', '1', '0', '2018-07-26 17:35:17');
INSERT INTO `disaster` VALUES ('4d2516dd8dbf11e8a2f668f7285847c8', '事故灾难', 'sgzns', '20a53c218bde11e8b73168f7285847c8', null, null, null, '0', '0', '0', '2018-07-22 22:55:40');
INSERT INTO `disaster` VALUES ('576b16f78e6911e885e268f7285847c8', '暴雨', '11B03', '903888858e3811e885e268f7285847c8', '3', '3', 'disaster/11B03-3.gif', '2', '1', '1', '2018-08-07 20:24:48');
INSERT INTO `disaster` VALUES ('5f320cd98e6911e885e268f7285847c8', '暴雨', '11B03', '903888858e3811e885e268f7285847c8', '2', '2', '/disaster/11B03-2.gif', '2', '1', '1', '2018-07-30 16:40:29');
INSERT INTO `disaster` VALUES ('668bafe38dbf11e8a2f668f7285847c8', '社会安全事件', 'shaqs', '20a53c218bde11e8b73168f7285847c8', null, null, null, '0', '0', '0', '2018-07-22 22:56:23');
INSERT INTO `disaster` VALUES ('6bd184e08e6911e885e268f7285847c8', '暴雨', '11B03', '903888858e3811e885e268f7285847c8', '1', '1', 'disaster/11B03-1.gif', '2', '1', '1', '2018-08-07 20:23:25');
INSERT INTO `disaster` VALUES ('71554af58dbf11e8a2f668f7285847c8', '公共卫生事件', 'ggwss', '20a53c218bde11e8b73168f7285847c8', null, null, null, '0', '0', '0', '2018-07-22 22:56:41');
INSERT INTO `disaster` VALUES ('71cb388d8e6911e885e268f7285847c8', '暴雨', '11B03', '903888858e3811e885e268f7285847c8', '0', '0', 'disaster/11B03-0.gif', '2', '1', '1', '2018-08-07 20:24:45');
INSERT INTO `disaster` VALUES ('74993d8a90b611e885e268f7285847c8', '水旱', '11A00', 'fcab05808e6811e885e268f7285847c8', '0', '0', '/disaster/11A00-0.gif', '2', '1', '0', '2018-10-30 18:05:09');
INSERT INTO `disaster` VALUES ('7d49e44c90b611e885e268f7285847c8', '水旱', '11A00', 'fcab05808e6811e885e268f7285847c8', '1', '1', '/disaster/11A00-1.gif', '2', '1', '0', '2018-07-26 17:29:56');
INSERT INTO `disaster` VALUES ('8274068190b611e885e268f7285847c8', '水旱', '11A00', 'fcab05808e6811e885e268f7285847c8', '2', '2', '/disaster/11A00-2.gif', '2', '1', '0', '2018-07-26 17:30:05');
INSERT INTO `disaster` VALUES ('889edd4c90b611e885e268f7285847c8', '水旱', '11A00', 'fcab05808e6811e885e268f7285847c8', '3', '3', '/disaster/11A00-3.gif', '2', '1', '0', '2018-07-26 17:30:15');
INSERT INTO `disaster` VALUES ('8e1daa07a74411e8bee28cec4b81c244', '大雾', '11B17', 'f271fc25a74311e8bee28cec4b81c244', '0', '0', '/disaster/11B17-0.gif', '2', '1', '0', '2018-08-24 10:22:14');
INSERT INTO `disaster` VALUES ('903888858e3811e885e268f7285847c8', '暴雨', '11B03', '2bced1308e2911e885e268f7285847c8', '0', '0', null, '2', '0', '0', '2018-07-23 13:23:42');
INSERT INTO `disaster` VALUES ('9eb1d1a5a74411e8bee28cec4b81c244', '大雾', '11B17', 'f271fc25a74311e8bee28cec4b81c244', '1', '1', '/disaster/11B17-1.gif', '2', '1', '1', '2018-08-24 10:30:35');
INSERT INTO `disaster` VALUES ('a046edf193d411e885e268f7285847c8', '台风', '11B01', 'cea0952e90b611e885e268f7285847c8', '3', '3', '/disaster/11B01-3.gif', '2', '1', '0', '2018-09-13 14:35:51');
INSERT INTO `disaster` VALUES ('a9f6636ea74411e8bee28cec4b81c244', '大雾', '11B17', 'f271fc25a74311e8bee28cec4b81c244', '2', '2', '/disaster/11B17-2.gif', '2', '1', '0', '2018-08-24 10:23:01');
INSERT INTO `disaster` VALUES ('cea0952e90b611e885e268f7285847c8', '台风', '11B01', '2bced1308e2911e885e268f7285847c8', '0', '0', null, '2', '0', '0', '2018-07-26 17:32:13');
INSERT INTO `disaster` VALUES ('dd0f5ea08e6811e885e268f7285847c8', '干旱', '11B22', '2bced1308e2911e885e268f7285847c8', '0', '0', null, '2', '0', '0', '2018-07-23 19:09:27');
INSERT INTO `disaster` VALUES ('f06b178790b611e885e268f7285847c8', '台风', '11B01', 'cea0952e90b611e885e268f7285847c8', '0', '0', '/disaster/11B01-0.gif', '2', '1', '0', '2018-09-13 14:35:51');
INSERT INTO `disaster` VALUES ('f271fc25a74311e8bee28cec4b81c244', '大雾', '11B17', '2bced1308e2911e885e268f7285847c8', '0', '0', null, '2', '0', '0', '2018-08-24 10:17:53');
INSERT INTO `disaster` VALUES ('f7762eca90b611e885e268f7285847c8', '台风', '11B01', 'cea0952e90b611e885e268f7285847c8', '1', '1', '/disaster/11B01-1.gif', '2', '1', '0', '2018-09-12 18:13:55');
INSERT INTO `disaster` VALUES ('fcab05808e6811e885e268f7285847c8', '水旱', '11A00', '2bced1308e2911e885e268f7285847c8', '0', '0', null, '2', '0', '0', '2018-07-23 19:10:20');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `login_name` varchar(50) NOT NULL COMMENT '登录名称',
  `login_password` varchar(64) NOT NULL COMMENT '登录密码',
  `name` varchar(50) DEFAULT NULL COMMENT '登录用户真实名称',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区id外键',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构外键',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '当前系统时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('0987db17956111e8a49f8cec4b81c244', 'yjb', '73daed4874fae71e0fdefd8957ce110c', '华池县应急办人员', '52db5b81970911e8a5ed8cec4b81c244', '233d9c8c987911e8a5ed8cec4b81c244', '15398744567', 'yingjiban@123.com', '1', '2018-09-29 15:00:42');
INSERT INTO `employee` VALUES ('5af52ab4db6e11e8bfa38cec4b81c244', 'lwd', '37abfead08e2e7e6b00458d3ae2e0d68', '李卫东', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '18503478998', 'lwd@23.com', '0', '2018-10-29 19:32:41');
INSERT INTO `employee` VALUES ('6ddd2841c2f011e89d3e8cec4b81c244', 'lxv', '4240f62bf61488766afb76402ab1340d', '李晓伟', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '13520070216', 'lxv@sina.com', '1', '2018-10-23 16:16:36');
INSERT INTO `employee` VALUES ('71c1ab28955f11e8a49f8cec4b81c244', 'fbzx', '4ab08cc3c4df6d9095c4ff0bc02afa84', '华池县发布中心人员', '52db5b81970911e8a5ed8cec4b81c244', '312ddd73987811e8a5ed8cec4b81c244', '18503488778', 'fabuzhongxin@123.com', '0', '2018-09-29 15:02:32');
INSERT INTO `employee` VALUES ('8d362798956111e8a49f8cec4b81c244', 'qxj', '7c4c7672cd19b5ae8789bb5788858dd5', '华池县气象台人员', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '18605377897', 'shuiliju@123.com', '0', '2018-09-28 09:10:17');
INSERT INTO `employee` VALUES ('a2a3f670cbb211e89d3e8cec4b81c244', 'jxc', 'd89eed7ffaf469b365ec0d0bc2803a1b', '姜新灿', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '18501377889', 'alittlexincan@sina.com', '1', '2018-10-09 19:00:46');
INSERT INTO `employee` VALUES ('a77f9f1be63911e88da08cec4b81c244', 'admin', 'c6ac59a147fa349f1032c521b7e521a3', '华池县气象局管理员', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '18501377889', '605941719@qq.com', '1', '2018-11-12 13:12:58');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `id` varchar(64) NOT NULL COMMENT '用户角色ID',
  `employee_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('5def6645db6e11e8bfa38cec4b81c244', '5af52ab4db6e11e8bfa38cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '2018-10-29 19:32:46');
INSERT INTO `employee_role` VALUES ('606ef38bdb2c11e8bfa38cec4b81c244', '6ddd2841c2f011e89d3e8cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '2018-10-29 11:40:24');
INSERT INTO `employee_role` VALUES ('764595b7d68f11e89a818cec4b81c244', '71c1ab28955f11e8a49f8cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '2018-10-23 14:46:52');
INSERT INTO `employee_role` VALUES ('7de45a33d68f11e89a818cec4b81c244', '0987db17956111e8a49f8cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '2018-10-23 14:47:05');
INSERT INTO `employee_role` VALUES ('8b08c15ad68f11e89a818cec4b81c244', '8d362798956111e8a49f8cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '2018-10-23 14:47:27');
INSERT INTO `employee_role` VALUES ('ab2ff981e63911e88da08cec4b81c244', 'a77f9f1be63911e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '2018-11-12 13:13:04');
INSERT INTO `employee_role` VALUES ('c39c5194d68511e89a818cec4b81c244', 'a2a3f670cbb211e89d3e8cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '2018-10-23 13:37:27');

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `id` varchar(64) NOT NULL,
  `user` varchar(64) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interface
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(64) NOT NULL COMMENT '菜单id',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `code` varchar(50) NOT NULL COMMENT '菜单编码',
  `url` varchar(50) NOT NULL COMMENT '菜单路径',
  `p_id` varchar(64) NOT NULL COMMENT '菜单父类id',
  `icon` varchar(50) DEFAULT '' COMMENT '菜单图标',
  `level` smallint(1) DEFAULT NULL COMMENT '菜单管理：1：一级；2：二级；3：三级',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `turn` smallint(3) DEFAULT NULL COMMENT '菜单排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('04eb0a93e0bb11e8bfa38cec4b81c244', '山洪', 'riskMountain', 'page/base/riskMountain', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '10', '2018-11-05 13:23:52');
INSERT INTO `menu` VALUES ('04ff91dae16311e8bfa38cec4b81c244', '规范制度', 'standard', '', 'navigation', 'layui-icon layui-icon-home', '1', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '17', '2018-11-06 09:26:21');
INSERT INTO `menu` VALUES ('093b731be1a511e8bfa38cec4b81c244', '学校', 'unitSchool', 'page/base/unitSchool', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '26', '2018-11-06 17:18:55');
INSERT INTO `menu` VALUES ('0a79d9e2caac11e89d3e8cec4b81c244', '员工管理', 'employee', 'page/sys/employee', '7de8a3fbcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '2', '2018-10-08 11:41:07');
INSERT INTO `menu` VALUES ('0bf7b0a6db2c11e8bfa38cec4b81c244', '产品制作', 'paoduct-model', 'page/ueditor/ueditor', 'dcaa1b90db2b11e8bfa38cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '0', '2018-11-02 11:48:12');
INSERT INTO `menu` VALUES ('0c006460caae11e89d3e8cec4b81c244', '发布中心发布', 'publish', 'page/warn/publish', '32362c53caa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '5', '2018-10-08 11:55:28');
INSERT INTO `menu` VALUES ('10c35f0fcaa911e89d3e8cec4b81c244', '导航', 'nav-manager', '', 'navigation', 'layui-icon layui-icon-home', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-10-19 12:58:20');
INSERT INTO `menu` VALUES ('11312583e23d11e8bfa38cec4b81c244', '危险品场所', 'unitDanger', 'page/base/unitDanger', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '19', '2018-11-07 11:27:12');
INSERT INTO `menu` VALUES ('192f30f0caad11e89d3e8cec4b81c244', '策略管理', 'strategy', 'page/sys/strategy', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '7', '2018-10-08 11:48:41');
INSERT INTO `menu` VALUES ('1adcb306caac11e89d3e8cec4b81c244', '角色管理', 'role', 'page/sys/role', '7de8a3fbcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '3', '2018-10-08 11:41:34');
INSERT INTO `menu` VALUES ('1b609b98e1a511e8bfa38cec4b81c244', '广场', 'unitSquare', 'page/base/unitSquare', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '27', '2018-11-06 17:19:25');
INSERT INTO `menu` VALUES ('1e2ecf50cdc511e8810a8cec4b81c244', '预警查询', 'warn-list', 'page/warn/list', '32362c53caa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '6', '2018-10-12 10:19:05');
INSERT INTO `menu` VALUES ('1ec96cf8e0bb11e8bfa38cec4b81c244', '森林火灾重点防范区', 'riskForest', 'page/base/riskForest', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '13', '2018-11-05 13:24:35');
INSERT INTO `menu` VALUES ('1f7696e1e23d11e8bfa38cec4b81c244', '桥梁', 'unitBridge', 'page/base/unitBridge', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '18', '2018-11-07 11:27:36');
INSERT INTO `menu` VALUES ('2527b2f1caad11e89d3e8cec4b81c244', '预警管理', 'warn', 'page/sys/warn', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '8', '2018-10-08 11:49:01');
INSERT INTO `menu` VALUES ('264d5387e16311e8bfa38cec4b81c244', '预警服务规范', 'standard', 'page/standard/standard', '04ff91dae16311e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '0', '2018-11-06 09:27:17');
INSERT INTO `menu` VALUES ('2afe6cb2caac11e89d3e8cec4b81c244', '权限管理', 'permission', 'page/sys/permission', '7de8a3fbcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '4', '2018-10-08 11:42:01');
INSERT INTO `menu` VALUES ('2bca3896de6c11e8bfa38cec4b81c244', '基础数据', 'data-manager', '', 'navigation', 'layui-icon layui-icon-template-1', '1', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '8', '2018-11-02 14:54:30');
INSERT INTO `menu` VALUES ('2d8994d7db6e11e8bfa38cec4b81c244', '展示分析', 'basiss-monitor', 'page/base/base', 'b2d9ea94db6d11e8bfa38cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-11-06 19:11:48');
INSERT INTO `menu` VALUES ('311a63bae1a511e8bfa38cec4b81c244', '车站', 'unitStation', 'page/base/unitStation', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '28', '2018-11-06 17:20:02');
INSERT INTO `menu` VALUES ('32362c53caa911e89d3e8cec4b81c244', '预警处理', 'warn-manager', '', 'navigation', 'layui-icon layui-icon-template', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '2', '2018-10-19 12:58:16');
INSERT INTO `menu` VALUES ('367a914ccaae11e89d3e8cec4b81c244', '主页', 'home', 'home', '10c35f0fcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '0', '2018-10-08 13:27:43');
INSERT INTO `menu` VALUES ('447ae6eee0bb11e8bfa38cec4b81c244', '陡坡', 'riskSlope', 'page/base/riskSlope', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '14', '2018-11-05 13:25:38');
INSERT INTO `menu` VALUES ('454c416fcaa911e89d3e8cec4b81c244', '日常服务', 'message-manager', '', 'navigation', 'layui-icon layui-icon-release', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '3', '2018-10-19 12:58:09');
INSERT INTO `menu` VALUES ('46fdfc89de7f11e8bfa38cec4b81c244', '办公场所', 'FacilityOffice', 'page/base/facilityOffice', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '4', '2018-11-02 17:11:16');
INSERT INTO `menu` VALUES ('4dff2eebde6c11e8bfa38cec4b81c244', '灾情类型', 'disaster_type', 'page/base/disasterType', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '1', '2018-11-02 15:12:54');
INSERT INTO `menu` VALUES ('51a018c1caaa11e89d3e8cec4b81c244', '菜单管理', 'menu', 'page/sys/menu', '7de8a3fbcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-10-08 11:28:47');
INSERT INTO `menu` VALUES ('63e1093ede7f11e8bfa38cec4b81c244', '发布设备', 'FacilityPublish', 'page/base/facilityPublish', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '5', '2018-11-02 17:12:04');
INSERT INTO `menu` VALUES ('669274cdde6c11e8bfa38cec4b81c244', '历史灾情', 'disaster_history', 'page/base/disasterHistory', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '2', '2018-11-02 14:56:08');
INSERT INTO `menu` VALUES ('68aaab7acaa911e89d3e8cec4b81c244', '业务管理', 'business-manager', '', 'navigation', 'layui-icon layui-icon-component', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '4', '2018-10-19 12:58:02');
INSERT INTO `menu` VALUES ('6f014044caac11e89d3e8cec4b81c244', '地区管理', 'area', 'page/sys/area', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '0', '2018-10-30 20:25:48');
INSERT INTO `menu` VALUES ('763f3ffbe1bb11e8bfa38cec4b81c244', '农业种植区', 'unitPlantArea', 'page/base/unitPlantArea', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '24', '2018-11-06 19:59:27');
INSERT INTO `menu` VALUES ('7d84743fde6c11e8bfa38cec4b81c244', '灾情路径', 'disaster_route', 'page/base/disasterRoute', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '3', '2018-11-02 14:56:47');
INSERT INTO `menu` VALUES ('7de8a3fbcaa911e89d3e8cec4b81c244', '系统管理', 'system-manager', '', 'navigation', 'layui-icon layui-icon-app', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '5', '2018-10-19 12:57:53');
INSERT INTO `menu` VALUES ('7debcc1dde7f11e8bfa38cec4b81c244', '应急避难所', 'FacilityShelter', 'page/base/facilityShelter', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '6', '2018-11-02 17:12:48');
INSERT INTO `menu` VALUES ('7f720469caac11e89d3e8cec4b81c244', '机构管理', 'organization', 'page/sys/organization', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-10-30 20:25:33');
INSERT INTO `menu` VALUES ('878d87cbe1bb11e8bfa38cec4b81c244', '商场', 'unitMarket', 'page/base/unitMarket', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '23', '2018-11-06 19:59:56');
INSERT INTO `menu` VALUES ('8cada440caac11e89d3e8cec4b81c244', '渠道管理', 'channel', 'page/sys/channel', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '3', '2018-10-08 11:44:45');
INSERT INTO `menu` VALUES ('8f2c7698de5211e8bfa38cec4b81c244', '服务产品', 'server-product', 'page/ueditor/serverProduct', 'dcaa1b90db2b11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '35', '2018-11-02 11:51:10');
INSERT INTO `menu` VALUES ('9bf4f0c3e1bb11e8bfa38cec4b81c244', '医院', 'unitHospital', 'page/base/unitHospital', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '22', '2018-11-06 20:00:30');
INSERT INTO `menu` VALUES ('9c849a33e19711e8bfa38cec4b81c244', '农业园区', 'unitAgriculturPark', 'page/base/unitAgriculturPark', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '16', '2018-11-06 15:42:49');
INSERT INTO `menu` VALUES ('a0f0a45bcaad11e89d3e8cec4b81c244', '一键发布', 'send', 'page/message/send', '454c416fcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-10-08 11:52:29');
INSERT INTO `menu` VALUES ('a1cd5459e59a11e88da08cec4b81c244', '防御作战图', 'gismap', '', 'navigation', 'layui-icon layui-icon-home', '1', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '9', '2018-11-11 18:15:52');
INSERT INTO `menu` VALUES ('a1d864dbcaac11e89d3e8cec4b81c244', '灾种管理', 'disaster', 'page/sys/disaster', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '4', '2018-10-08 11:45:21');
INSERT INTO `menu` VALUES ('a4d20cccde7f11e8bfa38cec4b81c244', '应急物资', 'FacilitySupply', 'page/base/facilitySupply', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '7', '2018-11-02 17:13:53');
INSERT INTO `menu` VALUES ('ab53d84acaad11e89d3e8cec4b81c244', '信息列表', 'list', 'page/message/list', '454c416fcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '2', '2018-10-08 11:52:46');
INSERT INTO `menu` VALUES ('ac71dcfae1bb11e8bfa38cec4b81c244', '水库', 'unitReservoir', 'page/base/unitReservoir', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '25', '2018-11-06 20:00:58');
INSERT INTO `menu` VALUES ('b2b2226ccaac11e89d3e8cec4b81c244', '群组管理', 'group', 'page/sys/group', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '5', '2018-10-08 11:45:49');
INSERT INTO `menu` VALUES ('b2d9ea94db6d11e8bfa38cec4b81c244', '综合数据', 'basiss-manager', '', 'navigation', 'layui-icon layui-icon-chart-screen', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-11-06 19:10:52');
INSERT INTO `menu` VALUES ('b4f83a09e0e211e8bfa38cec4b81c244', '内涝隐患点', 'riskWaterlogging', 'page/base/riskWaterlogging', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '13', '2018-11-05 18:07:57');
INSERT INTO `menu` VALUES ('b945335ce59a11e88da08cec4b81c244', '防御作战图', 'gismap', 'page/gismap/gismap', 'a1cd5459e59a11e88da08cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '1', '2018-11-11 18:15:20');
INSERT INTO `menu` VALUES ('b963ef5bcaad11e89d3e8cec4b81c244', '信息统计', 'total', 'page/message/total', '454c416fcaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '3', '2018-10-08 11:53:10');
INSERT INTO `menu` VALUES ('c609820ce0e211e8bfa38cec4b81c244', '易涝区', 'riskWaterloggingArea', 'page/base/riskWaterloggingArea', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '14', '2018-11-05 18:08:26');
INSERT INTO `menu` VALUES ('c73eb8b6e1a411e8bfa38cec4b81c244', '旅游景区', 'unitAttractions', 'page/base/unitAttractions', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '17', '2018-11-06 17:21:19');
INSERT INTO `menu` VALUES ('c8b0349ccaac11e89d3e8cec4b81c244', '受众管理', 'user', 'page/sys/user', '68aaab7acaa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '6', '2018-10-08 11:46:26');
INSERT INTO `menu` VALUES ('ccf961facaad11e89d3e8cec4b81c244', '预警编辑', 'edit', 'page/warn/edit', '32362c53caa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '1', '2018-10-08 11:53:43');
INSERT INTO `menu` VALUES ('ceac52b2e0a411e8bfa38cec4b81c244', '告警阈值', 'alarm-threshold', 'page/sys/alarmThreshold', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '36', '2018-11-05 10:44:52');
INSERT INTO `menu` VALUES ('d13eede6de8511e8bfa38cec4b81c244', '洼地', 'riskDepression', 'page/base/riskDepression', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '15', '2018-11-05 13:21:56');
INSERT INTO `menu` VALUES ('d91c35fce0ba11e8bfa38cec4b81c244', '地质灾害隐患点', 'riskGeologic', 'page/base/riskGeologic', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '8', '2018-11-05 13:22:38');
INSERT INTO `menu` VALUES ('dcaa1b90db2b11e8bfa38cec4b81c244', '服务产品', 'ueidtor', '', 'navigation', 'layui-icon layui-icon-template-1', '1', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '15', '2018-10-30 18:17:54');
INSERT INTO `menu` VALUES ('df8d74bbcaad11e89d3e8cec4b81c244', '预警审核', 'verify', 'page/warn/verify', '32362c53caa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '3', '2018-10-08 11:54:14');
INSERT INTO `menu` VALUES ('eea48a07caad11e89d3e8cec4b81c244', '预警签发', 'issue', 'page/warn/issue', '32362c53caa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '3', '2018-10-08 11:54:39');
INSERT INTO `menu` VALUES ('eeafb87ee23c11e8bfa38cec4b81c244', '高速公路', 'unitHighway', 'page/base/unitHighway', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '21', '2018-11-07 11:26:14');
INSERT INTO `menu` VALUES ('efa095a2e0ba11e8bfa38cec4b81c244', '中小河流洪水', 'riskFlood', 'page/base/riskFlood', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '9', '2018-11-05 13:23:16');
INSERT INTO `menu` VALUES ('fc1477becaad11e89d3e8cec4b81c244', '应急办签发', 'emergency', 'page/warn/emergency', '32362c53caa911e89d3e8cec4b81c244', '', '2', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '4', '2018-10-08 11:55:02');
INSERT INTO `menu` VALUES ('fdc0d1d8e23c11e8bfa38cec4b81c244', '提防', 'unitDike', 'page/base/unitDike', '2bca3896de6c11e8bfa38cec4b81c244', '', '2', '760ac371dc3e11e8bfa38cec4b81c244', '7ade8db3dc3f11e8bfa38cec4b81c244', '20', '2018-11-07 11:26:39');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `organization_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `area_name` varchar(50) DEFAULT NULL COMMENT '地区名称',
  `title` varchar(255) DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '0：短期预报；1：中期预报；2：长期预报；3：气象专题专报；4：重大气象专题专报',
  `template` int(1) DEFAULT NULL COMMENT '微信模板类型：1：气象灾害预警提醒；2：服务提醒通知；3：突发事件预警提醒；',
  `send_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一键消息发布表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for message_callback_child
-- ----------------------------
DROP TABLE IF EXISTS `message_callback_child`;
CREATE TABLE `message_callback_child` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `message_id` varchar(64) DEFAULT NULL COMMENT '消息ID（预警主表ID、一键发布主表ID）',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `code` varchar(50) DEFAULT NULL COMMENT '受众编码',
  `status` int(1) DEFAULT NULL COMMENT '数据状态：0：成功；1：失败',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分发平台返回状态子表数据';

-- ----------------------------
-- Records of message_callback_child
-- ----------------------------

-- ----------------------------
-- Table structure for message_callback_main
-- ----------------------------
DROP TABLE IF EXISTS `message_callback_main`;
CREATE TABLE `message_callback_main` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `message_id` varchar(64) DEFAULT NULL COMMENT '消息ID（预警主表ID，一件发布主表ID）',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `total` int(11) DEFAULT NULL COMMENT '总数',
  `success` int(11) DEFAULT NULL COMMENT '成功数',
  `fail` int(11) DEFAULT NULL COMMENT '失败数',
  `work` varchar(2000) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分发平台返回状态主表数据';

-- ----------------------------
-- Records of message_callback_main
-- ----------------------------

-- ----------------------------
-- Table structure for message_content
-- ----------------------------
DROP TABLE IF EXISTS `message_content`;
CREATE TABLE `message_content` (
  `id` varchar(64) NOT NULL,
  `message_id` varchar(64) DEFAULT NULL COMMENT '一键发布主表ID',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  `content` varchar(2000) DEFAULT NULL COMMENT '消息内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一键发布消息内容表';

-- ----------------------------
-- Records of message_content
-- ----------------------------

-- ----------------------------
-- Table structure for message_file
-- ----------------------------
DROP TABLE IF EXISTS `message_file`;
CREATE TABLE `message_file` (
  `id` varchar(64) NOT NULL COMMENT '预警上传文件ID',
  `message_id` varchar(64) DEFAULT NULL COMMENT '预警编辑主表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `size` varchar(50) DEFAULT NULL COMMENT '文件大小',
  `url` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一键发布上传文件表';

-- ----------------------------
-- Records of message_file
-- ----------------------------

-- ----------------------------
-- Table structure for message_user
-- ----------------------------
DROP TABLE IF EXISTS `message_user`;
CREATE TABLE `message_user` (
  `id` varchar(64) NOT NULL COMMENT '一键发布受众ID',
  `message_id` varchar(64) DEFAULT NULL COMMENT '一键发布主表ID',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  `user_group_id` varchar(64) DEFAULT NULL COMMENT '群组、受众ID',
  `user_group_name` varchar(50) DEFAULT NULL COMMENT '群组、受众名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一键发布受众表';

-- ----------------------------
-- Records of message_user
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` varchar(64) NOT NULL COMMENT '机构id',
  `organization_name` varchar(50) NOT NULL COMMENT '机构名称',
  `code` varchar(14) DEFAULT NULL COMMENT '机构编码',
  `p_id` varchar(64) DEFAULT NULL COMMENT '机构父类id',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区id',
  `type` smallint(1) DEFAULT '0' COMMENT '机构类型:0:发布中心；1：预案单位；2：应急办',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('233d9c8c987911e8a5ed8cec4b81c244', '华池县应急办', '62102300000000', '', '52db5b81970911e8a5ed8cec4b81c244', '2', '2018-08-16 15:14:40');
INSERT INTO `organization` VALUES ('312ddd73987811e8a5ed8cec4b81c244', '华池县发布中心', '62102300000000', '', '52db5b81970911e8a5ed8cec4b81c244', '0', '2018-08-16 15:14:43');
INSERT INTO `organization` VALUES ('caa42816a6a611e8bee28cec4b81c244', '甘肃省气象局', '62000000000000', '', '9aaef2f0970011e8a5ed8cec4b81c244', '0', '2018-08-23 15:33:01');
INSERT INTO `organization` VALUES ('d862501bb1a011e8bee28cec4b81c244', '庆阳市发布中心', '62200000000000', '', '4c55dcdc970511e8a5ed8cec4b81c244', '0', '2018-09-06 14:47:31');
INSERT INTO `organization` VALUES ('ec936abf987811e8a5ed8cec4b81c244', '华池县气象局', '62102300000000', '', '52db5b81970911e8a5ed8cec4b81c244', '1', '2018-08-29 18:39:36');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(64) NOT NULL COMMENT '权限主键ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:detail',
  `type` varchar(20) DEFAULT NULL COMMENT '资源类型，[menu、button]',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径.',
  `status` smallint(1) DEFAULT NULL COMMENT '是否可用：0：不可用；1：可用；如果不可用将不会添加给用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('3464441fc3ce11e89d3e8cec4b81c244', '菜单分配', 'menu', 'button', null, '1', '2018-09-29 17:58:13');
INSERT INTO `permission` VALUES ('38b4d465c3ce11e89d3e8cec4b81c244', '权限分配', 'permission', 'button', null, '1', '2018-09-29 17:58:21');
INSERT INTO `permission` VALUES ('3b5ee72ec3ce11e89d3e8cec4b81c244', '角色分配', 'role', 'button', null, '1', '2018-09-29 17:58:25');
INSERT INTO `permission` VALUES ('3e74ecbbc3ce11e89d3e8cec4b81c244', '下载操作', 'download', 'button', null, '1', '2018-09-29 17:58:30');
INSERT INTO `permission` VALUES ('41bde292c3ce11e89d3e8cec4b81c244', '上传操作', 'upload', 'button', null, '1', '2018-09-29 17:58:36');
INSERT INTO `permission` VALUES ('44a2eee4c3ce11e89d3e8cec4b81c244', '导出操作', 'export', 'button', null, '1', '2018-09-29 17:58:41');
INSERT INTO `permission` VALUES ('46c80075c3ce11e89d3e8cec4b81c244', '导入操作', 'import', 'button', null, '1', '2018-09-29 17:58:44');
INSERT INTO `permission` VALUES ('491bc1b0c3ce11e89d3e8cec4b81c244', '批量删除', 'batch', 'button', null, '1', '2018-09-29 17:58:48');
INSERT INTO `permission` VALUES ('4b327181c3ce11e89d3e8cec4b81c244', '删除操作', 'delete', 'button', null, '1', '2018-09-29 17:58:52');
INSERT INTO `permission` VALUES ('4d3f321dc3ce11e89d3e8cec4b81c244', '修改操作', 'update', 'button', null, '1', '2018-09-29 17:58:55');
INSERT INTO `permission` VALUES ('51ffa007c3ce11e89d3e8cec4b81c244', '添加操作', 'insert', 'button', null, '1', '2018-09-29 17:59:03');
INSERT INTO `permission` VALUES ('7d61054ccc6111e89d3e8cec4b81c244', '录入操作', 'edit', 'button', null, '1', '2018-10-10 15:52:26');
INSERT INTO `permission` VALUES ('816d533fcc6111e89d3e8cec4b81c244', '审核操作', 'verify', 'button', null, '1', '2018-10-10 15:52:32');
INSERT INTO `permission` VALUES ('85b57b3ecc6111e89d3e8cec4b81c244', '签发操作', 'issue', 'button', null, '1', '2018-10-10 15:52:40');
INSERT INTO `permission` VALUES ('896773f3cc6111e89d3e8cec4b81c244', '应急签发', 'emergency', 'button', null, '1', '2018-10-10 15:52:46');
INSERT INTO `permission` VALUES ('8c2a0f2bcc6111e89d3e8cec4b81c244', '发布操作', 'publish', 'button', null, '1', '2018-10-10 15:52:50');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `role` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `status` smallint(1) DEFAULT NULL COMMENT '是否可用：0：不可用；1：可用；如果不可用将不会添加给用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('417db7bcd68f11e89a818cec4b81c244', '应急办签发人员', '只有应急办适合此角色', '1', '2018-10-23 14:45:23');
INSERT INTO `role` VALUES ('6281c765d68f11e89a818cec4b81c244', '发布中心人员', '发布中心人员拥有此角色', '1', '2018-10-23 14:46:19');
INSERT INTO `role` VALUES ('894d02f1d68b11e89a818cec4b81c244', '管理员', '', '1', '2018-10-23 14:18:46');
INSERT INTO `role` VALUES ('8f9d2a0ed68411e89a818cec4b81c244', '超级管理员', '超级管理员', '1', '2018-10-23 13:28:50');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(64) NOT NULL COMMENT '角色对应菜单ID',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(64) DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色分配菜单';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('72d8f9b4e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '10c35f0fcaa911e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df5f87e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '367a914ccaae11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df60c0e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '32362c53caa911e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df60f6e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'ccf961facaad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df611de63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'df8d74bbcaad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6145e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'eea48a07caad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df616de63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '1e2ecf50cdc511e8810a8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6195e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '454c416fcaa911e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df61b7e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'a0f0a45bcaad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df61dfe63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'ab53d84acaad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6204e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'b963ef5bcaad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6226e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '68aaab7acaa911e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df624ee63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '6f014044caac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6273e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '7f720469caac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6298e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '8cada440caac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df62bde63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'a1d864dbcaac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df62dfe63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'b2b2226ccaac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6304e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', 'c8b0349ccaac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df632ce63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '192f30f0caad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df634ee63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '2527b2f1caad11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6373e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '7de8a3fbcaa911e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('72df6398e63a11e88da08cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '0a79d9e2caac11e89d3e8cec4b81c244', '2018-11-12 13:18:39');
INSERT INTO `role_menu` VALUES ('7a442edce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '10c35f0fcaa911e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a44450ae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '367a914ccaae11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444570e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'b2d9ea94db6d11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a4445a0e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '2d8994d7db6e11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a4445c5e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '32362c53caa911e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a4445f3e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'ccf961facaad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444615e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'df8d74bbcaad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a44463ae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'eea48a07caad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444659e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'fc1477becaad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a4449b4e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '0c006460caae11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a4449e7e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '1e2ecf50cdc511e8810a8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444a12e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '454c416fcaa911e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444a37e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'a0f0a45bcaad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444a5fe63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'ab53d84acaad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444a81e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'b963ef5bcaad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444aa3e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '68aaab7acaa911e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444ac5e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '6f014044caac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444ae7e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '7f720469caac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444b0ae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '8cada440caac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444b29e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'a1d864dbcaac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444b4be63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'b2b2226ccaac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444b6de63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'c8b0349ccaac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444b8ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '192f30f0caad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444bace63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '2527b2f1caad11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444bcbe63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '7de8a3fbcaa911e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444beae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '51a018c1caaa11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444c0ae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '0a79d9e2caac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444c2ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '1adcb306caac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444c4ee63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '2afe6cb2caac11e89d3e8cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444c6de63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '2bca3896de6c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444c8ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '4dff2eebde6c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444cace63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '669274cdde6c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444ccbe63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '7d84743fde6c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444cede63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '46fdfc89de7f11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444d0ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '63e1093ede7f11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444d2ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '7debcc1dde7f11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444d4be63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'a4d20cccde7f11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444d6ae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'd91c35fce0ba11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444d8ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'efa095a2e0ba11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444daee63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '04eb0a93e0bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444dcee63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '1ec96cf8e0bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444dede63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'b4f83a09e0e211e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444e0ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '447ae6eee0bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444e2ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'c609820ce0e211e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444e4be63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'd13eede6de8511e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444e6ae63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '9c849a33e19711e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444e8ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'c73eb8b6e1a411e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444eace63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '1f7696e1e23d11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444ecbe63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '11312583e23d11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444ef0e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'fdc0d1d8e23c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444f0fe63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'eeafb87ee23c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444f2ee63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '9bf4f0c3e1bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444f4ee63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '878d87cbe1bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444f70e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '763f3ffbe1bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444f8ce63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'ac71dcfae1bb11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444faee63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '093b731be1a511e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444fd3e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '1b609b98e1a511e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a444ff3e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '311a63bae1a511e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a445012e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'ceac52b2e0a411e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a445031e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'a1cd5459e59a11e88da08cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a445051e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'b945335ce59a11e88da08cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a445070e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', 'dcaa1b90db2b11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a44508fe63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '0bf7b0a6db2c11e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a4450f0e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '8f2c7698de5211e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a445112e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '04ff91dae16311e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('7a445137e63a11e88da08cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '264d5387e16311e8bfa38cec4b81c244', '2018-11-12 13:18:52');
INSERT INTO `role_menu` VALUES ('c9468a6ae63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '10c35f0fcaa911e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946a8a0e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '367a914ccaae11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946abcbe63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '32362c53caa911e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ac12e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '0c006460caae11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ac42e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '1e2ecf50cdc511e8810a8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ac6de63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '454c416fcaa911e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ac98e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', 'a0f0a45bcaad11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946acc2e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', 'ab53d84acaad11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ace7e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', 'b963ef5bcaad11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ad0ce63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '68aaab7acaa911e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ad31e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '6f014044caac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ad53e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '7f720469caac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ad78e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '8cada440caac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ad9be63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', 'a1d864dbcaac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946adbde63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', 'b2b2226ccaac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946addfe63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', 'c8b0349ccaac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ae07e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '192f30f0caad11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ae29e63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '2527b2f1caad11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ae4be63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '7de8a3fbcaa911e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('c946ae6de63911e88da08cec4b81c244', '6281c765d68f11e89a818cec4b81c244', '0a79d9e2caac11e89d3e8cec4b81c244', '2018-11-12 13:13:55');
INSERT INTO `role_menu` VALUES ('cc1675dfe63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '10c35f0fcaa911e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc168ecbe63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '367a914ccaae11e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc1691f0e63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '32362c53caa911e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc169231e63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', 'fc1477becaad11e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc16925fe63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '1e2ecf50cdc511e8810a8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc169287e63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '454c416fcaa911e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc1692b4e63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', 'a0f0a45bcaad11e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc1692d9e63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', 'ab53d84acaad11e89d3e8cec4b81c244', '2018-11-12 13:13:59');
INSERT INTO `role_menu` VALUES ('cc1692fbe63911e88da08cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', 'b963ef5bcaad11e89d3e8cec4b81c244', '2018-11-12 13:13:59');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(64) NOT NULL COMMENT '角色权限ID',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色ID',
  `permission_id` varchar(64) DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('46fe8da1d68f11e89a818cec4b81c244', '417db7bcd68f11e89a818cec4b81c244', '896773f3cc6111e89d3e8cec4b81c244', '2018-10-23 14:45:33');
INSERT INTO `role_permission` VALUES ('ac0cafbed68b11e89a818cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '51ffa007c3ce11e89d3e8cec4b81c244', '2018-10-23 14:19:44');
INSERT INTO `role_permission` VALUES ('ac0cca2dd68b11e89a818cec4b81c244', '894d02f1d68b11e89a818cec4b81c244', '44a2eee4c3ce11e89d3e8cec4b81c244', '2018-10-23 14:19:44');
INSERT INTO `role_permission` VALUES ('edbb4d64d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '8c2a0f2bcc6111e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb629ad68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '896773f3cc6111e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb6320d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '85b57b3ecc6111e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb634ad68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '816d533fcc6111e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb6378d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '7d61054ccc6111e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb639dd68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '51ffa007c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb63bfd68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '4d3f321dc3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb63e4d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '4b327181c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb6409d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '491bc1b0c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb642bd68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '46c80075c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb644ad68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '44a2eee4c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb646dd68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '41bde292c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb648fd68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '3e74ecbbc3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb6537d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '3b5ee72ec3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb6559d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '38b4d465c3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');
INSERT INTO `role_permission` VALUES ('edbb6578d68511e89a818cec4b81c244', '8f9d2a0ed68411e89a818cec4b81c244', '3464441fc3ce11e89d3e8cec4b81c244', '2018-10-23 13:38:37');

-- ----------------------------
-- Table structure for server_product
-- ----------------------------
DROP TABLE IF EXISTS `server_product`;
CREATE TABLE `server_product` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `type` smallint(2) DEFAULT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '名称',
  `path` varchar(255) DEFAULT NULL COMMENT 'word所在路径',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of server_product
-- ----------------------------

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `id` varchar(64) NOT NULL COMMENT '策略ID',
  `name` varchar(100) DEFAULT NULL COMMENT '策略名称',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区id',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `disaster_id` varchar(60) DEFAULT NULL COMMENT '灾种ID',
  `disaster_name` varchar(50) DEFAULT NULL COMMENT '灾种名称',
  `disaster_color` smallint(1) DEFAULT NULL COMMENT '灾种颜色',
  `disaster_level` smallint(1) DEFAULT NULL COMMENT '灾种级别',
  `flow` varchar(50) DEFAULT NULL COMMENT '发布流程：例如（0,1,2,3）0：录入；1：审核；2：签发；3：应急办签发；4：发布',
  `channel_id` varchar(500) DEFAULT NULL COMMENT '配置渠道：例如（0,1,2,3）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='策略配置';

-- ----------------------------
-- Records of strategy
-- ----------------------------
INSERT INTO `strategy` VALUES ('2f62b01593d611e885e268f7285847c8', '暴雨[黄色][Ⅲ级/较大]', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '5f320cd98e6911e885e268f7285847c8', '暴雨', '2', '2', '0,4', '8d2448858b1511e8b73168f7285847c8,ddc553428b1511e8b73168f7285847c8', '2018-10-30 18:07:33');
INSERT INTO `strategy` VALUES ('92fe9c6e93a611e885e268f7285847c8', '暴雨[蓝色][Ⅳ级/一般]', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '576b16f78e6911e885e268f7285847c8', '暴雨', '3', '3', '0,2,4', '8d2448858b1511e8b73168f7285847c8,ddc553428b1511e8b73168f7285847c8', '2018-10-30 18:07:39');
INSERT INTO `strategy` VALUES ('9c5c95d893a611e885e268f7285847c8', '暴雨[橙色][Ⅱ级/重大]', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '6bd184e08e6911e885e268f7285847c8', '暴雨', '1', '1', '0,1,3,4', '8d2448858b1511e8b73168f7285847c8,ddc553428b1511e8b73168f7285847c8', '2018-10-30 18:07:23');
INSERT INTO `strategy` VALUES ('ae1f0c5593a611e885e268f7285847c8', '暴雨[红色][Ⅰ级/特别重大]', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '71cb388d8e6911e885e268f7285847c8', '暴雨', '0', '0', '0,1,2,3,4', '8d2448858b1511e8b73168f7285847c8,ddc553428b1511e8b73168f7285847c8', '2018-10-30 18:07:09');
INSERT INTO `strategy` VALUES ('b889e362a74511e8bee28cec4b81c244', '大雾[橙色][Ⅱ级/重大]', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '9eb1d1a5a74411e8bee28cec4b81c244', '大雾', '1', '1', '0,4', '8d2448858b1511e8b73168f7285847c8,ddc553428b1511e8b73168f7285847c8', '2018-10-30 18:08:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL COMMENT '用户id',
  `name` varchar(50) NOT NULL COMMENT '用户名称',
  `code` varchar(100) DEFAULT NULL COMMENT '终端号码',
  `user_group_id` varchar(64) NOT NULL COMMENT '所属用户组id',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `longitude` double DEFAULT '0' COMMENT '经度',
  `latitude` double DEFAULT '0' COMMENT '纬度',
  `altitude` double DEFAULT '0' COMMENT '高度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `job_id` varchar(64) DEFAULT NULL COMMENT '职位信息',
  `type` int(11) DEFAULT NULL COMMENT '群组类型：1：责任人群组；2：基层防御群组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('167210008f0511e885e268f7285847c8', '李卫东电信', '17343031269', '442661118fe411e885e268f7285847c8', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '23.34', '234.23', '23.23', '2018-08-29 18:36:45', null, null);
INSERT INTO `user` VALUES ('18cfe8138fe211e885e268f7285847c8', '明少', '17600906598', '5d7502ffa50d11e8bee28cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '23.32', '32.32', '0', '2018-12-05 10:05:40', '486e5e69f41c4c59ba3dff93583bbc3f', '1');
INSERT INTO `user` VALUES ('1b422a92c1684d5d99c66f682a92a0e6', '李晓伟', '471046266@qq.com', '3d1a39bbe0df11e8bfa38cec4b81c244', '059d557bdd8511e8bfa38cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '123.45', '38.46', '0', '2018-11-06 15:06:24', '6867fc4da2684787bdb15969686e024b', null);
INSERT INTO `user` VALUES ('4f50751ed8f448e3b4594769c32ccf61', '李晓伟', '13520070261', '7914e95cf83211e8b6238cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '0', '0', '0', '2018-12-05 10:08:00', 'c7b21ee28a14400ab00e65c59140a283', '2');
INSERT INTO `user` VALUES ('50888707bbde11e885958cec4b81c244', 'dear', '18510811267', '442661118fe411e885e268f7285847c8', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '234', '234', '23', '2018-09-19 15:33:21', null, null);
INSERT INTO `user` VALUES ('51eedc9d8fe411e885e268f7285847c8', '大喇叭1', '343464534asdfa', 'f95e8bef8f2e11e885e268f7285847c8', 'a142bd608b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '0', '0', '0', '2018-08-17 21:11:28', null, null);
INSERT INTO `user` VALUES ('57489da6a46911e8bee28cec4b81c244', '唐勇勇', '18001060205', '442661118fe411e885e268f7285847c8', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '234.234', '23.234', '234.23', '2018-09-20 15:09:56', null, null);
INSERT INTO `user` VALUES ('62549e2e8fe611e885e268f7285847c8', '网站123', '124243543433231', 'eb761d21970c11e8a5ed8cec4b81c244', 'b94d47008b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '0', '0', '0', '2018-08-17 21:11:24', null, null);
INSERT INTO `user` VALUES ('684035c8bbde11e885958cec4b81c244', '彭威', '18201563075', '442661118fe411e885e268f7285847c8', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '234', '234', '234', '2018-09-19 15:34:01', null, null);
INSERT INTO `user` VALUES ('73d79240a50d11e8bee28cec4b81c244', '姜新灿', '18501377889', '5d7502ffa50d11e8bee28cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '123.43', '43.34', '12.43', '2018-08-29 18:34:51', null, null);
INSERT INTO `user` VALUES ('75c5f7cde0df11e8bfa38cec4b81c244', '姜新灿', 'alittlexincan@163.com', '3d1a39bbe0df11e8bfa38cec4b81c244', '059d557bdd8511e8bfa38cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '12.34', '123.34', '0', '2018-11-05 17:44:43', null, null);
INSERT INTO `user` VALUES ('a04c7536ab7711e8bee28cec4b81c244', '李卫东移动', '15210679594', '5d7502ffa50d11e8bee28cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '234.5', '23.546', '167.34', '2018-08-29 18:37:38', null, null);
INSERT INTO `user` VALUES ('ab437e3bab7811e8bee28cec4b81c244', '李晓蒙', '13426350482', '442661118fe411e885e268f7285847c8', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '23.34', '23.54', '56.65', '2018-08-29 18:45:06', null, null);
INSERT INTO `user` VALUES ('f218386cc3b311e89d3e8cec4b81c244', '李晓伟', '17600906598', '5d7502ffa50d11e8bee28cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '234.2', '21.23', '324.34', '2018-09-29 14:50:15', null, null);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` varchar(64) NOT NULL COMMENT '群组id',
  `name` varchar(50) NOT NULL COMMENT '群组名称',
  `p_id` varchar(64) DEFAULT NULL COMMENT '父类群组id',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区id',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '所属渠道Id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `type` int(11) DEFAULT NULL COMMENT '群组类型：1：责任人群组；2：基层防御群组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组表';

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('3d1a39bbe0df11e8bfa38cec4b81c244', '气象局邮件群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '059d557bdd8511e8bfa38cec4b81c244', '2018-11-05 17:43:08', null);
INSERT INTO `user_group` VALUES ('41ce7390b1a211e8bee28cec4b81c244', '庆阳市短信渠道', null, '4c55dcdc970511e8a5ed8cec4b81c244', 'd862501bb1a011e8bee28cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '2018-09-06 14:57:37', null);
INSERT INTO `user_group` VALUES ('442661118fe411e885e268f7285847c8', '气象局短信群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '2018-08-29 18:32:28', null);
INSERT INTO `user_group` VALUES ('4a17adb1b1a211e8bee28cec4b81c244', '庆阳市声讯渠道', null, '4c55dcdc970511e8a5ed8cec4b81c244', 'd862501bb1a011e8bee28cec4b81c244', '93f5c4fc8b1511e8b73168f7285847c8', '2018-09-06 14:57:51', null);
INSERT INTO `user_group` VALUES ('5d7502ffa50d11e8bee28cec4b81c244', '气象局短信测试群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '2018-08-29 18:32:46', null);
INSERT INTO `user_group` VALUES ('625c7b0e970c11e8a5ed8cec4b81c244', '气象局显示屏群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'aa2ebc348b1511e8b73168f7285847c8', '2018-08-29 18:33:10', null);
INSERT INTO `user_group` VALUES ('70f8843a970c11e8a5ed8cec4b81c244', '气象局APP群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '9b7a04038b1511e8b73168f7285847c8', '2018-08-29 18:33:15', null);
INSERT INTO `user_group` VALUES ('7914e95cf83211e8b6238cec4b81c244', '短信测试', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '8d2448858b1511e8b73168f7285847c8', '2018-12-05 10:07:07', '2');
INSERT INTO `user_group` VALUES ('7977fb22970c11e8a5ed8cec4b81c244', '气象局微博群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'd8730fa68b1511e8b73168f7285847c8', '2018-08-29 18:33:29', null);
INSERT INTO `user_group` VALUES ('82b310a9970c11e8a5ed8cec4b81c244', '气象局微信群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'ddc553428b1511e8b73168f7285847c8', '2018-08-29 18:33:19', null);
INSERT INTO `user_group` VALUES ('8bbdb3f0970c11e8a5ed8cec4b81c244', '气象局北斗群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'e53e88dd8b1511e8b73168f7285847c8', '2018-08-29 18:33:24', null);
INSERT INTO `user_group` VALUES ('99632b01970c11e8a5ed8cec4b81c244', '气象局电视群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'f1d29c338b1511e8b73168f7285847c8', '2018-08-29 18:33:35', null);
INSERT INTO `user_group` VALUES ('a2cb45af9a0a11e8a90d8cec4b81c244', '气象局广播群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'f7ce05fb8b1511e8b73168f7285847c8', '2018-08-29 18:33:50', null);
INSERT INTO `user_group` VALUES ('b7c191d39a0a11e8a90d8cec4b81c244', '气象局传真群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'fddde3b78b1511e8b73168f7285847c8', '2018-08-29 18:33:55', null);
INSERT INTO `user_group` VALUES ('d478cce78f2e11e885e268f7285847c8', '气象局声讯群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '93f5c4fc8b1511e8b73168f7285847c8', '2018-08-29 18:32:55', null);
INSERT INTO `user_group` VALUES ('eb761d21970c11e8a5ed8cec4b81c244', '气象局网站群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'b94d47008b1511e8b73168f7285847c8', '2018-08-29 18:33:43', null);
INSERT INTO `user_group` VALUES ('f95e8bef8f2e11e885e268f7285847c8', '气象局大喇叭群组', null, '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', 'a142bd608b1511e8b73168f7285847c8', '2018-08-29 18:33:02', null);

-- ----------------------------
-- Table structure for user_job
-- ----------------------------
DROP TABLE IF EXISTS `user_job`;
CREATE TABLE `user_job` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `job` varchar(255) DEFAULT NULL COMMENT '职务',
  `duties` varchar(255) DEFAULT NULL COMMENT '职责',
  `leader` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `user_group_id` varchar(64) DEFAULT NULL COMMENT '群组ID',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_job
-- ----------------------------
INSERT INTO `user_job` VALUES ('3a19ec2e1d114b0d9ec49eea33a60ac4', '明少', 'java软件开发工程师', '开发软件', '无', '21', '0', '中关村南大街', '2018-11-15 09:18:11', '18cfe8138fe211e885e268f7285847c8', null, null, null, null);
INSERT INTO `user_job` VALUES ('6867fc4da2684787bdb15969686e024b', '李晓伟', null, null, null, null, '1', null, '2018-11-06 15:06:24', '1b422a92c1684d5d99c66f682a92a0e6', null, null, null, null);

-- ----------------------------
-- Table structure for warn
-- ----------------------------
DROP TABLE IF EXISTS `warn`;
CREATE TABLE `warn` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '预警配置',
  `area_id` varchar(64) NOT NULL COMMENT '地区id',
  `organization_id` varchar(64) NOT NULL COMMENT '机构id',
  `disaster_id` varchar(64) NOT NULL COMMENT '灾种id',
  `disaster_name` varchar(50) NOT NULL COMMENT '灾种名称',
  `disaster_color` smallint(1) NOT NULL DEFAULT '0' COMMENT '灾种颜色：0：红色；1：橙色；2：黄色；3：蓝色',
  `disaster_level` smallint(1) NOT NULL DEFAULT '0' COMMENT '灾种级别：0：Ⅰ级/特别重大；1：Ⅱ级/重大；2：Ⅲ级/较大；3：Ⅳ级/一般',
  `content` varchar(2000) DEFAULT NULL COMMENT '预警内容',
  `measure` varchar(2000) DEFAULT NULL COMMENT '政府应对措施',
  `instruction` varchar(2000) DEFAULT NULL COMMENT '防御指南',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警配置';

-- ----------------------------
-- Records of warn
-- ----------------------------
INSERT INTO `warn` VALUES ('881b1ff1987a11e8a5ed8cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '5f320cd98e6911e885e268f7285847c8', '暴雨', '2', '2', '测试：暴雨黄色预警预计今天到明天有雨', '1：如果需要出行请使用滴滴搭船\n2：如果需要出行请使用滴滴搭船', '1：尽量不要出门', '2018-08-13 20:41:26');
INSERT INTO `warn` VALUES ('933e130ea74711e8bee28cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '9eb1d1a5a74411e8bee28cec4b81c244', '大雾', '1', '1', '我县已经出现能见度小于200米的大雾天气，预计今天上午我县部分地方仍将出现大雾天气并持续，请注意交通安全', '1：有关部门和单位按照职责做好防雾准备工作；\n2：高速公路等单位加强交通管理，保障安全；\n3：驾驶人员注意雾的变化，小心驾驶；\n4：户外活动注意安全；', '1：驾驶人员注意雾的变化，小心驾驶；\n2：户外活动注意安全；', '2018-08-24 15:41:30');
INSERT INTO `warn` VALUES ('b115c1b3987a11e8a5ed8cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '6bd184e08e6911e885e268f7285847c8', '暴雨', '1', '1', '测试：发布暴雨橙色预警', '1：如果需要出行请使用滴滴搭船（橙色）', '1：如果需要出行请使用滴滴搭船（橙色）', '2018-08-13 20:41:22');
INSERT INTO `warn` VALUES ('c8c4b8fb987a11e8a5ed8cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '71cb388d8e6911e885e268f7285847c8', '暴雨', '0', '0', '测试：发布暴雨红色预警（红色）', '1：请使用滴滴搭船', '1：请使用滴滴搭船', '2018-08-13 20:41:14');
INSERT INTO `warn` VALUES ('e13310ce946c11e8a49f8cec4b81c244', '52db5b81970911e8a5ed8cec4b81c244', 'ec936abf987811e8a5ed8cec4b81c244', '576b16f78e6911e885e268f7285847c8', '暴雨', '3', '3', '测试暴雨蓝色预警', '1：测试暴雨蓝色预警\n2：测试暴雨蓝色预警', '1：测试暴雨蓝色预警', '2018-08-24 11:29:56');

-- ----------------------------
-- Table structure for warn_callback_child
-- ----------------------------
DROP TABLE IF EXISTS `warn_callback_child`;
CREATE TABLE `warn_callback_child` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `message_id` varchar(64) DEFAULT NULL COMMENT '消息ID（预警主表ID、一键发布主表ID）',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `code` varchar(50) DEFAULT NULL COMMENT '受众编码',
  `status` int(1) DEFAULT NULL COMMENT '数据状态：0：成功；1：失败',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分发平台返回状态子表数据';

-- ----------------------------
-- Records of warn_callback_child
-- ----------------------------

-- ----------------------------
-- Table structure for warn_callback_main
-- ----------------------------
DROP TABLE IF EXISTS `warn_callback_main`;
CREATE TABLE `warn_callback_main` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `message_id` varchar(64) DEFAULT NULL COMMENT '消息ID（预警主表ID，一件发布主表ID）',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `total` int(11) DEFAULT NULL COMMENT '总数',
  `success` int(11) DEFAULT NULL COMMENT '成功数',
  `fail` int(11) DEFAULT NULL COMMENT '失败数',
  `work` varchar(2000) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分发平台返回状态主表数据';

-- ----------------------------
-- Records of warn_callback_main
-- ----------------------------

-- ----------------------------
-- Table structure for warn_edit
-- ----------------------------
DROP TABLE IF EXISTS `warn_edit`;
CREATE TABLE `warn_edit` (
  `id` varchar(64) NOT NULL COMMENT '预警编辑信息主键ID',
  `title` varchar(100) DEFAULT NULL COMMENT '预警标题',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `disaster_id` varchar(64) DEFAULT NULL COMMENT '灾种ID',
  `disaster_name` varchar(100) DEFAULT NULL COMMENT '灾种名称',
  `disaster_color` smallint(1) DEFAULT NULL COMMENT '灾种颜色：0：红色；1：橙色；2：黄色；3：蓝色',
  `disaster_level` smallint(1) DEFAULT NULL COMMENT '灾种级别：0：Ⅰ级/特别重大；1：Ⅱ级/重大；2：Ⅲ级/较大；3：Ⅳ级/一般',
  `warn_type` varchar(20) DEFAULT NULL COMMENT '预警类型：[Actual（实际）,Exercise（演练）,Test（测试）,Draft（草稿）],目前取值仅使用“Actual”和“Test”，其中 “Test”可用于发布测试预警， Exercise和Draft暂不使用',
  `msg_type` varchar(20) DEFAULT NULL COMMENT '信息类型：[Alert（首次）,Update（更新）,Cancel（解除）,Ack（确认）,Error（错误）]，目前只采用“Alert”“Update”“Cancel”三个枚举值，其余枚举值保留，暂不使用。',
  `scope` varchar(20) DEFAULT NULL COMMENT '发布范围：[Public（公开）,Restricted（限制权限）,Private（特定地址）],固定使用“Public”值，其余两个枚举值保留，暂不使用。',
  `edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '预警编辑时间',
  `forecast_time` datetime DEFAULT NULL COMMENT '预期发生时间',
  `invalid_time` datetime DEFAULT NULL COMMENT '失效时间',
  `record` smallint(1) DEFAULT NULL COMMENT '国突备案：0：否；1：是',
  `measure` varchar(2000) DEFAULT NULL COMMENT '政府应对措施',
  `instruction` varchar(2000) DEFAULT NULL COMMENT '防御指南',
  `flow` varchar(50) DEFAULT NULL COMMENT '总共流程',
  `status` smallint(1) DEFAULT NULL COMMENT '预警状态：0：未发布；1：已发布；2：解除；',
  `send_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警编辑基础信息表';

-- ----------------------------
-- Records of warn_edit
-- ----------------------------

-- ----------------------------
-- Table structure for warn_edit_content
-- ----------------------------
DROP TABLE IF EXISTS `warn_edit_content`;
CREATE TABLE `warn_edit_content` (
  `id` varchar(64) NOT NULL COMMENT '预警内容ID',
  `warn_edit_id` varchar(64) DEFAULT NULL COMMENT '预警编辑主表ID',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  `area_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `content` varchar(2000) DEFAULT NULL COMMENT '预警内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警编辑内容表';

-- ----------------------------
-- Records of warn_edit_content
-- ----------------------------

-- ----------------------------
-- Table structure for warn_edit_file
-- ----------------------------
DROP TABLE IF EXISTS `warn_edit_file`;
CREATE TABLE `warn_edit_file` (
  `id` varchar(64) NOT NULL COMMENT '预警上传文件ID',
  `warn_edit_id` varchar(64) DEFAULT NULL COMMENT '预警编辑主表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `size` varchar(50) DEFAULT NULL COMMENT '文件大小',
  `url` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警编辑上传文件表';

-- ----------------------------
-- Records of warn_edit_file
-- ----------------------------

-- ----------------------------
-- Table structure for warn_edit_flow
-- ----------------------------
DROP TABLE IF EXISTS `warn_edit_flow`;
CREATE TABLE `warn_edit_flow` (
  `id` varchar(64) NOT NULL COMMENT '预警编辑流程ID',
  `warn_edit_id` varchar(64) DEFAULT NULL COMMENT '预警编辑主表ID',
  `flow` smallint(2) DEFAULT NULL COMMENT '0：录入；1：审核；2：签发；3：应急办签发；4：发布；5：保存代发；6：驳回; 7：终止',
  `organization_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `organization_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `employee_id` varchar(64) DEFAULT NULL COMMENT '操作员工ID',
  `employee_name` varchar(50) DEFAULT NULL COMMENT '操作员工名称',
  `advice` varchar(2000) DEFAULT NULL COMMENT '意见建议',
  `is_option` smallint(1) DEFAULT NULL COMMENT '是否操作：0：未操作；1：已操作',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警编辑流程表';

-- ----------------------------
-- Records of warn_edit_flow
-- ----------------------------

-- ----------------------------
-- Table structure for warn_edit_user
-- ----------------------------
DROP TABLE IF EXISTS `warn_edit_user`;
CREATE TABLE `warn_edit_user` (
  `id` varchar(64) NOT NULL COMMENT '预警编辑发布受众ID',
  `warn_edit_id` varchar(64) DEFAULT NULL COMMENT '预警编辑主表ID',
  `channel_id` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  `user_group_id` varchar(64) DEFAULT NULL COMMENT '群组ID',
  `user_group_name` varchar(100) DEFAULT NULL COMMENT '群组名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警编辑发布受众表';

-- ----------------------------
-- Records of warn_edit_user
-- ----------------------------

-- ----------------------------
-- Function structure for getAreaCList
-- ----------------------------
DROP FUNCTION IF EXISTS `getAreaCList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getAreaCList`(rootId VARCHAR(64)) RETURNS varchar(4000) CHARSET utf8
BEGIN 

-- 根据当前id查询所有下级子节点，包含当前节点
-- （向下查询） 
-- 操作方式
-- select * From area where FIND_IN_SET(id, getAreaCList('5c8131f4883e11e8b73168f7285847c8'));
	DECLARE sChildList VARCHAR(4000);
	DECLARE sChildTemp VARCHAR(4000);
	SET sChildTemp =cast(rootId as CHAR);
	WHILE sChildTemp is not null DO
		IF (sChildList is not null) THEN
			SET sChildList = concat(sChildList,',',sChildTemp);
		ELSE
			SET sChildList = concat(sChildTemp);
		END IF;
				SELECT group_concat(id) INTO sChildTemp FROM area where FIND_IN_SET(p_id,sChildTemp)>0;
	END WHILE;
	RETURN sChildList;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getAreaPList
-- ----------------------------
DROP FUNCTION IF EXISTS `getAreaPList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getAreaPList`(rootId VARCHAR(64)) RETURNS varchar(4000) CHARSET utf8
BEGIN


-- 根据当前id查询所有上级父节点，包含当前节点
-- （向上查询）
-- 操作方式
-- select * From area where FIND_IN_SET(id, getAreaPList('5c8131f4883e11e8b73168f7285847c8'));

    DECLARE sTemp VARCHAR(4000);
    DECLARE sTempPar VARCHAR(4000); 
    SET sTemp = ''; 
    SET sTempPar =rootId; 
 
    #循环递归
    WHILE sTempPar is not null DO 
        #判断是否是第一个，不加的话第一个会为空
        IF sTemp != '' THEN
            SET sTemp = concat(sTemp,',',sTempPar);
        ELSE
            SET sTemp = sTempPar;
        END IF;
        SET sTemp = concat(sTemp,',',sTempPar); 
        SELECT group_concat(p_id) INTO sTempPar FROM area where p_id<>id and FIND_IN_SET(id,sTempPar)>0; 
    END WHILE; 
 
RETURN sTemp; 
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getOrgCList
-- ----------------------------
DROP FUNCTION IF EXISTS `getOrgCList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getOrgCList`(rootId VARCHAR(64)) RETURNS varchar(4000) CHARSET utf8
BEGIN 

-- 根据当前id查询所有下级子节点，包含当前节点
-- （向下查询） 
-- 操作方式
-- select * From area where FIND_IN_SET(id, getOrgCList('5c8131f4883e11e8b73168f7285847c8'));
	DECLARE sChildList VARCHAR(4000);
	DECLARE sChildTemp VARCHAR(4000);
	SET sChildTemp =cast(rootId as CHAR);
	WHILE sChildTemp is not null DO
		IF (sChildList is not null) THEN
			SET sChildList = concat(sChildList,',',sChildTemp);
		ELSE
			SET sChildList = concat(sChildTemp);
		END IF;
				SELECT group_concat(id) INTO sChildTemp FROM organization where FIND_IN_SET(p_id,sChildTemp)>0;
	END WHILE;
	RETURN sChildList;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getOrgPList
-- ----------------------------
DROP FUNCTION IF EXISTS `getOrgPList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getOrgPList`(rootId VARCHAR(64)) RETURNS varchar(4000) CHARSET utf8
BEGIN


-- 根据当前id查询所有上级父节点，包含当前节点
-- （向上查询）
-- 操作方式
-- select * From area where FIND_IN_SET(id, getOrgPList('5c8131f4883e11e8b73168f7285847c8'));

    DECLARE sTemp VARCHAR(4000);
    DECLARE sTempPar VARCHAR(4000); 
    SET sTemp = ''; 
    SET sTempPar =rootId; 
 
    #循环递归
    WHILE sTempPar is not null DO 
        #判断是否是第一个，不加的话第一个会为空
        IF sTemp != '' THEN
            SET sTemp = concat(sTemp,',',sTempPar);
        ELSE
            SET sTemp = sTempPar;
        END IF;
        SET sTemp = concat(sTemp,',',sTempPar); 
        SELECT group_concat(p_id) INTO sTempPar FROM organization where p_id<>id and FIND_IN_SET(id,sTempPar)>0; 
    END WHILE; 
 
RETURN sTemp; 
END
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
