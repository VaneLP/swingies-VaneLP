package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
/*
 Paleta de colores:
 - #FCEE9C (amarillo)
 - #063842 (verde letras)
 - #FAF6D4 (amarillo pastel)
 - #819992 (gris verdoso)

 Botones
 - 9EE493 (verde)
 - F03A47 (rojo)
 */
public class Login extends JFrame {
    //atributos
    private JPanel panelPrincipal;
    private JButton login;
    private JCheckBox recuerdameCheckBox;
    private JPasswordField contra;
    private JTextField nombreU;
    String user = "Vane";
    char [] pass = new char[]{'1','2','3','4'};
    private JLabel iconoU;
    private JLabel iconoC;


    // ---- CONSTRUCTOR ----
    public Login() {
        //para que se muestre en la ventana nuestro panel
        setContentPane(panelPrincipal);

        //titulo
        setTitle("Inicio de sesión");

        //para que se cierre cuando le damos a la X
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //para que se ajuste solo
        setSize(350,450);

        //la ventana se coloque en el centro
        setLocationRelativeTo(null);

        //hacer visible la ventana
        setVisible(true);

        //ponemos dos imagenes
        iconoC.setIcon(new ImageIcon("src/Practica_1/imagenes/candado.png"));
        iconoU.setIcon(new ImageIcon("src/Practica_1/imagenes/usuario.png"));


        // ---- LISTENERS ----
        //boton login
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //user: Vane
                //pass: 1234
                if(nombreU.getText().equals(user) && Arrays.equals(contra.getPassword(), pass)) {
                    login.setBackground(new Color(158, 228, 147));
                    abrirMenu();

                }
                else {
                    login.setBackground(new Color(240, 58, 71));
                    JOptionPane.showMessageDialog(null, "     Ups, algo salió mal" +
                            "\nPero no te preocupes, no es culpa tuya.");
                }
            }
        });
    }

    //metodo abrir el menu
    public void abrirMenu(){
        Menu m1 = new Menu();
        m1.setVisible(true);
    }


    // ---- MAIN ----
    public static void main(String[] args) {
        //llamamos al splashScreen para que se ejecute antes que el login
        SplashScreen splash = new SplashScreen();
    }

}
