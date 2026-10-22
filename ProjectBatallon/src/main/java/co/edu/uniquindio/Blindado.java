package co.edu.uniquindio;

public class Blindado extends Vehiculo {
    private int nivelBlindaje;

    public Blindado(String id, String modelo, int anioFabricacion, double kilometraje,
                    EstadoOperativo estadoOperativo, int nivelBlindaje) {
        super(id, modelo, anioFabricacion, kilometraje, estadoOperativo);
        this.nivelBlindaje = nivelBlindaje;
    }

    public int getNivelBlindaje() {
        return nivelBlindaje;
    }

    public boolean setNivelBlindaje(int nivelBlindaje) {
        boolean actualizado = nivelBlindaje > 0;
        if (actualizado) {
            this.nivelBlindaje = nivelBlindaje;
        }
        return actualizado;
    }

    @Override
    public boolean esValido() {
        return super.esValido() && nivelBlindaje > 0;
    }

    @Override
    public String toString() {
        return "Blindado{" + super.toString() + ", nivelBlindaje=" + nivelBlindaje + "}";
    }
}
