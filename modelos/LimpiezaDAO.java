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
import enums.Mirado;
import enums.SentenciasLimpieza;
import utilidades.ConecxionH2;

public class LimpiezaDAO extends Limpieza{

	public LimpiezaDAO() {
		super();
	}

	public LimpiezaDAO(int id_limpieza, LocalDate fecha, Mirado obrador, Mirado aseo, Mirado venta, Mirado picadora,
			Mirado embutidora, Mirado sierra, Mirado cuchillos_y_utensilios, Mirado frigorifico_materia_prima,
			Mirado frigorifico_producto_terminado, Mirado contenedor_residuos, Estado firmado) {
		super(id_limpieza, fecha, obrador, aseo, venta, picadora, embutidora, sierra, cuchillos_y_utensilios,
				frigorifico_materia_prima, frigorifico_producto_terminado, contenedor_residuos, firmado);
	}

	public LimpiezaDAO(LocalDate fecha, Mirado obrador, Mirado aseo, Mirado venta, Mirado picadora, Mirado embutidora,
			Mirado sierra, Mirado cuchillos_y_utensilios, Mirado frigorifico_materia_prima,
			Mirado frigorifico_producto_terminado, Mirado contenedor_residuos, Estado firmado) {
		super(fecha, obrador, aseo, venta, picadora, embutidora, sierra, cuchillos_y_utensilios, frigorifico_materia_prima,
				frigorifico_producto_terminado, contenedor_residuos, firmado);
	}

	public LimpiezaDAO(Limpieza l){
		id_limpieza=l.id_limpieza;
		fecha=l.fecha;
		obrador=l.obrador;
		aseo=l.aseo;
		venta=l.venta;
		picadora=l.picadora;
		embutidora=l.embutidora;
		sierra=l.sierra;
		cuchillos_y_utensilios=l.cuchillos_y_utensilios;
		frigorifico_materia_prima=l.frigorifico_materia_prima;
		frigorifico_producto_terminado=l.frigorifico_producto_terminado;
		contenedor_residuos=l.contenedor_residuos;
		firmado=l.firmado;
	}

	public LimpiezaDAO(int id){
		super();
		Connection conn=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos();
		PreparedStatement ps=null;
		ResultSet rs = null;

		try {
			ps=conn.prepareStatement(SentenciasLimpieza.SELECTPORID.getSentencia());
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					id_limpieza=id;
					if(rs.getDate("fecha")!=null){
						fecha=rs.getDate("fecha").toLocalDate();
					}else{
						fecha=null;
					}
					obrador=Mirado.valueOf(rs.getString("obrador"));
					aseo=Mirado.valueOf(rs.getString("aseo"));
					venta=Mirado.valueOf(rs.getString("venta"));
					picadora=Mirado.valueOf(rs.getString("picadora"));
					embutidora=Mirado.valueOf(rs.getString("embutidora"));
					sierra=Mirado.valueOf(rs.getString("sierra"));
					cuchillos_y_utensilios=Mirado.valueOf(rs.getString("cuchillos_y_utensilios"));
					frigorifico_materia_prima=Mirado.valueOf(rs.getString("frigorifico_materia_prima"));
					frigorifico_producto_terminado=Mirado.valueOf(rs.getString("frigorifico_producto_terminado"));
					contenedor_residuos=Mirado.valueOf(rs.getString("contenedor_residuos"));
					firmado=Estado.valueOf(rs.getString("firmado"));
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
	}

	public int save(){
		int resultado=0;
		PreparedStatement ps=null;
		Connection conn=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos();
		if(id_limpieza>0){
			try {
				ps=conn.prepareStatement(SentenciasLimpieza.UPDATE.getSentencia());
				if(fecha==null){
					ps.setDate(1, null);
				}else{
					ps.setDate(1, Date.valueOf(fecha));
				}

				ps.setString(2,obrador.toString());
				ps.setString(3,aseo.toString());
				ps.setString(4,venta.toString());
				ps.setString(5,picadora.toString());
				ps.setString(6,embutidora.toString());
				ps.setString(7,sierra.toString());
				ps.setString(8,cuchillos_y_utensilios.toString());
				ps.setString(9,frigorifico_materia_prima.toString());
				ps.setString(10,frigorifico_producto_terminado.toString());
				ps.setString(11,contenedor_residuos.toString());
				ps.setString(12,firmado.toString());
				ps.setInt(13, id_limpieza);
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
				ps=conn.prepareStatement(SentenciasLimpieza.INSERT.getSentencia());
				if(fecha==null){
					ps.setDate(1, null);
				}else{
					ps.setDate(1, Date.valueOf(fecha));
				}

				ps.setString(2,obrador.toString());
				ps.setString(3,aseo.toString());
				ps.setString(4,venta.toString());
				ps.setString(5,picadora.toString());
				ps.setString(6,embutidora.toString());
				ps.setString(7,sierra.toString());
				ps.setString(8,cuchillos_y_utensilios.toString());
				ps.setString(9,frigorifico_materia_prima.toString());
				ps.setString(10,frigorifico_producto_terminado.toString());
				ps.setString(11,contenedor_residuos.toString());
				ps.setString(12,firmado.toString());
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
		int resultado=0;
		PreparedStatement ps=null;
		try {
			ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(SentenciasLimpieza.DELETE.getSentencia());
			ps.setInt(1, id_limpieza);
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

	public static List<Limpieza> ListarAll(){
		return Select("", SentenciasLimpieza.SELECTALL);
	}
	public static List<Limpieza> ListarPorFecha(String cadena){
		return Select(cadena, SentenciasLimpieza.SELECTPORFECHA);
	}
	public static List<Limpieza> ListarPorMes(String cadena){
		return Select(cadena, SentenciasLimpieza.SELECTPORMES);
	}

	private static List<Limpieza> Select(String cadena,SentenciasLimpieza sql){
		List<Limpieza> lista=new ArrayList<Limpieza>();
		PreparedStatement ps = null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecLimpieza_Agua_Temperatura_Residuos().prepareStatement(sql.getSentencia());
			if(sql==SentenciasLimpieza.SELECTPORFECHA){
				ps.setString(1, cadena);
			}

			if(sql==SentenciasLimpieza.SELECTPORMES){
				ps.setDate(1,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), 1)));
				ps.setDate(2,Date.valueOf(LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena), LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(cadena),1).lengthOfMonth())));
			}

			rs=ps.executeQuery();
			if(rs!=null){
				while(rs.next()){
					LocalDate introfecha=null;
					if(rs.getDate("fecha")!=null){
						introfecha=rs.getDate("fecha").toLocalDate();
					}
					Limpieza nuevo=new Limpieza(rs.getInt("id"), introfecha, Mirado.valueOf(rs.getString("obrador")), Mirado.valueOf(rs.getString("aseo")),Mirado.valueOf(rs.getString("venta")), Mirado.valueOf(rs.getString("picadora")), Mirado.valueOf(rs.getString("embutidora")), Mirado.valueOf(rs.getString("sierra")), Mirado.valueOf(rs.getString("cuchillos_y_utensilios")),Mirado.valueOf(rs.getString("frigorifico_materia_prima")) , Mirado.valueOf(rs.getString("frigorifico_producto_terminado")), Mirado.valueOf(rs.getString("contenedor_residuos")),Estado.valueOf(rs.getString("firmado")));
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
