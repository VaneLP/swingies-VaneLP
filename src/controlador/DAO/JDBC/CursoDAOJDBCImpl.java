package controlador.DAO.JDBC;

import controlador.DAO.CursoDAO;
import modelo.Curso;

import java.sql.*;

public class CursoDAOJDBCImpl implements CursoDAO {

    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";
    private static final String user = "root";
    private static final String pass = "admin";


    private static void crearTablasCur() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaCusos = "CREATE TABLE IF NOT EXISTS Cursos " +
                    "(id INT PRIMARY KEY, " +
                    "Nombre VARCHAR(255) " +
                    ");";

            state.executeUpdate(crearTablaCusos);

            System.out.println("Tabla creada cur");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Curso cur) {

        crearTablasCur();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            String sentenciaInsertar = "INSERT INTO Cursos(id, Nombre)" +
                    "VALUES(?,?)";

            try (PreparedStatement psCur = connect.prepareStatement(sentenciaInsertar)) {
                psCur.setInt(1,cur.getCodigo());
                psCur.setString(2,cur.getNombre());

                psCur.execute();
            }

            System.out.println("Insercion cur en las tablas compleatado con exito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Curso cur) {
    }

    @Override
    public void delete(Integer idCur) {

        crearTablasCur();

        try (Connection connect = DriverManager.getConnection(url, user, pass)){

            String borrarTablaCur = "DELETE FROM Cusos WHERE id = ?;";

            try (PreparedStatement psCur = connect.prepareStatement(borrarTablaCur)) {
                psCur.setInt(1,idCur);

                psCur.executeUpdate(borrarTablaCur);
            }

            System.out.println("Tablas cur borradas con exito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Curso read(Integer idAlum) {

        crearTablasCur();

        return null;
    }
}
