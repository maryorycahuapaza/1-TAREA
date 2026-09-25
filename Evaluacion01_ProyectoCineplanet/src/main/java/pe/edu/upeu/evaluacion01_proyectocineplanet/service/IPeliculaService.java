package pe.edu.upeu.evaluacion01_proyectocineplanet.service;

import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Pelicula;

import java.util.List;

public interface IPeliculaService extends ICrudGenericoService<Pelicula, Long> {
    List<ComboBoxOption> listarEstadoPelicula();
}
