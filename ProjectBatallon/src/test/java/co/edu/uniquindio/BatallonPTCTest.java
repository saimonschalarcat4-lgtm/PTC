package co.edu.uniquindio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class BatallonPTCTest {

    private Batallon batallon;
    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        batallon = new Batallon("01", "Cacique", "03", "Calarcá", 3);
        vehiculo = new Apoyo("A-01", "Ambulancia", 2022, 5000, EstadoOperativo.DISPONIBLE, TipoFuncion.MEDICO);
        batallon.registrarVehiculo(vehiculo);
    }

    // Requerimiento 9: Verificar que un vehículo con exactamente 50 misiones no aparezca
    @Test
    void testAssertFalse_vehiculoCon50Misiones() {
        ejecutarCicloMisiones(50); // Registra 50 misiones completadas

        ArrayList<Vehiculo> destacados = batallon.obtenerVehiculosConMasDe50Misiones();

        // Verificación: El tamaño de la lista es 0 y no contiene el vehículo
        assertFalse(destacados.contains(vehiculo));
        assertEquals(0, destacados.size());
    }

    // Requerimiento 10: Obtener vehículos con más de 50 misiones completadas
    @Test
    void testAssertTrue_vehiculosConMasDe50Misiones() {
        ejecutarCicloMisiones(51); // Registra 51 misiones completadas

        ArrayList<Vehiculo> destacados = batallon.obtenerVehiculosConMasDe50Misiones();

        // Verificación: El vehículo sí aparece y tiene exactamente 51 misiones
        assertTrue(destacados.contains(vehiculo));
        assertEquals(1, destacados.size());
        assertEquals(51, destacados.get(0).contarMisionesCompletadas());
    }

    // Método auxiliar para facilitar la creación de misiones en BatallonTest
    private void ejecutarCicloMisiones(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            String idMision = "M-" + i;
            Mision mision = new Mision(idMision, LocalDate.now(), "Zona " + i);
            mision.agregarPersonal("Piloto");

            batallon.registrarMision(mision);
            batallon.asignarVehiculoMision(idMision, vehiculo.getId());
            batallon.iniciarMision(idMision);
            batallon.completarMision(idMision);
        }
    }
}