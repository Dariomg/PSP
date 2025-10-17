package HilosAlumnos;

import java.util.Scanner;

public class Hilo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Thread[] hilos = new Thread[3];
        Alumno[] alumnos = new Alumno[3];

        for (int i = 0; i < 3; i++) {
            System.out.print("Introduce el nombre del alumno " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            alumnos[i] = new Alumno(nombre);
            hilos[i] = new Thread(alumnos[i]);
        }

        for (Thread hilo : hilos) {
            hilo.start();
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("El hilo principal ha sido interrumpido.");
            }
        }

        scanner.close();
    }
}