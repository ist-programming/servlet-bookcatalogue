package ru.kpfu.itis.dao;

import ru.kpfu.itis.entity.Book;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private ConnectionProvider connectionProvider;

    public BookDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public int getCount() throws DbException {
        try {
            Statement st = this.connectionProvider.getCon().createStatement();
            ResultSet result = st.executeQuery("SELECT COUNT(id) AS cnt FROM book");
            result.next();
            return result.getInt("cnt");
        } catch (SQLException e) {
            throw new DbException("Can't get count of books in DB.", e);
        }
    }

    public List<Book> getPage(int page, int limit) throws DbException {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM book LIMIT ?, ?");
            st.setInt(1, limit * (page - 1));
            st.setInt(2, limit);
            ResultSet result = st.executeQuery();
            List<Book> books = new ArrayList<>();
            while (result.next()) {
                Book book = new Book(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getInt("issued")
                );
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DbException("Can't get book list from db.", e);
        }
    }

    public Book getDetail(int id) throws DbException {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM book WHERE id = ?");
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            boolean hasOne = result.next();
            if(hasOne) {
                return new Book(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getInt("issued")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get book details from db.", e);
        }
    }
}
