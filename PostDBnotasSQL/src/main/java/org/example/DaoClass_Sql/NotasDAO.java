package org.example.DaoClass_Sql;

import org.example.DB_tableClass.Notas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotasDAO {
    private Connection connection;
    public NotasDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarNota(Notas nota) throws SQLException {
        String query = "INSERT INTO notas (notas_id, estudiante_id, materia_id, valor_nota) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, nota.getId());
        statement.setInt(2, nota.getEstudianteId());
        statement.setInt(3, nota.getMateriaId());
        statement.setDouble(4, nota.getValorNota());
        statement.executeUpdate();
    }
    public List<Notas> obtenerNotasPorEstudiante(int estudianteId) throws SQLException {
        List<Notas> notas = new ArrayList<>();
        String query = "SELECT * FROM notas WHERE estudiante_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, estudianteId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("notas_id");
            int materiaId = resultSet.getInt("materia_id");
            double valorNota = resultSet.getDouble("valor_nota");
            Notas nota = new Notas(id, estudianteId, materiaId, valorNota);
            notas.add(nota);
        }
        resultSet.close();
        return notas;
    }
    public double calcularPromedioMateria(int materiaId) throws SQLException {
        String query = "SELECT AVG(valor_nota) AS promedio FROM notas WHERE materia_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, materiaId);
        ResultSet resultSet = statement.executeQuery();
        double promedio = 0.0;
        if (resultSet.next()) {
            promedio = resultSet.getDouble("promedio");
        }
        resultSet.close();
        return promedio;
    }
}
