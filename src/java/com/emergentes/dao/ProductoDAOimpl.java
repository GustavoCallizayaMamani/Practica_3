package com.emergentes.dao;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
        
public class ProductoDAOimpl extends ConexionDB implements ProductoDAO{

    @Override
    public void insert(Producto producto) throws Exception {
        try
        {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement ("INSERT into productos (nombre_producto, precio, stock) values (?, ?, ?)");
            ps.setString(1, producto.getNombre_producto());
            ps.setFloat(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            this.desconectar();
        }  
    }

    @Override
    public void update(Producto producto) throws Exception {
        
        try
        {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement ("UPDATE productos set nombre_producto = ?, precio = ?, stock = ? where id = ?");
            ps.setString(1, producto.getNombre_producto());
            ps.setFloat(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try
        {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement ("DELETE FROM productos WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            this.desconectar();
        }
    }
    
    @Override
    public Producto getById(int id) throws Exception {
        Producto pro = new Producto();
        try
        {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement ("SELECT * FROM productos WHERE id = ? ");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                pro.setId(rs.getInt("id"));
                pro.setNombre_producto(rs.getString("nombre_producto"));
                pro.setPrecio(rs.getFloat("precio"));
                pro.setStock(rs.getInt("stock"));
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> lista = null;
        try
        {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement ("SELECT * FROM productos");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Producto>();
            
            while(rs.next())
            {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setNombre_producto(rs.getString("nombre_producto"));
                pro.setPrecio(rs.getFloat("precio"));
                pro.setStock(rs.getInt("stock"));
                
                lista.add(pro);
            }
            rs.close();
            ps.close();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            this.desconectar();
        }
        return lista;
    }
}

