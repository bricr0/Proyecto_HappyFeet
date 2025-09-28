package com.mycompany.proyectojava.model.entities.Veterinarios;

public class Veterinarios {
    private Integer id;
    private String nombre_completo;
    private String telefono;
    private String email;

    public Veterinarios(Integer id, String nombre_completo, String telefono, String email) {
        this.id = id;
        this.nombre_completo = nombre_completo;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
