package controlador;

import controlador.DAO.AlumnoDAO;
import controlador.DAO.JPA.AlumnoDAOJPAImpl;
import modelo.Alumno;
import modelo.Curso;
import modelo.CursoInvalidoException;
import modelo.Profesor;

import java.util.ArrayList;
import java.util.List;

// Clase ListaAlumnos implementa la ILista para obtener sus metodos
public class ControladorAlumnos implements ILista<Alumno> {
	private List<Double> listNota = new ArrayList<Double>();
	//JDBC
	//private AlumnoDAO alumDao= new AlumnoDAOJDBCImpl();

	//JPA
	private AlumnoDAO alumJpa = new AlumnoDAOJPAImpl();

	private double allNotas = 0, media;
	private int cont = 0;


	// ---- METODOS ----

	@Override
	public void crearTablas() {
		alumJpa.crearTablasAlum();
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
		alumJpa.insert(new Alumno(nombre,DNI,tlf,edad,curso));

	}
	/**
	 * Metodo buscar de la interfaz ILista
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * @return nos duvuelve un alumno si corresponde con el DNI
	 */
	@Override
	public Alumno buscar(String dni) {
		//BBDD
		return alumJpa.readUno(dni);
	}

	/**
	 * Metodo eliminar de la interfaz ILista
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * @return nos duvuelve TRUE si se ha eliminado el alumno y FALSE sino
	 */
	@Override
	public void eliminar(String dni) {
		//BBDD
		alumJpa.delete(dni);
	}

	/**
	 * Metodo listar de la interfaz ILista
	 * @return nos duvuelve todos los alumnos que tenemos en la lista
	 */
	@Override
	public List<Alumno> listar() {
		//BBDD
		return alumJpa.listaAlumDAO();
	}

	/**
	 * Metodo ordenarAlfabeticamente
	 * Nos devuelve la lista ordenada alfabeticamente
	 */
	public List<Alumno> ordenarAlfabeticamente() {
		return alumJpa.ordenarAlumAlfDAO();
	}

	/**
	 * Metodo agregarNotaAlumno
	 */
	public void agregarNotaAlumno(Alumno a, double nota) {
		alumJpa.insertNota(a,nota);
	}
	public void agregarNotaAlumno(String dni, double nota) {
		alumJpa.insertNota(dni,nota);
	}


	/**
	 * Metodo listarAprobados
	 *
	 * @return devuelve una nueva lista con los alumnos aprobados
	 */
	public List<Alumno> listarAprobados() {
		return alumJpa.listaAlumAproDAO();
	}

	/**
	 * Metodo listarSuspensos
	 * 
	 * @return devuelve una nueva lista con los alumnos suspensos
	 */
	public List<Alumno> listarSuspensos() {
		return alumJpa.listaAlumSusDAO();
	}
	public List<Alumno> coincidenciaExactaNombre(String name){
		return alumJpa.coincidenciaExactaNombre(name);
	}
	public List<Alumno> contienePalabraClaveNombre(String name){
		return alumJpa.contienePalabraClaveNombre(name);
	}
	public List<Alumno> empiezaPorNombre(String name){
		return alumJpa.empiezaPorNombre(name);
	}
	public List<Alumno> terminaEnNombre(String name){
		return alumJpa.terminaEnNombre(name);
	}

	public List<Alumno> coincidenciaExactaDni(String dni){
		return alumJpa.coincidenciaExactaDni(dni);
	}
	public List<Alumno> contienePalabraClaveDni(String dni){
		return alumJpa.contienePalabraClaveDni(dni);
	}
	public List<Alumno> empiezaPorDni(String dni){
		return alumJpa.empiezaPorDni(dni);
	}
	public List<Alumno> terminaEnDni(String dni){
		return alumJpa.terminaEnDni(dni);
	}
	public List<Alumno> notaMediaAlum(Double mediia){
		return alumJpa.notaMediaAlum(mediia);
	}
	public List<Alumno> profesorTutorAlum(String nombreTutor){
		return alumJpa.profesorTutorAlum(nombreTutor);
	}

	public List<Alumno> buscarCursoAlum(List<String> listaCur){
		return alumJpa.buscarCursoAlum(listaCur);
	}
}
