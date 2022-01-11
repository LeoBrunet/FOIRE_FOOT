DROP TABLE IF EXISTS TEAMS RESTRICT;
DROP TABLE IF EXISTS RESULTS RESTRICT;
DROP TABLE IF EXISTS CATEGORIES RESTRICT;
DROP TABLE IF EXISTS TYPES RESTRICT;
CREATE TABLE TEAMS
(
    team_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    team_name      varchar(255) NOT NULL UNIQUE,
    club_id  MEDIUMINT NOT NULL,

    category varchar(255) NOT NULL,
    type       varchar(255) NOT NULL,
    PRIMARY KEY (team_id),
    FOREIGN KEY (club_id) REFERENCES CLUBS (club_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;

CREATE TABLE RESULTS
(
    result_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    score_ht        MEDIUMINT,
    score_ot      MEDIUMINT,

    home_team MEDIUMINT NOT NULL,
    outside_team      MEDIUMINT NOT NULL,
    PRIMARY KEY (result_id),
    FOREIGN KEY (home_team) REFERENCES TEAMS (team_id),
    FOREIGN KEY (outside_team) REFERENCES TEAMS (team_id)

)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;

CREATE TABLE CATEGORIES
(

    cat_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    cat_name varchar(255) NOT NULL,
    PRIMARY KEY (cat_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;

CREATE TABLE TYPES
(

    type_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    type_name varchar(255) NOT NULL,
    PRIMARY KEY (type_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
