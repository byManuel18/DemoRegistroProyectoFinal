package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clasemain.App;
import clasemain.Ejecutable;
import enums.Escenas;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import utilidades.XmlUtilidades;

public class ControladorPrimerInicio extends ControladorGenerar {

	@FXML
	private RadioButton rd1;
	@FXML
	private RadioButton rd2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void BorrarRD1(){
		if(rd1.isSelected()){
			rd1.setSelected(false);
		}
	}

	@FXML
	private void BorrarRD2(){
		if(rd2.isSelected()){
			rd2.setSelected(false);
		}
	}
	@FXML
	private void Siguiente(){

		if(rd2.isSelected()||rd1.isSelected()){
			if(rd1.isSelected()){
				Ejecutable.getOPciones().setGuardartxtporDefecto(true);
				try {
					App.setRoot(Escenas.INICIO.getUrl());
					App.CambiarTitulo("TIENDA ANTONIO GUERRERO");
					App.CambiarResizable(true);
					App.PonerMaximizado(true);
					XmlUtilidades.EscribirOPciones(Ejecutable.getOPciones());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(rd2.isSelected()){
				Ejecutable.getOPciones().setGuardartxtporDefecto(false);
				try {
					App.setRoot(Escenas.OPCIONESPRIMERAVEZINICIO.getUrl());
					App.CambiarTitulo("Opciones de guardado documenmtos");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}


}
