CREATE DATABASE  IF NOT EXISTS `medical-platform` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `medical-platform`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: medical-platform
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8 ;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `start` datetime NOT NULL,
  `patient_id` bigint(20) NOT NULL,
  `abnormal` bit(1) NOT NULL,
  `justified` bit(1) NOT NULL,
  `recommendation` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd9yye57f0rctpx0xi2dwq81rk` (`patient_id`),
  CONSTRAINT `FKd9yye57f0rctpx0xi2dwq81rk` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `caregiver`
--

DROP TABLE IF EXISTS `caregiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `caregiver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `caregiver_has_patient`
--

DROP TABLE IF EXISTS `caregiver_has_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `caregiver_has_patient` (
  `caregiver_id` bigint(20) NOT NULL,
  `patient_id` bigint(20) NOT NULL,
  PRIMARY KEY (`caregiver_id`,`patient_id`),
  KEY `FKik29mjwgfaj84swu66a0nt0wp` (`patient_id`),
  CONSTRAINT `FKik29mjwgfaj84swu66a0nt0wp` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKsahtdpk6lpj0pkc0lpe204o7q` FOREIGN KEY (`caregiver_id`) REFERENCES `caregiver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intake_interval`
--

DROP TABLE IF EXISTS `intake_interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `intake_interval` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_time` time NOT NULL,
  `start_time` time NOT NULL,
  `medication_id` bigint(20) NOT NULL,
  `plan_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK55xq2jdv0mp3ogbaxtyegk35v` (`medication_id`),
  KEY `FKke58gdex2i5xh5wvb051c2bw5` (`plan_id`),
  CONSTRAINT `FK55xq2jdv0mp3ogbaxtyegk35v` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`id`),
  CONSTRAINT `FKke58gdex2i5xh5wvb051c2bw5` FOREIGN KEY (`plan_id`) REFERENCES `medication_plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intake_taken`
--

DROP TABLE IF EXISTS `intake_taken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `intake_taken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `intake_id` bigint(20) NOT NULL,
  `taken` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKauet4w0e5pfqa2ukr9fxxjy0f` (`intake_id`),
  CONSTRAINT `FKauet4w0e5pfqa2ukr9fxxjy0f` FOREIGN KEY (`intake_id`) REFERENCES `intake_interval` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `medication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dosage` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `side_effects` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medication_plan`
--

DROP TABLE IF EXISTS `medication_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `medication_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `patient_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsbssmqwap7gwimwcnwmolheef` (`patient_id`),
  CONSTRAINT `FKsbssmqwap7gwimwcnwmolheef` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `user_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_65lk4tri2kkban9cavbpjlw5p` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dump completed on 2020-02-14 10:29:44
