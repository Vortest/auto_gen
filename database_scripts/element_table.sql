CREATE TABLE `autogendb`.`elements` (
  `elementid` INT NOT NULL AUTO_INCREMENT,
  `pageid` INT NOT NULL,
  `default_text` VARCHAR(5000) NULL,
  `tagName` VARCHAR(50) NULL,
  `attributes` VARCHAR(5000) NULL COMMENT 'This might be encoded as a base64 string',
  PRIMARY KEY (`elementid`));
