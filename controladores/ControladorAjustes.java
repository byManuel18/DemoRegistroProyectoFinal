package controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import clasemain.App;
import clasemain.Ejecutable;
import enums.Escenas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilidades.XmlUtilidades;

public class ControladorAjustes extends ControladorGenerar {
	@FXML
	private RadioButton radiobuttonpordefecto;
	@FXML
	private RadioButton radiobutonmodificadas;
	@FXML
	private TextField carne;
	@FXML
	private TextField agua;
	@FXML
	private TextField temperatura;
	@FXML
	private TextField residuos;
	@FXML
	private TextField mantenimiento;
	@FXML
	private TextField facturacion;
	@FXML
	private TextField limpieza;
	@FXML
	private TextField produccion;
	@FXML
	private TextField trazcarne;
	@FXML
	private TextField trazlaure;
	@FXML
	private TextField trazsalsas;
	@FXML
	private TextField trazmateriaprima;
	@FXML
	private TextField trazenvases;
	@FXML
	private Button butcarne;
	@FXML
	private Button butagua;
	@FXML
	private Button buttemperatura;
	@FXML
	private Button butresiduos;
	@FXML
	private Button butmantenimiento;
	@FXML
	private Button butfacturacion;
	@FXML
	private Button butlimpieza;
	@FXML
	private Button butproduccion;
	@FXML
	private Button buttrazcarne;
	@FXML
	private Button buttrazlaure;
	@FXML
	private Button buttrazsalsa;
	@FXML
	private Button buttrazmateriaprima;
	@FXML
	private Button buttrazenvases;
	@FXML
	private Button limpiartxtcarne;
	@FXML
	private Button limpiartxtagua;
	@FXML
	private Button limpiartxttemperatura;
	@FXML
	private Button limpiartxtresiduos;
	@FXML
	private Button limpiartxtmantenimiento;
	@FXML
	private Button limpiartxtfacturacion;
	@FXML
	private Button limpiartxtlimpieza;
	@FXML
	private Button limpiartxtproduccion;
	@FXML
	private Button limpiartxttrazcarne;
	@FXML
	private Button limpiartxttrazlaure;
	@FXML
	private Button limpiartxtsalsas;
	@FXML
	private Button limpiartxtmateriaprima;
	@FXML
	private Button limpiartxttrazenvases;
	// -------------Imagenes------------
	@FXML
	private RadioButton radiobuttonimagendefecto;
	@FXML
	private RadioButton radiobuttonimagenmodificadas;
	@FXML
	private ImageView defcarne;
	@FXML
	private ImageView defaggua;
	@FXML
	private ImageView deftemperatura;
	@FXML
	private ImageView deflimpieza;
	@FXML
	private ImageView defproduccion;
	@FXML
	private ImageView deffacturacion;
	@FXML
	private ImageView defmantenimiento;
	@FXML
	private ImageView defresiduos;
	@FXML
	private ImageView deftrazabilidad;
	@FXML
	private ImageView modcarne;
	@FXML
	private ImageView modaggua;
	@FXML
	private ImageView modtemperatura;
	@FXML
	private ImageView modlimpieza;
	@FXML
	private ImageView modproduccion;
	@FXML
	private ImageView modfacturacion;
	@FXML
	private ImageView modmantenimiento;
	@FXML
	private ImageView modresiduos;
	@FXML
	private ImageView modtrazabilidad;
	@FXML
	private Button buttcarne;
	@FXML
	private Button butttemperatura;
	@FXML
	private Button buttresiduos;
	@FXML
	private Button butttrazabilidad;
	@FXML
	private Button buttproduccion;
	@FXML
	private Button buttfacturacion;
	@FXML
	private Button buttlimpieza;
	@FXML
	private Button buttmantenimiento;
	@FXML
	private Button buttagua;
	@FXML
	private Button limpiarfotocarne;
	@FXML
	private Button limpiarfototemperatura;
	@FXML
	private Button limpiarfotoresiduos;
	@FXML
	private Button limpiarfototrazabilidad;
	@FXML
	private Button limpiarfotoproduccion;
	@FXML
	private Button limpiarfotofacturacion;
	@FXML
	private Button limpiarfotolimmpieza;
	@FXML
	private Button limpiarfotomantenimiento;
	@FXML
	private Button limpiarfotoagua;
	private String urlcarne=Ejecutable.getOPciones().getRutaFotoCarneSeleccionada();
	private String urlagua=Ejecutable.getOPciones().getRutaFotoAguaSeleccionada();
	private String urltemperatura=Ejecutable.getOPciones().getRutaFotoTemperaturaSeleccionada();
	private String urlproduccion=Ejecutable.getOPciones().getRutaFotoProduccionSeleccionada();
	private String urlfacturacion=Ejecutable.getOPciones().getRutaFotoFacturacionSeleccionada();
	private String urllimpieza=Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada();
	private String urlresiduos=Ejecutable.getOPciones().getRutaFotoResiduosSeleccionada();
	private String urlmantenimiento=Ejecutable.getOPciones().getRutaFotoMantenimientoSeleccionada();
	private String urltrazabilidad=Ejecutable.getOPciones().getRutaFotoTrazabilidadSeleccionada();



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carne.setEditable(false);
		agua.setEditable(false);
		temperatura.setEditable(false);
		residuos.setEditable(false);
		mantenimiento.setEditable(false);
		facturacion.setEditable(false);
		limpieza.setEditable(false);
		produccion.setEditable(false);
		trazcarne.setEditable(false);
		trazlaure.setEditable(false);
		trazsalsas.setEditable(false);
		trazmateriaprima.setEditable(false);
		trazenvases.setEditable(false);
		if (Ejecutable.getOPciones().isGuardartxtporDefecto()) {
			radiobuttonpordefecto.setSelected(true);
			carne.setDisable(true);
			agua.setDisable(true);
			temperatura.setDisable(true);
			residuos.setDisable(true);
			mantenimiento.setDisable(true);
			facturacion.setDisable(true);
			limpieza.setDisable(true);
			produccion.setDisable(true);
			trazcarne.setDisable(true);
			trazlaure.setDisable(true);
			trazsalsas.setDisable(true);
			trazmateriaprima.setDisable(true);
			trazenvases.setDisable(true);
			butcarne.setDisable(true);
			butagua.setDisable(true);
			buttemperatura.setDisable(true);
			butresiduos.setDisable(true);
			butmantenimiento.setDisable(true);
			butfacturacion.setDisable(true);
			butlimpieza.setDisable(true);
			butproduccion.setDisable(true);
			buttrazcarne.setDisable(true);
			buttrazlaure.setDisable(true);
			buttrazsalsa.setDisable(true);
			buttrazmateriaprima.setDisable(true);
			buttrazenvases.setDisable(true);
			limpiartxtcarne.setDisable(true);
			limpiartxtagua.setDisable(true);
			limpiartxttemperatura.setDisable(true);
			limpiartxtresiduos.setDisable(true);
			limpiartxtmantenimiento.setDisable(true);
			limpiartxtfacturacion.setDisable(true);
			limpiartxtlimpieza.setDisable(true);
			limpiartxtproduccion.setDisable(true);
			limpiartxttrazcarne.setDisable(true);
			limpiartxttrazlaure.setDisable(true);
			limpiartxtsalsas.setDisable(true);
			limpiartxtmateriaprima.setDisable(true);
			limpiartxttrazenvases.setDisable(true);

		} else {
			radiobutonmodificadas.setSelected(true);

		}
		carne.setText(Ejecutable.getOPciones().getRutaCarneSeleccionada());
		agua.setText(Ejecutable.getOPciones().getRutaAguaSeleccionada());
		temperatura.setText(Ejecutable.getOPciones().getRutaTemperaturaSeleccionada());
		residuos.setText(Ejecutable.getOPciones().getRutaResiduosSeleccionada());
		mantenimiento.setText(Ejecutable.getOPciones().getRutaManteniminetoSeleccionada());
		facturacion.setText(Ejecutable.getOPciones().getRutaFacturacionSeleccionada());
		limpieza.setText(Ejecutable.getOPciones().getRutaLimpiezaSeleccionada());
		produccion.setText(Ejecutable.getOPciones().getRutaproduccionSeleccionada());
		trazcarne.setText(Ejecutable.getOPciones().getRutaTrazCarneSeleccionada());
		trazlaure.setText(Ejecutable.getOPciones().getRutaTrazLaureSeleccionada());
		trazsalsas.setText(Ejecutable.getOPciones().getRutaTrazSalsasSeleccionada());
		trazmateriaprima.setText(Ejecutable.getOPciones().getRutaTrazMateriaPrimaSeleccionada());
		trazenvases.setText(Ejecutable.getOPciones().getRutaTrazEnvasesSeleccionada());
		defcarne.setImage(new Image(Ejecutable.getOPciones().getFotoProductosCarnicosDefecto()));
		defaggua.setImage(new Image(Ejecutable.getOPciones().getFotoAguaDefecto()));
		deftemperatura.setImage(new Image(Ejecutable.getOPciones().getFotoTemperaturasDefecto()));
		deflimpieza.setImage(new Image(Ejecutable.getOPciones().getFotoLimpiezaDefecto()));
		defproduccion.setImage(new Image(Ejecutable.getOPciones().getFotoproducciondefecto()));
		deffacturacion.setImage(new Image(Ejecutable.getOPciones().getFotofacturaciondefecto()));
		defmantenimiento.setImage(new Image(Ejecutable.getOPciones().getFotoMedidasDefecto()));
		defresiduos.setImage(new Image(Ejecutable.getOPciones().getFotoResiduosDefecto()));
		deftrazabilidad.setImage(new Image(Ejecutable.getOPciones().getFototrazavilidaddefecto()));
		if (Ejecutable.getOPciones().isImagenesdefecto()) {
			radiobuttonimagendefecto.setSelected(true);
		} else {
			radiobuttonimagenmodificadas.setSelected(true);
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
	private void Guardar() {
		boolean modificado = false;
		if (confirm("¿Modificar Ajustes?", "Revise si es necesario", "¿Modificar Ajustes?")) {
			if (radiobutonmodificadas.isSelected()) {
				Ejecutable.getOPciones().setGuardartxtporDefecto(false);
				Ejecutable.getOPciones().setRutaCarneSeleccionada(carne.getText());
				Ejecutable.getOPciones().setRutaAguaSeleccionada(agua.getText());
				Ejecutable.getOPciones().setRutaFacturacionSeleccionada(facturacion.getText());
				Ejecutable.getOPciones().setRutaTemperaturaSeleccionada(temperatura.getText());
				Ejecutable.getOPciones().setRutaTrazCarneSeleccionada(trazcarne.getText());
				Ejecutable.getOPciones().setRutaTrazEnvasesSeleccionada(trazenvases.getText());
				Ejecutable.getOPciones().setRutaTrazSalsasSeleccionada(trazsalsas.getText());
				Ejecutable.getOPciones().setRutaTrazLaureSeleccionada(trazlaure.getText());
				Ejecutable.getOPciones().setRutaTrazMateriaPrimaSeleccionada(trazmateriaprima.getText());
				Ejecutable.getOPciones().setRutaproduccionSeleccionada(produccion.getText());
				Ejecutable.getOPciones().setRutaLimpiezaSeleccionada(limpieza.getText());
				Ejecutable.getOPciones().setRutaManteniminetoSeleccionada(mantenimiento.getText());
				Ejecutable.getOPciones().setRutaResiduosSeleccionada(residuos.getText());
				modificado = true;
				muestrinformacion("Ajustes Guardados", "Los datos han sido guardados", "");
			} else if (radiobuttonpordefecto.isSelected()) {
				Ejecutable.getOPciones().setGuardartxtporDefecto(true);
				modificado = true;
				muestrinformacion("Ajustes Guardados", "Los datos han sido guardados", "");
			}
			if (modificado) {
				XmlUtilidades.EscribirOPciones(Ejecutable.getOPciones());
			}
		}

	}

	private static String ImportarGeneral() {
		String cadena = "";
		JFileChooser flich = new JFileChooser();
		flich.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "bmp", "gif", "jpg", "png");
		flich.setFileFilter(filter);
		int resultado = flich.showOpenDialog(null);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			File archivoElegido = flich.getSelectedFile();
			cadena = archivoElegido.getAbsolutePath();
		}
		return cadena;
	}

	private static String ImportarGeneralCarpetas(){
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
	private void ClikPorDefecto() {
		if (radiobuttonpordefecto.isSelected()) {
			carne.setDisable(true);
			agua.setDisable(true);
			temperatura.setDisable(true);
			residuos.setDisable(true);
			mantenimiento.setDisable(true);
			facturacion.setDisable(true);
			limpieza.setDisable(true);
			produccion.setDisable(true);
			trazcarne.setDisable(true);
			trazlaure.setDisable(true);
			trazsalsas.setDisable(true);
			trazmateriaprima.setDisable(true);
			trazenvases.setDisable(true);
			butcarne.setDisable(true);
			butagua.setDisable(true);
			buttemperatura.setDisable(true);
			butresiduos.setDisable(true);
			butmantenimiento.setDisable(true);
			butfacturacion.setDisable(true);
			butlimpieza.setDisable(true);
			butproduccion.setDisable(true);
			buttrazcarne.setDisable(true);
			buttrazlaure.setDisable(true);
			buttrazsalsa.setDisable(true);
			buttrazmateriaprima.setDisable(true);
			buttrazenvases.setDisable(true);
			radiobutonmodificadas.setSelected(false);
			limpiartxtcarne.setDisable(true);
			limpiartxtagua.setDisable(true);
			limpiartxttemperatura.setDisable(true);
			limpiartxtresiduos.setDisable(true);
			limpiartxtmantenimiento.setDisable(true);
			limpiartxtfacturacion.setDisable(true);
			limpiartxtlimpieza.setDisable(true);
			limpiartxtproduccion.setDisable(true);
			limpiartxttrazcarne.setDisable(true);
			limpiartxttrazlaure.setDisable(true);
			limpiartxtsalsas.setDisable(true);
			limpiartxtmateriaprima.setDisable(true);
			limpiartxttrazenvases.setDisable(true);
		} else {
			radiobuttonpordefecto.setSelected(true);
		}
	}

	@FXML
	private void ClickModificadas() {
		if (radiobutonmodificadas.isSelected()) {
			radiobuttonpordefecto.setSelected(false);
			carne.setDisable(false);
			agua.setDisable(false);
			temperatura.setDisable(false);
			residuos.setDisable(false);
			mantenimiento.setDisable(false);
			facturacion.setDisable(false);
			limpieza.setDisable(false);
			produccion.setDisable(false);
			trazcarne.setDisable(false);
			trazlaure.setDisable(false);
			trazsalsas.setDisable(false);
			trazmateriaprima.setDisable(false);
			trazenvases.setDisable(false);
			butcarne.setDisable(false);
			butagua.setDisable(false);
			buttemperatura.setDisable(false);
			butresiduos.setDisable(false);
			butmantenimiento.setDisable(false);
			butfacturacion.setDisable(false);
			butlimpieza.setDisable(false);
			butproduccion.setDisable(false);
			buttrazcarne.setDisable(false);
			buttrazlaure.setDisable(false);
			buttrazsalsa.setDisable(false);
			buttrazmateriaprima.setDisable(false);
			buttrazenvases.setDisable(false);
			limpiartxtcarne.setDisable(false);
			limpiartxtagua.setDisable(false);
			limpiartxttemperatura.setDisable(false);
			limpiartxtresiduos.setDisable(false);
			limpiartxtmantenimiento.setDisable(false);
			limpiartxtfacturacion.setDisable(false);
			limpiartxtlimpieza.setDisable(false);
			limpiartxtproduccion.setDisable(false);
			limpiartxttrazcarne.setDisable(false);
			limpiartxttrazlaure.setDisable(false);
			limpiartxtsalsas.setDisable(false);
			limpiartxtmateriaprima.setDisable(false);
			limpiartxttrazenvases.setDisable(false);

		} else {
			radiobutonmodificadas.setSelected(true);
		}
	}

	@FXML
	private void ClikFotosDefecto() {
		if (radiobuttonimagendefecto.isSelected()) {
			radiobuttonimagenmodificadas.setSelected(false);
			defcarne.setVisible(true);
			deftemperatura.setVisible(true);
			deftrazabilidad.setVisible(true);
			defproduccion.setVisible(true);
			deffacturacion.setVisible(true);
			deflimpieza.setVisible(true);
			defmantenimiento.setVisible(true);
			defresiduos.setVisible(true);
			defaggua.setVisible(true);
			modcarne.setVisible(false);
			modtemperatura.setVisible(false);
			modresiduos.setVisible(false);
			modtrazabilidad.setVisible(false);
			modproduccion.setVisible(false);
			modfacturacion.setVisible(false);
			modlimpieza.setVisible(false);
			modmantenimiento.setVisible(false);
			modaggua.setVisible(false);
			buttagua.setVisible(false);
			buttcarne.setVisible(false);
			buttemperatura.setVisible(false);
			buttfacturacion.setVisible(false);
			buttlimpieza.setVisible(false);
			buttmantenimiento.setVisible(false);
			buttproduccion.setVisible(false);
			buttresiduos.setVisible(false);
			butttrazabilidad.setVisible(false);
			butttemperatura.setVisible(false);
			limpiarfotocarne.setVisible(false);
			limpiarfototemperatura.setVisible(false);
			limpiarfotoresiduos.setVisible(false);
            limpiarfototrazabilidad.setVisible(false);
			limpiarfotoproduccion.setVisible(false);
			limpiarfotofacturacion.setVisible(false);
			limpiarfotolimmpieza.setVisible(false);
			limpiarfotomantenimiento.setVisible(false);
			limpiarfotoagua.setVisible(false);

		} else {
			radiobuttonimagendefecto.setSelected(true);
		}
	}

	@FXML
	private void ClikFotosModificadas() {
		if (radiobuttonimagenmodificadas.isSelected()) {
			radiobuttonimagendefecto.setSelected(false);
			defcarne.setVisible(false);
			deftemperatura.setVisible(false);
			deftrazabilidad.setVisible(false);
			defproduccion.setVisible(false);
			deffacturacion.setVisible(false);
			deflimpieza.setVisible(false);
			defmantenimiento.setVisible(false);
			defresiduos.setVisible(false);
			defaggua.setVisible(false);
			modcarne.setVisible(true);
			modtemperatura.setVisible(true);
			modresiduos.setVisible(true);
			modtrazabilidad.setVisible(true);
			modproduccion.setVisible(true);
			modfacturacion.setVisible(true);
			modlimpieza.setVisible(true);
			modmantenimiento.setVisible(true);
			modaggua.setVisible(true);
			buttagua.setVisible(true);
			buttcarne.setVisible(true);
			buttemperatura.setVisible(true);
			buttfacturacion.setVisible(true);
			buttlimpieza.setVisible(true);
			buttmantenimiento.setVisible(true);
			buttproduccion.setVisible(true);
			buttresiduos.setVisible(true);
			butttrazabilidad.setVisible(true);
			butttemperatura.setVisible(true);
			limpiarfotocarne.setVisible(true);
			limpiarfototemperatura.setVisible(true);
			limpiarfotoresiduos.setVisible(true);
            limpiarfototrazabilidad.setVisible(true);
			limpiarfotoproduccion.setVisible(true);
			limpiarfotofacturacion.setVisible(true);
			limpiarfotolimmpieza.setVisible(true);
			limpiarfotomantenimiento.setVisible(true);
			limpiarfotoagua.setVisible(true);
		} else {
			radiobuttonimagenmodificadas.setSelected(true);
		}
	}

	@FXML
	private void PestanaImagenes() {
		try {
			if(new File (Ejecutable.getOPciones().getRutaFotoCarneSeleccionada()).exists()){
				modcarne.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoCarneSeleccionada()));
			}else{
				modcarne.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modcarne.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoTemperaturaSeleccionada()).exists()){
				modtemperatura.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoTemperaturaSeleccionada()));
			}else{
				modtemperatura.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modtemperatura.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoResiduosSeleccionada()).exists()){
				modresiduos.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoResiduosSeleccionada()));
			}else{
				modresiduos.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modresiduos.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoTrazabilidadSeleccionada()).exists()){
				modtrazabilidad.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoTrazabilidadSeleccionada()));
			}else{
				modtrazabilidad.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modtrazabilidad.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoProduccionSeleccionada()).exists()){
				modproduccion.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoProduccionSeleccionada()));
			}else{
				modproduccion.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modproduccion.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoFacturacionSeleccionada()).exists()){
				modfacturacion.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoFacturacionSeleccionada()));
			}else{
				modfacturacion.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modfacturacion.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()).exists()){
				modlimpieza.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()));
			}else{
				modlimpieza.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modlimpieza.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()).exists()){
				modmantenimiento.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()));

			}else{
				modmantenimiento.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modmantenimiento.setImage(new Image("/assets/nodisponible.png"));
		}
		try {
			if(new File(Ejecutable.getOPciones().getRutaFotoAguaSeleccionada()).exists()){
				modaggua.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoAguaSeleccionada()));
			}else{
				modaggua.setImage(new Image("/assets/nodisponible.png"));
			}

		} catch (Exception e) {
			modaggua.setImage(new Image("/assets/nodisponible.png"));
		}
		if (Ejecutable.getOPciones().isImagenesdefecto()) {
			radiobuttonimagendefecto.setSelected(true);
			radiobuttonimagenmodificadas.setSelected(false);
			defcarne.setVisible(true);
			deftemperatura.setVisible(true);
			deftrazabilidad.setVisible(true);
			defproduccion.setVisible(true);
			deffacturacion.setVisible(true);
			deflimpieza.setVisible(true);
			defmantenimiento.setVisible(true);
			defresiduos.setVisible(true);
			defaggua.setVisible(true);
			modcarne.setVisible(false);
			modtemperatura.setVisible(false);
			modresiduos.setVisible(false);
			modtrazabilidad.setVisible(false);
			modproduccion.setVisible(false);
			modfacturacion.setVisible(false);
			modlimpieza.setVisible(false);
			modmantenimiento.setVisible(false);
			modaggua.setVisible(false);
			buttagua.setVisible(false);
			buttcarne.setVisible(false);
			buttemperatura.setVisible(false);
			buttfacturacion.setVisible(false);
			buttlimpieza.setVisible(false);
			buttmantenimiento.setVisible(false);
			buttproduccion.setVisible(false);
			buttresiduos.setVisible(false);
			butttrazabilidad.setVisible(false);
			butttemperatura.setVisible(false);
			limpiarfotocarne.setVisible(false);
			limpiarfototemperatura.setVisible(false);
			limpiarfotoresiduos.setVisible(false);
            limpiarfototrazabilidad.setVisible(false);
			limpiarfotoproduccion.setVisible(false);
			limpiarfotofacturacion.setVisible(false);
			limpiarfotolimmpieza.setVisible(false);
			limpiarfotomantenimiento.setVisible(false);
			limpiarfotoagua.setVisible(false);


		} else {
			radiobuttonimagenmodificadas.setSelected(true);
			radiobuttonimagendefecto.setSelected(false);
			defcarne.setVisible(false);
			deftemperatura.setVisible(false);
			deftrazabilidad.setVisible(false);
			defproduccion.setVisible(false);
			deffacturacion.setVisible(false);
			deflimpieza.setVisible(false);
			defmantenimiento.setVisible(false);
			defresiduos.setVisible(false);
			defaggua.setVisible(false);
			modcarne.setVisible(true);
			modtemperatura.setVisible(true);
			modresiduos.setVisible(true);
			modtrazabilidad.setVisible(true);
			modproduccion.setVisible(true);
			modfacturacion.setVisible(true);
			modlimpieza.setVisible(true);
			modmantenimiento.setVisible(true);
			modaggua.setVisible(true);
			buttagua.setVisible(true);
			buttcarne.setVisible(true);
			buttemperatura.setVisible(true);
			buttfacturacion.setVisible(true);
			buttlimpieza.setVisible(true);
			buttmantenimiento.setVisible(true);
			buttproduccion.setVisible(true);
			buttresiduos.setVisible(true);
			butttrazabilidad.setVisible(true);
			butttemperatura.setVisible(true);
			limpiarfotocarne.setVisible(true);
			limpiarfototemperatura.setVisible(true);
			limpiarfotoresiduos.setVisible(true);
            limpiarfototrazabilidad.setVisible(true);
			limpiarfotoproduccion.setVisible(true);
			limpiarfotofacturacion.setVisible(true);
			limpiarfotolimmpieza.setVisible(true);
			limpiarfotomantenimiento.setVisible(true);
			limpiarfotoagua.setVisible(true);
		}
	}
	@FXML
	private void GuardarFotos(){
		if(confirm("¿Modificar Ajustes?", "Revise si es necesario", "¿Modificar Ajustes?")){
			if(radiobuttonimagendefecto.isSelected()){
				Ejecutable.getOPciones().setImagenesdefecto(true);
			}else if(radiobuttonimagenmodificadas.isSelected()){
				Ejecutable.getOPciones().setImagenesdefecto(false);

					Ejecutable.getOPciones().setRutaFotoCarneSeleccionada(urlcarne);


					Ejecutable.getOPciones().setRutaFotoTrazabilidadSeleccionada(urltrazabilidad);


					Ejecutable.getOPciones().setRutaFotoAguaSeleccionada(urlagua);


					Ejecutable.getOPciones().setRutaFotoTemperaturaSeleccionada(urltemperatura);


					Ejecutable.getOPciones().setRutaFotoResiduosSeleccionada(urlresiduos);


					Ejecutable.getOPciones().setRutaFotoProduccionSeleccionada(urlproduccion);


					Ejecutable.getOPciones().setRutaFotoFacturacionSeleccionada(urlfacturacion);


					Ejecutable.getOPciones().setRutaFotoLimpiezaSeleccionada(urllimpieza);


					Ejecutable.getOPciones().setRutaFotoMantenimientoSeleccionada(urlmantenimiento);

			}
			XmlUtilidades.EscribirOPciones(Ejecutable.getOPciones());
			muestrinformacion("Ajustes Guardados", "Los datos han sido guardados", "");
		}

	}
	@FXML
	private void ImportarImagenCarne(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modcarne.setImage(new Image("file:///"+cadena));
				urlcarne=cadena;
			} catch (Exception e) {
				modcarne.setImage(new Image("/assets/nodisponible.png"));
				urlcarne=null;
			}
		}
	}
	@FXML
	private void ImportarImagenTrazabilidad(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modtrazabilidad.setImage(new Image("file:///"+cadena));
				urltrazabilidad=cadena;
			} catch (Exception e) {
				modtrazabilidad.setImage(new Image("/assets/nodisponible.png"));
				urlcarne=null;
			}
		}
	}
	@FXML
	private void ImportarImagenAgua(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modaggua.setImage(new Image("file:///"+cadena));
				urlagua=cadena;
			} catch (Exception e) {
				modaggua.setImage(new Image("/assets/nodisponible.png"));
				urlagua=null;
			}
		}
	}
	@FXML
	private void ImportarImagenTemperatura(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modtemperatura.setImage(new Image("file:///"+cadena));
				urltemperatura=cadena;
			} catch (Exception e) {
				modtemperatura.setImage(new Image("/assets/nodisponible.png"));
				urltemperatura=null;
			}
		}
	}
	@FXML
	private void ImportarImagenresiduos(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modresiduos.setImage(new Image("file:///"+cadena));
				urlresiduos=cadena;
			} catch (Exception e) {
				modresiduos.setImage(new Image("/assets/nodisponible.png"));
				urlresiduos=null;
			}
		}
	}
	@FXML
	private void ImportarImagenProduccion(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modproduccion.setImage(new Image("file:///"+cadena));
				urlproduccion=cadena;
			} catch (Exception e) {
				modproduccion.setImage(new Image("/assets/nodisponible.png"));
				urlproduccion=null;
			}
		}
	}
	@FXML
	private void ImportarImagenFacturacion(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modfacturacion.setImage(new Image("file:///"+cadena));
				urlfacturacion=cadena;
			} catch (Exception e) {
				modfacturacion.setImage(new Image("/assets/nodisponible.png"));
				urlfacturacion=null;
			}
		}
	}
	@FXML
	private void ImportarImagenLimpieza(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modlimpieza.setImage(new Image("file:///"+cadena));
				urllimpieza=cadena;
			} catch (Exception e) {
				modlimpieza.setImage(new Image("/assets/nodisponible.png"));
				urllimpieza=null;
			}
		}
	}
	@FXML
	private void ImportarImagenMantenimiento(){
		String cadena="";
		cadena+=ImportarGeneral();
		if(cadena.length()>8){
			try {
				modmantenimiento.setImage(new Image("file:///"+cadena));
				urlmantenimiento=cadena;
			} catch (Exception e) {
				modmantenimiento.setImage(new Image("/assets/nodisponible.png"));
				urlmantenimiento=null;
			}
		}
	}
	@FXML
	private void LimpiarFotoCarne(){
		urlcarne=null;
		modcarne.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoTemperatura(){
		urltemperatura=null;
		modtemperatura.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoResiduos(){
		urlresiduos=null;
		modresiduos.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoTrazabilidad(){
		urltrazabilidad=null;
		modtrazabilidad.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoProduccion(){
		urlproduccion=null;
		modproduccion.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoFacturacion(){
		urlfacturacion=null;
		modfacturacion.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoLimpieza(){
		urllimpieza=null;
		modlimpieza.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoMantenimiento(){
		urlmantenimiento=null;
		modmantenimiento.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarFotoAggua(){
		urlagua=null;
		modaggua.setImage(new Image("/assets/nodisponible.png"));
	}
	@FXML
	private void LimpiarTXTCarne(){
		carne.clear();
	}
	@FXML
	private void TxtCarne(){
		carne.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTAgua(){
		agua.clear();
	}
	@FXML
	private void TxtAgua(){
		agua.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTTemperatura(){
		temperatura.clear();
	}
	@FXML
	private void TxtTemperatura(){
		temperatura.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTResiduos(){
		residuos.clear();
	}
	@FXML
	private void TxtResiduos(){
		residuos.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTMantenimiento(){
		mantenimiento.clear();
	}
	@FXML
	private void TxtMantenimiento(){
		mantenimiento.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTfacturacion(){
		facturacion.clear();
	}
	@FXML
	private void TxtFacturacion(){
		facturacion.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTLimpieza(){
		limpieza.clear();
	}
	@FXML
	private void TxtLimpieza(){
		limpieza.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTProduccion(){
		produccion.clear();
	}
	@FXML
	private void TxtProduccion(){
		produccion.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTTrazCarne(){
		trazcarne.clear();
	}
	@FXML
	private void TxtTrazCarne(){
		trazcarne.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTtrazlaure(){
		trazlaure.clear();
	}
	@FXML
	private void TxtLaure(){
		trazlaure.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTtrazsalsas(){
		trazsalsas.clear();
	}
	@FXML
	private void TxtSalsa(){
		trazsalsas.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTtrazmateriaprima(){
		trazmateriaprima.clear();
	}
	@FXML
	private void TxtTrazMateriaPrima(){
		trazmateriaprima.setText(ImportarGeneralCarpetas());
	}
	@FXML
	private void LimpiarTXTtrazenvases(){
		trazenvases.clear();
	}
	@FXML
	private void TxtTrazEnvases(){
		trazenvases.setText(ImportarGeneralCarpetas());
	}
}
