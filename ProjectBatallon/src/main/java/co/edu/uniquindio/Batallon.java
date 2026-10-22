package co.edu.uniquindio;

public class Batallon {
    private String codigo;
    private String nombre;
    private String brigada;
    private String distrito;
    private int numero;

    public Batallon(String codigo, String nombre, String brigada, String distrito, int numero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.brigada = brigada;
        this.distrito = distrito;
        this.numero = numero;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBrigada() {
        return brigada;
    }

    public void setBrigada(String brigada) {
        this.brigada = brigada;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Batallon{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", brigada='" + brigada + '\'' +
                ", distrito='" + distrito + '\'' +
                ", numero=" + numero +
                '}';
    }
}
