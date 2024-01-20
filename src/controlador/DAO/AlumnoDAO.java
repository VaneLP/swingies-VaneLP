package controlador.DAO;

import modelo.Alumno;
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

    List<Alumno> coincidenciaExactaNombre();
    List<Alumno> contienePalabraClaveNombre();
    List<Alumno> empiezaPorNombre();
    List<Alumno> terminaEnNombre();

    List<Alumno> coincidenciaExactaDni();
    List<Alumno> contienePalabraClaveDni();
    List<Alumno> empiezaPorDni();
    List<Alumno> terminaEnDni();

    List<Alumno> notaMediaAlum();
    List<Alumno> profesorTutorAlum();


}
