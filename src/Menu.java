import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panelPrincipal;
    private JTabbedPane prof;
    private JTabbedPane cur;
    private JTabbedPane alum;

    private JMenu menu, menuA, menuP;
    private JMenuBar menuBar;
    private JLabel icono;
    private JLabel prueba;
    private JMenuItem o1, o2, o3, o4;

    public Menu() {
        setContentPane(panelPrincipal);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(700, 500);

        setLocationRelativeTo(null);

        setVisible(true);

        // ---- MENU ----

        //asignamos el JMenuBAr
        setJMenuBar(menuBar);

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


        //PRUEBAS 2
        menuA.setMnemonic(KeyEvent.VK_A);

        menuA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prueba.setText("FUNCIONA NEMO ALUM");
            }
        });


        //TODO cambiar todo
        //pruebas
//        o1.setMnemonic(KeyEvent.VK_A);
//
//        o1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                prueba.setText("FUNCIONO");
//            }
//        });


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


        //PRUEBAS 2
        menuP.setMnemonic(KeyEvent.VK_P);

        menuP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prueba.setText("FUNCIONA NEMO PROF");
            }
        });





        // ---- CURSO ----
        //creamos un nuevo menu y le ponemos un titulo
        menu = new JMenu("Curso");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menu);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar"+"      "+"c+1");
        o2 = new JMenuItem("Buscar"+"      "+"c+2");
        o3 = new JMenuItem("Eliminar"+"      "+"c+3");
        o4 = new JMenuItem("Mostrar"+"      "+"c+4");

        //añadimos a nuestro menu los items que acabamos de crear
        menu.add(o1);
        menu.add(o2);
        menu.add(o3);
        menu.add(o4);

        // ---- ABOUT ----
        // creamos un nuevo menu y le ponemos un titulo
        menu = new JMenu("About");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menu);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Interfaz realizada por Vanessa Lopez Pastor");

        //añadimos a nuestro menu los items que acabamos de crear
        menu.add(o1);

        //agregamos una imagen
        icono.setIcon(new ImageIcon("src/imagenes/logoThiar.png"));


    }


    // ---- MAIN ----
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu m = new Menu();
            }
        });
    }

}
