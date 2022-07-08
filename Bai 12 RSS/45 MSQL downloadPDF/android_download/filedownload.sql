-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Mar 10, 2013 at 08:39 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `android_download`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `filedownload`
-- 

DROP TABLE IF EXISTS `filedownload`;
CREATE TABLE IF NOT EXISTS `filedownload` (
  `id` int(11) NOT NULL auto_increment,
  `ten` varchar(100) default NULL,
  `duongdan` varchar(200) default NULL,
  `mota` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

-- 
-- Dumping data for table `filedownload`
-- 

INSERT INTO `filedownload` VALUES (1, 'file pdf 1', 'http://10.0.2.2:8080/android_download/file pdf ne.pdf', 'file pdf 1');
INSERT INTO `filedownload` VALUES (2, 'file 2 co chua hinh', 'http://10.0.2.2:8080/android_download/so2.pdf', 'file nay co chua hinh');
INSERT INTO `filedownload` VALUES (4, 'ti', 'http://10.0.2.2:8080/android_download/file pdf ne.pdf', 'ti ne');
INSERT INTO `filedownload` VALUES (5, 'file txt', 'http://10.0.2.2:8080/android_download/abc.txt', 'file txt ne');
INSERT INTO `filedownload` VALUES (6, 'hinh anh', 'http://10.0.2.2:8080/android_download/a.jpg', 'hinh anh ne');
