package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Actuador;
import modelo.GestorPersistencia;
import modelo.Logger;
import modelo.SmartTecnoHouse;
import vista.VistaPrincipal;


// Hace de puente entre la pantalla y los datos. Si el usuario toca algo
// el controlador ve qué hay que hacer, actualiza las cosas y refresca la pantalla.
// También le da el aviso al Logger si cambian los actuadores
 
public class ControladorPrincipal implements ActionListener {

    private VistaPrincipal vista;
    private SmartTecnoHouse modelo;
    private GestorPersistencia persistencia;
    private Logger logger;

    public ControladorPrincipal(VistaPrincipal vista) {
        this.vista = vista;
        this.modelo = SmartTecnoHouse.getInstancia();
        this.persistencia = new GestorPersistencia();
        this.logger = Logger.getInstancia();

        // Cargamos el estado guardado nada mas arrancar
        persistencia.cargar(modelo.getActuadores());

        // Eventos de los botones
        vista.getBotonActualizar().addActionListener(this);
        vista.getBotonAplicarReglas().addActionListener(this);
        vista.getBotonGuardar().addActionListener(this);

        vista.refrescar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();

        if (origen == vista.getBotonActualizar()) {
            modelo.actualizarSensores();
            vista.anadirLinea("Sensores actualizados.");
            vista.refrescar();

        } else if (origen == vista.getBotonAplicarReglas()) {
            // Nos guardamos cómo estaba antes para ver si algo cambia
            String[] estadosAntes = new String[modelo.getActuadores().size()];
            for (int i = 0; i < modelo.getActuadores().size(); i++) {
                estadosAntes[i] = modelo.getActuadores().get(i).getEstadoActual();
            }

            modelo.aplicarReglas();

            // Miramos qué ha cambiado, si un actuador es distinto, lo apuntamos en el log
            for (int i = 0; i < modelo.getActuadores().size(); i++) {
                Actuador a = modelo.getActuadores().get(i);
                if (!estadosAntes[i].equals(a.getEstadoActual())) {
                    logger.registrarCambio(a.getID(), a.getEstadoActual());
                }
            }

            vista.anadirLinea("Reglas aplicadas sobre los actuadores.");
            vista.refrescar();

        } else if (origen == vista.getBotonGuardar()) {
            persistencia.guardar(modelo.getActuadores());
            vista.anadirLinea("Estado guardado en estado.json.");
        }
    }
}
