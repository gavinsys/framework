/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : gogohome

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-03-25 15:59:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ctoken
-- ----------------------------
DROP TABLE IF EXISTS `ctoken`;
CREATE TABLE `ctoken` (
  `uuid` varchar(32) NOT NULL,
  `deviceId` varchar(50) NOT NULL,
  `appId` varchar(500) DEFAULT NULL,
  `accessToken` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uuid`,`deviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ctoken
-- ----------------------------
INSERT INTO `ctoken` VALUES ('78ade94091f245989eef0ac6ec0c7545', '357554061198433', 'fVBe6FzDn40:APA91bHKkCP86T2ByY68hhawXRyENktlkVFRZmbRFXtFAnW8H03QcVu8inKxx3zq4vticnsxtty1mYH3ENjWc2i4Pmm42-Ayq8dOnFN0d3o-XsUeD1YB7dBqrFw_9FxVIc_aiVHIeefv', '1ml2grrh6j6ka2moi7985xrpd');
INSERT INTO `ctoken` VALUES ('78ade94091f245989eef0ac6ec0c7545', '358188051895887', 'coGm6p-tgzU:APA91bGpRm9dJsfaSHSgBeT0uuOjjtTMVRG_KYp0UtOFmmly0-butn9-Y0SjomrN5Dw0Wg0YaWOKlU_pHwHT-PCTrmJg-QbPQpDZnwYUKHHjuHTM-vm9h9SokoxOTlhW0_VJxCNwoyP9', 'gg7gitw3lly5emdervxyr3tk');
INSERT INTO `ctoken` VALUES ('99324a23ee0d445b922943fbae6260f0', '358188051895887', 'fPKkSoUhlPo:APA91bEHhttL2R5_f8GcHRewObKJ2as3x7m0_2F-Jkngu56ehNeJY2PUf1GKAFVYt1eFr09cjh_6Ewy9GUrod1GIqJ4cwHUOXThjtwEX2okgMRuvOA-9SYKs73CKV8Pd0HDOP1qZQtKM', 'hvfqropip3o7zt64gn6nco4l');
INSERT INTO `ctoken` VALUES ('feaa07118ecd4656a88149c24f3023e8', '358188051895887', 'fPKkSoUhlPo:APA91bEHhttL2R5_f8GcHRewObKJ2as3x7m0_2F-Jkngu56ehNeJY2PUf1GKAFVYt1eFr09cjh_6Ewy9GUrod1GIqJ4cwHUOXThjtwEX2okgMRuvOA-9SYKs73CKV8Pd0HDOP1qZQtKM', '1f7vprxnnnuzb1gqnqna0epxt1');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` varchar(32) NOT NULL,
  `img` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `carAge` int(11) DEFAULT NULL,
  `gears` varchar(50) DEFAULT NULL,
  `urgentContacts` varchar(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('78ade94091f245989eef0ac6ec0c7545', null, null, '13417936319', null, null, null, '2016-03-24 15:20:56');
INSERT INTO `customer` VALUES ('99324a23ee0d445b922943fbae6260f0', null, null, '1310681678', null, null, null, '2016-03-24 17:38:59');
INSERT INTO `customer` VALUES ('feaa07118ecd4656a88149c24f3023e8', null, null, '13106816798', null, null, null, '2016-03-24 17:35:54');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` varchar(32) NOT NULL,
  `img` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `idNumber` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `dateLicense` varchar(50) DEFAULT NULL,
  `imgLicense` varchar(200) DEFAULT NULL,
  `imgIdNumber` varchar(200) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('21767e4ad2ab496fa165e9b398b6651b', null, null, null, '13417936319', null, null, null, '1', '1', '2016-03-24 17:54:48');
INSERT INTO `driver` VALUES ('3678d7b2d3be45cabb6a791d88c6ca1f', null, null, null, '13106816798', null, null, null, '1', '1', '2016-03-24 15:04:21');

-- ----------------------------
-- Table structure for dtoken
-- ----------------------------
DROP TABLE IF EXISTS `dtoken`;
CREATE TABLE `dtoken` (
  `uuid` varchar(32) NOT NULL,
  `deviceId` varchar(50) NOT NULL,
  `appId` varchar(500) DEFAULT NULL,
  `accessToken` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uuid`,`deviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dtoken
-- ----------------------------
INSERT INTO `dtoken` VALUES ('21767e4ad2ab496fa165e9b398b6651b', '358188051895887', 'dGh81votf48:APA91bGb0yK0LiyAec4gE7BrQS8NiabU3WUBionG1H0Xf_8DxYKqVaLQr5hf_taZX06ulWINWTBR1ECiLyokyPa_pwbR3sqh8lD0F370e_1KRFH5DkSsVRrUcC6_WX5mESq_JkWjxhRm', '1wis86tq9hvin14vx3ejcwz4u5');
INSERT INTO `dtoken` VALUES ('3678d7b2d3be45cabb6a791d88c6ca1f', '357554061198433', 'cjiEIvsEiVA:APA91bEg-T2kmIPU3ebaeW8mYKBLc-A_wxGS8czVhoAYpsZIBdDClYuU9Qqji4F7ytaKnzuoYsdi0JQlqb89fteBl4MFEL-Xi4HEUHCfGdFxCsR1lwsAr72PDM2yyHZAvoY3xOy9DrrO', 'z6wp9v5w8vp11pwuw7newji5t');
INSERT INTO `dtoken` VALUES ('3678d7b2d3be45cabb6a791d88c6ca1f', '358188051895887', 'ccYL57_sqcU:APA91bGXweDsTbeNQn8AamhgBMY5lpdE9l6K_ayswrY2Kqo024Tsyx0UblCr7Q848O5Cd3DMMdOk7x8upy6HZ6YqW_inTu12kT4DX8a9oQEBKB8GEm1f2SasQVTJP3e3g0M2DM2R-Z8U', 'gjcmkx7j4dut1t70h4r6ry75v');

-- ----------------------------
-- Table structure for intention
-- ----------------------------
DROP TABLE IF EXISTS `intention`;
CREATE TABLE `intention` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `orderNo` varchar(50) NOT NULL,
  `fromloc` varchar(200) DEFAULT NULL,
  `toloc` varchar(200) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`orderNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of intention
-- ----------------------------
INSERT INTO `intention` VALUES ('3678d7b2d3be45cabb6a791d88c6ca1f', '20160324192937500', 'aaa', 'bbb', '10', '2247.6', '10:30', '1');

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `driverId` varchar(32) NOT NULL,
  `lat` varchar(50) DEFAULT NULL,
  `lng` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`driverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('21767e4ad2ab496fa165e9b398b6651b', '22.2099927', '113.5330466');
INSERT INTO `location` VALUES ('3678d7b2d3be45cabb6a791d88c6ca1f', '22.2097065', '113.532893');
INSERT INTO `location` VALUES ('bb34e63d7ec8418ebf1dd9eb442e6c8b', '22.2099724', '113.533204');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(32) NOT NULL,
  `orderNo` varchar(32) DEFAULT NULL,
  `fromloc` varchar(200) DEFAULT NULL,
  `fromlat` varchar(200) DEFAULT NULL,
  `fromlng` varchar(200) DEFAULT NULL,
  `toloc` varchar(200) DEFAULT NULL,
  `tolat` varchar(200) DEFAULT NULL,
  `tolng` varchar(200) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  `driverId` varchar(32) DEFAULT NULL,
  `customerId` varchar(32) DEFAULT NULL,
  `contact` varchar(11) DEFAULT NULL,
  `routes` varchar(500) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_driver` (`driverId`),
  KEY `fk_customer` (`customerId`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_driver` FOREIGN KEY (`driverId`) REFERENCES `driver` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0a9ee9618e2c49b18ff2a4b4d82d8abb', '20160325152926462', 'aaa', '22.196934', '113.549765', 'bbb', '22.196834', '113.549865', '10:30', null, null, '1', null, null, '78ade94091f245989eef0ac6ec0c7545', null, null, '2016-03-25 15:29:26');
INSERT INTO `orders` VALUES ('132b1d1103e0469b8d3eda9010f6e2e2', '20160325154220850', 'aaa', '22.196934', '113.549765', 'bbb', '22.196834', '113.549865', '10:30', '100', '5', '5', null, '3678d7b2d3be45cabb6a791d88c6ca1f', '78ade94091f245989eef0ac6ec0c7545', null, null, '2016-03-25 15:42:20');
INSERT INTO `orders` VALUES ('6600a783bed04c0b862dca2b15f83ae8', '20160325145615006', 'aaa', '22.196934', '113.549765', 'bbb', '22.196834', '113.549865', '10:30', null, '5', '5', null, null, '78ade94091f245989eef0ac6ec0c7545', null, null, '2016-03-25 14:56:15');
INSERT INTO `orders` VALUES ('87726a961cee499e9cf4284f1d6048d7', '20160325151549579', 'aaa', '22.196934', '113.549765', 'bbb', '22.196834', '113.549865', '10:30', null, null, '0', null, null, '78ade94091f245989eef0ac6ec0c7545', null, null, '2016-03-25 15:15:49');
INSERT INTO `orders` VALUES ('fd3901dc327544b6a01d908d890b0c39', '20160325153044503', 'aaa', '22.196934', '113.549765', 'bbb', '22.196834', '113.549865', '10:30', '100', '5', '5', null, '3678d7b2d3be45cabb6a791d88c6ca1f', '78ade94091f245989eef0ac6ec0c7545', null, null, '2016-03-25 15:30:44');

-- ----------------------------
-- Table structure for routes
-- ----------------------------
DROP TABLE IF EXISTS `routes`;
CREATE TABLE `routes` (
  `id` varchar(32) NOT NULL,
  `orderNo` varchar(32) DEFAULT '',
  `loc` varchar(500) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of routes
-- ----------------------------
INSERT INTO `routes` VALUES ('2d14d667bbad43b3a209d7a5a97ed985', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:20');
INSERT INTO `routes` VALUES ('323e7e718a8b4ee59fc3e9dc5407a06a', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:05');
INSERT INTO `routes` VALUES ('55ad2f90c103484599b6d560875d52b5', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:10');
INSERT INTO `routes` VALUES ('88db8647575e4328b7081fe92d38ddd9', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:44:55');
INSERT INTO `routes` VALUES ('b7fd7dc9bd894b00bbc0565209ec9d92', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:15');
INSERT INTO `routes` VALUES ('bda91d41c90d487e9611f996f0bd188d', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:35');
INSERT INTO `routes` VALUES ('cfaa855976c146c3b29d8cf8f51c6159', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:00');
INSERT INTO `routes` VALUES ('ec27cc24dafd46fea817d4d353dbdd11', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:25');
INSERT INTO `routes` VALUES ('f4b462c16dfb49649de9d42deb1a9bcf', '20160325154220850', '22.209718:113.5329302,22.2097176:113.5329276,22.2097182:113.5329306,22.2097166:113.5329243,22.2097164:113.5329256,', '2016-03-25 15:45:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pass` varchar(32) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', '2016-03-02 11:55:06');
INSERT INTO `user` VALUES ('afd0f187df3e4447b3ed400239af5c05', 'user', 'EE11CBB19052E40B07AAC0CA060C23EE', '2016-03-02 18:01:32');
