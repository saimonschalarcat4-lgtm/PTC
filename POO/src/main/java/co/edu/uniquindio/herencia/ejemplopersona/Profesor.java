package co.edu.uniquindio.herencia.ejemplopersona;

public class Profesor extends Persona{
    private String titulo;
    private String materia;

    public Profesor(String cedula, String nombre, String telefono, String titulo, String materia) {
        super(cedula, nombre, telefono);
        this.titulo = titulo;
        this.materia = materia;
    }

    public Profesor(){
        super();
    }

    @Override
    public String hablar() {
        return "El profesor se presenta.";
    }

    @Override
    public String toString() {
        return "Profesor: "+ cedula + " - " + nombre + " - " + telefono + " - " + titulo + " - " + materia;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
