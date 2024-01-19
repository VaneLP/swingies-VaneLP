package controlador;

import controlador.DAO.AlumnoDAO;
import controlador.DAO.JDBC.AlumnoDAOJDBCImpl;
import modelo.Alumno;
import java.util.ArrayList;
import java.util.List;

// Clase ListaAlumnos implementa la ILista para obtener sus metodos
public class ControladorAlumnos implements ILista<Alumno> {
	private List<Double> listNota = new ArrayList<Double>();
	private AlumnoDAO alumDao= new AlumnoDAOJDBCImpl();

	private double allNotas = 0, media;
	private int cont = 0;


	// ---- METODOS ----
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
		//alumDao.listaAlumAproDAO();
		// creamos una nueva lista
//		ArrayList<Alumno> listaApro = new ArrayList<Alumno>();
//
//		// recorremos la lista de los alumnos
//		for (Alumno alumno : listaAlumnos) {
//			// a la variable listNota le añadimos las notas del alumno
//			listNota = alumno.getListaNotas();
//
//			// recorremos la listNotas
//			for (Double nota : listNota) {
//				// y a la variable allNotas le asignamos la misma variabla + nota para tener
//				// todas las notas sumadas
//				allNotas = allNotas + nota;
//				// tenemos un contador que va autoincrementando
//				cont++;
//			}
//
//			// sacamos la media con todas las notas y el contador
//			media = allNotas / cont;
//
//			// si la media es mayor o igual a 5 esta aprobado
//			if (media >= 5)
//				// añadimos al alumno a la listaApro
//				listaApro.add(alumno);
//
//			// reseteamos las variables para que no den problemas con otros alumnos
//			allNotas = 0;
//			cont = 0;
//		}
//
//		// devolvemos la lista
//		return listaApro;
		return null;
	}

	/**
	 * Metodo listarSuspensos
	 * 
	 * @return devuelve una nueva lista con los alumnos suspensos
	 */
	public List<Alumno> listarSuspensos() {
		//alumDao.listaAlumSusDAO();
//		// creamos una nueva lista
//		ArrayList<Alumno> listaSus = new ArrayList<Alumno>();
//
//		// recorremos la lista de los alumnos
//		for (Alumno alumno : listaAlumnos) {
//			// a la variable listNota le añadimos las notas del alumno
//			listNota = alumno.getListaNotas();
//
//			// recorremos la listNotas
//			for (Double nota : listNota) {
//				// y a la variable allNotas le asignamos la misma variabla + nota para tener
//				// todas las notas sumadas
//				allNotas = allNotas + nota;
//				// tenemos un contador que va autoincrementando
//				cont++;
//			}
//
//			// sacamos la media con todas las notas y el contador
//			media = allNotas / cont;
//
//			// si la media es menor a 5 esta aprobado
//			if (media < 5)
//				// añadimos al alumno a la listaSus
//				listaSus.add(alumno);
//
//			// reseteamos las variables para que no den problemas con otros alumnos
//			allNotas = 0;
//			cont = 0;
//		}
//
//		// devolvemos la lista
//		return listaSus;
		return null;
	}
}
