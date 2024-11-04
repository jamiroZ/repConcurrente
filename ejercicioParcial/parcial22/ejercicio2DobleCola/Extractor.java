package ejercicioParcial.parcial22.ejercicio2DobleCola;

import java.util.Random;

public class Extractor implements Runnable {
    private Buffer buff;
    public Extractor(Buffer buff){
            this.buff=buff;
    }

    public void run(){
        Random r=new Random();
         try {
            while(true){
               this.buff.extraer();
               Thread.sleep(r.nextInt(3999)+1000);
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
