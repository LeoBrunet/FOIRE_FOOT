package com.foirfoot.model.shop;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.User;

public class Transaction {
    private Club club;
    private User user;
    private Basket basket;

    public Transaction(Club club,User user,Basket basket){
        this.club = club;
        this.user = user;
        this.basket = basket;
    }
}
