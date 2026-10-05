package co.edu.uniquindio.enumrecord;

import java.time.LocalDate;

public class MainTest {
    static void main() {
        ReciboPago miRecibo =
                new ReciboPago("RP:01", LocalDate.now(), 20000.0, null);

        IO.println(miRecibo);
        IO.println(miRecibo.id());
    }
}
