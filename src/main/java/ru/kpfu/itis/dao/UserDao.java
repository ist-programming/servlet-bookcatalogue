package ru.kpfu.itis.dao;

import ru.kpfu.itis.entity.Book;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private ConnectionProvider connectionProvider;

    public UserDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws DbException {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM user WHERE email = ? AND password = MD5(?)");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet result = st.executeQuery();
            boolean hasOne = result.next();
            if(hasOne) {
                return new User(
                        result.getInt("id"),
                        result.getString("email"),
                        null,
                        result.getString("role")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get user from db.", e);
        }
    }
}
