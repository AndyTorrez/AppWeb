// Archivo: MiTareaWeb1/src/main/java/com/tarea/modelo/UsuarioDAO.java
package com.tarea.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // *** CONFIGURACIÓN DE TU BASE DE DATOS ***
    // La BD se llama 'users_crud_php' según tu archivo connection.php
    private String jdbcURL = "jdbc:mysql://localhost:3306/users_crud_php"; 
    private String jdbcUsername = "root";   
    private String jdbcPassword = "";       
    // *****************************************

    // SQL QUERIES
    private static final String INSERT_SQL = "INSERT INTO users (name, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, lastname, username, password, email FROM users WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, name, lastname, username, password, email FROM users";
    private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, lastname = ?, username = ?, password = ?, email = ? WHERE id = ?";// 6 parámetros, el último es WHERE ID

    // MÉTODOS CRUD (CREATE, READ, UPDATE, DELETE)

    public void insertarUsuario(Usuario usuario) throws SQLException {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            
            preparedStatement.setString(1, usuario.getName());
            preparedStatement.setString(2, usuario.getLastname());
            preparedStatement.setString(3, usuario.getUsername());
            preparedStatement.setString(4, usuario.getPassword());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public Usuario seleccionarUsuario(int id) {
        // ... (el código del método SELECT_BY_ID es el mismo que te pasé antes)
        Usuario usuario = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                usuario = new Usuario(id, name, lastname, username, password, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> seleccionarTodos() {
        // ... (el código del método SELECT_ALL es el mismo que te pasé antes)
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                usuarios.add(new Usuario(id, name, lastname, username, password, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            
            preparedStatement.setString(1, usuario.getName());
            preparedStatement.setString(2, usuario.getLastname());
            preparedStatement.setString(3, usuario.getUsername());
            preparedStatement.setString(4, usuario.getPassword());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setInt(6, usuario.getId()); // Condición WHERE
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean eliminarUsuario(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}