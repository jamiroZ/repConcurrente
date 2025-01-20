package TP__FINAL;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.FilaAutos;
import TP__FINAL.CarpetaObsCompartidos.FilaMontaña;

public class Visitante implements Runnable{
    private FilaMontaña fila;
    private FilaAutos filaA;
    public Visitante(FilaMontaña fila,FilaAutos filaA){
        this.fila = fila;
        this.filaA = filaA;
    }
    public void run(){
        Random r=new Random();
        Boolean log=false;
        try {
               this.filaA.subirAuto();
               this.filaA.bajarAuto();         
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}