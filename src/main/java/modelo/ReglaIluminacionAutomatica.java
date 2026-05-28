package modelo;

import java.util.List;

public class ReglaIluminacionAutomatica implements Regla {

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        // Con poca luz y presencia, encendemos la bombilla
        double luz = -1;
        double presencia = 0;

        for (int i = 0; i < sensores.size(); i++) {
            Sensor s = sensores.get(i);
            if (s.getID().equals("light")) {
                luz = s.getValor();
            } else if (s.getID().equals("pir")) {
                presencia = s.getValor();
            }
        }
        if (luz == -1) return;

        for (int i = 0; i < actuadores.size(); i++) {
            Actuador a = actuadores.get(i);
            if (a.getID().equals("bulb")) {
                if (luz < 200 && presencia == 1) {
                    a.ejecutarAccion("ON");
                } else {
                    a.ejecutarAccion("OFF");
                }
                break;
            }
        }
    }

    @Override
    public String getDescripcion() {
        return "R2. Iluminacion automatica por luz y presencia";
    }
}
