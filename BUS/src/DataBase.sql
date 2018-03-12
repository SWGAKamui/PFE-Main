-- phpMyAdmin SQL Dump
-- version 3.1.2deb1ubuntu0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2018 at 07:52 PM
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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Authentification`
--


-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE IF NOT EXISTS `data` (
  `id` int(25) NOT NULL auto_increment,
  `altitude` varchar(30) NOT NULL,
  `attitude` varchar(50) NOT NULL,
  `coordonnee` varchar(30) NOT NULL,
  `alert` varchar(30) NOT NULL,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `data`
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
