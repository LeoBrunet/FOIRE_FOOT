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
    club_id   MEDIUMINT    NOT NULL AUTO_INCREMENT,
    club_name varchar(255) NOT NULL,
    creator_user_id   MEDIUMINT,
    PRIMARY KEY (club_id),
    FOREIGN KEY (creator_user_id) REFERENCES USERS (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE TEAMS
(
    team_id MEDIUMINT NOT NULL AUTO_INCREMENT,
    team_name varchar(255) NOT NULL,
    club_id MEDIUMINT,
    PRIMARY KEY (team_id),
    FOREIGN KEY (club_id) REFERENCES CLUBS (club_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE USERS
    ADD FOREIGN KEY (club_id) REFERENCES CLUBS (club_id);
ALTER TABLE USERS
    ADD FOREIGN KEY (team_id) REFERENCES TEAMS (team_id);
SET FOREIGN_KEY_CHECKS = 1; -- to re-enable them

INSERT INTO USERS (user_email, user_password, user_name, user_first_name)
VALUES ('admin', SHA1('admin'), 'admin', 'admin');

SELECT *
FROM USERS;