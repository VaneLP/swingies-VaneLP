package controlador;

import controlador.DAO.AlumnoDAO;
import controlador.DAO.JDBC.AlumnoDAOJDBCImpl;
import modelo.Alumno;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase ListaAlumnos implementa la ILista para obtener sus metodos
public class ControladorAlumnos implements ILista<Alumno> {
	// atributos de la clase ListaAlumnos
	private List<Alumno> listaAlumnos = new ArrayList<Alumno>();
	private List<Double> listNota = new ArrayList<Double>();
	private AlumnoDAO alumDao= new AlumnoDAOJDBCImpl();

	private double allNotas = 0, media;
	private int cont = 0;

	// getter de la lista
	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	// ---- METODOS ----
	/**
	 * Metodo agregar de la interfaz ILista
	 * 
	 * @param a le pasamos un alumno
	 * 
	 * @return nos duvuelve si se ha añadido o no
	 */
	@Override
	public boolean agregar(Alumno a) {
		//BBDD
		alumDao.insert(a);

		return listaAlumnos.add(a);
	}

	/**
	 * Metodo buscar de la interfaz ILista
	 * 
	 * @param dni,le pasamos una cadena de texto, que haria referencia al DNI
	 * 
	 * @return nos duvuelve un alumno si corresponde con el DNI
	 */
	@Override
	public Alumno buscar(String dni) {
		// recorremos la lista de alumnos
		for (Alumno alum : listaAlumnos) {
			// controbamos si el dni es igual que nos pasan como parametro
			if (alum.getDNI().equalsIgnoreCase(dni))
				// devolvemos el alumno
				return alum;
		}

		return null;
	}

	/**
	 * Metodo eliminar de la interfaz ILista
	 * 
	 * @param DNI, le pasamos una cadena de texto, que haria referencia al DNI
	 * 
	 * @return nos duvuelve TRUE si se ha eliminado el alumno y FALSE sino
	 */
	@Override
	public boolean eliminar(String DNI) {
		// variables
		int indice = 0;
		boolean encontrado = false;

		// recorremos la listaAlumnos
		for (int i = 0; i != listaAlumnos.size(); i++) {
			// si el DNI del alumno es igual al dni que nos pasan
			if (DNI.equals(listaAlumnos.get(i).getDNI())) {
				// guardamos en nuestra variable el indice en el que estamos
				indice = i;
				// cambiamos el booleano a TRUE porque hemos encontrado el curso
				encontrado = true;
				// salimos
				break;
			}
		}

		//BBDD
		//todo buscar segun DNI el id
		alumDao.delete(1);

		// si el booleano es verdadero
		if (encontrado)
			// eliminamos de nuestra lista el curso en el indice que hemos guardado en
			// nuestra variable
			listaAlumnos.remove(indice);

		System.out.print("Alumno eliminado: " + encontrado + "\n");
		// si no se a encontrado el curso devolvemos FALSE
		return encontrado;

	}

	/**
	 * Metodo listar de la interfaz ILista
	 *
	 * @return nos duvuelve todos los alumnos que tenemos en la lista
	 */
	@Override
	public void listar() {
		//BBDD
		//todo id del alumno
		alumDao.read(1);

		// hacemos un foreach para recorrer la lista
		for (Alumno a : listaAlumnos) {
			// mostramos el curso
			System.out.println(a);
		}
	}

	/**
	 * Metodo ordenarAlfabeticamente
	 * Nos devuelve la lista ordenada alfabeticamente
	 */
	public void ordenarAlfabeticamente() {
		/*
		 * cogemos nuestra lista de alumnos y con el metodo . sort que tienen los
		 * arrayList hacemos un new Comparator de la clase clases.Alumno
		 */
		listaAlumnos.sort(new Comparator<Alumno>() {
			// sobreescribimos el metodo que tiene el Comparator de compare, al cual le
			// pasamos 2 alumnos
			@Override
			public int compare(Alumno a1, Alumno a2) {
				// nos devuelve ya la comparion entre los nombre de los dos alumnos ordenandolos
				// asi
				return a1.getNombre().compareToIgnoreCase(a2.getNombre());
			}
		});
	}

	/**
	 * Metodo agregarNotaAlumno
	 * 
	 * @param dni, nota le pasamos tanto el DNI del alumno para poder identificarlo
	 *             y ponerle correctamente la nota al alumno que queremos, como la
	 *             nota que queremos ponerle
	 */
	public void agregarNotaAlumno(String dni, double nota) {
		// recorremos nuesta lista de alumnos
		for (Alumno alumno : listaAlumnos) {
			// comprobamos si el DNI que tenemos es igual al DNI que nos pasan como
			// parametro
			if (alumno.getDNI().equalsIgnoreCase(dni))
				// si es correto agregamos la nota
				alumno.agregarNota(nota);
		}
	}

	/**
	 * Metodo eliminarNotasAlumno
	 * 
	 * @param dni, le pasamos el DNI del alumno para poder identinficarlo y poder
	 *             eliminarle todas las notas
	 */
	public void eliminarNotasAlumno(String dni) {
		// recorremos nuesta lista de alumnos
		for (Alumno alumno : listaAlumnos) {
			// comprobamos si el DNI que tenemos es igual al DNI que nos pasan como
			// parametro
			if (alumno.getDNI().equalsIgnoreCase(dni))
				// si es correto eliminamos todas las notas
				alumno.eliminarNotas();
		}
	}

	/**
	 * Metodo listarAprobados
	 * 
	 * @return devuelve una nueva lista con los alumnos aprobados
	 */
	public ArrayList<Alumno> listarAprobados() {
		// creamos una nueva lista
		ArrayList<Alumno> listaApro = new ArrayList<Alumno>();

		// recorremos la lista de los alumnos
		for (Alumno alumno : listaAlumnos) {
			// a la variable listNota le añadimos las notas del alumno
			listNota = alumno.getListaNotas();

			// recorremos la listNotas
			for (Double nota : listNota) {
				// y a la variable allNotas le asignamos la misma variabla + nota para tener
				// todas las notas sumadas
				allNotas = allNotas + nota;
				// tenemos un contador que va autoincrementando
				cont++;
			}

			// sacamos la media con todas las notas y el contador
			media = allNotas / cont;

			// si la media es mayor o igual a 5 esta aprobado
			if (media >= 5)
				// añadimos al alumno a la listaApro
				listaApro.add(alumno);

			// reseteamos las variables para que no den problemas con otros alumnos
			allNotas = 0;
			cont = 0;
		}
		
		// devolvemos la lista
		return listaApro;
	}

	/**
	 * Metodo listarSuspensos
	 * 
	 * @return devuelve una nueva lista con los alumnos suspensos
	 */
	public ArrayList<Alumno> listarSuspensos() {
		// creamos una nueva lista
		ArrayList<Alumno> listaSus = new ArrayList<Alumno>();

		// recorremos la lista de los alumnos
		for (Alumno alumno : listaAlumnos) {
			// a la variable listNota le añadimos las notas del alumno
			listNota = alumno.getListaNotas();

			// recorremos la listNotas
			for (Double nota : listNota) {
				// y a la variable allNotas le asignamos la misma variabla + nota para tener
				// todas las notas sumadas
				allNotas = allNotas + nota;
				// tenemos un contador que va autoincrementando
				cont++;
			}

			// sacamos la media con todas las notas y el contador
			media = allNotas / cont;

			// si la media es menor a 5 esta aprobado
			if (media < 5)
				// añadimos al alumno a la listaSus
				listaSus.add(alumno);

			// reseteamos las variables para que no den problemas con otros alumnos
			allNotas = 0;
			cont = 0;
		}
		
		// devolvemos la lista
		return listaSus;
	}
}
