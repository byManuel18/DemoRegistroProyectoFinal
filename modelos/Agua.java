package modelos;

import java.time.LocalDate;

import enums.Estado;
import enums.EstadoAgua;

public class Agua implements Comparable<Agua> {

	protected int id_agua;
	protected LocalDate fecha;
	protected String punto_muestreo;
	protected String control_organoleptico;
	protected EstadoAgua estado;
	protected Estado firmado;

	public Agua() {
		this(-1,"","",EstadoAgua.M,Estado.NO_FIRMADO,null);
	}

	public Agua(String punto_muestreo, String control_organoleptico, EstadoAgua estado, Estado firmado,LocalDate fecha) {
		this(-1,punto_muestreo,control_organoleptico,estado,firmado,fecha);
	}

	public Agua(int id_agua, String punto_muestreo, String control_organoleptico, EstadoAgua estado, Estado firmado,LocalDate fecha) {
		this.id_agua = id_agua;
		this.punto_muestreo = punto_muestreo;
		this.control_organoleptico = control_organoleptico;
		this.estado = estado;
		this.firmado = firmado;
		this.fecha=fecha;
	}



	public int getId_agua() {
		return id_agua;
	}



	public void setId_agua(int id_agua) {
		this.id_agua = id_agua;
	}



	public String getPunto_muestreo() {
		return punto_muestreo;
	}



	public void setPunto_muestreo(String punto_muestreo) {
		this.punto_muestreo = punto_muestreo;
	}



	public String getControl_organoleptico() {
		return control_organoleptico;
	}



	public void setControl_organoleptico(String control_organoleptico) {
		this.control_organoleptico = control_organoleptico;
	}



	public EstadoAgua getEstado() {
		return estado;
	}



	public void setEstado(EstadoAgua estado) {
		this.estado = estado;
	}



	public Estado getFirmado() {
		return firmado;
	}



	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}





	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_agua;
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
		Agua other = (Agua) obj;
		if (id_agua != other.id_agua)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " Registro Agua --> Fecha=" + fecha + ", Punto Muestreo=" + punto_muestreo
				+ ", Control Organoleptico=" + control_organoleptico + ", Estado=" + estado + ", Firmado=" + firmado+" ";
	}

	@Override
	public int compareTo(Agua o) {
		return this.fecha.compareTo(o.fecha);
	}







}
