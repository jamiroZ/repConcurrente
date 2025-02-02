package TP__FINAL;
import TP__FINAL.CarpetaObsCompartidos.FilaTeatro;
import TP__FINAL.CarpetaObsCompartidos.Parque;
import java.util.Random;
public class Asistente implements Runnable{
    private FilaTeatro filaTeatro;
    private Parque parque;
    public Asistente(FilaTeatro filaTeatro,Parque parque){
        this.filaTeatro=filaTeatro;
        this.parque=parque;
    }
    public void run(){
        Random r=new Random();
         try {
            while(true){
                //si el parque esta abierto que ingrese al teatro del mismo
                while(this.parque.estadoDelParque()){
                  this.filaTeatro.ingresaAsistente();
                  this.filaTeatro.saleAsistente();
                  Thread.sleep(r.nextInt(4000));//se toman un descanzo entre show
                }
                
            }
          
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
