package ejercicioParcial.recu23.Semaforo;
import java.util.Random;

public class AnimalMayor implements Runnable{
    private Casa casa;
    public AnimalMayor(Casa casa){
        this.casa=casa;
    }

    public void run(){
        Random r=new Random();
        try {
            while(true){
                this.casa.dibujar();
                this.casa.cocinar();
                //simula tiempo cinando
                Thread.sleep(r.nextInt(3000));
                this.casa.servirComida();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
