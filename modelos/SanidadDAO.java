package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import enums.Estado;
import enums.SelectPorSanidad;
import enums.SentenciasSanidad;
import utilidades.ConecxionH2;

public class SanidadDAO extends SanidadProducto {


	public SanidadDAO() {
		super(LocalDate.now(), "", "", Estado.NO_FIRMADO, "", -1);

	}

	public SanidadDAO(LocalDate fecha, String nombre_producto, String proveedor, Estado estado,
			String codigolote, int id) {
		super(fecha, nombre_producto, proveedor, estado, codigolote, id);

	}

	public SanidadDAO(LocalDate fecha, String nombre_producto, String proveedor, Estado estado,
			String codigolote) {
		super(fecha, nombre_producto, proveedor, estado, codigolote);

	}

	public SanidadDAO(SanidadProducto sn) {
		id = sn.id;
		fecha = sn.fecha;
		nombre_producto = sn.nombre_producto;
		proveedor = sn.proveedor;
		estado = sn.estado;
		codigolote = sn.codigolote;

	}

	public SanidadDAO(int id) {
		super();

		Connection consa = ConecxionH2.getConnecSanidad();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = consa.prepareStatement(SentenciasSanidad.SELECTSANIDAD.getSenten());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					this.id = id;
					Date d = rs.getDate("fecha");
					this.fecha = d.toLocalDate();
					this.nombre_producto = rs.getString("producto");
					this.codigolote = rs.getString("lote");
					this.proveedor = rs.getString("proveedor");
					this.estado = Estado.valueOf(rs.getString("firmado"));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}


	public int save() {
		int resultado = -1;
		PreparedStatement ps = null;
		if (this.id > 0) {
			try {
				ps = ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSanidad.UPDATESQANIDAD.getSenten());
				ps.setDate(1, Date.valueOf(this.fecha));
				ps.setString(2, this.getProveedor());
				ps.setString(3, this.getNombre_producto());
				ps.setString(4, this.getEstado().toString());
				ps.setString(5, this.getCodigolote());
				ps.setInt(6, this.id);
				resultado = ps.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				ps = ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSanidad.INSERTSANIDAD.getSenten());
				Date sqldate = Date.valueOf(this.fecha);
				ps.setDate(1, sqldate);
				ps.setString(2, this.proveedor);
				ps.setString(3, this.nombre_producto);
				ps.setString(4, this.estado.toString());
				ps.setString(5, this.codigolote);
				resultado = ps.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		}

		return resultado;
	}

	public int delete() {
		int resultado = -1;

		if (this.id > 0) {
			PreparedStatement ps = null;
			try {
				ps = ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSanidad.REMUVESANIDAD.getSenten());
				ps.setInt(1, this.id);
				resultado = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}

		}

		return resultado;
	}

	public static List<SanidadProducto> SelecAll() {
		return SeleccionarPor("","", SelectPorSanidad.ALL);
	}

	public static List<SanidadProducto> SelecByNombreproducto(String cadena) {
		return SeleccionarPor(cadena,"", SelectPorSanidad.NOMBRE_PRODUCTO);
	}

	public static List<SanidadProducto> SelecByProveedor(String cadena) {
		return SeleccionarPor(cadena,"", SelectPorSanidad.PROVEEDOR);
	}

	public static List<SanidadProducto> SelecByFecha(String cadena) {
		return SeleccionarPor(cadena,"", SelectPorSanidad.FECHA);
	}

	public static List<SanidadProducto> SelecEntreDosFechas(String cadena) {
		return SeleccionarPor(cadena,"", SelectPorSanidad.ENTREDOSFECHAS);
	}
	public static List<SanidadProducto> SelecPorMes(String cadena) {
		return SeleccionarPor(cadena,"", SelectPorSanidad.PORMES);
	}
	public static List<SanidadProducto> SelecPorAño(String cadena) {
		return SeleccionarPor(cadena,"", SelectPorSanidad.PORAÑO);
	}
	public static List<SanidadProducto> SelecPorMesyAño(String mes,String año) {
		return SeleccionarPor(mes,año, SelectPorSanidad.PORAÑOYMES);
	}

	private static List<SanidadProducto> SeleccionarPor(String cadena,String cadena2, SelectPorSanidad by) {
		List<SanidadProducto> lista = new ArrayList<SanidadProducto>();
		String sentencia = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		switch (by) {
		case NOMBRE_PRODUCTO:
			sentencia = SentenciasSanidad.SELECTSANIDADBYNOMBRE.getSenten();
			break;
		case PROVEEDOR:
			sentencia = SentenciasSanidad.SELECTSANIDADBYPROVEEDOR.getSenten();
			break;
		case FECHA:
			sentencia = SentenciasSanidad.SELECTSANIDADBYDATE.getSenten();
			break;
		case ENTREDOSFECHAS:
			sentencia= SentenciasSanidad.SELECTSANIDADENTREDOSFECHAS.getSenten();
			break;
		case PORMES:
			sentencia=SentenciasSanidad.SLECTPORMES.getSenten();
			break;
		case PORAÑO:
			sentencia=SentenciasSanidad.SLECTPORAÑO.getSenten();
			break;
		case PORAÑOYMES:
			sentencia=SentenciasSanidad.SLECTPORAÑOYMES.getSenten();
			break;
		default:
			sentencia = SentenciasSanidad.SLECTSANIDADALL.getSenten();
			break;
		}

		try {
			ps = ConecxionH2.getConnecSanidad().prepareStatement(sentencia);
			if(by==SelectPorSanidad.ENTREDOSFECHAS){
				ps.setString(1, cadena+"-01-01");
				ps.setString(2, cadena+"-12-31");
			}else if(by==SelectPorSanidad.PORMES){
					ps.setInt(1, Integer.parseInt(cadena));
				}else if(by == SelectPorSanidad.FECHA){
					ps.setString(1, cadena);
				}else if(by==SelectPorSanidad.NOMBRE_PRODUCTO||by==SelectPorSanidad.PROVEEDOR){
					ps.setString(1, cadena + "%");
				}else if(by==SelectPorSanidad.PORAÑO){
					ps.setInt(1, Integer.parseInt(cadena));
				}else if(by==SelectPorSanidad.PORAÑOYMES){
					ps.setInt(1, Integer.parseInt(cadena));
					ps.setInt(2, Integer.parseInt(cadena2));
				}

			/*
			 * {

				if(by==SelectPorSanidad.PORMES){
					ps = ConecxionH2.getConnecSanidad().prepareStatement(sentencia);
					ps.setInt(1, Integer.parseInt(cadena));
				}else{
					if (by != SelectPorSanidad.ALL) {
						ps = ConecxionH2.getConnecSanidad().prepareStatement(sentencia);
						if (by != SelectPorSanidad.FECHA) {
							ps.setString(1, cadena + "%");
						} else {
							ps.setString(1, cadena);
						}

					} else {
						ps = ConecxionH2.getConnecSanidad().prepareStatement(sentencia);
					}
				}

			}
			 */


			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SanidadProducto nuevo = new SanidadProducto(rs.getDate("fecha").toLocalDate(),
							rs.getString("producto"), rs.getString("proveedor"),
							Estado.valueOf(rs.getString("firmado")), rs.getString("lote"),
							rs.getInt("id"));
					lista.add(nuevo);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return lista;
	}

}
