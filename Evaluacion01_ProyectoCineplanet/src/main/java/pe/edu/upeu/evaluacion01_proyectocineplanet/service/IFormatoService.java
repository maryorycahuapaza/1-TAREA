package pe.edu.upeu.evaluacion01_proyectocineplanet.service;

import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Formato;

import java.util.List;

public interface IFormatoService extends ICrudGenericoService<Formato, Long> {
    List<ComboBoxOption> listarCombobox();
}
