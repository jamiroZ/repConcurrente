package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilaRealidadVirtual {
    private int capacidad=8;
    private int cont=0;
    private int cantVR=0;
    private int cantManoplas=0;
    private int cantBases=0;
    private int cantSalieron=0;
    private int cantEsp=0;
    private Boolean descanzo=false;
    private Boolean finalizo=false;
    private Boolean iniciar=false;
    private Lock lock = new ReentrantLock();

    private Condition condEntrada= lock.newCondition();
    private Condition condReposicion= lock.newCondition();
    private Condition condActividad= lock.newCondition();
    public FilaRealidadVirtual() {
        //capacidad de 8 personas actualmente
         this.cantBases=capacidad;
         this.cantManoplas=2*capacidad;
         this.cantVR=capacidad;
    }
    public void entrarRV() throws InterruptedException {
        lock.lock();
        try {
            //si falta algo para tener el equipo completo espera
            cantEsp++;
            while(this.cantBases==0 || this.cantManoplas<2 || this.cantVR==0) {
                 System.out.println(Thread.currentThread().getName()+" espera entrar a la sala de realidad virtual");
                 this.condEntrada.await();
            }
            cantEsp--;
            //obtiene el equipo completo para entrar a la sala de realidad virtual
            this.cantBases--;
            this.cantManoplas-=2;
            this.cantVR--;
            this.cont++;
            System.out.println(Thread.currentThread().getName()+" ha entrado a la sala de realidad virtual");
            if(cont==capacidad || cantEsp==0){
                this.iniciar=true;
               System.out.println("");
               this.condActividad.signalAll();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void salirRV() throws InterruptedException {
        lock.lock();
        try {
            while(!finalizo){//hasta que no finalize la actividad espera
                this.condEntrada.await();
            }
            System.out.println(Thread.currentThread().getName()+" ha salido de la sala de realidad virtual");
            cantSalieron++;
            if(cantSalieron==capacidad){//salieron los 8 repone
                this.descanzo=true;
                System.out.println("");
                this.condReposicion.signal();//avisa al repositor que reponga 
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void reponerEquipo() throws InterruptedException {
        lock.lock();
        try {
            while(!descanzo){//hasta que no termine la actividad no repone equipo
                this.condReposicion.await();
            }
            System.out.println("");
            System.out.println("--OPERADOR REPONE EL EQUIPO--");
            System.out.println("");
            //repone el equipo
            this.cantBases=capacidad;
            this.cantVR=capacidad;
            this.cantManoplas=capacidad*2;
            //entrega el equipo
            this.descanzo=false;
            this.finalizo=false;
            cantSalieron=0;
            cont=0;
            this.condEntrada.signalAll();//entrega el equipo a los visitantes
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void inicieActividad() throws InterruptedException {
        lock.lock();
        try {
            while( !this.iniciar){//hasta que no termine la actividad o no haya capacidad
                this.condActividad.await();
            }
            System.out.println("");
            System.out.println("--INICIA REALIDAD VIRTUAL--");
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }
    public void finalizoActividad()throws InterruptedException {
        lock.lock();
        try {

            System.out.println("--FINALIZA REALIDAD VIRTUAL--");
            System.out.println("");
            this.finalizo=true;
            this.iniciar=false;
            this.condEntrada.signalAll();//
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlock();
        }
    }

}
