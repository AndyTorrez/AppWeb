// Archivo: MiTareaWeb1/src/main/java/com/tarea/modelo/Usuario.java
package com.tarea.modelo;

public class Usuario {
    private int id;
    private String name;        
    private String lastname;    
    private String username;    
    private String password;    
    private String email;       

    // Constructor vacío (necesario para Java Beans)
    public Usuario() {} 
    
    // Constructor completo (sin ID, para INSERT)
    public Usuario(String name, String lastname, String username, String password, String email) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    // Constructor con ID (para SELECT y UPDATE)
    public Usuario(int id, String name, String lastname, String username, String password, String email) {
        this(name, lastname, username, password, email);
        this.id = id;
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
