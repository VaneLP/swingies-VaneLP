package ventanas;

import controlador.ControladorAlumnos;
import modelo.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusquedaAvanzadaAlum extends JFrame {
    private JPanel panelPrincipal;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JTextField textField1;
    private ControladorAlumnos controladorAlumnos = new ControladorAlumnos();
    private int indice;

    public BusquedaAvanzadaAlum(DefaultTableModel modeloTablaAlum) {
        setContentPane(panelPrincipal);

        //titulo
        setTitle("Busqueda avanzada");

        //para que se cierre cuando le damos a la X
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //para que se ajuste solo
        setSize(450, 300);

        //la ventana se coloque en el centro
        setLocationRelativeTo(null);

        //hacer visible la ventana
        setVisible(true);

        comboBox1.addItem("Busqueda por nombre");
        comboBox1.addItem("Busqueda por DNI");
        comboBox1.addItem("Nota media");
        comboBox1.addItem("Profesor tutor");

        comboBox2.addItem("Coincidencia exacta con la palabra clave");
        comboBox2.addItem("Contiene palabra clave");
        comboBox2.addItem("Empieza por...");
        comboBox2.addItem("Termina por....");


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionComboBox1 = (String) comboBox1.getSelectedItem();
                String seleccionComboBox2 = (String) comboBox2.getSelectedItem();

                switch (seleccionComboBox1) {

                    case "Busqueda por nombre":

                        switch (seleccionComboBox2) {
                            case "Coincidencia exacta con la palabra clave":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array;

                                for (Alumno alumno : controladorAlumnos.coincidenciaExactaNombre(textField1.getText())) {
                                    array = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Contiene palabra clave":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array2;

                                for (Alumno alumno : controladorAlumnos.contienePalabraClaveNombre(textField1.getText())) {
                                    array2 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array2);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Empieza por...":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array3;

                                for (Alumno alumno : controladorAlumnos.empiezaPorNombre(textField1.getText())) {
                                    array3 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array3);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Termina por....":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array4;

                                for (Alumno alumno : controladorAlumnos.terminaEnNombre(textField1.getText())) {
                                    array4 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array4);

                                    indice++;
                                }
                                dispose();
                                break;
                        }
                        dispose();
                        break;

                    case "Busqueda por DNI":

                        switch (seleccionComboBox2) {
                            case "Coincidencia exacta con la palabra clave":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array;

                                for (Alumno alumno : controladorAlumnos.coincidenciaExactaDni(textField1.getText())) {
                                    array = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Contiene palabra clave":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array2;

                                for (Alumno alumno : controladorAlumnos.contienePalabraClaveDni(textField1.getText())) {
                                    array2 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array2);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Empieza por...":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array3;

                                for (Alumno alumno : controladorAlumnos.empiezaPorDni(textField1.getText())) {
                                    array3 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array3);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Termina por....":
                                indice = 0;
                                modeloTablaAlum.setNumRows(0);
                                String[] array4;

                                for (Alumno alumno : controladorAlumnos.terminaEnDni(textField1.getText())) {
                                    array4 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                            String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                                    modeloTablaAlum.insertRow(indice, array4);

                                    indice++;
                                }
                                dispose();
                                break;
                        }
                        dispose();
                        break;

                    case "Nota media":
                        indice = 0;
                        modeloTablaAlum.setNumRows(0);
                        String[] array;

                        for (Alumno alumno : controladorAlumnos.notaMediaAlum(Integer.parseInt(textField1.getText()))) {
                            array = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                    String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                            modeloTablaAlum.insertRow(indice, array);

                            indice++;
                        }
                        dispose();
                        break;

                    case "Profesor tutor":
                        indice = 0;
                        modeloTablaAlum.setNumRows(0);
                        String[] array2;

                        for (Alumno alumno : controladorAlumnos.profesorTutorAlum(textField1.getText())) {
                            array2 = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                                    String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

                            modeloTablaAlum.insertRow(indice, array2);

                            indice++;
                        }
                        dispose();
                        break;
                }

            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionComboBox1 = (String) comboBox1.getSelectedItem();

                switch (seleccionComboBox1) {
                    case "Busqueda por nombre":
                        comboBox2.setVisible(true);
                        break;

                    case "Busqueda por DNI":
                        comboBox2.setVisible(true);
                        break;

                    case "Nota media":
                        comboBox2.setVisible(false);
                        break;

                    case "Profesor tutor":
                        comboBox2.setVisible(false);
                        break;
                }
            }
        });

    }
}
