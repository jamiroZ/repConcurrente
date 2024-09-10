package TP4_SemaforosBinarios.ej2;

import java.util.concurrent.Semaphore;

public class Compartido {
    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;
    private Semaphore sem4;
    public Compartido(int s1,int s2,int s3, int s4){
         this.sem1=new Semaphore(s1);
         this.sem2=new Semaphore(s2);
         this.sem3=new Semaphore(s3);
         this.sem4=new Semaphore(s4);
    }
    public void adquirirS1(){
        
        try {
             System.out.println(Thread.currentThread().getName()+" adquirio el semaforo 1");
             this.sem1.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }

       
    }
    public void liberarS1(){
         this.sem1.release();
    }
    public void adquirirS2(){
        try {
            System.out.println(Thread.currentThread().getName()+" adquirio el semaforo 2");
            this.sem2.acquire();
        } catch (Exception e) {
           // TODO: handle exception
        }

    }
    public void liberarS2(){
        this.sem2.release();
    }
    public void adquirirS3(){
        try {
            System.out.println(Thread.currentThread().getName()+" adquirio el semaforo 3");
            this.sem3.acquire();
        } catch (Exception e) {
           // TODO: handle exception
        }

    }
    public void liberarS3(){
        this.sem3.release();
    }
    public void adquirirS4(){
        try {
            System.out.println(Thread.currentThread().getName()+" adquirio el semaforo 4");
            this.sem4.acquire();
       } catch (Exception e) {
           // TODO: handle exception
       }

    }
    public void liberarS4(){
        this.sem4.release();
    }

}
