package TP7_Locks.ej2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class ProyectoSoft {
    private int contLibros=0;
    private int contPortatil=0;

    //lock Y CONDICIONES
    private Lock lock=new ReentrantLock();
     //private Lock portatil=new ReentrantLock();
    private Condition portatilYlibros= lock.newCondition();

    public  ProyectoSoft(int contLibros, int contPortatil) {
        this.contLibros = contLibros;
        this.contPortatil = contPortatil;
    }
    //intenta usar libro y portatil
    public void trabajarProyecto() throws  InterruptedException {

        lock.lock();
        try {
        
            if(contLibros ==0 && contPortatil ==0){
                portatilYlibros.await();
                System.out.println("No hay libros ni portatiles para trabajar ESPERA");
            }
            if(contLibros >0  && contPortatil > 0){
                contLibros--;
                contPortatil--;
                System.out.println("estudiante "+Thread.currentThread().getName()+" tomo un libro y un portatil ");
                System.out.println("trabaja");
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            lock.unlock();
        }
    }
    //libera el libro y portatil
    public void dejarProyecto() throws  InterruptedException {

        lock.lock();
        try {
            System.out.println("estudiante "+Thread.currentThread().getName()+" dejo un libro y un portatil ");
            contLibros++;
            contPortatil++;
            portatilYlibros.signal();
            
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }

}

