package org.AutoFix.dao;

import org.AutoFix.model.Mecanico;
import java.util.List;

public interface MecanicoDao {
    List<Mecanico> listarMecanicos();
    void agregarMecanico(Mecanico mecanico);
    void actualizarMecanico(Mecanico mecanico);
    void eliminarMecanico(int idMecanico);
    Mecanico buscarMecanicoPorId(int idMecanico);
}