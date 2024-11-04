package TP8.ej5;
import java.util.Random;
public class Canibales implements Runnable {
    private Hoguera  hoguera;

    public Canibales(Hoguera hoguera){
         this.hoguera=hoguera;
    }
    public void run(){
        Random r=new Random();
            try {
                this.hoguera.servirDeOlla();
            } catch (Exception e) {
                // TODO: handle exception
            }
    }
}
