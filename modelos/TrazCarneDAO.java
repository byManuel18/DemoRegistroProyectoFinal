package modelos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.SentenciasTrazCarne;
import utilidades.ConecxionH2;

public class TrazCarneDAO extends TrazCarne{

	public TrazCarneDAO() {
		super();

	}

	public TrazCarneDAO(SanidadProducto procar, int id, LocalDate fecha_inicio, LocalDate fehca_fin, Estado firmado) {
		super(procar, id, fecha_inicio, fehca_fin, firmado);

	}

	public TrazCarneDAO(SanidadProducto procar, LocalDate fecha_inicio, LocalDate fehca_fin, Estado firmado) {
		super(procar, fecha_inicio, fehca_fin, firmado);

	}

	public TrazCarneDAO(TrazCarne tc){
		id=tc.id;
		procar=tc.procar;
		fecha_inicio=tc.fecha_inicio;
		fehca_fin=tc.fehca_fin;
		firmado=tc.firmado;
	}

	public TrazCarneDAO(int id){
		super();
		ResultSet rs=null;
		PreparedStatement ps=null;
		SanidadProducto n=new SanidadProducto();

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasTrazCarne.SELECTBYID.getSentencia());
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					this.id=id;
					if(rs.getDate("fecha_inicio")!=null){
						fecha_inicio=rs.getDate("fecha_inicio").toLocalDate();
					}else{
						fecha_inicio=null;
					}
					if(rs.getDate("fecha_fin")!=null){

					}else{
						fehca_fin=null;
					}
					if(rs.getString("firmado")!=null){
						firmado=Estado.valueOf(rs.getString("firmado"));
					}else{
						firmado=null;
					}
					SanidadDAO baja=new SanidadDAO(rs.getInt("id_carne"));
					n.setId(baja.id);
					n.setEstado(baja.estado);
					n.setFecha(baja.fecha);
					n.setCodigolote(baja.codigolote);
					n.setNombre_producto(baja.nombre_producto);
					n.setProveedor(baja.proveedor);
					this.procar=n;
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

		if(id<0){
			try {
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasTrazCarne.INSERT.getSentencia());
				ps.setInt(1,procar.id);
				if(fecha_inicio!=null){
					ps.setDate(2, Date.valueOf(fecha_inicio));
				}else{
					ps.setDate(2, null);
				}
				if(fehca_fin!=null){
					ps.setDate(3, Date.valueOf(fehca_fin));
				}else{
					ps.setDate(3, null);
				}
				if(firmado!=null){
					ps.setString(4, firmado.toString());
				}else{
					ps.setString(4, null);
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

		}else{
			try {
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasTrazCarne.UPDATE.getSentencia());
				ps.setInt(1,procar.id);
				if(fecha_inicio!=null){
					ps.setDate(2, Date.valueOf(fecha_inicio));
				}else{
					ps.setDate(2, null);
				}
				if(fehca_fin!=null){
					ps.setDate(3, Date.valueOf(fehca_fin));
				}else{
					ps.setDate(3, null);
				}
				if(firmado!=null){
					ps.setString(4, firmado.toString());
				}else{
					ps.setString(4, null);
				}
				ps.setInt(5, id);
				resultado=ps.executeUpdate();
			} catch (SQLException e) {

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
		int resultado=-1;
		PreparedStatement ps=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasTrazCarne.DELETE.getSentencia());
			ps.setInt(1, id);
			resultado=ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public static List<TrazCarne> SelectAll(){
		return Select("",-1,-1, SentenciasTrazCarne.SELECTALL);
	}
	public static List<TrazCarne> SelectMesYAño(int mes,int ano){
		return Select("",mes,ano, SentenciasTrazCarne.SELECTPORMESYAÑO);
	}
	public static List<TrazCarne> SelectMes(int mes){
		return Select("",mes,-1, SentenciasTrazCarne.SELECTSOLOMES);
	}
	public static List<TrazCarne> SelectAno(int ano){
		return Select("",-1,ano, SentenciasTrazCarne.SELECTSOLOAÑO);
	}
	public static List<TrazCarne> SelectLlegadaCarne(String cadena){
		return Select(cadena, -1, -1, SentenciasTrazCarne.SELECTFECGALLEGADACARNE);
	}
	public static List<TrazCarne> SelectFechaFin(String cadena){
		return Select(cadena, -1, -1, SentenciasTrazCarne.SELECTFECHAFIN);
	}
	public static List<TrazCarne> SelectFechaIni(String cadena){
		return Select(cadena, -1, -1, SentenciasTrazCarne.SELECTFECHAINICIO);
	}

	private static List<TrazCarne> Select(String cadena,int mes,int ano, SentenciasTrazCarne sql){
		List<TrazCarne> lista=new ArrayList<TrazCarne>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(sql.getSentencia());

			if(sql==SentenciasTrazCarne.SELECTPORMESYAÑO){
				ps.setInt(1, mes);
				ps.setInt(2, ano);
			}
			if(sql==SentenciasTrazCarne.SELECTSOLOMES){
				ps.setInt(1, mes);
			}
			if(sql==SentenciasTrazCarne.SELECTSOLOAÑO){
				ps.setInt(1, ano);
			}
			if(sql==SentenciasTrazCarne.SELECTFECGALLEGADACARNE||sql==SentenciasTrazCarne.SELECTFECHAFIN||sql==SentenciasTrazCarne.SELECTFECHAINICIO){
				ps.setString(1, cadena);
			}

			rs=ps.executeQuery();

			if(rs!=null){
				while(rs.next()){
					SanidadDAO cp=new SanidadDAO(rs.getInt("id_carne"));
					TrazCarne nuevo=new TrazCarne();
					nuevo.setId(rs.getInt("id_traza"));
					if(rs.getDate("fecha_inicio")!=null){
						nuevo.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());
					}else{
						nuevo.setFecha_inicio(null);
					}
					if(rs.getDate("fecha_fin")!=null){
						nuevo.setFehca_fin(rs.getDate("fecha_fin").toLocalDate());
					}else{
						nuevo.setFehca_fin(null);
					}
					if(rs.getString("firmado")!=null){
						nuevo.setFirmado(Estado.valueOf(rs.getString("firmado")));
					}else{
						nuevo.setFirmado(null);
					}
					nuevo.setProcar(cp);
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
