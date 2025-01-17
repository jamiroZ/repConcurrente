package TP__FINAL;

import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.MontañaRusa;
import TP__FINAL.CarpetaObsCompartidos.FilaMontaña;

//controlador que crea los hilos y recursos compartidos,etc
public class Controlador{
    public static void main(String[] args) {
        FilaMontaña fila=new FilaMontaña();
        MontañaRusa m=new MontañaRusa(fila);
        Thread hilo=new Thread(m);
        hilo.start();
        Visitante[] visi=new Visitante[15];
        Thread[] hilos=new Thread[15];
        for(int i=0;i<15;i++){
            visi[i]=new Visitante(fila);
            hilos[i]=new Thread(visi[i],"v-"+i);
            hilos[i].start();
        }
    }
}