package controlador.DAO.JDBC;

import controlador.DAO.CursoDAO;
import modelo.Curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CursoDAOJDBCImpl implements CursoDAO {

    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";

    /*
       try (Connection connect = DriverManager.getConnection(url, "root", "admin");
             Statement state = connect.createStatement()) {

            String crearTablaCusos = "CREATE TABLE IF NOT EXISTS Cursos " +
                    "(id INT PRIMARY KEY, " +
                    "Nombre VARCHAR(255) " +
                    );";

        state.executeUpdate(crearTablaTrabajodores);
     } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     */

    @Override
    public void insert(Curso cur) {
        try (Connection connect2 = DriverManager.getConnection(url, "root", "admin")) {
            String sentenciaInsertar = "INSERT INTO Alumno(id, Nombre)" +
                    "VALUES(?,?)";
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
    public void update(Curso cur) {

    }

    @Override
    public void delete(Integer idCur) {

    }

    @Override
    public Curso read(Integer idAlum) {
        return null;
    }
}
