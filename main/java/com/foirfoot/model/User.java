package com.foirfoot.model;

import exceptions.WrongPasswordException;

import java.io.File;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;

public class User implements Role{
    private int id;
    private String name;
    private String firstname;
    private String email_address;
    private String password;
    private String tel;
    private File picture;

    public User(int id, String email_address, String password) {
        this.id = id;
        this.email_address = email_address;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email_address) {
        this.email_address = email_address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email_address;
    }

    public String getPassword() {
        return password;
    }

    public User login(String password) throws WrongPasswordException {
        if(comparePassword(password)) {
            return this;
        } else {
            throw new WrongPasswordException("Password provided mismatch.");
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
        return Objects.equals(email_address, user.email_address) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email_address, password);
    }

    @Override
    public String toString() {
        return "com.foirfoot.model.User{" +
                "email_address='" + email_address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
