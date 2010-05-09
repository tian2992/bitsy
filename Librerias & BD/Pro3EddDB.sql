-- phpMyAdmin SQL Dump
-- version 3.1.2deb1ubuntu0.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 05-05-2010 a las 11:15:12
-- Versión del servidor: 5.0.75
-- Versión de PHP: 5.2.6-3ubuntu4.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `Pro3Edd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(30) NOT NULL auto_increment,
  `correo` varchar(60) NOT NULL,
  `pass` varchar(30) NOT NULL,
  `nombres` varchar(40) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `fechanacimiento` date NOT NULL,
  PRIMARY KEY  (`idUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `correo`, `pass`, `nombres`, `apellidos`, `fechanacimiento`) VALUES
(1, 'witchy', 'pass', 'nombre', 'apellidos', '2010-05-04'),
(2, 'uno', 'uno', 'uno', 'uno', '2010-03-03'),
(3, 'rosewitchy@gmail.com', 'pass', 'witchy', 'hxryn', '2010-09-03');
