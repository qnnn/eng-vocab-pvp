/*
 Navicat Premium Data Transfer

 Source Server         : kimikimi
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : eng

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 10/07/2021 16:51:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails`  (
  `appId` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `resourceIds` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `appSecret` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `grantTypes` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `redirectUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `access_token_validity` int NULL DEFAULT NULL,
  `refresh_token_validity` int NULL DEFAULT NULL,
  `additionalInformation` varchar(4096) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `autoApproveScopes` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authentication` blob NULL,
  `refresh_token` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`  (
  `userId` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `expiresAt` timestamp(0) NULL DEFAULT NULL,
  `lastModifiedAt` timestamp(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `access_token_validity` int NULL DEFAULT NULL,
  `refresh_token_validity` int NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', 'backend-resources', '$2a$10$ErTxN82JEj7f0Issp0xwUeIpEjYaDhv/ZIo6LS0kYA2WUVO3aOl0W', 'app', 'authorization_code,refresh_token', 'http://127.0.0.1:8092/competitor/login', 'USER,ADMIN', 43200, 648000, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('single', 'single-resource', '$2a$10$ErTxN82JEj7f0Issp0xwUeIpEjYaDhv/ZIo6LS0kYA2WUVO3aOl0W', 'app', 'authorization_code,refresh_token', 'http://localhost:8080/single/login', 'USER,ADMIN', 43200, 648000, NULL, 'true');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authentication` blob NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication` blob NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_used` timestamp(0) NOT NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('admin', '0TibzE5oWJ1wAAKZyCCucA==', 'idZ7i3IqZ1cLhoohftl6Tw==', '2021-06-27 22:00:12');
INSERT INTO `persistent_logins` VALUES ('admin', '2l0nXfJyssfN54gfuZg9gw==', 'nwJb/Jvr9F0OsZ00VAQdUg==', '2021-06-27 23:03:09');
INSERT INTO `persistent_logins` VALUES ('admin', '7LmKI5GYXCJMqzkvl8z5/w==', '3n3RHUNgSy0cY7WoQAocwA==', '2021-06-27 18:45:16');
INSERT INTO `persistent_logins` VALUES ('admin', '9FoN6X0oIYmuahpVkmPV9g==', 'dwPhr0V/h021b0nacmEnlA==', '2021-06-27 21:56:48');
INSERT INTO `persistent_logins` VALUES ('admin', 'BACPzgJJJ8FebYBsnMyCKQ==', 'RcTjhFTny2XWjE/KIqpnuQ==', '2021-06-27 20:26:13');
INSERT INTO `persistent_logins` VALUES ('admin', 'HhTP5Ccge6w2jcODLcx3fA==', 'flIPKjN5HqotlXkLL5cDNA==', '2021-06-27 22:02:07');
INSERT INTO `persistent_logins` VALUES ('admin', 'KKo6I1sgtN1g0bYDxJZSCQ==', 'seQSpxj7SmoitIAA0XZUng==', '2021-06-27 22:02:27');
INSERT INTO `persistent_logins` VALUES ('admin', 'PDo8tx+d0D4RapLiiAPRUQ==', 'ZBnUIiE5j0RZWzZ7Dg8QaA==', '2021-06-27 20:24:50');
INSERT INTO `persistent_logins` VALUES ('admin', 'WKBbVENKH58CXEnNZ4x4Ug==', '9yCTMlxoXMeCHa1SBcFfjg==', '2021-06-27 20:54:10');
INSERT INTO `persistent_logins` VALUES ('amdin', 'ZFupYmDtSrxBqq0mXpDpsw==', '5DZDAPLHDNQckzBuBq3scg==', '2021-06-26 16:23:44');
INSERT INTO `persistent_logins` VALUES ('admin', 'bAP2RE8GtEgB58tLUHEsHg==', '5C8imVg2xB3/UhvV/hcO7A==', '2021-06-28 00:46:13');
INSERT INTO `persistent_logins` VALUES ('admin', 'cF+b9lLmsSq6QVWwdwaYvw==', 'zRrNdiqFK1wRXLPaLhhDdg==', '2021-06-27 21:30:26');
INSERT INTO `persistent_logins` VALUES ('admin', 'ckPsXR6ji3CFfXz8sFnwIg==', '319vDUOAUehN0WwRfekReA==', '2021-06-27 22:07:10');
INSERT INTO `persistent_logins` VALUES ('admin', 'mK4+ClGapzUNh3T3ho6QAA==', 'ZbJVXJTiz4oBwLRoh9+mDw==', '2021-06-27 22:00:44');
INSERT INTO `persistent_logins` VALUES ('admin', 'wp351hypchpnlCAMQCsfIA==', 'QvlXnJSph7OoaK6EhDOKzg==', '2021-06-27 21:54:40');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `sno` int NOT NULL AUTO_INCREMENT,
  `s_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1806300134 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1806300000, 'Hello', '$2a$10$VWT8uVFqJguNrVbyYJkEvOzcjWb/sfRTC4gxAiQh7RpVf2oJxz5cO');
INSERT INTO `t_student` VALUES (1806300001, 'playerTest1', '$2a$10$VWT8uVFqJguNrVbyYJkEvOzcjWb/sfRTC4gxAiQh7RpVf2oJxz5cO');
INSERT INTO `t_student` VALUES (1806300002, 'playerTest2', '$2a$10$VWT8uVFqJguNrVbyYJkEvOzcjWb/sfRTC4gxAiQh7RpVf2oJxz5cO');
INSERT INTO `t_student` VALUES (1806300003, 'playerTest3', '$2a$10$VWT8uVFqJguNrVbyYJkEvOzcjWb/sfRTC4gxAiQh7RpVf2oJxz5cO');
INSERT INTO `t_student` VALUES (1806300122, 'Loe', '$2a$10$VWT8uVFqJguNrVbyYJkEvOzcjWb/sfRTC4gxAiQh7RpVf2oJxz5cO');
INSERT INTO `t_student` VALUES (1806300133, 'Arlin', '$2a$10$VWT8uVFqJguNrVbyYJkEvOzcjWb/sfRTC4gxAiQh7RpVf2oJxz5cO');

SET FOREIGN_KEY_CHECKS = 1;
