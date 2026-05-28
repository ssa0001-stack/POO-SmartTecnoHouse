package modelo;

import java.util.Random;

public class SensorTemperatura extends Sensor {

    private Random rand;

    public SensorTemperatura() {
        super("temp", "Sensor de Temperatura", "ºC");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        // Lecturas entre 15 y 35 grados
        double nueva = 15 + (rand.nextDouble() * 20);
        // redondeamos
        this.ultimoValor = Math.round(nueva * 10.0) / 10.0;
    }
}
