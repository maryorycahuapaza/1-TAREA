package pe.edu.upeu.evaluacion01_proyectocineplanet.config;

import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.*;
import pe.edu.upeu.evaluacion01_proyectocineplanet.controller.*;
import pe.edu.upeu.evaluacion01_proyectocineplanet.repository.*;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.*;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class AppContext {

    // Singleton: una sola instancia en toda la app
    private static AppContext instance;

    public static synchronized AppContext getInstance() {
        if (instance == null) instance = new AppContext();
        return instance;
    }

    // El "directorio": Clase → Objeto
    private final Map<Class<?>, Object> cineplanet = new HashMap<>();

    // Constructor privado: aquí se arma toda la aplicación
    private AppContext() {
        registrarRepositorios();
        registrarServicios();
        registrarControladores();
    }

    // CAPA 1 — REPOSITORIOS
    // Cada repositorio sabe hablar con una tabla de la base de datos.
    // No reciben dependencias: solo necesitan la conexión (DatabaseConfig).
    private void registrarRepositorios() {
        //registrar(CategoriaRepository.class, new CategoriaRepository());


        registrar(ClasificacionRepository.class,     new ClasificacionRepository());
        registrar(GeneroRepository.class,     new GeneroRepository());
        registrar(FormatoRepository.class,         new FormatoRepository());
        registrar(PeliculaRepository.class,      new PeliculaRepository());

    }

    // CAPA 2 — SERVICIOS
    // Cada servicio recibe su repositorio por constructor.
    // Usamos getBean() para buscarlo en el directorio: no creamos nada nuevo.
    private void registrarServicios() {

        registrar(IClasificacionService.class,new ClasificacionServiceImp(   getBean(ClasificacionRepository.class)));
        registrar(IGeneroService.class,new GeneroServiceImp(   getBean(GeneroRepository.class)));
        registrar(IFormatoService.class,new FormatoServiceImp(     getBean(FormatoRepository.class)));
        registrar(IPeliculaService.class,new PeliculaServiceImp(    getBean(PeliculaRepository.class)));


    }

    // CAPA 3 — CONTROLADORES JavaFX
    // Cada controlador recibe los servicios que necesita por constructor.
    // El FXMLLoader los busca aquí a través de setControllerFactory().
    private void registrarControladores() {
        //registrar(LoginController.class, new LoginController(getBean(IUsuarioService.class)));
        registrar(PeliculaController.class,
                new PeliculaController(
                        getBean(IFormatoService.class),
                        getBean(IGeneroService.class),
                        getBean(IClasificacionService.class),
                        getBean(IPeliculaService.class)));

    }

    // API del contenedor — estos dos métodos son todo lo que hace la DI
    /** Guarda un objeto en el directorio, indexado por su tipo o interfaz. */
    private void registrar(Class<?> tipo, Object bean) {
        cineplanet.put(tipo, bean);
    }

    /**
     * Busca y devuelve un objeto por su tipo o interfaz.
     * Equivale a lo que hace Spring/Micronaut con @Inject automáticamente.
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> tipo) {
        Object bean = cineplanet.get(tipo);
        if (bean == null) {
            // Búsqueda por compatibilidad: sirve cuando se pide una interfaz
            // y el objeto guardado es su implementación concreta.
            bean = cineplanet.values().stream()
                    .filter(b -> tipo.isAssignableFrom(b.getClass()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Bean no encontrado: " + tipo.getName() +
                                    "\n→ ¿Lo registraste en AppContext?"));
        }
        return (T) bean;
    }
}