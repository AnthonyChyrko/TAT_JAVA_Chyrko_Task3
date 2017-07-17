-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema test_DB_library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test_DB_library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_DB_library` DEFAULT CHARACTER SET utf8 ;
USE `test_DB_library` ;

-- -----------------------------------------------------
-- Table `test_DB_library`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`Users` (
  `u_id` INT NOT NULL AUTO_INCREMENT,
  `u_login` VARCHAR(45) NOT NULL,
  `u_password` VARCHAR(45) NOT NULL,
  `u_access` ENUM('N', 'U', 'A', 'SA') NOT NULL,
  `u_signIn` ENUM('IN', 'OUT', 'BAN') NOT NULL,
  PRIMARY KEY (`u_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_DB_library`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`genres` (
  `g_id` INT NOT NULL AUTO_INCREMENT,
  `g_name` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`g_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_DB_library`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`books` (
  `b_id` INT NOT NULL AUTO_INCREMENT,
  `b_name` VARCHAR(150) NOT NULL,
  `b_year` SMALLINT(5) NULL,
  `b_quantity` SMALLINT(5) NOT NULL,
  `b_available` ENUM('Y', 'N') NOT NULL,
  PRIMARY KEY (`b_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_DB_library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`authors` (
  `a_id` INT NOT NULL AUTO_INCREMENT,
  `a_name` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`a_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_DB_library`.`subscriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`subscriptions` (
  `sb_id` INT NOT NULL AUTO_INCREMENT,
  `u_id` INT NOT NULL,
  `b_id` INT NOT NULL,
  `sb_start` DATE NOT NULL,
  `sb_finish` DATE NOT NULL,
  `sb_is_active` ENUM('Y', 'N') NOT NULL,
  PRIMARY KEY (`sb_id`),
  INDEX `fk_subscriptions_books1_idx` (`b_id` ASC),
  INDEX `fk_subscriptions_Users1_idx` (`u_id` ASC),
  CONSTRAINT `fk_subscriptions_books1`
    FOREIGN KEY (`b_id`)
    REFERENCES `test_DB_library`.`books` (`b_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscriptions_Users1`
    FOREIGN KEY (`u_id`)
    REFERENCES `test_DB_library`.`Users` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_DB_library`.`m2m_books_genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`m2m_books_genres` (
  `b_id` INT NOT NULL,
  `g_id` INT NOT NULL,
  PRIMARY KEY (`b_id`, `g_id`),
  INDEX `fk_books_has_genres_genres1_idx` (`g_id` ASC),
  INDEX `fk_books_has_genres_books_idx` (`b_id` ASC),
  CONSTRAINT `fk_books_has_genres_books`
    FOREIGN KEY (`b_id`)
    REFERENCES `test_DB_library`.`books` (`b_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_books_has_genres_genres1`
    FOREIGN KEY (`g_id`)
    REFERENCES `test_DB_library`.`genres` (`g_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_DB_library`.`m2m_books_authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_DB_library`.`m2m_books_authors` (
  `b_id` INT NOT NULL,
  `a_id` INT NOT NULL,
  PRIMARY KEY (`b_id`, `a_id`),
  INDEX `fk_books_has_authors_authors1_idx` (`a_id` ASC),
  INDEX `fk_books_has_authors_books1_idx` (`b_id` ASC),
  CONSTRAINT `fk_books_has_authors_books1`
    FOREIGN KEY (`b_id`)
    REFERENCES `test_DB_library`.`books` (`b_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_books_has_authors_authors1`
    FOREIGN KEY (`a_id`)
    REFERENCES `test_DB_library`.`authors` (`a_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
