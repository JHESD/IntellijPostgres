package org.example.DaoClass_Sql;

import org.example.DB_tableClass.Materias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriasDAO {

    private Connection connection;
    public MateriasDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarMateria(Materias materia) throws SQLException {
        String query = "INSERT INTO materias (mat_id, mat_nom, mat_cdt) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, materia.getId());
        statement.setString(2, materia.getNombre());
        statement.setInt(3, materia.getCreditos());
        statement.executeUpdate();
    }
    public List<Materias> obtenerMaterias() throws SQLException {
        List<Materias> materias = new ArrayList<>();
        String query = "SELECT * FROM materias";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("mat_id");
            String nombre = resultSet.getString("mat_nom");
            int creditos = resultSet.getInt("mat_cdt");
            Materias materia = new Materias(id, nombre, creditos);
            materias.add(materia);
        }
        resultSet.close();
        return materias;
    }
}