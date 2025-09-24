package com.mycompany.proyectojava.model.entities.Dueno;

public class Dueno {
    private Integer id;
    private String nombre;
    private String documento;
    private String direccion;
    private String telefono;
    private String email;
    private String estado;
    private String contacto_emergencia;

    public Dueno(Integer id, String nombre, String documento, String direccion, String telefono, String email, String estado, String contacto_emergencia) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.contacto_emergencia = contacto_emergencia;
    }

    public Dueno(String nombre, String documento, String direccion, String telefono, String email, String estado, String contacto_emergencia) {
        this.nombre = nombre;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.contacto_emergencia = contacto_emergencia;
    }

    // Getters and Setters

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getContacto_emergencia() {
        return contacto_emergencia;
    }

    public void setContacto_emergencia(String contacto_emergencia) {
        this.contacto_emergencia = contacto_emergencia;
    }

    @Override
    public String toString() {
        return "Dueno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", contacto_emergencia='" + contacto_emergencia + '\'' +
                '}';
    }
}
