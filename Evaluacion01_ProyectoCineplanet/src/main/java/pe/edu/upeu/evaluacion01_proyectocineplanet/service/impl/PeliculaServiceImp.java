package pe.edu.upeu.evaluacion01_proyectocineplanet.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.enums.EstadoPelicula;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Pelicula;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.ICrudGenericoRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.PeliculaRepository;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.IPeliculaService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PeliculaServiceImp extends CrudGenericoServiceImp<Pelicula, Long> implements IPeliculaService {
    private final PeliculaRepository peliculaRepository;

    @Override
    protected ICrudGenericoRepository<Pelicula, Long> getRepo() {
        return peliculaRepository;
    }

    @Override
    public List<ComboBoxOption> listarEstadoPelicula() {
        List<ComboBoxOption>listar=new ArrayList<>();
        for (EstadoPelicula tp:EstadoPelicula.values()){
            ComboBoxOption cb=new ComboBoxOption();
            cb.setKey(tp.name());
            cb.setValue(tp.getDescripcion());
            listar.add(cb);
        }
        return listar;
    }

    @Override
    public List<Pelicula> findAll() {

        if( peliculaRepository.findAll().isEmpty()) {
            peliculaRepository.seedData();
        }
        return peliculaRepository.findAll();
    }
}