-- MySQL dump 10.14  Distrib 5.5.60-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: sculpture
-- ------------------------------------------------------
-- Server version	5.5.60-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (411),(411),(411),(411),(411);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_record`
--

DROP TABLE IF EXISTS `log_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_record` (
  `id` bigint(20) NOT NULL,
  `afternoon_hour` double DEFAULT NULL,
  `evening_hour` double DEFAULT NULL,
  `morning_hour` double DEFAULT NULL,
  `remark` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `total_hour` double DEFAULT NULL,
  `afternoon_project_id` bigint(20) DEFAULT NULL,
  `evening_project_id` bigint(20) DEFAULT NULL,
  `morning_project_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqa6rlcq94xp4wmsbktgyfufew` (`afternoon_project_id`),
  KEY `FKdvr13oxtvvgc3ca88x9x3tss1` (`evening_project_id`),
  KEY `FKg64ul795lk4igjfvg0570l46` (`morning_project_id`),
  KEY `FKf539src7bac868ou7iti1hsdg` (`user_id`),
  CONSTRAINT `FKdvr13oxtvvgc3ca88x9x3tss1` FOREIGN KEY (`evening_project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKf539src7bac868ou7iti1hsdg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKg64ul795lk4igjfvg0570l46` FOREIGN KEY (`morning_project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKqa6rlcq94xp4wmsbktgyfufew` FOREIGN KEY (`afternoon_project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_record`
--

LOCK TABLES `log_record` WRITE;
/*!40000 ALTER TABLE `log_record` DISABLE KEYS */;
INSERT INTO `log_record` VALUES (49,4,4,4,'加班4个小时工资200','2019-03-14 00:00:00.000000',12,48,48,48,45),(51,3,1,4,'','2019-03-13 00:00:00.000000',8,50,50,50,45),(68,4,0,4,'','2019-03-14 00:00:00.000000',8,67,67,67,14),(69,4,0,4,'','2019-03-13 00:00:00.000000',8,67,67,67,14),(71,4,1,4,'','2019-03-12 00:00:00.000000',9,67,67,67,14),(96,0,0,4,'','2019-03-14 00:00:00.000000',4,95,95,95,93),(106,0,0,1,'','2019-03-15 00:00:00.000000',1,95,95,95,93),(114,4,0,4,'','2019-03-18 00:00:00.000000',8,113,113,113,90),(115,4,0,4,'','2019-03-20 00:00:00.000000',8,113,113,113,90),(116,4,0,4,'','2019-03-19 00:00:00.000000',8,113,113,113,90),(133,4,2.5,4,'','2019-04-01 00:00:00.000000',10.5,132,132,132,128),(134,4,2.5,4,'','2019-04-02 00:00:00.000000',10.5,132,132,132,128),(140,4,2,4,'借6','2019-04-04 00:00:00.000000',10,139,139,139,137),(242,4,0,4,'','2019-04-01 00:00:00.000000',8,241,241,241,239),(244,4,0,4,'','2019-04-02 00:00:00.000000',8,243,243,243,239),(246,4,0,4,'','2019-04-03 00:00:00.000000',8,245,245,245,239),(249,4,0,4,'','2019-04-04 00:00:00.000000',8,248,248,248,239),(258,4,0,4,'','2019-04-06 00:00:00.000000',8,257,257,257,239),(260,4,0,4,'','2019-04-07 00:00:00.000000',8,259,259,259,239),(280,4,0,4,'','2019-04-10 00:00:00.000000',8,279,279,279,270),(285,4,0,4,'','2019-04-10 00:00:00.000000',8,284,284,284,271),(296,0,0,0,'','2019-04-11 00:00:00.000000',0,295,295,295,293),(301,4,0,4,'','2019-04-11 00:00:00.000000',8,284,284,284,271),(302,4,0,4,'','2019-04-12 00:00:00.000000',8,284,284,284,271),(305,4,0,4,'','2019-03-09 00:00:00.000000',8,304,304,304,271),(306,4,0,4,'','2019-03-10 00:00:00.000000',8,304,304,304,271),(307,4,0,4,'','2019-03-11 00:00:00.000000',8,304,304,304,271),(314,4,0,4,'','2019-02-18 00:00:00.000000',8,313,313,313,1),(315,4,0,4,'','2019-02-19 00:00:00.000000',8,313,313,313,1),(316,4,0,4,'','2019-02-20 00:00:00.000000',8,313,313,313,1),(317,4,0,4,'','2019-02-21 00:00:00.000000',8,313,313,313,1),(318,4,0,4,'','2019-02-22 00:00:00.000000',8,313,313,313,1),(319,4,0,4,'','2019-02-23 00:00:00.000000',8,313,313,313,1),(320,4,0,4,'','2019-02-24 00:00:00.000000',8,313,313,313,1),(321,4,0,4,'','2019-02-25 00:00:00.000000',8,313,313,313,1),(322,4,0,4,'','2019-02-26 00:00:00.000000',8,313,313,313,1),(323,4,0,4,'','2019-02-27 00:00:00.000000',8,313,313,313,1),(325,4,0,4,'','2019-02-28 00:00:00.000000',8,324,324,324,1),(326,4,0,4,'','2019-03-01 00:00:00.000000',8,324,324,324,1),(327,4,0,4,'','2019-03-02 00:00:00.000000',8,324,324,324,1),(328,4,0,4,'','2019-03-03 00:00:00.000000',8,324,324,324,1),(330,4,0,4,'','2019-04-02 00:00:00.000000',8,329,329,329,1),(331,4,0,4,'','2019-04-01 00:00:00.000000',8,329,329,329,1),(332,4,0,4,'','2019-03-31 00:00:00.000000',8,329,329,329,1),(333,4,0,4,'','2019-03-30 00:00:00.000000',8,329,329,329,1),(334,4,0,4,'','2019-03-29 00:00:00.000000',8,329,329,329,1),(335,4,0,4,'','2019-03-28 00:00:00.000000',8,329,329,329,1),(336,4,0,4,'','2019-03-27 00:00:00.000000',8,329,329,329,1),(337,4,0,4,'','2019-03-26 00:00:00.000000',8,329,329,329,1),(338,4,0,4,'','2019-03-25 00:00:00.000000',8,329,329,329,1),(339,4,0,4,'','2019-03-24 00:00:00.000000',8,329,329,329,1),(340,4,0,4,'','2019-03-23 00:00:00.000000',8,329,329,329,1),(341,4,0,4,'','2019-03-22 00:00:00.000000',8,329,329,329,1),(342,4,0,4,'','2019-03-21 00:00:00.000000',8,329,329,329,1),(343,4,0,4,'','2019-03-20 00:00:00.000000',8,329,329,329,1),(344,4,0,4,'','2019-03-19 00:00:00.000000',8,329,329,329,1),(345,4,0,4,'','2019-03-18 00:00:00.000000',8,329,329,329,1),(346,4,0,4,'','2019-03-17 00:00:00.000000',8,329,329,329,1),(347,4,0,4,'','2019-03-16 00:00:00.000000',8,329,329,329,1),(348,4,0,4,'','2019-03-15 00:00:00.000000',8,329,329,329,1),(349,4,0,4,'','2019-03-14 00:00:00.000000',8,329,329,329,1),(350,4,0,4,'','2019-03-13 00:00:00.000000',8,329,329,329,1),(351,4,0,4,'','2019-03-12 00:00:00.000000',8,329,329,329,1),(352,4,0,4,'','2019-03-11 00:00:00.000000',8,329,329,329,1),(353,4,0,4,'','2019-03-10 00:00:00.000000',8,329,329,329,1),(354,4,0,4,'','2019-03-09 00:00:00.000000',8,329,329,329,1),(357,4,0,4,'','2019-04-04 00:00:00.000000',8,355,355,355,1),(358,4,0,4,'','2019-04-05 00:00:00.000000',8,355,355,355,1),(359,4,0,4,'','2019-04-06 00:00:00.000000',8,355,355,355,1),(360,4,0,4,'','2019-04-07 00:00:00.000000',8,355,355,355,1),(361,4,0,4,'','2019-04-08 00:00:00.000000',8,355,355,355,1),(362,4,0,4,'','2019-04-09 00:00:00.000000',8,355,355,355,1),(363,4,0,4,'','2019-04-10 00:00:00.000000',8,355,355,355,1),(364,4,0,4,'','2019-04-11 00:00:00.000000',8,355,355,355,1),(365,4,0,4,'','2019-04-12 00:00:00.000000',8,355,355,355,1),(366,4,0,4,'','2019-04-13 00:00:00.000000',8,355,355,355,1),(367,4,0,4,'','2019-04-14 00:00:00.000000',8,355,355,355,1),(368,4,0,4,'','2019-04-15 00:00:00.000000',8,355,355,355,1),(383,4,1,4,'','2019-04-17 00:00:00.000000',9,382,382,382,380),(384,4,0,4,'','2019-04-16 00:00:00.000000',8,382,382,382,380),(385,4,0,4,'','2019-04-15 00:00:00.000000',8,382,382,382,380),(386,4,0,4,'','2019-04-14 00:00:00.000000',8,382,382,382,380),(387,4,0,4,'','2019-04-13 00:00:00.000000',8,382,382,382,380),(388,4,0,4,'','2019-04-12 00:00:00.000000',8,382,382,382,380),(389,4,0,4,'','2019-04-11 00:00:00.000000',8,382,382,382,380),(390,4,0,4,'','2019-04-10 00:00:00.000000',8,382,382,382,380),(391,4,0,4,'','2019-04-09 00:00:00.000000',8,382,382,382,380),(392,4,0,4,'','2019-04-08 00:00:00.000000',8,382,382,382,380),(393,4,0,4,'','2019-04-07 00:00:00.000000',8,382,382,382,380),(394,4,0,4,'','2019-04-06 00:00:00.000000',8,382,382,382,380),(395,4,0,4,'','2019-04-05 00:00:00.000000',8,382,382,382,380),(396,4,0,4,'','2019-04-04 00:00:00.000000',8,382,382,382,380),(397,4,0,4,'','2019-04-03 00:00:00.000000',8,382,382,382,380),(398,4,0,4,'','2019-04-02 00:00:00.000000',8,382,382,382,380),(399,4,0,4,'','2019-04-01 00:00:00.000000',8,382,382,382,380),(400,4,0,4,'','2019-03-31 00:00:00.000000',8,382,382,382,380),(401,4,0,4,'','2019-03-30 00:00:00.000000',8,382,382,382,380),(402,4,0,4,'','2019-03-29 00:00:00.000000',8,382,382,382,380),(403,4,0,4,'','2019-03-28 00:00:00.000000',8,382,382,382,380),(404,4,0,4,'','2019-03-27 00:00:00.000000',8,382,382,382,380),(405,4,0,4,'','2019-03-26 00:00:00.000000',8,382,382,382,380),(406,4,0,4,'','2019-04-16 00:00:00.000000',8,355,355,355,1),(408,4,0,4,'','2019-04-17 00:00:00.000000',8,355,355,355,1),(409,4,0,4,'','2019-04-18 00:00:00.000000',8,355,355,355,1);
/*!40000 ALTER TABLE `log_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `principal`
--

DROP TABLE IF EXISTS `principal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `principal` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK289q9fn9r6d7s998yoglptg0q` (`user_id`),
  CONSTRAINT `FK289q9fn9r6d7s998yoglptg0q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `principal`
--

LOCK TABLES `principal` WRITE;
/*!40000 ALTER TABLE `principal` DISABLE KEYS */;
INSERT INTO `principal` VALUES (15,'刘东',NULL,14),(46,'田甜',NULL,45),(47,'田总',NULL,45),(66,'主任',NULL,14),(94,'才！我',NULL,93),(112,'等等',NULL,90),(125,'1',NULL,124),(129,'看看',NULL,128),(130,'空军建',NULL,128),(131,'周芷若',NULL,128),(138,'王',NULL,137),(240,'朱廷兵',NULL,239),(273,'刘东',NULL,272),(274,'主任',NULL,272),(277,'刘东',NULL,270),(278,'王成伟',NULL,270),(281,'张宏志',NULL,14),(283,'刘东',NULL,271),(288,'田野',NULL,287),(294,'胡强伟',NULL,293),(303,'志博',NULL,271),(311,'刘东',NULL,1),(312,'刘成伟',NULL,1),(371,'李萌',NULL,370),(376,'汤兴东',NULL,375),(377,'曹红利',NULL,375),(381,'旭',NULL,380);
/*!40000 ALTER TABLE `principal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL,
  `actual_total_wages` decimal(19,2) DEFAULT NULL,
  `address` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `daily_wages` decimal(19,2) DEFAULT NULL,
  `expect_total_wages` decimal(19,2) DEFAULT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `total_work_hour` double DEFAULT NULL,
  `principal_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn70kexb68sk6uhq42bq6mg6xq` (`principal_id`),
  KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
  CONSTRAINT `FKn70kexb68sk6uhq42bq6mg6xq` FOREIGN KEY (`principal_id`) REFERENCES `principal` (`id`),
  CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (48,0.00,'长沙市达美D6区(长沙市岳麓区)','2019-03-14 13:10:43.000000',600.00,900.00,'圆雕',12,46,45),(50,0.00,'湖南省长沙市岳麓区茶子山路19号(政府对面长郡中学西侧)','2019-03-14 13:12:48.000000',500.00,500.00,'浮雕',8,47,45),(67,0.00,'马家庄村(廊坊市三河市)','2019-03-14 18:45:36.000000',400.00,1250.00,'老范',25,15,14),(95,0.00,'金域首府(邢台市桥西区永康街777号)','2019-03-14 22:26:19.000000',400.00,250.00,'格力空调安装',5,94,93),(113,0.00,'河北省唐山市遵化市北二环东路与海东路交汇处','2019-03-18 19:01:01.000000',100.00,300.00,'上课',24,112,90),(132,0.00,'碧桂园克拉广场(黄埔区摇田河大街77号)','2019-04-02 09:11:02.000000',145.00,380.63,'检测',21,131,128),(139,0.00,'辽宁省沈阳市于洪区元江街20-1号(白山路丁香湖大道交汇处)','2019-04-04 08:58:34.000000',300.00,375.00,'外墙漆',10,138,137),(241,0.00,'司前镇(江门市新会区)','2019-04-08 20:30:19.000000',340.00,340.00,'工资清单',8,240,239),(243,0.00,'司前镇(江门市新会区)','2019-04-08 20:33:06.000000',300.00,300.00,'工资清单',8,240,239),(245,0.00,'司前镇(江门市新会区)','2019-04-08 20:33:43.000000',320.00,320.00,'工资清单',8,240,239),(248,0.00,'司前镇(江门市新会区)','2019-04-08 20:36:36.000000',510.00,510.00,'工资清单',8,240,239),(257,0.00,'司前镇(江门市新会区)','2019-04-08 20:51:09.000000',470.00,470.00,'工资清单',8,240,239),(259,0.00,'司前镇(江门市新会区)','2019-04-08 20:51:55.000000',340.00,340.00,'工资清单',8,240,239),(279,0.00,'齐心庄收费站(廊坊市三河市)','2019-04-10 21:30:23.000000',450.00,450.00,'雕塑',8,277,270),(284,0.00,'齐心庄收费站(廊坊市三河市)','2019-04-10 23:08:48.000000',500.00,1500.00,'太行山雕塑',24,283,271),(295,0.00,'日喀则市达尔旺孜(日喀则市江孜县)','2019-04-11 09:22:57.000000',300.00,0.00,'四川恒宝建筑工程有限',0,294,293),(304,0.00,'河北省廊坊市三河市','2019-04-12 19:33:17.000000',500.00,1500.00,'志博雕塑',24,303,271),(313,0.00,'北京市通州区宋庄','2019-04-14 10:14:42.000000',400.00,4000.00,'宋庄小稿',80,311,1),(324,0.00,'北京市通州区宋庄','2019-04-14 10:16:22.000000',400.00,1600.00,'小稿',32,312,1),(329,0.00,'河北省廊坊市三河市鼎盛东大街5号','2019-04-14 10:22:40.000000',400.00,10000.00,'三河大稿',200,311,1),(355,0.00,'北京市通州区宋庄','2019-04-14 10:24:04.000000',400.00,6000.00,'宋庄小稿+大稿',120,311,1),(382,0.00,'天津市津南区丰泽二支路6号正北方向38米','2019-04-17 06:50:34.000000',165.00,3815.63,'上班',185,381,380);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `avatar_url` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ3hO7bE81waDEKU9GclqyMiaI85tGIVtWugiambDNbOVvMT5BRw1JsUkMPxia9AcQqzqRRPSrNa0h0w/132','oTzds5Scr_kAQO4q1FfJBGi_IAN8','oTzds5Scr_kAQO4q1FfJBGi_IAN8',NULL,'Sun_Fen'),(2,NULL,'oTzds5RohcSslvN4tOXiQeTUNr6g','oTzds5RohcSslvN4tOXiQeTUNr6g',NULL,NULL),(14,NULL,'oTzds5azuSMngolnoL84qqHCnFoA','oTzds5azuSMngolnoL84qqHCnFoA',NULL,NULL),(20,NULL,'oTzds5ZlrWgFvatVfl5cml6TwM48','oTzds5ZlrWgFvatVfl5cml6TwM48',NULL,NULL),(37,NULL,'oTzds5UtZyMrtWB-JJCqbZ9WnnQE','oTzds5UtZyMrtWB-JJCqbZ9WnnQE',NULL,NULL),(38,NULL,'oTzds5eEvAwjOoZOT8OlTrN3z7vg','oTzds5eEvAwjOoZOT8OlTrN3z7vg',NULL,NULL),(42,NULL,'oTzds5QDl1BD7tER91xG_vDor_A4','oTzds5QDl1BD7tER91xG_vDor_A4',NULL,NULL),(43,NULL,'oTzds5eYAyWUMV-wowoqmtOAf-cs','oTzds5eYAyWUMV-wowoqmtOAf-cs',NULL,NULL),(44,NULL,'oTzds5U1IImxjQe3mwRNqIsDvcPg','oTzds5U1IImxjQe3mwRNqIsDvcPg',NULL,NULL),(45,NULL,'oTzds5cuWOQWkNdJC38sVeyZYD9M','oTzds5cuWOQWkNdJC38sVeyZYD9M',NULL,NULL),(52,NULL,'oTzds5TD2uO2SCZ16YH4mz1vtlYM','oTzds5TD2uO2SCZ16YH4mz1vtlYM',NULL,NULL),(53,NULL,'oTzds5bk_WqXYtiJmbLZCATlaKiA','oTzds5bk_WqXYtiJmbLZCATlaKiA',NULL,NULL),(55,NULL,'oTzds5US1_HS_TIwGPh7prQilpng','oTzds5US1_HS_TIwGPh7prQilpng',NULL,NULL),(56,NULL,'oTzds5ehbdYR_Lpm12T-xDD4XQzI','oTzds5ehbdYR_Lpm12T-xDD4XQzI',NULL,NULL),(57,NULL,'oTzds5QrnnSj9Ig8jjjde3ycABrs','oTzds5QrnnSj9Ig8jjjde3ycABrs',NULL,NULL),(58,NULL,'oTzds5fdu8hHfPhfKCqjVTrVATSU','oTzds5fdu8hHfPhfKCqjVTrVATSU',NULL,NULL),(59,NULL,'oTzds5fmi9_kIGkdDGk7UTcs-pCo','oTzds5fmi9_kIGkdDGk7UTcs-pCo',NULL,NULL),(60,NULL,'oTzds5eNmI_rK_q_NopvvBzkxJHA','oTzds5eNmI_rK_q_NopvvBzkxJHA',NULL,NULL),(61,NULL,'oTzds5YFGd0iNXzbM1A92DUmf2gw','oTzds5YFGd0iNXzbM1A92DUmf2gw',NULL,NULL),(62,NULL,'oTzds5e-gpNv06Zu3EXg4qRC88UE','oTzds5e-gpNv06Zu3EXg4qRC88UE',NULL,NULL),(63,NULL,'oTzds5S7IcoFWwa84m4gUG8U3tz4','oTzds5S7IcoFWwa84m4gUG8U3tz4',NULL,NULL),(64,NULL,'oTzds5btQuTUlnQw5bDjaAfr7wA0','oTzds5btQuTUlnQw5bDjaAfr7wA0',NULL,NULL),(65,NULL,'oTzds5SXTfDncrcXJVYO7QtokIAY','oTzds5SXTfDncrcXJVYO7QtokIAY',NULL,NULL),(70,NULL,'oTzds5bSQyFLK7eOk3mYsSQulqJU','oTzds5bSQyFLK7eOk3mYsSQulqJU',NULL,NULL),(73,NULL,'oTzds5eqL6SbmvUJIetHRPgwOMLo','oTzds5eqL6SbmvUJIetHRPgwOMLo',NULL,NULL),(74,NULL,'oTzds5SvFi2XbWyj9Fs1NtzoQj1U','oTzds5SvFi2XbWyj9Fs1NtzoQj1U',NULL,NULL),(75,NULL,'oTzds5RU-C494AeejC-TRAZbXsdg','oTzds5RU-C494AeejC-TRAZbXsdg',NULL,NULL),(76,NULL,'oTzds5fvHGS1YsRivTP6xld3ih8o','oTzds5fvHGS1YsRivTP6xld3ih8o',NULL,NULL),(77,NULL,'oTzds5RImUhEwVIAvWdqNznIF95E','oTzds5RImUhEwVIAvWdqNznIF95E',NULL,NULL),(78,NULL,'oTzds5bBo8x3b3cY96ibkV43Gl_M','oTzds5bBo8x3b3cY96ibkV43Gl_M',NULL,NULL),(79,NULL,'oTzds5QZrDfPKWnd-Y6HkMetgxrI','oTzds5QZrDfPKWnd-Y6HkMetgxrI',NULL,NULL),(80,NULL,'oTzds5UfXl0_rLO4PU-I73YnJpBU','oTzds5UfXl0_rLO4PU-I73YnJpBU',NULL,NULL),(81,'https://wx.qlogo.cn/mmopen/vi_32/h4ZdXEEL8MrP07Q1TVWgJYuGt1qoOyfogXZuOszMf3Vbcj6JvyKibFMb5dU5DNt2lFnG8SMqoUOF5M9WAsOzKww/132','oTzds5eUaCokPmSCT-I25Qwj4BTI','oTzds5eUaCokPmSCT-I25Qwj4BTI',NULL,'秀爱'),(82,NULL,'oTzds5UrbReh4urbXmWbbdXtSYrM','oTzds5UrbReh4urbXmWbbdXtSYrM',NULL,NULL),(83,NULL,'oTzds5bbdpivtGrosBO9pPAt13oA','oTzds5bbdpivtGrosBO9pPAt13oA',NULL,NULL),(84,NULL,'oTzds5ckZOZK3fBPiVU3mdFmlqv4','oTzds5ckZOZK3fBPiVU3mdFmlqv4',NULL,NULL),(85,NULL,'oTzds5RNE_coVFyyDb0liDyPqL0o','oTzds5RNE_coVFyyDb0liDyPqL0o',NULL,NULL),(86,NULL,'oTzds5Sv-23XB9iVPpFWFqdjhPpM','oTzds5Sv-23XB9iVPpFWFqdjhPpM',NULL,NULL),(87,NULL,'oTzds5fdaWJgjWig1U0qdf4ydelQ','oTzds5fdaWJgjWig1U0qdf4ydelQ',NULL,NULL),(88,NULL,'oTzds5ZmtJMgnlOpaKokzTimbS-Y','oTzds5ZmtJMgnlOpaKokzTimbS-Y',NULL,NULL),(89,NULL,'oTzds5SzgW7Uk8VRzLIFdyPK4QI0','oTzds5SzgW7Uk8VRzLIFdyPK4QI0',NULL,NULL),(90,'https://wx.qlogo.cn/mmopen/vi_32/VFDjB7jbrX98q9RdSHJCZWeibBicKY6iapprp3GGnfsrzP5DIwMEibXD9eLibjSquiaBic1t1HD6L7ibwArQtpzYqcK41g/132','oTzds5dLErekYmkRoWBE6ukzBc3s','oTzds5dLErekYmkRoWBE6ukzBc3s',NULL,'..........'),(91,NULL,'oTzds5TxSj1qHE7ycs1crm5knj5Q','oTzds5TxSj1qHE7ycs1crm5knj5Q',NULL,NULL),(92,NULL,'oTzds5dpsbbhFxmp5SrEo7lERiWs','oTzds5dpsbbhFxmp5SrEo7lERiWs',NULL,NULL),(93,NULL,'oTzds5V-DBLkMUBSWJUwQGijyrhA','oTzds5V-DBLkMUBSWJUwQGijyrhA',NULL,NULL),(97,NULL,'oTzds5RmbboMSgVsbB4a1FGWoGBk','oTzds5RmbboMSgVsbB4a1FGWoGBk',NULL,NULL),(98,NULL,'oTzds5driu8BbO4McI6h2ICJ1wJU','oTzds5driu8BbO4McI6h2ICJ1wJU',NULL,NULL),(100,NULL,'oTzds5diC1562TxKDx_p6TvvdUh4','oTzds5diC1562TxKDx_p6TvvdUh4',NULL,NULL),(101,NULL,'oTzds5ZVS5UfMSz4bJuPJd09KGYM','oTzds5ZVS5UfMSz4bJuPJd09KGYM',NULL,NULL),(102,NULL,'oTzds5T8B5iwCh4SytgCjs6EoczI','oTzds5T8B5iwCh4SytgCjs6EoczI',NULL,NULL),(103,NULL,'oTzds5TgZbGwReSxFY8eXByBkTco','oTzds5TgZbGwReSxFY8eXByBkTco',NULL,NULL),(104,NULL,'oTzds5eR_R2-o9mcvTq-RXgCYvbM','oTzds5eR_R2-o9mcvTq-RXgCYvbM',NULL,NULL),(105,NULL,'oTzds5eP93SvT_2_iEzP_vrf5onY','oTzds5eP93SvT_2_iEzP_vrf5onY',NULL,NULL),(107,NULL,'oTzds5c4rROgIRLucePQlTjNY-x8','oTzds5c4rROgIRLucePQlTjNY-x8',NULL,NULL),(108,NULL,'oTzds5SGEZWaf1a80tkj6-1H9bNg','oTzds5SGEZWaf1a80tkj6-1H9bNg',NULL,NULL),(109,NULL,'oTzds5QHyiXD8hTA6t7JaVVg-IRk','oTzds5QHyiXD8hTA6t7JaVVg-IRk',NULL,NULL),(110,'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep9hn7icic9ZqbHKBBGt0ibx2HzahLmBSuoibpkxmrNCdTvJWSyRlzwV4dibugLb6P8t4wcP8XqPD4fEBQ/132','oTzds5TiZJJoOeI3uAEKSoNONN3I','oTzds5TiZJJoOeI3uAEKSoNONN3I',NULL,'雕塑记'),(111,NULL,'oTzds5Zx-TrTwZ5_JwOwpcDb_lnU','oTzds5Zx-TrTwZ5_JwOwpcDb_lnU',NULL,NULL),(117,NULL,'oTzds5XkawJaB14-VzEk2pB7D50o','oTzds5XkawJaB14-VzEk2pB7D50o',NULL,NULL),(118,NULL,'oTzds5V3Vs-0C3muUTVbHrqngPTs','oTzds5V3Vs-0C3muUTVbHrqngPTs',NULL,NULL),(119,NULL,'oTzds5e5qI-ZKGz1lC5ekcS1iSKo','oTzds5e5qI-ZKGz1lC5ekcS1iSKo',NULL,NULL),(120,NULL,'oTzds5UX0IVhCpC7TD65sMahzsNs','oTzds5UX0IVhCpC7TD65sMahzsNs',NULL,NULL),(121,NULL,'oTzds5XRelP8Jg4cUm2JJNgx-3NM','oTzds5XRelP8Jg4cUm2JJNgx-3NM',NULL,NULL),(122,NULL,'oTzds5Wl4-iHadgqrgxh7j5PnxRI','oTzds5Wl4-iHadgqrgxh7j5PnxRI',NULL,NULL),(123,'https://wx.qlogo.cn/mmhead/TxKKvLIgreysmNww4GZI5akAVODOoum82W9jaJwchOM/132','oTzds5ZBDIrTZ8iEnEYZZINfDqLg','oTzds5ZBDIrTZ8iEnEYZZINfDqLg',NULL,'林晋辛'),(124,NULL,'oTzds5brpW0omKqrKox57EkvhnZM','oTzds5brpW0omKqrKox57EkvhnZM',NULL,NULL),(127,NULL,'oTzds5b4VHtH83EfHS9bpupJhX08','oTzds5b4VHtH83EfHS9bpupJhX08',NULL,NULL),(128,NULL,'oTzds5RH_fsRDEUUsFsHz4UAOzLI','oTzds5RH_fsRDEUUsFsHz4UAOzLI',NULL,NULL),(136,NULL,'oTzds5YtIjfDafirRk8p-sXJfKU4','oTzds5YtIjfDafirRk8p-sXJfKU4',NULL,NULL),(137,NULL,'oTzds5YKfkSDQYnbAqqK7Jsbbsac','oTzds5YKfkSDQYnbAqqK7Jsbbsac',NULL,NULL),(141,NULL,'oTzds5Z7vsql-9v2SKtS0eX3Fwck','oTzds5Z7vsql-9v2SKtS0eX3Fwck',NULL,NULL),(142,NULL,'oTzds5TBRJEubwo7ZnISfB-ff08Y','oTzds5TBRJEubwo7ZnISfB-ff08Y',NULL,NULL),(143,NULL,'oTzds5bXRjIclPb_UU-4JLzI74wE','oTzds5bXRjIclPb_UU-4JLzI74wE',NULL,NULL),(144,NULL,'oTzds5TSMqaz0MaHPsLcRJc81pto','oTzds5TSMqaz0MaHPsLcRJc81pto',NULL,NULL),(145,NULL,'oTzds5REmLY5kvngj-oOdhfd8iNw','oTzds5REmLY5kvngj-oOdhfd8iNw',NULL,NULL),(146,NULL,'oTzds5X561CE2BCaiK4dq5b0wgak','oTzds5X561CE2BCaiK4dq5b0wgak',NULL,NULL),(147,NULL,'oTzds5fwsg3CSTzsE8vMw-GPitxc','oTzds5fwsg3CSTzsE8vMw-GPitxc',NULL,NULL),(148,NULL,'oTzds5Y2M-wVzOjIBh-YH1N3ftKg','oTzds5Y2M-wVzOjIBh-YH1N3ftKg',NULL,NULL),(149,NULL,'oTzds5Wvi6R3o2xFDrJcJxX8LULk','oTzds5Wvi6R3o2xFDrJcJxX8LULk',NULL,NULL),(150,NULL,'oTzds5SsEkiiZYcRyOmHF2XJ-t2c','oTzds5SsEkiiZYcRyOmHF2XJ-t2c',NULL,NULL),(151,NULL,'oTzds5f3VY1vGxWoWkYsD7HDdBdc','oTzds5f3VY1vGxWoWkYsD7HDdBdc',NULL,NULL),(152,NULL,'oTzds5Q8WJ1N3uVCnDn8W5UUY42w','oTzds5Q8WJ1N3uVCnDn8W5UUY42w',NULL,NULL),(153,NULL,'oTzds5b7N_nOPouhK0pjDb4KKXcI','oTzds5b7N_nOPouhK0pjDb4KKXcI',NULL,NULL),(154,NULL,'oTzds5R8duh6hzoDCPtuhivgufHE','oTzds5R8duh6hzoDCPtuhivgufHE',NULL,NULL),(155,NULL,'oTzds5eZbFI2vVc0aRApAWGk-Ajc','oTzds5eZbFI2vVc0aRApAWGk-Ajc',NULL,NULL),(156,NULL,'oTzds5ZU4zdYb5yoxnij3RSVyACs','oTzds5ZU4zdYb5yoxnij3RSVyACs',NULL,NULL),(157,NULL,'oTzds5fI0nJ5IHVa58lBnjEuSOuA','oTzds5fI0nJ5IHVa58lBnjEuSOuA',NULL,NULL),(158,NULL,'oTzds5eMgUrevJQ6V18dRN08us5Q','oTzds5eMgUrevJQ6V18dRN08us5Q',NULL,NULL),(159,NULL,'oTzds5flSfsamcjFCR1JynU0DG3o','oTzds5flSfsamcjFCR1JynU0DG3o',NULL,NULL),(160,NULL,'oTzds5e9Omx8NA9rKTdZYpe2H8pc','oTzds5e9Omx8NA9rKTdZYpe2H8pc',NULL,NULL),(161,NULL,'oTzds5R17aw95jUoumLISB9UhQhs','oTzds5R17aw95jUoumLISB9UhQhs',NULL,NULL),(162,NULL,'oTzds5Q--6D0PywdOP9IDHCAOeq0','oTzds5Q--6D0PywdOP9IDHCAOeq0',NULL,NULL),(163,NULL,'oTzds5ZSce2v_Lkip-DTdT1W26Ms','oTzds5ZSce2v_Lkip-DTdT1W26Ms',NULL,NULL),(164,NULL,'oTzds5ZjPmM-at5Me04Bi0AXlPH0','oTzds5ZjPmM-at5Me04Bi0AXlPH0',NULL,NULL),(165,NULL,'oTzds5UKVsSeoUqqJ-dMUVwUvFl4','oTzds5UKVsSeoUqqJ-dMUVwUvFl4',NULL,NULL),(166,NULL,'oTzds5SrcAwtzA1HLCMW-liiYl4I','oTzds5SrcAwtzA1HLCMW-liiYl4I',NULL,NULL),(167,NULL,'oTzds5QH5KDjFLe7TT2ITb3GYpto','oTzds5QH5KDjFLe7TT2ITb3GYpto',NULL,NULL),(168,NULL,'oTzds5dzLRB0T1AGJ4PNfHhatt4o','oTzds5dzLRB0T1AGJ4PNfHhatt4o',NULL,NULL),(169,NULL,'oTzds5aIFbVLxXqueNvyNg5q0pqg','oTzds5aIFbVLxXqueNvyNg5q0pqg',NULL,NULL),(170,NULL,'oTzds5aiWnbtCFHJZRkVbtRYfYkM','oTzds5aiWnbtCFHJZRkVbtRYfYkM',NULL,NULL),(171,NULL,'oTzds5fUASSa8wDe-_Jnnkdz5BFM','oTzds5fUASSa8wDe-_Jnnkdz5BFM',NULL,NULL),(172,NULL,'oTzds5SK8M70682krrxrCxRguSFA','oTzds5SK8M70682krrxrCxRguSFA',NULL,NULL),(173,NULL,'oTzds5Wh8KPNCREdR4zJVZi9v7bQ','oTzds5Wh8KPNCREdR4zJVZi9v7bQ',NULL,NULL),(174,NULL,'oTzds5asp0sBudUytCbNZeBRKw-c','oTzds5asp0sBudUytCbNZeBRKw-c',NULL,NULL),(175,NULL,'oTzds5WV3ecbf0yCuvk9frIe98-8','oTzds5WV3ecbf0yCuvk9frIe98-8',NULL,NULL),(176,NULL,'oTzds5Uu-pthEqmTnhPepLThTujo','oTzds5Uu-pthEqmTnhPepLThTujo',NULL,NULL),(177,NULL,'oTzds5bX7_pMgx4Rzp-C-heW9Yog','oTzds5bX7_pMgx4Rzp-C-heW9Yog',NULL,NULL),(178,NULL,'oTzds5e-rvL9_dIDcQFIgGBx3CTA','oTzds5e-rvL9_dIDcQFIgGBx3CTA',NULL,NULL),(179,NULL,'oTzds5Zg3LDe7K0CGFK3AoLE4NAU','oTzds5Zg3LDe7K0CGFK3AoLE4NAU',NULL,NULL),(180,NULL,'oTzds5Qchpxws6l2KKot3e0Yt7oE','oTzds5Qchpxws6l2KKot3e0Yt7oE',NULL,NULL),(181,NULL,'oTzds5WHNY569fYXlj21AMAmVHUY','oTzds5WHNY569fYXlj21AMAmVHUY',NULL,NULL),(182,NULL,'oTzds5UdTaFLt_ZSaXt1D9v1x43E','oTzds5UdTaFLt_ZSaXt1D9v1x43E',NULL,NULL),(183,NULL,'oTzds5QZj8WjyXguTGCRwQ7qgMZ8','oTzds5QZj8WjyXguTGCRwQ7qgMZ8',NULL,NULL),(184,NULL,'oTzds5Z-2F5gIh_qoKgBQqNuVrF8','oTzds5Z-2F5gIh_qoKgBQqNuVrF8',NULL,NULL),(185,NULL,'oTzds5aXhpY4YVPfmLimRVqDHqdo','oTzds5aXhpY4YVPfmLimRVqDHqdo',NULL,NULL),(186,NULL,'oTzds5VxkvUrV67a4f_P_SlTrjZw','oTzds5VxkvUrV67a4f_P_SlTrjZw',NULL,NULL),(187,NULL,'oTzds5cPN9OnWvdQkGDf_EA31nx8','oTzds5cPN9OnWvdQkGDf_EA31nx8',NULL,NULL),(188,NULL,'oTzds5aSF7DEubEPIcWfU-_ZcbMI','oTzds5aSF7DEubEPIcWfU-_ZcbMI',NULL,NULL),(189,NULL,'oTzds5T8G_E26UFDGH1Ga5w4lPkk','oTzds5T8G_E26UFDGH1Ga5w4lPkk',NULL,NULL),(190,NULL,'oTzds5VGaBoIYzTr76ylwz-F-fUY','oTzds5VGaBoIYzTr76ylwz-F-fUY',NULL,NULL),(191,NULL,'oTzds5Z42FRE8e4uIcAXgZ4vVmHk','oTzds5Z42FRE8e4uIcAXgZ4vVmHk',NULL,NULL),(192,NULL,'oTzds5RW1hcmrWjc3QV97BqgbdrQ','oTzds5RW1hcmrWjc3QV97BqgbdrQ',NULL,NULL),(193,NULL,'oTzds5RnwzcAE19Y7xGo6sSU5qoA','oTzds5RnwzcAE19Y7xGo6sSU5qoA',NULL,NULL),(194,NULL,'oTzds5fGQb6e6nneOm0rK1AqK2ZY','oTzds5fGQb6e6nneOm0rK1AqK2ZY',NULL,NULL),(195,NULL,'oTzds5XOsRVpRZA9By6hu2XWLKo4','oTzds5XOsRVpRZA9By6hu2XWLKo4',NULL,NULL),(196,NULL,'oTzds5awzifBbDGGbJuczlv89950','oTzds5awzifBbDGGbJuczlv89950',NULL,NULL),(197,NULL,'oTzds5TS09eYz05js_W8-Q9EPXoU','oTzds5TS09eYz05js_W8-Q9EPXoU',NULL,NULL),(198,NULL,'oTzds5XfnNqeIXmTm_JDhQycFCNM','oTzds5XfnNqeIXmTm_JDhQycFCNM',NULL,NULL),(199,NULL,'oTzds5cUfnaDnRLD5oS3E8nRJRSY','oTzds5cUfnaDnRLD5oS3E8nRJRSY',NULL,NULL),(200,NULL,'oTzds5ZVuQXlhuSMN9Cy9qoSAFt0','oTzds5ZVuQXlhuSMN9Cy9qoSAFt0',NULL,NULL),(201,NULL,'oTzds5QT10aSHqfLR9bbkgcs391A','oTzds5QT10aSHqfLR9bbkgcs391A',NULL,NULL),(202,NULL,'oTzds5bkEe6flRqEstGEmLuA2sIA','oTzds5bkEe6flRqEstGEmLuA2sIA',NULL,NULL),(203,NULL,'oTzds5QH8G_fDVeJD4ISDu371YlQ','oTzds5QH8G_fDVeJD4ISDu371YlQ',NULL,NULL),(204,NULL,'oTzds5VLoslgwT_5fDko34JiqioU','oTzds5VLoslgwT_5fDko34JiqioU',NULL,NULL),(205,NULL,'oTzds5RS-vGm9_vgsx1mrbTrDUJ4','oTzds5RS-vGm9_vgsx1mrbTrDUJ4',NULL,NULL),(206,NULL,'oTzds5R-vDT0yR0RzRQBYMSAZuiw','oTzds5R-vDT0yR0RzRQBYMSAZuiw',NULL,NULL),(207,NULL,'oTzds5RYZ8PJvzaAg7wi885yPjvg','oTzds5RYZ8PJvzaAg7wi885yPjvg',NULL,NULL),(208,NULL,'oTzds5a2_n464CFSXB8Ei7d80llQ','oTzds5a2_n464CFSXB8Ei7d80llQ',NULL,NULL),(209,NULL,'oTzds5WjBNfhuPE1Mq7iGMHTjgiI','oTzds5WjBNfhuPE1Mq7iGMHTjgiI',NULL,NULL),(210,NULL,'oTzds5X3KfBbqj_Rb-TXoe7SdUsM','oTzds5X3KfBbqj_Rb-TXoe7SdUsM',NULL,NULL),(211,NULL,'oTzds5Y6zEG00mLizgwWFRuaHYiE','oTzds5Y6zEG00mLizgwWFRuaHYiE',NULL,NULL),(212,NULL,'oTzds5Wt6hGbyfPJZZwzE4YjGtjI','oTzds5Wt6hGbyfPJZZwzE4YjGtjI',NULL,NULL),(213,NULL,'oTzds5ZF1GjDTiulNwyY6tYrRl94','oTzds5ZF1GjDTiulNwyY6tYrRl94',NULL,NULL),(214,NULL,'oTzds5WHppPetTRRVlbLZE6lwH6g','oTzds5WHppPetTRRVlbLZE6lwH6g',NULL,NULL),(215,NULL,'oTzds5f-tvKjkhD0EbrP2zvMrxiw','oTzds5f-tvKjkhD0EbrP2zvMrxiw',NULL,NULL),(216,NULL,'oTzds5c2kdG_rrvxjnIpkYtLNhe4','oTzds5c2kdG_rrvxjnIpkYtLNhe4',NULL,NULL),(217,NULL,'oTzds5cmRZ1MuT9wkseXprihz6dE','oTzds5cmRZ1MuT9wkseXprihz6dE',NULL,NULL),(218,NULL,'oTzds5asptsuw36XGAj1lfAqc6Zw','oTzds5asptsuw36XGAj1lfAqc6Zw',NULL,NULL),(219,NULL,'oTzds5fKmzwPOpWfHXVtxvDdpYoM','oTzds5fKmzwPOpWfHXVtxvDdpYoM',NULL,NULL),(220,NULL,'oTzds5cnk4Sd7I1B0tO9phl50mno','oTzds5cnk4Sd7I1B0tO9phl50mno',NULL,NULL),(221,NULL,'oTzds5ct8vP5PRwkFLPUJ4ASTGwE','oTzds5ct8vP5PRwkFLPUJ4ASTGwE',NULL,NULL),(222,NULL,'oTzds5TgB1NTyAt02fuY_onWsFLA','oTzds5TgB1NTyAt02fuY_onWsFLA',NULL,NULL),(223,NULL,'oTzds5VW8Pv7yUCc6QkILfJFdKJE','oTzds5VW8Pv7yUCc6QkILfJFdKJE',NULL,NULL),(224,NULL,'oTzds5TWh9luOaUT6KmJAIDcDt7Q','oTzds5TWh9luOaUT6KmJAIDcDt7Q',NULL,NULL),(225,NULL,'oTzds5alm0THB_t0_JV9mPNmd0YU','oTzds5alm0THB_t0_JV9mPNmd0YU',NULL,NULL),(226,NULL,'oTzds5W6Yu3JI1fgaHpQq7eS-Qc4','oTzds5W6Yu3JI1fgaHpQq7eS-Qc4',NULL,NULL),(227,NULL,'oTzds5SaveBNVoid9ARks2JxqDWc','oTzds5SaveBNVoid9ARks2JxqDWc',NULL,NULL),(228,NULL,'oTzds5SJTbx1R2-sFnqF6NpMYNbI','oTzds5SJTbx1R2-sFnqF6NpMYNbI',NULL,NULL),(229,NULL,'oTzds5dZY9Yq9D-_jr-2kMRrrebw','oTzds5dZY9Yq9D-_jr-2kMRrrebw',NULL,NULL),(230,NULL,'oTzds5U_mIL-x3IXGPiG30SpM_1w','oTzds5U_mIL-x3IXGPiG30SpM_1w',NULL,NULL),(231,NULL,'oTzds5dP9icfL5HZ0s3DUNR5aBIg','oTzds5dP9icfL5HZ0s3DUNR5aBIg',NULL,NULL),(232,NULL,'oTzds5QRc0TU28MTwobXa1sDATac','oTzds5QRc0TU28MTwobXa1sDATac',NULL,NULL),(233,NULL,'oTzds5cdKybD_6C9OaxkxmjPnUR4','oTzds5cdKybD_6C9OaxkxmjPnUR4',NULL,NULL),(234,NULL,'oTzds5Tt2KtM_GwQzMrX4-7Na1eE','oTzds5Tt2KtM_GwQzMrX4-7Na1eE',NULL,NULL),(235,NULL,'oTzds5Xtg6Dd2aXXf7p_9MZGHlr4','oTzds5Xtg6Dd2aXXf7p_9MZGHlr4',NULL,NULL),(236,NULL,'oTzds5ae_B97kqOLv5xqcC6i-fPU','oTzds5ae_B97kqOLv5xqcC6i-fPU',NULL,NULL),(237,NULL,'oTzds5WZAiVjc4bZvHC5xTaYcprM','oTzds5WZAiVjc4bZvHC5xTaYcprM',NULL,NULL),(238,NULL,'oTzds5azsNACoJHDDIpo8VfuqOeU','oTzds5azsNACoJHDDIpo8VfuqOeU',NULL,NULL),(239,NULL,'oTzds5bJupjvot-uXndtzZSHH4eA','oTzds5bJupjvot-uXndtzZSHH4eA',NULL,NULL),(264,NULL,'oTzds5X8aR64YrTK53P5sYF-qS4w','oTzds5X8aR64YrTK53P5sYF-qS4w',NULL,NULL),(265,NULL,'oTzds5daU46ELizR87ea273RdGwo','oTzds5daU46ELizR87ea273RdGwo',NULL,NULL),(266,NULL,'oTzds5dn-R923x2QgBGLrGHPNVAo','oTzds5dn-R923x2QgBGLrGHPNVAo',NULL,NULL),(267,NULL,'oTzds5edY-OEyXtCzYvajfHCQIts','oTzds5edY-OEyXtCzYvajfHCQIts',NULL,NULL),(268,NULL,'oTzds5cQb-1azIwgyHxuyUSAF0gc','oTzds5cQb-1azIwgyHxuyUSAF0gc',NULL,NULL),(269,NULL,'oTzds5Zv4Kg2dDL5UybgPNuDsQQc','oTzds5Zv4Kg2dDL5UybgPNuDsQQc',NULL,NULL),(270,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLWMx3KMBra5YgXaefwsm3lO6wqVdG3iat3RatpibW0HsPj8VrTCD1rpozju8N621ib2rHLDQ1FXaSWg/132','oTzds5bBFGMzUPjhD8I3WoQHl_0c','oTzds5bBFGMzUPjhD8I3WoQHl_0c',NULL,'高文超'),(271,NULL,'oTzds5RmoKSPDUgONCZ-6iO_bQ14','oTzds5RmoKSPDUgONCZ-6iO_bQ14',NULL,NULL),(272,NULL,'oTzds5VKcrWETxTjVoIaYIStg-Ho','oTzds5VKcrWETxTjVoIaYIStg-Ho',NULL,NULL),(275,'https://wx.qlogo.cn/mmopen/vi_32/IHYMTv5cib5MROdfiaW2N7FB5kc8EiahP52m2Jh9pgsMEGwvQDicbTicibUlh6Qxwr40ibHhJXiaAtXaUItI5u4INnicWZQ/132','oTzds5UEjghVZQ18ccpO_VoEveGM','oTzds5UEjghVZQ18ccpO_VoEveGM',NULL,'清风何处不为家'),(276,NULL,'oTzds5YIgn6toLgFHRmyavwo4wKg','oTzds5YIgn6toLgFHRmyavwo4wKg',NULL,NULL),(282,NULL,'oTzds5eMTgZdGPeRx1G4sTZj0qaA','oTzds5eMTgZdGPeRx1G4sTZj0qaA',NULL,NULL),(286,NULL,'oTzds5feTRr6Xa9Q6Dg9u9FYBLOg','oTzds5feTRr6Xa9Q6Dg9u9FYBLOg',NULL,NULL),(287,NULL,'oTzds5S7OLChm7VnroA2N6T-T4l4','oTzds5S7OLChm7VnroA2N6T-T4l4',NULL,NULL),(289,NULL,'oTzds5cVeRmxk39wPFMpTn9WS2cU','oTzds5cVeRmxk39wPFMpTn9WS2cU',NULL,NULL),(293,'https://wx.qlogo.cn/mmopen/vi_32/nQAy9pfNPRykTs5XPooHu4nlrqtFIK5Hcj7ibOrvDeloA3rSXzicBQrmqTos5ns2zaNia9pdVLNml6UeReYdAy5PQ/132','oTzds5VA7HvurDT63fc_7xCIPR0s','oTzds5VA7HvurDT63fc_7xCIPR0s',NULL,'燊麦王子坊'),(297,NULL,'oTzds5TaS4J_9uXlmX3CQOJpcg0E','oTzds5TaS4J_9uXlmX3CQOJpcg0E',NULL,NULL),(308,NULL,'oTzds5VOnPZEzBtXV4qip-0u_yEQ','oTzds5VOnPZEzBtXV4qip-0u_yEQ',NULL,NULL),(309,NULL,'oTzds5W-SMPuv92BUuVpiMgC7rYc','oTzds5W-SMPuv92BUuVpiMgC7rYc',NULL,NULL),(310,NULL,'oTzds5VrdgPw6geG07sMoo3Sx_LI','oTzds5VrdgPw6geG07sMoo3Sx_LI',NULL,NULL),(369,NULL,'oTzds5dX1nD2CIUfIK1b7FPsNA_U','oTzds5dX1nD2CIUfIK1b7FPsNA_U',NULL,NULL),(370,'https://wx.qlogo.cn/mmopen/vi_32/eSY3Mr6bVlBGvYLJ57JPBibjZ2OV6zTzeMD52OpAscpenplKw1F0tQjdmhKP5fpWukva0iaibYIlRTCml3wSU0XVg/132','oTzds5f-qJB6WFDFNTSUQPtfa_Qw','oTzds5f-qJB6WFDFNTSUQPtfa_Qw',NULL,'蝶恋花香'),(372,NULL,'oTzds5Zdccim1V2H2PUipEtBKf3c','oTzds5Zdccim1V2H2PUipEtBKf3c',NULL,NULL),(373,NULL,'oTzds5fxMVcrqSDKmaRRKZSKagxA','oTzds5fxMVcrqSDKmaRRKZSKagxA',NULL,NULL),(374,NULL,'oTzds5YFgEaazxQGxbtUWRZoBL74','oTzds5YFgEaazxQGxbtUWRZoBL74',NULL,NULL),(375,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJuhpNLj1j5iafm6tpFR0ogRpDjpHlVgibDY1AOl03yPaoS72JH3rL8c1MI47rrjicy3DqvfYBSUOdrQ/132','oTzds5eAMskdZ6YmxDGj82fvQO8w','oTzds5eAMskdZ6YmxDGj82fvQO8w',NULL,' 汤 兴 东 '),(380,NULL,'oTzds5cck_gKqhU5UMbWbE00BE7E','oTzds5cck_gKqhU5UMbWbE00BE7E',NULL,NULL),(407,NULL,'oTzds5ZhujqKIw1HKvPloIOSN-lk','oTzds5ZhujqKIw1HKvPloIOSN-lk',NULL,NULL),(410,NULL,'oTzds5bqR9bkKaZIsABSfqUaAthQ','oTzds5bqR9bkKaZIsABSfqUaAthQ',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wages_record`
--

DROP TABLE IF EXISTS `wages_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wages_record` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `method` int(11) NOT NULL,
  `remark` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `wages` decimal(19,2) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2sal3rbgoegghebmkpwh8949a` (`project_id`),
  CONSTRAINT `FK2sal3rbgoegghebmkpwh8949a` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wages_record`
--

LOCK TABLES `wages_record` WRITE;
/*!40000 ALTER TABLE `wages_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `wages_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-19 14:08:49
