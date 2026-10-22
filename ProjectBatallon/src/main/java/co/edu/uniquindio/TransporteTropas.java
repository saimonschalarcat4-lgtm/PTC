package co.edu.uniquindio;

public class TransporteTropas extends Vehiculo {
    private int capacidadSoldados;

    public TransporteTropas(String id, String modelo, int anioFabricacion, double kilometraje,
                            EstadoOperativo estadoOperativo, int capacidadSoldados) {
        super(id, modelo, anioFabricacion, kilometraje, estadoOperativo);
        this.capacidadSoldados = capacidadSoldados;
    }

    public int getCapacidadSoldados() {
        return capacidadSoldados;
    }

    public boolean setCapacidadSoldados(int capacidadSoldados) {
        boolean actualizado = capacidadSoldados > 0;
        if (actualizado) {
            this.capacidadSoldados = capacidadSoldados;
        }
        return actualizado;
    }

    @Override
    public boolean esValido() {
        return super.esValido() && capacidadSoldados > 0;
    }

    @Override
    public String toString() {
        return "TransporteTropas{" + super.toString() + ", capacidadSoldados=" + capacidadSoldados + "}";
    }
}
