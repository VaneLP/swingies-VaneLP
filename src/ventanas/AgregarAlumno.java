package ventanas;

import controlador.ListaAlumnos;
import controlador.ListaCursos;
import modelo.Alumno;
import modelo.Curso;
import modelo.CursoInvalidoException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarAlumno extends JDialog {
    //atributos
    private JTextField textNombre;
    private JTextField textDni;
    private JTextField textTlf;
    private JTextField textEdad;
    private JPanel panelAgregar;
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JComboBox<String> cursoElegir;

    //atributos controlador
    private ListaAlumnos listaAlum;
    private ListaCursos listaCurso;

//-------------------------------------------------------------------------------------------------------------
    //constructor
    public AgregarAlumno(Menu m, boolean b, ListaAlumnos listaAlum, ListaCursos listaCurso) {
        super(m, b);
        this.listaAlum = listaAlum;
        this.listaCurso = listaCurso;

        //para que se muestre en la ventana nuestro panel
        setContentPane(panelAgregar);

        //titulo
        setTitle("Agregar alumno");

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
                //agregamos a l alumno
                try {
                    if (textNombre.getText().isBlank() && textDni.getText().isBlank() && textTlf.getText().isBlank() && textEdad.getText().isBlank()) {
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
                    } else if (!textTlf.getText().matches("\\d{9}")) {
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que el telefono sea incorrecto. Intentalo de nuevo.");
                    }
                    else if (!textEdad.getText().matches("\\d{2}")) {
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que la edad sea incorrecta. Intentalo de nuevo.");
                    }
                    else if (!textDni.getText().matches("[0-9]{8}[A-Za-z]")) {
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que el DNI sea incorrecto. Intentalo de nuevo.");
                    }
                    else if(!textNombre.getText().matches("[A-Za-z]+")){
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, puede que el nombre sea incorrecto. Intentalo de nuevo.");
                    }
                    else {
                        listaAlum.agregar(new Alumno(textNombre.getText(), textDni.getText(), textTlf.getText(),
                                textEdad.getText(), (String) cursoElegir.getSelectedItem()));
                        dispose();
                    }

                } catch (CursoInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, "Ups... algo salió mal, el curso no puede ser nulo. Intentalo de nuevo o crea un nuevo curso.");
                    System.out.println(ex.getMessage());
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

//-------------------------------------------------------------------------------------------------------------
    // ---- METODOS ----
    private void rellenar() {
        for (Curso c : listaCurso.getListaCursos()) {
            cursoElegir.addItem(c.getNombre());
        }
    }


}
