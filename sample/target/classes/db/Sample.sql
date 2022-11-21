-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.1.73-community

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
-- Table structure for table `tbl_banner`
--

DROP TABLE IF EXISTS `tbl_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_banner` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '배너 번호\n',
  `name` varchar(45) DEFAULT NULL COMMENT '배너 이름\n',
  `type` varchar(45) DEFAULT NULL COMMENT '배너 타입\n',
  `order` varchar(45) DEFAULT NULL COMMENT '배너 순서',
  `image` varchar(45) DEFAULT NULL COMMENT '배너 이미지\n',
  `create_tm` datetime DEFAULT NULL COMMENT '배너 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '배너 수정 시간',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='배너 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_banner`
--

LOCK TABLES `tbl_banner` WRITE;
/*!40000 ALTER TABLE `tbl_banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_board`
--

DROP TABLE IF EXISTS `tbl_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_board` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시판 번호',
  `level` int(11) DEFAULT NULL COMMENT '게시판 레벨 ( 0 = 일반 게시판 , 1 = 공지 게시판 ,  2 = 관리자 게시판 )',
  `reply` varchar(45) DEFAULT NULL COMMENT '댓글 ( TRUE , FALSE)\n',
  `type` int(11) DEFAULT NULL COMMENT '타입 ( 0 = 일반 , 1 = 썸네일 )',
  `name` varchar(45) DEFAULT NULL COMMENT '게시판 이름',
  `create_tm` datetime DEFAULT NULL COMMENT '게시판 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '게시판 업데이트 시간\n',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시판 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_board`
--

LOCK TABLES `tbl_board` WRITE;
/*!40000 ALTER TABLE `tbl_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_board_data`
--

DROP TABLE IF EXISTS `tbl_board_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_board_data` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시글 번호',
  `board_idx` int(11) NOT NULL COMMENT '게시판 번호',
  `level` int(11) DEFAULT NULL COMMENT '게시글 레벨',
  `type` varchar(45) DEFAULT NULL COMMENT '게시글 타입 ( 비밀글 , 그냥글 )',
  `title` varchar(45) DEFAULT NULL COMMENT '게시글 제목',
  `content` varchar(45) DEFAULT NULL COMMENT '게시글 내용\n',
  `member_id` varchar(45) DEFAULT NULL COMMENT '작성자 아이디\n',
  `name` varchar(45) DEFAULT NULL COMMENT '작성자 이름',
  `create_tm` datetime DEFAULT NULL COMMENT '게시글 작성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '게시글 수정 시간\n',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시글 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_board_data`
--

LOCK TABLES `tbl_board_data` WRITE;
/*!40000 ALTER TABLE `tbl_board_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_board_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_board_reply`
--

DROP TABLE IF EXISTS `tbl_board_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_board_reply` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시글 번호',
  `board_idx` int(11) NOT NULL COMMENT '게시판 번호',
  `board_data_idx` int(11) NOT NULL COMMENT '게시글 번호',
  `reply_idx` int(11) DEFAULT NULL COMMENT '댓글번호 (대댓글만)',
  `depth` int(11) DEFAULT NULL COMMENT '댓글 단계\n',
  `content` varchar(45) DEFAULT NULL COMMENT '댓글 내용',
  `member_id` varchar(45) DEFAULT NULL COMMENT '회원 아이디\n',
  `name` varchar(45) DEFAULT NULL COMMENT '회원 이름',
  `create_tm` datetime DEFAULT NULL COMMENT '댓글 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '댓글 수정 시간',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시글 댓글 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_board_reply`
--

LOCK TABLES `tbl_board_reply` WRITE;
/*!40000 ALTER TABLE `tbl_board_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_board_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_config`
--

DROP TABLE IF EXISTS `tbl_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_config` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '설정 번호\n',
  `level` int(11) DEFAULT NULL COMMENT '설정 레벨\n',
  `type` varchar(45) DEFAULT NULL COMMENT '설정 타입\n',
  `title` varchar(45) DEFAULT NULL COMMENT '설정 제목\n',
  `create_tm` datetime DEFAULT NULL COMMENT '설정 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '설정 수정 시간\n',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='서버 설정 테이블\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_config`
--

LOCK TABLES `tbl_config` WRITE;
/*!40000 ALTER TABLE `tbl_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_file_log`
--

DROP TABLE IF EXISTS `tbl_file_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_file_log` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '파일 번호',
  `type` int(11) DEFAULT NULL COMMENT '파일 타입\n',
  `filename` varchar(45) DEFAULT NULL COMMENT '파일 이름\n',
  `url` varchar(45) DEFAULT NULL COMMENT '파일 저장 위치\n',
  `create_tm` datetime DEFAULT NULL COMMENT '파일 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '파일 수정 시간',
  `board_idx` int(11) DEFAULT NULL COMMENT '게시판 번호\n',
  `board_data_idx` int(11) DEFAULT NULL COMMENT '게시글 번호',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='서버 파일 관리 테이블\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_file_log`
--

LOCK TABLES `tbl_file_log` WRITE;
/*!40000 ALTER TABLE `tbl_file_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_file_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_log`
--

DROP TABLE IF EXISTS `tbl_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_log` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그 번호\n',
  `work` varchar(45) DEFAULT NULL COMMENT '로그 행동\n',
  `ip` varchar(45) DEFAULT NULL COMMENT '로그 아이피\n',
  `status` varchar(45) DEFAULT NULL COMMENT '로그 상태\n',
  `create_tm` datetime DEFAULT NULL COMMENT '로그 생성 시간',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='로그 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_log`
--

LOCK TABLES `tbl_log` WRITE;
/*!40000 ALTER TABLE `tbl_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_menu`
--

DROP TABLE IF EXISTS `tbl_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_menu` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '메뉴 번호\n',
  `type` varchar(45) DEFAULT NULL COMMENT '메뉴 여부',
  `depth` int(11) DEFAULT NULL COMMENT '메뉴 뎁스',
  `name` varchar(45) DEFAULT NULL COMMENT '메뉴 명\n',
  `create_tm` varchar(45) DEFAULT NULL COMMENT '메뉴 생성 시간\n',
  `update_tm` varchar(45) DEFAULT NULL COMMENT '메뉴 수정 시간',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='서버 메뉴 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_menu`
--

LOCK TABLES `tbl_menu` WRITE;
/*!40000 ALTER TABLE `tbl_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_popup`
--

DROP TABLE IF EXISTS `tbl_popup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_popup` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '팝업 제목',
  `url` varchar(45) DEFAULT NULL COMMENT '팝업 올릴 경로',
  `type` int(11) DEFAULT NULL COMMENT '팝업 타입\n',
  `width` varchar(45) DEFAULT NULL COMMENT '넓이\n',
  `height` varchar(45) DEFAULT NULL COMMENT '높이\n',
  `image` varchar(45) DEFAULT NULL COMMENT '팝업 이미지\n',
  `create_tm` datetime DEFAULT NULL COMMENT '팝업 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '팝업 수정 시간\n',
  `start_tm` datetime DEFAULT NULL COMMENT '팝업 시작 시간\n',
  `end_tm` datetime DEFAULT NULL COMMENT '팝업 종료 시간',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='팝업 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_popup`
--

LOCK TABLES `tbl_popup` WRITE;
/*!40000 ALTER TABLE `tbl_popup` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_popup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_member`
--

DROP TABLE IF EXISTS `tbl_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_member` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원 번호',
  `level` int(11) DEFAULT NULL COMMENT '회원 레벨',
  `type` int(11) DEFAULT NULL COMMENT '회원 상태 ( 0 = 미승인 , 1 = 승인 , 2 = 삭제 )',
  `member_id` varchar(45) DEFAULT NULL COMMENT '회원 아이디\n',
  `password` varchar(45) DEFAULT NULL COMMENT '회원 비밀번호',
  `name` varchar(45) DEFAULT NULL COMMENT '회원 이름\n',
  `phone` varchar(45) DEFAULT NULL COMMENT '회원 전화 번호\n',
  `address` varchar(45) DEFAULT NULL COMMENT '회원 주소',
  `address_detail` varchar(45) DEFAULT NULL COMMENT '회원 상세 주소\n',
  `create_tm` datetime DEFAULT NULL COMMENT '회원 생성 시간\n',
  `update_tm` datetime DEFAULT NULL COMMENT '회원 수정 시간',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='유저 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_users`
--

LOCK TABLES `tbl_users` WRITE;
/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-09 16:06:02
