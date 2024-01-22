package ventanas;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import controlador.ControladorAlumnos;
import controlador.ControladorCursos;
import controlador.ControladorProfesores;
import modelo.Alumno;
import modelo.Curso;
import modelo.Profesor;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Menu extends JFrame {
    //atributos componentes menu
    private JTabbedPane tabbedPane1, jTabbedPaneProfe, jTabbedPaneCur, jTabbebPaneAlum;
    private JPanel panelPrincipal;

    //atributos menu
    private JMenu menuAbout, menuAlum, menuProfe, menuCur, menuCargaMasiva, menuExportar, menuImportar;
    private JMenuBar menuBar;
    private JLabel icono;
    private JMenuItem itemAbout,
            itemAgregarAlum, itemBuscarAlum, itemEliminarAlum, itemMostrarAlum, itemImportarNotas,
            itemAgregarProfe, itemBuscarProfe, itemEliminarProfe, itemMostrarProfe, itemImportarAsignaturas,
            itemAgregarCur, itemBuscarCur, itemEliminarCur, itemMostrarCur,
            itemExportarCur, itemImportarCur;

    //atributos tabla
    private JTable tablaAlum, tablaProfe, tablaCur;
    private JScrollPane scrollAlum, scrollProfe, scrollCur;
    private JButton agregarAlum, mostrarAlum, buscarAlum, eliminarAlum, añadirNotasButton,
            agregarProfe, mostrarProfe, buscarProfe, eliminarProfe,
            agregarCur, mostrarCur, buscarCur, eliminaCur,
            botonVentanaCheck,
            avanzadaAlum, avanzadaProfe, avanzadaCur;
    private JCheckBox alfaAlum, aproAlum, suspensoAlum,
            alfebeticamenteProfe, tutores,
            alfabeticamenteCuso;
    private JButton añadirAsignaturaButton;

    private DefaultTableModel modeloTablaAlum, modeloTablaProfe, modeloTablaCur;

    //Atributos controlador
    private ControladorAlumnos controladorAlumnos = new ControladorAlumnos();
    private ControladorCursos controladorCursos = new ControladorCursos();
    private ControladorProfesores controladorProfesores = new ControladorProfesores();

    //atributos
    private String dni, id, nombreCur;
    private int indice = 0, ind;
    String[] arrayMostrarCursos, arrayMostrarProfe, arrayMostrarAlum;
    private boolean encontrado;

    //-------------------------------------------------------------------------------------------------------------
    public Menu() {
        //para que se muestre en la ventana nuestro panel
        setContentPane(panelPrincipal);

        //el tamaño de la ventana
        setSize(800, 500);

        //para que se centre en medio de la pantalla
        setLocationRelativeTo(null);

//-------------------------------------------------------------------------------------------------------------
        // ---- MENU ----
        //asignamos el JMenuBAr
        setJMenuBar(menuBar);

        menuBar.setBackground(new Color(129, 153, 146));
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

        itemAgregarAlum.setBackground(new Color(250, 246, 212));
        itemBuscarAlum.setBackground(new Color(250, 246, 212));
        itemEliminarAlum.setBackground(new Color(250, 246, 212));
        itemMostrarAlum.setBackground(new Color(250, 246, 212));


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

        itemAgregarProfe.setBackground(new Color(250, 246, 212));
        itemBuscarProfe.setBackground(new Color(250, 246, 212));
        itemEliminarProfe.setBackground(new Color(250, 246, 212));
        itemMostrarProfe.setBackground(new Color(250, 246, 212));

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

        itemAgregarCur.setBackground(new Color(250, 246, 212));
        itemBuscarCur.setBackground(new Color(250, 246, 212));
        itemEliminarCur.setBackground(new Color(250, 246, 212));
        itemMostrarCur.setBackground(new Color(250, 246, 212));

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

        itemAbout.setBackground(new Color(250, 246, 212));

        //añadimos a nuestro menu los items que acabamos de crear
        menuAbout.add(itemAbout);

//-------------------------------------------------------------------------------------------------------------
        // ---- CARGA MASIVA (ACCESOS - PRACTICA 3) ----
        // creamos un nuevo menu y le ponemos un titulo
        menuCargaMasiva = new JMenu("Carga masiva");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuCargaMasiva);

        //creamos dos nuevo menus
        menuExportar = new JMenu("Exportar");
        menuImportar = new JMenu("Importar");

        //los añadimos al menu de carga masiva
        menuCargaMasiva.add(menuExportar);
        menuCargaMasiva.add(menuImportar);

        //creamos los items
        itemExportarCur = new JMenuItem("Exportar curso");
        itemImportarCur = new JMenuItem("Importar curso");
        itemImportarNotas = new JMenuItem("Importar notas");
        itemImportarAsignaturas = new JMenuItem("Importar asignaturas");

        //añadimos los items al menu correspondiente
        menuExportar.add(itemExportarCur);
        menuImportar.add(itemImportarCur);
        menuImportar.add(itemImportarNotas);
        menuImportar.add(itemImportarAsignaturas);

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
        modeloTablaProfe.addColumn("Asignaturas");

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

        //BBDD
        controladorCursos.crearTablas();
        controladorAlumnos.crearTablas();
        controladorProfesores.crearTablas();

        mostrarCursos();
        mostrarAlumnos();
        mostrarProfesores();

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
                if(alfaAlum.isSelected()) {
                    mostrarAlumAlfa();
                }
            }
        });

        // ---- CHECKBOX ORDENAR APROBADOS ALUMNO ----
        aproAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aproAlum.isSelected()) {
                    alumAprobados();
                }
            }
        });

        // ---- CHECKBOX ORDENAR SUSPENSOS ALUMNO ----
        suspensoAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(suspensoAlum.isSelected()) {
                    alumSuspensos();
                }
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
                if(alfebeticamenteProfe.isSelected()) {
                    mostrarProfeAlfa();
                }
            }
        });

        // ---- CHECKBOX ORDENAR TUTORES PROFESOR ----
        tutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tutores.isSelected()) {
                    mostrarTutores();
                }

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
                if(alfabeticamenteCuso.isSelected()) {
                    mostrarCursosAlfa();
                }
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

//-------------------------------------------------------------------------------------------------------------
        // ---- CARGA MASIVA (ACCESOS - PRACTICA 3) ----
        // ---- OPCION MENU EXPORTAR CURSO ----
        itemExportarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("""
                        Selecciona con anterioridad en la tabla los cursos que 
                         deseas guardar, si no por defecto se guardaran todos.
                               Escribe el nombre del archivo sin el .csv
                        """);
                //llamamos al metodo
                guardarCursos(nombreArchivo);

                //quitamos la seleccion de la tabla
                tablaCur.getSelectionModel().clearSelection();
            }
        });

        // ---- OPCION MENU IMPORTAR CURSO ----
        itemImportarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creamos un nuevo jfilechooser
                JFileChooser elegirArchivo = new JFileChooser();
                //creamos un nuevo FileNameExtensionFilter, para poder seleccionar solo ficheros .csv
                FileNameExtensionFilter extensionArchivo = new FileNameExtensionFilter("CSV", "csv");
                //le pasamos el filtro al fichero
                elegirArchivo.setFileFilter(extensionArchivo);

                //guardamos en una variable el resultado de abrir una venta de seleccion
                int resultado = elegirArchivo.showOpenDialog(null);
                File archivoElegido = new File("");
                //si a seleccionado correctamente la opcion
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    //devuelve el file
                    archivoElegido = elegirArchivo.getSelectedFile().getAbsoluteFile();
                    //llamamos al metodo con el archivo elegido
                    leerCursos(archivoElegido);
                }
            }
        });

        // ---- OPCION MENU IMPORTAR NOTAS ALUMNO ----
        itemImportarNotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creamos un nuevo jfilechooser
                JFileChooser elegirArchivo = new JFileChooser();
                //creamos un nuevo FileNameExtensionFilter, para poder seleccionar solo ficheros .csv
                FileNameExtensionFilter extensionArchivo = new FileNameExtensionFilter("CSV", "csv");
                //le pasamos el filtro al fichero
                elegirArchivo.setFileFilter(extensionArchivo);

                //guardamos en una variable el resultado de abrir una venta de seleccion
                int resultado = elegirArchivo.showOpenDialog(null);
                File archivoElegido = new File("");
                //si a seleccionado correctamente la opcion
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    //devuelve el file
                    archivoElegido = elegirArchivo.getSelectedFile().getAbsoluteFile();
                    //llamamos al metodo con el archivo elegido
                    leerNotasAlum(archivoElegido);
                }
            }
        });

        // ---- OPCION MENU IMPORTAR ASIGNATURAS PROFESOR ----
        itemImportarAsignaturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creamos un nuevo jfilechooser
                JFileChooser elegirArchivo = new JFileChooser();
                //creamos un nuevo FileNameExtensionFilter, para poder seleccionar solo ficheros .csv
                FileNameExtensionFilter extensionArchivo = new FileNameExtensionFilter("CSV", "csv");
                //le pasamos el filtro al fichero
                elegirArchivo.setFileFilter(extensionArchivo);

                //guardamos en una variable el resultado de abrir una venta de seleccion
                int resultado = elegirArchivo.showOpenDialog(null);
                File archivoElegido = new File("");
                //si a seleccionado correctamente la opcion
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    //devuelve el file
                    archivoElegido = elegirArchivo.getSelectedFile().getAbsoluteFile();
                    //llamamos al metodo con el archivo elegido
                    leerAsigProfe(archivoElegido);
                }
            }
        });

        //BBDD
        botonVentanaCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCheckBox vcb = new VentanaCheckBox(modeloTablaAlum);
            }
        });

        avanzadaAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusquedaAvanzadaAlum baA = new BusquedaAvanzadaAlum(modeloTablaAlum);
            }
        });

        avanzadaProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusquedaAvanzadaProfe baP = new BusquedaAvanzadaProfe(modeloTablaProfe);
            }
        });

        avanzadaCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusquedaAvanzadaCur baC = new BusquedaAvanzadaCur(modeloTablaCur);
            }
        });

        añadirAsignaturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAsigProfesor();
            }
        });
    }//Fin del constructor

//-------------------------------------------------------------------------------------------------------------
    // ---- CARGA MASIVA (ACCESOS - PRACTICA 3) ----
    // ---- METODOS ----

    /**
     * Metodo para guardar archivos
     *
     * @param nombreArchivo
     */
    public void guardarCursos(String nombreArchivo) {
        //si el nombre del fichero no es nulo
        if (nombreArchivo != null) {
            //creamos un csvWriter que escriba el nombre del fichero que le hemos pasado.csv (para que no haya problemas)
            try (CSVWriter escribir = new CSVWriter(new FileWriter(Path.of(nombreArchivo + ".csv").toString()))) {
                //creamos una lista de array de string para fuardar las filas que contendran los cursos
                List<String[]> guardarFilasCur = new LinkedList<>();

                //en un array de enteros guardamos las filas seleccionadas
                int[] fila = tablaCur.getSelectedRows();

                //si la fila es distinta de 0
                if (fila.length != 0) {
                    //recorremos el array de los cursos seleccionados
                    for (int i : fila) {
                        //guardamos su codigo
                        String codigo = (String) tablaCur.getValueAt(i, 0);

                        //recorremos la lista de cursos
                        for (Curso c : controladorCursos.listar()) {
                            //cuando el codigo del curso coincida con el codigo del curso selccionado
                            if (codigo.equals(String.valueOf(c.getId()))) {
                                //añadimos a la lista de array de String el curso con el codigo y el nombre
                                guardarFilasCur.add(new String[]{String.valueOf(c.getId()), c.getNombre()});
                            }
                        }
                    }
                }
                //si la fila es igual a 0, osea que no a seleccionado nada
                else {
                    //recorremos la lista de cursos
                    for (Curso c : controladorCursos.listar()) {
                        //añadimos todos los cursos a la lista de array de string
                        guardarFilasCur.add(new String[]{String.valueOf(c.getId()), c.getNombre()});
                    }
                }

                //escribimos todas las filas en el fichero
                escribir.writeAll(guardarFilasCur);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metodo para leer un archivo .csv de cursos que tendra:
     * el codigo del curso , el nombre del curso
     *
     * @param archivo
     */
    public void leerCursos(File archivo) {
        //leemos el archivo
        try (Reader lectura = Files.newBufferedReader(Path.of(archivo.toURI()))) {
            //CSVParse va analizando el archivo
            CSVParser parser = new CSVParserBuilder().build();

            //leemos el fichero
            CSVReader lectorCSV = new CSVReaderBuilder(lectura)
                    .withSkipLines(0)//desde la linea 0
                    .withCSVParser(parser)
                    .build();

            //creamos un array de string
            String[] linea;

            //vaciamos la lista de cursos para añadir los nuevos del fichero y para que no haya problemas
            //si hay codigos repetidos
            //listaCur.getListaCursos().clear();



            //mientras que el archivo tenga una siguiente linea
            while ((linea = lectorCSV.readNext()) != null) {
                boolean repe = false;
                String cursoRepe = "";
                for (Curso c : controladorCursos.listar()) {
                    if(linea[0].matches("\\d") && c.getId() == Integer.parseInt(linea[0])) {
                        repe = true;
                        cursoRepe= linea[0];
                    }
                }

                if (linea[0].matches("\\d") && !repe) {
                    //los agregamos los cursos a la lista
                    controladorCursos.agregar(new Curso(Integer.parseInt(linea[0]), linea[1]));
                }
                else if (repe) {
                    JOptionPane.showMessageDialog(null, "Error al importar, curso "+cursoRepe+" repetido.");

                }
                else {
                    JOptionPane.showMessageDialog(null, "Error al importar, asegurate que el documento contenga cursos.");
                    break;
                }

            }

            //mostramos la tabla
            mostrarCursos();

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para leer un archo .csv de las notas del alumno, que tendra:
     * el DNI del alumno , nota1 , nota2 , nota3
     *
     * @param archivo
     */
    public void leerNotasAlum(File archivo) {
        //leemos el archivo
        try (Reader lectura = Files.newBufferedReader(Path.of(archivo.toURI()))) {
            //CSVParse va analizando el archivo
            CSVParser parser = new CSVParserBuilder().build();

            //leemis el fichero
            CSVReader lectorCSV = new CSVReaderBuilder(lectura)
                    .withSkipLines(0)//desde la linea 0
                    .withCSVParser(parser)
                    .build();

            //creamos un array de string
            String[] linea;

            //mientras que el archivo tenga una siguiente linea
            while ((linea = lectorCSV.readNext()) != null) {
                //creamos una nueva lista para los alumnos no encontrados
                List<String> listaAlumNoEncontrado = new ArrayList<>();

                //si cuando buscamos a un alumno por su DNI es nulo, es decir no existe el alumno
                if (controladorAlumnos.buscar(linea[0]) == null) {
                    //lo guardamos en nuestra nueva lista
                    listaAlumNoEncontrado.add(linea[0]);
                }

                //si la lista de alumnos no encontrado es distinta de 0, es decir esta llena
                if (listaAlumNoEncontrado.size() != 0) {
                    //recorremos dicha lista
                    for (String s : listaAlumNoEncontrado) {
                        //mostramos un mensaje
                        JOptionPane.showMessageDialog(null, "Alumno con DNI: " + s + " no encontrado");
                    }
                }

                //por si hay mas de 3 notas, hacemos un bucle vaya desde la columa 1 que seria la primera nota hasta
                //el final de la linea
                for (int col = 1; col < linea.length; col++) {
                    if (!linea[col].matches("[0-9.]+")) {
                        JOptionPane.showMessageDialog(null, "Error al importar, asegurate que el documento contenga alumnos con notas.");
                        break;
                    }
                    //vamos agregando al alumno con dni que corresponda la nota
                    controladorAlumnos.agregarNotaAlumno(linea[0], Double.parseDouble(linea[col]));
                }
            }

            //mostramos la tabla
            mostrarAlumnos();

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para leer un archo .csv de las asignaturas de los profesores, que tendra:
     * El DNI del profesor ; asignatura1 ; asignatura2 ; asignatura3
     *
     * @param archivo
     */
    public void leerAsigProfe(File archivo) {
        //leemos el archivo
        try (Reader lectura = Files.newBufferedReader(Path.of(archivo.toURI()))) {
            //CSVParse va analizando el archivo
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';') //porque esta separado por ; en vez de por ,
                    .build();

            //leemis el fichero
            CSVReader lectorCSV = new CSVReaderBuilder(lectura)
                    .withSkipLines(0)//desde la linea 0
                    .withCSVParser(parser)
                    .build();

            //creamos un array de string
            String[] linea;

            //mientras que el archivo tenga una siguiente linea
            while ((linea = lectorCSV.readNext()) != null) {
                //creamos una nueva lista para los profesores no encontrados
                List<String> listaProfeNoEncontrado = new ArrayList<>();

                //si cuando buscamos a un profesor por su DNI es nulo, es decir no existe el profesor
                if (controladorProfesores.buscar(linea[0]) == null) {
                    //lo guardamos en nuestra nueva lista
                    listaProfeNoEncontrado.add(linea[0]);
                }

                //si la lista de profesores no encontrado es distinta de 0, es decir esta llena
                if (listaProfeNoEncontrado.size() != 0) {
                    //recorremos dicha lista
                    for (String s : listaProfeNoEncontrado) {
                        //mostramos un mensaje
                        JOptionPane.showMessageDialog(null, "Profesor con DNI: " + s + " no encontrado");
                    }
                }

                //por si hay mas de 3 asignaturas, hacemos un bucle vaya desde la columa 1 que seria la primera nota hasta
                //el final de la linea
                for (int col = 1; col < linea.length; col++) {
                    //vamos agregando al profesor con dni que corresponda la nota
                    controladorProfesores.agregarAsigProfe(linea[0], linea[col]);
                }

            }

            //mostramos la tabla
            mostrarProfesores();

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

//-------------------------------------------------------------------------------------------------------------
    // ---- METODOS ----
    // ---- ALUMNO ----

    /**
     * Metodo agregar alumno que nos abre otra ventana, la cual nos permitira agregar un alumno si rellenamos todos los
     * campos correctamente
     */
    private void agregarAlumno() {
        AgregarAlumno a = new AgregarAlumno(this, true, controladorAlumnos, controladorCursos);
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
            for (Alumno a : controladorAlumnos.listar()) {
                if (dni.equalsIgnoreCase(a.getDNI())) {
                    buscarAlum = new String[]{a.getNombre(), a.getDNI(), String.valueOf(a.getTlf()), String.valueOf(a.getEdad()), a.getCurso().getNombre()};

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
                for (int i = 0; i != controladorAlumnos.listar().size(); i++) {
                    // si el DNI del alumno es igual al dni que nos pasan
                    if (dniBorrar.equalsIgnoreCase(controladorAlumnos.listar().get(i).getDNI())) {
                        // guardamos en nuestra variable el indice en el que estamos
                        ind = i;
                        // cambiamos el booleano a TRUE porque hemos encontrado el curso
                        encontrado = true;
                        // salimos
                        break;
                    }
                }

                // si el booleano es verdadero
                if (encontrado) {
                    // eliminamos de nuestra lista el alumno en el indice que hemos guardado en
                    // nuestra variable
                    //controladorAlumnos.getListaAlumnos().remove(ind);
                    controladorAlumnos.eliminar(dniBorrar);
                }

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

        for (Alumno alumno : controladorAlumnos.listar()) {
            arrayMostrarAlum = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

            modeloTablaAlum.insertRow(indice, arrayMostrarAlum);

            indice++;
        }
    }
    private void mostrarAlumAlfa() {

        indice = 0;
        modeloTablaAlum.setNumRows(0);

        for (Alumno alumno : controladorAlumnos.ordenarAlfabeticamente()) {
            arrayMostrarAlum = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

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
            for (int i = 0; i != controladorAlumnos.listar().size(); i++) {
                // si el DNI del alumno es igual al dni que nos pasan
                if (dni.equalsIgnoreCase(controladorAlumnos.listar().get(i).getDNI())) {
                    for (int j = 0; j <= 5; j++) {
                        if (j == 5) {
                            controladorAlumnos.agregarNotaAlumno(controladorAlumnos.listar().get(i).getDNI(), Double.parseDouble(notasAlumno));
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

        for (Alumno alumno : controladorAlumnos.listarSuspensos()) {
            arrayMostrarSus = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

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

        for (Alumno alumno : controladorAlumnos.listarAprobados()) {
            arrayMostrarApro = new String[]{alumno.getNombre(), alumno.getDNI(), String.valueOf(alumno.getTlf()),
                    String.valueOf(alumno.getEdad()), alumno.getCurso().getNombre(), alumno.getListaNotas().toString()};

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
        AgregarProfesor p = new AgregarProfesor(this, true, controladorProfesores, controladorCursos);
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
            for (Profesor p : controladorProfesores.listar()) {
                if (dni.equalsIgnoreCase(p.getDNI())) {
                    buscarProfe = new String[]{p.getNombre(), p.getDNI(), String.valueOf(p.getTlf()),
                            String.valueOf(p.getEdad()), String.valueOf(p.getCurso())};

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
                for (int i = 0; i != controladorProfesores.listar().size(); i++) {
                    // si el DNI del profe es igual al dni que nos pasan
                    if (dniBorrar.equalsIgnoreCase(controladorProfesores.listar().get(i).getDNI())) {
                        // guardamos en nuestra variable el indice en el que estamos
                        ind = i;
                        // cambiamos el booleano a TRUE porque hemos encontrado el dni
                        encontrado = true;
                        // salimos
                        break;
                    }
                }

                // si el booleano es verdadero
                if (encontrado) {
                    // eliminamos de nuestra lista el curso en el indice que hemos guardado en
                    // nuestra variable
                    //controladorProfesores.getListaProfesores().remove(ind);
                    controladorProfesores.eliminar(dniBorrar);
                }

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

        for (Profesor profesor : controladorProfesores.listar()) {
            arrayMostrarProfe = new String[]{profesor.getNombre(), profesor.getDNI(),
                    String.valueOf(profesor.getTlf()), String.valueOf(profesor.getEdad()), String.valueOf(profesor.getCurso()), profesor.getListaAsignaturas().toString()};

            modeloTablaProfe.insertRow(indice, arrayMostrarProfe);

            indice++;
        }
    }
    private void mostrarProfeAlfa() {
        indice = 0;
        modeloTablaProfe.setNumRows(0);

        for (Profesor p : controladorProfesores.ordenarAlfabeticamente()) {
            arrayMostrarProfe = new String[]{p.getNombre(), p.getDNI(),
                    String.valueOf(p.getTlf()), String.valueOf(p.getEdad()), String.valueOf(p.getCurso()), p.getListaAsignaturas().toString()};

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

        for (Profesor profesor : controladorProfesores.listarTutores()) {
            arrayMostrarTutores = new String[]{profesor.getNombre(), profesor.getDNI(),
                    String.valueOf(profesor.getTlf()), String.valueOf(profesor.getEdad()), String.valueOf(profesor.getCurso())};

            modeloTablaProfe.insertRow(indice, arrayMostrarTutores);

            indice++;
        }
    }

    private void mostrarAsigProfesor() {
        String asignatura = JOptionPane.showInputDialog("Introduce la asignatura al profesor");

        int filaSeleccionadaAlumNota = tablaProfe.getSelectedRow();

        if (filaSeleccionadaAlumNota == -1) {
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
        } else {
            String dni = String.valueOf(tablaProfe.getValueAt(filaSeleccionadaAlumNota, 1));

            // recorremos la listaAlumnos
            for (int i = 0; i != controladorProfesores.listar().size(); i++) {
                // si el DNI del alumno es igual al dni que nos pasan
                if (dni.equalsIgnoreCase(controladorProfesores.listar().get(i).getDNI())) {
                    controladorProfesores.agregarAsigProfe(controladorProfesores.listar().get(i).getDNI(), asignatura);
                    // salimos
                    break;
                }
            }
            mostrarProfesores();
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
            controladorCursos.agregar(new Curso(nombreCur));
        else
            JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
    }

    /**
     * Metodo para buscar curso a traves de su codigo
     */
    private void buscarCurso() {
        id = JOptionPane.showInputDialog("Introduce el codigo");

        Curso cursito = controladorCursos.buscar(id);
        String[] buscarCurso = new String[]{String.valueOf(cursito.getId()), cursito.getNombre()};

        modeloTablaCur.setNumRows(0);
        modeloTablaCur.insertRow(0, buscarCurso);
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

        List<String> listaCur = new ArrayList<>();
        listaCur.add((String) tablaCur.getValueAt(filaSeleccionadaCur,1));

        int numeroAlum = controladorAlumnos.buscarCursoAlum(listaCur).size();

        JPanel panel = new JPanel();
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Estas seguro?\n Se van a eliminar " + numeroAlum + " alumnos.", "Eliminar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            if (filaSeleccionadaCur == -1) {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");
            } else {
                int codigoBorrar = Integer.parseInt((String) tablaCur.getValueAt(filaSeleccionadaCur, 0));

                // recorremos la listaCur
                for (int i = 0; i != controladorCursos.listar().size(); i++) {
                    // si el codigo del curso es igual al codigo del curso que nos pasan
                    if (codigoBorrar == controladorCursos.listar().get(i).getId()) {
                        // guardamos en nuestra variable el indice en el que estamos
                        ind = i;
                        // cambiamos el booleano a TRUE porque hemos encontrado el curso
                        encontrado = true;
                        cursoSave = controladorCursos.listar().get(i).getNombre();
                        // salimos
                        break;
                    }
                }

                // si el booleano es verdadero
                if (encontrado) {
                    controladorCursos.eliminar(String.valueOf(codigoBorrar));
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

        for (Curso c : controladorCursos.listar()) {
            arrayMostrarCursos = new String[]{String.valueOf(c.getId()), c.getNombre()};
            modeloTablaCur.insertRow(indice, arrayMostrarCursos);

           indice++;
       }
    }
    private void mostrarCursosAlfa() {
        indice = 0;
        modeloTablaCur.setNumRows(0);

        for (Curso c : controladorCursos.ordenarALfabeticamente()) {
            arrayMostrarCursos = new String[]{String.valueOf(c.getId()), c.getNombre()};
            modeloTablaCur.insertRow(indice, arrayMostrarCursos);

            indice++;
        }
    }

}