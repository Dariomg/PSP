package Tortugas;

import java.util.concurrent.Semaphore;

public class Tortuga extends Thread {
    private String nombre;
    private int posicion = 0;
    private int meta = 20;
    private int velocidad = 1000;
    private Semaphore tunel;

    public Tortuga(String nombre, Semaphore tunel) {
        this.nombre = nombre;
        this.tunel = tunel;
    }

    @Override
    public void run() {
        try {
            while (posicion < meta) {
                Thread.sleep(velocidad);
                posicion++;

                if (posicion == 5) {
                    System.out.println(nombre + " ha llegado al túnel y espera su turno...");
                    tunel.acquire();
                    System.out.println(nombre + " ha ENTRADO al túnel.");
                }

                if (posicion == 15) {
                    System.out.println(nombre + " ha SALIDO del túnel.");
                    tunel.release();
                }

                System.out.println(nombre + " está en el metro " + posicion);

                if (posicion == meta) {
                    System.out.println(nombre + " ha llegado a la meta!");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}