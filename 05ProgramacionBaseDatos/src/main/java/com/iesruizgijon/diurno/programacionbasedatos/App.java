package com.iesruizgijon.diurno.programacionbasedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        
        final String USER = "root";
        final String PASS = "123qweASD_";
        final String nameDB = "northwind";
        
        BaseDatos bd = new BaseDatos(USER, PASS, nameDB);
                
        bd.Conecta();
        
        bd.describe("orders");
        
        bd.Desconecta();
    }
}
