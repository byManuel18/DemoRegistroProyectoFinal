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
import enums.EstadoAgua;
import enums.SentenciasAgua;
import utilidades.ConecxionH2;

public class AguaDAO extends Agua{

	public AguaDAO(){
		super(-1, "", "", EstadoAgua.M, Estado.NO_FIRMADO,null);

	}

	public AguaDAO(int id_agua, String punto_muestreo, String control_organoleptico, EstadoAgua estado,
			Estado firmado,LocalDate fecha) {
		super(id_agua, punto_muestreo, control_organoleptico, estado, firmado,fecha);


	}

	public AguaDAO(String punto_muestreo, String control_organoleptico, EstadoAgua estado, Estado firmado,LocalDate fecha) {
		super(-1,punto_muestreo, control_organoleptico, estado, firmado,fecha);

	}

	public AguaDAO(Agua ag){
		id_agua=ag.id_agua;
		punto_muestreo=ag.punto_muestreo;
		control_organoleptico=ag.control_organoleptico;
		estado=ag.estado;
		firmado=ag.firmado;
		fecha=ag.fecha;
	}

	public AguaDAO(int id){
		super();


		Connection consa = ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps=consa.prepareStatement(SentenciasAgua.SELECTUNOPORID.getSenten());
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					id_agua=id;
					punto_muestreo=rs.getString("punto_muestreo");
					control_organoleptico=rs.getString("control_organoleptico");
					estado=EstadoAgua.valueOf(rs.getString("estado"));
					firmado=Estado.valueOf(rs.getString("firmado"));
					if(rs.getDate("fecha")!=null){
						fecha=rs.getDate("fecha").toLocalDate();
					}else{
						fecha=null;
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}



	public int Save(){
		int resultado=-1;
		PreparedStatement ps = null;
		if(id_agua>0){
			try {
				ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(SentenciasAgua.UPDATE.getSenten());
				if(fecha!=null){
					ps.setDate(1,Date.valueOf(fecha));
				}else{
					ps.setDate(1,null);
				}
				ps.setString(2, punto_muestreo);
				ps.setString(3, control_organoleptico);
				ps.setString(4, estado.toString());
				ps.setString(5, firmado.toString());
				ps.setInt(6, id_agua);
				resultado=ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(SentenciasAgua.INSERT.getSenten());
				if(fecha!=null){
					ps.setDate(1,Date.valueOf(fecha));
				}else{
					ps.setDate(1,null);
				}
				ps.setString(2, punto_muestreo);
				ps.setString(3, control_organoleptico);
				ps.setString(4, estado.toString());
				ps.setString(5, firmado.toString());
				resultado=ps.executeUpdate();
			} catch (SQLException e) {

			}finally {
				if(ps!=null){
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

	public int remuve(){
		int resultado=-1;
		PreparedStatement ps = null;
		try {
			ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(SentenciasAgua.DELETE.getSenten());
			ps.setInt(1, id_agua);
			resultado=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public static List<Agua> SelectAguaAll(){
		return SelectAgua("", SentenciasAgua.SELECTALL);
	}

	public static List<Agua> SelectAguaFecha(String cadena){
		return SelectAgua(cadena, SentenciasAgua.SELECPORFECHA);
	}

	public static List<Agua> SelectAguaMes(String cadena){
		return SelectAgua(cadena, SentenciasAgua.SELECTPORMES);
	}

	private static List<Agua> SelectAgua(String cadena,SentenciasAgua sel){
		List<Agua> lista=new ArrayList<Agua>();
		PreparedStatement ps = null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(sel.getSenten());

			if(sel==SentenciasAgua.SELECPORFECHA){
				ps.setString(1, cadena);
			}

			if(sel==SentenciasAgua.SELECTPORMES){

				ps.setDate(1,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), 1)));
				ps.setDate(2,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena),1).lengthOfMonth())));
			}


			rs=ps.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Agua n=new Agua(rs.getInt("id"), rs.getString("punto_muestreo"), rs.getString("control_organoleptico"), EstadoAgua.valueOf(rs.getString("estado")), Estado.valueOf(rs.getString("firmado")),rs.getDate("fecha").toLocalDate());
					lista.add(n);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return lista;
	}


}
