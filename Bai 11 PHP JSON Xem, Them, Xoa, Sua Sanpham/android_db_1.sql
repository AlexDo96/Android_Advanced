-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Dec 02, 2012 at 12:16 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `android_db_1`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `products`
-- 

CREATE TABLE `products` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) default NULL,
  `description` text,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

-- 
-- Dumping data for table `products`
-- 

INSERT INTO `products` VALUES (1, 'Nokia', 200.00, 'dang e hang', '2012-12-01 21:23:49', '2012-12-02 11:42:19');
INSERT INTO `products` VALUES (2, 'no kia', 200.00, 'e e e e', '2012-12-01 21:24:09', '2012-12-02 11:22:45');
INSERT INTO `products` VALUES (4, 'samsung galaxy', 350.00, 'con hang', '2012-12-01 22:20:57', NULL);

-- --------------------------------------------------------

-- 
-- Table structure for table `test`
-- 

CREATE TABLE `test` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- 
-- Dumping data for table `test`
-- 

INSERT INTO `test` VALUES (1, 'teo');
INSERT INTO `test` VALUES (2, 'teo');
INSERT INTO `test` VALUES (3, 'ti');

-- --------------------------------------------------------

-- 
-- Table structure for table `user`
-- 

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

-- 
-- Dumping data for table `user`
-- 

INSERT INTO `user` VALUES (11, 'beo', 'beo@yahoo.com', '1', '2012-11-27 13:57:38', NULL);
INSERT INTO `user` VALUES (3, 'ti', 'ti@yahoo.com', '1', NULL, NULL);
INSERT INTO `user` VALUES (5, 'tin', 'abc@yahoo.com', '1', '2012-11-23 11:07:25', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES (9, '1fsf', 'teo@yahoo.com', '1', '2012-11-27 13:53:19', NULL);
INSERT INTO `user` VALUES (10, 'aaa', 'aaa@yahoo.com', '1', '2012-11-27 13:56:47', NULL);
INSERT INTO `user` VALUES (12, 'khoa', 'khoa@yahoo.com', '1', '2012-12-01 11:39:02', NULL);
INSERT INTO `user` VALUES (13, 'anhkhoa', 'anhkhoa@yahoo.com', '1', '2012-12-01 12:14:29', NULL);
INSERT INTO `user` VALUES (14, 'anhkhoa1', 'khoanguyendo@yahoo.com', '123', '2012-12-01 12:16:39', NULL);
