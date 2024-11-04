package ejercicioParcial.parcial22.ejercicio2DobleCola;

import java.util.Random;
public class Insertor implements Runnable {
    private Buffer buff;
    public  Insertor( Buffer buff){
        this.buff=buff;
    }
    public void run(){
         Random r=new Random();
         try {
            while(true){
                int  num= r.nextInt(100);
                this.buff.insertar(num);
                Thread.sleep(r.nextInt(4000)+1000);
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}

