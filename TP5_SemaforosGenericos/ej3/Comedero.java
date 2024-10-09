package TP5_SemaforosGenericos.ej3;

import java.util.concurrent.Semaphore;

public class Comedero {
      private Semaphore platos;//cantidad de perros/gatos que pueden comer
      private Semaphore hayPerro;//si hay perros dentro del comedero se instancia en 0
      private Semaphore hayGato;//si hay gatos se instancia en 0
      
      private Semaphore mutux;

      private int MAX_animales_x_turno ;
      private int cantGatosEspernado;
      private int cantPerrosEspernado;

      private int cantPerrosComiendo;
      private int cantGatosComiendo;
     
      private int cant;
      public Comedero(int cant){//CONSTRUCTOR
            this.platos=new Semaphore(cant);//tal cantidad de perros o gatos pueden entrar
            this.cant=cant;//cantidad de platos
            
            this.mutux=new Semaphore(1);

            MAX_animales_x_turno = 12;//limite de animales que pueden entrar
            this.hayGato=new Semaphore(MAX_animales_x_turno);
            this.hayPerro=new Semaphore(MAX_animales_x_turno);
            
            
            //ESPERA
            cantGatosEspernado=0;
            cantPerrosEspernado=0;
            //COMIENDO
            cantPerrosComiendo=0;
            cantGatosComiendo=0;


      }

      public void comerPerro() {
          try {
              this.hayGato.tryAcquire(MAX_animales_x_turno);
              this.mutux.acquire();
                  cantPerrosEspernado++;
                  
              this.mutux.release();
              
                  this.hayPerro.acquire();
                  cantPerrosEspernado--;
                  cantPerrosComiendo++;
                
              System.out.println("perro "+Thread.currentThread().getName()+" entro a comer");
             
          } catch (Exception e) {
            // TODO: handle exception
          }
      }
      public void salirPerro(){     
          System.out.println("perro "+Thread.currentThread().getName()+" salio del comedor");
          //comieron todos los perros y hay gatos esperando
          try {
                 if(cantPerrosComiendo == cant && cantGatosEspernado > 0){
                      this.mutux.acquire();
                         cantPerrosEspernado=0;
                  
                     this.mutux.release();
                    
                      this.hayGato.release(MAX_animales_x_turno);
                  
                 }else //no comieron todos los perros pero no hay gatos esperando
                 if(cantPerrosComiendo < cant && cantGatosEspernado == 0){
                      
                      this.hayPerro.release();//libero permiso para que sigan entrando mas perros

                 }else //no comieron todos los perros ,no hay perros esperando y hay gatos esperando
                 if(cantPerrosComiendo < cant && cantPerrosEspernado==0 && cantGatosEspernado > 0 ){
                      
                      cantPerrosComiendo=0;  
                      this.hayGato.release(MAX_animales_x_turno);      
                       
                 }else if(cantGatosEspernado==0 && cantPerrosEspernado==0){//no hay perros ni gatos esperando (termina)
                      System.out.println("no hay mas perros ni gatos esperando");
               
                 }

          } catch (Exception e) {
            // TODO: handle exception
          }
          
         
          
      }
      public void comerGato() {
          try {
            this.hayPerro.tryAcquire(MAX_animales_x_turno);
            this.mutux.acquire();
                cantGatosEspernado++;
            this.mutux.release();
          
               this.hayGato.acquire();
               cantGatosEspernado--;
               cantPerrosComiendo++;
            
            System.out.println("gato "+Thread.currentThread().getName()+" entro a comer"); 
            
          } catch (Exception e) {
           // TODO: handle exception
          }
      }
      public void salirGato() {
          System.out.println("gato "+Thread.currentThread().getName()+" salio del comedor"); 
        try {
           
          if(cantGatosComiendo == cant && cantPerrosEspernado > 0){
                       
            this.mutux.acquire();
                 cantGatosComiendo=0;
            
            this.mutux.release();
          
            this.hayPerro.release(MAX_animales_x_turno);
        
          }else //no comieron todos los perros pero no hay gatos esperando
          if(cantGatosComiendo < cant && cantPerrosEspernado == 0){
              
            this.hayGato.release();//libero permiso para que sigan entrando mas perros
       
          }else //no comieron todos los perros ,no hay perros esperando y hay gatos esperando
          if(cantGatosComiendo < cant && cantGatosEspernado==0 && cantPerrosEspernado > 0 ){
            
            cantGatosComiendo=0;  
            this.hayPerro.release(MAX_animales_x_turno);      
             
          }else if(cantPerrosEspernado==0 && cantGatosEspernado==0){//no hay perros ni gatos esperando (termina)
            System.out.println("no hay mas perros ni gatos esperando");
     
          }
        } catch (Exception e) {
          // TODO: handle exception
        }
      
      }

}

