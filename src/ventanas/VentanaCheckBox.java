package ventanas;

import controlador.ControladorAlumnos;
import controlador.ControladorCursos;
import modelo.Alumno;
import modelo.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaCheckBox extends JFrame {
    private JPanel panelPrincipal;
    private JButton button1;
    private JCheckBox checkBox1;
    private List<JCheckBox> listaCheck = new ArrayList<JCheckBox>();
    private ControladorAlumnos controladorAlumnos = new ControladorAlumnos();
    private ControladorCursos controladorCur = new ControladorCursos();
    private int indice;

    public VentanaCheckBox(DefaultTableModel modeloTablaAlum) {
        setContentPane(panelPrincipal);

        //titulo
        setTitle("Cursos");

        //para que se cierre cuando le damos a la X
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //para que se ajuste solo
        setSize(350, 500);

        //la ventana se coloque en el centro
        //setLocationRelativeTo(null);
        setLocation(1350, 270);

        //hacer visible la ventana
        setVisible(true);

        // Fila inicial
        int fila = 0;

        for (Curso curso : controladorCur.listar()) {
            JCheckBox cb = new JCheckBox(curso.getNombre());

            // Añadir el JCheckBox al panel con el setConstraints
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = fila++;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;

            panelPrincipal.add(cb, gbc);
            listaCheck.add(cb);

            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(cb.getText());

                    List<String> listaCur = new ArrayList<>();

                    for (JCheckBox j : listaCheck) {
                        if(j.isSelected()){
                            listaCur.add(j.getText());
                        }
                    }

                    indice = 0;
                    modeloTablaAlum.setNumRows(0);
                    String[] array;

                    for (Alumno alumno : controladorAlumnos.buscarCursoAlum(listaCur)) {
                        array = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                        modeloTablaAlum.insertRow(indice, array);

                        indice++;
                    }

                }
            });

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
