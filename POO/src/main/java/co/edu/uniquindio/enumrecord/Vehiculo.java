package co.edu.uniquindio.enumrecord;

public class Vehiculo {
    private String id;
    private String anio;
    private String modelo;
    private double kilometraje;
    private EstadoOperativo estadoOperativo;
    private ReciboPago reciboPago;

    public Vehiculo(String id, String anio, String modelo, double kilometraje){
        this.id = id;
        this.anio = anio;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        estadoOperativo = EstadoOperativo.DISPONIBLE;
        reciboPago = null;
    }

    public EstadoOperativo getEstadoOperativo(){
        return estadoOperativo;
    }

    public void setEstadoOperativo(EstadoOperativo estadoOperativo){
        this.estadoOperativo = estadoOperativo;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
