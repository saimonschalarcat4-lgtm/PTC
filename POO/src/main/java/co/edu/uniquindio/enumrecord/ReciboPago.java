package co.edu.uniquindio.enumrecord;

import java.time.LocalDate;

public record ReciboPago(String id, LocalDate fecha, double monto, Vehiculo vehiculo) {

    @Override
    public String toString() {
        return "Recibo: " + id + " - " + fecha + " - " + monto;
    }
}
