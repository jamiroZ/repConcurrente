package TP3_Sincronizacion.ej5;
//LA CLASE AUTO ES UNA SUBCLASE DE LA CLASE VEHICULO Y SE IMPLEMENTA COMO UN HILO
public class Auto extends Vehiculo implements Runnable{
    private String patente;
    private Surtidor surtidor;
     public Auto(String patente,Surtidor surtidor, String modelo,String marca,int km){
         super(modelo,marca,km);//uso constructor de la superClase Vehiculo
         this.patente=patente;
         this.surtidor=surtidor;
     }
     public String getPatente(){
        return this.patente;
     }
     public String toString(){
        return "patente: " + patente +super.toString();
     }
     public void run(){
         try{
             int n=(int) (Math.random() * (100-10 + 1));//kilometros recorridos
             super.setKm(n);
             //System.out.println(n);
             if(n>30){//simula el limite para poder cargar nafta en el surtidor
                surtidor.usar();
             }
         }catch(Exception e){
             System.out.println(e);
         }
     }
}
