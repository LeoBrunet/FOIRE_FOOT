package com.foirfoot.model;

public class Product {
     private String nameProduct;
     private String nameCategory;
     private String description;
     private float price;
     private float stock;

     public Product(String nameProduct, String nameCategory, String description, float price, float stock) {
          this.nameProduct = nameProduct;
          this.nameCategory = nameCategory;
          this.description = description;
          this.price = price;
          this.stock = stock;
     }

     public String getName(){
          return this.nameProduct;
     }
     public String getNameCategory(){
          return this.nameCategory;
     }
     public String getDescription(){
          return this.description;
     }
     public float getPrice(){
          return this.price;
     }
}
