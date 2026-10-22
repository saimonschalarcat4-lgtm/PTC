package co.edu.uniquindio;

// Cada detalle vincula una misión con un único vehículo.
public class DetalleMision {
    private final Mision mision;
    private final Vehiculo vehiculo;
    private double kilometrosRecorridos;

    DetalleMision(Mision mision, Vehiculo vehiculo) {
        this.mision = mision;
        this.vehiculo = vehiculo;
        this.kilometrosRecorridos = 0;
    }

    public Mision getMision() {
        return mision;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    boolean setKilometrosRecorridos(double kilometrosRecorridos) {
        boolean actualizado = Double.isFinite(kilometrosRecorridos) && kilometrosRecorridos >= 0;
        if (actualizado) {
            this.kilometrosRecorridos = kilometrosRecorridos;
        }
        return actualizado;
    }

    @Override
    public String toString() {
        return "DetalleMision{mision='" + mision.getId() + "', vehiculo='" + vehiculo.getId()
                + "', kilometrosRecorridos=" + kilometrosRecorridos + "}";
    }
}
