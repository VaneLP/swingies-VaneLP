package controlador.DAO;

import modelo.Curso;
import java.util.List;

//clase para incluir logica de BDD
public interface CursoDAO {
    void crearTablasCur();
    void insert(Curso cur);
    void update(Curso cur);
    void delete(Integer idCur);
    Curso readUno(Integer idCur);
    List<Curso> listaCurDAO();
    List<Curso> ordenarCurAlfDAO();

    List<Curso> coincidenciaExactaId(int idd);
    List<Curso> contienePalabraClaveId(int idd);
    List<Curso> empiezaPorId(int idd);
    List<Curso> terminaEnId(int idd);

    List<Curso> coincidenciaExactaNombre(String name);
    List<Curso> contienePalabraClaveNombre(String name);
    List<Curso> empiezaPorNombre(String name);
    List<Curso> terminaEnNombre(String name);
}