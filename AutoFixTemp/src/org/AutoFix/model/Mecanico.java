package org.AutoFix.model;

public class Mecanico {
    private int idMecanico;
    private String nombres;
    private String apellidos;
    private String telefono;

    public Mecanico() {
    }

    public Mecanico(int idMecanico, String nombres, String apellidos, String telefono) {
        this.idMecanico = idMecanico;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public int getIdMecanico() { return idMecanico; }
    public void setIdMecanico(int idMecanico) { this.idMecanico = idMecanico; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}