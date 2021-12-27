package com.foirfoot.model.team;

import java.util.Objects;

public class Team {
    private int id;
    private String name;

    public Team(int id) {
        this.id = id;
    }

    public Team(int id, String name){
        this(id);
        this.name = name;
    }

    public Team(String name) {
        this.name = name;
    }

}
