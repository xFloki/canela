-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-11-2016 a las 20:51:26
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `discografia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `album`
--

CREATE TABLE `album` (
  `id` int(255) NOT NULL,
  `titulo` varchar(255) COLLATE utf8_bin NOT NULL,
  `publicacion` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `album`
--

INSERT INTO `album` (`id`, `titulo`, `publicacion`) VALUES
(0, 'Mi amor es eterno', 1993);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE `cancion` (
  `titulo` varchar(255) COLLATE utf8_bin NOT NULL,
  `duracion` varchar(255) COLLATE utf8_bin NOT NULL,
  `letras` text COLLATE utf8_bin NOT NULL,
  `id` int(255) NOT NULL,
  `album` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `cancion`
--

INSERT INTO `cancion` (`titulo`, `duracion`, `letras`, `id`, `album`) VALUES
('Amarte', '3:33', 'They say Taylor was a good girl, never one to be late\r\nComplain, express ideas in her brain\r\nWorking on the night shift, passing out the tickets\r\nYou''re gonna have to pay her if you want to park here\r\n\r\nWell mommy''s little dancer is quite a little secret\r\nWorking on the streets now, never gonna keep it\r\nIt''s quite an imposition and now she''s only wishin''\r\nThat she would have listened to the words they said\r\nPoor Taylor\r\n\r\nShe just wanders around, unaffected by,\r\nThe winter winds here, she''ll pretend that\r\nShe''s somewhere else, so far and clear\r\nAbout two thousand miles, from here\r\n\r\nWell Peter Patrick pitter patters on the window,\r\nThe sunny silhouette won''t let him in\r\nAnd poor old Pete''s got nothing ''cause he''s been falling\r\nAnd somehow sunny knows just where he''s been\r\n\r\nHe thinks that singin'' on Sunday''s gonna save his soul\r\nNow that Saturday''s gone\r\nAnd sometimes he thinks that he''s on his way,\r\nBut I can see, that his brake lights are on\r\n\r\nHe just wanders around, unaffected by,\r\nThe winter winds here, and he''ll pretend that\r\nHe''s somewhere else, so far and clear,\r\nAbout two thousand miles from here\r\n\r\nSuch a tough enchilada, filled up with nada\r\nGivin'' what you gotta give to get a dollar bill\r\nUsed to be a limber chicken, times have been a ticking\r\nNow she''s finger lickin'' to the man\r\nWith the money in his pocket, flying in his rocket\r\nAnd only stopping by on his way to a better world\r\n\r\nIf Taylor finds a better world\r\nThen Taylor''s gonna run away', 1, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `album` (`album`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD CONSTRAINT `cancion_ibfk_1` FOREIGN KEY (`album`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
