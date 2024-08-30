package TP3_Sincronizacion.ej1;

public class CuentaBanco {
    private int balance = 50;
    public CuentaBanco(){//CONSTRUCTOR VACIO
    }
    public int getBalance(){//RETORNA EL BALANCE =50
       return balance;
    }
    public void retiroBancario(int retiro){//MODIFICA EL BALANCE
       balance = balance - retiro;
    }
}

   