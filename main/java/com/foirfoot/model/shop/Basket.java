package com.foirfoot.model.shop;

import com.foirfoot.model.team.Team;

import java.util.List;

public class Basket {
    private List<Product> listProduct ;// remplacer par un map <product, quantity>


    public Basket(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
    public Basket(){};

    public void addProduct(Product p){
        this.listProduct.add(p);
    }
    public void deleteProduct(Product p){
                this.listProduct.remove(p);

    }
    public void deleteBasket(){
        this.listProduct = null;//revoir pour la mettre vide mieux
    }
    public void validBasket(){

    }
    public boolean emptyBasket(){
        return listProduct.size() == 0;
    }

    public void changeQuantity(Product p, int quantity){


    }

}
