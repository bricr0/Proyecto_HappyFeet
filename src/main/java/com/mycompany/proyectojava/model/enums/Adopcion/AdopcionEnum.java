package com.mycompany.proyectojava.model.enums.Adopcion;

public enum AdopcionEnum {
    ADOPCION, TEMPORAL;

    public static AdopcionEnum fromString(String valor) {
        if (valor == null) return null;

        String v = valor.trim().toUpperCase();

        for (AdopcionEnum tipo : AdopcionEnum.values()) {
            if (tipo.name().equalsIgnoreCase(v)) {
                return tipo;
            }
        }

        System.out.println("⚠️ Tipo de adopción desconocido en BD: " + valor);
        return null;
    }
}