package modelo;

public class ActuadorBombilla extends Actuador {

    public ActuadorBombilla() {
        super("bulb", "Bombilla", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        // Solo aceptamos ON y OFF, lo demas se ignora 
        if (accion.equals("ON") || accion.equals("OFF")) {
            this.estado = accion;
        }
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"ON", "OFF"};
    }
}
