package com.mycompany.proyectojava.model.entities.Proveedores;

public class Proveedores {
    private Integer id;
    private String nombre;
    private String contacto;
    private String telofono;
    private String email;

    public Proveedores(Integer id, String nombre, String contacto, String telofono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telofono = telofono;
        this.email = email;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelofono() {
        return telofono;
    }

    public void setTelofono(String telofono) {
        this.telofono = telofono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
