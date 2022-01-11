package com.foirfoot.model.user;

import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.team.Team;
import exceptions.WrongPasswordException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String firstname;
    private String tel;
    private File picture;
    private RoleName roleName;
    private Role role;
    private Basket basket;

    public User(String email, String password, String name, String firstname, RoleName roleName, int club, int team, boolean isClubCreator,Basket basket) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        if (roleName == RoleName.player){
            this.role = new Player(club, team, isClubCreator);
        } else if (roleName == RoleName.coach) {
            this.role = new Coach(club, team, isClubCreator);
        } else{
            this.role = new ClassicUser(club, -1, isClubCreator);
        }
        this.basket = basket;
    }

    public User(int id, String email, String password, String name, String firstname, RoleName roleName, int club, int team, boolean isClubCreator,Basket basket) {
        this(email, password, name, firstname, roleName, club, team, isClubCreator,basket);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public void setTeam(Team team){
        this.role.setTeam(team);
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

    public Team getTeam() {
        return this.role.getTeam();
    }

    public Club getClub() {
        return this.role.getClub();
    }

    public void setClub(Club club) {
        this.role.setClub(club);
    }

    public boolean isClubCreator() {
        return this.role.isClubCreator();
    }

    public void setIsClubCreator(boolean isClubCreator) {
        this.role.setClubCreator(isClubCreator);
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public Basket getBasket() {
        return basket;
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", tel='" + tel + '\'' +
                ", picture=" + picture +
                ", role=" + role +
                '}';
    }
}
