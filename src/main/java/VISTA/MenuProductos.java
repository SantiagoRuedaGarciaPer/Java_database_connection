package VISTA;

import CONTROLADOR.ProductoController;
import MODELO.Categoria;
import MODELO.Producto;


public class MenuProductos {
    public void menu(){
        Validaciones v = new Validaciones();
        ProductoController pc = new ProductoController();
        int op = 0;
        do{
            op = v.validarEnteroRango("""
                                        Digite la opcion
                                        1.    Agregar
                                        2.    Eliminar
                                        3.    Actualizar
                                        4.    Buscar
                                        5.    Listar
                                        6.    Salir
                                        """, 6, 1);
          switch (op) {
              case 1:
                  System.out.println("==========Modulo Agregar==========");
                  String nombre = v.validarTexto("ingrese el nombre");
                  String descripcion = v.validarTexto("Ingrese la descripcion");
                  Categoria categoria = pc.validarCategoria();
                  int stock = v.validarEntero("ingrese el stock");
                  double precio_compra = v.validarDecimal("ingrese el precio de la compra");
                  double precio_venta = v.validarDecimal("ingrese el precio de venta");
                  pc.insert(new Producto(0, nombre, descripcion, categoria, stock, precio_compra, precio_venta));
                  break;
              case 2:
                  pc.listar();
                  int idEliminar = v.validarEntero("Ingrese el id");
                  pc.delete(idEliminar);
                  break;
              case 3:
                  pc.mostrar();
                  int idActualizar = v.validarEntero("ingrese el id");
                  pc.actualizar(idActualizar);
                  break;
              case 4:
                  int idBuscar = v.validarEntero("ingrese el id");
                  System.out.println(pc.buscar(idBuscar));
                  break;
              case 5:
                  pc.mostrar();
                  break;
              case 6:
                  System.out.println("Hasta luego");
                  break;
          }
        }while(op !=6);
    }
}
