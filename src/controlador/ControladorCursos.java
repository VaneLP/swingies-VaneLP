package controlador;

import controlador.DAO.API.CursoDAOAPIImpl;
import controlador.DAO.CursoDAO;
import modelo.Curso;

import java.util.List;

// Clase ListaCurso implementa la ILista para obtener sus metodos
public class ControladorCursos implements ILista<Curso> {
    //private CursoDAO curDao = new CursoDAOJDBCImpl();
//    private CursoDAO curJpa = new CursoDAOJPAImpl();
    private CursoDAO curApi = new CursoDAOAPIImpl();

    // ---- METODOS ----

    @Override
    public void crearTablas() {
        curApi.crearTablasCur();
    }

    @Override
    public void agregar(Curso object) {

    }

    /**
     * Metodo agregar de la interfaz ILista
     */
    public void agregar(String nombre) {
        //BBDD
        curApi.insert(new Curso(nombre));
    }


    public void agregarCsv(long id, String nombre) {
        //BBDD
        curApi.insert(new Curso(id, nombre));
    }

    /**
     * Metodo buscar de la interfaz ILista
     * @param id pasamos una cadena de texto, que haria referencia al codigo
     * @return nos duvuelve un curso si corresponde con el codigo
     */
    @Override
    public Curso buscar(String id) {
        //BBDD
        return curApi.readUno(Integer.valueOf(id));
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
        curApi.delete(Integer.valueOf(codigo));
    }

    /**
     * Metodo listar de la interfaz ILista
     *
     * @return nos duvuelve todos los cursos que tenemos en la lista
     */
    @Override
    public List<Curso> listar() {
        //BBDD
        return curApi.listaCurDAO();
    }

    public List<Curso> ordenarALfabeticamente() {
        //BBDD
        return curApi.ordenarCurAlfDAO();
    }

    public List<Curso> coincidenciaExactaId(int idd){
        return curApi.coincidenciaExactaId(idd);
    }
    public List<Curso> contienePalabraClaveId(int idd){
        return curApi.contienePalabraClaveId(idd);
    }
    public List<Curso> empiezaPorId(int idd){
        return curApi.empiezaPorId(idd);
    }
    public List<Curso> terminaEnId(int idd){
        return curApi.terminaEnId(idd);
    }
    public List<Curso> coincidenciaExactaNombre(String name){
        return curApi.coincidenciaExactaNombre(name);
    }
    public List<Curso> contienePalabraClaveNombre(String name){
        return curApi.contienePalabraClaveNombre(name);
    }
    public List<Curso> empiezaPorNombre(String name){
        return curApi.empiezaPorNombre(name);
    }
    public List<Curso> terminaEnNombre(String name){
        return curApi.terminaEnNombre(name);
    }

}
