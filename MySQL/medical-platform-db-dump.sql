CREATE DATABASE  IF NOT EXISTS `medical-platform` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `medical-platform`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: medical-platform
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end` datetime NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start` datetime NOT NULL,
  `patient_id` bigint NOT NULL,
  `abnormal` bit(1) NOT NULL,
  `justified` bit(1) NOT NULL,
  `recommendation` varchar(1023) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd9yye57f0rctpx0xi2dwq81rk` (`patient_id`),
  CONSTRAINT `FKd9yye57f0rctpx0xi2dwq81rk` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'2021-01-19 11:02:00','Sleeping','2021-01-18 22:41:00',1,_binary '',_binary '\0',NULL),(2,'2021-01-19 11:34:00','Hygiene','2021-01-19 11:03:00',1,_binary '\0',_binary '\0',NULL),(3,'2021-01-19 12:10:00','Recreation','2021-01-19 11:35:00',1,_binary '\0',_binary '\0',NULL),(4,'2021-01-19 18:03:00','Outdoors','2021-01-19 12:13:00',1,_binary '\0',_binary '\0',NULL),(5,'2021-01-19 22:30:00','Recreation','2021-01-19 18:04:00',1,_binary '\0',_binary '\0',NULL),(6,'2021-01-20 06:58:00','Sleeping','2021-01-19 22:35:00',1,_binary '\0',_binary '\0',NULL),(7,'2021-01-20 08:02:00','Hygiene','2021-01-20 06:59:00',1,_binary '',_binary '','None; Patient reports simply having taken a long shower'),(8,'2021-01-20 11:49:00','Recreation','2021-01-20 08:04:00',1,_binary '\0',_binary '\0',NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caregiver`
--

DROP TABLE IF EXISTS `caregiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caregiver` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregiver`
--

LOCK TABLES `caregiver` WRITE;
/*!40000 ALTER TABLE `caregiver` DISABLE KEYS */;
INSERT INTO `caregiver` VALUES (1,'13 Pomeroy St, Toronto, Canada','1977-04-02','Male','Alfred William Moore'),(2,'5420 S Aberdeen St, Chicago, USA','1983-09-15','Female','Lisa Margaret Kyne');
/*!40000 ALTER TABLE `caregiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caregiver_has_patient`
--

DROP TABLE IF EXISTS `caregiver_has_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caregiver_has_patient` (
  `caregiver_id` bigint NOT NULL,
  `patient_id` bigint NOT NULL,
  PRIMARY KEY (`caregiver_id`,`patient_id`),
  KEY `FKik29mjwgfaj84swu66a0nt0wp` (`patient_id`),
  CONSTRAINT `FKik29mjwgfaj84swu66a0nt0wp` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKsahtdpk6lpj0pkc0lpe204o7q` FOREIGN KEY (`caregiver_id`) REFERENCES `caregiver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregiver_has_patient`
--

LOCK TABLES `caregiver_has_patient` WRITE;
/*!40000 ALTER TABLE `caregiver_has_patient` DISABLE KEYS */;
INSERT INTO `caregiver_has_patient` VALUES (1,1),(2,1),(1,2),(2,3);
/*!40000 ALTER TABLE `caregiver_has_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intake_interval`
--

DROP TABLE IF EXISTS `intake_interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intake_interval` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_time` time NOT NULL,
  `start_time` time NOT NULL,
  `medication_id` bigint NOT NULL,
  `plan_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK55xq2jdv0mp3ogbaxtyegk35v` (`medication_id`),
  KEY `FKke58gdex2i5xh5wvb051c2bw5` (`plan_id`),
  CONSTRAINT `FK55xq2jdv0mp3ogbaxtyegk35v` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`id`),
  CONSTRAINT `FKke58gdex2i5xh5wvb051c2bw5` FOREIGN KEY (`plan_id`) REFERENCES `medication_plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intake_interval`
--

LOCK TABLES `intake_interval` WRITE;
/*!40000 ALTER TABLE `intake_interval` DISABLE KEYS */;
INSERT INTO `intake_interval` VALUES (1,'12:00:00','11:00:00',1,1),(2,'20:00:00','19:00:00',1,1);
/*!40000 ALTER TABLE `intake_interval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intake_taken`
--

DROP TABLE IF EXISTS `intake_taken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intake_taken` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `intake_id` bigint NOT NULL,
  `taken` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKauet4w0e5pfqa2ukr9fxxjy0f` (`intake_id`),
  CONSTRAINT `FKauet4w0e5pfqa2ukr9fxxjy0f` FOREIGN KEY (`intake_id`) REFERENCES `intake_interval` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intake_taken`
--

LOCK TABLES `intake_taken` WRITE;
/*!40000 ALTER TABLE `intake_taken` DISABLE KEYS */;
INSERT INTO `intake_taken` VALUES (2,'2021-01-15',1,_binary ''),(3,'2021-01-15',2,_binary ''),(4,'2021-01-16',1,_binary ''),(5,'2021-01-16',2,_binary ''),(6,'2021-01-17',1,_binary '\0'),(7,'2021-01-17',2,_binary ''),(8,'2021-01-18',1,_binary ''),(9,'2021-01-18',2,_binary '\0'),(10,'2021-01-19',1,_binary ''),(11,'2021-01-19',2,_binary ''),(12,'2021-01-20',1,_binary '');
/*!40000 ALTER TABLE `intake_taken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medication` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dosage` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `side_effects` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'500 mg','Amoxicillin','Nausea, vomiting, rashes'),(2,'200 mg','Ibuprofen','Hearburn, rash');
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication_plan`
--

DROP TABLE IF EXISTS `medication_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medication_plan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `patient_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsbssmqwap7gwimwcnwmolheef` (`patient_id`),
  CONSTRAINT `FKsbssmqwap7gwimwcnwmolheef` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication_plan`
--

LOCK TABLES `medication_plan` WRITE;
/*!40000 ALTER TABLE `medication_plan` DISABLE KEYS */;
INSERT INTO `medication_plan` VALUES (1,'2021-02-26','2021-01-15',1);
/*!40000 ALTER TABLE `medication_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'13 Dombey St., London, Great Britain','1968-02-09','Male','Edgar Charles Weatherby'),(2,'7 Brommstraße, Frankfurt, Germany','1985-10-30','Female','Leone Brigitte Helmann'),(3,'77 Carrer d\'Isabel de Villena, Valencia, Spain','2004-07-20','Male','Miguel Paolo Esperanza');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_65lk4tri2kkban9cavbpjlw5p` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-20 13:55:28
