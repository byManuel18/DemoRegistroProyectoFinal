package modelos;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import enums.Estado;
import enums.SentenciasProduccion;
import utilidades.ConecxionH2;

public class ProduccionDAO extends Produccion {

	public ProduccionDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProduccionDAO(int id_p, LocalDate fecha_lote, String nombre_pro, String cantidad, Set<SanidadProducto> lotescarnes,
			Set<Laure> loteslaure, Set<Salsa> lotesalsas, Set<MateriaPrima> lotesmateriaprima, Estado firmado) {
		super(id_p, fecha_lote, nombre_pro, cantidad, lotescarnes, loteslaure, lotesalsas, lotesmateriaprima, firmado);
		// TODO Auto-generated constructor stub
	}

	public ProduccionDAO(LocalDate fecha_lote, String nombre_pro, String cantidad, Set<SanidadProducto> lotescarnes,
			Set<Laure> loteslaure, Set<Salsa> lotesalsas, Set<MateriaPrima> lotesmateriaprima, Estado firmado) {
		super(fecha_lote, nombre_pro, cantidad, lotescarnes, loteslaure, lotesalsas, lotesmateriaprima, firmado);
		// TODO Auto-generated constructor stub
	}

	public ProduccionDAO(Produccion p){
		id_p=p.id_p;
		fecha_lote=p.fecha_lote;
		nombre_pro=p.nombre_pro;
		cantidad=p.cantidad;
		lotescarnes=p.lotescarnes;
		lotesalsas=p.lotesalsas;
		loteslaure=p.loteslaure;
		lotesmateriaprima=p.lotesmateriaprima;
		firmado=p.firmado;
	}

	public ProduccionDAO(int id){
		super();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasProduccion.SELECTBYID.getSentencia());
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					id_p=id;
					fecha_lote=rs.getDate("fecha_lote").toLocalDate();
					nombre_pro=rs.getString("nombre");
					cantidad=rs.getString("cantidad");
					Set<SanidadProducto> listalotescarne=null;
					Array arraycarne=rs.getArray("lotes_carnes");
					if(arraycarne!=null){
						Object[] lotescarnes=(Object[])arraycarne.getArray();

						if(lotescarnes!=null){
							listalotescarne=new TreeSet<>();
							for(Object i:lotescarnes){
								SanidadDAO nuevo= new SanidadDAO(Integer.parseInt(i.toString()));
								if(nuevo.id>0){
									SanidadProducto add=new SanidadProducto(nuevo.getFecha(), nuevo.getNombre_producto(), nuevo.getProveedor(), nuevo.getEstado(), nuevo.getCodigolote(), nuevo.getId());
									listalotescarne.add(add);
								}
							}

						}
					}
					Set<Laure> listaloteslaure=null;
					Array arrayaliños=rs.getArray("lotes_laure");
					if(arrayaliños!=null){
						Object[] lotesaliños=(Object[])arrayaliños.getArray();

						if(lotesaliños!=null){
							for(Object i:lotesaliños){
								listaloteslaure=new TreeSet<>();
								LaureDAO nuevo=new LaureDAO(Integer.parseInt(i.toString()));
								if(nuevo.id_p>0){
									Laure add=new Laure(nuevo.id_p, nuevo.fecha_llegada, nuevo.mercancia, nuevo.getLote(), nuevo.fecha_in, nuevo.fecha_fin, nuevo.firmado);
									listaloteslaure.add(add);
								}
							}

						}
					}
					Set<MateriaPrima> listalotmate=null;
					Array arraymateriaprima =rs.getArray("lotes_materiaprima");
					if(arraymateriaprima!=null){
						Object[] lotesmate=(Object[])arraymateriaprima.getArray();

						if(lotesmate!=null){
							listalotmate=new TreeSet<>();
							for(Object i:lotesmate ){
								MateriaPrimaDAO nuevo=new MateriaPrimaDAO(Integer.parseInt(i.toString()));
								if(nuevo.id_p>0){
									MateriaPrima add=new MateriaPrima(nuevo.id_p, nuevo.fecha_llegada, nuevo.proveedor, nuevo.mercancia, nuevo.lote, nuevo.fecha_in, nuevo.fecha_fin, nuevo.firmado);
									listalotmate.add(add);
								}
							}

						}
					}

					Array arraysalsas=rs.getArray("lotes_salsas");
					Set<Salsa> listalotesalsas=null;
					if(arraysalsas!=null){
						Object[] lotessalsas=(Object[])arraysalsas.getArray();

						if(lotessalsas!=null){
							listalotesalsas=new TreeSet<>();
							for(Object i:lotessalsas){
								SalsaDAO nuevo=new SalsaDAO(Integer.parseInt(i.toString()));
								if(nuevo.id_p>0){
									Salsa add=new Salsa(nuevo.id_p, nuevo.fecha_llegada, nuevo.proveedor, nuevo.proveedor, nuevo.lote, nuevo.fecha_in, nuevo.fecha_fin, nuevo.firmado);
									listalotesalsas.add(add);
								}

							}
						}
					}
					lotesalsas=listalotesalsas;
					lotescarnes=listalotescarne;
					loteslaure=listaloteslaure;
					lotesmateriaprima=listalotmate;
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
				ps=ConecxionH2.getConnecSanidad().prepareCall(SentenciasProduccion.UPDATE.getSentencia());
				if(fecha_lote!=null){
					ps.setDate(1, Date.valueOf(fecha_lote));
				}else{
					ps.setDate(1, null);
				}

				ps.setString(2, nombre_pro);
				ps.setString(3, cantidad);
				if(lotescarnes!=null){
					Object[] lc=new Object[lotescarnes.size()];
					int con=0;
					for(SanidadProducto s:lotescarnes){
						lc[con]=s.id;
						con++;
					}
					ps.setArray(4, ConecxionH2.getConnecSanidad().createArrayOf("lotes_carnes", lc));
				}else{
					ps.setObject(4, null);
				}

				if(lotesalsas!=null){
					Object[] lc=new Object[lotesalsas.size()];
					int con=0;
					for(Salsa s:lotesalsas){
						lc[con]=s.id_p;
						con++;
					}
					ps.setArray(5, ConecxionH2.getConnecSanidad().createArrayOf("lotes_salsas", lc));
				}else{
					ps.setObject(5, null);
				}

				if(loteslaure!=null){
					Object[] lc=new Object[loteslaure.size()];
					int con=0;
					for(Laure s:loteslaure){
						lc[con]=s.id_p;
						con++;
					}
					ps.setArray(6, ConecxionH2.getConnecSanidad().createArrayOf("lotes_laure", lc));
				}else{
					ps.setObject(6, null);
				}

				if(lotesmateriaprima!=null){
					Object[] lc=new Object[lotesmateriaprima.size()];
					int con=0;
					for(MateriaPrima s:lotesmateriaprima){
						lc[con]=s.id_p;
						con++;
					}
					ps.setArray(7, ConecxionH2.getConnecSanidad().createArrayOf("lotes_materiaprima", lc));
				}else{
					ps.setObject(7, null);
				}

				if(firmado!=null){
					ps.setString(8, firmado.toString());
				}else{
					ps.setString(8, null);
				}
				ps.setInt(9, id_p);
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
				ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasProduccion.INSERT.getSentencia());
				if(fecha_lote!=null){
					ps.setDate(1, Date.valueOf(fecha_lote));
				}else{
					ps.setDate(1, null);
				}

				ps.setString(2, nombre_pro);
				ps.setString(3, cantidad);
				if(lotescarnes!=null){
					Object[] lc=new Object[lotescarnes.size()];
					int con=0;
					for(SanidadProducto s:lotescarnes){
						lc[con]=s.id;
						con++;
					}
					ps.setArray(4, ConecxionH2.getConnecSanidad().createArrayOf("lotes_carnes", lc));
				}else{
					ps.setObject(4, null);
				}

				if(lotesalsas!=null){
					Object[] lc=new Object[lotesalsas.size()];
					int con=0;
					for(Salsa s:lotesalsas){
						lc[con]=s.id_p;
						con++;
					}
					ps.setArray(5, ConecxionH2.getConnecSanidad().createArrayOf("lotes_salsas", lc));
				}else{
					ps.setObject(5, null);
				}

				if(loteslaure!=null){
					Object[] lc=new Object[loteslaure.size()];
					int con=0;
					for(Laure s:loteslaure){
						lc[con]=s.id_p;
						con++;
					}
					ps.setArray(6, ConecxionH2.getConnecSanidad().createArrayOf("lotes_laure", lc));
				}else{
					ps.setObject(6, null);
				}

				if(lotesmateriaprima!=null){
					Object[] lc=new Object[lotesmateriaprima.size()];
					int con=0;
					for(MateriaPrima s:lotesmateriaprima){
						lc[con]=s.id_p;
						con++;
					}
					ps.setArray(7, ConecxionH2.getConnecSanidad().createArrayOf("lotes_materiaprima", lc));
				}else{
					ps.setObject(7, null);
				}

				if(firmado!=null){
					ps.setString(8, firmado.toString());
				}else{
					ps.setString(8, null);
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
		int resultado=-1;
		PreparedStatement ps=null;
		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(SentenciasProduccion.DELETE.getSentencia());
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

	public static List<Produccion> SelectAll(){
		return Select("", -1, -1, SentenciasProduccion.SELECTALL);
	}
	public static List<Produccion> SelectFechaConcreta(String cadena){
		return Select(cadena, -1, -1, SentenciasProduccion.SELECTPORFECHACONCRETA);
	}
	public static List<Produccion> SelectMes(int mes){
		return Select("",mes, -1, SentenciasProduccion.SELECTPORMES);
	}

	public static List<Produccion> SelectAño(int años){
		return Select("", -1, años, SentenciasProduccion.SELECTPORAÑO);
	}

	public static List<Produccion> SelectMesyAño(int mes,int años){
		return Select("", mes, años, SentenciasProduccion.SELECTMESYAÑO);
	}


	private static List<Produccion> Select(String cadena, int mes, int año, SentenciasProduccion sql){
		List<Produccion> lista=new ArrayList<Produccion>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=ConecxionH2.getConnecSanidad().prepareStatement(sql.getSentencia());

			if(sql==SentenciasProduccion.SELECTPORFECHACONCRETA){
				ps.setString(1, cadena);
			}else if(sql==SentenciasProduccion.SELECTMESYAÑO){
				ps.setInt(1, mes);
				ps.setInt(2, año);
			}else if(sql==SentenciasProduccion.SELECTPORAÑO){
				ps.setInt(1, año);
			}else if(sql==SentenciasProduccion.SELECTPORMES){
				ps.setInt(1, mes);
			}


			rs=ps.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Estado firma=null;
					LocalDate fecha=null;
					if(rs.getString("firmado")!=null){
						firma=Estado.valueOf(rs.getString("firmado"));
					}
					if(rs.getDate("fecha_lote")!=null){
						fecha=rs.getDate("fecha_lote").toLocalDate();
					}
					Set<SanidadProducto> listalotescarne=null;
					Array arraycarne=rs.getArray("lotes_carnes");
					if(arraycarne!=null){
						Object[] lotescarnes=(Object[])arraycarne.getArray();

						if(lotescarnes!=null){
							listalotescarne=new TreeSet<>();
							for(Object i:lotescarnes){
								SanidadDAO nuevo= new SanidadDAO(Integer.parseInt(i.toString()));
								if(nuevo.id>0){
									SanidadProducto add=new SanidadProducto(nuevo.getFecha(), nuevo.getNombre_producto(), nuevo.getProveedor(), nuevo.getEstado(), nuevo.getCodigolote(), nuevo.getId());
									listalotescarne.add(add);
								}
							}

						}
					}
					Set<Laure> listaloteslaure=null;
					Array arrayaliños=rs.getArray("lotes_laure");
					if(arrayaliños!=null){
						Object[] lotesaliños=(Object[])arrayaliños.getArray();

						if(lotesaliños!=null){
							for(Object i:lotesaliños){
								listaloteslaure=new TreeSet<>();
								LaureDAO nuevo=new LaureDAO(Integer.parseInt(i.toString()));
								if(nuevo.id_p>0){
									Laure add=new Laure(nuevo.id_p, nuevo.fecha_llegada, nuevo.mercancia, nuevo.getLote(), nuevo.fecha_in, nuevo.fecha_fin, nuevo.firmado);
									listaloteslaure.add(add);
								}
							}

						}
					}
					Set<MateriaPrima> listalotmate=null;
					Array arraymateriaprima =rs.getArray("lotes_materiaprima");
					if(arraymateriaprima!=null){
						Object[] lotesmate=(Object[])arraymateriaprima.getArray();

						if(lotesmate!=null){
							listalotmate=new TreeSet<>();
							for(Object i:lotesmate ){
								MateriaPrimaDAO nuevo=new MateriaPrimaDAO(Integer.parseInt(i.toString()));
								if(nuevo.id_p>0){
									MateriaPrima add=new MateriaPrima(nuevo.id_p, nuevo.fecha_llegada, nuevo.proveedor, nuevo.mercancia, nuevo.lote, nuevo.fecha_in, nuevo.fecha_fin, nuevo.firmado);
									listalotmate.add(add);
								}
							}

						}
					}

					Array arraysalsas=rs.getArray("lotes_salsas");
					Set<Salsa> listalotesalsas=null;
					if(arraysalsas!=null){
						Object[] lotessalsas=(Object[])arraysalsas.getArray();

						if(lotessalsas!=null){
							listalotesalsas=new TreeSet<>();
							for(Object i:lotessalsas){
								SalsaDAO nuevo=new SalsaDAO(Integer.parseInt(i.toString()));
								if(nuevo.id_p>0){
									Salsa add=new Salsa(nuevo.id_p, nuevo.fecha_llegada, nuevo.proveedor, nuevo.proveedor, nuevo.lote, nuevo.fecha_in, nuevo.fecha_fin, nuevo.firmado);
									listalotesalsas.add(add);
								}

							}
						}
					}


					Produccion nuevo=new Produccion(rs.getInt("id_pro"), fecha, rs.getString("nombre"), rs.getString("cantidad"), listalotescarne, listaloteslaure, listalotesalsas, listalotmate, firma);
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
