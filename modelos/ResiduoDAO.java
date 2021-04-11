package modelos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.SentenciasResiduos;
import utilidades.ConecxionH2;

public class ResiduoDAO extends Residuo {

	public ResiduoDAO() {
		super();
	}

	public ResiduoDAO(int id_residuo, LocalDate fecha, String nombre_persona, String cantidad, Estado firmado) {
		super(id_residuo, fecha, nombre_persona, cantidad, firmado);

	}

	public ResiduoDAO(LocalDate fecha, String nombre_persona, String cantidad, Estado firmado) {
		super(-1, fecha, nombre_persona, cantidad, firmado);
	}

	public ResiduoDAO(int id) {
		super();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos()
					.prepareStatement(SentenciasResiduos.SELECTPORID.getSentencia());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					id_residuo = id;
					if (rs.getDate("fecha") != null) {
						fecha = rs.getDate("fecha").toLocalDate();
					} else {
						fecha = null;
					}
					nombre_persona = rs.getString("persona");
					cantidad = rs.getString("cantidad");
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

	public ResiduoDAO(Residuo r) {
		id_residuo = r.id_residuo;
		fecha = r.fecha;
		firmado = r.firmado;
		nombre_persona = r.nombre_persona;
		cantidad = r.cantidad;
	}

	public int save(){
		int resultado=-1;
		PreparedStatement ps=null;
		if(id_residuo>0){
			try {
				ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(SentenciasResiduos.UPDATE.getSentencia());
				if(fecha!=null){
					ps.setDate(1,Date.valueOf(fecha));
				}else{
					ps.setDate(1, null);
				}
				ps.setString(2, nombre_persona);
				ps.setString(3, cantidad);
				ps.setString(4, firmado.toString());
				ps.setInt(5, id_residuo);
				resultado=ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(SentenciasResiduos.INSERT.getSentencia());
				if(fecha!=null){
					ps.setDate(1,Date.valueOf(fecha));
				}else{
					ps.setDate(1, null);
				}
				ps.setString(2, nombre_persona);
				ps.setString(3, cantidad);
				ps.setString(4, firmado.toString());
				resultado=ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
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

	public int remuve() {
		int resultado = -1;
		PreparedStatement ps = null;

		try {
			ps = ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos()
					.prepareStatement(SentenciasResiduos.DELETE.getSentencia());
			ps.setInt(1, id_residuo);
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

	public static List<Residuo> SelectAll(){
		return Select("", SentenciasResiduos.SELECTALL);
	}
	public static List<Residuo> SelectMes(String cadena){
		return Select(cadena, SentenciasResiduos.SELECTPORMES);
	}
	public static List<Residuo> SelectPorFecha(String cadena){
		return Select(cadena, SentenciasResiduos.SELECTPORFECHA);
	}


	private static List<Residuo> Select(String cadena, SentenciasResiduos sql){
		List<Residuo> lista=new ArrayList<Residuo>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(sql.getSentencia());

			if(sql==SentenciasResiduos.SELECTPORFECHA){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasResiduos.SELECTPORMES){
				ps.setDate(1,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), 1)));
				ps.setDate(2,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena),1).lengthOfMonth())));
			}

			rs=ps.executeQuery();
			if(rs!=null){
				while(rs.next()){
					LocalDate fe=null;
					if(rs.getDate("fecha")!=null){
						fe=rs.getDate("fecha").toLocalDate();
					}
					Residuo nuevo=new Residuo(rs.getInt("id"), fe, rs.getString("persona"), rs.getString("cantidad"), Estado.valueOf(rs.getString("firmado")));
					lista.add(nuevo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
