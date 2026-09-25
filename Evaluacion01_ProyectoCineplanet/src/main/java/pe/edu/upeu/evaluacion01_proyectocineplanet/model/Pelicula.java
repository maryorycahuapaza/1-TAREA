package pe.edu.upeu.evaluacion01_proyectocineplanet.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pe.edu.upeu.evaluacion01_proyectocineplanet.enums.EstadoPelicula;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {
    private Long idPelicula;
    @NotBlank(message = "El nombre de la pelicula es obligatorio")
    private String nombre;
    @NotNull(message = "El estado de la pelicula es obligatorio")
    private EstadoPelicula estado;
    @NotNull(message = "El genero de la pelicula es obligatorio")
    private Genero idGenero;
    @NotNull(message = "La clasificacion de la pelicula es obligatoria")
    private Clasificacion idClasificacion;
    @NotNull(message = "El formato de la pelicula es obligatorio")
    private Formato idFormato;
    @NotBlank(message = "La duracion de la pelicula es obligatorio")
    private String duracion;



}

