package TP__FINAL.CarpetaObsCompartidos;

public class FilaAutos {
    private int autosOcupados=20;
    private int cont=0;
    private boolean finalizo=false;
    public FilaAutos() {}
    //metodos del visitante
    public void subirAuto() { 
        try {
           
           cont++;    
           while(cont > autosOcupados){//mientras no haya espacio que espere
                System.out.println(Thread.currentThread().getName()+" No pudo subirse espera ");
                this.wait();
                finalizo=false;
           }
            System.out.println(Thread.currentThread().getName()+" subio al auto ");
            
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.notifyAll();
        
    }
    public void bajarAuto() {
        try {
             while( !finalizo ){//si no finalizo la atraccion esperan
               this.wait();
             }
             System.out.println(Thread.currentThread().getName()+" baja de los autos chocadores ");
             cont--;//se bajan todos
             
             this.notifyAll();
        } catch (Exception e) {
            // TODO: handle exception
        }
       
    }
    //metodos de la atraccion de autos
    public void chano(){
        try {
            System.out.println("xxxxx");
            while(cont < autosOcupados){    
                System.out.println("espera a que esten las 20 personas en los autos");
                this.wait();
            }
            
            System.out.println("--los autos estan llenos--");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public void chanoChoco() {
       
        System.out.println("--terminaron los autos chocadores--");
        finalizo=true;
        this.notifyAll();
    }
        
}
