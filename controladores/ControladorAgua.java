package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import clasemain.App;
import enums.Escenas;
import enums.Estado;
import enums.EstadoAgua;
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
import modelos.Agua;
import modelos.AguaDAO;
import utilidades.EscribirArchivos;
import utilidades.LocalDateTableCell;

public class ControladorAgua extends ControladorGenerar{
	@FXML
	private TableView<Agua> tabla;
	@FXML
	private TableColumn<Agua, Estado> columna_firmado;
	@FXML
	private TableColumn<Agua, EstadoAgua> columna_estado;
	@FXML
	private TableColumn<Agua, LocalDate> columna_fecha;
	@FXML
	private TableColumn<Agua, String> columna_punto;
	@FXML
	private TableColumn<Agua, String> columna_control;
	@FXML
	private DatePicker busquedaconcreta;
	@FXML
	private DatePicker introducirfecha;
	@FXML
	private ChoiceBox<Meses> mesbusqueda;
	@FXML
	private ChoiceBox<Estado> firmar;
	@FXML
	private ChoiceBox<EstadoAgua> estado_agua;
	@FXML
	private TextField punto_muestreo;
	@FXML
	private TextField control_organoleptico;

	private ObservableList<Agua> listaaguadinamica;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listaaguadinamica=FXCollections.observableArrayList();
		MostrarTodos();

		for(Estado e:Estado.values()){
			firmar.getItems().add(e);
		}

		for(EstadoAgua e:EstadoAgua.values()){
			estado_agua.getItems().add(e);
		}

		for(Meses m:Meses.values()){
			mesbusqueda.getItems().add(m);
		}

		this.columna_fecha.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.columna_control.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getControl_organoleptico());
		});
		this.columna_estado.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getEstado());
		});
		this.columna_punto.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getPunto_muestreo());
		});
		this.columna_firmado.setCellValueFactory(eachRowData ->{
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});

		columna_firmado.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO,Estado.NO_FIRMADO));
		columna_firmado.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Agua,Estado>>() {

			@Override
			public void handle(CellEditEvent<Agua, Estado> event) {

				Agua n=(Agua) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				AguaDAO guardar=new AguaDAO(n);
				guardar.Save();
				EscribirArchivos.GuardarAgua();

			}
		});

		columna_estado.setCellFactory(ChoiceBoxTableCell.forTableColumn(EstadoAgua.B,EstadoAgua.M));
		columna_estado.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Agua,EstadoAgua>>() {

			@Override
			public void handle(CellEditEvent<Agua, EstadoAgua> event) {

				Agua n=(Agua) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setEstado(event.getNewValue());
				AguaDAO guardar=new AguaDAO(n);
				guardar.Save();
				EscribirArchivos.GuardarAgua();

			}
		});

		columna_fecha.setCellFactory(param -> new LocalDateTableCell<>(param));
		columna_fecha.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Agua,LocalDate>>() {

			@Override
			public void handle(CellEditEvent<Agua, LocalDate> event) {
				Agua n=(Agua) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFecha(event.getNewValue());
				AguaDAO guardar=new AguaDAO(n);
				guardar.Save();
				EscribirArchivos.GuardarAgua();

			}
		});

		columna_control.setCellFactory(TextFieldTableCell.forTableColumn());
		columna_control.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Agua,String>>() {

			@Override
			public void handle(CellEditEvent<Agua, String> event) {

				Agua n=(Agua) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setControl_organoleptico(event.getNewValue());
				AguaDAO guardar=new AguaDAO(n);
				guardar.Save();
				EscribirArchivos.GuardarAgua();
			}
		});

		columna_punto.setCellFactory(TextFieldTableCell.forTableColumn());
		columna_punto.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Agua,String>>() {

			@Override
			public void handle(CellEditEvent<Agua, String> event) {

				Agua n=(Agua) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setPunto_muestreo(event.getNewValue().toUpperCase());
				AguaDAO guardar=new AguaDAO(n);
				guardar.Save();
				EscribirArchivos.GuardarAgua();
			}
		});



		tabla.setEditable(true);
		tabla.setItems(listaaguadinamica);

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
	private void Limpiar(){
		estado_agua.setValue(null);
		firmar.setValue(null);
		control_organoleptico.clear();
		punto_muestreo.clear();
		introducirfecha.setValue(null);

	}

	@FXML
	private void ADD(){
		if(estado_agua.getValue()!=null&&firmar.getValue()!=null&&control_organoleptico.getText().length()>0&&punto_muestreo.getText().length()>0&&introducirfecha.getValue()!=null){
			Agua nuevo=new Agua(punto_muestreo.getText().toUpperCase(),control_organoleptico.getText(),estado_agua.getValue(),firmar.getValue(),introducirfecha.getValue());
			AguaDAO add=new AguaDAO(nuevo);
			if(add.Save()>0){
				listaaguadinamica.clear();
				MostrarTodos();
				punto_muestreo.clear();
				estado_agua.setValue(null);
				firmar.setValue(null);
				control_organoleptico.clear();
				muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				EscribirArchivos.GuardarAgua();

			}else{
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
			}
		}else{
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
		}
	}

	@FXML
	private void Eliminar(){
		Agua aeliminar=tabla.getSelectionModel().getSelectedItem();
		if(aeliminar!=null){
			if(confirm("", "", "")){

				AguaDAO eli=new AguaDAO(aeliminar);
				if(eli.remuve()>0){
					listaaguadinamica.remove(aeliminar);
					muestrinformacion("ELIMINACIÓN","Registro eliminado","");
					EscribirArchivos.GuardarAgua();
				}else{
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}
	}

	@FXML
	private void MostrarTodos(){
		listaaguadinamica.clear();
		listaaguadinamica.addAll(AguaDAO.SelectAguaAll());
		busquedaconcreta.setValue(null);
		mesbusqueda.setValue(null);
	}

	@FXML
	private void PorMes(){
		if(mesbusqueda.getValue()!=null){
			listaaguadinamica.clear();
			listaaguadinamica.addAll(AguaDAO.SelectAguaMes(Integer.toString(mesbusqueda.getValue().getNumero_Mes())));
		}
		busquedaconcreta.setValue(null);
	}

	@FXML
	private void FechaConcreta(){
		if(busquedaconcreta.getValue()!=null){
			listaaguadinamica.clear();
			listaaguadinamica.addAll(AguaDAO.SelectAguaFecha(busquedaconcreta.getValue().toString()));
		}
		mesbusqueda.setValue(null);
	}


}
