package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.Semaphore;
public class FilaMontaña {
    private int CAPACIDAD=5;//capacidad de la montaña rusa
    private int ESPACIO=10;//capacidad de espera 
    private int cont=0;
    //semaforos del visitante
    private Semaphore asientos;
    private Semaphore espera;
    private Semaphore visitanteSale;
    //semaforo de la montaña rusa
    private Semaphore montaña;
    public FilaMontaña(){
        this.asientos = new Semaphore(this.CAPACIDAD);
        this.espera = new Semaphore(this.ESPACIO);
        this.montaña = new Semaphore(0);
        this.visitanteSale=new Semaphore(0);
    }
    public Boolean subirMontaña() throws InterruptedException {
        this.espera.acquire();
           this.asientos.acquire();
           Boolean ret=false;//si no pudo ingresar retorna false y busca otra atraccion   
           
           if(this.cont<5){
               System.out.println(Thread.currentThread().getName()+" subiendo a la montaña rusa");
               ret=true;
               this.cont++;//se sube a la montaña rusa
               if(this.cont==5){//si la montaña rusa esta llena arranca
                  this.montaña.release();//libera un permiso
               }
            }else{
                System.out.println(Thread.currentThread().getName()+"No hay asientos disponibles , va a otra atraccion");
            }
        this.espera.release();//libera un permiso
        return ret;
    }
    public void bajarMontaña() throws InterruptedException {
        this.visitanteSale.acquire();//una ves que llega lo toma
        System.out.println(Thread.currentThread().getName()+" bajando de la montaña rusa");
        this.cont--;//bajan TODOS de la montaña rusa
        if(cont==0){
            this.asientos.release(CAPACIDAD);
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
