/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.5.2-MariaDB, for osx10.18 (x86_64)
--
-- Host: localhost    Database: likeHeroToZero
-- ------------------------------------------------------
-- Server version	11.5.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Current Database: `likeHeroToZero`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `likeHeroToZero` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `likeHeroToZero`;

--
-- Table structure for table `emissions`
--

DROP TABLE IF EXISTS `emissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) NOT NULL,
  `emission` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country` (`country`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emissions`
--

LOCK TABLES `emissions` WRITE;
/*!40000 ALTER TABLE `emissions` DISABLE KEYS */;
INSERT INTO `emissions` VALUES
(1,'China',24242.25),
(2,'United States',4233.61),
(3,'EU27',2804.75),
(4,'India',2693.68),
(5,'Russia',4585.25),
(6,'Japan',1800.63),
(8,'Italy',1412.12),
(9,'Ruanda',1.1),
(10,'Congo',2.22),
(11,'France',238.43),
(12,'Germany',23.23),
(13,'Estonia',1010.11),
(14,'Spain',1234.67),
(15,'Portugal',987.65),
(16,'Vatikanstadt',58.91),
(17,'Vietnam',1818.18),
(18,'Austria',1111.11),
(19,'Korea',234.56),
(21,'Libia',147.41);
/*!40000 ALTER TABLE `emissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`),
  UNIQUE KEY `userName_2` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'Pinco','Pallino','pincopallino@gmoail.com','pincopallino','a960bc341f186d7fb5f61ff2cff280d7569d4cc52dbf639c896ef5537ca8727d7a8431a8933877b44bd8bcee0979e1b4634ef98a94e7656cdc7b1c67c06f429a'),
(2,'Alain','Prost','alainprost@yahooooo.com','alainprost','d8323894a4df4a1a5760299df7a86cb3885c23f21ae9c03e65754ffed396fcaff5c9a90767eeff2911c007c246f828c5691282a579cb6e71cab901b2f7dad9bc'),
(3,'Ayrton','Senna','ayrtonsenna@freeeenet.de','ayrtonsenna','87b7022b95e90ad59daeb49e22d97b202895eafd7032c0a3fa5952fc968362ffa082eecce14fab8ad79cb254f5a4e27c2a998c040b536f2ec86bdf7c9751b969');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2024-09-09 17:35:31
