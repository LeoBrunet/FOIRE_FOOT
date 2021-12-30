DROP TABLE IF EXISTS PRODUCT RESTRICT;
CREATE TABLE PRODUCT
(
    product_id         MEDIUMINT    NOT NULL AUTO_INCREMENT,
    product_name      varchar(255) NOT NULL UNIQUE,
    product_category   varchar(255) NOT NULL,
    product_description varchar(255) NOT NULL,
    product_price       MEDIUMINT ,
    product_stock         MEDIUMINT,
    product_image       varchar(255),
    PRIMARY KEY (product_id)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8;
INSERT INTO PRODUCT (product_id, product_name, product_category, product_description, product_price,  product_stock ,product_image)
VALUES (1, 'shirt MTP', 'clothes', 'beautiful shirt', 2, 2,'girondins.png');
INSERT INTO PRODUCT (product_id, product_name, product_category, product_description, product_price,  product_stock ,product_image)
VALUES (2, 'tote bag', 'accessorize', 'beautiful tote bag', 2, 2,'girondins.png');
