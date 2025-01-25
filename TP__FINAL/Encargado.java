package TP__FINAL;

import TP__FINAL.CarpetaObsCompartidos.FilaRealidadVirtual;

public class Encargado implements Runnable {
    private FilaRealidadVirtual filaRV;
    public Encargado( FilaRealidadVirtual filaRV){
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
