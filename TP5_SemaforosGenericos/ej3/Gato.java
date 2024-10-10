package TP5_SemaforosGenericos.ej3;

import java.util.Random;
public class Gato implements Runnable {
     private Comedero objComedor;
      public Gato(Comedero objComedor){
           this.objComedor=objComedor;
      }
      public void run(){
            Random r=new Random();
            try {
                Thread.sleep( r.nextInt(200));
                this.objComedor.comerGato();
                Thread.sleep(r.nextInt(1000));
                System.out.println("come");
                this.objComedor.salirGato();
            } catch (Exception e) {
                // TODO: handle exception
            }
            
      }
}
