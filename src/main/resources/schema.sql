SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `goa_ss` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `goa_ss`;

DROP TABLE IF EXISTS `anime`;
CREATE TABLE IF NOT EXISTS `anime` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `address` varchar(255) DEFAULT NULL,
                                       `birth` timestamp NULL DEFAULT NULL,
                                       `date` varchar(255) DEFAULT NULL,
                                       `genre` varchar(255) DEFAULT NULL,
                                       `history` text DEFAULT NULL,
                                       `image` varchar(255) DEFAULT NULL,
                                       `links` text DEFAULT NULL,
                                       `name` varchar(255) DEFAULT NULL,
                                       `producer` varchar(255) DEFAULT NULL,
                                       `season` int(11) NOT NULL,
                                       `views` int(11) NOT NULL,
                                       `author_id` bigint(20) DEFAULT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `FKg456qmi4qcksi3cnadd0b1wmi` (`author_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `anime_video`;
CREATE TABLE IF NOT EXISTS `anime_video` (
                                             `anime_id` bigint(20) NOT NULL,
                                             `video_id` bigint(20) NOT NULL,
                                             PRIMARY KEY (`anime_id`,`video_id`),
                                             KEY `FK2mqwaqbtjdt6va8iy6vasx6a8` (`video_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bookmark`;
CREATE TABLE IF NOT EXISTS `bookmark` (
                                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                          `id_anime` bigint(20) DEFAULT NULL,
                                          `id_ep` bigint(20) DEFAULT NULL,
                                          `id_season` bigint(20) DEFAULT NULL,
                                          `anime_id` bigint(20) DEFAULT NULL,
                                          `user_id` bigint(20) DEFAULT NULL,
                                          PRIMARY KEY (`id`),
                                          KEY `FK100e3hx71ilfvyq0g6kyesx5t` (`anime_id`),
                                          KEY `FK3ogdxsxa4tx6vndyvpk1fk1am` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                         `date` datetime DEFAULT NULL,
                                         `text` varchar(255) DEFAULT NULL,
                                         `user_id` int(11) NOT NULL,
                                         `anime_id` int(11) NOT NULL,
                                         PRIMARY KEY (`id`),
                                         KEY `FK2gsad27q3ni66pqpcko7nelp4` (`anime_id`),
                                         KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `description` varchar(255) DEFAULT NULL,
                                       `role` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `email` varchar(255) DEFAULT NULL,
                                      `is_active` varchar(255) DEFAULT NULL,
                                      `password` varchar(255) DEFAULT NULL,
                                      `username` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_friends`;
CREATE TABLE IF NOT EXISTS `user_friends` (
                                              `user_id` bigint(20) NOT NULL,
                                              `friends_id` bigint(20) NOT NULL,
                                              PRIMARY KEY (`user_id`,`friends_id`),
                                              UNIQUE KEY `UK_bbrnh12js1l8culgfpipyat29` (`friends_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
                                            `user_id` bigint(20) NOT NULL,
                                            `roles_id` bigint(20) NOT NULL,
                                            PRIMARY KEY (`user_id`,`roles_id`),
                                            KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `video`;
CREATE TABLE IF NOT EXISTS `video` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `season` int(11) NOT NULL,
                                       `series` int(11) NOT NULL,
                                       `start` int(11) NOT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

