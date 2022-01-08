package com.foirfoot.model.shop;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;

public class Transaction {
    //private TransactionClub transactionClub;
    private int id;
    private int user;
    private Basket basket;
    //mettre en objet Address juste pour tester
    private String address;
    private String city;
    private String country;
    private Object payment;
    private int nbProducts;
    private int total;

    public Transaction(int id,int user,Basket basket,String address,String city, String country,Object payment, int nbProducts, int total){
        //this.transactionClub = transactionClub;
        this.id = id;
        this.user = user;
        this.basket = basket;
        this.address = address;
        this.city = city;
        this.country = country;
        this.payment = payment;
        this.nbProducts = nbProducts;
        this.total = total;
    }
    public Transaction(int user,Basket basket,String address,String city, String country,Object payment, int nbProducts, int total){
        //this.transactionClub = transactionClub;

        this.user = user;
        this.basket = basket;
        this.address = address;
        this.city = city;
        this.country = country;
        this.payment = payment;
        this.nbProducts = nbProducts;
        this.total = total;
    }

    /*public int getUserId() {
        return user.getId();
    }*/

    public int getUser() {
        return user;
    }

    public int getBasketId() {
        return basket.getUser_id();
    }

    public String getaddress() {
        return address;
    }

    public String getAddress() {
        return address;
    }

    public int getNbProducts() {
        return nbProducts;
    }

    public int getTotal() {
        return total;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Object getPayment() {
        return payment;
    }

    public int getId() {
        return id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void cancelTransaction(){

    }
    public void validTransaction(){

    }

    public void setId(int id) {
        this.id = id;
    }
}
