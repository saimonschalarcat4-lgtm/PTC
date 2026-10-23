package co.edu.uniquindio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class VehiculoTest {

    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        vehiculo = new TransporteTropas("V-01", "Camión", 2020, 0, EstadoOperativo.DISPONIBLE, 20);
    }

    // Requerimiento 8: Contar únicamente las misiones completadas de un vehículo
    @Test
    void testAssertEquals_misionesCompletadas() {
        // Misión 1 (Se lleva hasta estado COMPLETADA)
        Mision m1 = new Mision("M-01", LocalDate.now(), "Zona Norte");
        m1.agregarPersonal("Sargento Pérez");
        m1.asignarVehiculo(vehiculo);
        m1.iniciar();
        m1.completar();

        // Misión 2 (Se queda en estado EN_CURSO)
        Mision m2 = new Mision("M-02", LocalDate.now(), "Zona Sur");
        m2.agregarPersonal("Cabo Martínez");
        m2.asignarVehiculo(vehiculo);
        m2.iniciar();

        // Misión 3 (Se queda en estado PLANIFICADA)
        Mision m3 = new Mision("M-03", LocalDate.now(), "Zona Este");
        m3.asignarVehiculo(vehiculo);

        // Verificación: Solo 1 misión debe contabilizarse en el historial del vehículo
        assertEquals(1, vehiculo.contarMisionesCompletadas());
    }
}