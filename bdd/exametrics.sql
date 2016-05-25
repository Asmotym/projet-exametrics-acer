-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Mer 25 Mai 2016 à 18:29
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
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `area`
--

INSERT INTO `area` (`idArea`, `nameArea`, `colorArea`) VALUES
(109, 'intérieur ', '0x7fdb0000'),
(110, 'extérieur ', '0x7f00d40a'),
(111, 'cafet', '0x7f0000d0');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `note`
--

INSERT INTO `note` (`idNote`, `authorNote`, `textNote`, `dateNote`, `idArea`) VALUES
(25, 'Billy', 'Oui', '0000-00-00 00:00:00', 109),
(26, 'azerty', 'il fait beau', '0000-00-00 00:00:00', 110),
(27, 'Sarah', 'ouais j''avoue ', '0000-00-00 00:00:00', 110),
(28, 'Terminator', 'Sarah Conor ?', '0000-00-00 00:00:00', 111);

-- --------------------------------------------------------

--
-- Structure de la table `point`
--

CREATE TABLE `point` (
  `idPoint` int(11) NOT NULL,
  `longitude` decimal(12,8) NOT NULL,
  `latitude` decimal(12,8) NOT NULL,
  `idArea` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `point`
--

INSERT INTO `point` (`idPoint`, `longitude`, `latitude`, `idArea`) VALUES
(233, '2.84785923', '42.67445792', 109),
(234, '2.84797858', '42.67426244', 109),
(235, '2.84783810', '42.67420032', 109),
(236, '2.84771539', '42.67442045', 109),
(237, '2.84860455', '42.67467681', 110),
(238, '2.84849558', '42.67432678', 110),
(239, '2.84805704', '42.67432357', 110),
(240, '2.84794573', '42.67445866', 110),
(241, '2.84845468', '42.67526742', 111),
(242, '2.84752093', '42.67505765', 111),
(243, '2.84793198', '42.67454764', 111),
(244, '2.84873698', '42.67479759', 111);

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
  MODIFY `idArea` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=112;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `idNote` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT pour la table `point`
--
ALTER TABLE `point`
  MODIFY `idPoint` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=245;
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
