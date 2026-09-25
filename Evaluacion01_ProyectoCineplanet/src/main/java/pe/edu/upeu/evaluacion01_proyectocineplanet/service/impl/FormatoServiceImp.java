package pe.edu.upeu.evaluacion01_proyectocineplanet.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Formato;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.FormatoRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.ICrudGenericoRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.IFormatoService;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class FormatoServiceImp extends CrudGenericoServiceImp<Formato, Long> implements IFormatoService {
    private final FormatoRepository formatoRepository;

    @Override
    protected ICrudGenericoRepository<Formato, Long> getRepo() {
        return formatoRepository;
    }

    @Override
    public List<ComboBoxOption> listarCombobox() {
        if(formatoRepository.findAll().isEmpty()) {
            formatoRepository.seedData();
        }
        List<ComboBoxOption> listar = new ArrayList<>();
        for (Formato f : formatoRepository.findAll()) {
            ComboBoxOption cb = new ComboBoxOption();
            cb.setKey(String.valueOf(f.getIdFormato()));
            cb.setValue(f.getNombre());
            listar.add(cb);
        }
        return listar;

    }
}