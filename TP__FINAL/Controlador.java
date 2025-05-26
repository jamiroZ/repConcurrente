package TP__FINAL;

import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.*;

import java.util.concurrent.atomic.AtomicInteger;

import TP__FINAL.CarpetaAtracciones.*;
import TP__FINAL.CarpetaObsCompartidos.*;


//controlador que crea los hilos y recursos compartidos,etc
public class Controlador{
    public static void main(String[] args) {
        //-----OBJETOS COMPARTIDOS----
        MontañaRusa fila=new MontañaRusa();
        AutosChocadores filaA=new AutosChocadores();
        BarcoPirata filaB=new BarcoPirata();
        Teatro filaT=new Teatro();
        SalaRealidadVirtual filaRV = new SalaRealidadVirtual();
        Tren filaTren = new Tren();
        Comedor comedor=new Comedor(4);
        AreaDePremios premios=new AreaDePremios();
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

        HiloTrenes trenes=new HiloTrenes(filaTren,parque);
        Thread hiloTrenes=new Thread(trenes);
        hiloTrenes.start();
        
        HiloRV rv=new HiloRV(filaRV);
        Thread hiloRV = new Thread(rv);
        hiloRV.start();

        //ENCARGADO DEL AREA DE REALIDA VIRTUAL
        Encargado encargado=new Encargado(filaRV);
        Thread hiloEncargado=new Thread(encargado);
        hiloEncargado.start();
        //ENCARGADO DEL AREA DE PREMIOS
        HiloDePremios encargadoPremios=new HiloDePremios(premios);
       Thread hiloEncargadoPremios=new Thread(encargadoPremios);
       hiloEncargadoPremios.start();

        HiloMontaña m=new HiloMontaña(fila);
        Thread hilo=new Thread(m);
        hilo.start();

        HiloEspectaculo teatro=new HiloEspectaculo(filaT);
        Thread hiloTeatro=new Thread(teatro);
        hiloTeatro.start();
        
        //
        HiloAutos autos=new HiloAutos(filaA);
        Thread hilo2=new Thread(autos);
        hilo2.start();

        HiloBarco b=new HiloBarco(filaB);
        Thread hiloBarco=new Thread(b);
        hiloBarco.start();

        Asistente[] asis=new Asistente[6];
        Thread[]hiloAsis=new Thread[6];
        for(int i=0;i<6;i++){
            asis[i]= new Asistente(filaT,parque);
            hiloAsis[i]=new Thread(asis[i],""+i);
            hiloAsis[i].start();
        }
        try {
            Thread.sleep(5000);//
        } catch (Exception e) {
            // TODO: handle exception
        }
        for(int i=0;i<25;i++){
 
            visi[i]=new Visitante(fila, filaA,filaB,filaT,filaRV,reloj, parque,filaTren,comedor ,premios);
            hilos[i]=new Thread(visi[i],""+i);
            hilos[i].start();
        }
        //System.out.println(parque.estadoDelParque());
        
    }
}