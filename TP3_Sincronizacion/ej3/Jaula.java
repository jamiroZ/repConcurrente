package TP3_Sincronizacion.ej3;

public class Jaula {
      private Hamaca hamaca;
      private Plato plato;
      private Rueda rueda;
      public Jaula(Hamaca hamaca,Plato plato, Rueda rueda){
           this.hamaca=hamaca;
           this.plato=plato;
           this.rueda=rueda;
      }
      public void entrarJaula(){
           this.plato.comer();
           this.rueda.jugar();
           this.hamaca.duerme();
      }
}
