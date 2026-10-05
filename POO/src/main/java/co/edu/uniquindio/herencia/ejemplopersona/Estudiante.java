package co.edu.uniquindio.herencia.ejemplopersona;

public class Estudiante extends Persona{
    private String carrera;

    public Estudiante(String cedula, String nombre,
                      String telefono, String carrera){
        super(cedula, nombre, telefono);
        this.carrera = carrera;
    }

    public Estudiante() {

    }

    @Override
    public String toString() {
        return "Estudiante: "+ cedula + " - " + nombre + " - " + telefono + " - " + carrera;
    }

    public void setCarrera(String carrera){
        this.carrera = carrera;
    }

    public String getCarrera(){
        return carrera;
    }
}
