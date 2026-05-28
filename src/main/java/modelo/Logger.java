package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Servicio de logs. Lo dejamos como Singleton para que toda la app
//escriba en el mismo archivo sin que se pisen los datos, y de paso
//nos evitamos estar abriendo y cerrando el fichero en cada línea.
public class Logger {

    private static Logger instancia;
    private static final String FICHERO_LOG = "actuators.log";
    private SimpleDateFormat formatoFecha;

    private Logger() {
        formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static Logger getInstancia() {
        if (instancia == null) {
            instancia = new Logger();
        }
        return instancia;
    }

    //Añade una línea al log con la fecha, el aparato y su estado nuevo.
    //Si falla al escribir en el fichero, sacamos el aviso por consola 
    public void registrarCambio(String idActuador, String nuevoEstado) {
        String marca = formatoFecha.format(new Date());
        String linea = marca + " - " + idActuador + " -> " + nuevoEstado + "\n";

        try {
            // Ponemos append = true para escribir al final del archivo y no machacar lo que ya había
            FileWriter fw = new FileWriter(FICHERO_LOG, true);
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el log: " + e.getMessage());
        }
    }
}
