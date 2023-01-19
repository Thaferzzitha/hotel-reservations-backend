/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.factory;

import java.sql.*;

/**
 *
 * @author nesch
 */
public class MySqlConnection extends DbConnection{
    public MySqlConnection(String[] params) {  //recibve un array de string parametros
        this.params = params;    //el atributo params de superclase recibe el parametro del constructor
        this.open();        //llama al metodo que se implementa abajo
    }
    private static Connection conn;
    private static final String driver="com.mysql.jdbc.Driver";
    @Override
    public Connection open() {   //implementacion del metodo abstracto que abre bd
        try{
            Class.forName(driver);
            String url = this.params[0];
            String user = this.params[1];
            String password = this.params[2];
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            if(connection!=null){
                System.out.println("Conexión establecida");
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error de conexion " + e);
        }
        return this.connection;     //devuelve la conexion q es atributo declarada en superclase
    }
}
