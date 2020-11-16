--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `VendingMachine`.`Customer` DROP PRIMARY KEY;

ALTER TABLE `VendingMachine`.`Basket` DROP PRIMARY KEY;

ALTER TABLE `VendingMachine`.`Admin` DROP PRIMARY KEY;

ALTER TABLE `VendingMachine`.`Bill` DROP PRIMARY KEY;

ALTER TABLE `VendingMachine`.`Menu` DROP PRIMARY KEY;

ALTER TABLE `VendingMachine`.`Basket` DROP INDEX `VendingMachine`.`customer_phone`;

ALTER TABLE `VendingMachine`.`Bill` DROP INDEX `VendingMachine`.`customer_phone`;

DROP TABLE `VendingMachine`.`Basket`;

DROP TABLE `VendingMachine`.`Bill`;

DROP TABLE `VendingMachine`.`Customer`;

DROP TABLE `VendingMachine`.`Admin`;

DROP TABLE `VendingMachine`.`Menu`;

CREATE TABLE `VendingMachine`.`Basket` (
	`menu_id` VARCHAR(255) NOT NULL,
	`customer_phone` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`menu_id`,`customer_phone`)
) ENGINE=InnoDB;

CREATE TABLE `VendingMachine`.`Bill` (
	`id` MEDIUMINT NOT NULL,
	`customer_phone` VARCHAR(255) DEFAULT NULL,
	`total` INT DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `VendingMachine`.`Customer` (
	`name` VARCHAR(255) NOT NULL,
	`phone` VARCHAR(255) NOT NULL,
	`birthday` DATE DEFAULT 'NULL',
	`level` INT NOT NULL,
	PRIMARY KEY (`phone`)
) ENGINE=InnoDB;

CREATE TABLE `VendingMachine`.`Admin` (
	`user` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`user`)
) ENGINE=InnoDB;

CREATE TABLE `VendingMachine`.`Menu` (
	`id` VARCHAR(255) NOT NULL,
	`category` VARCHAR(255) NOT NULL,
	`path` VARCHAR(255) DEFAULT NULL,
	`name` VARCHAR(255) NOT NULL,
	`price` INT NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE INDEX `customer_phone` ON `VendingMachine`.`Basket` (`customer_phone` ASC);

CREATE INDEX `customer_phone` ON `VendingMachine`.`Bill` (`customer_phone` ASC);

