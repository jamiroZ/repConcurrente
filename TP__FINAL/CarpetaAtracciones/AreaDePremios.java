package TP__FINAL.CarpetaAtracciones;

import TP__FINAL.CarpetaObsCompartidos.Premios;

public class AreaDePremios implements Runnable{
    private Premios juegosPremios;
    public AreaDePremios(Premios juegosPremios){
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
