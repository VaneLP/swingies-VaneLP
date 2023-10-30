package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//creamos una nueva clase que extienda de JWindow
public class SplashScreen extends JWindow {
    //creamos una nueva barra de progreso
    private JProgressBar barraProgreso = new JProgressBar();
    static int count = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
    static Timer timer;

    // ---- CONSTRUCTOR ----
    public SplashScreen() {
        //creamos un contenedor vacio
        Container container = getContentPane();

        //creamos un nuevo JPAnel
        JPanel ventana = new JPanel();
        //añadimos la ventana al contenedor vacio
        container.add(ventana, BorderLayout.CENTER);

        //Creamos un nuevo label
        JLabel icono = new JLabel();
        //le añadimos la imagen
        icono.setIcon(new ImageIcon("src/imagenes/logoThiar.png"));

        //añadimos a la ventana nuestro label
        ventana.add(icono);

        //cambiamos el fondo de la barra
        barraProgreso.setBackground(new Color(129,153,146));
        //cambiamos el color de la barra
        barraProgreso.setForeground(new Color(252,238,156));
        //le añadimos a nuestra barra de progreso el maximo
        barraProgreso.setMaximum(PROGBAR_MAX);
        //añadimos al contenedor al que ya teniamos el label nuestra barra de progreso
        container.add(barraProgreso, BorderLayout.SOUTH);

        //para ajustarla al minimo tamaño posible
        pack();
        //lo colocamos en el centro
        setLocationRelativeTo(null);
        //hacemos visible
        setVisible(true);
        //la ejecutamos
        startProgressBar();
    }

    // ---- LISTENERS ----
    /**
     * Vigila el valor del progreso para que cuando se complete finalice y se abra el login
     */
    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            //le añadimos a nuestra barra de progreso el valor del contador = 1
            barraProgreso.setValue(count);
            //vamos comprobadon si el maximo es igual contador
            if (PROGBAR_MAX == count) {
                //termina el timer
                timer.stop();
                //hace invisible el splashScreen
                SplashScreen.this.setVisible(false);
                //llama al metodo para que se abra el login
                createAndShowFrame();
            }
            //sumamos el contador
            count++;
        }
    };

    // ---- METODOS ----

    /**
     * Para que empiece nuestra barra de progreso
     */
    private void startProgressBar() {
        //creamos un nuevo Timer al que le añadimos un delay y luego llamamos al listener
        timer = new Timer(TIMER_PAUSE, al);
        //hacemos que empiece
        timer.start();
    }

    /**
     * cuando termine la barra de progreso se nos abrira nuestro login
     */
    private void createAndShowFrame() {
        //ejecutamos el login
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login l = new Login();
            }
        });
    }

}
