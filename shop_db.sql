DROP TABLE IF EXISTS PRODUCT RESTRICT;
CREATE TABLE PRODUCT
(
    product_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    product_name      varchar(255) NOT NULL UNIQUE,

    product_description varchar(255) NOT NULL,
    product_price       varchar(255) NOT NULL,
    product_stock         varchar(255) NOT NULL,

    PRIMARY KEY (product_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
INSERT INTO PRODUCT (product_id, product_name,  product_description, product_price,  product_stock )
VALUES (1, 'shirt MTP', 'beautiful shirt', 2, 2);
INSERT INTO PRODUCT (product_id, product_name,  product_description, product_price,  product_stock )
VALUES (2, 'tote bag',  'beautiful tote bag', 2, 2);
