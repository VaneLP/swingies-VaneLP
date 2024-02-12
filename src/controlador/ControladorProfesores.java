package controlador;

import controlador.DAO.JDBC.ProfesorDAOJDBCImpl;
import controlador.DAO.JPA.ProfesorDAOJPAImpl;
import controlador.DAO.ProfesorDAO;
import modelo.Curso;
import modelo.CursoInvalidoException;
import modelo.Profesor;

import java.util.List;

//Clase ListaProfesores implementa la ILista para obtener sus metodos
public class ControladorProfesores implements ILista<Profesor> {
	//private ProfesorDAO profeDao= new ProfesorDAOJDBCImpl();
	private ProfesorDAO profeJpa = new ProfesorDAOJPAImpl();

	// ---- METODOS ----

	@Override
	public void crearTablas() {
		profeJpa.crearTablasProfe();
	}

	/**
	 * Metodo agregar de la interfaz ILista
	 * 
	 * @param p le pasamos un profesor
	 * 
	 * @return nos duvuelve si se ha añadido o no
	 */
	@Override
	public void agregar(Profesor p) {

	}

	public void agregar(String nombre, String DNI, String tlf, String edad, Curso curso) throws CursoInvalidoException {
		//BDD
		profeJpa.insert(new Profesor(nombre,DNI,tlf,edad,curso));

    }

	/**
	 * Metodo buscar de la interfaz ILista
	 * 
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * 
	 * @return nos duvuelve un alumno si corresponde con el DNI
	 */
	@Override
	public Profesor buscar(String dni) {
		return profeJpa.readUno(dni);
	}

	/**
	 * Metodo eliminar de la interfaz ILista
	 * 
	 * @param dni le pasamos una cadena de texto, que haria referencia al DNI
	 * 
	 * @return nos duvuelve TRUE si se ha eliminado el profesore y FALSE sino
	 */
	@Override
	public void eliminar(String dni) {
		profeJpa.delete(dni);
	}

	/**
	 * Metodo listar de la interfaz ILista
	 *
	 * @return nos duvuelve todos los profesores que tenemos en la lista
	 */
	@Override
	public List<Profesor> listar() {
		return profeJpa.listaProfeDAO();
	}

	// listarTutores
	public List<Profesor> listarTutores() {
		return profeJpa.listaProfeTutorDAO();
	}

	public List<Profesor> ordenarAlfabeticamente(){
		return profeJpa.ordenarProfeAlfDAO();
	}

	public void agregarAsigProfe(String dni, String asig) {
//		// recorremos nuesta lista de alumnos
//		for (Profesor profe : listar()) {
//			// comprobamos si el DNI que tenemos es igual al DNI que nos pasan como
//			// parametro
//			if (profe.getDNI().equalsIgnoreCase(dni))
//				// si es correto agregamos la nota
//				profe.agregarAsignatura(asig);
//		}

		profeJpa.insertAsig(dni, asig);
	}

	public List<Profesor> coincidenciaExactaNombre(String name){
		return profeJpa.coincidenciaExactaNombre(name);
	}
	public List<Profesor> contienePalabraClaveNombre(String name){
		return profeJpa.contienePalabraClaveNombre(name);
	}
	public List<Profesor> empiezaPorNombre(String name){
		return profeJpa.empiezaPorNombre(name);
	}
	public List<Profesor> terminaEnNombre(String name){
		return profeJpa.terminaEnNombre(name);
	}
	public List<Profesor> coincidenciaExactaDni(String dni){
		return profeJpa.coincidenciaExactaDni(dni);
	}
	public List<Profesor> contienePalabraClaveDni(String dni){
		return profeJpa.contienePalabraClaveDni(dni);
	}
	public List<Profesor> empiezaPorDni(String dni){
		return profeJpa.empiezaPorDni(dni);
	}
	public List<Profesor> terminaEnDni(String dni){
		return profeJpa.terminaEnDni(dni);
	}
	public List<Profesor> agruparAsignraturaProf(String asignatura){
		return profeJpa.agruparAsignraturaProf(asignatura);
	}

}
