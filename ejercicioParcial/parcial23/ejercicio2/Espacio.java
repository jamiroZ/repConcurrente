package ejercicioParcial.parcial23.ejercicio2;

public class Espacio  {
    private int cantOxigeno=0;
    private int cantHidrogeno=0;

    private int hayHidrogeno=0,hayOxigeno=0;
    private int recipiente=0;
    private int k=4;//cada cuatro veces que el metodo "hacerAgua" se ejecute incrementa hasta 4 y llena el reci, despues lo vacia
   
    private boolean flag=false;
    public Espacio(){
    }
    public synchronized void hacerAgua(){

        System.out.println(" hizo agua con 2 de hidrogeno "+this.cantHidrogeno+" y uno de oxigeno "+this.cantOxigeno);
        this.recipiente++;
        if(this.recipiente==this.k){//se lleno el recipiente
            System.out.println("recipiente lleno, vaciar");
            this.recipiente=0;
        }
        
        this.cantHidrogeno=0;
        this.cantOxigeno=0;
        this.notifyAll();//notifica que ya termino  de hacer agua
        
    }
    public synchronized void Olisto(){
       try { 
           this.hayOxigeno++;//queda oxigeno
           while( this.cantOxigeno>=1 &&  !flag ){
               System.out.println( Thread.currentThread().getName()+" "+this.cantHidrogeno+" "+this.cantOxigeno);
             
               if(this.cantHidrogeno==2 && this.cantOxigeno==1){
                   this.hacerAgua();
               }else if(this.hayOxigeno < 1 ){
                    flag=true;
                    this.notifyAll();
               }else{//si hay hidrogeno que el oxigeno
                    this.wait();    
               }

           }
           this.hayOxigeno--;//queda oxigeno    
           System.out.println(Thread.currentThread().getName()+" agrega uno de oxigeno ");
           this.cantOxigeno++;
          
       } catch (Exception e) {
        // TODO: handle exception
       }
    }
    public synchronized void Hlisto(){
        try {
            this.hayHidrogeno++;//hay hidrogeno 
            while( this.cantHidrogeno>=2 &&   !flag ){

                System.out.println(Thread.currentThread().getName()+" "+this.cantHidrogeno+" "+this.cantOxigeno);
                
                if(this.cantHidrogeno==2 && this.cantOxigeno==1){
                    this.hacerAgua();
                }else  if(this.hayHidrogeno < 2){
                    flag=true;
                    this.notifyAll();
                }else{//si hay hidrogeno que el oxigeno
                    this.wait();    
                }

             
            }
            this.hayHidrogeno--;//hay hidrogeno
            System.out.println(Thread.currentThread().getName()+" agrega uno de hidrogeno ");
            this.cantHidrogeno++;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
