package org.AutoFix.dao;

import org.AutoFix.model.OrdenTrabajo;
import org.AutoFix.exceptions.DBException;
import java.util.List;

public interface OrdenTrabajoDao {

    void agregarOrdenTrabajo(OrdenTrabajo orden) throws DBException;

    List<OrdenTrabajo> listarOrdenesTrabajo() throws DBException;

    void aprobarCalidad(int idOrdenTrabajo, int idJefe) throws DBException;
}
