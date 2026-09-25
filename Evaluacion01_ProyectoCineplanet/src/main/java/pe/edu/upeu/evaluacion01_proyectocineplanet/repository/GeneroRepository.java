package pe.edu.upeu.evaluacion01_proyectocineplanet.repository;

import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Genero;

public class GeneroRepository extends AbstractJpaRepository<Genero, Long> {
    private long sequence=1;
    @Override
    protected Long getId(Genero entity) {
        return entity.getIdGenero();
    }

    @Override
    protected void setId(Genero entity, Long id) {
        entity.setIdGenero(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }
    public void seedData() {
        if (findAll().isEmpty()) {
            save(new Genero(generateId(), "Comedia"));
            save(new Genero(generateId(),"Romance"));
            save(new Genero(generateId(),"Accion"));
        }
    }
}

