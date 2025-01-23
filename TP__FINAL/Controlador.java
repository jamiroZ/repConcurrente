package TP__FINAL;

import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.*;
import TP__FINAL.CarpetaAtracciones.*;
import TP__FINAL.CarpetaObsCompartidos.*;


//controlador que crea los hilos y recursos compartidos,etc
public class Controlador{
    public static void main(String[] args) {
        FilaMontaña fila=new FilaMontaña();
        FilaAutos filaA=new FilaAutos();
        FilaBarco filaB=new FilaBarco();
        FilaTeatro filaT=new FilaTeatro();
        
        MontañaRusa m=new MontañaRusa(fila);
        Thread hilo=new Thread(m);

        BarcoPirata b=new BarcoPirata(filaB);
        Thread hiloBarco=new Thread(b);
        //hiloBarco.start();

        Espectaculo teatro=new Espectaculo(filaT);
        Thread hiloTeatro=new Thread(teatro);
        hiloTeatro.start();
        // hilo.start();
       
        AutosChocadores autos=new AutosChocadores(filaA);
        Thread hilo2=new Thread(autos);
        //hilo2.start();

        Asistente[] asis=new Asistente[6];
        Thread[]hiloAsis=new Thread[6];
        for(int i=0;i<6;i++){
            asis[i]= new Asistente(filaT);
             hiloAsis[i]=new Thread(asis[i],""+i);
             hiloAsis[i].start();
        }
        Visitante[] visi=new Visitante[40];
        Thread[] hilos=new Thread[40];
        for(int i=0;i<40;i++){
            visi[i]=new Visitante(fila, filaA,filaB,filaT);
            hilos[i]=new Thread(visi[i],""+i);
            hilos[i].start();
        }
    }
}