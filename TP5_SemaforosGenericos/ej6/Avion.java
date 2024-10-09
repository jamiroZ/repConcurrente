package TP5_SemaforosGenericos.ej6;

import java.util.Random;

public class Avion implements Runnable{
    private TorreDeControl torre;
    public Avion( TorreDeControl torre){
        this.torre=torre;
    }
    public void run(){
        Random r=new Random();
        while(true){
            try {
                this.torre.IntentaAterrizar();
                Thread.sleep(300);
                this.torre.aterriza();
                Thread.sleep(r.nextInt(1000)+300);
                this.torre.IntentaDespegar();
                Thread.sleep(200);
                this.torre.despegar();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
