package org.AutoFix.model;

import java.time.LocalDate;

public class OrdenTrabajo {

    private int idOrdenTrabajo;
    private int idVehiculo;
    private int idMecanico;
    private LocalDate fechaReparacion;
    private String descripcion;
    private double costo;
    private int kilometraje;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(int idOrdenTrabajo, int idVehiculo,
            int idMecanico, LocalDate fechaReparacion,
            String descripcion, double costo) {

        this.idOrdenTrabajo = idOrdenTrabajo;
        this.idVehiculo = idVehiculo;
        this.idMecanico = idMecanico;
        this.fechaReparacion = fechaReparacion;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public LocalDate getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(LocalDate fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }
}
