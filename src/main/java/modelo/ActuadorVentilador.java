package modelo;

public class ActuadorVentilador extends Actuador {

    public ActuadorVentilador() {
        super("fan", "Ventilador", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        // Admite tres velocidades ademas de apagado
        if (accion.equals("OFF") || accion.equals("LOW")
                || accion.equals("MID") || accion.equals("HIGH")) {
            this.estado = accion;
        }
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"OFF", "LOW", "MID", "HIGH"};
    }
}
