-- MySQL dump 10.15  Distrib 10.0.38-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: granja
-- ------------------------------------------------------
-- Server version	10.0.38-MariaDB-0ubuntu0.16.04.1

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
-- Table structure for table `carro`
--

DROP TABLE IF EXISTS `carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placas` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carro_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_carro_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro`
--

LOCK TABLES `carro` WRITE;
/*!40000 ALTER TABLE `carro` DISABLE KEYS */;
INSERT INTO `carro` VALUES (12,'DRA-9834','Estaquita blanca',0),(13,'TRE-342-A','Ford Ranger Azul',0),(14,'SAR-325-B','Tsuru Rojo',0),(15,'RFV-3432','Nissan',0),(16,'DFR-659','Ford Ranger Roja',0),(17,'DRA-6985','Estaquita blanca con golpes en la facia',0),(18,'TRD-3655','Ford F150 roja',1);
/*!40000 ALTER TABLE `carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chofer`
--

DROP TABLE IF EXISTS `chofer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chofer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(205) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` char(10) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chofer_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_chofer_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chofer`
--

LOCK TABLES `chofer` WRITE;
/*!40000 ALTER TABLE `chofer` DISABLE KEYS */;
INSERT INTO `chofer` VALUES (11,'Jose Armando Perez ','Guayabal','9195632145','2019-03-25 18:00:13',0),(12,'Antonio Lopez Perez','Barrio San Antonio','9190236665','2019-03-25 18:00:32',0),(13,'Eddy Sanchez','Conocido','9612751708','2019-03-26 12:35:09',0),(14,'Mariano ','Rayon, Chis.','9612458751','2019-03-26 12:44:33',0),(15,'Adrian Tolentino','San Isidro','9191569877','2019-04-06 20:35:18',0),(16,'Vladimir Díaz Ruiz','Bochil','0124589877','2019-04-07 14:03:29',0),(17,'Felipe Cruz Vera','Tuxtla Gtz','9610256987','2019-04-14 21:05:54',1);
/*!40000 ALTER TABLE `chofer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `telefono` char(10) DEFAULT NULL,
  `direccion` varchar(255) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_cliente_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'José Armando Trejo Hernández','9191002569','Guayabal',0),(2,'Elena Vazquez Gonzalez','9191366665','San Isidro',0),(3,'Carmen Flores Ruiz','9190002569','Rayón',0),(4,'Carmen Flores Ruiz','9191103369','Pueblo',0),(5,'Isabel Torres Perez','0','Mexico',0);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `factura` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,1),(3,2),(4,3),(5,4),(6,5),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),(49,1),(50,1),(51,1),(52,1),(53,1),(54,1),(55,1),(56,1),(57,1),(58,1),(59,1),(60,1),(61,1),(62,1),(63,1),(64,1),(65,1),(66,1),(67,1),(68,1),(69,1),(70,1),(71,1),(72,1),(73,1),(74,1),(75,1),(76,1),(77,1),(78,1),(79,1),(80,1),(81,1),(82,1),(83,1),(84,1),(85,1),(86,1),(87,1),(88,1),(89,1),(90,1),(91,1),(92,1),(93,1),(94,1),(95,1),(96,1),(97,1),(98,1),(99,1),(100,1),(101,1),(102,1),(103,1),(104,1),(105,1),(106,1),(107,1),(108,1),(109,1),(110,1),(111,1),(112,1),(113,1),(114,1),(115,1),(116,1),(117,1),(118,1),(119,1),(120,1),(121,1),(122,1),(123,1),(124,1),(125,1),(126,1),(127,1),(128,1),(129,1),(130,1),(131,1),(132,1),(133,1),(134,1),(135,1),(136,1),(137,1),(138,1),(139,1),(140,1),(141,1),(142,1),(143,1),(144,1),(145,1),(146,1),(147,1),(148,1),(149,1),(150,1),(151,1),(152,1),(153,1),(154,1),(155,1),(156,1),(157,1),(158,1),(159,1),(160,1),(161,1),(162,1),(163,1),(164,1),(165,1),(166,1),(167,1),(168,1),(169,1),(170,1),(171,1),(172,1),(173,1),(174,1),(175,1),(176,1),(177,1),(178,1),(179,1),(180,1),(181,1),(182,1),(183,1),(184,1),(185,1),(186,1),(187,1),(188,1),(189,1),(190,1),(191,1),(192,1),(193,1),(194,1),(195,1),(196,1),(197,1),(198,1),(199,1),(200,1),(201,1),(202,1),(203,1),(204,1),(205,1),(206,1),(207,1),(208,1),(209,1);
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credito`
--

DROP TABLE IF EXISTS `credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monto` decimal(10,2) DEFAULT NULL,
  `tipo` int(2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_credito_cliente1_idx` (`cliente_id`),
  CONSTRAINT `fk_credito_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credito`
--

LOCK TABLES `credito` WRITE;
/*!40000 ALTER TABLE `credito` DISABLE KEYS */;
INSERT INTO `credito` VALUES (1,200.00,1,'2019-04-16 13:02:36',1),(2,0.00,1,'2019-05-16 20:20:12',5),(3,0.00,1,'2019-05-22 15:01:49',4),(4,0.00,1,'2019-05-22 15:10:44',5),(5,0.50,1,'2019-09-11 08:30:44',5),(6,88.00,1,'2019-09-11 08:32:52',5),(7,5.50,1,'2019-09-11 13:00:21',1),(8,60.00,1,'2019-09-11 13:00:40',1),(9,200.00,1,'2019-09-11 13:00:52',1),(10,0.00,1,'2019-09-11 13:21:54',2);
/*!40000 ALTER TABLE `credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destino`
--

DROP TABLE IF EXISTS `destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destino` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `preciokilo` decimal(10,2) DEFAULT NULL,
  `carro_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_destino_carro1_idx` (`carro_id`),
  KEY `fk_destino_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_destino_carro1` FOREIGN KEY (`carro_id`) REFERENCES `carro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_destino_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destino`
--

LOCK TABLES `destino` WRITE;
/*!40000 ALTER TABLE `destino` DISABLE KEYS */;
INSERT INTO `destino` VALUES (78,'Tapilula',26.00,12,0),(79,'Pueblo Nuevo',28.00,13,0),(80,'San Isidro',22.00,15,0),(81,'Bochil',27.50,14,0),(82,'Local',25.00,16,0),(83,'Copainala',29.50,17,0);
/*!40000 ALTER TABLE `destino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(10) NOT NULL,
  `kilos` double NOT NULL,
  `tipo` int(2) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `observacion` varchar(255) NOT NULL,
  `pollos_folio` char(20) DEFAULT NULL,
  `pedidos_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pollos_folio` (`pollos_folio`),
  CONSTRAINT `historico_ibfk_1` FOREIGN KEY (`pollos_folio`) REFERENCES `pollos` (`folio`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` VALUES (181,50,100,-1,'2019-04-16 13:29:05','Entrada de productos','TRE328',-1),(182,4,8.2,1,'2019-04-16 13:29:21','Transito','TRE328',322),(183,3,9.32,1,'2019-04-16 13:29:39','Transito','TRE328',323),(184,6,12.45,1,'2019-04-16 13:29:56','Transito','TRE328',324),(185,8,16.43,1,'2019-04-16 13:30:20','Transito','TRE328',325),(186,5,10.51,2,'2019-04-16 13:30:39','Transito','TRE328',326),(187,8,16.43,8,'2019-04-16 13:31:46','Pollos muertos en ruta','TRE328',325),(188,6,12.45,4,'2019-04-16 13:32:59','No se vendieron','TRE328',324),(189,3,9,1,'2019-05-16 19:47:47','Transito','TRE328',327),(190,4,8.2,8,'2019-05-16 19:48:32','se murieron','TRE328',322),(191,3,9.32,8,'2019-05-16 19:48:32','se murieron','TRE328',323),(192,7,21.5,2,'2019-05-16 19:48:53','Transito','TRE328',328),(193,6,18.93,3,'2019-05-16 19:49:07','Transito','TRE328',329),(194,5,15,3,'2019-05-16 19:50:12','muerte por tos','TRE328',329),(195,4,12,3,'2019-05-16 19:51:32','Transito','TRE328',330),(196,4,4,1,'2019-05-17 13:32:26','Transito','TRE328',331),(197,4,16,1,'2019-05-17 13:35:07','Transito','TRE328',332),(198,4,12,1,'2019-05-17 13:36:35','Transito','TRE328',333),(199,1,2,1,'2019-05-17 13:38:10','Transito','TRE328',334),(200,1,1,1,'2019-05-17 13:39:15','Transito','TRE328',335),(201,1,1,1,'2019-05-17 13:44:16','Transito','TRE328',336),(202,500,2000,-1,'2019-05-17 13:45:05','Entrada de productos','78123671',-1),(203,1,1,1,'2019-05-17 13:45:18','Transito','78123671',337),(204,3,7,1,'2019-05-17 13:46:28','Transito','78123671',338),(205,3,3,1,'2019-05-17 13:47:57','Transito','78123671',339),(206,4,4,1,'2019-05-17 13:48:56','Transito','78123671',340),(207,4,12,1,'2019-05-17 13:53:22','Transito','78123671',341),(208,3,9,4,'2019-05-17 13:53:58','','TRE328',327),(209,4,16,8,'2019-05-17 13:54:13','','TRE328',332),(210,4,12,8,'2019-05-17 13:54:19','','78123671',341),(211,4,8,1,'2019-05-17 13:55:11','Transito','78123671',342),(212,1,2,1,'2019-05-17 13:55:26','Transito','78123671',343),(213,4,4,3,'2019-05-22 11:14:07','Transito','78123671',344),(214,4,8,4,'2019-05-22 15:02:10','Prueba','78123671',342),(215,1,2,4,'2019-05-22 15:02:35','ee','78123671',343),(216,0,0,8,'2019-05-22 15:02:47','ñ','78123671',343),(217,0,0,8,'2019-05-22 15:02:47','ñ','78123671',343),(218,1,3,1,'2019-09-11 07:45:32','Transito','78123671',345),(219,2,6,1,'2019-09-11 07:49:41','Transito','78123671',346),(220,2,5,1,'2019-09-11 08:22:33','Transito','78123671',347),(221,1,3,3,'2019-09-11 08:23:16','Transito','78123671',348),(222,3,9,5,'2019-09-11 12:59:41','Transito','78123671',349),(223,3,9,3,'2019-09-11 13:21:30','Transito','78123671',350),(224,1,3,1,'2019-09-11 13:28:23','Transito','78123671',351),(225,2,5,1,'2019-09-11 13:28:34','Transito','78123671',352);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_destino`
--

DROP TABLE IF EXISTS `historico_destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico_destino` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `preciokilo` decimal(10,2) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `destino_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_historico_destino_destino1_idx` (`destino_id`),
  CONSTRAINT `fk_historico_destino_destino1` FOREIGN KEY (`destino_id`) REFERENCES `destino` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_destino`
--

LOCK TABLES `historico_destino` WRITE;
/*!40000 ALTER TABLE `historico_destino` DISABLE KEYS */;
INSERT INTO `historico_destino` VALUES (44,26.00,'2019-03-25 18:03:50',78),(45,28.00,'2019-03-25 18:04:11',79),(46,22.00,'2019-03-26 12:45:19',80),(47,27.50,'2019-04-04 23:49:42',81),(48,25.00,'2019-04-06 20:36:02',82),(49,29.50,'2019-04-07 14:04:33',83);
/*!40000 ALTER TABLE `historico_destino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumos`
--

DROP TABLE IF EXISTS `insumos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insumos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `estado` int(1) DEFAULT NULL,
  `destino_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_insumos_destino1_idx` (`destino_id`),
  KEY `fk_insumos_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_insumos_destino1` FOREIGN KEY (`destino_id`) REFERENCES `destino` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_insumos_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos`
--

LOCK TABLES `insumos` WRITE;
/*!40000 ALTER TABLE `insumos` DISABLE KEYS */;
INSERT INTO `insumos` VALUES (21,'alimento',30.00,'2019-04-09 17:31:09',1,83,1),(22,'Gasolina',150.00,'2019-04-09 17:31:17',1,83,1);
/*!40000 ALTER TABLE `insumos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `negocio`
--

DROP TABLE IF EXISTS `negocio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `negocio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` char(55) DEFAULT NULL,
  `codigo` char(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `negocio`
--

LOCK TABLES `negocio` WRITE;
/*!40000 ALTER TABLE `negocio` DISABLE KEYS */;
INSERT INTO `negocio` VALUES (6,'Pollos Rayón','Barrrio San Antonio','9191026589       9658966666     9194587459','578dc449eed52e14676461fd0b001c373fae5966');
/*!40000 ALTER TABLE `negocio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(6) DEFAULT NULL,
  `kilos` double DEFAULT NULL,
  `estado` int(2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `ruta_id` int(11) NOT NULL,
  `reja_id` int(11) NOT NULL,
  `configuracion_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pedidos_ruta1_idx` (`ruta_id`),
  KEY `fk_pedidos_reja1_idx` (`reja_id`),
  KEY `fk_pedidos_configuracion1_idx` (`configuracion_id`),
  KEY `fk_pedidos_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_pedidos_configuracion1` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_reja1` FOREIGN KEY (`reja_id`) REFERENCES `reja` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_ruta1` FOREIGN KEY (`ruta_id`) REFERENCES `ruta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (322,4,8.2,8,'2019-04-16 13:29:21',72,11,180,1),(323,3,9.32,8,'2019-04-16 13:29:39',72,12,180,1),(324,6,12.45,4,'2019-04-16 13:29:56',72,13,181,1),(325,8,16.43,8,'2019-04-16 13:30:20',72,15,182,1),(326,5,10.51,2,'2019-04-16 13:30:39',72,16,183,1),(327,3,9,4,'2019-05-16 19:47:47',69,11,184,3),(328,7,21.5,2,'2019-05-16 19:48:53',67,12,185,3),(329,1,3.9299999999999997,3,'2019-05-16 19:49:07',67,17,186,3),(330,4,12,3,'2019-05-16 19:51:32',72,13,187,3),(331,4,4,1,'2019-05-17 13:32:26',68,11,188,3),(332,4,16,8,'2019-05-17 13:35:06',68,13,189,3),(333,4,12,1,'2019-05-17 13:36:35',68,12,190,3),(334,1,2,1,'2019-05-17 13:38:10',68,15,191,3),(335,1,1,1,'2019-05-17 13:39:15',68,16,192,3),(336,1,1,1,'2019-05-17 13:44:16',68,17,193,3),(337,1,1,1,'2019-05-17 13:45:17',68,18,194,3),(338,3,7,1,'2019-05-17 13:46:28',68,19,195,3),(339,3,3,1,'2019-05-17 13:47:57',68,20,196,3),(340,4,4,1,'2019-05-17 13:48:56',68,21,197,3),(341,4,12,8,'2019-05-17 13:53:22',68,22,198,3),(342,4,8,4,'2019-05-17 13:55:11',70,13,199,3),(343,0,0,8,'2019-05-17 13:55:26',67,22,200,3),(344,4,4,3,'2019-05-22 11:14:07',72,11,201,3),(345,1,3,1,'2019-09-11 07:45:32',72,11,202,3),(346,2,6,1,'2019-09-11 07:49:41',72,12,203,3),(347,2,5,1,'2019-09-11 08:22:33',72,13,203,3),(348,1,3,1,'2019-09-11 08:23:16',72,15,204,3),(349,3,9,5,'2019-09-11 12:59:41',72,16,205,3),(350,3,9,3,'2019-09-11 13:21:29',70,16,206,3),(351,1,3,1,'2019-09-11 13:28:23',72,16,207,3),(352,2,5,1,'2019-09-11 13:28:34',70,17,208,3);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollos`
--

DROP TABLE IF EXISTS `pollos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollos` (
  `folio` char(20) NOT NULL,
  `cantidad` int(5) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `proveedor_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`folio`),
  KEY `proveedor_id` (`proveedor_id`),
  KEY `fk_pollos_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_pollos_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pollos_ibfk_1` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollos`
--

LOCK TABLES `pollos` WRITE;
/*!40000 ALTER TABLE `pollos` DISABLE KEYS */;
INSERT INTO `pollos` VALUES ('78123671',466,'2019-05-17 13:45:05',7,3),('TRE328',3,'2019-04-16 13:29:05',7,1);
/*!40000 ALTER TABLE `pollos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollos_total`
--

DROP TABLE IF EXISTS `pollos_total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollos_total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollos_total`
--

LOCK TABLES `pollos_total` WRITE;
/*!40000 ALTER TABLE `pollos_total` DISABLE KEYS */;
INSERT INTO `pollos_total` VALUES (5,469,'2019-05-22 15:02:47');
/*!40000 ALTER TABLE `pollos_total` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilegios`
--

DROP TABLE IF EXISTS `privilegios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilegios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilegios`
--

LOCK TABLES `privilegios` WRITE;
/*!40000 ALTER TABLE `privilegios` DISABLE KEYS */;
INSERT INTO `privilegios` VALUES (1,'Crear'),(2,'Modificar'),(3,'Eliminar'),(4,'Ventas');
/*!40000 ALTER TABLE `privilegios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `preciokilo` decimal(10,2) NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proveedor_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_proveedor_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (7,'Bachoco','Villaflores',21.00,'Proveedor Principal','2019-03-25 17:59:00',0),(8,'Avipollo S.A de C.V','Pichucalco, Chis',22.00,'Proveedor puntual','2019-03-26 13:24:06',0),(9,'Keken Pollos','Tuxtla',21.50,'pollos granes y limpios','2019-04-07 14:01:39',0),(10,'Avimarca','Villahermosa',23.22,'ninguna','2019-04-08 23:35:21',1),(11,'Pollos S.A. de C.V.','Tuxtla ',23.21,'ninguna','2019-04-08 23:36:11',1),(12,'Pollos el pechugon','mexico',21.05,'...','2019-04-08 23:36:53',3),(13,'Pollos del Sur S.A. de C.V.','Queretaro',23.20,'Ninguna','2019-04-14 21:04:57',1),(14,'Pollos Norteños S.A de C.V','Conocida',22.10,'Pollos Blancos','2019-05-22 15:00:37',3);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reja`
--

DROP TABLE IF EXISTS `reja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(5) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reja_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_reja_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reja`
--

LOCK TABLES `reja` WRITE;
/*!40000 ALTER TABLE `reja` DISABLE KEYS */;
INSERT INTO `reja` VALUES (11,56,'Reja en  buen estado	',0),(12,36,'Reja golpeada',0),(13,15,'Reja en excelente estado',0),(15,23,'Reja con capacidad de 15 pollos',0),(16,11,'capacidad con 7 aves, se encuentra en mal estado',0),(17,100,'Reja Nueva',0),(18,20,'Reja Blanca',0),(19,5,'Capacidad con 7 pollos',0),(20,3,'Capacidad con 4 pollos',0),(21,7,'Reja negra	\n',0),(22,50,'capacidad para 8 pollos',0),(23,12,'reja en buen estado',1),(24,999,'Para ventas locales',1),(25,23,'reja con capacidad para 6 a ves',1);
/*!40000 ALTER TABLE `reja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chofer_id` int(11) NOT NULL,
  `destino_id` int(11) NOT NULL,
  `estado` int(1) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chofer_has_carro_chofer_idx` (`chofer_id`),
  KEY `fk_ruta_destino1_idx` (`destino_id`),
  CONSTRAINT `fk_chofer_has_carro_chofer` FOREIGN KEY (`chofer_id`) REFERENCES `chofer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ruta_destino1` FOREIGN KEY (`destino_id`) REFERENCES `destino` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (67,11,78,1,'2019-03-25 18:03:50','zona corta'),(68,12,79,1,'2019-03-25 18:04:12','zona a 30 km'),(69,14,80,1,'2019-03-26 12:45:19','Ruta despejada'),(70,13,81,1,'2019-04-04 23:49:42','Zona peligrosa'),(71,15,82,1,'2019-04-06 20:36:02','Ruta Local'),(72,16,83,1,'2019-04-07 14:04:33','40 km');
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `rutasDisponibles`
--

DROP TABLE IF EXISTS `rutasDisponibles`;
/*!50001 DROP VIEW IF EXISTS `rutasDisponibles`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `rutasDisponibles` (
  `iddestino` tinyint NOT NULL,
  `id` tinyint NOT NULL,
  `chofer` tinyint NOT NULL,
  `destino` tinyint NOT NULL,
  `placas` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL,
  `kilo` tinyint NOT NULL,
  `comentario` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `usuario` char(10) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `estado` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Eddy Sanchez','admin','123',1),(2,'Eddy Sanchez','eddy','123',1),(3,'Manuel Vladimir Diaz Ruiz','root','root',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_privilegios`
--

DROP TABLE IF EXISTS `usuario_privilegios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_privilegios` (
  `usuario_id` int(11) NOT NULL,
  `privilegios_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`privilegios_id`),
  KEY `fk_usuario_has_privilegios_privilegios1` (`privilegios_id`),
  CONSTRAINT `fk_usuario_has_privilegios_privilegios1` FOREIGN KEY (`privilegios_id`) REFERENCES `privilegios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_privilegios_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_privilegios`
--

LOCK TABLES `usuario_privilegios` WRITE;
/*!40000 ALTER TABLE `usuario_privilegios` DISABLE KEYS */;
INSERT INTO `usuario_privilegios` VALUES (1,1),(1,2),(1,3),(3,1),(3,2),(3,3),(3,4);
/*!40000 ALTER TABLE `usuario_privilegios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vcredito`
--

DROP TABLE IF EXISTS `vcredito`;
/*!50001 DROP VIEW IF EXISTS `vcredito`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vcredito` (
  `estado` tinyint NOT NULL,
  `id` tinyint NOT NULL,
  `idcliente` tinyint NOT NULL,
  `factura` tinyint NOT NULL,
  `cantidad` tinyint NOT NULL,
  `kilos` tinyint NOT NULL,
  `preciokilo` tinyint NOT NULL,
  `total` tinyint NOT NULL,
  `nombre` tinyint NOT NULL,
  `fecha` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `venta_credito`
--

DROP TABLE IF EXISTS `venta_credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta_credito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `credito_id` int(11) NOT NULL,
  `configuracion_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_venta_credito_credito1_idx` (`credito_id`),
  KEY `fk_venta_credito_configuracion1_idx` (`configuracion_id`),
  KEY `fk_venta_credito_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_venta_credito_configuracion1` FOREIGN KEY (`configuracion_id`) REFERENCES `configuracion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_credito_credito1` FOREIGN KEY (`credito_id`) REFERENCES `credito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_credito_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_credito`
--

LOCK TABLES `venta_credito` WRITE;
/*!40000 ALTER TABLE `venta_credito` DISABLE KEYS */;
INSERT INTO `venta_credito` VALUES (1,1,179,1),(2,2,187,3),(3,3,201,3),(4,4,186,3),(5,5,204,3),(6,6,204,3),(7,7,205,3),(8,8,205,3),(9,9,205,3),(10,10,206,3);
/*!40000 ALTER TABLE `venta_credito` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `granja`.`tcredito`
AFTER INSERT ON `granja`.`venta_credito`
FOR EACH ROW
BEGIN
IF((select truncate(total,2) from vcredito where factura = (select distinct configuracion_id from venta_credito where id =(select max(id) from venta_credito)))=(select sum(monto) from venta_credito as vc
inner join credito as c on vc.credito_id = c.id where vc.configuracion_id = (select configuracion_id from venta_credito where id =(select max(id) from venta_credito)))) THEN
    
    UPDATE pedidos SET estado = 5 where configuracion_id = (select configuracion_id from venta_credito where id =(select max(id) from venta_credito));
    UPDATE historico set tipo = 5 where pedidos_id in (select id from pedidos where configuracion_id = (select configuracion_id from venta_credito where id =(select max(id) from venta_credito)));
    END IF;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary table structure for view `vventa_credito`
--

DROP TABLE IF EXISTS `vventa_credito`;
/*!50001 DROP VIEW IF EXISTS `vventa_credito`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vventa_credito` (
  `id` tinyint NOT NULL,
  `credito_id` tinyint NOT NULL,
  `configuracion_id` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'granja'
--

--
-- Dumping routines for database 'granja'
--
/*!50003 DROP PROCEDURE IF EXISTS `registroCancelacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroCancelacion`(in IDpedido int, KgPollo double, CantPollos int,comentarios varchar(255),folioPollo char(20))
BEGIN
		declare cantP int(6);
        declare kilosP double;
        declare totPollo int;
        declare sumPollo int;
        
		if CantPollos>0 then
        
			
            insert into historico values (0,CantPollos,KgPollo,4,now(),comentarios,folioPollo,IDpedido);
            
            set @totPollo=(SELECT cantidad from pollos where folio=folioPollo);
            set @sumPollo=(SELECT total from pollos_total);
            set @cantP=(SELECT cantidad from pedidos where id=IDpedido);
			set @kilosP=(SELECT kilos from pedidos where id=IDpedido);
            
            update pollos set cantidad=(@totPollo+CantPollos) where folio=folioPollo;
            update pollos_total set total=(@sumPollo+CantPollos), fecha=now();
            
            update pedidos set cantidad=(@cantP-CantPollos),kilos=(@kilosP-KgPollo) where id=IDpedido;
			
            SELECT 1 as 'estado';
        else
        
			set @cantP=(SELECT cantidad from pedidos where id=IDpedido);
            set @kilosP=(SELECT kilos from pedidos where id=IDpedido);
            
            
            insert into historico values (0,@cantP,@kilosP,4,now(),comentarios,folioPollo,IDpedido);
            
            set @totPollo=(SELECT cantidad from pollos where folio=folioPollo);
            set @sumPollo=(SELECT total from pollos_total);
            
            update pollos set cantidad=(@totPollo+@cantP) where folio=folioPollo;
            update pollos_total set total=(@sumPollo+@cantP), fecha=now();
            
            update pedidos set estado=4 where id=IDpedido;
			
            SELECT 1 as 'estado';                        
        
        end if;
        
        
        
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroCancelacionNo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroCancelacionNo`(in IDpedido int, KgPollo double, CantPollos int,comentarios varchar(255),folioPollo char(20))
BEGIN
		declare cantP int(6);
        declare kilosP double;
        declare totPollo int;
        declare sumPollo int;
        
		if CantPollos>0 then
        
			
            insert into historico values (0,CantPollos,KgPollo,8,now(),comentarios,folioPollo,IDpedido);
            
            set @totPollo=(SELECT cantidad from pollos where folio=folioPollo);
            set @sumPollo=(SELECT total from pollos_total);
            set @cantP=(SELECT cantidad from pedidos where id=IDpedido);
			set @kilosP=(SELECT kilos from pedidos where id=IDpedido);
            
            update pollos set cantidad=(@totPollo) where folio=folioPollo;
            update pollos_total set total=(@sumPollo), fecha=now();
            
            update pedidos set cantidad=(@cantP-CantPollos),kilos=(@kilosP-KgPollo) where id=IDpedido;
			
            SELECT 1 as 'estado';
        else
        
			set @cantP=(SELECT cantidad from pedidos where id=IDpedido);
            set @kilosP=(SELECT kilos from pedidos where id=IDpedido);
            
            
            insert into historico values (0,@cantP,@kilosP,8,now(),comentarios,folioPollo,IDpedido);
            
            set @totPollo=(SELECT cantidad from pollos where folio=folioPollo);
            set @sumPollo=(SELECT total from pollos_total);
            
            update pollos set cantidad=(@totPollo) where folio=folioPollo;
            update pollos_total set total=(@sumPollo), fecha=now();
            
            update pedidos set estado=8 where id=IDpedido;
			
            SELECT 1 as 'estado';                        
        
        end if;
        
        
        
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroHistorico` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroHistorico`(in idDestino int,kilo decimal(10,5),obs varchar(255))
BEGIN
        
        update destino set preciokilo=kilo where id=idDestino;
        insert into historico_destino values (0,kilo,now(),idDestino);
        update ruta set observacion=obs where destino_id=idDestino;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroPedidos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroPedidos`(in cantidadU int,kilos double,ruta_id int,reja_id int,factura int,foliofactura char(20),idUsuario int)
BEGIN
    declare disponible int;
    declare totalpollos int;
    declare max int;
    declare folioP char(20);
    declare nuevoTotal int;
    declare pollosA int;
    set @disponible=(SELECT total from pollos_total);
    
    
    set @pollosA=(SELECT cantidad from pollos where folio=foliofactura);

    if cantidadU<=@disponible then
        
        set @totalpollos=@disponible-cantidadU;
        update pollos_total set total=@totalpollos;
        
        set @nuevoTotal=(@pollosA-cantidadU);
		
        update pollos set cantidad=@nuevoTotal where folio=foliofactura;	
        insert into pedidos values (0,cantidadU,kilos,1,now(),ruta_id,reja_id,factura,idUsuario);
        set @max=(SELECT max(id) from pedidos);
        insert into historico values (0,cantidadU,kilos,1,now(),'Transito',foliofactura,@max);
        SELECT 1 as 'estado';
    else
        SELECT 0 as 'estado';
     end if;
    
    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroRuta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroRuta`(in nombreDestino varchar(200), 
														preciokilo decimal(10,2), 
														chofer_id int,
                                                        carro_id int,
                                                        observacion varchar(200),idUsuario int)
BEGIN
		declare busqueda varchar(100);
        declare busquedaChofer varchar(100);
        declare maximo int;
        
        SET @busqueda=(select  destino.nombre from destino,ruta where destino.id=ruta.destino_id and ruta.estado=1 and destino.nombre=nombreDestino);
         
        
        
        
                
       
        if @busqueda is NULL THEN
	
              
            insert into destino values (0,nombreDestino,preciokilo,carro_id,idUsuario);
            
            SET @maximo=(SELECT max(id) from destino);
            insert into historico_destino values(0,preciokilo,now(),@maximo);
           
            
            insert into ruta values (0,chofer_id,@maximo,1,now(),observacion);
            SELECT 1 as 'Estado';
		else
			SELECT 0 as 'Estado';
          
		end if;
        
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `rutasDisponibles`
--

/*!50001 DROP TABLE IF EXISTS `rutasDisponibles`*/;
/*!50001 DROP VIEW IF EXISTS `rutasDisponibles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rutasDisponibles` AS select `ruta`.`destino_id` AS `iddestino`,`ruta`.`id` AS `id`,`chofer`.`nombre` AS `chofer`,`destino`.`nombre` AS `destino`,`carro`.`placas` AS `placas`,`carro`.`descripcion` AS `descripcion`,`destino`.`preciokilo` AS `kilo`,`ruta`.`observacion` AS `comentario` from (((`ruta` join `destino`) join `chofer`) join `carro`) where ((`chofer`.`id` = `ruta`.`chofer_id`) and (`ruta`.`destino_id` = `destino`.`id`) and (`destino`.`carro_id` = `carro`.`id`) and (`ruta`.`estado` = 1)) order by `ruta`.`fecha` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vcredito`
--

/*!50001 DROP TABLE IF EXISTS `vcredito`*/;
/*!50001 DROP VIEW IF EXISTS `vcredito`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vcredito` AS select `p`.`estado` AS `estado`,`p`.`id` AS `id`,`cl`.`id` AS `idcliente`,`vc`.`configuracion_id` AS `factura`,sum(`p`.`cantidad`) AS `cantidad`,sum(`p`.`kilos`) AS `kilos`,`d`.`preciokilo` AS `preciokilo`,(sum(`p`.`kilos`) * `d`.`preciokilo`) AS `total`,`cl`.`nombre` AS `nombre`,`p`.`fecha` AS `fecha` from ((((((`pedidos` `p` join `configuracion` `co` on((`p`.`configuracion_id` = `co`.`id`))) join `ruta` `r` on((`p`.`ruta_id` = `r`.`id`))) join `destino` `d` on((`r`.`destino_id` = `d`.`id`))) join `vventa_credito` `vc` on((`co`.`id` = `vc`.`configuracion_id`))) join `credito` `cr` on((`vc`.`credito_id` = `cr`.`id`))) join `cliente` `cl` on((`cr`.`cliente_id` = `cl`.`id`))) group by `co`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vventa_credito`
--

/*!50001 DROP TABLE IF EXISTS `vventa_credito`*/;
/*!50001 DROP VIEW IF EXISTS `vventa_credito`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vventa_credito` AS select `venta_credito`.`id` AS `id`,`venta_credito`.`credito_id` AS `credito_id`,`venta_credito`.`configuracion_id` AS `configuracion_id` from `venta_credito` group by `venta_credito`.`configuracion_id` */;
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

-- Dump completed on 2019-09-17 16:05:26
