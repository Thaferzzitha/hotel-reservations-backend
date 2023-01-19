/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.factory;

/**
 *
 * @author nesch
 */
public class ConnectionDbFactory {
    public static String[] configMYSQL = {"jdbc:mysql://localhost:3306/hotel_reservations", "root", ""};  //configrar copnexion: nombre bd, usr, clave
    public static DbConnection open(){          //abrir bd mysql
        return new MySqlConnection(configMYSQL);  //no hace falta break porque esta return
     }
}
