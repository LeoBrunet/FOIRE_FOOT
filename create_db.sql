SET FOREIGN_KEY_CHECKS = 0; -- to disable them
DROP TABLE IF EXISTS USERS RESTRICT;
DROP TABLE IF EXISTS CLUBS RESTRICT;
DROP TABLE IF EXISTS TEAMS RESTRICT;
CREATE TABLE USERS
(
    user_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    user_email      varchar(255) NOT NULL UNIQUE,
    user_password   varchar(255) NOT NULL,
    user_first_name varchar(255) NOT NULL,
    user_name       varchar(255) NOT NULL,
    club_id         MEDIUMINT,
    team_id         MEDIUMINT,
    user_role       TINYINT,
    PRIMARY KEY (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE CLUBS
(
    club_id           MEDIUMINT    NOT NULL AUTO_INCREMENT,
    club_name         varchar(255) NOT NULL,
    club_address      varchar(255),
    club_phone_number varchar(25),
    club_website      varchar(255),
    club_image_name   varchar(255),
    creator_user_id   MEDIUMINT,
    PRIMARY KEY (club_id),
    FOREIGN KEY (creator_user_id) REFERENCES USERS (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE TEAMS
(
    team_id   MEDIUMINT    NOT NULL AUTO_INCREMENT,
    team_name varchar(255) NOT NULL,
    club_id   MEDIUMINT,
    PRIMARY KEY (team_id),
    FOREIGN KEY (club_id) REFERENCES CLUBS (club_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE USERS
    ADD FOREIGN KEY (club_id) REFERENCES CLUBS (club_id);
ALTER TABLE USERS
    ADD FOREIGN KEY (team_id) REFERENCES TEAMS (team_id);

INSERT INTO USERS (user_email, user_password, user_name, user_first_name, club_id, user_role)
VALUES ('admin', SHA1('admin'), 'admin', 'admin', null, 0);

INSERT INTO USERS (user_email, user_password, user_name, user_first_name, club_id, user_role)
VALUES ('leobrunet91@gmail.com', SHA1('1234'), 'Brunet', 'Léo', 1, 1);

INSERT INTO USERS (user_email, user_password, user_name, user_first_name, club_id, user_role)
VALUES ('clement', SHA1('1234'), 'Cantero', 'Clement', 2, 1);

INSERT INTO USERS (user_email, user_password, user_name, user_first_name, club_id, user_role)
VALUES ('pep@gmail.com', SHA1('1234'), 'Guardiola', 'Pep', 1, 2);

INSERT INTO USERS (user_email, user_password, user_name, user_first_name, club_id, user_role)
VALUES ('jose@gmail.com', SHA1('1234'), 'Mourinho', 'Jose', 2, 2);

INSERT INTO CLUBS (club_name, creator_user_id, club_address, club_phone_number, club_website, club_image_name)
VALUES ('FC Girondins de Bordeaux', 3, 'Rue Joliot-Curie, Le Haillan 33185 Bordeaux, France', '+330505156012',
        'http://www.girondins.com', 'girondins.png');

INSERT INTO CLUBS (club_name, creator_user_id, club_address, club_phone_number, club_website, club_image_name)
VALUES ('Montpellier Hérault FC', 2, 'Rue Joliot-Curie, Le Haillan 33185 Bordeaux, France', '+330505156012',
        'http://www.mhsc.com', 'mhsc.png');

SET FOREIGN_KEY_CHECKS = 1; -- to re-enable them

