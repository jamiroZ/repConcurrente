package TP__FINAL;

import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.AutosChocadores;
import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.MontañaRusa;
import TP__FINAL.CarpetaObsCompartidos.FilaAutos;
import TP__FINAL.CarpetaObsCompartidos.FilaMontaña;

//controlador que crea los hilos y recursos compartidos,etc
public class Controlador{
    public static void main(String[] args) {
        FilaMontaña fila=new FilaMontaña();
        FilaAutos filaA=new FilaAutos();

        MontañaRusa m=new MontañaRusa(fila);
        Thread hilo=new Thread(m);
        // hilo.start();

        AutosChocadores autos=new AutosChocadores(filaA);
        Thread hilo2=new Thread(autos);
        hilo2.start();

        Visitante[] visi=new Visitante[40];
        Thread[] hilos=new Thread[40];
        for(int i=0;i<40;i++){
            visi[i]=new Visitante(fila, filaA);
            hilos[i]=new Thread(visi[i],"v-"+i);
            hilos[i].start();
        }
    }
}