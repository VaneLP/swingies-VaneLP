package controlador.DAO.JDBC;

import controlador.DAO.ProfesorDAO;
import modelo.Profesor;

import java.sql.*;

public class ProfesorDAOJDBCImpl implements ProfesorDAO {
    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";
    private static final String user = "root";
    private static final String pass = "admin";

    private static void crearTablasProfe() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaProfesores = "CREATE TABLE IF NOT EXISTS Profesores " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(255), " +
                    "DNI VARCHAR(255), " +
                    "Tlf VARCHAR(255), " +
                    "Edad INT," +
                    "Curso VARCHAR(255)," +
                    "Asginaturas"
                    +
                    ");";

            state.executeUpdate(crearTablaProfesores);

            System.out.println("Tabla creada Profe");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
       try (Connection connect = DriverManager.getConnection(url, "root", "admin");
             Statement state = connect.createStatement()) {

            String crearTablaProfesores = "CREATE TABLE IF NOT EXISTS Profesores " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(255), " +
                    "DNI VARCHAR(255), " +
                    "Tlf VARCHAR(255), " +
                    "Edad INT," +
                    "Curso VARCHAR(255)," +
                    "Asginaturas "
                    );";

        state.executeUpdate(crearTablaTrabajodores);
     } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     */

    @Override
    public void insert(Profesor profe) {

        crearTablasProfe();

        try (Connection connect = DriverManager.getConnection(url,  user, pass)) {
            String sentenciaInsertar = "INSERT INTO Alumno(id, Nombre, DNI, tlf, edad, curso)" +
                    "VALUES(?,?,?,?,?,?)";

            try (PreparedStatement psProfe = connect.prepareStatement(sentenciaInsertar)) {
                psProfe.setInt(1, profe.getId());
                psProfe.setString(2, profe.getNombre());
                psProfe.setString(3, profe.getDNI());
                psProfe.setInt(4, profe.getTlf());
                psProfe.setInt(5, profe.getEdad());
                psProfe.setString(6, profe.getCurso());
            }

            System.out.println("Insercion en las tablas compleatado con exito");
            System.out.println("---------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Profesor profe) {

    }

    @Override
    public void delete(Integer idProfe) {

        crearTablasProfe();

        try (Connection connect = DriverManager.getConnection(url, user, pass)){

            String borrarTablaCur = "DELETE FROM Cusos WHERE id = ?;";

            try (PreparedStatement psCur = connect.prepareStatement(borrarTablaCur)) {
                psCur.setInt(1,idProfe);

                psCur.executeUpdate(borrarTablaCur);
            }

            System.out.println("Tablas cur borradas con exito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profesor read(Integer idAlum) {
        return null;
    }
}
