package clasemain;

import modelos.Opciones;
import utilidades.ConecxionH2;
import utilidades.EscribirArchivos;
import utilidades.XmlUtilidades;

public class Ejecutable {

	private static Opciones opciones=null;

	public static void main(String[] args) {
		opciones=XmlUtilidades.LeerOpcionesOCrearlas();
		EscribirArchivos.CrearCarpetasSources();
		App.main(args);
		ConecxionH2.CerrarAllConec();

	}

	public static Opciones getOPciones(){
		return opciones;
	}
}
