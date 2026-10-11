package org.AutoFix.dao;

import org.AutoFix.model.Repuesto;
import java.util.List;

public interface RepuestoDao {
    List<Repuesto> listarRepuestos();
    void agregarRepuesto(Repuesto repuesto);
    void actualizarRepuesto(Repuesto repuesto);
    void eliminarRepuesto(int idRepuesto);
    Repuesto buscarRepuestoPorId(int idRepuesto);
}