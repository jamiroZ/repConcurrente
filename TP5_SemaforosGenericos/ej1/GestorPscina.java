import java.util.concurrent.Semaphore;

public class GestorPscina{
     private int cap = 3;
     private Semaphore capacidad;
     public GestorPscina(){
         capacidad=new Semaphore(cap);
     }
     public void ingresarPiscina(){
         System.out.println(Thread.currentThread().getName()+" quiere ingresar a la piscina ");
         try {
            capacidad.acquire(1);//si hay espacio entra
            System.out.println(Thread.currentThread().getName()+" entro a la piscina ");
         } catch (Exception e) {
            // TODO: handle exception
         }
     }
     public void salirPiscina(){
         System.out.println(Thread.currentThread().getName()+" salio de la piscina ");
         capacidad.release(1);
     }
}