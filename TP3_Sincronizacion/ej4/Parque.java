package TP3_Sincronizacion.ej4;

public class Parque{
    private int[]areas;
    //CONSTRUCTOR
    public Parque(int cantAreas){
         this.areas=new int[cantAreas];//cantidad de areas por parametro
         for(int i=0; i<cantAreas ; i++){//CARGO EL ARRAY DE AREAS
              areas[i]= (int) ((Math.random() * 3) + 1);//le inserto un numero aleatorio
         }
    }
    public int getCantAreas(){
        return this.areas.length;
    }
    //si esta disponible la reserva
    public synchronized void reservarArea(int area){
          if(0<area && area<this.areas.length){//si esta en el rango
               System.out.println(Thread.currentThread().getName()+"DESEA RESERVAR EL AREA "+area);
               if(verificarAreaLibre(area)){
                   try{
                       Thread.sleep(2000);
                       this.ocuparArea(area);
                   }catch(InterruptedException e){
                       e.printStackTrace();
                   }
                   System.out.println("area "+area+" reservada por "+Thread.currentThread().getName());
               }else{
                   System.out.println("el area "+area+" esta ocupada");
               }
          }
    }
    //carga el arreglo de areas con un area
    public synchronized void ocuparArea(int i){
           this.areas[i]= this.areas[i]-1;
    }
    private Boolean verificarAreaLibre(int area){
         return this.areas[area]>0;
    }
}