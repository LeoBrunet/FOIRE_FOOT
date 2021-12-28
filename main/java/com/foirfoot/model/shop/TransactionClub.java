package com.foirfoot.model.shop;

import com.foirfoot.model.club.Club;

public class TransactionClub {
    private Club club;
    private Transaction transaction;

    public TransactionClub(Club club,Transaction transaction){
        this.club = club;
        this.transaction = transaction;
    }

    public Club getClub() {
        return club;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
