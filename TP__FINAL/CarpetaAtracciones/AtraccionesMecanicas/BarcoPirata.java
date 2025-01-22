package TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas;

import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.FilaBarco;

public class BarcoPirata implements Runnable{
    private FilaBarco filaBarco;
    public BarcoPirata(FilaBarco filaBarco){
         this.filaBarco = filaBarco;
    }
    public void run(){
        Random r=new Random();
        try {
            while(true){
                this.filaBarco.arrancarBarco();
                Thread.sleep(r.nextInt(2000)+1000);
                this.filaBarco.detenerBarco();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
