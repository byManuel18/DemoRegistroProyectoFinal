package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import clasemain.App;
import enums.CorreIncorre;
import enums.Escenas;
import enums.Estado;
import enums.Meses;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import modelos.Manipulador;
import modelos.ManipuladorDAO;
import modelos.Mantenimiento;
import modelos.MantenimientoDAO;
import modelos.MedPrev;
import modelos.MedPrevDAO;
import utilidades.LocalDateTableCell;

public class ControladorMantenimiento extends ControladorGenerar {
	@FXML
	private TableView<Manipulador> tabla_manipuladores;
	@FXML
	private TableColumn<Manipulador, LocalDate> colum_fecha_manipulador;
	@FXML
	private TableColumn<Manipulador, Estado> colum_firmado_manipulador;
	@FXML
	private TableColumn<Manipulador, CorreIncorre> colum_vestimienta_limpia_manipulador;
	@FXML
	private TableColumn<Manipulador, CorreIncorre> colum_higiene_personal_manipulador;
	@FXML
	private TableColumn<Manipulador, CorreIncorre> colum_incidencias_acciones_correctoras_manipulador;
	@FXML
	private TableColumn<Manipulador, CorreIncorre> colum_buenas_practicas_manipulador;
	@FXML
	private TableView<Mantenimiento> tabla_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, LocalDate> colum_fecha_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, Estado> colum_firmado_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_suelo_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_paredes_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_techo_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_electricidad_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_fonta_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_operaciones_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_calibracion_mantenimiento;
	@FXML
	private TableColumn<Mantenimiento, CorreIncorre> colum_revison_mantenimiento;
	@FXML
	private TableView<MedPrev> tabla_MedPrev;
	@FXML
	private TableColumn<MedPrev, LocalDate> colum_fecha_MedPrev;
	@FXML
	private TableColumn<MedPrev, Estado> colum_firmado_MedPrev;
	@FXML
	private TableColumn<MedPrev, CorreIncorre> colum_plagas_MedPrev;
	@FXML
	private TableColumn<MedPrev, CorreIncorre> colum_puertasyventanas_MedPrev;
	@FXML
	private TableColumn<MedPrev, CorreIncorre> colum_lamparas_MedPrev;
	@FXML
	private TableColumn<MedPrev, CorreIncorre> colum_desagues_MedPrev;
	@FXML
	private TableColumn<MedPrev, CorreIncorre> colum_limpieza_MedPrev;
	@FXML
	private DatePicker busquedaporfecha_manipuladores;
	@FXML
	private DatePicker busquedaporfecha_mantenimiento;
	@FXML
	private DatePicker busquedaporfecha_MedPrev;
	@FXML
	private DatePicker introfecha_manipuladores;
	@FXML
	private DatePicker introfecha_mantenimiento;
	@FXML
	private DatePicker introfecha_MedPrev;
	@FXML
	private ChoiceBox<Estado> intofirmado_manipuladores;
	@FXML
	private ChoiceBox<Estado> intofirmado_mantenimiento;
	@FXML
	private ChoiceBox<Estado> intofirmado_MedPrev;
	@FXML
	private ChoiceBox<Meses> buscarpormes_manipuladores;
	@FXML
	private ChoiceBox<Meses> buscarpormes_mantenimiento;
	@FXML
	private ChoiceBox<Meses> buscarpormes_MedPrev;
	@FXML
	private ChoiceBox<CorreIncorre> intro_vestimienta;
	@FXML
	private ChoiceBox<CorreIncorre> intro_higiene_personal;
	@FXML
	private ChoiceBox<CorreIncorre> intro_buenas_practicas;
	@FXML
	private ChoiceBox<CorreIncorre> intro_ncidencias_acciones_correctoras;
	@FXML
	private ChoiceBox<CorreIncorre> intro_paredes_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_suelo_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_techos_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_electricidad_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_font_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_opera_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_calibra_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_revision_mantenimiento;
	@FXML
	private ChoiceBox<CorreIncorre> intro_plagas_MedPrev;
	@FXML
	private ChoiceBox<CorreIncorre> intro_puertasyventanas_MedPrev;
	@FXML
	private ChoiceBox<CorreIncorre> intro_lamparas_MedPrev;
	@FXML
	private ChoiceBox<CorreIncorre> intro_desagues_MedPrev;
	@FXML
	private ChoiceBox<CorreIncorre> intro_limpieza_MedPrev;

	ObservableList<Manipulador> listadinamicamanipuladores;
	ObservableList<Mantenimiento> listadinamicamantenimiento;
	ObservableList<MedPrev> listadinamicaMedPrev;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listadinamicamanipuladores = FXCollections.observableArrayList();
		listadinamicamantenimiento = FXCollections.observableArrayList();
		listadinamicaMedPrev = FXCollections.observableArrayList();
		for (Estado e : Estado.values()) {
			intofirmado_manipuladores.getItems().add(e);
			intofirmado_mantenimiento.getItems().add(e);
			intofirmado_MedPrev.getItems().add(e);
		}
		for (CorreIncorre ci : CorreIncorre.values()) {
			intro_buenas_practicas.getItems().add(ci);
			intro_higiene_personal.getItems().add(ci);
			intro_ncidencias_acciones_correctoras.getItems().add(ci);
			intro_vestimienta.getItems().add(ci);
			intro_calibra_mantenimiento.getItems().add(ci);
			intro_electricidad_mantenimiento.getItems().add(ci);
			intro_opera_mantenimiento.getItems().add(ci);
			intro_paredes_mantenimiento.getItems().add(ci);
			intro_suelo_mantenimiento.getItems().add(ci);
			intro_techos_mantenimiento.getItems().add(ci);
			intro_revision_mantenimiento.getItems().add(ci);
			intro_font_mantenimiento.getItems().add(ci);
			intro_plagas_MedPrev.getItems().add(ci);
			intro_puertasyventanas_MedPrev.getItems().add(ci);
			intro_lamparas_MedPrev.getItems().add(ci);
			intro_desagues_MedPrev.getItems().add(ci);
			intro_limpieza_MedPrev.getItems().add(ci);
		}
		buscarpormes_manipuladores.getItems().addAll(Meses.values());
		buscarpormes_mantenimiento.getItems().addAll(Meses.values());
		buscarpormes_MedPrev.getItems().addAll(Meses.values());
		MostrarTodosManipuladores();
		MostrarTodosMantenimiento();
		MostrarTodosMedPrev();
		this.colum_fecha_manipulador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.colum_firmado_manipulador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		this.colum_buenas_practicas_manipulador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getBuenas_practicas());
		});
		this.colum_higiene_personal_manipulador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getHigiene_personal());
		});
		this.colum_incidencias_acciones_correctoras_manipulador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getIncidencias_acciones_correctas());
		});
		this.colum_vestimienta_limpia_manipulador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getVestimente_limpia());
		});
		colum_firmado_manipulador.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
		colum_firmado_manipulador.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Manipulador, Estado>>() {

			@Override
			public void handle(CellEditEvent<Manipulador, Estado> event) {

				Manipulador n = (Manipulador) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				ManipuladorDAO guardar = new ManipuladorDAO(n);
				guardar.save();

			}
		});
		colum_fecha_manipulador.setCellFactory(param -> new LocalDateTableCell<>(param));
		colum_fecha_manipulador.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Manipulador, LocalDate>>() {

			@Override
			public void handle(CellEditEvent<Manipulador, LocalDate> event) {
				Manipulador n = (Manipulador) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFecha(event.getNewValue());
				ManipuladorDAO guardar = new ManipuladorDAO(n);
				guardar.save();

			}
		});
		colum_buenas_practicas_manipulador
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_buenas_practicas_manipulador
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Manipulador, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Manipulador, CorreIncorre> event) {

						Manipulador n = (Manipulador) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setBuenas_practicas(event.getNewValue());
						ManipuladorDAO guardar = new ManipuladorDAO(n);
						guardar.save();

					}
				});
		colum_higiene_personal_manipulador
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_higiene_personal_manipulador
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Manipulador, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Manipulador, CorreIncorre> event) {

						Manipulador n = (Manipulador) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setHigiene_personal(event.getNewValue());
						ManipuladorDAO guardar = new ManipuladorDAO(n);
						guardar.save();

					}
				});
		colum_incidencias_acciones_correctoras_manipulador
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_incidencias_acciones_correctoras_manipulador
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Manipulador, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Manipulador, CorreIncorre> event) {

						Manipulador n = (Manipulador) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setIncidencias_acciones_correctas(event.getNewValue());
						ManipuladorDAO guardar = new ManipuladorDAO(n);
						guardar.save();

					}
				});
		colum_vestimienta_limpia_manipulador
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_vestimienta_limpia_manipulador
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Manipulador, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Manipulador, CorreIncorre> event) {

						Manipulador n = (Manipulador) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setVestimente_limpia(event.getNewValue());
						ManipuladorDAO guardar = new ManipuladorDAO(n);
						guardar.save();

					}
				});

		this.colum_fecha_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.colum_firmado_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		this.colum_calibracion_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getCalibracion_equipo_de_frio());
		});
		this.colum_electricidad_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getElectricidad());
		});
		this.colum_fonta_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFontan());
		});
		this.colum_operaciones_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getOperaciones_a_equipos_y_utensilios());
		});
		this.colum_paredes_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getParedes());
		});
		this.colum_revison_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getRevision_balanzas());
		});
		this.colum_suelo_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getSuelo());
		});
		this.colum_techo_mantenimiento.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getTecho());
		});
		colum_firmado_mantenimiento
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
		colum_firmado_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, Estado>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, Estado> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setFirmado(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_fecha_mantenimiento.setCellFactory(param -> new LocalDateTableCell<>(param));
		colum_fecha_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, LocalDate>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, LocalDate> event) {
						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setFecha(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_calibracion_mantenimiento
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_calibracion_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setCalibracion_equipo_de_frio(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_electricidad_mantenimiento
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_electricidad_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setElectricidad(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_fonta_mantenimiento.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_fonta_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setFontan(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_operaciones_mantenimiento
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_operaciones_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setOperaciones_a_equipos_y_utensilios(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_paredes_mantenimiento.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_paredes_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setParedes(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_revison_mantenimiento.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_revison_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setRevision_balanzas(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_suelo_mantenimiento.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_suelo_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setSuelo(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});
		colum_techo_mantenimiento.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_techo_mantenimiento
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Mantenimiento, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<Mantenimiento, CorreIncorre> event) {

						Mantenimiento n = (Mantenimiento) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setTecho(event.getNewValue());
						MantenimientoDAO guardar = new MantenimientoDAO(n);
						guardar.save();

					}
				});

		this.colum_fecha_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.colum_desagues_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getEstado_de_desagues());
		});
		this.colum_lamparas_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getEstado_de_lamparas());
		});
		this.colum_limpieza_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getEstado_de_limpieza());
		});
		this.colum_plagas_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getPlagas());
		});
		this.colum_puertasyventanas_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getEstado_de_puertas());
		});
		this.colum_firmado_MedPrev.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});

		colum_fecha_MedPrev.setCellFactory(param -> new LocalDateTableCell<>(param));
		colum_fecha_MedPrev.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, LocalDate>>() {

			@Override
			public void handle(CellEditEvent<MedPrev, LocalDate> event) {
				MedPrev n = (MedPrev) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFecha(event.getNewValue());
				MedPrevDAO guardar = new MedPrevDAO(n);
				guardar.save();

			}
		});
		colum_firmado_MedPrev
				.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
		colum_firmado_MedPrev
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, Estado>>() {

					@Override
					public void handle(CellEditEvent<MedPrev, Estado> event) {

						MedPrev n = (MedPrev) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setFirmado(event.getNewValue());
						MedPrevDAO guardar = new MedPrevDAO(n);
						guardar.save();

					}
				});
		colum_desagues_MedPrev.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_desagues_MedPrev
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<MedPrev, CorreIncorre> event) {

						MedPrev n = (MedPrev) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setEstado_de_desagues(event.getNewValue());
						MedPrevDAO guardar = new MedPrevDAO(n);
						guardar.save();

					}
				});
		colum_lamparas_MedPrev.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_lamparas_MedPrev
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<MedPrev, CorreIncorre> event) {

						MedPrev n = (MedPrev) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setEstado_de_lamparas(event.getNewValue());
						MedPrevDAO guardar = new MedPrevDAO(n);
						guardar.save();

					}
				});
		colum_limpieza_MedPrev.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_limpieza_MedPrev
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<MedPrev, CorreIncorre> event) {

						MedPrev n = (MedPrev) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setEstado_de_limpieza(event.getNewValue());
						MedPrevDAO guardar = new MedPrevDAO(n);
						guardar.save();

					}
				});
		colum_plagas_MedPrev.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_plagas_MedPrev
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<MedPrev, CorreIncorre> event) {

						MedPrev n = (MedPrev) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setPlagas(event.getNewValue());
						MedPrevDAO guardar = new MedPrevDAO(n);
						guardar.save();

					}
				});
		colum_puertasyventanas_MedPrev.setCellFactory(ChoiceBoxTableCell.forTableColumn(CorreIncorre.C, CorreIncorre.I));
		colum_puertasyventanas_MedPrev
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MedPrev, CorreIncorre>>() {

					@Override
					public void handle(CellEditEvent<MedPrev, CorreIncorre> event) {

						MedPrev n = (MedPrev) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						n.setEstado_de_puertas(event.getNewValue());
						MedPrevDAO guardar = new MedPrevDAO(n);
						guardar.save();

					}
				});

		tabla_manipuladores.setEditable(true);
		tabla_manipuladores.setItems(listadinamicamanipuladores);
		tabla_mantenimiento.setEditable(true);
		tabla_mantenimiento.setItems(listadinamicamantenimiento);
		tabla_MedPrev.setEditable(true);
		tabla_MedPrev.setItems(listadinamicaMedPrev);

	}

	@FXML
	private void MostrarTodosManipuladores() {
		listadinamicamanipuladores.clear();
		listadinamicamanipuladores.addAll(ManipuladorDAO.SelectAll());
		buscarpormes_manipuladores.setValue(null);
		busquedaporfecha_manipuladores.setValue(null);
	}

	@FXML
	private void MostrarTodosMantenimiento() {
		listadinamicamantenimiento.clear();
		listadinamicamantenimiento.addAll(MantenimientoDAO.SelectALL());
		buscarpormes_mantenimiento.setValue(null);
		busquedaporfecha_mantenimiento.setValue(null);
	}

	@FXML
	private void MostrarTodosMedPrev() {
		listadinamicaMedPrev.clear();
		listadinamicaMedPrev.addAll(MedPrevDAO.SelectAll());
		buscarpormes_MedPrev.setValue(null);
		busquedaporfecha_MedPrev.setValue(null);
	}

	@FXML
	private void Volver() {
		try {
			App.setRoot(Escenas.INICIO.getUrl());
			App.CambiarTitulo("TIENDA ANTONIO GUERRERO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void TituloManipuladores() {
		App.CambiarTitulo("REGISTRO MANIPULADORES");
	}

	@FXML
	private void TituloMantenimiento() {
		App.CambiarTitulo("REGISTRO MANTENIMIENTO");
	}

	@FXML
	private void TituloMedidasPreventivas() {
		App.CambiarTitulo("REGISTRO MEDIDAS PREVENTIVAS");
	}

	@FXML
	private void LimpiarManipuladores() {
		intro_buenas_practicas.setValue(null);
		intofirmado_manipuladores.setValue(null);
		intro_higiene_personal.setValue(null);
		intro_ncidencias_acciones_correctoras.setValue(null);
		intro_vestimienta.setValue(null);
		introfecha_manipuladores.setValue(null);
	}

	@FXML
	private void LimpiarMantenimiento() {
		intofirmado_mantenimiento.setValue(null);
		intro_calibra_mantenimiento.setValue(null);
		intro_electricidad_mantenimiento.setValue(null);
		intro_font_mantenimiento.setValue(null);
		intro_opera_mantenimiento.setValue(null);
		intro_calibra_mantenimiento.setValue(null);
		intro_revision_mantenimiento.setValue(null);
		intro_suelo_mantenimiento.setValue(null);
		intro_techos_mantenimiento.setValue(null);
		intro_paredes_mantenimiento.setValue(null);
		introfecha_mantenimiento.setValue(null);
	}

	@FXML
	private void LimpiarMedPrev() {
		intro_plagas_MedPrev.setValue(null);
		intro_puertasyventanas_MedPrev.setValue(null);
		intro_lamparas_MedPrev.setValue(null);
		intro_desagues_MedPrev.setValue(null);
		intro_limpieza_MedPrev.setValue(null);
		intofirmado_MedPrev.setValue(null);
		introfecha_MedPrev.setValue(null);
	}

	@FXML
	private void EliminarManipulador() {
		Manipulador aeli = tabla_manipuladores.getSelectionModel().getSelectedItem();
		if (confirm("", "", "")) {
			ManipuladorDAO eliminar = new ManipuladorDAO(aeli);
			if (eliminar.delete() > 0) {
				listadinamicamanipuladores.remove(aeli);
				muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
			} else {
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}
	}

	@FXML
	private void Eliminarmantenimientor() {
		Mantenimiento aeli = tabla_mantenimiento.getSelectionModel().getSelectedItem();
		if (confirm("", "", "")) {
			MantenimientoDAO eli = new MantenimientoDAO(aeli);
			if (eli.remuve() > 0) {
				listadinamicamantenimiento.remove(aeli);
				muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
			} else {
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}
	}

	@FXML
	private void EliminarMedPrev() {
		MedPrev aeli = tabla_MedPrev.getSelectionModel().getSelectedItem();
		if (confirm("", "", "")) {
			MedPrevDAO eli = new MedPrevDAO(aeli);
			if (eli.remuve() > 0) {
				listadinamicaMedPrev.remove(aeli);
				muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
			} else {
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}
	}

	@FXML
	private void ADDManipulador() {
		if (intofirmado_manipuladores.getValue() != null && intro_buenas_practicas.getValue() != null
				&& intro_higiene_personal.getValue() != null && intro_ncidencias_acciones_correctoras.getValue() != null
				&& intro_vestimienta.getValue() != null && introfecha_manipuladores.getValue() != null) {
			Manipulador nuevo = new Manipulador(introfecha_manipuladores.getValue(), intro_vestimienta.getValue(),
					intro_higiene_personal.getValue(), intro_buenas_practicas.getValue(),
					intro_ncidencias_acciones_correctoras.getValue(), intofirmado_manipuladores.getValue());
			ManipuladorDAO add = new ManipuladorDAO(nuevo);
			if (add.save() > 0) {
				listadinamicamanipuladores.clear();
				listadinamicamanipuladores.addAll(ManipuladorDAO.SelectAll());
				intro_buenas_practicas.setValue(null);
				intofirmado_manipuladores.setValue(null);
				intro_higiene_personal.setValue(null);
				intro_ncidencias_acciones_correctoras.setValue(null);
				intro_vestimienta.setValue(null);
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");

			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
						"Revise que todos los campos están rellenos y son válidos");
			}

		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que todos los campos están rellenos y son válidos");
		}
	}

	@FXML
	private void ADDMantenimienti() {
		if (intofirmado_mantenimiento.getValue() != null && introfecha_mantenimiento.getValue() != null
				&& intro_calibra_mantenimiento.getValue() != null && intro_electricidad_mantenimiento.getValue() != null
				&& intro_font_mantenimiento.getValue() != null && intro_opera_mantenimiento.getValue() != null
				&& intro_paredes_mantenimiento.getValue() != null && intro_revision_mantenimiento.getValue() != null
				&& intro_suelo_mantenimiento.getValue() != null && intro_techos_mantenimiento.getValue() != null) {
			Mantenimiento nuevo = new Mantenimiento(introfecha_mantenimiento.getValue(),
					intro_suelo_mantenimiento.getValue(), intro_paredes_mantenimiento.getValue(),
					intro_techos_mantenimiento.getValue(), intro_electricidad_mantenimiento.getValue(),
					intro_font_mantenimiento.getValue(), intro_opera_mantenimiento.getValue(),
					intro_calibra_mantenimiento.getValue(), intro_revision_mantenimiento.getValue(),
					intofirmado_mantenimiento.getValue());
			MantenimientoDAO add = new MantenimientoDAO(nuevo);
			if (add.save() > 0) {
				listadinamicamantenimiento.clear();
				listadinamicamantenimiento.addAll(MantenimientoDAO.SelectALL());
				intofirmado_mantenimiento.setValue(null);
				intro_calibra_mantenimiento.setValue(null);
				intro_electricidad_mantenimiento.setValue(null);
				intro_font_mantenimiento.setValue(null);
				intro_opera_mantenimiento.setValue(null);
				intro_calibra_mantenimiento.setValue(null);
				intro_revision_mantenimiento.setValue(null);
				intro_suelo_mantenimiento.setValue(null);
				intro_techos_mantenimiento.setValue(null);
				intro_paredes_mantenimiento.setValue(null);
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
						"Revise que todos los campos están rellenos y son válidos");
			}

		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que todos los campos están rellenos y son válidos");
		}
	}

	@FXML
	private void ADDMedPrev() {
		if (introfecha_MedPrev.getValue() != null && intro_plagas_MedPrev.getValue() != null
				&& intro_puertasyventanas_MedPrev.getValue() != null && intro_lamparas_MedPrev.getValue() != null
				&& intro_desagues_MedPrev.getValue() != null && intro_limpieza_MedPrev.getValue() != null
				&& intofirmado_MedPrev.getValue() != null) {
			MedPrev nuevo = new MedPrevDAO(introfecha_MedPrev.getValue(), intro_puertasyventanas_MedPrev.getValue(),
					intro_lamparas_MedPrev.getValue(), intro_desagues_MedPrev.getValue(),
					intro_limpieza_MedPrev.getValue(), intro_plagas_MedPrev.getValue(), intofirmado_MedPrev.getValue());
			MedPrevDAO add = new MedPrevDAO(nuevo);
			if (add.save() > 0) {
				MostrarTodosMedPrev();
				intro_plagas_MedPrev.setValue(null);
				intro_puertasyventanas_MedPrev.setValue(null);
				intro_lamparas_MedPrev.setValue(null);
				intro_desagues_MedPrev.setValue(null);
				intro_limpieza_MedPrev.setValue(null);
				intofirmado_MedPrev.setValue(null);
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
						"Revise que todos los campos están rellenos y son válidos");
			}

		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que todos los campos están rellenos y son válidos");
		}

	}

	@FXML
	private void BusquedaPorMesManipulador() {
		if (buscarpormes_manipuladores.getValue() != null) {
			busquedaporfecha_manipuladores.setValue(null);
			listadinamicamanipuladores.clear();
			listadinamicamanipuladores.addAll(ManipuladorDAO
					.SelectPorMes(Integer.toString(buscarpormes_manipuladores.getValue().getNumero_Mes())));
		}

	}

	@FXML
	private void BusquedaPorMesMantenimiento() {
		if (buscarpormes_mantenimiento.getValue() != null) {
			busquedaporfecha_mantenimiento.setValue(null);
			listadinamicamantenimiento.clear();
			listadinamicamantenimiento.addAll(MantenimientoDAO
					.SelectPorMes(Integer.toString(buscarpormes_mantenimiento.getValue().getNumero_Mes())));
		}
	}

	@FXML
	private void BusquedaPorMesMedPrev() {
		if (buscarpormes_MedPrev.getValue() != null) {
			busquedaporfecha_MedPrev.setValue(null);
			listadinamicaMedPrev.clear();
			listadinamicaMedPrev
					.addAll(MedPrevDAO.SelectPorMes(Integer.toString(buscarpormes_MedPrev.getValue().getNumero_Mes())));
		}
	}

	@FXML
	private void BusquedaPorFechaManipulador() {
		if (busquedaporfecha_manipuladores.getValue() != null) {
			buscarpormes_manipuladores.setValue(null);
			listadinamicamanipuladores.clear();
			listadinamicamanipuladores
					.addAll(ManipuladorDAO.SelectFecha(busquedaporfecha_manipuladores.getValue().toString()));
		}
	}

	@FXML
	private void BusquedaPorFechaMantenimiento() {
		if (busquedaporfecha_mantenimiento.getValue() != null) {
			buscarpormes_mantenimiento.setValue(null);
			listadinamicamantenimiento.clear();
			listadinamicamantenimiento
					.addAll(MantenimientoDAO.SelectPorFecha(busquedaporfecha_mantenimiento.getValue().toString()));

		}
	}

	@FXML
	private void BusquedaPorFechaMedPrev() {
		if (busquedaporfecha_MedPrev.getValue() != null) {
			buscarpormes_MedPrev.setValue(null);
			listadinamicaMedPrev.clear();
			listadinamicaMedPrev.addAll(MedPrevDAO.SelectPorFecha(busquedaporfecha_MedPrev.getValue().toString()));
		}

	}

}
