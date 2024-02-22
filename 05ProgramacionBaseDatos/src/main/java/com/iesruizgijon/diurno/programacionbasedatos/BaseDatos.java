package com.iesruizgijon.diurno.programacionbasedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
    
    private Connection conexion;
    private String USER;
    private String PASS;
    private String nameDB;
    private String URL = "jdbc:mysql://localhost:3306/";

    public BaseDatos(String USER, String PASS, String nameDB) {
        this.USER = USER;
        this.PASS = PASS;
        this.nameDB = nameDB;
    }
    
    public void Conecta(){
        try {
            conexion = DriverManager.getConnection(URL + nameDB, USER, PASS);
            System.out.println("Conexión establecida...");
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Desconecta(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultaPrueba(){
        String company;
        String apellidos;
        String nombre;
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select company, last_name, first_name from customers limit 10");
            while (resultado.next()) {
                company = resultado.getString("company");
                apellidos = resultado.getString("last_name");
                nombre = resultado.getString("first_name");

                System.out.println("Company " + company + " Apellidos " + apellidos + " Nombre " + nombre);

            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void consultaPrueba2() {
        int numeroPedidos=0;
        String nombre;
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select CONCAT(customers.first_name, \" \", customers.last_name) as Nombre, COUNT(orders.customer_id) as NumeroPedidos from customers inner join orders on customers.id=orders.customer_id GROUP BY(Nombre)");
            while (resultado.next()) {
                numeroPedidos = resultado.getInt("NumeroPedidos");
                nombre = resultado.getString("Nombre");

                System.out.println("Nombre: " + nombre + ", Numero de Pedidos: " + numeroPedidos);

            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String[] describe(String nombre) {

        String[] columnas = null;

        int n_columnas = 0;

        int i = 0;

        try {

            Statement statement = conexion.createStatement();

            ResultSet resultset = statement.executeQuery("SELECT * FROM " + nombre);

            ResultSetMetaData metadatos = resultset.getMetaData();

            n_columnas = metadatos.getColumnCount();

            columnas = new String[n_columnas];

            for (i = 1; i <= n_columnas; i++) {

                columnas[i - 1] = metadatos.getColumnName(i);

            }

        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);

        }

        return columnas;

    }
    
}
