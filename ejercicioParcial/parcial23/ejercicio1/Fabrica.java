package ejercicioParcial.parcial23.ejercicio1;

import java.util.concurrent.Semaphore;

public class Fabrica {
    private int caja;//cantidad de botellas en cada caja 10
    private int botellasVino=0;
    private int botellasAgua=0;
    private int cantCajas=0;//cada caja tiene 10lts a los 100lts se despachan del almacen

    private Semaphore hayBotellaAgua;
    private Semaphore hayBotellaVino;
    private Semaphore cajaLlena;
    private Semaphore semCamion;
    private Semaphore almacenLleno;
    private char tipoCaja=' ';//para saber si la caja es de agua o vino
    
    public Fabrica (){
          this.hayBotellaAgua=new Semaphore(1);
          this.hayBotellaVino=new Semaphore(1);
          this.semCamion=new Semaphore(0);
          this.cajaLlena=new Semaphore(0);
          this.almacenLleno=new Semaphore(1);
    }
    public void embotellarAgua(){
        try {
            almacenLleno.acquire();
            this.hayBotellaAgua.acquire();//exclusion:si embotella agua no puede embotellar vino
                this.hayBotellaVino.tryAcquire();//no puede  embotellar vino

                this.botellasAgua++;
                System.out.println(Thread.currentThread().getName()+"  embotellando agua: "+this.botellasAgua);

                this.caja++;//coloca botella en la caja
               
        } catch (Exception e) {
            // TODO: handle exception
        } 
        //cuando la caja se lleno con 10lts avisa al empaquetador
        if(this.caja==10){
              this.tipoCaja='a';
             this.cajaLlena.release();
        }else{
             this.hayBotellaAgua.release();
        }
        almacenLleno.release();
    }
    public void embotellarVino(){
        try {
            almacenLleno.acquire();
            this.hayBotellaVino.acquire();//exclusion:si embotella vino  no puede embotellar agua
                this.hayBotellaAgua.tryAcquire();

                this.botellasVino++;//contador de vinos
                System.out.println(Thread.currentThread().getName()+"  embotellando vino: "+this.botellasVino);

                this.caja++;//coloca botella en la caja
                //cuando la caja se lleno con 10lts avisa al empaquetador
                
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(this.caja==10){
            this.tipoCaja='v';
            this.cajaLlena.release();//avisa que la caja esta llena con 10 botellas
        }else{
             this.hayBotellaVino.release();
        }
        almacenLleno.release();
    }
    public void empaquetarCaja(){
        try {
            this.cajaLlena.acquire();
               this.cantCajas++;//agrega una caja al alamacen  
               
               if(this.tipoCaja=='a'){
                   
                   this.caja=0;//pone una caja nueva para llenar hasta 10 botellas
                   System.out.println("caja llena con agua");
                   this.hayBotellaAgua.release();
               }else{
                   
                   this.caja=0;//pone una caja nueva para llenar hasta 10 botellas
                   System.out.println("caja llena con vino");
                   this.hayBotellaVino.release();
               }
               
               if(this.cantCajas==2){//si llego a los 100lts en el almacen
                  this.almacenLleno.acquire();//evito que se siga llenando elalmacen
                  this.semCamion.release();//avisa al camion que estan los 100lts
               } 

        } catch (Exception e) {
            // TODO: handle exception
        }
        if(this.cantCajas==10){//si llego a los 100lts en el almacen
            this.semCamion.release();//avisa al camion que estan los 100lts
        }
      
    }
    public void vaciarAlmacen(){
         try {
            
            this.semCamion.acquire();//si estan los 100lts en el almacen
               this.cantCajas=0;//vacia el almacen 
               System.out.println(" CAMION SALE CON LOS 100LTS DEL ALMACEN ");
               
         } catch (Exception e) {
            // TODO: handle exception
         }
         this.almacenLleno.release();//avisa que el almacen se vacio puede seguir añadiendo cajas
    }


}
