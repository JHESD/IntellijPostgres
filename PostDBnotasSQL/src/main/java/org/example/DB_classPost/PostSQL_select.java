package org.example.DB_classPost;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostSQL_select {
    private Connection connection;

    public PostSQL_select(Connection connection) {
        this.connection = connection;
    }

    public ResultSet executeSelect(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}