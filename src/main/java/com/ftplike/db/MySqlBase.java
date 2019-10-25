package com.ftplike.db;

import com.ftplike.model.User;
import com.ftplike.service.LoggerService;

import java.sql.*;

public class MySqlBase implements DBase {
    private String url;
    private String userbase;
    private String passbase;
    private String basename;
    private String tablename;

    public MySqlBase(String user, String pass, String basename, String url, String tablename) {
        this.userbase = user;
        this.passbase = pass;
        this.basename = basename;
        this.tablename = tablename;
        this.url = url;

        String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (" +
                "login VARCHAR(15) NOT NULL, " +
                "email VARCHAR(50) NOT NULL, " +
                "password VARCHAR(50) NOT NULL, " +
                "homedir TEXT NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url + basename, userbase, passbase);
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
    }

    @Override
    public Boolean containsLogin(String login) {
        String sql = "SELECT COUNT(login) FROM " + tablename + " WHERE login = ? ;";

        try (Connection conn = DriverManager.getConnection(url + basename, userbase, passbase);
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, login);

            try (ResultSet rs = st.executeQuery()) {
                rs.next();
                int i = rs.getInt(1);
                return i != 0;
            }
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }

        return null;
    }

    @Override
    public Boolean containsMail(String email) {
        String sql = "SELECT COUNT(email) FROM " + tablename + " WHERE email = ? ;";

        try (Connection conn = DriverManager.getConnection(url + basename, userbase, passbase);
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, email);

            try (ResultSet rs = st.executeQuery()) {
                rs.next();
                int i = rs.getInt(1);
                return i != 0;
            }
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }

        return null;
    }

    @Override
    public void insertUser(String login, String email, String password, String homedir) {
        String sql = "INSERT INTO " + tablename + " VALUES(?, ?, ?, ?);";

        try (Connection conn = DriverManager.getConnection(url + basename, userbase, passbase);
             PreparedStatement pStatement = conn.prepareStatement(sql);
        ){
            pStatement.setString(1, login);
            pStatement.setString(2, email);
            pStatement.setString(3, password);
            pStatement.setString(4, homedir);

            pStatement.executeUpdate();
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
    }

    @Override
    public User getUser(String login) {
        String sql = "SELECT * FROM " + tablename + " WHERE login = ? OR email = ? ;";

        try (Connection conn = DriverManager.getConnection(url + basename, userbase, passbase);
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, login);
            st.setString(2, login);

            try (ResultSet rs = st.executeQuery()) {
                rs.next();

                String username = rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String homedir = rs.getString(4);

                return new User(username, email, password, homedir);
            }
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
        return null;
    }
}
