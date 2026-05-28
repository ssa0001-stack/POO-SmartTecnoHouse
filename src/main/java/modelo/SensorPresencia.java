package modelo;

import java.util.Random;

public class SensorPresencia extends Sensor {

    private Random rand;

    public SensorPresencia() {
        super("pir", "Sensor de Presencia", "");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        // 1 es hay alguien y 0 es no hay nadie
        this.ultimoValor = rand.nextInt(2);
    }

    @Override
    public String getEstadoActual() {
        if (ultimoValor == 1) {
            return "Detectado";
        }
        return "Sin movimiento";
    }
}
