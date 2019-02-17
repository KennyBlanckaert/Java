DROP DATABASE  IF EXISTS `spring_security`;

CREATE DATABASE  IF NOT EXISTS `spring_security`;
USE `spring_security`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
-- bcrypt password requires 68 characters
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
-- {noop} for plain text passwords
-- {bcrypt} for bcrypt encrypted passwords
('kenny','{bcrypt}$2a$04$c06tMpBQH4l6bNVyGv/IGeM7ToqE80fQ0JCYl/j7CyKFHrqSMB9nm',1),
('dennis','{bcrypt}$2a$04$TjH9eyyiIY.9XCUmjN2z.OPe/i9dVYlnx7ko9m74cGA4loNvFsgNy',1),
('bryan','{bcrypt}$2a$04$fORM.yPJIDE6z6C0JqmDIOfp9Fxp8hoqFJzbWnaWcEKzWBLJVmzsa',1),
('susan','{bcrypt}$2a$04$fORM.yPJIDE6z6C0JqmDIOfp9Fxp8hoqFJzbWnaWcEKzWBLJVmzsa',1),
('tom','{bcrypt}$2a$04$fORM.yPJIDE6z6C0JqmDIOfp9Fxp8hoqFJzbWnaWcEKzWBLJVmzsa',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('kenny','ROLE_ADMIN'),
('kenny','ROLE_MANAGER'),
('kenny','QA'),
('kenny','EMPLOYEE'),
('dennis','ROLE_MANAGER'),
('dennis','QA'),
('dennis','EMPLOYEE'),
('tom','ROLE_EMPLOYEE'),
('bryan','ROLE_EMPLOYEE'),
('susan','ROLE_EMPLOYEE');


