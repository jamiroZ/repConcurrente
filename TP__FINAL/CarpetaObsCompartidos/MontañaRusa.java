package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.Semaphore;
public class MontañaRusa {
    private int CAPACIDAD=5;//capacidad de la montaña rusa
    private int ESPACIO=10;//capacidad de espera 
    private int cont=0;
    private int contEspera=0;
    //semaforos del visitante
    private Semaphore asientos;
    private Semaphore espera;
    private Semaphore visitanteSale;
    private Semaphore exclusion_Espera;
    //semaforo de la montaña rusa
    private Semaphore montaña;
    public MontañaRusa(){
        this.asientos = new Semaphore(this.CAPACIDAD);
        this.espera = new Semaphore(this.ESPACIO);
        this.montaña = new Semaphore(0);
        this.visitanteSale=new Semaphore(0);
        this.exclusion_Espera=new Semaphore(1);
    }
    //PROBLEMA la capacidad de espera de 10 personas bloquea a mas de la capacidad
    public Boolean subirMontaña() throws InterruptedException {
        Boolean sube=true;//asumo que sube a la montaña rusa
        this.exclusion_Espera.acquire();
            if(!this.espera.tryAcquire()){//si no hay espacio de espera
                    System.out.println(Thread.currentThread().getName()+" No hay espacio de espera, va a otra atraccion");
                    sube=false;//no espera 
            } 
            this.contEspera++;
        this.exclusion_Espera.release();

        if(cont>=5 && contEspera < 10 ){          
                    System.out.println(Thread.currentThread().getName()+" espera montaña rusa");
        }
        this.asientos.acquire();//sube a la montaña rusa   
        

        this.exclusion_Espera.acquire();
            
            this.cont++;//ocupa un asiento
            System.out.println(Thread.currentThread().getName()+" subiendo a la montaña rusa");    
            if(this.cont==5){//si la montaña rusa esta llena arranca
                        this.montaña.release();//montaña lista para arrancar
            }
            this.contEspera--;
            this.espera.release();// libera un lugar en la fila de espera   
        this.exclusion_Espera.release();   
        return sube;      
    }
    public void bajarMontaña() throws InterruptedException {
        this.visitanteSale.acquire();//una ves que llega lo toma
        System.out.println(Thread.currentThread().getName()+" bajando de la montaña rusa");
        this.cont--;//bajan TODOS de la montaña rusa
        if(cont==0){
            System.out.println(" ");
            this.asientos.release(CAPACIDAD);//Libera 5 asientos
            this.espera.release(CAPACIDAD);//libera espacio de espera para 5 personas (libera 5 permisos)
        }        
    } 
    public void arrancar()throws InterruptedException{
        this.montaña.acquire();//una ves lleno la montaña rusa arranca
        System.out.println("--montaña rusa arranca--");
    }
    public void parar()throws InterruptedException{
        System.out.println("--finalizo el recorrido la montaña rusa--");   
       this.visitanteSale.release(5);//termino el recorrido entonces libera el semaforo
  
    }
}
