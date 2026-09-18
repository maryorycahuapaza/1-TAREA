package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.enums.TipoProducto;
import pe.edu.upeu.sysventas.model.Categoria;
import pe.edu.upeu.sysventas.repository.CategoriaRepository;
import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysventas.service.ICategoriaService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CategoriaServiceImp  extends CrudGenericoServiceImp<Categoria, Long> implements ICategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Override
    protected ICrudGenericoRepository<Categoria, Long> getRepo() {
        return categoriaRepository;
    }

    @Override
    public List<ComboBoxOption> listCategoria() {
        if (categoriaRepository.findAll().isEmpty()) {
            categoriaRepository.seedData();
        }
        List<ComboBoxOption> listar = new ArrayList<>();
        for (Categoria cat : categoriaRepository.findAll()) {
            ComboBoxOption cb = new ComboBoxOption();
            cb.setKey(String.valueOf(cat.getIdCategoria()));
            cb.setValue(cat.getNombre());
            listar.add(cb);
        }
        return listar;
    }
}

