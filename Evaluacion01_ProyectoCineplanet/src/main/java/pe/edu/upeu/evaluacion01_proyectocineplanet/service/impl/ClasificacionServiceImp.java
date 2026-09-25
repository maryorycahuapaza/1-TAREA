package pe.edu.upeu.evaluacion01_proyectocineplanet.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Clasificacion;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.ClasificacionRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.ICrudGenericoRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.IClasificacionService;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class ClasificacionServiceImp extends CrudGenericoServiceImp<Clasificacion, Long> implements IClasificacionService {
    private final ClasificacionRepository clasificacionRepository;

    @Override
    protected ICrudGenericoRepository<Clasificacion, Long> getRepo() {
        return clasificacionRepository;
    }

    @Override
    public List<ComboBoxOption> listClasificacion() {
        if (clasificacionRepository.findAll().isEmpty()) {
            clasificacionRepository.seedData();
        }
        List<ComboBoxOption> listar = new ArrayList<>();
        for (Clasificacion cat : clasificacionRepository.findAll()) {
            ComboBoxOption cb = new ComboBoxOption();
            cb.setKey(String.valueOf(cat.getIdClasificacion()));
            cb.setValue(cat.getNombre());
            listar.add(cb);
        }
        return listar;
    }
}