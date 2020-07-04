-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.18 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных restaurant
CREATE DATABASE IF NOT EXISTS `restaurant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `restaurant`;

-- Дамп структуры для таблица restaurant.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.admin: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id_admin`, `login`, `password`) VALUES
	(1, 'admin', 'admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.client
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.client: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`id_client`, `name`, `surname`, `login`, `password`) VALUES
	(1, 'Гоша', 'Паскробка', 'йцу', 'йцу'),
	(2, 'qwe', 'qwe', 'qwe', 'qwe'),
	(3, 'asd', 'asd', 'asd', 'asd'),
	(7, 'Саша', 'Боровиков', 'ячс', 'ячс');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.clientsorder
CREATE TABLE IF NOT EXISTS `clientsorder` (
  `id_clientsOrder` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `table` int(11) NOT NULL,
  `dish` varchar(50) NOT NULL,
  `amount` int(11) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Заказан',
  PRIMARY KEY (`id_clientsOrder`),
  KEY `dish` (`dish`),
  CONSTRAINT `FK_clientsorder_menu` FOREIGN KEY (`dish`) REFERENCES `menu` (`dish`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.clientsorder: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `clientsorder` DISABLE KEYS */;
INSERT INTO `clientsorder` (`id_clientsOrder`, `name`, `table`, `dish`, `amount`, `status`) VALUES
	(1, 'qwe', 4, 'Виски', 5, 'Подано'),
	(2, 'asd', 5, 'Виски', 3, 'Подано'),
	(5, 'Даниил', 5, 'Цезарь', 6, 'Подано');
/*!40000 ALTER TABLE `clientsorder` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id_menu` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `dish` varchar(50) NOT NULL,
  `weight` int(11) unsigned NOT NULL,
  `cost` double unsigned NOT NULL,
  PRIMARY KEY (`id_menu`),
  KEY `type` (`type`),
  KEY `dish` (`dish`),
  CONSTRAINT `FK_menu_type` FOREIGN KEY (`type`) REFERENCES `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.menu: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id_menu`, `type`, `dish`, `weight`, `cost`) VALUES
	(2, 'Алкоголь', 'Виски', 50, 10),
	(3, 'Салаты', 'Цезарь', 300, 10);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.order
CREATE TABLE IF NOT EXISTS `order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `seats` int(11) unsigned NOT NULL,
  `date` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL,
  `table` int(11) DEFAULT '0',
  `steward` varchar(50) DEFAULT NULL,
  `account` double DEFAULT '0',
  PRIMARY KEY (`id_order`),
  KEY `steward` (`steward`),
  CONSTRAINT `FK_order_steward` FOREIGN KEY (`steward`) REFERENCES `steward` (`surname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.order: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id_order`, `name`, `phone`, `seats`, `date`, `time`, `table`, `steward`, `account`) VALUES
	(5, 'Александр', '+375298631355', 5, '12.02.2020', '15.00', 3, 'Боровиков', 0),
	(6, 'Даник', '+375298631355', 8, '7.06.2020', '15.00', 8, 'Коршов', 60);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.status
CREATE TABLE IF NOT EXISTS `status` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_status`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.status: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` (`id_status`, `status`) VALUES
	(3, 'Забронирован'),
	(2, 'Занят'),
	(1, 'Свободен');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.steward
CREATE TABLE IF NOT EXISTS `steward` (
  `id_steward` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  PRIMARY KEY (`id_steward`),
  UNIQUE KEY `login` (`login`),
  KEY `surname` (`surname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.steward: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `steward` DISABLE KEYS */;
INSERT INTO `steward` (`id_steward`, `login`, `password`, `name`, `surname`) VALUES
	(1, 'qwe', 'qwe', 'Александр', 'Боровиков'),
	(4, 'korsh', '123', 'Даниил', 'Коршов');
/*!40000 ALTER TABLE `steward` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.tables
CREATE TABLE IF NOT EXISTS `tables` (
  `id_table` int(11) NOT NULL AUTO_INCREMENT,
  `table` int(11) unsigned NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_table`),
  KEY `status` (`status`),
  CONSTRAINT `FK_tables_status` FOREIGN KEY (`status`) REFERENCES `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.tables: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` (`id_table`, `table`, `status`) VALUES
	(1, 1, 'Занят'),
	(2, 2, 'Свободен'),
	(3, 3, 'Занят');
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;

-- Дамп структуры для таблица restaurant.type
CREATE TABLE IF NOT EXISTS `type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы restaurant.type: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` (`id_type`, `type`) VALUES
	(3, 'Алкоголь'),
	(2, 'Горячие блюда'),
	(5, 'Десерты'),
	(1, 'Напитки'),
	(7, 'Салаты'),
	(4, 'Супы'),
	(6, 'Холодные закуски');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
