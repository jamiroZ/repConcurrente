package TP6_Monitores.ej7;
import java.util.Random;
public class Pasajero implements Runnable {
    private Ferry ferry;
    public Pasajero(Ferry ferry){
          this.ferry = ferry;
    }
    public void run(){
        
         try {
            this.ferry.embarquePasajeros();
            this.ferry.desembarquePasajeros();
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
