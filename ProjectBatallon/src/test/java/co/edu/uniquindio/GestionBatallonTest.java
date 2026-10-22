package co.edu.uniquindio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GestionBatallonTest {
    private Batallon batallon;
    private TransporteTropas transporte;

    @BeforeEach
    void preparar() {
        batallon = new Batallon("01", "Cacique", "03", "Calarcá", 3);
        transporte = new TransporteTropas("T-01", "Camión", 2020, 1000,
                EstadoOperativo.DISPONIBLE, 25);
        assertTrue(batallon.registrarVehiculo(transporte));
    }

    private Mision crearMision(String id) {
        Mision mision = new Mision(id, LocalDate.of(2026, 10, 22), "Calarcá");
        assertTrue(mision.agregarPersonal("Ana Pérez"));
        assertTrue(batallon.registrarMision(mision));
        assertTrue(batallon.asignarVehiculoMision(id, transporte.getId()));
        return mision;
    }

    @Test
    void registraLosTresTiposYConservaSusDatos() {
        Blindado blindado = new Blindado("B-01", "Blindado", 2021, 500,
                EstadoOperativo.DISPONIBLE, 4);
        Apoyo apoyo = new Apoyo("A-01", "Ambulancia", 2022, 250,
                EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.MEDICO);
        batallon.registrarVehiculo(blindado);
        batallon.registrarVehiculo(apoyo);

        assertEquals(3, batallon.getListaVehiculos().size());
        assertEquals(2, batallon.obtenerVehiculosDisponibles().size());
        assertSame(blindado, batallon.buscarVehiculo("B-01"));
        assertEquals(25, transporte.getCapacidadSoldados());
        assertEquals(4, blindado.getNivelBlindaje());
        assertEquals(TipoFuncion.MEDICO, apoyo.getTipoFuncion());
    }

    @Test
    void rechazaIdsDuplicadosEntreTiposYEnMisiones() {
        Apoyo repetido = new Apoyo(" T-01 ", "Apoyo", 2022, 0,
                EstadoOperativo.DISPONIBLE, TipoFuncion.LOGISTICA);
        assertFalse(batallon.registrarVehiculo(repetido));
        crearMision("M-01");
        assertFalse(batallon.registrarMision(
                new Mision(" M-01 ", LocalDate.now(), "Otra zona")));
    }

    @Test
    void completaUnaMisionConVariosVehiculosYActualizaElKilometraje() {
        Apoyo apoyo = new Apoyo("A-01", "Apoyo", 2022, 2000,
                EstadoOperativo.DISPONIBLE, TipoFuncion.COMUNICACIONES);
        batallon.registrarVehiculo(apoyo);
        Mision mision = crearMision("M-01");
        batallon.asignarVehiculoMision("M-01", "A-01");
        assertSame(mision.getListaDetallesMision().get(0), transporte.getListaDetallesMision().get(0));
        assertEquals(0, transporte.contarMisionesCompletadas());

        assertTrue(batallon.iniciarMision("M-01"));
        assertEquals(EstadoMision.EN_CURSO, mision.getEstado());
        assertEquals(EstadoOperativo.EN_MISION, transporte.getEstadoOperativo());
        assertEquals(EstadoOperativo.EN_MISION, apoyo.getEstadoOperativo());
        assertEquals(0, transporte.contarMisionesCompletadas());
        batallon.registrarKilometrajeMision("M-01", "T-01", 20);
        batallon.registrarKilometrajeMision("M-01", "T-01", 80);
        batallon.registrarKilometrajeMision("M-01", "A-01", 75);
        assertTrue(batallon.completarMision("M-01"));

        assertTrue(mision.isCompletada());
        assertEquals(1080, transporte.getKilometraje());
        assertEquals(2075, apoyo.getKilometraje());
        assertEquals(EstadoOperativo.DISPONIBLE, transporte.getEstadoOperativo());
        assertEquals(EstadoOperativo.DISPONIBLE, apoyo.getEstadoOperativo());
        assertEquals(1, transporte.contarMisionesCompletadas());
        assertEquals(1, apoyo.contarMisionesCompletadas());
        assertFalse(batallon.completarMision("M-01"));
        assertEquals(1080, transporte.getKilometraje());
    }

    @Test
    void consultaExcluye50EIncluye51YSoloCuentaCompletadas() {
        for (int i = 1; i <= 50; i++) {
            String id = "M-" + i;
            crearMision(id);
            assertTrue(batallon.iniciarMision(id));
            assertTrue(batallon.completarMision(id));
        }
        assertEquals(50, transporte.contarMisionesCompletadas());
        assertTrue(batallon.obtenerVehiculosConMasDe50Misiones().isEmpty());

        crearMision("M-51");
        crearMision("M-52");
        assertTrue(batallon.obtenerVehiculosConMasDe50Misiones().isEmpty());
        batallon.iniciarMision("M-51");
        assertTrue(batallon.obtenerVehiculosConMasDe50Misiones().isEmpty());
        batallon.completarMision("M-51");

        assertEquals(51, transporte.contarMisionesCompletadas());
        assertEquals(1, batallon.obtenerVehiculosConMasDe50Misiones().size());
        assertSame(transporte, batallon.obtenerVehiculosConMasDe50Misiones().get(0));
    }

    @Test
    void mantenimientoImpideIniciarSinCambiarLosOtrosVehiculos() {
        Apoyo apoyo = new Apoyo("A-01", "Apoyo", 2022, 0,
                EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.LOGISTICA);
        batallon.registrarVehiculo(apoyo);
        Mision mision = crearMision("M-01");
        batallon.asignarVehiculoMision("M-01", "A-01");

        assertFalse(batallon.iniciarMision("M-01"));
        assertEquals(EstadoMision.PLANIFICADA, mision.getEstado());
        assertEquals(EstadoOperativo.DISPONIBLE, transporte.getEstadoOperativo());
        assertEquals(EstadoOperativo.EN_MANTENIMIENTO, apoyo.getEstadoOperativo());
        apoyo.setEstadoOperativo(EstadoOperativo.DISPONIBLE);
        assertTrue(batallon.iniciarMision("M-01"));
        assertEquals(EstadoMision.EN_CURSO, mision.getEstado());
    }

    @Test
    void noPermiteUnVehiculoEnDosMisionesSimultaneas() {
        crearMision("M-01");
        crearMision("M-02");
        assertTrue(batallon.iniciarMision("M-01"));
        assertFalse(batallon.iniciarMision("M-02"));
        assertTrue(batallon.completarMision("M-01"));
        batallon.iniciarMision("M-02");
        batallon.completarMision("M-02");
        assertEquals(2, transporte.contarMisionesCompletadas());
    }

    @Test
    void exigePersonalYVehiculosAntesDeIniciar() {
        Mision mision = new Mision("M-01", LocalDate.now(), "Calarcá");
        assertTrue(batallon.registrarMision(mision));
        assertFalse(batallon.iniciarMision("M-01"));
        assertTrue(mision.agregarPersonal("Ana Pérez"));
        assertFalse(batallon.iniciarMision("M-01"));
        mision.eliminarPersonal("Ana Pérez");
        batallon.asignarVehiculoMision("M-01", "T-01");
        assertFalse(batallon.iniciarMision("M-01"));
        assertTrue(mision.agregarPersonal("Ana Pérez"));
        assertTrue(batallon.iniciarMision("M-01"));
        assertEquals(EstadoMision.EN_CURSO, mision.getEstado());
    }

    @Test
    void rechazaVehiculosDesconocidosYAsignacionesRepetidas() {
        Mision mision = crearMision("M-01");
        assertFalse(batallon.asignarVehiculoMision("M-01", "T-01"));
        assertFalse(batallon.asignarVehiculoMision("M-01", "NO-EXISTE"));
        assertFalse(mision.agregarPersonal(" Ana Pérez "));
        assertFalse(batallon.iniciarMision("NO-EXISTE"));
        assertEquals(1, transporte.getListaDetallesMision().size());
    }

    @Test
    void noPermiteModificarLaMisionNiElEstadoDeUnVehiculoEnUso() {
        Mision mision = crearMision("M-01");
        assertFalse(batallon.completarMision("M-01"));
        assertFalse(batallon.registrarKilometrajeMision("M-01", "T-01", 5));
        assertTrue(batallon.iniciarMision("M-01"));
        assertFalse(mision.agregarPersonal("Luis Gómez"));
        assertFalse(mision.setUbicacion("Otra zona"));
        assertFalse(mision.setFecha(LocalDate.now()));
        assertFalse(transporte.setEstadoOperativo(EstadoOperativo.EN_MANTENIMIENTO));
        assertFalse(transporte.setKilometraje(2000));
        assertTrue(batallon.completarMision("M-01"));
        assertFalse(mision.agregarPersonal("Luis Gómez"));
        assertFalse(batallon.registrarKilometrajeMision("M-01", "T-01", 5));
    }

    @Test
    void rechazaDatosInvalidosYKilometrajesNoFinitos() {
        Blindado sinId = new Blindado("", "Modelo", 2020, 0, EstadoOperativo.DISPONIBLE, 3);
        assertFalse(batallon.registrarVehiculo(sinId));
        assertFalse(batallon.registrarVehiculo(null));
        assertFalse(batallon.registrarMision(null));
        assertFalse(batallon.registrarMision(new Mision(null, null, null)));
        assertFalse(batallon.iniciarMision(null));
        assertFalse(batallon.completarMision("NO-EXISTE"));
        assertFalse(batallon.asignarVehiculoMision("NO-EXISTE", "T-01"));
        assertFalse(batallon.registrarVehiculo(new Blindado("B-02", "Modelo", 2020, -5,
                EstadoOperativo.DISPONIBLE, 3)));
        assertFalse(batallon.registrarVehiculo(new Blindado("B-03", "Modelo", 2020, 0,
                EstadoOperativo.DISPONIBLE, 0)));
        assertFalse(batallon.registrarVehiculo(new Apoyo("A-02", "Apoyo", 2020, 0,
                EstadoOperativo.DISPONIBLE, null)));
        assertFalse(transporte.setCapacidadSoldados(0));
        assertFalse(transporte.setAnioFabricacion(-1));
        assertFalse(transporte.setKilometraje(-1));
        assertFalse(transporte.setKilometraje(999));
        assertFalse(transporte.setKilometraje(Double.NaN));
        assertFalse(transporte.setEstadoOperativo(null));
        crearMision("M-01");
        assertTrue(batallon.iniciarMision("M-01"));
        assertFalse(batallon.registrarKilometrajeMision("M-01", "T-01", -1));
        assertFalse(batallon.registrarKilometrajeMision("M-01", "T-01", Double.POSITIVE_INFINITY));
        assertFalse(batallon.registrarKilometrajeMision("M-01", "OTRO", 20));
        assertEquals(1000, transporte.getKilometraje());
    }

    @Test
    void permiteRegistrarYActualizarLosTresEstadosOperativos() {
        Apoyo apoyo = new Apoyo("A-01", "Apoyo", 2022, 0,
                EstadoOperativo.EN_MISION, TipoFuncion.LOGISTICA);
        batallon.registrarVehiculo(apoyo);
        assertEquals(EstadoOperativo.EN_MISION, apoyo.getEstadoOperativo());
        assertFalse(batallon.obtenerVehiculosDisponibles().contains(apoyo));
        apoyo.setEstadoOperativo(EstadoOperativo.EN_MANTENIMIENTO);
        assertEquals(EstadoOperativo.EN_MANTENIMIENTO, apoyo.getEstadoOperativo());
        apoyo.setEstadoOperativo(EstadoOperativo.DISPONIBLE);
        assertTrue(batallon.obtenerVehiculosDisponibles().contains(apoyo));
    }

    @Test
    void lasCopiasDeLasListasNoPermitenAlterarElHistorial() {
        Mision mision = crearMision("M-01");
        batallon.getListaVehiculos().clear();
        batallon.getListaMision().clear();
        mision.getListaDetallesMision().clear();
        mision.getPersonalAsignado().clear();
        transporte.getListaDetallesMision().clear();

        assertEquals(1, batallon.getListaVehiculos().size());
        assertEquals(1, batallon.getListaMision().size());
        assertEquals(1, mision.getListaDetallesMision().size());
        assertEquals(1, mision.getPersonalAsignado().size());
        assertEquals(1, transporte.getListaDetallesMision().size());
    }
}
