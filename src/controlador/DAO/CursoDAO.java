package controlador.DAO;

import modelo.Curso;
import java.util.List;

//clase para incluir logica de BDD
public interface CursoDAO {
    void crearTablasCur();
    void insert(Curso cur);
    void delete(Integer idCur);
    Curso readUno(Integer idCur);
    List<Curso> listaCurDAO();
    List<Curso> ordenarCurAlfDAO();

    List<Curso> coincidenciaExactaId();
    List<Curso> contienePalabraClaveId();
    List<Curso> empiezaPorId();
    List<Curso> terminaEnId();

    List<Curso> coincidenciaExactaNombre();
    List<Curso> contienePalabraClaveNombre();
    List<Curso> empiezaPorNombre();
    List<Curso> terminaEnNombre();
}