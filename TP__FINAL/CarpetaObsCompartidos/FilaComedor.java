package TP__FINAL.CarpetaObsCompartidos;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeoutException;

public class FilaComedor {

    private int cantEspera=0;//gente esperando para entrar al comedor
    private int cantMesas;//cantidad mesas del comedor
    private int cantOcupadas=0;//mesas ocupadas
    private int cantPersonasXmesa=4;//maximo de personas que pueden estar en una mesa
    private int capacidad;
    private int cantPersonas=0;
    private int[]asientosOcupados;
    private CyclicBarrier[]mesas;
    private CyclicBarrier[]dejarMesa;
    private Boolean []mesasOcupadas;//mesas llenas
   
    public FilaComedor(int cantMesas){
        this.cantMesas=cantMesas;
        this.capacidad=cantMesas*cantPersonasXmesa;
        this.mesas=new CyclicBarrier[cantMesas];
        this.dejarMesa=new CyclicBarrier[cantMesas];
        this.mesasOcupadas=new Boolean[cantMesas];
        this.asientosOcupados=new int[cantMesas];
        for(int i=0;i<cantMesas;i++){
            mesas[i]=new CyclicBarrier(cantPersonasXmesa);
            dejarMesa[i]=new CyclicBarrier(cantPersonasXmesa);
            mesasOcupadas[i]=false;
        }
    }

    public synchronized void entrarComedor() throws InterruptedException {
        if(cantPersonas==capacidad){//espera a que se desocupe espacio en el comedor
            this.wait();
        }
        cantPersonas++;//entra al comedor
        System.out.println("visitante "+Thread.currentThread().getName()+" entra al comedor ");
    }

    public synchronized int buscarMesa()throws InterruptedException, BrokenBarrierException, TimeoutException{
        int mesaElegida=-1;
        int i=0;//uso para desplazarme entre las mesas
        int j=0;
        while(mesaElegida==-1){//busca mesa 
            if(!mesasOcupadas[i]){//si la mesa tiene espacio esta libre 
                mesaElegida=i;
                asientosOcupados[i]= asientosOcupados[i]+1;//ocupa 1 de 4 asientos

                System.out.println("visitante "+Thread.currentThread().getName()+" se sienta en la mesa "+mesaElegida);
                if(asientosOcupados[i] == cantPersonasXmesa){//se lleno la mesa
                    mesasOcupadas[i]=true;//la mesa esta ocupada
                    j=i;
                }
            }else{
                i++;//busca otra mesa
            }
        }
        //si se sento alguien, espera 5minutos a que se llene o sirve la comida
        mesas[j].await(5, TimeUnit.SECONDS);
        if(mesasOcupadas[j]){//si la mesa esta llena comen
            System.out.println("visitante "+Thread.currentThread().getName()+" come en la mesa "+j);
        }
        return mesaElegida;
    } 

    public synchronized void dejarMesa(int mesaElegida) throws InterruptedException, BrokenBarrierException, TimeoutException {
         dejarMesa[mesaElegida].await(5, TimeUnit.SECONDS);
         asientosOcupados[mesaElegida]--;
         System.out.println("visitante "+Thread.currentThread().getName()+" deja la mesa "+mesaElegida);
         mesasOcupadas[mesaElegida]=false;//hay espacio para que se siente alguien
        
    }

    public synchronized void salirComedor() throws InterruptedException{
        System.out.println("visitante "+Thread.currentThread().getName()+" sale del comedor ");
        cantPersonas--;//hay espacio en el comedor
        this.notifyAll();
    }

}
