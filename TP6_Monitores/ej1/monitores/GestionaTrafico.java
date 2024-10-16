package TP6_Monitores.ej1.monitores;

public class GestionaTrafico {
    private int cochesNorte=0;
    private int cochesSur=0;
    
    private int salioSur=0;
    private int salioNorte=0;
    
    private int cantMax;
    public GestionaTrafico( int cantMax){
            this.cantMax=cantMax;
    }
    public synchronized void EntrarCocheDelNorte(){
          
          try {
              /*si hay autos del sur o esta lleno el puente, espera */
              while( cochesSur >0  || cochesNorte==cantMax ){
                   if(cochesSur>0){
                      System.out.println("auto del NORTE "+Thread.currentThread().getName()+" espera, hay autos del SUR");  
                   }else{
                      System.out.println("auto del NORTE "+Thread.currentThread().getName()+" espera, hay muchos autos del NORTE");  
                   }
                this.wait();
              }
          } catch (Exception e) {
            // TODO: handle exception
          }
          
          System.out.println("auto del NORTE "+Thread.currentThread().getName()+" pasa por el puente");
          cochesNorte++;//auto del norte pasa por el puente
          
    
    }

    public synchronized void SalirCocheDelNorte(){
        System.out.println(cochesNorte+"auto del NORTE "+Thread.currentThread().getName()+" salio del puente"+salioNorte);
        if(cochesNorte==cantMax){
            cochesNorte=0;
        }
        this.notify();
    }

    public synchronized void EntrarCocheDelSur(){
        //si hay autos del sur en el puente o esta lleno de autos del norte que espere
        try {
            //si hay coches del norte o esta lleno de coches del sur
            //que espere 
            while( cochesNorte>0  || cochesSur==cantMax ){
                 if(cochesNorte>0){
                    System.out.println("auto del SUR"+Thread.currentThread().getName()+" espera, hay autos del NORTE");  
                 }else{
                    System.out.println("auto del SUR"+Thread.currentThread().getName()+" espera, hay muchos autos del SUR");  
                 }
              this.wait();
            }
        } catch (Exception e) {
          // TODO: handle exception
        }
        
        System.out.println("auto del SUR "+Thread.currentThread().getName()+" pasa por el puente");
        cochesSur++;//auto del norte pasa por el puente
    }

    public synchronized void SalirCocheDelSur(){
        System.out.println(cochesSur+"auto del SUR "+Thread.currentThread().getName()+" salio del puente"+salioSur);
        if(cochesSur==cantMax){
            cochesSur=0;
        }
        
        this.notify();
    }
}
