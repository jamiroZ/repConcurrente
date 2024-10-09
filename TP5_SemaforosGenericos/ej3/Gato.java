package TP5_SemaforosGenericos.ej3;

public class Gato implements Runnable {
     private Comedero objComedor;
      public Gato(Comedero objComedor){
           this.objComedor=objComedor;
      }
      public void run(){
            try {
                Thread.sleep( 100);
                this.objComedor.comerGato();
                Thread.sleep(1000);
                System.out.println("come");
                this.objComedor.salirGato();
            } catch (Exception e) {
                // TODO: handle exception
            }
            
      }
}
