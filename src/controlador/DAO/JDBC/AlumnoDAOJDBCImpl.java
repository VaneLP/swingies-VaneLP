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


    @Override
    public void insert(Alumno alum) {
        try (Connection connect2 = DriverManager.getConnection(url, "root", "admin")) {
        String sentenciaInsertar = "INSERT INTO Alumno(Nombre, DNI, tlf, edad, curso)" +
                "VALUES(?,?,?,?,?)";
            try (PreparedStatement psAlumno = connect2.prepareStatement(sentenciaInsertar)) {
                psAlumno.setString(1, );
                psAlumno.setString(2, );
                psAlumno.setString(3, );
                psAlumno.setString(4, );
                psAlumno.setString(5, );
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
        String sentencia = "";
    }

    @Override
    public Alumno read(Integer idAlum) {

        return null;
    }
}
