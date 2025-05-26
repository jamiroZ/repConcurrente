package TP__FINAL.CarpetaAtracciones;

import TP__FINAL.CarpetaObsCompartidos.SalaRealidadVirtual;
import java.util.Random;
//simula al encargado de la atraccion entregando los 3 objetos requeridos para entrar a la atraccion y el tiempo que el de para la atraccion
public class HiloRV implements Runnable{
    private SalaRealidadVirtual filaRV;
    public HiloRV (SalaRealidadVirtual filaRV){
             this.filaRV = filaRV;
    }
    public void run(){
        Random r=new Random();
           try {
               while(true){
                    this.filaRV.inicieActividad();
                    Thread.sleep(r.nextInt(10000)+1000);
                    this.filaRV.finalizoActividad();
               }
           } catch (Exception e) {
            // TODO: handle exception
           }
    }
}
