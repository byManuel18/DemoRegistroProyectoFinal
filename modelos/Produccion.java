package modelos;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import enums.Estado;

public class Produccion implements Comparable<Produccion>{

	protected int id_p;
	protected LocalDate fecha_lote;
	protected String nombre_pro;
	protected String cantidad;
	protected Set<SanidadProducto> lotescarnes;
	protected Set<Laure> loteslaure;
	protected Set<Salsa> lotesalsas;
	protected Set<MateriaPrima> lotesmateriaprima;
	protected Estado firmado;

	public Produccion() {
		this(-1, null,  "",  "",null,
				null, null, null,  null);
	}

	public Produccion(LocalDate fecha_lote, String nombre_pro, String cantidad, Set<SanidadProducto> lotescarnes,
			Set<Laure> loteslaure, Set<Salsa> lotesalsas, Set<MateriaPrima> lotesmateriaprima, Estado firmado) {
		this(-1, fecha_lote,  nombre_pro,  cantidad,lotescarnes,
				loteslaure, lotesalsas, lotesmateriaprima,  firmado);
	}


	public Produccion(int id_p, LocalDate fecha_lote, String nombre_pro, String cantidad, Set<SanidadProducto> lotescarnes,
			Set<Laure> loteslaure, Set<Salsa> lotesalsas, Set<MateriaPrima> lotesmateriaprima, Estado firmado) {
		this.id_p = id_p;
		this.fecha_lote = fecha_lote;
		this.nombre_pro = nombre_pro;
		this.cantidad = cantidad;
		this.lotescarnes = lotescarnes;
		this.loteslaure = loteslaure;
		this.lotesalsas = lotesalsas;
		this.lotesmateriaprima = lotesmateriaprima;
		this.firmado = firmado;
	}



	public int getId_p() {
		return id_p;
	}







	public void setId_p(int id_p) {
		this.id_p = id_p;
	}







	public LocalDate getFecha_lote() {
		return fecha_lote;
	}







	public void setFecha_lote(LocalDate fecha_lote) {
		this.fecha_lote = fecha_lote;
	}







	public String getNombre_pro() {
		return nombre_pro;
	}







	public void setNombre_pro(String nombre_pro) {
		this.nombre_pro = nombre_pro;
	}







	public String getCantidad() {
		return cantidad;
	}







	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}







	public Set<SanidadProducto> getLotescarnes() {
		return lotescarnes;
	}







	public void setLotescarnes(Set<SanidadProducto> lotescarnes) {
		this.lotescarnes = lotescarnes;
	}







	public Set<Laure> getLoteslaure() {
		return loteslaure;
	}







	public void setLoteslaure(Set<Laure> loteslaure) {
		this.loteslaure = loteslaure;
	}







	public Set<Salsa> getLotesalsas() {
		return lotesalsas;
	}







	public void setLotesalsas(Set<Salsa> lotesalsas) {
		this.lotesalsas = lotesalsas;
	}







	public Set<MateriaPrima> getLotesmateriaprima() {
		return lotesmateriaprima;
	}







	public void setLotesmateriaprima(Set<MateriaPrima> lotesmateriaprima) {
		this.lotesmateriaprima = lotesmateriaprima;
	}







	public Estado getFirmado() {
		return firmado;
	}







	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}

	public boolean AddLoteSalsa(Salsa sp){
		boolean resultado=false;
		if(lotesalsas==null){
			lotesalsas=new TreeSet<Salsa>();
		}
		resultado=lotesalsas.add(sp);
		return resultado;
	}
	public boolean AddLoteLaure(Laure sp){
		boolean resultado=false;
		if(loteslaure==null){
			loteslaure=new TreeSet<Laure>();
		}
		resultado=loteslaure.add(sp);
		return resultado;
	}
	public boolean AddLoteMateriaPrima(MateriaPrima sp){
		boolean resultado=false;
		if(lotesmateriaprima==null){
			lotesmateriaprima=new TreeSet<MateriaPrima>();
		}
		resultado=lotesmateriaprima.add(sp);
		return resultado;
	}

	public boolean AddLoteCarne(SanidadProducto sp){
		boolean resultado=false;
		if(lotescarnes==null){
			lotescarnes=new TreeSet<SanidadProducto>();
		}
		resultado=lotescarnes.add(sp);
		return resultado;
	}

	public boolean EliminarLoteCarne(SanidadProducto sp){
		boolean resultado=false;
		if(lotescarnes!=null){
			resultado=lotescarnes.remove(sp);
		}
		return resultado;
	}

	public boolean EliminarLoteMateriaPrima(MateriaPrima sp){
		boolean resultado=false;
		if(lotesmateriaprima!=null){
			resultado=lotesmateriaprima.remove(sp);
		}

		return resultado;
	}

	public boolean EliminarLoteSalsa(Salsa sp){
		boolean resultado=false;
		if(lotesalsas!=null){
			resultado=lotesalsas.remove(sp);
		}

		return resultado;
	}
	public boolean EliminarLoteLaure(Laure sp){
		boolean resultado=false;
		if(loteslaure!=null){
			resultado=loteslaure.remove(sp);
		}

		return resultado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_p;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produccion other = (Produccion) obj;
		if (id_p != other.id_p)
			return false;
		return true;
	}



	@Override
	public int compareTo(Produccion o) {
		// TODO Auto-generated method stub
		return fecha_lote.compareTo(o.fecha_lote);
	}

	@Override
	public String toString() {
		String cadenalotessalsas="";
		String cadenalotesaliños="";
		String cadenalotesmateriaprima="";
		String cadenalotescarne="";
		if(lotesalsas!=null){
			int contador=0;
			for(Salsa s:lotesalsas){
				contador++;
				cadenalotessalsas+=s.lote;
				if(lotesalsas.size()<contador){
					cadenalotessalsas+=" / ";
				}
			}
		}
		if(lotescarnes!=null){
			int contador=0;
			for(SanidadProducto s:lotescarnes){
				contador++;
				cadenalotescarne+=s.codigolote;
				if(lotescarnes.size()<contador){
					cadenalotescarne+=" / ";
				}
			}
		}
		if(loteslaure!=null){
			int contador=0;
			for(Laure s:loteslaure){
				contador++;
				cadenalotesaliños+=s.lote;
				if(loteslaure.size()<contador){
					cadenalotesaliños+=" / ";
				}
			}
		}

		if(lotesmateriaprima!=null){
			int contador=0;
			for(MateriaPrima s:lotesmateriaprima){
				contador++;
				cadenalotesmateriaprima+=s.lote;
				if(loteslaure.size()<contador){
					cadenalotesmateriaprima+=" / ";
				}
			}
		}

		return "Produccion -->" +", Fecha/Lote=" + fecha_lote + ", Nombre Producto=" + nombre_pro + ", Cantidad="
				+ cantidad + ", lotes Carnes=" + cadenalotescarne + ", Lotes Aliños=" + cadenalotesaliños + ", Lotes Salsas="
				+ cadenalotessalsas + ", Lotes Materia Prima=" + cadenalotesmateriaprima + ", Firmado=" + firmado;
	}



}
