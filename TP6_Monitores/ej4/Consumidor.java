package TP6_Monitores.ej4;
import java.util.Random;

public class Consumidor implements Runnable{
    private Almacen almacen;
    
    public  Consumidor(Almacen almacen){
          this.almacen=almacen;
    }

    public void run(){
      try {
        while(true){
            this.almacen.eliminarProducto();
            Thread.sleep((int) (Math.random() * 1000)); // Esperar un tiempo aleatorio
        }
          
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
}
