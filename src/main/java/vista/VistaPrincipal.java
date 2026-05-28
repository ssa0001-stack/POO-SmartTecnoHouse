package vista;

import javax.swing.*;
import java.awt.*;

import modelo.Actuador;
import modelo.Sensor;
import modelo.SmartTecnoHouse;

// Ventana principal hecha con Swing. Aquí nos limitamos a dibujar la interfaz
// y a dejar los botones listos para que el controlador les enchufe los listeners.

public class VistaPrincipal extends JFrame {

    private JPanel panelSensores;
    private JPanel panelActuadores;
    private JButton botonActualizar;
    private JButton botonAplicarReglas;
    private JButton botonGuardar;
    private JTextArea areaLog;

    public VistaPrincipal() {
        setTitle("Smart TecnoHouse - Panel de Control");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelSensores = new JPanel();
        panelSensores.setBorder(BorderFactory.createTitledBorder("Sensores"));
        panelSensores.setLayout(new GridLayout(0, 1));

        panelActuadores = new JPanel();
        panelActuadores.setBorder(BorderFactory.createTitledBorder("Actuadores"));
        panelActuadores.setLayout(new GridLayout(0, 1));

        JPanel panelCentral = new JPanel(new GridLayout(1, 2));
        panelCentral.add(panelSensores);
        panelCentral.add(panelActuadores);

        botonActualizar = new JButton("Actualizar sensores");
        botonAplicarReglas = new JButton("Aplicar reglas");
        botonGuardar = new JButton("Guardar estado");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonActualizar);
        panelBotones.add(botonAplicarReglas);
        panelBotones.add(botonGuardar);

        areaLog = new JTextArea(6, 50);
        areaLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaLog);

        add(panelBotones, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(scrollLog, BorderLayout.SOUTH);
    }

    // Refresca los paneles
    public void refrescar() {
        SmartTecnoHouse casa = SmartTecnoHouse.getInstancia();

        panelSensores.removeAll();
        for (int i = 0; i < casa.getSensores().size(); i++) {
            Sensor s = casa.getSensores().get(i);
            JLabel etiqueta = new JLabel(s.getNombre() + ": " + s.getEstadoActual());
            panelSensores.add(etiqueta);
        }

        panelActuadores.removeAll();
        for (int i = 0; i < casa.getActuadores().size(); i++) {
            Actuador a = casa.getActuadores().get(i);
            JLabel etiqueta = new JLabel(a.getNombre() + ": " + a.getEstadoActual());
            panelActuadores.add(etiqueta);
        }

        panelSensores.revalidate();
        panelSensores.repaint();
        panelActuadores.revalidate();
        panelActuadores.repaint();
    }

    public void anadirLinea(String texto) {
        areaLog.append(texto + "\n");
    }

    public JButton getBotonActualizar() { return botonActualizar; }
    public JButton getBotonAplicarReglas() { return botonAplicarReglas; }
    public JButton getBotonGuardar() { return botonGuardar; }
}
