package com.mycompany.proyectojava.model.enums.Sexo;

public enum Sexo {
    Macho, Hembra;

    public static Sexo fromString(String sexo) {
            return Sexo.valueOf(sexo);
    }

}


