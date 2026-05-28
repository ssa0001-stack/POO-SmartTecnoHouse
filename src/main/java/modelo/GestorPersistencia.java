package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Guardamos el estado para que sobreviva al cierre de la app.
// Para no depender de librerías externas, lo monto a mano con StringBuilder
 
public class GestorPersistencia {

    private static final String FICHERO = "estado.json";

    public void guardar(List<Actuador> actuadores) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"actuadores\": [\n");
        for (int i = 0; i < actuadores.size(); i++) {
            Actuador a = actuadores.get(i);
            sb.append("    {\"id\": \"").append(a.getID()).append("\", ");
            sb.append("\"estado\": \"").append(a.getEstadoActual()).append("\"}");
            if (i < actuadores.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n");
        sb.append("}\n");

        try {
            FileWriter fw = new FileWriter(FICHERO);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el estado: " + e.getMessage());
        }
    }

    public void cargar(List<Actuador> actuadores) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FICHERO));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("\"id\"") && linea.contains("\"estado\"")) {
                    String id = extraerValor(linea, "id");
                    String estado = extraerValor(linea, "estado");
                    for (int i = 0; i < actuadores.size(); i++) {
                        if (actuadores.get(i).getID().equals(id)) {
                            actuadores.get(i).setEstado(estado);
                            break;
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            // Si no hay fichero previo, no pasa nada: primera ejecucion
            System.out.println("No hay estado previo, arrancamos con valores por defecto.");
        }
    }

    // Truco rapido para sacar el valor entre comillas tras una clave
    private String extraerValor(String linea, String clave) {
        int idxClave = linea.indexOf("\"" + clave + "\"");
        int idxDosp = linea.indexOf(":", idxClave);
        int idxC1 = linea.indexOf("\"", idxDosp);
        int idxC2 = linea.indexOf("\"", idxC1 + 1);
        return linea.substring(idxC1 + 1, idxC2);
    }
}
