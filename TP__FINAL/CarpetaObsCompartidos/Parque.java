package TP__FINAL.CarpetaObsCompartidos;

public class Parque {
    private int cantVisitantes; 
    private Boolean parqueAbierto;
    public Parque(){
        this.cantVisitantes=0;
        this.parqueAbierto=false;
    }
    //METODOS QUE USA EL VISITANTE
    /*
     * metodo ingresarParque los hace esperar hasta que abra el parque
     * y luego lo deja entrar sincronizadamente, una ves que abrio
     */
    public synchronized void ingresarParque(){
        try {
           
            while(!parqueAbierto){//espera a que abra el parque
                System.out.println("el parque esta cerrado , espere a que abra");
                this.wait();
            }
            if(cantVisitantes==0){//espacio si fue el primero
                System.out.println(" ");
            }
            System.out.println("visitante "+Thread.currentThread().getName()+" ingreso al parque");
            cantVisitantes++;

        } catch (InterruptedException ex) {
            System.out.println(" Error en Parque.ingresarParque" + ex.getMessage());
        }
    }
    /*
     * el metodo salirParque simplemente decrementa el contador de visitantes
     * si fue el ultimo notifica que se fueron todos
     */
    public synchronized void salirParque(){
          cantVisitantes--;
         
          if(cantVisitantes==0){
             System.out.println("--TODOS LOS VISIANTES SE FUERON--");
             System.out.println(" ");      
          }else{
            System.out.println("sale el visitante "+ Thread.currentThread().getName()+", quedan "+cantVisitantes+" visitantes en el parque");
          }
          
    }
    //METODOS QUE USA EL RELOJ DEL PARQUE
    /*
     * El metodo abrirParuqe abre el parque ,cambia el estado a abierto y
     * notifica a los visitantes
     */
    public synchronized void abrirParque(){
        System.out.println(" ");//ESPACIO
        System.out.println("-----------------");
        System.out.println("--EL PARQUE ABRE SUS PUERTAS--");
        System.out.println("-----------------");
        System.out.println(" ");//ESPACIO
        parqueAbierto=true;//deja el while en false
        this.notifyAll();//notifica al visitante que el parque esta abierto
    }
    /*
     * metodo cerrarParque cambia el estado del parque cerro
     * asi impidiendo que entren mas visitantes, y los que quedaron adentro
     * pueden terminar sus atracciones y salir 
     */
    public synchronized void cerrarParque(){
         parqueAbierto=false;
         System.out.println("-----------------");
         System.out.println("--EL PARQUE CIERRA SUS PUERTAS--");
         System.out.println("-----------------");
    }
}
