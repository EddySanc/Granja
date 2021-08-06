-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: granja
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB-0+deb9u1

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro`
--

LOCK TABLES `carro` WRITE;
/*!40000 ALTER TABLE `carro` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chofer`
--

LOCK TABLES `chofer` WRITE;
/*!40000 ALTER TABLE `chofer` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,1),(3,2),(4,3),(5,4),(6,5),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),(49,1),(50,1),(51,1),(52,1),(53,1),(54,1),(55,1),(56,1),(57,1),(58,1),(59,1),(60,1),(61,1),(62,1),(63,1),(64,1),(65,1),(66,1),(67,1),(68,1),(69,1),(70,1),(71,1),(72,1),(73,1),(74,1),(75,1),(76,1),(77,1),(78,1),(79,1),(80,1),(81,1),(82,1),(83,1),(84,1),(85,1),(86,1),(87,1),(88,1),(89,1),(90,1),(91,1),(92,1),(93,1),(94,1),(95,1),(96,1),(97,1),(98,1),(99,1),(100,1),(101,1),(102,1),(103,1),(104,1),(105,1),(106,1),(107,1),(108,1),(109,1),(110,1),(111,1),(112,1),(113,1),(114,1),(115,1),(116,1),(117,1),(118,1),(119,1),(120,1),(121,1),(122,1),(123,1),(124,1),(125,1),(126,1),(127,1),(128,1),(129,1),(130,1),(131,1),(132,1),(133,1),(134,1),(135,1),(136,1),(137,1),(138,1),(139,1),(140,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credito`
--

LOCK TABLES `credito` WRITE;
/*!40000 ALTER TABLE `credito` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos`
--

LOCK TABLES `insumos` WRITE;
/*!40000 ALTER TABLE `insumos` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `negocio`
--

LOCK TABLES `negocio` WRITE;
/*!40000 ALTER TABLE `negocio` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollos_total`
--

LOCK TABLES `pollos_total` WRITE;
/*!40000 ALTER TABLE `pollos_total` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reja`
--

LOCK TABLES `reja` WRITE;
/*!40000 ALTER TABLE `reja` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_credito`
--

LOCK TABLES `venta_credito` WRITE;
/*!40000 ALTER TABLE `venta_credito` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta_credito` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `granja`.`tcredito`
AFTER INSERT ON `granja`.`venta_credito`
FOR EACH ROW
BEGIN
	IF((select total from vcredito where factura = (select distinct configuracion_id from venta_credito where id =(select max(id) from venta_credito)))=(select sum(monto) from venta_credito as vc
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
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
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

-- Dump completed on 2019-04-09 19:25:43
