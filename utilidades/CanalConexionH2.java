package utilidades;

import java.util.ArrayList;
import java.util.List;

public class CanalConexionH2 {

	private String nombre_base;
	private String driver;
	private String conector_bae;
	private List<String> crearcion_tablas;

	public CanalConexionH2(String nombre_base, String driver, String conector_bae) {
		crearcion_tablas=new ArrayList<String>();
		this.nombre_base = nombre_base;
		this.driver = driver;
		this.conector_bae = conector_bae;
	}

	@SuppressWarnings("unused")
	private CanalConexionH2() {

	}

	public void AñadirSentencia(String sentencia){
		crearcion_tablas.add(sentencia);
	}

	public String getNombre_base() {
		return nombre_base;
	}

	public void setNombre_base(String nombre_base) {
		this.nombre_base = nombre_base;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getConector_bae() {
		return conector_bae;
	}

	public void setConector_bae(String conector_bae) {
		this.conector_bae = conector_bae;
	}

	public List<String> getCrearcion_tablas() {
		return crearcion_tablas;
	}

	public void setCrearcion_tablas(List<String> crearcion_tablas) {
		this.crearcion_tablas = crearcion_tablas;
	}

	@Override
	public String toString() {
		return "CanalConexionH2 [nombre_base=" + nombre_base + ", driver=" + driver + ", conector_bae=" + conector_bae
				+ ", crearcion_tablas=" + crearcion_tablas + "]";
	}




}
