package modelo;

import java.util.ArrayList;
import java.util.List;

// Lo hacemos Singleton para asegurarnos de que solo hay uno. De esta forma, el controlador 
// y la vista compartan datos y no se descoordinan

public class SmartTecnoHouse {

    private static SmartTecnoHouse instancia;

    private List<Sensor> sensores;
    private List<Actuador> actuadores;
    private List<Regla> reglasActivas;

    private SmartTecnoHouse() {
        sensores = new ArrayList<>();
        actuadores = new ArrayList<>();
        reglasActivas = new ArrayList<>();
        inicializarDispositivos();
    }

    public static SmartTecnoHouse getInstancia() {
        if (instancia == null) {
            instancia = new SmartTecnoHouse();
        }
        return instancia;
    }

    private void inicializarDispositivos() {
        // Dispositivos del prototipo base
        sensores.add(new SensorTemperatura());
        sensores.add(new SensorLuz());
        sensores.add(new SensorPresencia());
        sensores.add(new SensorHumedad());       // ampliacion

        actuadores.add(new ActuadorBombilla());
        actuadores.add(new ActuadorVentilador());
        actuadores.add(new ActuadorEnchufe());   // ampliacion

        // Reglas por defecto activas
        reglasActivas.add(new ReglaVentilacionConfortable());
        reglasActivas.add(new ReglaIluminacionAutomatica());
    }

    public void actualizarSensores() {
        for (int i = 0; i < sensores.size(); i++) {
            sensores.get(i).actualizarValor();
        }
    }

    public void aplicarReglas() {
        // se puede ver el olimorfismo, que que no sabemos que hace cada regla
        for (int i = 0; i < reglasActivas.size(); i++) {
            reglasActivas.get(i).aplicar(sensores, actuadores);
        }
    }

    public List<Sensor> getSensores() { return sensores; }
    public List<Actuador> getActuadores() { return actuadores; }
    public List<Regla> getReglas() { return reglasActivas; }
}
