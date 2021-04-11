package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import clasemain.App;
import enums.Busqueda;
import enums.Escenas;
import enums.Estado;
import enums.Meses;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import modelos.Envase;
import modelos.EnvasesDAO;
import modelos.Laure;
import modelos.LaureDAO;
import modelos.MateriaPrima;
import modelos.MateriaPrimaDAO;
import modelos.Salsa;
import modelos.SalsaDAO;
import modelos.SanidadDAO;
import modelos.SanidadProducto;
import modelos.TrazCarne;
import modelos.TrazCarneDAO;
import utilidades.LocalDateTableCell;

public class ControladorTrazabilidad extends ControladorGenerar {
	@FXML
	private DatePicker buscarporfech_carne;
	@FXML
	private DatePicker anadirfecharefistro_carne;
	@FXML
	private DatePicker add_fechainicio_carne;
	@FXML
	private DatePicker add_fecha_fin_carne;
	@FXML
	private DatePicker buscar_fecha_ini_trazcarne;
	@FXML
	private DatePicker buscar_fecha_fin_trazcarne;
	@FXML
	private DatePicker buscar_fecha_llegada_trazcarne;
	@FXML
	private ChoiceBox<Estado> firmado_traz_carne;
	@FXML
	private TextField proveedoranadirtraza_carne;
	@FXML
	private TextField productodoranadirtraza_carne;
	@FXML
	private TextField lotedoranadirtraza_carne;
	@FXML
	private ChoiceBox<Meses> buscarmes_carne;
	@FXML
	private ChoiceBox<Meses> buscarmes_traza_carne;
	@FXML
	private TableView<TrazCarne> tabla_traz_carne;
	@FXML
	private TableColumn<TrazCarne, LocalDate> fecha_traz_carne;
	@FXML
	private TableColumn<TrazCarne, LocalDate> fecha_fin_traz;
	@FXML
	private TableColumn<TrazCarne, LocalDate> fecha_inicio_traz;
	@FXML
	private TableColumn<TrazCarne, String> lote_traz_carne;
	@FXML
	private TableColumn<TrazCarne, String> pro_traz_carne;
	@FXML
	private TableColumn<TrazCarne, String> prove_traz_carne;
	@FXML
	private TableColumn<TrazCarne, Estado> firm_traz_carne;
	@FXML
	private TableView<SanidadProducto> tabla_carne;
	@FXML
	private TableColumn<SanidadProducto, LocalDate> fecha_Carne;
	@FXML
	private TableColumn<SanidadProducto, String> lote_carne;
	@FXML
	private TableColumn<SanidadProducto, String> provee_carne;
	@FXML
	private TableColumn<SanidadProducto, String> producto_carne;
	@FXML
	private ChoiceBox<Integer> anos;
	@FXML
	private ChoiceBox<Integer> anoscarne;
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// ----------------------------LAURE----------------------------------------
	@FXML
	private TableView<Laure> tablatrazlaure;
	@FXML
	private TableColumn<Laure, LocalDate> fecha_lleg_traz_laure;
	@FXML
	private TableColumn<Laure, LocalDate> fecha_ini_traz_laure;
	@FXML
	private TableColumn<Laure, LocalDate> fecha_fin_traz_laure;
	@FXML
	private TableColumn<Laure, String> proveedor_traz_laure;
	@FXML
	private TableColumn<Laure, String> mercancia_traz_laure;
	@FXML
	private TableColumn<Laure, String> lote_traz_laure;
	@FXML
	private TableColumn<Laure, Estado> firmado_traz_laure;
	@FXML
	private ChoiceBox<Meses> buscar_meses_laure;
	@FXML
	private ChoiceBox<Integer> anosdisponibles_laure;
	@FXML
	private ChoiceBox<Estado> firmar_registro_laure;
	@FXML
	private DatePicker addfechallegada_laure;
	@FXML
	private DatePicker addfechafin_laure;
	@FXML
	private DatePicker addfechainicio_laure;
	@FXML
	private TextField addproveedor_laure;
	@FXML
	private TextField addmercancia_laure;
	@FXML
	private TextField addlote_laure;
	@FXML
	private VBox lableslaure;
	@FXML
	private VBox buscarlaure;
	@FXML
	private ChoiceBox<String> tiposbusqueda_laure;
	@FXML
	private RadioButton busquedafechaconcreta_laure;
	@FXML
	private RadioButton busquedaampliada_laure;
	@FXML
	private DatePicker busquedaconcreta_laure;
	@FXML
	private Button gobuscar_laure;
	// -------------------------------------------------
	// -------------------------------------------------
	// ------------MATERIA PRIMA------------------------
	@FXML
	private TableView<MateriaPrima> tablatraz_materiaprima;
	@FXML
	private TableColumn<MateriaPrima, LocalDate> colum_fechallegada_trazmateriaprima;
	@FXML
	private TableColumn<MateriaPrima, LocalDate> colum_fechafin_trazmateriaprima;
	@FXML
	private TableColumn<MateriaPrima, LocalDate> colum_fechainicio_trazmateriaprima;
	@FXML
	private TableColumn<MateriaPrima, String> colum_proveedor_materiaprima;
	@FXML
	private TableColumn<MateriaPrima, String> colum_lote_materiaprima;
	@FXML
	private TableColumn<MateriaPrima, String> colum_mercancia_materiaprima;
	@FXML
	private TableColumn<MateriaPrima, Estado> colum_firmado_materiaprima;
	@FXML
	private DatePicker addfechallegada_trazmateriaprima;
	@FXML
	private DatePicker addfechafin_trazmateriaprima;
	@FXML
	private DatePicker addfechainicio_trazmateriaprima;
	@FXML
	private ChoiceBox<Estado> addfirmado_trazmateriaprima;
	@FXML
	private TextField addmercancia_trazmateriaprima;
	@FXML
	private TextField addlote_trazmateriaprima;
	@FXML
	private TextField addproveedor_trazmateriaprima;
	@FXML
	private Button buscar_trazmateriaprima;
	@FXML
	private ChoiceBox<String> tiposbusqueda_trazmateriaprima;
	@FXML
	private VBox labels_trazmateriaprima;
	@FXML
	private VBox busquedaampliadavbox_trazmateriaprima;
	@FXML
	private ChoiceBox<Meses> meses_trazmateriaprima;
	@FXML
	private ChoiceBox<Integer> anos_trazmateriaprima;
	@FXML
	private DatePicker busquedaconcreta_trazmateriaprima;
	@FXML
	private RadioButton cambiafechaconcreta_materiaprima;
	@FXML
	private RadioButton cambiardefechaampliada_materiaprima;
	private ObservableList<MateriaPrima> listamateriaprima;
	private Set<Integer> añosdisponibles_materiaprima;
	// -------------------------------------------------
	// -------------------------------------------------
	// -------------------SALSAS------------------------
	@FXML
	private TableView<Salsa> tabla_salsas;
	@FXML
	private TableColumn<Salsa, LocalDate> columna_fechallegada_salsas;
	@FXML
	private TableColumn<Salsa, LocalDate> columna_fechafin_salsas;
	@FXML
	private TableColumn<Salsa, LocalDate> columna_fechaini_salsas;
	@FXML
	private TableColumn<Salsa, String> columna_proveedor_salsas;
	@FXML
	private TableColumn<Salsa, String> columna_lote_salsas;
	@FXML
	private TableColumn<Salsa, String> columna_mercancia_salsas;
	@FXML
	private TableColumn<Salsa, Estado> columna_firmado_salsas;
	@FXML
	private DatePicker busquedaconcreta_salsas;
	@FXML
	private DatePicker addfechallegada_salsa;
	@FXML
	private DatePicker addfechafin_salsa;
	@FXML
	private DatePicker addfechainicio_salsa;
	@FXML
	private ChoiceBox<Estado> addfirmado_salsa;
	@FXML
	private ChoiceBox<Meses> busquedameses_salsas;
	@FXML
	private ChoiceBox<Integer> anosdisponibles_salsas;
	@FXML
	private ChoiceBox<String> tiposbusqueeda_salsa;
	@FXML
	private Button botonbusqueda_salsa;
	@FXML
	private VBox labels_salsa;
	@FXML
	private VBox vboxampliada_salsa;
	@FXML
	private RadioButton radiobuttonconcreta_salsa;
	@FXML
	private RadioButton radiobuttonampliado_salsa;
	@FXML
	private TextField add_proveedor_salsas;
	@FXML
	private TextField add_mercancia_salsas;
	@FXML
	private TextField add_lote_salsas;
	private ObservableList<Salsa> listasalsas;
	private Set<Integer> todoslosaños_salsas;
	// -------------------------------------------------
	// -------------------------------------------------
	// -------------------ENVASES------------------------
	@FXML
	private TableView<Envase> tabla_envases;
	@FXML
	private TableColumn<Envase, LocalDate> columna_fechallegada_envases;
	@FXML
	private TableColumn<Envase, LocalDate> columna_fechafin_envases;
	@FXML
	private TableColumn<Envase, LocalDate> columna_fechainicio_envases;
	@FXML
	private TableColumn<Envase, Estado> columna_firmado_envases;
	@FXML
	private TableColumn<Envase, String> columna_lote_envases;
	@FXML
	private TableColumn<Envase, String> columna_mercancia_envases;
	@FXML
	private TableColumn<Envase, String> columna_proveedor_envases;
	@FXML
	private DatePicker fechabusquedaconcreta_envases;
	@FXML
	private DatePicker addfechallegada_envases;
	@FXML
	private DatePicker addfechafin_envases;
	@FXML
	private DatePicker addfechainicio_envases;
	@FXML
	private TextField addlote_envases;
	@FXML
	private TextField addmercancia_envases;
	@FXML
	private TextField addproveedor_envases;
	@FXML
	private Button botonbuscar_ensases;
	@FXML
	private ChoiceBox<String> tipos_busqueda_envases;
	@FXML
	private ChoiceBox<Meses> mesesbusqueda_envases;
	@FXML
	private ChoiceBox<Integer> anosdisponibles_envases;
	@FXML
	private ChoiceBox<Estado> addfirmado_envases;
	@FXML
	private VBox VBoxLables_envases;
	@FXML
	private VBox VBoxbusquedaabanzada_envases;
	@FXML
	private RadioButton radiobuttonbusquedaconcreta;
	@FXML
	private RadioButton radiobuttonbusquedaabanzada;
	private ObservableList<Envase> listaenvases;
	private Set<Integer> todosañosdisponibles_envases;

	private ObservableList<SanidadProducto> listcarnes;
	private ObservableList<Laure> listadinamicalaure = null;
	private ObservableList<TrazCarne> listadinamicatrazacarne;
	private Set<Integer> tdoslosaños;
	private Set<Integer> tdoslosaños_laure;
	private Set<Integer> tdoslosaños_sanidadcarne;
	private SanidadProducto procarneaañadir = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listcarnes = FXCollections.observableArrayList();
		listadinamicatrazacarne = FXCollections.observableArrayList();
		tdoslosaños = new TreeSet<>();
		buscarmes_carne.getItems().addAll(Meses.values());
		buscarmes_traza_carne.getItems().addAll(Meses.values());
		firmado_traz_carne.getItems().addAll(Estado.values());
		MostrarTodoCarne();
		MostrarTodoTRazaCarne();
		darAños();
		anadirfecharefistro_carne.setEditable(false);
		productodoranadirtraza_carne.setEditable(false);
		proveedoranadirtraza_carne.setEditable(false);
		lotedoranadirtraza_carne.setEditable(false);
		tdoslosaños_sanidadcarne = new TreeSet<>();
		for (SanidadProducto c : listcarnes) {
			tdoslosaños_sanidadcarne.add(c.getFecha().getYear());
		}
		anoscarne.getItems().addAll(tdoslosaños_sanidadcarne);

		this.lote_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getCodigolote());
		});
		this.producto_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getNombre_producto());
		});
		this.provee_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
		});

		this.fecha_Carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});

		this.fecha_traz_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProcar().getFecha());
		});
		this.fecha_inicio_traz.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_inicio());
		});
		this.fecha_fin_traz.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFehca_fin());
		});
		this.firm_traz_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		this.lote_traz_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProcar().getCodigolote());
		});
		this.prove_traz_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProcar().getProveedor());
		});
		this.pro_traz_carne.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProcar().getNombre_producto());
		});
		firm_traz_carne.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
		firm_traz_carne.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrazCarne, Estado>>() {

			@Override
			public void handle(CellEditEvent<TrazCarne, Estado> event) {

				TrazCarne n = (TrazCarne) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				TrazCarneDAO guardar = new TrazCarneDAO(n);
				guardar.update();

			}
		});

		fecha_inicio_traz.setCellFactory(param -> new LocalDateTableCell<>(param));
		fecha_inicio_traz.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrazCarne, LocalDate>>() {

			@Override
			public void handle(CellEditEvent<TrazCarne, LocalDate> event) {
				TrazCarne n = (TrazCarne) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFecha_inicio(event.getNewValue());
				TrazCarneDAO guardar = new TrazCarneDAO(n);
				guardar.update();

			}
		});

		fecha_fin_traz.setCellFactory(param -> new LocalDateTableCell<>(param));
		fecha_fin_traz.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrazCarne, LocalDate>>() {

			@Override
			public void handle(CellEditEvent<TrazCarne, LocalDate> event) {
				TrazCarne n = (TrazCarne) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFehca_fin(event.getNewValue());
				TrazCarneDAO guardar = new TrazCarneDAO(n);
				guardar.update();

			}
		});
		tabla_traz_carne.setEditable(true);
		tabla_carne.setItems(listcarnes);
		tabla_traz_carne.setItems(listadinamicatrazacarne);

	}

	private void darAños() {
		tdoslosaños.clear();
		anos.getItems().clear();
		for (TrazCarne t : listadinamicatrazacarne) {
			tdoslosaños.add(t.getProcar().getFecha().getYear());
		}

		anos.getItems().addAll(tdoslosaños);
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
	private void MostrarTodoCarne() {
		listcarnes.clear();
		listcarnes.addAll(SanidadDAO.SelecAll());
		buscarmes_carne.setValue(null);
		buscarporfech_carne.setValue(null);
		anoscarne.setValue(null);

	}

	@FXML
	private void MostrarTodoTRazaCarne() {
		listadinamicatrazacarne.clear();
		listadinamicatrazacarne.addAll(TrazCarneDAO.SelectAll());
		buscarmes_traza_carne.setValue(null);
		buscarporfech_carne.setValue(null);
		anos.setValue(null);
		buscar_fecha_ini_trazcarne.setValue(null);
		buscar_fecha_fin_trazcarne.setValue(null);
		buscar_fecha_llegada_trazcarne.setValue(null);
	}

	@FXML
	private void BuscarPorFechaCarne() {
		if (buscarporfech_carne.getValue() != null) {
			listcarnes.clear();
			listcarnes.addAll(SanidadDAO.SelecByFecha(buscarporfech_carne.getValue().toString()));
			if (buscarmes_carne.getValue() != null) {
				buscarmes_carne.setValue(null);
			}
		}
	}

	@FXML
	private void BuscarPorMesCarne() {
		if (buscarmes_carne.getValue() != null) {
			listcarnes.clear();
			listcarnes.addAll(SanidadDAO.SelecPorMes(Integer.toString(buscarmes_carne.getValue().getNumero_Mes())));
			if (buscarporfech_carne.getValue() != null) {
				buscarporfech_carne.setValue(null);
			}
		}
		if (buscarmes_carne.getValue() != null || anoscarne.getValue() != null) {
			listcarnes.clear();
			if (buscarmes_carne.getValue() != null && anoscarne.getValue() != null) {
				listcarnes
						.addAll(SanidadDAO.SelecPorMesyAño(Integer.toString(buscarmes_carne.getValue().getNumero_Mes()),
								Integer.toString(anoscarne.getValue().intValue())));
			} else if (buscarmes_carne.getValue() != null && anoscarne.getValue() == null) {
				listcarnes.addAll(SanidadDAO.SelecPorMes(Integer.toString(buscarmes_carne.getValue().getNumero_Mes())));
			} else if (buscarmes_carne.getValue() == null && anoscarne.getValue() != null) {
				listcarnes.addAll(SanidadDAO.SelecPorAño(Integer.toString(anoscarne.getValue().intValue())));
			}

		}
	}

	@FXML
	private void BuscarPorMesYAñoTrazCarne() {
		if (buscarmes_traza_carne.getValue() != null || anos.getValue() != null) {

			if (buscarmes_traza_carne.getValue() != null && anos.getValue() != null) {
				listadinamicatrazacarne.clear();
				listadinamicatrazacarne.addAll(TrazCarneDAO
						.SelectMesYAño(buscarmes_traza_carne.getValue().getNumero_Mes(), anos.getValue().intValue()));
			} else if (buscarmes_traza_carne.getValue() != null && anos.getValue() == null) {
				listadinamicatrazacarne.clear();
				listadinamicatrazacarne
						.addAll(TrazCarneDAO.SelectMes(buscarmes_traza_carne.getValue().getNumero_Mes()));
			} else if (buscarmes_traza_carne.getValue() == null && anos.getValue() != null) {
				listadinamicatrazacarne.clear();
				listadinamicatrazacarne.addAll(TrazCarneDAO.SelectAno(anos.getValue().intValue()));
			}

			buscar_fecha_ini_trazcarne.setValue(null);
			buscar_fecha_fin_trazcarne.setValue(null);
			buscar_fecha_llegada_trazcarne.setValue(null);

		}

	}

	@FXML
	private void BuscarFechaLlegadaCarne() {
		if (buscar_fecha_llegada_trazcarne.getValue() != null) {
			listadinamicatrazacarne.clear();
			listadinamicatrazacarne
					.addAll(TrazCarneDAO.SelectLlegadaCarne(buscar_fecha_llegada_trazcarne.getValue().toString()));
			buscar_fecha_ini_trazcarne.setValue(null);
			buscar_fecha_fin_trazcarne.setValue(null);
			buscarmes_traza_carne.setValue(null);
			anos.setValue(null);
		}
	}

	@FXML
	private void BuscarFechaIniTrazCArne() {
		if (buscar_fecha_ini_trazcarne.getValue() != null) {
			listadinamicatrazacarne.clear();
			listadinamicatrazacarne
					.addAll(TrazCarneDAO.SelectFechaIni(buscar_fecha_ini_trazcarne.getValue().toString()));
			buscar_fecha_fin_trazcarne.setValue(null);
			buscarmes_traza_carne.setValue(null);
			anos.setValue(null);
			buscar_fecha_llegada_trazcarne.setValue(null);
		}
	}

	@FXML
	private void BuscarFechaFinTrazCArne() {
		if (buscar_fecha_fin_trazcarne.getValue() != null) {
			listadinamicatrazacarne.clear();
			listadinamicatrazacarne
					.addAll(TrazCarneDAO.SelectFechaFin(buscar_fecha_fin_trazcarne.getValue().toString()));
			buscarmes_traza_carne.setValue(null);
			anos.setValue(null);
			buscar_fecha_llegada_trazcarne.setValue(null);
			buscar_fecha_ini_trazcarne.setValue(null);
		}
	}

	@FXML
	private void AddCarne() {
		SanidadProducto add = tabla_carne.getSelectionModel().getSelectedItem();
		procarneaañadir = add;
		if (procarneaañadir != null) {
			anadirfecharefistro_carne.setValue(add.getFecha());
			productodoranadirtraza_carne.setText(add.getNombre_producto());
			proveedoranadirtraza_carne.setText(add.getProveedor());
			lotedoranadirtraza_carne.setText(add.getCodigolote());
		}
	}

	@FXML
	private void LimpiarAddRegistroCarne() {
		add_fecha_fin_carne.setValue(null);
		add_fechainicio_carne.setValue(null);
		firmado_traz_carne.setValue(null);
		lotedoranadirtraza_carne.clear();
		productodoranadirtraza_carne.clear();
		proveedoranadirtraza_carne.clear();
		procarneaañadir = null;
		anadirfecharefistro_carne.setValue(null);

	}

	@FXML
	private void Noclikear() {
		if (procarneaañadir != null) {
			anadirfecharefistro_carne.setValue(procarneaañadir.getFecha());
		} else {
			anadirfecharefistro_carne.setValue(null);
		}
	}

	@FXML
	private void AddalRegistroCarneTRaz() {
		if (procarneaañadir != null) {
			TrazCarne nuevo = new TrazCarne(procarneaañadir, add_fechainicio_carne.getValue(),
					add_fecha_fin_carne.getValue(), firmado_traz_carne.getValue());
			TrazCarneDAO add = new TrazCarneDAO(nuevo);
			if (add.update() > 0) {
				listadinamicatrazacarne.clear();
				listadinamicatrazacarne.addAll(TrazCarneDAO.SelectAll());
				LimpiarAddRegistroCarne();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				darAños();
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
						"Revise que todos los campos están rellenos y son válidos");
			}
		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Añada algún producto cárnico");
		}

	}

	@FXML
	private void EliminarRegistroTrazCarne() {
		TrazCarne ei = tabla_traz_carne.getSelectionModel().getSelectedItem();
		TrazCarneDAO elimi = new TrazCarneDAO(ei);
		if (confirm("", "", "")) {
			if (elimi.remuve() > 0) {
				listadinamicatrazacarne.remove(ei);

				if (listadinamicatrazacarne.isEmpty()) {
					MostrarTodoTRazaCarne();
					darAños();
				}

			} else {
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}

	}

	@FXML
	private void Pikeaartrazcarne() {
		App.CambiarTitulo("TRAZABILIDAD CARNE");
	}

	@FXML
	private void PikeaartrazLaure() {
		App.CambiarTitulo("TRAZABILIDAD LAURE");
		if (listadinamicalaure == null) {
			listadinamicalaure = FXCollections.observableArrayList();
			MOstrarTodoLaure();
			tdoslosaños_laure = new TreeSet<>();
			darañoslaure();
			addproveedor_laure.setText("LAURE");
			addproveedor_laure.setEditable(false);
			firmar_registro_laure.getItems().addAll(Estado.values());
			buscar_meses_laure.getItems().addAll(Meses.values());
			for (Busqueda b : Busqueda.values()) {
				tiposbusqueda_laure.getItems().add(b.getCadena());
			}
			lableslaure.setDisable(true);
			buscarlaure.setDisable(true);
			tiposbusqueda_laure.setDisable(true);
			gobuscar_laure.setDisable(true);
			busquedaconcreta_laure.setDisable(true);

			this.fecha_fin_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
			});
			this.fecha_ini_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
			});
			this.fecha_lleg_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
			});
			this.lote_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
			});
			this.mercancia_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
			});
			this.proveedor_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
			});
			this.firmado_traz_laure.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
			});
			firmado_traz_laure.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
			firmado_traz_laure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Laure, Estado>>() {

				@Override
				public void handle(CellEditEvent<Laure, Estado> event) {

					Laure n = (Laure) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFirmado(event.getNewValue());
					LaureDAO guardar = new LaureDAO(n);
					guardar.update();

				}
			});
			fecha_fin_traz_laure.setCellFactory(param -> new LocalDateTableCell<>(param));
			fecha_fin_traz_laure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Laure, LocalDate>>() {

				@Override
				public void handle(CellEditEvent<Laure, LocalDate> event) {
					Laure n = (Laure) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFecha_fin(event.getNewValue());
					LaureDAO guardar = new LaureDAO(n);
					guardar.update();

				}
			});
			fecha_ini_traz_laure.setCellFactory(param -> new LocalDateTableCell<>(param));
			fecha_ini_traz_laure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Laure, LocalDate>>() {

				@Override
				public void handle(CellEditEvent<Laure, LocalDate> event) {
					Laure n = (Laure) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFecha_in(event.getNewValue());
					LaureDAO guardar = new LaureDAO(n);
					guardar.update();

				}
			});
			fecha_lleg_traz_laure.setCellFactory(param -> new LocalDateTableCell<>(param));
			fecha_lleg_traz_laure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Laure, LocalDate>>() {

				@Override
				public void handle(CellEditEvent<Laure, LocalDate> event) {
					Laure n = (Laure) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFecha_llegada(event.getNewValue());
					LaureDAO guardar = new LaureDAO(n);
					guardar.update();

				}
			});
			lote_traz_laure.setCellFactory(TextFieldTableCell.forTableColumn());
			lote_traz_laure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Laure, String>>() {

				@Override
				public void handle(CellEditEvent<Laure, String> event) {
					Laure n = (Laure) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setLote(event.getNewValue());
					LaureDAO guardar = new LaureDAO(n);
					guardar.update();

				}
			});
			mercancia_traz_laure.setCellFactory(TextFieldTableCell.forTableColumn());
			mercancia_traz_laure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Laure, String>>() {

				@Override
				public void handle(CellEditEvent<Laure, String> event) {
					Laure n = (Laure) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setMercancia(event.getNewValue());
					LaureDAO guardar = new LaureDAO(n);
					guardar.update();

				}
			});

			tablatrazlaure.setEditable(true);
			tablatrazlaure.setItems(listadinamicalaure);

		}
	}

	@FXML
	private void LimpiarregistroLaure() {
		addfechafin_laure.setValue(null);
		addfechainicio_laure.setValue(null);
		addfechallegada_laure.setValue(null);
		addmercancia_laure.clear();
		addlote_laure.clear();
		firmar_registro_laure.setValue(null);

	}

	@FXML
	private void AddRegistroLaure() {
		if (addfechallegada_laure.getValue() != null && addlote_laure.getText().length() > 0
				&& addmercancia_laure.getText().length() > 0) {
			Laure nu = new Laure(addfechallegada_laure.getValue(), addmercancia_laure.getText().toUpperCase(),
					addlote_laure.getText(), addfechainicio_laure.getValue(), addfechafin_laure.getValue(),
					firmar_registro_laure.getValue());
			LaureDAO add = new LaureDAO(nu);
			if (add.update() > 0) {
				MOstrarTodoLaure();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				LimpiarregistroLaure();
				darañoslaure();
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "Problema al conectar con la base de datos", "  ");
			}
		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que campos: fecha inicio, mercancia y lote están rellenos y son válidos");
		}

	}

	@FXML
	private void MOstrarTodoLaure() {
		listadinamicalaure.clear();
		listadinamicalaure.addAll(LaureDAO.SelectAll());
		busquedafechaconcreta_laure.setSelected(false);
		busquedaampliada_laure.setSelected(false);
		lableslaure.setDisable(true);
		buscarlaure.setDisable(true);
		buscar_meses_laure.setValue(null);
		anosdisponibles_laure.setValue(null);
		busquedaconcreta_laure.setDisable(true);
		busquedaconcreta_laure.setValue(null);
		gobuscar_laure.setDisable(true);
		tiposbusqueda_laure.setDisable(true);
		tiposbusqueda_laure.setValue(null);
	}

	private void darañoslaure() {
		anosdisponibles_laure.getItems().clear();
		tdoslosaños_laure.clear();
		for (Laure l : listadinamicalaure) {
			tdoslosaños_laure.add(l.getFecha_llegada().getYear());
			if (l.getFecha_fin() != null) {
				tdoslosaños_laure.add(l.getFecha_fin().getYear());
			}
			if (l.getFecha_in() != null) {
				tdoslosaños_laure.add(l.getFecha_in().getYear());
			}
		}
		anosdisponibles_laure.getItems().addAll(tdoslosaños_laure);
	}

	@FXML
	private void BuscarLaure() {
		if (busquedafechaconcreta_laure.isSelected()) {
			if (busquedaconcreta_laure.getValue() != null && tiposbusqueda_laure.getValue() != null) {
				listadinamicalaure.clear();
				if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
					listadinamicalaure
							.addAll(LaureDAO.SelectFechaConcretaFin(busquedaconcreta_laure.getValue().toString()));
				} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
					listadinamicalaure
							.addAll(LaureDAO.SelectFechaConcretaInicio(busquedaconcreta_laure.getValue().toString()));
				} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
					listadinamicalaure
							.addAll(LaureDAO.SelectFechaConcretaLLegada(busquedaconcreta_laure.getValue().toString()));
				}
			}
		} else if (busquedaampliada_laure.isSelected()) {
			if (tiposbusqueda_laure.getValue() != null
					&& (buscar_meses_laure.getValue() != null || anosdisponibles_laure.getValue() != null)) {
				listadinamicalaure.clear();
				if (buscar_meses_laure.getValue() != null && anosdisponibles_laure.getValue() != null) {
					if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaFinMesyAño(buscar_meses_laure.getValue().getNumero_Mes(),
										anosdisponibles_laure.getValue().intValue()));
					} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaInicioMesyAño(buscar_meses_laure.getValue().getNumero_Mes(),
										anosdisponibles_laure.getValue().intValue()));
					} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listadinamicalaure.addAll(
								LaureDAO.SelectFechaLlegadaMesyAño(buscar_meses_laure.getValue().getNumero_Mes(),
										anosdisponibles_laure.getValue().intValue()));
					}

				} else if (buscar_meses_laure.getValue() != null && anosdisponibles_laure.getValue() == null) {
					if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaFinMes(buscar_meses_laure.getValue().getNumero_Mes()));
					} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaInicioMes(buscar_meses_laure.getValue().getNumero_Mes()));
					} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaLlegadaMes(buscar_meses_laure.getValue().getNumero_Mes()));
					}
				} else if (buscar_meses_laure.getValue() == null && anosdisponibles_laure.getValue() != null) {
					if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaFinAño(anosdisponibles_laure.getValue().intValue()));
					} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaInicioAño(anosdisponibles_laure.getValue().intValue()));
					} else if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listadinamicalaure
								.addAll(LaureDAO.SelectFechaLlegadaAño(anosdisponibles_laure.getValue().intValue()));
					}
				}
			}
		}
	}

	@FXML
	private void RadioButtonFechaConcreta() {

		if (!busquedafechaconcreta_laure.isSelected()) {
			busquedaconcreta_laure.setDisable(true);
			busquedaconcreta_laure.setValue(null);
			tiposbusqueda_laure.setDisable(true);
			gobuscar_laure.setDisable(true);
		} else {
			busquedaconcreta_laure.setDisable(false);
			tiposbusqueda_laure.setDisable(false);
			gobuscar_laure.setDisable(false);
			lableslaure.setDisable(true);
			buscarlaure.setDisable(true);
			busquedaampliada_laure.setSelected(false);
			buscar_meses_laure.setValue(null);
			anosdisponibles_laure.setValue(null);
			tiposbusqueda_laure.setValue(null);
		}

	}

	@FXML
	private void RadioButtonAmpliado() {
		if (!busquedaampliada_laure.isSelected()) {
			tiposbusqueda_laure.setDisable(true);
			gobuscar_laure.setDisable(true);
			buscarlaure.setDisable(true);
			lableslaure.setDisable(true);
			buscar_meses_laure.setValue(null);
			anosdisponibles_laure.setValue(null);
		} else {
			busquedaconcreta_laure.setValue(null);
			gobuscar_laure.setDisable(false);
			buscarlaure.setDisable(false);
			lableslaure.setDisable(false);
			tiposbusqueda_laure.setDisable(false);
			busquedaconcreta_laure.setDisable(true);
			busquedafechaconcreta_laure.setSelected(false);
			tiposbusqueda_laure.setValue(null);
		}
	}

	@FXML
	private void EliminarRegistroLaure() {
		Laure remuve = tablatrazlaure.getSelectionModel().getSelectedItem();
		if (remuve != null) {
			LaureDAO eli = new LaureDAO(remuve);
			if (confirm("", "", "")) {
				if (eli.remuve() > 0) {
					listadinamicalaure.remove(remuve);
					muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
					if (listadinamicalaure.isEmpty()) {
						MOstrarTodoLaure();
						darañoslaure();
					}
				} else {
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}
	}

	@FXML
	private void PikeaartrazeEnvases() {
		App.CambiarTitulo("TRAZABILIDAD ENVASES");
		if (listaenvases == null) {
			listaenvases = FXCollections.observableArrayList();
			MostrarTodoEnvases();
			todosañosdisponibles_envases = new TreeSet<>();
			DarAñosEnvases();
			addfirmado_envases.getItems().setAll(Estado.values());
			mesesbusqueda_envases.getItems().addAll(Meses.values());
			for (Busqueda b : Busqueda.values()) {
				tipos_busqueda_envases.getItems().add(b.getCadena());
			}

			this.columna_fechafin_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
			});
			this.columna_fechainicio_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
			});
			this.columna_fechallegada_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
			});
			this.columna_lote_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
			});
			this.columna_mercancia_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
			});
			this.columna_proveedor_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
			});
			this.columna_firmado_envases.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
			});
			columna_firmado_envases
					.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
			columna_firmado_envases.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, Estado>>() {

				@Override
				public void handle(CellEditEvent<Envase, Estado> event) {
					Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFirmado(event.getNewValue());
					EnvasesDAO guardar = new EnvasesDAO(n);
					guardar.update();

				}
			});
			columna_fechafin_envases.setCellFactory(param -> new LocalDateTableCell<>(param));
			columna_fechafin_envases.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, LocalDate>>() {

				@Override
				public void handle(CellEditEvent<Envase, LocalDate> event) {
					Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFecha_fin(event.getNewValue());
					EnvasesDAO guardar = new EnvasesDAO(n);
					if (guardar.update() > 0) {
						DarAñosEnvases();
					}

				}
			});
			columna_fechainicio_envases.setCellFactory(param -> new LocalDateTableCell<>(param));
			columna_fechainicio_envases
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, LocalDate>>() {

						@Override
						public void handle(CellEditEvent<Envase, LocalDate> event) {
							Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
							n.setFecha_in(event.getNewValue());
							EnvasesDAO guardar = new EnvasesDAO(n);
							if (guardar.update() > 0) {
								DarAñosEnvases();
							}
							;

						}
					});
			columna_fechallegada_envases.setCellFactory(param -> new LocalDateTableCell<>(param));
			columna_fechallegada_envases
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, LocalDate>>() {

						@Override
						public void handle(CellEditEvent<Envase, LocalDate> event) {
							Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
							n.setFecha_llegada(event.getNewValue());
							EnvasesDAO guardar = new EnvasesDAO(n);
							if (guardar.update() > 0) {
								DarAñosEnvases();
							}

						}
					});
			columna_lote_envases.setCellFactory(TextFieldTableCell.forTableColumn());
			columna_lote_envases.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, String>>() {

				@Override
				public void handle(CellEditEvent<Envase, String> event) {
					Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setLote(event.getNewValue());
					EnvasesDAO guardar = new EnvasesDAO(n);
					guardar.update();

				}
			});
			columna_mercancia_envases.setCellFactory(TextFieldTableCell.forTableColumn());
			columna_mercancia_envases.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, String>>() {

				@Override
				public void handle(CellEditEvent<Envase, String> event) {
					Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setMercancia(event.getNewValue());
					EnvasesDAO guardar = new EnvasesDAO(n);
					guardar.update();

				}
			});
			columna_proveedor_envases.setCellFactory(TextFieldTableCell.forTableColumn());
			columna_proveedor_envases.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Envase, String>>() {

				@Override
				public void handle(CellEditEvent<Envase, String> event) {
					Envase n = (Envase) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setProveedor(event.getNewValue());
					EnvasesDAO guardar = new EnvasesDAO(n);
					guardar.update();

				}
			});

			tabla_envases.setEditable(true);
			tabla_envases.setItems(listaenvases);
		}
	}

	@FXML
	private void MostrarTodoEnvases() {
		listaenvases.clear();
		listaenvases.addAll(EnvasesDAO.SelectAll());
		fechabusquedaconcreta_envases.setDisable(true);
		fechabusquedaconcreta_envases.setValue(null);
		VBoxLables_envases.setDisable(true);
		tipos_busqueda_envases.setDisable(true);
		tipos_busqueda_envases.setValue(null);
		botonbuscar_ensases.setDisable(true);
		VBoxbusquedaabanzada_envases.setDisable(true);
		anosdisponibles_envases.setValue(null);
		mesesbusqueda_envases.setValue(null);
		radiobuttonbusquedaconcreta.setSelected(false);
		radiobuttonbusquedaabanzada.setSelected(false);
	}

	@FXML
	private void DarAñosEnvases() {
		anosdisponibles_envases.getItems().clear();
		todosañosdisponibles_envases.clear();
		for (Envase e : listaenvases) {
			todosañosdisponibles_envases.add(e.getFecha_llegada().getYear());
			if (e.getFecha_fin() != null) {
				todosañosdisponibles_envases.add(e.getFecha_fin().getYear());
			}
			if (e.getFecha_in() != null) {
				todosañosdisponibles_envases.add(e.getFecha_in().getYear());
			}
		}
		anosdisponibles_envases.getItems().addAll(todosañosdisponibles_envases);
	}

	@FXML
	private void LimpiarRegistroEnvases() {
		addfechallegada_envases.setValue(null);
		addproveedor_envases.clear();
		addmercancia_envases.clear();
		addlote_envases.clear();
		addfechainicio_envases.setValue(null);
		addfechafin_envases.setValue(null);
		addfirmado_envases.setValue(null);
	}

	@FXML
	private void AddRegistroEnvases() {
		if (addfechallegada_envases.getValue() != null && addlote_envases.getText().length() > 0
				&& addmercancia_envases.getText().length() > 0 && addproveedor_envases.getText().length() > 0) {
			Envase nu = new Envase(addfechallegada_envases.getValue(), addproveedor_envases.getText().toUpperCase(),
					addmercancia_envases.getText().toUpperCase(), addlote_envases.getText(), addfechainicio_envases.getValue(),
					addfechafin_envases.getValue(), addfirmado_envases.getValue());
			EnvasesDAO add = new EnvasesDAO(nu);
			if (add.update() > 0) {
				MostrarTodoEnvases();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				LimpiarRegistroEnvases();
				DarAñosEnvases();
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "Problema al conectar con la base de datos", "  ");
			}
		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que campos: fecha inicio, mercancia y lote están rellenos y son válidos");
		}
	}

	@FXML
	private void EliminarRegistroEnvases() {
		Envase remuve = tabla_envases.getSelectionModel().getSelectedItem();
		if (remuve != null) {
			EnvasesDAO eli = new EnvasesDAO(remuve);
			if (confirm("", "", "")) {
				if (eli.remuve() > 0) {
					listaenvases.remove(remuve);
					muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
					if (listaenvases.isEmpty()) {
						MostrarTodoEnvases();
						DarAñosEnvases();
					}
				} else {
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}
	}

	@FXML
	private void BuscarEnvases() {
		if (radiobuttonbusquedaconcreta.isSelected()) {
			if (fechabusquedaconcreta_envases.getValue() != null && tipos_busqueda_envases.getValue() != null) {
				listaenvases.clear();
				if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
					listaenvases.addAll(
							EnvasesDAO.SelectFechaConcretaFin(fechabusquedaconcreta_envases.getValue().toString()));
				} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
					listaenvases.addAll(
							EnvasesDAO.SelectFechaConcretaInicio(fechabusquedaconcreta_envases.getValue().toString()));
				} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
					listaenvases.addAll(
							EnvasesDAO.SelectFechaConcretaLLegada(fechabusquedaconcreta_envases.getValue().toString()));
				}
			}
		} else if (radiobuttonbusquedaabanzada.isSelected()) {
			if (tipos_busqueda_envases.getValue() != null
					&& (mesesbusqueda_envases.getValue() != null || anosdisponibles_envases.getValue() != null)) {
				listaenvases.clear();
				if (mesesbusqueda_envases.getValue() != null && anosdisponibles_envases.getValue() != null) {
					if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listaenvases.addAll(
								EnvasesDAO.SelectFechaFinMesyAño(mesesbusqueda_envases.getValue().getNumero_Mes(),
										anosdisponibles_envases.getValue().intValue()));
					} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listaenvases.addAll(
								EnvasesDAO.SelectFechaInicioMesyAño(mesesbusqueda_envases.getValue().getNumero_Mes(),
										anosdisponibles_envases.getValue().intValue()));
					} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listaenvases.addAll(
								EnvasesDAO.SelectFechaLlegadaMesyAño(mesesbusqueda_envases.getValue().getNumero_Mes(),
										anosdisponibles_envases.getValue().intValue()));
					}

				} else if (mesesbusqueda_envases.getValue() != null && anosdisponibles_envases.getValue() == null) {
					if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listaenvases
								.addAll(EnvasesDAO.SelectFechaFinMes(mesesbusqueda_envases.getValue().getNumero_Mes()));
					} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listaenvases.addAll(
								EnvasesDAO.SelectFechaInicioMes(mesesbusqueda_envases.getValue().getNumero_Mes()));
					} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listaenvases.addAll(
								EnvasesDAO.SelectFechaLlegadaMes(mesesbusqueda_envases.getValue().getNumero_Mes()));
					}
				} else if (mesesbusqueda_envases.getValue() == null && anosdisponibles_envases.getValue() != null) {
					if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listaenvases
								.addAll(EnvasesDAO.SelectFechaFinAño(anosdisponibles_envases.getValue().intValue()));
					} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listaenvases
								.addAll(EnvasesDAO.SelectFechaInicioAño(anosdisponibles_envases.getValue().intValue()));
					} else if (tipos_busqueda_envases.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listaenvases.addAll(
								EnvasesDAO.SelectFechaLlegadaAño(anosdisponibles_envases.getValue().intValue()));
					}
				}
			}
		}
	}

	@FXML
	private void CambiarAbusquedaCOncreta() {
		if (!radiobuttonbusquedaconcreta.isSelected()) {
			fechabusquedaconcreta_envases.setValue(null);
			fechabusquedaconcreta_envases.setDisable(true);
			tipos_busqueda_envases.setDisable(true);
			tipos_busqueda_envases.setValue(null);
			botonbuscar_ensases.setDisable(true);
		} else {
			fechabusquedaconcreta_envases.setDisable(false);
			tipos_busqueda_envases.setDisable(false);
			tipos_busqueda_envases.setValue(null);
			botonbuscar_ensases.setDisable(false);
			radiobuttonbusquedaabanzada.setSelected(false);
			VBoxLables_envases.setDisable(true);
			VBoxbusquedaabanzada_envases.setDisable(true);
			mesesbusqueda_envases.setValue(null);
			anosdisponibles_envases.setValue(null);

		}

	}

	@FXML
	private void CambiarAbusquedaAmpliada() {
		if (!radiobuttonbusquedaabanzada.isSelected()) {
			VBoxLables_envases.setDisable(true);
			VBoxbusquedaabanzada_envases.setDisable(true);
			mesesbusqueda_envases.setValue(null);
			anosdisponibles_envases.setValue(null);
			botonbuscar_ensases.setDisable(true);
			tipos_busqueda_envases.setValue(null);
			tipos_busqueda_envases.setDisable(true);
		} else {
			radiobuttonbusquedaconcreta.setSelected(false);
			fechabusquedaconcreta_envases.setValue(null);
			fechabusquedaconcreta_envases.setDisable(true);
			VBoxLables_envases.setDisable(false);
			tipos_busqueda_envases.setValue(null);
			tipos_busqueda_envases.setDisable(false);
			botonbuscar_ensases.setDisable(false);
			VBoxbusquedaabanzada_envases.setDisable(false);
		}
	}

	@FXML
	private void PikeaartrazMateriaPrima() {
		App.CambiarTitulo("TRAZABILIDAD MATERIA PRIMA");
		if (listamateriaprima == null) {
			listamateriaprima = FXCollections.observableArrayList();
			MostrarTodoRegistroMateriaPrima();
			añosdisponibles_materiaprima = new TreeSet<>();
			DarAñosMateriaPrima();
			addfirmado_trazmateriaprima.getItems().addAll(Estado.values());
			meses_trazmateriaprima.getItems().addAll(Meses.values());
			for (Busqueda b : Busqueda.values()) {
				tiposbusqueda_trazmateriaprima.getItems().add(b.getCadena());
			}

			this.colum_fechafin_trazmateriaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
			});
			this.colum_fechainicio_trazmateriaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
			});
			this.colum_fechallegada_trazmateriaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
			});
			this.colum_lote_materiaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
			});
			this.colum_mercancia_materiaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
			});
			this.colum_proveedor_materiaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
			});
			this.colum_firmado_materiaprima.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
			});
			colum_firmado_materiaprima
					.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
			colum_firmado_materiaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, Estado>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, Estado> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setFirmado(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});
			colum_fechafin_trazmateriaprima.setCellFactory(param -> new LocalDateTableCell<>(param));
			colum_fechafin_trazmateriaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, LocalDate>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, LocalDate> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setFecha_fin(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});
			colum_fechainicio_trazmateriaprima.setCellFactory(param -> new LocalDateTableCell<>(param));
			colum_fechainicio_trazmateriaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, LocalDate>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, LocalDate> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setFecha_in(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});
			colum_fechallegada_trazmateriaprima.setCellFactory(param -> new LocalDateTableCell<>(param));
			colum_fechallegada_trazmateriaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, LocalDate>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, LocalDate> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setFecha_llegada(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});
			colum_lote_materiaprima.setCellFactory(TextFieldTableCell.forTableColumn());
			colum_lote_materiaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, String>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, String> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setLote(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});
			colum_mercancia_materiaprima.setCellFactory(TextFieldTableCell.forTableColumn());
			colum_mercancia_materiaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, String>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, String> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setMercancia(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});
			colum_proveedor_materiaprima.setCellFactory(TextFieldTableCell.forTableColumn());
			colum_proveedor_materiaprima
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MateriaPrima, String>>() {

						@Override
						public void handle(CellEditEvent<MateriaPrima, String> event) {
							MateriaPrima n = (MateriaPrima) event.getTableView().getItems()
									.get(event.getTablePosition().getRow());
							n.setProveedor(event.getNewValue());
							MateriaPrimaDAO guardar = new MateriaPrimaDAO(n);
							guardar.Update();

						}
					});

			tablatraz_materiaprima.setEditable(true);
			tablatraz_materiaprima.setItems(listamateriaprima);
		}
	}

	@FXML
	private void EliminarRegistroMateriaPrima() {
		MateriaPrima remuve = tablatraz_materiaprima.getSelectionModel().getSelectedItem();
		if (remuve != null) {
			MateriaPrimaDAO eli = new MateriaPrimaDAO(remuve);
			if (confirm("", "", "")) {
				if (eli.remuve() > 0) {
					listamateriaprima.remove(remuve);
					muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
					if (listamateriaprima.isEmpty()) {
						MostrarTodoRegistroMateriaPrima();
						DarAñosMateriaPrima();
					}
				} else {
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}
	}

	@FXML
	private void AnadirRegistroMateriaPrima() {
		if (addfechallegada_trazmateriaprima.getValue() != null && addlote_trazmateriaprima.getText().length() > 0
				&& addmercancia_trazmateriaprima.getText().length() > 0
				&& addproveedor_trazmateriaprima.getText().length() > 0) {
			MateriaPrima nu = new MateriaPrima(addfechallegada_trazmateriaprima.getValue(),
					addproveedor_trazmateriaprima.getText().toUpperCase(), addmercancia_trazmateriaprima.getText().toUpperCase(),
					addlote_trazmateriaprima.getText(), addfechainicio_trazmateriaprima.getValue(),
					addfechafin_trazmateriaprima.getValue(), addfirmado_trazmateriaprima.getValue());
			MateriaPrimaDAO add = new MateriaPrimaDAO(nu);
			if (add.Update() > 0) {
				MostrarTodoRegistroMateriaPrima();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				LimmpiarRegistroMateriaPrima();
				DarAñosMateriaPrima();
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "Problema al conectar con la base de datos", "  ");
			}
		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que campos: fecha inicio, mercancia y lote están rellenos y son válidos");
		}
	}

	@FXML
	private void MostrarTodoRegistroMateriaPrima() {
		listamateriaprima.clear();
		listamateriaprima.addAll(MateriaPrimaDAO.SelectAll());
		cambiafechaconcreta_materiaprima.setSelected(false);
		cambiardefechaampliada_materiaprima.setSelected(false);
		busquedaconcreta_trazmateriaprima.setValue(null);
		busquedaconcreta_trazmateriaprima.setDisable(true);
		labels_trazmateriaprima.setDisable(true);
		busquedaampliadavbox_trazmateriaprima.setDisable(true);
		meses_trazmateriaprima.setValue(null);
		anos_trazmateriaprima.setValue(null);
		buscar_trazmateriaprima.setDisable(true);
		tiposbusqueda_trazmateriaprima.setDisable(true);

	}

	@FXML
	private void LimmpiarRegistroMateriaPrima() {
		addfechallegada_trazmateriaprima.setValue(null);
		addproveedor_trazmateriaprima.clear();
		addmercancia_trazmateriaprima.clear();
		addlote_trazmateriaprima.clear();
		addfechainicio_trazmateriaprima.setValue(null);
		addfechafin_trazmateriaprima.setValue(null);
		addfirmado_trazmateriaprima.setValue(null);

	}

	@FXML
	private void BuscarMateriaPrima() {
		if (cambiafechaconcreta_materiaprima.isSelected()) {
			if (busquedaconcreta_trazmateriaprima.getValue() != null
					&& tiposbusqueda_trazmateriaprima.getValue() != null) {
				listamateriaprima.clear();
				if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
					listamateriaprima.addAll(MateriaPrimaDAO
							.SelectFechaConcretaFin(busquedaconcreta_trazmateriaprima.getValue().toString()));
				} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
					listamateriaprima.addAll(MateriaPrimaDAO
							.SelectFechaConcretaInicio(busquedaconcreta_trazmateriaprima.getValue().toString()));
				} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
					listamateriaprima.addAll(MateriaPrimaDAO
							.SelectFechaConcretaLLegada(busquedaconcreta_trazmateriaprima.getValue().toString()));
				}
			}
		} else if (cambiardefechaampliada_materiaprima.isSelected()) {
			if (tiposbusqueda_trazmateriaprima.getValue() != null
					&& (meses_trazmateriaprima.getValue() != null || anos_trazmateriaprima.getValue() != null)) {
				listamateriaprima.clear();
				if (meses_trazmateriaprima.getValue() != null && anos_trazmateriaprima.getValue() != null) {
					if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listamateriaprima.addAll(
								MateriaPrimaDAO.SelectFechaFinMesyAño(meses_trazmateriaprima.getValue().getNumero_Mes(),
										anos_trazmateriaprima.getValue().intValue()));
					} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listamateriaprima.addAll(MateriaPrimaDAO.SelectFechaInicioMesyAño(
								meses_trazmateriaprima.getValue().getNumero_Mes(),
								anos_trazmateriaprima.getValue().intValue()));
					} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listamateriaprima.addAll(MateriaPrimaDAO.SelectFechaLlegadaMesyAño(
								meses_trazmateriaprima.getValue().getNumero_Mes(),
								anos_trazmateriaprima.getValue().intValue()));
					}

				} else if (meses_trazmateriaprima.getValue() != null && anos_trazmateriaprima.getValue() == null) {
					if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listamateriaprima.addAll(
								MateriaPrimaDAO.SelectFechaFinMes(meses_trazmateriaprima.getValue().getNumero_Mes()));
					} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listamateriaprima.addAll(MateriaPrimaDAO
								.SelectFechaInicioMes(meses_trazmateriaprima.getValue().getNumero_Mes()));
					} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listamateriaprima.addAll(MateriaPrimaDAO
								.SelectFechaLlegadaMes(meses_trazmateriaprima.getValue().getNumero_Mes()));
					}
				} else if (meses_trazmateriaprima.getValue() == null && anos_trazmateriaprima.getValue() != null) {
					if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listamateriaprima
								.addAll(MateriaPrimaDAO.SelectFechaFinAño(anos_trazmateriaprima.getValue().intValue()));
					} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listamateriaprima.addAll(
								MateriaPrimaDAO.SelectFechaInicioAño(anos_trazmateriaprima.getValue().intValue()));
					} else if (tiposbusqueda_trazmateriaprima.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listamateriaprima.addAll(
								MateriaPrimaDAO.SelectFechaLlegadaAño(anos_trazmateriaprima.getValue().intValue()));
					}
				}
			}
		}
	}

	@FXML
	private void CambiarBusquedaConcretaMateriPrima() {
		if (!cambiafechaconcreta_materiaprima.isSelected()) {
			busquedaconcreta_trazmateriaprima.setDisable(true);
			busquedaconcreta_trazmateriaprima.setValue(null);
			tiposbusqueda_trazmateriaprima.setValue(null);
			tiposbusqueda_trazmateriaprima.setDisable(true);
			buscar_trazmateriaprima.setDisable(true);

		} else {
			tiposbusqueda_trazmateriaprima.setValue(null);
			busquedaconcreta_trazmateriaprima.setDisable(false);
			cambiardefechaampliada_materiaprima.setSelected(false);
			tiposbusqueda_trazmateriaprima.setDisable(false);
			buscar_trazmateriaprima.setDisable(false);
			labels_trazmateriaprima.setDisable(true);
			busquedaampliadavbox_trazmateriaprima.setDisable(true);
			meses_trazmateriaprima.setValue(null);
			anos_trazmateriaprima.setValue(null);
		}

	}

	@FXML
	private void CambiarBusquedaAmpliadaMateriPrima() {
		if (!cambiardefechaampliada_materiaprima.isSelected()) {
			labels_trazmateriaprima.setDisable(true);
			tiposbusqueda_trazmateriaprima.setValue(null);
			tiposbusqueda_trazmateriaprima.setDisable(true);
			busquedaampliadavbox_trazmateriaprima.setDisable(true);
			meses_trazmateriaprima.setValue(null);
			anos_trazmateriaprima.setValue(null);
			buscar_trazmateriaprima.setDisable(true);
		} else {
			tiposbusqueda_trazmateriaprima.setValue(null);
			cambiafechaconcreta_materiaprima.setSelected(false);
			busquedaconcreta_trazmateriaprima.setValue(null);
			busquedaconcreta_trazmateriaprima.setDisable(true);
			labels_trazmateriaprima.setDisable(false);
			busquedaampliadavbox_trazmateriaprima.setDisable(false);
			buscar_trazmateriaprima.setDisable(false);
			tiposbusqueda_trazmateriaprima.setDisable(false);
		}
	}

	private void DarAñosMateriaPrima() {
		añosdisponibles_materiaprima.clear();
		anos_trazmateriaprima.getItems().clear();
		for (MateriaPrima mp : listamateriaprima) {
			añosdisponibles_materiaprima.add(mp.getFecha_llegada().getYear());
			if (mp.getFecha_fin() != null) {
				añosdisponibles_materiaprima.add(mp.getFecha_fin().getYear());
			}
			if (mp.getFecha_in() != null) {
				añosdisponibles_materiaprima.add(mp.getFecha_in().getYear());
			}
		}
		anos_trazmateriaprima.getItems().addAll(añosdisponibles_materiaprima);
	}

	@FXML
	private void PikeaartrazeSalsas() {
		App.CambiarTitulo("TRAZABILIDAD SALSAS");
		if (listasalsas == null) {
			listasalsas = FXCollections.observableArrayList();
			todoslosaños_salsas = new TreeSet<>();
			MostrarTodoSalsas();
			DarañosSalsas();
			addfirmado_salsa.getItems().setAll(Estado.values());
			for (Busqueda b : Busqueda.values()) {
				tiposbusqueeda_salsa.getItems().add(b.getCadena());
			}
			busquedameses_salsas.getItems().setAll(Meses.values());
			this.columna_fechafin_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
			});
			this.columna_fechaini_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
			});
			this.columna_fechallegada_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
			});
			this.columna_lote_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
			});
			this.columna_mercancia_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
			});
			this.columna_proveedor_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
			});
			this.columna_firmado_salsas.setCellValueFactory(eachRowData -> {
				return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
			});
			columna_firmado_salsas.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
			columna_firmado_salsas.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, Estado>>() {

				@Override
				public void handle(CellEditEvent<Salsa, Estado> event) {
					Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFirmado(event.getNewValue());
					SalsaDAO guardar = new SalsaDAO(n);
					guardar.update();

				}
			});
			columna_fechafin_salsas.setCellFactory(param -> new LocalDateTableCell<>(param));
			columna_fechafin_salsas.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, LocalDate>>() {

				@Override
				public void handle(CellEditEvent<Salsa, LocalDate> event) {
					Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFecha_fin(event.getNewValue());
					SalsaDAO guardar = new SalsaDAO(n);
					if (guardar.update() > 0) {
						DarañosSalsas();
					}

				}
			});
			columna_fechaini_salsas.setCellFactory(param -> new LocalDateTableCell<>(param));
			columna_fechaini_salsas.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, LocalDate>>() {

				@Override
				public void handle(CellEditEvent<Salsa, LocalDate> event) {
					Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setFecha_in(event.getNewValue());
					SalsaDAO guardar = new SalsaDAO(n);
					if (guardar.update() > 0) {
						DarañosSalsas();
					}

				}
			});
			columna_fechallegada_salsas.setCellFactory(param -> new LocalDateTableCell<>(param));
			columna_fechallegada_salsas
					.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, LocalDate>>() {

						@Override
						public void handle(CellEditEvent<Salsa, LocalDate> event) {
							Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
							n.setFecha_llegada(event.getNewValue());
							SalsaDAO guardar = new SalsaDAO(n);
							if (guardar.update() > 0) {
								DarañosSalsas();
							}

						}
					});
			columna_lote_salsas.setCellFactory(TextFieldTableCell.forTableColumn());
			columna_lote_salsas.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, String>>() {

				@Override
				public void handle(CellEditEvent<Salsa, String> event) {
					Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setLote(event.getNewValue());
					SalsaDAO guardar = new SalsaDAO(n);
					guardar.update();

				}
			});
			columna_mercancia_salsas.setCellFactory(TextFieldTableCell.forTableColumn());
			columna_mercancia_salsas.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, String>>() {

				@Override
				public void handle(CellEditEvent<Salsa, String> event) {
					Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setMercancia(event.getNewValue());
					SalsaDAO guardar = new SalsaDAO(n);
					guardar.update();

				}
			});
			columna_proveedor_salsas.setCellFactory(TextFieldTableCell.forTableColumn());
			columna_proveedor_salsas.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Salsa, String>>() {

				@Override
				public void handle(CellEditEvent<Salsa, String> event) {
					Salsa n = (Salsa) event.getTableView().getItems().get(event.getTablePosition().getRow());
					n.setProveedor(event.getNewValue());
					SalsaDAO guardar = new SalsaDAO(n);
					guardar.update();

				}
			});

			tabla_salsas.setEditable(true);
			tabla_salsas.setItems(listasalsas);
		}
	}

	@FXML
	private void MostrarTodoSalsas() {
		listasalsas.clear();
		listasalsas.addAll(SalsaDAO.SelectAll());
		radiobuttonconcreta_salsa.setSelected(false);
		radiobuttonampliado_salsa.setSelected(false);
		busquedaconcreta_salsas.setValue(null);
		busquedaconcreta_salsas.setDisable(true);
		labels_salsa.setDisable(true);
		vboxampliada_salsa.setDisable(true);
		botonbusqueda_salsa.setDisable(true);
		tiposbusqueeda_salsa.setDisable(true);
	}

	@FXML
	private void DarañosSalsas() {
		todoslosaños_salsas.clear();
		anosdisponibles_salsas.getItems().clear();
		for (Salsa s : listasalsas) {
			todoslosaños_salsas.add(s.getFecha_llegada().getYear());
			if (s.getFecha_fin() != null) {
				todoslosaños_salsas.add(s.getFecha_fin().getYear());
			}
			if (s.getFecha_in() != null) {
				todoslosaños_salsas.add(s.getFecha_in().getYear());
			}

		}
		anosdisponibles_salsas.getItems().addAll(todoslosaños_salsas);
	}

	@FXML
	private void BuscarSalsas() {
		if (radiobuttonconcreta_salsa.isSelected()) {
			if (busquedaconcreta_salsas.getValue() != null && tiposbusqueeda_salsa.getValue() != null) {
				listasalsas.clear();
				if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
					listasalsas.addAll(SalsaDAO.SelectFechaConcretaFin(busquedaconcreta_salsas.getValue().toString()));
				} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
					listasalsas
							.addAll(SalsaDAO.SelectFechaConcretaInicio(busquedaconcreta_salsas.getValue().toString()));
				} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
					listasalsas
							.addAll(SalsaDAO.SelectFechaConcretaLLegada(busquedaconcreta_salsas.getValue().toString()));
				}
			}
		} else if (radiobuttonampliado_salsa.isSelected()) {
			if (tiposbusqueeda_salsa.getValue() != null
					&& (busquedameses_salsas.getValue() != null || anosdisponibles_salsas.getValue() != null)) {
				listasalsas.clear();
				if (busquedameses_salsas.getValue() != null && anosdisponibles_salsas.getValue() != null) {
					if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listasalsas
								.addAll(SalsaDAO.SelectFechaFinMesyAño(busquedameses_salsas.getValue().getNumero_Mes(),
										anosdisponibles_salsas.getValue().intValue()));
					} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listasalsas.addAll(
								SalsaDAO.SelectFechaInicioMesyAño(busquedameses_salsas.getValue().getNumero_Mes(),
										anosdisponibles_salsas.getValue().intValue()));
					} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listasalsas.addAll(
								SalsaDAO.SelectFechaLlegadaMesyAño(busquedameses_salsas.getValue().getNumero_Mes(),
										anosdisponibles_salsas.getValue().intValue()));
					}

				} else if (busquedameses_salsas.getValue() != null && anosdisponibles_salsas.getValue() == null) {
					if (tiposbusqueda_laure.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listasalsas.addAll(SalsaDAO.SelectFechaFinMes(busquedameses_salsas.getValue().getNumero_Mes()));
					} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listasalsas
								.addAll(SalsaDAO.SelectFechaInicioMes(busquedameses_salsas.getValue().getNumero_Mes()));
					} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listasalsas.addAll(
								SalsaDAO.SelectFechaLlegadaMes(busquedameses_salsas.getValue().getNumero_Mes()));
					}
				} else if (busquedameses_salsas.getValue() == null && anosdisponibles_salsas.getValue() != null) {
					if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAFIN.getCadena())) {
						listasalsas.addAll(SalsaDAO.SelectFechaFinAño(anosdisponibles_salsas.getValue().intValue()));
					} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())) {
						listasalsas.addAll(SalsaDAO.SelectFechaInicioAño(anosdisponibles_salsas.getValue().intValue()));
					} else if (tiposbusqueeda_salsa.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())) {
						listasalsas
								.addAll(SalsaDAO.SelectFechaLlegadaAño(anosdisponibles_salsas.getValue().intValue()));
					}
				}
			}
		}

	}

	@FXML
	private void RadioButtonCambiarFechaConcreta() {
		if (!radiobuttonconcreta_salsa.isSelected()) {
			busquedaconcreta_salsas.setDisable(true);
			busquedaconcreta_salsas.setValue(null);
			botonbusqueda_salsa.setDisable(true);
			tiposbusqueeda_salsa.setDisable(true);
			tiposbusqueeda_salsa.setValue(null);

		} else {
			radiobuttonampliado_salsa.setSelected(false);
			busquedaconcreta_salsas.setDisable(false);
			tiposbusqueeda_salsa.setDisable(false);
			botonbusqueda_salsa.setDisable(false);
			vboxampliada_salsa.setDisable(true);
			labels_salsa.setDisable(true);
			busquedameses_salsas.setValue(null);
			anosdisponibles_salsas.setValue(null);
			tiposbusqueeda_salsa.setValue(null);
		}

	}

	@FXML
	private void RadioButtonCambiarFechaAmpliada() {
		if (!radiobuttonampliado_salsa.isSelected()) {
			tiposbusqueeda_salsa.setValue(null);
			tiposbusqueeda_salsa.setDisable(true);
			botonbusqueda_salsa.setDisable(true);
			vboxampliada_salsa.setDisable(true);
			labels_salsa.setDisable(true);
			busquedameses_salsas.setValue(null);
			anosdisponibles_salsas.setValue(null);

		} else {
			vboxampliada_salsa.setDisable(false);
			labels_salsa.setDisable(false);
			tiposbusqueeda_salsa.setDisable(false);
			tiposbusqueeda_salsa.setValue(null);
			botonbusqueda_salsa.setDisable(false);
			radiobuttonconcreta_salsa.setSelected(false);
			busquedaconcreta_salsas.setDisable(true);
			busquedaconcreta_salsas.setValue(null);

		}

	}

	@FXML
	private void AddRegistroSalsa() {
		if (addfechallegada_salsa.getValue() != null && add_lote_salsas.getText().length() > 0
				&& add_mercancia_salsas.getText().length() > 0 && add_proveedor_salsas.getText().length() > 0) {
			Salsa nu = new Salsa(addfechallegada_salsa.getValue(), add_proveedor_salsas.getText().toUpperCase(),
					add_mercancia_salsas.getText().toUpperCase(), add_lote_salsas.getText(), addfechainicio_salsa.getValue(),
					addfechafin_salsa.getValue(), addfirmado_salsa.getValue());
			SalsaDAO add = new SalsaDAO(nu);
			if (add.update() > 0) {
				MostrarTodoSalsas();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				LimpiarregistroiSalsas();
				DarañosSalsas();
			} else {
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "Problema al conectar con la base de datos", "  ");
			}
		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que campos: fecha inicio, mercancia y lote están rellenos y son válidos");
		}
	}

	@FXML
	private void LimpiarregistroiSalsas() {
		addfechallegada_salsa.setValue(null);
		addfechainicio_salsa.setValue(null);
		addfechafin_salsa.setValue(null);
		addfirmado_salsa.setValue(null);
		add_lote_salsas.clear();
		add_mercancia_salsas.clear();
		add_proveedor_salsas.clear();
	}

	@FXML
	private void EliminarRegistroSalsa() {
		Salsa remuve = tabla_salsas.getSelectionModel().getSelectedItem();
		if (remuve != null) {
			SalsaDAO eli = new SalsaDAO(remuve);
			if (confirm("", "", "")) {
				if (eli.remuve() > 0) {
					listasalsas.remove(remuve);
					muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
					if (listasalsas.isEmpty()) {
						MostrarTodoSalsas();
						DarañosSalsas();
					}
				} else {
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}
	}

}
