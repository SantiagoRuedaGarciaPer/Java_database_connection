package CONTROLADOR;

import DAO.CategoriaDAO;
import DAO.ProductoDAO;
import MODELO.Categoria;
import MODELO.Producto;
import VISTA.Validaciones;
import java.util.ArrayList;
import java.util.Scanner;


public class ProductoController {

    ProductoDAO pd = new ProductoDAO();
    
    public Categoria validarCategoria(){
        Validaciones v = new Validaciones();
        
        CategoriaDAO cd = new CategoriaDAO();
        ArrayList<Categoria> cat =cd.listar();
        cat.forEach(System.out::println);
        
        int codigoCategoria = v.validarEntero("Ingrese el codigo de la categoria");
        
        
        while(cd.buscar(codigoCategoria) == null){
            codigoCategoria = v.validarEntero("Categoria no encontrada, intente nuevamente");
        }
        return cd.buscar(codigoCategoria);
    }
    
    public void insert(Producto p){
        pd.crear(p);
    }
    
    public void update(Producto p, Categoria c){
        pd.update(p);
    }
    
    public void listar(){
        pd.listar().forEach(System.out::println);
    }
    
    public void mostrar(){
        ArrayList<Producto> ps = pd.listar();
        for(Producto p : ps){
            System.out.println(p.getId()+" "+p.getNombre()+" --- "+p.getDescripcion());
        }
    }
    
    public Producto buscar(int id){
        return pd.buscar(id);
    }
    
    public void actualizar(int id){
        CategoriaDAO cd = new CategoriaDAO();
        Producto producto = pd.buscar(id);
        Validaciones v = new Validaciones();
        if(producto==null){
            System.out.println("NO SE ENCUENTRA DICHA CATEGORIA");
        }else{
            int op = v.validarEnteroRango("""
                                          Digite la opcion
                                          1.    nombre
                                          2.    descripcion
                                          3.    Categoria
                                          4.    Stock
                                          5.    Precio compra
                                          6.    Precio venta
                                          7.    Salir""", 7, 1);
            switch (op) {
                case 1:
                    producto.setNombre(v.validarTexto("Ingrese el nuevo nombre"));
                    break;
                case 2:
                    producto.setNombre(v.validarTexto("Ingrese la nueva descripcion"));
                    break;
                case 3:
                    Categoria c = validarCategoria();
                    producto.setCategoria(c);
                    break;
                case 4:
                    int stock = v.validarEntero("Ingrese el nuevo Stock");
                    producto.setStock(stock);
                    break;
                case 5:
                    double precio_compra = v.validarDecimal("Ingrese el nuevo precio de compra");
                    producto.setPrecio_compra(precio_compra);
                    break;
                case 6:
                    double precio_venta = v.validarDecimal("Ingrese el nuevo precio de venta");
                    producto.setPrecio_compra(precio_venta);
                    break;
                case 7:
                    System.out.println("Operacion cancelada");
                    break;
            }
            pd.update(producto);
        }
    }
    
    public void delete(int id){
        Producto p = pd.buscar(id);
        pd.delete(p);
    }
    
}
