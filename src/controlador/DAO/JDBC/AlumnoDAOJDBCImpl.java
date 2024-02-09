package controlador.DAO.JDBC;

import controlador.DAO.AlumnoDAO;
import modelo.Alumno;
import modelo.Curso;
import modelo.CursoInvalidoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOJDBCImpl implements AlumnoDAO {
    //guardamos la url donde se encuentre nuestra BD
    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";
    private static final String user = "root";
    private static final String pass = "admin";

    @Override
    public void crearTablasAlum() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaAlumnos =
                    "CREATE TABLE IF NOT EXISTS Alumnos " +
                            "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "Nombre VARCHAR(255) NOT NULL, " +
                            "DNI VARCHAR(255) NOT NULL UNIQUE, " +
                            "Tlf VARCHAR(255) NOT NULL, " +
                            "Edad VARCHAR(255) NOT NULL," +
                            "Curso_id INT NOT NULL, FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE CASCADE" +
                            ");";

            String crearTablaNotas =
                    "CREATE TABLE IF NOT EXISTS Notas" +
                            "(Alumno_id INT, FOREIGN KEY (alumno_id) REFERENCES alumnos(id) ON DELETE CASCADE," +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "Nota DECIMAL NOT NULL);";

            state.executeUpdate(crearTablaAlumnos);
            state.executeUpdate(crearTablaNotas);

            System.out.println("Tabla creada Alum y notas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Alumno alum) {
        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            String sentenciaInsertar = "INSERT INTO Alumnos(Nombre, DNI, Tlf, Edad, Curso_id)" +
                    "VALUES(?,?,?,?,?)";

            try (PreparedStatement psAlumno = connect.prepareStatement(sentenciaInsertar)) {
                psAlumno.setString(1, alum.getNombre());
                psAlumno.setString(2, alum.getDNI());
                psAlumno.setInt(3, alum.getTlf());
                psAlumno.setInt(4, alum.getEdad());
                psAlumno.setLong(5, alum.getCurso().getId());

                psAlumno.execute();
            }

            System.out.println("Insercion Alum ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String dni) {
        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            String borrarTablaAlum = "DELETE FROM Alumnos WHERE DNI = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(borrarTablaAlum)) {
                psAlum.setString(1, dni);

                psAlum.executeUpdate();
            }

            System.out.println("Tablas Alum borradas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Alumno readUno(String dniAlum) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.dni = ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, dniAlum);
                ResultSet rsAlum = psAlum.executeQuery();

                if (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                    return a;
                }

                System.out.println("Tablas Alum read 1");
            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Alumno> listaAlumDAO() {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> ordenarAlumAlfDAO() {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum = "SELECT * FROM Alumnos ORDER BY nombre ASC";
            String leerTablaCur = "SELECT * FROM Cursos WHERE id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psCur = connect.prepareStatement(leerTablaCur)) {

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
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

                        listaAlum.add(new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre)));
                    }
                }

                System.out.println("Tablas Alum ordenadas alfa");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertNota(String dni, double nota) {
        int id;

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            String sentenciaInsertarNota =
                    "INSERT INTO Notas(Alumno_id, nota)" +
                            "VALUES(?,?)";

            String sentenciaBuscarAlum =
                    "SELECT id FROM Alumnos WHERE dni = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(sentenciaBuscarAlum)) {
                psAlum.setString(1, dni);
                try (ResultSet rs = psAlum.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    } else {
                        // Handle the case when no rows are returned
                        throw new RuntimeException("No result found for the given DNI");
                    }
                }
            }

            try (PreparedStatement psNotas = connect.prepareStatement(sentenciaInsertarNota)) {
                psNotas.setInt(1, id);
                psNotas.setDouble(2, nota);

                psNotas.execute();
            }

            System.out.println("Insercion notas ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertNota(Alumno a, double nota) {
        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            String sentenciaInsertarNota = "INSERT INTO Notas(Alumno_id, nota)" +
                    "VALUES(?,?)";

            try (PreparedStatement psNotas = connect.prepareStatement(sentenciaInsertarNota)) {
                psNotas.setLong(1, a.getId());
                psNotas.setDouble(2, nota);

                psNotas.execute();
            }

            System.out.println("Insercion notas ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> listaAlumAproDAO() {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    double sumaNotas = 0;
                    for (Double n : notas) {
                        sumaNotas += n;
                    }
                    double media = sumaNotas / notas.size();

                    if (media >= 5) {
                        Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                        a.setListaNotas(notas);

                        listaAlum.add(a);
                    }
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> listaAlumSusDAO() {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    double sumaNotas = 0;
                    for (Double n : notas) {
                        sumaNotas += n;
                    }
                    double media = sumaNotas / notas.size();

                    if (media < 5) {
                        Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                        a.setListaNotas(notas);

                        listaAlum.add(a);
                    }
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> coincidenciaExactaNombre(String name) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.nombre = ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, name);

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);

                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> contienePalabraClaveNombre(String name) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.nombre LIKE ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, "%" + name + "%");

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> empiezaPorNombre(String name) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.nombre LIKE ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, name + "%");

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> terminaEnNombre(String name) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.nombre LIKE ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, "%" + name);

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> coincidenciaExactaDni(String dnii) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.dni = ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, dnii);

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> contienePalabraClaveDni(String dnii) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.dni LIKE ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, "%" + dnii + "%");

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> empiezaPorDni(String dnii) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id" +
                            "WHERE alum.dni LIKE ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, dnii + "%");

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> terminaEnDni(String dnii) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +
                            "WHERE alum.dni LIKE ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, "%" + dnii);

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> notaMediaAlum(int mediia) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    double sumaNotas = 0;
                    for (Double n : notas) {
                        sumaNotas += n;
                    }
                    double media = sumaNotas / notas.size();

                    if (media == mediia) {
                        Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                        a.setListaNotas(notas);

                        listaAlum.add(a);
                    }
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> profesorTutorAlum(String nombreTutor) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +

                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id " +

                            "JOIN profesores AS p " +
                            "ON p.curso_id = cur.id " +
                            "WHERE p.nombre = ?";

            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {
                psAlum.setString(1, nombreTutor);

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> buscarCursoAlum(List<String> listaCur) {
        List<Alumno> listaAlum = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaAlum =
                    "SELECT alum.*, cur.id AS id_curso, cur.nombre AS nombre_curso " +
                            "FROM Alumnos AS alum " +
                            "LEFT JOIN Cursos AS cur " +
                            "ON alum.Curso_id = cur.id ";


            int cursoCont = listaCur.size();

            if (cursoCont > 0) {
                mostrarTodoTablaAlum = mostrarTodoTablaAlum + " WHERE cur.nombre = ? ";

                for (int i = 1; i < cursoCont; i++) {
                    mostrarTodoTablaAlum = mostrarTodoTablaAlum + " OR cur.nombre = ? ";
                }

            }


            String leerTablaNotas =
                    "SELECT nota " +
                            "FROM notas " +
                            "WHERE Alumno_id = ?";

            try (PreparedStatement psAlum = connect.prepareStatement(mostrarTodoTablaAlum);
                 PreparedStatement psNotas = connect.prepareStatement(leerTablaNotas)
            ) {

                if (cursoCont > 0) {
                    for (int i = 0; i < cursoCont; i++) {
                        psAlum.setString(i+1,listaCur.get(i));
                    }
                }

                ResultSet rsAlum = psAlum.executeQuery();

                while (rsAlum.next()) {
                    int id = rsAlum.getInt("id");
                    String nombre = rsAlum.getString("Nombre");
                    String dni = rsAlum.getString("DNI");
                    String tlfn = rsAlum.getString("Tlf");
                    String edad = rsAlum.getString("Edad");

                    int curId = rsAlum.getInt("id_curso");
                    String curNombre = rsAlum.getString("nombre_curso");

                    psNotas.setInt(1, id);
                    List<Double> notas = new ArrayList<>();

                    try (ResultSet rsNotas = psNotas.executeQuery()) {
                        while (rsNotas.next()) {
                            notas.add(rsNotas.getDouble("nota"));
                        }
                    }

                    Alumno a = new Alumno(id, nombre, dni, tlfn, edad, new Curso(curId, curNombre));
                    a.setListaNotas(notas);

                    listaAlum.add(a);
                }

                System.out.println("Tablas Alum listadas");
                return listaAlum;

            } catch (CursoInvalidoException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
