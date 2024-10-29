package TP8.ej4;
import java.util.Random;
public class Persona implements Runnable{
      private SalaDeEspera sala;
      public Persona( SalaDeEspera sala){
          this.sala=sala;
      }
      public void run(){
          Random r=new Random();
          try {
              this.sala.entrarSala();
              Thread.sleep(r.nextInt(2000)+1000);
              this.sala.donarSangre();
          } catch (Exception e) {
            // TODO: handle exception
          }
      }
}
