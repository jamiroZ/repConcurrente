package TP__FINAL.CarpetaObsCompartidos;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class FilaTeatro {
    //
    private boolean hayObra=false;
    private int capacidad=20;
    private int cont=0;
    private int contAsistentes=0;
    private int contEspera=0;
    private int cantAsistentes=6;
    private Semaphore grupoAsistentes;
    private Semaphore grupoVisitantes;
    private Semaphore salidaVisitantes;
    private Semaphore salidaAsistentes;
    private Semaphore show;
    public FilaTeatro(){
        this.grupoAsistentes=new Semaphore(1);
        this.grupoVisitantes=new Semaphore(0);//entran de a 5
        this.show=new Semaphore(0);
        this.salidaVisitantes=new Semaphore(0);
        this.salidaAsistentes=new Semaphore(0);
    }
    public void ingresaAsistente(){
        try {
            this.grupoAsistentes.acquire();//intenta ingresar uno de a 6
               if(contAsistentes==0){
                    System.out.println(" ");
               }
               contAsistentes++;
               
               System.out.println(" Asistente "+Thread.currentThread().getName()+" ingreso al teatro ");
               if(contAsistentes >= cantAsistentes){
                      System.out.println(" ");
                      this.grupoVisitantes.release(5);
                      this.show.release();//libera un permiso
               }else{
                      this.grupoAsistentes.release();//ingreso uno de a 6 
               }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public void saleAsistente(){
        try {
            this.salidaAsistentes.acquire();
              
              System.out.println(" Asistente "+Thread.currentThread().getName()+" salio del teatro ");
              //System.out.println("s"+contAsistentes);
              contAsistentes--;
              if(contAsistentes == 0){
                System.out.println(" ");//espacio
                this.grupoAsistentes.release(1);//sale uno de a 6
                this.salidaVisitantes.release(1);
              }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
    }
    public void ingresaVisitante(){
        try {
            this.grupoVisitantes.acquire();//ingresan hasta 5 al mismo tiempo 
              System.out.println(" Visitante "+Thread.currentThread().getName()+" ingreso al teatro "+cont);
              cont++;

              if( ( cont % 5 == 0 && (cont > 0  && cont< 20 ))){//ingresaron 5 visitantes
                   System.out.println("--ingresa siguiente grupo--");
                   this.grupoVisitantes.release(5);//libera 5 espacios para que ingrese otro grupo
              }else if(cont==20){//si se lleno o no hay mas gente esperando que empieze la funcion
                   //System.out.println(" ccc"+contEspera);//espacio
                   this.show.release();//libera un permiso
              }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void saleVisitante(){
        try {
             this.salidaVisitantes.acquire(2);//hasta que no termine el show no sale 
              System.out.println(" Visitante "+Thread.currentThread().getName()+" salio del teatro ");
              cont--;
              if(cont==0){//salio el ultimo que ingresen nuevos visitantes
                 System.out.println(" ");
              }else{
                 this.salidaVisitantes.release(2);
              }
              
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void inicieLaFuncion(){
        try {
            this.show.acquire(2);//depende de que esten los visitantes y los asistentes osea que estos liberen los 2 permisos
            System.out.println("--COMIENZA EL SHOW--");
            System.out.println(" ");//espacio
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void bajarTelon(){
        System.out.println("--FINALIZA EL SHOW--");
        System.out.println(" ");
        this.salidaVisitantes.release(1);
        this.salidaAsistentes.release(cantAsistentes);
    }
}
