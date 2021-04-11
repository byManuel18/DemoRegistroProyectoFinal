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
import enums.SentenciasMedPrev;
import utilidades.ConecxionH2;

public class MedPrevDAO extends MedPrev {

	public MedPrevDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedPrevDAO(int id_medprev, LocalDate fecha, CorreIncorre estado_de_puertas, CorreIncorre estado_de_lamparas,
			CorreIncorre estado_de_desagues, CorreIncorre estado_de_limpieza, CorreIncorre plagas, Estado firmado) {
		super(id_medprev, fecha, estado_de_puertas, estado_de_lamparas, estado_de_desagues, estado_de_limpieza, plagas,
				firmado);
		// TODO Auto-generated constructor stub
	}

	public MedPrevDAO(LocalDate fecha, CorreIncorre estado_de_puertas, CorreIncorre estado_de_lamparas,
			CorreIncorre estado_de_desagues, CorreIncorre estado_de_limpieza, CorreIncorre plagas, Estado firmado) {
		super(fecha, estado_de_puertas, estado_de_lamparas, estado_de_desagues, estado_de_limpieza, plagas, firmado);
		// TODO Auto-generated constructor stub
	}

	public MedPrevDAO(MedPrev mp) {
		id_medprev = mp.id_medprev;
		fecha = mp.fecha;
		estado_de_puertas = mp.estado_de_puertas;
		estado_de_lamparas = mp.estado_de_lamparas;
		estado_de_desagues = mp.estado_de_desagues;
		estado_de_limpieza = mp.estado_de_limpieza;
		plagas = mp.plagas;
		firmado = mp.firmado;
	}

	public MedPrevDAO(int id) {
		super();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMedPrev.SELECTPORID.getSentencia());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					id_medprev = id;
					if (rs.getDate("fecha") != null) {
						fecha = rs.getDate("fecha").toLocalDate();
					} else {
						fecha = null;
					}
					estado_de_puertas = CorreIncorre.valueOf(rs.getString("estado_de_puertas_y_ventanas"));
					estado_de_lamparas = CorreIncorre.valueOf(rs.getString("estado_de_lamparas_antiinsectos"));
					estado_de_desagues = CorreIncorre.valueOf(rs.getString("estado_de_desagues"));
					estado_de_limpieza = CorreIncorre.valueOf(rs.getString("estado_de_limpieza"));
					plagas = CorreIncorre.valueOf(rs.getString("indicio_de_plagas"));
					firmado = Estado.valueOf(rs.getString("firmado"));
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
		int resul = -1;
		PreparedStatement ps = null;
		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMedPrev.DELETE.getSentencia());
			ps.setInt(1, id_medprev);
			resul = ps.executeUpdate();
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

		return resul;
	}

	public int save() {
		int result = -1;
		PreparedStatement ps = null;
		if (id_medprev > 0) {
			try {
				ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMedPrev.UPDATE.getSentencia());
				if (fecha != null) {
					ps.setDate(1, Date.valueOf(fecha));
				} else {
					ps.setDate(1, null);
				}
				ps.setString(2, estado_de_puertas.toString());
				ps.setString(3, estado_de_lamparas.toString());
				ps.setString(4, estado_de_desagues.toString());
				ps.setString(5, estado_de_limpieza.toString());
				ps.setString(6, plagas.toString());
				ps.setString(7, firmado.toString());
				ps.setInt(8, id_medprev);
				result = ps.executeUpdate();

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
				ps = ConecxionH2.getConnecMedidas().prepareStatement(SentenciasMedPrev.INSERT.getSentencia());
				if (fecha != null) {
					ps.setDate(1, Date.valueOf(fecha));
				} else {
					ps.setDate(1, null);
				}
				ps.setString(2, estado_de_puertas.toString());
				ps.setString(3, estado_de_lamparas.toString());
				ps.setString(4, estado_de_desagues.toString());
				ps.setString(5, estado_de_limpieza.toString());
				ps.setString(6, plagas.toString());
				ps.setString(7, firmado.toString());
				result = ps.executeUpdate();
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
		return result;
	}

	public static List<MedPrev> SelectAll(){
		return Select("", SentenciasMedPrev.SELECTALL);
	}
	public static List<MedPrev> SelectPorFecha(String cadena){
		return Select(cadena, SentenciasMedPrev.SELECTPORFECHA);
	}
	public static List<MedPrev> SelectPorMes(String cadena){
		return Select(cadena, SentenciasMedPrev.SELECTPORMES);
	}

	private static List<MedPrev> Select(String cadena, SentenciasMedPrev sql) {
		List<MedPrev> lista = new ArrayList<MedPrev>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = ConecxionH2.getConnecMedidas().prepareStatement(sql.getSentencia());

			if(sql==SentenciasMedPrev.SELECTPORFECHA){
				ps.setString(1,cadena);
			}

			if(sql==SentenciasMedPrev.SELECTPORMES){
				ps.setInt(1, Integer.parseInt(cadena));
			}

			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					LocalDate fe = null;
					if (rs.getDate("fecha") != null) {
						fe = rs.getDate("fecha").toLocalDate();
					}
					MedPrev nuevo = new MedPrev(rs.getInt("id"), fe,
							CorreIncorre.valueOf(rs.getString("estado_de_puertas_y_ventanas")),
							CorreIncorre.valueOf(rs.getString("estado_de_lamparas_antiinsectos")),
							CorreIncorre.valueOf(rs.getString("estado_de_desagues")),
							CorreIncorre.valueOf(rs.getString("estado_de_limpieza")),
							CorreIncorre.valueOf(rs.getString("indicio_de_plagas")),
							Estado.valueOf(rs.getString("firmado")));
					lista.add(nuevo);
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
