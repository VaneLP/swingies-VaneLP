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

    @Override
    public void crearTablasProfe() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaProfes =
                    "CREATE TABLE IF NOT EXISTS Profesores " +
                            "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "Nombre VARCHAR(255) NOT NULL, " +
                            "DNI VARCHAR(255) NOT NULL UNIQUE, " +
                            "Tlf VARCHAR(255) NOT NULL, " +
                            "Edad VARCHAR(255) NOT NULL," +
                            "Curso_id INT, FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE SET NULL" +
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
        try (Connection connect = DriverManager.getConnection(url,  user, pass)) {
            String sentenciaInsertar = "INSERT INTO Profesores(Nombre, DNI, Tlf, Edad, Curso_id)" +
                    "VALUES(?,?,?,?,?)";

            try (PreparedStatement psProfe = connect.prepareStatement(sentenciaInsertar)) {
                psProfe.setString(1, profe.getNombre());
                psProfe.setString(2, profe.getDNI());
                psProfe.setInt(3, profe.getTlf());
                psProfe.setInt(4, profe.getEdad());

                if(profe.getCurso().getId() == -1)
                    psProfe.setNull(5, Types.NULL);
                else
                    psProfe.setInt(5,profe.getCurso().getId());

                psProfe.execute();
            }

            System.out.println("Insercion profe");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String dni) {
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
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id ";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> ordenarProfeAlfDAO() {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id " +
                            "ORDER BY profe.nombre ASC";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //todo
    @Override
    public List<Profesor> coincidenciaExactaNombre(String name) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id "+
                            "WHERE profe.nombre = ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,name);

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> contienePalabraClaveNombre(String name) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id "+
                            "WHERE profe.nombre LIKE ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,"%"+name+"%");

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> empiezaPorNombre(String name) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id "+
                            "WHERE profe.nombre LIKE ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,name+"%");

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> terminaEnNombre(String name) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id "+
                            "WHERE profe.nombre LIKE ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,"%"+name);

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> coincidenciaExactaDni(String dnii) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id " +
                            "WHERE profe.dni = ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,dnii);

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> contienePalabraClaveDni(String dnii) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id "+
                            "WHERE profe.dni LIKE ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,"%"+dnii+"%");

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> empiezaPorDni(String dnii) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id " +
                            "WHERE profe.dni LIKE ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,dnii+"%");

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> terminaEnDni(String dnii) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id "+
                            "WHERE profe.dni LIKE ?";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {
                psProfe.setString(1,"%"+dnii);

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()==null)
                        p.setCurso(Curso.cursoNulo);

                    p.setListaAsignaturas(asignaturas);

                    listaProfe.add(p);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> agruparAsignraturaProf(String asginatura) {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id ";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(asignaturas.contains(asginatura)) {
                        Profesor p = new Profesor(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));

                        if(p.getCurso().getNombre()==null)
                            p.setCurso(Curso.cursoNulo);

                        p.setListaAsignaturas(asignaturas);

                        listaProfe.add(p);
                    }
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> listaProfeTutorDAO() {
        List<Profesor> listaProfe = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaProfe =
                    "SELECT profe.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Profesores AS profe " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON profe.Curso_id = cur.id ";

            String leerTablaAsig =
                    "SELECT asignatura " +
                            "FROM Asignaturas " +
                            "WHERE Profesor_id = ?";

            try (PreparedStatement psProfe = connect.prepareStatement(mostrarTodoTablaProfe);
                 PreparedStatement psAsig = connect.prepareStatement(leerTablaAsig)
            ) {

                ResultSet rsProfe = psProfe.executeQuery();

                while (rsProfe.next()) {
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

                    if(p.getCurso().getNombre()!=null)
                        listaProfe.add(p);

                    p.setListaAsignaturas(asignaturas);
                }

                System.out.println("Tablas profe listadas");
                return listaProfe;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
