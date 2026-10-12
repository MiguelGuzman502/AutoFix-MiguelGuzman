
package org.AutoFix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Conexion instancia;
    private Connection conexion;

    private static final Logger LOGGER =
            Logger.getLogger(Conexion.class.getName());

    private final String URL = "jdbc:mysql://localhost:3306/db_autofix?useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "MiguelAngel2009";

    private Conexion() {
        conectar();
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.conexion = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            LOGGER.log(Level.INFO,
                    "Conexion a la base de datos establecida correctamente.");

        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE,
                    "Error al conectar con la base de datos", e);
            this.conexion = null;
        }
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        } else {
            try {
                if (instancia.conexion == null ||
                        instancia.conexion.isClosed()) {
                    instancia.conectar();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE,
                        "Error al verificar la conexion", e);
                instancia.conectar();
            }
        }

        return instancia;
    }

    public Connection getConnection() {
        return this.conexion;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                LOGGER.log(Level.INFO,
                        "Conexion cerrada correctamente.");
            }

            conexion = null;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,
                    "Error al cerrar la conexion", e);
        }
    }
}
