package modelo;

import java.util.List;

// Interfaz para el patrón Strategy. Cada regla mete su propia
// lógica en el método aplicar(). Así el sistema central solo 
// recorre la lista y las ejecuta sin importarle qué hacen por dentro.

public interface Regla {
    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
    String getDescripcion();
}
