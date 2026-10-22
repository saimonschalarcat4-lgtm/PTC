package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BatallonTest {

    private static final Logger LOG = Logger.getLogger(BatallonTest.class.getName());

    @DisplayName("Prueba assertEquals")
    @Test
    void testInicial(){
        LOG.info("Iniciando prueba Batallon...");

        int resultado = 1 + 2;

        assertEquals(3,resultado);

        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertNotEquals")
    @Test
    void testNotEquals(){
        LOG.info("Iniciando prueba Batallon...");

        String cadena = "Hola a todos";

        assertNotEquals("Adios",cadena);

        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertNull")
    @Test
    void testNull(){
        LOG.info("Iniciando prueba Batallon...");

        Batallon batallon = null;

        assertNull(batallon);

        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertNotNull")
    @Test
    void testNotNull(){
        LOG.info("Iniciando prueba Batallon...");

        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarca", 3);

        assertNotNull(batallon);

        LOG.info(batallon.toString());
        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertTrue/assertFalse")
    @Test
    void testFalseTrue(){
        LOG.info("Iniciando prueba Batallon...");

        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarca", 3);

        assertTrue(batallon.getNumero() < 5);
        assertFalse(batallon.getNombre().equals("Calarca cacique"));

        LOG.info(batallon.toString());
        LOG.info("Finalizando prueba Batallon...");
    }


    //@BeforeEach
    void ejecutarAntes(){
        LOG.warning("Ejecucion del BeforeEach");
    }

//    @AfterEach
    void ejecutarDespues(){
        LOG.warning("Ejecucion del AfterEach");
    }

}