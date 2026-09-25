package pe.edu.upeu.evaluacion01_proyectocineplanet.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Formato {
    private Long idFormato;
    @ToString.Include // <-- Solo este campo se mostrará en la tabla
    private String nombre;

}
