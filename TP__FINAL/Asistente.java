package TP__FINAL;
import TP__FINAL.CarpetaObsCompartidos.FilaTeatro;
import java.util.Random;
public class Asistente implements Runnable{
    private FilaTeatro filaTeatro;
    public Asistente(FilaTeatro filaTeatro){
        this.filaTeatro=filaTeatro;
    }
    public void run(){
        Random r=new Random();
         try {
            while(true){
                  this.filaTeatro.ingresaAsistente();
                  this.filaTeatro.saleAsistente();
                  Thread.sleep(r.nextInt(4000));//se toman un descanzo entre show
            }
          
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
