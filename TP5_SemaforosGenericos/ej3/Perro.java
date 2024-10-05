package TP5_SemaforosGenericos.ej3;

public class Perro implements Runnable{
    private Comedero objComedor;
    public Perro(Comedero objComedor){
         this.objComedor=objComedor;
    }
    public void run(){
        try {
            Thread.sleep(600);
            this.objComedor.comerPerro();
            Thread.sleep(1000);
            System.out.println("come");
            this.objComedor.salirPerro();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
