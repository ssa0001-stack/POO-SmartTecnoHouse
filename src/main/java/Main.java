import javax.swing.SwingUtilities;
import controlador.ControladorPrincipal;
import vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {
        // Esto lanza la GUI en el hilo de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaPrincipal v = new VistaPrincipal();
                new ControladorPrincipal(v);
                v.setVisible(true);
            }
        });
    }
}
