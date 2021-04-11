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
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import modelos.Laure;
import modelos.LaureDAO;
import modelos.MateriaPrima;
import modelos.MateriaPrimaDAO;
import modelos.Produccion;
import modelos.ProduccionDAO;
import modelos.Salsa;
import modelos.SalsaDAO;
import modelos.SanidadDAO;
import modelos.SanidadProducto;
import utilidades.LocalDateTableCell;

public class ControladorProduccion extends ControladorGenerar{

	private Set<Integer>  todoaños=new TreeSet<>();
	@FXML
	private VBox labels;
	@FXML
	private Button buscar;
	@FXML
	private TitledPane panelsalsas;
	@FXML
	private TitledPane materiaprima;
	@FXML
	private TitledPane laure;
	@FXML
	private TitledPane carne;
	@FXML
	private RadioButton radiobuttonampliado;
	@FXML
	private RadioButton radiobuttonconcreto;
	@FXML
	private DatePicker busquedaconcretatraz;
	@FXML
	private ChoiceBox<Meses> mesestraz;
	@FXML
	private ChoiceBox<Integer> anostraz;
	@FXML
	private ChoiceBox<String> tiposbusuqeda;
	@FXML
	private TextField verproductonombre;
	@FXML
	private TextField verproductocantidad;
	@FXML
	private ChoiceBox<Estado> verpriductofirmaddo;
	@FXML
	private DatePicker verproductofecha;
	/*
	 * TABLA CARNE
	 */
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

	private ObservableList<SanidadProducto> listadinamicacarnes;
	/*
	 * Tabla Salsas
	 */

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

	private ObservableList<Salsa> listadinamicasalsas;

	/*
	 * TABLA MATERIA PRIMA
	 */

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

	private ObservableList<MateriaPrima> listadinamicamateriaprima;

	/*
	 * TABLA ALIÑOS
	 */
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

	private ObservableList<Laure> listadinamicaaliños;
	/*
	 * TABLA CARNES DE UN REGISTRO
	 */
	@FXML
	private TableView<SanidadProducto> 	tabla_carnes_delregistro;
	@FXML
	private TableColumn<SanidadProducto, LocalDate> columna_fechallegadacarne_delregistro;
	@FXML
	private TableColumn<SanidadProducto, String> columna_proveedorcarne_delregistro;
	@FXML
	private TableColumn<SanidadProducto, String> columna_mercanciacarne_delregistro;
	@FXML
	private TableColumn<SanidadProducto, String> columna_lotecarne_delregistro;
	/*
	 * TABLA SALSAS DE UN REGISTRO
	 */
	@FXML
	private TableView<Salsa> 	tabla_salsas_delregistro;
	@FXML
	private TableColumn<Salsa, LocalDate> columna_fechallegadasalsa_delregistro;
	@FXML
	private TableColumn<Salsa, String> columna_proveedorsalsa_delregistro;
	@FXML
	private TableColumn<Salsa, String> columna_mercanciasalsa_delregistro;
	@FXML
	private TableColumn<Salsa, String> columna_lotesalsa_delregistro;
	@FXML
	private TableColumn<Salsa, LocalDate> columna_fechainiciosalsa_delregistro;
	@FXML
	private TableColumn<Salsa, LocalDate> columna_fechafinsalsa_delregistro;
	/*
	 * TABLA MATERIA PRIMA DE UN REGISTRO
	 */
	@FXML
	private TableView<MateriaPrima> 	tabla_materiaprima_delregistro;
	@FXML
	private TableColumn<MateriaPrima, LocalDate> columna_fechallegadamateriaprima_delregistro;
	@FXML
	private TableColumn<MateriaPrima, String> columna_proveedormateriaprima_delregistro;
	@FXML
	private TableColumn<MateriaPrima, String> columna_mercanciamateriaprima_delregistro;
	@FXML
	private TableColumn<MateriaPrima, String> columna_lotemateriaprima_delregistro;
	@FXML
	private TableColumn<MateriaPrima, LocalDate> columna_fechainiciomateriaprima_delregistro;
	@FXML
	private TableColumn<MateriaPrima, LocalDate> columna_fechafinmateriaprima_delregistro;
	/*
	 * TABLA Laure DE UN REGISTRO
	 */
	@FXML
	private TableView<Laure> tabla_laure_delregistro;
	@FXML
	private TableColumn<Laure, LocalDate> columna_fechallegadalaure_delregistro;
	@FXML
	private TableColumn<Laure, String> columna_proveedorlaure_delregistro;
	@FXML
	private TableColumn<Laure, String> columna_mercancialaure_delregistro;
	@FXML
	private TableColumn<Laure, String> columna_lotelaure_delregistro;
	@FXML
	private TableColumn<Laure, LocalDate> columna_fechainiciolaure_delregistro;
	@FXML
	private TableColumn<Laure, LocalDate> columna_fechafinlaure_delregistro;


	@FXML
	private TableView<Produccion> tabla_produccion;
	@FXML
	private TableColumn<Produccion, LocalDate> columna_fecha_lote_produccion;
	@FXML
	private TableColumn<Produccion, String> columna_nombreproducto_produccion;
	@FXML
	private TableColumn<Produccion, String> columna_cantidadproducto_produccion;
	@FXML
	private TableColumn<Produccion, String> columna_lotecarnesproducto_produccion;
	@FXML
	private TableColumn<Produccion, String> columna_lotesalsasproducto_produccion;
	@FXML
	private TableColumn<Produccion, String> columna_lotesmateriaprimaproducto_produccion;
	@FXML
	private TableColumn<Produccion, String> columna_lotesalinos_produccion;
	@FXML
	private TableColumn<Produccion, Estado> columna_firmado_produccion;

	@FXML
	private DatePicker busquedaconcreta;
	@FXML
	private DatePicker fechaelaboracion;
	@FXML
	private TextField addnombreproducto;
	@FXML
	private TextField addcantidadproducto;
	@FXML
	private TextField addlotescarne;
	@FXML
	private TextField addlotesalinos;
	@FXML
	private TextField addlotessalsas;
	@FXML
	private TextField addlotesmateriaprima;

	@FXML
	private ChoiceBox<Meses> mesesbusqueda;
	@FXML
	private ChoiceBox<Integer> anosbusqueda;
	@FXML
	private ChoiceBox<Estado> addfirmadopro;

	private ObservableList<SanidadProducto> lotescarnesincluidos;
	private ObservableList<Laure> lotesaliñosincuidos;
	private ObservableList<MateriaPrima> lotesmateriaprimaincluidos;
	private ObservableList<Salsa> lotessalsasincluidos;

	private Produccion ver=null;
	private Set<Integer> añosdisponiblesproduccion;
	private Set<Salsa> salsasaañadi=new TreeSet<>();
	private Set<MateriaPrima> materiaprimaaañdir=new TreeSet<>();
	private Set<Laure> laureaañadir=new TreeSet<>();
	private Set<SanidadProducto> carneaañadir=new TreeSet<>();
	private ObservableList<Produccion> listadinamicaproduccion;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		añosdisponiblesproduccion=new TreeSet<>();
		listadinamicaproduccion=FXCollections.observableArrayList();
		listadinamicacarnes=FXCollections.observableArrayList();
		listadinamicacarnes.addAll(SanidadDAO.SelecAll());
		listadinamicasalsas=FXCollections.observableArrayList();
		listadinamicasalsas.addAll(SalsaDAO.SelectAll());
		listadinamicamateriaprima=FXCollections.observableArrayList();
		listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectAll());
		listadinamicaaliños=FXCollections.observableArrayList();
		listadinamicaaliños.addAll(LaureDAO.SelectAll());
		MostrarTodosProduccion();
		mesesbusqueda.getItems().addAll(Meses.values());
		DarAños();
		addfirmadopro.getItems().addAll(Estado.values());
		addlotescarne.setEditable(false);
		addlotesalinos.setEditable(false);
		addlotessalsas.setEditable(false);
		addlotesmateriaprima.setEditable(false);
		lotesaliñosincuidos=FXCollections.observableArrayList();
		lotescarnesincluidos=FXCollections.observableArrayList();
		lotesmateriaprimaincluidos=FXCollections.observableArrayList();
		lotessalsasincluidos=FXCollections.observableArrayList();
		verpriductofirmaddo.getItems().addAll(Estado.values());
		verpriductofirmaddo.setDisable(true);
		verproductocantidad.setEditable(false);
		verproductofecha.setEditable(false);
		verproductonombre.setEditable(false);
		busquedaconcretatraz.setDisable(true);
		mesestraz.setDisable(true);
		anostraz.setDisable(true);
		tiposbusuqeda.setDisable(true);
		buscar.setDisable(true);
		labels.setDisable(true);
		radiobuttonconcreto.setDisable(true);
		radiobuttonampliado.setDisable(true);
		for(Busqueda b:Busqueda.values()){
			tiposbusuqeda.getItems().add(b.getCadena());
		}
		mesestraz.getItems().addAll(Meses.values());
		this.columna_firmado_produccion.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		this.columna_fecha_lote_produccion.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_lote());
		});
		this.columna_cantidadproducto_produccion.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getCantidad());
		});
		this.columna_nombreproducto_produccion.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getNombre_pro());
		});
		this.columna_lotecarnesproducto_produccion.setCellValueFactory(eachRowData -> {
			String cadena=" ";
			if(eachRowData.getValue().getLotescarnes()!=null){
				int contador=0;
				for(SanidadProducto p:eachRowData.getValue().getLotescarnes()){
					cadena+=p.getCodigolote();
					contador++;
					if(contador<eachRowData.getValue().getLotescarnes().size()){
						cadena+=" / ";
					}
				}
			}

			return new SimpleObjectProperty<>(cadena);
		});
		this.columna_lotesalinos_produccion.setCellValueFactory(eachRowData -> {
			String cadena=" ";
			if(eachRowData.getValue().getLoteslaure()!=null){
				int contador=0;
				for(Laure p:eachRowData.getValue().getLoteslaure()){
					cadena+=p.getLote();
					contador++;
					if(contador<eachRowData.getValue().getLoteslaure().size()){
						cadena+=" / ";
					}
				}
			}

			return new SimpleObjectProperty<>(cadena);
		});
		this.columna_lotesmateriaprimaproducto_produccion.setCellValueFactory(eachRowData -> {
			String cadena=" ";
			if(eachRowData.getValue().getLotesmateriaprima()!=null){
				int contador=0;
				for(MateriaPrima p:eachRowData.getValue().getLotesmateriaprima()){
					cadena+=p.getLote();
					contador++;
					if(contador<eachRowData.getValue().getLotesmateriaprima().size()){
						cadena+=" / ";
					}
				}
			}

			return new SimpleObjectProperty<>(cadena);
		});
		this.columna_lotesalsasproducto_produccion.setCellValueFactory(eachRowData -> {
			String cadena=" ";
			if(eachRowData.getValue().getLotesalsas()!=null){
				int contador=0;
				for(Salsa p:eachRowData.getValue().getLotesalsas()){
					cadena+=p.getLote();
					contador++;
					if(contador<eachRowData.getValue().getLotesalsas().size()){
						cadena+=" / ";
					}
				}
			}

			return new SimpleObjectProperty<>(cadena);
		});

		columna_firmado_produccion.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
		columna_firmado_produccion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produccion, Estado>>() {

			@Override
			public void handle(CellEditEvent<Produccion, Estado> event) {

				Produccion n = (Produccion) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				ProduccionDAO guardar = new ProduccionDAO(n);
				guardar.update();

			}
		});

		columna_fecha_lote_produccion.setCellFactory(param -> new LocalDateTableCell<>(param));
		columna_fecha_lote_produccion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produccion, LocalDate>>() {

			@Override
			public void handle(CellEditEvent<Produccion, LocalDate> event) {
				Produccion n = (Produccion) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFecha_lote(event.getNewValue());
				ProduccionDAO guardar = new ProduccionDAO(n);
				guardar.update();

			}
		});

		columna_nombreproducto_produccion.setCellFactory(TextFieldTableCell.forTableColumn());
		columna_nombreproducto_produccion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produccion, String>>() {

			@Override
			public void handle(CellEditEvent<Produccion, String> event) {
				Produccion n = (Produccion) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setNombre_pro(event.getNewValue());
				ProduccionDAO guardar = new ProduccionDAO(n);
				guardar.update();

			}
		});
		columna_cantidadproducto_produccion.setCellFactory(TextFieldTableCell.forTableColumn());
		columna_cantidadproducto_produccion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produccion, String>>() {

			@Override
			public void handle(CellEditEvent<Produccion, String> event) {
				Produccion n = (Produccion) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setCantidad(event.getNewValue());
				ProduccionDAO guardar = new ProduccionDAO(n);
				guardar.update();

			}
		});

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
		//----------------------------------------------------------------------------//

		this.columna_fechainiciolaure_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
		});
		this.columna_fechafinlaure_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
		});
		this.columna_fechallegadalaure_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
		});
		this.columna_lotelaure_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
		});
		this.columna_mercancialaure_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
		});
		this.columna_proveedorlaure_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
		});

		this.columna_fechafinmateriaprima_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
		});
		this.columna_fechainiciomateriaprima_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
		});
		this.columna_fechallegadamateriaprima_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
		});
		this.columna_lotemateriaprima_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
		});
		this.columna_mercanciamateriaprima_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
		});
		this.columna_proveedormateriaprima_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
		});

		this.columna_fechafinsalsa_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_fin());
		});
		this.columna_fechainiciosalsa_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_in());
		});
		this.columna_fechallegadasalsa_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha_llegada());
		});
		this.columna_lotesalsa_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getLote());
		});
		this.columna_mercanciasalsa_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getMercancia());
		});
		this.columna_proveedorsalsa_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
		});

		this.columna_lotecarne_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getCodigolote());
		});
		this.columna_mercanciacarne_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getNombre_producto());
		});
		this.columna_proveedorcarne_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
		});

		this.columna_fechallegadacarne_delregistro.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});



		tabla_produccion.setEditable(true);
		tabla_produccion.setItems(listadinamicaproduccion);
		tabla_carne.setItems(listadinamicacarnes);
		tabla_salsas.setItems(listadinamicasalsas);
		tablatraz_materiaprima.setItems(listadinamicamateriaprima);
		tablatrazlaure.setItems(listadinamicaaliños);
		tabla_laure_delregistro.setItems(lotesaliñosincuidos);
		tabla_carnes_delregistro.setItems(lotescarnesincluidos);
		tabla_materiaprima_delregistro.setItems(lotesmateriaprimaincluidos);
		tabla_salsas_delregistro.setItems(lotessalsasincluidos);

	}

	@FXML
	private void MostrarTodosProduccion(){
		listadinamicaproduccion.clear();
		busquedaconcreta.setValue(null);
		mesesbusqueda.setValue(null);
		anosbusqueda.setValue(null);
		listadinamicaproduccion.addAll(ProduccionDAO.SelectAll());
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

	private void DarAños(){
		añosdisponiblesproduccion.clear();
		anosbusqueda.getItems().clear();
		for(Produccion p:listadinamicaproduccion){
			añosdisponiblesproduccion.add(p.getFecha_lote().getYear());
		}
		anosbusqueda.getItems().addAll(añosdisponiblesproduccion);
	}

	@FXML
	private void LimpoiarRegistroProduccion(){
		salsasaañadi.clear();
		materiaprimaaañdir.clear();
		laureaañadir.clear();
		carneaañadir.clear();
		fechaelaboracion.setValue(null);
		addnombreproducto.clear();
		addcantidadproducto.clear();
		addlotescarne.clear();
		addlotesalinos.clear();
		addlotessalsas.clear();
		addlotesmateriaprima.clear();
		addfirmadopro.setValue(null);
	}

	@FXML
	private void EliminarRegistro(){
		Produccion aeli=tabla_produccion.getSelectionModel().getSelectedItem();

		if(aeli!=null){
			if(confirm("", "", "")){
				ProduccionDAO eliminar=new ProduccionDAO(aeli);
				if(eliminar.remuve()>0){
					listadinamicaproduccion.remove(aeli);
					muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
					if(listadinamicaproduccion.isEmpty()){
						MostrarTodosProduccion();
						DarAños();
					}

				}else{
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}


	}

	@FXML
	private void AddCrane(){
		SanidadProducto nuevo=tabla_carne.getSelectionModel().getSelectedItem();
		if(nuevo!=null){
			carneaañadir.add(nuevo);
			int contador=0;
			String cadena="";
			for(SanidadProducto s:carneaañadir){
				contador++;
				cadena+=s.getCodigolote();
				if(contador<carneaañadir.size()){
					cadena+=" / ";
				}
			}
			addlotescarne.setText(cadena);
		}
	}
	@FXML
	private void AddSalsa(){
		Salsa nuevo= tabla_salsas.getSelectionModel().getSelectedItem();
		if(nuevo!=null){
			salsasaañadi.add(nuevo);
			int contador=0;
			String cadena="";
			for(Salsa s:salsasaañadi){
				cadena+=s.getLote();
				contador++;
				if(contador<salsasaañadi.size()){
					cadena+=" / ";
				}
			}

			addlotessalsas.setText(cadena);
		}

	}
	@FXML
	private void AddAlinos(){
		Laure nuevo=tablatrazlaure.getSelectionModel().getSelectedItem();
		if(nuevo!=null){
			laureaañadir.add(nuevo);
			int contador=0;
			String cadena="";
			for(Laure l:laureaañadir){
				cadena+=l.getLote();
				contador++;
				if(contador<laureaañadir.size()){
					cadena+=" / ";
				}

			}
			addlotesalinos.setText(cadena);
		}
	}
	@FXML
	private void AddMateriaPrima(){
		MateriaPrima nuevo=tablatraz_materiaprima.getSelectionModel().getSelectedItem();
		if(nuevo!=null){
			materiaprimaaañdir.add(nuevo);
			int contador=0;
			String cadena="";
			for(MateriaPrima mp:materiaprimaaañdir){
				cadena+=mp.getLote();
				contador++;
				if(contador<materiaprimaaañdir.size()){
					cadena+=" / ";
				}

			}
			addlotesmateriaprima.setText(cadena);
		}
	}
	@FXML
	private void AddRegistro(){

		if(fechaelaboracion.getValue()!=null&&addcantidadproducto.getText().length()>0&&addnombreproducto.getText().length()>0){
			Set<Salsa> ns=null;
			Set<MateriaPrima> nmp=null;
			Set<Laure> na=null;
			Set<SanidadProducto> nc=null;
			if(!carneaañadir.isEmpty()){
				nc=carneaañadir;
			}
			if(!materiaprimaaañdir.isEmpty()){
				nmp=materiaprimaaañdir;
			}
			if(!laureaañadir.isEmpty()){
				na=laureaañadir;
			}
			if(!salsasaañadi.isEmpty()){
				ns=salsasaañadi;
			}
			Produccion nuevo=new Produccion(fechaelaboracion.getValue(), addnombreproducto.getText().toUpperCase(), addcantidadproducto.getText(), nc, na, ns, nmp, addfirmadopro.getValue());
			ProduccionDAO add=new ProduccionDAO(nuevo);
			if(add.update()>0){
				MostrarTodosProduccion();
				LimpoiarRegistroProduccion();
				DarAños();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
			}else{
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "Problema al conectar con la base de datos", "  ");
			}
		}else{
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que campos: FECHA, PRODUCTO y CANTIDAD están rellenos y son válidos");
		}

	}
	@FXML
	private void Ver(){
		ver=tabla_produccion.getSelectionModel().getSelectedItem();
		lotessalsasincluidos.clear();
		lotesaliñosincuidos.clear();
		lotesmateriaprimaincluidos.clear();
		lotescarnesincluidos.clear();
		verproductofecha.setValue(ver.getFecha_lote());
		verproductonombre.setText(ver.getNombre_pro());
		verproductocantidad.setText(ver.getCantidad());
		verpriductofirmaddo.setValue(ver.getFirmado());
		if(ver!=null){
			if(ver.getLotesalsas()!=null){
				lotessalsasincluidos.addAll(ver.getLotesalsas());
			}
			if(ver.getLoteslaure()!=null){
				lotesaliñosincuidos.addAll(ver.getLoteslaure());
			}
			if(ver.getLotesmateriaprima()!=null){
				lotesmateriaprimaincluidos.addAll(ver.getLotesmateriaprima());
			}
			if(ver.getLotescarnes()!=null){
				lotescarnesincluidos.addAll(ver.getLotescarnes());
			}
		}

	}
	@FXML
	private void LimpiarVer(){
		ver=null;
		lotessalsasincluidos.clear();
		lotesaliñosincuidos.clear();
		lotesmateriaprimaincluidos.clear();
		lotescarnesincluidos.clear();
		verproductofecha.setValue(null);
		verproductonombre.clear();;
		verproductocantidad.clear();;
		verpriductofirmaddo.setValue(null);
	}
	@FXML
	private void AddCarneAUnRegistroSeleccionado(){
		if(ver!=null){
			SanidadProducto nuevo=tabla_carne.getSelectionModel().getSelectedItem();
			if(nuevo!=null){
				if(ver.AddLoteCarne(nuevo)){
					ProduccionDAO add=new ProduccionDAO(ver);
					if(add.update()>0){
						lotescarnesincluidos.add(nuevo);
						MostrarTodosProduccion();
					}

				}

			}
		}
	}
	@FXML
	private void AddAlinosAUnRegistroSeleccionado(){
		if(ver!=null){
			Laure nuevo=tablatrazlaure.getSelectionModel().getSelectedItem();
			if(nuevo!=null){
				if(ver.AddLoteLaure(nuevo)){
					ProduccionDAO add=new ProduccionDAO(ver);
					if(add.update()>0){
						lotesaliñosincuidos.add(nuevo);
						MostrarTodosProduccion();
					}
				}
			}
		}
	}
	@FXML
	private void AddMateriaPrimaAUnRegistroSeleccionado(){
		if(ver!=null){
			MateriaPrima nuevo=tablatraz_materiaprima.getSelectionModel().getSelectedItem();
			if(nuevo!=null){
				if(ver.AddLoteMateriaPrima(nuevo)){
					ProduccionDAO add=new ProduccionDAO(ver);
					if(add.update()>0){
						lotesmateriaprimaincluidos.add(nuevo);
						MostrarTodosProduccion();
					}
				}
			}
		}
	}
	@FXML
	private void AddSalsaAUnRegistroSeleccionado(){
		if(ver!=null){
			Salsa nuevo=tabla_salsas.getSelectionModel().getSelectedItem();
			if(nuevo!=null){
				if(ver.AddLoteSalsa(nuevo)){
					ProduccionDAO add=new ProduccionDAO(ver);
					if(add.update()>0){
						lotessalsasincluidos.add(nuevo);
						MostrarTodosProduccion();
					}
				}
			}
		}
	}
	@FXML
	private void DropCarnedeunRegistro(){
		if(ver!=null){
			SanidadProducto eliminar=tabla_carnes_delregistro.getSelectionModel().getSelectedItem();
			if(eliminar!=null){
				if(ver.EliminarLoteCarne(eliminar)){
					ProduccionDAO drop=new ProduccionDAO(ver);
					if(drop.update()>0){
						lotescarnesincluidos.remove(eliminar);
						MostrarTodosProduccion();
					}
				}
			}

		}
	}
	@FXML
	private void DropAlinosdeunRegistro(){
		if(ver!=null){
			Laure eliminar=tabla_laure_delregistro.getSelectionModel().getSelectedItem();
			if(eliminar!=null){
				if(ver.EliminarLoteLaure(eliminar)){
					ProduccionDAO drop=new ProduccionDAO(ver);
					if(drop.update()>0){
						lotesaliñosincuidos.remove(eliminar);
						MostrarTodosProduccion();
					}
				}
			}

		}
	}
	@FXML
	private void DropSalsasdeunRegistro(){
		if(ver!=null){
			Salsa eliminar=tabla_salsas_delregistro.getSelectionModel().getSelectedItem();
			if(eliminar!=null){
				if(ver.EliminarLoteSalsa(eliminar)){
					ProduccionDAO drop=new ProduccionDAO(ver);
					if(drop.update()>0){
						lotessalsasincluidos.remove(eliminar);
						MostrarTodosProduccion();
					}
				}
			}

		}
	}
	@FXML
	private void DropMateriaPrimasdeunRegistro(){
		if(ver!=null){
			MateriaPrima eliminar=tabla_materiaprima_delregistro.getSelectionModel().getSelectedItem();
			if(eliminar!=null){
				if(ver.EliminarLoteMateriaPrima(eliminar)){
					ProduccionDAO drop=new ProduccionDAO(ver);
					if(drop.update()>0){
						lotesmateriaprimaincluidos.remove(eliminar);
						MostrarTodosProduccion();
					}
				}
			}

		}
	}
	@FXML
	private void Alclikear(){
		if(ver!=null){
			verproductofecha.setValue(ver.getFecha_lote());
		}else{
			verproductofecha.setValue(null);
		}

	}
	@FXML
	private void BuscarConcretaProduccion(){
		if(busquedaconcreta.getValue()!=null){
			listadinamicaproduccion.clear();
			listadinamicaproduccion.addAll(ProduccionDAO.SelectFechaConcreta(busquedaconcreta.getValue().toString()));
			if(!listadinamicaproduccion.isEmpty()){
				mesesbusqueda.setValue(null);
				anosbusqueda.setValue(null);
			}
		}
	}
	@FXML
	private void BusquedaAmpliada(){
		if(mesesbusqueda.getValue()!=null||anosbusqueda.getValue()!=null){
			listadinamicaproduccion.clear();
			if(mesesbusqueda.getValue()!=null&&anosbusqueda.getValue()!=null){
				listadinamicaproduccion.addAll(ProduccionDAO.SelectMesyAño(mesesbusqueda.getValue().getNumero_Mes(),anosbusqueda.getValue().intValue()));
			}else if(mesesbusqueda.getValue()!=null&&anosbusqueda.getValue()==null){
				listadinamicaproduccion.addAll(ProduccionDAO.SelectMes(mesesbusqueda.getValue().getNumero_Mes()));
			}else if(mesesbusqueda.getValue()==null&&anosbusqueda.getValue()!=null){
				listadinamicaproduccion.addAll(ProduccionDAO.SelectAño(anosbusqueda.getValue().intValue()));
			}

		}

	}
	@FXML
	private void LimpiarBusqueda(){
		listadinamicaaliños.clear();
		listadinamicacarnes.clear();
		listadinamicamateriaprima.clear();
		listadinamicasalsas.clear();
		listadinamicaaliños.addAll(LaureDAO.SelectAll());
		listadinamicacarnes.addAll(SanidadDAO.SelecAll());
		listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectAll());
		listadinamicasalsas.addAll(SalsaDAO.SelectAll());
		radiobuttonconcreto.setSelected(false);
		radiobuttonampliado.setSelected(false);
		labels.setDisable(true);
		busquedaconcretatraz.setValue(null);
		busquedaconcretatraz.setDisable(true);
		mesestraz.setValue(null);
		mesestraz.setDisable(true);
		anostraz.setValue(null);
		anostraz.setDisable(true);
		buscar.setDisable(true);
		tiposbusuqeda.setValue(null);
		tiposbusuqeda.setDisable(true);
	}
	@FXML
	private void ClikearSalsas(){
		if(panelsalsas.isExpanded()){
			radiobuttonconcreto.setDisable(false);
			radiobuttonampliado.setDisable(false);
			if(radiobuttonconcreto.isSelected()||radiobuttonampliado.isSelected()){
				tiposbusuqeda.setDisable(false);
			}
			todoaños.clear();
			anostraz.getItems().clear();
			for(Salsa s:SalsaDAO.SelectAll()){
				if(s.getFecha_llegada()!=null){
					todoaños.add(s.getFecha_llegada().getYear());
				}
				if(s.getFecha_fin()!=null){
					todoaños.add(s.getFecha_fin().getYear());
				}
				if(s.getFecha_in()!=null){
					todoaños.add(s.getFecha_in().getYear());
				}
			}
			anostraz.getItems().addAll(todoaños);
		}else{
			radiobuttonconcreto.setDisable(true);
			radiobuttonampliado.setDisable(true);
			radiobuttonconcreto.setSelected(false);
			radiobuttonampliado.setSelected(false);
			busquedaconcretatraz.setValue(null);
			busquedaconcretatraz.setDisable(true);
			buscar.setDisable(true);
			labels.setDisable(true);
			mesestraz.setDisable(true);
			mesestraz.setValue(null);
			anostraz.setValue(null);
			anostraz.setDisable(true);
			tiposbusuqeda.setValue(null);
			tiposbusuqeda.setDisable(true);
		}
	}
	@FXML
	private void ClikearCarnes(){
		if(carne.isExpanded()){
			radiobuttonconcreto.setDisable(false);
			radiobuttonampliado.setDisable(false);
			if(radiobuttonconcreto.isSelected()||radiobuttonampliado.isSelected()){
				tiposbusuqeda.setDisable(true);
				tiposbusuqeda.setValue(Busqueda.PORFECHALLEGADA.getCadena());

			}
			todoaños.clear();
			anostraz.getItems().clear();
			for(SanidadProducto s:SanidadDAO.SelecAll()){
				if(s.getFecha()!=null){
					todoaños.add(s.getFecha().getYear());
				}
			}
			anostraz.getItems().addAll(todoaños);

		}else{
			radiobuttonconcreto.setDisable(true);
			radiobuttonampliado.setDisable(true);
			radiobuttonconcreto.setSelected(false);
			radiobuttonampliado.setSelected(false);
			busquedaconcretatraz.setValue(null);
			busquedaconcretatraz.setDisable(true);
			buscar.setDisable(true);
			labels.setDisable(true);
			mesestraz.setDisable(true);
			mesestraz.setValue(null);
			anostraz.setValue(null);
			anostraz.setDisable(true);
			tiposbusuqeda.setValue(null);
			tiposbusuqeda.setDisable(true);
		}
	}
	@FXML
	private void ClikearMateriaPrima(){
		if(materiaprima.isExpanded()){
			radiobuttonconcreto.setDisable(false);
			radiobuttonampliado.setDisable(false);
			if(radiobuttonconcreto.isSelected()||radiobuttonampliado.isSelected()){
				tiposbusuqeda.setDisable(false);
			}
			todoaños.clear();
			anostraz.getItems().clear();
			for(MateriaPrima s:MateriaPrimaDAO.SelectAll()){
				if(s.getFecha_llegada()!=null){
					todoaños.add(s.getFecha_llegada().getYear());
				}
				if(s.getFecha_fin()!=null){
					todoaños.add(s.getFecha_fin().getYear());
				}
				if(s.getFecha_in()!=null){
					todoaños.add(s.getFecha_in().getYear());
				}
			}
			anostraz.getItems().addAll(todoaños);
		}else{
			radiobuttonconcreto.setDisable(true);
			radiobuttonampliado.setDisable(true);
			radiobuttonconcreto.setSelected(false);
			radiobuttonampliado.setSelected(false);
			busquedaconcretatraz.setValue(null);
			busquedaconcretatraz.setDisable(true);
			buscar.setDisable(true);
			labels.setDisable(true);
			mesestraz.setDisable(true);
			mesestraz.setValue(null);
			anostraz.setValue(null);
			anostraz.setDisable(true);
			tiposbusuqeda.setValue(null);
			tiposbusuqeda.setDisable(true);
		}
	}
	@FXML
	private void ClikearLaure(){
		if(laure.isExpanded()){
			radiobuttonconcreto.setDisable(false);
			radiobuttonampliado.setDisable(false);
			if(radiobuttonconcreto.isSelected()||radiobuttonampliado.isSelected()){
				tiposbusuqeda.setDisable(false);
			}
			todoaños.clear();
			anostraz.getItems().clear();
			for(Laure s:LaureDAO.SelectAll()){
				if(s.getFecha_llegada()!=null){
					todoaños.add(s.getFecha_llegada().getYear());
				}
				if(s.getFecha_fin()!=null){
					todoaños.add(s.getFecha_fin().getYear());
				}
				if(s.getFecha_in()!=null){
					todoaños.add(s.getFecha_in().getYear());
				}
			}
			anostraz.getItems().addAll(todoaños);


		}else{
			radiobuttonconcreto.setDisable(true);
			radiobuttonampliado.setDisable(true);
			radiobuttonconcreto.setSelected(false);
			radiobuttonampliado.setSelected(false);
			busquedaconcretatraz.setValue(null);
			busquedaconcretatraz.setDisable(true);
			buscar.setDisable(true);
			labels.setDisable(true);
			mesestraz.setDisable(true);
			mesestraz.setValue(null);
			anostraz.setValue(null);
			anostraz.setDisable(true);
			tiposbusuqeda.setValue(null);
			tiposbusuqeda.setDisable(true);
		}
	}
	@FXML
	private void CambiarARadioButtonConcreto(){
		if(!radiobuttonconcreto.isSelected()){
			busquedaconcretatraz.setValue(null);
			busquedaconcretatraz.setDisable(true);
			tiposbusuqeda.setValue(null);
			tiposbusuqeda.setDisable(true);
			buscar.setDisable(true);
		}else{
			busquedaconcretatraz.setDisable(false);
			if(carne.isExpanded()){
				tiposbusuqeda.setDisable(true);
			}else{
				tiposbusuqeda.setDisable(false);
			}

			buscar.setDisable(false);
			if(carne.isExpanded()){
				tiposbusuqeda.setValue(Busqueda.PORFECHALLEGADA.getCadena());
			}else{
				tiposbusuqeda.setValue(null);
			}
			radiobuttonampliado.setSelected(false);
			mesestraz.setValue(null);
			anostraz.setValue(null);
			mesestraz.setDisable(true);
			anostraz.setDisable(true);
		}
	}
	@FXML
	private void CambiarRadioButtonAmpliado(){
		if(!radiobuttonampliado.isSelected()){
			mesestraz.setDisable(true);
			mesestraz.setValue(null);
			anostraz.setDisable(true);
			anostraz.setValue(null);
			buscar.setDisable(true);
			labels.setDisable(true);
			tiposbusuqeda.setValue(null);
			tiposbusuqeda.setDisable(true);
		}else{
			labels.setDisable(false);
			if(carne.isExpanded()){
				tiposbusuqeda.setValue(Busqueda.PORFECHALLEGADA.getCadena());
				tiposbusuqeda.setDisable(true);
			}else{
				tiposbusuqeda.setValue(null);
				tiposbusuqeda.setDisable(false);
			}

			buscar.setDisable(false);
			radiobuttonconcreto.setSelected(false);
			mesestraz.setDisable(false);
			anostraz.setDisable(false);
			busquedaconcretatraz.setValue(null);
			busquedaconcretatraz.setDisable(true);
		}
	}
	@FXML
	private void Busqueda(){
		if(carne.isExpanded()){
			if(radiobuttonconcreto.isSelected()){
				if(busquedaconcretatraz.getValue()!=null){
					listadinamicacarnes.clear();
					listadinamicacarnes.addAll(SanidadDAO.SelecByFecha(busquedaconcretatraz.getValue().toString()));

				}
			}else if(radiobuttonampliado.isSelected()){
				if(mesestraz.getValue()!=null||anostraz.getValue()!=null){
					listadinamicacarnes.clear();
					if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
						listadinamicacarnes.addAll(SanidadDAO.SelecPorMesyAño(Integer.toString(mesestraz.getValue().getNumero_Mes()),Integer.toString(anostraz.getValue().intValue())));
					}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
						listadinamicacarnes.addAll(SanidadDAO.SelecPorMes(Integer.toString(mesestraz.getValue().getNumero_Mes())));
					}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
						listadinamicacarnes.addAll(SanidadDAO.SelecPorAño(Integer.toString(anostraz.getValue().intValue())));

					}

				}
			}
		}else if(laure.isExpanded()){
			if(radiobuttonconcreto.isSelected()){
				if(busquedaconcretatraz.getValue()!=null&&tiposbusuqeda.getValue()!=null){
					listadinamicaaliños.clear();
					if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())){
						listadinamicaaliños.addAll(LaureDAO.SelectFechaConcretaLLegada(busquedaconcretatraz.getValue().toString()));

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())){
						listadinamicaaliños.addAll(LaureDAO.SelectFechaConcretaInicio(busquedaconcretatraz.getValue().toString()));

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAFIN.getCadena())){
						listadinamicaaliños.addAll(LaureDAO.SelectFechaConcretaFin(busquedaconcretatraz.getValue().toString()));

					}
				}
			}else if(radiobuttonampliado.isSelected()){
				if(tiposbusuqeda.getValue()!=null&&(mesestraz.getValue()!=null||anostraz.getValue()!=null)){
					listadinamicaaliños.clear();
					if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaLlegadaMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaLlegadaMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaLlegadaAño( anostraz.getValue().intValue()));

						}

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaInicioMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaInicioMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaInicioAño( anostraz.getValue().intValue()));
						}

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAFIN.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaFinMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaFinMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicaaliños.addAll(LaureDAO.SelectFechaFinAño( anostraz.getValue().intValue()));
						}
					}
				}
			}

		}else if(materiaprima.isExpanded()){

			if(radiobuttonconcreto.isSelected()){
				if(busquedaconcretatraz.getValue()!=null&&tiposbusuqeda.getValue()!=null){
					listadinamicamateriaprima.clear();
					if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())){
						listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaConcretaLLegada(busquedaconcretatraz.getValue().toString()));
					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())){
						listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaConcretaInicio(busquedaconcretatraz.getValue().toString()));
					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAFIN.getCadena())){
						listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaConcretaFin(busquedaconcretatraz.getValue().toString()));
					}
				}
			}else if(radiobuttonampliado.isSelected()){
				if(tiposbusuqeda.getValue()!=null&&(mesestraz.getValue()!=null||anostraz.getValue()!=null)){
					listadinamicamateriaprima.clear();
					if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaLlegadaMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaLlegadaMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaLlegadaAño(anostraz.getValue().intValue()));
						}

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaInicioMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaInicioMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaInicioAño(anostraz.getValue().intValue()));
						}

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAFIN.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaFinMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaFinMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicamateriaprima.addAll(MateriaPrimaDAO.SelectFechaFinAño(anostraz.getValue().intValue()));
						}
					}
				}
			}

		}else if(panelsalsas.isExpanded()){
			if(radiobuttonconcreto.isSelected()){
				if(busquedaconcretatraz.getValue()!=null&&tiposbusuqeda.getValue()!=null){
					listadinamicasalsas.clear();
					if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())){
						listadinamicasalsas.addAll(SalsaDAO.SelectFechaConcretaLLegada(busquedaconcretatraz.getValue().toString()));
					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())){
						listadinamicasalsas.addAll(SalsaDAO.SelectFechaConcretaInicio(busquedaconcretatraz.getValue().toString()));
					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAFIN.getCadena())){
						listadinamicasalsas.addAll(SalsaDAO.SelectFechaConcretaFin(busquedaconcretatraz.getValue().toString()));
					}
				}
			}else if(radiobuttonampliado.isSelected()){
				if(tiposbusuqeda.getValue()!=null&&(mesestraz.getValue()!=null||anostraz.getValue()!=null)){
					listadinamicasalsas.clear();
					if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHALLEGADA.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaLlegadaMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaLlegadaMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaLlegadaAño(anostraz.getValue().intValue()));
						}

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAINICIO.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaInicioMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaInicioMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaInicioAño(anostraz.getValue().intValue()));
						}

					}else if(tiposbusuqeda.getValue().equals(Busqueda.PORFECHAFIN.getCadena())){

						if(mesestraz.getValue()!=null&&anostraz.getValue()!=null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaFinMesyAño(mesestraz.getValue().getNumero_Mes(), anostraz.getValue().intValue()));
						}else if(mesestraz.getValue()!=null&&anostraz.getValue()==null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaFinMes(mesestraz.getValue().getNumero_Mes()));
						}else if(mesestraz.getValue()==null&&anostraz.getValue()!=null){
							listadinamicasalsas.addAll(SalsaDAO.SelectFechaFinAño(anostraz.getValue().intValue()));
						}
					}
				}
			}
		}
	}



}
