package com.mycompany.proyectojava.model.entities.Especie;

public class Especie {
    private Integer id;
    private String nombre;


    public Especie(String nombre) {
        this.nombre = nombre;
    }

    public Especie(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  "\n| nombre=                  " + nombre +
                "\n****************************************************************";
    }
}

