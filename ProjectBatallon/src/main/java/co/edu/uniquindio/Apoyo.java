package co.edu.uniquindio;

public class Apoyo extends Vehiculo {
    private TipoFuncion tipoFuncion;

    public Apoyo(String id, String modelo, int anioFabricacion, double kilometraje,
                 EstadoOperativo estadoOperativo, TipoFuncion tipoFuncion) {
        super(id, modelo, anioFabricacion, kilometraje, estadoOperativo);
        this.tipoFuncion = tipoFuncion;
    }

    public TipoFuncion getTipoFuncion() {
        return tipoFuncion;
    }

    public boolean setTipoFuncion(TipoFuncion tipoFuncion) {
        boolean actualizado = tipoFuncion != null;
        if (actualizado) {
            this.tipoFuncion = tipoFuncion;
        }
        return actualizado;
    }

    @Override
    public boolean esValido() {
        return super.esValido() && tipoFuncion != null;
    }

    @Override
    public String toString() {
        return "Apoyo{" + super.toString() + ", tipoFuncion=" + tipoFuncion + "}";
    }
}
