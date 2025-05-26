package TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.AutosChocadores;

public class HiloAutos implements Runnable {

    private AutosChocadores filaAutos;
    public HiloAutos(AutosChocadores filaAutos){
         this.filaAutos = filaAutos;
    }
    public void run(){
        Random r=new Random();
        try {
            while(true){
                this.filaAutos.iniciarJuego();
                Thread.sleep(r.nextInt(2000)+1000);
                this.filaAutos.finalizarJuego();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
