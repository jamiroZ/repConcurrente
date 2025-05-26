package TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas;

import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.BarcoPirata;

public class HiloBarco implements Runnable{
    private BarcoPirata filaBarco;
    public HiloBarco(BarcoPirata filaBarco){
         this.filaBarco = filaBarco;
    }
    public void run(){
        Random r=new Random();
        try {
            while(true){
                this.filaBarco.arrancarBarco();
                Thread.sleep(r.nextInt(6000)+1000);
                this.filaBarco.detenerBarco();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
