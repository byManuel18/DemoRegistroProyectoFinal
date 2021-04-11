package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import clasemain.App;
import enums.Escenas;
import enums.Estado;
import enums.Meses;
import enums.Mirado;
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
import modelos.Limpieza;
import modelos.LimpiezaDAO;
import utilidades.LocalDateTableCell;

public class ControladorLimpieza extends ControladorGenerar{
	@FXML
	private DatePicker intro_fecha;
	@FXML
	private DatePicker busque_fecha;
	@FXML
	private ChoiceBox<Meses> busque_mes;
	@FXML
	private ChoiceBox<Estado> firmar;
	@FXML
	private ChoiceBox<Mirado> obrador;
	@FXML
	private ChoiceBox<Mirado> aseo;
	@FXML
	private ChoiceBox<Mirado> picadora;
	@FXML
	private ChoiceBox<Mirado> venta;
	@FXML
	private ChoiceBox<Mirado> embutidora;
	@FXML
	private ChoiceBox<Mirado> sierra;
	@FXML
	private ChoiceBox<Mirado> cuchillos_y_utensilios;
	@FXML
	private ChoiceBox<Mirado> fri_materia_prima;
	@FXML
	private ChoiceBox<Mirado> fri_pro_terminado;
	@FXML
	private ChoiceBox<Mirado> contenedor;
	@FXML
	private TableView<Limpieza> tabla;
	@FXML
	private TableColumn<Limpieza, LocalDate> colum_fecha;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_aseo;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_obrador;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_venta;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_picadora;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_embutidora;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_sierra;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_cuchi_uti;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_fri_mp;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_fri_pt;
	@FXML
	private TableColumn<Limpieza, Mirado> colum_contenedor;
	@FXML
	private TableColumn<Limpieza, Estado> colum_firma;

	private ObservableList<Limpieza> listalimpidinamica;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listalimpidinamica=FXCollections.observableArrayList();
		MostrarTodo();

		for(Meses m:Meses.values()){
			busque_mes.getItems().add(m);
		}

		for(Estado e:Estado.values()){
			firmar.getItems().add(e);
		}

		for(Mirado m:Mirado.values()){
			obrador.getItems().add(m);
			aseo.getItems().add(m);
			picadora.getItems().add(m);
			embutidora.getItems().add(m);
			venta.getItems().add(m);
			sierra.getItems().add(m);
			cuchillos_y_utensilios.getItems().add(m);
			fri_materia_prima.getItems().add(m);
			fri_pro_terminado.getItems().add(m);
			contenedor.getItems().add(m);
		}
		this.colum_fecha.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.colum_obrador.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getObrador());
		});
		this.colum_aseo.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getAseo());
		});
		this.colum_picadora.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getPicadora());
		});
		this.colum_embutidora.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getEmbutidora());
		});
		this.colum_venta.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getVenta());
		});
		this.colum_sierra.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getSierra());
		});
		this.colum_cuchi_uti.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getCuchillos_y_utensilios());
		});
		this.colum_fri_mp.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getFrigorifico_materia_prima());
		});
		this.colum_fri_pt.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getFrigorifico_producto_terminado());
		});
		this.colum_contenedor.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getContenedor_residuos());
		});
		this.colum_firma.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		colum_fecha.setCellFactory(param -> new LocalDateTableCell<>(param));
		colum_fecha.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Limpieza,LocalDate>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, LocalDate> event) {
				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFecha(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();
			}
		});
		colum_firma.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO,Estado.NO_FIRMADO));
		colum_firma.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Estado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Estado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_aseo.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_aseo.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setAseo(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_obrador.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_obrador.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setObrador(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_venta.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_venta.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setVenta(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_picadora.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_picadora.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setPicadora(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_fri_mp.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_fri_mp.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFrigorifico_materia_prima(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_fri_pt.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_fri_pt.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFrigorifico_producto_terminado(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_contenedor.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_contenedor.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setContenedor_residuos(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_cuchi_uti.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_cuchi_uti.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setCuchillos_y_utensilios(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_sierra.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_sierra.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setSierra(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});
		colum_embutidora.setCellFactory(ChoiceBoxTableCell.forTableColumn(Mirado.X,Mirado.NULO));
		colum_embutidora.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Limpieza,Mirado>>() {

			@Override
			public void handle(CellEditEvent<Limpieza, Mirado> event) {

				Limpieza n=(Limpieza) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setEmbutidora(event.getNewValue());
				LimpiezaDAO guardar=new LimpiezaDAO(n);
				guardar.save();

			}
		});



		tabla.setEditable(true);
		tabla.setItems(listalimpidinamica);

	}

	@FXML
	private void MostrarTodo(){
		listalimpidinamica.clear();
		listalimpidinamica.addAll(LimpiezaDAO.ListarAll());
		busque_fecha.setValue(null);
		busque_mes.setValue(null);
	}
	@FXML
	private void PorMes(){
		if(busque_mes.getValue()!=null){
			listalimpidinamica.clear();
			listalimpidinamica.addAll(LimpiezaDAO.ListarPorMes(Integer.toString(busque_mes.getValue().getNumero_Mes())));
		}
		busque_fecha.setValue(null);
	}
	@FXML
	private void PorFecha(){
		if(busque_fecha.getValue()!=null){
			listalimpidinamica.clear();
			listalimpidinamica.addAll(LimpiezaDAO.ListarPorFecha(busque_fecha.getValue().toString()));
		}
		busque_mes.setValue(null);
	}
	@FXML
	private void Limpiar(){
		firmar.setValue(null);
		intro_fecha.setValue(null);
		obrador.setValue(null);
		aseo.setValue(null);
		venta.setValue(null);
		picadora.setValue(null);
		embutidora.setValue(null);
		sierra.setValue(null);
		cuchillos_y_utensilios.setValue(null);
		fri_materia_prima.setValue(null);
		fri_pro_terminado.setValue(null);
		contenedor.setValue(null);
	}
	@FXML
	private void Volver(){
		try {
			App.setRoot(Escenas.INICIO.getUrl());
			App.CambiarTitulo("TIENDA ANTONIO GUERRERO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void Add(){
		if(firmar.getValue()!=null&&intro_fecha.getValue()!=null&&obrador.getValue()!=null&&aseo.getValue()!=null&&venta.getValue()!=null&&picadora.getValue()!=null&&embutidora.getValue()!=null&&sierra.getValue()!=null&&cuchillos_y_utensilios.getValue()!=null&&fri_materia_prima.getValue()!=null&&fri_pro_terminado.getValue()!=null&&contenedor.getValue()!=null){
			Limpieza nuevo=new Limpieza(intro_fecha.getValue(), obrador.getValue(), aseo.getValue(), venta.getValue(), picadora.getValue(), embutidora.getValue(), sierra.getValue(), cuchillos_y_utensilios.getValue(), fri_materia_prima.getValue(), fri_pro_terminado.getValue(), contenedor.getValue(), firmar.getValue());
			LimpiezaDAO add=new LimpiezaDAO(nuevo);
			if(add.save()>0){
				listalimpidinamica.clear();
				listalimpidinamica.addAll(LimpiezaDAO.ListarAll());
				firmar.setValue(null);
				obrador.setValue(null);
				aseo.setValue(null);
				venta.setValue(null);
				picadora.setValue(null);
				embutidora.setValue(null);
				sierra.setValue(null);
				cuchillos_y_utensilios.setValue(null);
				fri_materia_prima.setValue(null);
				fri_pro_terminado.setValue(null);
				contenedor.setValue(null);
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
			}else{
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
			}
		}else{
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
		}
	}
	@FXML
	private void Eliminar(){
		Limpieza eli=tabla.getSelectionModel().getSelectedItem();
		LimpiezaDAO remu=new LimpiezaDAO(eli);
		if(confirm("", "", "")){
			if(remu.remuve()>0){
				listalimpidinamica.remove(eli);
				muestrinformacion("ELIMINACIÓN","Registro eliminado","");

			}else{
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}
	}

}
