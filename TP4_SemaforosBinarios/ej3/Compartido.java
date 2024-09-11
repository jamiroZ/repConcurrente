package TP4_SemaforosBinarios.ej3;

import java.util.concurrent.Semaphore;

public class Compartido {
    private Semaphore sem1_3;
    private Semaphore sem3_2;
    private Semaphore sem2_1;
    public Compartido(){
        this.sem1_3=new Semaphore(1);
        this.sem3_2=new Semaphore(0);
        this.sem2_1=new Semaphore(0);
    }
    public Semaphore getSem1_3(){
        return this.sem1_3;
    }
    public Semaphore getSem3_2(){
        return this.sem3_2;
    }
    public Semaphore getSem2_1(){
        return this.sem2_1;
    }
    //
}
