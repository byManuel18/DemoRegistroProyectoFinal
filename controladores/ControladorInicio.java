package controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clasemain.App;
import clasemain.Ejecutable;
import enums.Escenas;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilidades.ConecxionH2;
import utilidades.EscribirArchivos;

public class ControladorInicio extends ControladorGenerar {

	@FXML
	private ImageView agua_img;
	@FXML
	private ImageView carne_img;
	@FXML
	private ImageView limp_img;
	@FXML
	private ImageView temp_img;
	@FXML
	private ImageView res_img;
	@FXML
	private ImageView man_img;
	@FXML
	private ImageView traza_img;
	@FXML
	private ImageView facturacion;
	@FXML
	private ImageView produccion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if(!Ejecutable.getOPciones().isImagenesdefecto()){

			try{
				if(Ejecutable.getOPciones().getRutaFotoAguaSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoAguaSeleccionada()).exists()){
					agua_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoAguaSeleccionada()));
				}else{
					agua_img.setImage(new Image(Ejecutable.getOPciones().getFotoAguaDefecto()));
				}
			}catch (Exception e) {
				agua_img.setImage(new Image(Ejecutable.getOPciones().getFotoAguaDefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaCarneSeleccionada()!=null&&new File(Ejecutable.getOPciones().getRutaFotoCarneSeleccionada()).exists()){
					carne_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoCarneSeleccionada()));
				}else{
					carne_img.setImage(new Image(Ejecutable.getOPciones().getFotoProductosCarnicosDefecto()));
				}
			}catch (Exception e) {
				carne_img.setImage(new Image(Ejecutable.getOPciones().getFotoProductosCarnicosDefecto()));

			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()).exists()){
					limp_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoLimpiezaSeleccionada()));
				}else{
					limp_img.setImage(new Image(Ejecutable.getOPciones().getFotoLimpiezaDefecto()));
				}
			}catch (Exception e) {
				limp_img.setImage(new Image(Ejecutable.getOPciones().getFotoLimpiezaDefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoTemperaturaSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoTemperaturaSeleccionada()).exists()){
					temp_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoTemperaturaSeleccionada()));
				}else{
					temp_img.setImage(new Image(Ejecutable.getOPciones().getFotoTemperaturasDefecto()));
				}
			}catch (Exception e) {
				temp_img.setImage(new Image(Ejecutable.getOPciones().getFotoTemperaturasDefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoResiduosSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoResiduosSeleccionada()).exists()){
					res_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoResiduosSeleccionada()));
				}else{
					res_img.setImage(new Image(Ejecutable.getOPciones().getFotoResiduosDefecto()));
				}

			}catch (Exception e) {
				res_img.setImage(new Image(Ejecutable.getOPciones().getFotoResiduosDefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoMantenimientoSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoMantenimientoSeleccionada()).exists()){
					man_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoMantenimientoSeleccionada()));
				}else{
					man_img.setImage(new Image(Ejecutable.getOPciones().getFotoMedidasDefecto()));
				}
			}catch (Exception e) {
				man_img.setImage(new Image(Ejecutable.getOPciones().getFotoMedidasDefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoTrazabilidadSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoTrazabilidadSeleccionada()).exists()){
					traza_img.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoTrazabilidadSeleccionada()));
				}else{
					traza_img.setImage(new Image(Ejecutable.getOPciones().getFototrazavilidaddefecto()));
				}
			}catch (Exception e) {
				traza_img.setImage(new Image(Ejecutable.getOPciones().getFototrazavilidaddefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoFacturacionSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoFacturacionSeleccionada()).exists()){
					facturacion.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoFacturacionSeleccionada()));
				}else{
					facturacion.setImage(new Image(Ejecutable.getOPciones().getFotofacturaciondefecto()));
				}
			}catch (Exception e) {
				facturacion.setImage(new Image(Ejecutable.getOPciones().getFotofacturaciondefecto()));
			}
			try{
				if(Ejecutable.getOPciones().getRutaFotoProduccionSeleccionada()!=null&& new File(Ejecutable.getOPciones().getRutaFotoProduccionSeleccionada()).exists()){
					produccion.setImage(new Image("file:///"+Ejecutable.getOPciones().getRutaFotoProduccionSeleccionada()));
				}else{
					produccion.setImage(new Image(Ejecutable.getOPciones().getFotoproducciondefecto()));
				}
			}catch (Exception e) {
				produccion.setImage(new Image(Ejecutable.getOPciones().getFotoproducciondefecto()));
			}

		}else{
			agua_img.setImage(new Image(Ejecutable.getOPciones().getFotoAguaDefecto()));
			carne_img.setImage(new Image(Ejecutable.getOPciones().getFotoProductosCarnicosDefecto()));
			limp_img.setImage(new Image(Ejecutable.getOPciones().getFotoLimpiezaDefecto()));
			temp_img.setImage(new Image(Ejecutable.getOPciones().getFotoTemperaturasDefecto()));
			res_img.setImage(new Image(Ejecutable.getOPciones().getFotoResiduosDefecto()));
			man_img.setImage(new Image(Ejecutable.getOPciones().getFotoMedidasDefecto()));
			traza_img.setImage(new Image(Ejecutable.getOPciones().getFototrazavilidaddefecto()));
			facturacion.setImage(new Image(Ejecutable.getOPciones().getFotofacturaciondefecto()));
			produccion.setImage(new Image(Ejecutable.getOPciones().getFotoproducciondefecto()));
		}


	}


	@FXML
	private void registro_agua(){
		try {
			App.setRoot(Escenas.AGUA.getUrl());
			App.CambiarTitulo("REGISTRO AGUA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void Ajustes(){
		try {
			App.setRoot(Escenas.AJUSTES.getUrl());
			App.CambiarTitulo("AJUSTES");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void registro_temp(){
		try {
			App.setRoot(Escenas.TEMPERATURA.getUrl());
			App.CambiarTitulo("REGISTRO TEMPERATURAS");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void registro_carne(){
		try {
			App.setRoot(Escenas.SANIDAD.getUrl());
			App.CambiarTitulo("REGISTRO CARNE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void registro_limpieza(){
		try {
			App.setRoot(Escenas.LIMPIEZA.getUrl());
			App.CambiarTitulo("REGISTRO LIMPIEZA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void registro_residuos(){
		try {
			App.setRoot(Escenas.RESIDUOS.getUrl());
			App.CambiarTitulo("REGISTRO RESIDUOS");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void registro_mantenimiento(){
		try {
			App.setRoot(Escenas.MANTENIMIENTO.getUrl());
			App.CambiarTitulo("MEDIDAS PREVENTIVAS");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void registro_trazabilidad(){
		try {
			App.setRoot(Escenas.TRAZABILIDAD.getUrl());
			App.CambiarTitulo("TRAZABILIDAD CARNE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void registro_produccion(){
		try {
			App.setRoot(Escenas.PRODUCCION.getUrl());
			App.CambiarTitulo("REGISTRO PRODUCCIÓN");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void Cerrar(){
		ConecxionH2.CerrarAllConec();
		System.exit(0);
	}
	@FXML
	private void GuardarAgua(){
		EscribirArchivos.GuardarAgua();
	}
	@FXML
	private void GuardarCarne(){
		EscribirArchivos.GuardarRegistroCárnico();
	}
	@FXML
	private void GuardarTemperaturas(){
		EscribirArchivos.GuardarRegistroTemperaturas();
	}

	@FXML
	private void GuardarLimpieza(){
		EscribirArchivos.GuardarRegistroLimpieza();
	}
	@FXML
	private void GuardarResiduos(){
		EscribirArchivos.GuardarRegistroResiduos();
	}
	@FXML
	private void GuardarMedidasPrev(){
		EscribirArchivos.GuardarRegistroMedidasPrebentibas();
	}
	@FXML
	private void GuardarTrazabilidad(){
		EscribirArchivos.GuardarRegistroTrazSalsas();
		EscribirArchivos.GuardarRegistroTrazMateriaPrima();
		EscribirArchivos.GuardarRegistroTrazEnvases();
		EscribirArchivos.GuardarTrazabilidadCarne();
		EscribirArchivos.GuardarTrazabilidadLaure();
	}
	@FXML
	private void GuardarProduccion(){
		EscribirArchivos.GuardarRegistroProduccion();
	}
	@FXML
	private void GuardarTodo(){
		EscribirArchivos.GuardarRegistroMedidasPrebentibas();
		EscribirArchivos.GuardarRegistroResiduos();
		EscribirArchivos.GuardarRegistroLimpieza();
		EscribirArchivos.GuardarRegistroTemperaturas();
		EscribirArchivos.GuardarRegistroCárnico();
		EscribirArchivos.GuardarAgua();
		GuardarTrazabilidad();
		EscribirArchivos.GuardarRegistroProduccion();

	}

}
