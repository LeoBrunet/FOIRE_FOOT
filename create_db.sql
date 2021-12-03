CREATE DATABASE db_foir_foot;
USE db_foir_foot;

CREATE TABLE `USERS` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO USERS VALUES (1, 'leobrunet91@gmail.com', SHA1('1234'));