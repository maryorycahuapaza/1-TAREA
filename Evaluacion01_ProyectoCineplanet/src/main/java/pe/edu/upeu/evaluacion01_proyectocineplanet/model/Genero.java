package pe.edu.upeu.evaluacion01_proyectocineplanet.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Genero {
    private Long idGenero;
    @ToString.Include // <-- Solo este campo se mostrará en la tabla
    private String nombre;
}
