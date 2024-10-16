package TP6_Monitores.ej2;

public class SalaDeEstudio {
    private int mesas;
    private int mesasOcupadas;
    public SalaDeEstudio( int mesas){
         this.mesas=mesas;
         this.mesasOcupadas=mesas;
    }
    public synchronized void tomarMesa(){
        try {
            while( mesasOcupadas ==0){//TODAS LAS MESAS OCUPADAS
                System.out.println(mesasOcupadas+" alumno "+Thread.currentThread().getName()+" espera ");
                this.wait();
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
        mesasOcupadas--;//UNA MESA DISPONIBLE MENOS
        System.out.println(mesasOcupadas+ " alumno "+Thread.currentThread().getName()+" toma la mesa");
       
   
    }
    public synchronized void dejarMesa(){
        mesasOcupadas++;//UNA MESA DISPONIBLE MAS
        System.out.println(mesasOcupadas+" alumno "+Thread.currentThread().getName()+" deja la mesa");
        
        this.notify();
    }
}
