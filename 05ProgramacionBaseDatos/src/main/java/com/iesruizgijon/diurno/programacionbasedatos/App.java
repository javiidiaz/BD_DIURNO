package com.iesruizgijon.diurno.programacionbasedatos;

public class App {

    public static void main(String[] args) {
        
        final String USER = "root";
        final String PASS = "123qweASD_";
        final String nameDB = "classicmodels";
        
        BaseDatos bd = new BaseDatos(USER, PASS, nameDB);
                
        bd.Conecta();
        
        bd.getDataBaseNames();
        
        bd.Desconecta();
    }
}
