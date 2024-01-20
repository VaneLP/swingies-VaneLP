package controlador.DAO;

import modelo.Profesor;
import java.util.List;

//clase para incluir logica de BDD
public interface ProfesorDAO {
    void crearTablasProfe();
    void insert(Profesor profe);
    void delete(String dni);
    Profesor readUno(String idAlum);
    List<Profesor> listaProfeDAO();
    List<Profesor> listaProfeTutorDAO();
    List<Profesor> ordenarProfeAlfDAO();

    List<Profesor> coincidenciaExactaNombre();
    List<Profesor> contienePalabraClaveNombre();
    List<Profesor> empiezaPorNombre();
    List<Profesor> terminaEnNombre();

    List<Profesor> coincidenciaExactaDni();
    List<Profesor> contienePalabraClaveDni();
    List<Profesor> empiezaPorDni();
    List<Profesor> terminaEnDni();

    List<Profesor> agruparAsignraturaProf();
}
