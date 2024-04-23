package org.example.DB_classPost;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostSQL_insert {
    private Connection connection;

    public PostSQL_insert(Connection connection) {
        this.connection = connection;
    }

    public void executeInsert(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}