package TP3_Sincronizacion.ej3;

public class Hamster extends Thread{
    private Jaula jaula;
    public Hamster(Jaula jaula){
        this.jaula=jaula;
    }
    public void run(){
         this.jaula.entrarJaula();
    }
}
