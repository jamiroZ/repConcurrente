package TP6_Monitores.ej4;

public class Almacen {
    private int capacidad;
    private int estanterias;//objeto compartido
    private String[]productos;//productos
    public Almacen(int capacidad){
        //arranca con estanterias llenas
         this.estanterias=capacidad;
              
    }
    public synchronized void agregarProducto(){
        try {
            while(estanterias==capacidad){//estanterias llenas
                System.out.println("estanteria llena");
                this.wait();//espera a que falte producto
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        estanterias++;//agrega un producto
        System.out.println("Se ha agregado un producto");
        this.notify();//avisa a los consumidores
    }

    public synchronized void eliminarProducto(){
        try {
            while(estanterias==0){//estanterias vacias
                System.out.println("estanteria vacia");
                this.wait();//espera a que alla producto
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("consumidor consumio producto");
        estanterias--;//un producto menos
        this.notify();//notifica a los productores que falta producto
    }
}
