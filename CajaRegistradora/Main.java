package CajaRegistradora;

public class Main {
    public static void main(String[] args) {
        Caja caja = new Caja();

        Thread empleado1 = new Thread(new Empleado(caja, "Ana"));
        Thread empleado2 = new Thread(new Empleado(caja, "Luis"));
        Thread empleado3 = new Thread(new Empleado(caja, "María"));

        empleado1.start();
        empleado2.start();
        empleado3.start();

        try {
            empleado1.join();
            empleado2.join();
            empleado3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("TOTAL FINAL EN CAJA: " + String.format("%.2f", caja.getTotal()) + " €");
    }
}
