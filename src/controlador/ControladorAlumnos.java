package controlador;

import controlador.DAO.API.AlumnoDAOAPIImpl;
import controlador.DAO.AlumnoDAO;
import modelo.Alumno;
import modelo.Curso;
import modelo.CursoInvalidoException;

import java.util.ArrayList;
import java.util.List;

// Clase ListaAlumnos implementa la ILista para obtener sus metodos
public class ControladorAlumnos implements ILista<Alumno> {
	private List<Double> listNota = new ArrayList<Double>();
	//JDBC
	//private AlumnoDAO alumDao= new AlumnoDAOJDBCImpl();

	//JPP
	// private AlumnoDAO alumJpa = new AlumnoDAOJPAImpl();

	//API
	private AlumnoDAO alumApi = new AlumnoDAOAPIImpl();

	private double allNotas = 0, media;
	private int cont = 0;


	// ---- METODOS ----

	@Override
	public void crearTablas() {
		alumApi.crearTablasAlum();
	}

	/**
	 * Metodo agregar de la interfaz ILista
	 *
	 * @param a le pasamos un alumno
	 *
	 * @return nos duvuelve si se ha añadido o no
	 */
	@Override
	public void agregar(Alumno a) {

	}

	public void agregar(String nombre, String DNI, String tlf, String edad, Curso curso) throws CursoInvalidoException {
		//BDD
		alumApi.insert(new Alumno(nombre,DNI,tlf,edad,curso));

	}
	/**
	 * Metodo buscar de la interfaz ILista
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * @return nos duvuelve un alumno si corresponde con el DNI
	 */
	@Override
	public Alumno buscar(String dni) {
		//BBDD
		return alumApi.readUno(dni);
	}

	/**
	 * Metodo eliminar de la interfaz ILista
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * @return nos duvuelve TRUE si se ha eliminado el alumno y FALSE sino
	 */
	@Override
	public void eliminar(String dni) {
		//BBDD
		alumApi.delete(dni);
	}

	/**
	 * Metodo listar de la interfaz ILista
	 * @return nos duvuelve todos los alumnos que tenemos en la lista
	 */
	@Override
	public List<Alumno> listar() {
		//BBDD
		return alumApi.listaAlumDAO();
	}

	/**
	 * Metodo ordenarAlfabeticamente
	 * Nos devuelve la lista ordenada alfabeticamente
	 */
	public List<Alumno> ordenarAlfabeticamente() {
		return alumApi.ordenarAlumAlfDAO();
	}

	/**
	 * Metodo agregarNotaAlumno
	 */
	public void agregarNotaAlumno(Alumno a, double nota) {
		alumApi.insertNota(a,nota);
	}
	public void agregarNotaAlumno(String dni, double nota) {
		alumApi.insertNota(dni,nota);
	}


	/**
	 * Metodo listarAprobados
	 *
	 * @return devuelve una nueva lista con los alumnos aprobados
	 */
	public List<Alumno> listarAprobados() {
		return alumApi.listaAlumAproDAO();
	}

	/**
	 * Metodo listarSuspensos
	 * 
	 * @return devuelve una nueva lista con los alumnos suspensos
	 */
	public List<Alumno> listarSuspensos() {
		return alumApi.listaAlumSusDAO();
	}
	public List<Alumno> coincidenciaExactaNombre(String name){
		return alumApi.coincidenciaExactaNombre(name);
	}
	public List<Alumno> contienePalabraClaveNombre(String name){
		return alumApi.contienePalabraClaveNombre(name);
	}
	public List<Alumno> empiezaPorNombre(String name){
		return alumApi.empiezaPorNombre(name);
	}
	public List<Alumno> terminaEnNombre(String name){
		return alumApi.terminaEnNombre(name);
	}

	public List<Alumno> coincidenciaExactaDni(String dni){
		return alumApi.coincidenciaExactaDni(dni);
	}
	public List<Alumno> contienePalabraClaveDni(String dni){
		return alumApi.contienePalabraClaveDni(dni);
	}
	public List<Alumno> empiezaPorDni(String dni){
		return alumApi.empiezaPorDni(dni);
	}
	public List<Alumno> terminaEnDni(String dni){
		return alumApi.terminaEnDni(dni);
	}
	public List<Alumno> notaMediaAlum(Double mediia){
		return alumApi.notaMediaAlum(mediia);
	}
	public List<Alumno> profesorTutorAlum(String nombreTutor){
		return alumApi.profesorTutorAlum(nombreTutor);
	}

	public List<Alumno> buscarCursoAlum(List<String> listaCur){
		return alumApi.buscarCursoAlum(listaCur);
	}
}
