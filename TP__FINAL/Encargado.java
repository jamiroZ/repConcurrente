package TP__FINAL;

import TP__FINAL.CarpetaObsCompartidos.SalaRealidadVirtual;

public class Encargado implements Runnable {
    private SalaRealidadVirtual filaRV;
    public Encargado( SalaRealidadVirtual filaRV){
        this.filaRV=filaRV;
    }
    public void run(){
         try {
            while(true){
                this.filaRV.reponerEquipo();
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
