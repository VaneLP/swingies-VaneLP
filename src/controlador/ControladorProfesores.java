package controlador;

import controlador.DAO.JDBC.ProfesorDAOJDBCImpl;
import controlador.DAO.ProfesorDAO;
import modelo.Profesor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Clase ListaProfesores implementa la ILista para obtener sus metodos
public class ControladorProfesores implements ILista<Profesor> {
	private ProfesorDAO profeDao= new ProfesorDAOJDBCImpl();

	// ---- METODOS ----
	/**
	 * Metodo agregar de la interfaz ILista
	 * 
	 * @param p le pasamos un profesor
	 * 
	 * @return nos duvuelve si se ha añadido o no
	 */
	@Override
	public void agregar(Profesor p) {
		//BDD
		profeDao.insert(p);
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
		return profeDao.readUno(dni);
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
		profeDao.delete(dni);
	}

	/**
	 * Metodo listar de la interfaz ILista
	 *
	 * @return nos duvuelve todos los profesores que tenemos en la lista
	 */
	@Override
	public List<Profesor> listar() {
		return profeDao.listaProfeDAO();
	}

	// listarTutores
	public ArrayList<Profesor> listarTutores() {
		// creamos una nueva lista
		ArrayList<Profesor> listaTut = new ArrayList<Profesor>();

		// recorremos los profesores
		for (Profesor profesor : listar()) {
			// si el profesor su curso no es nulo es tutor
			if (!profesor.getCurso().equals("No"))
				// añadimos a la listaTut el profesor
				listaTut.add(profesor);
		}

		// devuelve la lista
		return listaTut;
	}

	/**
	 * Metodo listar por asignatura
	 * 
	 * @param asignatura, le pasamos una cadena de texto con el nombre de la
	 *                    asignatura
	 */
	public void listarPorAsignaturas(String asignatura) {
		System.out.println("Asignatura buscada: " + asignatura);

		// recorremos la listaProfesores
		for (Profesor p : listar()) {
			// ahora podemos recorrer la lista de asignaturas
			for (String asig : p.getListaAsignaturas()) {
				// si la asignatura en la que estamos es igual a la que pasamos como parametro
				if (asig.equalsIgnoreCase(asignatura)) {
					// mostramos al profesor
					System.out.println(p);
				}
			}
		}

	}

	public List<Profesor> ordenarAlfabeticamente(){
		return profeDao.ordenarProfeAlfDAO();
	}

	public void agregarAsigProfe(String dni, String asig) {
		// recorremos nuesta lista de alumnos
		for (Profesor profe : listar()) {
			// comprobamos si el DNI que tenemos es igual al DNI que nos pasan como
			// parametro
			if (profe.getDNI().equalsIgnoreCase(dni))
				// si es correto agregamos la nota
				profe.agregarAsignatura(asig);
		}
	}

}
