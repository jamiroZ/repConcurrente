package TP__FINAL;

import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.*;

import java.util.concurrent.atomic.AtomicInteger;

import TP__FINAL.CarpetaAtracciones.*;
import TP__FINAL.CarpetaObsCompartidos.*;


//controlador que crea los hilos y recursos compartidos,etc
public class Controlador{
    public static void main(String[] args) {
        //-----OBJETOS COMPARTIDOS----
        FilaMontaña fila=new FilaMontaña();
        FilaAutos filaA=new FilaAutos();
        FilaBarco filaB=new FilaBarco();
        FilaTeatro filaT=new FilaTeatro();
        FilaRealidadVirtual filaRV = new FilaRealidadVirtual();
        FilaTren filaTren = new FilaTren();
        FilaComedor comedor=new FilaComedor(4);
        Premios premios=new Premios();
        Parque parque=new Parque();


        //---------HILOS-------
        //horarios del parque
        AtomicInteger hora= new AtomicInteger(8);
        Reloj reloj=new Reloj(hora, parque);
        Thread hiloReloj=new Thread(reloj);
        hiloReloj.start();

        //hilos de visitantes
        Visitante[] visi=new Visitante[25];
        Thread[] hilos=new Thread[25];

        //HILOS DE ATRACCIONES Y ENCARGADOS
        EncargadoPremios encargadoP=new EncargadoPremios(premios);
        Thread hiloEncargadoP=new Thread(encargadoP);
        hiloEncargadoP.start();

        Trenes trenes=new Trenes(filaTren);
        Thread hiloTrenes=new Thread(trenes);
        hiloTrenes.start();
        
        RealidadVirtual rv=new RealidadVirtual(filaRV);
        Thread hiloRV = new Thread(rv);
        hiloRV.start();
        //ENCARGADO DEL AREA DE REALIDA VIRTUAL
        Encargado encargado=new Encargado(filaRV);
        Thread hiloEncargado=new Thread(encargado);
        hiloEncargado.start();

        MontañaRusa m=new MontañaRusa(fila);
        Thread hilo=new Thread(m);
        hilo.start();

        Espectaculo teatro=new Espectaculo(filaT);
        Thread hiloTeatro=new Thread(teatro);
        hiloTeatro.start();
        
        //ASISTENTES DEL TEATRO
        Asistente[] asis=new Asistente[6];
        Thread[]hiloAsis=new Thread[6];
        for(int i=0;i<6;i++){
            asis[i]= new Asistente(filaT);
             hiloAsis[i]=new Thread(asis[i],""+i);
             //hiloAsis[i].start();
        }
       
        AutosChocadores autos=new AutosChocadores(filaA);
        Thread hilo2=new Thread(autos);
        hilo2.start();

        BarcoPirata b=new BarcoPirata(filaB);
        Thread hiloBarco=new Thread(b);
        hiloBarco.start();

        for(int i=0;i<25;i++){
 
            visi[i]=new Visitante(fila, filaA,filaB,filaT,filaRV,reloj, parque,filaTren,comedor ,premios);
            hilos[i]=new Thread(visi[i],""+i);
            hilos[i].start();
        }
    }
}