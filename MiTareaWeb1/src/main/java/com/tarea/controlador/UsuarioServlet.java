// Archivo: MiTareaWeb1/src/main/java/com/tarea/controlador/UsuarioServlet.java
package com.tarea.controlador;

import com.tarea.modelo.UsuarioDAO;
import com.tarea.modelo.Usuario;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// El @WebServlet mapea esta clase a la URL /Usuario
@WebServlet("/Usuario") 
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        // En el patrón REST/MVC, POST maneja INSERT y UPDATE
        doGet(request, response); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String action = request.getParameter("action"); 
        
        if (action == null) { action = "list"; }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertUsuario(request, response);
                    break;
                case "delete":
                    deleteUsuario(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateUsuario(request, response);
                    break;
                default: // case "list"
                    listUsuario(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // --- Métodos del CRUD ---

    private void listUsuario(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Usuario> listUsuario = usuarioDAO.seleccionarTodos();
        request.setAttribute("listUsuario", listUsuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listado-usuarios.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-usuario.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUsuario(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        Usuario newUsuario = new Usuario(name, lastname, username, password, email);
        usuarioDAO.insertarUsuario(newUsuario);
        response.sendRedirect("Usuario?action=list"); // Redirige al listado
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario existingUsuario = usuarioDAO.seleccionarUsuario(id);
        
        request.setAttribute("usuario", existingUsuario); // Pasa el objeto para rellenar el form
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-usuario.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); 
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Usuario book = new Usuario(id, name, lastname, username, password, email);
        usuarioDAO.actualizarUsuario(book);
        response.sendRedirect("Usuario?action=list"); // Redirige al listado
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminarUsuario(id);
        response.sendRedirect("Usuario?action=list"); // Redirige al listado
    }
}