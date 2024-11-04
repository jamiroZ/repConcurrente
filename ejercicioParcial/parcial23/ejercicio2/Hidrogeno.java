package ejercicioParcial.parcial23.ejercicio2;

import java.util.Random;

public class Hidrogeno implements Runnable{
    private Espacio es;
    public Hidrogeno(Espacio es){
        this.es=es;
    }
     public void run(){
        Random r=new Random();
         try {
           while(true){
                this.es.Hlisto();
                Thread.sleep(r.nextInt(4000));
           }   
            
         } catch (Exception e) {
            // TODO: handle exception
         }
     }
}
