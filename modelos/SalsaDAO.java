package modelos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.SentenciasSalsas;
import utilidades.ConecxionH2;

public class SalsaDAO extends Salsa {

	public SalsaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalsaDAO(int id_p, LocalDate fecha_llegada, String proveedor, String mercancia, String lote,
			LocalDate fecha_in, LocalDate fecha_fin, Estado firmado) {
		super(id_p, fecha_llegada, proveedor, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public SalsaDAO(LocalDate fecha_llegada, String proveedor, String mercancia, String lote, LocalDate fecha_in,
			LocalDate fecha_fin, Estado firmado) {
		super(fecha_llegada, proveedor, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public SalsaDAO(Salsa s){
		id_p=s.id_p;
		fecha_llegada=s.fecha_llegada;
		fecha_fin=s.fecha_fin;
		fecha_in=s.fecha_in;
		lote=s.lote;
		proveedor=s.proveedor;
		mercancia=s.mercancia;
		firmado=s.firmado;
	}

	public SalsaDAO(int id){
		super();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSalsas.SELECTBYID.getSentenia());
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
					lote=rs.getString("lote");
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
	}

	public int update(){
		int resultado=-1;
		PreparedStatement ps=null;
		if(id_p>0){
			try {
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSalsas.UPDATE.getSentenia());
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
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSalsas.INSERT.getSentenia());
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
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasSalsas.DELETE.getSentenia());
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
	public static List<Salsa> SelectAll(){
		return Select("", -1, -1, SentenciasSalsas.SELECTALL);
	}
	public static List<Salsa> SelectFechaConcretaLLegada(String cadena){
		return Select(cadena, -1, -1, SentenciasSalsas.SELECTFECHACONCRETALLEGADA);
	}
	public static List<Salsa> SelectFechaConcretaFin(String cadena){
		return Select(cadena, -1, -1, SentenciasSalsas.SELECTFECHACONCRETAFIN);
	}
	public static List<Salsa> SelectFechaConcretaInicio(String cadena){
		return Select(cadena, -1, -1, SentenciasSalsas.SELECTFECHACONCRETAINICIO);
	}
	public static List<Salsa> SelectFechaLlegadaMes(int mes){
		return Select("", mes, -1, SentenciasSalsas.SELECTSOLOMESFECHALLEGADA);
	}
	public static List<Salsa> SelectFechaFinMes(int mes){
		return Select("", mes, -1, SentenciasSalsas.SELECTSOLOMESFECHAFIN);
	}
	public static List<Salsa> SelectFechaInicioMes(int mes){
		return Select("", mes, -1, SentenciasSalsas.SELECTSOLOMESFECHAINICIO);
	}
	public static List<Salsa> SelectFechaInicioAño(int año){
		return Select("", -1, año, SentenciasSalsas.SELECTSOLOAÑOFECHAINICIO);
	}
	public static List<Salsa> SelectFechaFinAño(int año){
		return Select("", -1, año, SentenciasSalsas.SELECTSOLOAÑOFECHAFIN);
	}
	public static List<Salsa> SelectFechaLlegadaAño(int año){
		return Select("", -1, año, SentenciasSalsas.SELECTSOLOAÑOFECHALLEGADA);
	}
	public static List<Salsa> SelectFechaLlegadaMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasSalsas.SELECTSOLOMESYAÑOFECHALLEGADA);
	}
	public static List<Salsa> SelectFechaFinMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasSalsas.SELECTSOLOMESYAÑOFECHAFIN);
	}
	public static List<Salsa> SelectFechaInicioMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasSalsas.SELECTSOLOMESYAÑOFECHAINICIO);
	}
	private static List<Salsa> Select(String cadena,int mes, int año,SentenciasSalsas sql){
		List<Salsa> lista=new ArrayList<Salsa>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(sql.getSentenia());
			if(sql==SentenciasSalsas.SELECTFECHACONCRETALLEGADA||sql==SentenciasSalsas.SELECTFECHACONCRETAINICIO||sql==SentenciasSalsas.SELECTFECHACONCRETAFIN){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasSalsas.SELECTSOLOMESFECHALLEGADA||sql==SentenciasSalsas.SELECTSOLOMESFECHAFIN||sql==SentenciasSalsas.SELECTSOLOMESFECHAINICIO){
				ps.setInt(1, mes);
			}
			if(sql==SentenciasSalsas.SELECTSOLOAÑOFECHAFIN||sql==SentenciasSalsas.SELECTSOLOAÑOFECHAINICIO||sql==SentenciasSalsas.SELECTSOLOAÑOFECHALLEGADA){
				ps.setInt(1, año);
			}
			if(sql==SentenciasSalsas.SELECTSOLOMESYAÑOFECHAINICIO||sql==SentenciasSalsas.SELECTSOLOMESYAÑOFECHAFIN||sql==SentenciasSalsas.SELECTSOLOMESYAÑOFECHALLEGADA){
				ps.setInt(1, mes);
				ps.setInt(2, año);
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
					Salsa nuevo=new Salsa(rs.getInt("id_traza"), f_llegada,rs.getString("proveedor") ,rs.getString("mercancia"), rs.getString("lote"), f_ini, f_fin, firm);
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
