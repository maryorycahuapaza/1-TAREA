package pe.edu.upeu.evaluacion01_proyectocineplanet.repository;

import pe.edu.upeu.evaluacion01_proyectocineplanet.enums.EstadoPelicula;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Clasificacion;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Formato;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Genero;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Pelicula;

public class PeliculaRepository extends AbstractJpaRepository<Pelicula, Long> {
    private long sequence=1;
    @Override
    protected Long getId(Pelicula entity) {
        return entity.getIdPelicula();
    }

    @Override
    protected void setId(Pelicula entity, Long id){
        entity.setIdPelicula(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }
    public void seedData() {
        if (findAll().isEmpty()) {
            Clasificacion c=new Clasificacion();
            c.setIdClasificacion(1L);
            Formato f=new Formato();
            f.setIdFormato(1L);
            Genero g=new Genero();
            g.setIdGenero(1L);
            save(new Pelicula(generateId(),"Evil Resident Noche Cero", EstadoPelicula.CARTELERA,g,c,f,"1h 35m"));

        }
    }
}
