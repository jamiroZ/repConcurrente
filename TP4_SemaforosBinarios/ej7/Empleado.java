package TP4_SemaforosBinarios.ej7;

public class Empleado implements Runnable {
    private Confiteria espacio;
    private int legajo;
    public Empleado(Confiteria espacio,int legajo){
           this.espacio=espacio;
           this.legajo=legajo;
    }
    public void run(){
         try {
            this.espacio.tomarAsiento();
            this.espacio.dejarAsiento();
         } catch (Exception e) {
            // TODO: handle exception
         }
    }
}
