/*
Navicat MySQL Data Transfer

Source Server         : imooc
Source Server Version : 50173
Source Host           : 192.168.207.54:3306
Source Database       : imooc

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-10-28 16:36:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `chapter_id` int(11) NOT NULL AUTO_INCREMENT,
  `chap_name` varchar(255) DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL,
  `chap_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1', '远征前准备', '1', '学习C++需要有C语言的基础（有基础的童鞋可以跳过此步）');
INSERT INTO `chapter` VALUES ('2', '远征起航', '1', '初始C++，了解C++与C的不同以及C++的基本语法');
INSERT INTO `chapter` VALUES ('3', '远征面向对象', '1', '这里讲述了C++中的面向对象，封装、继承、多态');
INSERT INTO `chapter` VALUES ('4', '远征模板', '1', 'C++中的友元、模板尽在其中');
INSERT INTO `chapter` VALUES ('5', '入门——SQL基础', '2', 'SQL语法是学习数据库的必备技能，函数和高级查询都是在实际项目开发中应用颇多的内容，它们会使你的开发工作更加得心应手！');
INSERT INTO `chapter` VALUES ('6', '成长——数据库开发', '2', '存储过程、自定义函数和触发器是我们在项目开发中常用的数据库对象，掌握了这些数据库对象，定会成为你的加薪利器！');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `cour_title` varchar(255) DEFAULT NULL,
  `cour_image` varchar(255) DEFAULT NULL,
  `cour_url` varchar(255) DEFAULT NULL,
  `cour_duration` varchar(255) DEFAULT NULL,
  `cour_hot` int(11) DEFAULT NULL,
  `cour_date` date DEFAULT NULL,
  `cour_source` varchar(255) DEFAULT NULL,
  `cour_content` varchar(255) DEFAULT NULL,
  `cour_language` varchar(255) DEFAULT NULL,
  `cour_teacher` varchar(255) DEFAULT NULL,
  `language_id` int(11) DEFAULT NULL,
  `decoration_id` int(11) DEFAULT NULL,
  `level_name` varchar(11) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('9', 'c++远征之起航篇', '11.jpg', '1.3gp', '3小时', '3', '2015-09-23', '1.mp4', '3', '4', '5', '6', '6', '中级', '1');
INSERT INTO `course` VALUES ('10', 'c++远征之封装篇（上）', '13.jpg', '2.3gp', '4小时', '4', '2015-09-29', '1.mp4', '4', '4', '4', '4', '4', '高级', '2');
INSERT INTO `course` VALUES ('11', 'c++远征之继承篇', '14.jpg', '2.3gp', '20分钟', '10', '2015-09-22', '3.mp4', 'Android有四大组件：Activity、Service、Broadcast Receiver、Content Provider', 'android', 'Mars', '1', '2', '初级', '3');
INSERT INTO `course` VALUES ('12', 'c++远征之继承篇(下)', '12.jpg', null, '10分钟', '15', '2015-10-21', '6.mp4', '哈哈', 'java', 'nicai', '2', '2', '中级', '4');
INSERT INTO `course` VALUES ('13', 'c语言指针与内存', '16.jpg', '1.3gp', '15分钟', '100', '2015-10-08', '2.mp4', 'qqqqqqqqqqqq', 'C', '1', '1', '1', '1', '5');
INSERT INTO `course` VALUES ('14', 'c++远征之封装篇（下）', '17.jpg', '2.3gp', '20分钟', '1000', '2015-10-09', '5.mp4', '我会讲故事', '汉语', '陈婧', '1', '1', '初级', '6');
INSERT INTO `course` VALUES ('15', 'Linux c语言机构体', '15.jpg', '1.3gp', '5小时', '99', '2015-10-28', '4.mp4', '6', '汉语', '陈婧', '1', '1', '初级', '7');

-- ----------------------------
-- Table structure for decoration
-- ----------------------------
DROP TABLE IF EXISTS `decoration`;
CREATE TABLE `decoration` (
  `decoration_id` int(11) NOT NULL AUTO_INCREMENT,
  `deco_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`decoration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of decoration
-- ----------------------------
INSERT INTO `decoration` VALUES ('1', '前端开发');
INSERT INTO `decoration` VALUES ('4', '整站开发');
INSERT INTO `decoration` VALUES ('5', '后端开发');

-- ----------------------------
-- Table structure for language
-- ----------------------------
DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `lang_name` varchar(255) DEFAULT NULL,
  `decoration_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of language
-- ----------------------------
INSERT INTO `language` VALUES ('1', 'c语言入门', '1');
INSERT INTO `language` VALUES ('2', 'c++', '2');
INSERT INTO `language` VALUES ('3', 'java', '3');

-- ----------------------------
-- Table structure for level
-- ----------------------------
DROP TABLE IF EXISTS `level`;
CREATE TABLE `level` (
  `level_id` int(11) NOT NULL AUTO_INCREMENT,
  `leve_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of level
-- ----------------------------
INSERT INTO `level` VALUES ('1', '初级');
INSERT INTO `level` VALUES ('2', '中级');
INSERT INTO `level` VALUES ('3', '高级');

-- ----------------------------
-- Table structure for mycourse
-- ----------------------------
DROP TABLE IF EXISTS `mycourse`;
CREATE TABLE `mycourse` (
  `mycourse_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `mycour_state` int(11) DEFAULT NULL,
  `mycour_focus` int(255) DEFAULT NULL,
  `mycour_name` varchar(255) DEFAULT NULL,
  `mycour_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mycourse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mycourse
-- ----------------------------
INSERT INTO `mycourse` VALUES ('1', '1', '11', '0', '1', null, null);
INSERT INTO `mycourse` VALUES ('2', '4', '9', '0', '1', null, null);
INSERT INTO `mycourse` VALUES ('3', '4', '10', '0', '1', null, null);
INSERT INTO `mycourse` VALUES ('4', '4', '13', '1', '1', null, null);
INSERT INTO `mycourse` VALUES ('6', '4', '11', '1', '1', null, null);
INSERT INTO `mycourse` VALUES ('8', '4', '12', '0', '1', null, null);

-- ----------------------------
-- Table structure for mydiscuss
-- ----------------------------
DROP TABLE IF EXISTS `mydiscuss`;
CREATE TABLE `mydiscuss` (
  `mydiscuss_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `disc_content` varchar(255) DEFAULT NULL,
  `disc_date` date DEFAULT NULL,
  `disc_praise` int(11) DEFAULT NULL,
  PRIMARY KEY (`mydiscuss_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mydiscuss
-- ----------------------------
INSERT INTO `mydiscuss` VALUES ('1', '1', '1', '22222', '2015-08-04', '222');

-- ----------------------------
-- Table structure for mynotes
-- ----------------------------
DROP TABLE IF EXISTS `mynotes`;
CREATE TABLE `mynotes` (
  `mynotes_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `mynote_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mynotes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mynotes
-- ----------------------------
INSERT INTO `mynotes` VALUES ('4', '4', '14', 'biji4');

-- ----------------------------
-- Table structure for myplan
-- ----------------------------
DROP TABLE IF EXISTS `myplan`;
CREATE TABLE `myplan` (
  `myplan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`myplan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myplan
-- ----------------------------
INSERT INTO `myplan` VALUES ('75', '4', '1');
INSERT INTO `myplan` VALUES ('76', '1', '2');
INSERT INTO `myplan` VALUES ('78', '2', '1');
INSERT INTO `myplan` VALUES ('80', '6', '22');
INSERT INTO `myplan` VALUES ('82', '7', '2');
INSERT INTO `myplan` VALUES ('83', '4', '3');
INSERT INTO `myplan` VALUES ('84', '4', '2');
INSERT INTO `myplan` VALUES ('85', '4', '5');
INSERT INTO `myplan` VALUES ('86', '4', '6');
INSERT INTO `myplan` VALUES ('87', '4', '7');
INSERT INTO `myplan` VALUES ('88', '4', '8');
INSERT INTO `myplan` VALUES ('89', '4', '9');

-- ----------------------------
-- Table structure for opinion
-- ----------------------------
DROP TABLE IF EXISTS `opinion`;
CREATE TABLE `opinion` (
  `opinion_id` int(11) NOT NULL AUTO_INCREMENT,
  `opin_useraddress` varchar(255) DEFAULT NULL,
  `opin_content` varchar(255) DEFAULT NULL,
  `opin_replycontent` varchar(255) DEFAULT NULL,
  `opin_date` date DEFAULT NULL,
  PRIMARY KEY (`opinion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of opinion
-- ----------------------------
INSERT INTO `opinion` VALUES ('1', 'dd', '数据1', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('2', 'dd', '数据2', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('3', 'dsadsad', '数据3', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('4', 'dsadsad', '数据4', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('5', '', '数据5', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('6', '', '数据6', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('7', '', '数据7', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('8', '', '数据8', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('9', '', '数据9', null, '2015-10-26');
INSERT INTO `opinion` VALUES ('10', 'DSADSA', '数据10', null, '2015-10-21');
INSERT INTO `opinion` VALUES ('11', null, '数据11', null, '2015-10-13');
INSERT INTO `opinion` VALUES ('12', '文陶', '我是不是超人', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('13', '的撒的撒的撒', '发的请问耳朵驱动器', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('14', '的撒打算的撒的', '的撒打算的撒打算的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('15', '的撒打算的撒', '打算的撒打算的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('16', '打算打算的撒的', '打算打算的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('17', '的撒的撒的', '达到撒的撒', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('18', '打算打算的', '的撒打算的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('19', '打算打算的', '的撒打算的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('20', '打算打算的', '的撒打算的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('21', '打算打算的', '的撒打算的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('22', '打算打算的', '的撒打算的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('23', '的撒撒的', '的撒打算打算的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('24', '的撒的撒的玩儿给我', '是的撒的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('25', '的撒的撒的玩儿给我', '是的撒的撒的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('26', '打算打算的撒', '打算打算的撒', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('27', '', '的撒打算的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('28', '打算的撒', '热情的请问的请问的', null, '2015-10-28');
INSERT INTO `opinion` VALUES ('29', '打算的撒', '热情的请问的请问的', null, '2015-10-28');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(255) DEFAULT NULL,
  `plan_content` varchar(255) DEFAULT NULL,
  `plan_img` varchar(255) DEFAULT NULL,
  `decoration_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES ('1', 'C++远征攻略', 'C++是在C语言的基础上开发的一种通用编程语言，应用广泛。C++支持多种编程范式 －－面向对象编程、泛型编程和过程化编程。C++语言灵活，运算符的数据结构丰富、具有结构化控制语句、程序执行效率高，而且同时具有高级语言与汇编语言的优点，与其它语言相比 ，可以直接访问物理地址，与汇编语言相比又具有良好的可读性和可移植性。 ', '13.jpg', '1');
INSERT INTO `plan` VALUES ('2', 'Oracle数据库开发必备利器', 'Oracle由于其良好的数据安全性和稳定性，在数据库领域一直处于领先地位。本计划针对数据库开发人员设计，从零开始对Oracle进行介绍。除了基本的SQL语法外，还为小伙伴们带来了数据库开发过程中必备的自定义函数和存储过程等内容。讲师们结合大量案例进行了深入浅出的讲解，一定会让你受益匪浅！ ', '1.jpg', '2');
INSERT INTO `plan` VALUES ('3', 'andorid', 'haha', '10.jpg', '3');
INSERT INTO `plan` VALUES ('4', 'css', null, '11.jpg', '3');
INSERT INTO `plan` VALUES ('5', 'js', null, '12.jpg', '2');
INSERT INTO `plan` VALUES ('6', 'jQuery', 'fgfgfgg', '16.jpg', '4');
INSERT INTO `plan` VALUES ('7', '鐭崇嫯甯�', '鐭崇嫯甯�', '2.jpg', '2');
INSERT INTO `plan` VALUES ('8', 'ss', 'ss', 'Jellyfish.jpg', '2');
INSERT INTO `plan` VALUES ('9', 'sss', 'sss', '2.jpg', '3');
INSERT INTO `plan` VALUES ('34', 'sssss', 'sssss', 'Tulips.jpg', '1');
INSERT INTO `plan` VALUES ('35', '111111', '111\r\n					', 'Penguins.jpg', '1');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `section_id` int(11) NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `sect_name` varchar(255) DEFAULT NULL COMMENT '节名',
  `chap_id` int(11) DEFAULT NULL COMMENT '章id',
  `section_img` varchar(255) DEFAULT NULL,
  `section_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('1', 'C语言入门', '6', '6.jpg', '进入编程世界的必修课-C语言');
INSERT INTO `section` VALUES ('2', 'Linux C语言编程基本原理与实践', '1', '7.jpg', 'C-最强编程语言的编程本质');
INSERT INTO `section` VALUES ('3', 'Linux C语言指针与内存', '1', '15.jpg', null);
INSERT INTO `section` VALUES ('4', 'Linux C语言结构体', '1', '16.jpg', null);
INSERT INTO `section` VALUES ('5', 'C++远征之起航篇', '2', '11.jpg', null);
INSERT INTO `section` VALUES ('6', 'C++远征之离港篇', '2', '12.jpg', null);
INSERT INTO `section` VALUES ('7', 'C++远征之封装篇（上）', '3', '13.jpg', null);
INSERT INTO `section` VALUES ('8', 'C++远征之封装篇（下）', '3', '17.jpg', null);
INSERT INTO `section` VALUES ('9', 'C++远征之模板篇', '4', '8.jpg', null);
INSERT INTO `section` VALUES ('10', '1Oracle数据库开发必备利器之SQL基础', '5', '', null);
INSERT INTO `section` VALUES ('11', 'Oracle数据库开发利器之函数', '5', null, null);
INSERT INTO `section` VALUES ('12', 'Oracle数据库开发必备利器之PL/SQL基础', '6', null, null);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '讲师id',
  `teacher_name` varchar(255) DEFAULT NULL COMMENT '讲师名',
  `teacher_contenet` varchar(255) DEFAULT NULL COMMENT '讲师内容',
  `teacher_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL,
  `user_job` varchar(255) DEFAULT NULL,
  `user_city` varchar(255) DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_sign` varchar(255) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '12343@qq.com', '1221010520', '123', '学生', '山西太原', 'nan ', '6', '1');
INSERT INTO `users` VALUES ('2', '14523554@qq.com', '看就业面嘘嘘么', '123', 'JAVA开发工程师', null, '男', '落·长虹大酒店呵呵', '1.png');
INSERT INTO `users` VALUES ('3', '1231234@qq.com', 'qweqwe', '123123', null, null, null, null, null);
INSERT INTO `users` VALUES ('4', '123123@qq.com', '123', '123', 'null', 'null', 'nan ', '啦啦啦', '54586653000151cd02200220-100-100.jpg');
INSERT INTO `users` VALUES ('5', '12345676@qq.com', '1323123123', '123123123123', null, null, null, null, null);
INSERT INTO `users` VALUES ('6', '12356@qq.com', '147177', 'vjjgffhk', null, null, null, null, null);
INSERT INTO `users` VALUES ('7', '111111@qq.com', 'hhh', '111111', null, null, null, null, null);
INSERT INTO `users` VALUES ('8', '132123@qq.com', '1234567890', 'udirorjfor', null, null, null, null, null);
