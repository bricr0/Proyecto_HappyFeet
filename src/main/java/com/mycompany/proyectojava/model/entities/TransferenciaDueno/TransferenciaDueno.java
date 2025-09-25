package com.mycompany.proyectojava.model.entities.TransferenciaDueno;

public class TransferenciaDueno {
    private Integer id;
    private Integer duenoId;
    private Integer nuevoDuenoId;

    public TransferenciaDueno(Integer id, Integer duenoId, Integer nuevoDuenoId) {
        this.id = id;
        this.duenoId = duenoId;
        this.nuevoDuenoId = nuevoDuenoId;
    }

    public TransferenciaDueno(Integer duenoId, Integer nuevoDuenoId) {
        this.duenoId = duenoId;
        this.nuevoDuenoId = nuevoDuenoId;
    }
}
