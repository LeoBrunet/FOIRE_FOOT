package com.foirfoot.model.shop;

import com.foirfoot.model.team.Team;

import java.util.List;

public class Basket {
    private List<Product> listProduct ;


    public Basket(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void addProduct(Product p){
        this.listProduct.add(p);
    }

}
