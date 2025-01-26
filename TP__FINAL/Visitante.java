package TP__FINAL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import TP__FINAL.CarpetaObsCompartidos.*;

public class Visitante implements Runnable{
    //ATRACCIONES DEL PARQUE
    private FilaMontaña fila;
    private FilaAutos filaA;
    private FilaBarco filaB;
    private FilaTeatro filaT;
    private FilaRealidadVirtual filaRV;

    private Reloj reloj;
    private Parque parque;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB,FilaTeatro filaT,FilaRealidadVirtual filaRV, Reloj reloj,Parque parque){
        this.parque=parque;
        this.reloj=reloj;
       
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
        this.filaT = filaT;
        this.filaRV =filaRV;  
    }
    public void run(){
        Random r=new Random();
        int num;
        try {
            AtomicInteger hora=new AtomicInteger(19);
            parque.ingresarParque();
            while(reloj.horaActual().get() <= hora.get()){
                   
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            parque.salirParque();
        }
        
    }
}