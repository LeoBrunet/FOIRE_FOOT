package com.foirfoot.model;

import com.foirfoot.dao.*;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.shop.*;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Main;
import exceptions.ClubNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.WrongPasswordException;

import java.io.InputStream;
import java.net.ProtocolException;
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
        User user = new User(email, password, name, firstName, RoleName.classic, -1, -1, false,new Basket());
        userDAOMySQL.save(user);
    }
    public Product createProduct(String name, String desc,String price, String stock,int clubId) throws SQLIntegrityConstraintViolationException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        Product product = new Product(name, desc, price, stock, Main.connectedUser.getClub().getId());
        System.out.println(Main.connectedUser.getClub().toString());
        System.out.println(product.toString());
        productDAOMySQL.save(product);
        return product;

    }

    /*public Address createAddress(  String address,String city, String country) throws SQLIntegrityConstraintViolationException {
        AddressDAOMySQL addressDAOMySQL = (AddressDAOMySQL) this.abstractDAOFactory.create("Adress");
        Address adress = new Address( address,city,country);
        System.out.println(adress.toString());
        addressDAOMySQL.save(adress);
        return adress;

    }*/

    public List<Product> getAllProducts() throws ProductNotFoundException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        List<Optional<Product>> products = productDAOMySQL.getAll();
        List<Product> returnedProducts = new ArrayList<>();
        for (Optional<Product> product : products) {
            returnedProducts.add(product.orElseThrow(ProductNotFoundException::new));
        }
        return returnedProducts;
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
}
