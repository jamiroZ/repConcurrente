package TP8.ej4;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaDeEspera {
    private int camillas=4;//camillas para donar sangre
    private int revistas=9;//nueve revistas en la sala de espera
    private Lock lock=new ReentrantLock();
    private Condition hayCamillas=lock.newCondition();
    public SalaDeEspera(){
              
    }
    public void  entrarSala() throws InterruptedException {
        lock.lock();
        try {
            if(this.camillas==0){//no hay camillas que espere en la sala
                System.out.println(Thread.currentThread().getName()+" espera en la sala");
                while(this.revistas==0){
                    System.out.println(Thread.currentThread().getName()+" mira la tv");
                    this.wait();//espera a ver si puede agarrar una revista
                }
                this.revistas--;//agarrar una revista
                System.out.println(Thread.currentThread().getName()+" agarró una revista");

                this.hayCamillas.await();//espera a una camilla libre mientras lee
                this.revistas++;//si paso a la camilla deja la revista

            }
            System.out.println(Thread.currentThread().getName()+" pasa a la camilla");
            this.camillas--;//ocupa una camilla
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }

    }
    public void donarSangre()  throws InterruptedException {
         lock.lock();
         try {
            
             System.out.println(Thread.currentThread().getName()+" libera una camilla"+camillas);
             
             this.camillas++;   
             this.hayCamillas.signal();
                 
         } catch (Exception e) {
           // TODO: handle exception
         }finally{
          lock.unlock();
         }
    }

}
