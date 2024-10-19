package TP6_Monitores.ej5;

import java.util.Random;

public class Productor implements Runnable{
    private Almacen almacen;
    
    public  Productor(Almacen almacen){
          this.almacen=almacen;
    }
   
    public void run(){

      try {

            this.almacen.agregarProducto();
            Thread.sleep((int) (Math.random() * 2000)); // Esperar un tiempo aleatorio
          
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
}
