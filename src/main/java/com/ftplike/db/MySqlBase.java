package com.ftplike.db;

import com.ftplike.model.User;

import javax.swing.*;
import java.io.*;
import java.sql.*;

public class MySqlBase implements DBase {
    private String ubasae = "root";
    private String pbase = "rootroot";
    private String url = "jdbc:mysql://localhost/";
    private String basename = "usersftp";

//    public MySqlBase(){
//        try(Connection conn = DriverManager.getConnection(url + basename, ubasae, pbase);
//            Statement st = conn.createStatement()){
//            String sql = "CREATE TABLE userlist (" +
//                    "login TEXT NOT NULL, " +
//                    "email TEXT NOT NULL, " +
//                    "password TEXT NOT NULL, " +
//                    "homedir BLOB NOT NULL);";
//
//            st.execute(sql);
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public Boolean containsLogin(String login) {
        String sql = "SELECT COUNT(login) FROM userlist WHERE login = '" + login + "';";

        try(Connection conn = DriverManager.getConnection(url + basename, "root", "rootroot");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            rs.next();
            int i = rs.getInt(1);
            return i != 0;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public Boolean containsMail(String email) {
        String sql = "SELECT COUNT(email) FROM userlist WHERE email = '" + email + "';";

        try(Connection conn = DriverManager.getConnection(url + basename, "root", "rootroot");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            rs.next();
            int i = rs.getInt(1);
            return i != 0;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public void insertUser(String login, String email, String password, File homedir) {
        String sql = "INSERT INTO userlist VALUES(?, ?, ?, ?);";

        try(Connection conn = DriverManager.getConnection(url + basename, ubasae, pbase);
            PreparedStatement pStatement = conn.prepareStatement(sql);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){

            oos.writeObject(homedir);

            pStatement.setString(1, login);
            pStatement.setString(2, email);
            pStatement.setString(3, password);
            pStatement.setBytes(4, baos.toByteArray());

            pStatement.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User getUser(String login) {
        String sql = "SELECT * FROM userlist WHERE login = '" + login + "' OR email = '" + login + "';";

        try(Connection conn = DriverManager.getConnection(url + basename, ubasae, pbase);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            rs.next();

            String username = rs.getString(1);
            String email = rs.getString(2);
            String password = rs.getString(3);

            ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(4));
            ObjectInputStream ois = new ObjectInputStream(bais);

            File homedir = (File)ois.readObject();

            bais.close();
            ois.close();

            return new User(username, email, password, homedir);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
