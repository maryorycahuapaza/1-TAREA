package pe.edu.upeu.evaluacion01_proyectocineplanet.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Genero;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.GeneroRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.ICrudGenericoRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.IGeneroService;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class GeneroServiceImp extends CrudGenericoServiceImp<Genero, Long> implements IGeneroService {
    private final GeneroRepository generoRepository;

    @Override
    protected ICrudGenericoRepository<Genero, Long> getRepo() {
        return generoRepository;
    }

    @Override
    public List<ComboBoxOption> listGenero() {
        if (generoRepository.findAll().isEmpty()) {
            generoRepository.seedData();
        }
        List<ComboBoxOption> listar = new ArrayList<>();
        for (Genero gen : generoRepository.findAll()) {
            ComboBoxOption cb = new ComboBoxOption();
            cb.setKey(String.valueOf(gen.getIdGenero()));
            cb.setValue(gen.getNombre());
            listar.add(cb);
        }
        return listar;
    }
}