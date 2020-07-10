-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: social_web
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment_likes`
--

DROP TABLE IF EXISTS `comment_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_likes` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `comment_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) /*!80000 INVISIBLE */,
  UNIQUE KEY `UNIQUE_LIKE` (`user_id`,`comment_id`) /*!80000 INVISIBLE */,
  KEY `fk_comment_likes_commrnt_id_idx` (`comment_id`),
  CONSTRAINT `fk_comment_likes_commrnt_id` FOREIGN KEY (`comment_id`) REFERENCES `photo_comment` (`id`),
  CONSTRAINT `fk_comment_likes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_likes`
--

LOCK TABLES `comment_likes` WRITE;
/*!40000 ALTER TABLE `comment_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `cross_likes`
--

DROP TABLE IF EXISTS `cross_likes`;
/*!50001 DROP VIEW IF EXISTS `cross_likes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `cross_likes` AS SELECT 
 1 AS `user_id`,
 1 AS `count`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `get_likes`
--

DROP TABLE IF EXISTS `get_likes`;
/*!50001 DROP VIEW IF EXISTS `get_likes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `get_likes` AS SELECT 
 1 AS `user_id`,
 1 AS `count`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `like_statistic`
--

DROP TABLE IF EXISTS `like_statistic`;
/*!50001 DROP VIEW IF EXISTS `like_statistic`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `like_statistic` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `get_likes`,
 1 AS `set_likes`,
 1 AS `cross_likes`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `data_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_photo_user_id_idx` (`user_id`),
  KEY `fk_photo_data_id_idx` (`data_id`),
  CONSTRAINT `fk_photo_data_id` FOREIGN KEY (`data_id`) REFERENCES `photos_data` (`id`),
  CONSTRAINT `fk_photo_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo_comment`
--

DROP TABLE IF EXISTS `photo_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo_comment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `photo_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  `comment` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_photo_comment_user_id_idx` (`user_id`),
  KEY `dfgd_idx` (`photo_id`),
  CONSTRAINT `fk_photo_comment_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`),
  CONSTRAINT `fk_photo_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo_comment`
--

LOCK TABLES `photo_comment` WRITE;
/*!40000 ALTER TABLE `photo_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `photo_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo_likes`
--

DROP TABLE IF EXISTS `photo_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo_likes` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `photo_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) /*!80000 INVISIBLE */,
  UNIQUE KEY `UNIQUE_LIKE` (`user_id`,`photo_id`) /*!80000 INVISIBLE */,
  KEY `fk_photo_likes_photo_id_idx` (`photo_id`),
  CONSTRAINT `fk_photo_likes_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`),
  CONSTRAINT `fk_photo_likes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo_likes`
--

LOCK TABLES `photo_likes` WRITE;
/*!40000 ALTER TABLE `photo_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `photo_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photos_data`
--

DROP TABLE IF EXISTS `photos_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photos_data` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `data` blob NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photos_data`
--

LOCK TABLES `photos_data` WRITE;
/*!40000 ALTER TABLE `photos_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `photos_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `set_likes`
--

DROP TABLE IF EXISTS `set_likes`;
/*!50001 DROP VIEW IF EXISTS `set_likes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `set_likes` AS SELECT 
 1 AS `user_id`,
 1 AS `count`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user_likes`
--

DROP TABLE IF EXISTS `user_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_likes` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `liked_user_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) /*!80000 INVISIBLE */,
  UNIQUE KEY `UNIQUE_LIKE` (`user_id`,`liked_user_id`) /*!80000 INVISIBLE */,
  KEY `fk_liked_user_id_idx` (`liked_user_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_likes_liked_user_id` FOREIGN KEY (`liked_user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_likes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_likes`
--

LOCK TABLES `user_likes` WRITE;
/*!40000 ALTER TABLE `user_likes` DISABLE KEYS */;
INSERT INTO `user_likes` VALUES (1,1,2),(4,1,3),(5,3,2),(6,2,1),(7,2,3);
/*!40000 ALTER TABLE `user_likes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `like_BEFORE_INSERT` BEFORE INSERT ON `user_likes` FOR EACH ROW BEGIN
	if new.user_id = new.liked_user_id then
    signal sqlstate '45000'
    set message_text = "user_id == liked_user_id";
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `like_BEFORE_UPDATE` BEFORE UPDATE ON `user_likes` FOR EACH ROW BEGIN
	if new.user_id = new.liked_user_id then
    signal sqlstate '45000'
    set message_text = "user_id == liked_user_id";
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Паша'),(2,'Маша'),(3,'Юра');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'social_web'
--

--
-- Dumping routines for database 'social_web'
--
/*!50003 DROP PROCEDURE IF EXISTS `new_procedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`(in id_user1 int, in  id_user2 int, in id_user3 int)
    READS SQL DATA
BEGIN
SELECT 
    a.user_id,
    a.liked_user_id AS liked_first_user_id,
    b.liked_user_id AS liked_second_user_id
FROM
    user_likes AS a
        INNER JOIN
    user_likes AS b ON b.user_id = a.user_id
        AND b.liked_user_id = id_user2
        AND a.liked_user_id = id_user1
WHERE
    a.user_id NOT IN (SELECT 
            user_id
        FROM
            user_likes
        WHERE
            liked_user_id = id_user3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `cross_likes`
--

/*!50001 DROP VIEW IF EXISTS `cross_likes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cross_likes` AS select `user_likes`.`user_id` AS `user_id`,count(`user_likes`.`user_id`) AS `count` from (`user_likes` join `user_likes` `liked` on(((`liked`.`liked_user_id` = `user_likes`.`user_id`) and (`liked`.`user_id` = `user_likes`.`liked_user_id`)))) group by `user_likes`.`user_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `get_likes`
--

/*!50001 DROP VIEW IF EXISTS `get_likes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `get_likes` AS select `user_likes`.`liked_user_id` AS `user_id`,count(`user_likes`.`liked_user_id`) AS `count` from `user_likes` group by `user_likes`.`liked_user_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `like_statistic`
--

/*!50001 DROP VIEW IF EXISTS `like_statistic`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `like_statistic` AS select `u`.`id` AS `id`,`u`.`name` AS `name`,`get_likes`.`count` AS `get_likes`,`set_likes`.`count` AS `set_likes`,`cross_likes`.`count` AS `cross_likes` from (((`users` `u` left join `get_likes` on((`get_likes`.`user_id` = `u`.`id`))) left join `set_likes` on((`set_likes`.`user_id` = `u`.`id`))) left join `cross_likes` on((`cross_likes`.`user_id` = `u`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `set_likes`
--

/*!50001 DROP VIEW IF EXISTS `set_likes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `set_likes` AS select `user_likes`.`user_id` AS `user_id`,count(`user_likes`.`user_id`) AS `count` from `user_likes` group by `user_likes`.`user_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-06 16:35:32
