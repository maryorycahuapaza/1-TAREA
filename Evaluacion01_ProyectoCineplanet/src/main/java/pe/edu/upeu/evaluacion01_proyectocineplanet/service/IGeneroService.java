package pe.edu.upeu.evaluacion01_proyectocineplanet.service;

import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Genero;

import java.util.List;

public interface IGeneroService extends ICrudGenericoService<Genero, Long> {
    List<ComboBoxOption> listGenero();
}
