-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 12, 2019 at 12:04 PM
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
-- Database: `MovieBase`
--
CREATE DATABASE IF NOT EXISTS `MovieBase` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `MovieBase`;

-- --------------------------------------------------------

--
-- Table structure for table `Actors`
--

CREATE TABLE IF NOT EXISTS `Actors` (
  `ActorId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Nationality` varchar(20) NOT NULL,
  `Birth` date NOT NULL,
  PRIMARY KEY (`ActorId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Actors`
--

INSERT INTO `Actors` (`ActorId`, `FirstName`, `LastName`, `Nationality`, `Birth`) VALUES
(1, 'Оуэн', 'Уилсон', 'EU', '1988-01-01'),
(2, 'Винс', 'Вон', 'EU', '1989-02-02'),
(3, 'Маколей', 'Калкин', 'EU', '2000-03-03'),
(4, 'Томми', 'Ли Джонс', 'EU', '1980-04-04'),
(5, 'Уилл', 'Смит', 'EU', '1985-05-05'),
(6, 'Брэд', 'Питт', 'EU', '1985-12-25'),
(7, 'Эрик', 'Бана', 'EU', '1985-10-25'),
(8, 'Дэниел', 'Рэдклифф', 'EU', '1995-07-09');

-- --------------------------------------------------------

--
-- Table structure for table `Directors`
--

CREATE TABLE IF NOT EXISTS `Directors` (
  `DirectorId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Nationality` varchar(20) NOT NULL,
  `Birth` date NOT NULL,
  PRIMARY KEY (`DirectorId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Directors`
--

INSERT INTO `Directors` (`DirectorId`, `FirstName`, `LastName`, `Nationality`, `Birth`) VALUES
(1, 'Барри', 'Зонненфельд', 'EU', '1985-05-05'),
(2, 'Криc', 'Коламбус', 'EU', '1986-06-06'),
(3, 'Шон', 'Леви', 'EU', '1986-04-06'),
(4, 'Вольфганг', 'Петерсен', 'EU', '1977-12-10'),
(5, 'Дэвид', 'Йэтс', 'EU', '1990-10-25');

-- --------------------------------------------------------

--
-- Table structure for table `Genres`
--

CREATE TABLE IF NOT EXISTS `Genres` (
  `GenreId` int(11) NOT NULL AUTO_INCREMENT,
  `GenreName` varchar(20) NOT NULL,
  PRIMARY KEY (`GenreId`),
  UNIQUE KEY `GenreName` (`GenreName`),
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Genres`
--

INSERT INTO `Genres` (`GenreId`, `GenreName`) VALUES
(3, 'Action'),
(1, 'Comedy'),
(2, 'Family'),
(5, 'History'),
(4, 'Mystic');

-- --------------------------------------------------------

--
-- Table structure for table `MovieActor`
--

CREATE TABLE IF NOT EXISTS `MovieActor` (
  `MovieId` int(11) NOT NULL,
  `ActorId` int(11) NOT NULL,
  KEY `MovieId` (`MovieId`),
  KEY `ActorId` (`ActorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `MovieActor`
--

INSERT INTO `MovieActor` (`MovieId`, `ActorId`) VALUES
(1, 4),
(1, 5),
(2, 3),
(3, 1),
(3, 2),
(4, 6),
(4, 7),
(5, 8),
(6, 8),
(7, 8),
(8, 8),
(9, 8),
(10, 8);

-- --------------------------------------------------------

--
-- Table structure for table `MovieGenres`
--

CREATE TABLE IF NOT EXISTS `MovieGenres` (
  `MovieId` int(11) NOT NULL,
  `GenreId` int(11) NOT NULL,
  KEY `MovieId` (`MovieId`),
  KEY `GenreId` (`GenreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `MovieGenres`
--

INSERT INTO `MovieGenres` (`MovieId`, `GenreId`) VALUES
(1, 3),
(2, 1),
(3, 1),
(2, 2),
(4, 5),
(5, 4),
(6, 4),
(7, 4),
(8, 4),
(9, 4),
(10, 4),
(5, 2),
(6, 2),
(7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Movies`
--

CREATE TABLE IF NOT EXISTS `Movies` (
  `MovieId` int(11) NOT NULL AUTO_INCREMENT,
  `DirectorId` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `ReleaseYear` int(11) NOT NULL,
  `Rating` int(11) NOT NULL,
  `Plot` longtext NOT NULL,
  `MovieLength` int(11) NOT NULL,
  PRIMARY KEY (`MovieId`),
  KEY `fk_movies_directors` (`DirectorId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Movies`
--

INSERT INTO `Movies` (`MovieId`, `DirectorId`, `Title`, `ReleaseYear`, `Rating`, `Plot`, `MovieLength`) VALUES
(1, 1, 'Люди в черном', 1997, 7, 'На нашей планете тайно проживают инопланетяне. Для решения вопросов их защиты создано бюро сотрудничества с инопланетянами. У агентов-землян есть современная технология взаимодействия с пришельцами', 160),
(2, 2, 'Один дома', 1990, 9, 'Действия фильма разворачиваются на кануне Рождества. Большая американская семья собирается отправиться в путешествие из Чикаго в Европу. Хаотичные сборы приводят к тому, что родители забыли одного из детей дома. Юное создание по имени Кевин не растерялся и продемонстрировал свою изобретательность', 160),
(3, 3, 'Стажеры', 2013, 8, 'Главных героев, Билла и Ника, увольняют с любимой работы. Они работали продавцами, но ведь на улице цифровой век, теперь можно продавать и на кресле перед монитором компьютера. Но, главные герои не унывают и решают тоже попробовать себя в интернете. Так они совершенно случайно попадают в отборочную группу одной популярной интернет компании', 155),
(4, 4, 'Троя', 2004, 6, 'События фильма происходят в Древней Греции, где страсть, вспыхнувшая между наследным принцем Трои Парисом и королевой Спарты Еленой, стала началом войны, которая чуть не уничтожила цивилизацию. Парис крадет Елену у ее супруга.', 190),
(5, 5, 'Гарри Поттер и философский камень', 2001, 10, 'В ночь, когда Гарри исполняется одиннадцать, появляется Хагрид и рассказывает ему, что он волшебник и будет учиться в Хогвартсе.', 175),
(6, 5, 'Гарри Поттер и Тайная комната', 2002, 10, 'Начинается новый учебный год, все проходят через портал на платформу 9¾, чтобы сесть на поезд, но Гарри и Рону по непонятной причине не удаётся этого сделать. Опоздав таким образом на поезд, они берут летающий автомобиль отца Рона и догоняют «Хогвартс-экспресс» по дороге в школу. Машина приземляется на Гремучую иву (последняя при этом жестоко избивает автомобиль), оживает и, «выплюнув» пассажиров и их вещи, уезжает в лес.', 175),
(7, 5, 'Гарри Поттер и узник Азкабана', 2004, 10, 'Становится очень холодно, и кто-то идёт по коридору, а когда дверь в купе открывается, друзья видят страшное существо в чёрной мантии — дементора. Гарри слышится женский крик, а затем он теряет сознание от атаки дементора. Профессор Люпин просыпается и пользуясь особым боевым заклинанием «Экспекто Патронум» выгоняет из купе дементора и тот отступает.', 190),
(8, 5, 'Гарри Поттер и Кубок огня', 2005, 10, 'Начинается учебный год. Альбус Дамблдор объявляет о том, что Хогвартс становится местом проведения грандиозного события — Турнира Трёх Волшебников, традиционных состязаний магических школ. На этот турнир приглашены представители Франции — школа «Шармбатон» и представители далёкого Севера — школа «Дурмстранг». В соответствии с новыми правилами, принять участие могут ученики, достигшие 17 лет, а самих участников выбирает Кубок Огня по одному от каждой школы.', 190),
(9, 5, 'Гарри Поттер и Орден Феникса', 2007, 10, 'Гарри и Дадли бегут домой через подземный переход, где на них нападают дементоры Азкабана. Гарри удаётся вызвать Патронус, прогнать дементоров и тем самым спасти себя и Дадли. Дома к Гарри приходит письмо из Министерства магии о его исключении из школы чародейства и волшебства «Хогвартс» за использование волшебства.', 200),
(10, 5, 'Гарри Поттер и Принц-полукровка', 2009, 9, 'Драко Малфой становится Пожирателем Смерти вместо арестованного отца и получает от Тёмного Лорда задание: убить Дамблдора. Тем временем Северус Снегг приносит Непреложный обет Нарциссе Малфой и клянётся помочь её сыну, Драко, в его задании. Для этого Малфой пытается починить исчезательный шкаф в Выручай-комнате и при этом несколько раз совершает покушение на жизнь Дамблдора. Жертвой первого покушения оказывается Кэти Белл, а второго — Рон Уизли.', 200);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `MovieActor`
--
ALTER TABLE `MovieActor`
  ADD CONSTRAINT `MovieActor_ibfk_1` FOREIGN KEY (`MovieId`) REFERENCES `Movies` (`MovieId`),
  ADD CONSTRAINT `MovieActor_ibfk_2` FOREIGN KEY (`ActorId`) REFERENCES `Actors` (`ActorId`);

--
-- Constraints for table `MovieGenres`
--
ALTER TABLE `MovieGenres`
  ADD CONSTRAINT `MovieGenres_ibfk_1` FOREIGN KEY (`MovieId`) REFERENCES `Movies` (`MovieId`),
  ADD CONSTRAINT `MovieGenres_ibfk_2` FOREIGN KEY (`GenreId`) REFERENCES `Genres` (`GenreId`);

--
-- Constraints for table `Movies`
--
ALTER TABLE `Movies`
  ADD CONSTRAINT `fk_movies_directors` FOREIGN KEY (`DirectorId`) REFERENCES `Directors` (`DirectorId`);
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
