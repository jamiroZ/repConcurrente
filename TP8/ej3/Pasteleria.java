package TP8.ej3;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pasteleria {
     private int pesoMaxCaja;//peso maximo de la caja
     private int pesoCaja=0;//contador de pasteles en la caja(peso)
     //cantidad de pasteles en el mostrador
     private int cantPastelesA=0;
     private int cantPastelesB=0;
     private int cantPastelesC=0;
  
     private int pesoA;
     private int pesoB;
     private int pesoC;

     private Lock caja=new ReentrantLock();
     private Condition  cajaLlena= caja.newCondition();
     private Condition llenarCaja= caja.newCondition();

     public Pasteleria(int pesoMaxCaja,int pesoA,int pesoB, int pesoC){
            this.pesoMaxCaja=pesoMaxCaja;
            this.pesoA=pesoA;
            this.pesoB=pesoB;
            this.pesoC=pesoC;
     }
     public void ponerEnMostradorPastel(int  pesoPastel){
        this.caja.lock();
         try {
            System.out.println(Thread.currentThread().getName()+" pone en  el mostrador un pastel de "+pesoPastel+" gramos");
            if(pesoPastel == this.pesoA){
                   this.cantPastelesA++;//ahora hay pasteles A en el mostrador  
            }else if(pesoPastel==this.pesoB){
                   this.cantPastelesB++;//ahora hay pasteles B en el  mostrador  
            }else{
                   this.cantPastelesC++;//ahora hay pasteles C en el mostrador
            }
            this.llenarCaja.signalAll();//avisa que hay pasteles para empacar
        } catch (Exception e) {
            // TODO: handle exception
         }finally{
            this.caja.unlock();
         }

     }
     public void colocarEnCaja(){
        this.caja.lock();
        try {
            if(this.cantPastelesA==0 || this.cantPastelesB==0 ||this.cantPastelesC==0){
                this.llenarCaja.await();//espera a que haya cajas en el mostrador
            }
            if(this.cantPastelesA>0){
                System.out.println(Thread.currentThread().getName()+" coloca en caja pastel de "+this.pesoA+" gramos");
                if(this.pesoCaja + this.pesoA < this.pesoMaxCaja){

                }
                this.pesoCaja=this.pesoCaja + this.pesoA;//coloca pastel en la caja
                this.cantPastelesA--;//saca del mostrador pastel A
            }else if(this.cantPastelesB>0){
                System.out.println(Thread.currentThread().getName()+" coloca en caja pastel de "+this.pesoB+" gramos");
                
                this.pesoCaja=this.pesoCaja + this.cantPastelesB;//coloca pastel en la caja
                this.cantPastelesB--;//saca del mostrador pastel B
            }else{
                System.out.println(Thread.currentThread().getName()+" coloca en caja pastel de "+this.pesoC+" gramos");
                this.pesoCaja=this.pesoCaja+ this.cantPastelesC;//coloca pastel en la caja
                this.cantPastelesC--;//saca del  mostrador pastel C
            }

        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            this.caja.unlock();
        }
     }
    
     public void retirarCaja(){
        this.caja.lock();
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            this.caja.unlock();
        }
     }
     public void reponerCaja(){

     }

}
