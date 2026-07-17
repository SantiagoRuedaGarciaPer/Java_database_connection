/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cp.integrador.crud_producto;

import CONTROLADOR.Conexion;

/**
 *
 * @author camper
 */
public class CRUD_PRODUCTO {

    public static void main(String[] args) {
        Conexion c = new Conexion();
        c.conectar();
        System.out.println("Conexion exitosa");
    }
}
