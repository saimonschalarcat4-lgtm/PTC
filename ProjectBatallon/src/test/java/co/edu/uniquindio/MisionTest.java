package co.edu.uniquindio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MisionTest {

    private Mision mision;

    @BeforeEach
    void setUp() {
        mision = new Mision("M-01", LocalDate.now(), "Base Central");
    }

    @Test
    void testAssertTrue_agregarPersonal() {
        // Verificar que el método retorna true y añade al personal correctamente
        assertTrue(mision.agregarPersonal("Ana Pérez"));
        assertEquals(1, mision.getPersonalAsignado().size());
    }

    @Test
    void testAssertFalse_iniciarMisionSinRequisitos() {
        // Según Mision.java, no se puede iniciar sin personal ni vehículos asignados
        assertFalse(mision.iniciar());
        assertEquals(EstadoMision.PLANIFICADA, mision.getEstado());
    }
}