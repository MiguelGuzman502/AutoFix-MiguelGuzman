package org.AutoFix.dao.impl;

import org.AutoFix.dao.DetalleRepuestoDao;
import org.AutoFix.model.DetalleRepuesto;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.Conexion;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleRepuestoDaoImpl implements DetalleRepuestoDao {

    @Override
    public void agregarDetalleRepuesto(DetalleRepuesto detalle) throws DBException {
        String sql = "{call sp_AgregarDetalleRepuesto(?, ?, ?)}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, detalle.getIdOrdenTrabajo());
            stmt.setInt(2, detalle.getIdRepuesto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Error al agregar el detalle de repuesto", e);
        }
    }

    @Override
    public List<DetalleRepuesto> listarDetallesRepuestos() throws DBException {
        List<DetalleRepuesto> lista = new ArrayList<>();
        String sql = "{call sp_ListarDetalleRepuestos()}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DetalleRepuesto dr = new DetalleRepuesto();
                dr.setIdDetalle(rs.getInt("idDetalle"));
                dr.setIdOrdenTrabajo(rs.getInt("idOrdenTrabajo"));
                dr.setIdRepuesto(rs.getInt("idRepuesto"));
                dr.setCantidad(rs.getInt("cantidad"));
                lista.add(dr);
            }
        } catch (SQLException e) {
            throw new DBException("Error al listar los detalles de repuestos", e);
        }
        return lista;
    }
}
