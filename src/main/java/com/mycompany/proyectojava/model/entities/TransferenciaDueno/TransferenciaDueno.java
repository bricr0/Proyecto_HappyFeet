package com.mycompany.proyectojava.model.entities.TransferenciaDueno;

public class TransferenciaDueno {
    private Integer mascotaId;
    private Integer nuevoDuenoId;

    public TransferenciaDueno(Integer mascotaId, Integer nuevoDuenoId) {
        this.mascotaId = mascotaId;
        this.nuevoDuenoId = nuevoDuenoId;
    }

    public Integer getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Integer mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Integer getNuevoDuenoId() {
        return nuevoDuenoId;
    }

    public void setNuevoDuenoId(Integer nuevoDuenoId) {
        this.nuevoDuenoId = nuevoDuenoId;
    }
}
