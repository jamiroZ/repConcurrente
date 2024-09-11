package TP4_SemaforosBinarios.ej3;

public class P3 implements Runnable{
    private Compartido obj;
      public P3(Compartido obj){
          this.obj=obj;
      }
      public void run(){
          try {
             obj.getSem3_2().acquire();
             System.out.println("Inicia proceso 3");
          } catch (Exception e) {
            // TODO: handle exception
          }
          dormir();
          obj.getSem2_1().release();
      }
      public static void dormir(){
        try {
            Thread.sleep(599);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
      
}
