package controlador.DAO.JDBC;

import controlador.DAO.ProfesorDAO;
import modelo.Profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfesorDAOJDBCImpl implements ProfesorDAO {

    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";

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
        try (Connection connect = DriverManager.getConnection(url, "root", "admin")) {
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

    }

    @Override
    public Profesor read(Integer idAlum) {
        return null;
    }
}
