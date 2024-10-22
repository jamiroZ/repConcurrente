package TP7_Locks.ej1;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class Sala{
    //contador de personas
    private int persona_EnSala=0;//cantidad de personas en la sala   
    private int maxPersonasNormales=50; //en condiciones normales 50
    private int maxPersonasCalor=35;//el maximo de personas baja a 35
    
    private int temp=0;//contador
    private int tUmbral=30;//umbral de temperatura
    private Lock lock=new ReentrantLock();
    private Condition jubilado= lock.newCondition();
  
    private Condition entrada= lock.newCondition();
    private boolean hayJubilado=false;

    public void entrarSala() throws  InterruptedException{

        lock.lock();
        try {
            if(hayJubilado){//si hay jubilados se bloquea hasta que entre                 
              this.jubilado.await();            
              System.out.println("persona esperando para entrar hay un jubilado");

            }

            if(temp>tUmbral){
                  if(persona_EnSala >= maxPersonasCalor){  
                     this.entrada.await();
                     System.out.println("persona esperando para entrar,sala llena");
                  } 
                  persona_EnSala++;
                  System.out.println(" persona entra a la sala, personas en sala "+persona_EnSala);
            }else{
                if(persona_EnSala >= maxPersonasNormales){
                    this.entrada.await();
                    System.out.println("persona esperando para entrar,sala llena");
                } 
                 
                persona_EnSala++;
                System.out.println(" persona entra a la sala,personas en sala "+persona_EnSala);
                    
            }
        }finally {
             lock.unlock();   // TODO: handle exception
        }
    }
    public void entrarSalaJubilado() throws   InterruptedException{

        lock.lock();
        try {
            this.hayJubilado=true;
            if(temp>tUmbral){
                  if(persona_EnSala >= maxPersonasCalor){
                    this.entrada.await();
                    System.out.println("jubilado esperando para entrar,sala llena"); 
                         
                  } 
                  persona_EnSala++;
                  System.out.println(" jubilado entra a la sala, personas en sala "+persona_EnSala);
            }else{
                if(persona_EnSala >= maxPersonasNormales){
                    this.entrada.await();
                    System.out.println("jubilado esperando para entrar,sala llena");               
  
                }
                persona_EnSala++;
                System.out.println(" jubilado entra a la sala,personas en sala "+persona_EnSala);
            }
            this.hayJubilado=false;//pudo entrar entonces ya no hay jubilado esperando
            this.jubilado.signal(); 
            System.out.println("persona esperando para entrar");
        }finally {

             lock.unlock();   // TODO: handle exception
        }
    }
    public void salirSala() throws  InterruptedException{

         lock.lock();
          try {
            if(persona_EnSala > 0){
                
                System.out.println("Persona sale de la sala"+persona_EnSala);
                persona_EnSala--;
                entrada.signal();//notifica a todos los hilos en espera
            }
          } finally {
             lock.unlock();
             
          }
    }
    public  void notificarTemperatura(){
          Random r=new Random();
          lock.lock();
          try{
              temp=r.nextInt(21)+20;//entre 20 y 40 grados
              System.out.println("temperatura media "+temp);
              
              //si es mayor a a 30 no entraran
          }finally{
                lock.unlock();
          }
          
    }
}