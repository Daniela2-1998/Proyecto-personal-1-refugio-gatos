-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2022 a las 00:37:27
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `refugiogatos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adopciones`
--

CREATE TABLE `adopciones` (
  `IDFamilia` int(50) NOT NULL,
  `Familia` varchar(50) NOT NULL,
  `NombreNuevo` varchar(50) NOT NULL,
  `FechaIngreso` timestamp NULL DEFAULT current_timestamp(),
  `Direccion` varchar(50) NOT NULL,
  `IDGato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `adopciones`
--

INSERT INTO `adopciones` (`IDFamilia`, `Familia`, `NombreNuevo`, `FechaIngreso`, `Direccion`, `IDGato`) VALUES
(1, 'Martinez', 'Copito', '2022-09-11 22:27:05', 'Avenida falsa 123', 2),
(2, 'Gomez', 'Gatucho', '2022-10-12 18:10:16', 'Avenida 123', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control`
--

CREATE TABLE `control` (
  `IDChequeo` int(11) NOT NULL,
  `NombreGato` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `IDGato` int(11) NOT NULL,
  `NumeroControl` int(11) NOT NULL,
  `Estado` text COLLATE utf8_unicode_ci NOT NULL,
  `Gastos` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `control`
--

INSERT INTO `control` (`IDChequeo`, `NombreGato`, `IDGato`, `NumeroControl`, `Estado`, `Gastos`) VALUES
(1, 'Camilo', 1, 1, 'Se encuentra en buen estado.', '$1.000'),
(2, 'Camilo', 1, 2, 'Requiere medicación especial', '$3.000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datoscuidador`
--

CREATE TABLE `datoscuidador` (
  `IDDatos` int(50) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Direccion` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GatosACargo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Capacidad` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `IDPersonal` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `datoscuidador`
--

INSERT INTO `datoscuidador` (`IDDatos`, `Nombre`, `Direccion`, `GatosACargo`, `Capacidad`, `IDPersonal`) VALUES
(1, 'Daniela', 'Avenida no existente 234', '2', '10', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastos`
--

CREATE TABLE `gastos` (
  `IDGasto` int(100) NOT NULL,
  `NombreGato` varchar(70) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `IDGato` int(11) NOT NULL,
  `Concepto` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Detalle` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Gasto` varchar(100) NOT NULL,
  `Gasto2` varchar(100) NOT NULL,
  `Gasto3` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gastos`
--

INSERT INTO `gastos` (`IDGasto`, `NombreGato`, `IDGato`, `Concepto`, `Detalle`, `Gasto`, `Gasto2`, `Gasto3`) VALUES
(1, 'Patita', 7, 'Veterinaria', 'Control y ecografia', '$1.000', '$2.000', ''),
(2, 'Manchita', 11, 'Cuidados', 'Compramos piedritas y un remedio \npara los ojitos.', '$3.000', '', ''),
(3, 'pupi', 2, 'Cuidados', 'piedritas', '$1.000', '', ''),
(4, 'Gatin', 6, 'Otros', 'gastamos en una nueva cucha', '$3.000', '', ''),
(5, 'Manchita', 11, 'Otros', 'compramos jaulita para bebes', '$7.000', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gastoscubiertos`
--

CREATE TABLE `gastoscubiertos` (
  `IDGastosCubiertos` int(50) NOT NULL,
  `Mes` varchar(50) NOT NULL,
  `Año` varchar(50) NOT NULL,
  `GastosTotales` varchar(50) NOT NULL,
  `Faltante` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gastoscubiertos`
--

INSERT INTO `gastoscubiertos` (`IDGastosCubiertos`, `Mes`, `Año`, `GastosTotales`, `Faltante`) VALUES
(1, '8', '2022', '$10.000', '$5.000'),
(2, '8', '2022', '$10.000', '$5.000'),
(3, '7', '2022', '$3.000', '$0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gatitos`
--

CREATE TABLE `gatitos` (
  `IDGato` int(11) NOT NULL,
  `NombreGato` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Edad` varchar(50) NOT NULL,
  `Sexo` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Raza` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Descripcion` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PersonaACargo` varchar(50) NOT NULL,
  `Status` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gatitos`
--

INSERT INTO `gatitos` (`IDGato`, `NombreGato`, `Edad`, `Sexo`, `Raza`, `Descripcion`, `PersonaACargo`, `Status`) VALUES
(1, 'Camilo', '11', 'Macho', 'siames', 'bueno, tranquilo, dormilon', 'Daniela', 'Buen estado'),
(2, 'pupi', '12', 'Macho', 'siames', '', 'Alicia', 'Rescatada en embarazo'),
(6, 'Gatin', '1 mes', 'Macho', 'atigrado', 'patita chueca', 'Karen', 'Mal estado'),
(7, 'Patita', '25 dias', 'Hembra', 'blanco', 'necesita mama', 'Karen', 'Rescatado y con necesidad de madre'),
(11, 'Manchita', '2 meses', 'Hembra', 'negro', '', 'Karen', 'Rescatado en buen estado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personalrefugio`
--

CREATE TABLE `personalrefugio` (
  `IDPersonal` int(50) NOT NULL,
  `Nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Apellido` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Usuario` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Contraseña` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Status` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `personalrefugio`
--

INSERT INTO `personalrefugio` (`IDPersonal`, `Nombre`, `Apellido`, `Usuario`, `Contraseña`, `Status`) VALUES
(1, 'Daniela', 'Mansilla', 'daniela', 'ff', ''),
(2, 'Alicia', 'Gomez', 'ali', '123', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguimiento`
--

CREATE TABLE `seguimiento` (
  `IDAdoptado` int(50) NOT NULL,
  `Familia` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NombreNuevo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `EstadoVet` text COLLATE utf8_unicode_ci NOT NULL,
  `Comportamiento` text COLLATE utf8_unicode_ci NOT NULL,
  `IDFamilia` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `seguimiento`
--

INSERT INTO `seguimiento` (`IDAdoptado`, `Familia`, `NombreNuevo`, `EstadoVet`, `Comportamiento`, `IDFamilia`) VALUES
(1, 'Martinez', 'Copito', 'La doc lo encontró en buen estado', 'Se está adaptando correctamente a la familia', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `adopciones`
--
ALTER TABLE `adopciones`
  ADD PRIMARY KEY (`IDFamilia`),
  ADD KEY `IDGato` (`IDGato`);

--
-- Indices de la tabla `control`
--
ALTER TABLE `control`
  ADD PRIMARY KEY (`IDChequeo`),
  ADD KEY `IDGato` (`IDGato`);

--
-- Indices de la tabla `datoscuidador`
--
ALTER TABLE `datoscuidador`
  ADD PRIMARY KEY (`IDDatos`),
  ADD KEY `IDPersonal` (`IDPersonal`);

--
-- Indices de la tabla `gastos`
--
ALTER TABLE `gastos`
  ADD PRIMARY KEY (`IDGasto`),
  ADD KEY `IDGato` (`IDGato`);

--
-- Indices de la tabla `gastoscubiertos`
--
ALTER TABLE `gastoscubiertos`
  ADD PRIMARY KEY (`IDGastosCubiertos`);

--
-- Indices de la tabla `gatitos`
--
ALTER TABLE `gatitos`
  ADD PRIMARY KEY (`IDGato`);

--
-- Indices de la tabla `personalrefugio`
--
ALTER TABLE `personalrefugio`
  ADD PRIMARY KEY (`IDPersonal`);

--
-- Indices de la tabla `seguimiento`
--
ALTER TABLE `seguimiento`
  ADD PRIMARY KEY (`IDAdoptado`),
  ADD KEY `IDFamilia` (`IDFamilia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `adopciones`
--
ALTER TABLE `adopciones`
  MODIFY `IDFamilia` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `control`
--
ALTER TABLE `control`
  MODIFY `IDChequeo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `datoscuidador`
--
ALTER TABLE `datoscuidador`
  MODIFY `IDDatos` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `gastos`
--
ALTER TABLE `gastos`
  MODIFY `IDGasto` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `gastoscubiertos`
--
ALTER TABLE `gastoscubiertos`
  MODIFY `IDGastosCubiertos` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `gatitos`
--
ALTER TABLE `gatitos`
  MODIFY `IDGato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `personalrefugio`
--
ALTER TABLE `personalrefugio`
  MODIFY `IDPersonal` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `seguimiento`
--
ALTER TABLE `seguimiento`
  MODIFY `IDAdoptado` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `adopciones`
--
ALTER TABLE `adopciones`
  ADD CONSTRAINT `adopciones_ibfk_1` FOREIGN KEY (`IDGato`) REFERENCES `gatitos` (`IDGato`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `control`
--
ALTER TABLE `control`
  ADD CONSTRAINT `control_ibfk_1` FOREIGN KEY (`IDGato`) REFERENCES `gatitos` (`IDGato`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `datoscuidador`
--
ALTER TABLE `datoscuidador`
  ADD CONSTRAINT `datoscuidador_ibfk_1` FOREIGN KEY (`IDPersonal`) REFERENCES `personalrefugio` (`IDPersonal`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `gastos`
--
ALTER TABLE `gastos`
  ADD CONSTRAINT `gastos_ibfk_1` FOREIGN KEY (`IDGato`) REFERENCES `gatitos` (`IDGato`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `seguimiento`
--
ALTER TABLE `seguimiento`
  ADD CONSTRAINT `seguimiento_ibfk_1` FOREIGN KEY (`IDFamilia`) REFERENCES `adopciones` (`IDFamilia`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
