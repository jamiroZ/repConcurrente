package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.Semaphore;
public class MontañaRusa {
    private int CAPACIDAD=5;//capacidad de la montaña rusa
    private int ESPACIO=10;//capacidad de espera 
    private int cont=0;//contador de visitantes en la montaña rusa
    private int contEspera=0;//contador de espera
    //semaforos 
    private Semaphore asientos;//
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
    //
    public Boolean subirMontaña() throws InterruptedException {
        boolean adquirioEspera = this.espera.tryAcquire(); // intenta entrar a la cola de espera
        if (!adquirioEspera) {
            synchronized(System.out){
             System.out.println(Thread.currentThread().getName() + " No hay espacio de espera, va a otra atraccion (cola llena)");
            }
            return false;
        }
       
        this.exclusion_Espera.acquire();
        try {       

            contEspera++; //ahora espera, aumenta contador de espera
            
            System.out.println("visitante " + Thread.currentThread().getName() + " espera montaña rusa (" + contEspera + "/" + 10 + ")");
        } finally {
            this.exclusion_Espera.release();
        }
       
        this.asientos.acquire(); // intenta adquirir el asiento
        
        this.exclusion_Espera.acquire();//
        try {  
            cont++; // ocupa asiento
            System.out.println("visitante " + Thread.currentThread().getName() + " subiendo a la montaña rusa (" + cont + "/" + 5 + ")");
             
            if (cont == 5) {
                contEspera=contEspera-5;//libera 5 personas de la cola de espera
                this.montaña.release(); // ya están todos, puede arrancar
                this.espera.release(5); // libera el permiso de espera para 5(puede esperar otro mas)
            }
        } finally {
            this.exclusion_Espera.release();
        }
        return true;
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
