package com.foirfoot.model;

import com.foirfoot.dao.AbstractDAOFactory;
import com.foirfoot.dao.ClubDAOMySQL;
import com.foirfoot.dao.MySQLDAOFactory;
import com.foirfoot.dao.UserDAOMySQL;
import com.foirfoot.model.user.Role;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import exceptions.ClubNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

public class Facade {

    private final AbstractDAOFactory<?> abstractDAOFactory = new MySQLDAOFactory();

    public User login(String email, String password) throws UserNotFoundException, WrongPasswordException, ClubNotFoundException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Optional<User> optionalUser = userDAOMySQL.getUserByEmail(email);
        User userFoundInDatabase = optionalUser.orElseThrow(UserNotFoundException::new);
        userFoundInDatabase.setClub(((ClubDAOMySQL) this.abstractDAOFactory.create("Club")).get(userFoundInDatabase.getClubId()).orElseThrow(ClubNotFoundException::new));
        return userFoundInDatabase.login(password);
    }

    public void register(String name, String firstName, String email, String password) throws SQLIntegrityConstraintViolationException{
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        User user = new User(email, password, name, firstName, RoleName.classic, -1, -1, false);
        userDAOMySQL.save(user);
    }
}
