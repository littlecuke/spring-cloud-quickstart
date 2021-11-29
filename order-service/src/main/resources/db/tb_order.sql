/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : cloud_order

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 29/11/2021 09:08:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单名称',
  `price` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '金额',
  `num` int(11) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (101, 1, 'Apple 苹果iPhone13', '5999', 1);
INSERT INTO `tb_order` VALUES (102, 2, '雅迪yadea新国标电动车', '5000', 1);
INSERT INTO `tb_order` VALUES (103, 3, '小熊1.6L双人电饭锅', '159', 1);
INSERT INTO `tb_order` VALUES (104, 4, '小米10双模5G骁龙888', '4399', 1);
INSERT INTO `tb_order` VALUES (105, 5, 'OPPO Reno3 Pro', '4399', 1);
INSERT INTO `tb_order` VALUES (106, 6, '美的新能效电风扇', '399', 1);
INSERT INTO `tb_order` VALUES (107, 2, '西昊人体工程学椅', '1099', 1);
INSERT INTO `tb_order` VALUES (108, 3, '梵班（FAMDBANN）', '31900', 1);

SET FOREIGN_KEY_CHECKS = 1;
