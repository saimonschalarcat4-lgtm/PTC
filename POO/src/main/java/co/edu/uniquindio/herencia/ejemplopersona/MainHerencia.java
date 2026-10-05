package co.edu.uniquindio.herencia.ejemplopersona;

import java.util.LinkedList;

public class MainHerencia {
    static void main() {
        Estudiante miEstudiante = new Estudiante("2222222", "pedro",
                "111111", "Sistemas");
//        IO.println(miEstudiante.hablar());

        Profesor miProfesor = new Profesor("1234", "JHan Carlos", "311111111",
                "Ingeniero de sistemas", "Programacion 1");

//        IO.println(miProfesor.hablar());

        Profesor profesorVacio = new Profesor();
        Estudiante estudianteVacio = new Estudiante();

        LinkedList<Persona> listaIntegrantesUQ = new LinkedList<>();
        listaIntegrantesUQ.add(miProfesor);
        listaIntegrantesUQ.add(miEstudiante);

        for(Persona persona: listaIntegrantesUQ){
            IO.println(persona + "TipoClase: " + persona.getClass());
        }

    }
}
