package org.AutoFix.dao.impl;

import org.AutoFix.dao.MecanicoDao;
import org.AutoFix.model.Mecanico;
import org.AutoFix.util.Conexion;
import org.AutoFix.exceptions.DBException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDaoImpl implements MecanicoDao {

    @Override
    public List<Mecanico> listarMecanicos() {
        List<Mecanico> lista = new ArrayList<>();
        String sql = "call sp_listarmecanicos()";
        
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Mecanico m = new Mecanico();
                m.setIdMecanico(rs.getInt("idmecanico"));
                m.setNombres(rs.getString("nombres"));
                m.setApellidos(rs.getString("apellidos"));
                m.setTelefono(rs.getString("telefono"));
                lista.add(m);
            }
        } catch (SQLException e) {
            throw new DBException("error al listar los mecanicos", e);
        }
        return lista;
    }

    @Override
    public void agregarMecanico(Mecanico mecanico) {
        String sql = "call sp_agregarmecanico(?, ?, ?)";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql)) {
            pstmt.setString(1, mecanico.getNombres());
            pstmt.setString(2, mecanico.getApellidos());
            pstmt.setString(3, mecanico.getTelefono());
            pstmt.execute();
        } catch (SQLException e) {
            throw new DBException("error al agregar el mecanico", e);
        }
    }

    @Override
    public void actualizarMecanico(Mecanico mecanico) {
        String sql = "call sp_actualizarmecanico(?, ?, ?, ?)";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql)) {
            pstmt.setInt(1, mecanico.getIdMecanico());
            pstmt.setString(2, mecanico.getNombres());
            pstmt.setString(3, mecanico.getApellidos());
            pstmt.setString(4, mecanico.getTelefono());
            pstmt.execute();
        } catch (SQLException e) {
            throw new DBException("error al actualizar el mecanico", e);
        }
    }

    @Override
    public void eliminarMecanico(int idMecanico) {
        String sql = "call sp_eliminarmecanico(?)";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql)) {
            pstmt.setInt(1, idMecanico);
            pstmt.execute();
        } catch (SQLException e) {
            throw new DBException("error al eliminar el mecanico", e);
        }
    }

    @Override
    public Mecanico buscarMecanicoPorId(int idMecanico) {
        Mecanico m = null;
        String sql = "call sp_buscarmecanicoporid(?)";
        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement pstmt = conn.prepareCall(sql)) {
            pstmt.setInt(1, idMecanico);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    m = new Mecanico();
                    m.setIdMecanico(rs.getInt("idmecanico"));
                    m.setNombres(rs.getString("nombres"));
                    m.setApellidos(rs.getString("apellidos"));
                    m.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            throw new DBException("error al buscar el mecanico", e);
        }
        return m;
    }
}