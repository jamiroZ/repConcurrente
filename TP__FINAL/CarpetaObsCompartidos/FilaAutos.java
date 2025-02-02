package TP__FINAL.CarpetaObsCompartidos;

public class FilaAutos {
    private int autosOcupados=20;
    private int cont=0;
    private boolean finalizo=false;
    private boolean inicio=false;
    public FilaAutos() {}
    //metodos del visitante
    public synchronized void subirAuto() { 
        try {
               
           while(cont >= autosOcupados){//mientras no haya espacio que espere
                System.out.println(Thread.currentThread().getName()+" No pudo subirse espera ");
                this.wait();      
           } 
            cont++;  
            finalizo=false; 
            if(cont==autosOcupados){
                inicio=true;
            }     
            System.out.println(Thread.currentThread().getName()+" subio al auto "+cont);  
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.notifyAll();
        
    }
    public synchronized void bajarAuto() {
        try {
             while( !finalizo ){//si no finalizo la atraccion esperan
               this.wait();
             }
             cont--;//se bajan todos
             System.out.println(Thread.currentThread().getName()+" baja de los autos chocadores "+cont);
             if(cont==0){
                
                this.notifyAll();    
             }     
        } catch (Exception e) {
            // TODO: handle exception
        }
       
    }/////////////
    //metodos de la atraccion de autos
    public synchronized void chano(){
        try {
            while( !inicio){ //no se llenaron los 10 autos con 2 personas espera            
                this.wait();
            } 
            System.out.println(" ");
            System.out.println("--los autos estan llenos--");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public synchronized void chanoChoco() {
       
        System.out.println("--terminaron los autos chocadores--");
        System.out.println(" ");
        finalizo=true;
        inicio=false;
        this.notifyAll();
    }
        
}
