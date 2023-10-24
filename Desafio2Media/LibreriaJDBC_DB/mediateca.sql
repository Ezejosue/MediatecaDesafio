-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 08-10-2023 a las 22:41:16
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mediateca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista`
--

CREATE TABLE `artista` (
  `IdArtista` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `IdAutor` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `directores`
--

CREATE TABLE `directores` (
  `IdDirector` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editoriales`
--

CREATE TABLE `editoriales` (
  `IdEditorial` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generos`
--

CREATE TABLE `generos` (
  `IdGenero` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiales`
--

CREATE TABLE `materiales` (
  `Id` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IdTipo` int(11) NOT NULL,
  `Titulo` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IdAutor` int(11) NOT NULL,
  `IdArtista` int(11) NOT NULL,
  `IdGenero` int(11) NOT NULL,
  `NumPag` int(11) NOT NULL,
  `IdEditorial` int(11) NOT NULL,
  `Duracion` time NOT NULL,
  `ISBN` varchar(17) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Periodicidad` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FechPublic` int(11) NOT NULL,
  `AnioPublic` int(11) NOT NULL,
  `NumCanciones` int(11) NOT NULL,
  `Stock` int(11) NOT NULL,
  `IdDirector` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `IdRol` int(11) NOT NULL,
  `Rol` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomateriales`
--

CREATE TABLE `tipomateriales` (
  `IdTipo` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usurarios`
--

CREATE TABLE `usurarios` (
  `IdUsuario` int(11) NOT NULL,
  `Nombre` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Correo` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Contrasena` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Estado` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IdRol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`IdArtista`);

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`IdAutor`);

--
-- Indices de la tabla `directores`
--
ALTER TABLE `directores`
  ADD PRIMARY KEY (`IdDirector`);

--
-- Indices de la tabla `editoriales`
--
ALTER TABLE `editoriales`
  ADD PRIMARY KEY (`IdEditorial`);

--
-- Indices de la tabla `generos`
--
ALTER TABLE `generos`
  ADD PRIMARY KEY (`IdGenero`);

--
-- Indices de la tabla `materiales`
--
ALTER TABLE `materiales`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdTipo` (`IdTipo`),
  ADD KEY `IdAutor` (`IdAutor`),
  ADD KEY `IdArtista` (`IdArtista`),
  ADD KEY `IdGenero` (`IdGenero`),
  ADD KEY `IdEditorial` (`IdEditorial`),
  ADD KEY `IdDirector` (`IdDirector`),
  ADD KEY `IdUsuario` (`IdUsuario`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`IdRol`);

--
-- Indices de la tabla `tipomateriales`
--
ALTER TABLE `tipomateriales`
  ADD PRIMARY KEY (`IdTipo`);

--
-- Indices de la tabla `usurarios`
--
ALTER TABLE `usurarios`
  ADD PRIMARY KEY (`IdUsuario`),
  ADD KEY `IdRol` (`IdRol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artista`
--
ALTER TABLE `artista`
  MODIFY `IdArtista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `IdAutor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `directores`
--
ALTER TABLE `directores`
  MODIFY `IdDirector` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `editoriales`
--
ALTER TABLE `editoriales`
  MODIFY `IdEditorial` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `generos`
--
ALTER TABLE `generos`
  MODIFY `IdGenero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `IdRol` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipomateriales`
--
ALTER TABLE `tipomateriales`
  MODIFY `IdTipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usurarios`
--
ALTER TABLE `usurarios`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `materiales`
--
ALTER TABLE `materiales`
  ADD CONSTRAINT `materiales_ibfk_1` FOREIGN KEY (`IdArtista`) REFERENCES `artista` (`IdArtista`) ON DELETE CASCADE,
  ADD CONSTRAINT `materiales_ibfk_2` FOREIGN KEY (`IdAutor`) REFERENCES `autores` (`IdAutor`) ON DELETE CASCADE,
  ADD CONSTRAINT `materiales_ibfk_3` FOREIGN KEY (`IdDirector`) REFERENCES `directores` (`IdDirector`) ON DELETE CASCADE,
  ADD CONSTRAINT `materiales_ibfk_4` FOREIGN KEY (`IdTipo`) REFERENCES `tipomateriales` (`IdTipo`) ON DELETE CASCADE,
  ADD CONSTRAINT `materiales_ibfk_5` FOREIGN KEY (`IdEditorial`) REFERENCES `editoriales` (`IdEditorial`) ON DELETE CASCADE,
  ADD CONSTRAINT `materiales_ibfk_6` FOREIGN KEY (`IdGenero`) REFERENCES `generos` (`IdGenero`) ON DELETE CASCADE,
  ADD CONSTRAINT `materiales_ibfk_7` FOREIGN KEY (`IdUsuario`) REFERENCES `usurarios` (`IdUsuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usurarios`
--
ALTER TABLE `usurarios`
  ADD CONSTRAINT `usurarios_ibfk_1` FOREIGN KEY (`IdRol`) REFERENCES `roles` (`IdRol`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
