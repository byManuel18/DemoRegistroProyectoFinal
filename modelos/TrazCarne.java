package modelos;

import java.time.LocalDate;

import enums.Estado;

public class TrazCarne implements Comparable<TrazCarne> {
	protected SanidadProducto procar;
	protected int id;
	protected LocalDate fecha_inicio;
	protected LocalDate fehca_fin;
	protected Estado firmado;

	public TrazCarne(SanidadProducto procar, int id, LocalDate fecha_inicio, LocalDate fehca_fin, Estado firmado) {

		this.procar = procar;
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fehca_fin = fehca_fin;
		this.firmado = firmado;
	}

	public TrazCarne(SanidadProducto procar, LocalDate fecha_inicio, LocalDate fehca_fin, Estado firmado) {
		this(procar,-1,fecha_inicio,fehca_fin,firmado);
	}

	public TrazCarne() {
		this(null,-1,null,null,null);
	}

	public SanidadProducto getProcar() {
		return procar;
	}

	public void setProcar(SanidadProducto procar) {
		this.procar = procar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFehca_fin() {
		return fehca_fin;
	}

	public void setFehca_fin(LocalDate fehca_fin) {
		this.fehca_fin = fehca_fin;
	}

	public Estado getFirmado() {
		return firmado;
	}

	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((procar == null) ? 0 : procar.hashCode());
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
		TrazCarne other = (TrazCarne) obj;
		if (id != other.id)
			return false;
		if (procar == null) {
			if (other.procar != null)
				return false;
		} else if (!procar.equals(other.procar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRAZABILIDAD CARNE --> Producto=" + procar  + ", Fecha Inicio=" + fecha_inicio + ", Fecha Fin="
				+ fehca_fin + ", Firmado=" + firmado;
	}

	@Override
	public int compareTo(TrazCarne o) {
		// TODO Auto-generated method stub
		return procar.fecha.compareTo(o.procar.fecha);
	}








}
