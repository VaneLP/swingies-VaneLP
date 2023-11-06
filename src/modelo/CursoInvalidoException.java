package modelo;

public class CursoInvalidoException extends Exception{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    public CursoInvalidoException(String message) {
        super(ANSI_RED+message+ANSI_RESET);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
