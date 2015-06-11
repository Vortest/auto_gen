CREATE TABLE `autogendb`.`pages` (
  `pageid` INT NOT NULL AUTO_INCREMENT,
  `websiteid` INT NULL,
  `uri` VARCHAR(3000) NULL,
  PRIMARY KEY (`pageid`));
