-- MySQL dump 10.13  Distrib 8.0.17, for osx10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: deemo
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Accessory`
--

DROP TABLE IF EXISTS `Accessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Accessory` (
  `accessoryId` bigint(20) unsigned NOT NULL,
  `fileId` bigint(20) unsigned NOT NULL,
  `accessoryName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `accessoryPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `accessoryTypeId` bigint(20) unsigned NOT NULL,
  `accessorySize` int(10) unsigned NOT NULL DEFAULT '0',
  `createDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`accessoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Accessory`
--

LOCK TABLES `Accessory` WRITE;
/*!40000 ALTER TABLE `Accessory` DISABLE KEYS */;
/*!40000 ALTER TABLE `Accessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Branch`
--

DROP TABLE IF EXISTS `Branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Branch` (
  `branchId` bigint(20) unsigned NOT NULL,
  `branchName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `branchShortName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`branchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Branch`
--

LOCK TABLES `Branch` WRITE;
/*!40000 ALTER TABLE `Branch` DISABLE KEYS */;
INSERT INTO `Branch` VALUES (4709478423330816,'诺克萨斯','诺克'),(4709597210214400,'疾风剑豪','亚索'),(4722022584156160,'德玛西亚之力-盖伦','德玛'),(4789652376518656,'Customer System Development','CSD');
/*!40000 ALTER TABLE `Branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Department` (
  `departmentId` bigint(20) unsigned NOT NULL,
  `departmentName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `connectPhone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `connectTelephone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `principalUserId` bigint(20) unsigned NOT NULL,
  `branchId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES (4717142704390144,'牛逼部门','18526325350','0086001',4715897054822400,4709597210214400),(4717361437343744,'收钱部门','18526325350','0086001',4715897054822400,4709597210214400),(4717381758746624,'挨打部门','18526325350','0086001',4715897054822400,4709597210214400),(4718641199185920,'加油部门','18526325350','0086001',4715897054822400,4709597210214400),(4718661759664128,'做饭部门','18526325350','0086001',4715897054822400,4709597210214400),(4718691837018112,'洗碗部门','18526325350','0086001',4715897054822400,4709597210214400),(4718712607211520,'扫地部门','18526325350','0086001',4715897054822400,4709597210214400);
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FileInfo`
--

DROP TABLE IF EXISTS `FileInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FileInfo` (
  `fileId` bigint(20) unsigned NOT NULL,
  `fileName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `filePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `fileTypeId` bigint(20) unsigned NOT NULL,
  `fileOwnerId` bigint(20) unsigned NOT NULL,
  `parentId` bigint(20) unsigned NOT NULL,
  `uploadTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `isDeleted` tinyint(4) NOT NULL DEFAULT '0',
  `parentIdInTrash` bigint(20) unsigned NOT NULL,
  `deleteTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FileInfo`
--

LOCK TABLES `FileInfo` WRITE;
/*!40000 ALTER TABLE `FileInfo` DISABLE KEYS */;
INSERT INTO `FileInfo` VALUES (273734225100800,'文件管理','',2458562643099648,4715897054822400,0,'2019-08-23 15:05:31','文件管理根目录',0,0,'2019-08-29 14:23:19'),(273734225100889,'回收站','',2458562643099648,4715897054822400,0,'2019-08-27 10:04:36','回收站根目录',0,0,'2019-08-29 14:23:19'),(2543243162025984,'文件夹','/Users/hasaker/Desktop/Deemo/Files/文件夹',2458562643099648,4715897054822400,273734225100800,'2019-08-28 17:15:42','这是一个文件夹',0,2543243162025984,'2019-08-29 14:23:19'),(2543472582066176,'文件夹2','/Users/hasaker/Desktop/Deemo/Files/文件夹2',2458562643099648,4715897054822400,273734225100800,'2019-08-28 17:16:37','这是一个文件夹2',0,273734225100800,'2019-08-29 14:23:19'),(2543951856795648,'文件2.jpg','/Users/hasaker/Desktop/Deemo/Files/文件2.jpg',2458562466938880,4715897054822400,273734225100800,'2019-08-28 17:18:31','这是一个文件2',0,273734225100800,'2019-08-29 14:23:19'),(2543967245697024,'文件1.jpg','/Users/hasaker/Desktop/Deemo/Files/文件1.jpg',2458562466938880,4715897054822400,273734225100800,'2019-08-28 17:18:35','这是一个文件2',0,273734225100800,'2019-08-29 14:23:19'),(2543998661033984,'文件3.xlsx','/Users/hasaker/Desktop/Deemo/Files/文件3.xlsx',2458562408218624,4715897054822400,273734225100800,'2019-08-28 17:18:42','这是一个文件2',0,273734225100800,'2019-08-29 14:23:19'),(2544093049651200,'文件4.xlsx','/Users/hasaker/Desktop/Deemo/Files/文件4.xlsx',2458562408218624,4715897054822400,2543243162025984,'2019-08-28 17:19:05','这是一个文件2',0,2543243162025984,'2019-08-29 14:23:19'),(2544125391929344,'文件5.ppt','/Users/hasaker/Desktop/Deemo/Files/文件5.ppt',2458562521464832,4715897054822400,2543243162025984,'2019-08-28 17:19:13','这是一个文件2',0,2543243162025984,'2019-08-29 14:23:19'),(2544166298976256,'文件6.pdf','/Users/hasaker/Desktop/Deemo/Files/文件6.pdf',2458562479521792,4715897054822400,2543243162025984,'2019-08-28 17:19:22','这是一个文件2',0,2543243162025984,'2019-08-29 14:23:19'),(2544764712910848,'文件7.pdf','/Users/hasaker/Desktop/Deemo/Files/文件7.pdf',2458562479521792,4715897054822400,2543472582066176,'2019-08-28 17:21:45','这是一个文件2',0,2543472582066176,'2019-08-29 14:23:19');
/*!40000 ALTER TABLE `FileInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FileType`
--

DROP TABLE IF EXISTS `FileType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FileType` (
  `fileTypeId` bigint(20) unsigned NOT NULL,
  `fileTypeName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `fileTypeImage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `fileTypeSuffix` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`fileTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FileType`
--

LOCK TABLES `FileType` WRITE;
/*!40000 ALTER TABLE `FileType` DISABLE KEYS */;
INSERT INTO `FileType` VALUES (2458559249907712,'Excel文档','classpath:/file_type_icons/excel.png','xls'),(2458562408218624,'Excel文档','classpath:/file_type_icons/excel.png','xlsx'),(2458562450161664,'其它','classpath:/file_type_icons/file.png','*'),(2458562466938880,'图片','classpath:/file_type_icons/jpg.png','jpg'),(2458562479521792,'PDF文档','classpath:/file_type_icons/pdf.png','pdf'),(2458562496299008,'图片','classpath:/file_type_icons/png.png','png'),(2458562521464832,'PPT文档','classpath:/file_type_icons/ppt.png','ppt'),(2458562538242048,'纯文本文档','classpath:/file_type_icons/txt.png','txt'),(2458562555019264,'Word文档','classpath:/file_type_icons/word.png','doc'),(2458562575990784,'Word文档','classpath:/file_type_icons/word.png','docx'),(2458562584379392,'压缩包','classpath:/file_type_icons/zip.png','zip'),(2458562613739520,'压缩包','classpath:/file_type_icons/zip.png','rar'),(2458562630516736,'压缩包','classpath:/file_type_icons/zip.png','7z'),(2458562643099648,'文件夹','classpath:/file_type_icons/folder.png','');
/*!40000 ALTER TABLE `FileType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoginLog`
--

DROP TABLE IF EXISTS `LoginLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LoginLog` (
  `loginId` bigint(20) unsigned NOT NULL,
  `isSuccess` tinyint(4) NOT NULL DEFAULT '0',
  `userId` bigint(20) unsigned NOT NULL,
  `loginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `loginIp` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `loginDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`loginId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginLog`
--

LOCK TABLES `LoginLog` WRITE;
/*!40000 ALTER TABLE `LoginLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `LoginLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ManualSign`
--

DROP TABLE IF EXISTS `ManualSign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ManualSign` (
  `manualSignId` bigint(20) unsigned NOT NULL,
  `userId` bigint(20) unsigned NOT NULL,
  `signTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `signDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `signTag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`manualSignId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ManualSign`
--

LOCK TABLES `ManualSign` WRITE;
/*!40000 ALTER TABLE `ManualSign` DISABLE KEYS */;
INSERT INTO `ManualSign` VALUES (4725314722725888,4715897054822400,'2019-09-03 17:46:29','签到啦啦啦',0),(4776686566506496,4715897054822400,'2019-09-03 21:10:37','啦啦啦',0),(4776722171953152,4715897054822400,'2019-09-03 21:10:45','啦啦啦',0),(4776731881766912,4715897054822400,'2019-09-03 21:10:48','啦啦啦',0),(4776731974041600,4715897054822400,'2019-09-03 21:10:48','啦啦啦',0),(4776732557049856,4715897054822400,'2019-09-03 21:10:48','啦啦啦',0),(4776733299441664,4715897054822400,'2019-09-03 21:10:48','啦啦啦',0),(4776733962141696,4715897054822400,'2019-09-03 21:10:48','啦啦啦',0),(4776734650007552,4715897054822400,'2019-09-03 21:10:48','啦啦啦',0),(4776735300124672,4715897054822400,'2019-09-03 21:10:48','修改测试',0),(4776735946047488,4715897054822400,'2019-09-03 21:10:49','啦啦啦',0),(4776736587776000,4715897054822400,'2019-09-03 21:10:49','啦啦啦',0),(4776737162395648,4715897054822400,'2019-09-03 21:10:49','啦啦啦',0),(4776737875427328,4715897054822400,'2019-09-03 21:10:49','啦啦啦',0),(4776740526227456,4715897054822400,'2019-09-03 21:10:50','啦啦啦',0),(4776741058904064,4715897054822400,'2019-09-03 21:10:50','啦啦啦',0);
/*!40000 ALTER TABLE `ManualSign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MeetingType`
--

DROP TABLE IF EXISTS `MeetingType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MeetingType` (
  `meetingTypeId` bigint(20) unsigned NOT NULL,
  `meetingTypeName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`meetingTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MeetingType`
--

LOCK TABLES `MeetingType` WRITE;
/*!40000 ALTER TABLE `MeetingType` DISABLE KEYS */;
/*!40000 ALTER TABLE `MeetingType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Menu` (
  `menuId` bigint(20) unsigned NOT NULL,
  `menuUrl` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `menuPath` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '',
  `menuComponent` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '',
  `menuName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `menuIcon` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '',
  `menuOrder` int(11) NOT NULL DEFAULT '0',
  `parentMenuId` bigint(20) unsigned NOT NULL,
  `requireAuth` tinyint(4) DEFAULT '1',
  `disabled` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MenuRole`
--

DROP TABLE IF EXISTS `MenuRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MenuRole` (
  `menuRoleId` bigint(20) unsigned NOT NULL,
  `menuId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`menuRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MenuRole`
--

LOCK TABLES `MenuRole` WRITE;
/*!40000 ALTER TABLE `MenuRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `MenuRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Message` (
  `messageId` bigint(20) unsigned NOT NULL,
  `messageTypeId` bigint(20) unsigned NOT NULL,
  `messageTitle` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `messageContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `beginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sendUserId` bigint(20) unsigned NOT NULL,
  `isPublished` tinyint(4) NOT NULL DEFAULT '0',
  `publishTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MessageTrans`
--

DROP TABLE IF EXISTS `MessageTrans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MessageTrans` (
  `messageTransId` bigint(20) unsigned NOT NULL,
  `messageId` bigint(20) unsigned NOT NULL,
  `recipientId` bigint(20) unsigned NOT NULL,
  `isRead` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`messageTransId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MessageTrans`
--

LOCK TABLES `MessageTrans` WRITE;
/*!40000 ALTER TABLE `MessageTrans` DISABLE KEYS */;
/*!40000 ALTER TABLE `MessageTrans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MessageType`
--

DROP TABLE IF EXISTS `MessageType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MessageType` (
  `messageTypeId` bigint(20) unsigned NOT NULL,
  `messageTypeName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `messageTypeDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`messageTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MessageType`
--

LOCK TABLES `MessageType` WRITE;
/*!40000 ALTER TABLE `MessageType` DISABLE KEYS */;
/*!40000 ALTER TABLE `MessageType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Note`
--

DROP TABLE IF EXISTS `Note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Note` (
  `noteId` bigint(20) unsigned NOT NULL,
  `noteTitle` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `noteContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`noteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Note`
--

LOCK TABLES `Note` WRITE;
/*!40000 ALTER TABLE `Note` DISABLE KEYS */;
/*!40000 ALTER TABLE `Note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OperationLog`
--

DROP TABLE IF EXISTS `OperationLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OperationLog` (
  `operationId` bigint(20) unsigned NOT NULL,
  `opeartionUserId` bigint(20) unsigned NOT NULL,
  `objectId` bigint(20) unsigned NOT NULL,
  `operationName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `opeartionDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `operationTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`operationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OperationLog`
--

LOCK TABLES `OperationLog` WRITE;
/*!40000 ALTER TABLE `OperationLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `OperationLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Precontract`
--

DROP TABLE IF EXISTS `Precontract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Precontract` (
  `precontractId` bigint(20) unsigned NOT NULL,
  `scheduleId` bigint(20) unsigned NOT NULL,
  `userId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`precontractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Precontract`
--

LOCK TABLES `Precontract` WRITE;
/*!40000 ALTER TABLE `Precontract` DISABLE KEYS */;
/*!40000 ALTER TABLE `Precontract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role` (
  `roleId` bigint(20) unsigned NOT NULL,
  `roleName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `roleDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (2867588375773184,'ROLE_ADMIN','超级管理员'),(2867590917521408,'ROLE_HR','管理员(运维/HR)'),(2867590938492928,'ROLE_USER','用户/员工');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Schedule`
--

DROP TABLE IF EXISTS `Schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Schedule` (
  `scheduleId` bigint(20) unsigned NOT NULL,
  `scheduleTitle` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `scheduleAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `scheduleContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `beginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `meetingTypeId` bigint(20) unsigned NOT NULL,
  `createUserId` bigint(20) unsigned NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isPrivate` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`scheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Schedule`
--

LOCK TABLES `Schedule` WRITE;
/*!40000 ALTER TABLE `Schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `Schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `userId` bigint(20) unsigned NOT NULL,
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(16) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '-1',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'http://pic.51yuansu.com/pic3/cover/02/96/71/5ad09ba522904_610.jpg',
  `departmentId` bigint(20) unsigned NOT NULL,
  `isBlocked` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (4715897054822400,'Hasaker','123','余天堂',0,'avatar',0,0),(4782823961526272,'fuduwen','123','付杜文',1,'132123',4718691837018112,0),(4782929997725696,'string','string','string',0,'string',0,0);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserRole` (
  `userRoleId` bigint(20) unsigned NOT NULL,
  `userId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRole`
--

LOCK TABLES `UserRole` WRITE;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WorkTime`
--

DROP TABLE IF EXISTS `WorkTime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `WorkTime` (
  `workTimeId` bigint(20) unsigned NOT NULL,
  `onDutyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `offDutyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`workTimeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WorkTime`
--

LOCK TABLES `WorkTime` WRITE;
/*!40000 ALTER TABLE `WorkTime` DISABLE KEYS */;
/*!40000 ALTER TABLE `WorkTime` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-03 22:58:24
