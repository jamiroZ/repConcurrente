package TP4_SemaforosBinarios.ej3;

public class P1 implements Runnable{
      private Compartido obj;
      public P1(Compartido obj){
          this.obj=obj;
      }
      public void run(){
           try {
              obj.getSem1_3().acquire();
              System.out.println("Inicia proceso 1");
           } catch (InterruptedException e) {
                e.printStackTrace(); // TODO: handle exception
           }
           dormir();
           obj.getSem3_2().release();
      }
    public static void dormir(){
        try {
            Thread.sleep(599);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
