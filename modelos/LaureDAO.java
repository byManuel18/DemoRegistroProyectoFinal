package modelos;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.SentenciasLaure;
import utilidades.ConecxionH2;

public class LaureDAO extends Laure{

	public LaureDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LaureDAO(int id_p, LocalDate fecha_llegada, String mercancia, String lote, LocalDate fecha_in,
			LocalDate fecha_fin, Estado firmado) {
		super(id_p, fecha_llegada, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public LaureDAO(LocalDate fecha_llegada, String mercancia, String lote, LocalDate fecha_in, LocalDate fecha_fin,
			Estado firmado) {
		super(fecha_llegada, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public LaureDAO(Laure L){
		id_p=L.id_p;
		fecha_llegada=L.fecha_llegada;
		proveedor=L.proveedor;
		mercancia=L.mercancia;
		lote=L.lote;
		fecha_fin=L.fecha_fin;
		fecha_in=L.fecha_in;
		firmado=L.firmado;
	}

	public LaureDAO(int id){
		super();
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasLaure.SELECTBYID.getSentencia());
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

	public int remuve(){
		int resultado=-1;
		PreparedStatement ps=null;
		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasLaure.DELETE.getSentencia());
			ps.setInt(1, id_p);
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

		return resultado;
	}

	public int update(){
		int resultadddo=-1;
		PreparedStatement ps=null;

		if(id_p>0){
			try {
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasLaure.UPDATE.getSentencia());
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
				resultadddo=ps.executeUpdate();
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
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasLaure.INSERT.getSentencia());
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
				resultadddo=ps.executeUpdate();
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
		return resultadddo;
	}

	public static List<Laure> SelectAll(){
		return Select("", -1, -1, SentenciasLaure.SELECTALL);
	}
	public static List<Laure> SelectFechaConcretaLLegada(String cadena){
		return Select(cadena, -1, -1, SentenciasLaure.SELECTFECHACONCRETALLEGADA);
	}
	public static List<Laure> SelectFechaConcretaFin(String cadena){
		return Select(cadena, -1, -1, SentenciasLaure.SELECTFECHACONCRETAFIN);
	}
	public static List<Laure> SelectFechaConcretaInicio(String cadena){
		return Select(cadena, -1, -1, SentenciasLaure.SELECTFECHACONCRETAINICIO);
	}
	public static List<Laure> SelectFechaLlegadaMes(int mes){
		return Select("", mes, -1, SentenciasLaure.SELECTSOLOMESFECHALLEGADA);
	}
	public static List<Laure> SelectFechaFinMes(int mes){
		return Select("", mes, -1, SentenciasLaure.SELECTSOLOMESFECHAFIN);
	}
	public static List<Laure> SelectFechaInicioMes(int mes){
		return Select("", mes, -1, SentenciasLaure.SELECTSOLOMESFECHAINICIO);
	}
	public static List<Laure> SelectFechaInicioAño(int año){
		return Select("", -1, año, SentenciasLaure.SELECTSOLOAÑOFECHAINICIO);
	}
	public static List<Laure> SelectFechaFinAño(int año){
		return Select("", -1, año, SentenciasLaure.SELECTSOLOAÑOFECHAFIN);
	}
	public static List<Laure> SelectFechaLlegadaAño(int año){
		return Select("", -1, año, SentenciasLaure.SELECTSOLOAÑOFECHALLEGADA);
	}
	public static List<Laure> SelectFechaLlegadaMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasLaure.SELECTSOLOMESYAÑOFECHALLEGADA);
	}
	public static List<Laure> SelectFechaFinMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasLaure.SELECTSOLOMESYAÑOFECHAFIN);
	}
	public static List<Laure> SelectFechaInicioMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasLaure.SELECTSOLOMESYAÑOFECHAINICIO);
	}

	private static List<Laure> Select(String cadena,int mes,int ano, SentenciasLaure sql){
		List<Laure> lista=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(sql.getSentencia());
			if(sql==SentenciasLaure.SELECTFECHACONCRETALLEGADA||sql==SentenciasLaure.SELECTFECHACONCRETAINICIO||sql==SentenciasLaure.SELECTFECHACONCRETAFIN){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasLaure.SELECTSOLOMESFECHALLEGADA||sql==SentenciasLaure.SELECTSOLOMESFECHAFIN||sql==SentenciasLaure.SELECTSOLOMESFECHAINICIO){
				ps.setInt(1, mes);
			}
			if(sql==SentenciasLaure.SELECTSOLOAÑOFECHAFIN||sql==SentenciasLaure.SELECTSOLOAÑOFECHAINICIO||sql==SentenciasLaure.SELECTSOLOAÑOFECHALLEGADA){
				ps.setInt(1, ano);
			}
			if(sql==SentenciasLaure.SELECTSOLOMESYAÑOFECHAINICIO||sql==SentenciasLaure.SELECTSOLOMESYAÑOFECHAFIN||sql==SentenciasLaure.SELECTSOLOMESYAÑOFECHALLEGADA){
				ps.setInt(1, mes);
				ps.setInt(2, ano);
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
					Laure nuevo=new Laure(rs.getInt("id_traza"), f_llegada, rs.getString("mercancia"), rs.getString("lote"), f_ini, f_fin, firm);
					lista.add(nuevo);

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
		return lista;
	}

}
