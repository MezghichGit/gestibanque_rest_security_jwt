-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 06 fév. 2024 à 11:26
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `amsdbrest2024`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `provider_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `label`, `picture`, `price`, `provider_id`) VALUES
(1, 'Samsung S20', 'samsungs20.png', 3000, 1),
(2, 'HP Pavillon', 'hp_pavillon.png', 2500, 102),
(52, 'Samsung A32S', 'a32s.png', 5000, 1),
(53, 'PC Portable HP Pavillon', 'PC-Portable-HP-Pavillon.png', 3200, 102);

-- --------------------------------------------------------

--
-- Structure de la table `article_seq`
--

CREATE TABLE `article_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `article_seq`
--

INSERT INTO `article_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Structure de la table `provider`
--

CREATE TABLE `provider` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `provider`
--

INSERT INTO `provider` (`id`, `address`, `email`, `name`, `logo`) VALUES
(1, 'Koré sud', 'samsung@hotmail.com', 'Samsung Galaxy', 'V1FE8B124D6QBJHS6ASamsung_Logo.svg.png'),
(102, 'USA Amercia', 'hp.pavillon@hotmail.com', 'HP Pavillon', '2ZGCTSQEXKEYICN6PJHP_New_Logo_2D.svg.png'),
(153, 'Tunisia', 'aziza@gmail.com', 'Aziza', 'E6HH8E9C970SWGTB1Zunnamed.png'),
(154, 'Japon', 'toshiba@gmail.com', 'Toshiba', 'MFTTYA42NVZUTSMA7XToshiba-Logo.png'),
(202, 'Tunisie TN', 'amine.mezghich@gmail.com', 'SIP Academy', 'OI63X8FT57UXE37CEJtéléchargement.jpeg'),
(252, 'Paris France', 'orange.paris@hotmail.fr', 'Orange', 'F50TMEU5CIIDCKYC5ZOrange_logo.svg.png');

-- --------------------------------------------------------

--
-- Structure de la table `provider_seq`
--

CREATE TABLE `provider_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `provider_seq`
--

INSERT INTO `provider_seq` (`next_val`) VALUES
(501);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` enum('SUPER_ADMIN','ADMIN','USER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN'),
(6, 'SUPER_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`) VALUES
(1, 'amine.mezghich@gmail.com', '$2a$10$0pQ/tMkITl.Mk8YzQnvMf.ec1IsIHe8fycaqJGPQncTfRmrUYZ122', 'amine'),
(2, 'amine.mezghich@ensi-uma.tn', '$2a$10$WTFqkdde8qJdWdjKDzkOHul7Ks.bdeyuIepZRt1H4yeFW4S74LDWS', 'mezghich'),
(8, 'hakim@gmail.com', '$2a$10$RVlg2uSo2B3i.npmtBmkIO2Nr.2BG8dg1tKhk114Q/qiAA3SXzBxq', 'hakim');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 2),
(1, 6),
(2, 1),
(8, 2),
(8, 6);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmnpkd1byk5tf93jmt5vay3c2y` (`provider_id`);

--
-- Index pour la table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Index pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `FKmnpkd1byk5tf93jmt5vay3c2y` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
