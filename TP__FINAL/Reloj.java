package TP__FINAL;

import java.util.concurrent.atomic.AtomicInteger;
import TP__FINAL.CarpetaObsCompartidos.Parque;

/*el complejo abre de 9am a 18pm
 * las actividades cierran a las 19pm 
 * y a las 23hs no debe haber nadie en el parque
*/
public class Reloj implements Runnable {
    private static final int apertura=9;//abre a a las 9am
    private static final int cierre=18;//cierra sus puertas a las 18pm
    private static final int nuevoDia=24;//pasaron al siguiente dia
    private final AtomicInteger horaActual;
    private Parque parque;
    public Reloj(AtomicInteger horaActual, Parque parque) {
             this.horaActual = horaActual;
             this.parque = parque; 

    }
    public AtomicInteger horaActual(){
        return horaActual;
    }
    public void run(){
        while(true){
            try {
                Thread.sleep(8000);
                horaActual.addAndGet(1);
                System.out.println("hora actual: "+horaActual);
                switch (horaActual.get()){
                    case apertura:{
                        parque.abrirParque();
                        Thread.sleep(1000);
                        break;
                    }
                    case cierre:{

                       parque.cerrarParque();
                       Thread.sleep(1000);
                       break;
                    }
                    case nuevoDia:{
                       horaActual.set(0);
                       System.out.println("");
                       System.out.println("---------------------");
                       System.out.println("comienza un nuevo dia");
                       System.out.println("---------------------");
                       System.out.println("");
                       break;
                    }
                    default:
                        break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error en Reloj.run: " + ex.getMessage());
            }
        }
    }
}
