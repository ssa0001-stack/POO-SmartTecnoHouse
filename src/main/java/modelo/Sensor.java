package modelo;


//Clase base para todos los sensores.
//Aquí metemos lo que es común a todos, el id, el nombre, la unidad y el último valor leído.
//Luego cada sensor actualiza sus propios datos.
 
public abstract class Sensor implements IDispositivo {

    protected String id;
    protected String nombre;
    protected String unidad;
    protected double ultimoValor;

    public Sensor(String id, String nombre, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.ultimoValor = 0.0;
    }

    public abstract void actualizarValor();

    public double getValor() {
        return ultimoValor;
    }

    @Override
    public String getID() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getEstadoActual() {
        // Mostramos valor junto a la unidad 
        return ultimoValor + " " + unidad;
    }
}
