package TP__FINAL.CarpetaAtracciones;
import TP__FINAL.CarpetaObsCompartidos.Tren;
import TP__FINAL.CarpetaObsCompartidos.Parque;
import java.util.Random;
public class HiloTrenes implements Runnable{
    private Tren filaT;
    //private Parque parque;
    public HiloTrenes(Tren filaT,Parque parque){
            this.filaT=filaT;
            //this.parque=parque;
    }
    public void run(){
        try {
            Thread.sleep(4000);
            Random r=new Random();
            while(true){
                //while(this.parque.estadoDelParque()){
                  this.filaT.arrancarRecorrido();
                  Thread.sleep(r.nextInt(8000));
                  this.filaT.terminoRecorrido();
                //}
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
