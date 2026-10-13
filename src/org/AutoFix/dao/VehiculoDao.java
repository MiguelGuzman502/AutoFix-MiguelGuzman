package org.AutoFix.dao;

import org.AutoFix.model.Vehiculo;
import java.util.List;

public interface VehiculoDao {
    List<Vehiculo> listarVehiculos();
    void agregarVehiculo(Vehiculo vehiculo);
    void actualizarVehiculo(Vehiculo vehiculo);
    void eliminarVehiculo(int idVehiculo);
    Vehiculo buscarVehiculoPorId(int idVehiculo);
}