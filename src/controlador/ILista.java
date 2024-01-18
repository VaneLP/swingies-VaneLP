package controlador;

import java.util.List;

//Interfaz la cual puede contener <T> porque no sabemos que clase va a contener
interface ILista<T> {
	// ---- METODOS ----
	/**
	 * Metodo agregar
	 * 
	 * @param object, le pasamos un objeto porque aun no sabemos la clase
	 */
	void agregar(T object);

	/**
	 * Metodo buscar
	 * 
	 * @param s, le pasamos un String
	 * @return devolvera un string
	 */
	T buscar(String s);

	/**
	 * Metodo eliminar
	 * 
	 * @param s, le pasamos un String
	 */
	void eliminar(String s);

	/**
	 * Metodo listar
	 * <p>
	 * No devuelve nada
	 */
	List<T> listar();
}
