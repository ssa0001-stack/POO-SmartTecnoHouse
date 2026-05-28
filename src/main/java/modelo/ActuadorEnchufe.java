package modelo;


// Nuevo enchufe inteligente  extra.
// Va casi igual que la bombilla, pero tiene un contador de encendidos
 
public class ActuadorEnchufe extends Actuador {

    private int vecesEncendido;

    public ActuadorEnchufe() {
        super("plug", "Enchufe Inteligente", "OFF");
        this.vecesEncendido = 0;
    }

    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equals("ON")) {
            this.estado = "ON";
            vecesEncendido++;
        } else if (accion.equals("OFF")) {
            this.estado = "OFF";
        }
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"ON", "OFF"};
    }

    public int getVecesEncendido() {
        return vecesEncendido;
    }
}
