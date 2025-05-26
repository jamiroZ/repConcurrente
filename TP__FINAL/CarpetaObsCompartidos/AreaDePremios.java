package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.Exchanger;
import java.util.Random;
public class AreaDePremios {
     private Exchanger<String> fichaExchanger = new Exchanger<>();
    private Exchanger<Integer> puntosExchanger = new Exchanger<>();
    private Exchanger<String> premioExchanger = new Exchanger<>();

     //Entregar ficha
    public synchronized void pedirFicha() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " pidió una ficha...");
        String ficha = fichaExchanger.exchange(null);  // espera entrega
        System.out.println(Thread.currentThread().getName() + " recibió una ficha: " + ficha);
    }

    public void entregarFicha() throws InterruptedException {
        fichaExchanger.exchange("Ficha"); // entrega al visitante que espera
        System.out.println("Encargado entregó una ficha a un visitante");
    }

    public void enviarPuntos(int puntos) throws InterruptedException {
        puntosExchanger.exchange(puntos);
        System.out.println(Thread.currentThread().getName() + " jugó y obtuvo " + puntos + " puntos");
    }

    public void entregarPremio() throws InterruptedException {
        int puntos = puntosExchanger.exchange(0);
        String premio;
        if (puntos >= 80) premio = "Pelota grande";
        else if (puntos >= 50) premio = "Pelota chica";
        else premio = "Sticker";
        premioExchanger.exchange(premio);
        System.out.println("Encargado entregó premio: " + premio + " (por " + puntos + " puntos)");
    }

    public void recibirPremio() throws InterruptedException {
        String premio = premioExchanger.exchange(null);
        System.out.println(Thread.currentThread().getName() + " recibió premio: " + premio);
    }
}
