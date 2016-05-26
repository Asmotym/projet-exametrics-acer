-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Jeu 26 Mai 2016 à 17:32
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
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `area`
--

INSERT INTO `area` (`idArea`, `nameArea`, `colorArea`) VALUES
(129, 'intérieur', '0x7fab0000'),
(130, 'extérieur', '0x7f00a100'),
(131, 'tressere', '0x7f1c1326');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`idArea`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `area`
--
ALTER TABLE `area`
  MODIFY `idArea` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=132;