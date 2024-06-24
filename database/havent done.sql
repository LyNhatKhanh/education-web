DROP SCHEMA IF EXISTS `web-education`;

CREATE SCHEMA `web-education`;

use `web-education`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `people` (
	`id` int NOT NULL AUTO_INCREMENT,
	`first_name` varchar(45) DEFAULT NULL,
	`last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    
    `createdate` TIMESTAMP NULL,
    `modifieddate` TIMESTAMP NULL,
    `createby` VARCHAR(255) NULL,
	`modifiedby` VARCHAR(255) NULL,

	PRIMARY KEY (`id`)
    
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course` (
	



)

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  `people_id` int NOT NULL AUTO_INCREMENT,
  
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_PEOPLE_ID` FOREIGN KEY (`people_id`) 
  REFERENCES `people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


