package modelo;

import jakarta.persistence.Entity;

//JPA
@Entity
public class Curso {
    //JPA
    
    //----------
    // atributos de la clase Curso
    private final int id;
    private static int contador = 1;
    private String nombre;
    public static final Curso cursoNulo= new Curso(-1,"Ninguno");

    //JPA
    public Curso() {}

    /*
     * constructor al que le pasamos unicamente el nombre, porque no queremos que el
     * codigo del curso se pueda modificar y para ello creamos un atributo estatico
     * llamado contador el cual luego se lo asignaremos a nuestro codigo y cada vez
     * que se genere un curso se pondra automaticamente un nuevo codigo unico
     */
    public Curso(String nombre) {
        this.nombre = nombre;

        // asignamos al codigo el contador
        this.id = contador;
        // hacemos que el contador aumente para que el siguiente codigo sea diferente
        contador++;
    }

    // ---- CARGA MASIVA (ACCESOS - PRACTICA 3) ----
    //constructor para poder pasarle el codigo y el nombre cuando leemos un fichero
    public Curso(int codigo, String nombre) {
        this.id = codigo;
        this.nombre = nombre;
    }

    // getters y setters
    // codigo
    public int getId() {
        return id;
    }

    // nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo toString
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return nombre;
    }

}
