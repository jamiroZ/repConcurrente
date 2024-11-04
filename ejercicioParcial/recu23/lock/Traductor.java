package ejercicioParcial.recu23.lock;

import java.util.Random;
public class Traductor  implements Runnable{
     private Serie capitulos;
     
     public Traductor(Serie capitulos){
           this.capitulos=capitulos;  
     }
     public void run(){
          Random r=new Random();
          try {
            //que se filmen n capitulos
            while( true ){
                this.capitulos.iniciarTraduccion();
                Thread.sleep(r.nextInt(3000));//tiempo de traduccion
                this.capitulos.finalizarTraduccion();
            }
           
          } catch (Exception e) {
            // TODO: handle exception
          }
     }
    
}
