package pe.edu.upeu.evaluacion01_proyectocineplanet.enums;

import lombok.Getter;

@Getter
public enum EstadoPelicula {
    PREVENTA("Preventa"),
    ESTRENO("Estreno"),
    CARTELERA("Cartelera");
    String descripcion;

    EstadoPelicula(String descripcion) {
        this.descripcion = descripcion;
    }
}
