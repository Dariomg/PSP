package CajaRegistradora;

import java.util.Random;

public class Empleado implements Runnable {
    private Caja caja;
    private String nombre;

    public Empleado(Caja caja, String nombre) {
        this.caja = caja;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            double cantidad = 5 + (95 * random.nextDouble());
            caja.cobrar(cantidad);
            System.out.println("Empleado " + nombre + " cobró " + String.format("%.2f", cantidad) + 
            		" €. Total en caja: " + String.format("%.2f", caja.getTotal()) + " €");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}