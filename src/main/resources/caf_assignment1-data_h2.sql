-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 30, 2015 at 12:59 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `caf_assignment1`
--

--
-- Dumping data for table `chObject`
--

INSERT INTO chObject (id, title, date, medium, creditline, description, gallery_text) VALUES
('68268301', 'Textile (Netherlands), 2011', '2011', 'cotton', 'Gift of Vlisco', 'Women''s high-heeled shoes. Printed in indigo, emerald green, lime green, and yellow on a white ground.', NULL),
('68268305', 'Textile (Netherlands), 1955', '1955', 'Medium: 100% cotton\nTechnique: wax-resist printed on plain weave', 'Gift of Vlisco', 'Pairs of slippers on a lattice background or one speckled with small hooked lines. Printed in indigo, yellow and orange on a white ground.', NULL),
('68268307', 'Textile, Telephone, 2004', '2004', 'Medium: 100% cotton\nTechnique: wax-resist printed on plain weave', 'Gift of Vlisco', 'Lightbulbs and electrical outlets on a speckled ground. Printed in indigo and orange on a dyed yellow ground.', NULL);

--
-- Dumping data for table `image`
--

INSERT INTO image (image_id, image_res, chObject_id, is_primary, height, width, url) VALUES
('22726', 'b', '68268395', '0', 761, 1024, 'https://images.collection.cooperhewitt.org/22726_a00aec163624a84b_b.jpg'),
('22726', 'd', '68268395', '0', 238, 320, 'https://images.collection.cooperhewitt.org/22726_a00aec163624a84b_d.gif'),
('22726', 'n', '68268395', '0', 238, 320, 'https://images.collection.cooperhewitt.org/22726_a00aec163624a84b_n.jpg'),
('22726', 'sq', '68268395', '0', 300, 300, 'https://images.collection.cooperhewitt.org/22726_a00aec163624a84b_sq.jpg'),
('22726', 'z', '68268395', '0', 476, 640, 'https://images.collection.cooperhewitt.org/22726_a00aec163624a84b_z.jpg');

--
-- Dumping data for table `participant`
--

INSERT INTO participant (person_id, person_name, person_date, person_url) VALUES
('68263859', 'Vlisco', '', 'http://collection.cooperhewitt.org/people/68263859/'),
('68263897', 'Nico Verbart', 'Dutch, b. 1953', 'http://collection.cooperhewitt.org/people/68263897/'),
('68263909', 'Marjo Penninx', '', 'http://collection.cooperhewitt.org/people/68263909/'),
('68263913', 'Jac Poppeliers', '', 'http://collection.cooperhewitt.org/people/68263913/');


--
-- Dumping data for table `participation`
--

INSERT INTO participation (chObject_id, participant_id, role_id) VALUES
(68268301, 68263859, 35236657),
(68268301, 68263909, 35236655),
(68268305, 68263859, 35236657),
(68268305, 68263913, 35236655),
(68268307, 68263859, 35236657),
(68268307, 68263897, 35236655);

--
-- Dumping data for table `role`
--

INSERT INTO role (role_id, role_name, role_display_name, role_url) VALUES
('35236655', 'Designer', 'Designed by', 'http://collection.cooperhewitt.org/roles/35236655/'),
('35236657', 'Manufacturer', 'Manufactured by', 'http://collection.cooperhewitt.org/roles/35236657/');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
