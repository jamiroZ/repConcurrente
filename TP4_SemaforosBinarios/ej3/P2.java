package TP4_SemaforosBinarios.ej3;

public class P2 implements Runnable{
    private Compartido obj;
      public P2(Compartido obj){
          this.obj=obj;
      }
      public void run(){
           try {
                obj.getSem2_1().acquire();
                System.out.println("Inicia proceso 2");
           } catch (Exception e) {
            // TODO: handle exception
           }
           dormir();
           obj.getSem1_3().release();
      }
      public static void dormir(){
        try {
            Thread.sleep(599);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
