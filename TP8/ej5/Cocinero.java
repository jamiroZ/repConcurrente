package TP8.ej5;
import java.util.Random;

public class Cocinero implements Runnable {
    private Hoguera hoguera;
    public Cocinero(Hoguera hoguera){
             this.hoguera=hoguera;
    }
    public void run(){
        Random r=new Random();
         try {
            while(true){
               this.hoguera.dormir();//duerme hasta que le avisen que llene olla
               Thread.sleep(r.nextInt(4000));//tiempo de cocina
               this.hoguera.llenarOlla();//llena la olla
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
