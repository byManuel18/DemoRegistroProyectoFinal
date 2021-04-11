package modelos;

import java.time.LocalDate;

import enums.CorreIncorre;
import enums.Estado;

public class MedPrev implements Comparable<MedPrev>{
	protected int id_medprev;
	protected LocalDate fecha;
	protected CorreIncorre estado_de_puertas;
	protected CorreIncorre estado_de_lamparas;
	protected CorreIncorre estado_de_desagues;
	protected CorreIncorre estado_de_limpieza;
	protected CorreIncorre plagas;
	protected Estado firmado;

	public MedPrev(){
		this(-1,null,CorreIncorre.I,CorreIncorre.I,CorreIncorre.I,CorreIncorre.I,CorreIncorre.I,Estado.NO_FIRMADO);
	}

	public MedPrev(LocalDate fecha, CorreIncorre estado_de_puertas, CorreIncorre estado_de_lamparas,
			CorreIncorre estado_de_desagues, CorreIncorre estado_de_limpieza, CorreIncorre plagas, Estado firmado) {

		this(-1,fecha,estado_de_puertas,estado_de_lamparas,estado_de_desagues,estado_de_limpieza,plagas,firmado);
	}



	public MedPrev(int id_medprev, LocalDate fecha, CorreIncorre estado_de_puertas, CorreIncorre estado_de_lamparas,
			CorreIncorre estado_de_desagues, CorreIncorre estado_de_limpieza, CorreIncorre plagas, Estado firmado) {
		this.id_medprev = id_medprev;
		this.fecha = fecha;
		this.estado_de_puertas = estado_de_puertas;
		this.estado_de_lamparas = estado_de_lamparas;
		this.estado_de_desagues = estado_de_desagues;
		this.estado_de_limpieza = estado_de_limpieza;
		this.plagas = plagas;
		this.firmado = firmado;
	}

	public int getId_medprev() {
		return id_medprev;
	}

	public void setId_medprev(int id_medprev) {
		this.id_medprev = id_medprev;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public CorreIncorre getEstado_de_puertas() {
		return estado_de_puertas;
	}

	public void setEstado_de_puertas(CorreIncorre estado_de_puertas) {
		this.estado_de_puertas = estado_de_puertas;
	}

	public CorreIncorre getEstado_de_lamparas() {
		return estado_de_lamparas;
	}

	public void setEstado_de_lamparas(CorreIncorre estado_de_lamparas) {
		this.estado_de_lamparas = estado_de_lamparas;
	}

	public CorreIncorre getEstado_de_desagues() {
		return estado_de_desagues;
	}

	public void setEstado_de_desagues(CorreIncorre estado_de_desagues) {
		this.estado_de_desagues = estado_de_desagues;
	}

	public CorreIncorre getEstado_de_limpieza() {
		return estado_de_limpieza;
	}

	public void setEstado_de_limpieza(CorreIncorre estado_de_limpieza) {
		this.estado_de_limpieza = estado_de_limpieza;
	}

	public CorreIncorre getPlagas() {
		return plagas;
	}

	public void setPlagas(CorreIncorre plagas) {
		this.plagas = plagas;
	}

	public Estado getFirmado() {
		return firmado;
	}

	public void setFirmado(Estado firmado) {
		this.firmado = firmado;
	}

	@Override
	public String toString() {
		return "--> Fecha=" + fecha + ", Estado de puertas=" + estado_de_puertas
				+ ", Estado de lamparas=" + estado_de_lamparas + ", Estado de desagues=" + estado_de_desagues
				+ ", Estado de limpieza=" + estado_de_limpieza + ", Plagas=" + plagas + ", Firmado=" + firmado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_medprev;
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
		MedPrev other = (MedPrev) obj;
		if (id_medprev != other.id_medprev)
			return false;
		return true;
	}

	@Override
	public int compareTo(MedPrev o) {
		// TODO Auto-generated method stub
		return this.fecha.compareTo(o.fecha);
	}



}
