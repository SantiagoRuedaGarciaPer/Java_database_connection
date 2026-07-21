package DAO;

import CONTROLADOR.Conexion;
import MODELO.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class CategoriaDAO {
    private Conexion c =  new Conexion();
    
    
    
    public void create(Categoria categoria){
        try (Connection con=c.conectar()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO Categoria(nombre, descripcion) values (?, ?)");
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Categoria categoria){
        try (Connection con=c.conectar()){
            PreparedStatement ps = con.prepareStatement("update Categoria set nombre=?, descripcion=? where id=?");
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setString(3, String.valueOf(categoria.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Categoria categoria){
        int op = JOptionPane.showConfirmDialog(null, "Esta segur@ de eliminar a "+categoria.getNombre()+"?", null, JOptionPane.YES_NO_OPTION);
        if(op == 0){
            try (Connection con=c.conectar()){
                PreparedStatement ps = con.prepareStatement("delete from Categoria where id=?");
                ps.setInt(1, categoria.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }    
        }else{
            JOptionPane.showInternalMessageDialog(null, "Operacion cancelada");
        }
        
    }
    
    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> respuesta = new ArrayList<>();
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from Categoria");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                respuesta.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public Categoria buscar(int id){
        Categoria result = null;
        try (Connection con=c.conectar()){
            PreparedStatement ps = con.prepareStatement("select * from Categoria where id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
}
