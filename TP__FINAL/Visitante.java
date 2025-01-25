package TP__FINAL;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.*;

public class Visitante implements Runnable{
    private FilaMontaña fila;
    private FilaAutos filaA;
    private FilaBarco filaB;
    private FilaTeatro filaT;
    private FilaRealidadVirtual filaRV;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB,FilaTeatro filaT,FilaRealidadVirtual filaRV){
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
        this.filaT = filaT;
        this.filaRV =filaRV;
    }
    public void run(){
        Random r=new Random();
        Boolean log=false;
        try {
            System.out.println("");
               this.filaRV.entrarRV();
               this.filaRV.salirRV();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}