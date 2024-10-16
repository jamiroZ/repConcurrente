package TP6_Monitores.ej2;
import java.util.Random;

public class Alumno implements Runnable{
     private SalaDeEstudio sala;
     public Alumno(SalaDeEstudio sala){
           this.sala=sala;
     }
     public void run(){
        
           try {
            Random r=new Random();
               this.sala.tomarMesa();
               Thread.sleep(r.nextInt(3000));
               this.sala.dejarMesa();
           } catch (Exception e) {
            // TODO: handle exception
           }
     }
}
