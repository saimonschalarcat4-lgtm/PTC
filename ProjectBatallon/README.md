# Proyecto Batallón

Implementación en Java con clases, herencia, enumeraciones, `ArrayList` y ciclos `for`.
Los datos se guardan en memoria durante la ejecución. No requiere frameworks ni base de datos.
Se conserva el paquete `co.edu.uniquindio` y el constructor original de `Batallon`.
Las listas son atributos `ArrayList` sin el modificador `final`.

## Clases y atributos

| Clase | Atributos |
| --- | --- |
| `Batallon` | codigo, nombre, brigada, distrito, numero, listaVehiculos, listaMision |
| `Vehiculo` (abstracta) | id, modelo, anioFabricacion, kilometraje, estadoOperativo, listaDetallesMision |
| `TransporteTropas` | atributos heredados y capacidadSoldados |
| `Blindado` | atributos heredados y nivelBlindaje (entero positivo, sin escala específica) |
| `Apoyo` | atributos heredados y tipoFuncion |
| `Mision` | id, fecha, ubicacion, estado, personalAsignado, listaDetallesMision |
| `DetalleMision` | mision, vehiculo, kilometrosRecorridos |

Enumeraciones: `EstadoOperativo` (DISPONIBLE, EN_MISION, EN_MANTENIMIENTO),
`TipoFuncion` (LOGISTICA, COMUNICACIONES, MEDICO) y
`EstadoMision` (PLANIFICADA, EN_CURSO, COMPLETADA).

El personal se representa con nombres (`ArrayList<String>`), pues el diagrama no define
una clase de soldados. No se modelan hojas de vida ni disponibilidad individual.
Un detalle pertenece a una misión y referencia un vehículo. Ambos guardan el mismo
detalle, para consultar el historial del vehículo y los vehículos usados en la misión.

## Uso

1. Crear los vehículos y registrarlos con `batallon.registrarVehiculo(vehiculo)`.
2. Crear una `Mision` y agregar nombres con `mision.agregarPersonal(nombre)`.
3. Registrarla con `batallon.registrarMision(mision)`.
4. Asignar vehículos con `batallon.asignarVehiculoMision(idMision, idVehiculo)`.
5. Ejecutar `batallon.iniciarMision(idMision)`: requiere personal, vehículos y disponibilidad.
6. Registrar la distancia de cada vehículo con `registrarKilometrajeMision(idMision, idVehiculo, kilometros)`.
7. Ejecutar `batallon.completarMision(idMision)`: suma las distancias al odómetro y libera los vehículos.
8. Consultar `batallon.obtenerVehiculosConMasDe50Misiones()`.

Registrar otra vez la distancia reemplaza el valor anterior de esa misión; no lo acumula.
La distancia empieza en cero, lo que permite misiones sin desplazamiento.
Las misiones planificadas o en curso no se cuentan. Exactamente 50 tampoco cumple: se necesitan 51 o más.
La misma misión no se puede completar dos veces ni modificar después de iniciarla.
Se pueden planificar varias misiones con un vehículo; su disponibilidad se valida al iniciar cada misión.
Para mantenimiento se usa `vehiculo.setEstadoOperativo(EstadoOperativo.EN_MANTENIMIENTO)`
y para liberarlo, `DISPONIBLE`. Se puede registrar un vehículo con cualquiera de los tres
estados. Al ejecutar misiones en esta aplicación, sus estados se actualizan automáticamente
y no se permite modificarlos manualmente mientras la misión esté en curso.

Los IDs no tienen setters para evitar duplicados después del registro. Las listas se devuelven
como copias: para registrar elementos se usan los métodos del modelo, no los getters.
Los demás atributos editables tienen getters y setters con validaciones básicas.
`buscarVehiculo` y `buscarMision` devuelven `null` si el ID no existe.

## Validaciones con condiciones

Se usan condiciones y variables de resultado, sin manejo de excepciones.
Cada método que devuelve un valor tiene un solo `return`, ubicado al final.
Los métodos `void` y los constructores no necesitan `return`.
Los métodos para registrar, asignar, iniciar, completar y actualizar datos validados
devuelven `true` cuando realizan la operación y `false` cuando no pueden realizarla.
Una operación rechazada deja los datos como estaban.

Por ejemplo, se inicia una variable `registrado` en `false`, se cambia a `true`
cuando se agrega el vehículo y al final se ejecuta `return registrado`.
Las búsquedas guardan el objeto encontrado en una variable y lo devuelven al terminar.

Los constructores guardan los datos recibidos. `registrarVehiculo` y `registrarMision`
comprueban esos datos con `esValido()` o `esValida()` antes de agregar el objeto a la lista.
Así, crear un objeto con datos incompletos no lo registra automáticamente.

Por ejemplo:

```java
if (batallon.iniciarMision("M-01")) {
    System.out.println("Misión iniciada.");
} else {
    System.out.println("No se pudo iniciar. Revisa el personal y los vehículos.");
}
```

## Ejecutar

El proyecto conserva Java 25, como la configuración original. Desde IntelliJ IDEA,
abrir `pom.xml`, cargar Maven y ejecutar `Main.main` o las pruebas de `src/test/java`.
`Main` muestra los tres tipos de vehículos y simula 51 misiones del transporte de tropas.

Con Maven instalado, desde la carpeta del proyecto:

```sh
mvn test
java -cp target/classes co.edu.uniquindio.Main
```

Para ejecutar únicamente la aplicación sin Maven:

```sh
mkdir -p target/classes
javac -encoding UTF-8 -d target/classes src/main/java/co/edu/uniquindio/*.java
java -cp target/classes co.edu.uniquindio.Main
```

Verificación realizada: compilación con `javac` y ejecución de `Main` con Java 25;
17 pruebas JUnit correctas (las 5 existentes y 12 de gestión del batallón).
Las pruebas incluyen el límite de 50/51 misiones, estados operativos, kilometraje,
IDs duplicados y disponibilidad de varios vehículos. Se ejecutaron con JUnit Launcher;
`mvn test` en modo sin conexión quedó pendiente por dependencias de Maven ausentes en la caché local.
