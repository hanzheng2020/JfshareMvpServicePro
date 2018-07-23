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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  `userId` int(10) DEFAULT NULL COMMENT 'userId',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `update_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  `score` int(11) DEFAULT NULL COMMENT '�ɳ���',
  `levle` char(1) DEFAULT NULL COMMENT '�ȼ�',
  `jvjindou` int(11) DEFAULT NULL COMMENT '�۽�',
  `levle_beyond` int(11) DEFAULT NULL COMMENT '������ǰ�ȼ��ɳ���',
  `real_jvjindou` int(11) DEFAULT NULL COMMENT '��Ч�۽�',
  `remark` varchar(200) DEFAULT NULL COMMENT '�����ֶ�',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_jvjindou_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_jvjindou_rule`;
CREATE TABLE `tb_jvjindou_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `giving_rule` char(255) DEFAULT NULL COMMENT '���͹���',
  `random_giving_min` int(11) DEFAULT NULL COMMENT '���������С��',
  `random_giving_max` int(11) DEFAULT NULL,
  `fixed_giving` int(11) DEFAULT NULL COMMENT '�̶�����ֵ',
  `update_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
