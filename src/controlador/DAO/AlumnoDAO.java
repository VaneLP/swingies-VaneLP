package controlador.DAO;

import modelo.Alumno;
import modelo.Curso;

import java.util.List;

//clase para incluir logica de BDD
public interface AlumnoDAO {
    void crearTablasAlum();
    void insert(Alumno alum);
    void delete(String dni);
    Alumno readUno(String idAlum);
    List<Alumno> listaAlumDAO();
    List<Alumno> ordenarAlumAlfDAO();
    void insertNota(String dni, double nota);
    void insertNota(Alumno a, double nota);

    List<Alumno> listaAlumAproDAO();
    List<Alumno> listaAlumSusDAO();

    List<Alumno> coincidenciaExactaNombre(String name);
    List<Alumno> contienePalabraClaveNombre(String name);
    List<Alumno> empiezaPorNombre(String name);
    List<Alumno> terminaEnNombre(String name);

    List<Alumno> coincidenciaExactaDni(String dni);
    List<Alumno> contienePalabraClaveDni(String dni);
    List<Alumno> empiezaPorDni(String dni);
    List<Alumno> terminaEnDni(String dni);

    List<Alumno> notaMediaAlum(int mediia);
    List<Alumno> profesorTutorAlum(String nombreTutor);

    List<Alumno> buscarCursoAlum(List<String> listaCur);
}
