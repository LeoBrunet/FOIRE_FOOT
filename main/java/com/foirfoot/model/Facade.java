package com.foirfoot.model;

import com.foirfoot.dao.AbstractDAOFactory;
import com.foirfoot.dao.MySQLDAOFactory;
import com.foirfoot.dao.UserDAOMySQL;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;

import java.util.Optional;

public class Facade {

    private final AbstractDAOFactory<?> abstractDAOFactory = new MySQLDAOFactory();

    public User login(String email_address, String password) throws UserNotFoundException, WrongPasswordException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Optional<User> optionalUser = userDAOMySQL.getUserByEmail(email_address);
        User userFoundInDatabase = optionalUser.orElseThrow(UserNotFoundException::new);
        return userFoundInDatabase.login(password);
    }
}
