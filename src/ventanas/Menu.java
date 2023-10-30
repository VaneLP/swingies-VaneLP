package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Menu extends JDialog {
    //atributos
    private JTabbedPane tabbedPane1;
    private JPanel panelPrincipal;
    private JTabbedPane prof;
    private JTabbedPane cur;
    private JTabbedPane alum;

    //atributos menu
    private JMenu menu, menuA, menuP, menuC;
    private JMenuBar menuBar;
    private JLabel icono;
    private JMenuItem o1, o2, o3, o4;

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
    private JCheckBox alfebeticamenteCheckBox;
    private JCheckBox tutoresCheckBox;
    private JCheckBox alfabeticamenteCheckBox1;
    private DefaultTableModel modeloTablaAlum;
    private DefaultTableModel modeloTablaProfe;
    private DefaultTableModel modeloTablaCur;

//-------------------------------------------------------------------------------------------------------------
    public Menu(Login l , boolean b) {
        super(l,b);

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

//-------------------------------------------------------------------------------------------------------------
        // ---- ALUMNOS ----
        //creamos un nuevo menu y le ponemos un titulo
        menuA = new JMenu("Alumnos");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuA);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar");
        o2 = new JMenuItem("Buscar");
        o3 = new JMenuItem("Eliminar");
        o4 = new JMenuItem("Mostrar");

        //añadimos a nuestro menu los items que acabamos de crear
        menuA.add(o1);
        menuA.add(o2);
        menuA.add(o3);
        menuA.add(o4);

        // al menu de alumnos le asociamos el atajo de Alt+A
        menuA.setMnemonic(KeyEvent.VK_A);

//-------------------------------------------------------------------------------------------------------------
        // ---- PROFESORES ----
        //creamos un nuevo menu y le ponemos un titulo
        menuP = new JMenu("Profesores");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuP);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar");
        o2 = new JMenuItem("Buscar");
        o3 = new JMenuItem("Eliminar");
        o4 = new JMenuItem("Mostrar");

        //añadimos a nuestro menu los items que acabamos de crear
        menuP.add(o1);
        menuP.add(o2);
        menuP.add(o3);
        menuP.add(o4);

        // al menu de profesores le asociamos el atajo de Alt+P
        menuP.setMnemonic(KeyEvent.VK_P);

//-------------------------------------------------------------------------------------------------------------
        // ---- CURSO ----
        //creamos un nuevo menu y le ponemos un titulo
        menuC = new JMenu("Curso");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menuC);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar");
        o2 = new JMenuItem("Buscar");
        o3 = new JMenuItem("Eliminar");
        o4 = new JMenuItem("Mostrar");

        //añadimos a nuestro menu los items que acabamos de crear
        menuC.add(o1);
        menuC.add(o2);
        menuC.add(o3);
        menuC.add(o4);

        // al menu de curso le asociamos el atajo de Alt+C
        menuC.setMnemonic(KeyEvent.VK_C);

//-------------------------------------------------------------------------------------------------------------
        // ---- ABOUT ----
        // creamos un nuevo menu y le ponemos un titulo
        menu = new JMenu("About");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menu);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Interfaz realizada por Vanessa Lopez Pastor");

        //añadimos a nuestro menu los items que acabamos de crear
        menu.add(o1);

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

        //a nuestra tabla de alumnos le asignamos el modelo
        tablaAlum.setModel(modeloTablaAlum);

        //hacemos la tabla visible (no afecta en nada)
        tablaAlum.setVisible(true);

        //al scroll le asignamos la tabla
        scrollAlum.add(tablaAlum);
        //hacemos que se visualicen los titulos de las tablas
        scrollAlum.setViewportView(tablaAlum);

        // BOTONES

//-------------------------------------------------------------------------------------------------------------
        // ---- TABLA PROFESORES ----
        //creamos un modelo de tabla para los alumnos
        modeloTablaProfe= new DefaultTableModel();

        //le añadimos las columnas
        modeloTablaProfe.addColumn("Nombre");
        modeloTablaProfe.addColumn("DNI");
        modeloTablaProfe.addColumn("Telefono");
        modeloTablaProfe.addColumn("Edad");

        //a nuestra tabla de alumnos le asignamos el modelo
        tablaProfe.setModel(modeloTablaProfe);

        //hacemos la tabla visible (no afecta en nada)
        tablaProfe.setVisible(true);

        //al scroll le asignamos la tabla
        scrollProfe.add(tablaProfe);
        //hacemos que se visualicen los titulos de las tablas
        scrollProfe.setViewportView(tablaProfe);

//-------------------------------------------------------------------------------------------------------------
        // ---- TABLA CURSOS ----
        //creamos un modelo de tabla para los alumnos
        modeloTablaCur= new DefaultTableModel();

        //le añadimos las columnas
        modeloTablaCur.addColumn("Codigo");
        modeloTablaCur.addColumn("Nombre");

        //a nuestra tabla de alumnos le asignamos el modelo
        tablaCur.setModel(modeloTablaCur);

        //hacemos la tabla visible (no afecta en nada)
        tablaCur.setVisible(true);

        //al scroll le asignamos la tabla
        scrollCur.add(tablaCur);
        //hacemos que se visualicen los titulos de las tablas
        scrollCur.setViewportView(tablaCur);

//-------------------------------------------------------------------------------------------------------------
        // ---- LISTENERS ----
        // ---- LISTENER BOTON AGREGAR ALUMNO ----
        agregarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirAgregar();
            }
        });

        // ---- LISTENER BOTON BUSCAR ALUMNO ----
        buscarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni=JOptionPane.showInputDialog("Introduce el DNI");
                System.out.println("buscado");
            }
        });

        // ---- LISTENER BOTON ELIMINAR ALUMNO ----
        eliminarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });

        // ---- LISTENER BOTON MOSTRAR ALUMNO ----
        mostrarAlum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//-------------------------------------------------------------------------------------------------------------
        // ---- LISTENER BOTON AGREGAR PROFESOR ----
        agregarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirAgregar();
            }
        });

        // ---- LISTENER BOTON BUSCAR PROFESOR ----
        buscarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni=JOptionPane.showInputDialog("Introduce el DNI");
                System.out.println("buscado");
            }
        });

        // ---- LISTENER BOTON ELIMINAR PROFESOR ----
        eliminarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });

        // ---- LISTENER BOTON MOSTRAR PROFESOR ----
        mostrarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//-------------------------------------------------------------------------------------------------------------
        // ---- LISTENER BOTON AGREGAR CURSO ----
        agregarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCur=JOptionPane.showInputDialog("Introduce el nombre del curso");
                System.out.println("añadido");
            }
        });

        // ---- LISTENER BOTON BUSCAR CURSO ----
        buscarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni=JOptionPane.showInputDialog("Introduce el codigo");
                System.out.println("buscado");
            }
        });

        // ---- LISTENER BOTON ELIMINAR CURSO ----
        eliminaCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });

        // ---- LISTENER BOTON MOSTRAR CURSO ----
        mostrarCur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });





    }


//-------------------------------------------------------------------------------------------------------------
    // ---- METODOS ----
    //eliminar
    private static void eliminar() {
        //todo
        JPanel panel = new JPanel();
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Estas seguro?","Eliminar", JOptionPane.YES_NO_OPTION);
        if(opcion==JOptionPane.YES_OPTION)
            System.out.println("eliminado");
        else
            System.out.println("no eliminado");
    }

    //abrir ventana modal agregar para profe como para alum
    public void abrirAgregar(){
        Agregar a = new Agregar(this,true);
        a.setVisible(true);
    }




}
