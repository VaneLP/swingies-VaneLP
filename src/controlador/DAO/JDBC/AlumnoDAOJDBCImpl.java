package controlador.DAO.JDBC;

import controlador.DAO.AlumnoDAO;
import modelo.Alumno;

import java.sql.*;

public class AlumnoDAOJDBCImpl implements AlumnoDAO {
    //guardamos la url donde se encuentre nuestra BD
    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";
    private static final String user = "root";
    private static final String pass = "admin";

    private static void crearTablasAlum() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaAlumnos = "CREATE TABLE IF NOT EXISTS Alumnos " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(255), " +
                    "DNI VARCHAR(255), " +
                    "Tlf INT, " +
                    "Edad INT," +
                    "Curso VARCHAR(255)," +
                    "Notas" +
                    ");";

            state.executeUpdate(crearTablaAlumnos);

            System.out.println("Tabla creada Alum");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Alumno alum) {

        crearTablasAlum();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
        String sentenciaInsertar = "INSERT INTO Alumno(id, Nombre, DNI, tlf, edad, curso)" +
                "VALUES(?,?,?,?,?,?)";

            try (PreparedStatement psAlumno = connect.prepareStatement(sentenciaInsertar)) {
                psAlumno.setInt(1, alum.getId());
                psAlumno.setString(2, alum.getNombre());
                psAlumno.setString(3, alum.getDNI());
                psAlumno.setInt(4, alum.getTlf());
                psAlumno.setInt(5, alum.getEdad());
                psAlumno.setString(6, alum.getCurso());
            }

            System.out.println("Insercion en las tablas compleatado con exito");
            System.out.println("---------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Alumno alum) {
    }

    @Override
    public void delete(Integer idAlum) {

        crearTablasAlum();

        try (Connection connect = DriverManager.getConnection(url, user, pass)){

            String borrarTablaCur = "DELETE FROM Cusos WHERE id = ?;";

            try (PreparedStatement psCur = connect.prepareStatement(borrarTablaCur)) {
                psCur.setInt(1,idAlum);

                psCur.executeUpdate(borrarTablaCur);
            }

            System.out.println("Tablas cur borradas con exito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Alumno read(Integer idAlum) {

        return null;
    }
}
