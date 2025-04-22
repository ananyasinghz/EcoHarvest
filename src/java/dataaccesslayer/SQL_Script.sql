

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `name` VARCHAR(50) NOT NULL,
                                              `password` VARCHAR(50) NOT NULL,
                                              `subscribeToPhone` TINYINT NOT NULL,
                                              `subscribeToEmail` TINYINT NOT NULL,
                                              `location` VARCHAR(50) NOT NULL,
                                              `role` VARCHAR(50) NOT NULL,
                                              `phone` VARCHAR(50) NULL,
                                              `mail` VARCHAR(50) NULL,
                                              `preference` VARCHAR(50) NULL,
                                              PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`item` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `name` VARCHAR(50) NOT NULL,
                                             `category` VARCHAR(50) NOT NULL,
                                             PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`retailer_inventory` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `users_id` INT NOT NULL,
                                                           `item_id` INT NOT NULL,
                                                           `batch` INT NOT NULL,
                                                           `expiry_date` DATETIME NOT NULL,
                                                           `quantity` INT NOT NULL,
                                                           `unit_price` DOUBLE NOT NULL,
                                                           `final_price` DOUBLE NOT NULL,
                                                           `sale` BOOLEAN NULL,
                                                           `donation` BOOLEAN NULL,
                                                           PRIMARY KEY (`id`),
                                                           INDEX `fk_userInventory_users_idx` (`users_id` ASC),
                                                           INDEX `fk_userInventory_item1_idx` (`item_id` ASC),
                                                           CONSTRAINT `fk_userInventory_users`
                                                               FOREIGN KEY (`users_id`)
                                                                   REFERENCES `mydb`.`users` (`id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION,
                                                           CONSTRAINT `fk_userInventory_item1`
                                                               FOREIGN KEY (`item_id`)
                                                                   REFERENCES `mydb`.`item` (`id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`charity_inventory` (
                                                          `id` INT NOT NULL,
                                                          `users_id` INT NOT NULL,
                                                          `quantity` VARCHAR(50) NOT NULL,
                                                          `userInventory_id` INT NOT NULL,
                                                          PRIMARY KEY (`id`, `userInventory_id`),
                                                          INDEX `fk_charitiyInventoy_users1_idx` (`users_id` ASC),
                                                          INDEX `fk_charitiyInventoy_userInventory1_idx` (`userInventory_id` ASC),
                                                          CONSTRAINT `fk_charitiyInventoy_users1`
                                                              FOREIGN KEY (`users_id`)
                                                                  REFERENCES `mydb`.`users` (`id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `fk_charitiyInventoy_userInventory1`
                                                              FOREIGN KEY (`userInventory_id`)
                                                                  REFERENCES `mydb`.`retailer_inventory` (`id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`transaction` (
                                                    `transaction_id` int NOT NULL AUTO_INCREMENT,
                                                    `userInventory_id` INT NOT NULL,
                                                    `users_id` INT NOT NULL,
                                                    `quantity` INT NOT NULL,
                                                    PRIMARY KEY (`transaction_id`),
                                                    INDEX `fk_transaction_userInventory1_idx` (`userInventory_id` ASC),
                                                    INDEX `fk_transaction_users1_idx` (`users_id` ASC),
                                                    CONSTRAINT `fk_transaction_userInventory1`
                                                        FOREIGN KEY (`userInventory_id`)
                                                            REFERENCES `mydb`.`retailer_inventory` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT `fk_transaction_users1`
                                                        FOREIGN KEY (`users_id`)
                                                            REFERENCES `mydb`.`users` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`userAccount` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `balance` DOUBLE NOT NULL,
                                                    `users_id` INT NOT NULL,
                                                    PRIMARY KEY (`id`, `users_id`),
                                                    INDEX `fk_userAccount_users1_idx` (`users_id` ASC),
                                                    CONSTRAINT `fk_userAccount_users1`
                                                        FOREIGN KEY (`users_id`)
                                                            REFERENCES `mydb`.`users` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;