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
import javafx.scene.control.TextField;

public class ControladorInicioPrimeravezOpciones extends ControladorGenerar {

	@FXML
	private TextField carne;
	@FXML
	private TextField limpieza;
	@FXML
	private TextField agua;
	@FXML
	private TextField temperatura;
	@FXML
	private TextField residuos;
	@FXML
	private TextField mantenimientoymedidas;
	@FXML
	private TextField factiracion;
	@FXML
	private TextField produccion;
	@FXML
	private TextField traz_carne;
	@FXML
	private TextField traz_laure;
	@FXML
	private TextField traz_salsas;
	@FXML
	private TextField traz_materiaprima;
	@FXML
	private TextField traz_envases;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carne.setEditable(false);
		limpieza.setEditable(false);
		agua.setEditable(false);
		temperatura.setEditable(false);
		residuos.setEditable(false);
		mantenimientoymedidas.setEditable(false);
		factiracion.setEditable(false);
		traz_envases.setEditable(false);
		traz_materiaprima.setEditable(false);
		traz_salsas.setEditable(false);
		traz_laure.setEditable(false);
		traz_carne.setEditable(false);
		produccion.setEditable(false);
	}
	@FXML
	private void ImprtarCarne(){
		carne.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarLimpieza(){
		limpieza.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarAgua(){
		agua.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarTemperatura(){
		temperatura.setText(ImportarGeneral());
	}
	@FXML
	private void Imprtarmantenimientoymedidas(){
		mantenimientoymedidas.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarFacturacion(){
		factiracion.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarResiduos(){
		residuos.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarTrazCarne(){
		traz_carne.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarTrazLaure(){
		traz_laure.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarTrazSalsas(){
		traz_salsas.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarTrazMateriaPrima(){
		traz_materiaprima.setText(ImportarGeneral());
	}
	@FXML
	private void ImprtarTrazEnvases(){
		traz_envases.setText(ImportarGeneral());
	}
	@FXML
	private void ImportarProduccion(){
		produccion.setText(ImportarGeneral());
	}


	private static String ImportarGeneral(){
		String cadena="";
		JFileChooser flich = new JFileChooser();
		flich.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado= flich.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
        	File archivoElegido = flich.getSelectedFile();
        	cadena=archivoElegido.getAbsolutePath();
        }
		return cadena;
	}
	@FXML
	private void Volver(){
		try {
			App.setRoot(Escenas.PRIMERACARGA.getUrl());
			App.CambiarTitulo("INICIO PROGRAMA. MENÚ OPCIONES");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void GuardarOpciones(){
		int contador=0;
		if(carne.getText()!=null&&carne.getText().length()>0){
			Ejecutable.getOPciones().setRutaCarneSeleccionada(carne.getText());
			contador++;
		}
		if(limpieza.getText()!=null&&limpieza.getText().length()>0){
			Ejecutable.getOPciones().setRutaLimpiezaSeleccionada(limpieza.getText());
			contador++;
		}
		if(agua.getText()!=null&&agua.getText().length()>0){
			Ejecutable.getOPciones().setRutaAguaSeleccionada(agua.getText());
			contador++;
		}
		if(temperatura.getText()!=null&&temperatura.getText().length()>0){
			Ejecutable.getOPciones().setRutaTemperaturaSeleccionada(temperatura.getText());
			contador++;
		}
		if(mantenimientoymedidas.getText()!=null&&mantenimientoymedidas.getText().length()>0){
			Ejecutable.getOPciones().setRutaManteniminetoSeleccionada(mantenimientoymedidas.getText());
			contador++;
		}
		if(factiracion.getText()!=null&&factiracion.getText().length()>0){
			Ejecutable.getOPciones().setRutaFacturacionSeleccionada(factiracion.getText());
			contador++;
		}
		if(residuos.getText()!=null&&residuos.getText().length()>0){
			Ejecutable.getOPciones().setRutaResiduosSeleccionada(residuos.getText());
			contador++;
		}
		if(traz_carne.getText()!=null&&traz_carne.getText().length()>0){
			Ejecutable.getOPciones().setRutaTrazCarneSeleccionada(traz_carne.getText());
			contador++;
		}
		if(traz_envases.getText()!=null&&traz_envases.getText().length()>0){
			Ejecutable.getOPciones().setRutaTrazEnvasesSeleccionada(traz_envases.getText());
			contador++;
		}
		if(traz_laure.getText()!=null&&traz_laure.getText().length()>0){
			Ejecutable.getOPciones().setRutaTrazLaureSeleccionada(traz_laure.getText());
			contador++;
		}
		if(traz_salsas.getText()!=null&&traz_salsas.getText().length()>0){
			Ejecutable.getOPciones().setRutaTrazSalsasSeleccionada(traz_salsas.getText());
			contador++;
		}
		if(traz_materiaprima.getText()!=null&&traz_materiaprima.getText().length()>0){
			Ejecutable.getOPciones().setRutaTrazMateriaPrimaSeleccionada(traz_materiaprima.getText());
			contador++;
		}
		if(produccion.getText()!=null&&produccion.getText().length()>0){
			Ejecutable.getOPciones().setRutaproduccionSeleccionada(produccion.getText());
		}
		if(contador>0){
			carne.clear();
			limpieza.clear();
			agua.clear();
			temperatura.clear();
			mantenimientoymedidas.clear();
			factiracion.clear();
			residuos.clear();
			traz_materiaprima.clear();
			traz_salsas.clear();
			traz_laure.clear();
			traz_envases.clear();
			traz_carne.clear();
			produccion.clear();
			Ejecutable.getOPciones().setGuardartxtporDefecto(false);
			muestrinformacion("GUARDADO", "Se han guardado los ajustes", "");

		}

	}
	@FXML
	private void Siguiente(){
		try {
			App.setRoot(Escenas.OPCIONESPRIMERAVEZINICIO2.getUrl());
			App.CambiarTitulo("INICIO PROGRAMA. MENÚ OPCIONES. BASE DE DATOS");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
