package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import clasemain.App;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import modelos.Temperatura;
import modelos.TemperaturaDAO;
import utilidades.LocalDateTableCell;
import utilidades.UtilidadesGenerales;

public class ControladorTemperaturas extends ControladorGenerar {
	@FXML
	private ChoiceBox<Meses> buscar_por_mes;
	@FXML
	private ChoiceBox<Estado> a_firmar;
	@FXML
	private DatePicker fecha_busqueda;
	@FXML
	private DatePicker intro_fecha;
	@FXML
	private TextField intro_vitrina_espositora;
	@FXML
	private TextField intro_armario_frigorifico;
	@FXML
	private TextField intro_armario_productos_cocinados;
	@FXML
	private TextField intro_armario_congelador;
	@FXML
	private TextField intro_alcon_congelador;
	@FXML
	private TableView<Temperatura> tabla;
	@FXML
	private TableColumn<Temperatura, LocalDate> colum_fecha;
	@FXML
	private TableColumn<Temperatura, Estado> colum_firmado;
	@FXML
	private TableColumn<Temperatura, String> colum_vitrina_espositora;
	@FXML
	private TableColumn<Temperatura, String> colum_armario_frigorifico;
	@FXML
	private TableColumn<Temperatura, String> colum_armario_productos_cocinados;
	@FXML
	private TableColumn<Temperatura, String> colum_armario_congelador;
	@FXML
	private TableColumn<Temperatura, String> colum_alcon_congelador;

	private ObservableList<Temperatura> listadinamicatem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listadinamicatem = FXCollections.observableArrayList();
		MostrarTodos();
		for (Estado e : Estado.values()) {
			a_firmar.getItems().add(e);
		}
		for (Meses m : Meses.values()) {
			buscar_por_mes.getItems().add(m);
		}

		this.colum_fecha.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.colum_alcon_congelador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getAlcon_congelador());
		});
		this.colum_armario_congelador.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getArmario_congelador());
		});
		this.colum_armario_frigorifico.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getArmario_frigorifico());
		});
		this.colum_armario_productos_cocinados.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getArmario_productos_cocinados());
		});
		this.colum_firmado.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		this.colum_vitrina_espositora.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getVitrina_espositora());
		});

		colum_firmado.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO, Estado.NO_FIRMADO));
		colum_firmado.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, Estado>>() {

			@Override
			public void handle(CellEditEvent<Temperatura, Estado> event) {

				Temperatura n = (Temperatura) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				TemperaturaDAO guardar = new TemperaturaDAO(n);
				guardar.save();

			}
		});

		colum_fecha.setCellFactory(param -> new LocalDateTableCell<>(param));
		colum_fecha.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, LocalDate>>() {

			@Override
			public void handle(CellEditEvent<Temperatura, LocalDate> event) {
				Temperatura n = (Temperatura) event.getTableView().getItems().get(event.getTablePosition().getRow());
				n.setFecha(event.getNewValue());
				TemperaturaDAO guardar = new TemperaturaDAO(n);
				guardar.save();

			}
		});

		colum_alcon_congelador.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_alcon_congelador.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, String>>() {

			@Override
			public void handle(CellEditEvent<Temperatura, String> event) {
				boolean modificado = false;
				Temperatura n = (Temperatura) event.getTableView().getItems().get(event.getTablePosition().getRow());
				if(event.getNewValue().length()!=0){
					if (UtilidadesGenerales.ValidarIntroducirTemp(event.getNewValue())) {
						n.setAlcon_congelador(event.getNewValue());
						modificado = true;
					} else {
						if (UtilidadesGenerales.ValidarIntroducirTemp(
								UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()))) {
							n.setAlcon_congelador(
									UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()));
							modificado = true;
						}
					}
				}else{
					n.setAlcon_congelador("EN DESUSO");
					modificado = true;
				}


				if (modificado) {
					TemperaturaDAO guardar = new TemperaturaDAO(n);
					guardar.save();
				}

			}
		});
		colum_armario_congelador.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_armario_congelador.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, String>>() {

			@Override
			public void handle(CellEditEvent<Temperatura, String> event) {
				boolean modificado = false;
				Temperatura n = (Temperatura) event.getTableView().getItems().get(event.getTablePosition().getRow());
				if(event.getNewValue().length()!=0){
					if (UtilidadesGenerales.ValidarIntroducirTemp(event.getNewValue())) {
						n.setArmario_congelador(event.getNewValue());
						modificado = true;
					} else {
						if (UtilidadesGenerales.ValidarIntroducirTemp(
								UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()))) {
							n.setArmario_congelador(
									UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()));
							modificado = true;
						}
					}
				}else{
					n.setArmario_congelador("EN DESUSO");
					modificado = true;
				}

				if (modificado) {
					TemperaturaDAO guardar = new TemperaturaDAO(n);
					guardar.save();
				}

			}
		});

		colum_armario_frigorifico.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_armario_frigorifico.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, String>>() {

			@Override
			public void handle(CellEditEvent<Temperatura, String> event) {
				boolean modificado = false;
				Temperatura n = (Temperatura) event.getTableView().getItems().get(event.getTablePosition().getRow());
				if(event.getNewValue().length()!=0){
					if (UtilidadesGenerales.ValidarIntroducirTemp(event.getNewValue())) {

						n.setArmario_frigorifico(event.getNewValue());
						modificado = true;
					} else {
						if (UtilidadesGenerales.ValidarIntroducirTemp(
								UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()))) {
							n.setArmario_frigorifico(
									UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()));
							modificado = true;
						}
					}
				}else{
					n.setArmario_frigorifico("EN DESUSO");
					modificado = true;
				}

				if (modificado) {
					TemperaturaDAO guardar = new TemperaturaDAO(n);
					guardar.save();
				}

			}
		});
		colum_armario_productos_cocinados.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_armario_productos_cocinados
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, String>>() {

					@Override
					public void handle(CellEditEvent<Temperatura, String> event) {
						boolean modificado = false;
						Temperatura n = (Temperatura) event.getTableView().getItems()
								.get(event.getTablePosition().getRow());
						if(event.getNewValue().length()!=0){
							if (UtilidadesGenerales.ValidarIntroducirTemp(event.getNewValue())) {

								n.setArmario_productos_cocinados(event.getNewValue());
								modificado = true;
							} else {
								if (UtilidadesGenerales.ValidarIntroducirTemp(
										UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()))) {

									n.setArmario_productos_cocinados(
											UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()));
								}
							}
						}else{
							n.setArmario_productos_cocinados("EN DESUSO");
							modificado = true;
						}


						if (modificado) {
							TemperaturaDAO guardar = new TemperaturaDAO(n);
							guardar.save();
						}

					}
				});
		colum_vitrina_espositora.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_vitrina_espositora.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temperatura, String>>() {

			@Override
			public void handle(CellEditEvent<Temperatura, String> event) {
				boolean modificado = false;
				Temperatura n = (Temperatura) event.getTableView().getItems().get(event.getTablePosition().getRow());
				if(event.getNewValue().length()!=0){
					if (UtilidadesGenerales.ValidarIntroducirTemp(event.getNewValue())) {

						n.setVitrina_espositora(event.getNewValue());
						modificado = true;

					} else {
						if (UtilidadesGenerales.ValidarIntroducirTemp(
								UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()))) {

							n.setVitrina_espositora(
									UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(event.getNewValue()));
							modificado = true;

						}

					}
				}else{
					n.setVitrina_espositora("EN DESUSO");
					modificado = true;

				}


				if (modificado) {
					TemperaturaDAO guardar = new TemperaturaDAO(n);
					guardar.save();
				}

			}
		});

		tabla.setEditable(true);
		tabla.setItems(listadinamicatem);

	}

	@FXML
	private void Limpiar() {
		a_firmar.setValue(null);
		intro_fecha.setValue(null);
		intro_alcon_congelador.clear();
		intro_armario_congelador.clear();
		intro_vitrina_espositora.clear();
		intro_armario_frigorifico.clear();
		intro_armario_productos_cocinados.clear();
	}

	@FXML
	private void MostrarTodos() {
		listadinamicatem.clear();
		listadinamicatem.addAll(TemperaturaDAO.SelectAll());
		buscar_por_mes.setValue(null);
		fecha_busqueda.setValue(null);

	}

	@FXML
	private void PorFecha() {
		if (fecha_busqueda.getValue() != null) {
			listadinamicatem.clear();
			listadinamicatem.addAll(TemperaturaDAO.SelectPorFecha(fecha_busqueda.getValue().toString()));
			buscar_por_mes.setValue(null);
		}

	}

	@FXML
	private void PorMes() {
		if (buscar_por_mes.getValue() != null) {
			listadinamicatem.clear();
			listadinamicatem
					.addAll(TemperaturaDAO.SelectPorMes(Integer.toString(buscar_por_mes.getValue().getNumero_Mes())));
			fecha_busqueda.setValue(null);
		}
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
	private void Add() {
		if (intro_fecha.getValue() != null
				&& a_firmar.getValue() != null) {
			boolean _alcon_congelador = false, _armario_congelador = false, _armario_frigorifico = false,
					_armario_productos_cocinados = false, _vitrina_espositora = false;
			String cro_alcon_congelador="", cro__armario_congelador="", cro__armario_frigorifico="",
			cro__armario_productos_cocinados="", cro__vitrina_espositora="";

			if(intro_alcon_congelador.getText().length()!=0){
				if(UtilidadesGenerales.ValidarIntroducirTemp(intro_alcon_congelador.getText())){
					cro_alcon_congelador=intro_alcon_congelador.getText();
					_alcon_congelador=true;
				}else{
					if(UtilidadesGenerales.ValidarIntroducirTemp(UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_alcon_congelador.getText()))){
						cro_alcon_congelador=UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_alcon_congelador.getText());
						_alcon_congelador=true;
					}else{
						intro_alcon_congelador.clear();
					}
				}
			}else{
				cro_alcon_congelador="EN DESUSO";
				_alcon_congelador=true;
			}

			if(intro_armario_congelador.getText().length()!=0){
				if(UtilidadesGenerales.ValidarIntroducirTemp(intro_armario_congelador.getText())){
					cro__armario_congelador=intro_armario_congelador.getText();
					_armario_congelador=true;
				}else{
					if(UtilidadesGenerales.ValidarIntroducirTemp(UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_armario_congelador.getText()))){
						cro__armario_congelador=UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_armario_congelador.getText());
						_armario_congelador=true;
					}else{
						intro_armario_congelador.clear();
					}
				}
			}else{
				cro__armario_congelador="EN DESUSO";
				_armario_congelador=true;
			}

			if(intro_armario_frigorifico.getText().length()!=0){
				if(UtilidadesGenerales.ValidarIntroducirTemp(intro_armario_frigorifico.getText())){
					cro__armario_frigorifico=intro_armario_frigorifico.getText();
					_armario_frigorifico=true;
				}else{
					if(UtilidadesGenerales.ValidarIntroducirTemp(UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_armario_frigorifico.getText()))){
						cro__armario_frigorifico=UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_armario_frigorifico.getText());
						_armario_frigorifico=true;
					}else{
						intro_armario_frigorifico.clear();
					}
				}
			}else{
				cro__armario_frigorifico="EN DESUSO";
				_armario_frigorifico=true;
			}

			if(intro_armario_productos_cocinados.getText().length()!=0){
				if(UtilidadesGenerales.ValidarIntroducirTemp(intro_armario_productos_cocinados.getText())){
					cro__armario_productos_cocinados=intro_armario_productos_cocinados.getText();
					_armario_productos_cocinados=true;
				}else{
					if(UtilidadesGenerales.ValidarIntroducirTemp(UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_armario_productos_cocinados.getText()))){
						cro__armario_productos_cocinados=UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_armario_productos_cocinados.getText());
						_armario_productos_cocinados=true;
					}else{
						intro_armario_productos_cocinados.clear();
					}
				}
			}else{
				cro__armario_productos_cocinados="EN DESUSO";
				_armario_productos_cocinados=true;
			}

			if(intro_vitrina_espositora.getText().length()!=0){
				if(UtilidadesGenerales.ValidarIntroducirTemp(intro_vitrina_espositora.getText())){
					cro__vitrina_espositora=intro_vitrina_espositora.getText();
					_vitrina_espositora=true;
				}else{
					if(UtilidadesGenerales.ValidarIntroducirTemp(UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_vitrina_espositora.getText()))){
						cro__vitrina_espositora=UtilidadesGenerales.ValidarIntroducirTempSolosimbolo(intro_vitrina_espositora.getText());
						_vitrina_espositora=true;
					}else{
						intro_vitrina_espositora.clear();
					}
				}
			}else{
				_vitrina_espositora=true;
				cro__vitrina_espositora="EN DESUSO";
			}


			if (_alcon_congelador && _armario_congelador && _armario_frigorifico && _armario_productos_cocinados
					&& _vitrina_espositora) {
				Temperatura nuevo = new Temperatura(intro_fecha.getValue(), cro__vitrina_espositora,
						cro__armario_frigorifico, cro__armario_productos_cocinados,
						cro__armario_congelador,cro_alcon_congelador, a_firmar.getValue());
				TemperaturaDAO add = new TemperaturaDAO(nuevo);
				if (add.save() > 0) {
					listadinamicatem.clear();
					listadinamicatem.addAll(TemperaturaDAO.SelectAll());
					a_firmar.setValue(null);
					intro_alcon_congelador.clear();
					intro_armario_congelador.clear();
					intro_vitrina_espositora.clear();
					intro_armario_frigorifico.clear();
					intro_armario_productos_cocinados.clear();
					muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");

				} else {
					muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
							"Revise que todos los campos están rellenos y son válidos");
				}
			}else{
				muestraerror("ERROR EN EL FORMATO", "",
						"Vuelve a introducir los datos en los campos en blanco");
			}

		} else {
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
					"Revise que todos los campos están rellenos y son válidos");
		}

	}

	@FXML
	private void Eliminar() {
		Temperatura aeli = tabla.getSelectionModel().getSelectedItem();
		if (confirm("", "", "")) {
			TemperaturaDAO eliminar = new TemperaturaDAO(aeli);
			if (eliminar.remove() > 0) {
				listadinamicatem.remove(aeli);
				muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
			} else {
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}
	}

}
