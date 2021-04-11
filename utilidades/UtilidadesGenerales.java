package utilidades;

import  java.util.regex.Matcher ;
import  java.util.regex.Pattern ;

public class UtilidadesGenerales {
	public static boolean ValidarIntroducirTemp(String cadena){
		boolean coincide=false;
		Pattern pat = Pattern.compile("[+|-]?+\\d+[.]?+\\d+(ºC)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	coincide=true;

        }
		return coincide;
	}

	public static String ValidarIntroducirTempSolosimbolo(String cadena){
		String coincide="ºC";
		Pattern pat = Pattern.compile("([+|-]?+(\\d)*+[.]?+(\\d)*)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	cadena+=coincide;

        }
		return cadena;
	}
	public static boolean ValidarIntroducirCantidadResiduos(String cadena){
		boolean coincide=false;
		Pattern pat = Pattern.compile("\\d+[.]?+\\d+( Kg)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	coincide=true;

        }
		return coincide;
	}

	public static String PonerKg(String cadena){
		String coincide=" Kg";
		Pattern pat = Pattern.compile("((\\d)*+[.]?+(\\d)*)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	cadena+=coincide;

        }
		return cadena;
	}

	public static boolean ValidarNombreArchivoAgua(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Agua_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivoCarne(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Productos Carnicos_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivoTemperaturas(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Temperaturas_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivoLimpieza(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Limpieza_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorResiduos(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Residuos_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorMedidas(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Medidas Prebentibas_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorMedidasaMantenimiento(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Medidas Mantenimiento_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorMedidasaManipulador(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Registro Medidas Manipulador+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorTrazCarne(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Trazabilidad_Carne_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorTrazMateriaPrima(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Trazabilidad_Materia_Prima_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorTrazLaure(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Trazabilidad_Laure_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorTrazSalsas(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Trazabilidad_Salsas_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}

	public static boolean ValidarNombreArchivorTrazEnvases(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Trazabilidad_Envases_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static boolean ValidarNombreArchivorProduccion(String cadena){
		boolean bien =false;
		Pattern pat = Pattern.compile("^Produccion_+[0-9]*+(.txt)$");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
        	bien =true;
        }
		return bien;
	}
	public static int contarAños(String cadena){
		int resultado=-1;
		String numero="";
		Pattern pat = Pattern.compile("[0-9]");
		for(int i=0;i<cadena.length()-1;i++){
			Matcher mat = pat.matcher(Character.toString(cadena.charAt(i)));
			if(mat.matches()){
				numero+=Character.toString(cadena.charAt(i));
			}
		}
		resultado=Integer.parseInt(numero);
		return resultado;
	}

}
