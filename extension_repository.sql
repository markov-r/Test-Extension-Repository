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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.authorities: ~2 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT IGNORE INTO `authorities` (`id`, `authority`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table extension_repository.extensions
CREATE TABLE IF NOT EXISTS `extensions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `is_featured` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number_of_downloads` int(11) DEFAULT NULL,
  `source_repository_link` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `git_hub_data_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8aip03gmt5hhgebh1e3wnvk34` (`git_hub_data_id`),
  KEY `FKkbnbqodseugohtwwyah8uw8c9` (`user_id`),
  CONSTRAINT `FK8aip03gmt5hhgebh1e3wnvk34` FOREIGN KEY (`git_hub_data_id`) REFERENCES `git_hub_data` (`id`),
  CONSTRAINT `FKkbnbqodseugohtwwyah8uw8c9` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.extensions: ~1 rows (approximately)
/*!40000 ALTER TABLE `extensions` DISABLE KEYS */;
INSERT IGNORE INTO `extensions` (`id`, `description`, `file_name`, `is_featured`, `name`, `number_of_downloads`, `source_repository_link`, `status`, `upload_date`, `version`, `git_hub_data_id`, `user_id`) VALUES
	(4, 'A Vim emulator extension', 'blob.zip', b'0', 'Vim', 0, 'https://github.com/vision-of-us/Resident-Evil', 'APPROVED', '2018-09-09 17:02:20', '1', 4, 1),
	(5, 'Some of the most popular Python extensions.', 'blob.zip', b'0', 'Python Extension Pack', 0, 'https://github.com/vision-of-us/Java-Blog', 'APPROVED', '2018-09-09 17:04:25', '1', 5, 1),
	(6, 'Deploy static websites and browse Azure Blob Containers.', 'blob.zip', b'0', 'Azure Storage', 0, 'https://github.com/vision-of-us/Tasks-Demo', 'APPROVED', '2018-09-09 17:06:25', '1', 6, 1),
	(7, 'Debug your code in the Chrome browser.', 'blob.zip', b'0', 'Debugger for Chrome', 0, 'https://github.com/markov-r/algo-tasks', 'APPROVED', '2018-09-09 17:17:50', '2.0', 7, 1),
	(8, 'A dark theme modeled after the Chrome Dev Tools.', 'blob.zip', b'0', 'Zero Dark Matrix Reloaded', 0, 'https://github.com/markov-r/markov-r.github.io', 'APPROVED', '2018-09-09 17:19:11', '1.0', 8, 1),
	(9, 'An extension to debug your code in Chrome browser.', 'blob.zip', b'0', 'Android WebView Debugging', 0, 'https://github.com/vision-of-us/Extension-Repository', 'APPROVED', '2018-09-09 17:22:52', '1.1', 9, 1),
	(10, 'Art template support for Visual Studio Code', 'blob.zip', b'0', 'Art Template Helper', 0, 'https://github.com/vision-of-us/Car-Dealer', 'APPROVED', '2018-09-09 17:24:25', '1.0', 10, 1),
	(11, 'Integrates ESLint JavaScript into your IDE.', 'blob.zip', b'0', 'ESLint', 0, 'https://github.com/vision-of-us/Test-Extension-Repository', 'APPROVED', '2018-09-09 17:27:09', '1.1', 11, 1),
	(12, 'Something for WXML written in Asian language.', 'blob.zip', b'0', 'WXML', 0, 'https://github.com/cnyballk/wxml-vscode', 'APPROVED', '2018-09-09 17:49:40', '2.1', 12, 1),
	(13, 'Rich language support for the Go language.', 'blob.zip', b'0', 'Go', 0, 'https://github.com/Microsoft/vscode-go/', 'APPROVED', '2018-09-09 17:51:35', '1.0', 13, 2),
	(14, 'Autocompletion for classes/ids from markup documents to stylesheets.', 'blob.zip', b'0', 'HTML to CSS autocompletion', 0, 'https://github.com/solnurkarim/HTML-to-CSS-autocompletion', 'APPROVED', '2018-09-09 17:52:34', '2.1', 14, 2),
	(15, 'TypeScript/Html snippets for Angular 2,4,5 & 6.', 'blob.zip', b'0', 'Angular 6 Snippets', 0, 'https://github.com/BeastCode/VSCode-Angular-TypeScript-Snippets', 'APPROVED', '2018-09-09 17:54:34', '3.1', 15, 2),
	(16, 'Quickly run SharePoint Framework tasks.', 'blob.zip', b'0', 'SPFx Task Runner', 0, 'https://github.com/estruyf/vscode-spfx-task-runner', 'APPROVED', '2018-09-09 18:05:23', '2.1', 16, 1),
	(17, 'Visualize code authorship at a glance via Git.', 'blob.zip', b'0', 'GitLens', 0, 'https://github.com/eamodio/vscode-gitlens.git', 'APPROVED', '2018-09-09 18:12:21', '1.1', NULL, 1),
	(18, 'Provides rich PowerShell language support .', 'blob.zip', b'0', 'PowerShell', 0, 'https://github.com/PowerShell/vscode-powershell.git', 'APPROVED', '2018-09-09 18:13:46', '1.1', NULL, 2),
	(19, 'Explore and query SQLite databases.', 'blob.zip', b'0', 'SQLite', 0, 'https://github.com/AlexCovizzi/vscode-sqlite', 'APPROVED', '2018-09-09 18:15:35', '1.1', 17, 2);
/*!40000 ALTER TABLE `extensions` ENABLE KEYS */;

-- Dumping structure for table extension_repository.git_hub_data
CREATE TABLE IF NOT EXISTS `git_hub_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num_of_issues` varchar(255) DEFAULT NULL,
  `latest_commit` datetime DEFAULT NULL,
  `num_of_pulls` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.git_hub_data: ~1 rows (approximately)
/*!40000 ALTER TABLE `git_hub_data` DISABLE KEYS */;
INSERT IGNORE INTO `git_hub_data` (`id`, `num_of_issues`, `latest_commit`, `num_of_pulls`) VALUES
	(4, '1', '2018-08-27 13:48:11', '0'),
	(5, '7', '2018-08-29 23:20:38', '0'),
	(6, '12', '2018-08-20 16:03:45', '2'),
	(7, '0', '2018-09-04 11:49:45', '0'),
	(8, '1', '2018-07-04 14:18:29', '0'),
	(9, '5', '2018-09-03 15:45:21', '0'),
	(10, '3', '2018-08-27 13:45:39', '0'),
	(11, '0', '2018-09-09 16:15:24', '1'),
	(12, '0', '2018-09-08 11:26:31', '2'),
	(13, '164', '2018-09-09 15:42:11', '432'),
	(14, '0', '2018-09-09 09:30:37', '0'),
	(15, '15', '2018-09-09 07:05:52', '18'),
	(16, '0', '2018-09-06 14:00:02', '0'),
	(17, '4', '2018-09-05 17:02:24', '11');
/*!40000 ALTER TABLE `git_hub_data` ENABLE KEYS */;

-- Dumping structure for table extension_repository.logs
CREATE TABLE IF NOT EXISTS `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgqy8beil5y4almtq1tiyofije` (`user_id`),
  CONSTRAINT `FKgqy8beil5y4almtq1tiyofije` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.logs: ~0 rows (approximately)
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;

-- Dumping structure for table extension_repository.system_properties
CREATE TABLE IF NOT EXISTS `system_properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `failure_details` varchar(255) NOT NULL,
  `last_failed_sync` datetime NOT NULL,
  `last_succ_sync` datetime NOT NULL,
  `update_interval` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.system_properties: ~1 rows (approximately)
/*!40000 ALTER TABLE `system_properties` DISABLE KEYS */;
INSERT IGNORE INTO `system_properties` (`id`, `failure_details`, `last_failed_sync`, `last_succ_sync`, `update_interval`, `version`) VALUES
	(1, 'no info', '2018-09-07 14:04:22', '2018-09-09 18:23:59', 120000, 17);
/*!40000 ALTER TABLE `system_properties` ENABLE KEYS */;

-- Dumping structure for table extension_repository.tags
CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.tags: ~2 rows (approximately)
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT IGNORE INTO `tags` (`id`, `name`) VALUES
	(1, 'Java'),
	(2, 'Blop'),
	(3, 'Vim'),
	(4, 'emulator'),
	(5, 'Python'),
	(6, 'pack'),
	(7, 'Azure'),
	(8, 'storage'),
	(9, 'Chrome'),
	(10, 'debugger'),
	(11, 'Dark'),
	(12, 'theme'),
	(13, 'Debugging'),
	(14, 'WebView'),
	(15, 'Art'),
	(16, 'template'),
	(17, 'ESLint'),
	(18, 'integration'),
	(19, 'WXML'),
	(20, 'Go'),
	(21, 'HTML'),
	(22, 'CSS'),
	(23, 'snippet'),
	(24, 'Angular'),
	(25, 'SPFx'),
	(26, 'runner'),
	(27, 'Annotation'),
	(28, 'git'),
	(29, 'Powershell'),
	(30, 'SQL');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;

-- Dumping structure for table extension_repository.tags_extensions
CREATE TABLE IF NOT EXISTS `tags_extensions` (
  `tag_id` int(11) NOT NULL,
  `extension_id` bigint(20) NOT NULL,
  PRIMARY KEY (`extension_id`,`tag_id`),
  KEY `FKrht00kvx0mtxmdidkjr8fx4fa` (`tag_id`),
  CONSTRAINT `FK2eqt1npckycdmpw6ctptl42wj` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`),
  CONSTRAINT `FKrht00kvx0mtxmdidkjr8fx4fa` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.tags_extensions: ~2 rows (approximately)
/*!40000 ALTER TABLE `tags_extensions` DISABLE KEYS */;
INSERT IGNORE INTO `tags_extensions` (`tag_id`, `extension_id`) VALUES
	(3, 4),
	(4, 4),
	(5, 5),
	(6, 5),
	(7, 6),
	(8, 6),
	(9, 7),
	(10, 7),
	(11, 8),
	(12, 8),
	(13, 9),
	(14, 9),
	(15, 10),
	(16, 10),
	(17, 11),
	(18, 11),
	(19, 12),
	(20, 13),
	(21, 14),
	(22, 14),
	(23, 15),
	(24, 15),
	(25, 16),
	(26, 16),
	(27, 17),
	(28, 17),
	(23, 18),
	(29, 18),
	(30, 19);
/*!40000 ALTER TABLE `tags_extensions` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_account_non_expired` bit(1) NOT NULL,
  `is_account_non_locked` bit(1) NOT NULL,
  `is_credentials_non_expired` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`id`, `email`, `is_account_non_expired`, `is_account_non_locked`, `is_credentials_non_expired`, `is_enabled`, `password`, `username`) VALUES
	(1, 'radoslav@ggg.com', b'1', b'1', b'1', b'1', '$2a$10$JRXMeLvo7q4aRhZSAp/kKO6C9cOmUUj7vt.uIoq/WZX0lpFeex8ym', 'radoslav'),
	(2, 'sdf@asd.com', b'1', b'1', b'1', b'1', '$2a$10$UhHZ1y1hqcG15UC/1M8oYu9pAhEzXfUMm1e2t8L/2Xq3j9S/HKEnq', 'parashkev'),
	(3, 'oksana666@gmail.com', b'1', b'1', b'1', b'1', '$2a$10$diQHeBGskKHxNLB7wnZ5Jed1k6VmvcnMy52HhtpyGAkV.v8VIfROy', 'oksana');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table extension_repository.users_authorities
CREATE TABLE IF NOT EXISTS `users_authorities` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK310yp83gynb6dlfimphwe1aa6` (`role_id`),
  CONSTRAINT `FK310yp83gynb6dlfimphwe1aa6` FOREIGN KEY (`role_id`) REFERENCES `authorities` (`id`),
  CONSTRAINT `FKq3lq694rr66e6kpo2h84ad92q` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table extension_repository.users_authorities: ~2 rows (approximately)
/*!40000 ALTER TABLE `users_authorities` DISABLE KEYS */;
INSERT IGNORE INTO `users_authorities` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 1);
/*!40000 ALTER TABLE `users_authorities` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
