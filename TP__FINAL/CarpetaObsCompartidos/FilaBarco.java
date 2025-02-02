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
    private Boolean unPasajero=false;//tiene que haber al menos un pasajero en el barco para que este arranque el conteo 
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
            unPasajero=true;
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
            boolean llenoATiempo=false;
            while(!unPasajero){//si no subio nadie que espere para arrancar el conteo
               llenoATiempo=llegada.await(2, TimeUnit.SECONDS);
            }
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
            espera=true; //
            unPasajero=false;//que espere hasta que se suba alguien y vuelva a contar el tiempo
            partioBarco=false;//ya no esta en marcha
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
