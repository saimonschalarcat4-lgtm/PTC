package co.edu.uniquindio.parcial1;

public class Empresa {
    private String nombre;
    //Atributos Propios de la clase empresa

    private Proyecto[] listaProyectos;
    private Cliente[] listaClientes;
    private ServicioAdicional[] listaServicios;
    private Desarrollador[] listaDesarrolladores;

    public Empresa(String nombre) {
        this.nombre = nombre;
        listaProyectos = new Proyecto[10];
        listaClientes = new Cliente[10];
        listaServicios = new ServicioAdicional[10];
        listaDesarrolladores = new Desarrollador[10];
    }

    //Metodos logicos de la clase empresa
    //CRUD Cliente
    //CRUD Desarrollador
    //CRUD Proyecto
    //CRUD Servicios
    //Metodo de calcular numero perfecto de telefono cliente.

    public double calcularIngresosTotales(){

        return 0;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {}
}
