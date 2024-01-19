package controlador.DAO;

import modelo.Alumno;
import modelo.Curso;

import java.util.List;

//clase para incluir logica de BDD
public interface AlumnoDAO {
    void insert(Alumno alum);
    void delete(String dni);
    Alumno readUno(String idAlum);
    List<Alumno> listaAlumDAO();
    List<Alumno> ordenarAlumAlfDAO();
    void insertNota(String dni, double nota);
    void insertNota(Alumno a, double nota);
    List<Alumno> listaAlumAproDAO();
    List<Alumno> listaAlumSusDAO();

}
