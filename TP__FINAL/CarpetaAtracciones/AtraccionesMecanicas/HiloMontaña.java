package TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.MontañaRusa;
public class HiloMontaña implements Runnable{
    private MontañaRusa fila;
    public HiloMontaña(MontañaRusa fila){
         this.fila = fila;
    }
    public void run(){
        Random r=new Random();
        try {
            while(true){
               this.fila.arrancar();
               Thread.sleep(r.nextInt(2000)+1000);
               this.fila.parar();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
