package controlador.DAO.JDBC;

import controlador.DAO.AlumnoDAO;
import modelo.Alumno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlumnoDAOJDBCImpl implements AlumnoDAO {
    //guardamos la url donde se encuentre nuestra BD
    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";

    /*
       try (Connection connect = DriverManager.getConnection(url, "root", "admin");
             Statement state = connect.createStatement()) {

            String crearTablaAlumnos = "CREATE TABLE IF NOT EXISTS Alumnos " +
                    "(id INT PRIMARY KEY, " +
                    "Nombre VARCHAR(255), " +
                    "DNI VARCHAR(255), " +
                    "Tlf VARCHAR(255), " +
                    "Edad INT," +
                    "Curso VARCHAR(255)," +
                    "Notas"
                    );";

        state.executeUpdate(crearTablaTrabajodores);
     } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     */
    @Override
    public void insert(Alumno alum) {
        try (Connection connect2 = DriverManager.getConnection(url, "root", "admin")) {
        String sentenciaInsertar = "INSERT INTO Alumno(id, Nombre, DNI, tlf, edad, curso)" +
                "VALUES(?,?,?,?,?,?)";
            try (PreparedStatement psAlumno = connect2.prepareStatement(sentenciaInsertar)) {
                //psAlumno.setString( );

            }
            System.out.println("Insercion en las tablas compleatado con exito");
            System.out.println("---------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Alumno alum) {
        String sentencia = "";
    }

    @Override
    public void delete(Integer id) {
        String sentencia = "DELETE FROM Alumno WHERE id = ";
    }

    @Override
    public Alumno read(Integer idAlum) {

        return null;
    }
}
