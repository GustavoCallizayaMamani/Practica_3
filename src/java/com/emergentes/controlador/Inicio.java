package com.emergentes.controlador;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emergentes.dao.ProductoDAO;

@WebServlet(name = "Inicio", urlPatterns = {"/inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAOimpl();
            int id;
            Producto pro = new Producto();
            String action = (request.getParameter("action") != null) ? request.getParameter("action"): "view";
            
            switch(action)
            {
                case "add":
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    
                break;
                    
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pro = dao.getById(id);
                    System.out.println(pro);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    
                break;
                    
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/inicio");
                    
                break;
                    
                case "view":
                    List<Producto> lista = dao.getAll();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    
                default:
                    
                break;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error "+ ex.getMessage());
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre_producto = request.getParameter("nombre_producto");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
              
        Producto pro = new Producto();
        
        pro.setId(id);
        pro.setNombre_producto(nombre_producto);
        pro.setPrecio(precio);
        pro.setStock(stock);
        
        if(id == 0)
        {
            try
            {
                ProductoDAO dao  = new ProductoDAOimpl();
                dao.insert(pro);
                response.sendRedirect(request.getContextPath()+"/inicio");
            }
            
            catch (Exception ex)
            {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else
        {
            try
            {
                ProductoDAO dao  = new ProductoDAOimpl();
                dao.update(pro);
                response.sendRedirect(request.getContextPath()+"/inicio");
            }
            catch (Exception ex)
            {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
