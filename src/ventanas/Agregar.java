package ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agregar extends JDialog {
    //atributos
    private JTextField textNombre;
    private JTextField textDni;
    private JTextField textTlf;
    private JTextField textEdad;
    private JPanel panelAgregar;
    private JTextField textCur;
    private JButton cancelarButton;
    private JButton aceptarButton;


    public Agregar(Menu m, boolean b) {
        super(m,b);

        //para que se muestre en la ventana nuestro panel
        setContentPane(panelAgregar);

        //titulo
        setTitle("Agregar alumno");

        //el tamaño de la ventana
        setSize(300, 300);

        //para que se centre en medio de la pantalla
        setLocationRelativeTo(null);

//-------------------------------------------------------------------------------------------------------------
        // ---- LISTENERS ----
        // ---- LISTENER BOTON ACEPTAR ----
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //agregamos a l alumno
                //todo
                System.out.println("Agregado Alum");
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



}
