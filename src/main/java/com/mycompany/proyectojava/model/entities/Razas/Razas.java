package com.mycompany.proyectojava.model.entities.Razas;

public class Razas {
    private Integer id;
    private String nombre;
    private Integer especie_id;

    public Razas(Integer id, String nombre, Integer especie_id) {
        this.id = id;
        this.nombre = nombre;
        this.especie_id = especie_id;
    }

    public Razas(String nombre, Integer especie_id) {
        this.nombre = nombre;
        this.especie_id = especie_id;
    }

    public Razas() {

    }

// Getters and Setters


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEspecie_id() {
        return especie_id;
    }


    @Override
    public String toString() {
        return  "\n| nombre=                  " + nombre +
                "\n| especie_id=              " + especie_id +
                "\n****************************************************************";
    }
}
