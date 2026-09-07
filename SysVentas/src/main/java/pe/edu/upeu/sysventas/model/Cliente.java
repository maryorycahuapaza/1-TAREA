package pe.edu.upeu.sysventas.model;

import lombok.Data;
import pe.edu.upeu.sysventas.enums.TipoDocumento;
@Data
public class Cliente {
    String dniRu;
    String nombres;
    String repLel;
    TipoDocumento tipoDocumento;
}

