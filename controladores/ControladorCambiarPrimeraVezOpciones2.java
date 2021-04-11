package controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import clasemain.App;
import clasemain.Ejecutable;
import enums.Escenas;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import utilidades.XmlUtilidades;

public class ControladorCambiarPrimeraVezOpciones2 extends ControladorGenerar{
	@FXML
	private HBox box;
	@FXML
	private TextField texto;
	@FXML
	private RadioButton b1;
	@FXML
	private RadioButton b2;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		texto.setEditable(false);
		box.setDisable(true);


	}
	@FXML
	private void ImportarGeneral(){
		String cadena="";
		JFileChooser flich = new JFileChooser();
		flich.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado= flich.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
        	File archivoElegido = flich.getSelectedFile();
        	cadena=archivoElegido.getAbsolutePath();
        }
        texto.setText(cadena);

	}

	@FXML
	private void Personalizado(){
		box.setDisable(false);
		b2.setSelected(false);
		if(!b1.isSelected()){
			box.setDisable(true);
		}
		Ejecutable.getOPciones().setBasededatosdefecto(false);
	}
	@FXML
	private void Defecto(){
		box.setDisable(true);
		b1.setSelected(false);
		texto.clear();
		Ejecutable.getOPciones().setBasededatosdefecto(true);
	}
	@FXML
	private void Volver(){
		try {
			App.setRoot(Escenas.OPCIONESPRIMERAVEZINICIO.getUrl());
			App.CambiarTitulo("Opciones de guardado documenmtos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void Finalizar(){
		boolean salir=false;
		if(b1.isSelected()||b2.isSelected()){
			if(b1.isSelected()){
				if(texto.getText()!=null&&texto.getText().length()>0){
					Ejecutable.getOPciones().setBasededatosdefecto(false);
					Ejecutable.getOPciones().setRutaBaseDatosSeleccionada(texto.getText());
					salir=true;
				}else{
					muestraerror("Error", "INTRODUZCA UNA RUTA DE GUARDADO", "");
				}
			}else{
				Ejecutable.getOPciones().setBasededatosdefecto(true);
				salir=true;
			}
			if(salir&&confirm("CONFIRMACIÓN", "¿GUARDAR TODO?", "Revise si es necesario")){
				try{
					App.setRoot(Escenas.INICIO.getUrl());
					App.CambiarTitulo("TIENDA ANTONIO GUERRERO");
					App.CambiarResizable(true);
					App.PonerMaximizado(true);
					XmlUtilidades.EscribirOPciones(Ejecutable.getOPciones());
				}catch (Exception e) {
				}
			}
		}else{
			muestraerror("Error", "", "SELECCIONE UNA DE LAS OPCIONES");
		}
	}

}
