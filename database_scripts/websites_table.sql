CREATE TABLE `autogendb`.`websites` (
  `websiteid` INT NOT NULL AUTO_INCREMENT,
  `uri` VARCHAR(300) NULL,
  `page_num` INT NULL,
  `test_num` INT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `active` INT NULL,
  PRIMARY KEY (`websiteid`),
  UNIQUE INDEX `uri_UNIQUE` (`uri` ASC));
