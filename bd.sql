SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `qbank` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `qbank` ;

-- -----------------------------------------------------
-- Table `qbank`.`AREA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`AREA` ;

CREATE TABLE IF NOT EXISTS `qbank`.`AREA` (
  `ArCod` INT NOT NULL,
  `ArNom` VARCHAR(50) NOT NULL,
  `ArCantPreg` VARCHAR(3) NOT NULL DEFAULT '60',
  `ArEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`ArCod`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`CURSO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`CURSO` ;

CREATE TABLE IF NOT EXISTS `qbank`.`CURSO` (
  `CuCod` INT NOT NULL,
  `CuNom` VARCHAR(50) NOT NULL,
  `CuEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`CuCod`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`AREA_DET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`AREA_DET` ;

CREATE TABLE IF NOT EXISTS `qbank`.`AREA_DET` (
  `ArCod` INT NOT NULL,
  `ArCursos` INT NOT NULL,
  `ArCursCant` INT NOT NULL,
  `ArEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`ArCod`, `ArCursos`),
  INDEX `ArCursos_idx` (`ArCursos` ASC),
  CONSTRAINT `ArCod`
    FOREIGN KEY (`ArCod`)
    REFERENCES `qbank`.`AREA` (`ArCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ArCursos`
    FOREIGN KEY (`ArCursos`)
    REFERENCES `qbank`.`CURSO` (`CuCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`BALOTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`BALOTA` ;

CREATE TABLE IF NOT EXISTS `qbank`.`BALOTA` (
  `BalCod` INT NOT NULL,
  `BalNom` VARCHAR(50) NOT NULL,
  `BalDesc` VARCHAR(200) NOT NULL,
  `BalCurs` INT NOT NULL,
  `BalEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`BalCod`),
  INDEX `BalCurs_idx` (`BalCurs` ASC),
  CONSTRAINT `BalCurs`
    FOREIGN KEY (`BalCurs`)
    REFERENCES `qbank`.`CURSO` (`CuCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`DIFICULTAD_TIPO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`DIFICULTAD_TIPO` ;

CREATE TABLE IF NOT EXISTS `qbank`.`DIFICULTAD_TIPO` (
  `DifTipCod` INT NOT NULL,
  `DifTipDesc` VARCHAR(50) NOT NULL,
  `DifTipEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`DifTipCod`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`PREGUNTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`PREGUNTA` ;

CREATE TABLE IF NOT EXISTS `qbank`.`PREGUNTA` (
  `PregCod` INT NOT NULL,
  `PregCurs` INT NOT NULL,
  `PregBal` INT NOT NULL,
  `PregForm` VARCHAR(50) NOT NULL,
  `PregDif` INT NOT NULL,
  `PregFecha` VARCHAR(10) NOT NULL,
  `PregEnum` VARCHAR(500) NOT NULL,
  `PregSol` VARCHAR(500) NOT NULL,
  `PregAltUno` VARCHAR(255) NOT NULL,
  `PregAltDos` VARCHAR(255) NOT NULL,
  `PregAltTres` VARCHAR(255) NOT NULL,
  `PregAltCuat` VARCHAR(255) NOT NULL,
  `PregAltCinc` VARCHAR(255) NOT NULL,
  `PregRespt` VARCHAR(255) NOT NULL,
  `PregEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`PregCod`),
  INDEX `PregDif_idx` (`PregDif` ASC),
  INDEX `PregBal_idx` (`PregBal` ASC),
  INDEX `PregCurs_idx` (`PregCurs` ASC),
  CONSTRAINT `PregDif`
    FOREIGN KEY (`PregDif`)
    REFERENCES `qbank`.`DIFICULTAD_TIPO` (`DifTipCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PregBal`
    FOREIGN KEY (`PregBal`)
    REFERENCES `qbank`.`BALOTA` (`BalCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PregCurs`
    FOREIGN KEY (`PregCurs`)
    REFERENCES `qbank`.`BALOTA` (`BalCurs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`EXAMEN_CAB`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`EXAMEN_CAB` ;

CREATE TABLE IF NOT EXISTS `qbank`.`EXAMEN_CAB` (
  `ExaCod` INT NOT NULL,
  `ExaProc` VARCHAR(50) NOT NULL,
  `ExaArea` INT NOT NULL,
  `ExaFech` VARCHAR(10) NOT NULL,
  `ExaEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`ExaCod`),
  INDEX `ExaArea_idx` (`ExaArea` ASC),
  CONSTRAINT `ExaArea`
    FOREIGN KEY (`ExaArea`)
    REFERENCES `qbank`.`AREA` (`ArCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`EXAMEN_DET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`EXAMEN_DET` ;

CREATE TABLE IF NOT EXISTS `qbank`.`EXAMEN_DET` (
  `ExaCod` INT NOT NULL,
  `ExaPreg` INT NOT NULL,
  `ExaEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`ExaPreg`, `ExaCod`),
  INDEX `ExaPreg_idx` (`ExaPreg` ASC),
  INDEX `ExaCod_idx` (`ExaCod` ASC),
  CONSTRAINT `ExaCod`
    FOREIGN KEY (`ExaCod`)
    REFERENCES `qbank`.`EXAMEN_CAB` (`ExaCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ExaPreg`
    FOREIGN KEY (`ExaPreg`)
    REFERENCES `qbank`.`PREGUNTA` (`PregCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`USERTIPO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`USERTIPO` ;

CREATE TABLE IF NOT EXISTS `qbank`.`USERTIPO` (
  `UsuTipCod` INT NOT NULL,
  `UsuTipDesc` VARCHAR(50) NOT NULL,
  `UsuTipEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`UsuTipCod`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`USUARIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`USUARIO` ;

CREATE TABLE IF NOT EXISTS `qbank`.`USUARIO` (
  `UsuCod` INT NOT NULL,
  `UsuNom` VARCHAR(50) NOT NULL,
  `UsuContra` VARCHAR(50) NOT NULL,
  `UsuTip` INT NOT NULL,
  `UsuEstReg` VARCHAR(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`UsuCod`),
  INDEX `UsuTip_idx` (`UsuTip` ASC),
  CONSTRAINT `UsuTip`
    FOREIGN KEY (`UsuTip`)
    REFERENCES `qbank`.`USERTIPO` (`UsuTipCod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qbank`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`user` ;

CREATE TABLE IF NOT EXISTS `qbank`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP);


-- -----------------------------------------------------
-- Table `qbank`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`category` ;

CREATE TABLE IF NOT EXISTS `qbank`.`category` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `qbank`.`category_1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`category_1` ;

CREATE TABLE IF NOT EXISTS `qbank`.`category_1` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `qbank`.`category_2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qbank`.`category_2` ;

CREATE TABLE IF NOT EXISTS `qbank`.`category_2` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
