package com.foirfoot.model.type;

public class Type {
    private int id;
    private String name;

    public Type(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Type(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }
}
