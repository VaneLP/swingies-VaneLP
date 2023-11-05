package controlador;

import modelo.Profesor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Clase ListaProfesores implementa la ILista para obtener sus metodos
public class ListaProfesores implements ILista<Profesor> {
	// atributos de la clase ListaProfesores
	private List<Profesor> listaProfesores = new ArrayList<Profesor>();

	// getter lista profesores
	public List<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	// ---- METODOS ----
	/**
	 * Metodo agregar de la interfaz ILista
	 * 
	 * @param p le pasamos un profesor
	 * 
	 * @return nos duvuelve si se ha añadido o no
	 */
	@Override
	public boolean agregar(Profesor p) {
		return listaProfesores.add(p);
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
		// recorremos la lista de alumnos
		for (Profesor profe : listaProfesores) {
			// controbamos si el dni es igual que nos pasan como parametro
			if (profe.getDNI().equalsIgnoreCase(dni))
				// devolvemos el alumno
				return profe;
		}

		System.out.println("Profesor no encontrado");
		return null;
	}

	/**
	 * Metodo eliminar de la interfaz ILista
	 * 
	 * @param DNI le pasamos una cadena de texto, que haria referencia al DNI
	 * 
	 * @return nos duvuelve TRUE si se ha eliminado el profesore y FALSE sino
	 */
	@Override
	public boolean eliminar(String DNI) {
		// variables
		int indice = 0;
		boolean encontrado = false;

		// recorremos la listaProfesores
		for (int i = 0; i != listaProfesores.size(); i++) {
			// si el DNI del profesor es igual al dni que nos pasan
			if (DNI.equals(listaProfesores.get(i).getDNI())) {
				// guardamos en nuestra variable el indice en el que estamos
				indice = i;
				// cambiamos el booleano a TRUE porque hemos encontrado el curso
				encontrado = true;
				// salimos
				break;
			}
		}
		// si el booleano es verdadero
		if (encontrado)
			// eliminamos de nuestra lista el curso en el indice que hemos guardado en
			// nuestra variable
			listaProfesores.remove(indice);

		System.out.print("Alumno eliminado: " + encontrado + "\n");

		// si no se a encontrado el curso devolvemos FALSE
		return encontrado;
	}

	/**
	 * Metodo MostrarProfesor
	 * 
	 * @param dni, le pasamos una cadena de texto que hace referencia al DNI
	 * 
	 * @return nos devuelve un prodesor si corresponde el DNI
	 */
	public Profesor mostrarProfesor(String dni) {
		// recorremos la listaProfesores
		for (Profesor profesor : listaProfesores) {
			// si el dni del profesor es igual al pasado por parametro
			if (profesor.getDNI().equalsIgnoreCase(dni))
				// devolvemos al profesor
				return profesor;
		}

		// si no devolvemos nulo
		return null;
	}

	/**
	 * Metodo eliminarAsignatuasProfesor
	 * 
	 * @param dni, le pasamos una cadena de texto que hace referencia al DNI
	 */
	public void eliminarAsignaturasProfesor(String dni) {
		// recorremos la lista
		for (Profesor profesor : listaProfesores) {
			// si el dni del profesor corresponde al que pasamos por parametro
			if (profesor.getDNI().equalsIgnoreCase(dni))
				// Eliminamos las asginaturas del profesor
				profesor.eliminarAsignaturas();
		}
	}

	/**
	 * Metodo listar de la interfaz ILista
	 *
	 * @return nos duvuelve todos los profesores que tenemos en la lista
	 */
	@Override
	public void listar() {
		// hacemos un foreach para recorrer la lista
		for (Profesor p : listaProfesores) {
			// mostramos el curso
			System.out.println(p);
		}

	}

	// listarTutores
	public ArrayList<Profesor> listarTutores() {
		// creamos una nueva lista
		ArrayList<Profesor> listaTut = new ArrayList<Profesor>();

		// recorremos los profesores
		for (Profesor profesor : listaProfesores) {
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
		for (Profesor p : listaProfesores) {
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

	public void ordenarAlfabeticamente(){
		/*
		 * cogemos nuestra lista de profesores y con el metodo . sort que tienen los
		 * arrayList hacemos un new Comparator de la clase Profesor
		 */
		listaProfesores.sort(new Comparator<Profesor>() {
			// sobreescribimos el metodo que tiene el Comparator de compare, al cual le
			// pasamos 2 profes
			@Override
			public int compare(Profesor p1, Profesor p2) {
				// nos devuelve ya la comparion entre los nombre de los dos profes ordenandolos
				// asi
				return p1.getNombre().compareTo(p2.getNombre());
			}
		});

	}

}
