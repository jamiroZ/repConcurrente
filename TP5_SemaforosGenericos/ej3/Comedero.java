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
              this.mutux.acquire();
                   //ADVERTENCIA 
                   //LA CANTIDAD DE PERMISOS DEL TRYACQUARE MODIFICA BASTANTE    
                   this.hayGato.tryAcquire(cantGatosEspernado);
                   cantPerrosEspernado++;       
                    
                   this.hayPerro.acquire();//toma uno de n permisos de perros
                   
                   cantPerrosEspernado--;//ya no esta en espera
              this.mutux.release();    
              this.platos.acquire();//si hay espacio que entre a comer      
                  cantPerrosComiendo++;//ahora esta c
              
              System.out.println("perro "+Thread.currentThread().getName()+" entro a comer");
               
          } catch (Exception e) {
            // TODO: handle exception
          }
      }
      public void salirPerro(){     
          System.out.println("perro "+Thread.currentThread().getName()+" salio del comedor");
          //comieron todos los perros y hay gatos esperando
          try {
              this.mutux.acquire();
                  if(cantPerrosComiendo == cant && cantGatosEspernado > 0){
                          System.out.println("a"+cantPerrosEspernado+" "+cantPerrosComiendo);
                          cantPerrosComiendo=0;
                          this.hayGato.release(cantGatosEspernado);
                    
                  }else //no comieron todos los perros pero no hay gatos esperando
                  if(cantPerrosComiendo < cant && cantGatosEspernado == 0){
                           System.out.println("b"+cantPerrosEspernado+" "+cantPerrosComiendo);
                           this.platos.release();
                      
                  }else //no comieron todos los perros ,no hay perros esperando y hay gatos esperando
                  if(cantPerrosComiendo < cant && cantPerrosEspernado == 0 && cantGatosEspernado > 0 ){    
                           System.out.println("c"+cantPerrosEspernado+" "+cantPerrosComiendo);
                           cantPerrosComiendo=0;
                           this.hayGato.release(cantGatosEspernado);  
                      
                  }else //no hay perros ni gatos esperando (termina)
                  if(cantGatosEspernado == 0 && cantPerrosComiendo < cant){
                          System.out.println("D");
                          this.platos.release();
                  
                  }else 
                  if(cantGatosEspernado == 0 && cantPerrosEspernado == 0){//no hay perros ni gatos esperando (termina)
                      System.out.println("no hay mas perros ni gatos esperando");
           
                  }
              this.mutux.release();
          } catch (Exception e) {
            // TODO: handle exception
          }
          
         
          
      }
      public void comerGato() {
          try {
            this.mutux.acquire();
                 this.hayPerro.tryAcquire(cantPerrosEspernado);  
                 cantGatosEspernado++;
                 
                 this.hayGato.acquire();//si hay espacio que 
                 
                 cantGatosEspernado--;
            this.mutux.release();       
            this.platos.acquire();
                 cantGatosComiendo++;
            
            System.out.println("gato "+Thread.currentThread().getName()+" entro a comer"); 
          
          } catch (Exception e) {
           // TODO: handle exception
          }
      }
      public void salirGato() {
        System.out.println("gato "+Thread.currentThread().getName()+" salio del comedor"); 
        try {
          this.mutux.acquire();   
             if(cantGatosComiendo == cant && cantPerrosEspernado > 0){
                    System.out.println("A"+cantGatosEspernado+" "+cantGatosComiendo);
                    cantGatosComiendo=0;
                    this.hayPerro.release(cantPerrosEspernado);
             
             }else //no comieron todos los perros pero no hay gatos esperando
             if(cantGatosComiendo < cant && cantPerrosEspernado == 0){
                     System.out.println("B"+cantGatosEspernado+" "+cantGatosComiendo);
                     this.platos.release();
             }else //no comieron todos los perros ,no hay perros esperando y hay gatos esperando
             if(cantGatosComiendo < cant && cantGatosEspernado==0 && cantPerrosEspernado > 0 ){
                     System.out.println("C"+cantGatosEspernado+" "+cantGatosComiendo);
                     cantGatosComiendo=0;
                     this.hayPerro.release(cantPerrosEspernado);      
          
             }else//no hay perros esperando que salga no ma (termina)
             if(cantPerrosEspernado == 0 && cantGatosComiendo < cant){
                     System.out.println("D"+cantGatosEspernado+" "+cantGatosComiendo);
                    this.platos.release();
              
             }else 
             if(cantPerrosEspernado==0 && cantGatosEspernado==0){//no hay perros ni gatos esperando (termina)
                  System.out.println("no hay mas perros ni gatos esperando");

             }
          this.mutux.release();
          
        } catch (Exception e) {
          // TODO: handle exception
        }
      
      }

}

