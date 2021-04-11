package modelos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.CorreIncorre;
import enums.Estado;
import enums.SentenciasMantenimiento;
import utilidades.ConecxionH2;

public class MantenimientoDAO extends Mantenimiento {

	public MantenimientoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MantenimientoDAO(int id_mantenimiento, LocalDate fecha, CorreIncorre suelo, CorreIncorre paredes,
			CorreIncorre techo, CorreIncorre electricidad, CorreIncorre fontan,
			CorreIncorre operaciones_a_equipos_y_utensilios, CorreIncorre calibracion_equipo_de_frio,
			CorreIncorre revision_balanzas, Estado firmado) {
		super(id_mantenimiento, fecha, suelo, paredes, techo, electricidad, fontan, operaciones_a_equipos_y_utensilios,
				calibracion_equipo_de_frio, revision_balanzas, firmado);
		// TODO Auto-generated constructor stub
	}

	public MantenimientoDAO(LocalDate fecha, CorreIncorre suelo, CorreIncorre paredes, CorreIncorre techo,
			CorreIncorre electricidad, CorreIncorre fontan, CorreIncorre operaciones_a_equipos_y_utensilios,
			CorreIncorre calibracion_equipo_de_frio, CorreIncorre revision_balanzas, Estado firmado) {
		super(fecha, suelo, paredes, techo, electricidad, fontan, operaciones_a_equipos_y_utensilios,
				calibracion_equipo_de_frio, revision_balanzas, firmado);
		// TODO Auto-generated constructor stub
	}

	public MantenimientoDAO(Mantenimiento m) {
		id_mantenimiento = m.id_mantenimiento;
		calibracion_equipo_de_frio = m.calibracion_equipo_de_frio;
		electricidad = m.electricidad;
		fecha = m.fecha;
		firmado = m.firmado;
		fontan = m.fontan;
		operaciones_a_equipos_y_utensilios = m.operaciones_a_equipos_y_utensilios;
		paredes = m.paredes;
		revision_balanzas = m.revision_balanzas;
		suelo = m.suelo;
		techo = m.techo;

	}

	public MantenimientoDAO(int id) {
		super();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMantenimiento.SELECTPORID.getSentencia());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					id_mantenimiento = id;
					calibracion_equipo_de_frio = CorreIncorre.valueOf(rs.getString("calibracion_equipo_frio"));
					electricidad = CorreIncorre.valueOf(rs.getString("electricidad"));
					if (rs.getDate("fecha") != null) {
						fecha = rs.getDate("fecha").toLocalDate();
					} else {
						fecha = null;
					}
					firmado = Estado.valueOf(rs.getString("firmado"));
					fontan = CorreIncorre.valueOf(rs.getString("fontaneria"));
					operaciones_a_equipos_y_utensilios = CorreIncorre
							.valueOf(rs.getString("operaciones_realizadas_a_equipos_y_utensilios"));
					paredes = CorreIncorre.valueOf(rs.getString("paredes"));
					revision_balanzas = CorreIncorre.valueOf(rs.getString("revision_balanzas"));
					suelo = CorreIncorre.valueOf(rs.getString("suelo"));
					techo = CorreIncorre.valueOf(rs.getString("techos"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int remuve() {
		int resultado = -1;
		PreparedStatement ps = null;
		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMantenimiento.DELETE.getSentencia());
			ps.setInt(1, id_mantenimiento);
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public int save() {
		int resultado = -1;
		PreparedStatement ps = null;
		if (id_mantenimiento > 0) {
			try {
				ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMantenimiento.UPDATE.getSentencia());
				if (fecha != null) {
					ps.setDate(1, Date.valueOf(fecha));
				} else {
					ps.setDate(1, null);
				}
				ps.setString(2, suelo.toString());
				ps.setString(3, paredes.toString());
				ps.setString(4, techo.toString());
				ps.setString(5, electricidad.toString());
				ps.setString(6, fontan.toString());
				ps.setString(7, operaciones_a_equipos_y_utensilios.toString());
				ps.setString(8, calibracion_equipo_de_frio.toString());
				ps.setString(9, revision_balanzas.toString());
				ps.setString(10, firmado.toString());
				ps.setInt(11, id_mantenimiento);
				resultado = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMantenimiento.INSERT.getSentencia());
				if (fecha != null) {
					ps.setDate(1, Date.valueOf(fecha));
				} else {
					ps.setDate(1, null);
				}
				ps.setString(2, suelo.toString());
				ps.setString(3, paredes.toString());
				ps.setString(4, techo.toString());
				ps.setString(5, electricidad.toString());
				ps.setString(6, fontan.toString());
				ps.setString(7, operaciones_a_equipos_y_utensilios.toString());
				ps.setString(8, calibracion_equipo_de_frio.toString());
				ps.setString(9, revision_balanzas.toString());
				ps.setString(10, firmado.toString());
				resultado = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return resultado;
	}

	public static List<Mantenimiento> SelectALL(){
		return Select("", SentenciasMantenimiento.SELCTALL);
	}

	public static List<Mantenimiento> SelectPorFecha(String cadena){
		return Select(cadena, SentenciasMantenimiento.SELECPORFECHA);
	}

	public static List<Mantenimiento> SelectPorMes(String cadena){
		return Select(cadena, SentenciasMantenimiento.SELECTPORMES);
	}

	private static List<Mantenimiento> Select(String cadena, SentenciasMantenimiento sql) {
		List<Mantenimiento> lista = new ArrayList<Mantenimiento>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(sql.getSentencia());
			if(sql==SentenciasMantenimiento.SELECPORFECHA){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasMantenimiento.SELECTPORMES){
				ps.setInt(1,Integer.parseInt(cadena));
			}

			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					LocalDate fe = null;
					if (rs.getDate("fecha") != null) {
						fe = rs.getDate("fecha").toLocalDate();
					}
					Mantenimiento add = new Mantenimiento(rs.getInt("id"), fe,
							CorreIncorre.valueOf(rs.getString("suelo")), CorreIncorre.valueOf(rs.getString("paredes")),
							CorreIncorre.valueOf(rs.getString("techos")),
							CorreIncorre.valueOf(rs.getString("electricidad")),
							CorreIncorre.valueOf(rs.getString("fontaneria")),
							CorreIncorre.valueOf(rs.getString("operaciones_realizadas_a_equipos_y_utensilios")),
							CorreIncorre.valueOf(rs.getString("calibracion_equipo_frio")),
							CorreIncorre.valueOf(rs.getString("revision_balanzas")),
							Estado.valueOf(rs.getString("firmado")));

					lista.add(add);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lista;
	}

}
