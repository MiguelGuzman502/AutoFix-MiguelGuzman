package org.AutoFix.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;
    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());

    private final String URL = "jdbc:mysql://localhost:3306/db_autofix?useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "MiguelAngel2009"; 

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.log(Level.INFO, "Conexión a la base de datos establecida exitosamente.");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos", e);
        }
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        } else {
            try {
                if (instancia.conexion.isClosed()) {
                    instancia = new Conexion();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al verificar el estado de la conexión", e);
            }
        }
        return instancia;
    }

    public Connection getConnection() {
        return this.conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                instancia = null;
                LOGGER.log(Level.INFO, "Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al cerrar la conexión", e);
        }
    }

    public Connection getConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}