package controlador.DAO;

import modelo.Profesor;
import java.util.List;

//clase para incluir logica de BDD
public interface ProfesorDAO {
    void crearTablasProfe();
    void insert(Profesor profe);
    void update(Profesor profe);
    void delete(String dni);
    Profesor readUno(String idAlum);
    List<Profesor> listaProfeDAO();
    List<Profesor> listaProfeTutorDAO();
    List<Profesor> ordenarProfeAlfDAO();
    void insertAsig(String dni, String asig);

    List<Profesor> coincidenciaExactaNombre(String name);
    List<Profesor> contienePalabraClaveNombre(String name);
    List<Profesor> empiezaPorNombre(String name);
    List<Profesor> terminaEnNombre(String name);

    List<Profesor> coincidenciaExactaDni(String dni);
    List<Profesor> contienePalabraClaveDni(String dni);
    List<Profesor> empiezaPorDni(String dni);
    List<Profesor> terminaEnDni(String dni);

    List<Profesor> agruparAsignraturaProf(String asginatura);
}
