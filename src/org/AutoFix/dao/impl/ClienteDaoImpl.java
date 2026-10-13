package org.AutoFix.dao.impl;

import org.AutoFix.dao.ClienteDao;
import org.AutoFix.model.Cliente;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.Conexion;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {

    @Override
    public void agregarCliente(Cliente cliente) throws DBException {
        String sql = "{call sp_AgregarCliente(?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, cliente.getNit());
            stmt.setString(2, cliente.getNombres());
            stmt.setString(3, cliente.getApellidos());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getDireccion());
            stmt.execute();
        } catch (SQLException e) {
            throw new DBException("Error al agregar el cliente", e);
        }
    }

    @Override
    public List<Cliente> listarClientes() throws DBException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "{call sp_ListarClientes()}";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNit(rs.getString("nit"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new DBException("Error al listar los clientes", e);
        }
        return lista;
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarCliente(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Cliente buscarClientePorId(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}