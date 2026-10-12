package org.AutoFix.dao.impl;

import org.AutoFix.dao.TransaccionDao;
import org.AutoFix.util.Conexion;
import org.AutoFix.exceptions.DBException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransaccionDaoImpl implements TransaccionDao {

    @Override
    public void registrarReparacion(
            int idVehiculo,
            int idMecanico,
            LocalDate fecha,
            String descripcion,
            double costo,
            int kilometraje) {

        try (Connection conn = Conexion.getInstancia().getConexion();
             CallableStatement stmt = conn.prepareCall(
                     "{call sp_RegistrarReparacion(?, ?, ?, ?, ?, ?)}")) {

            stmt.setInt(1, idVehiculo);
            stmt.setInt(2, idMecanico);
            stmt.setDate(3, Date.valueOf(fecha));
            stmt.setString(4, descripcion);
            stmt.setDouble(5, costo);
            stmt.setInt(6, kilometraje);

            stmt.execute();

        } catch (SQLException e) {
            throw new DBException(
                    "Error al registrar la reparación", e);
        }
    }
}
