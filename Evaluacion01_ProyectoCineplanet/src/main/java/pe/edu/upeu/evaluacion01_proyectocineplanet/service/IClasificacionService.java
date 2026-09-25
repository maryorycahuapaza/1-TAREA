package pe.edu.upeu.evaluacion01_proyectocineplanet.service;

import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Clasificacion;

import java.util.List;

public interface IClasificacionService extends ICrudGenericoService<Clasificacion, Long> {
    List<ComboBoxOption> listClasificacion();
}
