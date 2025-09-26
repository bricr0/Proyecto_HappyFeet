package com.mycompany.proyectojava.repository.TransferenciaDueno;

import com.mycompany.proyectojava.config.database.ConexionDBSingleton;
import com.mycompany.proyectojava.model.entities.Mascota.Mascota;
import com.mycompany.proyectojava.model.entities.TransferenciaDueno.TransferenciaDueno;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TransferenciaDuenoDAO implements ITransferenciaDueno{
    private final Connection conexion;

    public TransferenciaDuenoDAO() {
        this.conexion = ConexionDBSingleton.getInstance().getConnection();
    }

//    ----------------------------------------------- TRANSFERIR DUENO ----------------------------------------------

    @Override
    public void transferirDueno(TransferenciaDueno transferenciaDueno) {
        String sql = "{CALL transferir_propiedad(?, ?)}";

        try (CallableStatement stmt = conexion.prepareCall(sql)) {
            stmt.setInt(1, transferenciaDueno.getMascotaId());
            stmt.setInt(2, transferenciaDueno.getNuevoDuenoId());
            stmt.execute();
            System.out.println("✅ Propiedad transferida con éxito.");
        } catch (SQLException e) {
            System.out.println("❌ Error al transferir propiedad: " + e.getMessage());
        }
    }

}
