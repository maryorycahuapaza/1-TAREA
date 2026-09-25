package pe.edu.upeu.evaluacion01_proyectocineplanet.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.evaluacion01_proyectocineplanet.components.ColumnInfo;
import pe.edu.upeu.evaluacion01_proyectocineplanet.components.TableViewHelper;
import pe.edu.upeu.evaluacion01_proyectocineplanet.components.Toast;
import pe.edu.upeu.evaluacion01_proyectocineplanet.components.ToltipCustom;
import pe.edu.upeu.evaluacion01_proyectocineplanet.dto.ComboBoxOption;
import pe.edu.upeu.evaluacion01_proyectocineplanet.enums.EstadoPelicula;
import pe.edu.upeu.evaluacion01_proyectocineplanet.model.Pelicula;
import pe.edu.upeu.evaluacion01_proyectocineplanet.service.*;

import java.util.*;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class PeliculaController {
    private final IFormatoService fs;
    private final IGeneroService gs;
    private final IClasificacionService cs;
    private final IPeliculaService ps;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtDuracion;
    @FXML
    ComboBox<ComboBoxOption> cbxGenero, cbxClasificacion, cbxFormato, cbxEstadoPelicula;
    @FXML
    private TableView<Pelicula> tableView;
    ObservableList<Pelicula> listarPelicula;
    Pelicula formulario;
    Long idPeliculaCE = 0L;
    @FXML
    Label lbnMsg;
    @FXML private AnchorPane miCineplanet;
    Stage stage;
    private Validator validator;
    private final ToltipCustom ttc=new ToltipCustom();
    @FXML
    public void initialize() {
        /*Platform.runLater(() -> {
            stage = (Stage) micontenedor.getScene().getWindow();
            System.out.println("El título del stage es: " + stage.getTitle());
        });*/

        System.out.println("Holii");
        cbxEstadoPelicula.getItems().addAll(ps.listarEstadoPelicula());
        cbxFormato.getItems().addAll(fs.listarCombobox());
        cbxClasificacion.getItems().addAll(cs.listClasificacion());
        cbxGenero.getItems().addAll(gs.listGenero());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        TableViewHelper<Pelicula> tableViewHelper = new TableViewHelper<>();
        LinkedHashMap<String, ColumnInfo> columns = new LinkedHashMap<>();
        columns.put("ID", new ColumnInfo("idPelicula", 60.0));
        columns.put("Nombre", new ColumnInfo("nombre", 200.0));
        columns.put("Duración", new ColumnInfo("duracion", 100.0));
        columns.put("Género", new ColumnInfo("idGenero", 150.0));
        columns.put("Formato", new ColumnInfo("idFormato", 100.0));
        columns.put("Clasificación", new ColumnInfo("idClasificacion", 120.0));
        columns.put("Estado", new ColumnInfo("estado", 150.0));
        Consumer<Pelicula> updateAction = p-> {
            editForm(p);
            idPeliculaCE =p.getIdPelicula();
        };
        Consumer<Pelicula> deleteAction = p -> {ps.delete(p.getIdPelicula());
            Stage stage =(Stage) miCineplanet.getScene().getWindow();
            double w = stage.getWidth() / 1.5, h = stage.getHeight() / 2;
            Toast.showToast(stage, "Se eliminó correctamente!!", 2000, w, h);
            listar();};
        tableViewHelper.addColumnsInOrderWithSize(tableView, columns, updateAction, deleteAction);
        tableView.setTableMenuButtonVisible(true);
        listar();

    }
    public void listar() {
        try {
            tableView.getItems().clear();
            listarPelicula = FXCollections.observableArrayList(ps.findAll());
            tableView.getItems().addAll(listarPelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Llego"+stage.getTitle());
    }
    @FXML
    public void validarFormulario() {
        formulario = new Pelicula();
        formulario.setNombre(txtNombre.getText());
        formulario.setDuracion(txtDuracion.getText());

        String idxTP = cbxEstadoPelicula.getSelectionModel().getSelectedItem() == null ? ""
                : cbxEstadoPelicula.getSelectionModel().getSelectedItem().getKey();
        formulario.setEstado(idxTP.equals("") ? null : EstadoPelicula.valueOf(idxTP));

        String idxM = cbxFormato.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxFormato.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdFormato(idxM.equals("0") ? null : fs.findById(Long.parseLong(idxM)));

        String idxC = cbxClasificacion.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxClasificacion.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdClasificacion(idxC.equals("0") ? null : cs.findById(Long.parseLong(idxC)));

        String idxUM = cbxGenero.getSelectionModel().getSelectedItem() == null ? "0"
                : cbxGenero.getSelectionModel().getSelectedItem().getKey();
        formulario.setIdGenero(idxUM.equals("0") ? null : gs.findById(Long.parseLong(idxUM)));

        listar();
        Set<ConstraintViolation<Pelicula>> violaciones = validator.validate(formulario);
        List<ConstraintViolation<Pelicula>> violacionesOrdenadas = violaciones.stream()
                .sorted(Comparator.comparing(v -> v.getPropertyPath().toString())).toList();

        if (violacionesOrdenadas.isEmpty()) {
            procesarFormulario();

        } else {
            mostrarErroresValidacion(violacionesOrdenadas);
        }
    }
    private void mostrarErroresValidacion(List<ConstraintViolation<Pelicula>> violaciones) {
        limpiarError();
        Map<String, Control> campos = new LinkedHashMap<>();
        campos.put("nombre", txtNombre);
        campos.put("estadoPelicula", cbxEstadoPelicula);
        campos.put("duracion", txtDuracion);

        campos.put("idClasificacion", cbxClasificacion);
        campos.put("idFormato", cbxFormato);
        campos.put("idGenero", cbxGenero);

        LinkedHashMap<String, String> erroresOrdenados = new LinkedHashMap<>();
        final Control[] primerCtrl = {null};
        for (String campo : campos.keySet()) {
            violaciones.stream()
                    .filter(v -> v.getPropertyPath().toString().equals(campo))
                    .findFirst().ifPresent(v -> {

                        erroresOrdenados.put(campo, v.getMessage());

                        Control c = campos.get(campo);
                        if (c != null && !c.getStyleClass().contains("text-field-error")){
                            //c.getStyleClass().add("text-field-error");
                            if (c != null) ttc.marcarError(c, v.getMessage().trim());
                        }
                        if (primerCtrl[0] == null) primerCtrl[0] = c;
                    });
        }
        if (!erroresOrdenados.isEmpty()) {
            lbnMsg.setText(erroresOrdenados.entrySet().iterator().next().getValue());
            lbnMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            if (primerCtrl[0] != null) Platform.runLater(primerCtrl[0]::requestFocus);
        }
    }

    private void procesarFormulario() {
        lbnMsg.setText("Formulario válido");
        lbnMsg.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        Stage stage =(Stage) miCineplanet.getScene().getWindow();
        limpiarError();
        double w = stage.getWidth() / 1.5, h = stage.getHeight() / 2;
        if (idPeliculaCE > 0L) {
            formulario.setIdPelicula(idPeliculaCE);
            ps.update(idPeliculaCE, formulario);
            Toast.showToast(stage, "Se actualizó correctamente!!", 2000, w, h);
        } else {
            ps.save(formulario);
            Toast.showToast(stage, "Se guardó correctamente!!", 2000, w, h);
        }
        clearForm(); listar();
    }

    public void editForm(Pelicula pelicula) {
        txtNombre.setText(pelicula.getNombre());
        txtDuracion.setText(pelicula.getDuracion());
        cbxEstadoPelicula.getSelectionModel().select(
                cbxEstadoPelicula.getItems().stream()
                        .filter(m -> m.getKey() == pelicula.getEstado().name())
                        .findFirst().orElse(null));

        cbxClasificacion.getSelectionModel().select(
                cbxClasificacion.getItems().stream()
                        .filter(m -> Long.parseLong(m.getKey()) == pelicula.getIdClasificacion().getIdClasificacion())
                        .findFirst().orElse(null));
        cbxFormato.getSelectionModel().select(
                cbxFormato.getItems().stream()
                        .filter(c -> Long.parseLong(c.getKey()) == pelicula.getIdFormato().getIdFormato())
                        .findFirst().orElse(null));
        cbxGenero.getSelectionModel().select(
                cbxGenero.getItems().stream()
                        .filter(u -> Long.parseLong(u.getKey()) == pelicula.getIdGenero().getIdGenero())
                        .findFirst().orElse(null));
        idPeliculaCE = pelicula.getIdPelicula(); limpiarError();
    }
    public void limpiarError() {
        List.of(txtNombre,
                        txtDuracion,
                        cbxClasificacion, cbxFormato,
                        cbxGenero)
                .forEach(c -> {c.getStyleClass().remove("text-field-error");
                    ttc.limpiarCampo(c);
                });
    }
    public void clearForm() {
        txtNombre.clear();
        txtDuracion.clear();
        cbxEstadoPelicula.getSelectionModel().clearSelection();
        cbxClasificacion.getSelectionModel().clearSelection();
        cbxFormato.getSelectionModel().clearSelection();
        cbxGenero.getSelectionModel().clearSelection();
        idPeliculaCE = 0L;
        limpiarError();
    }

}

