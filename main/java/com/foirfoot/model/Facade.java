package com.foirfoot.model;

import com.foirfoot.dao.*;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.Player;
import com.foirfoot.model.shop.*;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Main;
import exceptions.ClubNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.TeamNotFoundException;
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

    public User login(String email, String password) throws UserNotFoundException, WrongPasswordException, ClubNotFoundException, ProductNotFoundException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Optional<User> optionalUser = userDAOMySQL.getUserByEmail(email);
        User userFoundInDatabase = optionalUser.orElseThrow(UserNotFoundException::new);
        Club club = null;
        try {
            club = ((ClubDAOMySQL) this.abstractDAOFactory.create("Club")).get(userFoundInDatabase.getClub().getId()).orElseThrow(ClubNotFoundException::new);
        } catch (ClubNotFoundException | ProductNotFoundException e) {
            e.printStackTrace();
        }
        userFoundInDatabase.setClub(club);
        return userFoundInDatabase.login(password);
    }

    public void register(String name, String firstName, String email, String password) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        User user = new User(email, password, name, firstName, RoleName.classic, -1, -1, false, new Basket());
        userDAOMySQL.save(user);
    }

    public void createProduct(String name, String desc, int price, String stock, int clubId, String localPathToImage, String imageName, InputStream imageIS) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        Product product = new Product(name, desc, price, stock, Main.connectedUser.getClub().getId(), imageName, imageIS);
        System.out.println(product);
        productDAOMySQL.save(product, localPathToImage);
    }

    /*public Address createAddress(  String address,String city, String country) throws SQLIntegrityConstraintViolationException {
        AddressDAOMySQL addressDAOMySQL = (AddressDAOMySQL) this.abstractDAOFactory.create("Adress");
        Address adress = new Address( address,city,country);
        System.out.println(adress.toString());
        addressDAOMySQL.save(adress);
        return adress;

    }*/

    public List<Product> getAllProductsOfClub(int clubId) throws ProductNotFoundException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        List<Optional<Product>> products = productDAOMySQL.getAllProductsOfClub(clubId);
        List<Product> returnedProducts = new ArrayList<>();
        for (Optional<Product> product : products) {
            returnedProducts.add(product.orElseThrow(ProductNotFoundException::new));
        }
        return returnedProducts;
    }


    public Club createClub(String name, String address, String phoneNumber, String website, User creator, String localPathToImage, String imageName, InputStream imageIS) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        Club club = new Club(name, address, phoneNumber, website, creator, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), imageName, imageIS);
        clubDAOMySQL.save(club, localPathToImage);
        creator.setClub(club);
        creator.setIsClubCreator(true);
        userDAOMySQL.update(creator);
        return club;
    }

    public Club getClub(int clubId) throws ClubNotFoundException, ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        return clubDAOMySQL.get(clubId).orElseThrow(ClubNotFoundException::new);
    }

    public Club updateClubAndClubImage(String name, String address, String phoneNumber, String website, User creator, String localPathToImage, String imageName, InputStream imageIS, Club originalClub) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        originalClub.setName(name);
        originalClub.setAddress(address);
        originalClub.setPhoneNumber(phoneNumber);
        originalClub.setWebsite(website);
        originalClub.setImageName(imageName);
        originalClub.setImageIS(imageIS);
        clubDAOMySQL.update(originalClub, localPathToImage);
        //TODO Vérifier si nécessaire
        //creator.setClub(originalClub);
        return originalClub;
    }

    public Club updateClub(String name, String address, String phoneNumber, String website, User creator, Club originalClub) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        originalClub.setName(name);
        originalClub.setAddress(address);
        originalClub.setPhoneNumber(phoneNumber);
        originalClub.setWebsite(website);
        clubDAOMySQL.update(originalClub);
        //TODO Vérifier si nécessaire
        //creator.setClub(originalClub);
        return originalClub;
    }

    public Team createTeam(Object category, Object type, Club club) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        TeamDAOMySQL teamDAOMySQL = (TeamDAOMySQL) this.abstractDAOFactory.create("Team");
        Team team = new Team((String) category, (String) type, club);
        teamDAOMySQL.save(team);
        return team;
    }

    public Team getTeam(int teamId) throws TeamNotFoundException, ProductNotFoundException {
        Optional<Team> team = null;
        team = ((TeamDAOMySQL) this.abstractDAOFactory.create("Team")).get(teamId);
        System.out.println(team);
        return team.orElseThrow(TeamNotFoundException::new);
    }

    public List<Club> searchClubs(String clubName) throws ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        return clubDAOMySQL.searchClub(clubName);
    }

    public List<User> searchUsers(String userName) throws ProductNotFoundException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        return userDAOMySQL.searchUsers(userName);
    }

    public void addProduct(Basket basket, Product p) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        BasketDAOMySQL basketDAOMySQL = (BasketDAOMySQL) this.abstractDAOFactory.create("Basket");
        //basket.addProduct(p);
        System.out.println(Main.connectedUser.getId());
        basketDAOMySQL.save(basket);
    }

    public void deleteProduct(Product product) throws ProductNotFoundException, SQLIntegrityConstraintViolationException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        productDAOMySQL.delete(product);


    }

    public Transaction createTransaction(int user, Basket basket, String address, String city, String country, Object payment, int nbProducts, int total) throws ProductNotFoundException, SQLIntegrityConstraintViolationException {


        TransactionDAOMySQL transactionDAOMySQL = (TransactionDAOMySQL) this.abstractDAOFactory.create("Transaction");
        Transaction transaction = new Transaction(user, basket, address, city, country, payment, nbProducts, total);
        transactionDAOMySQL.save(transaction);
        return transaction;

    }

    public void deleteAll() throws ProductNotFoundException {
        BasketDAOMySQL basketDAOMySQL = (BasketDAOMySQL) this.abstractDAOFactory.create("Basket");
        basketDAOMySQL.deleteAll();


    }

    public List<Transaction> getAllTransactions(int user_id) throws ProductNotFoundException {
        TransactionDAOMySQL transactionDAOMySQL = (TransactionDAOMySQL) this.abstractDAOFactory.create("Transaction");
        return transactionDAOMySQL.getAllTransactionsPerUser(user_id);


    }


}
