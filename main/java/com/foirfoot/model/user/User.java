package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import exceptions.WrongPasswordException;

import java.io.File;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String firstname;
    private String tel;
    private File picture;
    private Role role;

    /*public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String firstname, String email, String password) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }*/

    public User(String name, String firstname, String email, String password, RoleName roleName, Club club, Team team, boolean isClubCreator) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        if (roleName == RoleName.player){
            this.role = new Player(club, team, isClubCreator);
        } else if (roleName == RoleName.coach) {
            this.role = new Coach(club, team, isClubCreator);
        } else{
            this.role = null;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstname;
    }

    public User login(String password) throws WrongPasswordException {
        if(comparePassword(password)) {
            return this;
        } else {
            throw new WrongPasswordException();
        }
    }

    private boolean comparePassword(String password){
        return DigestUtils.sha1Hex(password).equals(this.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
