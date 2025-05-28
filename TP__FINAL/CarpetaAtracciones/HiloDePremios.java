package TP__FINAL.CarpetaAtracciones;

import TP__FINAL.CarpetaObsCompartidos.AreaDePremios;

public class HiloDePremios implements Runnable{
    private AreaDePremios juegosPremios;
    public HiloDePremios(AreaDePremios juegosPremios){
           this.juegosPremios = juegosPremios;
    }
    public void run(){
         try {
            //Thread.sleep(4000);
            while(true){
                /*
                  el encargado tiene como tarea principal entregar fichas para que jueguen 
                  despues cambiar los puntos por premios de los jugadores que ganaron
                */
                this.juegosPremios.darFicha();
                this.juegosPremios.darPremio();
            }
            
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
