package ejercicioParcial.recu23.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Serie {
    private int capitulosFilmados=0;
    private int capitulosTraducidos=0;

    private Lock capitulos=new ReentrantLock();
    private Condition verCapitulo=capitulos.newCondition();

    private Lock traduccion =new ReentrantLock();
    private Condition iniciarTraduccion= traduccion.newCondition();
    private Condition finalizoTraduccion= traduccion.newCondition();
    public Serie(){
    }
    public void filmarCap() throws InterruptedException{
        this.capitulos.lock();//se filma un capitulo a la vez
         try {
            System.out.println(Thread.currentThread().getName()+" finalizo la filmacion del capitulo ");
           
            this.capitulosFilmados++;//se filmo un capitulo
            this.verCapitulo.signalAll();//avisa que el cap en español ya esta
            this.iniciarTraduccion.signalAll();//avisa para que se inicie la traduccion
            
        } catch (Exception e) {
            // TODO: handle exception
         }finally{
          
            this.capitulos.unlock();
         }
    }
    public void iniciarTraduccion() throws  InterruptedException {
          this.traduccion.lock();
          try {  
                if(this.capitulosFilmados==0) {
                    this.iniciarTraduccion.await();//espera a que alla algun cap para traducir
                } 
                System.out.println(Thread.currentThread().getName()+" finalizo la traduccion del capitulo ");
                this.capitulosTraducidos++;
            } catch (Exception e) {
            // TODO: handle exception
          }finally{
            this.traduccion.unlock();
          }

    }
    public void finalizarTraduccion() throws InterruptedException{
          this.traduccion.lock();
          try {
              System.out.println(Thread.currentThread().getName()+" finalizo la traduccion del capitulo ");
              this.finalizoTraduccion.signalAll();//avisa que esta traducido
          } catch (Exception e) {
            // TODO: handle exception
          }finally{
            this.traduccion.unlock();
          }
    }
    public void verEnIngles() throws InterruptedException{
       this.traduccion.lock();
        try {
            if(this.capitulosTraducidos==0){
                this.finalizoTraduccion.await();//espera el episodio
            }
            System.out.println(Thread.currentThread().getName()+" ve  el capitulo en ingles");
        } catch (Exception e) {
          // TODO: handle exception
        }finally{
          this.traduccion.unlock();
        }
    }
   
    public void verEnEspañol () throws InterruptedException{
        this.traduccion.lock();
        try {
            if(this.capitulosFilmados==0){
                   this.verCapitulo.await();//espera el episodio
            }
            System.out.println(Thread.currentThread().getName()+" ve  el capitulo en español");

        } catch (Exception e) {
          // TODO: handle exception
        }finally{
          this.traduccion.unlock();
        }
    
    }

}
