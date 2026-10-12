package org.AutoFix.dao;
import java.time.LocalDate;

public interface TransaccionDao {

    void registrarReparacion(
            int idVehiculo,
            int idMecanico,
            LocalDate fecha,
            String descripcion,
            double costo,
            int kilometraje
    );
}
