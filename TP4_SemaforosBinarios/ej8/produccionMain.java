package TP4_SemaforosBinarios.ej8;

public class produccionMain {
    public static void main(String[] args) {
        int cantElec=1500;
        ControladorProduccion produccion=new ControladorProduccion();
        ProductoElec[] producElec = new ProductoElec[cantElec];
        ProductoMec[] producMec = new ProductoMec [15];




        for(int i=0 ; i< cantElec; i++){
            producElec[i]=new ProductoElec(produccion, "1923"+i);
            if(i<15){
                    producMec[i]=new ProductoMec(produccion, "1923"+i);
            }
        
        }



        Thread[] h1=new Thread[cantElec];
        Thread[] h2=new Thread[15];
        for (int i = 0; i < h1.length+h2.length; i++) {
            h1[i]=new Thread( producElec[i],"E"+i);
            
            h2[i]=new Thread( producMec[i],"M"+i);
        }





        for (int i = 0; i < h1.length; i++) {
            h1[i].start();
            h2[i].start();
        }










        for (int i = 0; i < h1.length; i++) {
            
            try {
                h1[i].join();
                h2[i].join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
      
        

    }
       
  }
