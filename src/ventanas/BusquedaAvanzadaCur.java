package ventanas;

import controlador.ControladorCursos;
import modelo.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusquedaAvanzadaCur extends JFrame {
    private JPanel panelPrincipal;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JTextField textField1;
    private ControladorCursos controladorCursos = new ControladorCursos();
    private int indice;

    public BusquedaAvanzadaCur(DefaultTableModel modeloTablaCur) {
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
        comboBox1.addItem("Busqueda por ID");

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
                                modeloTablaCur.setNumRows(0);
                                String[] array;

                                for (Curso c : controladorCursos.coincidenciaExactaNombre(textField1.getText())) {
                                    array = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Contiene palabra clave":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array2;

                                for (Curso c : controladorCursos.contienePalabraClaveNombre(textField1.getText())) {
                                    array2 = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array2);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Empieza por...":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array3;

                                for (Curso c : controladorCursos.empiezaPorNombre(textField1.getText())) {
                                    array3 = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array3);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Termina por....":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array4;

                                for (Curso c : controladorCursos.terminaEnNombre(textField1.getText())) {
                                    array4 = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array4);

                                    indice++;
                                }
                                dispose();
                                break;
                        }
                        break;

                    case "Busqueda por ID":

                        switch (seleccionComboBox2) {
                            case "Coincidencia exacta con la palabra clase":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array;

                                for (Curso c : controladorCursos.coincidenciaExactaId(Integer.parseInt(textField1.getText()))) {
                                    array = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Contiene palabra clave":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array2;

                                for (Curso c : controladorCursos.contienePalabraClaveId(Integer.parseInt(textField1.getText()))) {
                                    array2= new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array2);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Empieza por...":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array3;

                                for (Curso c : controladorCursos.empiezaPorId(Integer.parseInt(textField1.getText()))) {
                                    array3 = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array3);

                                    indice++;
                                }
                                dispose();
                                break;

                            case "Termina por....":
                                indice = 0;
                                modeloTablaCur.setNumRows(0);
                                String[] array4;

                                for (Curso c : controladorCursos.terminaEnId(Integer.parseInt(textField1.getText()))) {
                                    array4 = new String[]{String.valueOf(c.getId()), c.getNombre()};

                                    modeloTablaCur.insertRow(indice, array4);

                                    indice++;
                                }
                                dispose();
                                break;
                        }
                        break;

                }
            }
        });
    }
}
