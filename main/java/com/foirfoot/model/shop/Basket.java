package com.foirfoot.model.shop;
import java.util.*;
import com.foirfoot.model.team.Team;
import com.foirfoot.view.Main;

import java.util.HashMap;
import java.util.List;

public class Basket {
    private int user_id;
    private List<Product> listProduct = new ArrayList<>() ;

    public Basket(int user_id,List<Product> listProduct) {
        this.user_id = user_id;

        this.listProduct= listProduct;
    }
    /*public Basket(int user_id,Product p){
        this.user_id = user_id;
        this.listProduct.add(p);

    }*/
    public Basket(){};
    public List<Product> getListProduct(){
        return this.listProduct;
    }
    public int getProductId(){

        return this.listProduct.get(listProduct.size()-1).getId();
    }

    public int getUser_id(){
        return this.user_id;
    }

    public void addProduct(Product p){
        System.out.println(Main.connectedUser.getId());

            this.listProduct.add(p);
            System.out.println(listProduct);

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
