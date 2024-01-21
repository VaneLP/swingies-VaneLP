package ventanas;

import controlador.ControladorProfesores;
import modelo.Profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusquedaAvanzadaProfe extends JFrame {
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JTextField textField1;
    private JPanel panelPrincipal;
    private ControladorProfesores controladorProfesores = new ControladorProfesores();
    private int indice;

    public BusquedaAvanzadaProfe(DefaultTableModel modeloTablaProfe) {
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
        comboBox1.addItem("Asignatura o asignaturas");

        comboBox2.addItem("Coincidencia exacta con la palabra clase");
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
                            case "Coincidencia exacta con la palabra clase":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array;

                                for (Profesor profe : controladorProfesores.coincidenciaExactaNombre(textField1.getText())) {
                                    array = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Contiene palabra clave":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array2;

                                for (Profesor profe : controladorProfesores.contienePalabraClaveNombre(textField1.getText())) {
                                    array2 = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array2);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Empieza por...":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array3;

                                for (Profesor profe : controladorProfesores.empiezaPorNombre(textField1.getText())) {
                                    array3 = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array3);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Termina por....":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array4;

                                for (Profesor profe : controladorProfesores.terminaEnNombre(textField1.getText())) {
                                    array4 = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array4);

                                    indice++;
                                }
                                dispose();
                                break;
                        }
                        break;

                    case "Busqueda por DNI":

                        switch (seleccionComboBox2) {
                            case "Coincidencia exacta con la palabra clase":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array;

                                for (Profesor profe : controladorProfesores.coincidenciaExactaDni(textField1.getText())) {
                                    array = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Contiene palabra clave":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array2;

                                for (Profesor profe : controladorProfesores.contienePalabraClaveDni(textField1.getText())) {
                                    array2 = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array2);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Empieza por...":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array3;

                                for (Profesor profe : controladorProfesores.empiezaPorDni(textField1.getText())) {
                                    array3 = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array3);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Termina por....":
                                indice = 0;
                                modeloTablaProfe.setNumRows(0);
                                String[] array4;

                                for (Profesor profe : controladorProfesores.terminaEnDni(textField1.getText())) {
                                    array4 = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                            String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                                    modeloTablaProfe.insertRow(indice, array4);

                                    indice++;
                                }
                                dispose();
                                break;
                        }
                        break;

                    case "Asignatura o asignaturas":
                        indice = 0;
                        modeloTablaProfe.setNumRows(0);
                        String[] array;

                        for (Profesor profe : controladorProfesores.agruparAsignraturaProf(textField1.getText())) {
                            array = new String[]{profe.getNombre(), profe.getDNI(), String.valueOf(profe.getTlf()),
                                    String.valueOf(profe.getEdad()), profe.getCurso().getNombre(), profe.getListaAsignaturas().toString()};

                            modeloTablaProfe.insertRow(indice, array);

                            indice++;
                        }
                        dispose();
                        break;

                }
            }

        });
    }
}
