package modelo;

import java.util.Random;

public class SensorLuz extends Sensor {

    private Random rand;

    public SensorLuz() {
        super("light", "Sensor de Luz", "lux");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        // Lectura entre 0 y 1000 lux
        this.ultimoValor = rand.nextInt(1001);
    }
}
