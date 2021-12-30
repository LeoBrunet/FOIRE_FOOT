package com.foirfoot.model;

import com.foirfoot.dao.AbstractDAOFactory;
import com.foirfoot.dao.ClubDAOMySQL;
import com.foirfoot.dao.MySQLDAOFactory;
import com.foirfoot.dao.UserDAOMySQL;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.user.Player;
import com.foirfoot.model.user.Role;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import exceptions.ClubNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;

import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Facade {

    private final AbstractDAOFactory<?> abstractDAOFactory = new MySQLDAOFactory();

    public User login(String email, String password) throws UserNotFoundException, WrongPasswordException, ClubNotFoundException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Optional<User> optionalUser = userDAOMySQL.getUserByEmail(email);
        User userFoundInDatabase = optionalUser.orElseThrow(UserNotFoundException::new);
        Club club = null;
        try {
            club = ((ClubDAOMySQL) this.abstractDAOFactory.create("Club")).get(userFoundInDatabase.getClub().getId()).orElseThrow(ClubNotFoundException::new);
        } catch (ClubNotFoundException e){
            e.printStackTrace();
        }
        userFoundInDatabase.setClub(club);
        return userFoundInDatabase.login(password);
    }

    public void register(String name, String firstName, String email, String password) throws SQLIntegrityConstraintViolationException{
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        User user = new User(email, password, name, firstName, RoleName.classic, -1, -1, false);
        userDAOMySQL.save(user);
    }

    public Club createClub(String name, String address, String phoneNumber, String website, User creator, String localPathToImage, String imageName, InputStream imageIS) throws SQLIntegrityConstraintViolationException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        UserDAOMySQL userDAOMySQL  = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Club club = new Club(name, address, phoneNumber, website, creator, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), imageName, imageIS);
        clubDAOMySQL.save(club, localPathToImage);
        creator.setClub(club);
        creator.setIsClubCreator(true);
        userDAOMySQL.update(creator, null);
        return club;
    }

    public List<Club> searchClubs(String clubName){
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        return clubDAOMySQL.searchClub(clubName);
    }

    public List<User> searchUsers(String userName){
        UserDAOMySQL userDAOMySQL  = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        return userDAOMySQL.searchUsers(userName);
    }
}
