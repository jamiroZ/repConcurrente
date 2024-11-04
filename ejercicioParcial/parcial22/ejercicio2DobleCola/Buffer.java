package ejercicioParcial.parcial22.ejercicio2DobleCola;

import java.util.LinkedList;
import java.util.Queue;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private LinkedList<Integer> colaExt=new LinkedList<>();
    private LinkedList<Integer> colaIns=new LinkedList<>();

    private Lock lock=new ReentrantLock();

    private Condition colaVacia = lock.newCondition();
    public Buffer(){

    }
    public void insertar(int elem) throws InterruptedException{
        this.lock.lock();
        try {
             
             colaIns.offer(elem);//añade elemento a la cola
             System.out.println(Thread.currentThread().getName()+" inserto "+elem+" elementos en la cola :"+colaIns.size());
             this.colaVacia.signal();//avisa que la cola Inst ya no esta vacia
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            this.lock.unlock();
        }

    }
    public void extraer() throws InterruptedException{
        this.lock.lock();
        try {
            while(this.colaExt.size()==0){//cola vacia no extrae
                if(this.colaIns.size() > 0){//si la cola Insercio No esta vacia que oscile
                     this.oscilar();
                }else{
                     this.colaVacia.await();
                }
            }
            colaExt.poll();//elimina elemento de la cola
            System.out.println(Thread.currentThread().getName()+" extrae elementos en la cola :"+colaExt.size());
           
           
        } catch (Exception e) {
           // TODO: handle exception
        }finally{
            this.lock.unlock();
        }
    }
    public void  oscilar() throws InterruptedException{
        LinkedList<Integer> aux= this.colaExt;//clonamos la cola Extraccion
        this.colaExt=colaIns;//cambiamos la cola de extraccion por la insercion
        this.colaIns=aux;//cambiamos la cola de insercion por la extraccion
        System.out.println("----osilacion hecha----");
    }

}

