DROP TABLE USERS;
CREATE TABLE `USERS`
(
    `id`       MEDIUMINT    NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) NOT NULL UNIQUE ,
    `password` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `first_name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO USERS (email, password, name, first_name)
VALUES ('admin', SHA1('admin'), 'admin', 'admin');