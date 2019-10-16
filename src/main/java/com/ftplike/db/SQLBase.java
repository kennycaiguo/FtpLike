package com.ftplike.db;

import com.ftplike.model.User;

import java.io.*;
import java.sql.*;

public class SQLBase implements DBase {
    private static String url = "jdbc:sqlite:usersbase.db";

    private void init() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "login text NOT NULL," +
                    "email text NOT NULL," +
                    "password text NOT NULL," +
                    "homedir blob NOT NULL);");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean containsLogin(String login) {
        init();
        String sql = "SELECT COUNT(*) FROM users WHERE login = '" + login + "';";

        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            int i = rs.getInt(1);
            return i != 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Boolean containsMail(String email) {
        init();
        String sql = "SELECT COUNT(*) FROM users WHERE email = '" + email + "';";

        try(Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            int i = rs.getInt(1);
            return i != 0;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void insertUser(String login, String email, String password, File homedir) {
        String sql = "INSERT INTO users VALUES(?, ?, ?, ?);";

        try(Connection conn = DriverManager.getConnection(url);
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
        String sql = "SELECT * FROM users WHERE login = '" + login + "' OR email = '" + login + "';";

        try(Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){

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