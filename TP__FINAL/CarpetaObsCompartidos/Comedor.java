package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Comedor {
    private int totalMesas;
    private int sillas;
    private Semaphore semaforoMesas;//acceso al comedor y salida
    private CyclicBarrier []barreraMesa;//esperan a los 4 clientes para que puedan comer
    private int[]sillasOcupadas;//contador de asientos ocupados por mesa
    private final Object[] locksMesas; // Para sincronizar acceso por mesa
   
    public Comedor() {
        totalMesas = 5;// maximo de mesas
        sillas = 4;// constante,cantidad personas por mesa
        semaforoMesas = new Semaphore(totalMesas * sillas);
        sillasOcupadas = new int[totalMesas];//cuantos se van centando por mesa
        barreraMesa=new CyclicBarrier[totalMesas];
        locksMesas = new Object[totalMesas];
        for(int i=0; i< totalMesas; i++) {
          barreraMesa [i]= new CyclicBarrier(sillas, () -> System.out.println("La mesa se llena, todos empiezan a comer"));
          locksMesas[i]=new Object();//lock por mesa
        }
    }

    public boolean entrarComedor() {
        boolean resultado = semaforoMesas.tryAcquire();
        if (resultado) {
            System.out.println("visitante "+Thread.currentThread().getName() + " entró al comedor");
        } else {
            System.out.println("visitante "+Thread.currentThread().getName() + " no pudo entrar al comedor porque estaba lleno");
        }
        return resultado;
    }

    public int sentarse() throws InterruptedException {
       int mesa=-1;
       // Buscar mesa disponible
        for (int i = 0; i < totalMesas; i++) {
            synchronized (locksMesas[i]) {
                if (sillasOcupadas[i] < sillas) {
                    sillasOcupadas[i]++;
                    mesa = i;
                    break;
                }
            }
        }
        try {
            if(mesa== -1){//no hay mesa con espacio
                System.out.println("visitante "+Thread.currentThread().getName() + " no pudo sentarse ");
                return -1;
            }
            System.out.println("visitante "+Thread.currentThread().getName() + " se sentó en la mesa "+mesa);
            barreraMesa[mesa].await();//hasta que la mesa no este llena no comen
        }catch (Exception e) {
            System.out.println("Error en la sincronización de la mesa: " + e.getMessage());
        }
        return mesa;
    }

    public void salir(int mesa) {
        synchronized(locksMesas[mesa]){
            sillasOcupadas[mesa]--;
            System.out.println("visitante "+Thread.currentThread().getName() + " salio del comedor");
            if(sillasOcupadas[mesa]==0){//la mesa se vacio
                semaforoMesas.release(sillas);//deja entrar a 4 al comedor a buscar mesa
                System.out.println("La mesa"+mesa+" esta libre");
            } 
        }     
    }
}