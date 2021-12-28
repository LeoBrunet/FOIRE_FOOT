package com.foirfoot.model.shop;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;

public class Transaction {
    private TransactionClub transactionClub;
    private User user;
    private Basket basket;
    private Address address;
    private PaymentType payment;

    public Transaction(TransactionClub transactionClub,User user,Basket basket,Address address,PaymentType payment){
        this.transactionClub = transactionClub;
        this.user = user;
        this.basket = basket;
        this.address = address;
        this.payment = payment;
    }

    public void cancelTransaction(){

    }
    public void validTransaction(){

    }
}
