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
            
                this.es.Hlisto();
                

            
         } catch (Exception e) {
            // TODO: handle exception
         }
     }
}
