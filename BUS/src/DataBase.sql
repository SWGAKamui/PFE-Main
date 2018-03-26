-- phpMyAdmin SQL Dump
-- version 3.1.2deb1ubuntu0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 26, 2018 at 08:58 PM
-- Server version: 5.0.75
-- PHP Version: 5.2.6-3ubuntu4.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `azamouche`
--

-- --------------------------------------------------------

--
-- Table structure for table `Authentification`
--

CREATE TABLE IF NOT EXISTS `Authentification` (
  `id_client` int(11) NOT NULL auto_increment,
  `login` varchar(30) NOT NULL,
  `psw` varchar(100) NOT NULL,
  PRIMARY KEY  (`id_client`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `Authentification`
--

INSERT INTO `Authentification` (`id_client`, `login`, `psw`) VALUES
(4, 'AP', '0fd3f8dd5edc33b28db1162e15e8fcbc');

-- --------------------------------------------------------

-
-- --------------------------------------------------------

--
-- Table structure for table `Data`
--

CREATE TABLE IF NOT EXISTS `Data` (
  `id` int(255) NOT NULL auto_increment,
  `type` varchar(30) NOT NULL,
  `value` varchar(100) NOT NULL,
  `time` bigint(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1415 ;

--
-- Dumping data for table `Data`
--



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
