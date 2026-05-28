package modelo;

import java.util.Random;

// Nuevo sensor de humedad, similar a los demás, así demostramos que la jerarquía
// aguanta añadir más sensores sin tener que tocar el resto del código.

public class SensorHumedad extends Sensor {

    private Random rand;

    public SensorHumedad() {
        super("hum", "Sensor de Humedad", "%");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        // Entre 20% y 90% 
        double nueva = 20 + (rand.nextDouble() * 70);
        this.ultimoValor = Math.round(nueva * 10.0) / 10.0;
    }
}
