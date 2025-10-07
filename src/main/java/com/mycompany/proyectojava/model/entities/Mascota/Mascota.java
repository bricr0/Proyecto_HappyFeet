package com.mycompany.proyectojava.model.entities.Mascota;

import com.mycompany.proyectojava.model.entities.Dueno.Dueno;
import com.mycompany.proyectojava.model.entities.HistorialExamen.ElementosHistorial;
import com.mycompany.proyectojava.model.entities.Razas.Razas;

import java.util.Date;
import java.util.List;

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
    private Dueno dueno;
    private Razas raza;
    private String estado;
    private List<ElementosHistorial> elementos;

    public Mascota(Integer id, Integer dueno_id, String nombre, Integer raza_id, Date fecha_nacimiento, String sexo, String microchip, String foto_url, String alergias, String condiciones_preexistentes, Double peso_kg, String notas_medicas, Dueno dueno, Razas raza, String estado, List<ElementosHistorial> elementos) {
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
        this.dueno = dueno;
        this.raza = raza;
        this.estado = estado;
        this.elementos = elementos;
    }

    public Mascota(Integer id, Integer dueno_id, String nombre, Integer raza_id, Date fecha_nacimiento, String sexo, String microchip, String foto_url, String alergias, String condiciones_preexistentes, Double peso_kg, String notas_medicas, Dueno dueno, Razas raza, String estado) {
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
        this.dueno = dueno;
        this.raza = raza;
        this.estado = estado;
    }

    public Mascota(Integer dueno_id, String nombre, Integer raza_id, Date fecha_nacimiento, String sexo, String microchip, String foto_url, String alergias, String condiciones_preexistentes, Double peso_kg, String notas_medicas, String estado) {
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
        this.estado = estado;
    }

    public Mascota() {

    }
    // Getters and Setters

    public List<ElementosHistorial> getElementos() {
        return elementos;
    }

    public void setElementos(List<ElementosHistorial> elementos) {
        this.elementos = elementos;
    }

    public Mascota(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public Razas getRaza() {
        return raza;
    }

    public void setRaza(Razas raza) {
        this.raza = raza;
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
        return  "\n| dueño=                   " + (dueno != null ? dueno.getNombre() : "N/A") +
                "\n| nombre=                  " + nombre +
                "\n| raza=                    " + (raza != null ? raza.getNombre() : "N/A") +
                "\n| fecha_nacimiento=        " + fecha_nacimiento +
                "\n| sexo=                    " + sexo +
                "\n| microchip=               " + microchip +
                "\n| alergias=                " + alergias +
                "\n| condiciones_preexistentes=" + condiciones_preexistentes +
                "\n| peso_kg=                 " + peso_kg +
                "\n| notas_medicas=           " + notas_medicas +
                "\n****************************************************************";
    }
}
