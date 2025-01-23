package TP__FINAL;
import java.util.Random;

import TP__FINAL.CarpetaObsCompartidos.*;

public class Visitante implements Runnable{
    private FilaMontaña fila;
    private FilaAutos filaA;
    private FilaBarco filaB;
    private FilaTeatro filaT;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB,FilaTeatro filaT){
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
        this.filaT = filaT;
    }
    public void run(){
        Random r=new Random();
        Boolean log=false;
        try {
               this.filaT.ingresaVisitante();
               this.filaT.saleVisitante(); 
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}