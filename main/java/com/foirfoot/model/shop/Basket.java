package com.foirfoot.model.shop;
import java.util.*;
import com.foirfoot.model.team.Team;

import java.util.HashMap;
import java.util.List;

public class Basket {
    private HashMap<Product,Integer> mapProduct ;


    public Basket(HashMap<Product,Integer> mapProduct) {
        this.mapProduct= mapProduct;
    }
    public Basket(){};

    public void addProduct(Product p){
        this.mapProduct.put(p,1);
    }
    public void deleteProduct(Product p){
                this.mapProduct.remove(p);

    }
    public void deleteBasket(){
        this.mapProduct = null;//revoir pour la mettre vide mieux
    }
    public void validBasket(){

    }
    public boolean emptyBasket(){
        return mapProduct.size() == 0;
    }

    public void changeQuantity(Product p, int quantity){


    }

}
