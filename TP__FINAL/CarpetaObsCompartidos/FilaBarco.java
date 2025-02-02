package TP__FINAL.CarpetaObsCompartidos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.sampled.BooleanControl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.sql.Time;
import java.util.Random;

public class FilaBarco {
    private int cont=0;
    private int contEspera=0;
    private int MAX=20;
    private Lock lock=new ReentrantLock();
    private Condition llegada= lock.newCondition();
    private Condition salida= lock.newCondition();
    private Boolean finalizo=false;
    private Boolean espera=false;
    private Boolean partioBarco=false;
    public FilaBarco(){}
    
    public void subirBarco() throws InterruptedException {
        
        lock.lock();
        contEspera++;//espera
        try {
            System.out.println(cont+"-"+contEspera);
            while( (contEspera>0 && cont == MAX ) || partioBarco){//se lleno el barco o partio , esperan
                System.out.println(Thread.currentThread().getName()+" espera al barco");
                salida.await();
            }
            contEspera--;
            cont++;//se sube al barco 
            System.out.println(cont+"-"+contEspera);
            System.out.println(Thread.currentThread().getName()+" Se subio al barco");
            if(cont==MAX ){//barco lleno 
                System.out.println(" ");
                finalizo=true;
                llegada.signalAll();//notifica al barco que puede partir
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
          lock.unlock();
        }
    }
    public void arrancarBarco() throws InterruptedException ,TimeoutException{
        //si despues de 5 minutos no se subieron los 20 ya arranca el barco
        lock.lock();
        try {

            boolean llenoATiempo=llegada.await(8, TimeUnit.SECONDS);
            partioBarco=true;
            if(!llenoATiempo){
                System.out.println("--BARCO INCOMPLETO ESTA EN MARCHA--");
            }else{
                System.out.println("--BARCO LLENO ESTA EN MARCHA--");
            }
            
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
            System.out.println(" ");

            finalizo=false;
            espera=true; 
            partioBarco=false;
            salida.signalAll();
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
            cont--;
            System.out.println(Thread.currentThread().getName()+" Se bajo del barco"+cont);
            if(cont == 0){
                espera=false;
                System.out.println(" ");
                
                salida.signalAll();//nofica que ya se bajaron todos
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
             lock.unlock(); 
        }
    }
}
