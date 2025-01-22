package TP__FINAL;

import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.AutosChocadores;
import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.BarcoPirata;
import TP__FINAL.CarpetaAtracciones.AtraccionesMecanicas.MontañaRusa;
import TP__FINAL.CarpetaObsCompartidos.FilaAutos;
import TP__FINAL.CarpetaObsCompartidos.FilaBarco;
import TP__FINAL.CarpetaObsCompartidos.FilaMontaña;

//controlador que crea los hilos y recursos compartidos,etc
public class Controlador{
    public static void main(String[] args) {
        FilaMontaña fila=new FilaMontaña();
        FilaAutos filaA=new FilaAutos();
        FilaBarco filaB=new FilaBarco();

        MontañaRusa m=new MontañaRusa(fila);
        Thread hilo=new Thread(m);

        BarcoPirata b=new BarcoPirata(filaB);
        Thread hiloBarco=new Thread(b);
        hiloBarco.start();
        // hilo.start();

        AutosChocadores autos=new AutosChocadores(filaA);
        Thread hilo2=new Thread(autos);
        hilo2.start();

        Visitante[] visi=new Visitante[40];
        Thread[] hilos=new Thread[40];
        for(int i=0;i<40;i++){
            visi[i]=new Visitante(fila, filaA,filaB);
            hilos[i]=new Thread(visi[i],"v-"+i);
            hilos[i].start();
        }
    }
}