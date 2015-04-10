-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 30, 2015 at 12:58 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `caf_assignment1`
--

-- --------------------------------------------------------

--
-- Table structure for table `chObject`
--

CREATE TABLE IF NOT EXISTS `chObject` (
  `id` varchar(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `date` varchar(11) DEFAULT NULL,
  `medium` varchar(100) DEFAULT NULL,
  `creditline` varchar(100) DEFAULT NULL,
  `description` text,
  `gallery_text` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `image_id` varchar(11) NOT NULL,
  `image_res` varchar(11) NOT NULL,
  `chObject_id` varchar(11) NOT NULL,
  `is_primary` varchar(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `participant`
--

CREATE TABLE IF NOT EXISTS `participant` (
  `person_id` varchar(100) NOT NULL,
  `person_name` varchar(100) DEFAULT NULL,
  `person_date` varchar(100) DEFAULT NULL,
  `person_url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `participation`
--

CREATE TABLE IF NOT EXISTS `participation` (
  `chObject_id` int(11) NOT NULL,
  `participant_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(100) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `role_display_name` varchar(100) DEFAULT NULL,
  `role_url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Indexes for table `chObject`
--
ALTER TABLE `chObject`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
 ADD PRIMARY KEY (`image_id`,`image_res`);

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
 ADD PRIMARY KEY (`person_id`);

--
-- Indexes for table `participation`
--
ALTER TABLE `participation`
 ADD PRIMARY KEY (`chObject_id`,`participant_id`,`role_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
 ADD PRIMARY KEY (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
