package controlador;

import controlador.DAO.AlumnoDAO;
import controlador.DAO.JDBC.AlumnoDAOJDBCImpl;
import modelo.Alumno;
import modelo.Curso;

import java.util.ArrayList;
import java.util.List;

// Clase ListaAlumnos implementa la ILista para obtener sus metodos
public class ControladorAlumnos implements ILista<Alumno> {
	private List<Double> listNota = new ArrayList<Double>();
	private AlumnoDAO alumDao= new AlumnoDAOJDBCImpl();

	private double allNotas = 0, media;
	private int cont = 0;


	// ---- METODOS ----

	@Override
	public void crearTablas() {
		alumDao.crearTablasAlum();
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
		//BBDD
		alumDao.insert(a);
	}

	/**
	 * Metodo buscar de la interfaz ILista
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * @return nos duvuelve un alumno si corresponde con el DNI
	 */
	@Override
	public Alumno buscar(String dni) {
		//BBDD
		return alumDao.readUno(dni);
	}

	/**
	 * Metodo eliminar de la interfaz ILista
	 * @param dni, le pasamos una cadena de texto, que haria referencia al DNI
	 * @return nos duvuelve TRUE si se ha eliminado el alumno y FALSE sino
	 */
	@Override
	public void eliminar(String dni) {
		//BBDD
		alumDao.delete(dni);
	}

	/**
	 * Metodo listar de la interfaz ILista
	 * @return nos duvuelve todos los alumnos que tenemos en la lista
	 */
	@Override
	public List<Alumno> listar() {
		//BBDD
		return alumDao.listaAlumDAO();
	}

	/**
	 * Metodo ordenarAlfabeticamente
	 * Nos devuelve la lista ordenada alfabeticamente
	 */
	public List<Alumno> ordenarAlfabeticamente() {
		return alumDao.ordenarAlumAlfDAO();
	}

	/**
	 * Metodo agregarNotaAlumno
	 */
	public void agregarNotaAlumno(Alumno a, double nota) {
		alumDao.insertNota(a,nota);
	}
	public void agregarNotaAlumno(String dni, double nota) {
		alumDao.insertNota(dni,nota);
	}


	/**
	 * Metodo listarAprobados
	 *
	 * @return devuelve una nueva lista con los alumnos aprobados
	 */
	public List<Alumno> listarAprobados() {
		return alumDao.listaAlumAproDAO();
	}

	/**
	 * Metodo listarSuspensos
	 * 
	 * @return devuelve una nueva lista con los alumnos suspensos
	 */
	public List<Alumno> listarSuspensos() {
		return alumDao.listaAlumSusDAO();
	}
	public List<Alumno> coincidenciaExactaNombre(String name){
		return alumDao.coincidenciaExactaNombre(name);
	}
	public List<Alumno> contienePalabraClaveNombre(String name){
		return alumDao.contienePalabraClaveNombre(name);
	}
	public List<Alumno> empiezaPorNombre(String name){
		return alumDao.empiezaPorNombre(name);
	}
	public List<Alumno> terminaEnNombre(String name){
		return alumDao.terminaEnNombre(name);
	}

	public List<Alumno> coincidenciaExactaDni(String dni){
		return alumDao.coincidenciaExactaDni(dni);
	}
	public List<Alumno> contienePalabraClaveDni(String dni){
		return alumDao.contienePalabraClaveDni(dni);
	}
	public List<Alumno> empiezaPorDni(String dni){
		return alumDao.empiezaPorDni(dni);
	}
	public List<Alumno> terminaEnDni(String dni){
		return alumDao.terminaEnDni(dni);
	}
	public List<Alumno> notaMediaAlum(int mediia){
		return alumDao.notaMediaAlum(mediia);
	}
	public List<Alumno> profesorTutorAlum(String nombreTutor){
		return alumDao.profesorTutorAlum(nombreTutor);
	}

	public List<Alumno> buscarCursoAlum(List<String> listaCur){
		return alumDao.buscarCursoAlum(listaCur);
	}
}
