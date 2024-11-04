package ejercicioParcial.parcial22.ejercicio2;

import java.util.LinkedList;
import java.util.Queue;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private Queue<Integer> cola=new LinkedList<>();
    
    private Lock lock=new ReentrantLock();
    //si esta insertando espera el otro metodo
    private Condition colaVacia = lock.newCondition();
    public Buffer(){
       
  
    }
    public void insertar(int elem) throws InterruptedException{
        this.lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"inserto "+elem);
             cola.offer(elem);//añade elemento a la cola
             System.out.println("elementos en la cola :"+cola.size());
             if(this.cola.size()>0){
                 this.colaVacia.signal();
             }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            this.lock.unlock();
        }

    }
    public void extraer() throws InterruptedException{
        this.lock.lock();
        try {
            if(this.cola.size()==0){//cola vacia no extrae
                System.out.println("cola vacia");
                this.colaVacia.await();//duerme el hilo hasta que haya elementos
            }
            System.out.println(Thread.currentThread().getName()+"extrae");
            cola.poll();//elimina elemento de la cola
            System.out.println("elementos en la cola :"+cola.size());
        } catch (Exception e) {
           // TODO: handle exception
        }finally{
            this.lock.unlock();
        }
        
    }
}
