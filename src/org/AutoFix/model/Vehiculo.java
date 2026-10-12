package org.AutoFix.model;

public class Vehiculo {
    private int idVehiculo;
    private int idCliente;
    private String marca;
    private String modelo;
    private int anio;
    private String placa;

    public Vehiculo() {}

    public Vehiculo(int idVehiculo, int idCliente, String marca, String modelo, int anio, String placa) {
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.placa = placa;
    }

    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
}