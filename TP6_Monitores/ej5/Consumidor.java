package TP6_Monitores.ej5;

import java.util.Random;

public class Consumidor implements Runnable{
    private Almacen almacen;
    public  Consumidor(Almacen almacen){
          this.almacen=almacen;
    }

    public void run(){
      try {
    
            this.almacen.eliminarProducto();
            Thread.sleep((int) (Math.random() * 3000)); // Esperar un tiempo aleatorio

          
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
}

