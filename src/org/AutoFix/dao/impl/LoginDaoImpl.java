package org.AutoFix.dao.impl;
import org.AutoFix.dao.LoginDao;
import org.AutoFix.model.UsuarioSesion;
import org.AutoFix.exceptions.DBException;
import org.AutoFix.util.Conexion;
import java.sql.*;
public class LoginDaoImpl implements LoginDao {
    @Override public UsuarioSesion autenticar(String usuario, String password) {
        try (Connection conn=Conexion.getInstancia().getConexion();
             CallableStatement stmt=conn.prepareCall("{call sp_login(?, ?)}")) {
            stmt.setString(1,usuario); stmt.setString(2,password);
            try (ResultSet rs=stmt.executeQuery()) {
                if (!rs.next()) return null;
                // Ajustar los alias si sp_login devuelve columnas con otros nombres.
                return new UsuarioSesion(rs.getInt("idUsuario"), rs.getString("usuario"), rs.getString("rol"));
            }
        } catch (SQLException e) { throw new DBException("Error al validar el acceso; revisa columnas de sp_login",e); }
    }
}
