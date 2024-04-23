package org.example.DaoClass_Sql;

import org.example.DB_tableClass.Estudiantes;
import org.example.DB_tableClass.Materias;
import org.example.DB_tableClass.Notas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class EstudiantesDAO {
    private Connection connection;

    public EstudiantesDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarEstudianteConMateriasYNotas(Estudiantes estudiante, Materias... materias) throws SQLException {
        // Registrar estudiante
        registrarEstudiante(estudiante);

        // Registrar materias
        for (Materias materia : materias) {
            registrarMateria(materia);
        }

        // Registrar notas
        for (Notas nota : estudiante.getValorNota) {
            registrarNota(nota);
        }
    }

    private void registrarEstudiante(Estudiantes estudiante) throws SQLException {
        String query = "INSERT INTO estudiantes (est_id, est_nom, est_app, est_apm, est_dt, carrera) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, estudiante.getId());
            statement.setString(2, estudiante.getNombre());
            statement.setString(3, estudiante.getApellidoPaterno());
            statement.setString(4, estudiante.getApellidoMaterno());
            statement.setDate(5, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            statement.setString(6, estudiante.getCarrera());
            statement.executeUpdate();
        }
    }

    private void registrarMateria(Materias materia) throws SQLException {
        String query = "INSERT INTO materias (mat_id, mat_nom, mat_cdt) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, materia.getId());
            statement.setString(2, materia.getNombre());
            statement.setInt(3, materia.getCreditos());
            statement.executeUpdate();
        }
    }

    private void registrarNota(Notas nota) throws SQLException {
        String query = "INSERT INTO notas (notas_id, estudiante_id, materia_id, valor_nota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nota.getId());
            statement.setInt(2, nota.getEstudianteId());
            statement.setInt(3, nota.getMateriaId());
            statement.setDouble(4, nota.getValorNota());
            statement.executeUpdate();
        }
    }
}
