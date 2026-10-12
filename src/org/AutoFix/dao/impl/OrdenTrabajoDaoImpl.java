package org.AutoFix.dao.impl;

import org.AutoFix.dao.OrdenTrabajoDao;
import org.AutoFix.model.OrdenTrabajo;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoDaoImpl implements OrdenTrabajoDao {

    @Override
    public void agregarOrdenTrabajo(OrdenTrabajo orden) throws DBException {

        String sql = "{call sp_RegistrarReparacion(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, orden.getIdVehiculo());
            stmt.setInt(2, orden.getIdMecanico());
            stmt.setDate(3, Date.valueOf(orden.getFechaReparacion()));
            stmt.setString(4, orden.getDescripcion());
            stmt.setDouble(5, orden.getCosto());
            stmt.setInt(6, orden.getKilometraje());

            stmt.execute();

        } catch (SQLException e) {
            throw new DBException(
                    "Error al registrar la orden de trabajo", e);
        }
    }

    @Override
    public List<OrdenTrabajo> listarOrdenesTrabajo() throws DBException {

        List<OrdenTrabajo> lista = new ArrayList<>();

        String sql = "{call sp_ListarOrdenesTrabajo()}";

        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

               OrdenTrabajo orden = new OrdenTrabajo();

                        orden.setIdOrdenTrabajo(rs.getInt("idOrdenTrabajo"));
                        orden.setIdVehiculo(rs.getInt("idVehiculo"));
                        orden.setIdMecanico(rs.getInt("idMecanico"));
                        orden.setFechaReparacion(rs.getDate("fechaReparacion").toLocalDate());
                        orden.setDescripcion(rs.getString("descripcion"));
                        orden.setCosto(rs.getDouble("costo"));
                        orden.setKilometraje(rs.getInt("kilometraje"));

                            lista.add(orden);
            }

        } catch (SQLException e) {
            throw new DBException(
                    "Error al listar las órdenes de trabajo", e);
        }

        return lista;
    }

    @Override
    public void aprobarCalidad(int idOrdenTrabajo, int idJefe)
            throws DBException {

        String sql = "{call sp_AprobarCalidad(?, ?)}";

        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idOrdenTrabajo);
            stmt.setInt(2, idJefe);

            stmt.execute();

        } catch (SQLException e) {
            throw new DBException(
                    "Error al aprobar la calidad de la reparación", e);
        }
    }
}
