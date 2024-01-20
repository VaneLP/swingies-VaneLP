package controlador.DAO.JDBC;

import controlador.DAO.CursoDAO;
import modelo.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOJDBCImpl implements CursoDAO {
    private static final String url = "jdbc:mysql://localhost:3306/ies-thiar";
    private static final String user = "root";
    private static final String pass = "admin";

    @Override
    public void crearTablasCur() {
        try (Connection connect = DriverManager.getConnection(url, user, pass);
             Statement state = connect.createStatement()) {

            String crearTablaCusos =
                    "CREATE TABLE IF NOT EXISTS Cursos " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "Nombre VARCHAR(255)  NOT NULL" +
                    ")";

            state.executeUpdate(crearTablaCusos);

            System.out.println("Tabla creada cur");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Curso cur) {
        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            String sentenciaInsertar =
                    "INSERT INTO Cursos(Nombre)" +
                    "VALUES(?)";

            try (PreparedStatement psCur = connect.prepareStatement(sentenciaInsertar)) {
                psCur.setString(1, cur.getNombre());

                psCur.execute();
            }

            System.out.println("Insercion cur");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer idCur) {
        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String borrarTablaCur = "DELETE FROM Cursos WHERE id = ?";

            try (PreparedStatement psCur = connect.prepareStatement(borrarTablaCur)) {
                psCur.setInt(1, idCur);

                psCur.executeUpdate();
            }

            System.out.println("Tablas cur borradas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Curso readUno(Integer idCur) {
        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String leerTablaCur = "SELECT * FROM Cursos WHERE id = ?";

            try (PreparedStatement psCur = connect.prepareStatement(leerTablaCur)) {
                psCur.setInt(1, idCur);
                ResultSet rs = psCur.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    return new Curso(id, nombre);
                }
                System.out.println("Tablas cur listadas con exito");
            }
        } catch (SQLException e) {
            new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Curso> listaCurDAO() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> ordenarCurAlfDAO() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos ORDER BY nombre ASC";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur ordenadas alfa");
            return listaCur;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //todo
    @Override
    public List<Curso> coincidenciaExactaId() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> contienePalabraClaveId() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> empiezaPorId() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> terminaEnId() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> coincidenciaExactaNombre() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> contienePalabraClaveNombre() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> empiezaPorNombre() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }

    @Override
    public List<Curso> terminaEnNombre() {
        List<Curso> listaCur = new ArrayList<>();

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {

            String mostrarTodoTablaCur = "SELECT * FROM Cursos";

            try (PreparedStatement psCur = connect.prepareStatement(mostrarTodoTablaCur)) {

                ResultSet rs = psCur.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("Nombre");

                    listaCur.add(new Curso(id, nombre));
                }

            }

            System.out.println("Tablas cur listadas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCur;
    }
}
