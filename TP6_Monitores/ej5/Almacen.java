package TP6_Monitores.ej5;

public class Almacen {
    private int capacidad;
    private int estanterias;//objeto compartido

    public Almacen(int capacidad){
        //arranca con estanterias llenas
         this.estanterias=capacidad;//contador
         this.capacidad=capacidad;//limite
    }
    public synchronized void agregarProducto(){
        try {
            while(estanterias==capacidad){//estanterias llenas
                System.out.println("estanteria llena");
                this.wait();//espera a que falte producto
            }

            estanterias++;//agrega un producto
            System.out.println("Se ha agregado un producto "+estanterias+" "+capacidad);
            this.notifyAll();//avisa a los consumidores
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
            System.out.println("Agregación interrumpida");
        }
       
    }

    public synchronized void eliminarProducto(){
        try {
            while(estanterias == 0){//estanterias vacias
                System.out.println(Thread.currentThread().getName()+" estanteria vacia");
                this.wait();//espera a que alla producto
            }

            estanterias--;//un producto menos
            System.out.println(Thread.currentThread().getName()+" consumidor consumio producto");
            this.notifyAll();//notifica a los productores que falta producto
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
            System.out.println("Eliminación interrumpida");
        }
        
    }
}
