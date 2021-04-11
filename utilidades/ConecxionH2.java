package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ConecxionH2{
	private static Connection coneccionsanidad = null;
	private static Connection coneccionproductos = null;
	private static Connection coneccionproduccion = null;
	private static Connection conectionLimpieza_Agua_Temperatura_Residuos= null;
	private static Connection conectionMedidas=null;
	private static CanalConexionH2 sanidad = null;
	private static CanalConexionH2 productos = null;
	private static CanalConexionH2 produccion = null;
	private static CanalConexionH2 Limpieza_Agua_Temperatura_Residuos=null;
	private static CanalConexionH2 medidas=null;


	private static void CargarDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	private static void CargarCanales() {

		if (sanidad == null || productos == null) {
			List<CanalConexionH2> canales = XmlUtilidades.LeerCanales();
			for (CanalConexionH2 c : canales) {
				if(sanidad==null||productos==null||produccion==null||Limpieza_Agua_Temperatura_Residuos==null||medidas==null){
					if (c.getNombre_base().toUpperCase().equals("SANIDAD")) {
						sanidad = c;
					}

					if (c.getNombre_base().toUpperCase().equals("PRODUCTOS")) {
						productos = c;
					}

					if (c.getNombre_base().toUpperCase().equals("PRODUCCION")) {
						produccion = c;
					}

					if (c.getNombre_base().toUpperCase().equals("MANTENIMIENTO")) {
						Limpieza_Agua_Temperatura_Residuos = c;
					}
					if (c.getNombre_base().toUpperCase().equals("MEDIDAS")) {
						medidas = c;
					}
				}

			}
		}

	}

	private static Connection connectarBBDDSanidad() {
		Connection con = null;
		CargarCanales();
		CargarDriver(sanidad.getDriver());
		try {
			con = DriverManager.getConnection(sanidad.getConector_bae());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	private static Connection connectarBBDDProductos() {
		Connection con = null;
		CargarCanales();
		CargarDriver(productos.getDriver());
		try {
			con = DriverManager.getConnection(productos.getConector_bae());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	private static Connection connectarBBDDProduccion(){
		Connection con=null;
		CargarCanales();
		CargarDriver(produccion.getDriver());
		try {
			con = DriverManager.getConnection(produccion.getConector_bae());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	private static Connection connectarBBDDMedidas(){
		Connection con=null;
		CargarCanales();
		CargarDriver(medidas.getDriver());
		try {
			con = DriverManager.getConnection(medidas.getConector_bae());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	private static Connection connectarBBDDLimpieza_Agua_Temperatura_Residuos(){
		Connection con=null;
		CargarCanales();
		CargarDriver(Limpieza_Agua_Temperatura_Residuos.getDriver());
		try {
			con = DriverManager.getConnection(Limpieza_Agua_Temperatura_Residuos.getConector_bae());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getConnecSanidad() {
		if (coneccionsanidad == null) {
			coneccionsanidad = connectarBBDDSanidad();
			crearTablasSanidadSiNoExist();
		}

		return coneccionsanidad;
	}

	public static Connection getConnecProductosd() {
		if (coneccionproductos == null) {
			coneccionproductos = connectarBBDDProductos();
			crearTablasProductosiNoExist();
		}

		return coneccionproductos;
	}

	public static Connection getConnecProduccion() {
		if (coneccionproduccion == null) {
			coneccionproduccion = connectarBBDDProduccion();
			crearTablasGeneral(coneccionproduccion, produccion);
		}

		return coneccionproduccion;
	}

	public static Connection getConnecMedidas() {
		if (conectionMedidas == null) {
			conectionMedidas = connectarBBDDMedidas();
			crearTablasGeneral(conectionMedidas, medidas);
		}

		return conectionMedidas;
	}

	public static Connection getConnecLimpieza_Agua_Temperatura_Residuos() {
		if (conectionLimpieza_Agua_Temperatura_Residuos == null) {
			conectionLimpieza_Agua_Temperatura_Residuos = connectarBBDDLimpieza_Agua_Temperatura_Residuos();
			crearTablasGeneral(conectionLimpieza_Agua_Temperatura_Residuos, Limpieza_Agua_Temperatura_Residuos);
		}

		return conectionLimpieza_Agua_Temperatura_Residuos;
	}

	public static void CerrarAllConec() {
		try {
			if (coneccionproductos != null) {
				coneccionproductos.close();
			}
			if (coneccionsanidad != null) {
				coneccionsanidad.close();
			}
			if(coneccionproduccion!=null){
				coneccionproduccion.close();
			}
			if(conectionLimpieza_Agua_Temperatura_Residuos!=null){
				conectionLimpieza_Agua_Temperatura_Residuos.close();
			}
			if(conectionMedidas!=null){
				conectionMedidas.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void crearTablasSanidadSiNoExist() {

		try {
			coneccionsanidad.setAutoCommit(false);
			for (String se : sanidad.getCrearcion_tablas()) {
				PreparedStatement ps = coneccionsanidad.prepareStatement(se);
				ps.executeUpdate();
				ps.close();
			}
			coneccionsanidad.commit();
			coneccionsanidad.setAutoCommit(true);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void crearTablasProductosiNoExist() {

		try {
			coneccionproductos.setAutoCommit(false);
			for (String se : productos.getCrearcion_tablas()) {
				PreparedStatement ps = coneccionproductos.prepareStatement(se);
				ps.executeUpdate();
				ps.close();
			}
			coneccionproductos.commit();
			coneccionproductos.setAutoCommit(true);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void crearTablasGeneral(Connection con, CanalConexionH2 canal){
		try {
			con.setAutoCommit(false);
			for (String se : canal.getCrearcion_tablas()) {
				PreparedStatement ps = con.prepareStatement(se);
				ps.executeUpdate();
				ps.close();
			}
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
