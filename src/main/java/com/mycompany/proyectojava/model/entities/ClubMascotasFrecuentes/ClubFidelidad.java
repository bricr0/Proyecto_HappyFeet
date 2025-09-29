package com.mycompany.proyectojava.model.entities.ClubMascotasFrecuentes;

import com.mycompany.proyectojava.model.enums.ClubMascotasFrecuentes.ClubDeFidelidadNivel;

import java.time.LocalDateTime;

public class ClubFidelidad {
    private String nombreDueno;
    private String documentoDueno;
    private int puntosBalance;
    private ClubDeFidelidadNivel nivel;
    private LocalDateTime actualizado;

    public ClubFidelidad (){}

    public ClubFidelidad(String nombreDueno, String documentoDueno, int puntosBalance, ClubDeFidelidadNivel nivel, LocalDateTime actualizado) {
        this.nombreDueno = nombreDueno;
        this.documentoDueno = documentoDueno;
        this.puntosBalance = puntosBalance;
        this.nivel = nivel;
        this.actualizado = actualizado;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getDocumentoDueno() {
        return documentoDueno;
    }

    public void setDocumentoDueno(String documentoDueno) {
        this.documentoDueno = documentoDueno;
    }

    public int getPuntosBalance() {
        return puntosBalance;
    }

    public void setPuntosBalance(int puntosBalance) {
        this.puntosBalance = puntosBalance;
    }

    public ClubDeFidelidadNivel getNivel() {
        return nivel;
    }

    public void setNivel(ClubDeFidelidadNivel nivel) {
        this.nivel = nivel;
    }

    public LocalDateTime getActualizado() {
        return actualizado;
    }

    public void setActualizado(LocalDateTime actualizado) {
        this.actualizado = actualizado;
    }
}
