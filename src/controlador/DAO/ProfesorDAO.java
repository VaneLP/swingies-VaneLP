package controlador.DAO;

import modelo.Alumno;
import modelo.Profesor;

import java.util.List;

//clase para incluir logica de BDD
public interface ProfesorDAO {
    void insert(Profesor profe);
    void delete(String dni);
    Profesor readUno(String idAlum);
    List<Profesor> listaProfeDAO();
    List<Profesor> listaProfeTutorDAO();
    List<Profesor> ordenarProfeAlfDAO();
}
