package co.edu.uniquindio;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarcá", 3);

        TransporteTropas transporte = new TransporteTropas("T-01", "Camión de tropas", 2020,
                12000, EstadoOperativo.DISPONIBLE, 25);
        Blindado blindado = new Blindado("B-01", "Vehículo blindado", 2021,
                8000, EstadoOperativo.DISPONIBLE, 4);
        Apoyo apoyo = new Apoyo("A-01", "Ambulancia", 2022,
                5000, EstadoOperativo.DISPONIBLE, TipoFuncion.MEDICO);

        if (batallon.registrarVehiculo(transporte)) {
            System.out.println("Transporte registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el transporte.");
        }
        batallon.registrarVehiculo(blindado);
        batallon.registrarVehiculo(apoyo);

        Mision mision = new Mision("M-01", LocalDate.of(2026, 10, 22), "Calarcá");
        mision.agregarPersonal("Ana Pérez");
        mision.agregarPersonal("Luis Gómez");
        batallon.registrarMision(mision);
        batallon.asignarVehiculoMision("M-01", "T-01");
        batallon.asignarVehiculoMision("M-01", "A-01");

        if (batallon.iniciarMision("M-01")) {
            System.out.println("Estado durante la misión: " + transporte.getEstadoOperativo());
            batallon.registrarKilometrajeMision("M-01", "T-01", 80);
            batallon.registrarKilometrajeMision("M-01", "A-01", 75);
            batallon.completarMision("M-01");
            System.out.println(mision);

            // Datos de ejemplo para comprobar la consulta de más de 50 misiones.
            for (int i = 2; i <= 51; i++) {
                String id = "M-" + i;
                Mision siguiente = new Mision(id, LocalDate.of(2026, 10, 22), "Zona de apoyo");
                siguiente.agregarPersonal("Ana Pérez");
                batallon.registrarMision(siguiente);
                batallon.asignarVehiculoMision(id, transporte.getId());
                batallon.iniciarMision(id);
                batallon.registrarKilometrajeMision(id, transporte.getId(), 10);
                batallon.completarMision(id);
            }

            System.out.println("\nVehículos registrados:");
            for (Vehiculo vehiculo : batallon.getListaVehiculos()) {
                System.out.println(vehiculo);
            }

            System.out.println("\nVehículos con más de 50 misiones completadas:");
            for (Vehiculo vehiculo : batallon.obtenerVehiculosConMasDe50Misiones()) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No se pudo iniciar la misión. Revisa el personal y los vehículos.");
        }
    }
}
