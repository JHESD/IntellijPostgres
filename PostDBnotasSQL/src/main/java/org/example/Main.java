package org.example;

import org.example.DaoClass_Sql.EstudiantesDAO;
import org.example.DaoClass_Sql.MateriasDAO;
import org.example.DB_classPost.PostSQL_DB;
import org.example.DB_tableClass.Estudiantes;
import org.example.DB_tableClass.Materias;
import org.example.DB_tableClass.Notas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            // Establecer conexión a la base de datos
            PostSQL_DB db = new PostSQL_DB();
            Connection connection = db.getConnection();

            // Crear instancias de DAOs
            EstudiantesDAO estudiantesDAO = new EstudiantesDAO(connection);
            MateriasDAO materiasDAO = new MateriasDAO(connection);

            // Crear Scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Menú principal
            int opcion;
            do {
                System.out.println("Menú:");
                System.out.println("1. Agregar estudiante");
                System.out.println("2. Eliminar estudiante");
                System.out.println("3. Agregar materia");
                System.out.println("4. Eliminar materia");
                System.out.println("0. Salir");
                System.out.print("Ingrese su opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del Scanner

                switch (opcion) {
                    case 1:
                        // Agregar estudiante
                        agregarEstudiante(scanner, estudiantesDAO);
                        break;
                    case 2:
                        // Eliminar estudiante
                        eliminarEstudiante(scanner, estudiantesDAO);
                        break;
                    case 3:
                        // Agregar materia
                        agregarMateria(scanner, materiasDAO);
                        break;
                    case 4:
                        // Eliminar materia
                        eliminarMateria(scanner, materiasDAO);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            } while (opcion != 0);

            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void agregarEstudiante(Scanner scanner, EstudiantesDAO estudiantesDAO) {
        // Pedir datos del estudiante al usuario
        System.out.println("Ingrese los datos del estudiante:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del Scanner
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimientoString = scanner.nextLine();
        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();

        // Convertir la fecha de nacimiento a Date
        Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoString);

        // Crear instancia de Estudiantes y agregarlo a la base de datos
        Estudiantes estudiante = new Estudiantes(id, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, carrera);
        try {
            estudiantesDAO.registrarEstudiante(estudiante);
            System.out.println("Estudiante agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar estudiante.");
        }
    }

    private static void eliminarEstudiante(Scanner scanner, EstudiantesDAO estudiantesDAO) {
        // Pedir ID del estudiante al usuario
        System.out.print("Ingrese el ID del estudiante a eliminar: ");
        int id = scanner.nextInt();

        // Eliminar estudiante de la base de datos
        try {
            estudiantesDAO.eliminarEstudiante(id);
            System.out.println("Estudiante eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar estudiante.");
        }
    }

    private static void agregarMateria(Scanner scanner, MateriasDAO materiasDAO) {
        // Pedir datos de la materia al usuario
        System.out.println("Ingrese los datos de la materia:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del Scanner
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Créditos: ");
        int creditos = scanner.nextInt();

        // Crear instancia de Materias y agregarlo a la base de datos
        Materias materia = new Materias(id, nombre, creditos);
        try {
            materiasDAO.registrarMateria(materia);
            System.out.println("Materia agregada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar materia.");
        }
    }

    private static void eliminarMateria(Scanner scanner, MateriasDAO materiasDAO) {
        // Pedir ID de la materia al usuario
        System.out.print("Ingrese el ID de la materia a eliminar: ");
        int id = scanner.nextInt();

        // Eliminar materia de la base de datos
        try {
            materiasDAO.eliminarMateria(id);
            System.out.println("Materia eliminada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar materia.");
        }
    }
}
