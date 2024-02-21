package modelo;

import jakarta.persistence.*;
import java.util.List;

//JPA
@Entity
@Table(name = "Cursos")
public class Curso {
    //JPA
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    //@OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Alumno> listaAlumnos;
    //@OneToOne(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //private Profesor listaProfesor;


    //----------
    // atributos de la clase Curso
//    private static int contador = 1;

    @Column(nullable = false)
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

//        // asignamos al codigo el contador
//        this.id = contador;
//        // hacemos que el contador aumente para que el siguiente codigo sea diferente
//        contador++;
    }

    // ---- CARGA MASIVA (ACCESOS - PRACTICA 3) ----
    //constructor para poder pasarle el codigo y el nombre cuando leemos un fichero
    public Curso(long codigo, String nombre) {
        this.id = codigo;
        this.nombre = nombre;
    }

    // getters y setters
    // codigo
    public long getId() {
        return id;
    }

    // nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//    public List<Alumno> getListaAlumnos() {
//        return listaAlumnos;
//    }

    /**
     * Metodo toString
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return nombre;
    }

}
