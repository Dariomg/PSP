package Carrera;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore tunel = new Semaphore(1);

        Animal tortuga = new Animal("Tortuga", tunel, 2);
        Animal pajaro = new Animal("Pajaro", tunel, 3);
        Animal liebre = new Animal("Liebre", tunel, 5);

        System.out.println("La carrera ha empezado\n");
        tortuga.start();
        pajaro.start();
        liebre.start();
    }
}