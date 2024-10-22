package TP7_Locks.ej1;
import java.util.Random;

public class MedidorTemp implements Runnable {
    private Sala museo;
    public MedidorTemp(Sala museo){
        this.museo = museo;
    }
    
    public void run(){
        Random r=new Random();
        try {
            while(true){
               this.museo.notificarTemperatura(); 
               Thread.sleep(3000); // Esperar un tiempo 6seg
            }
        } catch (Exception e) {
         // TODO: handle exception
        }
    }
}
