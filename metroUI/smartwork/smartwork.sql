/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : smartask

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-03-13 03:30:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `contacts`
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `dept` int(2) DEFAULT NULL,
  `jobs` int(2) DEFAULT NULL,
  `namecn` varchar(10) DEFAULT NULL,
  `nameen` varchar(10) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES ('0', '0', '0', '陈顺平', 'louis', '13544924731', 'E10ADC3949BA59ABBE56E057F20F883E', '2916896306@qq.com', '2016-03-12 16:46:31');
INSERT INTO `contacts` VALUES ('1', '5', '1', '张志勇', 'brandon', '13544924732', 'E10ADC3949BA59ABBE56E057F20F883E', '2916896306@qq.com', '2016-03-12 16:45:29');
INSERT INTO `contacts` VALUES ('2', '5', '2', '钟惟桃', 'gavin', '13544924733', 'E10ADC3949BA59ABBE56E057F20F883E', '2916896306@qq.com', '2016-03-12 16:44:44');
INSERT INTO `contacts` VALUES ('3', '5', '2', '黄聪', 'brulez', '13544924734', 'E10ADC3949BA59ABBE56E057F20F883E', '2916896306@qq.com', '2016-03-12 15:21:22');

-- ----------------------------
-- Table structure for `daily`
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(100) DEFAULT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `contactsId` varchar(32) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contactsId` (`contactsId`),
  CONSTRAINT `fk_contactsId` FOREIGN KEY (`contactsId`) REFERENCES `contacts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily
-- ----------------------------
INSERT INTO `daily` VALUES ('04b25e0f333b4f298b19c4cb28be3f2a', '出文档', '2016-03-13 03:27:17', '2016-03-13 03:27:49', '2', '2016-03-13 03:27:17');
INSERT INTO `daily` VALUES ('06d05d7245eb4a6c97d7c14c984fdcf1', '验收', '2016-03-13 03:29:03', null, '3', '2016-03-13 03:29:03');
INSERT INTO `daily` VALUES ('0f32e2e6e1844ab58989c2e67395b9f3', '分析', '2016-03-13 03:28:49', '2016-03-13 03:29:05', '3', '2016-03-13 03:28:49');
INSERT INTO `daily` VALUES ('1069b67de3744020b3009b5c8a790f32', '讨论', '2016-03-13 03:27:12', '2016-03-13 03:27:48', '2', '2016-03-13 03:27:12');
INSERT INTO `daily` VALUES ('1d2e2ffd3d9d481aa71bb4cd2fa922a4', '做框架', '2016-03-13 03:27:23', '2016-03-13 03:27:50', '2', '2016-03-13 03:27:23');
INSERT INTO `daily` VALUES ('3417a6f34c7c4b04a7ee89947ae3dae6', '测试', '2016-03-13 03:28:52', null, '3', '2016-03-13 03:28:52');
INSERT INTO `daily` VALUES ('37bd649735364cd7ba6360140e86cc47', '验收', '2016-03-13 03:27:42', null, '2', '2016-03-13 03:27:42');
INSERT INTO `daily` VALUES ('8ca44f9f8e9743dd847669114e524d2c', '测试', '2016-03-13 03:27:31', null, '2', '2016-03-13 03:27:31');
INSERT INTO `daily` VALUES ('b67b53904fc848f9aab41d8c2474e304', '实现', '2016-03-13 03:27:28', null, '2', '2016-03-13 03:27:28');
INSERT INTO `daily` VALUES ('e24bc04622814f4796a0329d5c9899dc', '发布', '2016-03-13 03:27:34', null, '2', '2016-03-13 03:27:34');
