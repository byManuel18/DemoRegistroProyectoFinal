package modelos;

import java.time.LocalDate;

import enums.CorreIncorre;
import enums.Estado;

public class Manipulador {

	protected int id_manipulador;
	protected LocalDate fecha;
	protected CorreIncorre vestimente_limpia;
	protected CorreIncorre higiene_personal;
	protected CorreIncorre buenas_practicas;
	protected CorreIncorre incidencias_acciones_correctas;
	protected Estado firmado;

	public Manipulador(){
		this(-1,null,CorreIncorre.I,CorreIncorre.I,CorreIncorre.I,CorreIncorre.I,Estado.NO_FIRMADO);
	}

	public Manipulador(LocalDate fecha, CorreIncorre vestimente_limpia,
			CorreIncorre higiene_personal, CorreIncorre buenas_practicas, CorreIncorre incidencias_acciones_correctas,
			Estado firmado) {
		this(-1,fecha,vestimente_limpia,higiene_personal,buenas_practicas,incidencias_acciones_correctas,firmado);
	}

	public Manipulador(int id_manipulador, LocalDate fecha, CorreIncorre vestimente_limpia,
			CorreIncorre higiene_personal, CorreIncorre buenas_practicas, CorreIncorre incidencias_acciones_correctas,
			Estado firmado) {
		this.id_manipulador = id_manipulador;
		this.fecha = fecha;
		this.vestimente_limpia = vestimente_limpia;
		this.higiene_personal = higiene_personal;
		this.buenas_practicas = buenas_practicas;
		this.incidencias_acciones_correctas = incidencias_acciones_correctas;
		this.firmado = firmado;
	}



	public int getId_manipulador() {
		return id_manipulador;
	}

	public void setId_manipulador(int id_manipulador) {
		this.id_manipulador = id_manipulador;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public CorreIncorre getVestimente_limpia() {
		return vestimente_limpia;
	}

	public void setVestimente_limpia(CorreIncorre vestimente_limpia) {
		this.vestimente_limpia = vestimente_limpia;
	}

	public CorreIncorre getHigiene_personal() {
		return higiene_personal;
	}

	public void setHigiene_personal(CorreIncorre higiene_personal) {
		this.higiene_personal = higiene_personal;
	}

	public CorreIncorre getBuenas_practicas() {
		return buenas_practicas;
	}

	public void setBuenas_practicas(CorreIncorre buenas_practicas) {
		this.buenas_practicas = buenas_practicas;
	}

	public CorreIncorre getIncidencias_acciones_correctas() {
		return incidencias_acciones_correctas;
	}

	public void setIncidencias_acciones_correctas(CorreIncorre incidencias_acciones_correctas) {
		this.incidencias_acciones_correctas = incidencias_acciones_correctas;
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
		result = prime * result + id_manipulador;
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
		Manipulador other = (Manipulador) obj;
		if (id_manipulador != other.id_manipulador)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manipulador [id_manipulador=" + id_manipulador + ", fecha=" + fecha + ", vestimente_limpia="
				+ vestimente_limpia + ", higiene_personal=" + higiene_personal + ", buenas_practicas="
				+ buenas_practicas + ", incidencias_acciones_correctas=" + incidencias_acciones_correctas + ", firmado="
				+ firmado + "]";
	}





}
