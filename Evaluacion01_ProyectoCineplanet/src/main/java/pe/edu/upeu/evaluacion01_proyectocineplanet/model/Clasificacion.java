package pe.edu.upeu.evaluacion01_proyectocineplanet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clasificacion {
    private Long idClasificacion;
    private String nombre;
}
