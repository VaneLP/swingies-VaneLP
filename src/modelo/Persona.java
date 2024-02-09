package modelo;

import jakarta.persistence.*;

//JPA
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rol_instituto")
public abstract class Persona {
    //JPA
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name="Nombre")
    private String nombre;

    @Column(nullable = false, unique = true)
    private String DNI;

    @Column(nullable = true, name = "telefono")
    private int tlf;

    @Column(nullable = false)
    private int edad;

    //JPA
    public Persona() {}

    // constructor al cual le pasamos el nombre, el DNI, el tlf y la edad
    protected Persona(long id, String nombre, String DNI, String tlf, String edad) {
        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
            this.id = id;
        }
    }

    public Persona(String nombre, String DNI, String tlf, String edad) {
        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
        }
    }

    // getters y setters
    // nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // DNI
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dni) {
        if(DNI.matches("[0-9]{8}[A-Za-z]"))
            this.DNI = dni;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // tlf
    public int getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        if(tlf.matches("\\d{9}"))
            this.tlf = Integer.parseInt(tlf);
    }

    // edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        if(edad.matches("\\d{2}"))
            this.edad = Integer.parseInt(edad);
    }

    /**
     * Metodo toString
     *
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return String.format("""
                Nombre: %s
                DNI: %s
                TLF: %d
                Edad: %d
                """, nombre, DNI, tlf, edad);
    }
}
