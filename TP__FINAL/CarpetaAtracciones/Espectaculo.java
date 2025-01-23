package TP__FINAL.CarpetaAtracciones;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.FilaTeatro;
public class Espectaculo implements Runnable{
    private FilaTeatro filaTeatro;

    public Espectaculo(FilaTeatro filaTeatro){
        this.filaTeatro=filaTeatro;
    }
    public void run(){
        Random r=new Random();
         try {
            while(true){
                  this.filaTeatro.inicieLaFuncion();
                  Thread.sleep(r.nextInt(5000));
                  this.filaTeatro.bajarTelon();
            }
          
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
