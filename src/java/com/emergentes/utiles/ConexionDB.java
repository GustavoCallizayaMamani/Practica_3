package com.emergentes.utiles;
import java.sql.DriverManager;
import java.sql.*;

public class ConexionDB {

    static String url = "jdbc:mysql://localhost:3306/bd_almacen";
    static String usuario = "root";
    static String password = "";
    
    protected Connection conn = null;
    
    public ConexionDB()
    {
        try {
            conn = DriverManager.getConnection(url, usuario, password);
            if(conn != null)
            {
                System.out.println("Conexion Ok: " + conn);
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error de SQL: " + ex.getMessage());
        }
    }
    
    public Connection conectar()
    {
        return conn;
    }
    
    public void desconectar()
    {
        System.out.println("Cerrando la BD:" + conn);
        try
        {
            conn.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Error de SQL: " + ex.getMessage());
        }
    }
}
