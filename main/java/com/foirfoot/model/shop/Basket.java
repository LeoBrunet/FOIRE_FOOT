package com.foirfoot.model.shop;
import java.util.*;
import com.foirfoot.model.team.Team;
import com.foirfoot.view.Main;

import java.util.HashMap;
import java.util.List;

public class Basket {
    private int user_id;
    private List<Product> listProduct = new ArrayList<>() ;
    private List<Integer> listQuantity = new ArrayList<>() ;



    public Basket(int user_id,List<Product> listProduct,List<Integer> listQuantity) {
        this.user_id = user_id;

        this.listProduct= listProduct;
        this.listQuantity= listQuantity;

    }
    /*public Basket(int user_id,Product p){
        this.user_id = user_id;
        this.listProduct.add(p);

    }*/
    public Basket(){};
    public List<Product> getListProduct(){
        return this.listProduct;
    }

    public List<Integer> getListQuantity() {
        return listQuantity;
    }

    public int getNbProducts(){
        return this.listProduct.size();
    }


    public int calculTotal(){
        int total = 0;
        for(int i =0;i< listProduct.size();i++){
            total += listProduct.get(i).getPrice();

        }
                return total;
    }

    public List<Product> deleteAll(){
        this.listProduct = new ArrayList<>() ;
        return this.listProduct;
    }
    public int getProductId(){

        return this.listProduct.get(listProduct.size()-1).getId();
    }
    public int getQuantity(){

        return this.listQuantity.get(listQuantity.size()-1);
    }

    public int getUser_id(){
        return this.user_id;
    }

    public void addProduct(Product p,Integer quantity){
        System.out.println(Main.connectedUser.getId());

            this.listProduct.add(p);
            this.listQuantity.add(quantity);
            System.out.println(listProduct);

            System.out.println(listQuantity);

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
