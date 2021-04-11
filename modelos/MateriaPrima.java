package modelos;

import java.time.LocalDate;

import enums.Estado;

public class MateriaPrima implements Comparable<MateriaPrima>{
	protected int id_p;
	protected LocalDate fecha_llegada;
	protected String proveedor;
	protected String mercancia;
	protected String lote;
	protected LocalDate fecha_in;
	protected LocalDate fecha_fin;
	protected Estado firmado;


	public MateriaPrima() {

		this(-1,null,  "",  "",  "",
				 null,  null,  null);
	}

	public MateriaPrima(LocalDate fecha_llegada, String proveedor, String mercancia, String lote,
			LocalDate fecha_in, LocalDate fecha_fin, Estado firmado) {

		this(-1,fecha_llegada,  proveedor,  mercancia,  lote,
				 fecha_in,  fecha_fin,  firmado);
	}

	public MateriaPrima(int id_p, LocalDate fecha_llegada, String proveedor, String mercancia, String lote,
			LocalDate fecha_in, LocalDate fecha_fin, Estado firmado) {
		this.id_p = id_p;
		this.fecha_llegada = fecha_llegada;
		this.proveedor = proveedor;
		this.mercancia = mercancia;
		this.lote = lote;
		this.fecha_in = fecha_in;
		this.fecha_fin = fecha_fin;
		this.firmado = firmado;
	}
	public int getId_p() {
		return id_p;
	}
	public void setId_p(int id_p) {
		this.id_p = id_p;
	}
	public LocalDate getFecha_llegada() {
		return fecha_llegada;
	}
	public void setFecha_llegada(LocalDate fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getMercancia() {
		return mercancia;
	}
	public void setMercancia(String mercancia) {
		this.mercancia = mercancia;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public LocalDate getFecha_in() {
		return fecha_in;
	}
	public void setFecha_in(LocalDate fecha_in) {
		this.fecha_in = fecha_in;
	}
	public LocalDate getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(LocalDate fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Estado getFirmado() {
		return firmado;
	}
	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}
	@Override
	public String toString() {
		return "Materia Prima -->"+", Fecha Llegada=" + fecha_llegada + ", Proveedor=" + proveedor
				+ ", Mercancia=" + mercancia + ", Lote=" + lote + ", Fecha Inicio=" + fecha_in + ", Fecha Fin=" + fecha_fin
				+ ", Firmado=" + firmado;
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
		MateriaPrima other = (MateriaPrima) obj;
		if (id_p != other.id_p)
			return false;
		return true;
	}

	@Override
	public int compareTo(MateriaPrima o) {
		// TODO Auto-generated method stub
		return fecha_llegada.compareTo(o.fecha_llegada);
	}


}
