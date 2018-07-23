/*
Navicat MySQL Data Transfer

Source Server         : sa
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : dbgirl

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-23 09:41:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_level_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_level_info`;
CREATE TABLE `tb_level_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(10) DEFAULT NULL COMMENT 'userId',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `score` int(11) DEFAULT NULL COMMENT '成长点',
  `levle` char(1) DEFAULT NULL COMMENT '等级',
  `jvjindou` int(11) DEFAULT NULL COMMENT '聚金豆',
  `levle_beyond` int(11) DEFAULT NULL COMMENT '超出当前等级成长点',
  `real_jvjindou` int(11) DEFAULT NULL COMMENT '有效聚金豆',
  `remark` varchar(200) DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_jvjindou_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_jvjindou_rule`;
CREATE TABLE `tb_jvjindou_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `giving_rule` char(255) DEFAULT NULL COMMENT '赠送规则',
  `random_giving_min` int(11) DEFAULT NULL COMMENT '随机赠送最小数',
  `random_giving_max` int(11) DEFAULT NULL,
  `fixed_giving` int(11) DEFAULT NULL COMMENT '固定赠送值',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
