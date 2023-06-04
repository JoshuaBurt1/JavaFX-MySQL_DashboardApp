--Database structure

CREATE DATABASE transactions;
USE transactions;

CREATE TABLE `calculations` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) DEFAULT NULL,
  `variable1` double DEFAULT NULL,
  `variable2` double DEFAULT NULL,
  `output` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

CREATE TABLE `car` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `down` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `interest` double DEFAULT NULL,
  `totalPayment` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

CREATE TABLE `dental` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL,
  `address` VARCHAR(50) DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `service` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

CREATE TABLE `tips` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `tipPercent` double DEFAULT NULL,
  `tipAmount` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

-- INSERT INTO tips (amount, tipPercent, tipAmount, total) VALUES (10,43,12,24);
-- INSERT INTO tips (`total`) VALUES (68);
-- DELETE FROM tips WHERE ID = 1;
-- SELECT * FROM tips ORDER BY total ASC;
-- SELECT * FROM car ORDER BY totalPayment ASC;
-- SELECT * FROM tips;
-- SELECT COUNT(*)
-- FROM INFORMATION_SCHEMA.COLUMNS
-- WHERE TABLE_NAME = 'tips';
-- SELECT COUNT(*) as num FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tips';
-- SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'tips';
-- DROP TABLE calculations;
-- DROP DATABASE TRANSACTIONS;