package TP__FINAL.CarpetaObsCompartidos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.sound.sampled.BooleanControl;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.sql.Time;
import java.util.Random;

public class BarcoPirata {
   private int cont = 0;
    private final int MAX = 20;
    private final Lock lock = new ReentrantLock();
    private final Condition subir = lock.newCondition();
    private final Condition bajar = lock.newCondition();

    private final Lock lockBarco = new ReentrantLock();
    private final Condition zarpar = lockBarco.newCondition();

    private boolean espera = false;
    private boolean partioBarco = false;
    private boolean subioUnPasajero = false;

    public void subirBarco() throws InterruptedException {
        lock.lock();
        try {
            while (cont == MAX || partioBarco) {
                System.out.println(Thread.currentThread().getName() + " espera al barco");
                subir.await();
            }

            subioUnPasajero = true;//subio un pasajero 
            cont++;
            System.out.println(Thread.currentThread().getName() + " se subió al barco (" + cont + "/" + MAX + ")");
            //si subio un pasajero o se lleno notifica para arrancar conteo o viaje
            if (cont == MAX || (subioUnPasajero && cont == 1)) {
                lockBarco.lock();
                try {
                    zarpar.signal();
                } finally {
                    lockBarco.unlock();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void arrancarBarco() throws InterruptedException, TimeoutException {
        lockBarco.lock();
        try {
            Boolean llenoATiempo=false;
             // Espera a que suba al menos un pasajero
            while (!subioUnPasajero || cont < MAX) {
                if (subioUnPasajero) {
                    System.out.println("El barco espera 2 segundos a que se llene...");
                    llenoATiempo = zarpar.await(2, TimeUnit.SECONDS);
                    break; //salimos del while
                } else {
                    zarpar.await(); // Primer pasajero aún no subió
                }
            }
            partioBarco = true;

            if (!llenoATiempo && cont < MAX) {
                System.out.println("-- BARCO INCOMPLETO ESTÁ EN MARCHA -- (" + cont + "/" + MAX + ")");
            } else {
                System.out.println("-- BARCO LLENO ESTÁ EN MARCHA --");
            }

        } finally {
            lockBarco.unlock();
        }
    }

    public void detenerBarco() throws InterruptedException {
        lockBarco.lock();
        try {
            System.out.println("-- FINALIZÓ ATRACCIÓN DEL BARCO --\n");
            espera = true;
            subioUnPasajero = false;
            lock.lock();
            try{
               bajar.signalAll();//permiten que todos bajen
            }finally{
               lock.unlock();
            }
        } finally {
            lockBarco.unlock();
        }
    }

    public void bajarBarco() throws InterruptedException {
        lock.lock();
        try {
            while (!espera) {
                bajar.await();//espera señal para bajar
            }        
            System.out.println(Thread.currentThread().getName() + " se bajó del barco (" + cont + " restantes)");
            cont--;

            if (cont == 0) {//se bajaron todos
                espera = false;
                partioBarco = false;
                System.out.println("--Todos los pasajeros se bajaron. Preparando nuevo viaje--\n");
                subir.signalAll();//notifica que suban nuevos pasajeros
            }
        } finally {
            lock.unlock();
        }
    }
}