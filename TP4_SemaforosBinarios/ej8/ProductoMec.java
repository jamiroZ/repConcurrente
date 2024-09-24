package TP4_SemaforosBinarios.ej8;

import java.util.Random;

public class ProductoMec implements Runnable{
    private ControladorProduccion produccion;
        private String numSerie;
        public ProductoMec( ControladorProduccion produccion,String numSerie){
            this.produccion=produccion;
            this.numSerie=numSerie;
        }
        public void run(){
            Random r=new Random();
              try {
                    produccion.llegaMecanico();
                    Thread.sleep(r.nextInt(1000));
                    produccion.sale();
              } catch (Exception e) {
                // TODO: handle exception
              }
        }
}

