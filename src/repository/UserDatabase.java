package repository;

import config.DatabaseConfig;
import entity.User;

import java.sql.*;

public class UserDatabase {

    private Connection conn = null;
    public UserDatabase(Connection conn){
     this.conn = conn;
    }

    public void registerUser(User user) throws Exception {
        String sql = "insert into user values(?,?,?)";

            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
          int result =  ps.executeUpdate();
          if(result==0){
              throw new Exception("User was not inserted please try again");
          }else{
              System.out.println("Registered Successfully");
          }
    }

    public void login(String username, String password) throws Exception {
        String sql = "select password from user where username=? or email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               String pwd = rs.getString("password");
               if(!pwd.equals(password)){
                   throw new Exception("Credentials are Wrong please try again");
               }else{
                   System.out.println("Welcome to our Management App");
               }
            }else{
                throw new Exception("No such user exists please register first");
            }
    }


    public void updateUser(User user) throws Exception {
        String sql = "update user set username=? and email=? and password=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3,user.getPassword());
            if(ps.executeUpdate()==0){
                throw new Exception("Error occured while updating user data for username "+user.getUsername());
            }else{
                System.out.println("User data updated successfully");
            }

    }

    public User getUserByUsername(String username) throws Exception {
        String sql = "select password, email from user where username=?";
        PreparedStatement ps = null;

            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setUsername(username);
                return user;
            }else{
                throw new Exception("No user exists for username "+ username);
            }


    }

}
