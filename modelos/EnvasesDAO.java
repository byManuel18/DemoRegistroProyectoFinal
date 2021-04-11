package modelos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.SentenciasEnvases;
import utilidades.ConecxionH2;

public class EnvasesDAO extends Envase{

	public EnvasesDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnvasesDAO(int id_p, LocalDate fecha_llegada, String proveedor, String mercancia, String lote,
			LocalDate fecha_in, LocalDate fecha_fin, Estado firmado) {
		super(id_p, fecha_llegada, proveedor, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public EnvasesDAO(LocalDate fecha_llegada, String proveedor, String mercancia, String lote, LocalDate fecha_in,
			LocalDate fecha_fin, Estado firmado) {
		super(fecha_llegada, proveedor, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public EnvasesDAO(Envase e){
		id_p=e.id_p;
		fecha_llegada=e.fecha_llegada;
		fecha_fin=e.fecha_fin;
		fecha_in=e.fecha_in;
		lote=e.lote;
		proveedor=e.proveedor;
		mercancia=e.mercancia;
		firmado=e.firmado;
	}

	public EnvasesDAO(int id){
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasEnvases.SELECTBYID.getSentencia());
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					id_p=id;
					if(rs.getDate("fecha_llegada")!=null){
						fecha_llegada=rs.getDate("fecha_llegada").toLocalDate();
					}else{
						fecha_llegada=null;
					}
					if(rs.getDate("fecha_inicio")!=null){
						fecha_in=rs.getDate("fecha_inicio").toLocalDate();
					}else{
						fecha_in=null;
					}
					if(rs.getDate("fecha_fin")!=null){
						fecha_fin=rs.getDate("fecha_fin").toLocalDate();
					}else{
						fecha_fin=null;
					}
					if(rs.getString("firmado")!=null){
						firmado=Estado.valueOf((rs.getString("firmado")));
					}else{
						firmado=null;
					}
					proveedor=rs.getString("proveedor");
					mercancia=rs.getString("mercancia");
					proveedor=rs.getString("lote");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int update(){
		int resultado=-1;
		PreparedStatement ps=null;
		if(id_p>0){
			try {
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasEnvases.UPDATE.getSentencia());
				if(fecha_llegada!=null){
					ps.setDate(1, Date.valueOf(fecha_llegada));
				}else{
					ps.setDate(1,null);
				}
				if(fecha_in!=null){
					ps.setDate(2, Date.valueOf(fecha_in));
				}else{
					ps.setDate(2, null);
				}
				if(fecha_fin!=null){
					ps.setDate(3, Date.valueOf(fecha_fin));
				}else{
					ps.setDate(3, null);
				}
				ps.setString(4, proveedor);
				ps.setString(5, mercancia);
				ps.setString(6, lote);
				if(firmado!=null){
					ps.setString(7, firmado.toString());
				}else{
					ps.setString(7, null);
				}
				ps.setInt(8,id_p);
				resultado=ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(ps!=null){
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
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasEnvases.INSERT.getSentencia());
				if(fecha_llegada!=null){
					ps.setDate(1, Date.valueOf(fecha_llegada));
				}else{
					ps.setDate(1,null);
				}
				if(fecha_in!=null){
					ps.setDate(2, Date.valueOf(fecha_in));
				}else{
					ps.setDate(2, null);
				}
				if(fecha_fin!=null){
					ps.setDate(3, Date.valueOf(fecha_fin));
				}else{
					ps.setDate(3, null);
				}
				ps.setString(4, proveedor);
				ps.setString(5, mercancia);
				ps.setString(6, lote);
				if(firmado!=null){
					ps.setString(7, firmado.toString());
				}else{
					ps.setString(7, null);
				}
				resultado=ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(ps!=null){
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

	public int remuve(){
		int resUltado=-1;
		PreparedStatement ps=null;
		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasEnvases.DELETE.getSentencia());
			ps.setInt(1, id_p);
			resUltado=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resUltado;
	}
	public static List<Envase> SelectAll(){
		return Select("", -1, -1, SentenciasEnvases.SELECTALL);
	}
	public static List<Envase> SelectFechaConcretaLLegada(String cadena){
		return Select(cadena, -1, -1, SentenciasEnvases.SELECTFECHACONCRETALLEGADA);
	}
	public static List<Envase> SelectFechaConcretaFin(String cadena){
		return Select(cadena, -1, -1, SentenciasEnvases.SELECTFECHACONCRETAFIN);
	}
	public static List<Envase> SelectFechaConcretaInicio(String cadena){
		return Select(cadena, -1, -1, SentenciasEnvases.SELECTFECHACONCRETAINICIO);
	}
	public static List<Envase> SelectFechaLlegadaMes(int mes){
		return Select("", mes, -1, SentenciasEnvases.SELECTSOLOMESFECHALLEGADA);
	}
	public static List<Envase> SelectFechaFinMes(int mes){
		return Select("", mes, -1, SentenciasEnvases.SELECTSOLOMESFECHAFIN);
	}
	public static List<Envase> SelectFechaInicioMes(int mes){
		return Select("", mes, -1, SentenciasEnvases.SELECTSOLOMESFECHAINICIO);
	}
	public static List<Envase> SelectFechaInicioA�o(int a�o){
		return Select("", -1, a�o, SentenciasEnvases.SELECTSOLOA�OFECHAINICIO);
	}
	public static List<Envase> SelectFechaFinA�o(int a�o){
		return Select("", -1, a�o, SentenciasEnvases.SELECTSOLOA�OFECHAFIN);
	}
	public static List<Envase> SelectFechaLlegadaA�o(int a�o){
		return Select("", -1, a�o, SentenciasEnvases.SELECTSOLOA�OFECHALLEGADA);
	}
	public static List<Envase> SelectFechaLlegadaMesyA�o(int mes,int a�o){
		return Select("", mes, a�o, SentenciasEnvases.SELECTSOLOMESYA�OFECHALLEGADA);
	}
	public static List<Envase> SelectFechaFinMesyA�o(int mes,int a�o){
		return Select("", mes, a�o, SentenciasEnvases.SELECTSOLOMESYA�OFECHAFIN);
	}
	public static List<Envase> SelectFechaInicioMesyA�o(int mes,int a�o){
		return Select("", mes, a�o, SentenciasEnvases.SELECTSOLOMESYA�OFECHAINICIO);
	}
	private static List<Envase> Select(String cadena,int mes, int a�o,SentenciasEnvases sql){
		List<Envase> lista=new ArrayList<Envase>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(sql.getSentencia());
			if(sql==SentenciasEnvases.SELECTFECHACONCRETALLEGADA||sql==SentenciasEnvases.SELECTFECHACONCRETAINICIO||sql==SentenciasEnvases.SELECTFECHACONCRETAFIN){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasEnvases.SELECTSOLOMESFECHALLEGADA||sql==SentenciasEnvases.SELECTSOLOMESFECHAFIN||sql==SentenciasEnvases.SELECTSOLOMESFECHAINICIO){
				ps.setInt(1, mes);
			}
			if(sql==SentenciasEnvases.SELECTSOLOA�OFECHAFIN||sql==SentenciasEnvases.SELECTSOLOA�OFECHAINICIO||sql==SentenciasEnvases.SELECTSOLOA�OFECHALLEGADA){
				ps.setInt(1, a�o);
			}
			if(sql==SentenciasEnvases.SELECTSOLOMESYA�OFECHAINICIO||sql==SentenciasEnvases.SELECTSOLOMESYA�OFECHAFIN||sql==SentenciasEnvases.SELECTSOLOMESYA�OFECHALLEGADA){
				ps.setInt(1, mes);
				ps.setInt(2, a�o);
			}
			rs=ps.executeQuery();

			if(rs!=null){
				while(rs.next()){
					LocalDate f_llegada=null;
					LocalDate f_fin=null;
					LocalDate f_ini=null;
					Estado firm=null;
					if(rs.getDate("fecha_llegada")!=null){
						f_llegada=rs.getDate("fecha_llegada").toLocalDate();
					}
					if(rs.getDate("fecha_inicio")!=null){
						f_ini=rs.getDate("fecha_inicio").toLocalDate();
					}
					if(rs.getDate("fecha_fin")!=null){
						f_fin=rs.getDate("fecha_fin").toLocalDate();
					}
					if(rs.getString("firmado")!=null){
						firm=Estado.valueOf((rs.getString("firmado")));
					}
					Envase nuevo=new Envase(rs.getInt("id_traza"), f_llegada,rs.getString("proveedor") ,rs.getString("mercancia"), rs.getString("lote"), f_ini, f_fin, firm);
					lista.add(nuevo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null){
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
