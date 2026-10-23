package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
class BatallonTest {

    private static final Logger LOG = Logger.getLogger(BatallonTest.class.getName());
    private Batallon batallon;
    @BeforeEach
    void setUp() {
        LOG.info("Iniciando prueba de batallon");
        batallon = new Batallon("003","Norte","Evacuacion","Via el caimo",3);
    }
//    @DisplayName("Prueba assertEquals")
//    @Test
//    void testInicial(){
//        LOG.info("Iniciando prueba Batallon...");
//
//        int resultado = 1 + 2;
//
//        assertEquals(3,resultado);
//
//        LOG.info("Finalizando prueba Batallon...");
//    }
//
//    @DisplayName("Prueba assertNotEquals")
//    @Test
//    void testNotEquals(){
//        LOG.info("Iniciando prueba Batallon...");
//
//        String cadena = "Hola a todos";
//
//        assertNotEquals("Adios",cadena);
//
//        LOG.info("Finalizando prueba Batallon...");
//    }
//
//    @DisplayName("Prueba assertNull")
//    @Test
//    void testNull(){
//        LOG.info("Iniciando prueba Batallon...");
//
//        Batallon batallon = null;
//
//        assertNull(batallon);
//
//        LOG.info("Finalizando prueba Batallon...");
//    }
//
//    @DisplayName("Prueba assertNotNull")
//    @Test
//    void testNotNull(){
//        LOG.info("Iniciando prueba Batallon...");
//
//        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarca", 3);
//
//        assertNotNull(batallon);
//
//        LOG.info(batallon.toString());
//        LOG.info("Finalizando prueba Batallon...");
//    }
//
//    @DisplayName("Prueba assertTrue/assertFalse")
//    @Test
//    void testFalseTrue(){
//        LOG.info("Iniciando prueba Batallon...");
//
//        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarca", 3);
//
//        assertTrue(batallon.getNumero() < 5);
//        assertFalse(batallon.getNombre().equals("Calarca cacique"));
//
//        LOG.info(batallon.toString());
//        LOG.info("Finalizando prueba Batallon...");
//    }
//
//
//    //@BeforeEach
//    void ejecutarAntes(){
//        LOG.warning("Ejecucion del BeforeEach");
//    }
//
////    @AfterEach
//    void ejecutarDespues(){
//        LOG.warning("Ejecucion del AfterEach");
//    }
@Test
void validarCreacionAtributosVehiculos() {
    TransporteTropas transporte = new TransporteTropas("TS-002","APC",2014,20000,EstadoOperativo.EN_MANTENIMIENTO,24);
    Blindado blindado = new Blindado("T-001", "Comercial",2012,80780.0, EstadoOperativo.DISPONIBLE, 4);
    Apoyo apoyo = new Apoyo("A-003","Jeep Logístico",2026,7000,EstadoOperativo.EN_MISION,TipoFuncion.MEDICO);
    //Transporte
    assertEquals("TS-002", transporte.getId());
    assertEquals("APC", transporte.getModelo());
    assertEquals(24, transporte.getCapacidadSoldados());

    // Blindado
    assertEquals("B-001", blindado.getId());
    assertEquals("Tanque Centauro", blindado.getModelo());
    assertEquals("Nivel IV", blindado.getNivelBlindaje());

    // Apoyo
    assertEquals("A-001", apoyo.getId());
    assertEquals("Jeep Logístico", apoyo.getModelo());
    assertEquals("Medico", apoyo.getTipoFuncion());
    }
    //2
    @Test
    void agregarVehiculoExitosamente() {
        TransporteTropas transporte = new TransporteTropas("TS-002","APC",2014,20000,EstadoOperativo.EN_MANTENIMIENTO,24);
        boolean resultado = batallon.registrarVehiculo(transporte);
        assertTrue(resultado, "La operación debe retornar true al agregar un vehículo con ID nuevo.");
        assertNotNull(batallon.buscarVehiculo("TS-002"), "El vehículo debe quedar almacenado en la colección.");
    }
    //test 3
    @Test
    void InvalidarVehiculoIdRepetido() {
        TransporteTropas transporteOriginal = new TransporteTropas("T-001","APC",2014,20000,EstadoOperativo.EN_MANTENIMIENTO,24);
        Blindado vehiculoDuplicado = new Blindado("T-001", "Comercial",2012,80780.0, EstadoOperativo.DISPONIBLE, 4);
        batallon.registrarVehiculo(transporteOriginal);
        boolean resultadoDuplicado = batallon.registrarVehiculo(vehiculoDuplicado);
        assertFalse(resultadoDuplicado, "La operación debe retornar false al intentar registrar un ID ya existente.");
    }
    //Test 4
    @Test
    void buscarVehiculosExistentesEInexistentes() {
        Apoyo apoyo = new Apoyo("A-002","Jeep Logístico",2026,7000,EstadoOperativo.EN_MISION,TipoFuncion.MEDICO);
        batallon.registrarVehiculo(apoyo);
        Vehiculo vehiculoEncontrado = batallon.buscarVehiculo("A-002");
        assertNotNull(vehiculoEncontrado, "El sistema debe encontrar el vehículo registrado.");
        assertEquals("A-002", vehiculoEncontrado.getId());
        Vehiculo vehiculoNoEncontrado = batallon.buscarVehiculo("X-999");
        assertNull(vehiculoNoEncontrado, "La consulta de un ID inexistente debe retornar null (o el valor definido por tu lógica).");
    }
}