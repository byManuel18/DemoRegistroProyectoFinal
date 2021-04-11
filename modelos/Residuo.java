package modelos;

import java.time.LocalDate;

import enums.Estado;

public class Residuo implements Comparable<Residuo>{

	protected int id_residuo;
	protected LocalDate fecha;
	protected String nombre_persona;
	protected String cantidad;
	protected Estado firmado;

	public Residuo() {
		this(-1,null,"","",Estado.NO_FIRMADO);
	}

	public Residuo(LocalDate fecha, String nombre_persona, String cantidad, Estado firmado) {
		this(-1,fecha,nombre_persona,cantidad,firmado);
	}

	public Residuo(int id_residuo, LocalDate fecha, String nombre_persona, String cantidad, Estado firmado) {
		this.id_residuo = id_residuo;
		this.fecha = fecha;
		this.nombre_persona = nombre_persona;
		this.cantidad = cantidad;
		this.firmado = firmado;
	}



	public int getId_residuo() {
		return id_residuo;
	}

	public void setId_residuo(int id_residuo) {
		this.id_residuo = id_residuo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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
		result = prime * result + id_residuo;
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
		Residuo other = (Residuo) obj;
		if (id_residuo != other.id_residuo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "--> Fecha=" + fecha + ", Nombre persona=" + nombre_persona
				+ ", Cantidad=" + cantidad + ", Firmado=" + firmado;
	}

	@Override
	public int compareTo(Residuo o) {
		// TODO Auto-generated method stub
		return this.fecha.compareTo(o.fecha);
	}

}
