/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author filor
 */
public class Conexion {
    Connection conectar = null;
    
    String usuario = "root";
    String contraseña = "10279Fig";
    String bd = "crud";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            System.out.println("Coneccion exitosa");
        
        
        }catch(Exception e){
            System.out.println("Error unu" + e.getMessage());
        }
        return conectar;
    }
    
}
    
    
