package com.foirfoot.model;

import com.foirfoot.dao.*;
import com.foirfoot.model.club.Club;
import com.foirfoot.model.result.Result;
import com.foirfoot.model.shop.Basket;
import com.foirfoot.model.shop.Product;
import com.foirfoot.model.shop.Transaction;
import com.foirfoot.model.team.Team;
import com.foirfoot.model.user.RoleName;
import com.foirfoot.model.user.User;
import com.foirfoot.view.Main;
import exceptions.*;

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
        } catch (ClubNotFoundException e) {
            e.printStackTrace();
        }
        userFoundInDatabase.setClub(club);
        return userFoundInDatabase.login(password);
    }

    public void register(String name, String firstName, String email, String password) throws SQLIntegrityConstraintViolationException {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        User user = new User(email, password, name, firstName, RoleName.classic, -1, -1, false, new Basket());
        userDAOMySQL.save(user);
    }

    public void createProduct(String name, Object category, String desc, int price, String stock, int clubId, String localPathToImage, String imageName, InputStream imageIS) throws SQLIntegrityConstraintViolationException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        Product product = new Product(name, category, desc, price, stock, clubId, imageName, imageIS);
        productDAOMySQL.save(product, localPathToImage);
    }

    public List<Club> getAllClubs() throws ClubNotFoundException {
        List<Club> clubs = new ArrayList<>();
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        for(Optional<Club> c : clubDAOMySQL.getAll()){
            clubs.add(c.orElseThrow(ClubNotFoundException::new));
        }
        return clubs;
    }

    public List<Team> getAllTeams() throws TeamNotFoundException {
        List<Team> teams = new ArrayList<>();
        TeamDAOMySQL teamDAOMySQL = (TeamDAOMySQL) this.abstractDAOFactory.create("Team");
        for(Optional<Team> t : teamDAOMySQL.getAll()){
            teams.add(t.orElseThrow(TeamNotFoundException::new));
        }
        return teams;
    }

    public List<Product> getAllProductsOfClub(int clubId) throws ProductNotFoundException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        List<Optional<Product>> products = productDAOMySQL.getAllProductsOfClub(clubId);
        List<Product> returnedProducts = new ArrayList<>();
        for (Optional<Product> product : products) {
            returnedProducts.add(product.orElseThrow(ProductNotFoundException::new));
        }
        return returnedProducts;
    }


    public Club createClub(String name, String address, String phoneNumber, String website, User creator, String localPathToImage, String imageName, InputStream imageIS) throws SQLIntegrityConstraintViolationException {
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

    public void updateClubAndClubImage(String name, String address, String phoneNumber, String website, String localPathToImage, String imageName, InputStream imageIS, Club originalClub) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        originalClub.setName(name);
        originalClub.setAddress(address);
        originalClub.setPhoneNumber(phoneNumber);
        originalClub.setWebsite(website);
        originalClub.setImageName(imageName);
        originalClub.setImageIS(imageIS);
        clubDAOMySQL.update(originalClub, localPathToImage);
    }

    public void updateClub(String name, String address, String phoneNumber, String website, Club originalClub) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        originalClub.setName(name);
        originalClub.setAddress(address);
        originalClub.setPhoneNumber(phoneNumber);
        originalClub.setWebsite(website);
        clubDAOMySQL.update(originalClub);
    }

    public Team createTeam(Object category, Object type, Club club) throws SQLIntegrityConstraintViolationException {
        TeamDAOMySQL teamDAOMySQL = (TeamDAOMySQL) this.abstractDAOFactory.create("Team");
        Team team = new Team((String) category, (String) type, club);
        teamDAOMySQL.save(team);
        return team;
    }

    public Team getTeam(int teamId) throws TeamNotFoundException, ProductNotFoundException {
        Optional<Team> team = ((TeamDAOMySQL) this.abstractDAOFactory.create("Team")).get(teamId);
        System.out.println(team);
        return team.orElseThrow(TeamNotFoundException::new);
    }

    public Result createResult(int score_ht, int score_ot, Team home_team, Team outside_team) throws SQLIntegrityConstraintViolationException {
        ResultDAOMySQL resultDAOMySQL = (ResultDAOMySQL) this.abstractDAOFactory.create("Result");
        Result result = new Result(home_team, outside_team, score_ht, score_ot);
        resultDAOMySQL.save(result);
        return result;
    }

    public List<Result> getResults(int teamId) {
        return ((ResultDAOMySQL) this.abstractDAOFactory.create("Result")).getAllResultsOfTeam(teamId);
    }

    public List<Club> searchClubs(String clubName) {
        ClubDAOMySQL clubDAOMySQL = (ClubDAOMySQL) this.abstractDAOFactory.create("Club");
        return clubDAOMySQL.searchClub(clubName);
    }

    public List<User> searchUsers(String userName) {
        UserDAOMySQL userDAOMySQL = (UserDAOMySQL) this.abstractDAOFactory.create("User");
        return userDAOMySQL.searchUsers(userName);
    }

    public void addProduct(Basket basket, Product p, int quantity) throws SQLIntegrityConstraintViolationException, ProductNotFoundException {
        BasketDAOMySQL basketDAOMySQL = (BasketDAOMySQL) this.abstractDAOFactory.create("Basket");
        //basket.addProduct(p);
        basketDAOMySQL.save(basket);
    }

    public void deleteProduct(Product product) throws ProductNotFoundException, SQLIntegrityConstraintViolationException {
        ProductDAOMySQL productDAOMySQL = (ProductDAOMySQL) this.abstractDAOFactory.create("Product");
        productDAOMySQL.delete(product);


    }

    public Transaction createTransaction(int user, Basket basket, String address, String city, String country, Object payment, int nbProducts, int total) throws SQLIntegrityConstraintViolationException {


        TransactionDAOMySQL transactionDAOMySQL = (TransactionDAOMySQL) this.abstractDAOFactory.create("Transaction");
        Transaction transaction = new Transaction(user, basket, address, city, country, payment, nbProducts, total);
        transactionDAOMySQL.save(transaction);
        return transaction;

    }

    public void deleteAll() {
        BasketDAOMySQL basketDAOMySQL = (BasketDAOMySQL) this.abstractDAOFactory.create("Basket");
        basketDAOMySQL.deleteAll();


    }

    public List<Transaction> getAllTransactions(int user_id) {
        TransactionDAOMySQL transactionDAOMySQL = (TransactionDAOMySQL) this.abstractDAOFactory.create("Transaction");
        return transactionDAOMySQL.getAllTransactionsPerUser(user_id);


    }

    public int getQuantityOfProduct(int productId) {
        BasketDAOMySQL basketDAOMySQL = (BasketDAOMySQL) this.abstractDAOFactory.create("Basket");
        System.out.println("hey");
        return basketDAOMySQL.getQuantityOfProduct(productId);
    }


}
