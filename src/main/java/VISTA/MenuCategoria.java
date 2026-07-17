package VISTA;

import CONTROLADOR.CategoriaController;



public class MenuCategoria {
    
    public void menu(){
        Validaciones v = new Validaciones();
        CategoriaController cc = new CategoriaController();
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
                  cc.insert(nombre, descripcion);
                  break;
              case 2:
                  cc.listar();
                  int idEliminar = v.validarEntero("Ingrese el id");
                  cc.delete(idEliminar);
                  break;
              case 3:
                  int idActualizar = v.validarEntero("ingrese el id");
                  cc.actualizar(idActualizar);
                  break;
              case 4:
                  int idBuscar = v.validarEntero("ingrese el id");
                  System.out.println(cc.buscar(idBuscar));
                  break;
              case 5:
                  cc.listar();
                  break;
              case 6:
                  System.out.println("Hasta luego");
                  break;
              default:
                  throw new AssertionError();
          }
        }while(op !=6);
    }
    
}
