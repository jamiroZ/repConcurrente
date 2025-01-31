package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
public class FilaTren {
    private int cont=capacidad;
    private static int capacidad=10;
    private static int tiempoDeEspera=5;
    private Boolean log=false;
    private BlockingQueue <Integer> tren=new ArrayBlockingQueue<>(capacidad);
    private BlockingQueue <Integer> arrancarTren=new ArrayBlockingQueue<>(1);
    private BlockingQueue <Integer> esperaPasajero=new ArrayBlockingQueue<>(capacidad);
    private BlockingQueue <Integer> esperaTren=new ArrayBlockingQueue<>(1);
    public FilaTren() {
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
              esperaTren.take();//avSystem.out.println("bloqueo");isa que se prepare el tren
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
       
            System.out.println("visitante "+Thread.currentThread().getName()+" bajo del tren "+cont);
            cont++;//libera un espacio para que suba otro pasajero

            if(cont==capacidad){//se bajaron todos los pasajeros
                System.out.println(" ");//espacio
                for(int i=0; i<cont ; i++){
                    tren.take();//vacia espacios para que suban
                }
            }
    }
}
