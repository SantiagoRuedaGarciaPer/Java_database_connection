package DAO;

import CONTROLADOR.Conexion;
import MODELO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ProductoDAO {
    Conexion c = new Conexion();
    
    public void crear(Producto p){
        try(Connection con = c.conectar()) {
        PreparedStatement ps = con.prepareStatement("Insert into Productos(nombre, descripcion, categoria_id, stock, precio_compra, precio_venta) values (?, ?, ?, ?, ?, ?)");
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getDescripcion());
        ps.setInt(3, p.getCategoria().getId());
        ps.setInt(4, p.getStock());
        ps.setDouble(5, p.getPrecio_compra());
        ps.setDouble(6, p.getPrecio_venta());
        ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Producto p){
        try(Connection con = c.conectar()) {
        PreparedStatement ps = con.prepareStatement("update Productos set nombre = ?, descripcion=?, categoria_id=?, stock=?, precio_compra=?, precio_venta=?");
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getDescripcion());
        ps.setInt(3, p.getCategoria().getId());
        ps.setInt(4, p.getStock());
        ps.setDouble(5, p.getPrecio_compra());
        ps.setDouble(6, p.getPrecio_venta());
        ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Producto> listar() {
        CategoriaDAO cd = new CategoriaDAO();
        ArrayList<Producto> respuesta = new ArrayList<>();
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from Productos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria cat = cd.buscar(Integer.parseInt(rs.getString(4)));
                respuesta.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), cat, rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public Producto buscar(int id){
        CategoriaDAO cd = new CategoriaDAO();
        Producto result = null;
        try (Connection con=c.conectar()){
            PreparedStatement ps = con.prepareStatement("select * from Productos where id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Categoria cat = cd.buscar(Integer.parseInt(rs.getString(4)));
                result = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), cat, rs.getInt(5), rs.getDouble(6), rs.getDouble(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public void delete(Producto producto){
        int op = JOptionPane.showConfirmDialog(null, "Esta segur@ de eliminar a "+producto.getNombre()+"?", null, JOptionPane.YES_NO_OPTION);
        if(op == 0){
            try (Connection con=c.conectar()){
                PreparedStatement ps = con.prepareStatement("delete from Producto where id=?");
                ps.setInt(1, producto.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }    
        }else{
            JOptionPane.showInternalMessageDialog(null, "Operacion cancelada");
        }
    }
    
}
