/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : flightdb

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 17/05/2023 14:55:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for airport
-- ----------------------------
DROP TABLE IF EXISTS `airport`;
CREATE TABLE `airport`  (
  `ID` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of airport
-- ----------------------------
INSERT INTO `airport` VALUES ('1', '北京大兴', '北京');
INSERT INTO `airport` VALUES ('2', '深圳宝安T3', '深圳');
INSERT INTO `airport` VALUES ('3', '北京首都T2', '北京');
INSERT INTO `airport` VALUES ('4', '北京首都T3', '北京');
INSERT INTO `airport` VALUES ('5', '重庆江北T3', '重庆');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `ordernum` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `flight_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `seatnum` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `flight_id`) USING BTREE,
  INDEX `flight_id`(`flight_id` ASC) USING BTREE,
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('333', '163302200208265757', 'F1028LVQ', '2023-05-02 20:02:00', 12);
INSERT INTO `book` VALUES ('111', '232510201206176366', '84AA5ZX6', '2023-02-01 11:45:00', 34);
INSERT INTO `book` VALUES ('222', '232510201206176366', 'DUUMD4AA', '2023-04-12 02:53:00', 57);
INSERT INTO `book` VALUES ('999', '232510201206176366', 'M5MVNN3U', '2023-05-01 06:30:00', 10);
INSERT INTO `book` VALUES ('777', '279260199511247678', '84AA5ZX6', '2023-02-06 11:45:00', 62);
INSERT INTO `book` VALUES ('444', '279260199511247678', 'PJU7LZ7L', '2023-04-15 06:21:00', 15);
INSERT INTO `book` VALUES ('555', '299197190802244581', 'USUYRD8W', '2023-05-02 16:48:00', 48);
INSERT INTO `book` VALUES ('666', '461215191910139279', '4CV36R0G', '2023-03-06 14:46:00', 22);
INSERT INTO `book` VALUES ('888', '598168197211097225', 'DUUMD4AA', '2023-04-09 02:53:00', 83);

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
  `ID` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `airline` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `dep_airport` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `arr_airport` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `dep_time` datetime NULL DEFAULT NULL,
  `arr_time` datetime NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `dep_airport`(`dep_airport` ASC) USING BTREE,
  INDEX `arr_airport`(`arr_airport` ASC) USING BTREE,
  CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`dep_airport`) REFERENCES `airport` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `flight_ibfk_2` FOREIGN KEY (`arr_airport`) REFERENCES `airport` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES ('4CV36R0G', '公司1', '1', '3', '2023-03-13 14:46:00', '2023-03-13 17:36:00', 584.00);
INSERT INTO `flight` VALUES ('6U8TKU1C', '公司1', '3', '1', '2023-01-24 14:10:00', '2023-01-24 18:26:00', 828.00);
INSERT INTO `flight` VALUES ('84AA5ZX6', '公司1', '3', '4', '2023-02-06 11:45:00', '2023-02-06 14:46:00', 561.00);
INSERT INTO `flight` VALUES ('DUUMD4AA', '公司1', '2', '5', '2023-04-17 02:53:00', '2023-04-17 05:27:00', 991.00);
INSERT INTO `flight` VALUES ('F1028LVQ', '公司1', '4', '2', '2023-05-09 20:02:00', '2023-05-09 23:42:00', 985.00);
INSERT INTO `flight` VALUES ('IAYY2Y24', '公司1', '3', '5', '2023-02-05 11:12:00', '2023-02-05 16:51:00', 1171.00);
INSERT INTO `flight` VALUES ('M5MVNN3U', '公司1', '2', '3', '2023-05-05 06:30:00', '2023-05-05 06:30:00', 928.00);
INSERT INTO `flight` VALUES ('PJU7LZ7L', '公司1', '1', '4', '2023-04-28 06:21:00', '2023-04-28 09:51:00', 1161.00);
INSERT INTO `flight` VALUES ('S6TWS0WV', '公司1', '5', '2', '2023-03-10 13:06:00', '2023-03-10 17:11:00', 646.00);
INSERT INTO `flight` VALUES ('USUYRD8W', '公司1', '1', '2', '2023-05-09 16:48:00', '2023-05-09 19:52:00', 526.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `phonenum` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `access` int NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('154207193108056176', '杨海蓝', '男', 43, '15137455437', '12345', 0);
INSERT INTO `user` VALUES ('157347196610093469', '周维', '女', 28, '17574339673', '12345', 0);
INSERT INTO `user` VALUES ('163302200208265757', '陈蓉', '女', 46, '15319051398', '12345', 0);
INSERT INTO `user` VALUES ('173983199106067554', '赵云海', '男', 48, '17266126269', '12345', 0);
INSERT INTO `user` VALUES ('206533197312276380', '戚译升', '男', 28, '18116953224', '12345', 0);
INSERT INTO `user` VALUES ('21192319180509906X', '曹洋', '女', 29, '15106894869', '12345', 0);
INSERT INTO `user` VALUES ('232510201206176366', '王卫东', '男', 33, '13917751433', '12345', 0);
INSERT INTO `user` VALUES ('235039195808114744', '张渔', '男', 27, '19121654508', '12345', 0);
INSERT INTO `user` VALUES ('279260199511247678', '张嘉钰', '男', 34, '18582944573', '12345', 0);
INSERT INTO `user` VALUES ('283589201305025703', '代燕', '女', 23, '18097175895', '12345', 0);
INSERT INTO `user` VALUES ('299197190802244581', '赵丽', '女', 38, '17898679993', '12345', 0);
INSERT INTO `user` VALUES ('33283919100302940X', '庄本帅', '男', 32, '19152206740', '12345', 0);
INSERT INTO `user` VALUES ('417943192111104816', '杨红菊', '女', 50, '15511832278', '12345', 0);
INSERT INTO `user` VALUES ('461215191910139279', '蒋春辉', '男', 38, '14765939671', '12345', 0);
INSERT INTO `user` VALUES ('479098193910274487', '沈玲妍', '女', 36, '18631552641', '12345', 0);
INSERT INTO `user` VALUES ('481471200404131837', '邹帅', '男', 24, '15931003884', '12345', 0);
INSERT INTO `user` VALUES ('514247201112114995', '喻大亨', '男', 49, '13812948051', '12345', 0);
INSERT INTO `user` VALUES ('52742919551114298X', '尹岚', '女', 25, '19154024768', '12345', 0);
INSERT INTO `user` VALUES ('538604196411019678', '张振', '女', 25, '17705005938', '12345', 0);
INSERT INTO `user` VALUES ('55504319050926778X', '郑彧', '男', 51, '13242235704', '12345', 0);
INSERT INTO `user` VALUES ('55817619570409777X', '陈廷波', '男', 41, '19870646010', '12345', 0);
INSERT INTO `user` VALUES ('594114201703241000', '杜灿', '男', 23, '14555711699', '12345', 0);
INSERT INTO `user` VALUES ('598168197211097225', '王昱霖', '男', 31, '15888932850', '12345', 0);
INSERT INTO `user` VALUES ('598253201512219943', '赵书婷', '女', 22, '17763433756', '12345', 0);
INSERT INTO `user` VALUES ('601514197106132715', '唐金玉', '女', 32, '13330087643', '12345', 0);
INSERT INTO `user` VALUES ('619119191006179913', '温彩苓', '女', 34, '18770418736', '12345', 0);
INSERT INTO `user` VALUES ('635413190504164857', '王钰莹', '女', 38, '13378224354', '12345', 0);
INSERT INTO `user` VALUES ('63552319930801632X', '郭涛', '男', 46, '15604843166', '12345', 0);
INSERT INTO `user` VALUES ('635658199903259321', '彭礼旭', '男', 48, '17256052212', '12345', 0);
INSERT INTO `user` VALUES ('652752202002043227', '李凤', '女', 24, '13927823923', '12345', 0);

SET FOREIGN_KEY_CHECKS = 1;
