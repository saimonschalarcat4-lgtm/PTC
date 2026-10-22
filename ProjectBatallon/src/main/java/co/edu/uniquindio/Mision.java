package co.edu.uniquindio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mision {
    private final String id;
    private LocalDate fecha;
    private String ubicacion;
    private EstadoMision estado;
    private ArrayList<String> personalAsignado;
    private ArrayList<DetalleMision> listaDetallesMision;

    public Mision(String id, LocalDate fecha, String ubicacion) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.estado = EstadoMision.PLANIFICADA;
        this.personalAsignado = new ArrayList<>();
        this.listaDetallesMision = new ArrayList<>();
    }

    public boolean agregarPersonal(String nombre) {
        boolean agregado = false;
        if (validarPlanificada() && nombre != null && !nombre.isBlank()) {
            String nombreLimpio = nombre.trim();
            if (!personalAsignado.contains(nombreLimpio)) {
                personalAsignado.add(nombreLimpio);
                agregado = true;
            }
        }
        return agregado;
    }

    public boolean eliminarPersonal(String nombre) {
        boolean eliminado = false;
        if (validarPlanificada() && nombre != null) {
            eliminado = personalAsignado.remove(nombre.trim());
        }
        return eliminado;
    }

    // Batallon comprueba primero que el vehículo esté registrado.
    boolean asignarVehiculo(Vehiculo vehiculo) {
        boolean asignado = false;
        if (validarPlanificada() && vehiculo != null && vehiculo.esValido()) {
            boolean repetido = false;
            for (DetalleMision detalle : listaDetallesMision) {
                if (detalle.getVehiculo().getId().equals(vehiculo.getId())) {
                    repetido = true;
                }
            }
            if (!repetido) {
                DetalleMision detalle = new DetalleMision(this, vehiculo);
                listaDetallesMision.add(detalle);
                vehiculo.agregarDetalleMision(detalle);
                asignado = true;
            }
        }
        return asignado;
    }

    boolean iniciar() {
        boolean iniciada = validarPlanificada() && esValida()
                && !personalAsignado.isEmpty() && !listaDetallesMision.isEmpty();
        if (iniciada) {
            // Se revisan todos antes de cambiar el estado de cualquier vehículo.
            for (DetalleMision detalle : listaDetallesMision) {
                if (!detalle.getVehiculo().esValido()
                        || detalle.getVehiculo().getEstadoOperativo() != EstadoOperativo.DISPONIBLE) {
                    iniciada = false;
                }
            }
            if (iniciada) {
                for (DetalleMision detalle : listaDetallesMision) {
                    detalle.getVehiculo().iniciarMision();
                }
                estado = EstadoMision.EN_CURSO;
            }
        }
        return iniciada;
    }

    boolean registrarKilometraje(String idVehiculo, double kilometrosRecorridos) {
        boolean registrado = false;
        if (estado == EstadoMision.EN_CURSO) {
            for (DetalleMision detalle : listaDetallesMision) {
                if (detalle.getVehiculo().getId().equals(idVehiculo)) {
                    registrado = detalle.setKilometrosRecorridos(kilometrosRecorridos);
                }
            }
        }
        return registrado;
    }

    boolean completar() {
        boolean completada = estado == EstadoMision.EN_CURSO;
        if (completada) {
            for (DetalleMision detalle : listaDetallesMision) {
                double total = detalle.getVehiculo().getKilometraje() + detalle.getKilometrosRecorridos();
                if (!Double.isFinite(total)) {
                    completada = false;
                }
            }
            if (completada) {
                for (DetalleMision detalle : listaDetallesMision) {
                    detalle.getVehiculo().finalizarMision(detalle.getKilometrosRecorridos());
                }
                estado = EstadoMision.COMPLETADA;
            }
        }
        return completada;
    }

    private boolean validarPlanificada() {
        return estado == EstadoMision.PLANIFICADA;
    }

    public boolean esValida() {
        return id != null && !id.isBlank() && fecha != null
                && ubicacion != null && !ubicacion.isBlank();
    }

    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean setFecha(LocalDate fecha) {
        boolean actualizada = validarPlanificada() && fecha != null;
        if (actualizada) {
            this.fecha = fecha;
        }
        return actualizada;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public boolean setUbicacion(String ubicacion) {
        boolean actualizada = validarPlanificada() && ubicacion != null && !ubicacion.isBlank();
        if (actualizada) {
            this.ubicacion = ubicacion.trim();
        }
        return actualizada;
    }

    public EstadoMision getEstado() {
        return estado;
    }

    public boolean isCompletada() {
        return estado == EstadoMision.COMPLETADA;
    }

    public ArrayList<String> getPersonalAsignado() {
        return new ArrayList<>(personalAsignado);
    }

    public ArrayList<DetalleMision> getListaDetallesMision() {
        return new ArrayList<>(listaDetallesMision);
    }

    @Override
    public String toString() {
        return "Mision{id='" + id + "', fecha=" + fecha + ", ubicacion='" + ubicacion
                + "', estado=" + estado + ", personalAsignado=" + personalAsignado
                + ", listaDetallesMision=" + listaDetallesMision + "}";
    }
}
