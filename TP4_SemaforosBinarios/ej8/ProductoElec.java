package TP4_SemaforosBinarios.ej8;

import java.util.Random;

public class ProductoElec implements Runnable{
        private ControladorProduccion produccion;
        private String numSerie;
        public ProductoElec( ControladorProduccion produccion,String numSerie){
            this.produccion=produccion;
            this.numSerie=numSerie;
        }
        public void run(){
            Random r=new Random();
              try {
                    produccion.llegaElectrico();
                    Thread.sleep(r.nextInt(1500));
                    produccion.sale();
              } catch (Exception e) {
                // TODO: handle exception
              }
        }

}
