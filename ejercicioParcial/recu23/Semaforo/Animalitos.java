package ejercicioParcial.recu23.Semaforo;
import java.util.Random;
public class Animalitos implements Runnable{
    private Casa casa;
    public Animalitos(Casa casa){
        this.casa = casa;
    }
    public void run(){
        Random aleatorio = new Random();
        try {
          
              Thread.sleep(aleatorio.nextInt(5000));//simula tiempo jugando
              this.casa.tomarBanquito();
              Thread.sleep(aleatorio.nextInt(3000));//simula tiempo comiendo
              this.casa.dejarBanquito();//vuelve a jugar
        
        
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
