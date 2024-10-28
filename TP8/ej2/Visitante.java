package TP8.ej2;
import java.util.Random;
public class Visitante implements Runnable{
    private boolean sillaDeRuedas;
    private Observatorio obs;
    public Visitante(Observatorio obs, boolean sillaDeRuedas){
       this.obs=obs;
       this.sillaDeRuedas=sillaDeRuedas;
    }
    public void run(){
        Random r = new Random();
        try {
            this.obs.entrarVisitante(sillaDeRuedas);
            Thread.sleep(r.nextInt(2000));
            this.obs.salirVisitante(sillaDeRuedas);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
