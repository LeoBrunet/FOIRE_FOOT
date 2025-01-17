package com.foirfoot.model.club;

import com.foirfoot.model.shop.Product;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.User;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

public class Club {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String website;
    private User creator;
    private List<User> players;
    private List<User> coachs;
    private List<Team> teams;
    private InputStream imageIS;
    private String imageName;

    public Club(int id){
        this.id = id;
    }

    public Club(int id, String name, String address, String phoneNumber, String website, User creator, List<User> players, List<User> coachs, List<Team> teams, String imageName, InputStream imageIS) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.creator = creator;
        this.players = players;
        this.coachs = coachs;
        this.teams = teams;
        this.imageIS = imageIS;
        this.imageName = imageName;
    }

    public Club(String name, String address, String phoneNumber, String website, User creator, List<User> players, List<User> coachs, List<Team> teams, String imageName, InputStream imageIS) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.creator = creator;
        this.players = players;
        this.coachs = coachs;
        this.teams = teams;
        this.imageIS = imageIS;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public List<User> getPlayers() {
        return players;
    }

    public List<User> getCoaches() {
        return coachs;
    }

    public User getCreator() {
        return creator;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public InputStream getImageIS() {
        return imageIS;
    }

    public String getImageName() {
        return imageName;
    }

    public List<Team> getTeams() { return teams; }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getCoachs() {
        return coachs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setImageIS(InputStream imageIS) {
        this.imageIS = imageIS;
    }

    public void addTeam(Team team){
        getTeams().add(team);
    }
    @Override
    public String toString() {
        return "Club{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", website='" + website + '\'' +
                ", creator=" + creator +
                ", players=" + players +
                ", coachs=" + coachs +
                //", teams=" + teams +
                ", imageIS=" + imageIS +
                ", imageName='" + imageName + '\'' +
                '}';
    }

    public String toStringBis() {
        return "Club{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", website='" + website + '\'' +
                ", imageIS=" + imageIS + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
    public void addProductToShop(Product p){


    }
    public void removeproductFromShop(Product p){


    }
}
