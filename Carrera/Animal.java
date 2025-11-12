package Carrera;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Animal extends Thread {
    private String nombre;
    private int posicion = 0;
    private int Final = 165;
    private int velocidad;
    private Semaphore tunel;
    private Random random = new Random();
    private boolean dormir = false;   
    private boolean volar = false;   
    private boolean interior = false;   
    private int pasosDormir = 0;      

    public Animal(String nombre, Semaphore tunel, int velocidad) {
        this.nombre = nombre;
        this.tunel = tunel;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        try {
            while (posicion < Final) {
                if (posicion >= 50 && posicion < 150 && !interior) {
                    System.out.println(nombre + " LLEGO al tunel y espera.");
                    tunel.acquire();
                    interior = true;
                    System.out.println(nombre + " ENTRO al tunel.");
                }
                if (posicion >= 150 && interior) {
                    tunel.release();
                    interior = false;
                    System.out.println(nombre + " SALIO del tunel.");
                }
                if (nombre.equalsIgnoreCase("Liebre")) {
                    liebre();
                } else if (nombre.equalsIgnoreCase("Pajaro")) {
                    pajaro();
                } else if (nombre.equalsIgnoreCase("Tortuga")) {
                    tortuga();
                }
                if (random.nextInt(20) == 0) {
                    viento();
                }
                if (posicion >= Final) {
                    posicion = Final;
                    System.out.println(nombre + " llego al final");
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tortuga() throws InterruptedException {
        Thread.sleep(1000);
        posicion += 2;
        if (posicion > Final) posicion = Final;
        System.out.println(nombre + " esta en el metro " + posicion);
    }

    private void liebre() throws InterruptedException {
        if (interior) {
            dormir = false;
            pasosDormir = 0;
        }
        if (!dormir) {
            Thread.sleep(1000);
            posicion += 5;
            pasosDormir++;
            System.out.println(nombre + " esta en el metro " + posicion);
            if (pasosDormir >= 4 && !interior) {
                dormir = true;
                pasosDormir = 0;
                System.out.println(nombre + " se durmio");
                Thread.sleep(10000);
                dormir = false;
                System.out.println(nombre + " se desperto");
            }
        } else {
            Thread.sleep(1000);
        }
    }

    private void pajaro() throws InterruptedException {
        Thread.sleep(1000);
        if (!interior && random.nextInt(5) == 0) {
            volar = true;
            boolean haciaAdelante = random.nextBoolean();
            int avance = haciaAdelante ? 10 : -10;
            posicion += avance;
            if (posicion < 0) posicion = 0;
            System.out.println(nombre + " vuela " + (haciaAdelante ? "hacia adelante" : "hacia atras") + " a " + posicion + " metros");
        } else {
            volar = false;
            posicion += 3;
            if (posicion < 0) posicion = 0;
            System.out.println(nombre + " camina en el metro " + posicion);
        }
    }

    private void viento() {
        System.out.println("Sopla el viento");
        if (volar) { 
            int efecto = random.nextInt(11) - 5;
            posicion += efecto;
            if (posicion < 0) posicion = 0;
            String dir = efecto >= 0 ? "a favor" : "en contra";
            System.out.println(nombre + " el viento lo mueve " + dir + " " + Math.abs(efecto) + " metros, ahora esta a " + posicion);
        }
        if (nombre.equalsIgnoreCase("Liebre") && dormir) {
            dormir = false;
            pasosDormir = 0;
            System.out.println(nombre + " se desperto por el viento");
        }
    }
}