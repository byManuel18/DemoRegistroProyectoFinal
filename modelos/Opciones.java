package modelos;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="OPCIONES")
public class Opciones {
	private static final String RutaDefectoXmlCreacionBasedeDatos="./src/resources/BaseDatos/ConexionesH2.xml";
	private static final String RutaDefectoGuardarTodosLosArchivos="./src";
	private static final String RutaDefectoGuardarTodosLosArchivos2="./src/resources";
	private static final String RutaDirectorioTXTTrazabilidad="./src/resources/TXT/TRAZABILIDAD";
	private static final String RutaDefectoGUaradoBaseDatosTXTTrazabilidadCarne="./src/resources/TXT/TRAZABILIDAD/CARNE";
	private static final String RutaDefectoGUaradoBaseDatosTXTTrazabilidadLaure="./src/resources/TXT/TRAZABILIDAD/LAURE";
	private static final String RutaDefectoGUaradoBaseDatosTXTTrazabilidadSalsas="./src/resources/TXT/TRAZABILIDAD/SALSAS";
	private static final String RutaDefectoGUaradoBaseDatosTXTTrazabilidadMateriaPrima="./src/resources/TXT/TRAZABILIDAD/MATERIA_PRIMA";
	private static final String RutaDefectoGUaradoBaseDatosTXTTrazabilidadEnvases="./src/resources/TXT/TRAZABILIDAD/ENVASES";
	private static final String RutaDefectoGUaradoBaseDatos="./src/resources/BaseDatos";
	private static final String RutaDefectoGUaradoBaseDatosTXT="./src/resources/TXT";
	private static final String RutaDefectoGUaradoBaseDatosTXTAgua="./src/resources/TXT/AGUA";
	private static final String RutaDefectoGUaradoBaseDatosTXTProductosCarnicos="./src/resources/TXT/PRODUCTOS_CARNICOS";
	private static final String RutaDefectoGUaradoBaseDatosTXTTemperaturas="./src/resources/TXT/TEMPERATURAS";
	private static final String RutaDefectoGUaradoBaseDatosTXTLimpieza="./src/resources/TXT/LIMPIEZA";
	private static final String RutaDefectoGUaradoBaseDatosTXTResiduos="./src/resources/TXT/RESIDUOS";
	private static final String RutaDefectoGUaradoBaseDatosTXTMedidas="./src/resources/TXT/MEDIDAS";
	private static final String RutaDefectoGUaradoBaseDatosTXTMedidasPrebentibas="./src/resources/TXT/MEDIDAS/PREBENTIBAS";
	private static final String RutaDefectoGUaradoBaseDatosTXTMedidasMantenimiento="./src/resources/TXT/MEDIDAS/MANTENIMIENTO";
	private static final String RutaDefectoGUaradoBaseDatosTXTMedidasManipulador="./src/resources/TXT/MEDIDAS/MANIPULADOR";
	private static final String RutaDefectoGUaradoBaseDatosTXTFacturacion="./src/resources/TXT/FACTURACION";
	private static final String RutaDefectoGUaradoBaseDatosTXTProduccion="./src/resources/TXT/PRODUCCION";
	private static final String FotoProgramaDefecto="/assets/LogoAplicacion.jpg";
	private static final String FotoProductosCarnicosDefecto="/assets/carne.png";
	private static final String FotoLimpiezaDefecto="/assets/limpieza.jpg";
	private static final String FotoResiduosDefecto="/assets/residuos.jpg";
	private static final String FotoAguaDefecto="/assets/agua.png";
	private static final String FotoTemperaturasDefecto="/assets/temperatura.jpg";
	private static final String FotoMedidasDefecto="/assets/Mantenimiento.png";
	private static final String FotoFacturacionDefecto="/assets/facturacion.png";
	private static final String FotoProduccionDefecto="/assets/produccion.png";
	private static final String FotoTrazavilidadDefecto="/assets/trazabilidad.jpg";
	private boolean primeracargar=true;
	private boolean guardartxtporDefecto=true;
	private boolean imagenesdefecto=true;
	private boolean basededatosdefecto=true;
	private String RutaTrazCarneSeleccionada;
	private String RutaTrazLaureSeleccionada;
	private String RutaTrazSalsasSeleccionada;
	private String RutaTrazMateriaPrimaSeleccionada;
	private String RutaTrazEnvasesSeleccionada;
	private String RutaAguaSeleccionada;
	private String RutaCarneSeleccionada;
	private String RutaTemperaturaSeleccionada;
	private String RutaLimpiezaSeleccionada;
	private String RutaManteniminetoSeleccionada;
	private String RutaResiduosSeleccionada;
	private String RutaTrazabilidadSeleccionada;
	private String RutaFacturacionSeleccionada;
	private String RutaproduccionSeleccionada;
	private String RutaFotoAguaSeleccionada;
	private String RutaFotoCarneSeleccionada;
	private String RutaFotoTemperaturaSeleccionada;
	private String RutaFotoLimpiezaSeleccionada;
	private String RutaFotoMantenimientoSeleccionada;
	private String RutaFotoResiduosSeleccionada;
	private String RutaFotoFacturacionSeleccionada;
	private String RutaFotoProduccionSeleccionada;
	private String RutaFotoTrazabilidadSeleccionada;
	private String RutaBaseDatosSeleccionada;




	public Opciones(){

	}






	public String getRutaBaseDatosSeleccionada() {
		return RutaBaseDatosSeleccionada;
	}






	public void setRutaBaseDatosSeleccionada(String rutaBaseDatosSeleccionada) {
		RutaBaseDatosSeleccionada = rutaBaseDatosSeleccionada;
	}






	public  String getRutadefectoguaradobasedatostxtmedidasprebentibas() {
		return RutaDefectoGUaradoBaseDatosTXTMedidasPrebentibas;
	}






	public  String getRutadefectoguaradobasedatostxtmedidasmantenimiento() {
		return RutaDefectoGUaradoBaseDatosTXTMedidasMantenimiento;
	}






	public  String getRutadefectoguaradobasedatostxtmedidasmanipulador() {
		return RutaDefectoGUaradoBaseDatosTXTMedidasManipulador;
	}






	public String getRutaproduccionSeleccionada() {
		return RutaproduccionSeleccionada;
	}






	public void setRutaproduccionSeleccionada(String rutaproduccionSeleccionada) {
		RutaproduccionSeleccionada = rutaproduccionSeleccionada;
	}






	public  String getRutadefectoguaradobasedatostxtproduccion() {
		return RutaDefectoGUaradoBaseDatosTXTProduccion;
	}






	public String getRutaTrazCarneSeleccionada() {
		return RutaTrazCarneSeleccionada;
	}





	public void setRutaTrazCarneSeleccionada(String rutaTrazCarneSeleccionada) {
		RutaTrazCarneSeleccionada = rutaTrazCarneSeleccionada;
	}





	public String getRutaTrazLaureSeleccionada() {
		return RutaTrazLaureSeleccionada;
	}





	public void setRutaTrazLaureSeleccionada(String rutaTrazLaureSeleccionada) {
		RutaTrazLaureSeleccionada = rutaTrazLaureSeleccionada;
	}





	public String getRutaTrazSalsasSeleccionada() {
		return RutaTrazSalsasSeleccionada;
	}





	public void setRutaTrazSalsasSeleccionada(String rutaTrazSalsasSeleccionada) {
		RutaTrazSalsasSeleccionada = rutaTrazSalsasSeleccionada;
	}





	public String getRutaTrazMateriaPrimaSeleccionada() {
		return RutaTrazMateriaPrimaSeleccionada;
	}





	public void setRutaTrazMateriaPrimaSeleccionada(String rutaTrazMateriaPrimaSeleccionada) {
		RutaTrazMateriaPrimaSeleccionada = rutaTrazMateriaPrimaSeleccionada;
	}





	public String getRutaTrazEnvasesSeleccionada() {
		return RutaTrazEnvasesSeleccionada;
	}





	public void setRutaTrazEnvasesSeleccionada(String rutaTrazEnvasesSeleccionada) {
		RutaTrazEnvasesSeleccionada = rutaTrazEnvasesSeleccionada;
	}





	public  String getRutadirectoriotxttrazabilidad() {
		return RutaDirectorioTXTTrazabilidad;
	}





	public  String getRutadefectoguaradobasedatostxttrazabilidadcarne() {
		return RutaDefectoGUaradoBaseDatosTXTTrazabilidadCarne;
	}





	public  String getRutadefectoguaradobasedatostxttrazabilidadlaure() {
		return RutaDefectoGUaradoBaseDatosTXTTrazabilidadLaure;
	}





	public  String getRutadefectoguaradobasedatostxttrazabilidadsalsas() {
		return RutaDefectoGUaradoBaseDatosTXTTrazabilidadSalsas;
	}





	public  String getRutadefectoguaradobasedatostxttrazabilidadmateriaprima() {
		return RutaDefectoGUaradoBaseDatosTXTTrazabilidadMateriaPrima;
	}





	public  String getRutadefectoguaradobasedatostxttrazabilidadenvases() {
		return RutaDefectoGUaradoBaseDatosTXTTrazabilidadEnvases;
	}





	public String getRutaFotoProduccionSeleccionada() {
		return RutaFotoProduccionSeleccionada;
	}





	public void setRutaFotoProduccionSeleccionada(String rutaFotoProduccionSeleccionada) {
		RutaFotoProduccionSeleccionada = rutaFotoProduccionSeleccionada;
	}





	public String getRutaFotoTrazabilidadSeleccionada() {
		return RutaFotoTrazabilidadSeleccionada;
	}





	public void setRutaFotoTrazabilidadSeleccionada(String rutaFotoTrazabilidadSeleccionada) {
		RutaFotoTrazabilidadSeleccionada = rutaFotoTrazabilidadSeleccionada;
	}





	public  String getFotoproducciondefecto() {
		return FotoProduccionDefecto;
	}





	public  String getFototrazavilidaddefecto() {
		return FotoTrazavilidadDefecto;
	}





	public boolean isGuardartxtporDefecto() {
		return guardartxtporDefecto;
	}





	public void setGuardartxtporDefecto(boolean guardartxtporDefecto) {
		this.guardartxtporDefecto = guardartxtporDefecto;
	}





	public boolean isBasededatosdefecto() {
		return basededatosdefecto;
	}



	public void setBasededatosdefecto(boolean basededatosdefecto) {
		this.basededatosdefecto = basededatosdefecto;
	}



	public String getRutaFotoAguaSeleccionada() {
		return RutaFotoAguaSeleccionada;
	}



	public void setRutaFotoAguaSeleccionada(String rutaFotoAguaSeleccionada) {
		RutaFotoAguaSeleccionada = rutaFotoAguaSeleccionada;
	}



	public String getRutaFotoCarneSeleccionada() {
		return RutaFotoCarneSeleccionada;
	}



	public void setRutaFotoCarneSeleccionada(String rutaFotoCarneSeleccionada) {
		RutaFotoCarneSeleccionada = rutaFotoCarneSeleccionada;
	}



	public String getRutaFotoTemperaturaSeleccionada() {
		return RutaFotoTemperaturaSeleccionada;
	}



	public void setRutaFotoTemperaturaSeleccionada(String rutaFotoTemperaturaSeleccionada) {
		RutaFotoTemperaturaSeleccionada = rutaFotoTemperaturaSeleccionada;
	}



	public String getRutaFotoLimpiezaSeleccionada() {
		return RutaFotoLimpiezaSeleccionada;
	}



	public void setRutaFotoLimpiezaSeleccionada(String rutaFotoLimpiezaSeleccionada) {
		RutaFotoLimpiezaSeleccionada = rutaFotoLimpiezaSeleccionada;
	}



	public String getRutaFotoMantenimientoSeleccionada() {
		return RutaFotoMantenimientoSeleccionada;
	}



	public void setRutaFotoMantenimientoSeleccionada(String rutaFotoMantenimientoSeleccionada) {
		RutaFotoMantenimientoSeleccionada = rutaFotoMantenimientoSeleccionada;
	}



	public String getRutaFotoResiduosSeleccionada() {
		return RutaFotoResiduosSeleccionada;
	}



	public void setRutaFotoResiduosSeleccionada(String rutaFotoResiduosSeleccionada) {
		RutaFotoResiduosSeleccionada = rutaFotoResiduosSeleccionada;
	}


	public String getRutaFotoFacturacionSeleccionada() {
		return RutaFotoFacturacionSeleccionada;
	}



	public void setRutaFotoFacturacionSeleccionada(String rutaFotoFacturacionSeleccionada) {
		RutaFotoFacturacionSeleccionada = rutaFotoFacturacionSeleccionada;
	}



	public boolean isImagenesdefecto() {
		return imagenesdefecto;
	}



	public void setImagenesdefecto(boolean imagenesdefecto) {
		this.imagenesdefecto = imagenesdefecto;
	}



	public String getRutaAguaSeleccionada() {
		return RutaAguaSeleccionada;
	}



	public void setRutaAguaSeleccionada(String rutaAguaSeleccionada) {
		RutaAguaSeleccionada = rutaAguaSeleccionada;
	}



	public String getRutaCarneSeleccionada() {
		return RutaCarneSeleccionada;
	}



	public void setRutaCarneSeleccionada(String rutaCarneSeleccionada) {
		RutaCarneSeleccionada = rutaCarneSeleccionada;
	}



	public String getRutaTemperaturaSeleccionada() {
		return RutaTemperaturaSeleccionada;
	}



	public void setRutaTemperaturaSeleccionada(String rutaTemperaturaSeleccionada) {
		RutaTemperaturaSeleccionada = rutaTemperaturaSeleccionada;
	}



	public String getRutaLimpiezaSeleccionada() {
		return RutaLimpiezaSeleccionada;
	}



	public void setRutaLimpiezaSeleccionada(String rutaLimpiezaSeleccionada) {
		RutaLimpiezaSeleccionada = rutaLimpiezaSeleccionada;
	}



	public String getRutaManteniminetoSeleccionada() {
		return RutaManteniminetoSeleccionada;
	}



	public void setRutaManteniminetoSeleccionada(String rutaManteniminetoSeleccionada) {
		RutaManteniminetoSeleccionada = rutaManteniminetoSeleccionada;
	}



	public String getRutaResiduosSeleccionada() {
		return RutaResiduosSeleccionada;
	}



	public void setRutaResiduosSeleccionada(String rutaResiduosSeleccionada) {
		RutaResiduosSeleccionada = rutaResiduosSeleccionada;
	}



	public String getRutaTrazabilidadSeleccionada() {
		return RutaTrazabilidadSeleccionada;
	}



	public void setRutaTrazabilidadSeleccionada(String rutaTrazabilidadSeleccionada) {
		RutaTrazabilidadSeleccionada = rutaTrazabilidadSeleccionada;
	}



	public String getRutaFacturacionSeleccionada() {
		return RutaFacturacionSeleccionada;
	}



	public void setRutaFacturacionSeleccionada(String rutaFacturacionSeleccionada) {
		RutaFacturacionSeleccionada = rutaFacturacionSeleccionada;
	}


	public  String getRutadefectoguaradobasedatostxtfacturacion() {
		return RutaDefectoGUaradoBaseDatosTXTFacturacion;
	}



	public  String getFotofacturaciondefecto() {
		return FotoFacturacionDefecto;
	}

	public boolean isPrimeracargar() {
		return primeracargar;
	}

	public void setPrimeracargar(boolean primeracargar) {
		this.primeracargar = primeracargar;
	}

	public  String getRutaDefectoXmlCreacionBasedeDatos() {
		return RutaDefectoXmlCreacionBasedeDatos;
	}

	public  String getRutaDefectoGuardarTodosLosArchivos() {
		return RutaDefectoGuardarTodosLosArchivos;
	}

	public  String getRutaDefectoGuardarTodosLosArchivos2() {
		return RutaDefectoGuardarTodosLosArchivos2;
	}

	public  String getRutaDefectoGUaradoBaseDatos() {
		return RutaDefectoGUaradoBaseDatos;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXT() {
		return RutaDefectoGUaradoBaseDatosTXT;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXTAgua() {
		return RutaDefectoGUaradoBaseDatosTXTAgua;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXTProductosCarnicos() {
		return RutaDefectoGUaradoBaseDatosTXTProductosCarnicos;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXTTemperaturas() {
		return RutaDefectoGUaradoBaseDatosTXTTemperaturas;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXTLimpieza() {
		return RutaDefectoGUaradoBaseDatosTXTLimpieza;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXTResiduos() {
		return RutaDefectoGUaradoBaseDatosTXTResiduos;
	}

	public  String getRutaDefectoGUaradoBaseDatosTXTMedidas() {
		return RutaDefectoGUaradoBaseDatosTXTMedidas;
	}

	public  String getFotoProgramaDefecto() {
		return FotoProgramaDefecto;
	}

	public  String getFotoProductosCarnicosDefecto() {
		return FotoProductosCarnicosDefecto;
	}

	public  String getFotoLimpiezaDefecto() {
		return FotoLimpiezaDefecto;
	}

	public  String getFotoResiduosDefecto() {
		return FotoResiduosDefecto;
	}

	public String getFotoAguaDefecto() {
		return FotoAguaDefecto;
	}

	public  String getFotoTemperaturasDefecto() {
		return FotoTemperaturasDefecto;
	}

	public  String getFotoMedidasDefecto() {
		return FotoMedidasDefecto;
	}





}
