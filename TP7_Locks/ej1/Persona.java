package TP7_Locks.ej1;
import java.util.Random;

public class Persona implements Runnable{
    private char tipo;//si es jubilado 'j' si no da igual
    private Sala museo;
    public Persona( Sala museo,char tipo){
        this.museo=museo;
        this.tipo=tipo;
    
    }
    public void run(){
      Random r=new Random();
       try {
            if(this.tipo=='j'){
                
            }
             Thread.sleep((int) (Math.random() * 2000)); // Esperar un tiempo aleatorio
       } catch (Exception e) {
        // TODO: handle exception
       }
    }
}
