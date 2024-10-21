package TP7_Locks.ej1;
import java.util.Random;

public class medidorTemp implements Runnable {
    private Sala museo;
    public medidorTemp(Sala museo){
        this.museo = museo;
    }
    
    public void run(){
        Random r=new Random();
        try {
            Thread.sleep((int) (Math.random() * 2000)); // Esperar un tiempo aleatorio
        } catch (Exception e) {
         // TODO: handle exception
        }
    }
}
