package com.mycompany.proyectojava.model.entities.Mascota;

import java.util.Date;

public class Mascota {
    private Integer id;
    private Integer dueno_id;
    private String nombre;
    private Integer raza_id;
    private Date fecha_nacimiento;
    private String sexo;
    private String microchip;
    private String foto_url;
    private String alergias;
    private String condiciones_preexistentes;
    private Double peso_kg;
    private String notas_medicas;

    public Mascota(Integer id, Integer dueno_id, String nombre, Integer raza_id, Date fecha_nacimiento, String sexo, String microchip, String foto_url, String alergias, String condiciones_preexistentes, Double peso_kg, String notas_medicas) {
        this.id = id;
        this.dueno_id = dueno_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.microchip = microchip;
        this.foto_url = foto_url;
        this.alergias = alergias;
        this.condiciones_preexistentes = condiciones_preexistentes;
        this.peso_kg = peso_kg;
        this.notas_medicas = notas_medicas;
    }

    public Mascota(Integer dueno_id, String nombre, Integer raza_id, Date fecha_nacimiento, String sexo, String microchip, String foto_url, String alergias, String condiciones_preexistentes, Double peso_kg, String notas_medicas) {
        this.dueno_id = dueno_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.microchip = microchip;
        this.foto_url = foto_url;
        this.alergias = alergias;
        this.condiciones_preexistentes = condiciones_preexistentes;
        this.peso_kg = peso_kg;
        this.notas_medicas = notas_medicas;
    }

    // Getters and Setters


    public Integer getId() {
        return id;
    }

    public Integer getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(Integer dueno_id) {
        this.dueno_id = dueno_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRaza_id() {
        return raza_id;
    }

    public void setRaza_id(Integer raza_id) {
        this.raza_id = raza_id;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondiciones_preexistentes() {
        return condiciones_preexistentes;
    }

    public void setCondiciones_preexistentes(String condiciones_preexistentes) {
        this.condiciones_preexistentes = condiciones_preexistentes;
    }

    public Double getPeso_kg() {
        return peso_kg;
    }

    public void setPeso_kg(Double peso_kg) {
        this.peso_kg = peso_kg;
    }

    public String getNotas_medicas() {
        return notas_medicas;
    }

    public void setNotas_medicas(String notas_medicas) {
        this.notas_medicas = notas_medicas;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", dueno_id=" + dueno_id +
                ", nombre='" + nombre + '\'' +
                ", raza_id=" + raza_id +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", sexo='" + sexo + '\'' +
                ", microchip='" + microchip + '\'' +
                ", foto_url='" + foto_url + '\'' +
                ", alergias='" + alergias + '\'' +
                ", condiciones_preexistentes='" + condiciones_preexistentes + '\'' +
                ", peso_kg=" + peso_kg +
                ", notas_medicas='" + notas_medicas + '\'' +
                '}';
    }

}
