package TP__FINAL.CarpetaAtracciones;
import TP__FINAL.CarpetaObsCompartidos.FilaTren;
import java.util.Random;
public class Trenes implements Runnable{
    private FilaTren filaT;
    public Trenes(FilaTren filaT){
            this.filaT=filaT;
    }
    public void run(){
        try {
            Random r=new Random();
            while(true){
                filaT.arrancarTren();
                Thread.sleep(r.nextInt(8000)+2000);
                filaT.frenarTren();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
