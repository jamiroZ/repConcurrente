package ejercicioParcial.parcial23.ejercicio2;

public class Espacio  {
    private int cantOxigeno=0;
    private int cantHidrogeno=0;

    private int recipiente=0;
    private int k=4;//cada cuatro veces que el metodo "hacerAgua" se ejecute incrementa hasta 4 y llena el reci, despues lo vacia
    public Espacio(){
    }
    public synchronized void hacerAgua(){

        System.out.println(" hizo agua "+this.cantHidrogeno+" "+this.cantOxigeno);
        this.recipiente++;
        if(this.recipiente==this.k){//se lleno el recipiente vacialo
            System.out.println("recipiente lleno, vaciar");
            this.recipiente=0;
        }
        this.cantHidrogeno=0;
        this.cantOxigeno=0;
        this.notifyAll();//notifica que ya termino  de hacer agua


    }
    public synchronized void Olisto(){
       try { 
           
           while( this.cantOxigeno>=1){
               System.out.println( Thread.currentThread().getName()+" "+this.cantHidrogeno+" "+this.cantOxigeno);
               if(this.cantHidrogeno==2 && this.cantOxigeno==1){
                   this.hacerAgua();
               }else{
                   this.wait();
               }
               
           }
           System.out.println(Thread.currentThread().getName()+" agrega uno de oxigeno ");
           this.cantOxigeno++;
          
       } catch (Exception e) {
        // TODO: handle exception
       }
    }
    public synchronized void Hlisto(){
        try {
           
            while( this.cantHidrogeno>=2){
                System.out.println(Thread.currentThread().getName()+" "+this.cantHidrogeno+" "+this.cantOxigeno);
                if(this.cantHidrogeno==2 && this.cantOxigeno==1){
                    this.hacerAgua();
                }else{
                    this.wait();
                }
                
            }
            System.out.println(Thread.currentThread().getName()+" agrega uno de hidrogeno ");
            this.cantHidrogeno++;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
