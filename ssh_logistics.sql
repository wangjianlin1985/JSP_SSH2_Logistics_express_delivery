/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : ssh_logistics

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-08 19:23:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_caiwu`
-- ----------------------------
DROP TABLE IF EXISTS `t_caiwu`;
CREATE TABLE `t_caiwu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `jine` double NOT NULL,
  `leixing` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `cheliangid` bigint(20) DEFAULT NULL,
  `churukuid` bigint(20) DEFAULT NULL,
  `dingdanid` bigint(20) DEFAULT NULL,
  `kucunid` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9E4D143EC54D6E9A` (`kucunid`),
  KEY `FK9E4D143E627E2A1C` (`dingdanid`),
  KEY `FK9E4D143E22FBB85A` (`cheliangid`),
  KEY `FK9E4D143EB61BF750` (`churukuid`),
  KEY `FK9E4D143E48E361B6` (`userid`),
  CONSTRAINT `FK9E4D143E22FBB85A` FOREIGN KEY (`cheliangid`) REFERENCES `t_cheliang` (`id`),
  CONSTRAINT `FK9E4D143E48E361B6` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK9E4D143E627E2A1C` FOREIGN KEY (`dingdanid`) REFERENCES `t_dingdan` (`id`),
  CONSTRAINT `FK9E4D143EB61BF750` FOREIGN KEY (`churukuid`) REFERENCES `t_churuku` (`id`),
  CONSTRAINT `FK9E4D143EC54D6E9A` FOREIGN KEY (`kucunid`) REFERENCES `t_kucun` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_caiwu
-- ----------------------------
INSERT INTO `t_caiwu` VALUES ('1', '2017-06-01 22:19:14', '3000', '财务支出', '入库支出', null, '1', null, null, '2');
INSERT INTO `t_caiwu` VALUES ('2', '2017-06-01 22:19:14', '30', '财务支出', '入库支出', null, null, null, '1', '2');
INSERT INTO `t_caiwu` VALUES ('3', '2017-06-01 22:20:05', '20', '财务收入', '订单收入', null, null, '1', null, '2');
INSERT INTO `t_caiwu` VALUES ('4', '2019-10-05 08:49:57', '5000', '订单收入', '财务收入', null, null, '2', null, '3');
INSERT INTO `t_caiwu` VALUES ('5', '2019-10-05 08:54:10', '5000', '入库支出', '财务支出', null, '3', null, null, '3');
INSERT INTO `t_caiwu` VALUES ('6', '2019-10-05 08:54:10', '100000', '库存支出', '财务支出', null, null, null, '2', '3');

-- ----------------------------
-- Table structure for `t_cangzu`
-- ----------------------------
DROP TABLE IF EXISTS `t_cangzu`;
CREATE TABLE `t_cangzu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zujin` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cangzu
-- ----------------------------
INSERT INTO `t_cangzu` VALUES ('1', '100');

-- ----------------------------
-- Table structure for `t_cheliang`
-- ----------------------------
DROP TABLE IF EXISTS `t_cheliang`;
CREATE TABLE `t_cheliang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chelianglock` int(11) NOT NULL,
  `chengyungongsi` varchar(255) DEFAULT NULL,
  `chepai` varchar(255) DEFAULT NULL,
  `chexing` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `diaoduzhuangtai` varchar(255) DEFAULT NULL,
  `guihao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cheliang
-- ----------------------------
INSERT INTO `t_cheliang` VALUES ('1', '0', '南京物流中心', '苏A12345', '大型货车', '2019-10-05 08:48:52', '未调度', 'A01');

-- ----------------------------
-- Table structure for `t_churuku`
-- ----------------------------
DROP TABLE IF EXISTS `t_churuku`;
CREATE TABLE `t_churuku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `dingdanhao` varchar(255) DEFAULT NULL,
  `jiage` double NOT NULL,
  `shangpingming` varchar(255) DEFAULT NULL,
  `shuliang` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `zhanyongmianji` double NOT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4BE33ED248E361B6` (`userid`),
  CONSTRAINT `FK4BE33ED248E361B6` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_churuku
-- ----------------------------
INSERT INTO `t_churuku` VALUES ('1', '2017-06-01 22:19:14', null, '3000', '电脑', '1', '1', '3', '2');
INSERT INTO `t_churuku` VALUES ('2', '2019-10-05 08:51:41', '1570236596994', '5000', '电脑', '20', '2', '0', '3');
INSERT INTO `t_churuku` VALUES ('3', '2019-10-05 08:54:10', null, '5000', '笔记本', '100', '1', '1000', '3');

-- ----------------------------
-- Table structure for `t_diaodu`
-- ----------------------------
DROP TABLE IF EXISTS `t_diaodu`;
CREATE TABLE `t_diaodu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chengyungongsi` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `diaoduriqi` varchar(255) DEFAULT NULL,
  `diaoduzhuangtai` varchar(255) DEFAULT NULL,
  `xianluming` varchar(255) DEFAULT NULL,
  `yaoqiudaidashijian` varchar(255) DEFAULT NULL,
  `yunshufeiyong` double NOT NULL,
  `cheliangid` bigint(20) DEFAULT NULL,
  `dingchedanid` bigint(20) DEFAULT NULL,
  `dingdanid` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `user2id` bigint(20) DEFAULT NULL,
  `user3id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2D77458FB15A343E` (`dingchedanid`),
  KEY `FK2D77458F627E2A1C` (`dingdanid`),
  KEY `FK2D77458F71F274B2` (`user2id`),
  KEY `FK2D77458F71F27873` (`user3id`),
  KEY `FK2D77458F22FBB85A` (`cheliangid`),
  KEY `FK2D77458F48E361B6` (`userid`),
  CONSTRAINT `FK2D77458F22FBB85A` FOREIGN KEY (`cheliangid`) REFERENCES `t_cheliang` (`id`),
  CONSTRAINT `FK2D77458F48E361B6` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK2D77458F627E2A1C` FOREIGN KEY (`dingdanid`) REFERENCES `t_dingdan` (`id`),
  CONSTRAINT `FK2D77458F71F274B2` FOREIGN KEY (`user2id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK2D77458F71F27873` FOREIGN KEY (`user3id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK2D77458FB15A343E` FOREIGN KEY (`dingchedanid`) REFERENCES `t_dingchedan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_diaodu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_dingchedan`
-- ----------------------------
DROP TABLE IF EXISTS `t_dingchedan`;
CREATE TABLE `t_dingchedan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chuanzhen` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `dianhua` varchar(255) DEFAULT NULL,
  `dingchedanhao` varchar(255) DEFAULT NULL,
  `dingchexingzhi` varchar(255) DEFAULT NULL,
  `fenpeizhuangtai` varchar(255) DEFAULT NULL,
  `lianxiren` varchar(255) DEFAULT NULL,
  `youjian` varchar(255) DEFAULT NULL,
  `yunshuxingzhi` varchar(255) DEFAULT NULL,
  `cheliangid` bigint(20) DEFAULT NULL,
  `dingdanid` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB2CB63A627E2A1C` (`dingdanid`),
  KEY `FKB2CB63A22FBB85A` (`cheliangid`),
  KEY `FKB2CB63A48E361B6` (`userid`),
  CONSTRAINT `FKB2CB63A22FBB85A` FOREIGN KEY (`cheliangid`) REFERENCES `t_cheliang` (`id`),
  CONSTRAINT `FKB2CB63A48E361B6` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKB2CB63A627E2A1C` FOREIGN KEY (`dingdanid`) REFERENCES `t_dingdan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dingchedan
-- ----------------------------

-- ----------------------------
-- Table structure for `t_dingdan`
-- ----------------------------
DROP TABLE IF EXISTS `t_dingdan`;
CREATE TABLE `t_dingdan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dingchedangeshu` int(11) NOT NULL,
  `dingdanhao` varchar(255) DEFAULT NULL,
  `duizhangzhuangtai` varchar(255) DEFAULT NULL,
  `fahuodi` varchar(255) DEFAULT NULL,
  `huowubianhao` varchar(255) DEFAULT NULL,
  `huowumingchen` varchar(255) DEFAULT NULL,
  `jine` double NOT NULL,
  `kehuxingming` varchar(255) DEFAULT NULL,
  `liaxifangshi` varchar(255) DEFAULT NULL,
  `mianji` double NOT NULL,
  `mudidi` varchar(255) DEFAULT NULL,
  `riqi` datetime DEFAULT NULL,
  `shouhuozhuangtai` varchar(255) DEFAULT NULL,
  `shuliang` int(11) NOT NULL,
  `tiji` double NOT NULL,
  `zhongliang` double NOT NULL,
  `kehuid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8224F8E8370A4AEE` (`kehuid`),
  CONSTRAINT `FK8224F8E8370A4AEE` FOREIGN KEY (`kehuid`) REFERENCES `t_kehu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dingdan
-- ----------------------------
INSERT INTO `t_dingdan` VALUES ('1', '0', '1496326805735', '已对账', '赣州', '1496326754966', '电脑', '20', '黄俊峰', '110', '3', '北京', '2017-06-01 22:20:05', '已收货', '1', '123', '213', '1');
INSERT INTO `t_dingdan` VALUES ('2', '0', '1570236596994', '未对帐', '南京', '1496326754966', '电脑', '5000', '黄俊峰', '13022502404', '500', '北京', '2019-10-05 08:49:56', '商品已出库，转入调度中心', '20', '500', '100', '1');

-- ----------------------------
-- Table structure for `t_kehu`
-- ----------------------------
DROP TABLE IF EXISTS `t_kehu`;
CREATE TABLE `t_kehu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `gongsimingchen` varchar(255) DEFAULT NULL,
  `jiaoyicishu` int(11) NOT NULL,
  `jiaoyijine` double NOT NULL,
  `kehulock` int(11) NOT NULL,
  `kehumingcheng` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_kehu
-- ----------------------------
INSERT INTO `t_kehu` VALUES ('1', '2017-06-01 22:18:12', '黄俊峰1', '2', '5020', '0', '黄俊峰');

-- ----------------------------
-- Table structure for `t_kucun`
-- ----------------------------
DROP TABLE IF EXISTS `t_kucun`;
CREATE TABLE `t_kucun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bianhao` varchar(255) DEFAULT NULL,
  `shangpingming` varchar(255) DEFAULT NULL,
  `shuliang` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_kucun
-- ----------------------------
INSERT INTO `t_kucun` VALUES ('1', '1496326754966', '电脑', '980');
INSERT INTO `t_kucun` VALUES ('2', '1570236850616', '笔记本', '100');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `truename` varchar(255) DEFAULT NULL,
  `userlock` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', null, 'admin', '1', 'admin', '0', 'admin');
INSERT INTO `t_user` VALUES ('2', '2017-06-01 22:16:38', '123456', '0', '小王', '0', 'jerry');
INSERT INTO `t_user` VALUES ('3', '2017-06-01 22:28:57', '123456', '0', '小李', '0', 'xiaoli');
