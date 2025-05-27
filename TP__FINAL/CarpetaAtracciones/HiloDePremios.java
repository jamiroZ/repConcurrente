package TP__FINAL.CarpetaAtracciones;

import TP__FINAL.CarpetaObsCompartidos.AreaDePremios;

public class HiloDePremios implements Runnable{
    private AreaDePremios juegosPremios;
    public HiloDePremios(AreaDePremios juegosPremios){
           this.juegosPremios = juegosPremios;
    }
    public void run(){
         try {
            while(true){
                this.juegosPremios.darFicha();
            }
            
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
