package controlador.DAO.JDBC;

import controlador.DAO.ProfesorDAO;
import modelo.Alumno;
import modelo.Curso;
import modelo.CursoInvalidoException;
import modelo.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOJDBCImpl implements ProfesorDAO {
    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";
    private static final String user = "root";
    private static final String pass = "admin";

    private static void crearTablasProfe() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaProfes =
                    "CREATE TABLE IF NOT EXISTS Profesores " +
                            "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "Nombre VARCHAR(255) NOT NULL, " +
                            "DNI VARCHAR(255) NOT NULL UNIQUE, " +
                            "Tlf VARCHAR(255) NOT NULL, " +
                            "Edad VARCHAR(255) NOT NULL," +
                            "Curso_id INT NOT NULL, FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE SET NULL" +
                            ");";

            String crearTablaAsig =
                    "CREATE TABLE IF NOT EXISTS Asignaturas" +
                            "(Profesor_id INT, FOREIGN KEY (profesor_id) REFERENCES profesores(id) ON DELETE CASCADE," +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "Asignatura VARCHAR(255) NOT NULL" +
                            ");";

            state.executeUpdate(crearTablaProfes);
            state.executeUpdate(crearTablaAsig);

            System.out.println("Tabla creada profe y asig");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Profesor profe) {

        crearTablasProfe();

        try (Connection connect = DriverManager.getConnection(url,  user, pass)) {
            String sentenciaInsertar = "INSERT INTO Profesores(id, Nombre, DNI, Tlf, Edad)" +
                    "VALUES(?,?,?,?,?)";

            try (PreparedStatement psProfe = connect.prepareStatement(sentenciaInsertar)) {
                psProfe.setInt(1, profe.getId());
                psProfe.setString(2, profe.getNombre());
                psProfe.setString(3, profe.getDNI());
                psProfe.setInt(4, profe.getTlf());
                psProfe.setInt(5, profe.getEdad());

                psProfe.execute();
            }

            System.out.println("Insercion profe");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String dni) {

        crearTablasProfe();

        try (Connection connect = DriverManager.getConnection(url, user, pass)){
            String borrarTablaProfe = "DELETE FROM Profesores WHERE DNI = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(borrarTablaProfe)) {
                psProfe.setString(1, dni);

                psProfe.executeUpdate();
            }

            System.out.println("Tablas prof borradas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profesor readUno(String dniProfe) {
        crearTablasProfe();
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id " +
                            "WHERE profe.dni = ?";

            String leerTablaAsig =
                    "SELECT asginatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1, dniProfe);
                ResultSet rsProfe = psProfe.executeQuery();

                if (rsProfe.next()) {
                    int id = rsProfe.getInt("id");
                    String nombre = rsProfe.getString("Nombre");
                    String dni = rsProfe.getString("DNI");
                    String tlfn = rsProfe.getString("Tlf");
                    String edad = rsProfe.getString("Edad");

                    int curId = rsProfe.getInt("id_curso");
                    String curNombre = rsProfe.getString("nombre_curso");

                    psAsig.setInt(1, id);
                    List<String> asignaturas = new ArrayList<>();
                    try (ResultSet rsAsig = psAsig.executeQuery()) {
                        while (rsAsig.next()) {
                            asignaturas.add(rsAsig.getString("asignatura"));
                        }
                    }

                    Profesor p = new Profesor(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe read 1");
            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Profesor> listaProfeDAO() {
        crearTablasProfe();
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id ";

            String leerTablaAsig =
                    "SELECT asginatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {

                ResultSet rsProfe = psProfe.executeQuery();

                if (rsProfe.next()) {
                    int id = rsProfe.getInt("id");
                    String nombre = rsProfe.getString("Nombre");
                    String dni = rsProfe.getString("DNI");
                    String tlfn = rsProfe.getString("Tlf");
                    String edad = rsProfe.getString("Edad");

                    int curId = rsProfe.getInt("id_curso");
                    String curNombre = rsProfe.getString("nombre_curso");

                    psAsig.setInt(1, id);
                    List<String> asignaturas = new ArrayList<>();
                    try (ResultSet rsAsig = psAsig.executeQuery()) {
                        while (rsAsig.next()) {
                            asignaturas.add(rsAsig.getString("asignatura"));
                        }
                    }

                    Profesor p = new Profesor(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Profesor> listaProfeTutorDAO() {
        return null;
    }

    @Override
    public List<Profesor> ordenarProfeAlfDAO() {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe = "SELECT * FROM Profesores ORDER BY nombre ASC";
            String leerTablaAsig = "SELECT * FROM Asignaturas WHERE id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psCur = connect.prepareStatement(leerTablaAsig)) {

                ResultSet rsAlum = psAlum.executeQuery();

                if (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");
                    int idCur = rsAlum.getInt("Curso_id");

                    psCur.setInt(1, idCur);
                    ResultSet rsCur = psCur.executeQuery();

                    if (rsCur.next()) {
                        int curId = rsCur.getInt("id");
                        String curNombre = rsCur.getString("Nombre");

                        listaProfe.add(new Profesor(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre)));
                    }
                }

                System.out.println("Tablas Alum ordenadas alfa");

                return listaProfe;
            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
