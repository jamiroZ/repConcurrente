package ejercicioParcial.recu23.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Serie {
    private int capitulosFilmados=0;
    private int capitulosTraducidos=0;

    private Lock capitulos=new ReentrantLock();
    private Condition verCapitulo=capitulos.newCondition();
    private Condition iniciarTraduccion= capitulos.newCondition();
    private Condition finalizoTraduccion= capitulos.newCondition();

    public Serie(){
    }
    public void filmarCap() throws InterruptedException{
        this.capitulos.lock();//se filma un capitulo a la vez
         try {

            System.out.println(Thread.currentThread().getName()+" finalizo la filmacion del capitulo ");
            this.capitulosFilmados++;
            this.verCapitulo.signalAll();//pueden verse en español
            this.iniciarTraduccion.signalAll();//avisa que estan lo cap para traducir
            
        } catch (Exception e) {
            // TODO: handle exception
         }finally{
          
            this.capitulos.unlock();
         }
        }
    public void iniciarTraduccion() throws  InterruptedException {
          this.capitulos.lock();
          try {  
                if(this.capitulosFilmados==0) {
                    this.iniciarTraduccion.await();//espera a que alla algun cap para traducir
                    //this.capitulosFilmados--;//ya se tradujo
                } 
                System.out.println(Thread.currentThread().getName()+" inicio la traduccion del capitulo ");
                this.capitulosTraducidos++;
            } catch (Exception e) {
            // TODO: handle exception
          }finally{
            this.capitulos.unlock();
          }

    }
    public void finalizarTraduccion() throws InterruptedException{
          this.capitulos.lock();
          try {
          
              System.out.println(Thread.currentThread().getName()+" finalizo la traduccion del capitulo ");
              //this.capitulosTraducidos--;
              this.finalizoTraduccion.signalAll();//ya estan los capitulos traducidos para ver
          } catch (Exception e) {
            // TODO: handle exception
          }finally{
            this.capitulos.unlock();
          }
    }
    public void verEnIngles() throws InterruptedException{
       this.capitulos.lock();
        try {
            if(this.capitulosTraducidos==0){
              System.out.println("bloquea");
               this.finalizoTraduccion.await();//espera a que esten traducidos
            }
            System.out.println(Thread.currentThread().getName()+" ve  el capitulo en ingles");
        } catch (Exception e) {
          // TODO: handle exception
        }finally{
          this.capitulos.unlock();
        }
    }
   
    public void verEnEspañol () throws InterruptedException{
        this.capitulos.lock();
        try {
            if(this.capitulosFilmados==0){
                   this.verCapitulo.await();//espera el episodio
            }
            System.out.println(Thread.currentThread().getName()+" ve  el capitulo en español");

        } catch (Exception e) {
          // TODO: handle exception
        }finally{
          this.capitulos.unlock();
        }
    
    }

}
