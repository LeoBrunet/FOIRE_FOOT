DROP TABLE IF EXISTS PRODUCT RESTRICT;
DROP TABLE IF EXISTS TRANSACTION RESTRICT;
DROP TABLE IF EXISTS ADDRESS RESTRICT;
DROP TABLE IF EXISTS BASKET RESTRICT;
CREATE TABLE PRODUCT
(
    product_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    product_name      varchar(255) NOT NULL UNIQUE,

    product_description varchar(255) NOT NULL,
    product_price       varchar(255) NOT NULL,
    product_stock         varchar(255) NOT NULL,
    product_clubId       MEDIUMINT,

    PRIMARY KEY (product_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
INSERT INTO PRODUCT (product_id, product_name,  product_description, product_price,  product_stock ,product_clubId)
VALUES (1, 'shirt MTP', 'beautiful shirt', 2, 2,1);
INSERT INTO PRODUCT (product_id, product_name,  product_description, product_price,  product_stock,product_clubId )
VALUES (2, 'tote bag',  'beautiful tote bag', 2, 2,2);
CREATE TABLE TRANSACTION
(
    transaction_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    transaction_user varchar(255) NOT NULL,
    transaction_basket      varchar(255) NOT NULL ,

    transaction_address varchar(255) NOT NULL,
    transaction_city       varchar(255) NOT NULL,
    transaction_country         varchar(255) NOT NULL,

    PRIMARY KEY (transaction_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
CREATE TABLE ADDRESS
(
    address_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    address_address varchar(255) NOT NULL,
    address_city      varchar(255) NOT NULL ,

    address_country varchar(255) NOT NULL,


    PRIMARY KEY (address_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
CREATE TABLE BASKET
(
    user_id         MEDIUMINT    ,
    product_id MEDIUMINT,



    PRIMARY KEY (user_id, product_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
INSERT INTO BASKET (user_id,product_id )
VALUES (7,2);