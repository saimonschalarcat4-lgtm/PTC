package co.edu.uniquindio.herencia.ejemplopersona;

public abstract class Persona {
    protected String cedula;
    protected String nombre;
    protected String telefono;

    public Persona(String cedula, String nombre, String telefono){
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Persona(){
    }

    public String hablar(){
        return "Hola soy una persona.";
    }

    @Override
    public String toString() {
        return "Persona: "+ cedula + " - " + nombre + " - " + telefono;
    }

    public String getCedula(){
        return this.cedula;
    }

    public void setCedula(String cedula){
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
