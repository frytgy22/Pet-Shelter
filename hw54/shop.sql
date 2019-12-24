-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 24, 2019 at 01:20 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop`
--
CREATE DATABASE IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shop`;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `customersID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `genderID` int(11) NOT NULL,
  PRIMARY KEY (`customersID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `login` (`login`),
  KEY `customer_gender_fk` (`genderID`),
  KEY `customer_login_idx` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `genders`
--

CREATE TABLE IF NOT EXISTS `genders` (
  `genderID` int(11) NOT NULL AUTO_INCREMENT,
  `gender` set('male','female','unknown') NOT NULL DEFAULT 'unknown',
  PRIMARY KEY (`genderID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `genders`
--

INSERT INTO `genders` (`genderID`, `gender`) VALUES
(1, 'male'),
(2, 'female');

-- --------------------------------------------------------

--
-- Table structure for table `goods`
--

CREATE TABLE IF NOT EXISTS `goods` (
  `goodsID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `counter` int(11) NOT NULL,
  `cost` decimal(5,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`goodsID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `goods`
--

INSERT INTO `goods` (`goodsID`, `name`, `counter`, `cost`) VALUES
(1, 'Зеленый дракон', 10, '0.00'),
(2, 'Филадельфия макси', 10, '0.00'),
(3, 'Калифорния сякэ', 10, '0.00'),
(4, 'Красный дракон', 10, '0.00'),
(5, 'Феликс ролл с лососем', 10, '0.00'),
(6, 'Парадайз', 10, '0.00'),
(7, 'Филадельфия спайси-кунцей', 0, '0.00'),
(8, 'Токио', 10, '0.00'),
(9, 'Унаги-кунцей филадельфия', 10, '0.00'),
(10, 'Лосось тай', 10, '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `ordersID` int(11) NOT NULL AUTO_INCREMENT,
  `sellerID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`ordersID`),
  KEY `order_seller_fk` (`sellerID`),
  KEY `customer_order_fk` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ordersGoods`
--

CREATE TABLE IF NOT EXISTS `ordersGoods` (
  `ordersGoodsID` int(11) NOT NULL AUTO_INCREMENT,
  `orderID` int(11) NOT NULL,
  `goodsID` int(11) NOT NULL,
  PRIMARY KEY (`ordersGoodsID`),
  KEY `ordersGoods_fk1` (`orderID`),
  KEY `ordersGoods_fk2` (`goodsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sellers`
--

CREATE TABLE IF NOT EXISTS `sellers` (
  `sellersID` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(20) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `genderID` int(11) NOT NULL,
  PRIMARY KEY (`sellersID`),
  KEY `seller_gender_fk` (`genderID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sellers`
--

INSERT INTO `sellers` (`sellersID`, `lastName`, `firstName`, `genderID`) VALUES
(1, 'Maria', 'Ivanova', 2),
(2, 'Ivan', 'Petrov', 1),
(3, 'Petr', 'Iglikovskiy', 1),
(4, 'Darya', 'Popova', 2),
(5, 'Dmitriy', 'Popov', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customer_gender_fk` FOREIGN KEY (`genderID`) REFERENCES `genders` (`genderID`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `customer_order_fk` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customersID`),
  ADD CONSTRAINT `order_seller_fk` FOREIGN KEY (`sellerID`) REFERENCES `sellers` (`sellersID`);

--
-- Constraints for table `ordersGoods`
--
ALTER TABLE `ordersGoods`
  ADD CONSTRAINT `ordersGoods_fk1` FOREIGN KEY (`orderID`) REFERENCES `orders` (`ordersID`),
  ADD CONSTRAINT `ordersGoods_fk2` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`goodsID`);

--
-- Constraints for table `sellers`
--
ALTER TABLE `sellers`
  ADD CONSTRAINT `seller_gender_fk` FOREIGN KEY (`genderID`) REFERENCES `genders` (`genderID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
