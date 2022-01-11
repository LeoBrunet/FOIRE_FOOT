DROP TABLE IF EXISTS PRODUCT RESTRICT;
DROP TABLE IF EXISTS TRANSACTION RESTRICT;
DROP TABLE IF EXISTS ADDRESS RESTRICT;
DROP TABLE IF EXISTS BASKET RESTRICT;
CREATE TABLE PRODUCT
(
    product_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    product_name      varchar(255) NOT NULL UNIQUE,
    product_category  varchar(255) NOT NULL,

    product_description varchar(255) NOT NULL,
    product_price       varchar(255) NOT NULL,
    product_stock         varchar(255) NOT NULL,
    product_clubId       MEDIUMINT,
    product_image       varchar(255) NOT NULL,
    PRIMARY KEY (product_id),
    FOREIGN KEY (product_clubId) REFERENCES CLUBS (club_id) ON DELETE CASCADE
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;

INSERT INTO PRODUCT (product_id, product_name, product_category, product_description, product_price,  product_stock ,product_clubId,product_image)
VALUES (1, 'shirt MTP', 'clothes','beautiful shirt', 2, 2,1,'girondins.png');
INSERT INTO PRODUCT (product_id, product_name,  product_category, product_description, product_price,  product_stock,product_clubId, product_image )
VALUES (2, 'tote bag','accessorize',  'beautiful tote bag', 2, 2,2,'girondins.png');

CREATE TABLE TRANSACTION
(
    transaction_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    transaction_user        MEDIUMINT,
    transaction_basket      MEDIUMINT,

    transaction_address varchar(255) NOT NULL,
    transaction_city       varchar(255) NOT NULL,
    transaction_country         varchar(255) NOT NULL,
    transaction_payment         varchar(255) NOT NULL,
    transaction_nbproducts  MEDIUMINT,
    transaction_total       MEDIUMINT,
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
    quantity MEDIUMINT,



    PRIMARY KEY (user_id, product_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;

INSERT INTO BASKET (user_id,product_id,quantity )
VALUES (7,2,1);