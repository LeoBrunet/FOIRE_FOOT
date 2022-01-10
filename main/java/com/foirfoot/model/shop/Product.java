package com.foirfoot.model.shop;

import java.io.InputStream;

public class Product {
     private int id;
     private String nameProduct;
     private Object nameCategory;
     private String description;
     private int price;
     private String stock;
     private int clubId;
     private String imageName;
     private InputStream imageIS;

     public Product(int id,String nameProduct, Object nameCategory, String description, int price, String stock,int clubId,String imageName,InputStream imageIS) {
          this.id = id;
          this.nameProduct = nameProduct;
          this.nameCategory = nameCategory;
          this.description = description;
          this.price = price;
          this.stock = stock;
          this.clubId = clubId;
          this.imageName = imageName;
          this.imageIS = imageIS;
     }

     public Product(String product_name,Object nameCategory, String product_description, int product_price, String product_stock,int clubId,String imageName,InputStream imageIS) {
          this.nameProduct = product_name;
          this.nameCategory = nameCategory;
          this.description=product_description;
          this.price= product_price;
          this.stock= product_stock;
          this.clubId = clubId;
          this.imageName = imageName;
          this.imageIS = imageIS;

     }

     public Product(String product_name, Object nameCategory,String product_description, int product_price, String product_stock,int clubId,String imageName) {
          this.nameProduct = product_name;
          this.nameCategory = nameCategory;
          this.description=product_description;
          this.price= product_price;
          this.stock= product_stock;
          this.clubId = clubId;
          this.imageName = imageName;

     }

     public Product(int id,String product_name,Object nameCategory, String product_description, int product_price, String product_stock,int clubId,String imageName) {
          this.id =id;
          this.nameProduct = product_name;
          this.nameCategory = nameCategory;
          this.description=product_description;
          this.price= product_price;
          this.stock= product_stock;
          this.clubId = clubId;
          this.imageName = imageName;

     }

     public InputStream getImageIS() {
          return imageIS;
     }

     public String getName(){
          return this.nameProduct;
     }

     public Object getNameCategory() {
          return nameCategory;
     }

     /*public String getNameCategory(){
               return this.nameCategory;
          }*/
     public String getDescription(){
          return this.description;
     }
     public int getPrice(){
          return this.price;
     }
     public String getStock(){
          return this.stock;
     }
     /*public String getImage(){
          return this.imageName;
     }*/

     public String getNameProduct() {
          return nameProduct;
     }

     public String getImageName() {
          return imageName;
     }

     public void setId(int id) {
          this.id = id;
     }
     public int getId(){
          return this.id;
     }

     public int getClubId() {
          return clubId;
     }

     @Override
     public String toString() {
          return "Product{" +
                  "id=" + id +
                  ", nameProduct='" + nameProduct + '\'' +
                  ", description='" + description + '\'' +
                  ", price='" + price + '\'' +
                  ", stock='" + stock + '\'' +
                  ", clubId='" + clubId + '\''+
                  '}';
     }
}
