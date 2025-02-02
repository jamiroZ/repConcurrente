package TP__FINAL;

import TP__FINAL.CarpetaObsCompartidos.Premios;

public class EncargadoPremios implements Runnable{
    private Premios juegosPremios;
    public EncargadoPremios(Premios juegosPremios){
        this.juegosPremios=juegosPremios;
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
