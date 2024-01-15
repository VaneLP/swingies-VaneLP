package controlador.DAO.JDBC;

import controlador.DAO.ProfesorDAO;
import modelo.Profesor;

public class ProfesorDAOJDBCImpl implements ProfesorDAO {

    /*
       try (Connection connect = DriverManager.getConnection(url, "root", "admin");
             Statement state = connect.createStatement()) {

            String crearTablaProfesores = "CREATE TABLE IF NOT EXISTS Profesores " +
                    "(id INT PRIMARY KEY, " +
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
