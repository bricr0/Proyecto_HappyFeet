package com.mycompany.proyectojava.model.enums.Adopcion;


public enum AdopcionEstadoEnum {
    pendiente, completada, rechazada, cancelada;

    public static AdopcionEstadoEnum fromString(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            return pendiente;
        }
        String limpio = estado.trim();
        for (AdopcionEstadoEnum e : AdopcionEstadoEnum.values()) {
            if (e.name().equalsIgnoreCase(limpio)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado de adopción inválido: [" + estado + "]");
    }
}