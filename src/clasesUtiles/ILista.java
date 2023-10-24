package clasesUtiles;

//Interfaz la cual puede contener <T> porque no sabemos que clase va a contener
interface ILista<T> {
	// ---- METODOS ----
	/**
	 * Metodo agregar
	 * 
	 * @param object, le pasamos un objeto porque aun no sabemos la clase
	 * @return True or false
	 */
	boolean agregar(T object);

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
	 * @return True o false
	 */
	boolean eliminar(String s);

	/**
	 * Metodo listar
	 * 
	 * No devuelve nada
	 */
	void listar();
}
