-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Lun 23 Mai 2016 à 19:09
-- Version du serveur :  5.5.42
-- Version de PHP :  5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `exametrics`
--

-- --------------------------------------------------------

--
-- Structure de la table `area`
--

CREATE TABLE `area` (
  `idArea` int(11) NOT NULL,
  `nameArea` varchar(45) NOT NULL,
  `colorArea` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `area`
--

INSERT INTO `area` (`idArea`, `nameArea`, `colorArea`) VALUES
(1, 'The World', '0x7f121212'),
(2, 'Star Platinum', '0x7f600060'),
(51, 'Naughty', '0x7f953ff6'),
(52, 'yummy', '0x7f4e9c45');

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `idNote` int(11) NOT NULL,
  `authorNote` varchar(45) DEFAULT 'Anonyme',
  `textNote` text NOT NULL,
  `dateNote` datetime NOT NULL,
  `idArea` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `note`
--

INSERT INTO `note` (`idNote`, `authorNote`, `textNote`, `dateNote`, `idArea`) VALUES
(1, 'Clément', 'test', '2016-05-19 00:00:00', 1),
(2, 'Clement', 'test', '2016-05-16 00:00:00', 1),
(3, 'Anonyme', 'sfgdf', '2016-05-19 00:00:00', 2),
(4, 'test', 'test choucroute', '2016-05-20 11:00:00', 1),
(5, '', 'OUI', '2016-05-20 13:00:00', 2),
(6, '', 'NON', '2016-05-20 14:00:00', 2);

-- --------------------------------------------------------

--
-- Structure de la table `point`
--

CREATE TABLE `point` (
  `idPoint` int(11) NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  `idArea` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `point`
--

INSERT INTO `point` (`idPoint`, `longitude`, `latitude`, `idArea`) VALUES
(3, 41, 1, 1),
(4, 42, 1, 1),
(5, 42, 2, 1),
(6, 41, 2, 1),
(7, 44, 1, 2),
(8, 44, 2, 2),
(9, 45, 2, 2),
(10, 45, 1, 2),
(38, -5.52577, 42.1427, 51),
(39, -10.6926, 42.6641, 51),
(40, -10.7831, 45.1502, 51),
(41, 15.1004, 42.7516, 52),
(42, 9.50772, 41.2911, 52),
(43, 9.17995, 45.7004, 52),
(44, 14.2195, 45.5787, 52),
(45, 17.0773, 44.5226, 52),
(46, 15.7969, 44.2225, 52);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`idArea`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`idNote`),
  ADD KEY `idZone` (`idArea`);

--
-- Index pour la table `point`
--
ALTER TABLE `point`
  ADD PRIMARY KEY (`idPoint`),
  ADD KEY `idZone` (`idArea`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `area`
--
ALTER TABLE `area`
  MODIFY `idArea` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `idNote` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `point`
--
ALTER TABLE `point`
  MODIFY `idPoint` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`);

--
-- Contraintes pour la table `point`
--
ALTER TABLE `point`
  ADD CONSTRAINT `point_ibfk_1` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`) ON DELETE CASCADE ON UPDATE CASCADE;