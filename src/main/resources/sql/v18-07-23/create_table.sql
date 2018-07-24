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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '锟斤拷锟斤拷',
  `userId` int(10) DEFAULT NULL COMMENT 'userId',
  `create_time` datetime DEFAULT NULL COMMENT '锟斤拷锟斤拷时锟斤拷',
  `update_time` datetime DEFAULT NULL COMMENT '锟睫革拷时锟斤拷',
  `score` int(11) DEFAULT NULL COMMENT '锟缴筹拷锟斤拷',
  `levle` char(1) DEFAULT NULL COMMENT '锟饺硷拷',
  `jvjindou` int(11) DEFAULT NULL COMMENT '锟桔斤拷',
  `levle_beyond` int(11) DEFAULT NULL COMMENT '锟斤拷锟斤拷锟斤拷前锟饺硷拷锟缴筹拷锟斤拷',
  `real_jvjindou` int(11) DEFAULT NULL COMMENT '锟斤拷效锟桔斤拷',
  `remark` varchar(200) DEFAULT NULL COMMENT '锟斤拷锟斤拷锟街讹拷',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tb_jvjindou_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_jvjindou_rule`;
CREATE TABLE `tb_jvjindou_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '锟斤拷锟斤拷',
  `create_time` datetime DEFAULT NULL COMMENT '锟斤拷锟斤拷时锟斤拷',
  `giving_rule` char(255) DEFAULT NULL COMMENT '锟斤拷锟酵癸拷锟斤拷',
  `random_giving_min` int(11) DEFAULT NULL COMMENT '锟斤拷锟斤拷锟斤拷锟斤拷锟叫★拷锟�',
  `random_giving_max` int(11) DEFAULT NULL,
  `fixed_giving` int(11) DEFAULT NULL COMMENT '锟教讹拷锟斤拷锟斤拷值',
  `update_time` datetime DEFAULT NULL COMMENT '锟睫革拷时锟斤拷',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tb_product_promotion`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_promotion`;
CREATE TABLE `tb_product_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_no` int(11) NOT NULL,
  `promotion_pic_url` varchar(255) NOT NULL,
  `promotion_url` varchar(255) DEFAULT NULL,
  `product_one_id` varchar(255) NOT NULL,
  `product_one_desc` varchar(255) NOT NULL,
  `product_one_pic_url` varchar(255) NOT NULL,
  `product_two_id` varchar(255) NOT NULL,
  `product_two_desc` varchar(255) NOT NULL,
  `product_two_pic_url` varchar(255) NOT NULL,
  `product_three_id` varchar(255) NOT NULL,
  `product_three_desc` varchar(255) NOT NULL,
  `product_three_pic_url` varchar(255) NOT NULL,
  `product_four_id` varchar(255) NOT NULL,
  `product_four_desc` varchar(255) NOT NULL,
  `product_four_pic_url` varchar(255) NOT NULL,
  `product_five_id` varchar(255) NOT NULL,
  `product_five_desc` varchar(255) NOT NULL,
  `product_five_pic_url` varchar(255) NOT NULL,
  `product_six_id` varchar(255) NOT NULL,
  `product_six_desc` varchar(255) NOT NULL,
  `product_six_pic_url` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_jf_raiders
-- ----------------------------
DROP TABLE IF EXISTS `tb_jf_raiders`;
CREATE TABLE `tb_jf_raiders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) NOT NULL COMMENT '标题',
  `content` blob NOT NULL COMMENT '内容',
  `create_ime` datetime NOT NULL COMMENT '创建时间',
  `img_url` varchar(300) DEFAULT NULL COMMENT '图片路径',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建用户',
  `status` int(2) NOT NULL COMMENT '状态(1:新建，2:发布)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分攻略表';

-- ----------------------------
-- Table structure for `tb_product_item_show`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_item_show`;
CREATE TABLE `tb_product_item_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_no` int(11) NOT NULL,
  `item_desc` varchar(255) DEFAULT NULL,
  `products` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

=====================================================================
删除 jvjindou 超出等级成长点字段 
Date: 2018-07-24 17:50:34
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
  `growth_point` int(11) DEFAULT NULL COMMENT '成长点',
  `levle` char(1) DEFAULT NULL COMMENT '等级',
  `real_jvjindou` int(11) DEFAULT NULL COMMENT '有效聚金豆',
  `remark` varchar(200) DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`) USING BTREE COMMENT 'userId 唯一键'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

=============================================================================================
增加修改时间字段
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_jvjindou_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_jvjindou_rule`;
CREATE TABLE `tb_jvjindou_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `giving_rule` char(1) DEFAULT NULL COMMENT '赠送规则 1固定 2随机',
  `random_giving_min` int(11) DEFAULT NULL COMMENT '随机赠送最小数',
  `random_giving_max` int(11) DEFAULT NULL COMMENT '最大数',
  `fixed_giving` int(11) DEFAULT NULL COMMENT '固定赠送值',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
=============================================================================================

