package org.example.DB_classPost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostSQL_DB {
    public Connection getConnection() throws SQLException {
        // Conexion PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/notasdbpostgres";
        String user = "postgres";
        String password = "1234";

        //
        return DriverManager.getConnection(url, user, password);
    }

}