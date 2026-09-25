package pe.edu.upeu.evaluacion01_proyectocineplanet.repository;

import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Clasificacion;

public class ClasificacionRepository extends AbstractJpaRepository<Clasificacion, Long> {
    private long sequence=1;
    @Override
    protected Long getId(Clasificacion entity) {
        return entity.getIdClasificacion();
    }

    @Override
    protected void setId(Clasificacion entity, Long id) {
        entity.setIdClasificacion(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }
    public void seedData() {
        if (findAll().isEmpty()) {
            save(new Clasificacion(generateId(), "+14"));
            save(new Clasificacion(generateId(),"APT"));
            save(new Clasificacion(generateId(),"+18"));
        }
    }
}
