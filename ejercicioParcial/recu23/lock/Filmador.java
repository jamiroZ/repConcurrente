package ejercicioParcial.recu23.lock;
import java.util.Random;

public class Filmador implements Runnable {
    private Serie capitulos;
    private int cantCap=0;
    private int cantFilmados=0;
    public Filmador(Serie capitulos,int cantCap){
          this.capitulos=capitulos;
          this.cantCap=cantCap;
    }
    public void run(){
         Random r=new Random();
         try {
           //que se filmen n capitulos
           while( this.cantFilmados <=this.cantCap){
               
               Thread.sleep(r.nextInt(3000));//tiempo de filmacion
               this.capitulos.filmarCap();
               this.cantFilmados++;
           }
           Thread.sleep(r.nextInt(3000));//tiempo de filmacion
         } catch (Exception e) {
           // TODO: handle exception
         }
    }
}
