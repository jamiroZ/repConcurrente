package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
public class Tren {
    private BlockingQueue<String> colaTren;//cola de tren con capacidad para 10
    private LinkedBlockingQueue<String> colaEspera=new LinkedBlockingQueue<>();//cola de espera sin limite
    private Semaphore semaforoBajada,semaforoTren;
    private Lock lock=new ReentrantLock();
    private Condition arranque=lock.newCondition();
    private int pasajeros;//contador de pasajeros
    private int max;//capacidad maxima del tren
    private Boolean espera=false;
    public Tren() {
        this.max=10;
        this.pasajeros = 0;//hay 0 pasajeros
        colaTren = new ArrayBlockingQueue<>(max);
        semaforoBajada = new Semaphore(0);
        semaforoTren= new Semaphore(1);
    }
    //metodo que permite subir pasajeros y esperar en la cola
    public void entrarColaDeEspera() throws InterruptedException{
        String visitante="visitante "+Thread.currentThread().getName();   
        colaEspera.put(visitante);//un visitante en espera
        System.out.println(visitante+" en espera ");
        lock.lock();
        try {   
            if(colaTren.remainingCapacity()>0){//si hay espacio
                colaTren.put(visitante);//si hay espacio en el tren sube,sino queda en espera
                pasajeros++;//se sube un pasajero
                System.out.println(visitante+" deja espera y sube al tren ("+pasajeros+"/"+max+")");
                if(pasajeros==1){//si fue el primero arranca el conteo para que el tren se mueva
                    espera=true;
                    arranque.signal();//que arranque la espera de 5 segundos
                }    
                if(pasajeros == max ){//tren lleno arranca                 
                    arranque.signal();//que arranque el tren 
                }  
            }
            colaEspera.take();//sale de la cola de espera
           // System.out.println(" visitantes en espera "+colaEspera.size());

        } finally {
                lock.unlock();
        }
        
    }
    //meoto de arranque del trayecto del tren
    public void arrancarRecorrido()throws InterruptedException{
        lock.lock();
        try {  
            // esperamos hasta que suba uno
            while (pasajeros == 0 || !espera) {
                arranque.await(); // se despierta solo cuando entra el primero
            }
            // subió al menos uno, esperamos a que se llene o pasen 5 segundos
            System.out.println(" Esperando que el tren se llene (5 segundos máx)");
            arranque.await(5, TimeUnit.SECONDS); // espera a que se llene o que se cumpla el tiempo

            System.out.println("Tren arranca con "+pasajeros+" pasajeros.");
        }finally{
            lock.unlock();
        }
    }
    public void terminoRecorrido()throws InterruptedException{
       System.out.println("tren finalizo recorrido");
       espera=false;
       semaforoBajada.release(max);//permite a los visitantes bajar
    }
    public void bajarTren()throws InterruptedException{
        semaforoBajada.acquire();//se baja un pasajero
        System.out.println("visitante "+Thread.currentThread().getName()+" se bajo del tren");
        lock.lock();
        try {
            pasajeros--;//baja del tren
            if(pasajeros==0){//tren vacio
                System.out.println("tren vacio");
                colaTren.clear();//vacio la cola
            }
        } finally{
            lock.unlock();
        }
       
    }
}
    /*private int cont=capacidad;
    private static int capacidad=10;
    private static int tiempoDeEspera=5;
    private Boolean log=false;
    private BlockingQueue <Integer> tren=new ArrayBlockingQueue<>(capacidad);
    private BlockingQueue <Integer> arrancarTren=new ArrayBlockingQueue<>(1);
    private BlockingQueue <Integer> esperaPasajero=new ArrayBlockingQueue<>(capacidad);
    private BlockingQueue <Integer> esperaTren=new ArrayBlockingQueue<>(1);
    public Tren() {
        try {
            arrancarTren.put(1);
            esperaTren.put(1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public  void subirTren() throws InterruptedException {
        tren.put(1);//sube al tren o espera

          System.out.println("visitante "+Thread.currentThread().getName()+" subio al tren ");
          // System.out.println("CONT :"+cont+" SIZE:"+tren.size());
          if(cont==capacidad){//se subio al menos un pasajero que arranque     
              esperaTren.take();//avisa que se prepare el tren
              arrancarTren.take();//arranca el conteo de tiempo de espera (5minutos)
          }else if(cont==0){
              System.out.println("tren lleno");
              System.out.println(" ");
          }
          cont--;//ocupa un espacio 
          // System.out.println("CONT :"+cont+" SIZE:"+tren.size());
         
    }

    public  void arrancarTren() throws InterruptedException {
        esperaTren.put(1);
        //si paso el tiempo de espera del tren(false) ,si se lleno el tren(true)
        log=arrancarTren.offer(1, tiempoDeEspera, TimeUnit.SECONDS);
        if(!log){//paso el tiempo de espera
            for(int i=0; i<cont ; i++){
                  tren.put(1);//rellena espacios para que no suban mas mientras el tren esta en movimiento
            }
            System.out.println("-- EL TREN ARRANCO INCOMPLETO --");
        }else{//se subieron todos
            System.out.println("-- EL TREN ARRANCA COMPLETO-- ");
        }

    }
    public void frenarTren() throws InterruptedException { 
            System.out.println("-- EL TREN FINALIZO EL RECORRIDO --");
            for(int i=0 ;i<capacidad ; i++){
                 esperaPasajero.put(1);//avisa al pasajero que puede bajarse
            }
    }

    public void bajarTren() throws InterruptedException {
        //System.out.println("bajarTren");
        esperaPasajero.take();
       
            System.out.println("visitante "+Thread.currentThread().getName()+" bajo del tren ");
            cont++;//libera un espacio para que suba otro pasajero

            if(cont==capacidad){//se bajaron todos los pasajeros
                System.out.println(" ");//espacio
                for(int i=0; i<cont ; i++){
                    tren.take();//vacia espacios para que suban
                }
            }
    }
}*/
