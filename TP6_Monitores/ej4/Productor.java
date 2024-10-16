package TP6_Monitores.ej4;
import java.util.Random;

public class Productor implements Runnable{
    private Almacen almacen;
    
    public  Productor(Almacen almacen){
          this.almacen=almacen;
    }
   
    public void run(){
      Random r=new Random();
      try {
        while(true){
            this.almacen.agregarProducto();
            Thread.sleep((int) (Math.random() * 1000)); // Esperar un tiempo aleatorio
        }
        
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
}
