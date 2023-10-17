import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panelPrincipal;
    private JTabbedPane prof;
    private JTabbedPane cur;
    private JTabbedPane alum;

    private JMenu menu;
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
        menu = new JMenu("Alumnos");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menu);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar"+"      "+"a+1");
        o2 = new JMenuItem("Agregar"+"      "+"a+2");
        o3 = new JMenuItem("Agregar"+"      "+"a+3");
        o4 = new JMenuItem("Agregar"+"      "+"a+4");

        //añadimos a nuestro menu los items que acabamos de crear
        menu.add(o1);
        menu.add(o2);
        menu.add(o3);
        menu.add(o4);

        // ---- LISTENERS ALUMNOS ----
        tabbedPane1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });
        o1.addMenuKeyListener(new MenuKeyListener() {
            @Override
            public void menuKeyTyped(MenuKeyEvent e) {

            }

            @Override
            public void menuKeyPressed(MenuKeyEvent e) {
                if(e.equals("a"))
                    prueba.setText("dsasd");
            }

            @Override
            public void menuKeyReleased(MenuKeyEvent e) {

            }
        });

        // ---- PROFESORES ----
        //creamos un nuevo menu y le ponemos un titulo
        menu = new JMenu("Profesores");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menu);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar"+"      "+"p+1");
        o2 = new JMenuItem("Agregar"+"      "+"p+2");
        o3 = new JMenuItem("Agregar"+"      "+"p+3");
        o4 = new JMenuItem("Agregar"+"      "+"p+4");

        //añadimos a nuestro menu los items que acabamos de crear
        menu.add(o1);
        menu.add(o2);
        menu.add(o3);
        menu.add(o4);

        // ---- CURSO ----
        //creamos un nuevo menu y le ponemos un titulo
        menu = new JMenu("Curso");
        //a nuestro JMenuBar le asignamos nuestro menu
        menuBar.add(menu);

        //creamos los items y le pomenos un nombre
        o1 = new JMenuItem("Agregar"+"      "+"c+1");
        o2 = new JMenuItem("Agregar"+"      "+"c+2");
        o3 = new JMenuItem("Agregar"+"      "+"c+3");
        o4 = new JMenuItem("Agregar"+"      "+"c+4");

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
