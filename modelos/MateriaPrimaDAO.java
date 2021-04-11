package modelos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.SentenciasMateriaPrima;
import utilidades.ConecxionH2;

public class MateriaPrimaDAO extends MateriaPrima{

	public MateriaPrimaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MateriaPrimaDAO(int id_p, LocalDate fecha_llegada, String proveedor, String mercancia, String lote,
			LocalDate fecha_in, LocalDate fecha_fin, Estado firmado) {
		super(id_p, fecha_llegada, proveedor, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public MateriaPrimaDAO(LocalDate fecha_llegada, String proveedor, String mercancia, String lote, LocalDate fecha_in,
			LocalDate fecha_fin, Estado firmado) {
		super(fecha_llegada, proveedor, mercancia, lote, fecha_in, fecha_fin, firmado);
		// TODO Auto-generated constructor stub
	}

	public MateriaPrimaDAO(MateriaPrima mp){
		id_p=mp.id_p;
		fecha_fin=mp.fecha_fin;
		fecha_in=mp.fecha_in;
		fecha_llegada=mp.fecha_llegada;
		lote=mp.lote;
		proveedor=mp.proveedor;
		mercancia=mp.mercancia;
		firmado=mp.firmado;

	}

	public MateriaPrimaDAO(int id){
		super();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasMateriaPrima.SELECTBYID.GetSentencia());
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

	public int Update(){
		int resultado=-1;
		PreparedStatement ps=null;
		if(id_p>0){
			try {
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasMateriaPrima.UPDATE.GetSentencia());
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
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasMateriaPrima.INSERT.GetSentencia());
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
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasMateriaPrima.DELETE.GetSentencia());
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
	public static List<MateriaPrima> SelectAll(){
		return Select("", -1, -1, SentenciasMateriaPrima.SELECTALL);
	}
	public static List<MateriaPrima> SelectFechaConcretaLLegada(String cadena){
		return Select(cadena, -1, -1, SentenciasMateriaPrima.SELECTFECHACONCRETALLEGADA);
	}
	public static List<MateriaPrima> SelectFechaConcretaFin(String cadena){
		return Select(cadena, -1, -1, SentenciasMateriaPrima.SELECTFECHACONCRETAFIN);
	}
	public static List<MateriaPrima> SelectFechaConcretaInicio(String cadena){
		return Select(cadena, -1, -1, SentenciasMateriaPrima.SELECTFECHACONCRETAINICIO);
	}
	public static List<MateriaPrima> SelectFechaLlegadaMes(int mes){
		return Select("", mes, -1, SentenciasMateriaPrima.SELECTSOLOMESFECHALLEGADA);
	}
	public static List<MateriaPrima> SelectFechaFinMes(int mes){
		return Select("", mes, -1, SentenciasMateriaPrima.SELECTSOLOMESFECHAFIN);
	}
	public static List<MateriaPrima> SelectFechaInicioMes(int mes){
		return Select("", mes, -1, SentenciasMateriaPrima.SELECTSOLOMESFECHAINICIO);
	}
	public static List<MateriaPrima> SelectFechaInicioAño(int año){
		return Select("", -1, año, SentenciasMateriaPrima.SELECTSOLOAÑOFECHAINICIO);
	}
	public static List<MateriaPrima> SelectFechaFinAño(int año){
		return Select("", -1, año, SentenciasMateriaPrima.SELECTSOLOAÑOFECHAFIN);
	}
	public static List<MateriaPrima> SelectFechaLlegadaAño(int año){
		return Select("", -1, año, SentenciasMateriaPrima.SELECTSOLOAÑOFECHALLEGADA);
	}
	public static List<MateriaPrima> SelectFechaLlegadaMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasMateriaPrima.SELECTSOLOMESYAÑOFECHALLEGADA);
	}
	public static List<MateriaPrima> SelectFechaFinMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasMateriaPrima.SELECTSOLOMESYAÑOFECHAFIN);
	}
	public static List<MateriaPrima> SelectFechaInicioMesyAño(int mes,int año){
		return Select("", mes, año, SentenciasMateriaPrima.SELECTSOLOMESYAÑOFECHAINICIO);
	}
	private static List<MateriaPrima> Select(String cadena,int mes, int año,SentenciasMateriaPrima sql){
		List<MateriaPrima> lista=new ArrayList<MateriaPrima>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(sql.GetSentencia());
			if(sql==SentenciasMateriaPrima.SELECTFECHACONCRETALLEGADA||sql==SentenciasMateriaPrima.SELECTFECHACONCRETAINICIO||sql==SentenciasMateriaPrima.SELECTFECHACONCRETAFIN){
				ps.setString(1, cadena);
			}
			if(sql==SentenciasMateriaPrima.SELECTSOLOMESFECHALLEGADA||sql==SentenciasMateriaPrima.SELECTSOLOMESFECHAFIN||sql==SentenciasMateriaPrima.SELECTSOLOMESFECHAINICIO){
				ps.setInt(1, mes);
			}
			if(sql==SentenciasMateriaPrima.SELECTSOLOAÑOFECHAFIN||sql==SentenciasMateriaPrima.SELECTSOLOAÑOFECHAINICIO||sql==SentenciasMateriaPrima.SELECTSOLOAÑOFECHALLEGADA){
				ps.setInt(1, año);
			}
			if(sql==SentenciasMateriaPrima.SELECTSOLOMESYAÑOFECHAINICIO||sql==SentenciasMateriaPrima.SELECTSOLOMESYAÑOFECHAFIN||sql==SentenciasMateriaPrima.SELECTSOLOMESYAÑOFECHALLEGADA){
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
					MateriaPrima nuevo=new MateriaPrima(rs.getInt("id_traza"), f_llegada,rs.getString("proveedor") ,rs.getString("mercancia"), rs.getString("lote"), f_ini, f_fin, firm);
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
