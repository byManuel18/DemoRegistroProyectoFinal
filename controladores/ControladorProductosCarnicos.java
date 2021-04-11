package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import clasemain.App;
import enums.Escenas;
import enums.Estado;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import modelos.SanidadDAO;
import modelos.SanidadProducto;
import utilidades.LocalDateTableCell;

public class ControladorProductosCarnicos extends ControladorGenerar{

	@FXML
	private TableView<SanidadProducto> tabla;
	@FXML
	private TableColumn<SanidadProducto, String> columnombre;
	@FXML
	private TableColumn<SanidadProducto, String> columprovee;
	@FXML
	private TableColumn<SanidadProducto, String> columlote;
	@FXML
	private TableColumn<SanidadProducto, LocalDate> columfecha;
	@FXML
	private TableColumn<SanidadProducto, Estado> columestado;
	@FXML
	private DatePicker fecha;
	@FXML
	private DatePicker fecha_busqueda;
	@FXML
	private javafx.scene.control.TextField provee;
	@FXML
	private javafx.scene.control.TextField nombrepro;
	@FXML
	private javafx.scene.control.TextField lote;
	@FXML
	private ChoiceBox<Estado> estadopro;
	@FXML
	private javafx.scene.control.TextField buscarnombrepro;
	@FXML
	private javafx.scene.control.TextField buscarnombreproveedor;

	private ObservableList<SanidadProducto> productos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productos=FXCollections.observableArrayList();
		if(estadopro!=null){
			for(Estado es: Estado.values()){
				estadopro.getItems().add(es);
			}
			estadopro.setValue(Estado.FIRMADO);
		}

		List<SanidadProducto> lis=SanidadDAO.SelecAll();
		productos.addAll(lis);


			this.columlote.setCellValueFactory(eachRowData ->{
				return new SimpleObjectProperty<>(eachRowData.getValue().getCodigolote());
			});
			this.columnombre.setCellValueFactory(eachRowData ->{
				return new SimpleObjectProperty<>(eachRowData.getValue().getNombre_producto());
			});
			this.columprovee.setCellValueFactory(eachRowData ->{
				return new SimpleObjectProperty<>(eachRowData.getValue().getProveedor());
			});

			this.columestado.setCellValueFactory(eachRowData ->{
				return new SimpleObjectProperty<>(eachRowData.getValue().getEstado());
			});

			this.columfecha.setCellValueFactory(eachRowData ->{
				return new SimpleObjectProperty<>(eachRowData.getValue().getFecha());
			});

			columestado.setCellFactory(ChoiceBoxTableCell.forTableColumn(Estado.FIRMADO,Estado.NO_FIRMADO));
			columestado.setOnEditCommit(
					new EventHandler<TableColumn.CellEditEvent<SanidadProducto,Estado>>() {

				@Override
				public void handle(CellEditEvent<SanidadProducto, Estado> event) {

					SanidadProducto n=(SanidadProducto) event.getTableView().getItems().get(
	                        event.getTablePosition().getRow());
					n.setEstado(event.getNewValue());
					SanidadDAO guardar=new SanidadDAO(n);
					guardar.save();

				}
			});

			columfecha.setCellFactory(param -> new LocalDateTableCell<>(param));
			columfecha.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SanidadProducto,LocalDate>>() {

				@Override
				public void handle(CellEditEvent<SanidadProducto, LocalDate> event) {
					SanidadProducto n=(SanidadProducto) event.getTableView().getItems().get(
	                        event.getTablePosition().getRow());
					n.setFecha(event.getNewValue());
					SanidadDAO guardar=new SanidadDAO(n);
					guardar.save();

				}
			});

			columnombre.setCellFactory(TextFieldTableCell.forTableColumn());
			columnombre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SanidadProducto,String>>() {

				@Override
				public void handle(CellEditEvent<SanidadProducto, String> event) {

					SanidadProducto n=(SanidadProducto) event.getTableView().getItems().get(
	                        event.getTablePosition().getRow());
					n.setNombre_producto(event.getNewValue().toUpperCase());
					SanidadDAO guardar=new SanidadDAO(n);
					guardar.save();
				}
			});

			columprovee.setCellFactory(TextFieldTableCell.forTableColumn());
			columprovee.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SanidadProducto,String>>() {

				@Override
				public void handle(CellEditEvent<SanidadProducto, String> event) {

					SanidadProducto n=(SanidadProducto) event.getTableView().getItems().get(
	                        event.getTablePosition().getRow());
					n.setProveedor(event.getNewValue().toUpperCase());
					SanidadDAO guardar=new SanidadDAO(n);
					guardar.save();
				}
			});

			columlote.setCellFactory(TextFieldTableCell.forTableColumn());
			columlote.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SanidadProducto,String>>() {

				@Override
				public void handle(CellEditEvent<SanidadProducto, String> event) {

					SanidadProducto n=(SanidadProducto) event.getTableView().getItems().get(
	                        event.getTablePosition().getRow());
					n.setCodigolote(event.getNewValue().toUpperCase());
					SanidadDAO guardar=new SanidadDAO(n);
					guardar.save();
				}
			});


		this.tabla.setEditable(true);
		this.tabla.setItems(productos);


	}

	@FXML
	private void Volver(ActionEvent event){
		try {
			App.setRoot(Escenas.INICIO.getUrl());
			App.CambiarTitulo("TIENDA ANTONIO GUERRERO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void Add(ActionEvent event){
		if(fecha!=null&&nombrepro!=null&&estadopro!=null&&lote!=null&&provee!=null){
			if(fecha.getValue()!=null&&nombrepro.getText().length()>0&&lote.getText().length()>0&&provee.getText().length()>0){
				SanidadProducto nuevo=new SanidadProducto(fecha.getValue(),nombrepro.getText().toUpperCase(), provee.getText().toUpperCase(), estadopro.getValue(), lote.getText().toUpperCase());
				SanidadDAO add= new SanidadDAO(nuevo);
				if(add.save()>0){
					List<SanidadProducto> nuevalis=SanidadDAO.SelecAll();
					productos.clear();
					productos.addAll(nuevalis);
					//fecha.setValue(null);
					nombrepro.clear();
					lote.clear();
					//provee.clear();
					estadopro.setValue(Estado.FIRMADO);
					muestrinformacion("Registro añadido correctamente", "", "Nuevo registro añadido");
				}else{
					muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
				}
			}else{
				muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
			}
		}else{
			muestraerror("ERROR AL INTRODUCIR REGISTRO", "", "Revise que todos los campos están rellenos y son válidos");
		}
	}

	@FXML
	private void Eliminar(ActionEvent event){
		SanidadProducto eliminar=tabla.getSelectionModel().getSelectedItem();
		if(eliminar!=null){
			if(confirm("","","")){
				productos.remove(eliminar);
				SanidadDAO eli=new SanidadDAO(eliminar);
				if(eli.delete()>0){
					muestrinformacion("ELIMINACIÓN","Registro eliminado","");
				}else{
					muestraerror("Error al eliminar el registro", "", "Ha ocurrido algún error al intentar eliminar");
				}
			}
		}
	}

	@FXML
	private void BuscarPorFecha(ActionEvent event){
		if(fecha_busqueda!=null&&fecha_busqueda.getValue()!=null){
			List<SanidadProducto> nueva=SanidadDAO.SelecByFecha(fecha_busqueda.getValue().toString());
			productos.clear();
			productos.addAll(nueva);
		}else{
			List<SanidadProducto> nueva=SanidadDAO.SelecAll();
			productos.clear();
			productos.addAll(nueva);
		}
		if(buscarnombrepro.getText().length()>0){
			buscarnombrepro.clear();
		}
		if(buscarnombreproveedor.getText().length()>0){
			buscarnombreproveedor.clear();
		}

	}

	@FXML
	private void BuscarPorNombreDeProducto(ActionEvent event){
		if(buscarnombrepro!=null&&buscarnombrepro.getText().length()>0){
			List<SanidadProducto> nueva=SanidadDAO.SelecByNombreproducto(buscarnombrepro.getText().toUpperCase());
			productos.clear();
			productos.addAll(nueva);
		}else{
			List<SanidadProducto> nueva=SanidadDAO.SelecAll();
			productos.clear();
			productos.addAll(nueva);
		}
		if(buscarnombreproveedor.getText().length()>0){
			buscarnombreproveedor.clear();
		}
		if(fecha_busqueda.getValue()!=null){
			fecha_busqueda.setValue(null);
		}
	}

	@FXML
	private void BuscarPorNombreDeProveedor(ActionEvent event){
		if(buscarnombreproveedor!=null&&buscarnombreproveedor.getText().length()>0){
			List<SanidadProducto> nueva=SanidadDAO.SelecByProveedor(buscarnombreproveedor.getText().toUpperCase());
			productos.clear();
			productos.addAll(nueva);
		}else{
			List<SanidadProducto> nueva=SanidadDAO.SelecAll();
			productos.clear();
			productos.addAll(nueva);
		}
		if(buscarnombrepro.getText().length()>0){
			buscarnombrepro.clear();
		}
		if(fecha_busqueda.getValue()!=null){
			fecha_busqueda.setValue(null);
		}
	}

}
