package ventanas;

import controlador.ListaAlumnos;
import controlador.ListaCursos;
import modelo.Alumno;
import modelo.Curso;

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
    private JComboBox<String>  cursoElegir;

    //atributos controlador
    private ListaAlumnos listaAlum;
    private ListaCursos listaCurso;

//-------------------------------------------------------------------------------------------------------------
    //constructor
    public AgregarAlumno(Menu m, boolean b, ListaAlumnos listaAlum,ListaCursos listaCurso) {
        super(m,b);
        this.listaAlum=listaAlum;
        this.listaCurso =listaCurso;

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
                //todo
                if(cursoElegir.getSelectedItem() != null){
                    listaAlum.agregar(new Alumno(textNombre.getText(), textDni.getText(), Integer.parseInt(textTlf.getText()),
                            Integer.parseInt(textEdad.getText()), (String) cursoElegir.getSelectedItem()));
                    dispose();
                }
                else {
                    System.out.println("ERROR");
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
