package pe.edu.upeu.sysventas.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Emisor {
    Long idEmisor;
    String ruc;
    String nombreComercial;
    String ubigeo;
    String domiciloFiscal;
    String urbanizacion;
    String departamento;
    String provincia;
    String distrito;
}
