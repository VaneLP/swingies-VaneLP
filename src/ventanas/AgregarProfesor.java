package ventanas;

import controlador.ControladorCursos;
import controlador.ControladorProfesores;
import modelo.Curso;
import modelo.CursoInvalidoException;
import modelo.Profesor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarProfesor extends JDialog {
    //atributos
    private JTextField textNombre;
    private JTextField textDni;
    private JTextField textTlf;
    private JTextField textEdad;
    private JPanel panelAgregar;
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JComboBox<Curso> cursoElegir;

    //atributos controlador
    private ControladorProfesores listaProfe;
    private ControladorCursos controladorCursos;

    //constructor
    public AgregarProfesor(Menu m, boolean b, ControladorProfesores listaProfe, ControladorCursos listaCur) {
        super(m, b);
        this.listaProfe = listaProfe;
        this.controladorCursos = listaCur;

        //para que se muestre en la ventana nuestro panel
        setContentPane(panelAgregar);

        //titulo
        setTitle("Agregar profesor");

        //el tamaño de la ventana
        setSize(300, 300);

        //para que se centre en medio de la pantalla
        setLocationRelativeTo(null);

        rellenar();

//-------------------------------------------------------------------------------------------------------------
        // ---- LISTENERS ----
        // ---- LISTENER BOTON ACEPTAR ----
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cursoElegir.getSelectedItem().equals("No")) {
                    try {
                        agregarProf();
                    } catch (CursoInvalidoException ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
                    }
                } else {
                    try {
                        agregarProf();
                    } catch (CursoInvalidoException ex) {
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, el curso no puede ser nulo. Intentalo de nuevo o crea un nuevo curso.");
                        System.out.println(ex.getMessage());
                    }

                }
            }
        });

        // ---- LISTENER BOTON CANCELAR ----
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cerramos la ventana
                dispose();
            }
        });
    }

    private void agregarProf() throws CursoInvalidoException {
        if (textNombre.getText().isBlank() && textDni.getText().isBlank() && textTlf.getText().isBlank() && textEdad.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
        } else if (!textTlf.getText().matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que el telefono sea incorrecto. Intentalo de nuevo.");
        } else if (!textEdad.getText().matches("\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que la edad sea incorrecta. Intentalo de nuevo.");
        } else if (!textDni.getText().matches("[0-9]{8}[A-Za-z]")) {
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que el DNI sea incorrecto. Intentalo de nuevo.");
        } else if(!textNombre.getText().matches("[A-Za-z]+")){
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que el nombre sea incorrecto. Intentalo de nuevo.");
        }else {
            listaProfe.agregar(textNombre.getText(), textDni.getText(), textTlf.getText(),
                    textEdad.getText(), (Curso) cursoElegir.getSelectedItem());

            dispose();
        }
    }

    //-------------------------------------------------------------------------------------------------------------
    // ---- METODOS ----
    private void rellenar() {
        cursoElegir.addItem(Curso.cursoNulo);

        for (Curso c : controladorCursos.listar()) {
            cursoElegir.addItem(c);
        }
    }

}