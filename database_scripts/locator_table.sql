CREATE TABLE `autogendb`.`locators` (
  `locatorid` INT NOT NULL,
  `elementid` INT NULL,
  `locator_by` ENUM('classname', 'xpath', 'id', 'name', 'linktext', 'partial_linktext', 'css') NULL,
  `locator_param` VARCHAR(2000) NULL,
  PRIMARY KEY (`locatorid`));