package TP__FINAL.CarpetaAtracciones;
import TP__FINAL.CarpetaObsCompartidos.FilaTren;
import TP__FINAL.CarpetaObsCompartidos.Parque;
import java.util.Random;
public class Trenes implements Runnable{
    private FilaTren filaT;
    private Parque parque;
    public Trenes(FilaTren filaT,Parque parque){
            this.filaT=filaT;
            this.parque=parque;
    }
    public void run(){
        try {
            Thread.sleep(4000);
            Random r=new Random();
            while(true){
                while(this.parque.estadoDelParque()){
                  filaT.arrancarTren();
                  Thread.sleep(r.nextInt(8000));
                  filaT.frenarTren();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
