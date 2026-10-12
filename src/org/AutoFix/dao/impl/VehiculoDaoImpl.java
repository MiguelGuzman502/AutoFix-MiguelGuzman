package org.AutoFix.dao.impl;

import org.AutoFix.dao.VehiculoDao;
import org.AutoFix.model.Vehiculo;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.Conexion;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDaoImpl implements VehiculoDao {

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) throws DBException {
        String sql = "{call sp_AgregarVehiculo(?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, vehiculo.getIdCliente());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getAnio());
            stmt.setString(5, vehiculo.getPlaca());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Error al agregar el vehículo", e);
        }
    }

    @Override
    public List<Vehiculo> listarVehiculos() throws DBException {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "{call sp_ListarVehiculos()}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setIdVehiculo(rs.getInt("idVehiculo"));
                v.setIdCliente(rs.getInt("idCliente"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAnio(rs.getInt("anio"));
                v.setPlaca(rs.getString("placa"));
                lista.add(v);
            }
        } catch (SQLException e) {
            throw new DBException("Error al listar los vehículos", e);
        }
        return lista;
    }

    @Override
    public void actualizarVehiculo(Vehiculo vehiculo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarVehiculo(int idVehiculo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Vehiculo buscarVehiculoPorId(int idVehiculo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}