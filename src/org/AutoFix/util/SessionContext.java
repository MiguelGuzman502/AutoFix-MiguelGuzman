package org.AutoFix.util;

import java.time.LocalDateTime;

public class SessionContext {
    private static SessionContext instancia;
    private int idUsuario;
    private String usuario;
    private String rol;
    private LocalDateTime horaInicioSesion;

    private SessionContext(String usuario, String rol) {
        this.usuario = usuario;
        this.rol = rol;
        this.horaInicioSesion = LocalDateTime.now();
    }

    public static synchronized SessionContext iniciarSesion(String usuario, String rol) {
        instancia = new SessionContext(usuario, rol);
        return instancia;
    }

    public static synchronized SessionContext getInstancia() {
        return instancia;
    }

    public static synchronized void cerrarSesion() {
        instancia = null;
    }

    public static synchronized SessionContext iniciarSesion(int idUsuario, String usuario, String rol) {
        instancia = new SessionContext(usuario, rol);
        instancia.idUsuario = idUsuario;
        return instancia;
    }

    public int getIdUsuario() { return idUsuario; }

    public boolean tieneRol(String rolRequerido) { return rol != null && rol.equalsIgnoreCase(rolRequerido); }

    public String getUsuario() {
        return usuario;
    }

    public String getRol() {
        return rol;
    }

    public LocalDateTime getHoraInicioSesion() {
        return horaInicioSesion;
    }
}
