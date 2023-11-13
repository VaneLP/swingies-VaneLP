package ventanas;

import controlador.ListaAlumnos;
import controlador.ListaCursos;
import controlador.ListaProfesores;
import modelo.Alumno;
import modelo.Curso;
import modelo.Profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JDialog {
    //atributos componentes menu
    private JTabbedPane tabbedPane1;
    private JPanel panelPrincipal;
    private JTabbedPane jTabbedPaneProfe;
    private JTabbedPane jTabbedPaneCur;
    private JTabbedPane jTabbebPaneAlum;

    //atributos menu
    private JMenu menuAbout, menuAlum, menuProfe, menuCur, menuDatos, menuExportar, menuImportar;
    private JMenuBar menuBar;
    private JLabel icono;
    private JMenuItem itemAbout,
            itemAgregarAlum, itemBuscarAlum, itemEliminarAlum, itemMostrarAlum,
            itemAgregarProfe, itemBuscarProfe, itemEliminarProfe, itemMostrarProfe,
            itemAgregarCur, itemBuscarCur, itemEliminarCur, itemMostrarCur,
            itemExportarCur, itemImportarCur;

    //atributos tabla
    private JTable tablaAlum;
    private JScrollPane scrollAlum;
    private JScrollPane scrollProfe;
    private JTable tablaProfe;
    private JScrollPane scrollCur;
    private JTable tablaCur;
    private JButton agregarAlum;
    private JButton mostrarAlum;
    private JButton buscarAlum;
    private JButton eliminarAlum;
    private JButton agregarProfe;
    private JButton mostrarProfe;
    private JButton buscarProfe;
    private JButton eliminarProfe;
    private JButton agregarCur;
    private JButton mostrarCur;
    private JButton buscarCur;
    private JButton eliminaCur;
    private JCheckBox alfaAlum;
    private JCheckBox aproAlum;
    private JCheckBox suspensoAlum;
    private JCheckBox alfebeticamenteProfe;
    private JCheckBox tutores;
    private JCheckBox alfabeticamenteCuso;
    private JButton añadirNotasButton;
    private DefaultTableModel modeloTablaAlum;
    private DefaultTableModel modeloTablaProfe;
    private DefaultTableModel modeloTablaCur;

    //Atributos controlador
    private ListaAlumnos listaAlum = new ListaAlumnos();
    private ListaCursos listaCur = new ListaCursos();
    private ListaProfesores listaProfe = new ListaProfesores();

    //atributos
    private String dni, codigo, nombreCur;
    private int indice = 0;
    String[] arrayMostrarCursos, arrayMostrarProfe, arrayMostrarAlum;
    private int ind;
    private boolean encontrado;

//-------------------------------------------------------------------------------------------------------------
    public Menu(Login l, boolean b) {
        super(l, b);

        //para que se muestre en la ventana nuestro panel
        setContentPane(panelPrincipal);

        //el tamaño de la ventana
        setSize(700, 500);

        //para que se centre en medio de la pantalla
        setLocationRelativeTo(null);

//-------------------------------------------------------------------------------------------------------------
        // ---- MENU ----
        //asignamos el JMenuBAr
        setJMenuBar(menuBar);

        menuBar.setBackground(new Color(129,153,146));
//-------------------------------------------------------------------------------------------------------------
        // ---- ALUMNOS ----
        //creamos un nuevo menu y le ponemos un titulo
        menuAlum = new JMenu("Alumnos");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuAlum);

        //creamos los items y le pomenos un nombre
        itemAgregarAlum = new JMenuItem("Agregar");
        itemBuscarAlum = new JMenuItem("Buscar");
        itemEliminarAlum = new JMenuItem("Eliminar");
        itemMostrarAlum = new JMenuItem("Mostrar");

        itemAgregarAlum.setBackground(new Color(250,246,212));
        itemBuscarAlum.setBackground(new Color(250,246,212));
        itemEliminarAlum.setBackground(new Color(250,246,212));
        itemMostrarAlum.setBackground(new Color(250,246,212));

        //añadimos a nuestro menu los items que acabamos de crear
        menuAlum.add(itemAgregarAlum);
        menuAlum.add(itemBuscarAlum);
        menuAlum.add(itemEliminarAlum);
        menuAlum.add(itemMostrarAlum);

        // al menu de alumnos le asociamos el atajo de Alt+A
        menuAlum.setMnemonic(KeyEvent.VK_A);

//-------------------------------------------------------------------------------------------------------------
        // ---- PROFESORES ----
        //creamos un nuevo menu y le ponemos un titulo
        menuProfe = new JMenu("Profesores");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuProfe);

        //creamos los items y le pomenos un nombre
        itemAgregarProfe = new JMenuItem("Agregar");
        itemBuscarProfe = new JMenuItem("Buscar");
        itemEliminarProfe = new JMenuItem("Eliminar");
        itemMostrarProfe = new JMenuItem("Mostrar");

        //añadimos a nuestro menu los items que acabamos de crear
        menuProfe.add(itemAgregarProfe);
        menuProfe.add(itemBuscarProfe);
        menuProfe.add(itemEliminarProfe);
        menuProfe.add(itemMostrarProfe);

        itemAgregarProfe.setBackground(new Color(250,246,212));
        itemBuscarProfe.setBackground(new Color(250,246,212));
        itemEliminarProfe.setBackground(new Color(250,246,212));
        itemMostrarProfe.setBackground(new Color(250,246,212));

        // al menu de profesores le asociamos el atajo de Alt+P
        menuProfe.setMnemonic(KeyEvent.VK_P);

//-------------------------------------------------------------------------------------------------------------
        // ---- CURSO ----
        //creamos un nuevo menu y le ponemos un titulo
        menuCur = new JMenu("Curso");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuCur);

        //creamos los items y le pomenos un nombre
        itemAgregarCur = new JMenuItem("Agregar");
        itemBuscarCur = new JMenuItem("Buscar");
        itemEliminarCur = new JMenuItem("Eliminar");
        itemMostrarCur = new JMenuItem("Mostrar");

        itemAgregarCur.setBackground(new Color(250,246,212));
        itemBuscarCur.setBackground(new Color(250,246,212));
        itemEliminarCur.setBackground(new Color(250,246,212));
        itemMostrarCur.setBackground(new Color(250,246,212));

        //añadimos a nuestro menu los items que acabamos de crear
        menuCur.add(itemAgregarCur);
        menuCur.add(itemBuscarCur);
        menuCur.add(itemEliminarCur);
        menuCur.add(itemMostrarCur);

        // al menu de curso le asociamos el atajo de Alt+C
        menuCur.setMnemonic(KeyEvent.VK_C);

//-------------------------------------------------------------------------------------------------------------
        // ---- ABOUT ----
        // creamos un nuevo menu y le ponemos un titulo
        menuAbout = new JMenu("About");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuAbout);

        //creamos los items y le pomenos un nombre
        itemAbout = new JMenuItem("Interfaz realizada por Vanessa Lopez Pastor");

        itemAbout.setBackground(new Color(250,246,212));

        //añadimos a nuestro menu los items que acabamos de crear
        menuAbout.add(itemAbout);

//-------------------------------------------------------------------------------------------------------------
        // ---- DATOS (ACCESOS) ----
        // creamos un nuevo menu y le ponemos un titulo
        menuDatos = new JMenu("Carga masiva");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuDatos);

        //creamos los items y le pomenos un nombre
        menuExportar = new JMenu("Exportar");
        menuImportar = new JMenu("Importar");

        menuDatos.add(menuExportar);
        menuDatos.add(menuImportar);

        menuExportar.setBackground(new Color(250,246,212));
        menuImportar.setBackground(new Color(250,246,212));

        //añadimos a nuestro menu los items que acabamos de crear
        menuAbout.add(itemAbout);

//-------------------------------------------------------------------------------------------------------------
        // ---- ICONO ----
        //agregamos una imagen
        icono.setIcon(new ImageIcon("src/imagenes/logoThiar.png"));

//-------------------------------------------------------------------------------------------------------------
        // ---- TABLAS ----
        // ---- TABLA ALUMNOS ----
        //creamos un modelo de tabla para los alumnos
        modeloTablaAlum = new DefaultTableModel();

        //le añadimos las columnas
        modeloTablaAlum.addColumn("Nombre");
        modeloTablaAlum.addColumn("DNI");
        modeloTablaAlum.addColumn("Telefono");
        modeloTablaAlum.addColumn("Edad");
        modeloTablaAlum.addColumn("Curso");
        modeloTablaAlum.addColumn("Notas");

        //a nuestra tabla de alumnos le asignamos el modelo
        tablaAlum.setModel(modeloTablaAlum);

        //para que no se puedan mover las columnas
        tablaAlum.getTableHeader().setReorderingAllowed(false);
        //para que no se puedan cambiar de tamaño
        tablaAlum.getTableHeader().setResizingAllowed(false);
        //para que no se puedan editar las filas
        tablaAlum.setDefaultEditor(Object.class, null);

        //hacemos la tabla visible (no afecta en nada)
        tablaAlum.setVisible(true);

        //al scroll le asignamos la tabla
        scrollAlum.add(tablaAlum);
        //hacemos que se visualicen los titulos de las tablas
        scrollAlum.setViewportView(tablaAlum);

//-------------------------------------------------------------------------------------------------------------
        // ---- TABLA PROFESORES ----
        //creamos un modelo de tabla para los profesores
        modeloTablaProfe = new DefaultTableModel();

        //le añadimos las columnas
        modeloTablaProfe.addColumn("Nombre");
        modeloTablaProfe.addColumn("DNI");
        modeloTablaProfe.addColumn("Telefono");
        modeloTablaProfe.addColumn("Edad");
        modeloTablaProfe.addColumn("Curso");

        //a nuestra tabla de alumnos le asignamos el modelo
        tablaProfe.setModel(modeloTablaProfe);

        //para que no se puedan mover las columnas
        tablaProfe.getTableHeader().setReorderingAllowed(false);
        //para que no se puedan cambiar de tamaño
        tablaProfe.getTableHeader().setResizingAllowed(false);
        //para que no se puedan editar las filas
        tablaProfe.setDefaultEditor(Object.class, null);

        //hacemos la tabla visible (no afecta en nada)
        tablaProfe.setVisible(true);

        //al scroll le asignamos la tabla
        scrollProfe.add(tablaProfe);
        //hacemos que se visualicen los titulos de las tablas
        scrollProfe.setViewportView(tablaProfe);

//-------------------------------------------------------------------------------------------------------------
        // ---- TABLA CURSOS ----
        //creamos un modelo de tabla para los curso
        modeloTablaCur = new DefaultTableModel();

        //le añadimos las columnas
        modeloTablaCur.addColumn("Codigo");
        modeloTablaCur.addColumn("Nombre");

        //a nuestra tabla de alumnos le asignamos el modelo
        tablaCur.setModel(modeloTablaCur);

        //para que no se puedan mover las columnas
        tablaCur.getTableHeader().setReorderingAllowed(false);
        //para que no se puedan cambiar de tamaño
        tablaCur.getTableHeader().setResizingAllowed(false);
        //para que no se puedan editar las filas
        tablaCur.setDefaultEditor(Object.class, null);

        //hacemos la tabla visible (no afecta en nada)
        tablaCur.setVisible(true);

        //al scroll le asignamos la tabla
        scrollCur.add(tablaCur);
        //hacemos que se visualicen los titulos de las tablas
        scrollCur.setViewportView(tablaCur);

//-------------------------------------------------------------------------------------------------------------
        // ---- LISTENERS ----
        // ---- ALUMNO ----
        // ---- LISTENER BOTON AGREGAR ALUMNO ----
        agregarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlumno();
            }
        });

        // ---- LISTENER BOTON BUSCAR ALUMNO ----
        buscarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno();
            }
        });

        // ---- LISTENER BOTON ELIMINAR ALUMNO ----
        eliminarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAlumno();
            }
        });

        // ---- LISTENER BOTON MOSTRAR ALUMNO ----
        mostrarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAlumnos();
            }
        });

        // ---- LISTENER BOTON AGREGAR NOTA ALUMNO ----
        añadirNotasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNotasAlum();
            }
        });

        // ---- CHECKBOX ORDENAR ALFABETICAMENTE ALUMNO----
        alfaAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaAlum.ordenarAlfabeticamente();
                mostrarAlumnos();
            }
        });

        // ---- CHECKBOX ORDENAR APROBADOS ALUMNO ----
        aproAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumAprobados();
            }
        });

        // ---- CHECKBOX ORDENAR SUSPENSOS ALUMNO ----
        suspensoAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumSuspensos();
            }
        });

        // ---- OPCION MENU AGREGAR ALUMNO ----
        itemAgregarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlumno();
            }
        });

        // ---- OPCION MENU BUSCAR ALUMNO ----
        itemBuscarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno();
            }
        });

        // ---- OPCION MENU ELIMINAR ALUMNO ----
        itemEliminarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAlumno();
            }
        });

        // ---- OPCION MENU MOSTRAR ALUMNO ----
        itemMostrarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAlumnos();
            }
        });

//-------------------------------------------------------------------------------------------------------------
        // ---- PROFESOR ----
        // ---- LISTENER BOTON AGREGAR PROFESOR ----
        agregarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProfesor();
            }
        });

        // ---- LISTENER BOTON BUSCAR PROFESOR ----
        buscarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProfesor();
            }
        });

        // ---- LISTENER BOTON ELIMINAR PROFESOR ----
        eliminarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProfesor();
            }
        });

        // ---- LISTENER BOTON MOSTRAR PROFESOR ----
        mostrarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProfesores();
            }
        });

        // ---- CHECKBOX ORDENAR ALFABETICAMENTE PROFESOR----
        alfebeticamenteProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaProfe.ordenarAlfabeticamente();
                mostrarProfesores();
            }
        });

        // ---- CHECKBOX ORDENAR TUTORES PROFESOR ----
        tutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTutores();
            }
        });

        // ---- OPCION MENU AGREGAR PROFESOR ----
        itemAgregarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProfesor();
            }
        });

        // ---- OPCION MENU BUSCAR PROFESOR ----
        itemBuscarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProfesor();
            }
        });

        // ---- OPCION MENU ELIMINAR PROFESOR ----
        itemEliminarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProfesor();
            }
        });

        // ---- OPCION MENU MOSTRAR PROFESOR ----
        itemMostrarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProfesores();
            }
        });

//-------------------------------------------------------------------------------------------------------------
        // ---- CURSO ----
        // ---- LISTENER BOTON AGREGAR CURSO ----
        agregarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCurso();
            }
        });

        // ---- LISTENER BOTON BUSCAR CURSO ----
        buscarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCurso();
            }
        });

        // ---- LISTENER BOTON ELIMINAR CURSO ----
        eliminaCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarcurso();
            }
        });

        // ---- LISTENER BOTON MOSTRAR CURSO ----
        mostrarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCursos();
            }
        });

        // ---- CHECKBOX ORDENAR ALFABETICAMENTE CURSO----
        alfabeticamenteCuso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaCur.ordenarALfabeticamente();
                mostrarCursos();
            }
        });

        // ---- OPCION MENU AGREGAR CURSO ----
        itemAgregarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCurso();
            }
        });

        // ---- OPCION MENU BUSCAR CURSO ----
        itemBuscarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCurso();
            }
        });

        // ---- OPCION MENU ELIMINAR CURSO ----
        itemEliminarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarcurso();
            }
        });

        // ---- OPCION MENU MOSTRAR CURSO ----
        itemMostrarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCursos();
            }
        });


    }

//-------------------------------------------------------------------------------------------------------------
    // ---- METODOS ----
    // ---- ALUMNO ----

    /**
     * Metodo agregar alumno que nos abre otra ventana, la cual nos permitira agregar un alumno si rellenamos todos los
     * campos correctamente
     */
    private void agregarAlumno() {
        AgregarAlumno a = new AgregarAlumno(this, true, listaAlum, listaCur);
        a.setVisible(true);
    }

    /**
     * Metodo buscar alumno a traves de su DNI
     */
    private void buscarAlumno() {
        dni = JOptionPane.showInputDialog("Introduce el DNI");
        String[] buscarAlum;
        indice = 0;

        if (dni != null) {
            for (Alumno a : listaAlum.getListaAlumnos()) {
                if (dni.equalsIgnoreCase(a.getDNI())) {
                    buscarAlum = new String[]{a.getNombre(), a.getDNI(), String.valueOf(a.getTlf()), String.valueOf(a.getEdad()), a.getCurso()};

                    modeloTablaAlum.setNumRows(0);

                    modeloTablaAlum.insertRow(indice, buscarAlum);
                }
            }
        }

    }

    /**
     * Metodo eliminar un alumno seleccionado
     */
    private void eliminarAlumno() {
        // variables
        ind = 0;
        encontrado = false;

        int filaSeleccionadaAlum = tablaAlum.getSelectedRow();

        JPanel panel = new JPanel();
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Estas seguro?", "Eliminar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            if (filaSeleccionadaAlum == -1) {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
            } else {
                String dniBorrar = String.valueOf(tablaAlum.getValueAt(filaSeleccionadaAlum, 1));

                // recorremos la listaAlumnos
                for (int i = 0; i != listaAlum.getListaAlumnos().size(); i++) {
                    // si el DNI del alumno es igual al dni que nos pasan
                    if (dniBorrar.equalsIgnoreCase(listaAlum.getListaAlumnos().get(i).getDNI())) {
                        // guardamos en nuestra variable el indice en el que estamos
                        ind = i;
                        // cambiamos el booleano a TRUE porque hemos encontrado el curso
                        encontrado = true;
                        // salimos
                        break;
                    }
                }

                // si el booleano es verdadero
                if (encontrado)
                    // eliminamos de nuestra lista el alumno en el indice que hemos guardado en
                    // nuestra variable
                    listaAlum.getListaAlumnos().remove(ind);

                mostrarAlumnos();
            }
        }
    }

    /**
     * Metodo mostrar todos los alumnos
     */
    private void mostrarAlumnos() {
        indice = 0;
        modeloTablaAlum.setNumRows(0);

        for (Alumno alumno : listaAlum.getListaAlumnos()) {
            arrayMostrarAlum = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso(), alumno.getListaNotas().toString()};

            modeloTablaAlum.insertRow(indice, arrayMostrarAlum);

            indice++;
        }
    }

    private void mostrarNotasAlum() {
        String notasAlumno = JOptionPane.showInputDialog("Introduce la nota al alumno");

        int filaSeleccionadaAlumNota = tablaAlum.getSelectedRow();

        if (filaSeleccionadaAlumNota == -1 || !notasAlumno.matches("[0-1]?[0-9]{1}([.][0-9]*)?")) {
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
        } else {
            String dni = String.valueOf(tablaAlum.getValueAt(filaSeleccionadaAlumNota, 1));

            // recorremos la listaAlumnos
            for (int i = 0; i != listaAlum.getListaAlumnos().size(); i++) {
                // si el DNI del alumno es igual al dni que nos pasan
                if (dni.equalsIgnoreCase(listaAlum.getListaAlumnos().get(i).getDNI())) {
                    for (int j = 0; j <= 5; j++) {
                        if (j == 5) {
                            listaAlum.agregarNotaAlumno(listaAlum.getListaAlumnos().get(i).getDNI(), Double.parseDouble(notasAlumno));
                        }
                    }
                    // salimos
                    break;
                }
            }
            mostrarAlumnos();
        }
    }

    /**
     * Metodo para mostrar los alumnos suspensos
     */
    private void alumSuspensos() {
        indice = 0;
        modeloTablaAlum.setNumRows(0);
        String[] arrayMostrarSus;

        for (Alumno alumno : listaAlum.listarSuspensos()) {
            arrayMostrarSus = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso(),alumno.getListaNotas().toString()};

            modeloTablaAlum.insertRow(indice, arrayMostrarSus);

            indice++;
        }

    }

    /**
     * Metodo para mostrar los alumnos aprobados
     */
    private void alumAprobados() {
        indice = 0;
        modeloTablaAlum.setNumRows(0);
        String[] arrayMostrarApro;

        for (Alumno alumno : listaAlum.listarAprobados()) {
            arrayMostrarApro = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso(),alumno.getListaNotas().toString()};

            modeloTablaAlum.insertRow(indice, arrayMostrarApro);

            indice++;
        }

    }

    // -------------------------------------------------------------------------------------------------------------
    // ---- PROFESOR ----

    /**
     * Metodo agregar profesor, que nos abre otra ventana a traves de la cual rellenando correctamente los campos
     * podremos añadir un nuevo profesor
     */
    private void agregarProfesor() {
        AgregarProfesor p = new AgregarProfesor(this, true, listaProfe, listaCur);
        p.setVisible(true);
    }

    /**
     * Metodo buscar profesor, lo buscaremos a traves de su DNI
     */
    private void buscarProfesor() {
        dni = JOptionPane.showInputDialog("Introduce el DNI");
        String[] buscarProfe;
        indice = 0;

        if (dni != null) {
            for (Profesor p : listaProfe.getListaProfesores()) {
                if (dni.equalsIgnoreCase(p.getDNI())) {
                    buscarProfe = new String[]{p.getNombre(), p.getDNI(), String.valueOf(p.getTlf()),
                            String.valueOf(p.getEdad()), p.getCurso()};

                    modeloTablaProfe.setNumRows(0);

                    modeloTablaProfe.insertRow(indice, buscarProfe);
                }
            }
        }
    }

    /**
     * Metodo eliminar un profesor que tengamos seleccionado
     */
    private void eliminarProfesor() {
        // variables
        ind = 0;
        encontrado = false;

        int filaSeleccionadaProfe = tablaProfe.getSelectedRow();

        JPanel panel = new JPanel();
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Estas seguro?", "Eliminar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            if (filaSeleccionadaProfe == -1) {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

            } else {
                String dniBorrar = String.valueOf(tablaProfe.getValueAt(filaSeleccionadaProfe, 1));

                // recorremos la listaProfe
                for (int i = 0; i != listaProfe.getListaProfesores().size(); i++) {
                    // si el DNI del profe es igual al dni que nos pasan
                    if (dniBorrar.equalsIgnoreCase(listaProfe.getListaProfesores().get(i).getDNI())) {
                        // guardamos en nuestra variable el indice en el que estamos
                        ind = i;
                        // cambiamos el booleano a TRUE porque hemos encontrado el dni
                        encontrado = true;
                        // salimos
                        break;
                    }
                }

                // si el booleano es verdadero
                if (encontrado)
                    // eliminamos de nuestra lista el curso en el indice que hemos guardado en
                    // nuestra variable
                    listaProfe.getListaProfesores().remove(ind);

                mostrarProfesores();
            }
        }

    }

    /**
     * Metodo mostrar todos los profesores
     */
    private void mostrarProfesores() {
        indice = 0;
        modeloTablaProfe.setNumRows(0);

        for (Profesor profesor : listaProfe.getListaProfesores()) {
            arrayMostrarProfe = new String[]{profesor.getNombre(), profesor.getDNI(),
                    String.valueOf(profesor.getTlf()), String.valueOf(profesor.getEdad()), profesor.getCurso()};

            modeloTablaProfe.insertRow(indice, arrayMostrarProfe);

            indice++;
        }
    }

    /**
     * Metodo para mostrar los tutores, que son los que tienen cursos
     */
    private void mostrarTutores() {
        indice = 0;
        modeloTablaProfe.setNumRows(0);
        String[] arrayMostrarTutores;

        for (Profesor profesor : listaProfe.listarTutores()) {
            arrayMostrarTutores = new String[]{profesor.getNombre(), profesor.getDNI(),
                    String.valueOf(profesor.getTlf()), String.valueOf(profesor.getEdad()), profesor.getCurso()};

            modeloTablaProfe.insertRow(indice, arrayMostrarTutores);

            indice++;
        }
    }

    // -------------------------------------------------------------------------------------------------------------
    // ---- CURSO ----

    /**
     * Metodo para agregar curso solo con el nombre, porque el codigo es automatico
     */
    private void agregarCurso() {
        nombreCur = JOptionPane.showInputDialog("Introduce el nombre del curso");

        if (nombreCur != null && nombreCur.matches("[A-Za-z]+"))
            listaCur.agregar(new Curso(nombreCur));
        else
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
    }

    /**
     * Metodo para buscar curso a traves de su codigo
     */
    private void buscarCurso() {
        codigo = JOptionPane.showInputDialog("Introduce el codigo");
        String[] buscarCurso;
        indice = 0;

        for (Curso curso : listaCur.getListaCursos()) {
            if (codigo.equals(String.valueOf(curso.getCodigo()))) {
                buscarCurso = new String[]{String.valueOf(curso.getCodigo()), curso.getNombre()};

                modeloTablaCur.setNumRows(0);

                modeloTablaCur.insertRow(indice, buscarCurso);
            }
            else
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
        }

    }

    /**
     * Metodo eliminar un curso, si lo tenemos seleccionado
     */
    private void eliminarcurso() {
        // variables
        ind = 0;
        encontrado = false;
        int filaSeleccionadaCur = tablaCur.getSelectedRow();
        String cursoSave = "";

        JPanel panel = new JPanel();
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Estas seguro?", "Eliminar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            if (filaSeleccionadaCur == -1) {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
            } else {
                int codigoBorrar = Integer.parseInt((String) tablaCur.getValueAt(filaSeleccionadaCur, 0));

                // recorremos la listaCur
                for (int i = 0; i != listaCur.getListaCursos().size(); i++) {
                    // si el codigo del curso es igual al codigo del curso que nos pasan
                    if (codigoBorrar == listaCur.getListaCursos().get(i).getCodigo()) {
                        // guardamos en nuestra variable el indice en el que estamos
                        ind = i;
                        // cambiamos el booleano a TRUE porque hemos encontrado el curso
                        encontrado = true;
                        cursoSave= listaCur.getListaCursos().get(i).getNombre();
                        // salimos
                        break;
                    }
                }

                // si el booleano es verdadero
                if (encontrado) {
                    // eliminamos de nuestra lista el curso en el indice que hemos guardado en
                    // nuestra variable
                    listaCur.getListaCursos().remove(ind);
                }


                for (Alumno a : listaAlum.getListaAlumnos()) {
                    System.out.println(a.getCurso());
                    if(a.getCurso().equalsIgnoreCase(cursoSave))
                        a.setCurso("");
                }
                mostrarCursos();
            }
        }

    }

    /**
     * Metodo mostrar todos los cursos
     */
    private void mostrarCursos() {
        indice = 0;
        modeloTablaCur.setNumRows(0);

        for (Curso c : listaCur.getListaCursos()) {
            arrayMostrarCursos = new String[]{String.valueOf(c.getCodigo()), c.getNombre()};
            modeloTablaCur.insertRow(indice, arrayMostrarCursos);

            indice++;
        }
    }


}