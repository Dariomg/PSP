package Carrera;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Animal extends Thread {
    private String nombre;
    private int metros = 0;
    private int meta = 165;
    private int velocidad;
    private Semaphore tunel;
    private Random random = new Random();
    
    private boolean dormir = false;   
    private boolean volar = false;   
    private boolean interior = false;   
    private int pasosAntesDeDormir = 0;      

    public Animal(String nombre, Semaphore tunel, int velocidad) {
        this.nombre = nombre;
        this.tunel = tunel;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        try {
            while (metros < meta) {
                if (metros >= 50 && metros < 150 && !interior) {
                    System.out.println(nombre + " LLEGO al tunel y espera.");
                    tunel.acquire();
                    interior = true;
                    System.out.println(nombre + " ha ENTRADO al tunel.");
                }
                if (metros >= 150 && interior) {
                    tunel.release();
                    interior = false;
                    System.out.println(nombre + " ha SALIDO del tunel.");
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
                if (metros >= meta) {
                	metros = meta;
                    System.out.println(nombre + " LLEGO al final.");
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tortuga() throws InterruptedException {
        Thread.sleep(1000);
        metros += 2;
        if (metros > meta) metros = meta;
        System.out.println(nombre + " esta a " + metros + " metros.");
    }

    private void liebre() throws InterruptedException {
        if (interior) {
            dormir = false;
            pasosAntesDeDormir = 0;
        }
        if (!dormir) {
            Thread.sleep(1000);
            metros += 5;
            pasosAntesDeDormir++;
            System.out.println(nombre + " esta a " + metros + " metros.");
            if (pasosAntesDeDormir >= 4 && !interior) {
                dormir = true;
                pasosAntesDeDormir = 0;
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
            metros += avance;
            if (metros < 0) metros = 0;
            System.out.println(nombre + " vuela " + 
                (haciaAdelante ? "hacia adelante" : "hacia atrás") + 
                " y esta en el metro " + metros);
        } else {
            volar = false;
            metros += 3;
            if (metros < 0) metros = 0;
            System.out.println(nombre + " camina en " + metros + " metros.");
        }
    }

    private void viento() {
        System.out.println("Viento");
        if (volar) {
            int efecto = random.nextInt(11) - 5;
            metros += efecto;
            if (metros < 0) metros = 0;
            String dir = efecto >= 0 ? "a favor" : "en contra";
            System.out.println(nombre + " vuela con viento " + dir + 
                " y esta en el metro " + metros + 
                " (" + (efecto >= 0 ? "+" : "") + efecto + " metros)");
        }

        if (nombre.equalsIgnoreCase("Liebre") && dormir && !interior) {
            dormir = false;
            pasosAntesDeDormir = 0;
            System.out.println(nombre + " se desperto por el viento.");
        }
    }
}