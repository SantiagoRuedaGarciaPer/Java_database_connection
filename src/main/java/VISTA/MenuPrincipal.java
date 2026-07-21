package VISTA;


public class MenuPrincipal {
    public void menu(){
        Validaciones v = new Validaciones();
        int op = 0;
        do {            
            
            op = v.validarEnteroRango("""
                                      BIENVENIDOS A NUESTRO SISTEMA
                                      Digite la opcion a escoger
                                      1. Categorias
                                      2. Productos
                                      3. Salir
                                      """, 3, 1);
            
            switch (op) {
                case 1:
                    MenuCategoria mc = new MenuCategoria();
                    mc.menu();
                    break;
                case 2:
                    MenuProductos mp = new MenuProductos();
                    mp.menu();
                case 3:
                    System.out.println("Hasta luego");
            }
            
        } while (op !=3);
    }
}
