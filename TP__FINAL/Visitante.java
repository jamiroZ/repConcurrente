package TP__FINAL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import TP__FINAL.CarpetaObsCompartidos.AutosChocadores;
import TP__FINAL.CarpetaObsCompartidos.BarcoPirata;
import TP__FINAL.CarpetaObsCompartidos.Comedor;
import TP__FINAL.CarpetaObsCompartidos.MontañaRusa;
import TP__FINAL.CarpetaObsCompartidos.SalaRealidadVirtual;
import TP__FINAL.CarpetaObsCompartidos.Teatro;
import TP__FINAL.CarpetaObsCompartidos.Tren;
import TP__FINAL.CarpetaObsCompartidos.Parque;
import TP__FINAL.CarpetaObsCompartidos.AreaDePremios;

public class Visitante implements Runnable{
    //ATRACCIONES DEL PARQUE
    private MontañaRusa fila;
    private AutosChocadores filaA;
    private BarcoPirata filaB;
    private Teatro filaT;
    private SalaRealidadVirtual filaRV;
    private AreaDePremios juegosPremios;
    private Tren filaTren;
    private Reloj reloj;
    private Parque parque;
    private Comedor comedor;
    public Visitante(MontañaRusa fila,AutosChocadores filaA,BarcoPirata filaB,Teatro filaT,SalaRealidadVirtual filaRV, Reloj reloj,Parque parque,Tren filaTren,Comedor comedor,AreaDePremios juegosPremios){
        this.parque=parque;
        this.reloj=reloj;
        this.comedor=comedor;
        this.fila = fila;
        this.filaA = filaA;
        this.filaB = filaB;
        this.filaT = filaT;
        this.filaRV =filaRV;  
        this.filaTren=filaTren;
        this.comedor=comedor;
        this.juegosPremios=juegosPremios;
    }
    public void run(){
        Random r=new Random();
        int num;
        try {
            AtomicInteger hora=new AtomicInteger(19);
            parque.ingresarParque();
            Thread.sleep(4000);
            int num2=0;
            while(this.parque.estadoDelParque() ){//mientras el parque este abierto puede realizar las atracciones
                   Boolean ret;
                 /*   
                   ret=this.fila.subirMontaña();
                   if(ret){
                    this.fila.bajarMontaña();
                   }
                   Thread.sleep(r.nextInt(10000));
                   this.filaB.subirBarco();
                   Thread.sleep(500);
                   this.filaB.bajarBarco();
                
                   Thread.sleep(r.nextInt(3000));
               
                   this.filaA.subirAuto();
                   this.filaA.bajarAuto();
                */
                   
                 /*                 
                   this.filaRV.entrarRV();
                   Thread.sleep(r.nextInt(6000));//tiempo jugando en la sala de realidad virtual
                   this.filaRV.salirRV();

                   this.filaT.ingresaVisitante();
                   this.filaT.saleVisitante();
                   
                   this.filaTren.subirTren();
                   this.filaTren.bajarTren();

                
                   this.comedor.entrarComedor();
                   this.comedor.buscarMesa();
                   Thread.sleep(r.nextInt(10000));//tiempo comiendo
                   this.comedor.salirComedor();
                   //
                */
                   this.juegosPremios.pedirFicha();
                   Thread.sleep(r.nextInt(3000)+1000);// tiempo jugando
                   int puntos = (int) (Math.random() * 100);
                   this.juegosPremios.enviarPuntos(puntos);
                     Thread.sleep(r.nextInt(6000)+1000);// tiempo jugando
            }           

             
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            
            parque.salirParque();    
        }
        
    }
}