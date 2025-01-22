package TP__FINAL.CarpetaObsCompartidos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class FilaBarco {
    private int cont=0;
    private int contEspera=0;
    private int MAX=20;
    private Lock lock=new ReentrantLock();
    private Condition salida= lock.newCondition();
    private Condition llegada= lock.newCondition();
    private Boolean finalizo=false;
    private Boolean espera=false;
    public FilaBarco(){}
    
    public void subirBarco() throws InterruptedException {
        lock.lock();
        try {
            contEspera++;
            while(contEspera > MAX){//se subieron 20 , esperan
                System.out.println(Thread.currentThread().getName()+" espera al barco");
                salida.await();
            }
            cont++;//se sube
            System.out.println(Thread.currentThread().getName()+" Se subio al barco");
            if(cont==MAX){
                System.out.println(cont);
                finalizo=true;

                llegada.signalAll();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
          lock.unlock();
        }
    }
      
    public void bajarBarco() throws InterruptedException {
        lock.lock();
        try {
            while(!espera){
                salida.await();
            }
            contEspera--;
            cont--;
            System.out.println(Thread.currentThread().getName()+" Se bajo del barco");
            if(cont==0){
                espera=false;
                salida.signalAll();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
             lock.unlock(); 
        }
    }

      
    public void arrancarBarco() throws InterruptedException {

        lock.lock();
        try {
            while(!finalizo){//si no se llenaron los 20 cupos espera a arrancar
               llegada.await();
            } 
            System.out.println("--EL BARCO ESTA EN MARCHA--");
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
          lock.unlock();
        }
    }

      
    public void  detenerBarco() throws InterruptedException {
        lock.lock();
        try {
            
            System.out.println("--FINALIZO ATRACCION DEL BARCO--");
            finalizo=false;
            espera=true;
            salida.signalAll();
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }


}
