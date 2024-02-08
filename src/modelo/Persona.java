//package modelo;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//@Entity
//public abstract class Persona {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private long idP;
//
//
//    // atributos de la clase Persona
//    private String nombre;
//    private String DNI;
//    private int tlf;
//    private int edad;
//
//    // constructor al cual le pasamos el nombre, el DNI, el tlf y la edad
//    protected Persona(String nombre, String DNI, String tlf, String edad) {
//        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
//            this.nombre = nombre;
//            this.DNI = DNI;
//            this.tlf = Integer.parseInt(tlf);
//            this.edad = Integer.parseInt(edad);
//        }
//    }
//
//    public Persona() {
//
//    }
//
//    // getters y setters
//    // nombre
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    // DNI
//    public String getDNI() {
//        return DNI;
//    }
//
//    public void setDNI(String dni) {
//        if(DNI.matches("[0-9]{8}[A-Za-z]"))
//            this.DNI = dni;
//    }
//
//    // tlf
//    public int getTlf() {
//        return tlf;
//    }
//
//    public void setTlf(String tlf) {
//        if(tlf.matches("\\d{9}"))
//            this.tlf = Integer.parseInt(tlf);
//    }
//
//    // edad
//    public int getEdad() {
//        return edad;
//    }
//
//    public void setEdad(String edad) {
//        if(edad.matches("\\d{2}"))
//            this.edad = Integer.parseInt(edad);
//    }
//
//    /**
//     * Metodo toString
//     *
//     * @return una cadena de texto formateada
//     */
//    @Override
//    public String toString() {
//        return String.format("""
//                Nombre: %s
//                DNI: %s
//                TLF: %d
//                Edad: %d
//                """, nombre, DNI, tlf, edad);
//    }
//}
