package modelo;

import java.util.List;

public class ReglaVentilacionConfortable implements Regla {

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        // Buscamos el sensor de temperatura
        double tempActual = -999; 
        for (int i = 0; i < sensores.size(); i++) {
            Sensor s = sensores.get(i);
            if (s.getID().equals("temp")) {
                tempActual = s.getValor();
                break;
            }
        }
        if (tempActual == -999) return;

        // Actuamos sobre el ventilador segun los grados que hay
        for (int i = 0; i < actuadores.size(); i++) {
            Actuador a = actuadores.get(i);
            if (a.getID().equals("fan")) {
                if (tempActual >= 28) {
                    a.ejecutarAccion("HIGH");
                } else if (tempActual >= 24) {
                    a.ejecutarAccion("MID");
                } else if (tempActual >= 21) {
                    a.ejecutarAccion("LOW");
                } else {
                    a.ejecutarAccion("OFF");
                }
                break;
            }
        }
    }

    @Override
    public String getDescripcion() {
        return "R1. Ventilacion confortable segun temperatura";
    }
}
