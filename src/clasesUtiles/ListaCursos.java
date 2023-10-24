package clasesUtiles;

import clasesPrincipales.Curso;

import java.util.ArrayList;
import java.util.List;

// Clase ListaCurso implementa la clasesUtiles.ILista para obtener sus metodos
public class ListaCursos implements ILista<Curso> {
	// atributos de la clase clasesUtiles.ListaCursos
	private List<Curso> listaCursos = new ArrayList<Curso>();

	// ---- METODOS ----
	/**
	 * Metodo agregar de la interfaz clasesUtiles.ILista
	 * 
	 * @param c le pasamos un curso
	 * 
	 * @return nos duvuelve si se ha añadido o no
	 */
	@Override
	public boolean agregar(Curso c) {
		return listaCursos.add(c);
	}

	/**
	 * Metodo buscar de la interfaz clasesUtiles.ILista
	 * 
	 * @param le pasamos una cadena de texto, que haria referencia al codigo
	 * 
	 * @return nos duvuelve un curso si corresponde con el codigo
	 */
	@Override
	public Curso buscar(String c) {
		// hacemos un foreach de la lista
		for (Curso curso : listaCursos) {
			// si el codigo es igual al que le hemos pasado como parametro
			if (curso.getCodigo() == Integer.parseInt(c))
				// nos devuelve el curso
				return curso;
		}

		// si no lo encuentra nos devuelve el curso nulo
		System.out.println("clases.Curso no encontrado");
		return null;
	}

	/**
	 * Metodo eliminar de la interfaz clasesUtiles.ILista
	 * 
	 * @param codigo le pasamos una cadena de texto, que haria referencia al codigo
	 * 
	 * @return nos duvuelve TRUE si se ha eliminado el curso y FALSE sino
	 */
	@Override
	public boolean eliminar(String codigo) {
		// variables
		int indice = 0;
		boolean encontrado = false;

		/*
		 * hacemos un for que empiece en 0 y continue mientras que el tamaño de la lista
		 * sea diferente
		 */
		for (int i = 0; i != listaCursos.size(); i++) {
			// si el codigo del parametro es igual al codigo de la listaCursos en la
			// posicion en la que estamos
			if (Integer.parseInt(codigo) == listaCursos.get(i).getCodigo()) {
				// guardamos en nuestra variable el indice en el que estamos
				indice = i;
				// cambiamos el booleano a TRUE porque hemos encontrado el curso
				encontrado = true;
			}
		}

		// si el booleano es verdadero
		if (encontrado)
			// eliminamos de nuestra lista el curso en el indice que hemos guardado en
			// nuestra variable
			listaCursos.remove(indice);

		System.out.print("clases.Curso eliminado: " + encontrado + "\n");

		// si no se a encontrado el curso devolvemos FALSE
		return encontrado;

	}

	/**
	 * Metodo listar de la interfaz clasesUtiles.ILista
	 * 
	 * @return nos duvuelve todos los cursos que tenemos en la lista
	 */
	@Override
	public void listar() {
		System.out.println("~~~~~~ LISTAR CURSOS ~~~~~~");
		
		// hacemos un foreach para recorrer la lista
		for (Curso curso : listaCursos) {
			// mostramos el curso
			System.out.println(curso);
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}
