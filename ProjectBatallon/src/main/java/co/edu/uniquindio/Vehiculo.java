package co.edu.uniquindio;

import java.time.Year;
import java.util.ArrayList;

public abstract class Vehiculo {
    protected final String id;
    protected String modelo;
    protected int anioFabricacion;
    protected double kilometraje;
    protected EstadoOperativo estadoOperativo;
    protected ArrayList<DetalleMision> listaDetallesMision;

    public Vehiculo(String id, String modelo, int anioFabricacion, double kilometraje,
                    EstadoOperativo estadoOperativo) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.kilometraje = kilometraje;
        this.estadoOperativo = estadoOperativo;
        this.listaDetallesMision = new ArrayList<>();
    }

    // Los datos del constructor se revisan antes de registrar el vehículo.
    public boolean esValido() {
        return id != null && !id.isBlank()
                && modelo != null && !modelo.isBlank()
                && anioFabricacion > 0 && anioFabricacion <= Year.now().getValue()
                && Double.isFinite(kilometraje) && kilometraje >= 0
                && estadoOperativo != null;
    }

    // El total se calcula a partir del historial, sin mantener otro contador.
    public int contarMisionesCompletadas() {
        int cantidad = 0;
        for (DetalleMision detalle : listaDetallesMision) {
            if (detalle.getMision().isCompletada()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // Estos métodos los utiliza Mision dentro del mismo paquete.
    void agregarDetalleMision(DetalleMision detalle) {
        listaDetallesMision.add(detalle);
    }

    boolean iniciarMision() {
        boolean iniciada = estadoOperativo == EstadoOperativo.DISPONIBLE;
        if (iniciada) {
            estadoOperativo = EstadoOperativo.EN_MISION;
        }
        return iniciada;
    }

    void finalizarMision(double kilometrosRecorridos) {
        kilometraje += kilometrosRecorridos;
        estadoOperativo = EstadoOperativo.DISPONIBLE;
    }

    public String getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean setModelo(String modelo) {
        boolean actualizado = modelo != null && !modelo.isBlank();
        if (actualizado) {
            this.modelo = modelo.trim();
        }
        return actualizado;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public boolean setAnioFabricacion(int anioFabricacion) {
        boolean actualizado = anioFabricacion > 0 && anioFabricacion <= Year.now().getValue();
        if (actualizado) {
            this.anioFabricacion = anioFabricacion;
        }
        return actualizado;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public boolean setKilometraje(double kilometraje) {
        boolean actualizado = Double.isFinite(kilometraje) && kilometraje >= 0
                && estadoOperativo != EstadoOperativo.EN_MISION;
        if (kilometraje < this.kilometraje) {
            actualizado = false;
        }
        if (actualizado) {
            this.kilometraje = kilometraje;
        }
        return actualizado;
    }

    public EstadoOperativo getEstadoOperativo() {
        return estadoOperativo;
    }

    public boolean setEstadoOperativo(EstadoOperativo estadoOperativo) {
        boolean actualizado = estadoOperativo != null;
        for (DetalleMision detalle : listaDetallesMision) {
            if (detalle.getMision().getEstado() == EstadoMision.EN_CURSO) {
                actualizado = false;
            }
        }
        if (actualizado) {
            this.estadoOperativo = estadoOperativo;
        }
        return actualizado;
    }

    public ArrayList<DetalleMision> getListaDetallesMision() {
        return new ArrayList<>(listaDetallesMision);
    }

    @Override
    public String toString() {
        return "Vehiculo{id='" + id + "', modelo='" + modelo
                + "', anioFabricacion=" + anioFabricacion + ", kilometraje=" + kilometraje
                + ", estadoOperativo=" + estadoOperativo
                + ", misionesCompletadas=" + contarMisionesCompletadas() + "}";
    }
}
