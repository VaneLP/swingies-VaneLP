package ventanas;

import controlador.ListaCursos;
import controlador.ListaProfesores;
import modelo.Curso;
import modelo.ExcepcionCurnoInvalido;
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
    private JComboBox<String> cursoElegir;

    //atributos controlador
    private ListaProfesores listaProfe;
    private ListaCursos listaCur;

    //constructor
    public AgregarProfesor(Menu m, boolean b, ListaProfesores listaProfe, ListaCursos listaCur) {
        super(m, b);
        this.listaProfe = listaProfe;
        this.listaCur = listaCur;

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
                //agregamos a l alumno
                //todo

                if (cursoElegir.getSelectedItem().equals("No")) {

                    try {
                        if (!textNombre.getText().isBlank() && !textDni.getText().isBlank() && !textTlf.getText().isBlank() && !textEdad.getText().isBlank()) {
                            listaProfe.agregar(new Profesor(textNombre.getText(), textDni.getText(), textTlf.getText(),
                                    textEdad.getText(), "No"));

                            dispose();
                        } else
                            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

                    } catch (ExcepcionCurnoInvalido ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
                    }
                } else {
                    try {
                        if (!textNombre.getText().isBlank() && !textDni.getText().isBlank() && !textTlf.getText().isBlank() && !textEdad.getText().isBlank()) {
                            listaProfe.agregar(new Profesor(textNombre.getText(), textDni.getText(), textTlf.getText(),
                                    textEdad.getText(), (String) cursoElegir.getSelectedItem()));

                            dispose();
                        } else
                            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

                    } catch (ExcepcionCurnoInvalido ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
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

//-------------------------------------------------------------------------------------------------------------
    // ---- METODOS ----
    private void rellenar() {
        cursoElegir.addItem("No");

        for (Curso c : listaCur.getListaCursos()) {
            cursoElegir.addItem(c.getNombre());
        }
    }

}