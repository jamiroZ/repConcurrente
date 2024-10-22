package TP7_Locks.ej1;
import java.util.Random;

public class Persona implements Runnable{
    private boolean flag;//si es jubilado 'true'
    private Sala museo;
    public Persona( Sala museo,boolean flag){
        this.museo=museo;
        this.flag=flag;
    
    }
    public void run(){
      Random r=new Random();
       try {
            if(flag){
               this.museo.entrarSalaJubilado();
            }else{
                this.museo.entrarSala();
            }
            Thread.sleep(r.nextInt(4000)+1000);
            this.museo.salirSala();
            
       } catch (Exception e) {
        // TODO: handle exception
       }
    }
}
