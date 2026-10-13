package org.AutoFix.dao;

import org.AutoFix.model.DetalleRepuesto;
import org.AutoFix.exceptions.DBException;
import java.util.List;

public interface DetalleRepuestoDao {
    void agregarDetalleRepuesto(DetalleRepuesto detalle) throws DBException;
    List<DetalleRepuesto> listarDetallesRepuestos() throws DBException;
}