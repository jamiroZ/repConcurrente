package ejercicioParcial.parcial23.ejercicio2;
import java.util.Random;
public class Oxigeno implements Runnable {
    private Espacio es;
    public Oxigeno(Espacio es){
        this.es=es;
    }
     public void run(){
        Random r=new Random();
         try {
            while(true){
                this.es.Olisto();
            }
           
         } catch (Exception e) {
            // TODO: handle exception
         }
     }
}
