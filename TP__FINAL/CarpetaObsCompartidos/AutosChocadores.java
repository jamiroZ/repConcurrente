package TP__FINAL.CarpetaObsCompartidos;
//CHEQUEAR LOS "notifyAll y notify"
public class AutosChocadores {
    private int asientosMax=20;//ASIENTOS POR AUTO (HAY 10 AUTOS Y ENTRAN 2 PERSONAS POR AUTO)
    private int cont=0;
    private boolean finalizo=false;
    private boolean inicio=false;
    private boolean subir=true;
    public AutosChocadores() {}
    //metodos del visitante
    public synchronized void subirAuto() { 
        try {         
           while(cont >= asientosMax || !subir){//mientras no haya espacio que espere
System.out.println(Thread.currentThread().getName()+" No pudo subirse espera");
                this.wait();      
           } 
            cont++;  
            if(cont==asientosMax){
                subir=false;
                inicio=true;
                this.notifyAll();
            }     
            System.out.println(Thread.currentThread().getName()+" subio al auto ("+cont+"/"+asientosMax+")");  
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();// TODO: handle exception
        }  
    }
    public synchronized void bajarAuto() {
        try {
             while( !finalizo ){//si no finalizo la atraccion esperan
                 this.wait();
             }
             cont--;//se bajan todos
             System.out.println(Thread.currentThread().getName()+" baja de los autos chocadores ("+cont+"/"+asientosMax+")");
             if(cont==0){
                subir=true;
                finalizo=false;
                this.notifyAll();    
             }     
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();// TODO: handle exception
        }    
    }
    //metodos de la atraccion de autos
    public synchronized void iniciarJuego(){
        try {
            while( !inicio){ //no se llenaron los 10 autos con 2 personas espera            
                this.wait();
            } 
            System.out.println(" ");
            System.out.println("--los autos estan llenos--");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();// TODO: handle exception
        }    
    }
    public synchronized void finalizarJuego() {
        System.out.println("--terminaron los autos chocadores--");
        System.out.println(" ");
        finalizo=true;
        inicio=false;
        this.notifyAll();
    }      
}
