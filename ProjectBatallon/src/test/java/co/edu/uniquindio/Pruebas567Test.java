package co.edu.uniquindio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class Pruebas567Test {

    private Batallon batallon;
    private Blindado blindado;

    @BeforeEach
    void preparar() {
        batallon = new Batallon("B01", "Batallon Uno", "Brigada 1", "Distrito 1", 1);
        blindado = new Blindado("V001", "Tanque X", 2020, 1000.0,
                EstadoOperativo.DISPONIBLE, 3);
    }

    // Prueba 5: eliminar un vehículo registrado sin misiones
    @Test
    void eliminarVehiculoRegistradoSinMisiones() {
        assertTrue(batallon.registrarVehiculo(blindado));

        boolean resultado = batallon.eliminarVehiculo("V001");

        assertTrue(resultado);
        assertNull(batallon.buscarVehiculo("V001"));
        assertFalse(batallon.getListaVehiculos().contains(blindado));
    }

    // Prueba 6: actualizar kilometraje y estado operativo
    @Test
    void actualizarKilometrajeYEstadoOperativo() {
        batallon.registrarVehiculo(blindado);

        boolean kmActualizado = blindado.setKilometraje(5000.0);
        boolean estadoActualizado = blindado.setEstadoOperativo(EstadoOperativo.EN_MANTENIMIENTO);

        assertTrue(kmActualizado);
        assertTrue(estadoActualizado);
        Vehiculo encontrado = batallon.buscarVehiculo("V001");
        assertEquals(5000.0, encontrado.getKilometraje());
        assertEquals(EstadoOperativo.EN_MANTENIMIENTO, encontrado.getEstadoOperativo());
    }

    // Prueba 7: registrar misión con personal y vehículos
    @Test
    void registrarMisionConPersonalYVehiculos() {
        batallon.registrarVehiculo(blindado);
        Mision mision = new Mision("M001", LocalDate.of(2026, 10, 22), "Armenia");
        assertTrue(mision.agregarPersonal("Juan Perez"));
        assertTrue(mision.agregarPersonal("Ana Gomez"));
        assertTrue(mision.asignarVehiculo(blindado));

        boolean resultado = batallon.registrarMision(mision);

        assertTrue(resultado);
        assertEquals(mision, batallon.buscarMision("M001"));
        assertEquals(LocalDate.of(2026, 10, 22), mision.getFecha());
        assertEquals("Armenia", mision.getUbicacion());
        assertEquals(2, mision.getPersonalAsignado().size());
        assertTrue(mision.getPersonalAsignado().contains("Juan Perez"));
        assertEquals(1, mision.getListaDetallesMision().size());
        assertEquals(blindado, mision.getListaDetallesMision().get(0).getVehiculo());
        assertEquals(EstadoMision.PLANIFICADA, mision.getEstado());
    }
}