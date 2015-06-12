CREATE TABLE `autogendb`.`locators` (
  `locatorid` INT NOT NULL AUTO_INCREMENT,
  `elementid` INT NULL,
  `locator_by` INT NULL,
  `locator_param` VARCHAR(2000) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `active` INT NULL,
  PRIMARY KEY (`locatorid`));