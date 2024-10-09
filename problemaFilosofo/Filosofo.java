/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problemaFilosofo;

/**
 *
 * @author Usuario
 */
public class Filosofo extends Thread {

    private int id;
    private Mesa mesa;

    public Filosofo(int id, Mesa unaMesa) {
        this.id = id;
        this.mesa = unaMesa;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((int) (Math.random() * 1000)); // Simula el tiempo de pensar
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep((int) (Math.random() * 1000)); // Simula el tiempo de comer
    }
    
    @Override
    public void run() {
            try {
                while (true) {
                    pensar();
                    
                    // Toma los dos tenedores
                    System.out.println("Filósofo " + id + " terminó de pensar e intenta agarrar los tenedores.");
                    mesa.agarrarTenedores();
                    System.out.println("Filósofo " + id + " agarró los 2 tenedores.");
                    comer();
                    
                    // Soltar los tenedores                   
                    mesa.soltarTenedores();
                    System.out.println("Filósofo " + id + " soltó los 2 tenedores.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
}
