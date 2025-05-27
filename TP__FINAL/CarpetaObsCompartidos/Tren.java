package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
public class Tren {
    private BlockingQueue<String> colaTren;
    private int pasajeros, max, minutos;
    private Semaphore semaforoTren, semaforoBajada;
    boolean trenListo;

    public Tren(int capacidad) {
        colaTren = new ArrayBlockingQueue<>(capacidad);
        this.pasajeros = 0;
        max = capacidad;
        minutos = 0;
        semaforoTren = new Semaphore(1);
        semaforoBajada = new Semaphore(0);
        trenListo = false;
    }

    public int getMinutos() {
        return minutos;
    }

    public void reiniciarReloj() {
        minutos = 0;
    }

    public void incrementar() {
        minutos++;
    }

    public void esperarTren() throws InterruptedException {
        colaTren.put("Agrega nuevo visitante");
        System.out.println(Thread.currentThread().getName() + " esta esperando el tren");
    }

    public void abordar() throws InterruptedException {
        if (!vacia() && pasajeros <= max) {
            // mientras que haya gente en la cola la sube al tren
            // con esa condicion evito que el hilo Maquinista quede en deadlock
            semaforoTren.acquire();
            colaTren.take();
            pasajeros++;
            System.out.println("Pasajero subio al tren.pasajeros: " + pasajeros);
            semaforoTren.release();
        }
        if (pasajeros == max) {
            hacerRecorrido();
        }
    }

    public void hacerRecorrido() throws InterruptedException {
        semaforoTren.acquire();
        System.out.println("El tren parte de la estacion");
        Thread.sleep(4500);
        // termina el recorrido.
        reiniciarReloj();// reinicia el cronometro de 5 minutos
        System.out.println("Finaliza el recorrido");
        semaforoTren.release();// libera el tren
        semaforoBajada.release();
    }

    public void bajarTren() throws InterruptedException {
        semaforoBajada.acquire();
        System.out.println(Thread.currentThread().getName() + " bajo del tren");
        pasajeros--;
        if (pasajeros > 0) {
            semaforoBajada.release();// aseguro que el ultimo pasajero no deje 1 permiso liberado de mas
        }
    }

    public boolean trenLleno() {
        return pasajeros >= max;
    }

    public boolean vacia() {
        return colaTren.isEmpty();
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
