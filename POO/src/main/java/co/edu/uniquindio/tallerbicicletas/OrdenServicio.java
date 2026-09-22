package co.edu.uniquindio.tallerbicicletas;

import java.time.LocalDate;

public class OrdenServicio {
    private String id;
    private LocalDate fechaIngreso;
    private String descripcionProblema;
    private String estado;

    private Repuesto[] listRepuestos;
    private Mecanico theMecanico;
    private Bicicleta theBicicleta;
    private Tarea[] listTareas;

    public OrdenServicio(String id, LocalDate fechaIngreso, String descripcionProblema, String estado) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.descripcionProblema = descripcionProblema;
        this.estado = estado;
        listRepuestos = new Repuesto[10];
        theMecanico = null;
        listTareas = new Tarea[10];
    }

    public Repuesto[] getListRepuestos() {
        return listRepuestos;
    }

    public void agregarRepuesto(Repuesto repuesto) {

        for (int i = 0; i < listRepuestos.length; i++){
            if(listRepuestos[i] == null){
                listRepuestos[i] = repuesto;
            }
        }
    }

    public Mecanico getTheMecanico() {
        return theMecanico;
    }

    public void setTheMecanico(Mecanico theMecanico) {
        this.theMecanico = theMecanico;
    }

    public Bicicleta getTheBicicleta() {
        return theBicicleta;
    }

    public void setTheBicicleta(Bicicleta theBicicleta) {
        this.theBicicleta = theBicicleta;
    }

    public Tarea[] getListTareas() {
        return listTareas;
    }

    public void setListTareas(Tarea[] listTareas) {
        this.listTareas = listTareas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
