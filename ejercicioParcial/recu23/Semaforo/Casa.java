package ejercicioParcial.recu23.Semaforo;

import java.util.concurrent.Semaphore;

public class Casa {
    private Semaphore banquitos;
    private Semaphore cocinar;

    private boolean dibujar=true;
    private int comiendo=0;
    public Casa(){
       this.banquitos=new Semaphore(4);//cuatro banquitos disponibles
       this.cocinar=new Semaphore(0);
    }
    public void tomarBanquito(){
        try {
            this.banquitos.acquire();//toma un banquito
            
              System.out.println(Thread.currentThread().getName()+"  se sienta en un banquito");
              this.comiendo++;
              this.dibujar=false;
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(this.comiendo > 0){
            this.cocinar.release();//avisa que cocine al animalGrande
        }
    }
    public void dejarBanquito(){
        try {
             System.out.println(Thread.currentThread().getName()+"  ha dejado el banquito");
             this.comiendo--;//libera espacio
             if(this.comiendo==0){
                this.dibujar=true;
                this.cocinar.acquire();
             }
        } catch (Exception e) {
            // TODO: handle exception
        }  
        this.banquitos.release();//libera un banquito
        
    }
    public void cocinar(){
        System.out.println(Thread.currentThread().getName()+" cocinando ");
    }
    public void servirComida(){
        System.out.println(Thread.currentThread().getName()+" sirve comida ");
        
    }
    public void dibujar(){
        try {    
            if(this.dibujar){//no hay nadie que dibuje
                System.out.println(Thread.currentThread().getName()+" dibuja ");
            }
            this.cocinar.acquire();//se queda bloqueda,simulando que dibuja hasta que lo llamen
        } catch (Exception e) {
            // TODO: handle exception
        }
    } 
}
