package ventanas;

import controlador.ControladorAlumnos;
import controlador.ControladorCursos;
import modelo.Curso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCheckBox extends JFrame {
    private JPanel panelPrincipal;
    private JButton button1;
    private JCheckBox checkBox1;
    private ControladorCursos controladorCur = new ControladorCursos();


    public VentanaCheckBox() {
        setContentPane(panelPrincipal);

        //titulo
        setTitle("cursos");

        //para que se cierre cuando le damos a la X
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //para que se ajuste solo
        setSize(350, 500);

        //la ventana se coloque en el centro
        //setLocationRelativeTo(null);
        setLocation(1300,270);

        //hacer visible la ventana
        setVisible(true);

        // Fila inicial
        int fila = 0;

        for (Curso curso : controladorCur.listar()) {
            JCheckBox cb = new JCheckBox(curso.getNombre());

            // Añadir el JCheckBox al panel con el setConstraints
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;  // Columna
            gbc.gridy = fila++;  // Incrementar la fila para el próximo componente
            gbc.anchor = GridBagConstraints.CENTER;  // Alineación izquierda
            gbc.weighty = 1.0;
            gbc.weightx=1.0;

            panelPrincipal.add(cb, gbc);

            if(cb.isSelected()){

            }
        }

        //boton
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
