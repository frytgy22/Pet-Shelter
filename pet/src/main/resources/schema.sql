SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `pets`
--
DROP DATABASE IF EXISTS `pets`;
CREATE DATABASE IF NOT EXISTS `pets` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pets`;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
                              `id` int(11) NOT NULL,
                              `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(10, 'ADVICE'),
(6, 'CARE'),
(7, 'CATS'),
(8, 'DOGS'),
(9, 'FUN'),
(4, 'GAMES'),
(1, 'HEALTH'),
(11, 'INFO'),
(2, 'MEDICINE'),
(3, 'NUTRITION'),
(5, 'TRAINING');

-- --------------------------------------------------------

--
-- Table structure for table `cats`
--

DROP TABLE IF EXISTS `cats`;
CREATE TABLE `cats` (
                        `id` int(11) NOT NULL,
                        `birth_date` date NOT NULL,
                        `description` varchar(5000) NOT NULL,
                        `gender` varchar(255) NOT NULL,
                        `photo` varchar(255) DEFAULT NULL,
                        `breed_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cats`
--

INSERT INTO `cats` (`id`, `birth_date`, `description`, `gender`, `photo`, `breed_fk`) VALUES
(1, '2020-03-28', 'Young beautiful kitty is waiting for her family in the shelter. Hand, affectionate, languishing in a cage. Melts in his arms, purrs like a tractor. Will be the perfect girlfriend. Take her home!', 'GIRL', '11.jpg', 11),
(2, '2019-10-16', 'Young beautiful kitty is waiting for her family in the shelter. Hand, affectionate, languishing in a cage. Melts in his arms, purrs like a tractor. Will be the perfect friend. Take her home!', 'BOY', '2.jpg', 1),
(3, '2020-01-02', 'Young beautiful kitty is waiting for her family in the shelter. Hand, affectionate, languishing in a cage. Melts in his arms, purrs like a tractor. Will be the perfect girlfriend. Take her home!', 'GIRL', '31.jpeg', 2),
(4, '2019-12-30', 'Young beautiful kitty is waiting for her family in the shelter. Hand, affectionate, languishing in a cage. Melts in his arms, purrs like a tractor. Will be the perfect friend. Take her home!\r\n', 'BOY', '4.jpg', 10),
(5, '2019-09-06', 'Young beautiful kitty is waiting for her family in the shelter. Hand, affectionate, languishing in a cage. Melts in his arms, purrs like a tractor. Will be the perfect friend. Take her home!', 'BOY', '5.jpg', 5);

-- --------------------------------------------------------

--
-- Table structure for table `cat_breed`
--

DROP TABLE IF EXISTS `cat_breed`;
CREATE TABLE `cat_breed` (
                             `id` int(11) NOT NULL,
                             `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cat_breed`
--

INSERT INTO `cat_breed` (`id`, `name`) VALUES
(6, 'ABYSSINIAN'),
(12, 'AMERICAN SHORTHAIR'),
(5, 'BENGAL'),
(7, 'BIRMAN'),
(10, 'DEVON REX'),
(11, 'HIMALAYAN'),
(3, 'MAINE COON'),
(8, 'ORIENTAL SHORTHAIR'),
(2, 'PERSIAN'),
(4, 'RAGDOLL'),
(1, 'SIAMESE'),
(9, 'SPHYNX');

-- --------------------------------------------------------

--
-- Table structure for table `dogs`
--

DROP TABLE IF EXISTS `dogs`;
CREATE TABLE `dogs` (
                        `id` int(11) NOT NULL,
                        `birth_date` date NOT NULL,
                        `description` varchar(5000) NOT NULL,
                        `gender` varchar(255) NOT NULL,
                        `photo` varchar(255) DEFAULT NULL,
                        `breed_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dogs`
--

INSERT INTO `dogs` (`id`, `birth_date`, `description`, `gender`, `photo`, `breed_fk`) VALUES
(1, '2020-03-27', 'Beautiful purebred puppy, smart and playful. It will be a great friend for family and children. Easy to learn and obedient.', 'BOY', '1.jpg', 23),
(2, '2019-12-30', 'Beautiful purebred puppy, smart and playful. It will be a great friend for family and children. Easy to learn and obedient.', 'GIRL', '2.jpg', 36),
(3, '2019-02-01', 'Beautiful purebred puppy, smart and playful. It will be a great friend for family and children. Easy to learn and obedient.', 'GIRL', '4.jpg', 11),
(4, '2019-05-05', 'Beautiful purebred puppy, smart and playful. It will be a great friend for family and children. Easy to learn and obedient.', 'GIRL', '1.jpeg', 33),
(5, '2019-12-31', 'Beautiful purebred puppy, smart and playful. It will be a great friend for family and children. Easy to learn and obedient.', 'BOY', '1.jpg', 21);

-- --------------------------------------------------------

--
-- Table structure for table `dog_breed`
--

DROP TABLE IF EXISTS `dog_breed`;
CREATE TABLE `dog_breed` (
                             `id` int(11) NOT NULL,
                             `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dog_breed`
--

INSERT INTO `dog_breed` (`id`, `name`) VALUES
(1, 'AFADOR'),
(2, 'AFFENHUAHUA'),
(3, 'AFFENPINSCHER'),
(4, 'AFGHAN HOUND'),
(5, 'AIREDALE TERRIER'),
(19, 'AUSTRALIAN SHEPHERDS'),
(10, 'BEAGLES'),
(25, 'BOSTON TERRIERS'),
(15, 'BOXERS'),
(29, 'BRITTANYS	'),
(9, 'BULLDOGS'),
(22, 'CAVALIER KING CHARLES SPANIELS'),
(33, 'COCKER SPANIELS'),
(36, 'COLLIES'),
(16, 'DACHSHUNDS'),
(37, 'DALMATIANS'),
(21, 'DOBERMAN PINSCHERS'),
(30, 'ENGLISH SPRINGER SPANIELS'),
(8, 'FRENCH BULLDOGS'),
(13, 'GERMAN SHORTHAIRED POINTERS'),
(7, 'GOLDEN RETRIEVERS'),
(20, 'GREAT DANES'),
(27, 'HAVANESE'),
(6, 'LABRADOR RETRIEVERS'),
(32, 'MASTIFFS'),
(35, 'MINIATURE AMERICAN SHEPHERDS	'),
(23, 'MINIATURE SCHNAUZERS '),
(38, 'OLD ENGLISH SHEEPDOGS'),
(17, 'PEMBROKE WELSH CORGIS'),
(26, 'POMERANIANS'),
(11, 'POODLES'),
(31, 'PUGS'),
(12, 'ROTTWEILERS'),
(28, 'SHETLAND SHEEPDOGS'),
(24, 'SHIH TZU'),
(18, 'SIBERIAN HUSKIES'),
(34, 'VIZSLAS'),
(14, 'YORKSHIRE TERRIERS');

-- --------------------------------------------------------

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
                                     `username` varchar(50) NOT NULL,
                                     `series` varchar(64) NOT NULL,
                                     `token` varchar(64) NOT NULL,
                                     `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
                         `id` int(11) NOT NULL,
                         `contents` text NOT NULL,
                         `created` date NOT NULL,
                         `file` varchar(255) DEFAULT NULL,
                         `file_type` varchar(255) DEFAULT NULL,
                         `subtitle` varchar(1000) NOT NULL,
                         `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `contents`, `created`, `file`, `file_type`, `subtitle`, `title`) VALUES
(1, 'There are two different methods for showing your puppy what “sit” means. The first method is called capturing. Stand in front of your puppy holding some of his dog food or treats. \r\n\r\nWait for him to sit – say “yes” and give him a treat. Then step backwards or sideways to encourage him to stand and wait for him to sit. Give another treat as soon as they sit. After a few repetitions, you can begin saying “sit” right as he begins to sit. The next option is called luring. Get down in front of your puppy, holding a treat as a lure. \r\n\r\nPut the treat right in front of the pup’s nose, then slowly lift the food above his head. He will probably sit as he lifts his head to nibble at the treat. Allow him to eat the treat when his bottom touches the ground. Repeat one or two times with the food lure, then remove the food and use just your empty hand, but continue to reward the puppy after he sits. Once he understands the hand signal to sit, you can begin saying “sit” right before you give the hand signal. Never physically put your puppy into the sitting position; this can be confusing or upsetting to some dogs.', '2020-05-05', '1.jpg', 'image', 'How To Teach a Dog To Sit', 'Teach Your Puppy'),
(2, 'Know the 15 Human food that are good for dogs.', '2020-05-05', '15 human foods good for Dogs.mp4', 'video', 'Usually we hear table or human foods are dangerous for dogs but some foods are good and sometimes required as a change. ', '15 human foods good for Dogs'),
(3, 'In choosing a cat, you must first decide whether you want to bring home a kitten, a juvenile, or an adult. Generally, kittens are curious, playful, and energetic. You get to watch them grow and mature, and can influence the development of their personality. A kitten may also be more readily accepted by pets that you already have. An adult cat’s personality is already established, so you’ll have a better idea of how the cat will fit in your particular home situation. Healthy adult cats usually require less intensive care and supervision than kittens or juveniles do.\r\n\r\nA second thing to consider in choosing a cat is whether you want a pedigreed or a mixed-breed animal, either of which can be excellent companions. Mixed-breed cats are generally categorized as either domestic shorthairs or domestic longhairs. The greatest advantage of getting a pedigreed cat is that its size, appearance, and to some extent, personality, are likely to fit the profile of its particular breed. With a mixed-breed cat, you will be unable to predict its adult size and appearance as accurately.', '2020-05-05', '2.jpg', 'image', 'Before choosing a new cat, do your research and think about your options. Keep in mind the personality, age, and appearance, you’re looking for as well as the kinds of pets you already have at home. If you’ve never owned a cat before, it’s also important to know what taking care of your new cat will involve in advance.', 'Choosing and Caring for Your New Cat');

-- --------------------------------------------------------

--
-- Table structure for table `post_categories`
--

DROP TABLE IF EXISTS `post_categories`;
CREATE TABLE `post_categories` (
                                   `posts_id` int(11) NOT NULL,
                                   `categories_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `post_categories`
--

INSERT INTO `post_categories` (`posts_id`, `categories_id`) VALUES
(1, 5),
(1, 8),
(1, 10),
(2, 3),
(2, 8),
(3, 7),
(3, 10);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(30) NOT NULL,
                         `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`) VALUES
(1, 'admin@1.ua', 'admin', '$2a$10$GVdyIMr61bhkul6eW1ZzPu2zRhFnpYqpp.oq5OFB4JH.sW.49X/Zi'),
(2, 'user1@ru.ru', 'name1', '$2a$10$eC7ROh.x8WQwD5mLHhnBAuv.OhKW75maSEkJEnhFoKWpCoNNiUr52'),
(3, 'user2@ru.ru', 'user2', '$2a$10$QFF6qAA8EL1L3Ew0BasNUeERCh672OwrIPiqd6gGJtrM6dfGj1XNW'),
(4, 'user3@ru.ru', 'user3', '$2a$10$5Fwhoi04RWZ7vO450sg2wuxDBFJm9oFFh7VPU2x/tky.CcR308Lpq'),
(5, 'user5@ru.ru', 'user5', '$2a$10$lKz3noKYW70fnGc3vMJgdexAJZZyv2NTb8hIXZspL5B7V2ohgrty2'),
(6, 'user6@ru.ru', 'user6', '$2a$10$39QhyApFTn15oNJZ4OYhZONRY/hqCHl/BjyViC.vBijjIo5bvVJW2'),
(7, 'user7@ru.ru', 'user7', '$2a$10$vvtnirmOA/Ij.xypMHAe.umzhOEw9WeInv0nzLWzgCQpgQjuzY0Pa'),
(8, 'user8@ru.ru', 'user8', '$2a$10$wnn9Zd155gDuklq3dz4PMelo1/QP86OXdBylGvCDaHpqbCPef7TEm'),
(9, 'user9@ru.ru', 'user9', '$2a$10$rPWn/6ZsGA05Xq6sUbSzIOFkN79RtLblY3M1N2NWMSz0TymJZXzye');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `user_id` int(11) NOT NULL,
                             `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_USER'),
(4, 'ROLE_ADMIN'),
(4, 'ROLE_USER'),
(5, 'ROLE_ADMIN'),
(6, 'ROLE_USER'),
(7, 'ROLE_USER'),
(8, 'ROLE_USER'),
(9, 'ROLE_USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`);

--
-- Indexes for table `cats`
--
ALTER TABLE `cats`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKhws2gl8jbj5016xoajs5i4sle` (`breed_fk`);

--
-- Indexes for table `cat_breed`
--
ALTER TABLE `cat_breed`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_rrysf5sjbyxrv627dkisjdmm9` (`name`);

--
-- Indexes for table `dogs`
--
ALTER TABLE `dogs`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FK5y9f2nwe02xb9g8y7219p7fhv` (`breed_fk`);

--
-- Indexes for table `dog_breed`
--
ALTER TABLE `dog_breed`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_37tt731txruktlhs2j8rhss5a` (`name`);

--
-- Indexes for table `persistent_logins`
--
ALTER TABLE `persistent_logins`
    ADD PRIMARY KEY (`series`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post_categories`
--
ALTER TABLE `post_categories`
    ADD PRIMARY KEY (`posts_id`,`categories_id`),
    ADD KEY `FKb2cry08nwy0gejm9tsy28xjkg` (`categories_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
    ADD KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `cats`
--
ALTER TABLE `cats`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `cat_breed`
--
ALTER TABLE `cat_breed`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `dogs`
--
ALTER TABLE `dogs`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `dog_breed`
--
ALTER TABLE `dog_breed`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cats`
--
ALTER TABLE `cats`
    ADD CONSTRAINT `FKhws2gl8jbj5016xoajs5i4sle` FOREIGN KEY (`breed_fk`) REFERENCES `cat_breed` (`id`);

--
-- Constraints for table `dogs`
--
ALTER TABLE `dogs`
    ADD CONSTRAINT `FK5y9f2nwe02xb9g8y7219p7fhv` FOREIGN KEY (`breed_fk`) REFERENCES `dog_breed` (`id`);

--
-- Constraints for table `post_categories`
--
ALTER TABLE `post_categories`
    ADD CONSTRAINT `FKb2cry08nwy0gejm9tsy28xjkg` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`),
    ADD CONSTRAINT `FKtlf8x6wuv9a5bq7yqsmf4p8b1` FOREIGN KEY (`posts_id`) REFERENCES `posts` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
    ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;
