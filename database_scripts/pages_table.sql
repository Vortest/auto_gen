CREATE TABLE `autogendb`.`pages` (
  `pageid` INT NOT NULL AUTO_INCREMENT,
  `websiteid` INT NULL,
  `uri` VARCHAR(3000) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `active` INT NULL,
  PRIMARY KEY (`pageid`));
