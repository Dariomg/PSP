package Tortugas;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore tunel = new Semaphore(1);

        Tortuga t1 = new Tortuga("Tortuga 1", tunel);
        Tortuga t2 = new Tortuga("Tortuga 2", tunel);
        Tortuga t3 = new Tortuga("Tortuga 3", tunel);

        System.out.println("¡Comienza la carrera de tortugas!\n");
        t1.start();
        t2.start();
        t3.start();
    }
}
