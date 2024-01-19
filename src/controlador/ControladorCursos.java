package controlador;

import controlador.DAO.CursoDAO;
import controlador.DAO.JDBC.CursoDAOJDBCImpl;
import modelo.Curso;
import java.util.ArrayList;
import java.util.List;

// Clase ListaCurso implementa la ILista para obtener sus metodos
public class ControladorCursos implements ILista<Curso> {
    private CursoDAO curDao = new CursoDAOJDBCImpl();

    // ---- METODOS ----

    @Override
    public void crearTablas() {
        curDao.crearTablasCur();
    }

    /**
     * Metodo agregar de la interfaz ILista
     *
     * @param c le pasamos un curso
     * @return nos duvuelve si se ha añadido o no
     */
    @Override
    public void agregar(Curso c) {
        //BBDD
        curDao.insert(c);
    }

    /**
     * Metodo buscar de la interfaz ILista
     * @param id pasamos una cadena de texto, que haria referencia al codigo
     * @return nos duvuelve un curso si corresponde con el codigo
     */
    @Override
    public Curso buscar(String id) {
        //BBDD
        return curDao.readUno(Integer.valueOf(id));
    }

    /**
     * Metodo eliminar de la interfaz ILista
     *
     * @param codigo le pasamos una cadena de texto, que haria referencia al codigo
     * @return nos duvuelve TRUE si se ha eliminado el curso y FALSE sino
     */
    @Override
    public void eliminar(String codigo) {
        //BBDD
        curDao.delete(Integer.valueOf(codigo));
    }

    /**
     * Metodo listar de la interfaz ILista
     *
     * @return nos duvuelve todos los cursos que tenemos en la lista
     */
    @Override
    public List<Curso> listar() {
        //BBDD
        return curDao.listaCurDAO();
    }

    public List<Curso> ordenarALfabeticamente() {
        //BBDD
        return curDao.ordenarCurAlfDAO();
    }
}
