package TP6_Monitores.ej7;

import java.util.function.DoubleUnaryOperator;

public class Ferry {
    private int capacidad;
    private int espaciOcupado;// contador

    private int pasajeroEsperando=0;
    private int automovilEsperando=0;
    private boolean flag;
    public Ferry(int capacidad) {
        this.capacidad = capacidad;// espacios iniciales
        this.espaciOcupado = capacidad;// contador
        this.flag=false;//bandera que avisa cuando llego al destino
    }

    // metodos del chofer
    public synchronized void arrancar() {
        try {
            while(this.espaciOcupado > 0 ){//si el ferry no esta lleno espere
                  this.wait();
            }
            //this.flag=true;//para avisar que arranco
            System.out.println("----ferry arranca viaje----");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public synchronized void llegoDestino() {
        try {
            
            System.out.println("----ferry llego al destino----");
            this.flag=true;//para avisar que llego
            this.notifyAll();//notifica para que bajen
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // metodos de pasajeros
    public synchronized void embarquePasajeros(){
        try{
            
            this.pasajeroEsperando++;
            while(this.espaciOcupado==0 || flag){//no hay espacio espere
                System.out.println("el ferry no volvio espere");
                this.wait();
                
            }
           
            //si ocupo un espacio se resta y ya no es pasajero esperando
            this.espaciOcupado--;
            this.pasajeroEsperando--;
            System.out.println(Thread.currentThread().getName()+" sube al ferry "+this.espaciOcupado);
            
            if(this.espaciOcupado == 0){
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // TODO: handle exception
        }
    }

    public synchronized void desembarquePasajeros() {
        try {
            while(!flag){
                this.wait();
            }
            this.espaciOcupado++;//libera el espacio
            System.out.println(Thread.currentThread().getName()+" baja "+this.espaciOcupado);
            if(this.espaciOcupado==capacidad){//si fue el ultimo en salir
                this.flag=false;//que no puedan bajarse si recien suben
                this.notifyAll();
                
            }    
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    //metodos de automovil
    public synchronized void embarqueAutomoviles(){
        try{
            this.automovilEsperando++;
            while(this.espaciOcupado < 4 || flag ){

                this.wait();
               
            }
   
            this.espaciOcupado -=4;
            this.automovilEsperando--;//deja de esperar
            System.out.println(Thread.currentThread().getName()+"  sube al ferry "+this.espaciOcupado);
            
            if(this.espaciOcupado == 0){
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // TODO: handle exception
        }
    }

    public synchronized void desembarqueAutomoviles() {
        try {
            while(!flag){

                this.wait();
            }
            this.espaciOcupado +=4;//libera el espacio
            System.out.println(Thread.currentThread().getName()+" baja "+this.espaciOcupado);
            
            if(this.espaciOcupado==capacidad){//si fue el ultimo en salir
                this.flag=false;
                this.notifyAll();
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
