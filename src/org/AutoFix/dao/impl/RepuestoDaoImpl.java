package org.AutoFix.dao.impl;

import org.AutoFix.dao.RepuestoDao;
import org.AutoFix.model.Repuesto;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.Conexion;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepuestoDaoImpl implements RepuestoDao {

    @Override
    public void agregarRepuesto(Repuesto repuesto) throws DBException {
        String sql = "{call sp_AgregarRepuesto(?, ?, ?, ?)}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, repuesto.getNombreRepuesto());
            stmt.setDouble(2, repuesto.getPrecio());
            stmt.setInt(3, repuesto.getStock());
            stmt.setString(4, repuesto.getProveedor());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Error al agregar el repuesto", e);
        }
    }

    @Override
    public List<Repuesto> listarRepuestos() throws DBException {
        List<Repuesto> lista = new ArrayList<>();
        String sql = "{call sp_ListarRepuestos()}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Repuesto r = new Repuesto();
                r.setIdRepuesto(rs.getInt("idRepuesto"));
                r.setNombreRepuesto(rs.getString("nombreRepuesto"));
                r.setPrecio(rs.getDouble("precio"));
                r.setStock(rs.getInt("stock"));
                r.setProveedor(rs.getString("proveedor"));
                lista.add(r);
            }
        } catch (SQLException e) {
            throw new DBException("Error al listar los repuestos", e);
        }
        return lista;
    }

    @Override
    public void actualizarRepuesto(Repuesto repuesto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarRepuesto(int idRepuesto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Repuesto buscarRepuestoPorId(int idRepuesto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}