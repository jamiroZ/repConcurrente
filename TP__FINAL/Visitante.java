package TP__FINAL;
import java.util.Random;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

import TP__FINAL.CarpetaObsCompartidos.*;

public class Visitante implements Runnable{
    //ATRACCIONES DEL PARQUE
    private FilaMontaña fila;
    private FilaAutos filaA;
    private FilaBarco filaB;
    private FilaTeatro filaT;
    private FilaRealidadVirtual filaRV;

    private FilaTren filaTren;
    private Reloj reloj;
    private Parque parque;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB,FilaTeatro filaT,FilaRealidadVirtual filaRV, Reloj reloj,Parque parque,FilaTren filaTren){
        this.parque=parque;
        this.reloj=reloj;
       
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
        this.filaT = filaT;
        this.filaRV =filaRV;  
        this.filaTren=filaTren;
         
    }
    public void run(){
        Random r=new Random();
        int num;
        try {
            AtomicInteger hora=new AtomicInteger(19);
            parque.ingresarParque();
            Thread.sleep(1000);
            while(reloj.horaActual().get() <= hora.get() ){
                   this.filaTren.subirTren();
                   this.filaTren.bajarTren();
            } 
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            parque.salirParque();
        }
        
    }
}