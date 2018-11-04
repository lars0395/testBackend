CREATE TABLE `evaluationPortal`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `productType` VARCHAR(45) NOT NULL,
  `payment_costs` DOUBLE NOT NULL,
  `payment_rate` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
