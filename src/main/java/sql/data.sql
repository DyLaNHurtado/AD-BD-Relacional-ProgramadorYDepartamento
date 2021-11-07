SET NAMES utf8;
SET
time_zone = '+00:00';
SET
foreign_key_checks = 0;
SET
sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP
DATABASE IF EXISTS `pruebas`;
CREATE
DATABASE `pruebas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE
`pruebas`;

DROP TABLE IF EXISTS `programador`;
CREATE TABLE `programador`
(
    id           BIGINT(50) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre       varchar(100) NOT NULL,
    aniosExp     INT          NOT NULL,
    salario      DOUBLE       NOT NULL,
    lenguajes    varchar(100) NOT NULL,
    departamento varchar(100) NOT NULL
);

INSERT INTO `programador` (id, nombre, aniosExp, salario, lenguajes, departamento)
VALUES (1, 'Federico', 10, 1000, 'Python;C;C++', 'Departamento de Ventas'),
       (2, 'Jose Luis', 8, 1300, 'JavaScript;TypeScript;Java', 'Departamento de Ventas'),
       (3, 'Sergio', 5, 2000, 'Java;TypeScript', 'Departamento de Ventas'),
       (4, 'Eneko', 6, 2500, 'Java;Python', 'Departamento de Ventas'),
       (5, 'Spiderman', 10, 2100, 'Java;TypeScript', 'Departamento de Justicia'),
       (6, 'Batman', 12, 2000, 'Java;C;TypeScript', 'Departamento de Justicia'),
       (7, 'Ironman', 20, 3000, 'C;C++', 'Departamento de Justicia'),
       (8, 'Hulk', 3, 500, 'Java;C;JavaScript', 'Departamento de Justicia'),
       (9, 'Pikachu', 1, 1500, 'TypeScript', 'Departamento de Marketing'),
       (10, 'Charmander', 3, 2150, 'C++', 'Departamento de Marketing'),
       (11, 'Squirtle', 2, 2100, 'Python', 'Departamento de Marketing'),
       (12, 'Bulbasur', 5, 2800, 'Java;C++', 'Departamento de Marketing');

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE departamento
(
    id              BIGINT(50) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre          varchar(100) NOT NULL,
    presupuesto     int(11) NOT NULL,
    jefe            varchar(100) NOT NULL,
    programadorList varchar(250) NOT NULL
);

INSERT INTO `departamento` (`id`, `nombre`, `presupuesto`, `jefe`, `programadorList`)
VALUES (1, 'Departamento de Ventas', 10000, 'Federico', 'Jose Luis;Sergio;Eneko'),
       (2, 'Deartamento de Justicia', 20000, 'Spiderman', 'Batman;IronMan;Hulk'),
       (3, 'Deartamento de Marketing', 1000, 'Pikachu', 'Charmander;Squirtle;Bulbasur');