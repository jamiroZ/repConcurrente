package TP6_Monitores.ej7;

import java.util.function.DoubleUnaryOperator;

public class Ferry {
    private int capacidad;
    private int espaciOcupado;// contador

    private int pasajeroEsperando;
    private int automovilEsperando;
    private boolean flag;
    public Ferry(int capacidad) {
        this.capacidad = capacidad;// espacios iniciales
        this.espaciOcupado = capacidad;// contador
        this.flag=false;
    }

    // metodos del chofer
    public void arrancar() {
        try {
            while(this.espaciOcupado > 0){//si el ferry no esta lleno espere
                  this.wait();
            }

            System.out.println("----ferry arranca viaje----");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void llegoDestino() {
        try {
            while(!this.flag){
                
            }
            System.out.println("----ferry llego al destino----");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // metodos de pasajeros
    public void embarquePasajeros(){
        try{
            while(this.espaciOcupado==0){//no hay espacio espere
                System.out.println("el ferry no volvio espere");
                this.wait();
                this.espaciOcupado=capacidad;
            }
            this.espaciOcupado--;
            System.out.println(Thread.currentThread().getName()+" pasajero sube al ferry ");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void desembarquePasajeros() {

    }

    //metodos de automovil
    public void embarqueAutomoviles(){
        try{
            while(this.espaciOcupado==0){
                System.out.println("el ferry no volvio espere");
                this.wait();
                this.espaciOcupado=capacidad;
            }
            this.espaciOcupado=this.espaciOcupado-4;
            System.out.println(Thread.currentThread().getName()+" automovil sube al ferry ");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void desembarqueAutomoviles() {
        
    }

}
