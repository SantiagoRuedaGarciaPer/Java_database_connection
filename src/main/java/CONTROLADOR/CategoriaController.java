package CONTROLADOR;

import MODELO.Categoria;
import DAO.CategoriaDAO;
import VISTA.Validaciones;
import java.util.ArrayList;


public class CategoriaController {
    
    CategoriaDAO cd = new CategoriaDAO();

    
    public void insert(String nombre, String descripcion){
        Categoria categoria = new Categoria(0, nombre, descripcion);
        cd.create(categoria);
        
    }
    
    public void delete(int id){
        Categoria c = cd.buscar(id);
        cd.delete(c);
    }
    
    public void listar() {
        cd.listar().forEach(System.out::println);
    }
    
    
    public Categoria buscar(int id){
        Categoria c = cd.buscar(id);
        return c;
    }
    
    public void actualizar(int id){
        Categoria c = cd.buscar(id);
        Validaciones v = new Validaciones();
        if(c==null){
            System.out.println("NO SE ENCUENTRA DICHA CATEGORIA");
        }else{
            int op = v.validarEnteroRango("""
                                          Digite la opcion
                                          1.    nombre
                                          2.    descripcion
                                          3.    cancela""", 3, 1);
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    c.setNombre(v.validarTexto("Ingrese el nuevo nombre"));
                    break;
                case 2:
                    System.out.println("Ingrese la nueva descripcion");
                    c.setNombre(v.validarTexto("Ingrese la nueva descripcion"));
                    break;
                case 3:
                    System.out.println("Operaciopn cancelada");
                    break;
            }
            
        }
    }
    
}
