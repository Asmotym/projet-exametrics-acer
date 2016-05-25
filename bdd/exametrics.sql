-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Mer 25 Mai 2016 à 19:49
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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `point`
--

CREATE TABLE `point` (
  `idPoint` int(11) NOT NULL,
  `longitude` decimal(12,8) NOT NULL,
  `latitude` decimal(12,8) NOT NULL,
  `idArea` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=latin1;

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
  MODIFY `idArea` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=113;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `idNote` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT pour la table `point`
--
ALTER TABLE `point`
  MODIFY `idPoint` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=249;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk_note_id_area` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`) ON DELETE CASCADE;

--
-- Contraintes pour la table `point`
--
ALTER TABLE `point`
  ADD CONSTRAINT `fk_point_id_area` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`) ON DELETE CASCADE;