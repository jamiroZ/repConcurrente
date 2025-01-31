package TP__FINAL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import TP__FINAL.CarpetaObsCompartidos.FilaAutos;
import TP__FINAL.CarpetaObsCompartidos.FilaBarco;
import TP__FINAL.CarpetaObsCompartidos.FilaComedor;
import TP__FINAL.CarpetaObsCompartidos.FilaMontaña;
import TP__FINAL.CarpetaObsCompartidos.FilaRealidadVirtual;
import TP__FINAL.CarpetaObsCompartidos.FilaTeatro;
import TP__FINAL.CarpetaObsCompartidos.FilaTren;
import TP__FINAL.CarpetaObsCompartidos.Parque;
import TP__FINAL.CarpetaObsCompartidos.Premios;

public class Visitante implements Runnable{
    //ATRACCIONES DEL PARQUE
    private FilaMontaña fila;
    private FilaAutos filaA;
    private FilaBarco filaB;
    private FilaTeatro filaT;
    private FilaRealidadVirtual filaRV;
    private Premios juegosPremios;
    private FilaTren filaTren;
    private Reloj reloj;
    private Parque parque;
    private FilaComedor comedor;
    public Visitante(FilaMontaña fila,FilaAutos filaA,FilaBarco filaB,FilaTeatro filaT,FilaRealidadVirtual filaRV, Reloj reloj,Parque parque,FilaTren filaTren,FilaComedor comedor,Premios juegosPremios){
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
        this.juegosPremios=juegosPremios;
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
                   this.juegosPremios.pedirFicha();
                   Thread.sleep(r.nextInt(4000));//simula tiempo jugando
                   this.juegosPremios.jugarJuegos();
                   this.juegosPremios.ganarPremio();

            }                 
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            parque.salirParque();
        }
        
    }
}