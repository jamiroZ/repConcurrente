package TP__FINAL;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.*;

public class Visitante implements Runnable{
    private FilaMontaña fila;
    private FilaAutos filaA;
    private FilaBarco filaB;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB){
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
    }
    public void run(){
        Random r=new Random();
        Boolean log=false;
        try {
               this.filaB.subirBarco();
               this.filaB.bajarBarco();  
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}