package controlador.DAO.JDBC;

import controlador.DAO.CursoDAO;
import modelo.Curso;

public class CursoDAOJDBCImpl implements CursoDAO {

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
