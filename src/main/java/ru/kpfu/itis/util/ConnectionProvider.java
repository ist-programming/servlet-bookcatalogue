package ru.kpfu.itis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbException {
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection con;

    private ConnectionProvider() throws DbException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java3_books", "", "");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DbException("Can't connect to DB.", e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
