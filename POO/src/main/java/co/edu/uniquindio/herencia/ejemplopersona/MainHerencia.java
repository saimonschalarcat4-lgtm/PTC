package co.edu.uniquindio.herencia.ejemplopersona;

public class MainHerencia {
    static void main() {
        Estudiante miEstudiante = new Estudiante("2222222", "pedro",
                "111111", "Sistemas");
        IO.println(miEstudiante.hablar());

        Profesor miProfesor = new Profesor("1234", "JHan Carlos", "311111111",
                "Ingeniero de sistemas", "Programacion 1");

        IO.println(miProfesor.hablar());

    }
}
