package org.AutoFix.model;
public class UsuarioSesion {
    private final int id; private final String nombre; private final String rol;
    public UsuarioSesion(int id, String nombre, String rol) { this.id=id; this.nombre=nombre; this.rol=rol; }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
}
