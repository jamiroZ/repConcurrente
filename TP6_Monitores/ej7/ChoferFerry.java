package TP6_Monitores.ej7;

import java.util.Random;

public class ChoferFerry implements Runnable{
    private Ferry ferry;
    public ChoferFerry(Ferry ferry){
              
    }
    public void run(){
        Random r=new Random();
           try {
               this.ferry.arrancar();
               Thread.sleep(r.nextInt(3000)+3000);
               this.ferry.llegoDestino();
               Thread.sleep(r.nextInt(3000)+3000);
           } catch (Exception e) {
            // TODO: handle exception
           }
    }
}
