package org.AutoFix.model;

public class DetalleRepuesto {
    private int idDetalle;
    private int idOrdenTrabajo;
    private int idRepuesto;
    private int cantidad;

    public DetalleRepuesto() {}

    public DetalleRepuesto(int idDetalle, int idOrdenTrabajo, int idRepuesto, int cantidad) {
        this.idDetalle = idDetalle;
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.idRepuesto = idRepuesto;
        this.cantidad = cantidad;
    }

    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdOrdenTrabajo() { return idOrdenTrabajo; }
    public void setIdOrdenTrabajo(int idOrdenTrabajo) { this.idOrdenTrabajo = idOrdenTrabajo; }

    public int getIdRepuesto() { return idRepuesto; }
    public void setIdRepuesto(int idRepuesto) { this.idRepuesto = idRepuesto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}