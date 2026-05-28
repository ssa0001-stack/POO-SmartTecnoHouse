package modelo;


 //Clase base para actuadores, bombilla, ventilador, enchufe...
 //Cada uno guarda cómo está ahora mismo ON, OFF, HIGH, y que cosas se le pueden hacer.
 
public abstract class Actuador implements IDispositivo {

    protected String id;
    protected String nombre;
    protected String estado;

    public Actuador(String id, String nombre, String estadoInicial) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estadoInicial;
    }

    public abstract void ejecutarAccion(String accion);

    public abstract String[] getAccionesPosibles();

    // Necesario para que la persistencia pueda restaurar estados al cargar
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String getID() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getEstadoActual() { return estado; }
}
