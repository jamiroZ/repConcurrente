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
        this.grupoVisitantes=new Semaphore(5);//entran de a 5
        this.show=new Semaphore(0);
        this.salidaVisitantes=new Semaphore(0);
        this.salidaAsistentes=new Semaphore(0);
    }
    public void ingresaAsistente(){
        try {
            this.grupoAsistentes.acquire();//intenta ingresar uno de a 6
               contAsistentes++;
               System.out.println(" Asistente "+Thread.currentThread().getName()+" ingreso al teatro ");
               System.out.println(contAsistentes+"-"+cantAsistentes);
               if(contAsistentes >= cantAsistentes){
                      System.out.println(" ");
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
              contAsistentes--;
              System.out.println("s"+contAsistentes);

              if(contAsistentes == 0){
                System.out.println("xxxx ");//espacio
                this.grupoAsistentes.release(cantAsistentes);//sale uno de a 6 
              }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
    }
    public void ingresaVisitante(){
        try {
            contEspera++;//espera
            this.grupoVisitantes.acquire();//ingresan hasta 5 al mismo tiempo
              contEspera--;//paso 
              System.out.println(" Visitante "+Thread.currentThread().getName()+" ingreso al teatro ");
              cont++;
              if(cont % 5 ==0 && cont!=0 && cont!=20 && contEspera!=0){//ingresaron 5 visitantes
                   System.out.println("--ingresa siguiente grupo--");
                   this.grupoVisitantes.release(5);//libera 5 espacios para que ingrese otro grupo
              }
              if(cont==20 || contEspera==0){//si se lleno o no hay mas gente esperando que empieze la funcion
                   System.out.println(" ");//espacio
                   this.show.release();//libera un permiso
              }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void saleVisitante(){
        try {
            this.salidaVisitantes.acquire();//hasta que no termine el show no sale 
              System.out.println(" Visitante "+Thread.currentThread().getName()+" salio del teatro ");
              cont--;
              if(cont==0){//salio el ultimo que ingresen nuevos visitantes
                  System.out.println(" ");//espacio
                  this.grupoAsistentes.release(5);
              }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void inicieLaFuncion(){
        try {
            this.show.acquire(2);//depende de que esten los visitantes y los asistentes osea que estos liberen los 2 permisos
            System.out.println("--COMIENZA EL SHOW--");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void bajarTelon(){
        System.out.println("--FINALIZA EL SHOW--");
        this.salidaVisitantes.release(5);
        this.salidaAsistentes.release(cantAsistentes);
    }
}
