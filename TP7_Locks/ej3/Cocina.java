package TP7_Locks.ej3;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Cocina {
    private int cantCarne;
    private int cantPasta;
    private int cantVerdura;
    private Lock lock= new ReentrantLock();
    private Condition hayCarne = lock.newCondition();
    private Condition hayPasta = lock.newCondition();
    private Condition hayVerdura = lock.newCondition();
    public Cocina(int cantCarne, int cantPasta, int cantVerdura){
         this.cantCarne=cantCarne;
         this.cantPasta=cantPasta;
         this.cantVerdura=cantVerdura;
    }
    public void cocinarCarne(){
         lock.lock();
         try {
             //
             if(this.cantCarne==0){//no hay carne 
                  System.out.println( Thread.currentThread().getName()+" no hay carne");
                  this.hayCarne.await();
             }
             System.out.println( Thread.currentThread().getName()+" cocina carne");
             this.cantCarne--;//toma un  trozo de carne
         } catch (Exception e) {
            // TODO: handle exception
         }finally{
             lock.unlock();
         }
    }
    public void cocinarVerdura(){
        lock.lock();
        try {
            if(this.cantVerdura==0){//no hay verdura
                System.out.println( Thread.currentThread().getName()+" no hay verdura");
                 this.hayVerdura.await();
            }
            System.out.println( Thread.currentThread().getName()+" cocina verduras");
            this.cantVerdura--;
        } catch (Exception e) {
           // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void cocinarPasta(){
        lock.lock();
        try {
            if(this.cantPasta==0){
                System.out.println( Thread.currentThread().getName()+" no hay pasta");
                this.hayPasta.await();
            }
            System.out.println( Thread.currentThread().getName()+" cocina pasta");
            this.cantPasta--;
        } catch (Exception e) {
           // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void reponerCarne(){
        lock.lock();
        try {
            this.cantCarne++;//repone carga
            System.out.println( Thread.currentThread().getName()+" repone carne");
            this.hayCarne.signalAll();
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void reponerVerdura(){
        lock.lock();
        try {
            this.cantVerdura++;//repone verdura
            System.out.println( Thread.currentThread().getName()+" repone verdura");
            this.hayVerdura.signalAll();
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void reponerPasta(){
        lock.lock();
        try {
            this.cantPasta++;//repone pastas
            System.out.println( Thread.currentThread().getName()+" repone pasta");
            this.hayPasta.signalAll();
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
}
