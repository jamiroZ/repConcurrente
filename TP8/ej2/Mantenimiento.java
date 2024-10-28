package TP8.ej2;
import java.util.Random;
public class Mantenimiento implements Runnable {
    private Observatorio obs;
    public Mantenimiento(Observatorio obs){
          this.obs = obs; 
    }
    public void run(){
        Random r = new Random();
        try {
            this.obs.entrarMantenimiento();
            Thread.sleep(r.nextInt(2000));
            this.obs.salirMantenimiento();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
