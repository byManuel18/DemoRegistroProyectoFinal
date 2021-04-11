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
import enums.SentenciasTemperatura;
import utilidades.ConecxionH2;

public class TemperaturaDAO extends Temperatura{

	private static Connection conn=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos();

	public TemperaturaDAO() {
		super();
	}

	public TemperaturaDAO(int id_tempe, LocalDate fecha, String vitrina_espositora, String armario_frigorifico,
			String armario_productos_cocinados, String armario_congelador, String alcon_congelador, Estado firmado) {
		super(id_tempe, fecha, vitrina_espositora, armario_frigorifico, armario_productos_cocinados, armario_congelador,
				alcon_congelador, firmado);
	}

	public TemperaturaDAO(LocalDate fecha, String vitrina_espositora, String armario_frigorifico,
			String armario_productos_cocinados, String armario_congelador, String alcon_congelador, Estado firmado) {
		super(-1,fecha, vitrina_espositora, armario_frigorifico, armario_productos_cocinados, armario_congelador, alcon_congelador,
				firmado);
	}

	public TemperaturaDAO(Temperatura t){
		id_tempe=t.id_tempe;
		fecha=t.fecha;
		vitrina_espositora=t.vitrina_espositora;
		armario_frigorifico=t.armario_frigorifico;
		armario_congelador=t.armario_congelador;
		armario_productos_cocinados=t.armario_productos_cocinados;
		alcon_congelador=t.alcon_congelador;
		firmado=t.firmado;

	}

	public TemperaturaDAO(int id){
		super();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=conn.prepareStatement(SentenciasTemperatura.SELECTPORID.getSentencia());
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					id_tempe=id;
					if(rs.getDate("fecha")!=null){
						fecha=rs.getDate("fecha").toLocalDate();
					}else{
						fecha=null;
					}
					if(rs.getString("vitrina_espositora")!=null){
						vitrina_espositora=rs.getString("vitrina_espositora");
					}else{
						vitrina_espositora=null;
					}
					if(rs.getString("armario_frigorifico")!=null){
						armario_frigorifico=rs.getString("armario_frigorifico");
					}else{
						armario_frigorifico=null;
					}
					if(rs.getString("armario_congelador")!=null){
						armario_congelador=rs.getString("armario_congelador");
					}else{
						armario_congelador=null;
					}
					if(rs.getString("armario_productos_cocinados")!=null){
						armario_productos_cocinados=rs.getString("armario_productos_cocinados");
					}else{
						armario_productos_cocinados=null;
					}
					if(rs.getString("alcon_congelador")!=null){
						alcon_congelador=rs.getString("alcon_congelador");
					}else{
						alcon_congelador=null;
					}
					if(rs.getString("firmado")!=null){
						firmado=Estado.valueOf(rs.getString("firmado"));
					}else{
						firmado=null;
					}

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

	public int save(){
		int resultado=-1;
		PreparedStatement ps=null;
		if(id_tempe>0){
			try {
				ps=conn.prepareStatement(SentenciasTemperatura.UPDATE.getSentencia());
				if(fecha==null){
					ps.setDate(1, null);
				}else{
					ps.setDate(1, Date.valueOf(fecha));
				}
				if(vitrina_espositora!=null){
					ps.setString(2, vitrina_espositora);
				}else{
					ps.setString(2, null);
				}
				if(armario_frigorifico!=null){
					ps.setString(3, armario_frigorifico);
				}else{
					ps.setString(3, null);
				}
				if(armario_congelador!=null){
					ps.setString(4, armario_congelador);
				}else{
					ps.setString(4, null);
				}
				if(armario_productos_cocinados!=null){
					ps.setString(5, armario_productos_cocinados);
				}else{
					ps.setString(5, null);
				}
				if(alcon_congelador!=null){
					ps.setString(6, alcon_congelador);
				}else{
					ps.setString(6, null);
				}

				if(firmado==null){
					ps.setString(7, null);
				}else{
					ps.setString(7, firmado.toString());
				}
				ps.setInt(8, id_tempe);
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
				ps=conn.prepareStatement(SentenciasTemperatura.INSERT.getSentencia());
				if(fecha==null){
					ps.setDate(1, null);
				}else{
					ps.setDate(1, Date.valueOf(fecha));
				}
				if(vitrina_espositora!=null){
					ps.setString(2, vitrina_espositora);
				}else{
					ps.setString(2, null);
				}
				if(armario_frigorifico!=null){
					ps.setString(3, armario_frigorifico);
				}else{
					ps.setString(3, null);
				}
				if(armario_congelador!=null){
					ps.setString(4, armario_congelador);
				}else{
					ps.setString(4, null);
				}
				if(armario_productos_cocinados!=null){
					ps.setString(5, armario_productos_cocinados);
				}else{
					ps.setString(5, null);
				}
				if(alcon_congelador!=null){
					ps.setString(6, alcon_congelador);
				}else{
					ps.setString(6, null);
				}
				if(firmado==null){
					ps.setString(7, null);
				}else{
					ps.setString(7, firmado.toString());
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

	public int remove(){
		int resultado=-1;
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(SentenciasTemperatura.DELETE.getSentencia());
			ps.setInt(1, id_tempe);
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
	public static List<Temperatura> SelectAll(){
		return Select("", SentenciasTemperatura.SELECTALL);
	}
	public static List<Temperatura> SelectPorFecha(String cadena){
		return Select(cadena, SentenciasTemperatura.SELECPORFECHA);
	}
	public static List<Temperatura> SelectPorMes(String cadena){
		return Select(cadena, SentenciasTemperatura.SELECTPORMES);
	}

	private static List<Temperatura> Select(String cadena,SentenciasTemperatura sql){
		List<Temperatura> lista=new ArrayList<Temperatura>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=conn.prepareStatement(sql.getSentencia());

			if(sql==SentenciasTemperatura.SELECPORFECHA){
				ps.setString(1, cadena);
			}

			if(sql==SentenciasTemperatura.SELECTPORMES){
				ps.setDate(1,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), 1)));
				ps.setDate(2,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena),1).lengthOfMonth())));
			}

			rs=ps.executeQuery();

			if(rs!=null){
				while(rs.next()){
					LocalDate n_fecha=null;
					if(rs.getDate("fecha")!=null){
						n_fecha=rs.getDate("fecha").toLocalDate();
					}
					Estado f=null;
					if(rs.getString("firmado")!=null){
						f=Estado.valueOf(rs.getString("firmado"));
					}
					Temperatura nuevo=new Temperatura(rs.getInt("id"), n_fecha, rs.getString("vitrina_espositora"), rs.getString("armario_frigorifico"), rs.getString("armario_productos_cocinados"), rs.getString("armario_congelador"), rs.getString("alcon_congelador"), f);
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
