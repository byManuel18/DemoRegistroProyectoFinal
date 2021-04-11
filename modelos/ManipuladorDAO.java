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
import enums.SentenciasManipulador;
import utilidades.ConecxionH2;

public class ManipuladorDAO extends Manipulador {

	public ManipuladorDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManipuladorDAO(int id_manipulador, LocalDate fecha, CorreIncorre vestimente_limpia,
			CorreIncorre higiene_personal, CorreIncorre buenas_practicas, CorreIncorre incidencias_acciones_correctas,
			Estado firmado) {
		super(id_manipulador, fecha, vestimente_limpia, higiene_personal, buenas_practicas,
				incidencias_acciones_correctas, firmado);
		// TODO Auto-generated constructor stub
	}

	public ManipuladorDAO(LocalDate fecha, CorreIncorre vestimente_limpia, CorreIncorre higiene_personal,
			CorreIncorre buenas_practicas, CorreIncorre incidencias_acciones_correctas, Estado firmado) {
		super(fecha, vestimente_limpia, higiene_personal, buenas_practicas, incidencias_acciones_correctas, firmado);
		// TODO Auto-generated constructor stub
	}

	public ManipuladorDAO(Manipulador m) {
		id_manipulador = m.id_manipulador;
		fecha = m.fecha;
		vestimente_limpia = m.vestimente_limpia;
		higiene_personal = m.higiene_personal;
		buenas_practicas = m.buenas_practicas;
		incidencias_acciones_correctas = m.incidencias_acciones_correctas;
		firmado = m.firmado;
	}

	public ManipuladorDAO(int id) {
		super();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasManipulador.SELECTPORID.getSentencia());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (ps != null) {
				if (rs.next()) {
					id_manipulador = id;
					if (rs.getDate("fecha") != null) {
						fecha = rs.getDate("fecha").toLocalDate();
					} else {
						fecha = null;
					}
					vestimente_limpia = CorreIncorre.valueOf(rs.getString("vestimienta_limpia"));
					higiene_personal = CorreIncorre.valueOf(rs.getString("higiene_personal"));
					buenas_practicas = CorreIncorre.valueOf(rs.getString("buenas_practicas"));
					incidencias_acciones_correctas = CorreIncorre
							.valueOf(rs.getString("incidencias_acciones_correctoras"));
					firmado = Estado.valueOf(rs.getString("firmado"));
				}
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public int save() {
		int resultado = -1;
		PreparedStatement ps = null;
		if (id_manipulador > 0) {
			try {
				ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasManipulador.UPDATE.getSentencia());
				if (fecha != null) {
					ps.setDate(1, Date.valueOf(fecha));
				} else {
					ps.setDate(1, null);
				}
				ps.setString(2, vestimente_limpia.toString());
				ps.setString(3, higiene_personal.toString());
				ps.setString(4, buenas_practicas.toString());
				ps.setString(5, incidencias_acciones_correctas.toString());
				ps.setString(6, firmado.toString());
				ps.setInt(7, id_manipulador);
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
				ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasManipulador.INSERT.getSentencia());
				if (fecha != null) {
					ps.setDate(1, Date.valueOf(fecha));
				} else {
					ps.setDate(1, null);
				}
				ps.setString(2, vestimente_limpia.toString());
				ps.setString(3, higiene_personal.toString());
				ps.setString(4, buenas_practicas.toString());
				ps.setString(5, incidencias_acciones_correctas.toString());
				ps.setString(6, firmado.toString());
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

	public int delete() {
		int resultado = -1;
		PreparedStatement ps = null;
		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasManipulador.DLETE.getSentencia());
			ps.setInt(1, id_manipulador);
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
	public static List<Manipulador> SelectAll(){
		return Select("", SentenciasManipulador.SELECTALL);
	}
	public static List<Manipulador> SelectFecha(String cadena){
		return Select(cadena, SentenciasManipulador.SELECPORFECHA);
	}
	public static List<Manipulador> SelectPorMes(String cadena){
		return Select(cadena, SentenciasManipulador.SELECTPORMES);
	}
	private static List<Manipulador> Select(String cadena, SentenciasManipulador sql) {
		List<Manipulador> lista = new ArrayList<Manipulador>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(sql.getSentencia());
			if(sql==SentenciasManipulador.SELECPORFECHA){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasManipulador.SELECTPORMES){

				ps.setInt(1, Integer.parseInt(cadena));
			}

			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					LocalDate fe = null;
					if (rs.getDate("fecha") != null) {
						fe = rs.getDate("fecha").toLocalDate();
					}
					Manipulador nuevo = new Manipulador(rs.getInt("id"), fe,
							CorreIncorre.valueOf(rs.getString("vestimienta_limpia")),
							CorreIncorre.valueOf(rs.getString("higiene_personal")),
							CorreIncorre.valueOf(rs.getString("buenas_practicas")),
							CorreIncorre.valueOf(rs.getString("incidencias_acciones_correctoras")),
							Estado.valueOf(rs.getString("firmado")));
					lista.add(nuevo);
				}
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return lista;
	}

}
