package com.foirfoot.model.shop;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;

public class Transaction {
    //private TransactionClub transactionClub;
    private int id;
    private User user;
    private Basket basket;
    //mettre en objet Address juste pour tester
    private String address;
    private String city;
    private String country;
    private PaymentType payment;

    public Transaction(int id,User user,Basket basket,String address,String city, String country,PaymentType payment){
        //this.transactionClub = transactionClub;
        this.id = id;
        this.user = user;
        this.basket = basket;
        this.address = address;
        this.city = city;
        this.country = country;
        this.payment = payment;
    }
    public Transaction(User user,Basket basket,String address,String city, String country,PaymentType payment){
        //this.transactionClub = transactionClub;

        this.user = user;
        this.basket = basket;
        this.address = address;
        this.city = city;
        this.country = country;
        this.payment = payment;
    }

    public void cancelTransaction(){

    }
    public void validTransaction(){

    }
}
