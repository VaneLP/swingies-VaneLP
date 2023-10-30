package modelo;

public abstract class Persona {
    // atributos de la clase Persona
    private String nombre;
    private String DNI;
    private int tlf;
    private int edad;

    // constructor al cual le pasamos el nombre, el DNI, el tlf y la edad
    public Persona(String nombre, String DNI, int tlf, int edad) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.tlf = tlf;
        this.edad = edad;
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
    public void setDNI(String dNI) {
        DNI = dNI;
    }

    // tlf
    public int getTlf() {
        return tlf;
    }
    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    // edad
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
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
