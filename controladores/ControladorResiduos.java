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
import modelos.Residuo;
import modelos.ResiduoDAO;
import utilidades.LocalDateTableCell;
import utilidades.UtilidadesGenerales;

public class ControladorResiduos extends ControladorGenerar{
	@FXML
	private TableView<Residuo> tabla;
	@FXML
	private DatePicker intro_fecha;
	@FXML
	private DatePicker fecha_busque;
	@FXML
	private ChoiceBox<Estado> intro_firma;
	@FXML
	private ChoiceBox<Meses> busque_mes;
	@FXML
	private TextField intro_nombre;
	@FXML
	private TextField intro_canti;
	@FXML
	private TableColumn<Residuo, LocalDate> colum_fecha;
	@FXML
	private TableColumn<Residuo, String> colum_nombre;
	@FXML
	private TableColumn<Residuo, String> colum_cantidad;
	@FXML
	private TableColumn<Residuo, Estado> colum_estado;

	private ObservableList<Residuo> listadinamicaresiduos;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listadinamicaresiduos=FXCollections.observableArrayList();
		MostrarTodo();
		for(Meses m:Meses.values()){
			busque_mes.getItems().add(m);
		}
		for(Estado e:Estado.values()){
			intro_firma.getItems().add(e);
		}

		this.colum_fecha.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
		});
		this.colum_estado.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getFirmado());
		});
		this.colum_cantidad.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getCantidad());
		});
		this.colum_nombre.setCellValueFactory(eachRowData -> {
			return new SimpleObjectProperty<>(eachRowData.getValue().getNombre_persona());
		});

		colum_estado.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO,Estado.NO_FIRMADO));
		colum_estado.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Residuo,Estado>>() {

			@Override
			public void handle(CellEditEvent<Residuo, Estado> event) {

				Residuo n=(Residuo) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFirmado(event.getNewValue());
				ResiduoDAO guardar=new ResiduoDAO(n);
				guardar.save();

			}
		});
		colum_fecha.setCellFactory(param -> new LocalDateTableCell<>(param));
		colum_fecha.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Residuo,LocalDate>>() {

			@Override
			public void handle(CellEditEvent<Residuo, LocalDate> event) {
				Residuo n=(Residuo) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setFecha(event.getNewValue());
				ResiduoDAO guardar=new ResiduoDAO(n);
				guardar.save();

			}
		});

		colum_nombre.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_nombre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Residuo,String>>() {

			@Override
			public void handle(CellEditEvent<Residuo, String> event) {

				Residuo n=(Residuo) event.getTableView().getItems().get(
                        event.getTablePosition().getRow());
				n.setNombre_persona(event.getNewValue().toUpperCase());
				ResiduoDAO guardar=new ResiduoDAO(n);
				guardar.save();
			}
		});
		colum_cantidad.setCellFactory(TextFieldTableCell.forTableColumn());
		colum_cantidad.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Residuo,String>>() {

			@Override
			public void handle(CellEditEvent<Residuo, String> event) {

				boolean modificado = false;
				Residuo n = (Residuo) event.getTableView().getItems().get(event.getTablePosition().getRow());
				if (UtilidadesGenerales.ValidarIntroducirCantidadResiduos(event.getNewValue())) {
					n.setCantidad(event.getNewValue());
					modificado = true;
				} else {
					if (UtilidadesGenerales.ValidarIntroducirCantidadResiduos(
							UtilidadesGenerales.PonerKg(event.getNewValue()))) {
						n.setCantidad(
								UtilidadesGenerales.PonerKg(event.getNewValue()));
						modificado = true;
					}
				}

				if (modificado) {
					ResiduoDAO guardar = new ResiduoDAO(n);
					guardar.save();
				}
			}
		});
		tabla.setEditable(true);
		tabla.setItems(listadinamicaresiduos);
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
	private void Add(){
		if(intro_canti.getText().length()>0&&intro_canti.getText().length()>0&&intro_fecha.getValue()!=null&&intro_firma.getValue()!=null){
			String intro_cantidad="";
			boolean procede=false;
			if(UtilidadesGenerales.ValidarIntroducirCantidadResiduos(intro_canti.getText())){
				intro_cantidad=intro_canti.getText();
				procede=true;
			}else{
				if(UtilidadesGenerales.ValidarIntroducirCantidadResiduos(UtilidadesGenerales.PonerKg(intro_canti.getText()))){
					procede=true;
					intro_cantidad=UtilidadesGenerales.PonerKg(intro_canti.getText());
				}else{
					intro_canti.clear();
				}
			}
			if(procede){
				Residuo nuevo=new Residuo(intro_fecha.getValue(), intro_nombre.getText(), intro_cantidad, intro_firma.getValue());
				ResiduoDAO add=new ResiduoDAO(nuevo);
				if(add.save()>0){
					listadinamicaresiduos.clear();
					listadinamicaresiduos.addAll(ResiduoDAO.SelectAll());
					intro_canti.clear();
					intro_firma.setValue(null);
					intro_nombre.clear();
					muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				}else{
					muestraerror("ERROR AL INTRODUCIR REGISTRO", "",
							"Revise que todos los campos están rellenos y son válidos");
				}
			}else{
				muestraerror("ERROR EN EL FORMATO", "",
						"Vuelve a introducir los datos en los campos en blanco");
			}

		}else{
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos.");
		}
	}
	@FXML
	private void Eliminar(){
		Residuo aeli=tabla.getSelectionModel().getSelectedItem();
		if(confirm("", "", "")){
			ResiduoDAO eliminar=new ResiduoDAO(aeli);
			if(eliminar.remuve()>0){
				listadinamicaresiduos.remove(aeli);
				muestrinformacion("ELIMINACIÓN", "Registro eliminado", "");
			}else{
				muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
			}
		}

	}

	@FXML
	private void Limpiar(){
		intro_canti.clear();
		intro_fecha.setValue(null);
		intro_firma.setValue(null);
		intro_nombre.clear();
	}
	@FXML
	private void MostrarTodo(){
		listadinamicaresiduos.clear();
		listadinamicaresiduos.addAll(ResiduoDAO.SelectAll());
		busque_mes.setValue(null);
		fecha_busque.setValue(null);
	}
	@FXML
	private void PorFecha(){
		if(fecha_busque.getValue()!=null){
			busque_mes.setValue(null);
			listadinamicaresiduos.clear();
			listadinamicaresiduos.addAll(ResiduoDAO.SelectPorFecha(fecha_busque.getValue().toString()));
		}
	}
	@FXML
	private void PorMes(){
		if(busque_mes.getValue()!=null){
			fecha_busque.setValue(null);
			listadinamicaresiduos.clear();
			listadinamicaresiduos.addAll(ResiduoDAO.SelectMes(Integer.toString(busque_mes.getValue().getNumero_Mes())));
		}
	}


}
