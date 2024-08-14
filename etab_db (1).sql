-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 14 août 2024 à 00:21
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `etab_db`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `AjouterEleve`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AjouterEleve` (IN `p_classe` VARCHAR(50), IN `p_matricule` VARCHAR(50), IN `p_id_personne` INT)  BEGIN
    INSERT INTO Eleve (classe, matricule, id_personne)
    VALUES (p_classe, p_matricule, p_id_personne);
END$$

DROP PROCEDURE IF EXISTS `AjouterProfesseur`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AjouterProfesseur` (IN `p_vacant` BOOLEAN, IN `p_matieresEnseigne` VARCHAR(255), IN `p_prochainCours` VARCHAR(255), IN `p_sujetProchaineReunion` VARCHAR(255), IN `p_id_personne` INT)  BEGIN
    INSERT INTO Professeur (vacant, matieresEnseigne, prochainCours, sujetProchaineReunion, id_personne)
    VALUES (p_vacant, p_matieresEnseigne, p_prochainCours, p_sujetProchaineReunion, p_id_personne);
END$$

DROP PROCEDURE IF EXISTS `ModifierEleve`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModifierEleve` (IN `p_id` INT, IN `p_classe` VARCHAR(50), IN `p_matricule` VARCHAR(50))  BEGIN
    UPDATE Eleve
    SET classe = p_classe,
        matricule = p_matricule
    WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `ModifierProfesseur`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModifierProfesseur` (IN `p_id` INT, IN `p_vacant` BOOLEAN, IN `p_matieresEnseigne` VARCHAR(255), IN `p_prochainCours` VARCHAR(255), IN `p_sujetProchaineReunion` VARCHAR(255))  BEGIN
    UPDATE Professeur
    SET vacant = p_vacant,
        matieresEnseigne = p_matieresEnseigne,
        prochainCours = p_prochainCours,
        sujetProchaineReunion = p_sujetProchaineReunion
    WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `ObtenirEleve`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenirEleve` (IN `p_id` INT)  BEGIN
    SELECT * FROM Eleve WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `ObtenirProfesseur`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenirProfesseur` (IN `p_id` INT)  BEGIN
    SELECT * FROM Professeur WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `SupprimerEleve`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SupprimerEleve` (IN `p_id` INT)  BEGIN
    DELETE FROM Eleve WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `SupprimerProfesseur`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SupprimerProfesseur` (IN `p_id` INT)  BEGIN
    DELETE FROM Professeur WHERE id = p_id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

DROP TABLE IF EXISTS `eleve`;
CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classe` varchar(50) NOT NULL,
  `matricule` varchar(50) NOT NULL,
  `id_personne` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricule` (`matricule`),
  KEY `id_personne` (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateNaissance` date NOT NULL,
  `ville` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

DROP TABLE IF EXISTS `professeur`;
CREATE TABLE IF NOT EXISTS `professeur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vacant` tinyint(1) NOT NULL,
  `matieresEnseigne` varchar(255) NOT NULL,
  `prochainCours` varchar(255) DEFAULT NULL,
  `sujetProchaineReunion` varchar(255) DEFAULT NULL,
  `id_personne` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_personne` (`id_personne`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) NOT NULL,
  `motDePasse` varchar(255) NOT NULL,
  `dateCreation` date NOT NULL,
  `id_personne` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pseudo` (`pseudo`),
  KEY `id_personne` (`id_personne`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `pseudo`, `motDePasse`, `dateCreation`, `id_personne`) VALUES
(3, 'admin', 'admin', '2024-08-14', 1),
(4, 'soum', 'soum', '2024-08-14', 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
