-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: electrogrid
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` int NOT NULL,
  `email` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `NIC_UNIQUE` (`NIC`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (78,'nimal','perra','997925364V','colombo',123456789,'thth@gmail.cmo');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `billId` int NOT NULL AUTO_INCREMENT,
  `issueDate` date NOT NULL,
  `unit` int NOT NULL,
  `balance` float NOT NULL,
  `amountToPay` float NOT NULL,
  `totalAmount` float NOT NULL,
  `customerId` int NOT NULL,
  PRIMARY KEY (`billId`),
  UNIQUE KEY `billId_UNIQUE` (`billId`),
  KEY `userId_idx` (`customerId`),
  CONSTRAINT `userId_bill` FOREIGN KEY (`customerId`) REFERENCES `customer` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (2,'2022-04-12',50,50,1000,1050,4),(7,'2022-04-05',150,0,1500,1500,3),(8,'2022-04-10',90,100,1400,1500,2);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connections`
--

DROP TABLE IF EXISTS `connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connections` (
  `conId` int NOT NULL AUTO_INCREMENT,
  `conName` varchar(65) NOT NULL,
  `conType` varchar(65) NOT NULL,
  `conDesc` varchar(75) NOT NULL,
  `conAdminId` int NOT NULL,
  PRIMARY KEY (`conId`),
  UNIQUE KEY `conId_UNIQUE` (`conId`),
  KEY `con_userId_idx` (`conAdminId`),
  CONSTRAINT `con_userId` FOREIGN KEY (`conAdminId`) REFERENCES `admin` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connections`
--

LOCK TABLES `connections` WRITE;
/*!40000 ALTER TABLE `connections` DISABLE KEYS */;
INSERT INTO `connections` VALUES (4,'Four Phase 100/160 A - 70/112 kVA','Bulk Supply Connections','100 (A) and 160 (A) connections are provided directly from a nearby station',78),(5,'Single Phase 15 (A) – 3.5 kVA','Single Phase 15 (A) – 3.5 kVA','Ordinary Supply Connections',78),(6,'Three Phase 160 A - 112 kVA','Three Phase 160 A - 112 kVA','Bulk Supply Connections',78);
/*!40000 ALTER TABLE `connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` int NOT NULL,
  `email` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `NIC_UNIQUE` (`NIC`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'Kamal','Perera','123456789V','62/2, Nugegoda',712538695,'kamal@gmail.com'),(3,'Vimal','Dias','992136558V','50/Colombo',767152204,'agf@gmail.com'),(4,'Thulya','Madhawee','993456789V','62, Delkanda, Nugegoda',782538697,'thuli@gmail.com'),(5,'Thulyaaaa','Premathilake','993456787V','62, Wijerama,Nugegoda',782538698,'thulya@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `paymentId` int NOT NULL AUTO_INCREMENT,
  `cardNumber` varchar(12) NOT NULL,
  `CVV` varchar(3) NOT NULL,
  `expDate` date NOT NULL,
  `amount` float NOT NULL,
  `payCustomerId` int NOT NULL,
  PRIMARY KEY (`paymentId`),
  UNIQUE KEY `paymentId_UNIQUE` (`paymentId`),
  KEY `pay_customerfk_idx` (`payCustomerId`),
  CONSTRAINT `pay_customerfk` FOREIGN KEY (`payCustomerId`) REFERENCES `customer` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (3,'145836927458','178','2025-10-12',1480,3),(4,'785636927458','960','2028-08-15',5200,3);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-26 12:51:44
