package service;

import entity.User;
import repository.UserDatabase;

import java.sql.Connection;

public class UserService {

    private Connection conn = null;
    UserDatabase database = null;
    public UserService(Connection conn){
        this.conn = conn;
        database = new UserDatabase(this.conn);
    }

    public void loginUser(String emailOrUsername , String password){
        try {
            database.login(emailOrUsername,password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void registerUser(User user){
        try {
            database.registerUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
