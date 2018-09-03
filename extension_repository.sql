-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for extension_repository
CREATE DATABASE IF NOT EXISTS `extension_repository` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `extension_repository`;

-- Dumping structure for table extension_repository.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.authorities: ~0 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table extension_repository.extensions_old
CREATE TABLE IF NOT EXISTS `extensions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `description` tinytext NOT NULL DEFAULT '0',
  `version` varchar(50) NOT NULL DEFAULT '0',
  `number_of_downloads` bigint(20) NOT NULL DEFAULT 0,
  `source_repository_link` text NOT NULL DEFAULT '0',
  `download_link` text NOT NULL DEFAULT '0',
  `number_of_open_issues` int(10) NOT NULL DEFAULT 0,
  `number_of_pull_request` int(10) NOT NULL DEFAULT 0,
  `last_commit_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extensions_old: ~0 rows (approximately)
/*!40000 ALTER TABLE `extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `extensions` ENABLE KEYS */;

-- Dumping structure for table extension_repository.extensions_customers
CREATE TABLE IF NOT EXISTS `extensions_customers` (
  `extension_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  PRIMARY KEY (`extension_id`,`user_id`),
  KEY `FK_extensions_customers_users` (`user_id`),
  CONSTRAINT `FK_extensions_customers_extensions` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`),
  CONSTRAINT `FK_extensions_customers_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extensions_customers: ~0 rows (approximately)
/*!40000 ALTER TABLE `extensions_customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `extensions_customers` ENABLE KEYS */;

-- Dumping structure for table extension_repository.owners_extensions
CREATE TABLE IF NOT EXISTS `owners_extensions` (
  `user_id` int(10) NOT NULL,
  `extension_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`extension_id`),
  KEY `FK_owners_extensions_extensions` (`extension_id`),
  CONSTRAINT `FK_owners_extensions_extensions` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`),
  CONSTRAINT `FK_owners_extensions_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.owners_extensions: ~0 rows (approximately)
/*!40000 ALTER TABLE `owners_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `owners_extensions` ENABLE KEYS */;

-- Dumping structure for table extension_repository.tags
CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.tags: ~0 rows (approximately)
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;

-- Dumping structure for table extension_repository.tags_extensions
CREATE TABLE IF NOT EXISTS `tags_extensions` (
  `tag_id` int(10) NOT NULL,
  `extension_id` int(10) NOT NULL,
  PRIMARY KEY (`tag_id`,`extension_id`),
  KEY `FK_tags_extensions_extensions` (`extension_id`),
  CONSTRAINT `FK_tags_extensions_extensions` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`),
  CONSTRAINT `FK_tags_extensions_tags` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.tags_extensions: ~0 rows (approximately)
/*!40000 ALTER TABLE `tags_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags_extensions` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(20) NOT NULL DEFAULT '0',
  `email` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users_authorities
CREATE TABLE IF NOT EXISTS `users_authorities` (
  `user_id` int(10) NOT NULL,
  `authority_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  KEY `FK_users_authorities_authorities` (`authority_id`),
  CONSTRAINT `FK_users_authorities_authorities` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`),
  CONSTRAINT `FK_users_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Dumping data for table extension_repository.users_authorities: ~0 rows (approximately)
/*!40000 ALTER TABLE `users_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_authorities` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;