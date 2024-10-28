package TP8.ej2;
import java.util.Random;

public class Investigador implements Runnable{
    private Observatorio obs;
    public Investigador(Observatorio obs){
       this.obs=obs;
    }
    public void run(){
        Random r = new Random();
        try {
            this.obs.entrarInvestigador();
            Thread.sleep(r.nextInt(2000));
            this.obs.salirInvestigador();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
