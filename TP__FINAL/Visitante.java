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
    private FilaComedor comedor;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB,FilaTeatro filaT,FilaRealidadVirtual filaRV, Reloj reloj,Parque parque,FilaTren filaTren,FilaComedor comedor){
        this.parque=parque;
        this.reloj=reloj;
       this.comedor=comedor;
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
        this.filaT = filaT;
        this.filaRV =filaRV;  
        this.filaTren=filaTren;
        this.comedor=comedor;
    }
    public void run(){
        Random r=new Random();
        int num;
        try {
            AtomicInteger hora=new AtomicInteger(19);
            parque.ingresarParque();
            Thread.sleep(1000);
            int num2=0;
            while(reloj.horaActual().get() <= hora.get() ){
                   comedor.entrarComedor();
                   num2=comedor.buscarMesa();
                   Thread.sleep(r.nextInt(4000));//simula tiempo comiend0
                   comedor.dejarMesa(num2);
                   comedor.salirComedor();
            } 
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            parque.salirParque();
        }
        
    }
}